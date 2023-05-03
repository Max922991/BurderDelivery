package com.example.burderdelivery.service;

import com.example.burderdelivery.dto.IngredientDTO;
import com.example.burderdelivery.dto.IngredientTypeDTO;
import com.example.burderdelivery.models.IngredientType;
import com.example.burderdelivery.repository.IngredientTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientTypeService {
    private final IngredientTypeRepo ingredientTypeRepo;

    public IngredientType save(IngredientTypeDTO ingredientTypeDTO) {
        return ingredientTypeRepo.save(
                IngredientType.builder()
                        .name(ingredientTypeDTO.getName())
                        .build()
        );
    }

    public void deleteIngredient(Long id) {
        ingredientTypeRepo.deleteById(id);
    }

    public IngredientTypeDTO getById(Long id) {
        return ingredientTypeRepo.findById(id)
                .map(ingredientType -> new IngredientTypeDTO(ingredientType.getName()))
                .orElseThrow(() -> new NoSuchElementException("Element not found"));
    }

    public List<IngredientTypeDTO> getAllIngredients() {
        return ingredientTypeRepo.findAll().stream()
                .map(ingredientType -> new IngredientTypeDTO(ingredientType.getName()))
                .collect(Collectors.toList());
    }

    public Boolean update(Long id, IngredientTypeDTO ingredientTypeDTO) {
        IngredientType ingredientType = ingredientTypeRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Element not found"));
        ingredientType.setName(ingredientTypeDTO.getName());
        return true;
    }
}
