package com.example.burderdelivery.service;

import com.example.burderdelivery.models.Person;

public interface UserEventPublisher {

    void publishUser(Person person);
}
