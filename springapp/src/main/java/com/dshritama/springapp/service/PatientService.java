package com.dshritama.springapp.service;

import java.util.List;
import java.util.Optional;

import com.dshritama.springapp.model.Patient;

public interface PatientService {
    public Patient createPatient(Patient patient);
	public Patient updatePatient(Patient patient);
	public void deletePatient(Long id);
	public List<Patient> getAllPatients();
	public Patient getPatientById(Long id);
}
