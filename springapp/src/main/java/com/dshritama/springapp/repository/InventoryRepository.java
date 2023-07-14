package com.dshritama.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dshritama.springapp.model.Inventory;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{
    
}
