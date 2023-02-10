package com.example.prog1.model;

import java.io.Serializable;

public class CartRow implements Serializable {
    private Equipment equipment;
    private Integer quantity;

    public CartRow(Equipment equipment, Integer avail){

    }
    public Double getSubTotal (){
        return equipment.getPrice() * quantity;
    }
    public Equipment getEquipment(){
        return equipment;
    }
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getEquipId(){
        return equipment.getEquipID();
    }
    public String getEquipType (){
        return equipment.getEquipType();
    }
    public String getEquipSize (){
        return equipment.getSize();
    }
    public Double getPrice (){
        return equipment.getPrice();
    }
    /* teniamo traccia della mail dove affiattiamo le cose */
    public String getEquimentRental(){
        return equipment.getRentalEmail();
    }
}
