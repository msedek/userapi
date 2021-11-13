package com.globallogic.userapi.entities;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("mensaje")
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
