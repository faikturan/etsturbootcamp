package com.example.springboottodoapp.service;


import com.example.springboottodoapp.models.User;
import com.example.springboottodoapp.request.UserRegistrationRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationRequest userRegistrationRequest);
}
