package com.medicalapp.dto;

import com.medicalapp.entity.Admin;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * AdminResponseDTO.java
 *
 * DTO for returning Admin details to the UI.
 * Excludes the password for security reasons.
 *
 * OOP Concepts Applied:
 * - ENCAPSULATION: Hiding sensitive data (private fields) effectively from unauthorized eyes.
 *
 * Component: Component 04 — Admin Management
 * Layer: DTO
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
public class AdminResponseDTO {

    private Long id;
    private String username;
    private String email;
    private Admin.AdminRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
