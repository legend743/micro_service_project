package com.example.microservices.hotel_microservices.exception;



public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(){
        super("Resource Not Found.");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }

}
