package com.example.burderdelivery.models;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String address;
    @OneToMany(mappedBy = "order")
    List<Burger> burger;
    LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "payment_id")
    Payment payment;
    @ManyToOne
    @JoinColumn(name = "person_id")
    Person person;
    @ManyToOne
    @JoinColumn(name = "status_order_id")
    StatusOrder statusOrder;


}
