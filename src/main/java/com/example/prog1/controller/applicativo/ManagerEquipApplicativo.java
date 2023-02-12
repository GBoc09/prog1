package com.example.prog1.controller.applicativo;

import com.example.prog1.DAO.EquipDAO;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.catalogue.EquipCatalogue;
import com.example.prog1.exception.DuplicatedEquipException;
import com.example.prog1.exception.InvalidInsertionEquipException;
import com.example.prog1.model.Equipment;
import com.example.prog1.model.Manager;

import java.util.List;
import java.util.Objects;

public class ManagerEquipApplicativo {
    public void addProduct (EquipBean equipBean, UserBean userBean) throws DuplicatedEquipException, InvalidInsertionEquipException {
        EquipDAO equipDAO = new EquipDAO();
        EquipCatalogue equipCatalogue = equipDAO.loadAllEquipByManager(userBean.getLicense());
        Manager manager = new Manager(userBean.getUserEmail(), userBean.getName(), userBean.getSurname(), userBean.getLicense());
        Equipment equipment = new Equipment(equipBean.getId(), equipBean.getType(), equipBean.getSize(),equipBean.getPrice(),equipBean.getAvail(),manager);
        equipment.setManager(manager);
        List<Equipment> equips = equipCatalogue.filterByType(equipment.getEquipType());
        for(Equipment locEquip : equips){
            if (controlDuplicatedItem(locEquip, equipment)){
                throw new DuplicatedEquipException("Product alread in");
            }
        }
        equipCatalogue.addEquip(equipment);
        if (!equipDAO.insertEquip(equipment, userBean.getLicense())){
            throw new InvalidInsertionEquipException("Invalid product");
        }
    }
    private boolean controlDuplicatedItem(Equipment locEquip, Equipment equipment){
        return (Objects.equals(locEquip.getEquipType(), equipment.getEquipType())) && (Objects.equals(locEquip.getSize(), equipment.getSize()));
    }
}
