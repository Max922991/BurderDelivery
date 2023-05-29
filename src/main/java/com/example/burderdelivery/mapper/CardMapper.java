package com.example.burderdelivery.mapper;

import com.example.burderdelivery.dto.CardDto;
import com.example.burderdelivery.models.Card;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardDto toDto(Card card);
}
