package com.example.prog1.dao;

import com.example.prog1.db.MyConnectionSingleton;
import com.example.prog1.exception.DuplicatedUserException;
import com.example.prog1.exception.SqlException;
import com.example.prog1.model.Manager;
import com.example.prog1.model.User;
import com.example.prog1.query.UserQuery;

import java.sql.*;

public class ManagerDAO {
    private String error_msg = "SQL ERROR";
    private static final String MANAGER = "manager";
    /* colonne SQL */
    private static final String MANAGER_EMAIL = "email";
    private static final String MANAGER_NAME = "name";
    private static final String MANAGER_SURNAME = "lastname";
    private static final String MANAGER_LICENSE = "license";

    public void insertManager(User manager) throws DuplicatedUserException, SqlException {
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement())
        {
            UserQuery.insertIntoUser(stmt, manager.getEmail(), manager.getPass(), MANAGER);
            UserQuery.insertIntoManager(stmt, manager.getLicense(), manager.getName(), manager.getLastname(), manager.getEmail());
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedUserException("User already registered");
        }
        catch (SQLException sqlException){
            throw new SqlException(error_msg);
        }
    }
    public Manager loadManager(String manLic) throws SqlException {
        Connection con = MyConnectionSingleton.getConnection();
        Manager manager = null;
        try (Statement stmt = con.createStatement();
             ResultSet rs = UserQuery.selectManagerByLicense(stmt, manLic);){
            if(rs.next()){
                manager = createManager(rs);
            }
        }catch (SQLException sqlException){
            throw new SqlException(error_msg);
        }
        return manager;
    }
    public Manager createManager(ResultSet rs) throws SQLException {
        String license = rs.getString(MANAGER_LICENSE);
        String name = rs.getString(MANAGER_NAME);
        String surname = rs.getString(MANAGER_SURNAME);
        String email = rs.getString(MANAGER_EMAIL);

        return new Manager(email,"",name,surname,license);
    }
    public Manager selectEmailMan() throws SqlException {
        Connection con = MyConnectionSingleton.getConnection();
        Manager emailMan = null;
        try (Statement stmt = con.createStatement();
             ResultSet rs = UserQuery.selectManager(stmt);){
            if (rs.next()){
                emailMan.setEmail(rs.getString(1));
            }
        } catch (SQLException sqlException){
            throw new SqlException(error_msg);
        }
        return emailMan;
    }
}
