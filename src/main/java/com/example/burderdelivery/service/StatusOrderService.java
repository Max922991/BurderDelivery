package com.example.burderdelivery.service;

import com.example.burderdelivery.models.Order;
import com.example.burderdelivery.models.StatusOrder;
import com.example.burderdelivery.repository.StatusOrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusOrderService {

    private final StatusOrderRepo statusOrderRepo;

    public StatusOrder getByName(String name) {
       return statusOrderRepo.findByName(name)
               .orElseThrow(() -> new EntityNotFoundException("Status not found"));
    }

    public StatusOrder getById(Long id) {
        return statusOrderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status not found"));
    }

    public StatusOrder save(StatusOrder statusOrder) {
        return statusOrderRepo.save(statusOrder);
    }

    public void removeStatusFromOrder(Long order) {
        StatusOrder waitingForPay = getByName("Waiting for pay");
        List<Order> orders = waitingForPay.getOrders();
        orders.removeIf(order1 -> order1.getId().equals(order));
        save(waitingForPay);
    }
}
