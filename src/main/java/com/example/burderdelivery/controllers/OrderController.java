package com.example.burderdelivery.controllers;

import com.example.burderdelivery.dto.OrderDTO;
import com.example.burderdelivery.models.Order;
import com.example.burderdelivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO orderDTO = orderService.getById(id);
        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping
    public ResponseEntity<Order> saveOrder(@RequestBody OrderDTO orderDTO) {
        Order order = null;
        try {
            order = orderService.save(orderDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "enable create order!");
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrderDTO(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        Boolean isUpdate = orderService.update(id, orderDTO);
        if (isUpdate) {
            OrderDTO updateOrder = orderService.getById(id);
            return ResponseEntity.ok(updateOrder);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
