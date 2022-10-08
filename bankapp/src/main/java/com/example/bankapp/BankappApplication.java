package com.example.bankapp;

import com.example.bankapp.model.*;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.AddressRepository;
import com.example.bankapp.repository.CustomerRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableCaching
@RequiredArgsConstructor
public class BankappApplication implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;


    @Bean
    public OpenAPI customOpenAPI (@Value("@{application-description}") String description,
                                  @Value("@{application-version}") String version){
        return new OpenAPI()
                .info(new Info()
                        .title("ETSTur Bank API")
                        .version(version)
                        .description(description)
                        .license(new License().name("ETSTur Bank API Licence")));
    }


    public static void main(String[] args) {
        SpringApplication.run(BankappApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        Customer c1 = Customer.builder()
                .id("1234568")
                .name("Faik")
                .address(Address.builder().city(City.ISTANBUL)
                        .postcode("456312").addressDetails("bu bir adrestir")
                        .build())
                .city(City.ISTANBUL)
                .dateOfBirth(1978)
                .build();

        Customer c2 = Customer.builder()
                .id("789456")
                .name("Semih")
                .address(Address.builder().city(City.MANISA)
                        .postcode("456312").addressDetails("bu bir adrestir 2")
                        .build())
                .city(City.MANISA)
                .dateOfBirth(1980)
                .build();

        Customer c3 = Customer.builder()
                .id("456238")
                .name("Zeynep")
                .address(Address.builder().city(City.IZMIR)
                        .postcode("456312").addressDetails("bu bir adrestir 3")
                        .build())
                .city(City.IZMIR)
                .dateOfBirth(1985)
                .build();

        customerRepository.saveAll(Arrays.asList(c1, c2, c3));

        Account a1 = Account.builder()
                .id("100")
                .customerId("1234568")
                .city(City.ISTANBUL)
                .balance(1230.0)
                .currency(Currency.TL)
                .build();

        Account a2 = Account.builder()
                .id("101")
                .customerId("789456")
                .city(City.MANISA)
                .balance(7898.0)
                .currency(Currency.TL)
                .build();


        Account a3 = Account.builder()
                .id("102")
                .customerId("456238")
                .city(City.IZMIR)
                .balance(120000.0)
                .currency(Currency.TL)
                .build();

        accountRepository.saveAll(Arrays.asList(a1, a2, a3));

    }
}
