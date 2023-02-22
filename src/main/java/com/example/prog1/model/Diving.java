package com.example.prog1.model;

public class Diving {
    private String name;
    private String location;
    private String telephone;
    private String divingMan;
    public Diving (String name, String loc, String tel, String man){
        this.name = name;
        this.location = loc;
        this.telephone = tel;
        this.divingMan = man;
    }
    public Diving (String name, String loc, String tel){
        this.name = name;
        this.location = loc;
        this.telephone = tel;
    }
    public Diving () {}
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
    public String getDivingMan() {
        return divingMan;
    }
    public void setDivingMan(String divingMan) {
        this.divingMan = divingMan;
    }
}
