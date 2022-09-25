package com.example.springboot01mongorestapi.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;


@Data
@Document
public class User {

    @Id
    private String id;

    private String name;

    private String surName;

    private HashMap properties;
}
