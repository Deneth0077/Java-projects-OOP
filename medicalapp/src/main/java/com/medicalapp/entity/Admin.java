package com.medicalapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Admin.java
 *
 * Entity representing an Administrator with system credentials.
 * Critical for security as it handles sensitive login data.
 *
 * OOP Concepts Applied:
 * - INHERITANCE: extends BaseEntity.
 * - ENCAPSULATION: Passwords are kept private and never exposed directly in responses.
 *
 * Component: Component 04 — Admin Management
 * Layer: Entity
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
@Entity
@Table(name = "admins")
@EqualsAndHashCode(callSuper = true)
public class Admin extends BaseEntity {

    @NotBlank(message = "Username is required")
    @Column(unique = true)
    private String username;

    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @Enumerated(EnumType.STRING)
    private AdminRole role;

    public enum AdminRole {
        SUPER_ADMIN, ADMIN
    }
}
