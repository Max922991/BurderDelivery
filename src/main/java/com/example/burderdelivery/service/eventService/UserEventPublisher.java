package com.example.burderdelivery.service.eventService;

import com.example.burderdelivery.models.Person;

public interface UserEventPublisher {
    void publishUser(Person person);
}
