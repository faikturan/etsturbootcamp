package com.example.bankapp.repository;

import com.example.bankapp.model.Cities;
import com.example.bankapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitiesRepository extends JpaRepository<Cities, Long> {
}
