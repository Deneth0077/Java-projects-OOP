package com.medicalapp.service;

import com.medicalapp.dto.AdminRequestDTO;
import com.medicalapp.dto.AdminResponseDTO;
import java.util.List;

/**
 * IAdminService.java
 *
 * Abstraction for Administrator operations.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Separates identity management from business controllers.
 *
 * Component: Component 04 — Admin Management
 * Layer: Service Interface
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
public interface IAdminService {

    AdminResponseDTO createAdmin(AdminRequestDTO dto);

    List<AdminResponseDTO> getAllAdmins();

    AdminResponseDTO getAdminById(Long id);

    AdminResponseDTO updateAdmin(Long id, AdminRequestDTO dto);

    void deleteAdmin(Long id);

    void changePassword(Long id, String oldPassword, String newPassword);
}
