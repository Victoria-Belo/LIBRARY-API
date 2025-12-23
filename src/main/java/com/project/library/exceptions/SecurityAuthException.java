package com.project.library.exceptions;

/**
 * @author Victoria
 */
public class SecurityAuthException extends RuntimeException {

    private final SecurityErrorType errorType;

    public SecurityAuthException(SecurityErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public SecurityErrorType getErrorType(){
        return errorType;
    }
}
