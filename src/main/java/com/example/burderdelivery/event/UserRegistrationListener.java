package com.example.burderdelivery.event;

import com.example.burderdelivery.models.Person;
import com.example.burderdelivery.service.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegistrationListener {

    private final MessageSender messageSender;

    @EventListener
    public void handleUserRegistrationEvent(
            UserRegisterEvent userRegisterEvent)
    {
        Person person = userRegisterEvent.getPerson();
        String email = person.getUsername();
        messageSender.sendMessage(email);
    }
}
