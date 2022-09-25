package com.example.springboot01mongorestapi.api;

import com.example.springboot01mongorestapi.entity.User;
import com.example.springboot01mongorestapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/users/")
@RequiredArgsConstructor
public class UsersController {

    private final UserRepository userRepository;

    @PostConstruct
    public void init(){
        User user = new User();
        user.setName("Faik");
        user.setSurName("Faik");
        userRepository.save(user);
    }


    @PostMapping("add")
    public ResponseEntity<User> add(@RequestBody User user){
        System.out.println(user.getName());
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

}
