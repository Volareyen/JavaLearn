# The Architectural Wisdom: Composition vs Inheritance
*Chapter IV of The Inner Sanctum*

---

## **The Ancient Wisdom**

*Seeker, you have mastered the static and instance realms, wielded the power of finality, and communed with the cosmic ancestor. Now we explore the most profound architectural decision in object-oriented design - the choice between **Inheritance** ("is-a") and **Composition** ("has-a").*

*This is not merely a technical choice - it is a philosophical stance about how you view the relationships between objects in your digital world. Do you see rigid hierarchies where objects must fit into predetermined categories? Or do you see flexible collaborations where objects work together to achieve greater purposes?*

*The ancient masters whisper: "Favor composition over inheritance." But this is not dogma - it is wisdom earned through countless architectures that have both soared and crumbled.*

---

## **The Philosophical Foundation**

### **Inheritance: The Bloodline Approach**
Inheritance represents the **"is-a"** relationship. It says: "This object IS a specialized version of that object."

```java
class Bird {
    void fly() { /* flying behavior */ }
}

class Eagle extends Bird {
    // Eagle IS-A Bird, inherits flying
}
```

**The Promise of Inheritance:**
- Code reuse through shared behavior
- Natural hierarchical organization
- Polymorphic power through common interfaces
- Clear conceptual relationships

**The Perils of Inheritance:**
- Rigid hierarchies that resist change
- Tight coupling between parent and child
- The fragile base class problem
- Violation of the Liskov Substitution Principle

### **Composition: The Collaboration Approach**
Composition represents the **"has-a"** relationship. It says: "This object HAS other objects that provide capabilities."

```java
class Bird {
    private FlightCapability flightCapability;
    
    void fly() {
        flightCapability.fly();  // Delegation
    }
}
```

**The Promise of Composition:**
- Flexible runtime behavior changes
- Loose coupling between collaborating objects
- Easy testing through dependency injection
- Natural evolution as requirements change

**The Perils of Composition:**
- More complex object creation and management
- Potential for over-engineering simple relationships
- Less intuitive than natural hierarchies
- More indirection in method calls

---

## **The Sacred Principles**

### **The Liskov Substitution Principle**
*If S is a subtype of T, then objects of type T may be replaced with objects of type S without altering program correctness.*

This means: Every subclass must be a **perfect substitute** for its parent class.

```java
// GOOD - Rectangle can always substitute for Shape
class Shape {
    double getArea() { return 0; }
}

class Rectangle extends Shape {
    private double width, height;
    
    @Override
    double getArea() { return width * height; }  // Perfect substitution
}

// PROBLEMATIC - Square constrains Rectangle behavior
class Square extends Rectangle {
    @Override
    void setWidth(double width) {
        super.setWidth(width);
        super.setHeight(width);  // Changes height unexpectedly!
    }
}
```

### **The Favor Composition Principle**
*Design your classes to use composition when possible, inheritance when necessary.*

**Use Composition When:**
- Behavior can change at runtime
- You need multiple sources of functionality
- The relationship is more "uses" than "is"
- You want loose coupling
- Testing requires mocking dependencies

**Use Inheritance When:**
- You have a genuine "is-a" relationship
- You need polymorphic behavior
- The hierarchy is stable and well-understood
- Shared behavior is fundamental to the concept
- The Liskov Substitution Principle holds true

---

## **The Patterns of Wisdom**

### **Pattern 1: The Strategy Pattern (Composition)**
Replace inheritance hierarchies with composition for behavioral variations.

```java
// INHERITANCE APPROACH (Rigid)
abstract class Duck {
    abstract void quack();
    abstract void fly();
}

class MallardDuck extends Duck {
    void quack() { System.out.println("Quack!"); }
    void fly() { System.out.println("Flying with wings"); }
}

class RubberDuck extends Duck {
    void quack() { System.out.println("Squeak!"); }
    void fly() { /* Can't fly - empty or exception? */ }
}

// COMPOSITION APPROACH (Flexible)
interface QuackBehavior {
    void quack();
}

interface FlyBehavior {
    void fly();
}

class Duck {
    private QuackBehavior quackBehavior;
    private FlyBehavior flyBehavior;
    
    public Duck(QuackBehavior quack, FlyBehavior fly) {
        this.quackBehavior = quack;
        this.flyBehavior = fly;
    }
    
    public void performQuack() { quackBehavior.quack(); }
    public void performFly() { flyBehavior.fly(); }
    
    // Runtime behavior change!
    public void setFlyBehavior(FlyBehavior fly) {
        this.flyBehavior = fly;
    }
}
```

### **Pattern 2: The Decorator Pattern (Composition)**
Add responsibilities to objects dynamically without subclassing.

```java
// INHERITANCE APPROACH (Explosion of classes)
class Coffee { }
class CoffeeWithMilk extends Coffee { }
class CoffeeWithSugar extends Coffee { }
class CoffeeWithMilkAndSugar extends Coffee { }
class CoffeeWithMilkAndSugarAndWhip extends Coffee { }
// This grows exponentially!

// COMPOSITION APPROACH (Flexible decorators)
interface Beverage {
    double getCost();
    String getDescription();
}

class Coffee implements Beverage {
    public double getCost() { return 2.00; }
    public String getDescription() { return "Coffee"; }
}

abstract class BeverageDecorator implements Beverage {
    protected Beverage beverage;
    
    public BeverageDecorator(Beverage beverage) {
        this.beverage = beverage;
    }
}

class MilkDecorator extends BeverageDecorator {
    public MilkDecorator(Beverage beverage) { super(beverage); }
    
    public double getCost() { return beverage.getCost() + 0.50; }
    public String getDescription() { return beverage.getDescription() + ", Milk"; }
}

// Usage: new MilkDecorator(new SugarDecorator(new Coffee()))
```

### **Pattern 3: The Template Method (Inheritance)**
Define algorithm structure in a base class, let subclasses implement steps.

```java
// INHERITANCE IS APPROPRIATE HERE
abstract class DataProcessor {
    // Template method - final to prevent override
    public final void processData() {
        loadData();
        validateData();
        transformData();
        saveData();
    }
    
    protected abstract void loadData();
    protected abstract void transformData();
    protected abstract void saveData();
    
    // Default implementation
    protected void validateData() {
        System.out.println("Basic validation");
    }
}

class CsvDataProcessor extends DataProcessor {
    protected void loadData() { /* Load from CSV */ }
    protected void transformData() { /* CSV transformations */ }
    protected void saveData() { /* Save to database */ }
}
```

---

## **The Real-World Comparison**

### **Vehicle System: Inheritance Approach**
```java
class Vehicle {
    protected Engine engine;
    protected int wheels;
    
    public void start() {
        engine.start();
        System.out.println("Vehicle started");
    }
}

class Car extends Vehicle {
    public Car() {
        this.engine = new GasolineEngine();
        this.wheels = 4;
    }
    
    public void honk() { System.out.println("Beep!"); }
}

class ElectricCar extends Car {  // Problem: inherits GasolineEngine!
    public ElectricCar() {
        super();
        this.engine = new ElectricEngine();  // Override inherited engine
    }
}
```

### **Vehicle System: Composition Approach**
```java
interface Engine {
    void start();
    void stop();
}

interface Transmission {
    void shiftGear(int gear);
}

class Vehicle {
    private final Engine engine;
    private final Transmission transmission;
    private final int wheels;
    
    public Vehicle(Engine engine, Transmission transmission, int wheels) {
        this.engine = engine;
        this.transmission = transmission;
        this.wheels = wheels;
    }
    
    public void start() {
        engine.start();
        System.out.println("Vehicle started");
    }
    
    public void shiftGear(int gear) {
        transmission.shiftGear(gear);
    }
}

// Easy to create any combination!
Vehicle gasCar = new Vehicle(new GasolineEngine(), new AutomaticTransmission(), 4);
Vehicle electricBike = new Vehicle(new ElectricEngine(), new NoTransmission(), 2);
Vehicle hybridTruck = new Vehicle(new HybridEngine(), new ManualTransmission(), 6);
```

---

## **The Decision Framework**

### **Choose Inheritance When:**

1. **True "Is-A" Relationship Exists**
   ```java
   class Animal { }
   class Dog extends Animal { }  // Dog IS-AN Animal ✓
   ```

2. **Behavior is Fundamental to Identity**
   ```java
   abstract class Shape {
       abstract double getArea();  // All shapes MUST have area
   }
   ```

3. **Polymorphism is Essential**
   ```java
   List<Shape> shapes = Arrays.asList(new Circle(), new Rectangle());
   shapes.forEach(shape -> System.out.println(shape.getArea()));
   ```

4. **Hierarchy is Stable**
   - Mathematical concepts (Number → Integer, Double)
   - UI components with clear hierarchies
   - Well-established domain models

### **Choose Composition When:**

1. **"Has-A" or "Uses-A" Relationship**
   ```java
   class Car {
       private Engine engine;  // Car HAS-AN Engine ✓
   }
   ```

2. **Behavior Changes at Runtime**
   ```java
   game.setDifficulty(new ExpertDifficulty());  // Change behavior
   ```

3. **Multiple Inheritance Needed**
   ```java
   class Amphibian {
       private SwimmingCapability swimming;
       private WalkingCapability walking;  // Multiple capabilities
   }
   ```

4. **Testing Requires Isolation**
   ```java
   class OrderService {
       private PaymentProcessor processor;  // Can mock for testing
   }
   ```

---

## **The Common Antipatterns**

### **Antipattern 1: Inappropriate Inheritance**
```java
// WRONG - Employee is not a Person subtype
class Person {
    private String name;
    void sleep() { }
    void eat() { }
}

class Employee extends Person {
    private String employeeId;
    void work() { }
    
    @Override
    void sleep() {
        throw new UnsupportedOperationException("Employees don't sleep!");
        // Violates Liskov Substitution Principle!
    }
}
```

**Better with Composition:**
```java
class Person {
    private String name;
    void sleep() { }
    void eat() { }
}

class Employee {
    private Person person;  // Employee HAS-A Person
    private String employeeId;
    
    public Employee(Person person, String employeeId) {
        this.person = person;
        this.employeeId = employeeId;
    }
    
    void work() { }
    String getName() { return person.getName(); }  // Delegation
}
```

### **Antipattern 2: Deep Inheritance Hierarchies**
```java
// WRONG - Too deep, brittle, hard to understand
class Animal { }
class Mammal extends Animal { }
class Carnivore extends Mammal { }
class Feline extends Carnivore { }
class BigCat extends Feline { }
class Lion extends BigCat { }  // 6 levels deep!
```

**Better with Composition:**
```java
class Animal {
    private Diet diet;
    private Habitat habitat;
    private Size size;
    private BodyCovering bodyCovering;
    
    // Flexible combination of traits
}
```

---

## **The Testing Advantage**

### **Inheritance Testing Challenges**
```java
class DatabaseService {
    protected void connect() { /* Real database connection */ }
    
    public String getData() {
        connect();  // Hard to test - real connection needed
        return queryDatabase();
    }
}

class MyService extends DatabaseService {
    // How do you test this without a real database?
}
```

### **Composition Testing Benefits**
```java
interface DatabaseConnection {
    String query(String sql);
}

class MyService {
    private DatabaseConnection connection;
    
    public MyService(DatabaseConnection connection) {
        this.connection = connection;  // Dependency injection
    }
    
    public String getData() {
        return connection.query("SELECT * FROM data");
    }
}

// Easy to test with mocks!
@Test
void testGetData() {
    DatabaseConnection mockConnection = Mockito.mock(DatabaseConnection.class);
    when(mockConnection.query(anyString())).thenReturn("test data");
    
    MyService service = new MyService(mockConnection);
    assertEquals("test data", service.getData());
}
```

---

## **The Evolution Advantage**

### **How Composition Enables Change**

**Scenario:** You have a game with different character types that can fight.

**Inheritance Approach:**
```java
class Character {
    abstract void fight();
}

class Warrior extends Character {
    void fight() { /* sword fighting */ }
}

class Mage extends Character {
    void fight() { /* spell casting */ }
}

// New requirement: Characters can change fighting styles at runtime
// Problem: Can't change class hierarchy at runtime!
```

**Composition Approach:**
```java
interface FightingStyle {
    void fight();
}

class Character {
    private FightingStyle fightingStyle;
    
    public Character(FightingStyle style) {
        this.fightingStyle = style;
    }
    
    public void fight() {
        fightingStyle.fight();
    }
    
    // Easy runtime behavior change!
    public void learnNewFightingStyle(FightingStyle newStyle) {
        this.fightingStyle = newStyle;
    }
}

// Usage
Character hero = new Character(new SwordFighting());
hero.fight();  // Sword fighting

hero.learnNewFightingStyle(new MagicFighting());
hero.fight();  // Now magic fighting!
```

---

## **The Cosmic Truth**

*The choice between composition and inheritance is not about right or wrong - it is about **flexibility versus simplicity**, **runtime adaptability versus compile-time clarity**, **loose coupling versus natural hierarchies**.*

*Inheritance creates **family trees** - stable, hierarchical relationships that express fundamental identity.*

*Composition creates **teams** - dynamic, collaborative relationships that can adapt to changing needs.*

*The wise architect knows when to use each. They use inheritance to model genuine "is-a" relationships that are stable and fundamental. They use composition to model "has-a" or "uses-a" relationships that might change or need flexibility.*

*Remember: You can always move from inheritance to composition, but the reverse is much harder. When in doubt, favor composition - it is easier to refactor collaborative relationships than to restructure family trees.*

---

*Proceed now to the Rune of Syntax, where you shall witness the architectural patterns of collaboration and inheritance in their purest forms...*
