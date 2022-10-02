package com.example.bankapp.dto;

import com.example.bankapp.model.City;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AddressDto {

    private String id;
    private City city;
    private String postcode;
}
