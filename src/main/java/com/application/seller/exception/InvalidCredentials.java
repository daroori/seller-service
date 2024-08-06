package com.application.seller.exception;

public class InvalidCredentials extends RuntimeException{
    public InvalidCredentials(String message){
        super(message);
    }
}
