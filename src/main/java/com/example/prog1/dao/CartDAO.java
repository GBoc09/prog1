package com.example.prog1.dao;

import com.example.prog1.bean.EquipBean;
import com.example.prog1.db.MyConnectionSingleton;
import com.example.prog1.query.RentalQuery;

import java.sql.*;

public class CartDAO {
    public void insertIntoCart(EquipBean equipBean, int quant,String email)  {
        Connection con = MyConnectionSingleton.getConnection();
        try (Statement stmt = con.createStatement()) {
            RentalQuery.insertIntoCart(stmt, equipBean.getType(), equipBean.getSize(), equipBean.getPrice(), quant,email);
        } catch (SQLException e) {
           e.printStackTrace();
        }

    }
}
