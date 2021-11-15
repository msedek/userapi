package com.globallogic.userapi.entities;

import org.springframework.http.HttpStatus;

public class AuditDataResponse {

    private HttpStatus status;
    private String result;
    private User user;

    public AuditDataResponse(HttpStatus status, String result, User user) {
        this.status = status;
        this.result = result;
        this.user = user;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
