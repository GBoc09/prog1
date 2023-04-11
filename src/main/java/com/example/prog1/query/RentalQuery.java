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
    public static boolean insertIntoCartCLI (Statement stmt, String type, String size, Integer price, Integer quant, String email) throws SQLException {
        String query = String.format("INSERT INTO Cart (equipType, equipSize, equipPrice, equipQuantity, scuba)values ('%s', '%s', '%d', '%d', '%s');", type, size, price, quant, email);
        return stmt.execute(query);
    }
    public static boolean insertIntoRent (Statement stmt, String type,String email, String name, Integer price) throws SQLException {
        String query = String.format("INSERT INTO Rental (equipType, scuba, diving, equipPrice)values ('%s', '%s','%s','%d');", type,email,name,price);
        return stmt.execute(query);
    }
    public static boolean insertIntoRentPart (Statement stmt, Integer total) throws SQLException {
        String query = String.format("INSERT INTO Rental (total)value ('%d');", total);
        return stmt.execute(query);
    }
    public static ResultSet loadRent(Statement stmt, String email ) throws SQLException {
        String query = String.format("SELECT idRental ,equipType, diving, total FROM Rental WHERE scuba = '%s';", email);
        return stmt.executeQuery(query);
    }
    public static boolean deleteItem(Statement stmt, String email) throws SQLException {
        String query = String.format("DELETE FROM Rental WHERE scuba = '%s';", email);
        return stmt.execute(query);
    }
}
