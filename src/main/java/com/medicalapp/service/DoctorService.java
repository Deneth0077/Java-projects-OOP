package com.medicalapp.service;

import com.medicalapp.dto.DoctorDTO;
import com.medicalapp.entity.Doctor;
import com.medicalapp.entity.GeneralDoctor;
import com.medicalapp.entity.SpecialistDoctor;
import com.medicalapp.exception.DuplicateEntryException;
import com.medicalapp.exception.ResourceNotFoundException;
import com.medicalapp.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DoctorService.java
 *
 * Implementation of Doctor Management logic.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Implements IDoctorService.
 * - POLYMORPHISM: Accesses the polymorphic calculateConsultationFee() method.
 *
 * Component: Component 02 — Doctor Management
 * Layer: Service Implementation
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Service
@RequiredArgsConstructor
@Transactional
public class DoctorService implements IDoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public DoctorDTO addDoctor(DoctorDTO dto) {
        if (doctorRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateEntryException("Doctor email already exists: " + dto.getEmail());
        }

        Doctor doctor;
        if (dto.getDoctorType() == Doctor.DoctorType.SPECIALIST) {
            doctor = new SpecialistDoctor();
            ((SpecialistDoctor) doctor).setSpecialistArea(dto.getSpecialistArea());
        } else {
            doctor = new GeneralDoctor();
        }

        mapDtoToEntity(dto, doctor);
        Doctor saved = doctorRepository.save(doctor);
        return mapEntityToDto(saved);
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
        return mapEntityToDto(doctor);
    }

    @Override
    public List<DoctorDTO> getDoctorsBySpecialization(String spec) {
        return doctorRepository.findBySpecialization(spec).stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorDTO> getDoctorsByAvailableDay(String day) {
        return doctorRepository.findByAvailableDaysContaining(day).stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorDTO updateDoctor(Long id, DoctorDTO dto) {
        Doctor existing = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));

        mapDtoToEntity(dto, existing);
        if (existing instanceof SpecialistDoctor && dto.getSpecialistArea() != null) {
            ((SpecialistDoctor) existing).setSpecialistArea(dto.getSpecialistArea());
        }

        return mapEntityToDto(doctorRepository.save(existing));
    }

    @Override
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Doctor not found with ID: " + id);
        }
        doctorRepository.deleteById(id);
    }

    @Override
    public double getCalculatedFee(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
        
        // POLYMORPHISM: Calling abstract method — actual execution depends on whether it's General or Specialist
        return doctor.calculateConsultationFee();
    }

    private void mapDtoToEntity(DoctorDTO dto, Doctor doctor) {
        doctor.setName(dto.getName());
        doctor.setEmail(dto.getEmail());
        doctor.setPhone(dto.getPhone());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setDoctorType(dto.getDoctorType());
        doctor.setConsultationFee(dto.getConsultationFee());
        doctor.setAvailableDays(dto.getAvailableDays());
        doctor.setAvailableFrom(dto.getAvailableFrom());
        doctor.setAvailableTo(dto.getAvailableTo());
    }

    private DoctorDTO mapEntityToDto(Doctor doctor) {
        DoctorDTO dto = new DoctorDTO();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setEmail(doctor.getEmail());
        dto.setPhone(doctor.getPhone());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setDoctorType(doctor.getDoctorType());
        dto.setConsultationFee(doctor.getConsultationFee());
        dto.setAvailableDays(doctor.getAvailableDays());
        dto.setAvailableFrom(doctor.getAvailableFrom());
        dto.setAvailableTo(doctor.getAvailableTo());
        if (doctor instanceof SpecialistDoctor) {
            dto.setSpecialistArea(((SpecialistDoctor) doctor).getSpecialistArea());
        }
        return dto;
    }
}
