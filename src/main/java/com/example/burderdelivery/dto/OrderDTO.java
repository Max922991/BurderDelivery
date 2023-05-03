package com.example.burderdelivery.dto;

import com.example.burderdelivery.models.Burger;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    String address;
    List<Burger> burger; // BurgerDTO ???
    LocalDateTime dateTime;
    Boolean isReady;
}
