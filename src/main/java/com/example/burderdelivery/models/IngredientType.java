package com.example.burderdelivery.models;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredient_types")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @OneToMany(mappedBy = "ingredientType")
    List<Ingredient> ingredient;
}
