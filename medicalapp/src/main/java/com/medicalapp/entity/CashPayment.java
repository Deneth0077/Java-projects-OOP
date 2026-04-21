package com.medicalapp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * CashPayment.java
 *
 * Implementation of Payment for physical transactions at the counter.
 *
 * OOP Concepts Applied:
 * - INHERITANCE: extends Payment.
 * - POLYMORPHISM: Overrides processPayment() to calculate change and record handling.
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
@DiscriminatorValue("CASH")
@EqualsAndHashCode(callSuper = true)
public class CashPayment extends Payment {

    private String receivedBy;
    private Double cashReceived;
    private Double changeGiven;

    /**
     * POLYMORPHISM: Logic for physical transactions including change calculation.
     *
     * @return Summary of cash handling
     */
    @Override
    public String processPayment() {
        this.setChangeGiven(cashReceived - getAmount());
        this.setPaidAt(LocalDateTime.now());
        // POLYMORPHISM: Context-aware return string for over-the-counter payments
        return String.format("Cash payment of LKR %.2f received by %s. Cash received: %.2f, Change: %.2f",
                getAmount(), receivedBy, cashReceived, changeGiven);
    }
}
