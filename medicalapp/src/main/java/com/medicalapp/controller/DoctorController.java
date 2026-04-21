package com.medicalapp.controller;

import com.medicalapp.dto.DoctorDTO;
import com.medicalapp.service.IDoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * DoctorController.java
 *
 * REST API Endpoints for Doctor Management.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Relies on IDoctorService.
 * - POLYMORPHISM: getCalculatedFee endpoint demonstrates specialized fee logic.
 *
 * Component: Component 02 — Doctor Management
 * Layer: Controller
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final IDoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorDTO> addDoctor(@Valid @RequestBody DoctorDTO dto) {
        return new ResponseEntity<>(doctorService.addDoctor(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<DoctorDTO> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public DoctorDTO getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @GetMapping("/specialization/{spec}")
    public List<DoctorDTO> getDoctorsBySpecialization(@PathVariable String spec) {
        return doctorService.getDoctorsBySpecialization(spec);
    }

    @GetMapping("/available/{day}")
    public List<DoctorDTO> getDoctorsByAvailableDay(@PathVariable String day) {
        return doctorService.getDoctorsByAvailableDay(day);
    }

    @GetMapping("/{id}/fee")
    public Double getCalculatedFee(@PathVariable Long id) {
        // POLYMORPHISM: This call resolves to different logic for General vs Specialist doctors
        return doctorService.getCalculatedFee(id);
    }

    @PutMapping("/{id}")
    public DoctorDTO updateDoctor(@PathVariable Long id, @Valid @RequestBody DoctorDTO dto) {
        return doctorService.updateDoctor(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
