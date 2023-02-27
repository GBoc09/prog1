package com.example.prog1.bean;

public class RentalBean implements Bean{
    private Integer idRental;
    private String equipType;
    private String scuba;
    private String div;
    private Integer total;

    public RentalBean (){}
    public RentalBean(Integer idR, String eqT, String s, String m){
        this.idRental = idR;
        this.equipType = eqT;
        this.scuba = s;
        this.div = m;
    }
    public Integer getIdRental() {
        return idRental;
    }

    public void setIdRental(Integer idRental) {
        this.idRental = idRental;
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public String getScuba() {
        return scuba;
    }

    public void setScuba(String scuba) {
        this.scuba = scuba;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String divMan) {
        this.div = div;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
