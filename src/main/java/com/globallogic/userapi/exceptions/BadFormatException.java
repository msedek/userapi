package com.globallogic.userapi.exceptions;

public class BadFormatException extends Exception {

    private String message;
    private int code;

    public BadFormatException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private static final long serialVersionUID = 1L;
}
