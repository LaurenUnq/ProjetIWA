package com.example.projetIWA.services;

import com.example.projetIWA.models.Notification;
import com.example.projetIWA.models.User;
import com.example.projetIWA.repositories.NotificationRepository;
import com.example.projetIWA.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationsService {


    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * get all notification for given user id and set all notification seen
     * @param id - the user's id
     * @return the user's notification list
     */
    public List<Notification> getAllNotificationByUserId(Long id) {
        User user = this.userRepository.getOne(id);

        // put all notification view to true
        user.getNotifications().forEach(notification -> notification.setViewed(true));
        userRepository.saveAndFlush(user);

        return user.getNotifications();
    }

    /**
     * get the number of notifications for given user id which are not viewed
     * @param id - the user's id
     * @return the number of user's notification which are not viewed
     */
    public long getNumberNotificationNotViewedByUserId(Long id) {
        return this.userRepository.getOne(id).getNotifications().stream().filter(notification -> !notification.getViewed()).count();
    }
}
