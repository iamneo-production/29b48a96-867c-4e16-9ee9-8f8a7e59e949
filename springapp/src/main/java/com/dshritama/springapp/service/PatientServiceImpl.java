package com.dshritama.springapp.service;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dshritama.springapp.model.Patient;
import com.dshritama.springapp.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"));
    }


    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    /* @Override
    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
    } */

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
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
