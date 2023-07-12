package com.example.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.model.Billing;
import com.example.springapp.model.Patient;
import com.example.springapp.repository.BillingRepository;
import com.example.springapp.repository.PatientRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class BillingService {
    @Autowired
	private BillingRepository billingRepository;
	@Autowired
	private PatientRepository patientRepository;

    public List<Billing> getAllBilling() {
        return billingRepository.findAll();
    }
    
    public Billing getBillById(Long id) {
        return billingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bill not found with ID: " + id));
    }

    
    public Billing createBill(Billing bill) {
        Patient patient = patientRepository.findById(bill.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"));
        if(patient!=null) {
        	Billing newBill = new Billing();
            newBill.setDate(bill.getDate());
            newBill.setAmount(bill.getAmount());
            newBill.setTreatmentDescription(bill.getTreatmentDescription());
            newBill.setPatientId(patient.getId());
            billingRepository.save(newBill);
            return new Billing(newBill.getId(),newBill.getPatientId(),newBill.getDate(),newBill.getTreatmentDescription(),newBill.getAmount());
        }
        else  throw new IllegalStateException("Patient not found");
    }


    public Billing updateBill(Billing bill) {
        Billing updatedBill=billingRepository.findById(bill.getId()).orElseThrow(() -> new EntityNotFoundException("Bill not found with ID: " + bill.getId()));
        if(updatedBill!=null) {
        	updatedBill.setId(bill.getId());
        	updatedBill.setDate(bill.getDate());
        	updatedBill.setAmount(bill.getAmount());
        	updatedBill.setTreatmentDescription(bill.getTreatmentDescription());
        	updatedBill.setPatientId(bill.getPatientId());
        	billingRepository.save(updatedBill);
        	return updatedBill;
        }
        else {
        	return null;
        }
    }

    public void deleteBill(Long id) {
        billingRepository.deleteById(id);
    }

    public List<Billing> getRecordsByPatientId(Long patientId) {
        return billingRepository.findByPatientId(patientId);
    }
}
