package com.example.projetIWA.kafka;

import com.example.projetIWA.kafka.models.UserLocalisation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService
{
    private final Logger logger =
            LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "usersLocalisations",
            containerFactory = "userKafkaListenerContainerFactory")
    public void consume(UserLocalisation userLocalisation)
    {
        logger.info(String.format("Message recieved -> %s", userLocalisation));
    }
}
