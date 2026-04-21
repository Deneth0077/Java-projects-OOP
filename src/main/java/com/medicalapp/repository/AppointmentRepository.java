package com.medicalapp.repository;

import com.medicalapp.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * AppointmentRepository.java
 *
 * Data Access Layer for medical appointments.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Hides low-level persistence logic.
 *
 * Component: Component 03 — Appointment Management
 * Layer: Repository
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatient_Id(Long patientId);

    List<Appointment> findByDoctor_Id(Long doctorId);

    List<Appointment> findByStatus(Appointment.AppointmentStatus status);

    List<Appointment> findByAppointmentDate(LocalDate date);

    /**
     * ABSTRACTION: Conflict check query.
     */
    Optional<Appointment> findByDoctor_IdAndAppointmentDateAndAppointmentTime(Long doctorId, LocalDate date, LocalTime time);
}
