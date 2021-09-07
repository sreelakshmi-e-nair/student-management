package com.example.demo.exceptions;

public class StudentDatabaseException extends RuntimeException{
    private String message;

    public StudentDatabaseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "StudentDatabaseException{" +
                "message='" + message + '\'' +
                '}';
    }
}
