package com.example.springapp.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@JsonPropertyOrder({"id", "medication_name", "dosage", "refill_date", "prescription_number", "patient_id"})
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("patient_id")
    private Long patientId;

    @JsonProperty("medication_name")
    private String medicationName;

    @JsonProperty("dosage")
    private String dosage;

    @JsonProperty("refill_date")
    private LocalDate refillDate;

    @JsonProperty("prescription_number")
    private Long prescriptionNumber;


    public Pharmacy() {
    }

    public Pharmacy(Long id, Long patientId, String medicationName, String dosage, LocalDate refillDate,
            Long prescriptionNumber) {
        this.id = id;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.refillDate = refillDate;
        this.prescriptionNumber = prescriptionNumber;
        
    }

    @Override
    public String toString() {
        return "Pharmacy [id=" + id  + ", medicationName=" + medicationName + ", dosage="
                + dosage + ", refillDate=" + refillDate + ", prescriptionNumber=" + prescriptionNumber + ", patientId=" + patientId + "]";
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

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public LocalDate getRefillDate() {
        return refillDate;
    }

    public void setRefillDate(LocalDate refillDate) {
        this.refillDate = refillDate;
    }

    public Long getPrescriptionNumber() {
        return prescriptionNumber;
    }

    public void setPrescriptionNumber(Long prescriptionNumber) {
        this.prescriptionNumber = prescriptionNumber;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", insertable = false, updatable = false)
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Patient patient;
}