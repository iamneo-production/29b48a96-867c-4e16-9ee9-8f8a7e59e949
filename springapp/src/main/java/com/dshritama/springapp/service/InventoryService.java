package com.dshritama.springapp.service;

import java.util.List;

import com.dshritama.springapp.model.Inventory;

public interface InventoryService {
    List<Inventory> getAllInventory();
    Inventory getInventoryById(Long id);
    Inventory createInventory(Inventory inventory);
    Inventory updateInventory(Inventory inventory);
    void deleteInventoryById(Long id);   
}
