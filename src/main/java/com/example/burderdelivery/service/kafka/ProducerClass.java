package com.example.burderdelivery.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProducerClass {

    @KafkaListener(topics = "test_topic", groupId = "group_id")
    public void consumer(String message) {
        System.out.println("Consumed message: " + message);
    }

}
