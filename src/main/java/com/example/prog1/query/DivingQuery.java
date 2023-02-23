package com.example.prog1.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DivingQuery implements Query{
    public static ResultSet selectDivingName(Statement stmt, String divingMan) throws SQLException {
        String query = String.format("SELECT name FROM Diving WHERE divingManager = '%s';", divingMan);
        return stmt.executeQuery(query);
    }
    public static boolean insertDiving(Statement stmt,  String name, String loc, String tel, String email) throws SQLException {
        String query = String.format("INSERT INTO Diving values ('%s', '%s', '%s', '%s');",name, loc, tel, email);
        return stmt.execute(query);
    }
    public static ResultSet loadDiv(Statement stmt) throws SQLException {
        String query = String.format("SELECT name, location, telephone FROM Diving;");
        return stmt.executeQuery(query);
    }
    public static ResultSet loadDivMan(Statement stmt, String email) throws SQLException {
        String query = String.format("SELECT name, location, telephone FROM Diving WHERE divingManager = '%s';", email);
        return stmt.executeQuery(query);
    }
}
