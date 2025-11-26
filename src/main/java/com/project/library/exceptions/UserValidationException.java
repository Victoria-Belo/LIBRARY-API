package com.project.library.exceptions;

public class UserValidationException extends RuntimeException {
    public UserValidationException(UserErrorType message) {
        super(message.getMessage());
    }
}
