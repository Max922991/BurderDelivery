package com.example.burderdelivery.controllers;

import com.example.burderdelivery.models.Person;
import com.example.burderdelivery.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/sign-up")
    public ResponseEntity<Person> sign(@RequestBody Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        ResponseEntity<Person> responseEntity = (ResponseEntity<Person>) ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(personService.savePerson(person));
        return responseEntity;
    }
}
