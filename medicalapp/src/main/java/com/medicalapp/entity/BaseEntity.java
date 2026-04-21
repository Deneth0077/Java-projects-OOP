package com.medicalapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * BaseEntity.java
 *
 * Abstract base class for all entity models to minimize code duplication.
 * Provides mandatory auditing fields for every table.
 *
 * OOP Concepts Applied:
 * - INHERITANCE: Used as a @MappedSuperclass to provide common fields to child entities.
 * - ENCAPSULATION: Private fields accessed only through Lombok-generated getters/setters.
 *
 * Component: Common
 * Layer: Entity (MappedSuperclass)
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    // ENCAPSULATION: private field — accessed only via getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ENCAPSULATION: private field
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    // ENCAPSULATION: private field
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
