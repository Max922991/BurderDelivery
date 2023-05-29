package com.example.burderdelivery.mapper;

import com.example.burderdelivery.dto.BurgerDTO;
import com.example.burderdelivery.models.Burger;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BurgerMapper  {

    Burger toBurger(BurgerDTO burgerDTO);
    BurgerDTO toDto(Burger burger);
    List<BurgerDTO> toDtoList(List<Burger> burgers);
}
