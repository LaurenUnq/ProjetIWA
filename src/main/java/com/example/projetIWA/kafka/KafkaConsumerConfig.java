package com.example.projetIWA.kafka;

import java.util.HashMap;
import java.util.Map;

import com.example.projetIWA.kafka.models.UserLocalisation;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig
{
    // Consume user objects from Kafka

    public ConsumerFactory<String, UserLocalisation> userConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "MSI:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id");
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props,
                new StringDeserializer(),
                new JsonDeserializer<>(UserLocalisation.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserLocalisation>
    userKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserLocalisation> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactory());
        return factory;
    }
}