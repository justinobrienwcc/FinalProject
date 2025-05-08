package com.example.finalproject;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import java.sql.*;

public class AddCustomerView extends VBox {

    public AddCustomerView() {
        // Create the UI components
        Label titleLabel = new Label("Add New Customer");
        titleLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        Label nameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();

        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();

        Label licenseLabel = new Label("License Number:");
        TextField licenseField = new TextField();

        Button addButton = new Button("Add Customer");

        // Set up the button action
        addButton.setOnAction(e -> addCustomerToDatabase(firstNameField.getText(), lastNameField.getText(), phoneField.getText(), licenseField.getText()));

        // Layout for AddCustomerView
        this.setPadding(new Insets(20));
        this.setSpacing(10);
        this.getChildren().addAll(titleLabel, nameLabel, firstNameField, lastNameLabel, lastNameField, phoneLabel, phoneField, licenseLabel, licenseField, addButton);
    }

    // Method to insert customer into the database
    public void addCustomerToDatabase(String firstName, String lastName, String phone, String licenseNumber) {
        String query = "INSERT INTO Customer (firstName, lastName, phoneNumber, licenseNumber) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();  // Using DBUtil to get the connection
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set parameters for the prepared statement
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, phone);
            stmt.setString(4, licenseNumber);

            // Execute the insert query
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // If insertion is successful, print success message
                System.out.println("Customer added successfully!");
            } else {
                // If insertion fails, print error message
                System.out.println("Failed to add customer.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
