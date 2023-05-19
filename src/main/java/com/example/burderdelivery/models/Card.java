package com.example.burderdelivery.models;

import com.example.burderdelivery.validation.Operation;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cards")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be not null!", groups = {Operation.OnUpdate.class, Operation.OnDelete.class})
    Long id;
    String nameOwner;
    String numberOfCard;
    LocalDateTime dateOfCreated;
    String cvcCode;
    Double balance;

    @ManyToOne
    @JoinColumn(name = "person_id")
    Person person;

    @OneToMany(mappedBy = "card")
    List<Payment> payment;

}
