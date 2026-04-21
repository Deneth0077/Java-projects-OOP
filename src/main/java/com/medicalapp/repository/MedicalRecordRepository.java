package com.medicalapp.repository;

import com.medicalapp.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * MedicalRecordRepository.java
 *
 * Data Access Layer for clinical patient history.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Using interfaces for persistence.
 *
 * Component: Component 06 — Medical Record Management
 * Layer: Repository
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    List<MedicalRecord> findByPatient_Id(Long patientId);

    List<MedicalRecord> findByDoctor_Id(Long doctorId);

    List<MedicalRecord> findByRecordType(MedicalRecord.RecordType type);

    List<MedicalRecord> findByRecordDateBetween(LocalDate from, LocalDate to);
}
