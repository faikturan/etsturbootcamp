package com.example.bankapp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoneyTransferRequest {

    private String fromId;
    private String toId;
    private Double amount;

}
