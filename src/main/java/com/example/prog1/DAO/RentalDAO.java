package com.example.prog1.DAO;

import com.example.prog1.dbConnection.MyConnectionSingleton;
import com.example.prog1.model.Rental;

import java.sql.*;

public class RentalDAO {
    private static final String ID_RENT = "idRental";
    private static final String ID_EQUIP = "idEquip";
    private static final String NOLEGGIATORE = "scuba";
    private static final String ID_DIVING = "diving";
    private static final String TOTALE = "total";

//    public void saveRent(Rental rent){
//        Connection con = MyConnectionSingleton.getConnection();
//        try(PreparedStatement stmt = con.prepareStatement("INSERT INTO Rental (scuba, total) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);){
//            stmt.setString(1,rent.getOwnerEmail());
//            stmt.setDouble(2,rent.getTotal());
//            stmt.executeUpdate();
//            try(ResultSet generatedKeys = stmt.getGeneratedKeys();){
//                if (generatedKeys.next()) {
//                    Integer rentKey = generatedKeys.getInt(1);
//                    rent.setIdRent(rentKey);
//                }
//            }
//            CartDAO cartDAO = new CartDAO();
//            cartDAO.saveCart(rent.getCartRows(), rent.getIdRent());
//        }catch(SQLException sqlException){
//            sqlException.printStackTrace();
//        }
//    }
}
