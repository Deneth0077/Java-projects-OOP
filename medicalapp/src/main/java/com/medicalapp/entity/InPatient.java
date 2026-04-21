package com.medicalapp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * InPatient.java
 *
 * Specialized subtype for In-Patients who are admitted to a ward.
 *
 * OOP Concepts Applied:
 * - INHERITANCE: Extends Patient while adding admission-specific data.
 * - ENCAPSULATION: Private wardNumber field.
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
@DiscriminatorValue("INPATIENT")
@EqualsAndHashCode(callSuper = true)
public class InPatient extends Patient {

    // ENCAPSULATION: private field admission-specific
    private String wardNumber;
}
