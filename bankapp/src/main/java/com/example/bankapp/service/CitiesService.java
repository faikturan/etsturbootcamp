package com.example.bankapp.service;

import com.example.bankapp.model.Cities;
import com.example.bankapp.repository.CitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitiesService {

    private final CitiesRepository citiesRepository;

    public List<Cities> getAllCities(){
        return new ArrayList<Cities>((Collection<? extends Cities>) citiesRepository.findAll());
    }

    public Cities getCitiesById(Long id){
        return citiesRepository.findById(id).orElseThrow(()->
                new RuntimeException("Coluldn't find city by id: "+id));
    }

    public Cities createCities(Cities cities){
       return citiesRepository.save(cities);
    }

    public Cities updateCities(Long id, Cities cities){
        Cities oldCity = getCitiesById(id);

        oldCity.setName(cities.getName());
        oldCity.setPlateCode(cities.getPlateCode());

        return citiesRepository.save(oldCity);
    }

    public void deleteCities(Long id){
        Cities city = getCitiesById(id);

        citiesRepository.delete(city);
    }


}
