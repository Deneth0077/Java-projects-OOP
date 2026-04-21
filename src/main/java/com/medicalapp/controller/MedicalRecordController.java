package com.medicalapp.controller;

import com.medicalapp.dto.MedicalRecordDTO;
import com.medicalapp.service.IMedicalRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MedicalRecordController.java
 *
 * REST API Endpoints for Clinical History.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Relies on IMedicalRecordService.
 * - POLYMORPHISM: summary endpoint provides contextual data based on record type.
 *
 * Component: Component 06 — Medical Record Management
 * Layer: Controller
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final IMedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<MedicalRecordDTO> addRecord(@Valid @RequestBody MedicalRecordDTO dto) {
        return new ResponseEntity<>(medicalRecordService.addRecord(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<MedicalRecordDTO> getAllRecords() {
        return medicalRecordService.getAllRecords();
    }

    @GetMapping("/{id}")
    public MedicalRecordDTO getRecordById(@PathVariable Long id) {
        return medicalRecordService.getRecordById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<MedicalRecordDTO> getRecordsByPatient(@PathVariable Long patientId) {
        return medicalRecordService.getRecordsByPatient(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<MedicalRecordDTO> getRecordsByDoctor(@PathVariable Long doctorId) {
        return medicalRecordService.getRecordsByDoctor(doctorId);
    }

    @GetMapping("/{id}/summary")
    public String getRecordSummary(@PathVariable Long id) {
        // POLYMORPHISM: Actual summary depends on whether it's a Prescription or Lab record
        return medicalRecordService.getRecordSummary(id);
    }

    @PutMapping("/{id}")
    public MedicalRecordDTO updateRecord(@PathVariable Long id, @Valid @RequestBody MedicalRecordDTO dto, @RequestParam Long doctorId) {
        return medicalRecordService.updateRecord(id, dto, doctorId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        medicalRecordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}
