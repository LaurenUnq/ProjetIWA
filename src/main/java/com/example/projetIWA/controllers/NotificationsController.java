package com.example.projetIWA.controllers;

import com.example.projetIWA.models.Location;
import com.example.projetIWA.models.Notification;
import com.example.projetIWA.models.User;
import com.example.projetIWA.repositories.LocationRepository;
import com.example.projetIWA.repositories.NotificationRepository;
import com.example.projetIWA.services.NotificationsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notifications")
public class NotificationsController {

    // route send notif (if i am positive)

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationsService notificationsService;

    /**
     * get all notifications of given user id
     * @param id - the user's id
     * @return the user's notification list
     */
    @GetMapping
    @RequestMapping("user/{id}")
    public List<Notification> getAllNotifications(@PathVariable Long id) {
        return notificationsService.getAllNotificationByUserId(id);
    }

    /**
     * get the number of notifications for given user id which are not viewed
     * @param id - the user's id
     * @return the number of user's notification which are not viewed
     */
    @GetMapping
    @RequestMapping("user/{id}/notViewed")
    public long getNumberNotificationsNotViewedByUserId(@PathVariable Long id) {
        return notificationsService.getNumberNotificationNotViewedByUserId(id);
    }


    /**
     * create notification for users who have been in contact with the given positive user
     * @param user - the user id of positive user
     * @return
     */
    @PostMapping
    @RequestMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final User user) {
        notificationsService.createNotificationsFromPostiveUser(user.getUser_id());
    }
}
