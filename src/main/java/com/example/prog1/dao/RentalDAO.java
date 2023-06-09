package com.example.prog1.dao;

import com.example.prog1.db.MyConnectionSingleton;
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
    private String error = "SQLException Error";
    public void insertRent(String type, String email, String name, Integer price, Integer total) {
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement()){
            RentalQuery.insertIntoRent(stmt,type,email, name, price,total );
        }catch (SQLException sqlException){
            logger.log(Level.INFO, error);
        }
    }
    public Integer idRent() {
        Connection con = MyConnectionSingleton.getConnection();
        Integer id = null;
        try(Statement stmt = con.createStatement();
            ResultSet rs = RentalQuery.loadIdRent(stmt)){
            while(rs.next()) {
                id = rs.getInt(1);
            }
        }catch (SQLException sqlException){
            logger.log(Level.INFO, error);
        }
        return id;
    }
    public List<Rental> getRentInfo (String email, Integer id) {
        Connection con = MyConnectionSingleton.getConnection();
        List<Rental> rents = new ArrayList<>();
        try(Statement stmt = con.createStatement();
            ResultSet rs = RentalQuery.loadRent(stmt, id, email)){
            while (rs. next()){
                Rental newEquip = new Rental();
                newEquip.setIdRent(rs.getInt(1));
                newEquip.setEquipType(rs.getString(2));
                newEquip.setDiving(rs.getString(3));
                newEquip.setTotal(rs.getInt(4));
                rents.add(newEquip);
            }
        }catch (SQLException sqlException){
            logger.log(Level.INFO, error);
    }
        return rents;
    }
    public Integer getMaxIdRent(){
        Connection con = MyConnectionSingleton.getConnection();
        Integer id = 0;
        try(Statement stmt = con.createStatement();
            ResultSet rs = RentalQuery.loadMaxRent(stmt)){
            if (rs.next()){
                id = rs.getInt(1);
            }
        }catch (SQLException sqlException){
            logger.log(Level.INFO, error);
        }
        return id;
    }
    public void deleteItemsFromRental(String email) {
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement();){
           RentalQuery.deleteItem(stmt, email);
        } catch (SQLException sqlException){
            logger.log(Level.INFO, error);
        }
    }
    public String divingEmail(String diving) {
        Connection con = MyConnectionSingleton.getConnection();
        String divEmail = null;
        try(Statement stmt = con.createStatement();
        ResultSet rs = RentalQuery.selectDivingEmail(stmt, diving)){
            while(rs.next()) {
                divEmail = rs.getString(1);
            }
        }catch (SQLException sqlException){
            logger.log(Level.INFO, error);
        }
        return divEmail;
    }

    public String buyerInfo(String divingMan){
        Connection con = MyConnectionSingleton.getConnection();
        String email = null;
        try(Statement stmt = con.createStatement();
            ResultSet rs = RentalQuery.selectBuyerEmail(stmt, divingMan)){
            while(rs.next()) {
                email = rs.getString(1);
            }
        }catch (SQLException sqlException){
            logger.log(Level.INFO, error);
        }
        return email;
    }
}
