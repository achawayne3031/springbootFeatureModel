package com.example.springbootFeatureModel.config.exception;

public class EmailAlreadyExistException  extends RuntimeException{


    public EmailAlreadyExistException(String message){
        super(message);
    }
}
