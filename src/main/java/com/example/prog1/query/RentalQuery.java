package com.example.prog1.query;

import java.sql.SQLException;
import java.sql.Statement;

public class RentalQuery implements Query{
    public static boolean insertIntoCart (Statement stmt, String type, String size, Integer price, Integer quant, String email) throws SQLException {
        String query = String.format("INSERT INTO Cart (equipType, equipSize, equipPrice, equipQuantity, scuba)values ('%s', '%s', '%d', '%d', '%s');", type, size, price, quant, email);
        return stmt.execute(query);
    }
    public static boolean insertIntoRent (Statement stmt, String type, Integer price, String email) throws SQLException {
        String query = String.format("INSERT INTO Rental (equipType, equipPrice, scuba)values ('%s', '%d', '%s');", type, price, email);
        return stmt.execute(query);
    }
}
