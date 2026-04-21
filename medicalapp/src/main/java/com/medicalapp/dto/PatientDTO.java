package com.medicalapp.dto;

import com.medicalapp.entity.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * PatientDTO.java
 *
 * Data Transfer Object for exchanging Patient data.
 *
 * OOP Concepts Applied:
 * - ENCAPSULATION: Limits data exposure and allows for input validation.
 *
 * Component: Component 01 — Patient Management
 * Layer: DTO
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
public class PatientDTO {

    private Long id;

    // ENCAPSULATION: Input validation rules
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "NIC is required")
    private String nic;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @Email(message = "Invalid email format")
    private String email;

    private String address;

    private String medicalHistory;

    private Patient.PatientType patientType;

    // For InPatient subtype
    private String wardNumber;
}
