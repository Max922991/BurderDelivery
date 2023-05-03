package com.example.burderdelivery.service;

import com.example.burderdelivery.dto.MenuDTO;
import com.example.burderdelivery.models.Menu;
import com.example.burderdelivery.repository.MenuRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepo menuRepo;

    public Menu save(MenuDTO menuDTO) {
        return menuRepo.save(
                Menu.builder()
                        .name(menuDTO.getName())
                        .burgerList(menuDTO.getBurgerList())
                        .build()
        );
    }

    public void deleteMenu(Long id) {
        menuRepo.deleteById(id);
    }

    public MenuDTO getById(Long id) {
        return menuRepo.findById(id)
                .map(menu -> new MenuDTO(menu.getName(), menu.getBurgerList()))
                .orElseThrow(() -> new NoSuchElementException("Element not found"));
    }

    public List<MenuDTO> getAllMenu() {
        return menuRepo.findAll().stream()
                .map(menu -> new MenuDTO(menu.getName(),
                        menu.getBurgerList()))
                .collect(Collectors.toList());
    }

    public Boolean update(Long id, MenuDTO menuDTO) {
        Menu menu = menuRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Element not found"));
        menu.setName(menuDTO.getName());
        menu.setBurgerList(menuDTO.getBurgerList());
        return true;
    }
}
