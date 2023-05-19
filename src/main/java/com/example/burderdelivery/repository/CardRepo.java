package com.example.burderdelivery.repository;

import com.example.burderdelivery.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepo extends JpaRepository<Card, Long> {

    Optional<Card> findByNumberOfCard(String numberOfCard);
}
