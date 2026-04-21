package com.medicalapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Patient.java
 *
 * Base entity representing a Patient in the medical system.
 * Uses Single Table Inheritance to handle different patient types.
 *
 * OOP Concepts Applied:
 * - INHERITANCE: extends BaseEntity to reuse id, createdAt, updatedAt.
 * - ENCAPSULATION: Private fields protected by Lombok @Data.
 *
 * Component: Component 01 — Patient Management
 * Layer: Entity
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
@Entity
@Table(name = "patients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "sub_patient_type")
@EqualsAndHashCode(callSuper = true)
public class Patient extends BaseEntity {

    // ENCAPSULATION: private field
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "NIC is required")
    @Column(unique = true)
    private String nic;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @Email(message = "Invalid email format")
    private String email;

    private String address;

    @Column(columnDefinition = "TEXT")
    private String medicalHistory;

    @Enumerated(EnumType.STRING)
    private PatientType patientType;

    public enum PatientType {
        OUTPATIENT, INPATIENT
    }
}
