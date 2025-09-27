/**
 * _Concept.java - The Rune of Syntax for Enums
 * 
 * This sacred scroll demonstrates the pure syntax of enumerations
 * in their most fundamental forms. Study these patterns well,
 * for they are the building blocks of type-safe constants.
 * 
 * "Observe the progression from simple to sophisticated - 
 * each example reveals another facet of enum power."
 */

// ========================================
// BASIC ENUM DECLARATION
// ========================================
/**
 * The simplest form - a fixed set of named constants
 * Each constant is public, static, and final by default
 */
enum BasicColor {
    RED, GREEN, BLUE, YELLOW, PURPLE, ORANGE
}

// ========================================
// ENUM WITH CONSTRUCTOR AND FIELDS
// ========================================
/**
 * Enums can have constructors, fields, and methods
 * The constructor is always private (explicitly or implicitly)
 */
enum Planet {
    // Each constant calls the constructor
    MERCURY(0.330, 4879),
    VENUS(4.87, 12104), 
    EARTH(5.97, 12756),
    MARS(0.642, 6792),
    JUPITER(1898, 142984),
    SATURN(568, 120536);
    
    // Fields to store the constructor parameters
    private final double mass;     // Mass in 10^24 kg
    private final double diameter; // Diameter in km
    
    // Constructor - always private in enums
    Planet(double mass, double diameter) {
        this.mass = mass;
        this.diameter = diameter;
    }
    
    // Getter methods to access the fields
    public double getMass() { return mass; }
    public double getDiameter() { return diameter; }
    
    // Computed property method
    public double getSurfaceArea() {
        double radius = diameter / 2;
        return 4 * Math.PI * radius * radius;
    }
}

// ========================================
// ENUM WITH ABSTRACT METHODS
// ========================================
/**
 * Each constant can have its own implementation
 * of abstract methods - the ultimate in polymorphism!
 */
enum Operation {
    PLUS("+") {
        @Override
        public double calculate(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double calculate(double x, double y) {
            return x - y;
        }
    },
    MULTIPLY("*") {
        @Override
        public double calculate(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double calculate(double x, double y) {
            return x / y;
        }
    };
    
    private final String symbol;
    
    Operation(String symbol) {
        this.symbol = symbol;
    }
    
    // Abstract method that each constant must implement
    public abstract double calculate(double x, double y);
    
    // Concrete method available to all constants
    public String getSymbol() { return symbol; }
    
    @Override
    public String toString() { return symbol; }
}

// ========================================
// ENUM IMPLEMENTING INTERFACE
// ========================================
/**
 * Enums can implement interfaces, gaining additional contracts
 */
interface Describable {
    String getDescription();
}

enum Priority implements Describable {
    LOW(1, "Low priority - handle when convenient"),
    MEDIUM(5, "Medium priority - handle within reasonable time"),
    HIGH(10, "High priority - handle as soon as possible"),
    CRITICAL(15, "Critical priority - drop everything and handle now!");
    
    private final int level;
    private final String description;
    
    Priority(int level, String description) {
        this.level = level;
        this.description = description;
    }
    
    public int getLevel() { return level; }
    
    // Implementing the interface method
    @Override
    public String getDescription() { return description; }
    
    // Utility method to compare priorities
    public boolean isHigherThan(Priority other) {
        return this.level > other.level;
    }
}

// ========================================
// ENUM WITH STATIC METHODS
// ========================================
/**
 * Enums can have static methods for utility functions
 */
enum Status {
    DRAFT("Draft", false, false),
    REVIEW("Under Review", false, true),
    APPROVED("Approved", true, true),
    PUBLISHED("Published", true, false),
    ARCHIVED("Archived", false, false);
    
    private final String displayName;
    private final boolean isActive;
    private final boolean canEdit;
    
    Status(String displayName, boolean isActive, boolean canEdit) {
        this.displayName = displayName;
        this.isActive = isActive;
        this.canEdit = canEdit;
    }
    
    public String getDisplayName() { return displayName; }
    public boolean isActive() { return isActive; }
    public boolean canEdit() { return canEdit; }
    
    // Static utility method to find active statuses
    public static Status[] getActiveStatuses() {
        return java.util.Arrays.stream(values())
                .filter(Status::isActive)
                .toArray(Status[]::new);
    }
    
    // Static utility method to find editable statuses
    public static boolean isEditable(Status status) {
        return status != null && status.canEdit;
    }
}

// ========================================
// DEMONSTRATION CLASS
// ========================================
/**
 * This class demonstrates how to use all the enum concepts
 * shown above in practical scenarios.
 */
public class _Concept {
    
    public static void main(String[] args) {
        System.out.println("=== ENUM CONCEPT DEMONSTRATIONS ===\n");
        
        // Basic enum usage
        demonstrateBasicEnums();
        
        // Enum with fields and methods
        demonstratePlanetEnums();
        
        // Enum with abstract methods
        demonstrateOperationEnums();
        
        // Enum implementing interface
        demonstratePriorityEnums();
        
        // Enum with static methods
        demonstrateStatusEnums();
        
        // Built-in enum methods
        demonstrateBuiltInMethods();
    }
    
    /**
     * Demonstrates basic enum constants and switch statements
     */
    private static void demonstrateBasicEnums() {
        System.out.println("--- Basic Enum Usage ---");
        
        BasicColor favoriteColor = BasicColor.BLUE;
        System.out.println("My favorite color is: " + favoriteColor);
        
        // Using enum in switch statements
        switch (favoriteColor) {
            case RED -> System.out.println("Red is passionate!");
            case GREEN -> System.out.println("Green is natural!");
            case BLUE -> System.out.println("Blue is calming!");
            default -> System.out.println("Beautiful color choice!");
        }
        System.out.println();
    }
    
    /**
     * Demonstrates enums with constructors, fields, and methods
     */
    private static void demonstratePlanetEnums() {
        System.out.println("--- Planet Enum with Fields ---");
        
        Planet earth = Planet.EARTH;
        System.out.println("Planet: " + earth.name());
        System.out.println("Mass: " + earth.getMass() + " x 10^24 kg");
        System.out.println("Diameter: " + earth.getDiameter() + " km");
        System.out.println("Surface Area: " + String.format("%.2e", earth.getSurfaceArea()) + " km²");
        
        System.out.println("\nAll planets:");
        for (Planet planet : Planet.values()) {
            System.out.printf("%-8s: Mass=%-5.2f, Diameter=%-6.0f%n",
                planet.name(), planet.getMass(), planet.getDiameter());
        }
        System.out.println();
    }
    
    /**
     * Demonstrates enums with abstract methods (constant-specific methods)
     */
    private static void demonstrateOperationEnums() {
        System.out.println("--- Operation Enum with Abstract Methods ---");
        
        double x = 15.0, y = 3.0;
        
        for (Operation op : Operation.values()) {
            double result = op.calculate(x, y);
            System.out.printf("%.1f %s %.1f = %.2f%n", x, op.getSymbol(), y, result);
        }
        System.out.println();
    }
    
    /**
     * Demonstrates enum implementing an interface
     */
    private static void demonstratePriorityEnums() {
        System.out.println("--- Priority Enum Implementing Interface ---");
        
        Priority currentPriority = Priority.HIGH;
        System.out.println("Current Priority: " + currentPriority);
        System.out.println("Level: " + currentPriority.getLevel());
        System.out.println("Description: " + currentPriority.getDescription());
        
        System.out.println("\nPriority Comparisons:");
        System.out.println("HIGH > MEDIUM: " + Priority.HIGH.isHigherThan(Priority.MEDIUM));
        System.out.println("LOW > CRITICAL: " + Priority.LOW.isHigherThan(Priority.CRITICAL));
        System.out.println();
    }
    
    /**
     * Demonstrates enum with static utility methods
     */
    private static void demonstrateStatusEnums() {
        System.out.println("--- Status Enum with Static Methods ---");
        
        Status currentStatus = Status.REVIEW;
        System.out.println("Current Status: " + currentStatus.getDisplayName());
        System.out.println("Is Active: " + currentStatus.isActive());
        System.out.println("Can Edit: " + currentStatus.canEdit());
        
        System.out.println("\nActive Statuses:");
        for (Status status : Status.getActiveStatuses()) {
            System.out.println("- " + status.getDisplayName());
        }
        
        System.out.println("\nEditable Status Check:");
        System.out.println("DRAFT is editable: " + Status.isEditable(Status.DRAFT));
        System.out.println("PUBLISHED is editable: " + Status.isEditable(Status.PUBLISHED));
        System.out.println();
    }
    
    /**
     * Demonstrates built-in enum methods that all enums inherit
     */
    private static void demonstrateBuiltInMethods() {
        System.out.println("--- Built-in Enum Methods ---");
        
        BasicColor color = BasicColor.GREEN;
        
        // name() - returns the constant name as declared
        System.out.println("name(): " + color.name());
        
        // ordinal() - returns the position (0-based)
        System.out.println("ordinal(): " + color.ordinal());
        
        // toString() - by default same as name(), but can be overridden
        System.out.println("toString(): " + color.toString());
        
        // values() - returns array of all constants
        System.out.println("All colors:");
        for (BasicColor c : BasicColor.values()) {
            System.out.println("  " + c.ordinal() + ": " + c.name());
        }
        
        // valueOf() - converts string to enum constant
        try {
            BasicColor fromString = BasicColor.valueOf("RED");
            System.out.println("valueOf('RED'): " + fromString);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid enum constant: " + e.getMessage());
        }
        
        System.out.println();
    }
}

/* 
 * ========================================
 * KEY SYNTAX PATTERNS TO REMEMBER:
 * ========================================
 * 
 * 1. Basic Declaration:
 *    enum Name { CONSTANT1, CONSTANT2, CONSTANT3 }
 * 
 * 2. With Constructor:
 *    enum Name { 
 *        CONST1(param1), CONST2(param2);
 *        private final Type field;
 *        Name(Type param) { this.field = param; }
 *    }
 * 
 * 3. With Abstract Methods:
 *    enum Name {
 *        CONST1 { public ReturnType method() { ... } };
 *        public abstract ReturnType method();
 *    }
 * 
 * 4. Implementing Interface:
 *    enum Name implements Interface { ... }
 * 
 * 5. Built-in Methods:
 *    - name() - constant name
 *    - ordinal() - position index
 *    - values() - all constants array
 *    - valueOf(String) - string to enum
 * 
 * Remember: Enums are classes! They can have constructors,
 * fields, methods, and implement interfaces. But they cannot
 * be extended and their constructors are always private.
 */
