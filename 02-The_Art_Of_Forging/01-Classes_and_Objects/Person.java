/**
 * 📚 CLASSES AND OBJECTS - THE RUNE OF SYNTAX
 * 
 * This sacred scroll demonstrates the pure syntax of Classes and Objects
 * in their most fundamental form. Study each line carefully, for these
 * are the building blocks upon which all Object-Oriented wisdom rests.
 * 
 * Remember: A Class is the BLUEPRINT, an Object is the INSTANCE
 */

// ═══════════════════════════════════════════════════════════════════════════════
// 🏗️ BASIC CLASS DECLARATION - THE BLUEPRINT
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * The most basic Class structure
 * - 'public' means it can be accessed from anywhere
 * - 'class' is the keyword that declares this as a Class
 * - 'Person' is the Class name (always starts with capital letter)
 */
public class Person {
    // This is the inside of the Class - the blueprint definition
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🎯 CLASS WITH FIELDS - STORING OBJECT STATE
// ═══════════════════════════════════════════════════════════════════════════════

class Student {
    // Fields (also called instance variables or attributes)
    // These define what data each Student object will store
    
    String name;        // Each Student has a name
    int age;           // Each Student has an age  
    String major;      // Each Student has a major
    double gpa;        // Each Student has a GPA
    boolean isEnrolled; // Each Student has enrollment status
    
    // Note: These are the "what" - what a Student IS
    // Each Student object will have its own copy of these fields
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🚀 CLASS WITH METHODS - DEFINING OBJECT BEHAVIOR
// ═══════════════════════════════════════════════════════════════════════════════

class Car {
    // Fields - what a Car IS (its state/attributes)
    String make;
    String model;
    int year;
    String color;
    double fuelLevel;
    boolean isRunning;
    
    // Methods - what a Car CAN DO (its behavior/actions)
    
    /**
     * Method with no parameters and no return value
     */
    void start() {
        isRunning = true;
        System.out.println("The " + color + " " + make + " " + model + " is starting up!");
    }
    
    /**
     * Method with no parameters but WITH return value
     */
    boolean isRunning() {
        return isRunning;
    }
    
    /**
     * Method with parameters and no return value
     */
    void refuel(double gallons) {
        fuelLevel += gallons;
        System.out.println("Added " + gallons + " gallons. Fuel level: " + fuelLevel);
    }
    
    /**
     * Method with parameters and WITH return value
     */
    double calculateRange(double milesPerGallon) {
        return fuelLevel * milesPerGallon;
    }
    
    /**
     * Method that uses multiple fields and parameters
     */
    void displayInfo() {
        System.out.println("=== CAR INFORMATION ===");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Color: " + color);
        System.out.println("Fuel Level: " + fuelLevel + " gallons");
        System.out.println("Running: " + (isRunning ? "Yes" : "No"));
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🎭 OBJECT CREATION AND USAGE - BRINGING BLUEPRINTS TO LIFE
// ═══════════════════════════════════════════════════════════════════════════════

class ObjectCreationDemo {
    public static void main(String[] args) {
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🌟 CREATING OBJECTS - THE SACRED RITUAL
        // ═══════════════════════════════════════════════════════════════════════
        
        // Syntax: ClassName objectName = new ClassName();
        // This creates a NEW object from the Class blueprint
        
        Car myCar = new Car();      // Create first Car object
        Car friendCar = new Car();  // Create second Car object
        
        // Each object is SEPARATE and UNIQUE, even though they're from the same Class
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🎯 SETTING OBJECT STATE - GIVING OBJECTS THEIR IDENTITY
        // ═══════════════════════════════════════════════════════════════════════
        
        // Setting fields for first car object
        myCar.make = "Toyota";
        myCar.model = "Camry";
        myCar.year = 2022;
        myCar.color = "Blue";
        myCar.fuelLevel = 15.5;
        myCar.isRunning = false;
        
        // Setting fields for second car object (different values!)
        friendCar.make = "Honda";
        friendCar.model = "Civic";
        friendCar.year = 2021;
        friendCar.color = "Red";
        friendCar.fuelLevel = 12.3;
        friendCar.isRunning = false;
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🚀 CALLING OBJECT METHODS - MAKING OBJECTS PERFORM ACTIONS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("=== DEMONSTRATING OBJECT BEHAVIOR ===\n");
        
        // Each object can perform the same actions, but with their own data
        myCar.displayInfo();
        System.out.println();
        friendCar.displayInfo();
        System.out.println();
        
        // Calling methods on specific objects
        myCar.start();                    // Only myCar starts
        friendCar.refuel(5.0);           // Only friendCar gets refueled
        
        // Methods with return values
        boolean myCarRunning = myCar.isRunning();
        double myCarRange = myCar.calculateRange(30.0);  // 30 MPG
        
        System.out.println("\nMy car is running: " + myCarRunning);
        System.out.println("My car's range: " + myCarRange + " miles");
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🎪 MULTIPLE OBJECTS - THE POWER OF REUSABILITY
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n=== CREATING MULTIPLE OBJECTS ===");
        
        // Create an array of Car objects
        Car[] garage = new Car[3];
        
        // Each array element holds a DIFFERENT Car object
        garage[0] = new Car();
        garage[1] = new Car();  
        garage[2] = new Car();
        
        // Set up each car with different data
        garage[0].make = "Ford"; garage[0].model = "F-150"; garage[0].color = "Black";
        garage[1].make = "BMW"; garage[1].model = "X5"; garage[1].color = "White";
        garage[2].make = "Tesla"; garage[2].model = "Model 3"; garage[2].color = "Silver";
        
        // Each object can perform the same action, but with its own data
        for (int i = 0; i < garage.length; i++) {
            System.out.println("Car " + (i + 1) + ": " + garage[i].color + 
                             " " + garage[i].make + " " + garage[i].model);
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🔬 ADVANCED SYNTAX CONCEPTS
// ═══════════════════════════════════════════════════════════════════════════════

class AdvancedConcepts {
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🎯 OBJECT REFERENCES - THE MYSTICAL CONNECTION
    // ═══════════════════════════════════════════════════════════════════════
    
    public static void demonstrateReferences() {
        Car car1 = new Car();
        car1.make = "Toyota";
        car1.model = "Prius";
        
        // car2 doesn't create a NEW object - it references the SAME object as car1
        Car car2 = car1;
        
        // Changing car2 also changes car1 (they're the SAME object!)
        car2.color = "Green";
        
        System.out.println("car1 color: " + car1.color);  // Prints "Green"
        System.out.println("car2 color: " + car2.color);  // Also prints "Green"
        
        // This is because car1 and car2 are references to the SAME object
    }
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🎪 NULL REFERENCES - THE VOID
    // ═══════════════════════════════════════════════════════════════════════
    
    public static void demonstrateNull() {
        Car emptyCar = null;  // This reference points to nothing
        
        // This would cause a NullPointerException:
        // emptyCar.start();  // ERROR! Can't call methods on null
        
        // Always check for null before using an object
        if (emptyCar != null) {
            emptyCar.start();
        } else {
            System.out.println("Car reference is null - no object exists!");
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🔄 OBJECT COMPARISON - IDENTITY VS EQUALITY
    // ═══════════════════════════════════════════════════════════════════════
    
    public static void demonstrateComparison() {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = car1;  // Same reference as car1
        
        // Set same values
        car1.make = "Honda"; car1.model = "Civic";
        car2.make = "Honda"; car2.model = "Civic";
        
        // Reference comparison (==) - checks if they're the SAME object
        System.out.println("car1 == car2: " + (car1 == car2));  // false (different objects)
        System.out.println("car1 == car3: " + (car1 == car3));  // true (same object)
        
        // Even with same values, == returns false for different objects
        // (We'll learn about .equals() method later for value comparison)
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 📚 SYNTAX SUMMARY REFERENCE
// ═══════════════════════════════════════════════════════════════════════════════

/*
 * CLASS DECLARATION SYNTAX:
 * [access_modifier] class ClassName {
 *     // Fields (state)
 *     dataType fieldName;
 *     
 *     // Methods (behavior)
 *     returnType methodName(parameters) {
 *         // method body
 *         return value; // if not void
 *     }
 * }
 * 
 * OBJECT CREATION SYNTAX:
 * ClassName objectName = new ClassName();
 * 
 * FIELD ACCESS SYNTAX:
 * objectName.fieldName = value;        // Setting
 * dataType var = objectName.fieldName; // Getting
 * 
 * METHOD CALL SYNTAX:
 * objectName.methodName();                    // No parameters, no return
 * objectName.methodName(arguments);           // With parameters, no return
 * returnType result = objectName.methodName(); // No parameters, with return
 * returnType result = objectName.methodName(arguments); // Parameters + return
 * 
 * KEY CONCEPTS:
 * - Class = Blueprint (written once)
 * - Object = Instance (created many times)
 * - new keyword = Creates new object in memory
 * - Object references = Variables that point to objects
 * - null = Reference that points to nothing
 * - Each object has its own copy of the fields
 * - All objects of same class share the same methods
 */
