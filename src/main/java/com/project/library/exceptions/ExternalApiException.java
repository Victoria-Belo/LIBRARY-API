package com.project.library.exceptions;

/**
 * @author Victoria
 */
public class ExternalApiException extends RuntimeException {

    private final ExternalApiErrorType errorType;

    public ExternalApiException(ExternalApiErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ExternalApiErrorType getErrorType(){
        return errorType;
    }


}
