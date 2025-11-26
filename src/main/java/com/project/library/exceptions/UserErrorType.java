package com.project.library.exceptions;

public enum UserErrorType {

    USER_NOT_FOUND("User not found."),
    EMAIL_NOT_FOUND("Email not found."),
    INVALID_EMAIL("Email format is invalid."),
    PASSWORD_TOO_SHORT("Password must have at least 8 characters."),
    USER_TAKEN("User already exists.");

    private final String message;

    UserErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
