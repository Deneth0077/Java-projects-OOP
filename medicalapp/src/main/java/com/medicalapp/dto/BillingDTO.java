package com.medicalapp.dto;

import com.medicalapp.entity.Billing;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * BillingDTO.java
 *
 * DTO for financial billing transactions.
 *
 * OOP Concepts Applied:
 * - ENCAPSULATION: Groups complex invoice data into a simple structure.
 *
 * Component: Component 05 — Billing & Payment Management
 * Layer: DTO
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Data
public class BillingDTO {

    private Long id;

    @NotNull(message = "Appointment ID is required")
    private Long appointmentId;

    @NotNull(message = "Amount is required")
    private Double amount;

    private Billing.PaymentType paymentType;

    private Billing.PaymentStatus status;

    private String invoiceNumber;

    private String paymentResult;

    private LocalDateTime paidAt;
}
