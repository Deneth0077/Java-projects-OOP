package com.medicalapp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * PrescriptionRecord.java
 *
 * Concrete record for Medication and Dosage details.
 *
 * OOP Concepts Applied:
 * - INHERITANCE: extends MedicalRecord.
 * - POLYMORPHISM: Overrides getRecordSummary() to detail medication instructions.
 *
 * Component: Component 06 — Medical Record Management
 * Layer: Entity (Subclass)
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
@Entity
@DiscriminatorValue("PRESCRIPTION")
@EqualsAndHashCode(callSuper = true)
public class PrescriptionRecord extends MedicalRecord {

    private String medicineName;
    private String dosage;
    private String duration;

    /**
     * POLYMORPHISM: Detailed prescription overview implementation.
     *
     * @return Formatted prescription string
     */
    @Override
    public String getRecordSummary() {
        // POLYMORPHISM: Providing medication-specific record logic
        return String.format("Prescription | Diagnosis: %s | Medicine: %s %s for %s",
                getDiagnosis(), medicineName, dosage, duration);
    }
}
