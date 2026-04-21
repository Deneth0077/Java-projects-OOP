package com.medicalapp.service;

import com.medicalapp.dto.MedicalRecordDTO;
import java.util.List;

/**
 * IMedicalRecordService.java
 *
 * Abstraction for Clinical data and polymorphic Record summaries.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Centralized entry for diverse record types (Prescription/Lab).
 *
 * Component: Component 06 — Medical Record Management
 * Layer: Service Interface
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
public interface IMedicalRecordService {

    MedicalRecordDTO addRecord(MedicalRecordDTO dto);

    List<MedicalRecordDTO> getAllRecords();

    MedicalRecordDTO getRecordById(Long id);

    List<MedicalRecordDTO> getRecordsByPatient(Long patientId);

    List<MedicalRecordDTO> getRecordsByDoctor(Long doctorId);

    /**
     * ABSTRACTION: Polymorphic summary retrieval method.
     */
    String getRecordSummary(Long id);

    MedicalRecordDTO updateRecord(Long id, MedicalRecordDTO dto, Long doctorId);

    void deleteRecord(Long id);
}
