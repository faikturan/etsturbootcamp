package com.etstour.hotelbooking.services;

import com.etstour.hotelbooking.entity.User;
import com.etstour.hotelbooking.temp.CurrentUser;
import org.springframework.security.core.userdetails.UserDetailsService;

//Service Pattern for User
public interface UserService extends UserDetailsService {

    public User findUserByEmail(String email);

    public void saveUser(CurrentUser currentUser);

    public int getLoggedUserId();

}
