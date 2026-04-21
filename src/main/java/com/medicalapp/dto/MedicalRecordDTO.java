package com.medicalapp.dto;

import com.medicalapp.entity.MedicalRecord;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

/**
 * MedicalRecordDTO.java
 *
 * DTO for exchanging clinical history and lab results.
 * Includes all possible fields for both specialized record types.
 *
 * OOP Concepts Applied:
 * - ENCAPSULATION: Groups diverse clinical attributes for easy transmission.
 *
 * Component: Component 06 — Medical Record Management
 * Layer: DTO
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
public class MedicalRecordDTO {

    private Long id;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    @NotNull(message = "Record date is required")
    private LocalDate recordDate;

    private String diagnosis;

    private MedicalRecord.RecordType recordType;

    // Prescription specific
    private String medicineName;
    private String dosage;
    private String duration;

    // Lab Result specific
    private String testName;
    private String testResult;
    private String referenceRange;

    // From polymorphic getRecordSummary()
    private String summary;
}
