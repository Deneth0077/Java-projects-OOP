package com.medicalapp.controller;

import com.medicalapp.dto.AdminRequestDTO;
import com.medicalapp.dto.AdminResponseDTO;
import com.medicalapp.service.IAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * AdminController.java
 *
 * REST API Endpoints for System Administrators.
 *
 * OOP Concepts Applied:
 * - ENCAPSULATION: Uses ResponseDTO to avoid leakage of internal security hashes (passwords).
 *
 * Component: Component 04 — Admin Management
 * Layer: Controller
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final IAdminService adminService;

    @PostMapping
    public ResponseEntity<AdminResponseDTO> createAdmin(@Valid @RequestBody AdminRequestDTO dto) {
        return new ResponseEntity<>(adminService.createAdmin(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<AdminResponseDTO> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public AdminResponseDTO getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @PutMapping("/{id}")
    public AdminResponseDTO updateAdmin(@PathVariable Long id, @Valid @RequestBody AdminRequestDTO dto) {
        return adminService.updateAdmin(id, dto);
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody Map<String, String> body) {
        adminService.changePassword(id, body.get("oldPassword"), body.get("newPassword"));
        return ResponseEntity.ok("Password updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
