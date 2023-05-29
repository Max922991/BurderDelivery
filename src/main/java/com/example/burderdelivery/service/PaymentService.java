package com.example.burderdelivery.service;

import com.example.burderdelivery.dto.AskDto;
import com.example.burderdelivery.models.Burger;
import com.example.burderdelivery.models.Card;
import com.example.burderdelivery.models.Order;
import com.example.burderdelivery.models.StatusOrder;
import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrderService orderService;
    private final CardService cardService;
    private final StatusOrderService statusOrderService;

    public AskDto payToOrder(Long orderId, Long cardId) {
        Order order = orderService.getById(orderId);
        Card card = cardService.getById(cardId);
        double moneySubtraction = 0;

        List<Burger> burgerList = order.getBurgers();
        double sum = burgerList.stream()
                .mapToDouble(Burger::getPrice).sum();

        if (sum > 100) {
            throw new IllegalArgumentException();
        }
        if (sum <= 50) {
            // TODO вопрос с кэшами
        }
        if (sum <= 100) {
           moneySubtraction = card.getBalance() - sum;
           card.setBalance(moneySubtraction);
           cardService.save(card);
           StatusOrder statusOrder = statusOrderService.getByName("Оплата успешно прошла");
           orderService.linkOrderToStatusOrder(orderId, statusOrder.getId());
        }

        AskDto askDto = AskDto.builder()
                .answer("Оплата прошла")
                .build();

        return askDto;
    }

}
