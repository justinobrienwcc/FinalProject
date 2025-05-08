package com.example.finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// DBUtil class just provides a method for obtaining a connection to
// the db
public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/CarRentalDb"; // Change this to your database URL
    private static final String USER = "root"; // Change this to your database username
    private static final String PASSWORD = "Jobrien98!"; // Change this to your database password

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException {
        try {
            // Make sure the JDBC driver is loaded
            Class.forName("com.mysql.cj.jdbc.Driver"); // Make sure the MySQL driver is in your classpath
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Unable to connect to the database", e);
        }
    }
}
