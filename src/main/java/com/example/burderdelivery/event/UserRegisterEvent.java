package com.example.burderdelivery.event;

import com.example.burderdelivery.models.Person;
import org.springframework.context.ApplicationEvent;

public class UserRegisterEvent extends ApplicationEvent {

    private final Person person;
    public UserRegisterEvent(Object source, Person person) {
        super(source);
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
