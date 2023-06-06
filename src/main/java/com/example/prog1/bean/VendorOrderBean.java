package com.example.prog1.bean;

import com.example.prog1.pattern.observer.Subject;

public class VendorOrderBean extends Subject {
    private String vendor;
    private String orderOwner;

    public VendorOrderBean(String vendor, String orderOwner){
        setVendor(vendor);
        setOrderOwner(orderOwner);
    }
    public String getVendor() {
        return vendor;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    public String getOrderOwner() {
        return orderOwner;
    }
    public void setOrderOwner(String orderOwner) {
        this.orderOwner = orderOwner;
    }

    public void notifyChanges(){
        super.notifyObservers();
    }
}