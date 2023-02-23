package com.example.prog1.dao;

import com.example.prog1.bean.CartBean;
import com.example.prog1.db.MyConnectionSingleton;
import com.example.prog1.query.RentalQuery;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RentalDAO {
    public void insertRent(CartBean type, CartBean price, String email ){
        Connection con = MyConnectionSingleton.getConnection();
        System.out.println("rental dao: "+type.getType()+" "+price.getPrice());
        try(Statement stmt = con.createStatement()){
            RentalQuery.insertIntoRent(stmt, type.getType(), price.getPrice(), email);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
