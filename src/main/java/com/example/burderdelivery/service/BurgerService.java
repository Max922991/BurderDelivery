package com.example.burderdelivery.service;

import com.example.burderdelivery.dto.BurgerDTO;
import com.example.burderdelivery.models.Burger;
import com.example.burderdelivery.repository.BurgerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BurgerService {
    private final BurgerRepo burgerRepo;

    public Burger save(BurgerDTO burgerDTO) {
        return burgerRepo.save(
                Burger.builder()
                        .name(burgerDTO.getName())
                        .description(burgerDTO.getDescription())
                        .price(burgerDTO.getPrice())
                        .isSpicy(burgerDTO.getIsSpicy())
                        .ingredients(burgerDTO.getIngredients())
                        .build()
        );
    }

    public void deleteBurger(Long id) {
        burgerRepo.deleteById(id);
    }

    public BurgerDTO getById(Long id) {
        return burgerRepo.findById(id)
                .map(burger -> new BurgerDTO(burger.getName(),
                        burger.getDescription(),
                        burger.getPrice(),
                        burger.getIsSpicy(),
                        burger.getIngredients()))
                .orElseThrow(() -> new NoSuchElementException("Element not found"));
    }

    public List<BurgerDTO> getAllBurgers() {
        return burgerRepo.findAll().stream()
                .map(burger -> new BurgerDTO(burger.getName(),
                        burger.getDescription(),
                        burger.getPrice(),
                        burger.getIsSpicy(),
                        burger.getIngredients()))
                .collect(Collectors.toList());
    }

    public Boolean update(Long id, BurgerDTO burgerDTO) {
        Burger burger = burgerRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Element not found"));
        burger.setDescription(burgerDTO.getName());
        burger.setName(burgerDTO.getDescription());
        burger.setPrice(burgerDTO.getPrice());
        burger.setIsSpicy(burgerDTO.getIsSpicy());
        burger.setIngredients(burgerDTO.getIngredients());
        return true;
    }
}
