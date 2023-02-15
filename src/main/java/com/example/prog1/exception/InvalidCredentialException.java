package com.example.prog1.exception;

public class InvalidCredentialException  extends Exception{
    public InvalidCredentialException(String error){
        super(error);
    }
    public InvalidCredentialException(){
        super();
    }
}
