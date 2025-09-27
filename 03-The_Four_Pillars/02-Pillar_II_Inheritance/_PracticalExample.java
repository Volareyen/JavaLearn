/**
 * THE LIVING MANUSCRIPT: Gaming Character System
 * 
 * A comprehensive demonstration of Inheritance principles through a
 * realistic role-playing game character system. This example showcases
 * inheritance hierarchies, method overriding, constructor chaining,
 * protected access, and the power of the IS-A relationship.
 * 
 * This system demonstrates:
 * - Multi-level inheritance hierarchies
 * - Method overriding for specialized behavior
 * - Constructor chaining with super()
 * - Protected members for family sharing
 * - Abstract methods for enforced specialization
 * - Polymorphic behavior through inheritance
 */

public class _PracticalExample {
    public static void main(String[] args) {
        System.out.println("=== GAMING CHARACTER SYSTEM ===");
        System.out.println("Demonstrating Inheritance - The Second Pillar\n");
        
        // DEMONSTRATION 1: Character Creation and Inheritance
        System.out.println("1. CHARACTER CREATION AND INHERITANCE:");
        
        Warrior warrior = new Warrior("Thorin", 100, 20, "Sword", 80);
        Mage mage = new Mage("Gandalf", 80, 15, "Fire", 120);
        Archer archer = new Archer("Legolas", 90, 18, "Longbow", 95);
        Paladin paladin = new Paladin("Arthur", 110, 22, "Holy Sword", 85, 50);
        
        // Display all characters
        warrior.displayInfo();
        mage.displayInfo();
        archer.displayInfo();
        paladin.displayInfo();
        
        // DEMONSTRATION 2: Inherited Methods and Specialization
        System.out.println("2. INHERITED METHODS AND SPECIALIZATION:");
        
        // All characters can perform basic actions (inherited)
        warrior.rest();        // From GameCharacter
        mage.levelUp();        // From GameCharacter
        archer.takeDamage(15); // From GameCharacter
        
        // Each character has specialized attacks (overridden)
        warrior.attack();      // Warrior's specialized attack
        mage.attack();         // Mage's specialized attack
        archer.attack();       // Archer's specialized attack
        paladin.attack();      // Paladin's specialized attack
        
        // DEMONSTRATION 3: Constructor Chaining and Super Usage
        System.out.println("3. CONSTRUCTOR CHAINING AND SUPER USAGE:");
        
        // Create characters with different constructors
        Warrior basicWarrior = new Warrior("Conan");  // Uses default values
        Mage powerfulMage = new Mage("Merlin", 150, 25, "Lightning", 200);
        
        basicWarrior.displayInfo();
        powerfulMage.displayInfo();
        
        // DEMONSTRATION 4: Protected Members and Family Access
        System.out.println("4. PROTECTED MEMBERS AND FAMILY ACCESS:");
        
        // Characters can access protected members from parent
        warrior.demonstrateProtectedAccess();
        mage.demonstrateProtectedAccess();
        
        // DEMONSTRATION 5: Polymorphic Behavior Through Inheritance
        System.out.println("5. POLYMORPHIC BEHAVIOR THROUGH INHERITANCE:");
        
        // Array of GameCharacter references (polymorphism preview)
        GameCharacter[] party = {warrior, mage, archer, paladin};
        
        System.out.println("=== PARTY COMBAT SIMULATION ===");
        for (GameCharacter character : party) {
            character.attack();  // Each calls their own overridden attack()
            character.useSpecialAbility();  // Each has unique special ability
        }
        
        // DEMONSTRATION 6: Equipment System with Inheritance
        System.out.println("6. EQUIPMENT SYSTEM WITH INHERITANCE:");
        
        Equipment sword = new Weapon("Excalibur", 50, "Sword", 25);
        Equipment armor = new Armor("Dragon Scale Mail", 100, "Heavy", 30);
        Equipment potion = new Consumable("Health Potion", 10, "Healing", 50);
        
        sword.displayInfo();
        armor.displayInfo();
        potion.displayInfo();
        
        // Equip items (polymorphic usage)
        warrior.equipItem(sword);
        warrior.equipItem(armor);
        mage.equipItem(potion);
        
        // DEMONSTRATION 7: Advanced Combat with Inheritance
        System.out.println("7. ADVANCED COMBAT WITH INHERITANCE:");
        
        BattleSystem battle = new BattleSystem();
        battle.simulateBattle(warrior, mage);
        
        // DEMONSTRATION 8: Character Progression and Specialization
        System.out.println("8. CHARACTER PROGRESSION AND SPECIALIZATION:");
        
        // Level up characters and see specialized growth
        warrior.gainExperience(150);
        mage.gainExperience(200);
        archer.gainExperience(175);
        
        System.out.println("After gaining experience:");
        warrior.displayInfo();
        mage.displayInfo();
        archer.displayInfo();
        
        System.out.println("=== INHERITANCE DEMONSTRATION COMPLETE ===");
        System.out.println("All character classes successfully inherit and specialize!");
    }
}

/**
 * BASE CLASS: GameCharacter - The ancestral foundation
 * All game characters inherit from this base class
 */
abstract class GameCharacter {
    // PROTECTED FIELDS: Available to all subclasses
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int attackPower;
    protected int level;
    protected int experience;
    protected boolean isAlive;
    
    // PRIVATE FIELD: Not inherited (internal use only)
    private String characterId;
    
    // CONSTRUCTOR: Base initialization for all characters
    public GameCharacter(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.attackPower = attackPower;
        this.level = 1;
        this.experience = 0;
        this.isAlive = true;
        this.characterId = "CHAR-" + System.currentTimeMillis();
        
        System.out.println("Game character created: " + name);
    }
    
    // CONCRETE METHODS: Inherited by all children
    public void rest() {
        health = maxHealth;
        System.out.println(name + " rests and recovers full health (" + health + " HP)");
    }
    
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            isAlive = false;
            System.out.println(name + " has been defeated!");
        } else {
            System.out.println(name + " takes " + damage + " damage. Health: " + health + "/" + maxHealth);
        }
    }
    
    public void levelUp() {
        level++;
        maxHealth += 10;
        health = maxHealth;
        attackPower += 5;
        System.out.println(name + " levels up to level " + level + "!");
        System.out.println("Health increased to " + maxHealth + ", Attack power: " + attackPower);
    }
    
    public void gainExperience(int exp) {
        experience += exp;
        System.out.println(name + " gains " + exp + " experience (Total: " + experience + ")");
        
        // Level up every 100 experience points
        while (experience >= 100) {
            experience -= 100;
            levelUp();
        }
    }
    
    // PROTECTED METHOD: Available to subclasses for internal operations
    protected void logAction(String action) {
        System.out.println("[" + characterId + "] " + name + ": " + action);
    }
    
    // GETTER METHODS: Public access to protected data
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getAttackPower() { return attackPower; }
    public int getLevel() { return level; }
    public boolean isAlive() { return isAlive; }
    
    // ABSTRACT METHODS: Must be implemented by all children
    public abstract void attack();
    public abstract void useSpecialAbility();
    public abstract void displayInfo();
    
    // METHOD TO BE OVERRIDDEN: Default behavior that children can specialize
    public void equipItem(Equipment item) {
        System.out.println(name + " equips " + item.getName());
        logAction("Equipped " + item.getName());
    }
}

/**
 * WARRIOR CLASS: Inherits from GameCharacter and specializes for melee combat
 */
class Warrior extends GameCharacter {
    // WARRIOR-SPECIFIC FIELDS
    protected String weaponType;  // Protected so Paladin can access it
    private int strength;
    
    // CONSTRUCTOR: Calls parent constructor and adds warrior-specific initialization
    public Warrior(String name, int health, int attackPower, String weaponType, int strength) {
        super(name, health, attackPower);  // Call parent constructor
        this.weaponType = weaponType;
        this.strength = strength;
        System.out.println("Warrior specialization: " + weaponType + " wielder");
    }
    
    // CONVENIENCE CONSTRUCTOR: Default warrior values
    public Warrior(String name) {
        this(name, 100, 20, "Sword", 75);  // Call full constructor with defaults
    }
    
    // IMPLEMENT ABSTRACT METHOD: Warrior's attack style
    @Override
    public void attack() {
        int damage = attackPower + (strength / 5);  // Strength affects damage
        System.out.println(name + " swings their " + weaponType + " for " + damage + " damage!");
        logAction("Performed melee attack with " + weaponType);
    }
    
    // IMPLEMENT ABSTRACT METHOD: Warrior's special ability
    @Override
    public void useSpecialAbility() {
        System.out.println(name + " uses BERSERKER RAGE! Attack power doubled for next attack!");
        int originalAttack = attackPower;
        attackPower *= 2;
        attack();  // Perform enhanced attack
        attackPower = originalAttack;  // Reset attack power
    }
    
    // IMPLEMENT ABSTRACT METHOD: Warrior-specific information display
    @Override
    public void displayInfo() {
        System.out.println("=== WARRIOR: " + name + " ===");
        System.out.println("Level: " + level + " | Health: " + health + "/" + maxHealth);
        System.out.println("Attack Power: " + attackPower + " | Strength: " + strength);
        System.out.println("Weapon: " + weaponType + " | Status: " + (isAlive ? "Alive" : "Defeated"));
        System.out.println();
    }
    
    // WARRIOR-SPECIFIC METHOD
    public void charge() {
        System.out.println(name + " charges forward with " + weaponType + " raised!");
        attackPower += 5;  // Temporary attack boost
    }
    
    // DEMONSTRATE PROTECTED ACCESS
    public void demonstrateProtectedAccess() {
        System.out.println("Warrior " + name + " accessing protected members:");
        System.out.println("  Protected name: " + name);           // From parent
        System.out.println("  Protected health: " + health);       // From parent
        System.out.println("  Protected level: " + level);         // From parent
        logAction("Demonstrated protected access");                 // Protected method
    }
}

/**
 * MAGE CLASS: Inherits from GameCharacter and specializes for magic combat
 */
class Mage extends GameCharacter {
    // MAGE-SPECIFIC FIELDS
    private String magicSchool;
    private int mana;
    private int maxMana;
    
    // CONSTRUCTOR: Enhanced initialization for magical characters
    public Mage(String name, int health, int attackPower, String magicSchool, int mana) {
        super(name, health, attackPower);  // Call parent constructor
        this.magicSchool = magicSchool;
        this.mana = mana;
        this.maxMana = mana;
        System.out.println("Mage specialization: " + magicSchool + " magic");
    }
    
    // OVERRIDE PARENT METHOD: Mages rest differently (restore mana too)
    @Override
    public void rest() {
        super.rest();  // Call parent's rest method
        mana = maxMana;  // Also restore mana
        System.out.println(name + " also restores mana to " + maxMana);
    }
    
    // OVERRIDE PARENT METHOD: Mages level up differently
    @Override
    public void levelUp() {
        super.levelUp();  // Call parent's levelUp
        maxMana += 20;    // Mages get more mana per level
        mana = maxMana;
        System.out.println("Mana increased to " + maxMana);
    }
    
    // IMPLEMENT ABSTRACT METHOD: Mage's magical attack
    @Override
    public void attack() {
        if (mana >= 10) {
            int damage = attackPower + (level * 3);  // Intelligence-based damage
            System.out.println(name + " casts " + magicSchool + " spell for " + damage + " damage!");
            mana -= 10;
            logAction("Cast " + magicSchool + " spell (Mana: " + mana + "/" + maxMana + ")");
        } else {
            System.out.println(name + " is out of mana! Uses staff for basic attack.");
            System.out.println(name + " hits with staff for " + (attackPower / 2) + " damage!");
        }
    }
    
    // IMPLEMENT ABSTRACT METHOD: Mage's special ability
    @Override
    public void useSpecialAbility() {
        if (mana >= 30) {
            System.out.println(name + " casts METEOR STORM! Devastating area attack!");
            mana -= 30;
            logAction("Cast Meteor Storm (Ultimate spell)");
        } else {
            System.out.println(name + " doesn't have enough mana for special ability!");
        }
    }
    
    // IMPLEMENT ABSTRACT METHOD: Mage-specific information display
    @Override
    public void displayInfo() {
        System.out.println("=== MAGE: " + name + " ===");
        System.out.println("Level: " + level + " | Health: " + health + "/" + maxHealth);
        System.out.println("Attack Power: " + attackPower + " | Mana: " + mana + "/" + maxMana);
        System.out.println("Magic School: " + magicSchool + " | Status: " + (isAlive ? "Alive" : "Defeated"));
        System.out.println();
    }
    
    // MAGE-SPECIFIC METHOD
    public void meditate() {
        mana = Math.min(maxMana, mana + 20);
        System.out.println(name + " meditates and restores mana to " + mana + "/" + maxMana);
    }
    
    // DEMONSTRATE PROTECTED ACCESS
    public void demonstrateProtectedAccess() {
        System.out.println("Mage " + name + " accessing protected members:");
        System.out.println("  Protected experience: " + experience);  // From parent
        System.out.println("  Protected attackPower: " + attackPower); // From parent
        logAction("Demonstrated protected access from mage");           // Protected method
    }
}

/**
 * ARCHER CLASS: Another specialization of GameCharacter
 */
class Archer extends GameCharacter {
    private String bowType;
    private int accuracy;
    
    public Archer(String name, int health, int attackPower, String bowType, int accuracy) {
        super(name, health, attackPower);
        this.bowType = bowType;
        this.accuracy = accuracy;
        System.out.println("Archer specialization: " + bowType + " archer");
    }
    
    @Override
    public void attack() {
        // Accuracy affects critical hit chance
        boolean criticalHit = (Math.random() * 100) < (accuracy / 10);
        int damage = criticalHit ? attackPower * 2 : attackPower;
        
        System.out.println(name + " shoots " + bowType + " for " + damage + " damage!" + 
                          (criticalHit ? " CRITICAL HIT!" : ""));
        logAction("Shot " + bowType + (criticalHit ? " (Critical)" : ""));
    }
    
    @Override
    public void useSpecialAbility() {
        System.out.println(name + " uses MULTI-SHOT! Fires three arrows simultaneously!");
        for (int i = 0; i < 3; i++) {
            System.out.println("  Arrow " + (i + 1) + " hits for " + (attackPower / 2) + " damage!");
        }
    }
    
    @Override
    public void displayInfo() {
        System.out.println("=== ARCHER: " + name + " ===");
        System.out.println("Level: " + level + " | Health: " + health + "/" + maxHealth);
        System.out.println("Attack Power: " + attackPower + " | Accuracy: " + accuracy);
        System.out.println("Bow Type: " + bowType + " | Status: " + (isAlive ? "Alive" : "Defeated"));
        System.out.println();
    }
}

/**
 * PALADIN CLASS: Multi-level inheritance (Paladin extends Warrior)
 */
class Paladin extends Warrior {
    private int holyPower;
    
    public Paladin(String name, int health, int attackPower, String weaponType, int strength, int holyPower) {
        super(name, health, attackPower, weaponType, strength);  // Call Warrior constructor
        this.holyPower = holyPower;
        System.out.println("Paladin blessed with " + holyPower + " holy power");
    }
    
    // OVERRIDE WARRIOR'S ATTACK: Add holy damage
    @Override
    public void attack() {
        super.attack();  // Call Warrior's attack
        int holyDamage = holyPower / 10;
        System.out.println("  + " + holyDamage + " holy damage!");
        logAction("Added holy damage to attack");
    }
    
    // OVERRIDE WARRIOR'S SPECIAL: Holy version of berserker rage
    @Override
    public void useSpecialAbility() {
        System.out.println(name + " calls upon DIVINE BLESSING! Holy power enhanced!");
        holyPower += 10;
        attack();  // Enhanced attack with more holy power
    }
    
    // PALADIN-SPECIFIC METHOD
    public void heal(GameCharacter target) {
        int healing = holyPower / 5;
        target.health = Math.min(target.maxHealth, target.health + healing);
        System.out.println(name + " heals " + target.name + " for " + healing + " HP!");
        holyPower -= 5;  // Healing costs holy power
    }
    
    @Override
    public void displayInfo() {
        System.out.println("=== PALADIN: " + name + " ===");
        System.out.println("Level: " + level + " | Health: " + health + "/" + maxHealth);
        System.out.println("Attack Power: " + attackPower + " | Holy Power: " + holyPower);
        System.out.println("Weapon: " + weaponType + " | Status: " + (isAlive ? "Alive" : "Defeated"));
        System.out.println();
    }
}

/**
 * EQUIPMENT HIERARCHY: Demonstrates inheritance in a different domain
 */
abstract class Equipment {
    protected String name;
    protected int value;
    protected String description;
    
    public Equipment(String name, int value, String description) {
        this.name = name;
        this.value = value;
        this.description = description;
    }
    
    public String getName() { return name; }
    public int getValue() { return value; }
    
    // Abstract method - each equipment type displays differently
    public abstract void displayInfo();
    
    // Template method - common behavior with specialized parts
    public void use() {
        System.out.println("Using " + name + "...");
        performUseAction();  // Specialized behavior
    }
    
    protected abstract void performUseAction();
}

/**
 * WEAPON CLASS: Specialized equipment for combat
 */
class Weapon extends Equipment {
    private String weaponType;
    private int damage;
    
    public Weapon(String name, int value, String weaponType, int damage) {
        super(name, value, "A " + weaponType + " weapon");
        this.weaponType = weaponType;
        this.damage = damage;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("WEAPON: " + name + " (" + weaponType + ")");
        System.out.println("  Damage: " + damage + " | Value: " + value + " gold");
        System.out.println("  " + description);
    }
    
    @Override
    protected void performUseAction() {
        System.out.println("Wielding " + name + " - ready for combat!");
    }
}

/**
 * ARMOR CLASS: Specialized equipment for protection
 */
class Armor extends Equipment {
    private String armorType;
    private int defense;
    
    public Armor(String name, int value, String armorType, int defense) {
        super(name, value, "Protective " + armorType + " armor");
        this.armorType = armorType;
        this.defense = defense;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("ARMOR: " + name + " (" + armorType + ")");
        System.out.println("  Defense: " + defense + " | Value: " + value + " gold");
        System.out.println("  " + description);
    }
    
    @Override
    protected void performUseAction() {
        System.out.println("Wearing " + name + " - defense increased!");
    }
}

/**
 * CONSUMABLE CLASS: Specialized equipment for single use
 */
class Consumable extends Equipment {
    private String effect;
    private int potency;
    
    public Consumable(String name, int value, String effect, int potency) {
        super(name, value, "Consumable item with " + effect + " effect");
        this.effect = effect;
        this.potency = potency;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("CONSUMABLE: " + name);
        System.out.println("  Effect: " + effect + " (" + potency + ") | Value: " + value + " gold");
        System.out.println("  " + description);
    }
    
    @Override
    protected void performUseAction() {
        System.out.println("Consuming " + name + " - " + effect + " effect applied!");
    }
}

/**
 * BATTLE SYSTEM: Demonstrates polymorphic usage of inherited classes
 */
class BattleSystem {
    public void simulateBattle(GameCharacter fighter1, GameCharacter fighter2) {
        System.out.println("=== BATTLE: " + fighter1.getName() + " vs " + fighter2.getName() + " ===");
        
        int round = 1;
        while (fighter1.isAlive() && fighter2.isAlive() && round <= 3) {
            System.out.println("Round " + round + ":");
            
            // Fighter 1 attacks Fighter 2
            System.out.print(fighter1.getName() + "'s turn: ");
            fighter1.attack();
            if (Math.random() > 0.5) {  // 50% chance for special ability
                fighter2.takeDamage(fighter1.getAttackPower());
            }
            
            if (!fighter2.isAlive()) break;
            
            // Fighter 2 attacks Fighter 1
            System.out.print(fighter2.getName() + "'s turn: ");
            fighter2.attack();
            if (Math.random() > 0.5) {  // 50% chance for special ability
                fighter1.takeDamage(fighter2.getAttackPower());
            }
            
            System.out.println("End of round " + round + " - " + 
                             fighter1.getName() + ": " + fighter1.getHealth() + " HP, " +
                             fighter2.getName() + ": " + fighter2.getHealth() + " HP");
            System.out.println();
            
            round++;
        }
        
        if (round > 3) {
            System.out.println("Battle ended in a draw after 3 rounds!");
        } else if (fighter1.isAlive()) {
            System.out.println(fighter1.getName() + " wins the battle!");
        } else {
            System.out.println(fighter2.getName() + " wins the battle!");
        }
        
        // Rest both fighters for next battle
        fighter1.rest();
        fighter2.rest();
        System.out.println();
    }
}

/**
 * INHERITANCE MASTERY DEMONSTRATED:
 * 
 * This gaming system showcases complete mastery of inheritance through:
 * 
 * 1. INHERITANCE HIERARCHY:
 *    - GameCharacter (abstract base)
 *    - Warrior, Mage, Archer (specialized characters)
 *    - Paladin extends Warrior (multi-level inheritance)
 *    - Equipment hierarchy (Weapon, Armor, Consumable)
 * 
 * 2. METHOD OVERRIDING:
 *    - Each character class overrides attack() with unique behavior
 *    - Special abilities implemented differently by each class
 *    - Display methods customized for each character type
 * 
 * 3. CONSTRUCTOR CHAINING:
 *    - All constructors properly call super() as first statement
 *    - Multiple constructor patterns (full, convenience, default)
 *    - Proper initialization chain from base to specialized
 * 
 * 4. PROTECTED ACCESS:
 *    - Base class fields available to all subclasses
 *    - Protected methods for internal family operations
 *    - Demonstration methods show protected access working
 * 
 * 5. ABSTRACT METHODS:
 *    - Base class defines contracts that children must fulfill
 *    - Forces consistent interface across all character types
 *    - Enables polymorphic usage while ensuring implementation
 * 
 * 6. IS-A RELATIONSHIPS:
 *    - Warrior IS-A GameCharacter ✓
 *    - Paladin IS-A Warrior (and GameCharacter) ✓
 *    - Weapon IS-A Equipment ✓
 *    - All relationships make logical sense
 * 
 * 7. CODE REUSABILITY:
 *    - Common character behavior in base class (rest, levelUp, etc.)
 *    - Specialized behavior in each subclass
 *    - Equipment hierarchy shares common structure
 *    - Battle system works with any GameCharacter type
 * 
 * The result is a flexible, extensible gaming system where:
 * - New character types can be easily added
 * - Common behavior is shared and consistent
 * - Specialized behavior is properly encapsulated
 * - Polymorphic usage enables flexible system design
 * 
 * This demonstrates that inheritance is the foundation for building
 * organized, reusable, and extensible object-oriented systems where
 * each class builds upon the wisdom of its ancestors while contributing
 * its own unique capabilities to the family tree.
 */
