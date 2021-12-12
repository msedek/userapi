package com.globallogic.userapi.customexceptions;

import org.springframework.http.HttpStatus;

public class AuditDataServiceException extends RuntimeException{

    private final HttpStatus status;

    /**
     * AuditDataServiceException exception
     * @param  status status HTTP.
     * @param  message, mensaje de error.
     */
    public AuditDataServiceException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
