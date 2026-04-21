package com.medicalapp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * GeneralDoctor.java
 *
 * Concrete implementation for General Practitioners.
 *
 * OOP Concepts Applied:
 * - INHERITANCE: Extends Doctor.
 * - POLYMORPHISM: Overrides calculateConsultationFee() to return the base fee without premium.
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
@DiscriminatorValue("GENERAL")
@EqualsAndHashCode(callSuper = true)
public class GeneralDoctor extends Doctor {

    /**
     * POLYMORPHISM: Override returns base consultation fee.
     *
     * @return Base fee amount
     */
    @Override
    public double calculateConsultationFee() {
        // ENCAPSULATION: Accessing private field via getter
        return this.getConsultationFee();
    }
}
