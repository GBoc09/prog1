package com.example.prog1.dao;

import com.example.prog1.db.MyConnectionSingleton;
import com.example.prog1.exception.DuplicatedUserException;
import com.example.prog1.model.User;
import com.example.prog1.query.UserQuery;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerDAO {
    Logger logger = Logger.getLogger(ManagerDAO.class.getName());
    private String error = "SQLException Error";
    private static final String MANAGER = "manager";
    /* colonne SQL */
    public void insertManager(User manager) throws DuplicatedUserException {
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement())
        {
            UserQuery.insertIntoUser(stmt, manager.getEmail(), manager.getPass(), MANAGER);
            UserQuery.insertIntoManager(stmt, manager.getLicense(), manager.getName(), manager.getLastname(), manager.getEmail());
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedUserException("User already registered");
        }
        catch (SQLException sqlException){
            logger.log(Level.INFO,error);
        }
    }
}
