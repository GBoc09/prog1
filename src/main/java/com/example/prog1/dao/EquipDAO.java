package com.example.prog1.dao;

import com.example.prog1.bean.EquipBean;
import com.example.prog1.catalogue.EquipCatalogue;
import com.example.prog1.dbConnection.MyConnectionSingleton;
import com.example.prog1.model.Equipment;
import com.example.prog1.query.DivingQuery;
import com.example.prog1.query.EquipQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipDAO {
    /* SQL column*/
    private static final String ID_EQUIP = "idEquipment";
    private static final String TYPE_EQUIP = "equipType";
    private static final String PRICE = "price";
    private static final String SIZE = "size";
    private static final String AVAIL = "availability";
    private static final String MANAGER = "manager";
    private static final String DIVING = "DivingName";

    /** inserimento diretto per tipo prezzo taglia e diponibilità
     * inserimento diretto per license
     * inserimento indiretto per diving: prendi diving name con query da diving se il numero di license corrisponde
     * poi salvarlo in una stringa e passalo alla query di inserimento in equipment */
    public String divingName (String manEmail){
        String name = null;
        Connection con = MyConnectionSingleton.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = DivingQuery.selectDivingName(stmt, manEmail);){
             if (rs.next()){
                 name = rs.getString(1);
            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return name;
    }
    public void insertEquip( EquipBean equipBean, String manEmail){
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement()){
            String nomeDiving = divingName(manEmail);
           EquipQuery.insertEquip(stmt, equipBean.getType(), equipBean.getSize(), equipBean.getAvail(), equipBean.getPrice(), nomeDiving,manEmail);

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    public EquipBean selectEquipByOrder (int index) {
        EquipBean equipBean= new EquipBean();
        Connection con = MyConnectionSingleton.getConnection();
        try (Statement stmt = con.createStatement();
        ResultSet rs = EquipQuery.loadEquipByOrder(stmt, index);){
            while (rs.next()){
                equipBean.setType(rs.getString(1));
                equipBean.setSize(rs.getString(2));
                equipBean.setPrice(rs.getInt(3));
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return equipBean;
    }
    public Integer selectAvailability(int index){
        Integer i=0;
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement();
        ResultSet rs = EquipQuery.loadAvail(stmt,index)){
            while(rs.next()){
                i = rs.getInt(1);
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return i;
    }
    public EquipCatalogue loadAllProducts(){
        ArrayList<Equipment> equips = new ArrayList<>();
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = EquipQuery.loadAllProducts(stmt);){
            while (rs.next()){
                Equipment equipment = createProduct(rs);
                equips.add(equipment);
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return new EquipCatalogue(equips);
    }
    private Equipment createProduct(ResultSet rs) throws SQLException {
        String type = rs.getString(TYPE_EQUIP);
        String size = rs.getString(SIZE);
        Integer price = rs.getInt(PRICE);
        Integer avail = rs.getInt(AVAIL);
        String idMan = rs.getString(MANAGER);
        return new Equipment(type,size,avail,price);

    }
    public Equipment loadEquipByID (Integer id) {
        Equipment equipment = null;
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement();
        ResultSet rs = EquipQuery.loadEquipByID(stmt, id)){
            if(rs.next()){
                equipment = createProduct(rs);
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return equipment;
    }
    public List<Equipment> getEquipInfo(){
        Connection con = MyConnectionSingleton.getConnection();
        List<Equipment> equips = new ArrayList<>();
        try(Statement stmt = con.createStatement();
            ResultSet rs = EquipQuery.loadEquip(stmt)){
            while (rs. next()){
                Equipment newEquip = new Equipment();
                newEquip.setEquipID(rs.getInt(1));
                newEquip.setEquipType(rs.getString(2));
                newEquip.setSize(rs.getString(3));
                newEquip.setAvail(rs.getInt(4));
                newEquip.setPrice(rs.getInt(5));
                equips.add(newEquip);
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return equips;
    }
    public List<Equipment> getEquipInfoForCart(int i){
        Connection con = MyConnectionSingleton.getConnection();
        List<Equipment> equips = new ArrayList<>();
        try(Statement stmt = con.createStatement();
            ResultSet rs = EquipQuery.loadEquipByOrder(stmt, i)){
            while (rs. next()){
                Equipment newEquip = new Equipment();
                newEquip.setEquipType(rs.getString(1));
                newEquip.setSize(rs.getString(2));
                newEquip.setPrice(rs.getInt(3));
                equips.add(newEquip);
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return equips;
    }
    public List<Equipment> getCart(String email){
        Connection con = MyConnectionSingleton.getConnection();
        List<Equipment> equips = new ArrayList<>();
        try(Statement stmt = con.createStatement();
            ResultSet rs = EquipQuery.loadEquipCart(stmt, email)){
            while (rs. next()){
                Equipment newEquip = new Equipment();
                newEquip.setEquipType(rs.getString(1));
                newEquip.setSize(rs.getString(2));
                newEquip.setPrice(rs.getInt(3));
                newEquip.setAvail(rs.getInt(4));
                equips.add(newEquip);
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return equips;
    }
    public void deleteItemsFromCart(String email){
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement();){
            EquipQuery.deleteItem(stmt, email);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    public EquipCatalogue loadAllEquipByManager(String license){
        ArrayList<Equipment> equipments = new ArrayList<>();
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement();
        ResultSet rs = EquipQuery.loadEquipByManager(stmt, license)){
            while (rs.next()){
                Equipment locEquip = createProduct(rs);
                equipments.add(locEquip);
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return new EquipCatalogue(equipments);
    }
}
