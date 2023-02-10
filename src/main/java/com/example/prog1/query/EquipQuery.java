package com.example.prog1.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EquipQuery {
    public static ResultSet loadAllProducts(Statement stmt) throws SQLException {
        String query = "SELECT * FROM Equipment;";
        return stmt.executeQuery(query);
    }
}
