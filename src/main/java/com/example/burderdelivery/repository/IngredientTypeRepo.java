package com.example.burderdelivery.repository;

import com.example.burderdelivery.models.IngredientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface IngredientTypeRepo extends JpaRepository<IngredientType, Long> {
}
