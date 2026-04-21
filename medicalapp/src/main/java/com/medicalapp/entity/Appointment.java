package com.medicalapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Appointment.java
 *
 * Entity representing a scheduled medical consultation between a Patient and Doctor.
 *
 * OOP Concepts Applied:
 * - INHERITANCE: extends BaseEntity.
 * - ENCAPSULATION: Relationships and statuses are encapsulated in private fields.
 * - ABSTRACTION: Relationships to Patient and Doctor are managed via JPA abstractions.
 *
 * Component: Component 03 — Appointment Management
 * Layer: Entity
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
@Entity
@Table(name = "appointments")
@EqualsAndHashCode(callSuper = true)
public class Appointment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @NotNull(message = "Appointment date is required")
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment time is required")
    private LocalTime appointmentTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Enumerated(EnumType.STRING)
    private AppointmentType type;

    private String notes;

    public enum AppointmentStatus {
        PENDING, CONFIRMED, CANCELLED, COMPLETED
    }

    public enum AppointmentType {
        ONLINE, IN_PERSON
    }
}
