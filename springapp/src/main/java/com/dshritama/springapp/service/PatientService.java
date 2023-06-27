package com.dshritama.springapp.service;

import java.util.List;
import java.util.Optional;

import com.dshritama.springapp.model.Patient;

public interface PatientService {
    List<Patient> getAllPatients();
    Optional<Patient> getPatientById(Long id);
    Patient createPatient(Patient patient);
    Patient updatePatient(Long id, Patient patient);
    void deletePatientById(Long id);
}
