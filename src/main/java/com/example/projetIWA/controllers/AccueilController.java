package com.example.projetIWA.controllers;

import com.example.projetIWA.models.User;
import com.example.projetIWA.services.UsersServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class AccueilController {

    @Autowired
    private UsersServices usersServices;

    @GetMapping(path = "/")
    public String index() {
        return "external";
    }

}