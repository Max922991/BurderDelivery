package com.example.burderdelivery.service.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ConsumerClass {
    private static final String TOPIC = "test_topic";
    private final KafkaTemplate<String, String> template;

    public void sendMessage(String message) {
        template.send(TOPIC, message);
    }
}
