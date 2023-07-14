package com.dshritama.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapp.model.Billing;
import com.example.springapp.service.BillingService;

@RestController
@RequestMapping("/billing")
public class BillingController {
    @Autowired
    private  BillingService billingService;

    @GetMapping
    public List<Billing> getAllBilling() {
        return billingService.getAllBilling();
    }

    @GetMapping("/{id}")
    public Billing getBillById(@PathVariable Long id) {
        return billingService.getBillById(id);
    }

    @PostMapping
    public Billing createMedicalRecord(@RequestBody Billing bill) {
        return billingService.createBill(bill);
    }

    @PutMapping
    public Billing updateBill(@RequestBody Billing bill) {
        return billingService.updateBill(bill);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable Long id) {
        billingService.deleteBill(id);
        return ResponseEntity.ok("Deleted");
    }
}