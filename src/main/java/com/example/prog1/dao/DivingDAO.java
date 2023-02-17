package com.example.prog1.dao;

import com.example.prog1.bean.DivingBean;
import com.example.prog1.bean.EquipBean;
import com.example.prog1.dbConnection.MyConnectionSingleton;
import com.example.prog1.exception.DuplicatedUserException;
import com.example.prog1.model.Diving;
import com.example.prog1.model.Manager;
import com.example.prog1.query.DivingQuery;
import com.example.prog1.query.EquipQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DivingDAO {
    public void insertDiving(DivingBean divingBean, String manEmail){
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement()){
            DivingQuery.insertDiving(stmt, divingBean.getName(), divingBean.getLocation(), divingBean.getTelephone(), manEmail);

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
