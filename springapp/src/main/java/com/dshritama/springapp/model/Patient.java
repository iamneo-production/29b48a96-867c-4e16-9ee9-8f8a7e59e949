package com.dshritama.springapp.model;

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
    private Integer age;
    
    private String gender;
    private String address;
    private String phone;
    private String email;
    private String history;
    private String treatment;

    public Patient() {}

    public Patient(Long id, String name, Integer age, String gender, String address, String phone, String email,
            String history, String treatment) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.history = history;
        this.treatment = treatment;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", address=" + address
                + ", phone=" + phone + ", email=" + email + ", history=" + history + ", treatment="
                + treatment + "]";
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    public String getHistory() {
        return history;
    }

    public void setHistory(String medicalHistory) {
        this.history = medicalHistory;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatmentPlan) {
        this.treatment = treatmentPlan;
    }
}
