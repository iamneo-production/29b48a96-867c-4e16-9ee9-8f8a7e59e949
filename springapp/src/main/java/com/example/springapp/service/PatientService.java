package com.example.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.model.Appointment;
import com.example.springapp.model.Billing;
import com.example.springapp.model.MedicalRecord;
import com.example.springapp.model.Patient;
import com.example.springapp.model.Pharmacy;
import com.example.springapp.repository.AppointmentRepository;
import com.example.springapp.repository.BillingRepository;
import com.example.springapp.repository.MedicalRecordRepository;
import com.example.springapp.repository.PatientRepository;
import com.example.springapp.repository.PharmacyRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private MedicalRecordRepository medicalrecordRepository;

	@Autowired
    private AppointmentRepository appointmentRepository;

	@Autowired
    private BillingRepository billingRepository;

    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"));
    }

    public void deletePatient(Long id) {
        List<Pharmacy> pharmacyList = pharmacyRepository.findByPatientId(id);
        pharmacyRepository.deleteAll(pharmacyList);

        List<MedicalRecord> medicalRecords = medicalrecordRepository.findByPatientId(id);
        medicalrecordRepository.deleteAll(medicalRecords);

		List<Appointment> appointments = appointmentRepository.findByPatientId(id);
        appointmentRepository.deleteAll(appointments);

		List<Billing> bills = billingRepository.findByPatientId(id);
        billingRepository.deleteAll(bills);
        
        patientRepository.deleteById(id);
    }

	public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Patient patient) {
        Patient updatedPatient = patientRepository.findById(patient.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"));
		updatedPatient.setName(patient.getName());
		updatedPatient.setAge(patient.getAge());
		updatedPatient.setGender(patient.getGender());
		updatedPatient.setAddress(patient.getAddress());
		updatedPatient.setPhone(patient.getPhone());
		updatedPatient.setEmail(patient.getEmail());
		updatedPatient.setMedicalHistory(patient.getMedicalHistory());
		updatedPatient.setTreatmentPlan(patient.getTreatmentPlan());

        return patientRepository.save(updatedPatient);
    }
}