package com.medicalapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalTime;

/**
 * Doctor.java
 *
 * Abstract base entity representing a Doctor.
 * Defines the contract for polymorphic consultation fee calculation.
 *
 * OOP Concepts Applied:
 * - INHERITANCE: Extends BaseEntity.
 * - POLYMORPHISM: Abstract calculateConsultationFee() method resolved at runtime by subtypes.
 * - ENCAPSULATION: Private fields for name, schedule, and fees.
 *
 * Component: Component 02 — Doctor Management
 * Layer: Entity
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
@Entity
@Table(name = "doctors")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "sub_doctor_type")
@EqualsAndHashCode(callSuper = true)
public abstract class Doctor extends BaseEntity {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phone;

    private String specialization;

    @Enumerated(EnumType.STRING)
    private DoctorType doctorType;

    private Double consultationFee;

    private String availableDays; // e.g. "MON,WED,FRI"
    private LocalTime availableFrom;
    private LocalTime availableTo;

    public enum DoctorType {
        GENERAL, SPECIALIST
    }

    /**
     * POLYMORPHISM: Abstract method to be overridden by subclasses with specific fee logic.
     *
     * @return The calculated consultation fee
     */
    public abstract double calculateConsultationFee();
}
