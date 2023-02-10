package com.example.prog1.exception;

public class DuplicatedUserException extends Exception{
    public DuplicatedUserException(String error){
        super(error);
    }
}
