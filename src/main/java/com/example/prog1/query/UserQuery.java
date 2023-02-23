package com.example.prog1.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserQuery implements Query{
    public static ResultSet selectUserByCredentials(Statement stmt, String email, String pass) throws SQLException {
        String query = String.format("SELECT * FROM user WHERE email = '%s' AND password = '%s';", email, pass);
        return stmt.executeQuery(query);
    }
    public static boolean insertIntoUser (Statement stmt, String email, String pass, String type) throws SQLException {
        String query = String.format("INSERT INTO User values ('%s', '%s', '%s');", email,pass,type);
        return stmt.execute(query);
    }
    public static boolean insertIntoScuba (Statement stmt, String lic, String name, String surname, String email) throws SQLException {
        String query = String.format("INSERT INTO Scuba values ('%s', '%s', '%s', '%s');", lic, name, surname,email);
        return stmt.execute(query);
    }
    public static boolean insertIntoFree (Statement stmt, String lic, String name, String surname, String email) throws SQLException {
        String query = String.format("INSERT INTO Free values ('%s', '%s', '%s', '%s');", lic, name, surname,email);
        return stmt.execute(query);
    }
    public static boolean insertIntoManager (Statement stmt, String lic, String name, String surname, String email) throws SQLException {
        String query = String.format("INSERT INTO Manager values ('%s', '%s', '%s', '%s');", lic, name, surname,email);
        return stmt.execute(query);
    }
    public static ResultSet selectManagerByLicense(Statement stmt, String manLic) throws SQLException {
        String query = String.format("SELECT * FROM Manager WHERE license = '%s';", manLic);
        return stmt.executeQuery(query);
    }
    public static ResultSet selectScubaByEmail(Statement stmt, String email) throws SQLException {
        String query = String.format("SELECT * FROM Scuba WHERE email = '%s';", email);
        return stmt.executeQuery(query);
    }
    public static ResultSet selectManager(Statement stmt) throws SQLException {
        String query = String.format("SELECT email FROM Manager;");
        return stmt.executeQuery(query);
    }

}
