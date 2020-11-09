package com.example.projetIWA.controllers;

import com.example.projetIWA.AdminKeycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/home")
public class HomeController {

    @Value("${app.version}")
    private String appVersion;

    @GetMapping
    public String get() throws IOException {
        return this.appVersion;
    }
}
