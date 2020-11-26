package com.example.projetIWA.controllers;

import com.example.projetIWA.auth.AuthService;
import com.example.projetIWA.models.Location;
import com.example.projetIWA.repositories.LocationRepository;
import com.example.projetIWA.services.LocationsService;
import com.example.projetIWA.services.UsersServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("locations")
public class LocationsController {


    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationsService locationsService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UsersServices usersServices;

    /**
     * Create localisation for user connected TODO
     * @param location - the location
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody final Location location) {

        String userId = this.authService.getUserIdByContext();
        if(!usersServices.userExist(userId)){
            this.locationsService.create(location, userId);
            return  location;
        }
        else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You have to be log");
        }
    }
}
