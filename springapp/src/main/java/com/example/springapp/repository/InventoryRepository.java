package com.example.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springapp.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{
    
}