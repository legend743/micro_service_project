package com.example.microservices.hotel_microservices.exception;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import lombok.experimental.ExtensionMethod;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Map<String, Object>> arithmeticException(ArithmeticException ex, HttpServletRequest req) {
        return builder(HttpStatus.BAD_REQUEST, ex.getMessage(), req);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>>resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest req){
        return builder(HttpStatus.BAD_REQUEST, ex.getMessage(), req);

    }
@ExceptionHandler(BadRequestHandler.class)
public ResponseEntity<Map<String,Object>>badRequestHandler(BadRequestHandler ex,HttpServletRequest req){
    return builder(HttpStatus.BAD_REQUEST, ex.getMessage(), req);
}
    private ResponseEntity<Map<String, Object>> builder(HttpStatus status, String message, HttpServletRequest req) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put(message, message);
        body.put("status", status.value());
        body.put("path", req.getRequestURI());
        return ResponseEntity.status(status).body(body);

    }

}
