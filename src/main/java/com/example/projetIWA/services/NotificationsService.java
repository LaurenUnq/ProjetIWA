package com.example.projetIWA.services;

import com.example.projetIWA.kafka.KafkaConsumerService;
import com.example.projetIWA.kafka.KafkaProducerConfig;
import com.example.projetIWA.kafka.KafkaProducerService;
import com.example.projetIWA.kafka.models.UserLocalisation;
import com.example.projetIWA.models.Location;
import com.example.projetIWA.models.Notification;
import com.example.projetIWA.models.User;
import com.example.projetIWA.repositories.NotificationRepository;
import com.example.projetIWA.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationsService {


    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

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

    public void createNotificationsFromPostiveUser(User user) {
        /*Location loc = new Location();
        loc.setLatitude(652.152455);
        loc.setLongitude(5.24850);
        loc.setLocation_date(new Date());

        UserLocalisation ul = new UserLocalisation();
        ul.setLocation(loc);
        ul.setUser_id(userId);

        this.kafkaProducerService.saveCreateUserLog(ul);*/
        // insert all localisation from kafka to postgre (7 derniers jour)

        // pour toute ces localiations chercher les cas contact sur kafka
        //List<Notification> l = this.notificationRepository.findAllNotifByUserId(userId);

        // créer un notifications pour chaque user
        //this.kafkaConsumerService.consume();
    }
}
