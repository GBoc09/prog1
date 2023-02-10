package com.example.prog1.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserQuery {
    public static ResultSet selectUserByCredentials(Statement stmt, String email, String pass) throws SQLException {
        String query = String.format("SELECT * FROM user WHERE email = '%s' AND password = '%s';", email, pass);
        return stmt.executeQuery(query);
    }
    public static boolean insertIntoUser (Statement stmt, String email, String pass, String type) throws SQLException {
        String query = String.format("INSERT INTO User values ('%s', '%s', '%s');", email,pass,type);
        return stmt.execute(query);
    }
    public static boolean insertIntoScuba (Statement stmt, String lic, String name, String surname, String email) throws SQLException {
        String query = String.format("INSERT INTO User values ('%s', '%s', '%s', '%s');", lic, name, surname,email);
        return stmt.execute(query);
    }
    public static boolean insertIntoFree (Statement stmt, String lic, String name, String surname, String email) throws SQLException {
        String query = String.format("INSERT INTO User values ('%s', '%s', '%s', '%s');", lic, name, surname,email);
        return stmt.execute(query);
    }
    public static boolean insertIntoManager (Statement stmt, String lic, String name, String surname, String email) throws SQLException {
        String query = String.format("INSERT INTO User values ('%s', '%s', '%s', '%s');", lic, name, surname,email);
        return stmt.execute(query);
    }

}
