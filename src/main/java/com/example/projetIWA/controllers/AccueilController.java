package com.example.projetIWA.controllers;

import com.example.projetIWA.models.User;
import com.example.projetIWA.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/")
    public String index() {
        return "external";
    }

    @GetMapping(path = "/users")
    public String customers(Principal principal, Model model) {
        //addUsers();
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("username", principal.getName());
        return "customers";
    }

    // add customers for demonstration
    public void addUsers() {

        User user1 = new User();
        user1.setFirst_name("firstname1");
        user1.setLast_name("Lastname1");
        user1.setUsername("username1");
        //user1.setPassword("pass1");
        userRepository.save(user1);

        User user2 = new User();
        user2.setFirst_name("firstname2");
        user2.setLast_name("Lastname2");
        user2.setUsername("username2");
        //user2.setPassword("pass2");
        userRepository.save(user2);

        User user3 = new User();
        user3.setFirst_name("firstname3");
        user3.setLast_name("Lastname3");
        user3.setUsername("username3");
        //user3.setPassword("pass3");
        userRepository.save(user3);

    }
}