package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class StudentGeneralException extends RuntimeException{
	private HttpStatus statusCode;
	private String message;

	public StudentGeneralException(HttpStatus statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
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
		return "StudentGeneralException{" +
				"statusCode=" + statusCode +
				", message='" + message + '\'' +
				'}';
	}
}
