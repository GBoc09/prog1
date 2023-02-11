package com.example.prog1.model;

import java.io.Serializable;

public class Equipment implements Serializable {
    private Integer equipID;
    private String equipType;
    private String size;
    private Double price;
    private Integer avail;
    private Diving rental;

    public Equipment(Integer id, String type, String size, String avail, String s, Double price, Diving diving){
        this(0,"","",0.0,0,null);
    }
    public Equipment(Integer id, String type, String taglia, Double prezzo, Integer disp, Diving rental){
        setEquipID(id);
        setEquipType(type);
        setSize(taglia);
        setPrice(prezzo);
        setAvail(disp);
        setRental(rental);
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAvail() {
        return avail;
    }

    public void setAvail(Integer avail) {
        this.avail = avail;
    }

    public Diving getRental() {
        return rental;
    }

    public void setRental(Diving rental) {
        this.rental = rental;
    }
    public Integer getRentalID(){
        return rental.getId();
    }
   /* @Override
    public boolean equals (Object toCompare){
        Boolean result;
        if (this == toCompare){
            result = true;
        } else if (toCompare instanceof Equipment) {
            result = (String.compare(this.getEquipID(), (Equipment)((Equipment) toCompare).getEquipID()) == 0);
        }else result = false;
        return result;
    }*/
    @Override
    public int hashCode(){
        return super.hashCode();
    }
}

