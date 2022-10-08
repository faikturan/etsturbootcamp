package com.example.bankapp.service;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.dto.AccountDtoConverter;
import com.example.bankapp.dto.CreateAccountRequest;
import com.example.bankapp.model.*;
import com.example.bankapp.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import({AccountService.class, CustomerService.class })
class AccountServiceTest {

    private AccountService accountService;
    private AccountRepository accountRepository;
    private CustomerService customerService;
    private AccountDtoConverter accountDtoConverter;
    private DirectExchange exchange;
    private AmqpTemplate rabbitTemplate;
    private  KafkaTemplate kafkaTemplate;

    @Before
    void setUp() throws Exception {

        accountRepository = Mockito.mock(AccountRepository.class);
        customerService = Mockito.mock(CustomerService.class);
        accountDtoConverter = Mockito.mock(AccountDtoConverter.class);
        exchange = Mockito.mock(DirectExchange.class);
        rabbitTemplate = Mockito.mock(AmqpTemplate.class);
        kafkaTemplate = Mockito.mock(KafkaTemplate.class);

        accountService = new AccountService(accountRepository,
                customerService, accountDtoConverter, exchange, rabbitTemplate, kafkaTemplate);
    }

    @Test
    public void whenCreateAccountCalledWithValidRequest_itShouldReturnValidAccountDto(){
        CreateAccountRequest createAccountRequest = generateAccountRequest();
        Customer customer = generateCustomer();
        Account account = generateAccount(createAccountRequest);
        AccountDto accountDto = generateAccountDto();

        //Determine mock services behavior test senario
        Mockito.when(customerService.getCustomerById("12345")).thenReturn(customer);
        Mockito.when(accountRepository.save(account)).thenReturn(account);
        Mockito.when(accountDtoConverter.convert(account)).thenReturn(accountDto);

        //Call the testing method
        AccountDto result = accountService.createAccount(createAccountRequest);

        //Check results and verify the mock methods are called
        Assert.assertEquals(result, accountDto );
        Mockito.verify(customerService).getCustomerById("12345");
        Mockito.verify(accountRepository).save(account);
        Mockito.verify(accountDtoConverter).convert(account);

    }

    private CreateAccountRequest generateAccountRequest(){
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("12345");
        createAccountRequest.setCustomerId("12345");
        createAccountRequest.setBalance(100.0);
        createAccountRequest.setCity(City.ISTANBUL);
        return createAccountRequest;
    }

    private Customer generateCustomer(){
        return Customer.builder()
                .id("12345")
                .address(Address.builder().city(City.ISTANBUL).postcode("456312")
                        .addressDetails("bu bir adrestir").build())
                .city(City.ISTANBUL)
                .dateOfBirth(1998)
                .name("Fatih")
                .build();
    }

    private Account generateAccount(CreateAccountRequest accountRequest){
        return Account.builder()
                .id(accountRequest.getId())
                .balance(accountRequest.getBalance())
                .currency(accountRequest.getCurrency())
                .customerId(accountRequest.getCustomerId())
                .city(accountRequest.getCity())
                .build();
    }

    private AccountDto generateAccountDto(){
        return AccountDto.builder()
                .id("1234")
                .customerId("12345")
                .currency(Currency.TL)
                .balance(100.0)
                .build();
    }

}