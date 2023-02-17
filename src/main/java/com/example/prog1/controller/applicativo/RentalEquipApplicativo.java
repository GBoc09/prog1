package com.example.prog1.controller.applicativo;

import com.example.prog1.controller.grafico.InternalControllerGrafico;
import com.example.prog1.dao.CartDAO;
import com.example.prog1.dao.EquipDAO;
import com.example.prog1.bean.*;
import com.example.prog1.dao.ManagerDAO;
import com.example.prog1.dao.RentalDAO;
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
    public void saveItem(List<CartBean> cartBeans, UserBean userBean){
        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.insertRent(cartBeans.get(0), cartBeans.get(3),userBean.getUserEmail());
    }
    public EquipBean infoEquipGeneral(int selectedIndex){
        EquipDAO equipDAO = new EquipDAO();
        EquipBean equipBean = null;
        equipBean = equipDAO.selectEquipByOrder(selectedIndex);
        return equipBean;
    }
    public Integer infoDispEquip(int index){
        EquipDAO equipDAO = new EquipDAO();
        Integer i;
        i = equipDAO.selectAvailability(index);
        return i;
    }
    public void infoEquipCart(int selectedIndex, int quant, String email) {
        EquipDAO equipDAO = new EquipDAO();
        CartDAO cartDAO = new CartDAO();
        EquipBean equipBean = null;
        equipBean = equipDAO.selectEquipByOrder(selectedIndex);
        cartDAO.insertIntoCart(equipBean, quant, email);
    }
    /** trovare un modi di scegliere il diving e passare i dati del manager */
    public void sendEmail(VendorOrderBean vendorOrderBean){
        UserBean userBean = InternalControllerGrafico.getInternalControllerInstance().getLoggedUser();
        List<Manager> managers = getEmailManager();
        ((VendorOrderBean)vendorOrderBean).setOrderOwnerEmail(userBean.getUserEmail());
        for (Manager manger : managers){
            ((VendorOrderBean)vendorOrderBean).addSubscribedEmail(manger.getEmail());
        }
//        super(sendEmail(vendorOrderBean));
        vendorOrderBean.notifyRental();
    }
    private List<Manager> getEmailManager(){
        ManagerDAO managerDAO = new ManagerDAO();
        List<Manager> manger = new ArrayList<>();
        Manager man;
        man = managerDAO.selectEmailMan();
        manger.add(man);
        return manger;
    }
}

