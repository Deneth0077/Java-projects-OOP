package com.medicalapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * MedicalAppointmentSystemApplication.java
 *
 * Entry point for the Medical Appointment Scheduling System Spring Boot application.
 * Bootstraps the application context and enables JPA Auditing.
 *
 * OOP Concepts Applied:
 * - Abstraction: The @SpringBootApplication annotation abstracts the complex startup process.
 *
 * Component: Core System
 * Layer: Application Bootstrapper
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@SpringBootApplication
@EnableJpaAuditing // Enables automatic handling of createdAt/updatedAt via BaseEntity
public class MedicalAppointmentSystemApplication {

    /**
     * Main method to launch the Spring Boot application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MedicalAppointmentSystemApplication.class, args);
    }
}
