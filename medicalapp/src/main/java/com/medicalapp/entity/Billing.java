package com.medicalapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * Billing.java
 *
 * Entity representing a Bill generated for an individual Appointment.
 *
 * OOP Concepts Applied:
 * - INHERITANCE: extends BaseEntity.
 * - ENCAPSULATION: Maintains the financial status and links to polymorphic payment results.
 *
 * Component: Component 05 — Billing & Payment Management
 * Layer: Entity
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
@Entity
@Table(name = "billings")
@EqualsAndHashCode(callSuper = true)
public class Billing extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @NotNull(message = "Billing amount is required")
    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(unique = true)
    private String invoiceNumber;

    @Column(columnDefinition = "TEXT")
    private String paymentResult; // Outcome from polymorphic processPayment()

    private LocalDateTime paidAt;

    public enum PaymentType {
        ONLINE, CASH
    }

    public enum PaymentStatus {
        PENDING, PAID, FAILED, REFUNDED
    }
}
