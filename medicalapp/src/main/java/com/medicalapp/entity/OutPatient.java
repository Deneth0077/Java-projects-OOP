package com.medicalapp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OutPatient.java
 *
 * Specialized subtype for Out-Patients who do not require overnight stay.
 *
 * OOP Concepts Applied:
 * - INHERITANCE: Extends Patient to inherit personal and contact details.
 *
 * Component: Component 01 — Patient Management
 * Layer: Entity (Subclass)
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
@Entity
@DiscriminatorValue("OUTPATIENT")
@EqualsAndHashCode(callSuper = true)
public class OutPatient extends Patient {
    // No extra fields currently, but inherits all from Patient
}
