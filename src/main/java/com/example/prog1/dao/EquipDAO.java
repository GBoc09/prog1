package com.example.prog1.dao;

import com.example.prog1.bean.CominicationBean;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.db.MyConnectionSingleton;
import com.example.prog1.model.Equipment;
import com.example.prog1.query.DivingQuery;
import com.example.prog1.query.EquipQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipDAO {
    /* SQL column*/
    private static final String TYPE_EQUIP = "equipType";
    private static final String PRICE = "price";
    private static final String SIZE = "size";
    private static final String AVAIL = "availability";

    /** inserimento diretto per tipo prezzo taglia e diponibilit√†
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
    public List<Equipment> getEquipInfoManEDiv(UserBean userBean, CominicationBean cominicationBean){
        Connection con = MyConnectionSingleton.getConnection();
        String user = userBean.getUserEmail();
        String diving = cominicationBean.getStr();
        List<Equipment> equips = new ArrayList<>();
        try(Statement stmt = con.createStatement();
            ResultSet rs = EquipQuery.loadEquipDivingManager(stmt, diving, user)){
            while (rs. next()){
                Equipment newEquip = new Equipment();
                newEquip.setEquipType(rs.getString(1));
                newEquip.setSize(rs.getString(2));
                newEquip.setAvail(rs.getInt(3));
                newEquip.setPrice(rs.getInt(4));
                equips.add(newEquip);
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return equips;
    }
    public List<Equipment> getEquipInfoName(String name){
        Connection con = MyConnectionSingleton.getConnection();
        List<Equipment> equips = new ArrayList<>();
        try(Statement stmt = con.createStatement();
            ResultSet rs = EquipQuery.loadEquipName(stmt, name)){
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
}
