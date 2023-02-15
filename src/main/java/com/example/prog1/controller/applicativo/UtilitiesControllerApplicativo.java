package com.example.prog1.controller.applicativo;

import com.example.prog1.DAO.CartDAO;
import com.example.prog1.DAO.EquipDAO;
import com.example.prog1.bean.EquipBean;

public class UtilitiesControllerApplicativo {
    public UtilitiesControllerApplicativo(){/* costruttore */}
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
}
