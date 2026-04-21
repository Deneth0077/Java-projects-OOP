package com.qatesting;

/**
 * Subclass representing a Van.
 */
public class Van extends Vehicle {
    private double cargoCapacityKg;

    public Van(String vehicleId, String brand, String model, double baseRatePerDay, double cargoCapacityKg) {
        super(vehicleId, brand, model, baseRatePerDay);
        this.cargoCapacityKg = cargoCapacityKg;
    }

    public double getCargoCapacityKg() {
        return cargoCapacityKg;
    }

    public void setCargoCapacityKg(double cargoCapacityKg) {
        this.cargoCapacityKg = cargoCapacityKg;
    }

    @Override
    public double calculateRentalCost(int days) {
        // totalCost = baseRatePerDay * days + (cargoCapacityKg * 0.2 * days)
        return (getBaseRatePerDay() * days) + (cargoCapacityKg * 0.2 * days);
    }

    @Override
    public void displayDetails() {
        System.out.println("--- Van Details ---");
        super.displayDetails();
        System.out.println("Cargo Capacity: " + cargoCapacityKg + " kg");
    }
}
