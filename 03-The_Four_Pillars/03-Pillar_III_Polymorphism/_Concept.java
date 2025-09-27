/**
 * THE SACRED RUNE OF SYNTAX: Polymorphism - The Third Pillar
 * 
 * This file demonstrates the pure syntax and mechanics of Polymorphism
 * in its most essential forms. Study these patterns, for they are the 
 * building blocks of dynamic behavior and the Art of Many Forms.
 * 
 * Polymorphism = Runtime Dispatch + Method Overriding + Interface Implementation + Method Overloading
 */

// DEMONSTRATION 1: Runtime Polymorphism with Method Overriding

/**
 * BASE CLASS: Defines common interface for all animals
 */
abstract class Animal {
    protected String name;
    protected String species;
    
    public Animal(String name, String species) {
        this.name = name;
        this.species = species;
    }
    
    // CONCRETE METHOD: Shared by all animals
    public void eat() {
        System.out.println(name + " is eating");
    }
    
    // ABSTRACT METHOD: Must be overridden (polymorphic contract)
    public abstract void makeSound();
    public abstract void move();
    
    // METHOD TO BE OVERRIDDEN: Default behavior that can be specialized
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
    
    // GETTER METHODS
    public String getName() { return name; }
    public String getSpecies() { return species; }
}

/**
 * CONCRETE SUBCLASS: Dog implementation
 */
class Dog extends Animal {
    private String breed;
    
    public Dog(String name, String breed) {
        super(name, "Canine");  // Call parent constructor
        this.breed = breed;
    }
    
    // IMPLEMENT ABSTRACT METHOD: Dog-specific sound
    @Override
    public void makeSound() {
        System.out.println(name + " the " + breed + " says: Woof! Woof!");
    }
    
    // IMPLEMENT ABSTRACT METHOD: Dog-specific movement
    @Override
    public void move() {
        System.out.println(name + " runs on four legs");
    }
    
    // OVERRIDE CONCRETE METHOD: Specialized sleeping behavior
    @Override
    public void sleep() {
        System.out.println(name + " curls up in a cozy spot");
    }
    
    // DOG-SPECIFIC METHOD: Not polymorphic
    public void bark() {
        System.out.println(name + " is barking loudly!");
    }
}

/**
 * CONCRETE SUBCLASS: Cat implementation
 */
class Cat extends Animal {
    private boolean isIndoor;
    
    public Cat(String name, boolean isIndoor) {
        super(name, "Feline");
        this.isIndoor = isIndoor;
    }
    
    // IMPLEMENT ABSTRACT METHOD: Cat-specific sound
    @Override
    public void makeSound() {
        System.out.println(name + " the " + (isIndoor ? "house" : "outdoor") + " cat says: Meow!");
    }
    
    // IMPLEMENT ABSTRACT METHOD: Cat-specific movement
    @Override
    public void move() {
        System.out.println(name + " moves gracefully and silently");
    }
    
    // OVERRIDE CONCRETE METHOD: Cat-specific sleeping
    @Override
    public void sleep() {
        System.out.println(name + " finds a sunny spot and naps");
    }
    
    // CAT-SPECIFIC METHOD: Not polymorphic
    public void purr() {
        System.out.println(name + " is purring contentedly");
    }
}

/**
 * CONCRETE SUBCLASS: Bird implementation
 */
class Bird extends Animal {
    private boolean canFly;
    
    public Bird(String name, boolean canFly) {
        super(name, "Avian");
        this.canFly = canFly;
    }
    
    // IMPLEMENT ABSTRACT METHOD: Bird-specific sound
    @Override
    public void makeSound() {
        System.out.println(name + " the bird says: Tweet tweet!");
    }
    
    // IMPLEMENT ABSTRACT METHOD: Bird-specific movement
    @Override
    public void move() {
        if (canFly) {
            System.out.println(name + " soars through the sky");
        } else {
            System.out.println(name + " hops along the ground");
        }
    }
    
    // BIRD-SPECIFIC METHOD
    public void buildNest() {
        System.out.println(name + " is building a nest");
    }
}

// DEMONSTRATION 2: Interface-based Polymorphism

/**
 * INTERFACE: Pure contract for drawable objects
 */
interface Drawable {
    void draw();
    void setColor(String color);
    double getArea();
}

/**
 * INTERFACE: Contract for objects that can be moved
 */
interface Movable {
    void moveTo(double x, double y);
    double getX();
    double getY();
}

/**
 * CLASS: Implements multiple interfaces (multiple polymorphic contracts)
 */
class Circle implements Drawable, Movable {
    private double x, y;
    private double radius;
    private String color;
    
    public Circle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = "black";
    }
    
    // IMPLEMENT DRAWABLE INTERFACE
    @Override
    public void draw() {
        System.out.println("Drawing " + color + " circle at (" + x + ", " + y + 
                          ") with radius " + radius);
    }
    
    @Override
    public void setColor(String color) {
        this.color = color;
        System.out.println("Circle color set to " + color);
    }
    
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
    
    // IMPLEMENT MOVABLE INTERFACE
    @Override
    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
        System.out.println("Circle moved to (" + x + ", " + y + ")");
    }
    
    @Override
    public double getX() { return x; }
    
    @Override
    public double getY() { return y; }
}

/**
 * CLASS: Different implementation of same interfaces
 */
class Rectangle implements Drawable, Movable {
    private double x, y;
    private double width, height;
    private String color;
    
    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = "black";
    }
    
    // IMPLEMENT DRAWABLE INTERFACE (different implementation than Circle)
    @Override
    public void draw() {
        System.out.println("Drawing " + color + " rectangle at (" + x + ", " + y + 
                          ") with dimensions " + width + "x" + height);
    }
    
    @Override
    public void setColor(String color) {
        this.color = color;
        System.out.println("Rectangle color set to " + color);
    }
    
    @Override
    public double getArea() {
        return width * height;
    }
    
    // IMPLEMENT MOVABLE INTERFACE (same contract, different implementation)
    @Override
    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
        System.out.println("Rectangle moved to (" + x + ", " + y + ")");
    }
    
    @Override
    public double getX() { return x; }
    
    @Override
    public double getY() { return y; }
}

// DEMONSTRATION 3: Method Overloading (Compile-time Polymorphism)

/**
 * CLASS: Demonstrates method overloading
 */
class MathOperations {
    
    // OVERLOADED METHODS: Same name, different parameters
    
    // Add two integers
    public int add(int a, int b) {
        System.out.println("Adding two integers: " + a + " + " + b);
        return a + b;
    }
    
    // Add two doubles
    public double add(double a, double b) {
        System.out.println("Adding two doubles: " + a + " + " + b);
        return a + b;
    }
    
    // Add three integers
    public int add(int a, int b, int c) {
        System.out.println("Adding three integers: " + a + " + " + b + " + " + c);
        return a + b + c;
    }
    
    // Add array of integers
    public int add(int[] numbers) {
        System.out.print("Adding array of integers: ");
        int sum = 0;
        for (int num : numbers) {
            sum += num;
            System.out.print(num + " ");
        }
        System.out.println("= " + sum);
        return sum;
    }
    
    // Variable arguments (varargs) version - uses different method name to avoid conflict
    public int addVarArgs(int... numbers) {
        System.out.print("Adding variable arguments: ");
        int sum = 0;
        for (int num : numbers) {
            sum += num;
            System.out.print(num + " ");
        }
        System.out.println("= " + sum);
        return sum;
    }
    
    // String concatenation "add"
    public String add(String a, String b) {
        System.out.println("Concatenating strings: '" + a + "' + '" + b + "'");
        return a + b;
    }
}

// DEMONSTRATION 4: Polymorphic Collections and Arrays

/**
 * CLASS: Demonstrates polymorphism with collections
 */
class PolymorphicCollections {
    
    // Method that works with any Drawable objects
    public static void drawAll(Drawable[] shapes) {
        System.out.println("Drawing all shapes polymorphically:");
        for (Drawable shape : shapes) {
            shape.setColor("blue");  // Polymorphic method call
            shape.draw();           // Polymorphic method call
            System.out.println("Area: " + shape.getArea());
        }
    }
    
    // Method that works with any Movable objects
    public static void moveAll(Movable[] objects, double newX, double newY) {
        System.out.println("Moving all objects to (" + newX + ", " + newY + "):");
        for (Movable obj : objects) {
            obj.moveTo(newX, newY);  // Polymorphic method call
        }
    }
    
    // Method that works with any Animal objects
    public static void makeAllSounds(Animal[] animals) {
        System.out.println("Making all animals make sounds:");
        for (Animal animal : animals) {
            animal.makeSound();  // Polymorphic method call - different sound per animal
        }
    }
}

// DEMONSTRATION 5: Casting and instanceof

/**
 * CLASS: Demonstrates safe casting and type checking
 */
class TypeChecking {
    
    public static void processAnimal(Animal animal) {
        System.out.println("Processing animal: " + animal.getName());
        
        // Polymorphic methods work on any Animal
        animal.makeSound();  // Calls correct overridden method
        animal.move();       // Calls correct overridden method
        
        // Type checking for specific behaviors
        if (animal instanceof Dog) {
            System.out.println("This is a dog!");
            Dog dog = (Dog) animal;  // Safe downcasting
            dog.bark();              // Access Dog-specific method
        } else if (animal instanceof Cat) {
            System.out.println("This is a cat!");
            Cat cat = (Cat) animal;  // Safe downcasting
            cat.purr();              // Access Cat-specific method
        } else if (animal instanceof Bird) {
            System.out.println("This is a bird!");
            Bird bird = (Bird) animal;  // Safe downcasting
            bird.buildNest();           // Access Bird-specific method
        }
        
        // Modern pattern matching (Java 14+)
        // if (animal instanceof Dog dog) {
        //     dog.bark();  // Automatic casting and assignment
        // }
        
        System.out.println();
    }
    
    // Method demonstrating upcasting and downcasting
    public static void demonstrateCasting() {
        // UPCASTING: Child to Parent (automatic, always safe)
        Dog myDog = new Dog("Buddy", "Golden Retriever");
        Animal animal = myDog;  // Automatic upcasting Dog -> Animal
        
        // Can call Animal methods
        animal.makeSound();  // Still calls Dog's makeSound() - polymorphism!
        
        // DOWNCASTING: Parent to Child (explicit, potentially unsafe)
        if (animal instanceof Dog) {
            Dog dogAgain = (Dog) animal;  // Explicit downcasting Animal -> Dog
            dogAgain.bark();              // Now can call Dog-specific methods
        }
        
        // UNSAFE DOWNCASTING (would throw ClassCastException)
        // Cat cat = (Cat) animal;  // Runtime error - animal is actually a Dog!
    }
}

// DEMONSTRATION 6: Abstract Classes with Polymorphism

/**
 * ABSTRACT CLASS: Partial implementation with polymorphic methods
 */
abstract class Vehicle {
    protected String brand;
    protected int year;
    protected double speed;
    
    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
        this.speed = 0.0;
    }
    
    // CONCRETE METHOD: Shared implementation
    public void start() {
        System.out.println(brand + " vehicle starting...");
        speed = 0.0;
    }
    
    public void stop() {
        System.out.println(brand + " vehicle stopping...");
        speed = 0.0;
    }
    
    // ABSTRACT METHODS: Must be implemented polymorphically
    public abstract void accelerate();
    public abstract int getMaxSpeed();
    public abstract String getVehicleType();
    
    // TEMPLATE METHOD: Uses polymorphic methods
    public void performSpeedTest() {
        System.out.println("=== Speed Test for " + brand + " " + getVehicleType() + " ===");
        start();
        accelerate();
        System.out.println("Maximum speed: " + getMaxSpeed() + " km/h");
        stop();
    }
}

/**
 * CONCRETE CLASS: Car implementation
 */
class Car extends Vehicle {
    private int doors;
    
    public Car(String brand, int year, int doors) {
        super(brand, year);
        this.doors = doors;
    }
    
    @Override
    public void accelerate() {
        speed += 20;
        System.out.println(brand + " car accelerating smoothly to " + speed + " km/h");
    }
    
    @Override
    public int getMaxSpeed() {
        return 200;  // km/h
    }
    
    @Override
    public String getVehicleType() {
        return "Car (" + doors + " doors)";
    }
}

/**
 * CONCRETE CLASS: Motorcycle implementation
 */
class Motorcycle extends Vehicle {
    private boolean hasSidecar;
    
    public Motorcycle(String brand, int year, boolean hasSidecar) {
        super(brand, year);
        this.hasSidecar = hasSidecar;
    }
    
    @Override
    public void accelerate() {
        speed += 35;
        System.out.println(brand + " motorcycle accelerating rapidly to " + speed + " km/h");
    }
    
    @Override
    public int getMaxSpeed() {
        return hasSidecar ? 150 : 300;  // Sidecar reduces max speed
    }
    
    @Override
    public String getVehicleType() {
        return "Motorcycle" + (hasSidecar ? " with sidecar" : "");
    }
}

/**
 * MAIN DEMONSTRATION CLASS: Shows all polymorphism concepts in action
 */
public class _Concept {
    
    public static void main(String[] args) {
        System.out.println("=== POLYMORPHISM CONCEPT DEMONSTRATIONS ===\n");
        
        // DEMONSTRATION 1: Runtime Polymorphism with Animals
        System.out.println("1. RUNTIME POLYMORPHISM WITH INHERITANCE:");
        
        Animal[] animals = {
            new Dog("Buddy", "Golden Retriever"),
            new Cat("Whiskers", true),
            new Bird("Tweety", true),
            new Dog("Max", "German Shepherd"),
            new Cat("Shadow", false)
        };
        
        PolymorphicCollections.makeAllSounds(animals);
        System.out.println();
        
        // DEMONSTRATION 2: Interface-based Polymorphism
        System.out.println("2. INTERFACE-BASED POLYMORPHISM:");
        
        Drawable[] shapes = {
            new Circle(10, 20, 5),
            new Rectangle(0, 0, 8, 6),
            new Circle(50, 50, 3)
        };
        
        PolymorphicCollections.drawAll(shapes);
        System.out.println();
        
        // DEMONSTRATION 3: Method Overloading
        System.out.println("3. METHOD OVERLOADING (COMPILE-TIME POLYMORPHISM):");
        
        MathOperations math = new MathOperations();
        math.add(5, 3);                    // int, int
        math.add(5.5, 3.2);               // double, double
        math.add(1, 2, 3);                // int, int, int
        math.add(new int[]{1, 2, 3, 4});  // int array
        math.add("Hello", " World");       // String, String
        System.out.println();
        
        // DEMONSTRATION 4: Casting and Type Checking
        System.out.println("4. CASTING AND TYPE CHECKING:");
        
        for (Animal animal : animals) {
            TypeChecking.processAnimal(animal);
        }
        
        TypeChecking.demonstrateCasting();
        System.out.println();
        
        // DEMONSTRATION 5: Abstract Class Polymorphism
        System.out.println("5. ABSTRACT CLASS POLYMORPHISM:");
        
        Vehicle[] vehicles = {
            new Car("Toyota", 2023, 4),
            new Motorcycle("Honda", 2022, false),
            new Car("BMW", 2023, 2),
            new Motorcycle("Harley", 2021, true)
        };
        
        for (Vehicle vehicle : vehicles) {
            vehicle.performSpeedTest();  // Polymorphic template method
            System.out.println();
        }
        
        // DEMONSTRATION 6: Multiple Interface Implementation
        System.out.println("6. MULTIPLE INTERFACE IMPLEMENTATION:");
        
        Circle circle = new Circle(100, 100, 10);
        
        // Can be treated as Drawable
        Drawable drawable = circle;
        drawable.draw();
        drawable.setColor("red");
        
        // Can be treated as Movable
        Movable movable = circle;
        movable.moveTo(200, 200);
        
        // Still the same object!
        System.out.println("Same object? " + (drawable == movable));
        System.out.println();
        
        // DEMONSTRATION 7: Polymorphic Method Parameters
        System.out.println("7. POLYMORPHIC METHOD PARAMETERS:");
        
        demonstratePolymorphicParameters();
        
        System.out.println("=== POLYMORPHISM CONCEPTS DEMONSTRATION COMPLETE ===");
    }
    
    /**
     * Demonstrates how polymorphic parameters work
     */
    public static void demonstratePolymorphicParameters() {
        // Methods that accept polymorphic parameters
        
        // Accept any Animal
        processAnimal(new Dog("Rex", "Bulldog"));
        processAnimal(new Cat("Luna", true));
        
        // Accept any Drawable
        processDrawable(new Circle(0, 0, 7));
        processDrawable(new Rectangle(10, 10, 5, 8));
        
        // Accept any Vehicle
        processVehicle(new Car("Ford", 2023, 4));
        processVehicle(new Motorcycle("Yamaha", 2022, false));
    }
    
    public static void processAnimal(Animal animal) {
        System.out.println("Processing any animal:");
        animal.makeSound();  // Polymorphic call
        animal.move();       // Polymorphic call
    }
    
    public static void processDrawable(Drawable shape) {
        System.out.println("Processing any drawable shape:");
        shape.draw();        // Polymorphic call
        System.out.println("Area: " + shape.getArea());  // Polymorphic call
    }
    
    public static void processVehicle(Vehicle vehicle) {
        System.out.println("Processing any vehicle:");
        System.out.println("Type: " + vehicle.getVehicleType());    // Polymorphic call
        System.out.println("Max Speed: " + vehicle.getMaxSpeed());  // Polymorphic call
    }
}

/**
 * KEY POLYMORPHISM PATTERNS TO REMEMBER:
 * 
 * 1. RUNTIME POLYMORPHISM:
 *    Parent reference = new Child();
 *    reference.method();  // Calls Child's overridden method
 * 
 * 2. INTERFACE POLYMORPHISM:
 *    Interface reference = new ImplementingClass();
 *    reference.method();  // Calls implementation's method
 * 
 * 3. METHOD OVERLOADING:
 *    Same method name, different parameters
 *    Resolved at compile time based on parameter types
 * 
 * 4. SAFE CASTING:
 *    if (object instanceof SpecificType) {
 *        SpecificType specific = (SpecificType) object;
 *        specific.specificMethod();
 *    }
 * 
 * 5. POLYMORPHIC COLLECTIONS:
 *    BaseType[] array = {new Child1(), new Child2(), new Child3()};
 *    for (BaseType item : array) {
 *        item.method();  // Calls each child's version
 *    }
 * 
 * 6. ABSTRACT POLYMORPHISM:
 *    Abstract classes define partial implementations
 *    Children complete the implementation polymorphically
 * 
 * Remember: Polymorphism enables "one interface, many implementations"
 * - Write code once that works with many types
 * - Add new types without changing existing code
 * - Let objects reveal their true behavior at runtime
 * - Create flexible, extensible, and maintainable systems
 */
