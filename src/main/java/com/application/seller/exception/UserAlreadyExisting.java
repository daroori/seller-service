package com.application.seller.exception;

public class UserAlreadyExisting extends RuntimeException{
    public UserAlreadyExisting(String message){
        super(message);
    }
}
