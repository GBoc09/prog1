package com.example.prog1.dao;

import com.example.prog1.db.MyConnectionSingleton;
import com.example.prog1.exception.DuplicatedUserException;
import com.example.prog1.model.User;
import com.example.prog1.query.UserQuery;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScubaDAO {
    Logger logger = Logger.getLogger(ScubaDAO.class.getName());
    private static final String SCUBA = "scuba";
    public void insertScuba (User scuba) throws DuplicatedUserException {
        Connection con = MyConnectionSingleton.getConnection();
        try (Statement stmt = con.createStatement()) {
            UserQuery.insertIntoUser(stmt, scuba.getEmail(), scuba.getPass(), SCUBA);
            UserQuery.insertIntoScuba(stmt, scuba.getLicense(), scuba.getName(), scuba.getLastname(), scuba.getEmail());
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedUserException("User already registered");
        } catch (SQLException sqlException) {
            logger.log(Level.INFO, "SQLException Error");
        }
    }
}
