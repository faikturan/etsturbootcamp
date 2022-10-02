package com.example.bankapp.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCustomerRequest extends BaseCustomerRequest {

    @NotBlank(message = "Customer id must not be empty")
    private String id;
}
