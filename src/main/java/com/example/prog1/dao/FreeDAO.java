package com.example.prog1.dao;

import com.example.prog1.db.MyConnectionSingleton;
import com.example.prog1.exception.DuplicatedUserException;
import com.example.prog1.exception.SqlException;
import com.example.prog1.model.User;
import com.example.prog1.query.UserQuery;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class FreeDAO {
    private static final String FREE = "free";

    public void insertFree (User free) throws DuplicatedUserException, SqlException {
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement())
        {
            UserQuery.insertIntoUser(stmt, free.getEmail(), free.getPass(), FREE);
            UserQuery.insertIntoFree(stmt, free.getLicense(), free.getName(), free.getLastname(), free.getEmail());
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedUserException("User already registered");
        }
        catch (SQLException sqlException){
            throw new SqlException("SQL ERROR");
        }

    }
}
