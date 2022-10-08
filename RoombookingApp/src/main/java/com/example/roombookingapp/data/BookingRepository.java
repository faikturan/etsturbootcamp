package com.example.roombookingapp.data;

import com.example.roombookingapp.model.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByDate(Date date);
}
