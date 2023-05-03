package com.example.burderdelivery.dto;

import com.example.burderdelivery.models.IngredientType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {

    String name;
    Double price;
    IngredientType ingredientType;
}
