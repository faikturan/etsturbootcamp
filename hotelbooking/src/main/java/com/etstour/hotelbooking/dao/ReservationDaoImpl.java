package com.etstour.hotelbooking.dao;

import com.etstour.hotelbooking.entity.Reservation;
import com.etstour.hotelbooking.entity.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
public class ReservationDaoImpl implements ReservationDao {

    private static EntityManager entityManager;

    private Session currentSession(){
        return entityManager.unwrap(Session.class);
    }

    @Override
    public Reservation getReservationForLoggedUserById(int resId) {
        //create query with HQL to get user
        Query<Reservation> query = currentSession().
                createQuery("FROM Reservation where id=:resId", Reservation.class);
        query.setParameter("resId", resId);

        return query.getSingleResult();
    }

    @Override
    public Collection<Reservation> getReservationsUserById(int userId) {
        //create query with HQL to get user
        Query<Reservation> query = currentSession().
                createQuery("FROM Reservation where userId=:userId", Reservation.class);
        query.setParameter("userId", userId);

        return query.getResultList();
    }

    @Override
    public void saveOrUpdateReservation(Reservation reservation) {
        currentSession().saveOrUpdate(reservation);
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        currentSession().delete(reservation);
    }
}
