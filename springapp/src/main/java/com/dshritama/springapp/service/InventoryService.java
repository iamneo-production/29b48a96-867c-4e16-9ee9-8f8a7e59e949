package com.dshritama.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dshritama.springapp.model.Inventory;
import com.dshritama.springapp.repository.InventoryRepository;

@Service
public class InventoryService{
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid inventory ID"));
    }

    public void deleteInventoryById(Long id) {
        inventoryRepository.deleteById(id);
    }

    public Inventory createInventory(Inventory Inventory) {
        return inventoryRepository.save(Inventory);
    }

    public Inventory updateInventory(Inventory inventory) {
        Inventory updatedInventory = inventoryRepository.findById(inventory.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Inventory ID"));
		updatedInventory.setName(inventory.getName());
		updatedInventory.setPrice(inventory.getPrice());
		updatedInventory.setCategory(inventory.getCategory());
		updatedInventory.setQuantity(inventory.getQuantity());
		updatedInventory.setSupplier(inventory.getSupplier());

        return inventoryRepository.save(updatedInventory);
    }
}