package com.example.burderdelivery.mapper;

import com.example.burderdelivery.dto.BurgerDTO;
import com.example.burderdelivery.models.Burger;

@Mapper(componentModel = "spring")
public interface BurgerMapper {

    BurgerDTO toDTO(Burger burger);
    Burger toEntity(BurgerDTO burgerDTO);
}
