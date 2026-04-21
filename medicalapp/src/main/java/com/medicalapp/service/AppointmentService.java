package com.medicalapp.service;

import com.medicalapp.dto.AppointmentDTO;
import com.medicalapp.entity.Appointment;
import com.medicalapp.entity.Doctor;
import com.medicalapp.entity.Patient;
import com.medicalapp.exception.AppointmentConflictException;
import com.medicalapp.exception.ResourceNotFoundException;
import com.medicalapp.repository.AppointmentRepository;
import com.medicalapp.repository.DoctorRepository;
import com.medicalapp.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AppointmentService.java
 *
 * Implementation of Appointment Management logic with strict scheduling rules.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Implements IAppointmentService interface.
 * - ENCAPSULATION: Hides complex logic for availability and conflict checks.
 *
 * Component: Component 03 — Appointment Management
 * Layer: Service Implementation
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentService implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public AppointmentDTO bookAppointment(AppointmentDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        // ENCAPSULATION: Validating doctor availability on the requested day
        String dayShort = dto.getAppointmentDate().getDayOfWeek().name().substring(0, 3);
        if (!doctor.getAvailableDays().contains(dayShort)) {
            throw new AppointmentConflictException("Doctor is not available on " + dto.getAppointmentDate().getDayOfWeek());
        }

        // ENCAPSULATION: Conflict check for existing appointments
        if (appointmentRepository.findByDoctor_IdAndAppointmentDateAndAppointmentTime(
                dto.getDoctorId(), dto.getAppointmentDate(), dto.getAppointmentTime()).isPresent()) {
            throw new AppointmentConflictException("Doctor already has an appointment at this time");
        }

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setStatus(Appointment.AppointmentStatus.PENDING);
        appointment.setType(dto.getType());
        appointment.setNotes(dto.getNotes());

        return mapEntityToDto(appointmentRepository.save(appointment));
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        Appointment app = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        return mapEntityToDto(app);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatient_Id(patientId).stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctor_Id(doctorId).stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO rescheduleAppointment(Long id, LocalDate newDate, LocalTime newTime) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        // Use same logic as book to check for conflicts if time changed
        if (appointmentRepository.findByDoctor_IdAndAppointmentDateAndAppointmentTime(
                existing.getDoctor().getId(), newDate, newTime).isPresent()) {
            throw new AppointmentConflictException("Conflict detected for new requested time");
        }

        existing.setAppointmentDate(newDate);
        existing.setAppointmentTime(newTime);
        existing.setStatus(Appointment.AppointmentStatus.PENDING);
        
        return mapEntityToDto(appointmentRepository.save(existing));
    }

    @Override
    public AppointmentDTO cancelAppointment(Long id) {
        Appointment app = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        app.setStatus(Appointment.AppointmentStatus.CANCELLED);
        return mapEntityToDto(appointmentRepository.save(app));
    }

    @Override
    public AppointmentDTO completeAppointment(Long id) {
        Appointment app = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        app.setStatus(Appointment.AppointmentStatus.COMPLETED);
        return mapEntityToDto(appointmentRepository.save(app));
    }

    @Override
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Appointment not found");
        }
        appointmentRepository.deleteById(id);
    }

    private AppointmentDTO mapEntityToDto(Appointment app) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(app.getId());
        dto.setPatientId(app.getPatient().getId());
        dto.setDoctorId(app.getDoctor().getId());
        dto.setAppointmentDate(app.getAppointmentDate());
        dto.setAppointmentTime(app.getAppointmentTime());
        dto.setStatus(app.getStatus());
        dto.setType(app.getType());
        dto.setNotes(app.getNotes());
        return dto;
    }
}
