package com.example.prog1.controller.applicativo;

import com.example.prog1.DAO.EquipDAO;
import com.example.prog1.bean.*;
import com.example.prog1.model.*;

import java.util.ArrayList;
import java.util.List;

public class RentalEquipApplicativo {
    public RentalEquipApplicativo(){/* costruttore */ }
    public List<EquipBean> getEquips () {
        List<EquipBean> equip = new ArrayList<>();
        EquipDAO equipmentDAO = new EquipDAO();
        List<Equipment> equip2 = equipmentDAO.getEquipInfo();
        for (Equipment d : equip2) {
            EquipBean equipmentBean = new EquipBean();
            equipmentBean.setType(d.getEquipType());
            equipmentBean.setSize(d.getSize());
            equipmentBean.setAvail(d.getAvail());
            equipmentBean.setPrice(d.getPrice());
            equip.add(equipmentBean);
        }
        return equip;
    }
//    public List<EquipBean> getEquipForCart (int i) {
//        List<EquipBean> equip = new ArrayList<>();
//        EquipDAO equipmentDAO = new EquipDAO();
//        List<Equipment> equip2 = equipmentDAO.getEquipInfoForCart(i);
//        for (Equipment d : equip2) {
//            EquipBean equipmentBean = new EquipBean();
//            equipmentBean.setType(d.getEquipType());
//            equipmentBean.setSize(d.getSize());
//            equipmentBean.setPrice(d.getPrice());
//            equip.add(equipmentBean);
//        }
//        return equip;
//    }
    public List<CartBean> showCart (String email) {
        List<CartBean> cart = new ArrayList<>();
        EquipDAO equipDAO = new EquipDAO();
        List<Equipment> equip2 = equipDAO.getCart(email);
        for (Equipment d : equip2) {
            CartBean cartBean1 = new CartBean();
            cartBean1.setType(d.getEquipType());
            cartBean1.setSize(d.getSize());
            cartBean1.setPrice(d.getPrice());
            cart.add(cartBean1);
        }
        return cart;
    }
    public void deleteItem(UserBean userBean){
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.deleteItemsFromCart(userBean.getUserEmail());
    }
    private VendorOrderBean createNotificationInfo(String vendor, Rental rental){
        return new VendorOrderBean(vendor, rental.getOwnerEmail(), rental.getIdRent());
    }
}
