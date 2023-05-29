package com.example.burderdelivery.mapper;

import com.example.burderdelivery.dto.OrderDTO;
import com.example.burderdelivery.models.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderDTO orderDTO);
    OrderDTO toOrderDto(Order order);
    List<OrderDTO> toOrderListDto(List<Order> orderList);

}
