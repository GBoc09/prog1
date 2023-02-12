package com.example.prog1.bean;

public class VendorOrderBean {
    private String vendor;
    private String orderOwner;
    private Integer rentCode;

    public VendorOrderBean(String vendor, String orderOwner, Integer rentCode){
        setVendor(vendor);
        setOrderOwner(orderOwner);
        setRentCode(rentCode);
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

    public Integer getRentCode() {
        return rentCode;
    }

    public void setRentCode(Integer rentCode) {
        this.rentCode = rentCode;
    }

    public String getExternalOrderCode(){
        return Integer.toString(rentCode);
    }
}
