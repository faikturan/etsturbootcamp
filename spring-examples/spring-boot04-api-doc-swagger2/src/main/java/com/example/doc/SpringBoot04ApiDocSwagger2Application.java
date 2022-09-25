package com.example.doc;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Api(value = "Benim Pet API dökümantasyonum")
public class SpringBoot04ApiDocSwagger2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot04ApiDocSwagger2Application.class, args);
    }

}
