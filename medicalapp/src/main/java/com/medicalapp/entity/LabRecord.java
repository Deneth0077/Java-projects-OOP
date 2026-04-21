package com.medicalapp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * LabRecord.java
 *
 * Concrete record for Laboratory test outcomes.
 *
 * OOP Concepts Applied:
 * - INHERITANCE: extends MedicalRecord.
 * - POLYMORPHISM: Overrides getRecordSummary() to detail diagnostic findings.
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
@DiscriminatorValue("LAB_RESULT")
@EqualsAndHashCode(callSuper = true)
public class LabRecord extends MedicalRecord {

    private String testName;
    private String testResult;
    private String referenceRange;

    /**
     * POLYMORPHISM: Detailed lab result overview implementation.
     *
     * @return Formatted lab finding string
     */
    @Override
    public String getRecordSummary() {
        // POLYMORPHISM: Providing test-specific record logic distinct from prescriptions
        return String.format("Lab Result | Test: %s | Result: %s (Normal range: %s)",
                testName, testResult, referenceRange);
    }
}
