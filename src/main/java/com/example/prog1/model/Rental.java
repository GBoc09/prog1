package com.example.prog1.model;

import java.util.List;

public class Rental {
    private Integer idRent;
    private Integer idEquip;
    private Integer diving;
    private Double total;
    private Scuba rentalOwner;

    private List<CartRow> cartRows;
    public Rental(){}
    public Rental(Integer id, Integer equip, Integer div,Double costo, Scuba owner){
        setIdRent(id);
        setIdEquip(equip);
        setDiving(div);
        setTotal(costo);
        setRentalOwner(owner);
    }

    public Integer getDiving() {
        return diving;
    }

    public void setDiving(Integer diving) {
        this.diving = diving;
    }

    public Integer getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(Integer idEquip) {
        this.idEquip = idEquip;
    }

    public List<CartRow> getCartRows() {
        return cartRows;
    }
    public String getOwnerEmail(){
        return rentalOwner.getEmail();
    }

    public void setCartRows(List<CartRow> cartRows) {
        this.cartRows = cartRows;
    }

    public Integer getIdRent() {
        return idRent;
    }

    public void setIdRent(Integer idRent) {
        this.idRent = idRent;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Scuba getRentalOwner() {
        return rentalOwner;
    }

    public void setRentalOwner(Scuba rentalOwner) {
        this.rentalOwner = rentalOwner;
    }
}
