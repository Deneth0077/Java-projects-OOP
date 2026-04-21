package com.medicalapp.service;

import com.medicalapp.dto.AdminRequestDTO;
import com.medicalapp.dto.AdminResponseDTO;
import com.medicalapp.entity.Admin;
import com.medicalapp.exception.DuplicateEntryException;
import com.medicalapp.exception.ResourceNotFoundException;
import com.medicalapp.exception.UnauthorizedException;
import com.medicalapp.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * AdminService.java
 *
 * Implementation of Admin Management and security logic.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Implements IAdminService interface.
 * - ENCAPSULATION: Hides password hashing and security verification.
 *
 * Component: Component 04 — Admin Management
 * Layer: Service Implementation
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AdminService implements IAdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public AdminResponseDTO createAdmin(AdminRequestDTO dto) {
        if (adminRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new DuplicateEntryException("Username already taken: " + dto.getUsername());
        }
        if (adminRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateEntryException("Email already taken: " + dto.getEmail());
        }

        Admin admin = new Admin();
        admin.setUsername(dto.getUsername());
        admin.setEmail(dto.getEmail());
        admin.setRole(dto.getRole());
        
        // ENCAPSULATION: Hashing password before persistence for safety
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));

        return mapEntityToResponseDto(adminRepository.save(admin));
    }

    @Override
    public List<AdminResponseDTO> getAllAdmins() {
        return adminRepository.findAll().stream()
                .map(this::mapEntityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdminResponseDTO getAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
        return mapEntityToResponseDto(admin);
    }

    @Override
    public AdminResponseDTO updateAdmin(Long id, AdminRequestDTO dto) {
        Admin existing = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
        
        existing.setUsername(dto.getUsername());
        existing.setEmail(dto.getEmail());
        existing.setRole(dto.getRole());

        return mapEntityToResponseDto(adminRepository.save(existing));
    }

    @Override
    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new ResourceNotFoundException("Admin not found");
        }
        adminRepository.deleteById(id);
    }

    @Override
    public void changePassword(Long id, String oldPassword, String newPassword) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        // ENCAPSULATION: Verify old password hash matches
        if (!passwordEncoder.matches(oldPassword, admin.getPassword())) {
            throw new UnauthorizedException("Invalid old password");
        }

        admin.setPassword(passwordEncoder.encode(newPassword));
        adminRepository.save(admin);
    }

    private AdminResponseDTO mapEntityToResponseDto(Admin admin) {
        AdminResponseDTO dto = new AdminResponseDTO();
        dto.setId(admin.getId());
        dto.setUsername(admin.getUsername());
        dto.setEmail(admin.getEmail());
        dto.setRole(admin.getRole());
        dto.setCreatedAt(admin.getCreatedAt());
        dto.setUpdatedAt(admin.getUpdatedAt());
        return dto;
    }
}
