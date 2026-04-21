# Vehicle Rental Management System

This is a Java Command-Line Application for managing vehicle rentals. It demonstrates key Object-Oriented Programming (OOP) principles such as Inheritance, Polymorphism, Abstraction, and Encapsulation.

## Features
- **Class Hierarchy**: Uses an abstract `Vehicle` class with subclasses `Car`, `Bike`, and `Van`.
- **Menu-Driven CLI**: A user-friendly text interface to interact with the system.
- **Rental Operations**: Rent and return vehicles with automatic cost calculation.
- **Search & Tracking**: Search for vehicles by ID and track total rental income.
- **Input Validation**: Robust handling of invalid numeric inputs and menu selections.
- **Challenging Component - File Handling**: Saves vehicle data and rental income to `vehicles_data.txt` and loads it automatically on startup.

## How to Compile and Run

### Prerequisites
- Java Development Kit (JDK) 17 or higher.
- Maven (optional, but project structure is Maven-compatible).

### Using the Command Line (Manual)
1. **Navigate to the project source directory**:
   ```bash
   cd "d:/Working Projects/OOP ass 2/src/main/java"
   ```
2. **Compile the source files**:
   ```bash
   javac com/qatesting/*.java
   ```
3. **Run the application**:
   ```bash
   java com.qatesting.RentalApp
   ```

### Using Maven
1. **Navigate to the project root**:
   ```bash
   cd "d:/Working Projects/OOP ass 2"
   ```
2. **Build and Run**:
   ```bash
   mvn compile
   mvn exec:java -Dexec.mainClass="com.qatesting.RentalApp"
   ```

## Assumptions Made
- Vehicle IDs are unique across all types.
- Rental cost calculation includes mandatory extra fees specified in the instructions (e.g., seat fee for cars).
- Data is saved in a comma-separated text file (`vehicles_data.txt`) in the project root.
- The user inputs follow the prompts provided by the CLI.

## Sample Menu Usage
1. **Add a Vehicle**: Choose option 1, then select vehicle type (Car/Bike/Van) and provide details.
2. **Rent a Vehicle**: Choose option 3, enter the ID of an available vehicle and the number of days.
3. **View All Vehicles**: Choose option 2 to see the status and details of all registered vehicles.
4. **Exit**: Choose option 7 to save data and quit.
