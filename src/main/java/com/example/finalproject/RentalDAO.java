// File: RentalDAO.java
package com.example.finalproject;

import java.sql.*;
import java.time.LocalDate;

public class RentalDAO {

    public static boolean isCarAvailable(int carId, LocalDate start, LocalDate end) throws SQLException {
        String sql = "SELECT COUNT(*) FROM RentalRecord WHERE carId = ? AND NOT (endDate < ? OR startDate > ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carId);
            stmt.setDate(2, java.sql.Date.valueOf(start));
            stmt.setDate(3, java.sql.Date.valueOf(end));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        }
        return false;
    }

    public static boolean createRental(int customerId, int carId, LocalDate start, LocalDate end, double totalCost) throws SQLException {
        if (!isCarAvailable(carId, start, end)) return false;

        String sql = "INSERT INTO RentalRecord (customerId, carId, startDate, endDate, totalCost) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.setInt(2, carId);
            stmt.setDate(3, java.sql.Date.valueOf(start));
            stmt.setDate(4, java.sql.Date.valueOf(end));
            stmt.setDouble(5, totalCost);
            stmt.executeUpdate();
            return true;
        }
    }
}
