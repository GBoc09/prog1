package com.example.prog1.dao;

import com.example.prog1.bean.EquipBean;
import com.example.prog1.db.MyConnectionSingleton;
import com.example.prog1.query.EquipQuery;
import com.example.prog1.query.RentalQuery;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartDAO {
    Logger logger = Logger.getLogger(CartDAO.class.getName());
    private String error = "SQLException Error";
    public void insertIntoCart(EquipBean equipBean, int quant,String email)  {
        Connection con = MyConnectionSingleton.getConnection();
        try (Statement stmt = con.createStatement()) {
            RentalQuery.insertIntoCart(stmt, equipBean.getType(), equipBean.getSize(), equipBean.getPrice(), quant, email);
        } catch (SQLException e) {
            logger.log(Level.INFO, error);

        }
    }
    public void insertIntoCartCLI(String item, String size, Integer quant,String email)  {
        Integer price  = takePrice(item);
        Connection con = MyConnectionSingleton.getConnection();
        try (Statement stmt = con.createStatement()) {
            RentalQuery.insertIntoCart(stmt, item, size, price, quant, email);
        } catch (SQLException e) {
            logger.log(Level.INFO, error);
        }
    }
    public Integer takePrice(String item)  {
        Connection con = MyConnectionSingleton.getConnection();
        Integer price = 0;
        try(Statement stmt = con.createStatement();
            ResultSet rs = EquipQuery.loadPriceByName(stmt, item)){
            if (rs.next()){
                price = rs.getInt(1);
            }
        }catch (SQLException sqlException){
            logger.log(Level.INFO, error);
        }
        return price;
    }
}
