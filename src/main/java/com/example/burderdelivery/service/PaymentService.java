package com.example.burderdelivery.service;

import com.example.burderdelivery.dto.AskDto;
import com.example.burderdelivery.exception.PaymentException;
import com.example.burderdelivery.models.Burger;
import com.example.burderdelivery.models.Card;
import com.example.burderdelivery.models.Order;
import com.example.burderdelivery.models.StatusOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrderService orderService;
    private final CardService cardService;
    private final StatusOrderService statusOrderService;

    @Transactional
    public AskDto payToOrder(Long orderId, Long cardId) {
        Order order = orderService.getById(orderId);
        Card card = cardService.getById(cardId);
        StatusOrder statusOrder = statusOrderService.getByName("Order has been pay");
        double moneySubtraction = 0;

        LocalDateTime localDateTime = LocalDateTime.now();

        List<Burger> burgerList = order.getBurgers();
        double sum = burgerList.stream()
                .mapToDouble(Burger::getPrice).sum();

      if (card.getExpiryDate().isBefore(localDateTime)) {
          throw new PaymentException("dwd");
      }
      if (sum <= card.getBalance()) {
          moneySubtraction = card.getBalance() - sum;
          card.setBalance(moneySubtraction);
          cardService.save(card);
          statusOrderService.removeStatusFromOrder(orderId);
          orderService.linkOrderToStatusOrder(orderId, statusOrder.getId());
          return AskDto.builder()
                  .answer("Оплата прошла")
                  .build();
      }
        throw new PaymentException("dwdw");
    }
}
