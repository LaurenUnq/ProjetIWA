package com.example.projetIWA.kafka;

import com.example.projetIWA.kafka.models.UserLocalisation;
import com.example.projetIWA.services.LocationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService
{

    @Autowired
    LocationsService locationsService;

    private final Logger logger =
            LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "usersLocalisations",
            containerFactory = "userKafkaListenerContainerFactory")
    public void consume(UserLocalisation userLocalisation)
    {
        logger.info(String.format("Message recieved -> %s", userLocalisation));
        this.locationsService.saveInDb(userLocalisation);
    }
}
