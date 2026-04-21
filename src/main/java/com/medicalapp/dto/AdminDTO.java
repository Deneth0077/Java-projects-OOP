package com.medicalapp.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * AdminDTO.java
 *
 * Data Transfer Object for Admin entity.
 * Critical for security: Does NOT include the password field (Encapsulation).
 *
 * @author Antigravity
 * @version 1.0
 */
@Data
public class AdminDTO {
    private Long id;
    private String username;
    private String email;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
