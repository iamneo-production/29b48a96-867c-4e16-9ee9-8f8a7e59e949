package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springapp.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>{
    
}
