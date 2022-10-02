package com.example.bankapp.service;

import com.example.bankapp.dto.*;
import com.example.bankapp.exception.CustomerNotFoundException;
import com.example.bankapp.model.Account;
import com.example.bankapp.model.Customer;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter accountDtoConverter;


    private final DirectExchange exchange;

    private final AmqpTemplate rabbitTemplate;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("firstRoute")
    String routingKey;

    @Value("firstStepQueue")
    String queueName;

    @CachePut(value = "accounts", key = "#id")
    public AccountDto createAccount(CreateAccountRequest createAccountRequest){
        Customer customer = customerService.getCustomerById(createAccountRequest.getCustomerId());

        if (customer.getId() == null || customer.getId().trim().equals("")){
            throw new CustomerNotFoundException("Customer Not Found!");
        }

        Account account = Account.builder()
                .id(createAccountRequest.getId())
                .balance(createAccountRequest.getBalance())
                .currency(createAccountRequest.getCurrency())
                .customerId(createAccountRequest.getCustomerId())
                .city(createAccountRequest.getCity())
                .build();

        return accountDtoConverter.convert(accountRepository.save(account));
    }

    @CacheEvict(value = "accounts", allEntries = true)
    public AccountDto updateAccount(String id, UpdateAccountRequest request){
        Customer customer = customerService.getCustomerById(request.getCustomerId());
        if (customer.getId().equals("") || customer.getId() == null){
            return AccountDto.builder().build();
        }

        Optional<Account> optionalAccount = accountRepository.findById(id);
        optionalAccount.ifPresent(account -> {
            account.setBalance(request.getBalance());
            account.setCity(request.getCity());
            account.setCurrency(request.getCurrency());
            account.setCustomerId(request.getCustomerId());
            accountRepository.save(account);
        });

        return optionalAccount.map(accountDtoConverter::convert).orElse(new AccountDto());
    }

    @Cacheable(value = "accounts")
    public List<AccountDto> getAllAccountDto(){
        List<Account> accountList = accountRepository.findAll();

        return accountList.stream().map(accountDtoConverter::convert).collect(Collectors.toList());
    }

    public AccountDto getAccountById(String id){
        return accountRepository.findById(id)
                .map(accountDtoConverter::convert)
                .orElse(new AccountDto());
    }

    @CacheEvict(value = "accounts", allEntries = true)
    public void deleteAccount(String id){
        accountRepository.deleteById(id);
    }

    public AccountDto withdrawMoney(String id, Double amount){
        Optional<Account> accountOptional = accountRepository.findById(id);
        accountOptional.ifPresent(account -> {
            if(account.getBalance() >= amount){
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
            }else{
                System.out.println("Insufficent funds -> accountId: " + id + "balance: " +account.getBalance()+
                        " amount: " + amount);
            }
        });

        return accountOptional.map(accountDtoConverter::convert).orElse(new AccountDto());
    }

    public AccountDto addMoney(String id, Double amount){
        Optional<Account> accountOptional = accountRepository.findById(id);

        accountOptional.ifPresent(account -> {
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);
        });

        return accountOptional.map(accountDtoConverter::convert).orElse(new AccountDto());
    }

    public void transferMoney(MoneyTransferRequest transferRequest){
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, transferRequest);
    }

    @RabbitListener(queues = "firstStepQueue")
    public void transferMoneyMessage(MoneyTransferRequest transferRequest){
        Optional<Account> accountOptional = accountRepository.findById(transferRequest.getFromId());
        accountOptional.ifPresentOrElse(account -> {
            if(account.getBalance() >= transferRequest.getAmount()){
                account.setBalance(account.getBalance() - transferRequest.getAmount());
                accountRepository.save(account);
                rabbitTemplate.convertAndSend(exchange.getName(), "secondRoute", transferRequest);
            }else{
                System.out.println("Insufficent funds -> accountId: " +
                        transferRequest.getFromId()+ "balance: " +account.getBalance()+
                        " amount: " + transferRequest.getAmount());
            }},
                () -> System.out.println("Account not found")

                );
    }

    @RabbitListener(queues = "secondStepQueue")
    public void updateReceiverAccount(MoneyTransferRequest transferRequest){
        Optional<Account> accountOptional = accountRepository.findById(transferRequest.getToId());
        accountOptional.ifPresentOrElse(account -> {
            if (account.getBalance() >= transferRequest.getAmount()) {
                account.setBalance(account.getBalance() + transferRequest.getAmount());
                accountRepository.save(account);
                rabbitTemplate.convertAndSend(exchange.getName(), "thirdRoute", transferRequest);
            }
        },()->{
            System.out.println("Receiver Account not found");
            Optional<Account> senderAccount = accountRepository.findById(transferRequest.getFromId());
            senderAccount.ifPresent(sender->{
                System.out.println("Money charge back to sender");
                sender.setBalance(sender.getBalance() + transferRequest.getAmount());
                accountRepository.save(sender);
            });
        });
    }

}
