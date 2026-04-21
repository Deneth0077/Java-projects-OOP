package com.medicalapp.service;

import com.medicalapp.dto.PatientDTO;
import java.util.List;

/**
 * IPatientService.java
 *
 * Abstraction for Patient Management logic.
 * Defines the contract for patient-related operations.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Interface defines the 'what', hiding the 'how' from controllers.
 *
 * Component: Component 01 — Patient Management
 * Layer: Service Interface
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
public interface IPatientService {

    /**
     * ABSTRACTION: Contract method for registration.
     */
    PatientDTO registerPatient(PatientDTO dto);

    List<PatientDTO> getAllPatients();

    PatientDTO getPatientById(Long id);

    PatientDTO getPatientByNic(String nic);

    PatientDTO getPatientByPhone(String phone);

    PatientDTO updatePatient(Long id, PatientDTO dto);

    void deletePatient(Long id);
}
