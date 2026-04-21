package com.medicalapp.service;

import com.medicalapp.dto.PatientDTO;
import com.medicalapp.entity.InPatient;
import com.medicalapp.entity.OutPatient;
import com.medicalapp.entity.Patient;
import com.medicalapp.exception.DuplicateEntryException;
import com.medicalapp.exception.ResourceNotFoundException;
import com.medicalapp.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PatientService.java
 *
 * Implementation of Patient Management logic.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Implements IPatientService interface.
 * - ENCAPSULATION: Repository access and business logic are hidden from the controller.
 *
 * Component: Component 01 — Patient Management
 * Layer: Service Implementation
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PatientService implements IPatientService {

    private final PatientRepository patientRepository;

    @Override
    public PatientDTO registerPatient(PatientDTO dto) {
        // ENCAPSULATION: Business logic check before saving
        if (patientRepository.findByNic(dto.getNic()).isPresent()) {
            throw new DuplicateEntryException("NIC already exists: " + dto.getNic());
        }
        if (patientRepository.findByPhone(dto.getPhone()).isPresent()) {
            throw new DuplicateEntryException("Phone already exists: " + dto.getPhone());
        }

        Patient patient;
        if (dto.getPatientType() == Patient.PatientType.INPATIENT) {
            patient = new InPatient();
            ((InPatient) patient).setWardNumber(dto.getWardNumber());
        } else {
            patient = new OutPatient();
        }

        mapDtoToEntity(dto, patient);
        Patient saved = patientRepository.save(patient);
        return mapEntityToDto(saved);
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));
        return mapEntityToDto(patient);
    }

    @Override
    public PatientDTO getPatientByNic(String nic) {
        Patient patient = patientRepository.findByNic(nic)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with NIC: " + nic));
        return mapEntityToDto(patient);
    }

    @Override
    public PatientDTO getPatientByPhone(String phone) {
        Patient patient = patientRepository.findByPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with Phone: " + phone));
        return mapEntityToDto(patient);
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO dto) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));

        mapDtoToEntity(dto, existing);
        if (existing instanceof InPatient && dto.getWardNumber() != null) {
            ((InPatient) existing).setWardNumber(dto.getWardNumber());
        }

        return mapEntityToDto(patientRepository.save(existing));
    }

    @Override
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found with ID: " + id);
        }
        patientRepository.deleteById(id);
    }

    private void mapDtoToEntity(PatientDTO dto, Patient patient) {
        patient.setName(dto.getName());
        patient.setNic(dto.getNic());
        patient.setPhone(dto.getPhone());
        patient.setEmail(dto.getEmail());
        patient.setAddress(dto.getAddress());
        patient.setMedicalHistory(dto.getMedicalHistory());
        patient.setPatientType(dto.getPatientType());
    }

    private PatientDTO mapEntityToDto(Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setNic(patient.getNic());
        dto.setPhone(patient.getPhone());
        dto.setEmail(patient.getEmail());
        dto.setAddress(patient.getAddress());
        dto.setMedicalHistory(patient.getMedicalHistory());
        dto.setPatientType(patient.getPatientType());
        if (patient instanceof InPatient) {
            dto.setWardNumber(((InPatient) patient).getWardNumber());
        }
        return dto;
    }
}
