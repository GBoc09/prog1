package com.example.prog1.pattern.factory;

import com.example.prog1.model.Scuba;
import com.example.prog1.model.User;
/**
 * creiamo dei concrete product per l'entità user che è astratta */
public class Factory {
    public User createScuba(String email, String pass, String name, String last, String lic){
        return new Scuba(email, pass, name, last, lic);
    }
    public User createFree(String email, String pass, String name, String last, String lic){
        return new Scuba(email, pass, name, last, lic);
    }
    public User createManager(String email, String pass, String name, String last, String lic){
        return new Scuba(email, pass, name, last, lic);
    }
}
