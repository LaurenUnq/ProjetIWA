package com.example.projetIWA.controllers;

import com.example.projetIWA.models.Location;
import com.example.projetIWA.models.Notification;
import com.example.projetIWA.repositories.LocationRepository;
import com.example.projetIWA.repositories.NotificationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notifications")
public class NotificationsController {


    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping
    //Get toutes les locations
    public List<Notification> list() {
        return notificationRepository.findAll();
    }

    //get la location correspondant à tel id
    @GetMapping
    @RequestMapping("{id}")
    public Notification get(@PathVariable Long id) {
        return notificationRepository.getOne(id);
    }


    //creer une location
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Notification create(@RequestBody final Notification notification) {
        return  notificationRepository.saveAndFlush(notification);
    }

    //delete une location
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // Toujours verifier s'il faut supprimer aussi
        // les enregistrements enfants
        notificationRepository.deleteById(id);
    }

    //Update une location
    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Notification update(@PathVariable Long id, @RequestBody Notification notification) {
        // TODO: Ajouter ici une validation si tous
        // les champs ont ete passes
        // Sinon, retourner une erreur 400 (Bad Payload)
        Notification existingNotification = notificationRepository.getOne(id);
        BeanUtils.copyProperties(notification,existingNotification,"notification_id");
        return notificationRepository.saveAndFlush(existingNotification);
    }
}
