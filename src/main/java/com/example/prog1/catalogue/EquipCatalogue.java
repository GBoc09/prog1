package com.example.prog1.catalogue;

import com.example.prog1.model.Equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EquipCatalogue {
    private final List<Equipment> equipmentList;
    public EquipCatalogue(List<Equipment> equips){
        this.equipmentList = equips;
    }
    public List<Equipment> filterByType(String equipType){
        List<Equipment> filteredEquipArray = new ArrayList<>();
        for (Equipment equipment : equipmentList){
            if(equipment.getEquipType().contains(equipType)){
                filteredEquipArray.add(equipment);
            }
        }
        return filteredEquipArray;
    }
    public Equipment getEquipByID(Integer id){
        for (Equipment equipment: equipmentList){
            if(Objects.equals(equipment.getEquipID(), id)){
                return equipment;
            }
        }
        return null;
    }
    public void addEquip(Equipment newEquip){
        this.equipmentList.add(newEquip);
    }
}
