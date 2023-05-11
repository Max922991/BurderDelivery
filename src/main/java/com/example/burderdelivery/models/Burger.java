package com.example.burderdelivery.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.burderdelivery.validation.Operation;
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
    @NotNull(message = "Id must be not null!", groups = {Operation.OnUpdate.class, Operation.OnDelete.class})
    Long id;

    @NotBlank(message = "name must be not blank!", groups = {Operation.OnUpdate.class, Operation.OnCreate.class})
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
