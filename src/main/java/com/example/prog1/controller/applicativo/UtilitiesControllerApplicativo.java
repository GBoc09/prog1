package com.example.prog1.controller.applicativo;

import com.example.prog1.DAO.EquipDAO;
import com.example.prog1.bean.EquipBean;

public class UtilitiesControllerApplicativo {
    public UtilitiesControllerApplicativo(){}
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
}
