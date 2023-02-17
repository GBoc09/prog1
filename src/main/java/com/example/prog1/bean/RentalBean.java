package com.example.prog1.bean;

public class RentalBean implements Bean{
    private Integer idRental;
    private Integer idEquip;
    private String equipType;
    private String scuba;
    private String divMan;
    private Integer total;

    public RentalBean (){}
    public RentalBean(Integer idR, Integer idE, String eqT, String s, String m){
        this.idRental = idR;
        this.idEquip = idE;
        this.equipType = eqT;
        this.scuba = s;
        this.divMan = m;
    }
    public Integer getIdRental() {
        return idRental;
    }

    public void setIdRental(Integer idRental) {
        this.idRental = idRental;
    }

    public Integer getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(Integer idEquip) {
        this.idEquip = idEquip;
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

    public String getDivMan() {
        return divMan;
    }

    public void setDivMan(String divMan) {
        this.divMan = divMan;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
