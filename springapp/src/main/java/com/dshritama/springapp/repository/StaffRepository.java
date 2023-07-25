package com.dshritama.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dshritama.springapp.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}