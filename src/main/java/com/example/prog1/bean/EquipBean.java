package com.example.prog1.bean;

public class EquipBean {
    private Integer id;
    private String type;
    private String size;
    private Integer avail;
    private Double price;

    public EquipBean(){
        this(0, "", "", 0, 0.0);
    }

    public EquipBean(Integer id){
        this(id, "", "", 0, 0.0);
    }
    public EquipBean(Integer id, String type, String size, Integer avail, Double price){
        setId(id);
        setType(type);
        setSize(size);
        setAvail(avail);
        setPrice(price);
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getAvail() {
        return avail;
    }

    public void setAvail(Integer avail) {
        this.avail = avail;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
