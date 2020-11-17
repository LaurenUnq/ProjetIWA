package com.example.projetIWA.controllers;

import com.example.projetIWA.models.Location;
import com.example.projetIWA.repositories.LocationRepository;
import com.example.projetIWA.services.LocationsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("locations")
public class LocationsController {


    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationsService locationsService;

    /**
     * Create localisation for user connected TODO
     * @param location - the location
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody final Location location) {
        String userId = "e9f7b368-f3ff-46d9-9d46-b60fabafedb8"; // TODO
        this.locationsService.create(location, userId);
        return  location;
    }
}
