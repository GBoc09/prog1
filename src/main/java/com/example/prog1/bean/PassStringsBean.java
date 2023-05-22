package com.example.prog1.bean;

public class PassStringsBean implements Bean{
    private String item;
    private String size;
    private Integer quant;
    public PassStringsBean(String str1, String str2, Integer i){
        setItem(str1);
        setSize(str2);
        setQuant(i);
    }

    public String getItem() {return item;}
    public void setItem(String item) {this.item = item;}

    public String getSize() {return size;}
    public void setSize(String size) {this.size = size;}

    public Integer getQuant() {return quant;}
    public void setQuant(Integer quant) {this.quant = quant;}
}
