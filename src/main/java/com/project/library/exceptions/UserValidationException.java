package com.project.library.exceptions;

public class UserValidationException extends RuntimeException {

    private final UserErrorType errorType;

    public UserValidationException(UserErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public UserErrorType getErrorType() {
        return errorType;
    }
}
