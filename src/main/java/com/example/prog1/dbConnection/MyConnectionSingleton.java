package com.example.prog1.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnectionSingleton {
    private static MyConnectionSingleton instance = null;
    private Connection con;
    private static String nomeDB = "prog1";
    private String portaDB = "3306";
    private static String usernameDB = "root";
    private static String passwordDB = "root";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private MyConnectionSingleton() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection("jdbc:mysql://LocalHost:" + portaDB + "/" + nomeDB, usernameDB, passwordDB);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static MyConnectionSingleton getInstance() {
        if (instance == null){
            instance = new MyConnectionSingleton();
        }
        return instance;
    }
    public Connection getConnection () {
        return con;
    }

}

