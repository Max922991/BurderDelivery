package com.example.burderdelivery.models;

import com.example.burderdelivery.validation.Operation;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be not null!", groups = {Operation.OnUpdate.class, Operation.OnDelete.class})
    Long id;
    Double paymentAmount;
    @OneToMany(mappedBy = "payment")
    List<Order> orderList;
    @ManyToOne
    @JoinColumn(name = "card_id")
    Card card;

    @ManyToOne
    @JoinColumn(name = "person_id")
    Person person;



}
