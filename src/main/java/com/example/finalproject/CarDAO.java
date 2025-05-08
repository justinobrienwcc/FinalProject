package com.example.finalproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/CarRentalDB";
    private static final String USER = "root"; // Update with your MySQL username if needed
    private static final String PASSWORD = "Jobrien98!"; // Update with your MySQL password if needed

    // Method to get all available cars from the database
    public static List<Car> getAllAvailableCars() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT carId, make, model, year, type, pricePerDay, availability " +
                "FROM Car WHERE availability = TRUE";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int carId = rs.getInt("carId");
                String make = rs.getString("make");
                String model = rs.getString("model");
                int year = rs.getInt("year");
                String type = rs.getString("type");
                double pricePerDay = rs.getDouble("pricePerDay");
                boolean availability = rs.getBoolean("availability");

                // Create a new Car object and add it to the list
                Car car = new Car(carId, make, model, year, type, pricePerDay, availability);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    // Method to get a car by its ID
    public static Car getCarById(int carId) {
        String query = "SELECT carId, make, model, year, type, pricePerDay, availability " +
                "FROM Car WHERE carId = ?";
        Car car = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, carId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String make = rs.getString("make");
                String model = rs.getString("model");
                int year = rs.getInt("year");
                String type = rs.getString("type");
                double pricePerDay = rs.getDouble("pricePerDay");
                boolean availability = rs.getBoolean("availability");

                car = new Car(carId, make, model, year, type, pricePerDay, availability);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    // Method to add a new car to the database
    public static boolean addCar(Car car) {
        String query = "INSERT INTO Car (make, model, year, type, pricePerDay, availability) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, car.getMake());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());
            stmt.setString(4, car.getType());
            stmt.setDouble(5, car.getPricePerDay());
            stmt.setBoolean(6, car.isAvailable());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to update car availability
    public static boolean updateCarAvailability(int carId, boolean availability) {
        String query = "UPDATE Car SET availability = ? WHERE carId = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setBoolean(1, availability);
            stmt.setInt(2, carId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to remove a car from the database
    public static boolean removeCar(int carId) {
        String query = "DELETE FROM Car WHERE carId = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, carId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
