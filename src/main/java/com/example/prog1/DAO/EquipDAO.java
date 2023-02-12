package com.example.prog1.DAO;

import com.example.prog1.bean.EquipBean;
import com.example.prog1.catalogue.EquipCatalogue;
import com.example.prog1.dbConnection.MyConnectionSingleton;
import com.example.prog1.model.Diving;
import com.example.prog1.model.Equipment;
import com.example.prog1.model.Manager;
import com.example.prog1.query.EquipQuery;
import com.example.prog1.query.RentalQuery;

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
    private static final String DIVING = "idDiving";
    public boolean insertEquip( Equipment equipment, String license){
        boolean flag = true;
        Connection con = MyConnectionSingleton.getConnection();
        try(PreparedStatement ps = con.prepareStatement("INSERT Equipment(equipType, size, availability, price, manager) VALUES(?,?,?,?,?);"))
        {
            ps.setString(1,equipment.getEquipType());
            ps.setString(2,equipment.getSize());
            ps.setInt(3,equipment.getAvail());
            ps.setDouble(4, equipment.getPrice());
            ps.setString(5,equipment.getManLicense());
            ps.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return flag;
    }
    public EquipBean selectEquipByOrder (int index) {
        EquipBean equipBean= new EquipBean();
        Connection con = MyConnectionSingleton.getConnection();
        try (Statement stmt = con.createStatement();
        ResultSet rs = EquipQuery.loadEquipByOrder(stmt, index);){
            while (rs.next()){
                equipBean.setType(rs.getString(1));
                equipBean.setSize(rs.getString(2));
                equipBean.setPrice(rs.getDouble(3));
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
        Integer id = rs.getInt(ID_EQUIP);
        String type = rs.getString(TYPE_EQUIP);
        String size = rs.getString(SIZE);
        Double price = rs.getDouble(PRICE);
        Integer avail = rs.getInt(AVAIL);
        Integer idDiv = rs.getInt(DIVING);
        String idMan = rs.getString(MANAGER);
        ManagerDAO managerDAO = new ManagerDAO();
        Manager manager = managerDAO.loadManager(idMan);
        return new Equipment(id,type,size,price,avail,manager);

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
                newEquip.setPrice(rs.getDouble(5));
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
                newEquip.setPrice(rs.getDouble(3));
                equips.add(newEquip);
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return equips;
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
