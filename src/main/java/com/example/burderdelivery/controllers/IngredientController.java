package com.example.burderdelivery.controllers;

import com.example.burderdelivery.dto.IngredientDTO;
import com.example.burderdelivery.models.Ingredient;
import com.example.burderdelivery.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredients")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping
    public List<IngredientDTO> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @PostMapping
    public ResponseEntity<Ingredient> saveIngredient(@RequestBody IngredientDTO ingredientDTO) {
        Ingredient ingredient = null;
        try {
            ingredient = ingredientService.save(ingredientDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "enable create ingredient");
        }
        return ResponseEntity.ok(ingredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDTO> getIngredientById(@PathVariable Long id) {
        IngredientDTO ingredientDTO = ingredientService.getById(id);
        return ResponseEntity.ok(ingredientDTO);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<IngredientDTO> updateIngredient(@PathVariable Long id, @RequestBody IngredientDTO ingredientDTO) {
        Boolean isUpdate = ingredientService.update(id, ingredientDTO);
        if (isUpdate) {
            IngredientDTO updateDTO = ingredientService.getById(id);
            return ResponseEntity.ok(updateDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
