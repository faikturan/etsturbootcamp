package com.etstour.hotelbooking.services;

import com.etstour.hotelbooking.entity.Reservation;
import com.etstour.hotelbooking.temp.CurrentReservation;

import java.util.Collection;

//Service Pattern for Reservation
public interface ReservationService {

    public Reservation getReservationForLoggedUserById(int resId);

    public Collection<Reservation> getReservationsForLoggedUser();

    public void saveOrUpadateReservation(CurrentReservation currentReservation);

    public void deleteReservation(int resId);

    public CurrentReservation reservationToCurrentReservation(int resId);

}
