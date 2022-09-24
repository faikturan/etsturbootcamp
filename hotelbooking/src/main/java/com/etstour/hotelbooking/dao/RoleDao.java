package com.etstour.hotelbooking.dao;

import com.etstour.hotelbooking.entity.Role;

//DAO Pattern for Role
public interface RoleDao {

    public Role findRoleByName(String roleName);
}
