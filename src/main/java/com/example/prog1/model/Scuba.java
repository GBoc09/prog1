package com.example.prog1.model;

import java.io.Serializable;

public class Scuba extends User implements Serializable {
    public Scuba(String email, String pass, String name, String last, String lic){
        super (email,pass,name,last,lic);
    }
}
