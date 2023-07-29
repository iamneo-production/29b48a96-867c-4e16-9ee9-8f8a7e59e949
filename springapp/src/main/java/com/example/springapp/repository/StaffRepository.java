package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springapp.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}