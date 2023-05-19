package com.example.burderdelivery.repository;

import com.example.burderdelivery.models.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BurgerRepo extends JpaRepository<Burger, Long> {

    Optional<Burger> findByName(String name);

    List<Burger> findByIsSpicyTrue();

    List<Burger> findByIsSpicyTrueAndPriceBetweenOrderByPriceAsc(Double minPrice, Double maxPrice);

    List<Burger> findByPriceBetweenOrderByPriceDesc(Double minPrice, Double maxPrice);
}
