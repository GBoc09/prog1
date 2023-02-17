package com.example.prog1.dao;

import com.example.prog1.bean.CartBean;
import com.example.prog1.dbConnection.MyConnectionSingleton;
import com.example.prog1.query.RentalQuery;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RentalDAO {
    public void insertRent(CartBean type, CartBean price, String email ){
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement()){
            RentalQuery.insertIntoRent(stmt, type.getType(), price.getPrice(), email);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

}
