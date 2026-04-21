package com.medicalapp.service;

import com.medicalapp.dto.MedicalRecordDTO;
import com.medicalapp.entity.*;
import com.medicalapp.exception.ResourceNotFoundException;
import com.medicalapp.exception.UnauthorizedException;
import com.medicalapp.repository.DoctorRepository;
import com.medicalapp.repository.MedicalRecordRepository;
import com.medicalapp.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * MedicalRecordService.java
 *
 * Implementation of Medical Record Management and polymorphic summary logic.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Implements IMedicalRecordService.
 * - POLYMORPHISM: Dynamic summary retrieval from Prescription/Lab records.
 *
 * Component: Component 06 — Medical Record Management
 * Layer: Service Implementation
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Service
@RequiredArgsConstructor
@Transactional
public class MedicalRecordService implements IMedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public MedicalRecordDTO addRecord(MedicalRecordDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        MedicalRecord record;
        if (dto.getRecordType() == MedicalRecord.RecordType.PRESCRIPTION) {
            record = new PrescriptionRecord();
            ((PrescriptionRecord) record).setMedicineName(dto.getMedicineName());
            ((PrescriptionRecord) record).setDosage(dto.getDosage());
            ((PrescriptionRecord) record).setDuration(dto.getDuration());
        } else {
            record = new LabRecord();
            ((LabRecord) record).setTestName(dto.getTestName());
            ((LabRecord) record).setTestResult(dto.getTestResult());
            ((LabRecord) record).setReferenceRange(dto.getReferenceRange());
        }

        record.setPatient(patient);
        record.setDoctor(doctor);
        record.setRecordDate(dto.getRecordDate());
        record.setDiagnosis(dto.getDiagnosis());
        record.setRecordType(dto.getRecordType());

        return mapEntityToDto(medicalRecordRepository.save(record));
    }

    @Override
    public List<MedicalRecordDTO> getAllRecords() {
        return medicalRecordRepository.findAll().stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalRecordDTO getRecordById(Long id) {
        MedicalRecord record = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
        return mapEntityToDto(record);
    }

    @Override
    public List<MedicalRecordDTO> getRecordsByPatient(Long patientId) {
        return medicalRecordRepository.findByPatient_Id(patientId).stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicalRecordDTO> getRecordsByDoctor(Long doctorId) {
        return medicalRecordRepository.findByDoctor_Id(doctorId).stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public String getRecordSummary(Long id) {
        MedicalRecord record = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
        
        // POLYMORPHISM: Shared method call on record object resolves to specific implementation
        return record.getRecordSummary();
    }

    @Override
    public MedicalRecordDTO updateRecord(Long id, MedicalRecordDTO dto, Long doctorId) {
        MedicalRecord existing = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
        
        // ENCAPSULATION: Security rule verification
        if (!existing.getDoctor().getId().equals(doctorId)) {
            throw new UnauthorizedException("Only the assigned doctor can modify this record");
        }

        existing.setDiagnosis(dto.getDiagnosis());
        existing.setRecordDate(dto.getRecordDate());
        
        if (existing instanceof PrescriptionRecord) {
            ((PrescriptionRecord) existing).setMedicineName(dto.getMedicineName());
            ((PrescriptionRecord) existing).setDosage(dto.getDosage());
            ((PrescriptionRecord) existing).setDuration(dto.getDuration());
        } else if (existing instanceof LabRecord) {
            ((LabRecord) existing).setTestName(dto.getTestName());
            ((LabRecord) existing).setTestResult(dto.getTestResult());
            ((LabRecord) existing).setReferenceRange(dto.getReferenceRange());
        }

        return mapEntityToDto(medicalRecordRepository.save(existing));
    }

    @Override
    public void deleteRecord(Long id) {
        if (!medicalRecordRepository.existsById(id)) {
            throw new ResourceNotFoundException("Record not found");
        }
        medicalRecordRepository.deleteById(id);
    }

    private MedicalRecordDTO mapEntityToDto(MedicalRecord r) {
        MedicalRecordDTO dto = new MedicalRecordDTO();
        dto.setId(r.getId());
        dto.setPatientId(r.getPatient().getId());
        dto.setDoctorId(r.getDoctor().getId());
        dto.setRecordDate(r.getRecordDate());
        dto.setDiagnosis(r.getDiagnosis());
        dto.setRecordType(r.getRecordType());
        
        if (r instanceof PrescriptionRecord) {
            dto.setMedicineName(((PrescriptionRecord) r).getMedicineName());
            dto.setDosage(((PrescriptionRecord) r).getDosage());
            dto.setDuration(((PrescriptionRecord) r).getDuration());
        } else if (r instanceof LabRecord) {
            dto.setTestName(((LabRecord) r).getTestName());
            dto.setTestResult(((LabRecord) r).getTestResult());
            dto.setReferenceRange(((LabRecord) r).getReferenceRange());
        }
        
        // POLYMORPHISM: Including summary in DTO response
        dto.setSummary(r.getRecordSummary());
        return dto;
    }
}
