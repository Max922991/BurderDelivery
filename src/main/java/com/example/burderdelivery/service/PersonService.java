package com.example.burderdelivery.service;

import com.example.burderdelivery.models.Person;
import com.example.burderdelivery.repository.PersonRepo;
import com.example.burderdelivery.service.eventService.UserEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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

    public Person findById(Long id) {
        return personRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person nit found"));
    }
}
