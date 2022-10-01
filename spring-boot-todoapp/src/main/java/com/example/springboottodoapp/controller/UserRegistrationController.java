package com.example.springboottodoapp.controller;

import com.example.springboottodoapp.request.UserRegistrationRequest;
import com.example.springboottodoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserService userService;

    @GetMapping
    public String showRegistration(Model model){
        model.addAttribute("user", new UserRegistrationRequest());
        return "registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") UserRegistrationRequest userRegistrationRequest){
        userService.save(userRegistrationRequest);
        return "redirect:/registration?success";

    }


}
