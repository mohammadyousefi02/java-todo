package com.example.todoo.exceptionHandlers;

import org.springframework.http.HttpStatus;

public class Exception {
    private String message;
    private int statusCode;

    public Exception(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return "Exception{" +
                "message='" + message + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }
}
