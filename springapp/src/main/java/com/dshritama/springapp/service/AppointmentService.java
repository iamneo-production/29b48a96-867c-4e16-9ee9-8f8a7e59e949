package com.dshritama.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dshritama.springapp.model.Appointment;
import com.dshritama.springapp.model.Patient;
import com.dshritama.springapp.model.Staff;
import com.dshritama.springapp.repository.AppointmentRepository;
import com.dshritama.springapp.repository.PatientRepository;
import com.dshritama.springapp.repository.StaffRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private StaffRepository staffRepository;

    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with ID: " + id));
    }

    public Appointment createAppointment(Appointment appointment) {
        Patient patient = patientRepository.findById(appointment.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"));

        Staff doctor = staffRepository.findById(appointment.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID"));

        if (doctor != null && doctor.getJobTitle().equalsIgnoreCase("doctor")) {
            Appointment newAppointment = new Appointment();
            newAppointment.setPatientId(patient.getId());
            newAppointment.setDoctorId(doctor.getId());
            newAppointment.setDate(appointment.getDate());
            newAppointment.setTime(appointment.getTime());
            newAppointment.setDuration(appointment.getDuration());
            newAppointment.setStatus(appointment.getStatus());
            appointmentRepository.save(newAppointment);
            return newAppointment;
        } else {
            throw new IllegalStateException("Doctor not found");
        }
    }

    public Appointment updateAppointment(Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(appointment.getId())
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with ID: " + appointment.getId()));

        existingAppointment.setDate(appointment.getDate());
        existingAppointment.setTime(appointment.getTime());
        existingAppointment.setDuration(appointment.getDuration());
        existingAppointment.setStatus(appointment.getStatus());

        appointmentRepository.save(existingAppointment);
        return existingAppointment;
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> getAppointmentByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }
}