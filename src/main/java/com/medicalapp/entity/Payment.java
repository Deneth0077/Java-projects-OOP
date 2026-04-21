package com.medicalapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * Payment.java
 *
 * Abstract base entity representing a Payment transaction.
 * Defines the core behavior for different payment methods.
 *
 * OOP Concepts Applied:
 * - INHERITANCE: extends BaseEntity.
 * - POLYMORPHISM: Abstract processPayment() method defines shared behavior with distinct logic.
 * - ENCAPSULATION: Private amount and timestamp fields.
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
@Table(name = "payments")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_method")
@EqualsAndHashCode(callSuper = true)
public abstract class Payment extends BaseEntity {

    private Double amount;
    private LocalDateTime paidAt;

    /**
     * POLYMORPHISM: Method to be overridden by Online/Cash payment subtypes.
     *
     * @return Execution result summary
     */
    public abstract String processPayment();
}
