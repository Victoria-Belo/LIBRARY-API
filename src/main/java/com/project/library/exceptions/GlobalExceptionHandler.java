package com.project.library.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Victoria
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserValidationException.class)
    public ResponseEntity<ErrorResponse> handleUserValidation(UserValidationException ex) {

        UserErrorType error = ex.getErrorType();
        ErrorResponse response = new ErrorResponse(
                error.getStatus().value(),
                error.getMessage()
        );

        return new ResponseEntity<>(response, error.getStatus());
    }


    @ExceptionHandler(SecurityAuthException.class)
    public ResponseEntity<ErrorResponse>handleSecurityAuth(SecurityAuthException ex){
        SecurityErrorType error = ex.getErrorType();
        ErrorResponse response = new ErrorResponse(
                error.getStatus().value(),
                error.getMessage()
        );
        return new ResponseEntity<>(response, error.getStatus());
    }

    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<ErrorResponse>handleExternalApi(ExternalApiException ex){
        ExternalApiErrorType error = ex.getErrorType();
        ErrorResponse response = new ErrorResponse(
                error.getStatus().value(),
                error.getMessage()
        );
        return new ResponseEntity<>(response, error.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex
    ) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Validation error");

        ErrorResponse response = new ErrorResponse(
                400,
                message
        );

        return ResponseEntity.badRequest().body(response);
    }
}