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

import java.util.*;

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

    /**
     * Create all contacts cases for given user
     * cas contact = 7j avant max, + ou - 0.00005 en longitude latitude, + ou - 15 min avant la localication
     * @param user - the user positive
     */
    public void createNotificationsFromPositiveUser(User user) {
        // toutes les localisations du user donnée durant le 7 dernier jour
        Optional<User> userFound = this.userRepository.findById(user.getUser_id());

        if (userFound.isPresent()) {
            List<Location> allLocations = userFound.get().getLocations();

            // pour toutes ces localiations chercher les cas contact
            List<User> allContactCases = new ArrayList<User>();
            Date limitDate = new Date(new Date().getTime() - 604800000L); // - 7 jours en milisecondes
            allLocations.forEach(location -> {
                if(location.getLocation_date().after(limitDate)) {
                    List<User> allUsers = this.userRepository.findAllContactCase(
                            location.getLatitude() + 0.00005,
                            location.getLatitude() - 0.00005,
                            location.getLongitude() + 0.00005,
                            location.getLongitude() - 0.00005,
                            new Date(location.getLocation_date().getTime() + 900000), //+ 15min en milisecondes
                            new Date(location.getLocation_date().getTime() - 900000), //- 15min en milisecondes
                            user.getUser_id()
                    );
                    allUsers.forEach(userSelect -> {
                        if (!allContactCases.contains(userSelect)){
                            allContactCases.add(userSelect);
                        }
                    });
                }
            });

            // créer la notification pour chaque user
            Notification notif = new Notification();
            notif.setDescription("Cas contact 1er degré");
            notif.setNotification_date(new Date());
            notif.setViewed(false);
            Notification notificationSave = this.notificationRepository.saveAndFlush(notif);

            allContactCases.forEach(userSelect -> {
                userSelect.getNotifications().add(notificationSave);
            });
            this.userRepository.saveAll(allContactCases);
        }
    }
}
