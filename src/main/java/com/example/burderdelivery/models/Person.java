package com.example.burderdelivery.models;

import com.example.burderdelivery.validation.Operation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "user_name")
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", groups =
            {Operation.OnCreate.class, Operation.OnUpdate.class})
    String username;

    @Size(min = 2, max = 10, groups =
            {Operation.OnCreate.class, Operation.OnUpdate.class})
    String password;

    @OneToMany(mappedBy = "person")
    List<Order> orderList;

    @OneToMany(mappedBy = "person")
    List<Payment> paymentList;

    @OneToMany(mappedBy = "person")
    Set<Card> cardSet;

}
