package com.example.finalproject;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginView extends VBox {
    private final Stage stage;
    private final CarRentalApplication app;

    public LoginView(Stage stage, CarRentalApplication app) {
        this.stage = stage;
        this.app = app;

        setPadding(new Insets(20));
        setSpacing(10);

        Label titleLabel = new Label("Login");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Query the database to validate the login
            int customerId = validateLogin(username, password);
            if (customerId != 0) {
                app.showCustomerView(stage, customerId);   // Show customer side
            } else {
                showAlert(Alert.AlertType.ERROR, "Invalid username or password.");
            }
        });

        getChildren().addAll(titleLabel, usernameField, passwordField, loginButton);
    }

    // Query the database to validate the login
    private int validateLogin(String username, String password) {
        String query = "SELECT customerId FROM User WHERE username = ? AND password = ?";
        int customerId = 0;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customerId = rs.getInt("customerId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerId;
    }

    // Helper method to show alerts
    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type, msg);
        alert.showAndWait();
    }
}
