package com.example.bankapp.dto;

import com.example.bankapp.model.City;
import com.example.bankapp.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseAccountRequest {

    @NotBlank(message = "Customer id must not be null")
    private String customerId;

    @NotNull
    @Min(0)
    private Double balance;

    @NotNull(message = "Currency must not be null")
    private Currency currency;

    @NotNull(message = "City must not be null")
    private City city;

}
