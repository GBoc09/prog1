package com.example.prog1.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RentalQuery{
    private RentalQuery(){}
    public static boolean insertIntoCart (Statement stmt, String type, String size, Integer price, Integer quant, String email) throws SQLException {
        String query = String.format("INSERT INTO Cart (equipType, equipSize, equipPrice, equipQuantity, scuba)values ('%s', '%s', '%d', '%d', '%s');", type, size, price, quant, email);
        return stmt.execute(query);
    }
    public static boolean insertIntoRent (Statement stmt, String type,String email, String name, Integer price, Integer total) throws SQLException {
        String query = String.format("INSERT INTO Rental (equipType, scuba, diving, equipPrice, total)values ('%s', '%s','%s','%d', '%d');", type,email,name,price, total);
        return stmt.execute(query);
    }
    public static ResultSet loadRent(Statement stmt, Integer id, String email) throws SQLException {
        String query = String.format("SELECT idRental ,equipType, diving, total FROM Rental WHERE idRental = '%d' AND scuba = '%s';", id, email);
        return stmt.executeQuery(query);
    }
    public static ResultSet loadRentMan(Statement stmt, Integer id) throws SQLException {
        String query = String.format("SELECT equipType, total FROM Rental WHERE idRental = '%d';", id);
        return stmt.executeQuery(query);
    }
    public static ResultSet loadMaxRent(Statement stmt ) throws SQLException {
        String query = "SELECT MAX(idRental) FROM Rental";
        return stmt.executeQuery(query);
    }
    public static ResultSet loadIdRent(Statement stmt ) throws SQLException {
        String query = "SELECT MAX(idRental) FROM Rental";
        return stmt.executeQuery(query);
    }
    public static boolean deleteItem(Statement stmt, String email) throws SQLException {
        String query = String.format("DELETE FROM Rental WHERE scuba = '%s';", email);
        return stmt.execute(query);
    }
    public static ResultSet selectDivingEmail(Statement stmt, String diving ) throws SQLException {
        String query = String.format("SELECT divingManager FROM diving WHERE name = '%s';", diving);
        return stmt.executeQuery(query);
    }
    public static ResultSet selectBuyerEmail(Statement stmt, String diving ) throws SQLException {
        String query = String.format("SELECT scuba FROM Rental join Diving WHERE divingManager = '%s';", diving);
        return stmt.executeQuery(query);
    }

}
