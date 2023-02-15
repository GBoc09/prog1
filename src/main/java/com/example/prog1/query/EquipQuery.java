package com.example.prog1.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EquipQuery {
    public static boolean insertEquip (Statement stmt, String type, String size, Integer avail, Integer price, String divingName, String manEmail) throws SQLException {
        String query = String.format("INSERT INTO Equipment (EquipType, size, availability, price, divingName, manager)values ('%s', '%s', '%d', '%d', '%s','%s');", type, size, avail, price, divingName,manEmail);
        return stmt.execute(query);
    }
    public static boolean insertDivingName(Statement stmt, String name) throws SQLException {
        String query = String.format("INSERT INTO Equipment(divingName)value('%s');", name);
        return stmt.execute(query);
    }
    public static boolean deleteItem(Statement stmt, String email) throws SQLException {
        String query = String.format("DELETE FROM Cart WHERE scuba = '%s';", email);
        return stmt.execute(query);
    }
    public static ResultSet loadAllProducts(Statement stmt) throws SQLException {
        String query = String.format("SELECT * FROM Equipment;");
        return stmt.executeQuery(query);
    }
    public static ResultSet loadEquipByID(Statement stmt, Integer id) throws SQLException {
        String query = String.format("SELECT * FROM Equipment WHERE idEquipment = '%d';", id);
        return stmt.executeQuery(query);
    }
    public static ResultSet loadEquip(Statement stmt) throws SQLException {
        String query = String.format("SELECT idEquipment, equipType, size, availability, price FROM Equipment;");
        return stmt.executeQuery(query);
    }
    public static ResultSet loadEquipByManager(Statement stmt, String license) throws SQLException {
        String query = String.format("SELECT idEquipment, equipType, size, availability, price, manager FROM Equipment manager = '%s';", license);
        return stmt.executeQuery(query);
    }
    public static ResultSet loadEquipByOrder(Statement stmt, int index) throws SQLException {
        String query = String.format("SELECT equipType, size, price FROM Equipment WHERE idEquipment = '%d' ORDER BY equipType;", index);
        return stmt.executeQuery(query);
    }
    public static ResultSet loadEquipCart(Statement stmt, String mail ) throws SQLException {
        String query = String.format("SELECT equipType, equipSize, equipPrice, equipQuantity FROM Cart WHERE scuba = '%s';", mail);
        return stmt.executeQuery(query);
    }
    public static ResultSet loadAvail(Statement stmt, int index) throws SQLException {
        String query = String.format("SELECT availability FROM Equipment WHERE idEquipment = '%d';", index);
        return stmt.executeQuery(query);
    }
}
