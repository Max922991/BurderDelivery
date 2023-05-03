package com.example.burderdelivery.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.burderdelivery.models.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class FilterAuth extends UsernamePasswordAuthenticationFilter {

    public static final String SECRET_KEY = "secret key";
    public static final Long EXPIRATION_TIME = 84_000_000L;
    public static final String PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
    public static final String ACCESS = "/users/sign_up";

    private AuthenticationManager authenticationManager;

    public FilterAuth(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            Person person = new ObjectMapper().readValue(request.getInputStream(), Person.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(person.getUsername(),
                    person.getPassword(),
                    new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET_KEY.getBytes()));
        response.addHeader(HEADER, PREFIX + token);
    }
}
