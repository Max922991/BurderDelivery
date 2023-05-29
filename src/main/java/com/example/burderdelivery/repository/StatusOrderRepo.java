package com.example.burderdelivery.repository;

import com.example.burderdelivery.models.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusOrderRepo extends JpaRepository<StatusOrder, Long> {

    Optional<StatusOrder> findByName(String name);
}
