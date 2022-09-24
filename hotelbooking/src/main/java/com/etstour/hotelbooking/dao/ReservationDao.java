package com.etstour.hotelbooking.dao;

import com.etstour.hotelbooking.entity.Reservation;
import org.springframework.stereotype.Repository;

import java.util.Collection;

//DAO Pattern for Reservation
public interface ReservationDao {

    public Reservation getReservationForLoggedUserById(int resId);

    public Collection<Reservation> getReservationsUserById(int userId);

    public void saveOrUpdateReservation(Reservation reservation);

    public void deleteReservation(Reservation reservation);


}
