package com.example.prog1.exception;

public class NotExistentUserException extends Exception{
    public NotExistentUserException(String error){
        super(error);
    }
}
