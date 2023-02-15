package com.example.prog1.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DivingQuery {
    public static ResultSet selectDiving(Statement stmt, String divingMan) throws SQLException {
        String query = String.format("SELECT * FROM Diving WHERE divingManager = '%s';", divingMan);
        return stmt.executeQuery(query);
    }
    public static ResultSet selectDivingName(Statement stmt, String divingMan) throws SQLException {
        String query = String.format("SELECT name FROM Diving WHERE divingManager = '%s';", divingMan);
        return stmt.executeQuery(query);
    }
}
