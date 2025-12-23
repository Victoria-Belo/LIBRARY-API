package com.project.library.exceptions;

import org.springframework.http.HttpStatus;
/**
 * @author Victoria
 */
public enum SecurityErrorType {

    TOKEN_INVALID("Invalid or expired token", HttpStatus.UNAUTHORIZED);

    private String message;
    private HttpStatus status;

    SecurityErrorType(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
