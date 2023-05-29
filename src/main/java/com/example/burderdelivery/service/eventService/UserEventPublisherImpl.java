package com.example.burderdelivery.service.eventService;

import com.example.burderdelivery.event.UserRegisterEvent;
import com.example.burderdelivery.models.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventPublisherImpl implements UserEventPublisher {

   private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void publishUser(Person person) {
        applicationEventPublisher.publishEvent(
                new UserRegisterEvent(this, person));
    }
}
