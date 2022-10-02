package com.example.bankapp.service;

import com.example.bankapp.dto.CreateCustomerRequest;
import com.example.bankapp.dto.CustomerDto;
import com.example.bankapp.dto.CustomerDtoConverter;
import com.example.bankapp.dto.UpdateCustomerRequest;
import com.example.bankapp.model.City;
import com.example.bankapp.model.Customer;
import com.example.bankapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;


    public CustomerDto createCustomer(CreateCustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setId(customerRequest.getId());
        customer.setName(customerRequest.getName());
        customer.setDateOfBirth(customerRequest.getDateOfBirth());
        customer.setCity(City.valueOf(customerRequest.getCity().name()));

        customerRepository.save(customer);

        return customerDtoConverter.convert(customer);
    }

    public List<CustomerDto> getAllCustomers(){
        List<Customer> customerList = new ArrayList<>();

        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customer customer : customerList) {
            customerDtoList.add(customerDtoConverter.convert(customer));
        }

        return customerDtoList;
    }

    @Transactional
    public CustomerDto getCustomerDtoById(String id){
        Optional<Customer> customerOptional = customerRepository.findById(id);

        return customerOptional.map(customerDtoConverter::convert).orElse(new CustomerDto());
    }

    public void deleteCustomer(String id){
        customerRepository.deleteById(id);
    }

    public CustomerDto updateCustomer(String id, UpdateCustomerRequest customerRequest){
        Optional<Customer> customerOptional = customerRepository.findById(id);

        customerOptional.ifPresent(customer -> {
            customer.setName(customerRequest.getName());
            customer.setCity(City.valueOf(customerRequest.getCity().name()));
            customer.setDateOfBirth(customerRequest.getDateOfBirth());
            customerRepository.save(customer);
        });

        return customerOptional.map(customerDtoConverter::convert).orElse(new CustomerDto());
    }

    protected Customer getCustomerById(String id){
        return customerRepository.findById(id).orElse(new Customer());
    }

}
