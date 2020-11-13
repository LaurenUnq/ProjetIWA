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

    @GetMapping
    //Get toutes les locations
    public List<Location> list() {
        return locationRepository.findAll();
    }

    //get la location correspondant à tel id
    @GetMapping
    @RequestMapping("{id}")
    public Location get(@PathVariable Long id) {
        return locationRepository.getOne(id);
    }


    /**
     * Create localisation for user connected TODO
     * @param location - the location
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location create(@RequestBody final Location location) {
        String userId = "2"; // TODO
        this.locationsService.create(location, userId);
        return  location;
    }

    //delete une location
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // Toujours verifier s'il faut supprimer aussi
        // les enregistrements enfants
        locationRepository.deleteById(id);
    }

    //Update une location
    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Location update(@PathVariable Long id, @RequestBody Location location) {
        // TODO: Ajouter ici une validation si tous
        // les champs ont ete passes
        // Sinon, retourner une erreur 400 (Bad Payload)
        Location existingLocation = locationRepository.getOne(id);
        BeanUtils.copyProperties(location,existingLocation,"location_id");
        return locationRepository.saveAndFlush(existingLocation);
    }

}
