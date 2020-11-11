package com.example.projetIWA.kafka;

import com.example.projetIWA.kafka.models.UserLocalisation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerService {
    private static final Logger logger =
            LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    private KafkaTemplate<String, UserLocalisation> kafkaTemplate;


    public void saveCreateUserLog(UserLocalisation userLocalisation)
    {
        logger.info(String.format("User location created -> %s", userLocalisation));
        this.kafkaTemplate.send("usersLocalisations", userLocalisation);
    }
}
