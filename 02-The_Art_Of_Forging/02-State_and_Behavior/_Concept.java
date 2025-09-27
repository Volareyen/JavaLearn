/**
 * 🎭 STATE AND BEHAVIOR - THE RUNE OF SYNTAX
 * 
 * This sacred scroll demonstrates the pure syntax of State (fields) and 
 * Behavior (methods) in their most fundamental forms. Study how state
 * and behavior work together to create living, dynamic Objects.
 * 
 * Remember: STATE = What an object IS, BEHAVIOR = What an object CAN DO
 */

// ═══════════════════════════════════════════════════════════════════════════════
// 🎯 BASIC STATE - FIELDS THAT DEFINE WHAT AN OBJECT IS
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Simple class demonstrating various types of state (fields)
 */
class BasicStateExample {
    
    // ═══════════════════════════════════════════════════════════════════════
    // Different types of state fields
    // ═══════════════════════════════════════════════════════════════════════
    
    // Numerical state
    int age;              // Whole number state
    double height;        // Decimal number state
    long phoneNumber;     // Large whole number state
    
    // Text state
    String name;          // Text/string state
    char grade;           // Single character state
    
    // Boolean state (true/false)
    boolean isActive;     // Simple yes/no state
    boolean hasLicense;   // Another boolean state
    
    // Array state (collections of values)
    String[] hobbies;     // Array of strings
    int[] scores;         // Array of numbers
    
    // Object reference state (references to other objects)
    String address;       // Reference to a String object
    BasicStateExample friend; // Reference to another object of same type
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🚀 BASIC BEHAVIOR - METHODS THAT DEFINE WHAT AN OBJECT CAN DO
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Simple class demonstrating various types of behavior (methods)
 */
class BasicBehaviorExample {
    
    // State that behavior will work with
    String name;
    int energy;
    boolean isAwake;
    
    // ═══════════════════════════════════════════════════════════════════════
    // Different types of behavior methods
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Simple behavior - no parameters, no return value
     * Just performs an action
     */
    void sayHello() {
        System.out.println("Hello! My name is " + name);
    }
    
    /**
     * Behavior with parameters - takes input to perform action
     */
    void greet(String otherName) {
        System.out.println("Hello " + otherName + "! I'm " + name);
    }
    
    /**
     * Behavior with return value - gives back information
     */
    boolean isRested() {
        return energy > 50;  // Returns true if energy > 50, false otherwise
    }
    
    /**
     * Behavior with parameters AND return value
     */
    int calculateEnergyAfterActivity(int energyCost) {
        return energy - energyCost;
    }
    
    /**
     * Behavior that modifies state (changes the object)
     */
    void sleep() {
        energy = 100;        // Changes state
        isAwake = false;     // Changes state
        System.out.println(name + " is now sleeping and fully rested");
    }
    
    /**
     * Behavior that reads state to make decisions
     */
    void wakeUp() {
        if (!isAwake) {      // Checks current state
            isAwake = true;  // Changes state
            System.out.println(name + " wakes up with " + energy + " energy");
        } else {
            System.out.println(name + " is already awake!");
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🎭 STATE AND BEHAVIOR WORKING TOGETHER
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Complete example showing how state and behavior interact
 */
class Player {
    
    // ═══════════════════════════════════════════════════════════════════════
    // STATE - What the Player IS
    // ═══════════════════════════════════════════════════════════════════════
    
    String name;           // Player's name
    int health;           // Current health points (0-100)
    int experience;       // Experience points earned
    int level;            // Current level
    boolean isAlive;      // Whether player is alive
    String currentWeapon; // What weapon they're holding
    int gold;             // Amount of gold they have
    
    // ═══════════════════════════════════════════════════════════════════════
    // BEHAVIOR - What the Player CAN DO
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Behavior that reads state and provides information
     */
    void displayStatus() {
        System.out.println("=== PLAYER STATUS ===");
        System.out.println("Name: " + name);
        System.out.println("Health: " + health + "/100");
        System.out.println("Level: " + level);
        System.out.println("Experience: " + experience);
        System.out.println("Gold: " + gold);
        System.out.println("Weapon: " + currentWeapon);
        System.out.println("Status: " + (isAlive ? "Alive" : "Dead"));
    }
    
    /**
     * Behavior that modifies state based on parameters
     */
    void takeDamage(int damage) {
        if (isAlive) {
            health -= damage;              // Modify state
            System.out.println(name + " takes " + damage + " damage!");
            
            if (health <= 0) {             // State-dependent logic
                health = 0;                // Ensure health doesn't go negative
                isAlive = false;           // Change another state field
                System.out.println(name + " has been defeated!");
            }
        } else {
            System.out.println(name + " is already defeated!");
        }
    }
    
    /**
     * Behavior that modifies state with validation
     */
    void heal(int healAmount) {
        if (isAlive) {
            health += healAmount;          // Modify state
            if (health > 100) {            // Validation - don't exceed maximum
                health = 100;
            }
            System.out.println(name + " heals for " + healAmount + " points. Health: " + health);
        } else {
            System.out.println("Cannot heal - " + name + " is defeated!");
        }
    }
    
    /**
     * Behavior that uses state to determine if action is possible
     */
    void attack(String target) {
        if (!isAlive) {
            System.out.println(name + " cannot attack - they are defeated!");
            return;  // Exit early if state doesn't allow action
        }
        
        if (health < 10) {
            System.out.println(name + " is too weak to attack effectively!");
            return;
        }
        
        // Action is possible based on current state
        System.out.println(name + " attacks " + target + " with " + currentWeapon + "!");
        
        // Attacking costs some health (gets tired)
        health -= 5;
    }
    
    /**
     * Behavior that modifies multiple state fields
     */
    void gainExperience(int expPoints) {
        experience += expPoints;           // Modify experience state
        System.out.println(name + " gains " + expPoints + " experience!");
        
        // Check if player should level up (state-dependent behavior)
        int experienceNeededForNextLevel = level * 100;
        if (experience >= experienceNeededForNextLevel) {
            level++;                       // Modify level state
            health = 100;                  // Restore health on level up
            System.out.println("LEVEL UP! " + name + " is now level " + level + "!");
        }
    }
    
    /**
     * Behavior with return value based on state
     */
    boolean canAfford(int cost) {
        return gold >= cost;  // Return value depends on current state
    }
    
    /**
     * Behavior that uses state to make complex decisions
     */
    void buyItem(String itemName, int cost) {
        if (!canAfford(cost)) {            // Use other behavior to check state
            System.out.println(name + " cannot afford " + itemName + 
                             " (costs " + cost + ", have " + gold + ")");
            return;
        }
        
        // Can afford the item
        gold -= cost;                      // Modify state
        
        // Different behavior based on item type
        if (itemName.contains("Sword") || itemName.contains("Axe")) {
            currentWeapon = itemName;      // Modify weapon state
            System.out.println(name + " bought and equipped " + itemName);
        } else {
            System.out.println(name + " bought " + itemName);
        }
    }
    
    /**
     * Behavior that returns calculated information from state
     */
    String getPlayerRank() {
        if (level >= 50) {
            return "Master";
        } else if (level >= 25) {
            return "Expert";
        } else if (level >= 10) {
            return "Skilled";
        } else if (level >= 5) {
            return "Novice";
        } else {
            return "Beginner";
        }
    }
    
    /**
     * Behavior that resets state to starting values
     */
    void respawn() {
        if (!isAlive) {
            isAlive = true;                // Reset alive state
            health = 50;                   // Reset health (but not to full)
            currentWeapon = "Fists";       // Reset weapon
            // Keep experience and level (progress is maintained)
            System.out.println(name + " has respawned!");
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🎪 DEMONSTRATION OF STATE AND BEHAVIOR INTERACTION
// ═══════════════════════════════════════════════════════════════════════════════

class StateAndBehaviorDemo {
    public static void main(String[] args) {
        
        // ═══════════════════════════════════════════════════════════════════════
        // Creating objects and setting initial state
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("=== CREATING PLAYER OBJECTS ===\n");
        
        // Create first player object
        Player hero = new Player();
        hero.name = "Sir Lancelot";
        hero.health = 100;
        hero.experience = 0;
        hero.level = 1;
        hero.isAlive = true;
        hero.currentWeapon = "Iron Sword";
        hero.gold = 50;
        
        // Create second player object with different state
        Player villain = new Player();
        villain.name = "Dark Wizard";
        villain.health = 80;
        villain.experience = 500;
        villain.level = 5;
        villain.isAlive = true;
        villain.currentWeapon = "Magic Staff";
        villain.gold = 200;
        
        // ═══════════════════════════════════════════════════════════════════════
        // Demonstrating behavior that reads state
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("=== INITIAL STATUS ===\n");
        hero.displayStatus();
        System.out.println();
        villain.displayStatus();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // Demonstrating behavior that modifies state
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("=== COMBAT SIMULATION ===\n");
        
        // Hero attacks villain
        hero.attack("Dark Wizard");
        System.out.println("Hero health after attack: " + hero.health);
        System.out.println();
        
        // Villain takes damage
        villain.takeDamage(25);
        System.out.println("Villain health: " + villain.health);
        System.out.println();
        
        // Villain heals
        villain.heal(15);
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // Demonstrating state-dependent behavior
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("=== SHOPPING SIMULATION ===\n");
        
        // Check if hero can afford expensive item
        System.out.println("Can hero afford Dragon Sword (100 gold)? " + hero.canAfford(100));
        
        // Try to buy something affordable
        hero.buyItem("Health Potion", 30);
        System.out.println("Hero gold after purchase: " + hero.gold);
        System.out.println();
        
        // Try to buy something too expensive
        hero.buyItem("Dragon Sword", 100);
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // Demonstrating complex state changes
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("=== EXPERIENCE AND LEVELING ===\n");
        
        // Hero gains experience
        hero.gainExperience(80);
        System.out.println("Hero level: " + hero.level + ", Experience: " + hero.experience);
        System.out.println();
        
        // More experience to trigger level up
        hero.gainExperience(50);
        System.out.println("Hero level: " + hero.level + ", Experience: " + hero.experience);
        System.out.println("Hero health after level up: " + hero.health);
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // Demonstrating calculated behavior based on state
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("=== PLAYER RANKINGS ===\n");
        
        System.out.println(hero.name + " rank: " + hero.getPlayerRank());
        System.out.println(villain.name + " rank: " + villain.getPlayerRank());
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // Demonstrating death and respawn mechanics
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("=== DEATH AND RESPAWN ===\n");
        
        // Deal massive damage to hero
        hero.takeDamage(150);  // More than current health
        System.out.println("Is hero alive? " + hero.isAlive);
        
        // Try to attack while dead
        hero.attack("Dark Wizard");
        
        // Respawn
        hero.respawn();
        System.out.println("Hero health after respawn: " + hero.health);
        System.out.println("Is hero alive? " + hero.isAlive);
        
        // Final status
        System.out.println("\n=== FINAL STATUS ===\n");
        hero.displayStatus();
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🔬 ADVANCED STATE AND BEHAVIOR PATTERNS
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Advanced examples showing sophisticated state-behavior relationships
 */
class AdvancedPatterns {
    
    // ═══════════════════════════════════════════════════════════════════════
    // STATE VALIDATION - Ensuring state remains valid
    // ═══════════════════════════════════════════════════════════════════════
    
    class BankAccount {
        private double balance;  // Note: we'll learn about 'private' later
        
        // Behavior that validates state changes
        void deposit(double amount) {
            if (amount > 0) {                    // Validate input
                balance += amount;               // Safe state change
                System.out.println("Deposited $" + amount + ". Balance: $" + balance);
            } else {
                System.out.println("Cannot deposit negative or zero amount");
            }
        }
        
        void withdraw(double amount) {
            if (amount > 0 && amount <= balance) { // Validate input and state
                balance -= amount;                 // Safe state change
                System.out.println("Withdrew $" + amount + ". Balance: $" + balance);
            } else {
                System.out.println("Invalid withdrawal amount");
            }
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════════
    // STATE TRANSITIONS - Objects that move through defined states
    // ═══════════════════════════════════════════════════════════════════════
    
    class OrderStatus {
        String status;  // "PENDING", "CONFIRMED", "SHIPPED", "DELIVERED"
        
        void confirmOrder() {
            if (status.equals("PENDING")) {
                status = "CONFIRMED";
                System.out.println("Order confirmed");
            } else {
                System.out.println("Cannot confirm order in " + status + " state");
            }
        }
        
        void shipOrder() {
            if (status.equals("CONFIRMED")) {
                status = "SHIPPED";
                System.out.println("Order shipped");
            } else {
                System.out.println("Cannot ship order in " + status + " state");
            }
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════════
    // CALCULATED STATE - State computed from other state
    // ═══════════════════════════════════════════════════════════════════════
    
    class Circle {
        double radius;  // Stored state
        
        // Calculated state - computed when needed, not stored
        double getArea() {
            return Math.PI * radius * radius;
        }
        
        double getCircumference() {
            return 2 * Math.PI * radius;
        }
        
        double getDiameter() {
            return 2 * radius;
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 📚 SYNTAX SUMMARY REFERENCE
// ═══════════════════════════════════════════════════════════════════════════════

/*
 * STATE (FIELDS) SYNTAX:
 * 
 * [access_modifier] dataType fieldName;           // Basic field
 * [access_modifier] dataType fieldName = value;   // Field with initial value
 * 
 * Examples:
 * String name;                    // String field
 * int age = 0;                   // int field with initial value
 * boolean isActive;              // boolean field
 * double[] scores = new double[5]; // Array field
 * 
 * BEHAVIOR (METHODS) SYNTAX:
 * 
 * [access_modifier] returnType methodName(parameters) {
 *     // method body
 *     return value; // if returnType is not void
 * }
 * 
 * Examples:
 * void doSomething() { }                           // No parameters, no return
 * void doSomething(int param) { }                  // With parameter, no return
 * int getValue() { return someValue; }             // No parameters, with return
 * String process(String input) { return result; } // Parameter and return
 * 
 * STATE-BEHAVIOR INTERACTION PATTERNS:
 * 
 * 1. BEHAVIOR READS STATE:
 *    if (someField > 0) { ... }
 * 
 * 2. BEHAVIOR MODIFIES STATE:
 *    someField = newValue;
 * 
 * 3. BEHAVIOR VALIDATES STATE:
 *    if (input > 0 && input <= maxValue) { someField = input; }
 * 
 * 4. STATE-DEPENDENT BEHAVIOR:
 *    if (isActive) { performAction(); } else { showError(); }
 * 
 * 5. CALCULATED STATE:
 *    return field1 + field2; // Computed from stored state
 * 
 * KEY PRINCIPLES:
 * - Fields define what an object IS (state)
 * - Methods define what an object CAN DO (behavior)
 * - Behavior should maintain consistent, valid state
 * - State should enable meaningful behavior
 * - Related state and behavior should be grouped together in the same class
 */
