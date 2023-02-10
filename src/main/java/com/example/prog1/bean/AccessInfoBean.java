package com.example.prog1.bean;

public class AccessInfoBean implements Bean{
    String userEmail;
    String userPass;

    public AccessInfoBean(String userEmail, String userPass){
        setUserEmail(userEmail);
        setUserPass(userPass);
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
