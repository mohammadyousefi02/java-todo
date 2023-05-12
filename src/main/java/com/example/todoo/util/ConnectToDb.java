package com.example.todoo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDb {
    private static Connection connection;
    private static String DB_URL = "jdbc:postgresql://luca.iran.liara.ir:33795/postgres";
    private static String NAME = "root";
    private static String PASSWORD = "tV7OztdGGNQOtOk53vpMsz8K";

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        try {
            connection = DriverManager.getConnection(DB_URL, NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
