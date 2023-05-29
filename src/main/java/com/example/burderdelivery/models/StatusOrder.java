package com.example.burderdelivery.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "status_orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    @OneToMany
    @JoinColumn(name = "status_order_id", referencedColumnName = "id")
    List<Order> orders = new ArrayList<>();

}
