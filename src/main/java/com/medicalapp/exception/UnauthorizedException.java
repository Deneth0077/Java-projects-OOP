package com.medicalapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UnauthorizedException.java
 *
 * Thrown when security checks or ownership validations fail (403 Forbidden).
 *
 * OOP Concepts Applied:
 * - INHERITANCE: Extends RuntimeException for access control violations.
 *
 * Component: Exception Handling
 * Layer: Exception
 *
 * @author Antigravity
 * @version 1.0
 * @since 2024
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }
}
