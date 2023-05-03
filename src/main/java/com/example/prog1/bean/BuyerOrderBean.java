package com.example.prog1.bean;

public class BuyerOrderBean {
    private String divingOwner;
    private String buyer;

    public BuyerOrderBean(String buyer, String divingOwner){
        setBuyer(buyer);
        setDivingOwner(divingOwner);
    }

    public String getDivingOwner() { return divingOwner;}
    public void setDivingOwner(String divingOwer) { this.divingOwner = divingOwer;}
    public String getBuyer() { return buyer;}
    public void setBuyer(String buyer) {this.buyer = buyer;}
}
