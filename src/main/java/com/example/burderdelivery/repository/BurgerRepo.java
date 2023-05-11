package com.example.burderdelivery.repository;

import com.example.burderdelivery.models.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BurgerRepo extends JpaRepository<Burger, Long> {
}
