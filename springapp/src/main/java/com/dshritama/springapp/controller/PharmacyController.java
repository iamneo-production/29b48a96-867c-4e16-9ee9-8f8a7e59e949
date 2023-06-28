package com.dshritama.springapp.controller;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dshritama.springapp.model.Patient;
import com.dshritama.springapp.model.Pharmacy;
import com.dshritama.springapp.service.PharmacyService;
import com.dshritama.springapp.service.PatientService;

@RestController
@RequestMapping("/pharms")
@CrossOrigin(origins = "http://localhost:3000")
public class PharmacyController {
    private final PharmacyService pharmacyService;

    @Autowired
    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping
    public ResponseEntity<List<Pharmacy>> getAllPharmacies() {
        List<Pharmacy> pharmacies = pharmacyService.getAllPharmacies();
        return new ResponseEntity<>(pharmacies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pharmacy> getPharmacyById(@PathVariable("id") Long id) {
        Pharmacy pharmacy = pharmacyService.getPharmacyById(id);
        if (pharmacy != null) {
            return new ResponseEntity<>(pharmacy, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Pharmacy> createPharmacy(@RequestBody Pharmacy pharmacy) {
        Pharmacy createdPharmacy = pharmacyService.createPharmacy(pharmacy);
        return new ResponseEntity<>(createdPharmacy, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pharmacy> updatePharmacy(@PathVariable("id") Long id, @RequestBody Pharmacy updatedPharmacy) {
        Pharmacy pharmacy = pharmacyService.updatePharmacy(id, updatedPharmacy);
        if (pharmacy != null) {
            return new ResponseEntity<>(pharmacy, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePharmacy(@PathVariable("id") Long id) {
        pharmacyService.deletePharmacy(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/patient")
    public ResponseEntity<Patient> getPatientByPharmacyId(@PathVariable("id") Long id) {
        Patient patient = pharmacyService.getPatientByPharmacyId(id);
        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } 
} 