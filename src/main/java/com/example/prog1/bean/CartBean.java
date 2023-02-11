package com.example.prog1.bean;

import java.util.ArrayList;
import java.util.List;

public class CartBean implements Bean {
    private Double total;
    private List<CartRowBean> cartRowBeanList;

    public CartBean(){
        this(0.0, new ArrayList<>());

    }
    public CartBean(Double total, List<CartRowBean> cartRowBean){
        setTotal(total);
        setCartRowBeanList(cartRowBean);

    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<CartRowBean> getCartRowBeanList() {
        return cartRowBeanList;
    }

    public  void setCartRowBeanList(List<CartRowBean> cartRowBeanList) {
        this.cartRowBeanList = cartRowBeanList;
    }
}
