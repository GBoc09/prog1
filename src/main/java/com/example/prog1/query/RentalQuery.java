package com.example.prog1.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RentalQuery {
    public static ResultSet selectCartByRentalId(Statement stmt, Integer rentalID) throws SQLException {
        String query = String.format("SELECT * FROM Rental WHERE idRental = %d;", rentalID);
        return stmt.executeQuery(query);
    }
}
