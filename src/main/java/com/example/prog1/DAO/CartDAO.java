package com.example.prog1.DAO;

import com.example.prog1.bean.EquipBean;
import com.example.prog1.dbConnection.MyConnectionSingleton;
import com.example.prog1.query.RentalQuery;

import java.sql.*;

public class CartDAO {
    public void insertIntoCart(EquipBean equipBean, int quant,String email)  {
        Connection con = MyConnectionSingleton.getConnection();
        try (Statement stmt = con.createStatement()) {
            RentalQuery.insertIntoCart(stmt, equipBean.getType(), equipBean.getSize(), equipBean.getPrice(), quant,email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
