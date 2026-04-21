package com.medicalapp.repository;

import com.medicalapp.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * PatientRepository.java
 *
 * Abstraction for database operations on Patients.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Extends JpaRepository to hide complexity of SQL queries.
 *
 * Component: Component 01 — Patient Management
 * Layer: Repository
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * ABSTRACTION: Query method automatically implemented by Spring Data.
     */
    Optional<Patient> findByNic(String nic);

    Optional<Patient> findByPhone(String phone);

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByPatientType(Patient.PatientType type);
}
