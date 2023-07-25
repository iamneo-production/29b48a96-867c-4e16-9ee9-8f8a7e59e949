package com.dshritama.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dshritama.springapp.model.MedicalRecord;
import com.dshritama.springapp.model.Patient;
import com.dshritama.springapp.model.Staff;
import com.dshritama.springapp.repository.MedicalRecordRepository;
import com.dshritama.springapp.repository.PatientRepository;
import com.dshritama.springapp.repository.StaffRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class MedicalRecordService {
    @Autowired
	private MedicalRecordRepository medicalRecordRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private StaffRepository staffRepository;
	
	public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }
    
    public Optional<MedicalRecord> getMedicalRecordById(Long id) {
        Optional<MedicalRecord> record = medicalRecordRepository.findById(id);
        return record;
    }

    
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        Patient patient = patientRepository.findById(medicalRecord.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"));
        Staff doctor = staffRepository.findById(medicalRecord.getDoctorId()).orElseThrow(() -> new IllegalArgumentException("Invalid Doctor ID"));;
        if(doctor!=null && doctor.getJobTitle().equalsIgnoreCase("doctor")) {
        	MedicalRecord newMedicalRecord = new MedicalRecord();
            newMedicalRecord.setPatientId(patient.getId());
            newMedicalRecord.setDoctorId(doctor.getId());
            newMedicalRecord.setDate(medicalRecord.getDate());
            newMedicalRecord.setDiagnosis(medicalRecord.getDiagnosis());
            newMedicalRecord.setPrescription(medicalRecord.getPrescription());
            newMedicalRecord.setNotes(medicalRecord.getNotes());
            medicalRecordRepository.save(newMedicalRecord);
            return new MedicalRecord(newMedicalRecord.getId(),newMedicalRecord.getPatientId(),newMedicalRecord.getDoctorId(),newMedicalRecord.getDate(),newMedicalRecord.getDiagnosis(),newMedicalRecord.getPrescription(),newMedicalRecord.getNotes());
        }
        else  throw new IllegalStateException("Doctor not found");
    }


    public Optional<MedicalRecord> updateMedicalRecord(MedicalRecord medicalRecord) {
        Optional<MedicalRecord> record = medicalRecordRepository.findById(medicalRecord.getId());
        if (record.isPresent()) {
            MedicalRecord existingRecord = record.get();
            existingRecord.setDate(medicalRecord.getDate());
            existingRecord.setDiagnosis(medicalRecord.getDiagnosis());
            existingRecord.setNotes(medicalRecord.getNotes());
            existingRecord.setPrescription(medicalRecord.getPrescription());
            existingRecord.setDoctorId(medicalRecord.getDoctorId());
            existingRecord.setPatientId(medicalRecord.getPatientId());
            medicalRecordRepository.save(existingRecord);
            return record;
        } else {
            return Optional.empty();
        }
    }
    

    public void deleteMedicalRecord(Long id) {
        medicalRecordRepository.deleteById(id);
    }

    public List<MedicalRecord> getRecordsByPatientId(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }
}