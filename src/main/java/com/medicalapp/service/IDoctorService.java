package com.medicalapp.service;

import com.medicalapp.dto.DoctorDTO;
import java.util.List;

/**
 * IDoctorService.java
 *
 * Abstraction for Doctor Management logic.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Contract for handling diverse doctor types and polymorphically calculating fees.
 *
 * Component: Component 02 — Doctor Management
 * Layer: Service Interface
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
public interface IDoctorService {

    DoctorDTO addDoctor(DoctorDTO dto);

    List<DoctorDTO> getAllDoctors();

    DoctorDTO getDoctorById(Long id);

    List<DoctorDTO> getDoctorsBySpecialization(String spec);

    List<DoctorDTO> getDoctorsByAvailableDay(String day);

    DoctorDTO updateDoctor(Long id, DoctorDTO dto);

    void deleteDoctor(Long id);

    /**
     * ABSTRACTION: Method to retrieve polymorphically calculated fee.
     */
    double getCalculatedFee(Long id);
}
