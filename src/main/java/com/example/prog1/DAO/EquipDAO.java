package com.example.prog1.DAO;

import com.example.prog1.catalogue.EquipCatalogue;
import com.example.prog1.dbConnection.MyConnectionSingleton;
import com.example.prog1.model.Equipment;
import com.example.prog1.model.Manager;
import com.example.prog1.query.EquipQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EquipDAO {
    private static final String ID_EQUIP = "id";
    private static final String TYPE_EQUIP = "type";
    private static final String PRICE = "price";
    private static final String DIVING = "diving";
    private static final String SIZE = "size";
    private static final String AVAIL = "availability";


    MyConnectionSingleton connection = MyConnectionSingleton.getInstance();
    public EquipCatalogue loadAllProducts(){
        ArrayList<Equipment> equips = new ArrayList<>();
        Connection con = connection.getConnection();
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
        String diving = rs.getString(DIVING);
        ManagerDAO managerDAO = new ManagerDAO();
        Manager rental = managerDAO.loadManager()
    }
    /**
     * rental è il manager o il diving ?
     * devo controllare bene e ragionare
     * in questo caso è conveniente che sia il diving, controllare nei casi precedenti
     * dovrebbe essere conveniente avere sempre il riferimento al diving e da li posso arrivare
     * comunque al manager con una join */
}
