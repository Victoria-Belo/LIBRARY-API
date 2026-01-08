package com.project.library.exceptions;

import org.springframework.http.HttpStatus;

public enum UserErrorType {

    USER_NOT_FOUND("User not found.", HttpStatus.NOT_FOUND),
    EMAIL_NOT_FOUND("Email not found.", HttpStatus.NOT_FOUND),
    INVALID_PASSWORD("Invalid password", HttpStatus.UNAUTHORIZED),
    INVALID_EMAIL("Email format is invalid.", HttpStatus.BAD_REQUEST),
    PASSWORD_TOO_SHORT("Password must have at least 5 characters.", HttpStatus.BAD_REQUEST),
    USER_TAKEN("User already exists.", HttpStatus.CONFLICT),
    BOOK_NOT_FOUND("Book not found.", HttpStatus.NOT_FOUND),
    BOOK_ALREADY_EXISTS("Book already exists", HttpStatus.CONFLICT),
    TITLE_IS_EMPTY("Title must not be empty", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus status;

    UserErrorType(String message, HttpStatus status) {
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
