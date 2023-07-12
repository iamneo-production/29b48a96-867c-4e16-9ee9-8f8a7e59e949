package com.example.springapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long age;
    
    private String gender;
    private String address;
    private String phone;
    private String email;
    
    @Column(name = "medicalHistory")
    private String medicalHistory;
    @Column(name = "treatmentPlan")
    private String treatmentPlan;

    public Patient() {}

    public Patient(Long id, String name, Long age, String gender, String address, String phone, String email,
            String medicalHistory, String treatmentPlan) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.medicalHistory = medicalHistory;
        this.treatmentPlan = treatmentPlan;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", address=" + address
                + ", phone=" + phone + ", email=" + email + ", medicalHistory=" + medicalHistory + ", treatmentPlan="
                + treatmentPlan + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }
}
