package com.globallogic.userapi.CustoExceptions;

import org.springframework.http.HttpStatus;

public class AuditDataServiceException extends RuntimeException{

    private final HttpStatus status;

    public AuditDataServiceException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
