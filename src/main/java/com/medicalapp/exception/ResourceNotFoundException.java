package com.medicalapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ResourceNotFoundException.java
 *
 * Custom exception for missing entities (404 Not Found).
 *
 * OOP Concepts Applied:
 * - INHERITANCE: extends RuntimeException to benefit from Spring's exception handling.
 *
 * Component: Exception Handling
 * Layer: Exception
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * INHERITANCE: Constructor calling the superclass (RuntimeException).
     *
     * @param message Error message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
