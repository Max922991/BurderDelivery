package com.example.burderdelivery.models;

import com.example.burderdelivery.validation.Operation;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "burger")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Burger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be not null!",
            groups = {Operation.OnUpdate.class, Operation.OnDelete.class})
    Long id;

    @NotBlank(message = "Name must be not empty!")
    @Length(max = 50, message = "Name length must be smaller than 50",
            groups = {Operation.OnUpdate.class, Operation.OnCreate.class})
    String name;
    @NotBlank(message = "Description must be not empty!")
    @Length(max = 255, message = "Description length must be smaller than 255",
            groups = {Operation.OnUpdate.class, Operation.OnDelete.class})
    String description;
    @Min(value = 1, message = "Price must be more than 0",
            groups = {Operation.OnUpdate.class, Operation.OnCreate.class})
    Double price;
    @Column(name = "is_spicy")
    Boolean isSpicy;
}
