package com.example.prog1.model;

import java.io.Serializable;

public class CartRow implements Serializable {
    private Equipment equipment;
    private Integer quantity;

    public CartRow(Equipment equipment, Integer avail){

    }
    public Integer getSubTotal (){
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
    public Integer getPrice (){
        return equipment.getPrice();
    }
    /* teniamo traccia della licensa del manager del diving dove prendiamo le cose */
    public String getEquipVendor(){
        return equipment.getManLicense();
    }
}
