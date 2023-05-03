package com.example.burderdelivery.dto;

import com.example.burderdelivery.models.Ingredient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class BurgerDTO {

    String name;
    String description;
    Double price;
    Boolean isSpicy;
    List<Ingredient> ingredients;
}
