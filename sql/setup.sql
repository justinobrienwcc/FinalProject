-- Create and select the database
CREATE DATABASE IF NOT EXISTS CarRentalDB;
USE CarRentalDB;

-- Drop tables if they already exist
DROP TABLE IF EXISTS RentalRecord;
DROP TABLE IF EXISTS Car;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Customer;

-- Create Customer table
CREATE TABLE Customer (
    customerId INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    phoneNumber VARCHAR(20),
    licenseNumber VARCHAR(50)
);

-- Create User table with a foreign key to Customer
CREATE TABLE User (
    userId INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'customer') NOT NULL,
    customerId INT UNIQUE,
    FOREIGN KEY (customerId) REFERENCES Customer(customerId)
        ON DELETE CASCADE
);

-- Create Car table
CREATE TABLE Car (
    carId INT PRIMARY KEY AUTO_INCREMENT,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year INT CHECK (year >= 1886),
    type VARCHAR(30),
    pricePerDay DECIMAL(10, 2),
    availability BOOLEAN
);

-- Create RentalRecord table
CREATE TABLE RentalRecord (
    rentalId INT PRIMARY KEY AUTO_INCREMENT,
    customerId INT,
    carId INT,
    startDate DATE,
    endDate DATE,
    totalCost DECIMAL(10, 2),
    FOREIGN KEY (customerId) REFERENCES Customer(customerId)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (carId) REFERENCES Car(carId)
        ON DELETE SET NULL ON UPDATE CASCADE
);

-- Insert Customer records
INSERT INTO Customer (customerId, firstName, lastName, phoneNumber, licenseNumber) VALUES
(1, 'Alice', 'Johnson', '555-123-4567', 'A1234567'),
(2, 'Bob', 'Smith', '555-987-6543', 'B7654321'),
(3, 'Charlie', 'Kim', '555-456-7890', 'C9876543');

-- Insert User records and link to customers
INSERT INTO User (username, password, role, customerId) VALUES
('admin1', 'password', 'admin', NULL),
('john_doe', 'password', 'customer', 1),
('charlie_k', 'secretpass', 'customer', 3);

-- Insert Car records
INSERT INTO Car (carId, make, model, year, type, pricePerDay, availability) VALUES
(1, 'Toyota', 'Camry', 2020, 'Sedan', 45.00, TRUE),
(2, 'Honda', 'CR-V', 2019, 'SUV', 60.00, TRUE),
(3, 'Ford', 'Focus', 2021, 'Compact', 40.00, FALSE);

-- Insert RentalRecord records
INSERT INTO RentalRecord (rentalId, customerId, carId, startDate, endDate, totalCost) VALUES
(1, 1, 2, '2025-04-01', '2025-04-05', 240.00),
(2, 2, 1, '2025-04-10', '2025-04-12', 90.00);
