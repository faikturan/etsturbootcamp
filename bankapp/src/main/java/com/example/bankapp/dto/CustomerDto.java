package com.example.bankapp.dto;

import com.example.bankapp.model.Address;
import com.example.bankapp.model.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String id;
    private String name;
    private Integer dateOfBirth;
    private City city;
    private Address address;
}
