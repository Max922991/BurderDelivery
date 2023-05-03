package com.example.burderdelivery.service;

import com.example.burderdelivery.dto.OrderDTO;
import com.example.burderdelivery.models.Order;
import com.example.burderdelivery.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;


    public Order save(OrderDTO orderDTO) {
        return orderRepo.save(
                Order.builder()
                        .address(orderDTO.getAddress())
                        .burger(orderDTO.getBurger())
                        .dateTime(orderDTO.getDateTime())
                        .isReady(orderDTO.getIsReady())
                        .build()
        );
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }

    public OrderDTO getById(Long id) {
        return orderRepo.findById(id)
                .map(order -> new OrderDTO(order.getAddress(),
                        order.getBurger(),
                        order.getDateTime(),
                        order.getIsReady()))
                .orElseThrow(() -> new NoSuchElementException("Element not found"));
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepo.findAll().stream()
                .map(order -> new OrderDTO(order.getAddress(),
                        order.getBurger(),
                        order.getDateTime(),
                        order.getIsReady()))
                .collect(Collectors.toList());
    }

    public Boolean update(Long id, OrderDTO orderDTO) {
        Order order = orderRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Element not found"));
        order.setAddress(orderDTO.getAddress());
        order.setBurger(orderDTO.getBurger());
        order.setDateTime(orderDTO.getDateTime());
        order.setIsReady(orderDTO.getIsReady());
        return true;
    }



}