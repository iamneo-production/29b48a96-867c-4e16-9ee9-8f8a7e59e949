package com.dshritama.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dshritama.springapp.model.Appointment;
import com.dshritama.springapp.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins="https://8081-feceaeedabbcfbdefaebabceebadffeaeaadbdbabf.project.examly.io")

public class AppointmentController {
    @Autowired
    private  AppointmentService appointmentService;
	
    @GetMapping
    public List<Appointment> getAllAppointment() {
        return appointmentService.getAllAppointment();
    }

    @GetMapping("/{id}")
    public Appointment getMedicalRecordById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @PostMapping
    public Appointment createMedicalRecord(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @PutMapping
    public Appointment updateAppointment(@RequestBody Appointment appointment) {
        return appointmentService.updateAppointment(appointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}