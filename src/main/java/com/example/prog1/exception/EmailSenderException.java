package com.example.prog1.exception;

public class EmailSenderException extends Exception{
    public EmailSenderException(String error){
        super(error);
    }
    public EmailSenderException(){
        super();
    }
}
