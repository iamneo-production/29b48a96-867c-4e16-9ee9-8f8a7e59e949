package com.example.springapp.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patientId")
    private Long patientId;

    @Column(name = "doctorId")
    private Long doctorId;

    private LocalDate date;
    private LocalTime time;
    private String duration;
    private String status;

    @ManyToOne
    @JoinColumn(name = "patientId", insertable = false, updatable = false)
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctorId", insertable = false, updatable = false)
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Staff doctor;



    public Appointment() {
    }

    public Appointment(Long id, Long patientId, Long doctorId, LocalDate date, LocalTime time, String duration,
            String status) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.status = status;
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}