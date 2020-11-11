package com.example.projetIWA.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
class KafkaTopicConfig {

    @Bean
    public NewTopic topic1() {
        // rétention des messages pendant 1 mois
        return TopicBuilder.name("usersLocalisations").config(TopicConfig.RETENTION_MS_CONFIG, "2628000000,00002623").build();
    }
}