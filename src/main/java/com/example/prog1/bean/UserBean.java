package com.example.prog1.bean;

public class UserBean implements Bean{
    private String UserEmail;
    private String name;
    private String surname;
    private String pass;
    private String license;
    private Integer userType;

    public UserBean(){
    }
    public UserBean (String userEmail, Integer userType){
        setUserEmail(userEmail);
        setUserType(userType);
    }
    public UserBean(String userEmail){
        setUserEmail(userEmail);
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
