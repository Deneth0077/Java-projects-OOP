package com.medicalapp.dto;

import com.medicalapp.entity.Appointment;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * AppointmentDTO.java
 *
 * Data Transfer Object for appointment scheduling.
 *
 * OOP Concepts Applied:
 * - ENCAPSULATION: Hides complex entity relationships by using ID references.
 *
 * Component: Component 03 — Appointment Management
 * Layer: DTO
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
public class AppointmentDTO {

    private Long id;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    @NotNull(message = "Appointment date is required")
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment time is required")
    private LocalTime appointmentTime;

    private Appointment.AppointmentStatus status;

    private Appointment.AppointmentType type;

    private String notes;
}
