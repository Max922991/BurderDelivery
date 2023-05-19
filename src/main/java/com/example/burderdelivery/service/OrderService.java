package com.example.burderdelivery.service;

import com.example.burderdelivery.dto.OrderDTO;
import com.example.burderdelivery.models.Order;
import com.example.burderdelivery.models.StatusOrder;
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
                        .statusOrder(StatusOrder.builder()
                                .name("Жду оплаты")
                                .description("dwdwdwd")
                                .build())
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
                        order.setStatusOrder(StatusOrder.builder()
                                        .name("dwdw")
                                        .description("dwdwd")
                                        .build())
                .collect(Collectors.toList());
    }

    public Boolean update(Order order) {
        Order orderFound = orderRepo.findById(order.getId()).orElseThrow(() -> new NoSuchElementException("Element not found"));
        orderFound.setAddress(order.getAddress());
        orderFound.setBurger(order.getBurger());
        orderFound.setDateTime(order.getDateTime());
        order.setStatusOrder(order.getStatusOrder());
        return true;
    }

    public List<Order> findByCardNumber(String cardNumber) {
        return orderRepo.findByCardNumber(cardNumber);
    }


}