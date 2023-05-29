package com.example.burderdelivery.models;

import com.example.burderdelivery.validation.Operation;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Column(name = "name_owner")
    String nameOwner;
    @Column(name = "card_number")
    String numberOfCard;
    @Column(name = "expiry_date")
    LocalDateTime expiryDate;
    String cvcCode;
    double balance;
    @ManyToOne
    @JoinColumn(name = "person_id")
    Person person;
    @OneToMany
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    List<Payment> payment = new ArrayList<>();

}
