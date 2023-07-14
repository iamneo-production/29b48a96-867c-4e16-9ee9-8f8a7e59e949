package com.dshritama.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dshritama.springapp.model.Billing;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long>{
    List<Billing> findByPatientId(Long patientId);
}
