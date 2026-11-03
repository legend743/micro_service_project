package com.microservices.userservice.exception;

public class InvalidRequesPassParameter extends RuntimeException{
    public InvalidRequesPassParameter() {
        super("Please pass UserId ");

    }
}
