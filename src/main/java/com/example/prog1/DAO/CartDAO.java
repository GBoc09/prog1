package com.example.prog1.DAO;

import com.example.prog1.dbConnection.MyConnectionSingleton;
import com.example.prog1.model.CartRow;
import com.example.prog1.model.Equipment;
import com.example.prog1.query.RentalQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    private static final String EQUIP_TYPE = "equipID";
    private static final String AVAIL = "quantity";

    public void saveCart(List<CartRow> cartRowList, Integer rentalKey){
        Connection con = MyConnectionSingleton.getConnection();
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
    /* rental = divingID  */
    public List<CartRow> loadCartByOrderCodeAndRental(Integer orderCode, Integer rental){
        Connection con = MyConnectionSingleton.getConnection();
        List<CartRow> cartRowList = new ArrayList<>();
        try(Statement stmt = con.createStatement();
            ResultSet rs = RentalQuery.selectCartByRentalId(stmt, orderCode);) {
            EquipDAO equipDAO = new EquipDAO();
            while (rs.next()) {
                Integer itemId = rs.getInt(EQUIP_TYPE);
                Integer quantity = rs.getInt(AVAIL);

                Equipment equipment = equipDAO.loadEquipByID(itemId);
                if(equipment.getRentalID().equals(rental)){
                    CartRow cartRow = new CartRow(equipment, quantity);
                    cartRowList.add(cartRow);
                }
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return cartRowList;
    }
}
