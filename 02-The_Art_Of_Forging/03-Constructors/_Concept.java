/**
 * 🎭 CONSTRUCTORS - THE RUNE OF SYNTAX
 * 
 * This sacred scroll demonstrates the pure syntax of Constructors
 * in all their forms. Study how Constructors breathe life into Objects
 * and give them their initial state.
 * 
 * Remember: Constructors are special methods that run when Objects are born
 */

// ═══════════════════════════════════════════════════════════════════════════════
// 🏗️ BASIC CONSTRUCTOR SYNTAX
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Simple class demonstrating default constructor behavior
 */
class BasicClass {
    String name;
    int value;
    boolean flag;
    
    // Java automatically provides this invisible default constructor:
    // public BasicClass() {
    //     name = null;     // Reference fields become null
    //     value = 0;       // Numeric fields become 0
    //     flag = false;    // Boolean fields become false
    // }
}

/**
 * Class with explicit no-argument constructor
 */
class ExplicitConstructor {
    String name;
    int value;
    boolean flag;
    
    // Explicit no-argument constructor
    public ExplicitConstructor() {
        name = "Default Name";
        value = 42;
        flag = true;
        System.out.println("Object created with default values");
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🎯 PARAMETERIZED CONSTRUCTORS - THE POWER OF SPECIFIC INITIALIZATION
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Class demonstrating parameterized constructor
 */
class Student {
    String name;
    int age;
    String major;
    double gpa;
    boolean isEnrolled;
    
    // Parameterized constructor
    public Student(String studentName, int studentAge, String studentMajor) {
        name = studentName;
        age = studentAge;
        major = studentMajor;
        gpa = 0.0;           // Default value
        isEnrolled = true;   // Default value
        
        System.out.println("Student created: " + name + ", Age: " + age + ", Major: " + major);
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🎪 CONSTRUCTOR OVERLOADING - MULTIPLE PATHS TO CREATION
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Class demonstrating multiple constructors (constructor overloading)
 */
class Car {
    String make;
    String model;
    int year;
    String color;
    double price;
    boolean isElectric;
    
    // Constructor 1: No parameters (default car)
    public Car() {
        make = "Unknown";
        model = "Unknown";
        year = 2023;
        color = "White";
        price = 25000.0;
        isElectric = false;
        System.out.println("Default car created");
    }
    
    // Constructor 2: Basic information
    public Car(String carMake, String carModel) {
        make = carMake;
        model = carModel;
        year = 2023;           // Default value
        color = "White";       // Default value
        price = 25000.0;       // Default value
        isElectric = false;    // Default value
        System.out.println("Car created: " + make + " " + model);
    }
    
    // Constructor 3: Make, model, and year
    public Car(String carMake, String carModel, int carYear) {
        make = carMake;
        model = carModel;
        year = carYear;
        color = "White";       // Default value
        price = 25000.0;       // Default value
        isElectric = false;    // Default value
        System.out.println("Car created: " + year + " " + make + " " + model);
    }
    
    // Constructor 4: Full specification
    public Car(String carMake, String carModel, int carYear, String carColor, double carPrice, boolean electric) {
        make = carMake;
        model = carModel;
        year = carYear;
        color = carColor;
        price = carPrice;
        isElectric = electric;
        System.out.println("Full car specification created: " + year + " " + color + " " + make + " " + model);
        System.out.println("Price: $" + price + ", Electric: " + (electric ? "Yes" : "No"));
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🔗 CONSTRUCTOR CHAINING WITH this()
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Class demonstrating constructor chaining to avoid code duplication
 */
class BankAccount {
    String accountNumber;
    String holderName;
    double balance;
    String accountType;
    boolean isActive;
    
    // Master constructor - all others chain to this one
    public BankAccount(String number, String name, double initialBalance, String type, boolean active) {
        accountNumber = number;
        holderName = name;
        balance = initialBalance;
        accountType = type;
        isActive = active;
        
        System.out.println("Account created: " + accountNumber + " for " + holderName);
        System.out.println("Type: " + accountType + ", Balance: $" + balance + ", Active: " + isActive);
    }
    
    // Constructor with default balance and active status
    public BankAccount(String number, String name, String type) {
        this(number, name, 0.0, type, true); // Call the master constructor
    }
    
    // Constructor with default type, balance, and active status
    public BankAccount(String number, String name) {
        this(number, name, 0.0, "Checking", true); // Call the master constructor
    }
    
    // Constructor with just account number (for special cases)
    public BankAccount(String number) {
        this(number, "Account Holder", 0.0, "Checking", true); // Call the master constructor
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// ✅ CONSTRUCTOR VALIDATION - ENSURING OBJECT INTEGRITY
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Class demonstrating parameter validation in constructors
 */
class Person {
    String name;
    int age;
    String email;
    String phoneNumber;
    
    public Person(String personName, int personAge, String personEmail, String phone) {
        // Validate name
        if (personName == null || personName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        
        // Validate age
        if (personAge < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        if (personAge > 150) {
            throw new IllegalArgumentException("Age cannot exceed 150 years");
        }
        
        // Validate email (basic validation)
        if (personEmail == null || !personEmail.contains("@") || !personEmail.contains(".")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        
        // Validate phone (basic validation)
        if (phone != null && phone.length() < 10) {
            throw new IllegalArgumentException("Phone number must be at least 10 digits");
        }
        
        // If all validation passes, initialize the object
        name = personName.trim();
        age = personAge;
        email = personEmail.toLowerCase();
        phoneNumber = phone;
        
        System.out.println("Valid person created: " + name + ", Age: " + age);
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🔒 IMMUTABLE OBJECTS WITH CONSTRUCTORS
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Class demonstrating immutable object creation
 * Once created, these objects cannot be changed
 */
class Point {
    private final int x;  // final = cannot be changed after initialization
    private final int y;  // final = cannot be changed after initialization
    
    public Point(int xCoordinate, int yCoordinate) {
        x = xCoordinate;
        y = yCoordinate;
        System.out.println("Immutable point created at (" + x + ", " + y + ")");
    }
    
    // Only getters, no setters - object is immutable
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }
    
    public Point translate(int deltaX, int deltaY) {
        // Since this object is immutable, return a NEW point
        return new Point(x + deltaX, y + deltaY);
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🏭 FACTORY METHODS WITH PRIVATE CONSTRUCTORS
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Class demonstrating factory methods for meaningful object creation
 */
class Temperature {
    private final double kelvin;
    
    // Private constructor - can only be called from within this class
    private Temperature(double kelvinValue) {
        if (kelvinValue < 0) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero");
        }
        kelvin = kelvinValue;
    }
    
    // Factory methods with descriptive names
    public static Temperature fromCelsius(double celsius) {
        System.out.println("Creating temperature from Celsius: " + celsius + "°C");
        return new Temperature(celsius + 273.15);
    }
    
    public static Temperature fromFahrenheit(double fahrenheit) {
        System.out.println("Creating temperature from Fahrenheit: " + fahrenheit + "°F");
        return new Temperature((fahrenheit - 32) * 5.0/9.0 + 273.15);
    }
    
    public static Temperature fromKelvin(double kelvin) {
        System.out.println("Creating temperature from Kelvin: " + kelvin + "K");
        return new Temperature(kelvin);
    }
    
    // Conversion methods
    public double toCelsius() {
        return kelvin - 273.15;
    }
    
    public double toFahrenheit() {
        return (kelvin - 273.15) * 9.0/5.0 + 32;
    }
    
    public double toKelvin() {
        return kelvin;
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🎮 COMPLEX CONSTRUCTOR EXAMPLE - GAME CHARACTER
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Complex class showing sophisticated constructor design
 */
class GameCharacter {
    String name;
    String characterClass;
    int level;
    int health;
    int maxHealth;
    int mana;
    int maxMana;
    int strength;
    int intelligence;
    int agility;
    String[] equipment;
    
    // Constructor for brand new character
    public GameCharacter(String characterName, String charClass) {
        // Validate inputs
        if (characterName == null || characterName.trim().isEmpty()) {
            throw new IllegalArgumentException("Character name cannot be empty");
        }
        
        name = characterName.trim();
        characterClass = charClass;
        level = 1;
        equipment = new String[4]; // 4 equipment slots
        
        // Set stats based on character class
        switch (charClass.toLowerCase()) {
            case "warrior":
                maxHealth = 120;
                maxMana = 50;
                strength = 15;
                intelligence = 8;
                agility = 10;
                equipment[0] = "Iron Sword";
                equipment[1] = "Leather Armor";
                break;
                
            case "mage":
                maxHealth = 80;
                maxMana = 150;
                strength = 8;
                intelligence = 18;
                agility = 12;
                equipment[0] = "Wooden Staff";
                equipment[1] = "Cloth Robes";
                break;
                
            case "rogue":
                maxHealth = 100;
                maxMana = 75;
                strength = 12;
                intelligence = 10;
                agility = 16;
                equipment[0] = "Steel Dagger";
                equipment[1] = "Studded Leather";
                break;
                
            default:
                // Default balanced character
                maxHealth = 100;
                maxMana = 100;
                strength = 12;
                intelligence = 12;
                agility = 12;
                equipment[0] = "Basic Weapon";
                equipment[1] = "Basic Armor";
                characterClass = "Adventurer";
        }
        
        // Start at full health and mana
        health = maxHealth;
        mana = maxMana;
        
        System.out.println("New " + characterClass + " created: " + name);
        System.out.println("Level " + level + " - Health: " + health + "/" + maxHealth + 
                         ", Mana: " + mana + "/" + maxMana);
        System.out.println("Stats - STR: " + strength + ", INT: " + intelligence + ", AGI: " + agility);
    }
    
    // Constructor for loading existing character from save data
    public GameCharacter(String characterName, String charClass, int savedLevel, 
                        int currentHealth, int currentMana, int[] stats) {
        // Call main constructor first for basic setup
        this(characterName, charClass);
        
        // Override with saved data
        level = Math.max(1, savedLevel);
        health = Math.max(0, Math.min(currentHealth, maxHealth));
        mana = Math.max(0, Math.min(currentMana, maxMana));
        
        // Apply stat bonuses from leveling
        if (stats != null && stats.length >= 3) {
            strength += stats[0];
            intelligence += stats[1];
            agility += stats[2];
        }
        
        System.out.println("Loaded existing character: " + name + " (Level " + level + ")");
    }
    
    // Copy constructor - create a copy of another character
    public GameCharacter(GameCharacter other) {
        name = other.name + " (Copy)";
        characterClass = other.characterClass;
        level = other.level;
        health = other.health;
        maxHealth = other.maxHealth;
        mana = other.mana;
        maxMana = other.maxMana;
        strength = other.strength;
        intelligence = other.intelligence;
        agility = other.agility;
        
        // Deep copy equipment array
        equipment = new String[other.equipment.length];
        for (int i = 0; i < equipment.length; i++) {
            equipment[i] = other.equipment[i];
        }
        
        System.out.println("Created copy of character: " + name);
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🧪 CONSTRUCTOR DEMONSTRATION - SHOWING ALL PATTERNS IN ACTION
// ═══════════════════════════════════════════════════════════════════════════════

class ConstructorDemo {
    public static void main(String[] args) {
        
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("   CONSTRUCTOR SYNTAX DEMONSTRATION");
        System.out.println("═══════════════════════════════════════════════════════");
        
        // ═══════════════════════════════════════════════════════════════════════
        // 1. DEFAULT CONSTRUCTOR USAGE
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n--- 1. DEFAULT CONSTRUCTOR ---");
        
        BasicClass basic = new BasicClass(); // Uses invisible default constructor
        System.out.println("BasicClass created - name: " + basic.name + 
                         ", value: " + basic.value + ", flag: " + basic.flag);
        
        ExplicitConstructor explicit = new ExplicitConstructor(); // Uses explicit constructor
        
        // ═══════════════════════════════════════════════════════════════════════
        // 2. PARAMETERIZED CONSTRUCTOR USAGE
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n--- 2. PARAMETERIZED CONSTRUCTOR ---");
        
        Student student1 = new Student("Alice Johnson", 20, "Computer Science");
        Student student2 = new Student("Bob Smith", 19, "Mathematics");
        
        // ═══════════════════════════════════════════════════════════════════════
        // 3. CONSTRUCTOR OVERLOADING DEMONSTRATION
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n--- 3. CONSTRUCTOR OVERLOADING ---");
        
        Car car1 = new Car(); // Default constructor
        Car car2 = new Car("Toyota", "Camry"); // Two parameters
        Car car3 = new Car("Honda", "Civic", 2022); // Three parameters
        Car car4 = new Car("Tesla", "Model 3", 2023, "Red", 45000.0, true); // Full constructor
        
        // ═══════════════════════════════════════════════════════════════════════
        // 4. CONSTRUCTOR CHAINING DEMONSTRATION
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n--- 4. CONSTRUCTOR CHAINING ---");
        
        BankAccount account1 = new BankAccount("123456789"); // Chains to full constructor
        BankAccount account2 = new BankAccount("987654321", "John Doe"); // Chains to full constructor
        BankAccount account3 = new BankAccount("456789123", "Jane Smith", "Savings"); // Chains to full constructor
        
        // ═══════════════════════════════════════════════════════════════════════
        // 5. CONSTRUCTOR VALIDATION DEMONSTRATION
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n--- 5. CONSTRUCTOR VALIDATION ---");
        
        try {
            Person person1 = new Person("Charlie Brown", 25, "charlie@email.com", "555-123-4567");
            System.out.println("Valid person created successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating person: " + e.getMessage());
        }
        
        // Try creating invalid person
        try {
            Person invalidPerson = new Person("", -5, "invalid-email", "123");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation caught invalid person: " + e.getMessage());
        }
        
        // ═══════════════════════════════════════════════════════════════════════
        // 6. IMMUTABLE OBJECTS DEMONSTRATION
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n--- 6. IMMUTABLE OBJECTS ---");
        
        Point origin = new Point(0, 0);
        Point corner = new Point(10, 10);
        
        System.out.println("Origin distance from origin: " + origin.distanceFromOrigin());
        System.out.println("Corner distance from origin: " + corner.distanceFromOrigin());
        
        // Create new point by translating (original stays unchanged)
        Point translated = corner.translate(5, 5);
        System.out.println("Translated point: (" + translated.getX() + ", " + translated.getY() + ")");
        System.out.println("Original corner unchanged: (" + corner.getX() + ", " + corner.getY() + ")");
        
        // ═══════════════════════════════════════════════════════════════════════
        // 7. FACTORY METHODS DEMONSTRATION
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n--- 7. FACTORY METHODS ---");
        
        Temperature freezing = Temperature.fromCelsius(0);
        Temperature boiling = Temperature.fromFahrenheit(212);
        Temperature absolute = Temperature.fromKelvin(0);
        
        System.out.println("Freezing point: " + freezing.toCelsius() + "°C = " + freezing.toFahrenheit() + "°F");
        System.out.println("Boiling point: " + boiling.toCelsius() + "°C = " + boiling.toFahrenheit() + "°F");
        
        // ═══════════════════════════════════════════════════════════════════════
        // 8. COMPLEX CONSTRUCTOR DEMONSTRATION
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n--- 8. COMPLEX CONSTRUCTORS ---");
        
        GameCharacter warrior = new GameCharacter("Sir Lancelot", "Warrior");
        GameCharacter mage = new GameCharacter("Merlin", "Mage");
        
        // Load existing character
        int[] savedStats = {5, 3, 2}; // Bonus stats from leveling
        GameCharacter loadedChar = new GameCharacter("Gandalf", "Mage", 10, 75, 140, savedStats);
        
        // Create copy
        GameCharacter copyChar = new GameCharacter(warrior);
        
        // ═══════════════════════════════════════════════════════════════════════
        // SUMMARY
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("   CONSTRUCTOR PATTERNS DEMONSTRATED:");
        System.out.println("   ✅ Default constructors (implicit and explicit)");
        System.out.println("   ✅ Parameterized constructors");
        System.out.println("   ✅ Constructor overloading");
        System.out.println("   ✅ Constructor chaining with this()");
        System.out.println("   ✅ Parameter validation");
        System.out.println("   ✅ Immutable object creation");
        System.out.println("   ✅ Factory methods");
        System.out.println("   ✅ Complex initialization patterns");
        System.out.println("═══════════════════════════════════════════════════════");
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 📚 CONSTRUCTOR SYNTAX SUMMARY REFERENCE
// ═══════════════════════════════════════════════════════════════════════════════

/*
 * CONSTRUCTOR SYNTAX PATTERNS:
 * 
 * 1. DEFAULT CONSTRUCTOR (provided by Java if no constructors written):
 *    public ClassName() {
 *        // Initialize fields to default values
 *    }
 * 
 * 2. NO-ARGUMENT CONSTRUCTOR (explicit):
 *    public ClassName() {
 *        field1 = defaultValue1;
 *        field2 = defaultValue2;
 *    }
 * 
 * 3. PARAMETERIZED CONSTRUCTOR:
 *    public ClassName(Type param1, Type param2) {
 *        field1 = param1;
 *        field2 = param2;
 *    }
 * 
 * 4. CONSTRUCTOR CHAINING:
 *    public ClassName(Type param) {
 *        this(param, defaultValue); // Must be first statement
 *    }
 * 
 * 5. VALIDATION IN CONSTRUCTOR:
 *    public ClassName(Type param) {
 *        if (param == null) {
 *            throw new IllegalArgumentException("Parameter cannot be null");
 *        }
 *        field = param;
 *    }
 * 
 * 6. PRIVATE CONSTRUCTOR (for factory methods):
 *    private ClassName(Type param) {
 *        field = param;
 *    }
 *    
 *    public static ClassName createInstance(Type param) {
 *        return new ClassName(param);
 *    }
 * 
 * KEY RULES:
 * - Constructor name must match class name exactly
 * - Constructors have no return type (not even void)
 * - If you write ANY constructor, Java won't provide default constructor
 * - this() call must be first statement in constructor
 * - Use constructors to ensure objects start in valid state
 * - Validate parameters to prevent invalid objects
 * - Consider constructor overloading for flexibility
 */
