package com.example.prog1.DAO;

import com.example.prog1.bean.EquipBean;
import com.example.prog1.dbConnection.MyConnectionSingleton;
import com.example.prog1.exception.DuplicatedUserException;
import com.example.prog1.exception.InvalidInsertionEquipException;
import com.example.prog1.model.CartRow;
import com.example.prog1.model.Equipment;
import com.example.prog1.query.RentalQuery;
import com.example.prog1.query.UserQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    private static final String EQUIP_TYPE_CART = "equipType";
    private static final String EQUIP_SIZE_CART = "equipSize";
    private static final String EQUIP_PRICE_CART = "equipPrice";
    private static final String EQUIP_Q_CART = "equipQuantity";

    /**
     * data truncated for column equipPrice */
    public void insertIntoCart(EquipBean equipBean, int quant)  {
        Connection con = MyConnectionSingleton.getConnection();
        try (Statement stmt = con.createStatement()) {
            RentalQuery.insertIntoCart(stmt, equipBean.getType(), equipBean.getSize(), equipBean.getPrice(), quant);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

//    private static final String EQUIP_TYPE = "equipID";
//    private static final String AVAIL = "quantity";
//
//    public void saveCart(List<CartRow> cartRowList, Integer rentalKey){
//        Connection con = MyConnectionSingleton.getConnection();
//        String insert = "INSERT INTO EquipRental(equipID, rentID, quantity) VALUES (?,?,?);";
//        try(PreparedStatement stmt = con.prepareStatement(insert);){
//            for (CartRow cartRow : cartRowList){
//                stmt.setInt(1,cartRow.getEquipId());
//                stmt.setInt(2,rentalKey);
//                stmt.setInt(3,cartRow.getQuantity());
//
//                stmt.executeUpdate();
//            }
//        }catch (SQLException sqlException){
//            sqlException.printStackTrace();
//        }
//    }
//    /* rental = divingID  */
//    public List<CartRow> loadCartByOrderCodeAndVendor(Integer orderCode, String license){
//        Connection con = MyConnectionSingleton.getConnection();
//        List<CartRow> cartRowList = new ArrayList<>();
//        try(Statement stmt = con.createStatement();
//            ResultSet rs = RentalQuery.selectCartByRentalId(stmt, orderCode);) {
//            EquipDAO equipDAO = new EquipDAO();
//            while (rs.next()) {
//                Integer itemId = rs.getInt(EQUIP_TYPE);
//                Integer quantity = rs.getInt(AVAIL);
//
//                Equipment equipment = equipDAO.loadEquipByID(itemId);
//                if(equipment.getManLicense().equals(license)){
//                    CartRow cartRow = new CartRow(equipment, quantity);
//                    cartRowList.add(cartRow);
//                }
//            }
//        }catch (SQLException sqlException){
//            sqlException.printStackTrace();
//        }
//        return cartRowList;
//    }
}
