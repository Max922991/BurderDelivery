package com.example.burderdelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BurgerDTO {

    String name;
    String description;
    Double price;
    Boolean isSpicy;
}
