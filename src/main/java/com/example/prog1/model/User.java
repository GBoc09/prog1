package com.example.prog1.model;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String email;
    private String pass;
    private String name;
    private String lastname;
    private String license;

    protected User (String email, String pass, String name, String lastname, String license){
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.lastname = lastname;
        this.license = license;
    }
    protected User (String email, String name, String lastname, String license){
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.license = license;
    }
    protected User(){}

    protected User(String cName, String cSurname){
        this.name = cName;
        this.lastname = cSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
