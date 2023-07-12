package com.example.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.model.Pharmacy;
import com.example.springapp.repository.PatientRepository;
import com.example.springapp.repository.PharmacyRepository;

@Service
public class PharmacyService {
    @Autowired
    private final PharmacyRepository pharmacyRepository;
    private final PatientRepository patientRepository;

    public PharmacyService(PharmacyRepository pharmacyRepository, PatientRepository patientRepository) {
        this.pharmacyRepository = pharmacyRepository;
        this.patientRepository = patientRepository;
    }

    public List<Pharmacy> getAllPharmacy() {
        return pharmacyRepository.findAll();
    }

    public Pharmacy getPharmacyById(Long id) {
        return pharmacyRepository.findById(id).orElse(null);
    }

    public Pharmacy createPharmacy(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    public Pharmacy updatePharmacy(Pharmacy updatedPharmacy) {
        Pharmacy pharmacy = pharmacyRepository.findById(updatedPharmacy.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid pharmacy ID"));;
       
           
            pharmacy.setMedicationName(updatedPharmacy.getMedicationName());
            pharmacy.setDosage(updatedPharmacy.getDosage());
            pharmacy.setRefillDate(updatedPharmacy.getRefillDate());
            pharmacy.setPrescriptionNumber(updatedPharmacy.getPrescriptionNumber());
            pharmacy.setPatientId(updatedPharmacy.getPatientId());
            return pharmacyRepository.save(pharmacy);
        
    
    }

    public void deletePharmacy(Long id) {
        pharmacyRepository.deleteById(id);
    }
}
