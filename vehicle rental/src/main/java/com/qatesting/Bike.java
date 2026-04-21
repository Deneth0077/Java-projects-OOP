package com.qatesting;

/**
 * Subclass representing a Bike.
 */
public class Bike extends Vehicle {
    private int engineCapacityCC;

    public Bike(String vehicleId, String brand, String model, double baseRatePerDay, int engineCapacityCC) {
        super(vehicleId, brand, model, baseRatePerDay);
        this.engineCapacityCC = engineCapacityCC;
    }

    public int getEngineCapacityCC() {
        return engineCapacityCC;
    }

    public void setEngineCapacityCC(int engineCapacityCC) {
        this.engineCapacityCC = engineCapacityCC;
    }

    @Override
    public double calculateRentalCost(int days) {
        // totalCost = baseRatePerDay * days + (engineCapacityCC * 0.5 * days)
        return (getBaseRatePerDay() * days) + (engineCapacityCC * 0.5 * days);
    }

    @Override
    public void displayDetails() {
        System.out.println("--- Bike Details ---");
        super.displayDetails();
        System.out.println("Engine Capacity: " + engineCapacityCC + " CC");
    }
}
