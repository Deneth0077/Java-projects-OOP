package com.medicalapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ValidationException.java
 *
 * Custom exception for manual input validation logic failures (400 Bad Request).
 *
 * OOP Concepts Applied:
 * - INHERITANCE: Customizing the runtime exception hierarchy.
 *
 * Component: Exception Handling
 * Layer: Exception
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
