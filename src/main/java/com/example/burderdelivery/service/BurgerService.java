package com.example.burderdelivery.service;

import com.example.burderdelivery.dto.BurgerDTO;
import com.example.burderdelivery.mapper.BurgerMapper;
import com.example.burderdelivery.models.Burger;
import com.example.burderdelivery.repository.BurgerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class BurgerService {
    private final BurgerRepo burgerRepo;
    private final BurgerMapper burgerMapper;

    public Burger getById(Long id) {
        return burgerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Burger not found!"));
    }

    public BurgerDTO save(Burger burger) {
        burgerRepo.save(burger);
        return burgerMapper.toDto(burger);
    }
}