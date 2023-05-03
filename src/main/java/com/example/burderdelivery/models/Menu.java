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
@Table(name = "menus")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @OneToMany(mappedBy = "menu")
    List<Burger> burgerList;





}
