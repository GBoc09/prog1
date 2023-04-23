package com.example.prog1.dao;

import com.example.prog1.db.MyConnectionSingleton;
import com.example.prog1.exception.SqlException;
import com.example.prog1.model.Rental;
import com.example.prog1.query.RentalQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RentalDAO {
    Logger logger = Logger.getLogger(RentalDAO.class.getName());
    public void insertRent(String type, String email, String name, Integer price) throws SqlException {
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement()){
            RentalQuery.insertIntoRent(stmt,type,email, name, price);
        }catch (SQLException sqlException){
            logger.log(Level.INFO, "SQLException Error");
        }
    }
    public void insertRentPart(Integer total) throws SqlException {
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement()){
            RentalQuery.insertIntoRentPart(stmt, total);
        }catch (SQLException sqlException){
            logger.log(Level.INFO, "SQLException Error");
        }
    }
    public List<Rental> getRentInfo (String email) throws SqlException {
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
            logger.log(Level.INFO, "SQLException Error");
    }
        return rents;
    }
    public void deleteItemsFromRental(String email) throws SqlException {
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement();){
           RentalQuery.deleteItem(stmt, email);
        } catch (SQLException sqlException){
            logger.log(Level.INFO, "SQLException Error");
        }
    }
}
