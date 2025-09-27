/*
 * THE RUNE OF SYNTAX - PILLAR IV: ABSTRACTION
 * 
 * "Here lie the sacred symbols of Abstraction - the abstract class and the interface.
 * Study these forms well, for they are the building blocks of all sophisticated design."
 * 
 * This file demonstrates the pure syntax of Abstraction concepts:
 * - Abstract Classes with concrete and abstract methods
 * - Interfaces with constants and method signatures
 * - Implementation inheritance vs Interface contracts
 * - Multiple interface implementation
 * - Access modifiers in abstractions
 */

// ================================================================================================
// FORM I: ABSTRACT CLASSES - The Incomplete Temple
// ================================================================================================

/**
 * Abstract Class: Cannot be instantiated, serves as a template.
 * May contain both concrete (implemented) and abstract (unimplemented) methods.
 */
public abstract class AbstractVehicle {
    
    // FIELDS: Abstract classes can have instance variables
    protected String brand;           // Shared state for all vehicles
    protected int year;
    private double fuel;             // Private field with controlled access
    
    // CONSTRUCTORS: Abstract classes can have constructors
    // (Called when concrete subclasses are instantiated)
    public AbstractVehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
        this.fuel = 0.0;
    }
    
    // CONCRETE METHODS: Complete implementation shared by all subclasses
    public void refuel(double amount) {
        this.fuel += amount;
        System.out.println("Refueled " + amount + " units. Total: " + fuel);
    }
    
    public void displayInfo() {
        System.out.println(year + " " + brand + " - Fuel: " + fuel);
    }
    
    // ABSTRACT METHODS: Must be implemented by concrete subclasses
    // Note: No method body - ends with semicolon
    public abstract void startEngine();      // Each vehicle starts differently
    public abstract void move();             // Each vehicle moves differently  
    public abstract int getMaxSpeed();       // Each vehicle has different max speed
    public abstract double getFuelEfficiency(); // Different efficiency ratings
    
    // FINAL METHOD: Cannot be overridden by subclasses
    public final void shutdown() {
        System.out.println("Vehicle systems shutting down...");
    }
}

// ================================================================================================
// CONCRETE IMPLEMENTATION: Completing the Abstract Template
// ================================================================================================

/**
 * Concrete class extending abstract class - must implement all abstract methods
 */
class SportsCar extends AbstractVehicle {
    
    private boolean turboMode;
    
    // Constructor must call super constructor
    public SportsCar(String brand, int year) {
        super(brand, year);  // Call parent constructor
        this.turboMode = false;
    }
    
    // REQUIRED: Implement all abstract methods from parent
    @Override
    public void startEngine() {
        System.out.println("Sports car engine roars to life!");
    }
    
    @Override
    public void move() {
        String mode = turboMode ? "TURBO MODE" : "normal mode";
        System.out.println("Racing at high speed in " + mode + "!");
    }
    
    @Override
    public int getMaxSpeed() {
        return turboMode ? 320 : 250;  // km/h
    }
    
    @Override
    public double getFuelEfficiency() {
        return turboMode ? 8.5 : 12.0;  // km per liter
    }
    
    // Additional specific behavior
    public void enableTurbo() {
        this.turboMode = true;
        System.out.println("TURBO MODE ACTIVATED!");
    }
}

// ================================================================================================
// FORM II: INTERFACES - The Pure Contract
// ================================================================================================

/**
 * Interface: Pure contract defining what implementing classes CAN DO
 * All methods are implicitly public abstract
 * All variables are implicitly public static final (constants)
 */
interface Flyable {
    
    // CONSTANTS: Implicitly public, static, final
    int MAX_ALTITUDE = 50000;        // Maximum flying altitude in feet
    double GRAVITY = 9.8;            // Earth's gravity constant
    
    // ABSTRACT METHODS: Implicitly public abstract (no 'abstract' keyword needed)
    void takeOff();                  // How to begin flying
    void fly();                      // How to maintain flight
    void land();                     // How to end flight safely
    int getCurrentAltitude();        // Current height above ground
    
    // DEFAULT METHODS: New in Java 8 - provide default implementation
    default void performPreFlightCheck() {
        System.out.println("Performing standard pre-flight safety check...");
    }
    
    // STATIC METHODS: Utility methods belonging to the interface
    static boolean isSafeAltitude(int altitude) {
        return altitude > 0 && altitude <= MAX_ALTITUDE;
    }
}

/**
 * Another interface - classes can implement multiple interfaces
 */
interface Swimmable {
    
    // Constants
    double WATER_DENSITY = 1000.0;   // kg/m³
    
    // Method contracts
    void dive();
    void swim();
    void surface();
    int getSwimDepth();
    
    // Default method with implementation
    default void performWaterSafetyCheck() {
        System.out.println("Checking water safety protocols...");
    }
}

/**
 * Third interface for demonstration of multiple capabilities
 */
interface Walkable {
    void walk();
    void run();
    void stop();
    
    default void stretchLegs() {
        System.out.println("Stretching before walking...");
    }
}

// ================================================================================================
// MULTIPLE INTERFACE IMPLEMENTATION
// ================================================================================================

/**
 * Concrete class implementing multiple interfaces
 * Must implement ALL abstract methods from ALL interfaces
 */
class AmphibiousVehicle extends AbstractVehicle implements Flyable, Swimmable {
    
    private int altitude;
    private int swimDepth;
    private boolean isFlying;
    private boolean isSwimming;
    
    public AmphibiousVehicle(String brand, int year) {
        super(brand, year);
        this.altitude = 0;
        this.swimDepth = 0;
        this.isFlying = false;
        this.isSwimming = false;
    }
    
    // ===== IMPLEMENTING ABSTRACT VEHICLE METHODS =====
    @Override
    public void startEngine() {
        System.out.println("Multi-environment engine starting...");
    }
    
    @Override
    public void move() {
        if (isFlying) {
            System.out.println("Flying through the air at " + altitude + " feet");
        } else if (isSwimming) {
            System.out.println("Swimming underwater at " + swimDepth + " meters depth");
        } else {
            System.out.println("Driving on land");
        }
    }
    
    @Override
    public int getMaxSpeed() {
        if (isFlying) return 180;
        if (isSwimming) return 25;
        return 120;  // land speed
    }
    
    @Override
    public double getFuelEfficiency() {
        if (isFlying) return 5.0;
        if (isSwimming) return 8.0;
        return 15.0;  // most efficient on land
    }
    
    // ===== IMPLEMENTING FLYABLE INTERFACE =====
    @Override
    public void takeOff() {
        isFlying = true;
        isSwimming = false;
        altitude = 100;
        System.out.println("Taking off! Current altitude: " + altitude + " feet");
    }
    
    @Override
    public void fly() {
        if (isFlying) {
            altitude += 500;
            if (altitude > MAX_ALTITUDE) altitude = MAX_ALTITUDE;
            System.out.println("Flying at " + altitude + " feet altitude");
        } else {
            System.out.println("Cannot fly - not in flight mode");
        }
    }
    
    @Override
    public void land() {
        if (isFlying) {
            altitude = 0;
            isFlying = false;
            System.out.println("Landing complete. Back on solid ground.");
        }
    }
    
    @Override
    public int getCurrentAltitude() {
        return altitude;
    }
    
    // ===== IMPLEMENTING SWIMMABLE INTERFACE =====
    @Override
    public void dive() {
        isSwimming = true;
        isFlying = false;
        altitude = 0;
        swimDepth = 5;
        System.out.println("Diving underwater to " + swimDepth + " meters");
    }
    
    @Override
    public void swim() {
        if (isSwimming) {
            swimDepth += 2;
            System.out.println("Swimming deeper to " + swimDepth + " meters");
        } else {
            System.out.println("Cannot swim - not in water mode");
        }
    }
    
    @Override
    public void surface() {
        if (isSwimming) {
            swimDepth = 0;
            isSwimming = false;
            System.out.println("Surfacing - back on water surface");
        }
    }
    
    @Override
    public int getSwimDepth() {
        return swimDepth;
    }
}

// ================================================================================================
// ABSTRACT CLASS WITH NO ABSTRACT METHODS (Still can't be instantiated)
// ================================================================================================

/**
 * Abstract class that provides complete implementation but still cannot be instantiated
 * Useful for preventing direct instantiation while providing common functionality
 */
abstract class UtilityBase {
    
    protected String utilityName;
    
    public UtilityBase(String name) {
        this.utilityName = name;
    }
    
    // All methods are concrete, but class is still abstract
    public void performTask() {
        System.out.println("Utility " + utilityName + " performing its task");
    }
    
    public String getName() {
        return utilityName;
    }
}

/**
 * Concrete implementation - even though parent had no abstract methods
 */
class ConcreteUtility extends UtilityBase {
    
    public ConcreteUtility(String name) {
        super(name);
    }
    
    // Can add specific functionality
    public void performSpecializedTask() {
        System.out.println("Performing specialized task for " + getName());
    }
}

// ================================================================================================
// INTERFACE EXTENDING INTERFACE (Interface Inheritance)
// ================================================================================================

/**
 * Interfaces can extend other interfaces, combining contracts
 */
interface AdvancedFlyable extends Flyable {
    
    // Additional constants
    int SUPERSONIC_THRESHOLD = 343;  // Speed of sound in m/s
    
    // Additional method requirements
    void activateAutoPilot();
    void performAerialManeuver(String maneuver);
    boolean isSupersonic();
    
    // Can override default methods from parent interface
    @Override
    default void performPreFlightCheck() {
        System.out.println("Performing ADVANCED pre-flight check with extra safety protocols...");
    }
}

// ================================================================================================
// SYNTAX DEMONSTRATION CLASS
// ================================================================================================

/**
 * This class demonstrates the syntax concepts without being part of the hierarchy
 */
class AbstractionSyntaxDemo {
    
    public static void demonstrateAbstractionSyntax() {
        
        System.out.println("=== ABSTRACTION SYNTAX DEMONSTRATION ===\n");
        
        // ===== CANNOT INSTANTIATE ABSTRACT CLASSES =====
        // AbstractVehicle vehicle = new AbstractVehicle("Generic", 2024); // COMPILER ERROR!
        
        // ===== CAN INSTANTIATE CONCRETE SUBCLASSES =====
        SportsCar ferrari = new SportsCar("Ferrari", 2024);
        ferrari.startEngine();        // Implemented abstract method
        ferrari.move();               // Implemented abstract method
        ferrari.refuel(50.0);         // Inherited concrete method
        ferrari.displayInfo();       // Inherited concrete method
        ferrari.enableTurbo();        // Class-specific method
        ferrari.move();               // Different behavior with turbo
        
        System.out.println("Max Speed: " + ferrari.getMaxSpeed() + " km/h");
        System.out.println("Fuel Efficiency: " + ferrari.getFuelEfficiency() + " km/L\n");
        
        // ===== INTERFACE IMPLEMENTATION =====
        AmphibiousVehicle seaplane = new AmphibiousVehicle("SeaPlane X1", 2024);
        
        // Using as AbstractVehicle (inheritance)
        seaplane.startEngine();
        seaplane.move();
        
        // Using as Flyable (interface contract)
        seaplane.performPreFlightCheck();  // Default interface method
        seaplane.takeOff();
        seaplane.fly();
        seaplane.move();  // Different behavior when flying
        
        // Using as Swimmable (another interface contract)
        seaplane.land();
        seaplane.dive();
        seaplane.swim();
        seaplane.move();  // Different behavior when swimming
        seaplane.surface();
        
        // ===== POLYMORPHISM WITH ABSTRACTIONS =====
        System.out.println("\n=== POLYMORPHIC BEHAVIOR ===");
        
        // Can reference concrete objects through abstract parent
        AbstractVehicle vehicle1 = new SportsCar("Lamborghini", 2024);
        AbstractVehicle vehicle2 = new AmphibiousVehicle("Duck Boat", 2024);
        
        // Same method call, different implementations
        vehicle1.startEngine();  // Sports car implementation
        vehicle2.startEngine();  // Amphibious vehicle implementation
        
        // Can reference through interface contract
        Flyable flyingMachine = new AmphibiousVehicle("Flying Car", 2024);
        flyingMachine.takeOff();
        flyingMachine.fly();
        
        // ===== USING INTERFACE STATIC METHODS =====
        System.out.println("\nSafe altitude check: " + Flyable.isSafeAltitude(30000));
        System.out.println("Safe altitude check: " + Flyable.isSafeAltitude(60000));
        
        // ===== ACCESSING INTERFACE CONSTANTS =====
        System.out.println("Maximum flying altitude: " + Flyable.MAX_ALTITUDE + " feet");
        System.out.println("Water density: " + Swimmable.WATER_DENSITY + " kg/m³");
    }
}

/*
 * SACRED TRUTHS REVEALED IN THIS SYNTAX:
 * 
 * 1. Abstract classes blend concrete and abstract - shared implementation with enforced contracts
 * 2. Interfaces are pure contracts - define capabilities without implementation
 * 3. A class can extend ONE abstract class but implement MULTIPLE interfaces
 * 4. Abstract methods MUST be implemented by concrete subclasses
 * 5. Interface methods are implicitly public abstract (unless default/static)
 * 6. Interface variables are implicitly public static final (constants)
 * 7. Default methods provide shared implementation in interfaces
 * 8. Static methods in interfaces provide utility functions
 * 9. Both forms enable powerful polymorphism and flexible design
 * 10. Abstraction enables coding to contracts rather than concrete implementations
 * 
 * "Master these forms, and you master the art of defining what must be 
 *  without constraining how it should be accomplished."
 */
