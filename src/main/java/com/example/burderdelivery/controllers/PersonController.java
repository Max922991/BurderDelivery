package com.example.burderdelivery.controllers;

import com.example.burderdelivery.dto.Response;
import com.example.burderdelivery.exception.PaymentException;
import com.example.burderdelivery.models.Person;
import com.example.burderdelivery.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping(value = "/sign-up")
    public ResponseEntity<Person> sign(@RequestBody Person person) {
//        if (person.getPassword().length() < 10) {
//            throw new IllegalArgumentException("password");
//        }
//        if (!isValidUsername(person.getUsername())) {
//            throw new IllegalArgumentException("symbols");
//        }
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        ResponseEntity<Person> responseEntity = ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(personService.savePerson(person));
        return responseEntity;
    }

//    private Boolean isValidUsername(String username) {
//        String pattern = "^[a-zA-Z0-9_]*$";
//        return username.matches(pattern);
//    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<Response> handleException(PaymentException exception) {
          return ResponseEntity
                  .status(HttpStatus.PAYMENT_REQUIRED)
                  .body(new Response(exception.getMessage()));
    }
}
