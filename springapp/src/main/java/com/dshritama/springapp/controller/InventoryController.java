package com.dshritama.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.dshritama.springapp.model.Inventory;
import com.dshritama.springapp.service.InventoryService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        List<Inventory> inventories = inventoryService.getAllInventory();
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        Inventory inventory = inventoryService.getInventoryById(id);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        Inventory createdInventory = inventoryService.createInventory(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInventory);
    }

    @PutMapping
    public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory inventory) {
        Inventory updatedInventory = inventoryService.updateInventory(inventory);
        return ResponseEntity.ok(updatedInventory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable("id") Long id) {
        inventoryService.deleteInventoryById(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}