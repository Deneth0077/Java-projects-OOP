package com.medicalapp.service;

import com.medicalapp.dto.BillingDTO;
import com.medicalapp.entity.*;
import com.medicalapp.exception.ResourceNotFoundException;
import com.medicalapp.repository.AppointmentRepository;
import com.medicalapp.repository.BillingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * BillingService.java
 *
 * Implementation of Billing and polymorphic Payment processing logic.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Implements IBillingService.
 * - POLYMORPHISM: Executes processPayment() on Payment subtypes online/cash.
 *
 * Component: Component 05 — Billing & Payment Management
 * Layer: Service Implementation
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Service
@RequiredArgsConstructor
@Transactional
public class BillingService implements IBillingService {

    private final BillingRepository billingRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public BillingDTO createBill(BillingDTO dto) {
        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        Billing billing = new Billing();
        billing.setAppointment(appointment);
        billing.setAmount(dto.getAmount());
        billing.setStatus(Billing.PaymentStatus.PENDING);
        
        // ENCAPSULATION: Auto-generating unique invoice number
        billing.setInvoiceNumber("INV-" + System.currentTimeMillis() + "-" + appointment.getId());

        return mapEntityToDto(billingRepository.save(billing));
    }

    @Override
    public List<BillingDTO> getAllBills() {
        return billingRepository.findAll().stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BillingDTO getBillById(Long id) {
        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found"));
        return mapEntityToDto(billing);
    }

    @Override
    public List<BillingDTO> getBillsByPatient(Long patientId) {
        return billingRepository.findByAppointment_Patient_Id(patientId).stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BillingDTO getBillByInvoiceNumber(String invoiceNumber) {
        Billing billing = billingRepository.findByInvoiceNumber(invoiceNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with invoice: " + invoiceNumber));
        return mapEntityToDto(billing);
    }

    @Override
    public String processPayment(Long id, Billing.PaymentType type, Map<String, String> details) {
        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found"));

        Payment payment;
        if (type == Billing.PaymentType.ONLINE) {
            payment = new OnlinePayment();
            ((OnlinePayment) payment).setPaymentGateway(details.get("gateway"));
        } else {
            payment = new CashPayment();
            ((CashPayment) payment).setReceivedBy(details.get("receivedBy"));
            ((CashPayment) payment).setCashReceived(Double.valueOf(details.get("cashReceived")));
        }
        
        payment.setAmount(billing.getAmount());

        // POLYMORPHISM: Single method call produces different execution paths for Online/Cash
        String result = payment.processPayment();
        
        billing.setPaymentResult(result);
        billing.setPaymentType(type);
        billing.setStatus(Billing.PaymentStatus.PAID);
        billing.setPaidAt(payment.getPaidAt());
        
        billingRepository.save(billing);
        return result;
    }

    @Override
    public void deleteBill(Long id) {
        if (!billingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bill not found");
        }
        billingRepository.deleteById(id);
    }

    private BillingDTO mapEntityToDto(Billing b) {
        BillingDTO dto = new BillingDTO();
        dto.setId(b.getId());
        dto.setAppointmentId(b.getAppointment().getId());
        dto.setAmount(b.getAmount());
        dto.setPaymentType(b.getPaymentType());
        dto.setStatus(b.getStatus());
        dto.setInvoiceNumber(b.getInvoiceNumber());
        dto.setPaymentResult(b.getPaymentResult());
        dto.setPaidAt(b.getPaidAt());
        return dto;
    }
}
