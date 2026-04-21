package com.medicalapp.repository;

import com.medicalapp.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * AdminRepository.java
 *
 * Data Access Layer for administrative system users.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Abstracted SQL operations.
 *
 * Component: Component 04 — Admin Management
 * Layer: Repository
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUsername(String username);

    Optional<Admin> findByEmail(String email);

    List<Admin> findByRole(Admin.AdminRole role);
}
