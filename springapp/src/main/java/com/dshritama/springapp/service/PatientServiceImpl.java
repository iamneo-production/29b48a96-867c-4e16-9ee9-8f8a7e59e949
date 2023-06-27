package com.dshritama.springapp.service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }


    @Override
    public void deletePatientById(Long id) {
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
    public Patient updatePatient(Long id, Patient patient) {
        Optional<Patient> existingPatient = patientRepository.findById(id);
    if (existingPatient.isPresent()) {
        Patient patientToUpdate = existingPatient.get();
        patientToUpdate.setName(patient.getName());
        patientToUpdate.setAge(patient.getAge());
        patientToUpdate.setGender(patient.getGender());
        patientToUpdate.setAddress(patient.getAddress());
        patientToUpdate.setPhone(patient.getPhone());
        patientToUpdate.setEmail(patient.getEmail());
        patientToUpdate.setHistory(patient.getHistory());
        patientToUpdate.setTreatment(patient.getTreatment());
        patientRepository.save(patientToUpdate);
    }
    return patient;
    }

}
