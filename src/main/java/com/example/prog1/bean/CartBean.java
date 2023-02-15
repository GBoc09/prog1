package com.example.prog1.bean;
public class CartBean implements Bean {
    private String type;
    private String size;
    private Integer quant;
    private Integer price;
    public CartBean(){}
    public CartBean (String type, String size, Integer quant, Integer price){
        this.type = type;
        this.size = size;
        this.quant = quant;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQuant() {
        return quant;
    }

    public void setQuant(Integer quant) {
        this.quant = quant;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
