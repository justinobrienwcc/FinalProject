package com.example.finalproject;

public class Car {
    private int carId;
    private String make;
    private String model;
    private int year;
    private String type; // "SUV", "Sedan", etc.
    private double pricePerDay;
    private boolean availability;

    // Constructor
    public Car(int carId, String make, String model, int year, String type, double pricePerDay, boolean availability) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.availability = availability;
    }

    // Getters and Setters
    public int getCarId() { return carId; }
    public void setCarId(int carId) { this.carId = carId; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }

    public boolean isAvailable() { return availability; }
    public void setAvailable(boolean availability) { this.availability = availability; }
}


//Here's the MySQL CREATE TABLE statement for your Car class:
//
//sql
//Copy code
//CREATE TABLE Car (
//        carId INT PRIMARY KEY AUTO_INCREMENT,
//        make VARCHAR(50) NOT NULL,
//model VARCHAR(50) NOT NULL,
//year INT CHECK (year >= 1886), -- First car invented in 1886
//type VARCHAR(30),
//pricePerDay DECIMAL(10, 2),
//availability BOOLEAN
//);
//Breakdown:
//carId INT PRIMARY KEY: Maps to int carId. Unique identifier for each car.
//
//make and model: VARCHAR(50) each; you can adjust based on expected input lengths.
//
//year INT CHECK (year >= 1886): Ensures a realistic year.
//
//type VARCHAR(30): Like "SUV", "Sedan", etc.
//
//        pricePerDay DECIMAL(10, 2): For monetary values (10 digits total, 2 after the decimal).
//
//availability BOOLEAN: Maps to the boolean availability.
//
//Let me know if you want to auto-increment carId or relate this to other tables like Customer (e.g., for rentals).
//
//
//
//
//

