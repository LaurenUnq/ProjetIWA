package com.example.projetIWA.controllers;

import com.example.projetIWA.models.Location;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/home")
public class HomeController {

    @Value("${app.version}")
    private String appVersion;

    @GetMapping
    @RequestMapping("/")
    public String get() {
        return this.appVersion;
    }
}
