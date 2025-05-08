package com.example.finalproject;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.List;

public class BookingView extends VBox {

    private final ComboBox<Car> carComboBox = new ComboBox<>();
    private final DatePicker startDatePicker = new DatePicker();
    private final DatePicker endDatePicker = new DatePicker();
    private final Button bookButton = new Button("Book Car");

    public BookingView(int customerId) {
        setSpacing(10);
        setPadding(new Insets(20));

        Label carLabel = new Label("Select Car:");
        Label startDateLabel = new Label("Start Date:");
        Label endDateLabel = new Label("End Date:");

        // Load available cars
        List<Car> cars = CarDAO.getAllAvailableCars(); // Use getAllAvailableCars instead of getAllCars
        carComboBox.getItems().addAll(cars);

        // Show car name in ComboBox
        carComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Car car) {
                return car == null ? "" : car.getMake() + " " + car.getModel();
            }

            @Override
            public Car fromString(String string) {
                return null; // not needed
            }
        });

        bookButton.setOnAction(e -> {
            Car selectedCar = carComboBox.getValue();
            LocalDate start = startDatePicker.getValue();
            LocalDate end = endDatePicker.getValue();

            if (selectedCar == null || start == null || end == null) {
                showAlert(Alert.AlertType.WARNING, "Please fill out all fields.");
                return;
            }

            if (!start.isBefore(end) && !start.equals(end)) {
                showAlert(Alert.AlertType.ERROR, "Start date must be before end date.");
                return;
            }

            double totalCost = selectedCar.getPricePerDay() * (end.toEpochDay() - start.toEpochDay());

            try {
                boolean success = RentalDAO.createRental(customerId, selectedCar.getCarId(), start, end, totalCost); // Use getCarId() instead of getId()
                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Car booked successfully!");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Car is not available for the selected period.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error booking car: " + ex.getMessage());
            }
        });

        getChildren().addAll(carLabel, carComboBox, startDateLabel, startDatePicker, endDateLabel, endDatePicker, bookButton);
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type, msg);
        alert.showAndWait();
    }
}
