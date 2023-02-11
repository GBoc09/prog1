package com.example.prog1.bean;

public class CartRowBean {
    private String equipType;
    private Integer equipID;
    private Integer quantity;
    private Double price;
    private String size;

    public CartRowBean(String equipType, Integer equipID, Integer quantity, Double price, String size){
        setEquipType(equipType);
        setEquipID(equipID);
        setQuantity(quantity);
        setPrice(price);
        setSize(size);
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public Integer getEquipID() {
        return equipID;
    }

    public void setEquipID(Integer equipID) {
        this.equipID = equipID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
