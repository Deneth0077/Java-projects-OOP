package com.medicalapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * AppointmentConflictException.java
 *
 * Thrown when a doctor is double-booked or unavailable (409 Conflict).
 *
 * OOP Concepts Applied:
 * - INHERITANCE: Extends RuntimeException for business-specific scheduling errors.
 *
 * Component: Exception Handling
 * Layer: Exception
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class AppointmentConflictException extends RuntimeException {

    public AppointmentConflictException(String message) {
        super(message);
    }
}
