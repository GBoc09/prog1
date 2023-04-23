package com.example.prog1.dao;

import com.example.prog1.db.MyConnectionSingleton;
import com.example.prog1.exception.DuplicatedUserException;
import com.example.prog1.exception.NotExistentUserException;
import com.example.prog1.model.Scuba;
import com.example.prog1.model.User;
import com.example.prog1.query.UserQuery;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScubaDAO {
    Logger logger = Logger.getLogger(ScubaDAO.class.getName());
    private static final String SCUBA = "scuba";
    /* SQL column */
    private static final String SCUBA_EMAIL = "email";
    private static final String SCUBA_NAME = "name";
    private static final String SCUBA_SURNAME = "lastname";
    private static final String SCUBA_LICENSE = "license";

    public void insertScuba (User scuba) throws DuplicatedUserException {
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement())
        {
            UserQuery.insertIntoUser(stmt, scuba.getEmail(), scuba.getPass(), SCUBA);
            UserQuery.insertIntoScuba(stmt, scuba.getLicense(), scuba.getName(), scuba.getLastname(), scuba.getEmail());
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedUserException("User already registered");
        }
        catch (SQLException sqlException){
            logger.log(Level.INFO, "SQLException Error");
        }
    }
    public Scuba loadScubaByEmail(String email) throws NotExistentUserException {
        Scuba scuba = takeInfo(email);
        if(scuba == null){
            throw new NotExistentUserException("User not found");
        }
        return scuba;
    }
    public Scuba takeInfo(String email) {
        Scuba scuba = null;
        Connection con = MyConnectionSingleton.getConnection();
        try(Statement stmt = con.createStatement();
            ResultSet rs = UserQuery.selectScubaByEmail(stmt, email)){
            while (rs.next()){
                scuba = createScuba(rs);
            }
        }catch (SQLException sqlException){
            logger.log(Level.INFO, "SQLException Error");
        }
        return scuba;
    }
    private Scuba createScuba(ResultSet resultSet) throws SQLException {
        String license = resultSet.getString(SCUBA_LICENSE);
        String name = resultSet.getString(SCUBA_NAME);
        String surname = resultSet.getString(SCUBA_SURNAME);
        String email = resultSet.getString(SCUBA_EMAIL);
        return new Scuba(email,name,surname,license);
    }
}
