package com.example.burderdelivery.models;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredients")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    Double price;

    @ManyToOne
    @JoinColumn(name = "ingredient_type_id")
    IngredientType ingredientType;

    @ManyToOne
    @JoinColumn(name = "burger_id")
    Burger burger;
}
