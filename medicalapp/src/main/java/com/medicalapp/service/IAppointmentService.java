package com.medicalapp.service;

import com.medicalapp.dto.AppointmentDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * IAppointmentService.java
 *
 * Abstraction for Appointment Management logic.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Standardizes scheduling operations for the UI.
 *
 * Component: Component 03 — Appointment Management
 * Layer: Service Interface
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
public interface IAppointmentService {

    AppointmentDTO bookAppointment(AppointmentDTO dto);

    List<AppointmentDTO> getAllAppointments();

    AppointmentDTO getAppointmentById(Long id);

    List<AppointmentDTO> getAppointmentsByPatient(Long patientId);

    List<AppointmentDTO> getAppointmentsByDoctor(Long doctorId);

    AppointmentDTO rescheduleAppointment(Long id, LocalDate newDate, LocalTime newTime);

    AppointmentDTO cancelAppointment(Long id);

    AppointmentDTO completeAppointment(Long id);

    void deleteAppointment(Long id);
}
