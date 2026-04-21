package com.medicalapp.service;

import com.medicalapp.dto.BillingDTO;
import com.medicalapp.entity.Billing.PaymentType;
import java.util.List;
import java.util.Map;

/**
 * IBillingService.java
 *
 * Abstraction for Billing and polymorphic Payment processing.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Hides complex payment gateway and cash logic transitions.
 *
 * Component: Component 05 — Billing & Payment Management
 * Layer: Service Interface
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
public interface IBillingService {

    BillingDTO createBill(BillingDTO dto);

    List<BillingDTO> getAllBills();

    BillingDTO getBillById(Long id);

    List<BillingDTO> getBillsByPatient(Long patientId);

    BillingDTO getBillByInvoiceNumber(String invoiceNumber);

    /**
     * ABSTRACTION: Single pay method that triggers polymorphic execution.
     */
    String processPayment(Long id, PaymentType type, Map<String, String> paymentDetails);

    void deleteBill(Long id);
}
