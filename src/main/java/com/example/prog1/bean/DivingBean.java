package com.example.prog1.bean;

public class DivingBean implements Bean{
    String name;
    String location;
    String telephone;
    public DivingBean(){}
    public DivingBean(String name, String location, String tel){
        this.name = name;
        this.location = location;
        this.telephone = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
