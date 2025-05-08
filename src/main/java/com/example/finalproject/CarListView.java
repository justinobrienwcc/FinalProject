package com.example.finalproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import java.util.List;

public class CarListView extends VBox {

    public CarListView() {
        // Create the UI components
        Label titleLabel = new Label("List of Available Cars");
        titleLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        // Table to show car details
        TableView<Car> carTable = new TableView<>();

        // Columns for the TableView
        TableColumn<Car, String> makeColumn = new TableColumn<>("Make");
        TableColumn<Car, String> modelColumn = new TableColumn<>("Model");
        TableColumn<Car, Integer> yearColumn = new TableColumn<>("Year");
        TableColumn<Car, String> typeColumn = new TableColumn<>("Type");
        TableColumn<Car, Double> pricePerDayColumn = new TableColumn<>("Price per Day");

        // Add columns to the table
        carTable.getColumns().addAll(makeColumn, modelColumn, yearColumn, typeColumn, pricePerDayColumn);

        // Set cell value factories for each column
        makeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMake()));
        modelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
        yearColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getYear()).asObject());
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        pricePerDayColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPricePerDay()).asObject());

        // Load car data from the database using CarDAO
        List<Car> availableCars = CarDAO.getAllAvailableCars();
        carTable.getItems().addAll(availableCars);

        // Layout for CarListView
        this.setPadding(new Insets(20));
        this.setSpacing(10);
        this.getChildren().addAll(titleLabel, carTable);
    }
}
