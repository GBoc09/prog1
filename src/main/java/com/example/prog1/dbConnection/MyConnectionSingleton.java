package com.example.prog1.dbConnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyConnectionSingleton {
    private  MyConnectionSingleton (){}
    private static Connection con;

    static {
        // Does not work if generating a jar file
        try (InputStream input = new FileInputStream("src/main/resources/com/example/prog1/config.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            String connectionUrl = properties.getProperty("CONNECTION_URL");
            String user = properties.getProperty("USER");
            String pass = properties.getProperty("PASS");

            con = DriverManager.getConnection(connectionUrl, user, pass);
        } catch (SQLException | IOException e) {
            Logger.getAnonymousLogger().log(Level.INFO, e.getMessage());
        }
    }

    public static Connection getConnection() {
        return con;
    }

}

