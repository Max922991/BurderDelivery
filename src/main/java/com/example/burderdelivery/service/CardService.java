package com.example.burderdelivery.service;

import com.example.burderdelivery.models.Card;
import com.example.burderdelivery.repository.CardRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepo cardRepo;

    public void update(Card card) {
        cardRepo.findById(card.getId())
                .map(card1 -> Card.builder()
                        .balance(card.getBalance()))
                .orElseThrow(() -> new UsernameNotFoundException("wdwd"));
    }

    public Card getCardByNumber(String number) {
       return cardRepo.findByNumberOfCard(number)
                .orElseThrow(() -> new UsernameNotFoundException("wdwd"));
    }



}
