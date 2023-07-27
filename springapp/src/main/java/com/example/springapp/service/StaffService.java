package com.example.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springapp.model.Staff;
import com.example.springapp.repository.StaffRepository;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;


    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Staff staff) {
        Staff existingStaff = staffRepository.findById(staff.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff ID"));

        existingStaff.setName(staff.getName());
        existingStaff.setAge(staff.getAge());
        existingStaff.setGender(staff.getGender());
        existingStaff.setAddress(staff.getAddress());
        existingStaff.setPhone(staff.getPhone());
        existingStaff.setJobTitle(staff.getJobTitle());
        existingStaff.setEmail(staff.getEmail());
        existingStaff.setSalary(staff.getSalary());
        existingStaff.setBenefits(staff.getBenefits());

        return staffRepository.save(existingStaff);
    }

    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

	public Optional<Staff> getStaffById(Long id) {
        Optional<Staff> staffOptional = staffRepository.findById(id);
        return staffOptional.map(Optional::ofNullable).orElse(Optional.empty());
    }
    

	public Staff getDoctorById(Long id){
		Staff doctor=staffRepository.findById(id).orElse(null);
		if(doctor!=null) {
			if(doctor.getJobTitle().toLowerCase().equals("doctor")) {
				return doctor;
			}
		}
		return null;
	}
}