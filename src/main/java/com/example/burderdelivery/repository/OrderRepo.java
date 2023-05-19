package com.example.burderdelivery.repository;

import com.example.burderdelivery.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findByStatusOrderName(String name);
    @Query("select o from Order o join o.person p join p.cardSet cd where cd.numberOfCard = :numberOfCard")
    List<Order> findByCardNumber(@Param("numberOfCard") String cardNumber);



}
