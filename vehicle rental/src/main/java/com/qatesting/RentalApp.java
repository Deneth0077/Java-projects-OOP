package com.qatesting;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main application class for the Vehicle Rental Management System.
 */
public class RentalApp {
    private static ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static double totalRentalIncome = 0.0;
    private static final String DATA_FILE = "vehicles_data.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();
        boolean exitApp = false;

        System.out.println("=========================================");
        System.out.println("  Vehicle Rental Management System");
        System.out.println("=========================================");

        while (!exitApp) {
            System.out.println("\n--- SELECT ROLE ---");
            System.out.println("1. Admin Mode (Add/Remove Vehicles, View Income)");
            System.out.println("2. User Mode (Rent/Return Vehicles, Search, Sort)");
            System.out.println("3. Exit Application");
            int roleChoice = getIntInput("Select Role: ");

            switch (roleChoice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    userMenu();
                    break;
                case 3:
                    saveData();
                    System.out.println("Exiting... Thank you!");
                    exitApp = true;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    // ROLE-BASED MENUS
    private static void adminMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Add a Vehicle");
            System.out.println("2. Remove a Vehicle");
            System.out.println("3. View All Vehicles");
            System.out.println("4. View Total Rental Income");
            System.out.println("5. Logout to Main Menu");
            int choice = getIntInput("Select an option: ");

            switch (choice) {
                case 1:
                    addVehicle();
                    break;
                case 2:
                    removeVehicle();
                    break;
                case 3:
                    viewAllVehicles();
                    break;
                case 4:
                    viewTotalRentalIncome();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    private static void userMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- USER MENU ---");
            System.out.println("1. View All Vehicles");
            System.out.println("2. Rent a Vehicle");
            System.out.println("3. Return a Vehicle");
            System.out.println("4. Advanced Search");
            System.out.println("5. Sort Vehicles");
            System.out.println("6. Logout to Main Menu");
            int choice = getIntInput("Select an option: ");

            switch (choice) {
                case 1:
                    viewAllVehicles();
                    break;
                case 2:
                    rentVehicle();
                    break;
                case 3:
                    returnVehicle();
                    break;
                case 4:
                    advancedSearch();
                    break;
                case 5:
                    sortVehicles();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    // CORE FEATURES
    private static void addVehicle() {
        System.out.println("\n--- ADD A VEHICLE ---");
        System.out.println("1. Car\n2. Bike\n3. Van");
        int type = getIntInput("Select vehicle type: ");

        String id = getStringInput("Enter Vehicle ID: ");
        if (findVehicleById(id) != null) {
            System.out.println("Error: Vehicle ID must be unique.");
            return;
        }

        String brand = getStringInput("Enter Brand: ");
        String model = getStringInput("Enter Model: ");
        double rate = getDoubleInput("Enter Base Rate Per Day: ");

        switch (type) {
            case 1:
                int seats = getIntInput("Enter Number of Seats: ");
                vehicles.add(new Car(id, brand, model, rate, seats));
                break;
            case 2:
                int engine = getIntInput("Enter Engine Capacity (CC): ");
                vehicles.add(new Bike(id, brand, model, rate, engine));
                break;
            case 3:
                double cargo = getDoubleInput("Enter Cargo Capacity (kg): ");
                vehicles.add(new Van(id, brand, model, rate, cargo));
                break;
            default:
                System.out.println("Invalid vehicle type.");
                return;
        }
        System.out.println("Vehicle added successfully!");
        saveData();
    }

    private static void removeVehicle() {
        String id = getStringInput("\nEnter Vehicle ID to remove: ");
        Vehicle v = findVehicleById(id);
        if (v != null) {
            vehicles.remove(v);
            System.out.println("Vehicle '" + id + "' removed successfully.");
            saveData();
        } else {
            System.out.println("Vehicle ID not found.");
        }
    }

    private static void viewAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("\nNo vehicles in the system.");
            return;
        }
        System.out.println("\n--- ALL VEHICLES (Total: " + vehicles.size() + ") ---");
        for (Vehicle v : vehicles) {
            v.displayDetails();
            System.out.println("-------------------");
        }
    }

    private static void rentVehicle() {
        String id = getStringInput("\nEnter Vehicle ID to rent: ");
        Vehicle v = findVehicleById(id);

        if (v == null) {
            System.out.println("Vehicle ID not found.");
            return;
        }

        if (!v.isAvailable()) {
            System.out.println("Vehicle is already rented.");
            return;
        }

        int days = getIntInput("Enter number of rental days: ");
        if (days <= 0) {
            System.out.println("Rental days must be greater than zero.");
            return;
        }

        double cost = v.calculateRentalCost(days);
        System.out.println("Rental successful!");
        System.out.printf("Total Rental Cost: $%.2f\n", cost);

        v.rentVehicle();
        totalRentalIncome += cost;
        saveData();
    }

    private static void returnVehicle() {
        String id = getStringInput("\nEnter Vehicle ID to return: ");
        Vehicle v = findVehicleById(id);

        if (v == null) {
            System.out.println("Vehicle ID not found.");
            return;
        }

        if (v.isAvailable()) {
            System.out.println("Vehicle is not currently rented.");
            return;
        }

        v.returnVehicle();
        System.out.println("Vehicle returned successfully!");
        saveData();
    }

    // ADVANCED SEARCH & FILTERING
    private static void advancedSearch() {
        System.out.println("\n--- SEARCH VEHICLES ---");
        System.out.println("1. By Brand");
        System.out.println("2. By Model");
        System.out.println("3. Show Only Available Vehicles");
        int subChoice = getIntInput("Select search type: ");

        String query = "";
        if (subChoice == 1 || subChoice == 2) {
            query = getStringInput("Enter search term: ");
        }

        boolean found = false;
        System.out.println("\n--- SEARCH RESULTS ---");
        for (Vehicle v : vehicles) {
            boolean match = false;
            switch (subChoice) {
                case 1:
                    match = v.getBrand().equalsIgnoreCase(query);
                    break;
                case 2:
                    match = v.getModel().equalsIgnoreCase(query);
                    break;
                case 3:
                    match = v.isAvailable();
                    break;
            }
            if (match) {
                v.displayDetails();
                System.out.println("-------------------");
                found = true;
            }
        }
        if (!found)
            System.out.println("No matching vehicles found.");
    }

    // SORTING MECHANISM
    private static void sortVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        System.out.println("\n--- SORT VEHICLES ---");
        System.out.println("1. By Brand Name (A-Z)");
        System.out.println("2. By Base Rate (Low to High)");
        System.out.println("3. By Vehicle Type");
        int sortChoice = getIntInput("Select sorting option: ");

        switch (sortChoice) {
            case 1:
                vehicles.sort((v1, v2) -> v1.getBrand().compareToIgnoreCase(v2.getBrand()));
                break;
            case 2:
                vehicles.sort((v1, v2) -> Double.compare(v1.getBaseRatePerDay(), v2.getBaseRatePerDay()));
                break;
            case 3:
                vehicles.sort(
                        (v1, v2) -> v1.getClass().getSimpleName().compareToIgnoreCase(v2.getClass().getSimpleName()));
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }
        System.out.println("Vehicles sorted successfully!");
        viewAllVehicles();
    }

    private static void viewTotalRentalIncome() {
        System.out.printf("\nTotal Rental Income: $%.2f\n", totalRentalIncome);
    }

    private static Vehicle findVehicleById(String id) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                return v;
            }
        }
        return null;
    }

    // Input Utilities
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
            }
        }
    }

    private static String getStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty())
                return input;
            System.out.println("Input cannot be empty.");
        }
    }

    // Persistence Logic
    private static void saveData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            writer.println(totalRentalIncome);
            for (Vehicle v : vehicles) {
                String type = v instanceof Car ? "CAR" : (v instanceof Bike ? "BIKE" : "VAN");
                String extra = "";
                if (v instanceof Car)
                    extra = String.valueOf(((Car) v).getNumberOfSeats());
                else if (v instanceof Bike)
                    extra = String.valueOf(((Bike) v).getEngineCapacityCC());
                else if (v instanceof Van)
                    extra = String.valueOf(((Van) v).getCargoCapacityKg());

                writer.println(String.join(",", type, v.getVehicleId(), v.getBrand(), v.getModel(),
                        String.valueOf(v.getBaseRatePerDay()), String.valueOf(v.isAvailable()), extra));
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static void loadData() {
        File file = new File(DATA_FILE);
        if (!file.exists())
            return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String incomeLine = reader.readLine();
            if (incomeLine != null)
                totalRentalIncome = Double.parseDouble(incomeLine);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length < 7)
                    continue;

                Vehicle v = null;
                switch (p[0]) {
                    case "CAR":
                        v = new Car(p[1], p[2], p[3], Double.parseDouble(p[4]), Integer.parseInt(p[6]));
                        break;
                    case "BIKE":
                        v = new Bike(p[1], p[2], p[3], Double.parseDouble(p[4]), Integer.parseInt(p[6]));
                        break;
                    case "VAN":
                        v = new Van(p[1], p[2], p[3], Double.parseDouble(p[4]), Double.parseDouble(p[6]));
                        break;
                }
                if (v != null) {
                    v.setAvailable(Boolean.parseBoolean(p[5]));
                    vehicles.add(v);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
