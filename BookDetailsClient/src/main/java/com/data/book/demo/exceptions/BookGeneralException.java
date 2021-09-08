package com.data.book.demo.exceptions;

import org.springframework.http.HttpStatus;

public class BookGeneralException extends RuntimeException {
	
	private String message;
	private HttpStatus statusCode;
	
	
	public BookGeneralException(String message, HttpStatus statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}
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
	@Override
	public String toString() {
		return "BookGeneralException [message=" + message + ", statusCode=" + statusCode + "]";
	}
	

}
