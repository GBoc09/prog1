package com.example.prog1.DAO;

import com.example.prog1.dbConnection.MyConnectionSingleton;
import com.example.prog1.exception.DuplicatedUserException;
import com.example.prog1.model.User;
import com.example.prog1.query.UserQuery;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class FreeDAO {
    private static final String FREE = "free";
    private static final String FREE_EMAIL = "emailUser";
    private static final String FREE_NAME = "name";
    private static final String FREE_SURNAME = "lastname";
    private static final String FREE_LICENSE = "license";

    MyConnectionSingleton connection = MyConnectionSingleton.getInstance();

    public void insertFree (User free) throws DuplicatedUserException {
        Connection con =connection.getConnection();
        try(Statement stmt = con.createStatement())
        {
            UserQuery.insertIntoUser(stmt, free.getEmail(), free.getPass(), FREE);
            UserQuery.insertIntoFree(stmt, free.getLicense(), free.getName(), free.getLastname(), free.getEmail());
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedUserException("User already registered");
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }
}
