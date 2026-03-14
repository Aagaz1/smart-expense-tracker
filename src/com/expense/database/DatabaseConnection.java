package com.expense.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/expense_tracker";
    private static final String USER = "root";
    private static final String PASSWORD = "root123";

    public static Connection getConnection() {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return conn;
    }
}