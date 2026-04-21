package com.medicalapp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * OnlinePayment.java
 *
 * Implementation of Payment for digital transactions via gateways.
 *
 * OOP Concepts Applied:
 * - INHERITANCE: extends Payment.
 * - POLYMORPHISM: Overrides processPayment() to handle virtual gateway logic.
 *
 * Component: Component 05 — Billing & Payment Management
 * Layer: Entity (Subclass)
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
@Entity
@DiscriminatorValue("ONLINE")
@EqualsAndHashCode(callSuper = true)
public class OnlinePayment extends Payment {

    private String transactionId;
    private String paymentGateway;

    /**
     * POLYMORPHISM: Logic for online transactions including gateway tracking.
     *
     * @return Detailed outcome of digital payment
     */
    @Override
    public String processPayment() {
        this.setTransactionId(UUID.randomUUID().toString()); // Mocking gateway response
        this.setPaidAt(LocalDateTime.now());
        // POLYMORPHISM: Context-aware return string
        return String.format("Online payment of LKR %.2f processed via %s. Transaction ID: %s",
                getAmount(), paymentGateway, transactionId);
    }
}
