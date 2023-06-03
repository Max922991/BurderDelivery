package com.example.burderdelivery.service;

import com.example.burderdelivery.dto.OrderDTO;
import com.example.burderdelivery.dto.StatusOrderDto;
import com.example.burderdelivery.mapper.OrderMapper;
import com.example.burderdelivery.mapper.StatusOrderMapper;
import com.example.burderdelivery.models.Burger;
import com.example.burderdelivery.models.Order;
import com.example.burderdelivery.models.Person;
import com.example.burderdelivery.models.StatusOrder;
import com.example.burderdelivery.repository.BurgerRepo;
import com.example.burderdelivery.repository.OrderRepo;
import com.example.burderdelivery.repository.PersonRepo;
import com.example.burderdelivery.repository.StatusOrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {


   private final KafkaTemplate<String, Object> template;

    private final OrderRepo orderRepo;
    private final BurgerService burgerService;
    private final StatusOrderService statusOrderService;
    private final PersonService personService;
    private final OrderMapper orderMapper;
    private final PersonRepo personRepo;
    private final StatusOrderRepo statusOrderRepo;
    private final StatusOrderMapper statusOrderMapper;
    private int counter = 1;
    private final BurgerRepo burgerRepo;

    public OrderDTO createOrder(Long personId, List<Long> burgerId) {
        List<Burger> burgers = burgerId.stream()
                .map(burgerService::getById)
                .toList();
        double sum = burgers.stream()
                .mapToDouble(Burger::getPrice)
                .sum();

        Order order = Order.builder()
                .numberOrder(counter++)
                .burgers(burgers)
                .createdAt(Instant.now())
                .build();

        orderRepo.save(order);

        StatusOrder statusOrder = statusOrderService.getByName("waiting for pay");

        linkOrderToStatusOrder(statusOrder.getId(), order.getId());
        linkOrderToPerson(order.getId(), personId);

        OrderDTO orderDTO = orderMapper.toOrderDto(order);
        StatusOrderDto statusOrderDto = statusOrderMapper.toDto(statusOrder);

        orderDTO.setStatusOrderDto(statusOrderDto);
        orderDTO.setToPay(sum);

        template.send("delivery_burger", orderDTO);

        return orderDTO;
    }

    public void linkOrderToPerson(Long orderId, Long personId) {
        Order order = getById(orderId);
        Person person = personService.findById(personId);

        person.getOrders().add(order);
        orderRepo.save(order);
    }

    public void linkOrderToStatusOrder(Long orderId, Long statusOrderId) {
        Order order = getById(orderId);
        StatusOrder statusOrder = statusOrderService.getById(statusOrderId);

        statusOrder.getOrders().add(order);
        statusOrderService.save(statusOrder);
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }

    public Order getById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found"));
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepo.findAll();
        return orderMapper.toOrderListDto(orders);
    }

    public OrderDTO update(Long orderId, Long burgerId, int numberBurger) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new EntityNotFoundException("dwdw"));
        Burger burger = burgerRepo.findById(burgerId).orElseThrow(() -> new EntityNotFoundException("dwdw"));
        List<Burger> burgerList = order.getBurgers();
        burgerList.stream()
                .filter(burger1 -> burger1.getId().equals(burgerId))
                .toList();
        if (numberBurger < 0) {
            throw new IllegalArgumentException("dwdw");
        }
        if (burgerList.size() == numberBurger) {
            throw new IllegalArgumentException("wdw");
        }
        double sum = burgerList.stream()
                .mapToDouble(Burger::getPrice)
                .sum();
        OrderDTO orderDTO = orderMapper.toOrderDto(order);
        orderDTO.setToPay(sum);

        orderRepo.save(order);
        return orderDTO;
    }
}