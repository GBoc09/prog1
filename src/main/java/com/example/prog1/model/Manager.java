package com.example.prog1.model;

import java.io.Serializable;

public class Manager extends User implements Serializable {
    public Manager(String email, String pass, String name, String last, String lic){
        super (email,pass,name,last,lic);
    }
}
