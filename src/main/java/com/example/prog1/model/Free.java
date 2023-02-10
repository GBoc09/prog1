package com.example.prog1.model;

import java.io.Serializable;

public class Free extends User implements Serializable {
    public Free(String email, String pass, String name, String last, String lic){
        super (email,pass,name,last,lic);
    }
}
