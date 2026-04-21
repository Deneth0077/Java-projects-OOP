package com.medicalapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

/**
 * MedicalRecord.java
 *
 * Abstract base entity for patient clinical records.
 *
 * OOP Concepts Applied:
 * - INHERITANCE: extends BaseEntity.
 * - POLYMORPHISM: Abstract method getRecordSummary() to be specified by Clinical/Lab types.
 * - ENCAPSULATION: Private patient/doctor linkages.
 *
 * Component: Component 06 — Medical Record Management
 * Layer: Entity
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
@Entity
@Table(name = "medical_records")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "record_sub_type")
@EqualsAndHashCode(callSuper = true)
public abstract class MedicalRecord extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @NotNull(message = "Record date is required")
    private LocalDate recordDate;

    private String diagnosis;

    @Enumerated(EnumType.STRING)
    private RecordType recordType;

    public enum RecordType {
        PRESCRIPTION, LAB_RESULT
    }

    /**
     * POLYMORPHISM: Method to be overridden to provide contextual clinical data.
     *
     * @return Summary string of the medical findings
     */
    public abstract String getRecordSummary();
}
