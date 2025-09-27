/**
 * The Rune of Syntax: Composition vs Inheritance
 * 
 * This sacred scroll demonstrates the pure syntax and behavior patterns
 * of composition and inheritance in their elemental forms.
 * 
 * Study each implementation carefully - here lies the grammar of
 * object relationships and architectural wisdom.
 */
import java.util.*;

public class _Concept {
    
    // ═══════════════════════════════════════════════════════════════
    // INHERITANCE HIERARCHY DEMONSTRATION
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Base class demonstrating inheritance fundamentals
     */
    static abstract class Animal {
        protected String name;
        protected int age;
        protected boolean alive;
        
        public Animal(String name, int age) {
            this.name = name;
            this.age = age;
            this.alive = true;
        }
        
        // Concrete methods - inherited by all subclasses
        public void sleep() {
            System.out.println(name + " is sleeping...");
        }
        
        public void age() {
            this.age++;
            System.out.println(name + " is now " + age + " years old");
        }
        
        // Abstract method - must be implemented by subclasses
        public abstract void makeSound();
        
        // Template method - defines algorithm structure
        public final void dailyRoutine() {
            wakeUp();
            eat();
            makeSound();  // Polymorphic call
            play();
            sleep();
        }
        
        // Methods for template pattern
        protected void wakeUp() {
            System.out.println(name + " wakes up");
        }
        
        protected abstract void eat();
        protected abstract void play();
        
        // Getters
        public String getName() { return name; }
        public int getAge() { return age; }
        public boolean isAlive() { return alive; }
    }
    
    /**
     * Inheritance: Dog IS-AN Animal
     */
    static class Dog extends Animal {
        private String breed;
        
        public Dog(String name, int age, String breed) {
            super(name, age);  // Call parent constructor
            this.breed = breed;
        }
        
        @Override
        public void makeSound() {
            System.out.println(name + " barks: Woof! Woof!");
        }
        
        @Override
        protected void eat() {
            System.out.println(name + " eats dog food");
        }
        
        @Override
        protected void play() {
            System.out.println(name + " plays fetch");
        }
        
        // Additional behavior specific to Dog
        public void wagTail() {
            System.out.println(name + " wags tail happily!");
        }
        
        public String getBreed() { return breed; }
    }
    
    /**
     * Inheritance: Cat IS-AN Animal
     */
    static class Cat extends Animal {
        private boolean indoor;
        
        public Cat(String name, int age, boolean indoor) {
            super(name, age);
            this.indoor = indoor;
        }
        
        @Override
        public void makeSound() {
            System.out.println(name + " meows: Meow! Meow!");
        }
        
        @Override
        protected void eat() {
            System.out.println(name + " eats cat food");
        }
        
        @Override
        protected void play() {
            System.out.println(name + " plays with yarn");
        }
        
        // Cat-specific behavior
        public void purr() {
            System.out.println(name + " purrs contentedly");
        }
        
        public boolean isIndoor() { return indoor; }
    }
    
    // ═══════════════════════════════════════════════════════════════
    // COMPOSITION DEMONSTRATION - BUILDING FLEXIBLE SYSTEMS
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Movement behavior interface for composition
     */
    interface MovementBehavior {
        void move();
    }
    
    /**
     * Sound behavior interface for composition
     */
    interface SoundBehavior {
        void makeSound();
    }
    
    /**
     * Feeding behavior interface for composition
     */
    interface FeedingBehavior {
        void eat();
    }
    
    // Concrete behavior implementations
    static class FlyingMovement implements MovementBehavior {
        public void move() {
            System.out.println("Flying through the air with wings");
        }
    }
    
    static class WalkingMovement implements MovementBehavior {
        public void move() {
            System.out.println("Walking on legs");
        }
    }
    
    static class SwimmingMovement implements MovementBehavior {
        public void move() {
            System.out.println("Swimming through water");
        }
    }
    
    static class QuackSound implements SoundBehavior {
        public void makeSound() {
            System.out.println("Quack! Quack!");
        }
    }
    
    static class ChirpSound implements SoundBehavior {
        public void makeSound() {
            System.out.println("Chirp! Tweet!");
        }
    }
    
    static class SilentSound implements SoundBehavior {
        public void makeSound() {
            System.out.println("...");  // Silent
        }
    }
    
    static class WormFeeding implements FeedingBehavior {
        public void eat() {
            System.out.println("Eating worms and insects");
        }
    }
    
    static class SeedFeeding implements FeedingBehavior {
        public void eat() {
            System.out.println("Eating seeds and berries");
        }
    }
    
    /**
     * Bird class using composition instead of inheritance
     * Bird HAS behaviors rather than IS a specific type
     */
    static class Bird {
        private String name;
        private String species;
        
        // Composed behaviors - can change at runtime!
        private MovementBehavior movementBehavior;
        private SoundBehavior soundBehavior;
        private FeedingBehavior feedingBehavior;
        
        public Bird(String name, String species, 
                   MovementBehavior movement, SoundBehavior sound, FeedingBehavior feeding) {
            this.name = name;
            this.species = species;
            this.movementBehavior = movement;
            this.soundBehavior = sound;
            this.feedingBehavior = feeding;
        }
        
        // Delegate to composed behaviors
        public void move() {
            System.out.print(name + " is moving: ");
            movementBehavior.move();
        }
        
        public void makeSound() {
            System.out.print(name + " makes sound: ");
            soundBehavior.makeSound();
        }
        
        public void eat() {
            System.out.print(name + " is feeding: ");
            feedingBehavior.eat();
        }
        
        // Runtime behavior change - impossible with inheritance!
        public void setMovementBehavior(MovementBehavior movement) {
            this.movementBehavior = movement;
            System.out.println(name + " learned a new way to move!");
        }
        
        public void setSoundBehavior(SoundBehavior sound) {
            this.soundBehavior = sound;
            System.out.println(name + " learned a new sound!");
        }
        
        public void performDailyRoutine() {
            System.out.println("\n=== " + name + "'s Daily Routine ===");
            move();
            eat();
            makeSound();
            System.out.println(name + " rests...\n");
        }
        
        public String getName() { return name; }
        public String getSpecies() { return species; }
    }
    
    // ═══════════════════════════════════════════════════════════════
    // DECORATOR PATTERN - COMPOSITION FOR DYNAMIC ENHANCEMENT
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Base component for decoration
     */
    interface Coffee {
        double getCost();
        String getDescription();
    }
    
    /**
     * Concrete component
     */
    static class SimpleCoffee implements Coffee {
        public double getCost() {
            return 2.00;
        }
        
        public String getDescription() {
            return "Simple Coffee";
        }
    }
    
    /**
     * Base decorator using composition
     */
    static abstract class CoffeeDecorator implements Coffee {
        protected Coffee coffee;  // Composition - HAS-A Coffee
        
        public CoffeeDecorator(Coffee coffee) {
            this.coffee = coffee;
        }
        
        public double getCost() {
            return coffee.getCost();  // Delegation
        }
        
        public String getDescription() {
            return coffee.getDescription();  // Delegation
        }
    }
    
    /**
     * Concrete decorators - add functionality through composition
     */
    static class MilkDecorator extends CoffeeDecorator {
        public MilkDecorator(Coffee coffee) {
            super(coffee);
        }
        
        @Override
        public double getCost() {
            return coffee.getCost() + 0.50;  // Add milk cost
        }
        
        @Override
        public String getDescription() {
            return coffee.getDescription() + ", Milk";
        }
    }
    
    static class SugarDecorator extends CoffeeDecorator {
        public SugarDecorator(Coffee coffee) {
            super(coffee);
        }
        
        @Override
        public double getCost() {
            return coffee.getCost() + 0.25;  // Add sugar cost
        }
        
        @Override
        public String getDescription() {
            return coffee.getDescription() + ", Sugar";
        }
    }
    
    static class WhipDecorator extends CoffeeDecorator {
        public WhipDecorator(Coffee coffee) {
            super(coffee);
        }
        
        @Override
        public double getCost() {
            return coffee.getCost() + 0.75;  // Add whip cost
        }
        
        @Override
        public String getDescription() {
            return coffee.getDescription() + ", Whipped Cream";
        }
    }
    
    // ═══════════════════════════════════════════════════════════════
    // STRATEGY PATTERN - COMPOSITION FOR ALGORITHM SELECTION
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Strategy interface for sorting algorithms
     */
    interface SortStrategy {
        void sort(int[] array);
        String getName();
    }
    
    /**
     * Concrete strategies
     */
    static class BubbleSort implements SortStrategy {
        public void sort(int[] array) {
            System.out.println("Sorting with Bubble Sort...");
            // Simplified bubble sort implementation
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }
        
        public String getName() { return "Bubble Sort"; }
    }
    
    static class QuickSort implements SortStrategy {
        public void sort(int[] array) {
            System.out.println("Sorting with Quick Sort...");
            quickSort(array, 0, array.length - 1);
        }
        
        private void quickSort(int[] array, int low, int high) {
            if (low < high) {
                int pi = partition(array, low, high);
                quickSort(array, low, pi - 1);
                quickSort(array, pi + 1, high);
            }
        }
        
        private int partition(int[] array, int low, int high) {
            int pivot = array[high];
            int i = (low - 1);
            for (int j = low; j < high; j++) {
                if (array[j] < pivot) {
                    i++;
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            int temp = array[i + 1];
            array[i + 1] = array[high];
            array[high] = temp;
            return i + 1;
        }
        
        public String getName() { return "Quick Sort"; }
    }
    
    /**
     * Context class using composition to select algorithm
     */
    static class Sorter {
        private SortStrategy strategy;  // Composition - HAS-A SortStrategy
        
        public Sorter(SortStrategy strategy) {
            this.strategy = strategy;
        }
        
        public void setStrategy(SortStrategy strategy) {
            this.strategy = strategy;
            System.out.println("Switched to " + strategy.getName());
        }
        
        public void sortArray(int[] array) {
            System.out.println("Before sorting: " + Arrays.toString(array));
            strategy.sort(array);  // Delegation
            System.out.println("After sorting: " + Arrays.toString(array));
            System.out.println("Used strategy: " + strategy.getName() + "\n");
        }
    }
    
    // ═══════════════════════════════════════════════════════════════
    // PROBLEMATIC INHERITANCE EXAMPLE
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Example of problematic inheritance - Rectangle/Square problem
     */
    static class Rectangle {
        protected double width;
        protected double height;
        
        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
        
        public void setWidth(double width) {
            this.width = width;
        }
        
        public void setHeight(double height) {
            this.height = height;
        }
        
        public double getWidth() { return width; }
        public double getHeight() { return height; }
        
        public double getArea() {
            return width * height;
        }
        
        @Override
        public String toString() {
            return String.format("Rectangle(%.1f x %.1f) = %.1f", width, height, getArea());
        }
    }
    
    /**
     * Problematic inheritance - violates Liskov Substitution Principle
     */
    static class Square extends Rectangle {
        public Square(double side) {
            super(side, side);
        }
        
        @Override
        public void setWidth(double width) {
            this.width = width;
            this.height = width;  // Problem: changes height unexpectedly!
        }
        
        @Override
        public void setHeight(double height) {
            this.width = height;   // Problem: changes width unexpectedly!
            this.height = height;
        }
        
        @Override
        public String toString() {
            return String.format("Square(%.1f) = %.1f", width, getArea());
        }
    }
    
    /**
     * Better approach using composition
     */
    interface Shape {
        double getArea();
        String getDescription();
    }
    
    static class ComposedRectangle implements Shape {
        private final double width;
        private final double height;
        
        public ComposedRectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
        
        public double getArea() { return width * height; }
        public String getDescription() { return "Rectangle"; }
        public double getWidth() { return width; }
        public double getHeight() { return height; }
    }
    
    static class ComposedSquare implements Shape {
        private final double side;
        
        public ComposedSquare(double side) {
            this.side = side;
        }
        
        public double getArea() { return side * side; }
        public String getDescription() { return "Square"; }
        public double getSide() { return side; }
    }
    
    // ═══════════════════════════════════════════════════════════════
    // MAIN METHOD - COMPREHENSIVE DEMONSTRATION
    // ═══════════════════════════════════════════════════════════════
    
    public static void main(String[] args) {
        System.out.println("🏗️ THE RUNE OF SYNTAX: Composition vs Inheritance 🏗️\n");
        
        // ═══════════════════════════════════════════════════════════
        // INHERITANCE DEMONSTRATION
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("🧬 INHERITANCE DEMONSTRATION:");
        System.out.println("Creating animals using inheritance hierarchy...\n");
        
        Animal dog = new Dog("Buddy", 3, "Golden Retriever");
        Animal cat = new Cat("Whiskers", 2, true);
        
        // Polymorphism - same method call, different behavior
        System.out.println("Polymorphic behavior:");
        dog.makeSound();  // Calls Dog.makeSound()
        cat.makeSound();  // Calls Cat.makeSound()
        
        // Template method pattern
        System.out.println("\nTemplate method pattern (inheritance):");
        dog.dailyRoutine();
        
        // Specific subclass methods
        if (dog instanceof Dog) {
            ((Dog) dog).wagTail();
        }
        
        if (cat instanceof Cat) {
            ((Cat) cat).purr();
        }
        
        System.out.println("\n" + "─".repeat(50));
        
        // ═══════════════════════════════════════════════════════════
        // COMPOSITION DEMONSTRATION - FLEXIBLE BIRDS
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("\n🔧 COMPOSITION DEMONSTRATION:");
        System.out.println("Creating birds using composition...\n");
        
        // Create different birds with different behavior combinations
        Bird eagle = new Bird("Eddie", "Eagle", 
                             new FlyingMovement(), new ChirpSound(), new WormFeeding());
        
        Bird penguin = new Bird("Penny", "Penguin", 
                               new SwimmingMovement(), new QuackSound(), new WormFeeding());
        
        Bird chicken = new Bird("Chuck", "Chicken", 
                               new WalkingMovement(), new QuackSound(), new SeedFeeding());
        
        // Demonstrate composed behaviors
        System.out.println("Composed behaviors:");
        eagle.performDailyRoutine();
        penguin.performDailyRoutine();
        chicken.performDailyRoutine();
        
        // Runtime behavior change - impossible with inheritance!
        System.out.println("🔄 RUNTIME BEHAVIOR CHANGE:");
        System.out.println("Teaching penguin to walk...");
        penguin.setMovementBehavior(new WalkingMovement());
        penguin.move();
        
        System.out.println("Teaching chicken to fly...");
        chicken.setMovementBehavior(new FlyingMovement());
        chicken.move();
        
        System.out.println("\n" + "─".repeat(50));
        
        // ═══════════════════════════════════════════════════════════
        // DECORATOR PATTERN DEMONSTRATION
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("\n☕ DECORATOR PATTERN DEMONSTRATION:");
        System.out.println("Building custom coffee with composition...\n");
        
        // Start with simple coffee
        Coffee coffee = new SimpleCoffee();
        System.out.println("Base: " + coffee.getDescription() + " - $" + coffee.getCost());
        
        // Add milk using composition
        coffee = new MilkDecorator(coffee);
        System.out.println("Add milk: " + coffee.getDescription() + " - $" + coffee.getCost());
        
        // Add sugar using composition
        coffee = new SugarDecorator(coffee);
        System.out.println("Add sugar: " + coffee.getDescription() + " - $" + coffee.getCost());
        
        // Add whip using composition
        coffee = new WhipDecorator(coffee);
        System.out.println("Add whip: " + coffee.getDescription() + " - $" + coffee.getCost());
        
        // Create a different combination
        Coffee anotherCoffee = new WhipDecorator(
            new SugarDecorator(
                new SugarDecorator(  // Double sugar!
                    new SimpleCoffee()
                )
            )
        );
        System.out.println("\nAnother combination: " + anotherCoffee.getDescription() + 
                          " - $" + anotherCoffee.getCost());
        
        System.out.println("\n" + "─".repeat(50));
        
        // ═══════════════════════════════════════════════════════════
        // STRATEGY PATTERN DEMONSTRATION
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("\n🎯 STRATEGY PATTERN DEMONSTRATION:");
        System.out.println("Sorting with different algorithms using composition...\n");
        
        // Create data to sort
        int[] data1 = {64, 34, 25, 12, 22, 11, 90};
        int[] data2 = {64, 34, 25, 12, 22, 11, 90};
        
        // Create sorter with bubble sort strategy
        Sorter sorter = new Sorter(new BubbleSort());
        sorter.sortArray(data1.clone());
        
        // Change strategy at runtime
        sorter.setStrategy(new QuickSort());
        sorter.sortArray(data2.clone());
        
        System.out.println("" + "─".repeat(50));
        
        // ═══════════════════════════════════════════════════════════
        // PROBLEMATIC INHERITANCE - RECTANGLE/SQUARE
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("\n❌ PROBLEMATIC INHERITANCE EXAMPLE:");
        System.out.println("Rectangle/Square problem - violates Liskov Substitution Principle\n");
        
        demonstrateRectangleSquareProblem();
        
        System.out.println("\n✅ BETTER APPROACH WITH COMPOSITION:");
        demonstrateBetterShapeDesign();
        
        System.out.println("\n" + "─".repeat(50));
        
        // ═══════════════════════════════════════════════════════════
        // PERFORMANCE COMPARISON
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("\n⚡ PERFORMANCE AND FLEXIBILITY COMPARISON:");
        
        System.out.println("\nInheritance characteristics:");
        System.out.println("  ✅ Direct method calls (no indirection)");
        System.out.println("  ✅ Memory efficient (single object)");
        System.out.println("  ❌ Compile-time behavior binding");
        System.out.println("  ❌ Tight coupling to parent class");
        System.out.println("  ❌ Cannot change behavior at runtime");
        
        System.out.println("\nComposition characteristics:");
        System.out.println("  ✅ Runtime behavior changes");
        System.out.println("  ✅ Loose coupling between objects");
        System.out.println("  ✅ Easy to test (dependency injection)");
        System.out.println("  ✅ Multiple capability combinations");
        System.out.println("  ❌ Extra indirection (method delegation)");
        System.out.println("  ❌ More objects in memory");
        
        // ═══════════════════════════════════════════════════════════
        // FINAL SUMMARY
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("\n✨ THE ARCHITECTURAL WISDOM REVEALED! ✨");
        System.out.println("🔹 Inheritance models 'is-a' relationships with polymorphic power");
        System.out.println("🔹 Composition models 'has-a' relationships with runtime flexibility");
        System.out.println("🔹 Template method pattern leverages inheritance for algorithm structure");
        System.out.println("🔹 Strategy and Decorator patterns leverage composition for flexibility");
        System.out.println("🔹 Problematic inheritance violates Liskov Substitution Principle");
        System.out.println("🔹 Composition enables behavior changes at runtime");
        System.out.println("🔹 Choose inheritance for stable hierarchies, composition for flexibility");
        
        System.out.println("\n🏗️ Master both approaches and choose wisely for each architectural decision!");
    }
    
    /**
     * Demonstrates the Rectangle/Square inheritance problem
     */
    private static void demonstrateRectangleSquareProblem() {
        Rectangle rectangle = new Rectangle(5, 3);
        System.out.println("Original rectangle: " + rectangle);
        
        rectangle.setWidth(4);
        System.out.println("After setWidth(4): " + rectangle);
        
        // Now try with Square (should behave like Rectangle for Liskov Substitution)
        Rectangle square = new Square(5);  // Square IS-A Rectangle (inheritance)
        System.out.println("\nOriginal square: " + square);
        
        square.setWidth(4);  // This also changes height in Square!
        System.out.println("After setWidth(4): " + square + " ⚠️ Height unexpectedly changed!");
        
        // This violates expectations - client expects only width to change
        System.out.println("❌ Liskov Substitution Principle violated!");
        System.out.println("   Square cannot substitute for Rectangle in all contexts");
    }
    
    /**
     * Demonstrates better shape design using composition
     */
    private static void demonstrateBetterShapeDesign() {
        List<Shape> shapes = Arrays.asList(
            new ComposedRectangle(5, 3),
            new ComposedSquare(4)
        );
        
        System.out.println("Shapes using composition:");
        for (Shape shape : shapes) {
            System.out.println(shape.getDescription() + " - Area: " + shape.getArea());
        }
        
        // Each shape is immutable and behaves predictably
        // No unexpected side effects from method calls
        System.out.println("✅ Clean design - no surprising behavior changes");
        System.out.println("✅ Each shape is predictable and follows its contract");
    }
}
