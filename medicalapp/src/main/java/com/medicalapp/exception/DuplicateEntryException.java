package com.medicalapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * DuplicateEntryException.java
 *
 * Custom exception for unique constraint violations (409 Conflict).
 *
 * OOP Concepts Applied:
 * - INHERITANCE: Specializes RuntimeException for data integrity issues.
 *
 * Component: Exception Handling
 * Layer: Exception
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEntryException extends RuntimeException {

    public DuplicateEntryException(String message) {
        super(message);
    }
}
