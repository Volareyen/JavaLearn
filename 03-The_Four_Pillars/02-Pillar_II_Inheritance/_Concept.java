/**
 * THE SACRED RUNE OF SYNTAX: Inheritance - The Second Pillar
 * 
 * This file demonstrates the pure syntax and mechanics of Inheritance
 * in its most essential forms. Study these patterns, for they are the 
 * building blocks of code reusability and the "IS-A" relationship.
 * 
 * Inheritance = extends + super + @Override + protected + IS-A relationship
 */

// DEMONSTRATION 1: Basic Inheritance with extends keyword

/**
 * PARENT CLASS (Superclass): The ancestral wisdom
 * Contains common fields and methods that children will inherit
 */
class Animal {
    // PROTECTED: Available to subclasses
    protected String species;
    protected int age;
    protected double weight;
    
    // PRIVATE: Not inherited (hidden from children)
    private String internalId;
    
    // PUBLIC: Inherited and accessible to all
    public String habitat;
    
    // CONSTRUCTOR: Can be called by children using super()
    public Animal(String species, int age) {
        this.species = species;
        this.age = age;
        this.internalId = "ID-" + System.currentTimeMillis();
        System.out.println("Animal created: " + species);
    }
    
    // METHODS: Inherited by all children
    public void breathe() {
        System.out.println(species + " is breathing");
    }
    
    public void eat() {
        System.out.println(species + " is eating");
    }
    
    public void sleep() {
        System.out.println(species + " is sleeping");
    }
    
    // PROTECTED METHOD: Available to subclasses only
    protected void digest() {
        System.out.println("Digesting food...");
    }
    
    // GETTER for private field (controlled access)
    public String getInternalId() {
        return internalId;
    }
}

/**
 * CHILD CLASS (Subclass): Inherits from Animal and adds specialization
 */
class Dog extends Animal {  // "Dog IS-A Animal"
    
    // NEW FIELD: Specific to Dog
    private String breed;
    
    // CONSTRUCTOR: Must call parent constructor using super()
    public Dog(String breed, int age) {
        super("Canine", age);  // Call Animal constructor (MUST be first line)
        this.breed = breed;
        System.out.println("Dog created: " + breed);
    }
    
    // NEW METHOD: Specific to Dog
    public void bark() {
        System.out.println(breed + " dog says: Woof! Woof!");
    }
    
    // ACCESSING INHERITED MEMBERS
    public void demonstrateInheritance() {
        // Can access protected members from parent
        System.out.println("Species: " + species);  // Protected from Animal
        System.out.println("Age: " + age);          // Protected from Animal
        
        // Can access public members from parent
        habitat = "Domestic";                       // Public from Animal
        System.out.println("Habitat: " + habitat);
        
        // Can call inherited methods
        breathe();  // From Animal
        eat();      // From Animal
        digest();   // Protected method from Animal
        
        // Cannot access private members from parent
        // System.out.println(internalId);  // ERROR: private in Animal
        
        // Must use public getter for private data
        System.out.println("ID: " + getInternalId());
    }
}

/**
 * ANOTHER CHILD CLASS: Different specialization of Animal
 */
class Cat extends Animal {  // "Cat IS-A Animal"
    
    private boolean isIndoor;
    
    public Cat(int age, boolean isIndoor) {
        super("Feline", age);  // Call Animal constructor
        this.isIndoor = isIndoor;
        System.out.println("Cat created: " + (isIndoor ? "Indoor" : "Outdoor"));
    }
    
    public void meow() {
        System.out.println("Feline cat says: Meow!");
    }
    
    public void purr() {
        System.out.println("Cat is purring contentedly");
    }
    
    public boolean isIndoor() {
        return isIndoor;  // Use the isIndoor field
    }
}

// DEMONSTRATION 2: Method Overriding with @Override annotation

/**
 * PARENT CLASS: Defines default behavior
 */
class Vehicle {
    protected String brand;
    protected String fuel;
    
    public Vehicle(String brand, String fuel) {
        this.brand = brand;
        this.fuel = fuel;
    }
    
    // METHOD TO BE OVERRIDDEN: Default implementation
    public void start() {
        System.out.println(brand + " vehicle is starting with " + fuel);
    }
    
    public void stop() {
        System.out.println(brand + " vehicle is stopping");
    }
    
    // FINAL METHOD: Cannot be overridden
    public final void displayBrand() {
        System.out.println("Brand: " + brand);
    }
}

/**
 * CHILD CLASS: Overrides parent methods with specialized behavior
 */
class ElectricCar extends Vehicle {
    
    private int batteryLevel;
    
    public ElectricCar(String brand, int batteryLevel) {
        super(brand, "Electric");  // Call parent constructor
        this.batteryLevel = batteryLevel;
    }
    
    // OVERRIDE: Specialized starting behavior
    @Override
    public void start() {
        if (batteryLevel > 10) {
            System.out.println(brand + " electric car starting silently...");
            System.out.println("Battery level: " + batteryLevel + "%");
        } else {
            System.out.println("Cannot start - battery too low!");
        }
    }
    
    // OVERRIDE: Specialized stopping behavior
    @Override
    public void stop() {
        System.out.println(brand + " electric car stopping with regenerative braking");
        batteryLevel += 2;  // Regenerative braking adds charge
    }
    
    // NEW METHOD: Specific to electric cars
    public void charge() {
        batteryLevel = Math.min(100, batteryLevel + 20);
        System.out.println("Charging... Battery now at " + batteryLevel + "%");
    }
}

/**
 * ANOTHER CHILD: Different override approach
 */
class SportsCar extends Vehicle {
    
    private boolean turboMode;
    
    public SportsCar(String brand, boolean turboMode) {
        super(brand, "Premium Gasoline");
        this.turboMode = turboMode;
    }
    
    // OVERRIDE: High-performance starting
    @Override
    public void start() {
        System.out.println(brand + " sports car roaring to life!");
        if (turboMode) {
            System.out.println("TURBO MODE ENGAGED!");
        }
        // Call parent method for common logic
        super.start();
    }
    
    // OVERRIDE: Performance stopping
    @Override
    public void stop() {
        System.out.println(brand + " sports car stopping with performance brakes");
        super.stop();  // Call parent's stop method
    }
}

// DEMONSTRATION 3: Using super keyword in different contexts

/**
 * PARENT CLASS: Foundation with initialization and methods
 */
class Employee {
    protected String name;
    protected double baseSalary;
    protected String department;
    
    public Employee(String name, double baseSalary, String department) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.department = department;
        System.out.println("Employee created: " + name);
    }
    
    public double calculatePay() {
        return baseSalary;
    }
    
    public void displayInfo() {
        System.out.println("Employee: " + name + ", Department: " + department);
    }
    
    protected void processPayroll() {
        System.out.println("Processing payroll for " + name);
    }
}

/**
 * CHILD CLASS: Demonstrates multiple uses of super
 */
class Manager extends Employee {
    
    private double bonus;
    private int teamSize;
    
    // SUPER IN CONSTRUCTOR: Call parent constructor
    public Manager(String name, double baseSalary, String department, double bonus, int teamSize) {
        super(name, baseSalary, department);  // Call Employee constructor
        this.bonus = bonus;
        this.teamSize = teamSize;
        System.out.println("Manager created with team size: " + teamSize);
    }
    
    // SUPER IN METHOD OVERRIDE: Extend parent behavior
    @Override
    public double calculatePay() {
        double basePay = super.calculatePay();  // Call parent's calculatePay()
        return basePay + bonus;                 // Add manager-specific bonus
    }
    
    // SUPER IN METHOD OVERRIDE: Extend parent display
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call parent's displayInfo()
        System.out.println("Manager bonus: $" + bonus + ", Team size: " + teamSize);
    }
    
    // SUPER IN REGULAR METHOD: Access parent's protected method
    public void manageTeam() {
        System.out.println("Managing team of " + teamSize + " people");
        super.processPayroll();  // Call parent's protected method
    }
}

// DEMONSTRATION 4: Access Modifier Inheritance

/**
 * PARENT CLASS: Demonstrates different access levels
 */
class AccessDemo {
    private String privateField = "Private - Not inherited";
    String packageField = "Package - Inherited if same package";
    protected String protectedField = "Protected - Always inherited";
    public String publicField = "Public - Always inherited";
    
    private void privateMethod() {
        System.out.println("Private method - Not inherited");
    }
    
    void packageMethod() {
        System.out.println("Package method - Inherited if same package");
    }
    
    protected void protectedMethod() {
        System.out.println("Protected method - Always inherited");
    }
    
    public void publicMethod() {
        System.out.println("Public method - Always inherited");
    }
    
    // Internal method to use private members (for demonstration)
    public void demonstratePrivateAccess() {
        System.out.println("Private field: " + privateField);  // Use private field
        privateMethod();  // Use private method
    }
}

/**
 * CHILD CLASS: Shows what gets inherited
 */
class AccessChild extends AccessDemo {
    
    public void testAccess() {
        // CANNOT ACCESS PRIVATE MEMBERS
        // System.out.println(privateField);  // ERROR: Cannot access
        // privateMethod();                   // ERROR: Cannot access
        
        // CAN ACCESS PACKAGE MEMBERS (same package)
        System.out.println(packageField);    // OK: Same package
        packageMethod();                     // OK: Same package
        
        // CAN ACCESS PROTECTED MEMBERS (always inherited)
        System.out.println(protectedField);  // OK: Protected inherited
        protectedMethod();                   // OK: Protected inherited
        
        // CAN ACCESS PUBLIC MEMBERS (always inherited)
        System.out.println(publicField);     // OK: Public inherited
        publicMethod();                      // OK: Public inherited
    }
}

// DEMONSTRATION 5: Inheritance Chain (Multi-level inheritance)

/**
 * GRANDPARENT CLASS: Top of the hierarchy
 */
class LivingBeing {
    protected boolean isAlive = true;
    
    public void exist() {
        System.out.println("I exist as a living being");
    }
}

/**
 * PARENT CLASS: Inherits from LivingBeing
 */
class Mammal extends LivingBeing {
    protected boolean isWarmBlooded = true;
    
    public void produceWarmth() {
        System.out.println("Maintaining body temperature");
    }
    
    // Override grandparent method
    @Override
    public void exist() {
        super.exist();  // Call grandparent method
        System.out.println("I exist as a warm-blooded mammal");
    }
}

/**
 * CHILD CLASS: Inherits from entire chain
 */
class Human extends Mammal {
    private String name;
    
    public Human(String name) {
        this.name = name;
    }
    
    public void think() {
        System.out.println(name + " is thinking");
    }
    
    // Override parent method
    @Override
    public void exist() {
        super.exist();  // Call parent (which calls grandparent)
        System.out.println("I exist as " + name + ", a thinking human");
    }
    
    // Demonstrate access to entire inheritance chain
    public void demonstrateInheritance() {
        // From LivingBeing (grandparent)
        System.out.println("Am I alive? " + isAlive);
        
        // From Mammal (parent)
        System.out.println("Am I warm-blooded? " + isWarmBlooded);
        produceWarmth();
        
        // Own method
        think();
        
        // Overridden method (calls entire chain)
        exist();
    }
}

// DEMONSTRATION 6: Abstract class preview (sets up for Pillar IV)

/**
 * ABSTRACT PARENT: Cannot be instantiated, defines contract for children
 */
abstract class Shape {
    protected double x, y;
    
    public Shape(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    // CONCRETE METHOD: All children inherit this
    public void move(double newX, double newY) {
        this.x = newX;
        this.y = newY;
        System.out.println("Shape moved to (" + x + ", " + y + ")");
    }
    
    // ABSTRACT METHOD: Children MUST implement this
    public abstract double calculateArea();
    
    // ABSTRACT METHOD: Children MUST implement this
    public abstract void draw();
}

/**
 * CONCRETE CHILD: Must implement all abstract methods
 */
class Circle extends Shape {
    private double radius;
    
    public Circle(double x, double y, double radius) {
        super(x, y);  // Call abstract parent constructor
        this.radius = radius;
    }
    
    // MUST IMPLEMENT: Abstract method from parent
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    // MUST IMPLEMENT: Abstract method from parent
    @Override
    public void draw() {
        System.out.println("Drawing circle at (" + x + ", " + y + ") with radius " + radius);
    }
}

/**
 * MAIN DEMONSTRATION CLASS: Shows all inheritance concepts in action
 */
public class _Concept {
    
    public static void main(String[] args) {
        System.out.println("=== INHERITANCE CONCEPT DEMONSTRATIONS ===\n");
        
        // DEMONSTRATION 1: Basic inheritance
        System.out.println("1. BASIC INHERITANCE:");
        Dog myDog = new Dog("Golden Retriever", 3);
        myDog.demonstrateInheritance();
        myDog.bark();  // Dog-specific method
        System.out.println();
        
        Cat myCat = new Cat(2, true);
        myCat.breathe();  // Inherited from Animal
        myCat.meow();     // Cat-specific method
        System.out.println();
        
        // DEMONSTRATION 2: Method overriding
        System.out.println("2. METHOD OVERRIDING:");
        ElectricCar tesla = new ElectricCar("Tesla", 85);
        tesla.start();     // Overridden method
        tesla.stop();      // Overridden method
        tesla.charge();    // New method
        tesla.displayBrand();  // Inherited final method
        System.out.println();
        
        SportsCar ferrari = new SportsCar("Ferrari", true);
        ferrari.start();   // Overridden method
        ferrari.stop();    // Overridden method
        System.out.println();
        
        // DEMONSTRATION 3: Super keyword usage
        System.out.println("3. SUPER KEYWORD USAGE:");
        Manager manager = new Manager("Alice Johnson", 75000, "Engineering", 15000, 8);
        System.out.println("Total pay: $" + manager.calculatePay());
        manager.displayInfo();
        manager.manageTeam();
        System.out.println();
        
        // DEMONSTRATION 4: Access modifiers in inheritance
        System.out.println("4. ACCESS MODIFIERS IN INHERITANCE:");
        AccessChild accessTest = new AccessChild();
        accessTest.testAccess();
        System.out.println();
        
        // DEMONSTRATION 5: Inheritance chain
        System.out.println("5. INHERITANCE CHAIN:");
        Human human = new Human("Bob");
        human.demonstrateInheritance();
        System.out.println();
        
        // DEMONSTRATION 6: Abstract class implementation
        System.out.println("6. ABSTRACT CLASS IMPLEMENTATION:");
        Circle circle = new Circle(10, 20, 5);
        circle.move(15, 25);  // Inherited concrete method
        circle.draw();        // Implemented abstract method
        System.out.println("Area: " + circle.calculateArea());
        System.out.println();
        
        // DEMONSTRATION 7: IS-A relationship testing
        System.out.println("7. IS-A RELATIONSHIP TESTING:");
        demonstrateIsARelationship(myDog);
        demonstrateIsARelationship(myCat);
        System.out.println();
        
        System.out.println("=== INHERITANCE CONCEPTS DEMONSTRATION COMPLETE ===");
    }
    
    /**
     * Demonstrates the IS-A relationship through parameter polymorphism
     */
    public static void demonstrateIsARelationship(Animal animal) {
        System.out.println("This animal IS-A Animal: " + (animal instanceof Animal));
        animal.breathe();  // Can call Animal methods on any subclass
        
        // Type checking for specific behaviors
        if (animal instanceof Dog) {
            System.out.println("This animal IS-A Dog");
            ((Dog) animal).bark();  // Cast to access Dog-specific methods
        } else if (animal instanceof Cat) {
            System.out.println("This animal IS-A Cat");
            ((Cat) animal).meow();  // Cast to access Cat-specific methods
        }
    }
}

/**
 * KEY INHERITANCE PATTERNS TO REMEMBER:
 * 
 * 1. EXTENDS KEYWORD:
 *    class Child extends Parent { }
 *    Establishes IS-A relationship and inherits non-private members
 * 
 * 2. SUPER KEYWORD USAGE:
 *    super();                    // Call parent constructor (must be first)
 *    super.method();             // Call parent method
 *    super.field;                // Access parent field
 * 
 * 3. METHOD OVERRIDING:
 *    @Override
 *    public void method() { }    // Specialize parent behavior
 * 
 * 4. CONSTRUCTOR CHAINING:
 *    public Child() {
 *        super();                // Call parent constructor first
 *        // Child initialization
 *    }
 * 
 * 5. ACCESS INHERITANCE:
 *    private    -> NOT inherited
 *    default    -> Inherited if same package
 *    protected  -> ALWAYS inherited
 *    public     -> ALWAYS inherited
 * 
 * 6. IS-A RELATIONSHIP TEST:
 *    Use inheritance only when child IS-A parent
 *    Dog IS-A Animal ✓
 *    Car IS-A Vehicle ✓
 *    Student IS-A Person ✓
 *    Stack IS-A ArrayList ✗ (use composition instead)
 * 
 * Remember: Inheritance creates family relationships where children
 * inherit the wisdom of ancestors while adding their own unique gifts.
 * It's the foundation for code reusability and the gateway to polymorphism.
 */
