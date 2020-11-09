package com.example.projetIWA.controllers;

import com.example.projetIWA.AdminTokenKeycloak;
import com.example.projetIWA.repositories.UserRepository;
import com.example.projetIWA.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
/*
@Controller
public class testController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private UserRepository userRepository;

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @GetMapping({"/"})
    public String external() throws IOException {

        return "external";
    }

    @GetMapping({"/index"})
    public String index() {
        return "index";
    }

    //@GetMapping({"/users"})
    //public String users() {
    //    return "users";
    //}
}*/