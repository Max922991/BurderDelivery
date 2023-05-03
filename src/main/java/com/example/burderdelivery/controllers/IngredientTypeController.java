package com.example.burderdelivery.controllers;

import com.example.burderdelivery.dto.IngredientTypeDTO;
import com.example.burderdelivery.models.IngredientType;
import com.example.burderdelivery.service.IngredientTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class IngredientTypeController {

    private final IngredientTypeService ingredientTypeService;

    @GetMapping
    public List<IngredientTypeDTO> getAllIngredientType() {
        return ingredientTypeService.getAllIngredients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientTypeDTO> getIngredientTypeById(@PathVariable Long id) {
        IngredientTypeDTO ingredientTypeDTO = ingredientTypeService.getById(id);
        return ResponseEntity.ok(ingredientTypeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredientTypeDTO(@PathVariable Long id) {
        ingredientTypeService.deleteIngredient(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<IngredientType> saveIngredientTypeDTO(@RequestBody IngredientTypeDTO ingredientTypeDTO) {
        IngredientType ingredientType = null;
        try {
            ingredientType = ingredientTypeService.save(ingredientTypeDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "enable create ingredientType");
        }
        return ResponseEntity.ok(ingredientType);
    }

    @PostMapping("/{id}")
    public ResponseEntity<IngredientTypeDTO> updateIngredientTypeDTO(@PathVariable Long id, @RequestBody IngredientTypeDTO ingredientTypeDTO) {
        Boolean isUpdate = ingredientTypeService.update(id, ingredientTypeDTO);
        if (isUpdate) {
            IngredientTypeDTO update = ingredientTypeService.getById(id);
            return ResponseEntity.ok(update);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
