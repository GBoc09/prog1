package com.example.prog1.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RentalQuery {
    public static ResultSet selectCartByRentalId(Statement stmt, Integer rentalID) throws SQLException {
        String query = String.format("SELECT * FROM Rental WHERE idRental = %d;", rentalID);
        return stmt.executeQuery(query);
    }
    public static boolean insertIntoCart (Statement stmt, String type, String size, Integer price, Integer quant, String email) throws SQLException {
        String query = String.format("INSERT INTO Cart (equipType, equipSize, equipPrice, equipQuantity, scuba)values ('%s', '%s', '%d', '%d', '%s');", type, size, price, quant, email);
        return stmt.execute(query);
    }
}
