package com.example.burderdelivery.repository;

import com.example.burderdelivery.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MenuRepo extends JpaRepository<Menu, Long>{
}
