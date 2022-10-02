package com.example.bankapp.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
public class Account implements Serializable {
    @Id
    private String id;

    private String customerId;
    private Double balance;
    private City city;
    private Currency currency;

}
