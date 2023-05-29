package com.example.burderdelivery.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    String nameOwner;
    String numberOfCard;
    LocalDateTime expiryDate;
    String cvcCode;
    double balance;
}
