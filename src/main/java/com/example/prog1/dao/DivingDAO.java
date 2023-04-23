package com.example.prog1.dao;

import com.example.prog1.bean.DivingBean;
import com.example.prog1.bean.UserBean;
import com.example.prog1.db.MyConnectionSingleton;
import com.example.prog1.exception.SqlException;
import com.example.prog1.model.Diving;
import com.example.prog1.query.DivingQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DivingDAO {
    Logger logger = Logger.getLogger(DivingDAO.class.getName());
    public void insertDiving(DivingBean divingBean, String manEmail) throws SqlException {
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement()){
            DivingQuery.insertDiving(stmt, divingBean.getName(), divingBean.getLocation(), divingBean.getTelephone(), manEmail);

        }catch (SQLException sqlException){
            logger.log(Level.INFO, "SQLException Error");
        }
    }
    public List<Diving> getDivInfo() throws SqlException {
        Connection con = MyConnectionSingleton.getConnection();
        List<Diving> divs = new ArrayList<>();
        try(Statement stmt = con.createStatement();
            ResultSet rs = DivingQuery.loadDiv(stmt)){
            while (rs. next()){
                Diving newDiv = new Diving();
                newDiv.setName(rs.getString(1));
                newDiv.setLocation(rs.getString(2));
                newDiv.setTelephone(rs.getString(3));
                divs.add(newDiv);
            }
        }catch (SQLException sqlException){
            logger.log(Level.INFO, "SQLException Error");
        }
        return divs;
    }
    public List<Diving> getDivInfoMan(UserBean userBean) throws SqlException {
        Connection con = MyConnectionSingleton.getConnection();
        List<Diving> divs = new ArrayList<>();
        try(Statement stmt = con.createStatement();
            ResultSet rs = DivingQuery.loadDivMan(stmt, userBean.getUserEmail())){
            while (rs. next()){
                Diving newDiv = new Diving();
                newDiv.setName(rs.getString(1));
                newDiv.setLocation(rs.getString(2));
                newDiv.setTelephone(rs.getString(3));
                divs.add(newDiv);
            }
        }catch (SQLException sqlException){
            logger.log(Level.INFO, "SQLException Error");
        }
        return divs;
    }
}
