package com.example.prog1.controller.applicativo;

import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.DivingBean;
import com.example.prog1.dao.DivingDAO;
import com.example.prog1.dao.EquipDAO;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.model.Diving;
import com.example.prog1.model.Equipment;

import java.util.ArrayList;
import java.util.List;

public class ManagerApplicativo {
    public void addProduct (EquipBean equipBean, UserBean userBean)  {
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.insertEquip(equipBean, userBean.getUserEmail());
    }
    public void addDiving(UserBean userBean, DivingBean divingBean)  {
        DivingDAO divingDAO = new DivingDAO();
        divingDAO.insertDiving(divingBean, userBean.getUserEmail());
    }
    public static List<DivingBean> getDivings(UserBean userBean) {
        List<DivingBean> div = new ArrayList<>();
        DivingDAO divingDAO = new DivingDAO();
        List<Diving> div2 = divingDAO.getDivInfo();
        for (Diving d : div2) {
            DivingBean divingBean = new DivingBean();
            divingBean.setName(d.getName());
            divingBean.setLocation(d.getLocation());
            divingBean.setTelephone(d.getTelephone());
            div.add(divingBean);
        }
        return div;
    }
    public static List<EquipBean> getEquips(UserBean userBean, CominicationBean cominicationBean) {
        List<EquipBean> equip = new ArrayList<>();
        EquipDAO equipDAO = new EquipDAO();
        List<Equipment> equip2 = equipDAO.getEquipInfoManEDiv(userBean, cominicationBean);
        for (Equipment d : equip2) {
            EquipBean equipBean = new EquipBean();
            equipBean.setType(d.getEquipType());
            equipBean.setSize(d.getSize());
            equipBean.setAvail(d.getAvail());
            equipBean.setPrice(d.getPrice());
            equip.add(equipBean);
        }
        return equip;
    }
}
