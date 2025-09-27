/**
 * 🎮 PRACTICAL EXAMPLE: VIDEO GAME CHARACTER SYSTEM
 * 
 * This living manuscript demonstrates State and Behavior through a comprehensive
 * video game character system. Here, you'll witness how Objects use STATE to
 * define what they ARE and BEHAVIOR to define what they CAN DO.
 * 
 * Key Concepts Demonstrated:
 * - Rich object state defining character attributes
 * - Complex behaviors that read and modify state
 * - State-dependent behavior (actions change based on current state)
 * - Object interaction through behavior calls
 * - Real-world modeling of game mechanics
 * - State validation and consistency maintenance
 */

// ═══════════════════════════════════════════════════════════════════════════════
// 🎮 GAMECHARACTER CLASS - COMPLETE STATE AND BEHAVIOR EXAMPLE
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * GameCharacter Class - Demonstrates comprehensive state and behavior design
 * 
 * This class models a video game character with:
 * - Rich STATE: health, mana, level, equipment, status effects, etc.
 * - Complex BEHAVIOR: combat, magic, movement, interaction, progression
 * - State-dependent actions that change based on current condition
 */
class GameCharacter {
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🎯 OBJECT STATE - What a GameCharacter IS (Complete Character Profile)
    // ═══════════════════════════════════════════════════════════════════════
    
    // Basic Identity State
    String name;              // Character's name
    String characterClass;    // "Warrior", "Mage", "Rogue", "Healer"
    int level;               // Current character level (1-100)
    
    // Health and Resource State
    int health;              // Current health points
    int maxHealth;           // Maximum health points
    int mana;                // Current mana/magic points
    int maxMana;             // Maximum mana points
    int stamina;             // Current stamina/energy
    int maxStamina;          // Maximum stamina
    
    // Core Attributes State
    int strength;            // Physical power (affects damage)
    int intelligence;        // Mental power (affects mana and spell damage)
    int agility;             // Speed and accuracy
    int defense;             // Damage reduction
    
    // Equipment State
    String weapon;           // Currently equipped weapon
    String armor;            // Currently equipped armor
    String accessory;        // Ring, amulet, etc.
    
    // Status and Condition State
    boolean isAlive;         // Whether character is alive
    boolean isInCombat;      // Whether character is fighting
    boolean isPoisoned;      // Whether character is poisoned
    boolean isBlessed;       // Whether character has blessing buff
    String currentLocation;  // Where the character is
    
    // Experience and Progression State
    int experience;          // Current experience points
    int experienceToNextLevel; // Experience needed for next level
    int skillPoints;         // Unspent skill points
    
    // Inventory and Wealth State
    int gold;                // Amount of gold coins
    String[] inventory;      // Items in character's backpack
    int inventoryCount;      // Number of items currently carried
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🚀 OBJECT BEHAVIOR - What a GameCharacter CAN DO
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Initialize a new character with starting values
     * This behavior sets up the character's initial state
     */
    void createCharacter(String charName, String charClass) {
        name = charName;
        characterClass = charClass;
        level = 1;
        
        // Set initial stats based on character class
        if (charClass.equals("Warrior")) {
            maxHealth = 120; strength = 15; intelligence = 8; agility = 10; defense = 12;
            weapon = "Iron Sword"; armor = "Leather Armor";
        } else if (charClass.equals("Mage")) {
            maxHealth = 80; strength = 8; intelligence = 18; agility = 12; defense = 8;
            maxMana = 150; weapon = "Wooden Staff"; armor = "Cloth Robes";
        } else if (charClass.equals("Rogue")) {
            maxHealth = 100; strength = 12; intelligence = 10; agility = 16; defense = 9;
            weapon = "Steel Dagger"; armor = "Studded Leather";
        } else { // Healer
            maxHealth = 90; strength = 9; intelligence = 16; agility = 11; defense = 10;
            maxMana = 130; weapon = "Healing Staff"; armor = "Priest Robes";
        }
        
        // Initialize current values to maximum
        health = maxHealth;
        mana = maxMana;
        maxStamina = 100;
        stamina = maxStamina;
        
        // Initialize other state
        isAlive = true;
        isInCombat = false;
        isPoisoned = false;
        isBlessed = false;
        currentLocation = "Starting Village";
        experience = 0;
        experienceToNextLevel = 100;
        skillPoints = 0;
        gold = 50;
        inventory = new String[20]; // Can carry 20 items
        inventoryCount = 0;
        
        System.out.println("🎮 New " + characterClass + " character created: " + name);
        System.out.println("   Starting location: " + currentLocation);
        System.out.println("   Starting equipment: " + weapon + ", " + armor);
    }
    
    /**
     * Display complete character information
     * This behavior reads state to provide comprehensive status
     */
    void displayCharacterSheet() {
        System.out.println("🎮 ═══════════════════════════════════════");
        System.out.println("   CHARACTER SHEET: " + name.toUpperCase());
        System.out.println("   ═══════════════════════════════════════");
        System.out.println("   Class: " + characterClass + " (Level " + level + ")");
        System.out.println("   Location: " + currentLocation);
        System.out.println();
        System.out.println("   VITAL STATISTICS:");
        System.out.println("     Health: " + health + "/" + maxHealth);
        if (maxMana > 0) {
            System.out.println("     Mana: " + mana + "/" + maxMana);
        }
        System.out.println("     Stamina: " + stamina + "/" + maxStamina);
        System.out.println();
        System.out.println("   ATTRIBUTES:");
        System.out.println("     Strength: " + strength + " | Intelligence: " + intelligence);
        System.out.println("     Agility: " + agility + " | Defense: " + defense);
        System.out.println();
        System.out.println("   EQUIPMENT:");
        System.out.println("     Weapon: " + weapon);
        System.out.println("     Armor: " + armor);
        if (accessory != null) {
            System.out.println("     Accessory: " + accessory);
        }
        System.out.println();
        System.out.println("   PROGRESSION:");
        System.out.println("     Experience: " + experience + "/" + (experience + experienceToNextLevel));
        System.out.println("     Skill Points: " + skillPoints);
        System.out.println("     Gold: " + gold);
        System.out.println();
        System.out.println("   STATUS:");
        System.out.println("     Alive: " + (isAlive ? "Yes" : "No"));
        System.out.println("     In Combat: " + (isInCombat ? "Yes" : "No"));
        if (isPoisoned) System.out.println("     ☠️ POISONED");
        if (isBlessed) System.out.println("     ✨ BLESSED");
        System.out.println("     Inventory: " + inventoryCount + "/20 items");
        System.out.println("   ═══════════════════════════════════════");
    }
    
    /**
     * Attack another character - behavior that uses state and modifies target's state
     */
    void attack(GameCharacter target) {
        // State-dependent behavior - check if attack is possible
        if (!isAlive) {
            System.out.println("❌ " + name + " cannot attack - character is dead!");
            return;
        }
        
        if (stamina < 10) {
            System.out.println("❌ " + name + " is too tired to attack!");
            return;
        }
        
        if (!target.isAlive) {
            System.out.println("❌ Cannot attack " + target.name + " - already defeated!");
            return;
        }
        
        // Calculate damage based on current state
        int baseDamage = strength + getWeaponDamage();
        int actualDamage = Math.max(1, baseDamage - target.defense); // Minimum 1 damage
        
        // Apply blessing bonus if blessed
        if (isBlessed) {
            actualDamage += 5;
            System.out.println("✨ " + name + "'s blessing increases damage!");
        }
        
        // Perform the attack
        stamina -= 10; // Attacking costs stamina
        isInCombat = true;
        target.isInCombat = true;
        
        System.out.println("⚔️ " + name + " attacks " + target.name + " with " + weapon + "!");
        System.out.println("   Damage dealt: " + actualDamage);
        
        // Apply damage to target
        target.takeDamage(actualDamage);
    }
    
    /**
     * Take damage - behavior that modifies health state
     */
    void takeDamage(int damage) {
        if (!isAlive) {
            return; // Dead characters can't take more damage
        }
        
        health -= damage;
        System.out.println("💥 " + name + " takes " + damage + " damage! Health: " + health + "/" + maxHealth);
        
        // Check if character dies
        if (health <= 0) {
            health = 0;
            isAlive = false;
            isInCombat = false;
            System.out.println("💀 " + name + " has been defeated!");
        } else if (health < maxHealth * 0.25) {
            System.out.println("⚠️ " + name + " is critically wounded!");
        }
    }
    
    /**
     * Cast a spell - behavior that uses mana and creates magical effects
     */
    void castSpell(String spellName, GameCharacter target) {
        // State validation
        if (!isAlive) {
            System.out.println("❌ " + name + " cannot cast spells - character is dead!");
            return;
        }
        
        if (maxMana == 0) {
            System.out.println("❌ " + name + " (" + characterClass + ") cannot cast spells!");
            return;
        }
        
        // Different spells have different mana costs and effects
        int manaCost = 0;
        
        if (spellName.equals("Fireball")) {
            manaCost = 25;
            if (mana >= manaCost) {
                mana -= manaCost;
                int spellDamage = intelligence + 10;
                System.out.println("🔥 " + name + " casts Fireball at " + target.name + "!");
                target.takeDamage(spellDamage);
            }
        } else if (spellName.equals("Heal")) {
            manaCost = 20;
            if (mana >= manaCost) {
                mana -= manaCost;
                int healAmount = intelligence + 15;
                target.heal(healAmount);
                System.out.println("✨ " + name + " casts Heal on " + target.name + "!");
            }
        } else if (spellName.equals("Poison")) {
            manaCost = 15;
            if (mana >= manaCost) {
                mana -= manaCost;
                target.applyPoison();
                System.out.println("☠️ " + name + " casts Poison on " + target.name + "!");
            }
        } else if (spellName.equals("Blessing")) {
            manaCost = 30;
            if (mana >= manaCost) {
                mana -= manaCost;
                target.applyBlessing();
                System.out.println("✨ " + name + " casts Blessing on " + target.name + "!");
            }
        } else {
            System.out.println("❌ " + name + " doesn't know the spell: " + spellName);
            return;
        }
        
        if (mana < manaCost) {
            System.out.println("❌ " + name + " doesn't have enough mana! (Need " + manaCost + ", have " + mana + ")");
        }
    }
    
    /**
     * Heal the character - behavior that restores health state
     */
    void heal(int healAmount) {
        if (!isAlive) {
            System.out.println("❌ Cannot heal " + name + " - character is dead!");
            return;
        }
        
        int oldHealth = health;
        health += healAmount;
        
        // Don't exceed maximum health
        if (health > maxHealth) {
            health = maxHealth;
        }
        
        int actualHealing = health - oldHealth;
        System.out.println("💚 " + name + " heals for " + actualHealing + " points! Health: " + health + "/" + maxHealth);
    }
    
    /**
     * Rest to recover stamina and mana - behavior that restores resources
     */
    void rest() {
        if (!isAlive) {
            System.out.println("❌ " + name + " cannot rest - character is dead!");
            return;
        }
        
        if (isInCombat) {
            System.out.println("❌ " + name + " cannot rest during combat!");
            return;
        }
        
        // Restore resources
        int staminaRestored = Math.min(30, maxStamina - stamina);
        int manaRestored = Math.min(25, maxMana - mana);
        
        stamina += staminaRestored;
        mana += manaRestored;
        
        System.out.println("😴 " + name + " rests and recovers...");
        if (staminaRestored > 0) {
            System.out.println("   Stamina restored: " + staminaRestored + " (Now: " + stamina + "/" + maxStamina + ")");
        }
        if (manaRestored > 0) {
            System.out.println("   Mana restored: " + manaRestored + " (Now: " + mana + "/" + maxMana + ")");
        }
        
        // Resting might cure poison (25% chance)
        if (isPoisoned && Math.random() < 0.25) {
            isPoisoned = false;
            System.out.println("✨ " + name + " recovers from poison while resting!");
        }
    }
    
    /**
     * Gain experience and potentially level up
     */
    void gainExperience(int expPoints) {
        if (!isAlive) {
            return; // Dead characters don't gain experience
        }
        
        experience += expPoints;
        System.out.println("📈 " + name + " gains " + expPoints + " experience!");
        
        // Check for level up
        while (experience >= experienceToNextLevel) {
            levelUp();
        }
    }
    
    /**
     * Level up the character - complex behavior that modifies multiple state values
     */
    void levelUp() {
        level++;
        experience -= experienceToNextLevel;
        experienceToNextLevel = level * 100; // Each level requires more experience
        skillPoints += 3; // Gain skill points to spend
        
        // Increase stats based on class
        if (characterClass.equals("Warrior")) {
            strength += 2; maxHealth += 15; defense += 1;
        } else if (characterClass.equals("Mage")) {
            intelligence += 3; maxMana += 20; maxHealth += 8;
        } else if (characterClass.equals("Rogue")) {
            agility += 2; strength += 1; maxHealth += 10;
        } else { // Healer
            intelligence += 2; maxMana += 15; maxHealth += 12;
        }
        
        // Restore health and mana on level up
        health = maxHealth;
        mana = maxMana;
        
        System.out.println("🎉 LEVEL UP! " + name + " is now level " + level + "!");
        System.out.println("   Health and mana fully restored!");
        System.out.println("   Gained 3 skill points (Total: " + skillPoints + ")");
    }
    
    /**
     * Move to a new location - behavior that changes location state
     */
    void moveTo(String newLocation) {
        if (!isAlive) {
            System.out.println("❌ " + name + " cannot travel - character is dead!");
            return;
        }
        
        if (isInCombat) {
            System.out.println("❌ " + name + " cannot leave during combat!");
            return;
        }
        
        if (stamina < 5) {
            System.out.println("❌ " + name + " is too tired to travel!");
            return;
        }
        
        String oldLocation = currentLocation;
        currentLocation = newLocation;
        stamina -= 5; // Traveling costs stamina
        
        System.out.println("🗺️ " + name + " travels from " + oldLocation + " to " + newLocation);
        System.out.println("   Stamina: " + stamina + "/" + maxStamina);
    }
    
    /**
     * Equip new equipment - behavior that changes equipment state
     */
    void equipItem(String itemType, String itemName) {
        if (!isAlive) {
            System.out.println("❌ " + name + " cannot equip items - character is dead!");
            return;
        }
        
        String oldItem = "";
        
        if (itemType.equals("weapon")) {
            oldItem = weapon;
            weapon = itemName;
        } else if (itemType.equals("armor")) {
            oldItem = armor;
            armor = itemName;
        } else if (itemType.equals("accessory")) {
            oldItem = (accessory != null) ? accessory : "nothing";
            accessory = itemName;
        } else {
            System.out.println("❌ Unknown item type: " + itemType);
            return;
        }
        
        System.out.println("🎒 " + name + " equips " + itemName);
        System.out.println("   Replaced: " + oldItem);
        
        // Recalculate stats based on new equipment (simplified)
        updateStatsFromEquipment();
    }
    
    /**
     * Add item to inventory - behavior that manages inventory state
     */
    boolean addToInventory(String item) {
        if (inventoryCount >= inventory.length) {
            System.out.println("❌ " + name + "'s inventory is full!");
            return false;
        }
        
        inventory[inventoryCount] = item;
        inventoryCount++;
        
        System.out.println("🎒 " + name + " picks up: " + item);
        System.out.println("   Inventory: " + inventoryCount + "/" + inventory.length);
        
        return true;
    }
    
    /**
     * Apply poison status effect
     */
    void applyPoison() {
        isPoisoned = true;
        System.out.println("☠️ " + name + " is now poisoned!");
    }
    
    /**
     * Apply blessing status effect
     */
    void applyBlessing() {
        isBlessed = true;
        System.out.println("✨ " + name + " is now blessed!");
    }
    
    /**
     * Process status effects each turn
     */
    void processStatusEffects() {
        if (!isAlive) return;
        
        if (isPoisoned) {
            int poisonDamage = 3;
            System.out.println("☠️ " + name + " takes " + poisonDamage + " poison damage!");
            takeDamage(poisonDamage);
            
            // Poison might wear off (20% chance each turn)
            if (Math.random() < 0.2) {
                isPoisoned = false;
                System.out.println("✨ " + name + " recovers from poison!");
            }
        }
        
        if (isBlessed) {
            // Blessing might wear off (10% chance each turn)
            if (Math.random() < 0.1) {
                isBlessed = false;
                System.out.println("✨ " + name + "'s blessing fades away.");
            }
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🛠️ HELPER METHODS - Supporting behaviors
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Calculate weapon damage based on equipped weapon
     */
    int getWeaponDamage() {
        if (weapon.contains("Sword")) return 12;
        if (weapon.contains("Staff")) return 8;
        if (weapon.contains("Dagger")) return 10;
        if (weapon.contains("Axe")) return 15;
        return 5; // Default/unarmed damage
    }
    
    /**
     * Update stats based on equipped items (simplified)
     */
    void updateStatsFromEquipment() {
        // This would normally be more complex, checking item properties
        if (weapon.contains("Magic")) {
            intelligence += 2;
        }
        if (armor.contains("Heavy")) {
            defense += 3;
        }
    }
    
    /**
     * Check if character is in good condition for adventuring
     */
    boolean isReadyForAdventure() {
        return isAlive && health > maxHealth * 0.3 && stamina > 20 && !isInCombat;
    }
    
    /**
     * Get a summary of the character's current condition
     */
    String getStatusSummary() {
        String status = name + " (" + characterClass + " Lv." + level + ") - ";
        
        if (!isAlive) {
            status += "DEFEATED";
        } else if (isInCombat) {
            status += "IN COMBAT";
        } else if (health < maxHealth * 0.25) {
            status += "CRITICALLY WOUNDED";
        } else if (stamina < 20) {
            status += "EXHAUSTED";
        } else {
            status += "READY";
        }
        
        return status;
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🎪 GAME WORLD SIMULATION - DEMONSTRATING STATE AND BEHAVIOR IN ACTION
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Main class that demonstrates the GameCharacter system in action
 * Shows how complex state and behavior interactions create engaging gameplay
 */
public class GameWorldSimulation {
    
    public static void main(String[] args) {
        
        System.out.println("🎮 ═══════════════════════════════════════════════════════");
        System.out.println("   WELCOME TO THE OBJECT-ORIENTED GAME WORLD");
        System.out.println("   Demonstrating State and Behavior in Action!");
        System.out.println("   ═══════════════════════════════════════════════════════");
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🌟 CHARACTER CREATION - SETTING INITIAL STATE
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n🎭 CREATING GAME CHARACTERS...\n");
        
        // Create diverse characters with different classes
        GameCharacter hero = new GameCharacter();
        hero.createCharacter("Sir Galahad", "Warrior");
        System.out.println();
        
        GameCharacter mage = new GameCharacter();
        mage.createCharacter("Merlin the Wise", "Mage");
        System.out.println();
        
        GameCharacter rogue = new GameCharacter();
        rogue.createCharacter("Shadow", "Rogue");
        System.out.println();
        
        GameCharacter healer = new GameCharacter();
        healer.createCharacter("Sister Agnes", "Healer");
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 📊 CHARACTER INSPECTION - READING STATE
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("📊 CHARACTER SHEETS...\n");
        
        hero.displayCharacterSheet();
        System.out.println();
        
        mage.displayCharacterSheet();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // ⚔️ COMBAT SIMULATION - STATE-DEPENDENT BEHAVIOR
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("⚔️ COMBAT ENCOUNTER BEGINS!\n");
        
        // Hero attacks mage
        hero.attack(mage);
        System.out.println();
        
        // Mage retaliates with magic
        mage.castSpell("Fireball", hero);
        System.out.println();
        
        // Show health after combat
        System.out.println("💖 Health Status:");
        System.out.println("   " + hero.name + ": " + hero.health + "/" + hero.maxHealth);
        System.out.println("   " + mage.name + ": " + mage.health + "/" + mage.maxHealth);
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🔮 MAGIC AND HEALING - COMPLEX BEHAVIOR INTERACTIONS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🔮 MAGICAL ASSISTANCE...\n");
        
        // Healer helps the wounded
        healer.castSpell("Heal", hero);
        healer.castSpell("Heal", mage);
        System.out.println();
        
        // Mage applies magical effects
        mage.castSpell("Blessing", hero);
        mage.castSpell("Poison", rogue); // Rogue becomes target practice
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🏃‍♂️ EXPLORATION AND TRAVEL - STATE CHANGES
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🏃‍♂️ EXPLORATION PHASE...\n");
        
        // Characters move to different locations
        hero.moveTo("Dark Forest");
        mage.moveTo("Ancient Library");
        rogue.moveTo("Thieves' Den");
        healer.moveTo("Sacred Temple");
        System.out.println();
        
        // Show stamina after travel
        System.out.println("💨 Stamina Status:");
        System.out.println("   " + hero.name + ": " + hero.stamina + "/" + hero.maxStamina);
        System.out.println("   " + mage.name + ": " + mage.stamina + "/" + mage.maxStamina);
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🎒 EQUIPMENT AND INVENTORY - STATE MANAGEMENT
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🎒 EQUIPMENT UPGRADES...\n");
        
        // Characters find and equip new items
        hero.equipItem("weapon", "Flame Sword");
        hero.equipItem("armor", "Dragon Scale Mail");
        hero.addToInventory("Health Potion");
        hero.addToInventory("Magic Ring");
        System.out.println();
        
        mage.equipItem("weapon", "Staff of Power");
        mage.equipItem("accessory", "Mana Crystal");
        mage.addToInventory("Spell Scroll");
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 😴 REST AND RECOVERY - RESOURCE MANAGEMENT
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("😴 REST AND RECOVERY...\n");
        
        // Characters rest to recover resources
        hero.rest();
        mage.rest();
        rogue.rest(); // Might recover from poison
        healer.rest();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 📈 EXPERIENCE AND PROGRESSION - GROWTH MECHANICS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("📈 EXPERIENCE GAINS...\n");
        
        // Characters gain experience from their adventures
        hero.gainExperience(120);
        mage.gainExperience(150);
        rogue.gainExperience(90);
        healer.gainExperience(110);
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // ☠️ STATUS EFFECTS SIMULATION - ONGOING STATE CHANGES
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("☠️ STATUS EFFECT PROCESSING...\n");
        
        // Process status effects over several turns
        for (int turn = 1; turn <= 3; turn++) {
            System.out.println("--- Turn " + turn + " ---");
            hero.processStatusEffects();
            mage.processStatusEffects();
            rogue.processStatusEffects();
            healer.processStatusEffects();
            System.out.println();
        }
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🏆 FINAL STATUS - COMPREHENSIVE STATE REVIEW
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🏆 FINAL CHARACTER STATUS...\n");
        
        System.out.println("Party Status Summary:");
        System.out.println("  • " + hero.getStatusSummary());
        System.out.println("  • " + mage.getStatusSummary());
        System.out.println("  • " + rogue.getStatusSummary());
        System.out.println("  • " + healer.getStatusSummary());
        System.out.println();
        
        // Detailed final status for main character
        System.out.println("Detailed Status for Party Leader:");
        hero.displayCharacterSheet();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🎯 DEMONSTRATION SUMMARY - THE POWER OF STATE AND BEHAVIOR
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n🎯 STATE AND BEHAVIOR DEMONSTRATION COMPLETE!\n");
        
        System.out.println("✅ STATE DEMONSTRATED:");
        System.out.println("  • Rich character attributes (health, mana, stats, equipment)");
        System.out.println("  • Dynamic state changes (damage, healing, status effects)");
        System.out.println("  • Complex state relationships (level affects stats)");
        System.out.println("  • State persistence throughout gameplay");
        
        System.out.println("\n✅ BEHAVIOR DEMONSTRATED:");
        System.out.println("  • State-dependent actions (can't attack when dead)");
        System.out.println("  • State-modifying behaviors (combat changes health)");
        System.out.println("  • Complex behavior chains (level up affects multiple stats)");
        System.out.println("  • Object interaction through behavior calls");
        
        System.out.println("\n✅ STATE-BEHAVIOR INTEGRATION:");
        System.out.println("  • Behaviors read state to make decisions");
        System.out.println("  • Behaviors modify state as results of actions");
        System.out.println("  • State enables or prevents certain behaviors");
        System.out.println("  • Rich, dynamic object interactions emerge naturally");
        
        System.out.println("\n🎮 This demonstrates how STATE and BEHAVIOR work together");
        System.out.println("   to create rich, interactive, and realistic object models!");
    }
    
    // ═══════════════════════════════════════════════════════════════════════════════
    // 🎲 ADDITIONAL DEMONSTRATION METHODS
    // ═══════════════════════════════════════════════════════════════════════════════
    
    /**
     * Simulate a complete combat encounter between two characters
     */
    public static void simulateCombat(GameCharacter attacker, GameCharacter defender) {
        System.out.println("⚔️ COMBAT: " + attacker.name + " vs " + defender.name);
        
        int round = 1;
        while (attacker.isAlive && defender.isAlive && round <= 10) {
            System.out.println("--- Round " + round + " ---");
            
            // Attacker's turn
            if (attacker.mana >= 25 && Math.random() < 0.4) {
                attacker.castSpell("Fireball", defender);
            } else {
                attacker.attack(defender);
            }
            
            // Defender's turn (if still alive)
            if (defender.isAlive) {
                if (defender.health < defender.maxHealth * 0.3 && defender.mana >= 20) {
                    defender.castSpell("Heal", defender);
                } else {
                    defender.attack(attacker);
                }
            }
            
            // Process status effects
            attacker.processStatusEffects();
            defender.processStatusEffects();
            
            round++;
            System.out.println();
        }
        
        // Declare winner
        if (attacker.isAlive && !defender.isAlive) {
            System.out.println("🏆 " + attacker.name + " wins!");
            attacker.gainExperience(50);
        } else if (!attacker.isAlive && defender.isAlive) {
            System.out.println("🏆 " + defender.name + " wins!");
            defender.gainExperience(50);
        } else {
            System.out.println("🤝 Combat ends in a draw!");
        }
    }
    
    /**
     * Demonstrate party-based adventure mechanics
     */
    public static void simulatePartyAdventure(GameCharacter[] party) {
        System.out.println("🗺️ PARTY ADVENTURE SIMULATION");
        
        String[] locations = {"Goblin Cave", "Dragon's Lair", "Haunted Mansion", "Crystal Mines"};
        
        for (String location : locations) {
            System.out.println("\n--- Exploring " + location + " ---");
            
            // Party travels together
            for (GameCharacter member : party) {
                if (member.isReadyForAdventure()) {
                    member.moveTo(location);
                }
            }
            
            // Encounter challenges
            for (GameCharacter member : party) {
                if (member.isAlive && Math.random() < 0.6) {
                    int damage = (int)(Math.random() * 20) + 5;
                    member.takeDamage(damage);
                    System.out.println("⚠️ " + member.name + " encounters danger!");
                }
            }
            
            // Party members help each other
            for (GameCharacter healer : party) {
                if (healer.characterClass.equals("Healer") && healer.mana >= 20) {
                    for (GameCharacter patient : party) {
                        if (patient != healer && patient.health < patient.maxHealth * 0.5) {
                            healer.castSpell("Heal", patient);
                            break; // Heal one person per turn
                        }
                    }
                }
            }
            
            // Gain experience
            for (GameCharacter member : party) {
                if (member.isAlive) {
                    member.gainExperience(30);
                }
            }
        }
        
        System.out.println("\n🏁 Adventure Complete! Final party status:");
        for (GameCharacter member : party) {
            System.out.println("  • " + member.getStatusSummary());
        }
    }
}

/*
 * 🎓 KEY LEARNING OUTCOMES FROM THIS EXAMPLE:
 * 
 * 1. COMPREHENSIVE STATE MODELING:
 *    ✅ Objects can have rich, complex state with many different data types
 *    ✅ State represents what an object IS at any given moment
 *    ✅ State can change over time through object interactions
 *    ✅ Related state should be grouped together in the same object
 * 
 * 2. SOPHISTICATED BEHAVIOR IMPLEMENTATION:
 *    ✅ Behaviors define what objects CAN DO
 *    ✅ Behaviors can read state to make decisions
 *    ✅ Behaviors can modify state as a result of actions
 *    ✅ Behaviors can interact with other objects
 * 
 * 3. STATE-BEHAVIOR INTEGRATION:
 *    ✅ State and behavior are intimately connected
 *    ✅ Current state determines what behaviors are possible
 *    ✅ Behaviors maintain object state consistency
 *    ✅ Complex systems emerge from simple state-behavior interactions
 * 
 * 4. REAL-WORLD MODELING EXCELLENCE:
 *    ✅ Game characters model real game mechanics naturally
 *    ✅ Object interactions mirror player expectations
 *    ✅ State persistence creates continuity and progression
 *    ✅ Behavior complexity creates engaging gameplay
 * 
 * 5. PROFESSIONAL DESIGN PATTERNS:
 *    ✅ State validation prevents invalid object states
 *    ✅ Behavior preconditions ensure safe operations
 *    ✅ Status effects demonstrate temporal state changes
 *    ✅ Resource management shows state conservation principles
 * 
 * This example demonstrates that well-designed State and Behavior create
 * objects that are not just data containers or function collections,
 * but living, dynamic entities that model complex real-world systems
 * with remarkable fidelity and power.
 * 
 * The magic of Object-Oriented Programming lies in this synthesis:
 * when State and Behavior work together, they create objects that
 * think, act, and interact just like the entities they represent.
 */
