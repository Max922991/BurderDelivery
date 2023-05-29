package com.example.burderdelivery.mapper;

import com.example.burderdelivery.dto.StatusOrderDto;
import com.example.burderdelivery.models.StatusOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusOrderMapper {

    StatusOrderDto toDto(StatusOrder statusOrder);
}
