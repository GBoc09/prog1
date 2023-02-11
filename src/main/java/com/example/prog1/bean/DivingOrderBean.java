package com.example.prog1.bean;

public class DivingOrderBean {
    private Integer diving;
    private String orderOwner;
    private Integer idRent;
    public DivingOrderBean(Integer diving, String own, Integer idR ){
        setDiving(diving);
        setOrderOwner(own);
        setIdRent(idR);
    }

    public Integer getDiving() {
        return diving;
    }

    public void setDiving(Integer diving) {
        this.diving = diving;
    }

    public String getOrderOwner() {
        return orderOwner;
    }

    public void setOrderOwner(String orderOwner) {
        this.orderOwner = orderOwner;
    }

    public Integer getIdRent() {
        return idRent;
    }

    public void setIdRent(Integer idRent) {
        this.idRent = idRent;
    }
    public String getExternalOrderCode(){
        return Integer.toString(idRent);
    }
}
