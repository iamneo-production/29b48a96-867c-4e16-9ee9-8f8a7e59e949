package com.dshritama.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dshritama.springapp.model.Inventory;
import com.dshritama.springapp.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository InventoryRepository;

    @Override
    public List<Inventory> getAllInventory() {
        return InventoryRepository.findAll();
    }

    @Override
    public Inventory getInventoryById(Long id) {
        return InventoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid inventory ID"));
    }


    @Override
    public void deleteInventoryById(Long id) {
        InventoryRepository.deleteById(id);
    }


    @Override
    public Inventory createInventory(Inventory Inventory) {
        return InventoryRepository.save(Inventory);
    }

    @Override
    public Inventory updateInventory(Inventory inventory) {
        Inventory updatedInventory = InventoryRepository.findById(inventory.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Inventory ID"));
		updatedInventory.setName(inventory.getName());
		updatedInventory.setPrice(inventory.getPrice());
		updatedInventory.setCategory(inventory.getCategory());
		updatedInventory.setQuantity(inventory.getQuantity());
		updatedInventory.setSupplier(inventory.getSupplier());

        return InventoryRepository.save(updatedInventory);
    }
}