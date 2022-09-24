package com.etstour.hotelbooking.dao;

import com.etstour.hotelbooking.entity.Role;
import com.etstour.hotelbooking.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class RoleDaoImpl implements RoleDao {

    private static EntityManager entityManager;

    private Session currentSession(){
        return entityManager.unwrap(Session.class);
    }

    @Override
    public Role findRoleByName(String roleName) {
        //create query with HQL to get user
        Query<Role> query = currentSession().createQuery("FROM Role where name=:roleName", Role.class);
        query.setParameter("roleName", roleName);

        //check if valid user and is exist or null

        Role role = null;
        try {
            role = query.getSingleResult();

        }catch (Exception e){
            role = null;
        }
        return role;
    }
}
