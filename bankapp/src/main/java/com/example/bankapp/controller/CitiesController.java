package com.example.bankapp.controller;

import com.example.bankapp.model.Cities;
import com.example.bankapp.service.CitiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CitiesController {

    private final CitiesService citiesService;

    @GetMapping
    public ResponseEntity<List<Cities>> getAllCities(){
        return ResponseEntity.ok(citiesService.getAllCities());
    }

    @PostMapping
    public ResponseEntity<Cities> createCities(@RequestBody Cities cities){
        return ResponseEntity.ok(citiesService.createCities(cities));
    }

}
