package com.example.burderdelivery.service;

import com.example.burderdelivery.models.Person;
import com.example.burderdelivery.repository.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepo personRepo;
    private final UserEventPublisher userEventPublisher;

    public Person savePerson(Person person) {
        Person register = personRepo.save(person);
        userEventPublisher.publishUser(register);
        return register;
    }
}
