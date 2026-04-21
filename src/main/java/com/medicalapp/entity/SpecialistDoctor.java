package com.medicalapp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SpecialistDoctor.java
 *
 * Concrete implementation for Doctors with specialized areas of expertise.
 *
 * OOP Concepts Applied:
 * - INHERITANCE: Extends Doctor.
 * - POLYMORPHISM: Overrides calculateConsultationFee() to apply a 50% specialist premium.
 * - ENCAPSULATION: Specialized field for expert area.
 *
 * Component: Component 02 — Doctor Management
 * Layer: Entity (Subclass)
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
@Entity
@DiscriminatorValue("SPECIALIST")
@EqualsAndHashCode(callSuper = true)
public class SpecialistDoctor extends Doctor {

    private String specialistArea;

    /**
     * POLYMORPHISM: Override applies premium fee logic for specialized care.
     *
     * @return Fee with 50% premium
     */
    @Override
    public double calculateConsultationFee() {
        // POLYMORPHISM: Implementing specialized logic distinct from GeneralDoctor
        return this.getConsultationFee() * 1.5;
    }
}
