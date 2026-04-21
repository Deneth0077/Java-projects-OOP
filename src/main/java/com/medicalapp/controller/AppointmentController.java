package com.medicalapp.controller;

import com.medicalapp.dto.AppointmentDTO;
import com.medicalapp.service.IAppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * AppointmentController.java
 *
 * REST API Endpoints for scheduling and status management.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Interaction via IAppointmentService interface.
 *
 * Component: Component 03 — Appointment Management
 * Layer: Controller
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final IAppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentDTO> bookAppointment(@Valid @RequestBody AppointmentDTO dto) {
        return new ResponseEntity<>(appointmentService.bookAppointment(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public AppointmentDTO getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<AppointmentDTO> getAppointmentsByPatient(@PathVariable Long patientId) {
        return appointmentService.getAppointmentsByPatient(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<AppointmentDTO> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        return appointmentService.getAppointmentsByDoctor(doctorId);
    }

    @PutMapping("/{id}/reschedule")
    public AppointmentDTO rescheduleAppointment(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return appointmentService.rescheduleAppointment(id, 
                java.time.LocalDate.parse(body.get("date")), 
                java.time.LocalTime.parse(body.get("time")));
    }

    @PutMapping("/{id}/cancel")
    public AppointmentDTO cancelAppointment(@PathVariable Long id) {
        return appointmentService.cancelAppointment(id);
    }

    @PutMapping("/{id}/complete")
    public AppointmentDTO completeAppointment(@PathVariable Long id) {
        return appointmentService.completeAppointment(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
