package com.example.burderdelivery.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
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
    String name;
    @Column(name = "number_order")
    int numberOrder;
    @Column(name = "created_date")
    Instant createdAt = Instant.now();
    @OneToMany
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    List<Burger> burgers = new ArrayList<>();


}
