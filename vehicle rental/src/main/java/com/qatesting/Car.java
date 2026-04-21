package com.qatesting;

/**
 * Subclass representing a Car.
 */
public class Car extends Vehicle {
    private int numberOfSeats;

    public Car(String vehicleId, String brand, String model, double baseRatePerDay, int numberOfSeats) {
        super(vehicleId, brand, model, baseRatePerDay);
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public double calculateRentalCost(int days) {
        // totalCost = baseRatePerDay * days + (numberOfSeats * 200 * days)
        return (getBaseRatePerDay() * days) + (numberOfSeats * 200.0 * days);
    }

    @Override
    public void displayDetails() {
        System.out.println("--- Car Details ---");
        super.displayDetails();
        System.out.println("Number of Seats: " + numberOfSeats);
    }
}
