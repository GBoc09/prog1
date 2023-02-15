package com.example.prog1.dao;

import com.example.prog1.dbConnection.MyConnectionSingleton;
import com.example.prog1.model.Diving;
import com.example.prog1.model.Manager;
import com.example.prog1.query.DivingQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DivingDAO {
    /* NOMI COLONNE SQL */
    private static final String DIVING_NAME = "name";
    private static final String DIVING_LOCATION = "location";
    private static final String DIVING_TEL = "telephone";

    private String managerLicense;

    public String getManagerLicense() {
        return managerLicense;
    }

    public void setManagerLicense(String managerLicense) {
        this.managerLicense = managerLicense;
    }

    public Diving loadDivingByManLic(String license){
        Connection con = MyConnectionSingleton.getConnection();
        Diving diving = null;
        try(Statement stmt = con.createStatement();
        ResultSet rs = DivingQuery.selectDiving(stmt, license);){
            while (rs.next()){
                diving = createDiving(rs);
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return diving;
    }
    public Diving createDiving(ResultSet rs) throws SQLException {
        String name = rs.getString(DIVING_NAME);
        String location = rs.getString(DIVING_LOCATION);
        String tel = rs.getString(DIVING_TEL);
        ManagerDAO managerDAO = new ManagerDAO();
        Manager man = managerDAO.loadManager(managerLicense);
        String manager = man.getLicense();
        return new Diving(name,location,tel,manager);
    }
}
