package com.example.prog1.DAO;

import com.example.prog1.dbConnection.MyConnectionSingleton;
import com.example.prog1.exception.DuplicatedUserException;
import com.example.prog1.model.User;
import com.example.prog1.query.UserQuery;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class ManagerDAO {
    private static final String MANAGER = "manager";
    private static final String MANAGER_EMAIL = "emailUser";
    private static final String MANAGER_NAME = "name";
    private static final String MANAGER_SURNAME = "lastname";
    private static final String MANAGER_LICENSE = "license";

    MyConnectionSingleton connection = MyConnectionSingleton.getInstance();

    public void insertManager(User manager) throws DuplicatedUserException {
        Connection con =connection.getConnection();
        try(Statement stmt = con.createStatement())
        {
            UserQuery.insertIntoUser(stmt, manager.getEmail(), manager.getPass(), MANAGER);
            UserQuery.insertIntoScuba(stmt, manager.getLicense(), manager.getName(), manager.getLastname(), manager.getEmail());
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedUserException("User already registered");
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }
}
