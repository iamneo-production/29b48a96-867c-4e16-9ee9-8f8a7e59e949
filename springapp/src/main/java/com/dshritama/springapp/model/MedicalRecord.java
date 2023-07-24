package com.dshritama.springapp.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id")
    @JsonProperty("patient_id")
    private Long patientId;

    @Column(name = "doctor_id")
    @JsonProperty("doctor_id")
    private Long doctorId;

    private LocalDate date;
    private String diagnosis;
    private String prescription;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "patient_id", insertable = false, updatable = false)
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Staff doctor;

    public MedicalRecord() {
    }

    public MedicalRecord(Long id, Long patientId, Long doctorId, LocalDate date, String diagnosis, String prescription, String notes){
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}