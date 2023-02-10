package com.example.prog1.DAO;

import com.example.prog1.dbConnection.MyConnectionSingleton;
import com.example.prog1.exception.DuplicatedUserException;
import com.example.prog1.model.User;
import com.example.prog1.query.UserQuery;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class ScubaDAO {
    private static final String SCUBA = "scuba";
    private static final String SCUBA_EMAIL = "emailUser";
    private static final String SCUBA_NAME = "name";
    private static final String SCUBA_SURNAME = "lastname";
    private static final String SCUBA_LICENSE = "license";

    MyConnectionSingleton connection = MyConnectionSingleton.getInstance();

    public void insertScuba (User scuba) throws DuplicatedUserException {
        Connection con =connection.getConnection();
        try(Statement stmt = con.createStatement())
        {
            UserQuery.insertIntoUser(stmt, scuba.getEmail(), scuba.getPass(), SCUBA);
            UserQuery.insertIntoScuba(stmt, scuba.getLicense(), scuba.getName(), scuba.getLastname(), scuba.getEmail());
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedUserException("User already registered");
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }
}
