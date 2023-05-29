package com.example.burderdelivery.models;

import com.example.burderdelivery.validation.Operation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
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
    @NotNull(message = "Username must be not empty")
    @Size(min = 5, max = 40, message = "Email must be greater than 5 or less than 40",
            groups = {Operation.OnUpdate.class, Operation.OnCreate.class})
    @Email(regexp = ".+[@].+[\\.].+", message = "Email must contain only letters and numbers",
            groups = {Operation.OnUpdate.class, Operation.OnCreate.class})
    String username;
    @Size(min = 8, max = 50, message = "Password length must be smaller than 50",
            groups = {Operation.OnCreate.class, Operation.OnUpdate.class, Operation.OnDelete.class})
    String password;

    @Column(name = "phone_number")
    String phoneNumber;
    @OneToMany
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    List<Order> orders = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    Set<Card> cardSet = new HashSet<>();

}
