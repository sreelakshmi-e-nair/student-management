package com.example.demo.exceptions;

import com.mongodb.MongoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StudentExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<Object> nullPointerException(){
        ApiError apiError=new ApiError(HttpStatus.NOT_FOUND,"Data not found");
        return buildResponseEntity(apiError);

    }

    @ExceptionHandler(StudentDatabaseException.class)
    protected ResponseEntity<Object> mongoException(){
       ApiError apiError=new ApiError(HttpStatus.BAD_GATEWAY,"Database not found");
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
