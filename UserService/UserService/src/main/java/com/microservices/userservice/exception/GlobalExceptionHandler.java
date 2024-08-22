package com.microservices.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.microservices.userservice.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exc) {
        String message = exc.getMessage();
        ApiResponse response = new ApiResponse.Builder()
            .message(message)
            .success(false)
            .status(HttpStatus.NOT_FOUND)
            .build();
        
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
