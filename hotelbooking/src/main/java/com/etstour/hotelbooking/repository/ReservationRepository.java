package com.etstour.hotelbooking.repository;

import com.etstour.hotelbooking.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Reservation findById(int resId);

    Collection<Reservation> findAllByUserId(int userId);

}
