package com.example.demo.models;

import org.springframework.http.HttpStatus;

public class StudentResponseModel {
    private String message;
    private HttpStatus statusCode;
    private Object result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "StudentResponseModel{" +
                "message='" + message + '\'' +
                ", statusCode=" + statusCode +
                ", result=" + result +
                '}';
    }
}
