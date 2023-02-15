package com.example.prog1.model;

import javafx.scene.image.Image;

public class Equipment {
    private Integer equipID;
    private String equipType;
    private String size;
    private Integer price;
    private Integer avail;
    private Manager manager;
    private Diving diving;
    public Equipment (String type, String size, Integer avail,Integer price){
        this. equipType = type;
        this.size = size;
        this.avail = avail;
        this.price = price;
    }
    public Equipment (String type, String size, Integer avail, Integer price, Diving diving, Manager manager){
        this. equipType = type;
        this.size = size;
        this.avail = avail;
        this.price = price;
        this.diving = diving;
        this.manager = manager;
    }
    public Equipment(){}
    public Integer getEquipID() {
        return equipID;
    }
    public void setEquipID(Integer equipID) {
        this.equipID = equipID;
    }
    public String getEquipType() {
        return equipType;
    }
    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getAvail() {
        return avail;
    }
    public void setAvail(Integer avail) {
        this.avail = avail;
    }
    public Manager getManager() {
        return manager;
    }
    public void setManager(Manager manager) {
        this.manager = manager;
    }
    public String getManLicense () {
        return manager.getLicense();
    }
    public Diving getDiving() {
        return diving;
    }
    public void setDiving(Diving diving) {
        this.diving = diving;
    }
    public String getDivingName(){
        return diving.getName();
    }

}

