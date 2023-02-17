package com.example.prog1.controller.applicativo;

import com.example.prog1.bean.DivingBean;
import com.example.prog1.dao.DivingDAO;
import com.example.prog1.dao.EquipDAO;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
public class ManagerApplicativo {
    public void addProduct (EquipBean equipBean, UserBean userBean) {
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.insertEquip(equipBean, userBean.getUserEmail());
    }
    public void addDiving(UserBean userBean, DivingBean divingBean){
        DivingDAO divingDAO = new DivingDAO();
        divingDAO.insertDiving(divingBean, userBean.getUserEmail());
    }

}
