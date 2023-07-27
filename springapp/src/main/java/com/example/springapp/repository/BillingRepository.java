package com.example.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springapp.model.Billing;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long>{
    List<Billing> findByPatientId(Long patientId);
}