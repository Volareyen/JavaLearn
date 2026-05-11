/**
 * VehicleFleet.java — Constructors Solution
 *
 * Demonstrates default, parameterized, copy constructors, and constructor chaining
 * in a vehicle fleet management system.
 *
 * Run: javac VehicleFleet.java && java VehicleFleet
 */
import java.util.*;

public class VehicleFleet {

    static class Vehicle {
        private String make;
        private String model;
        private int year;
        private double mileage;
        private String color;
        private boolean isElectric;

        // Default constructor
        Vehicle() {
            this("Unknown", "Unknown", 2024);
        }

        // Parameterized — chains to full constructor
        Vehicle(String make, String model, int year) {
            this(make, model, year, 0.0, "White", false);
        }

        // Full constructor
        Vehicle(String make, String model, int year, double mileage, String color, boolean isElectric) {
            this.make = make; this.model = model; this.year = year;
            this.mileage = mileage; this.color = color; this.isElectric = isElectric;
        }

        // Copy constructor
        Vehicle(Vehicle other) {
            this(other.make, other.model, other.year, other.mileage, other.color, other.isElectric);
        }

        void drive(double km) { mileage += km; }

        @Override
        public String toString() {
            return String.format("%d %s %s [%s] %.0fkm%s",
                year, make, model, color, mileage, isElectric ? " ⚡" : "");
        }
    }

    static class Fleet {
        private String company;
        private List<Vehicle> vehicles = new ArrayList<>();

        Fleet(String company) { this.company = company; }

        void add(Vehicle v) { vehicles.add(v); }
        void printFleet() {
            System.out.println("  Fleet: " + company + " (" + vehicles.size() + " vehicles)");
            vehicles.forEach(v -> System.out.println("    • " + v));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== CONSTRUCTORS SOLUTION: VEHICLE FLEET ===\n");

        Vehicle v1 = new Vehicle();                                        // default
        Vehicle v2 = new Vehicle("Toyota", "Camry", 2022);               // chaining
        Vehicle v3 = new Vehicle("Tesla", "Model 3", 2023, 5000, "Red", true); // full
        Vehicle v4 = new Vehicle(v3);                                      // copy

        v2.drive(12500);
        v3.drive(5200);

        System.out.println("  Vehicles created:");
        System.out.println("  Default:      " + v1);
        System.out.println("  Parameterized:" + v2);
        System.out.println("  Full:         " + v3);
        System.out.println("  Copy:         " + v4);
        System.out.println("  Original != Copy: " + (v3 != v4) + " (independent objects)");

        Fleet fleet = new Fleet("Apex Rentals");
        fleet.add(v2); fleet.add(v3);
        System.out.println();
        fleet.printFleet();

        System.out.println("\nConstructors ✅ — default, parameterized, chained, copy all demonstrated");
    }
}
