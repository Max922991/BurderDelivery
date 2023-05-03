package com.example.burderdelivery.service;

import com.example.burderdelivery.dto.IngredientDTO;
import com.example.burderdelivery.models.Ingredient;
import com.example.burderdelivery.repository.IngredientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepo ingredientRepo;

    public Ingredient save(IngredientDTO ingredientDTO) {
        return ingredientRepo.save(
                Ingredient.builder()
                        .name(ingredientDTO.getName())
                        .price(ingredientDTO.getPrice())
                        .ingredientType(ingredientDTO.getIngredientType())
                        .build()
        );
    }

    public void deleteIngredient(Long id) {
        ingredientRepo.deleteById(id);
    }

    public IngredientDTO getById(Long id) {
      return ingredientRepo.findById(id)
                .map(ingredient -> new IngredientDTO(ingredient.getName(),
                        ingredient.getPrice(),
                        ingredient.getIngredientType()))
                .orElseThrow(() -> new NoSuchElementException("Element not found"));
    }

    public List<IngredientDTO> getAllIngredients() {
        return ingredientRepo.findAll().stream()
                .map(ingredient -> new IngredientDTO(ingredient.getName(),
                        ingredient.getPrice(),
                        ingredient.getIngredientType()))
                .toList();
    }

    public Boolean update(Long id, IngredientDTO ingredientDTO) {
        Ingredient ingredient = ingredientRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Element not found"));
        ingredient.setName(ingredientDTO.getName());
        ingredient.setPrice(ingredientDTO.getPrice());
        ingredient.setIngredientType(ingredientDTO.getIngredientType());
        return true;
    }
}
