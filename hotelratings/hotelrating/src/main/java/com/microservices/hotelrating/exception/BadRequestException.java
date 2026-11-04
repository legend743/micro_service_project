package com.microservices.hotelrating.exception;



public class BadRequestException extends RuntimeException {
    public BadRequestException(){
        super("Required parameter not passed");
    }
    public BadRequestException(String message){
        super(message);
    }

}
