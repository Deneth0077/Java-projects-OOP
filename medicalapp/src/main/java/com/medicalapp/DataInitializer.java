package com.medicalapp;

import com.medicalapp.dto.*;
import com.medicalapp.entity.Admin;
import com.medicalapp.entity.Appointment;
import com.medicalapp.entity.Billing;
import com.medicalapp.entity.MedicalRecord;
import com.medicalapp.entity.Patient;
import com.medicalapp.entity.Doctor;
import com.medicalapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * DataInitializer.java
 *
 * Startup component to seed the database with initial sample data for demonstration.
 * Ensures all components have working records across all hierarchies.
 *
 * OOP Concepts Applied:
 * - ABSTRACTION: Uses Service interfaces to interact with the underlying database logic.
 *
 * Component: Project Configuration
 * Layer: Data Initializer
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final IPatientService patientService;
    private final IDoctorService doctorService;
    private final IAdminService adminService;
    private final IAppointmentService appointmentService;
    private final IBillingService billingService;
    private final IMedicalRecordService medicalRecordService;

    /**
     * Executes data seeding logic on application startup.
     */
    @Override
    public void run(String... args) {
        System.out.println("=== INITIALIZING SAMPLE DATA ===");

        // 1. Seed Patients (2 Out, 1 In)
        PatientDTO p1 = new PatientDTO();
        p1.setName("Alice Johnson");
        p1.setNic("901234567V");
        p1.setPhone("0712223334");
        p1.setEmail("alice@gmail.com");
        p1.setPatientType(Patient.PatientType.OUTPATIENT);
        p1 = patientService.registerPatient(p1);

        PatientDTO p2 = new PatientDTO();
        p2.setName("Bob Smith");
        p2.setNic("856789012V");
        p2.setPhone("0774445556");
        p2.setEmail("bob@hotmail.com");
        p2.setPatientType(Patient.PatientType.OUTPATIENT);
        p2 = patientService.registerPatient(p2);

        PatientDTO p3 = new PatientDTO();
        p3.setName("Charlie Ward");
        p3.setNic("998877665V");
        p3.setPhone("0728889990");
        p3.setPatientType(Patient.PatientType.INPATIENT);
        p3.setWardNumber("Ward-05A");
        p3 = patientService.registerPatient(p3);

        // 2. Seed Doctors (2 Gen, 1 Spec)
        DoctorDTO d1 = new DoctorDTO();
        d1.setName("Dr. Gregory House");
        d1.setEmail("house@hospital.com");
        d1.setPhone("0112345678");
        d1.setDoctorType(Doctor.DoctorType.GENERAL);
        d1.setConsultationFee(1000.0);
        d1.setAvailableDays("MON,WED,FRI");
        d1.setAvailableFrom(LocalTime.of(9, 0));
        d1.setAvailableTo(LocalTime.of(17, 0));
        d1 = doctorService.addDoctor(d1);

        DoctorDTO d2 = new DoctorDTO();
        d2.setName("Dr. James Wilson");
        d2.setEmail("wilson@hospital.com");
        d2.setPhone("0119998881");
        d2.setDoctorType(Doctor.DoctorType.GENERAL);
        d2.setConsultationFee(1000.0);
        d2.setAvailableDays("TUE,THU");
        d2.setAvailableFrom(LocalTime.of(10, 0));
        d2.setAvailableTo(LocalTime.of(18, 0));
        d2 = doctorService.addDoctor(d2);

        DoctorDTO d3 = new DoctorDTO();
        d3.setName("Dr. Eric Foreman");
        d3.setEmail("foreman@hospital.com");
        d3.setPhone("0117772223");
        d3.setDoctorType(Doctor.DoctorType.SPECIALIST);
        d3.setSpecialistArea("Neurology");
        d3.setConsultationFee(2000.0);
        d3.setAvailableDays("SAT");
        d3.setAvailableFrom(LocalTime.of(8, 0));
        d3.setAvailableTo(LocalTime.of(12, 0));
        d3 = doctorService.addDoctor(d3);

        // 3. Seed Admins
        AdminRequestDTO a1 = new AdminRequestDTO();
        a1.setUsername("superadmin");
        a1.setEmail("super@hospital.com");
        a1.setPassword("super123");
        a1.setRole(Admin.AdminRole.SUPER_ADMIN);
        adminService.createAdmin(a1);

        AdminRequestDTO a2 = new AdminRequestDTO();
        a2.setUsername("receptionist");
        a2.setEmail("frontdesk@hospital.com");
        a2.setPassword("pass123");
        a2.setRole(Admin.AdminRole.ADMIN);
        adminService.createAdmin(a2);

        // 4. Seed Appointments
        AppointmentDTO app1 = new AppointmentDTO();
        app1.setPatientId(p1.getId());
        app1.setDoctorId(d1.getId());
        app1.setAppointmentDate(LocalDate.now().with(java.time.temporal.TemporalAdjusters.next(java.time.DayOfWeek.MONDAY)));
        app1.setAppointmentTime(LocalTime.of(10, 0));
        app1.setType(Appointment.AppointmentType.IN_PERSON);
        app1.setNotes("First consultation for hypertension");
        app1 = appointmentService.bookAppointment(app1);

        AppointmentDTO app2 = new AppointmentDTO();
        app2.setPatientId(p2.getId());
        app2.setDoctorId(d2.getId());
        app2.setAppointmentDate(LocalDate.now().with(java.time.temporal.TemporalAdjusters.next(java.time.DayOfWeek.TUESDAY)));
        app2.setAppointmentTime(LocalTime.of(14, 30));
        app1.setType(Appointment.AppointmentType.ONLINE);
        appointmentService.bookAppointment(app2);

        AppointmentDTO app3 = new AppointmentDTO();
        app3.setPatientId(p3.getId());
        app3.setDoctorId(d1.getId());
        app3.setAppointmentDate(LocalDate.now().with(java.time.temporal.TemporalAdjusters.next(java.time.DayOfWeek.WEDNESDAY)));
        app3.setAppointmentTime(LocalTime.of(11, 0));
        app3.setType(Appointment.AppointmentType.IN_PERSON);
        app3 = appointmentService.bookAppointment(app3);
        appointmentService.completeAppointment(app3.getId());

        // 5. Seed Billing
        BillingDTO b1 = new BillingDTO();
        b1.setAppointmentId(app3.getId());
        b1.setAmount(doctorService.getCalculatedFee(d1.getId()));
        b1 = billingService.createBill(b1);
        
        Map<String, String> pDetails = new HashMap<>();
        pDetails.put("gateway", "PayPay");
        billingService.processPayment(b1.getId(), Billing.PaymentType.ONLINE, pDetails);

        BillingDTO b2 = new BillingDTO();
        b2.setAppointmentId(app1.getId());
        b2.setAmount(doctorService.getCalculatedFee(d1.getId()));
        billingService.createBill(b2);

        // 6. Seed Medical Records
        MedicalRecordDTO mr1 = new MedicalRecordDTO();
        mr1.setPatientId(p3.getId());
        mr1.setDoctorId(d1.getId());
        mr1.setRecordDate(LocalDate.now());
        mr1.setDiagnosis("Common Cold");
        mr1.setRecordType(MedicalRecord.RecordType.PRESCRIPTION);
        mr1.setMedicineName("Paracetamol");
        mr1.setDosage("500mg");
        mr1.setDuration("3 Days");
        medicalRecordService.addRecord(mr1);

        MedicalRecordDTO mr2 = new MedicalRecordDTO();
        mr2.setPatientId(p3.getId());
        mr2.setDoctorId(d1.getId());
        mr2.setRecordDate(LocalDate.now());
        mr2.setDiagnosis("Anemia Fever");
        mr2.setRecordType(MedicalRecord.RecordType.LAB_RESULT);
        mr2.setTestName("Full Blood Count");
        mr2.setTestResult("Hemoglobin: 11.2 (Low)");
        mr2.setReferenceRange("13.5 - 17.5");
        medicalRecordService.addRecord(mr2);

        System.out.println("=== SAMPLE DATA SEEDING COMPLETED ===");
    }
}
