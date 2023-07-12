package com.example.springapp.model;

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
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private Long amount;

    @Column(name = "treatment_description")
    @JsonProperty("treatment_description")
    private String treatmentDescription;

    @Column(name = "patient_id")
    @JsonProperty("patient_id")
    private Long patientId;

    @ManyToOne
    @JoinColumn(name = "patient_id", insertable = false, updatable = false)
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Patient patient;

    public Billing() {
    }

    public Billing(Long id, Long patientId, LocalDate date, String treatmentDescription, Long amount) {
        this.id = id;
        this.patientId = patientId;
        this.date = date;
        this.treatmentDescription = treatmentDescription;
        this.amount = amount;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    public void setTreatmentDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
