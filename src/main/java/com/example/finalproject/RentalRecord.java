package com.example.finalproject;

import java.time.LocalDate;

public class RentalRecord {
    private int rentalId;
    private int customerId;
    private int carId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;

    // Constructor
    public RentalRecord(int rentalId, int customerId, int carId, LocalDate startDate, LocalDate endDate, double totalCost) {
        this.rentalId = rentalId;
        this.customerId = customerId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
    }

    // Getters and Setters
    public int getRentalId() { return rentalId; }
    public void setRentalId(int rentalId) { this.rentalId = rentalId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getCarId() { return carId; }
    public void setCarId(int carId) { this.carId = carId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
}

//
//Here's the SQL CREATE TABLE statement for your RentalRecord class, including foreign key references to the Customer and Car tables:
//
//sql
//Copy code
//CREATE TABLE RentalRecord (
//        rentalId INT PRIMARY KEY AUTO_INCREMENT,
//        customerId INT,
//        carId INT,
//        startDate DATE,
//        endDate DATE,
//        totalCost DECIMAL(10, 2),
//FOREIGN KEY (customerId) REFERENCES Customer(customerId),
//FOREIGN KEY (carId) REFERENCES Car(carId)
//        );
//Explanation:
//rentalId INT PRIMARY KEY: Unique ID for each rental record.
//
//customerId INT: Foreign key linking to Customer.
//
//carId INT: Foreign key linking to Car.
//
//startDate and endDate DATE: Use SQL DATE to match Java's LocalDate.
//
//totalCost DECIMAL(10, 2): Same as with pricePerDay, handles currency.
//
//Foreign keys ensure referential integrity — e.g., can't rent a car for a non-existent customer.
//
//If you plan to auto-generate rental IDs or want to handle cascading deletes/updates, we can tweak that too. Want help setting up all three with some sample data or Java JDBC integration?