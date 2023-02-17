package com.example.prog1.bean;

public class EquipBean implements Bean{
    private String type;
    private String size;
    private Integer avail;
    private Integer price;
    public EquipBean(){}
    public EquipBean(String type, String size, Integer avail, Integer price){
        this.type = type;
        this.size = size;
        this.avail = avail;
        this.price = price;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getAvail() {
        return avail;
    }

    public void setAvail(Integer avail) {
        this.avail = avail;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
