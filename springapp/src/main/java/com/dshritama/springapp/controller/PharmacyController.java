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

//import com.dshritama.springapp.model.Patient;
import com.dshritama.springapp.model.Pharmacy;
import com.dshritama.springapp.service.PharmacyService;


@CrossOrigin(origins="https://8081-eccceeeebceecccfbdefaebabceebadffeaeaadbdbabf.project.examly.io/")
@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {
    @Autowired
    private PharmacyService pharmacyService;

    
    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping
    public ResponseEntity<List<Pharmacy>> getAllPharmacy() {
        List<Pharmacy> pharmacies = pharmacyService.getAllPharmacy();
        return new ResponseEntity<>(pharmacies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pharmacy> getPharmacyById(@PathVariable Long id) {
        Pharmacy pharmacy = pharmacyService.getPharmacyById(id);
            return new ResponseEntity<>(pharmacy, HttpStatus.OK);
        
    }

    @PostMapping
    public ResponseEntity<Pharmacy> createPharmacy(@RequestBody Pharmacy pharmacy) {
        Pharmacy createdPharmacy = pharmacyService.createPharmacy(pharmacy);
        return new ResponseEntity<>(createdPharmacy, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Pharmacy> updatePharmacy(@RequestBody Pharmacy updatedPharmacy) {
        Pharmacy pharmacy = pharmacyService.updatePharmacy(updatedPharmacy);
        if (pharmacy != null) {
            return new ResponseEntity<>(pharmacy, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePharmacy(@PathVariable("id") Long id) {
        pharmacyService.deletePharmacy(id);
        return  ResponseEntity.ok("Deleted");
    }

    
}