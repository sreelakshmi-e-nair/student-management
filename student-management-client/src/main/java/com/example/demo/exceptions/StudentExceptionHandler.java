package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class StudentExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<Object> clientError(){
        ApiError apiError=new ApiError(HttpStatus.FORBIDDEN,"Client failed!");
        return buildResponseEntity(apiError);

    }

 
    @ExceptionHandler(StudentGeneralException.class)
    protected ResponseEntity<Object> generalException(){
        ApiError apiError=new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"Something went wrong!");
        return buildResponseEntity(apiError);

    }
   
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
