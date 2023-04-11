package com.example.prog1.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EquipQuery{
    private EquipQuery(){}
    public static boolean insertEquip (Statement stmt, String type, String size, Integer avail, Integer price, String divingName, String manEmail) throws SQLException {
        String query = String.format("INSERT INTO Equipment (EquipType, size, availability, price, divingName, manager)values ('%s', '%s', '%d', '%d', '%s','%s');", type, size, avail, price, divingName,manEmail);
        return stmt.execute(query);
    }
    public static boolean deleteItem(Statement stmt, String email) throws SQLException {
        String query = String.format("DELETE FROM Cart WHERE scuba = '%s';", email);
        return stmt.execute(query);
    }
    public static ResultSet loadEquip(Statement stmt) throws SQLException {
        String query ="SELECT idEquipment, equipType, size, availability, price FROM Equipment";
        return stmt.executeQuery(query);
    }
    public static ResultSet loadPriceByName(Statement stmt, String item) throws SQLException {
        String query = String.format("SELECT price FROM Equipment WHERE equipType = '%s';", item);
        return stmt.executeQuery(query);
    }
    public static ResultSet loadEquipDivingManager(Statement stmt, String diving,String manager) throws SQLException {
        String query = String.format("SELECT equipType, size, availability, price FROM Equipment WHERE divingName = '%s' AND manager = '%s';",diving, manager);
        return stmt.executeQuery(query);
    }
    public static ResultSet loadEquipName(Statement stmt, String name) throws SQLException {
        String query = String.format("SELECT idEquipment, equipType, size, availability, price FROM Equipment WHERE divingName = '%s';", name);
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
