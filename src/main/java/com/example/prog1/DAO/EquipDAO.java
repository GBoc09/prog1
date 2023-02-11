package com.example.prog1.DAO;

import com.example.prog1.catalogue.EquipCatalogue;
import com.example.prog1.dbConnection.MyConnectionSingleton;
import com.example.prog1.model.Diving;
import com.example.prog1.model.Equipment;
import com.example.prog1.query.EquipQuery;
import com.example.prog1.query.RentalQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EquipDAO {
    /* SQL column*/
    private static final String ID_EQUIP = "idEquipment";
    private static final String TYPE_EQUIP = "equipType";
    private static final String PRICE = "price";
    private static final String SIZE = "size";
    private static final String AVAIL = "availability";
    private String managerLicense;

    public String getManagerLicense() {
        return managerLicense;
    }

    public void setManagerLicense(String managerLicense) {
        this.managerLicense = managerLicense;
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
        String avail = rs.getString(AVAIL);
        Double price = rs.getDouble(PRICE);
        DivingDAO divingDAO = new DivingDAO();
        Diving diving = divingDAO.loadDivingByManLic(managerLicense);

        return new Equipment(id, type,size,avail,size,price,diving);
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
}
