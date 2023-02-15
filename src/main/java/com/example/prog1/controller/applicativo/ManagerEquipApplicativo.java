package com.example.prog1.controller.applicativo;

import com.example.prog1.DAO.EquipDAO;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
public class ManagerEquipApplicativo {
    public void addProduct (EquipBean equipBean, UserBean userBean) {
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.insertEquip(equipBean, userBean.getUserEmail());
    }

}
