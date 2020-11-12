package com.example.projetIWA.services;

import com.example.projetIWA.kafka.KafkaProducerService;
import com.example.projetIWA.kafka.models.UserLocalisation;
import com.example.projetIWA.models.Location;
import com.example.projetIWA.models.User;
import com.example.projetIWA.repositories.LocationRepository;
import com.example.projetIWA.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class LocationsService {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Create location for given user's id
     * @param location - the location
     * @param userId - the user's id
     */
    public void create(Location location, Long userId){
        UserLocalisation ul = new UserLocalisation();
        location.setLocation_date(new Date());
        ul.setLocation(location);
        ul.setUser_id(userId);

        this.kafkaProducerService.saveCreateUserLog(ul);
    }

    /**
     * Insert in db the location observe by kafka
     * @param userLocalisation - the userLocalisation to insert
     */
    public void saveInDb(UserLocalisation userLocalisation) {

        Optional<User> user = this.userRepository.findById(userLocalisation.getUser_id());
        if (user.isPresent()) {
            // create location
            Location loc = this.locationRepository.saveAndFlush(userLocalisation.getLocation());
            //create user_location
            user.get().getLocations().add(loc);
            this.userRepository.saveAndFlush(user.get());
        }
    }
}
