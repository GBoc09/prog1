package com.example.prog1.dao;

import com.example.prog1.db.MyConnectionSingleton;
import com.example.prog1.exception.NotExistentUserException;
import com.example.prog1.exception.SqlException;
import com.example.prog1.query.UserQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
    private String error_msg = "SQL ERROR";
    private static final String SCUBA_TYPE = "scuba";
    private static final String FREE_TYPE = "free";
    private static final String MANAGER_TYPE = "manager";
    private static final String USER_TYPE = "type";
    private static final Integer SCUBA_CODE = 0;
    private static final Integer FREE_CODE = 1;
    private static final Integer MANAGER_CODE = 2;

    public Integer loadUserByCredentials(String userEmail, String userPass) throws NotExistentUserException, SqlException {
        Connection con = MyConnectionSingleton.getConnection();
        Integer userType = -1;
        try (Statement stmt = con.createStatement();
             ResultSet rs = UserQuery.selectUserByCredentials(stmt, userEmail, userPass))
        {
            if(rs.next()){
                String userEnum = rs.getString(USER_TYPE);
                if(userEnum.compareTo(SCUBA_TYPE) == 0){
                    userType = SCUBA_CODE;
                } else if (userEnum.compareTo(FREE_TYPE) == 0) {
                    userType = FREE_CODE;
                } else if (userEnum.compareTo(MANAGER_TYPE) == 0) {
                    userType = MANAGER_CODE;
                }
                }else {
                throw new NotExistentUserException("Not existent user");
            }
        } catch (SQLException sqlException){
            throw new SqlException(error_msg);
        }
        return userType;
    }

}
