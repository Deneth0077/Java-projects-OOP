package com.medicalapp.repository;

import com.medicalapp.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * DoctorRepository.java
 *
 * Data Access Layer for Doctor entities.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Interface based data access.
 *
 * Component: Component 02 — Doctor Management
 * Layer: Repository
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findBySpecialization(String specialization);

    List<Doctor> findByDoctorType(Doctor.DoctorType type);

    Optional<Doctor> findByEmail(String email);

    List<Doctor> findByAvailableDaysContaining(String day);
}
