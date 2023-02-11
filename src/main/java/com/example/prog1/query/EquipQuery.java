package com.example.prog1.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EquipQuery {
    public static ResultSet loadAllProducts(Statement stmt) throws SQLException {
        String query = String.format("SELECT * FROM Equipment;");
        return stmt.executeQuery(query);
    }
    public static ResultSet loadEquipByID(Statement stmt, Integer id) throws SQLException {
        String query = String.format("SELECT * FROM Equipment WHERE idEquipment = '%d';", id);
        return stmt.executeQuery(query);
    }
}
