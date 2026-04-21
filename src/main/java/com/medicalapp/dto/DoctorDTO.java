package com.medicalapp.dto;

import com.medicalapp.entity.Doctor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalTime;

/**
 * DoctorDTO.java
 *
 * Data Transfer Object for exchanging Doctor data.
 *
 * OOP Concepts Applied:
 * - ENCAPSULATION: Protects internal entity state while exposing necessary features.
 *
 * Component: Component 02 — Doctor Management
 * Layer: DTO
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
public class DoctorDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phone;

    private String specialization;

    private Doctor.DoctorType doctorType;

    private Double consultationFee;

    private String availableDays;

    private LocalTime availableFrom;

    private LocalTime availableTo;

    // For SpecialistDoctor subtype
    private String specialistArea;
}
