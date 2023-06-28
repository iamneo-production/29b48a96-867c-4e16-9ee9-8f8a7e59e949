package com.dshritama.springapp.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dshritama.springapp.model.Pharmacy;
import com.dshritama.springapp.repository.PharmacyRepository;
import com.dshritama.springapp.repository.PatientRepository;

@Service
public class PharmacyServiceImpl implements PharmacyService {
    private final PharmacyRepository pharmacyRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public PharmacyServiceImpl(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Pharmacy> getAllPharmacies() {
        return pharmacyRepository.findAll();
    }

    @Override
    public Pharmacy getPharmacyById(Long id) {
        return pharmacyRepository.findById(id).orElse(null);
    }

    @Override
    public Pharmacy createPharmacy(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    @Override
    public Pharmacy updatePharmacy(Long id, Pharmacy updatedPharmacy) {
        Pharmacy pharmacy = pharmacyRepository.findById(id).orElse(null);
        if (pharmacy != null) {
            pharmacy.setPatientId(updatedPharmacy.getPatientId());
            pharmacy.setMedicationName(updatedPharmacy.getMedicationName());
            pharmacy.setDosage(updatedPharmacy.getDosage());
            pharmacy.setRefillDate(updatedPharmacy.getRefillDate());
            pharmacy.setPrescriptionNumber(updatedPharmacy.getPrescriptionNumber());
            return pharmacyRepository.save(pharmacy);
        }
        return null;
    }

    @Override
    public void deletePharmacy(Long id) {
        pharmacyRepository.deleteById(id);
    }

    @Override
    public Patient getPatientByPharmacyId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPatientByPharmacyId'");
    }
 
   @Override
    public Patient getPatientByPharmacyId(Long id) {
        Pharmacy pharmacy = pharmacyRepository.findById(id).orElse(null);
        if (pharmacy != null) {
            Long patientId = pharmacy.getPatientId();
            return patientRepository.findById(patientId).orElse(null);
        }
        return null;
    }
} 