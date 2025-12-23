package com.project.library.exceptions;
import org.springframework.http.HttpStatus;

/**
 * @author Victoria
 */

public enum ExternalApiErrorType {
    EXTERNAL_API_ERROR("Erro ao consultar serviço externo", HttpStatus.BAD_GATEWAY);

    private String message;
    private HttpStatus status;

    ExternalApiErrorType(String message, HttpStatus status) {
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
