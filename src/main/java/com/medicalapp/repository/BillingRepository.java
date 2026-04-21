package com.medicalapp.repository;

import com.medicalapp.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * BillingRepository.java
 *
 * Data Access Layer for financial billing records.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Repositories abstract the storage mechanism.
 *
 * Component: Component 05 — Billing & Payment Management
 * Layer: Repository
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {

    Optional<Billing> findByAppointment_Id(Long appointmentId);

    Optional<Billing> findByInvoiceNumber(String invoiceNumber);

    List<Billing> findByStatus(Billing.PaymentStatus status);

    List<Billing> findByAppointment_Patient_Id(Long patientId);
}
