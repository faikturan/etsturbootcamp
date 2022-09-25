package com.example.springboot03datajpapostgresql.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class KisiDto {
    private Long id;

    @NotNull
    private String adi;
    private String soyadi;
    private List<String> adresler;
}
