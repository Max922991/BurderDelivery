package com.example.burderdelivery.service;

import com.example.burderdelivery.dto.CardDto;
import com.example.burderdelivery.mapper.CardMapper;
import com.example.burderdelivery.models.Card;
import com.example.burderdelivery.models.Person;
import com.example.burderdelivery.repository.CardRepo;
import com.example.burderdelivery.repository.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardMapper cardMapper;
    private final CardRepo cardRepo;
    private final PersonRepo personRepo;
    private final PersonService personService;

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

    public CardDto create(Long personId ,String nameOwner,
                          String numberOfCard,
                          LocalDateTime expiryDate,
                          String cvcCode) {
        Card card = Card.builder()
                .nameOwner(nameOwner)
                .numberOfCard(numberOfCard)
                .expiryDate(expiryDate)
                .cvcCode(cvcCode)
                .balance(100.0)
                .build();
        cardRepo.save(card);

        linkPersonToCard(personId, card.getId());
        return cardMapper.toDto(card);
    }

    public void linkPersonToCard(Long personId, Long cardId) {
        Person person = personService.findById(personId);
        Card card = findById(cardId);

        person.getCardSet().add(card);
        cardRepo.save(card);
    }

    public Card findById(Long id) {
        return cardRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));
    }


    public Card getById(Long cardId) {
       return cardRepo.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));
    }

    public void save(Card card) {
        cardRepo.save(card);
    }
}
