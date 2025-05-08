package com.example.finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CarRentalApplication extends Application {

    private int currentCustomerId; // ✅ Store the logged-in customer's ID

    @Override
    public void start(Stage stage) {
        showLogin(stage); // Start with login screen
    }

    // ✅ Method to show the login screen
    public void showLogin(Stage stage) {
        LoginView loginView = new LoginView(stage, this);
        stage.setScene(new Scene(loginView, 400, 300));
        stage.setTitle("Login");
        stage.show();
    }

    // ✅ Called when admin logs in
    public void showAdminDashboard(Stage stage) {
        BorderPane adminRoot = new BorderPane();

        Button addCustomerButton = new Button("Add Customer");
        Button viewCarsButton = new Button("View Cars");
        Button logoutButton = new Button("Logout");

        AddCustomerView addCustomerView = new AddCustomerView();
        adminRoot.setCenter(addCustomerView);

        viewCarsButton.setOnAction(e -> adminRoot.setCenter(new CarListView()));
        addCustomerButton.setOnAction(e -> adminRoot.setCenter(addCustomerView));
        logoutButton.setOnAction(e -> showLogin(stage)); // Logout action

        VBox topMenu = new VBox(addCustomerButton, viewCarsButton, logoutButton);
        adminRoot.setTop(topMenu);

        stage.setScene(new Scene(adminRoot, 600, 400));
        stage.setTitle("Admin Dashboard");
    }

    // ✅ Called when customer logs in
    public void showCustomerView(Stage stage, int customerId) {
        this.currentCustomerId = customerId;

        BorderPane customerRoot = new BorderPane();

        CarListView carListView = new CarListView();
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> showLogin(stage));

        Button bookCarButton = new Button("Book a Car");
        bookCarButton.setOnAction(e -> customerRoot.setCenter(new BookingView(currentCustomerId)));

        VBox topMenu = new VBox(logoutButton, bookCarButton);
        customerRoot.setTop(topMenu);
        customerRoot.setCenter(carListView);

        Scene customerScene = new Scene(customerRoot, 600, 400);
        stage.setScene(customerScene);
        stage.setTitle("Customer View");
    }

    public static void main(String[] args) {
        launch();
    }
}
