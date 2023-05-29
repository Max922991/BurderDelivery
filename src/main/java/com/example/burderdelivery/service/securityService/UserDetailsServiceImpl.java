package com.example.burderdelivery.service.securityService;

import com.example.burderdelivery.models.Person;
import com.example.burderdelivery.repository.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonRepo personRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person byUsername = personRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(byUsername.getUsername(), byUsername.getPassword(), Collections.emptyList());
    }
}
