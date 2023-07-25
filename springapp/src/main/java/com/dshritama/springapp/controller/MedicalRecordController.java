package com.dshritama.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


import com.dshritama.springapp.model.MedicalRecord;
import com.dshritama.springapp.service.MedicalRecordService;

@RestController
@RequestMapping("/medical-records")
@CrossOrigin(origins="https://8081-feceaeedabbcfbdefaebabceebadffeaeaadbdbabf.project.examly.io")
public class MedicalRecordController {
    @Autowired
    private  MedicalRecordService medicalRecordService;
	
    @GetMapping
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordService.getAllMedicalRecords();
    }

    @GetMapping("/{id}")
        public ResponseEntity<Optional<MedicalRecord>> getMedicalRecordById(@PathVariable Long id) {
            Optional<MedicalRecord> medicalRecord = medicalRecordService.getMedicalRecordById(id);
            return ResponseEntity.ok(medicalRecord);
        }


    @PostMapping
    public MedicalRecord createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordService.createMedicalRecord(medicalRecord);
    }

    @PutMapping
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        Optional<MedicalRecord> updatedRecord = medicalRecordService.updateMedicalRecord(medicalRecord);
        if (updatedRecord.isPresent()) {
            return ResponseEntity.ok(updatedRecord.get());
        } else {
            return ResponseEntity.notFound().build();
        }
}


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicalRecord(@PathVariable Long id) {
        medicalRecordService.deleteMedicalRecord(id);
        return ResponseEntity.ok("Deleted");
    }
}