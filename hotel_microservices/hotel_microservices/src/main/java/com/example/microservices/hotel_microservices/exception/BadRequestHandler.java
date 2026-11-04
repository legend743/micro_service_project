package com.example.microservices.hotel_microservices.exception;

public class BadRequestHandler extends RuntimeException {
    public BadRequestHandler() {
        super();
    }

    public BadRequestHandler(String message) {
        super(message);
    }

}
