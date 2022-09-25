package com.example.springboot01mongorestapi.repository;

import com.example.springboot01mongorestapi.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
