package com.qatesting;

import java.io.Serializable;

/**
 * Abstract class representing a generic vehicle in the rental system.
 */
public abstract class Vehicle implements Serializable {
    private String vehicleId;
    private String brand;
    private String model;
    private double baseRatePerDay;
    private boolean isAvailable;

    public Vehicle(String vehicleId, String brand, String model, double baseRatePerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.baseRatePerDay = baseRatePerDay;
        this.isAvailable = true;
    }

    // Getters and Setters
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getBaseRatePerDay() {
        return baseRatePerDay;
    }

    public void setBaseRatePerDay(double baseRatePerDay) {
        this.baseRatePerDay = baseRatePerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Displays the basic details of the vehicle.
     */
    public void displayDetails() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Base Rate Per Day: $" + baseRatePerDay);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Rented"));
    }

    /**
     * Marks the vehicle as rented.
     */
    public void rentVehicle() {
        if (isAvailable) {
            isAvailable = false;
        } else {
            System.out.println("Vehicle " + vehicleId + " is already rented.");
        }
    }

    /**
     * Marks the vehicle as available.
     */
    public void returnVehicle() {
        isAvailable = true;
    }

    /**
     * Abstract method to be implemented by subclasses to calculate rental cost.
     * 
     * @param days Number of rental days.
     * @return Total rental cost.
     */
    public abstract double calculateRentalCost(int days);

    @Override
    public String toString() {
        return String.format("%s %s (%s)", brand, model, vehicleId);
    }
}
