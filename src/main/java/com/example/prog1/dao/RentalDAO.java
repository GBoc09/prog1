package com.example.prog1.dao;

import com.example.prog1.bean.CartBean;
import com.example.prog1.db.MyConnectionSingleton;
import com.example.prog1.model.Rental;
import com.example.prog1.query.RentalQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO {
    public void insertRent(CartBean type, CartBean price, String email ){
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement()){
            RentalQuery.insertIntoRent(stmt, type.getType(), price.getPrice(), email);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    public List<Rental> getRentInfo (String email) {
        Connection con = MyConnectionSingleton.getConnection();
        List<Rental> rents = new ArrayList<>();
        try(Statement stmt = con.createStatement();
            ResultSet rs = RentalQuery.loadRent(stmt, email)){
            while (rs. next()){
                Rental newEquip = new Rental();
                newEquip.setIdRent(rs.getInt(1));
                newEquip.setEquipType(rs.getString(2));
                newEquip.setDiving(rs.getString(3));
                newEquip.setTotal(rs.getInt(4));
                rents.add(newEquip);
            }
        }catch (SQLException sqlException){
        sqlException.printStackTrace();
    }
        return rents;
    }
}
