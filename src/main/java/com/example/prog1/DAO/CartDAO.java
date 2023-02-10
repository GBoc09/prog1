package com.example.prog1.DAO;

import com.example.prog1.dbConnection.MyConnectionSingleton;
import com.example.prog1.model.CartRow;
import com.example.prog1.query.RentalQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    private static final String EQUIP_TYPE = "equipID";
    private static final String AVAIL = "quantity";
    MyConnectionSingleton connection = MyConnectionSingleton.getInstance();

    public void saveCart(List<CartRow> cartRowList, Integer rentalKey){
        Connection con = connection.getConnection();
        String insert = "INSERT INTO EquipRental(equipID, rentID, quantity) VALUES (?,?,?);";
        try(PreparedStatement stmt = con.prepareStatement(insert);){
            for (CartRow cartRow : cartRowList){
                stmt.setInt(1,cartRow.getEquipId());
                stmt.setInt(2,rentalKey);
                stmt.setInt(3,cartRow.getQuantity());

                stmt.executeUpdate();
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    /* rental = diving manager per identificazione del noleggiatore */
    public List<CartRow> loadCartByOrderCodeAndRental(Integer orderCode, String rental){
        Connection con = connection.getConnection();
        List<CartRow> cartRowList = new ArrayList<>();
        try(Statement stmt = con.createStatement();
            ResultSet rs = RentalQuery.selectCartByRentalId(stmt, orderCode);){

        }
    }
}
