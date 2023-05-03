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
@Table(name = "burgers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Burger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    Double price;
    Boolean isSpicy;
    @OneToMany(mappedBy = "burger")
    List<Ingredient> ingredients;
    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;
    @ManyToOne()
    @JoinColumn(name = "menu_id")
    Menu menu;


}
