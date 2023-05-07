package com.example.burderdelivery.controllers;

import com.example.burderdelivery.dto.BurgerDTO;
import com.example.burderdelivery.models.Burger;
import com.example.burderdelivery.service.BurgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/burgers")
@RequiredArgsConstructor
public class BurgerController {

    private final BurgerService burgerService;

    @GetMapping
    public List<BurgerDTO> getAllBurgers() {
       return burgerService.getAllBurgers();
    }

    @PostMapping
    public ResponseEntity<Burger> saveBurger(@RequestBody BurgerDTO burgerDTO) {
        Burger burger = null;
        try {
          burger = burgerService.save(burgerDTO);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "enable create burger");
        }
        return ResponseEntity.ok(burger);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBurger(@PathVariable Long id) {
        burgerService.deleteBurger(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BurgerDTO> getBurger(@PathVariable Long id) {
        BurgerDTO burgerDTO = burgerService.getById(id);
        return ResponseEntity.ok(burgerDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BurgerDTO> updateBurger(@PathVariable Long id, @RequestBody BurgerDTO burgerDTO){
        Boolean isUpdate = burgerService.update(id, burgerDTO);
        if (isUpdate) {
            BurgerDTO updateDTO = burgerService.getById(id);
            return ResponseEntity.ok(updateDTO);
        }else {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
