package com.medicalapp.dto;

import com.medicalapp.entity.Admin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * AdminRequestDTO.java
 *
 * DTO for creating and updating Administrator accounts.
 * Includes the password field for intake operations.
 *
 * OOP Concepts Applied:
 * - ENCAPSULATION: Segregating request and response models for security.
 *
 * Component: Component 04 — Admin Management
 * Layer: DTO
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
public class AdminRequestDTO {

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private Admin.AdminRole role;
}
