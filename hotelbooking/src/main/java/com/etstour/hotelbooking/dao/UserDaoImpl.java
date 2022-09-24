package com.etstour.hotelbooking.dao;

import com.etstour.hotelbooking.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserDaoImpl implements UserDao{
    //dao pattern to deal with retrieve and send data to and from database for user


    private static EntityManager entityManager;

    //get current hibernate session
    private Session currentSession(){
        return entityManager.unwrap(Session.class);
    }


    // get user from database with email
    @Override
    public User findUserByEmail(String email) {
        //create query with HQL to get user
        Query<User> query = currentSession().createQuery("FROM User where email=:email", User.class);
        query.setParameter("email", email);

        //check if valid user and is exist or null

        User user = null;
        try {
            user = query.getSingleResult();

        }catch (Exception e){
            user = null;
        }
        return user;
    }

    //get user from database using username
    @Override
    public User findUserByUsername(String username) {
        //create query with HQL to get user
        Query<User> query = currentSession().createQuery("FROM User where username=:username", User.class);
        query.setParameter("username", username);

        //check if valid user and is exist or null

        User user = null;
        try {
            user = query.getSingleResult();

        }catch (Exception e){
            user = null;
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        currentSession().saveOrUpdate(user);
    }
}
