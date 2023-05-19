package com.example.burderdelivery.service;

import com.example.burderdelivery.models.Card;
import com.example.burderdelivery.models.Order;
import com.example.burderdelivery.models.StatusOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrderService orderService;
    private final CardService cardService;

    public Boolean payForOrder(Card card) {
        Boolean isPay = false;
        Card cardByNumber = cardService.getCardByNumber(card.getNumberOfCard());
        List<Order> byCardNumber = orderService.findByCardNumber(card.getNumberOfCard());

        for (Order order : byCardNumber) {
            if (!cardByNumber.getPerson().getUsername().equals(order.getPerson().getUsername())) {
                return isPay;
            }
            if (cardByNumber.getBalance() < order.getPayment().getPaymentAmount()) {
                return isPay;
            }
            cardByNumber.setBalance(cardByNumber.getBalance() - order.getPayment().getPaymentAmount());
            cardService.update(cardByNumber);
            order.setStatusOrder(StatusOrder.builder()
                    .name("change")
                    .description("change")
                    .build());
            orderService.update(order);
            isPay = true;
        }
        return isPay;
    }
}
