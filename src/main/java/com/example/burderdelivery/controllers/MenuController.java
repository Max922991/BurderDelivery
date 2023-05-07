package com.example.burderdelivery.controllers;

import com.example.burderdelivery.dto.MenuDTO;
import com.example.burderdelivery.models.Menu;
import com.example.burderdelivery.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> getMenuById(@PathVariable Long id) {
        MenuDTO menuDTO = menuService.getById(id);
        return ResponseEntity.ok(menuDTO);
    }

    @PostMapping
    public ResponseEntity<Menu> saveMenu(@RequestBody MenuDTO menuDTO) {
        Menu menu = null;
        try {
            menu = menuService.save(menuDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "enable create menu");
        }
        return ResponseEntity.ok(menu);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MenuDTO> updateMenuDTO(@PathVariable Long id, @RequestBody MenuDTO menuDTO) {
        Boolean isUpdate = menuService.update(id, menuDTO);
        if (isUpdate) {
            MenuDTO updateMenu = menuService.getById(id);
            return ResponseEntity.ok(updateMenu);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
