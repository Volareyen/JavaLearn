/**
 * CharacterClass.java - Foundation Character Archetypes
 * 
 * Represents the different character classes in our RPG system
 * with rich data modeling and class-specific business logic.
 * 
 * "Each class is a unique path through the realm of combat and magic,
 * with its own strengths, weaknesses, and sacred traditions."
 */

import java.util.Arrays;
import java.util.EnumSet;

/**
 * Character classes with unique attributes and abilities
 */
public enum CharacterClass {
    WARRIOR(120, 20, "Strength", "Master of melee combat and heavy armor", 50) {
        @Override
        public int getStartingGold() {
            return 100 + (int)(Math.random() * 50); // 100-150 gold
        }
        
        @Override
        public boolean canUseWeapon(WeaponType weapon) {
            // Warriors can use all melee weapons but not magic-focused ones
            return weapon != WeaponType.WAND && weapon != WeaponType.STAFF;
        }
        
        @Override
        public int getLevelUpBonus(int level) {
            return 15 + (level / 5); // Strong health progression
        }
    },
    
    MAGE(60, 100, "Intelligence", "Master of arcane arts and elemental magic", 30) {
        @Override
        public int getStartingGold() {
            return 75 + (int)(Math.random() * 25); // 75-100 gold
        }
        
        @Override
        public boolean canUseWeapon(WeaponType weapon) {
            // Mages prefer magical implements and light weapons
            return weapon == WeaponType.STAFF || weapon == WeaponType.WAND || weapon == WeaponType.DAGGER;
        }
        
        @Override
        public int getLevelUpBonus(int level) {
            return 8 + (level / 3); // Moderate health, focuses on mana
        }
    },
    
    ROGUE(80, 40, "Dexterity", "Master of stealth, speed, and precision strikes", 40) {
        @Override
        public int getStartingGold() {
            return 125 + (int)(Math.random() * 75); // 125-200 gold (they "find" extra)
        }
        
        @Override
        public boolean canUseWeapon(WeaponType weapon) {
            // Rogues prefer quick, precise weapons
            return weapon == WeaponType.DAGGER || weapon == WeaponType.BOW || weapon == WeaponType.SWORD;
        }
        
        @Override
        public int getLevelUpBonus(int level) {
            return 10 + (level / 4); // Balanced health progression
        }
    },
    
    PALADIN(100, 60, "Wisdom", "Holy warrior balancing divine magic and combat", 45) {
        @Override
        public int getStartingGold() {
            return 80 + (int)(Math.random() * 40); // 80-120 gold
        }
        
        @Override
        public boolean canUseWeapon(WeaponType weapon) {
            // Paladins can use most weapons but avoid dark/underhanded ones
            return weapon != WeaponType.DAGGER; // Daggers are for rogues, not honorable paladins
        }
        
        @Override
        public int getLevelUpBonus(int level) {
            return 12 + (level / 4); // Solid health progression
        }
    },
    
    ARCHER(70, 30, "Dexterity", "Master of ranged combat and wilderness survival", 35) {
        @Override
        public int getStartingGold() {
            return 90 + (int)(Math.random() * 30); // 90-120 gold
        }
        
        @Override
        public boolean canUseWeapon(WeaponType weapon) {
            // Archers specialize in ranged weapons and light melee backup
            return weapon == WeaponType.BOW || weapon == WeaponType.DAGGER || weapon == WeaponType.SWORD;
        }
        
        @Override
        public int getLevelUpBonus(int level) {
            return 9 + (level / 4); // Light health progression
        }
    };
    
    // Instance fields
    private final int baseHealth;
    private final int baseMana;
    private final String primaryStat;
    private final String description;
    private final int baseMovementSpeed;
    
    // Constructor
    CharacterClass(int baseHealth, int baseMana, String primaryStat, 
                   String description, int baseMovementSpeed) {
        this.baseHealth = baseHealth;
        this.baseMana = baseMana;
        this.primaryStat = primaryStat;
        this.description = description;
        this.baseMovementSpeed = baseMovementSpeed;
    }
    
    // Abstract methods that each class must implement
    public abstract int getStartingGold();
    public abstract boolean canUseWeapon(WeaponType weapon);
    public abstract int getLevelUpBonus(int level);
    
    // Concrete methods available to all classes
    public int getBaseHealth() { return baseHealth; }
    public int getBaseMana() { return baseMana; }
    public String getPrimaryStat() { return primaryStat; }
    public String getDescription() { return description; }
    public int getBaseMovementSpeed() { return baseMovementSpeed; }
    
    /**
     * Calculate combat effectiveness against different damage types
     */
    public double getCombatEffectiveness(String damageType) {
        return switch (this) {
            case WARRIOR -> switch (damageType.toLowerCase()) {
                case "physical" -> 1.2; // 20% bonus to physical damage
                case "magical" -> 0.8;  // 20% penalty to magical damage
                default -> 1.0;
            };
            case MAGE -> switch (damageType.toLowerCase()) {
                case "magical" -> 1.3;  // 30% bonus to magical damage
                case "physical" -> 0.7; // 30% penalty to physical damage
                default -> 1.0;
            };
            case ROGUE -> switch (damageType.toLowerCase()) {
                case "critical" -> 1.5; // 50% bonus to critical hits
                case "sneak" -> 2.0;    // 100% bonus for sneak attacks
                default -> 1.0;
            };
            case PALADIN -> switch (damageType.toLowerCase()) {
                case "holy" -> 1.4;     // 40% bonus to holy damage
                case "undead" -> 1.6;   // 60% bonus vs undead
                case "dark" -> 1.2;     // 20% bonus vs dark magic
                default -> 1.0;
            };
            case ARCHER -> switch (damageType.toLowerCase()) {
                case "ranged" -> 1.3;   // 30% bonus to ranged attacks
                case "precision" -> 1.4; // 40% bonus to precise shots
                default -> 1.0;
            };
        };
    }
    
    /**
     * Get the class's preferred combat range
     */
    public String getPreferredRange() {
        return switch (this) {
            case WARRIOR, PALADIN -> "Melee";
            case MAGE -> "Long Range";
            case ROGUE -> "Close Range";
            case ARCHER -> "Long Range";
        };
    }
    
    /**
     * Check if this class is considered a spellcaster
     */
    public boolean isSpellcaster() {
        return baseMana >= 60; // Mage and Paladin
    }
    
    /**
     * Check if this class is heavily armored
     */
    public boolean isHeavilyArmored() {
        return baseHealth >= 100; // Warrior and Paladin
    }
    
    /**
     * Get recommended difficulty for new players
     */
    public int getBeginnerFriendliness() {
        return switch (this) {
            case WARRIOR -> 9;  // Very beginner friendly - simple and tanky
            case PALADIN -> 7;  // Fairly beginner friendly - versatile
            case ARCHER -> 6;   // Moderate - requires positioning
            case ROGUE -> 4;    // Advanced - requires skill and timing
            case MAGE -> 3;     // Expert - complex resource management
        };
    }
    
    // ============= STATIC UTILITY METHODS =============
    
    /**
     * Get classes recommended for beginners
     */
    public static CharacterClass[] getRecommendedForBeginner() {
        return Arrays.stream(values())
                .filter(clazz -> clazz.getBeginnerFriendliness() >= 7)
                .toArray(CharacterClass[]::new);
    }
    
    /**
     * Find classes by primary stat
     */
    public static CharacterClass[] getByPrimaryStat(String stat) {
        return Arrays.stream(values())
                .filter(clazz -> clazz.getPrimaryStat().equalsIgnoreCase(stat))
                .toArray(CharacterClass[]::new);
    }
    
    /**
     * Get all spellcasting classes
     */
    public static CharacterClass[] getSpellcasters() {
        return Arrays.stream(values())
                .filter(CharacterClass::isSpellcaster)
                .toArray(CharacterClass[]::new);
    }
    
    /**
     * Get classes that can use a specific weapon type
     */
    public static CharacterClass[] getClassesForWeapon(WeaponType weapon) {
        return Arrays.stream(values())
                .filter(clazz -> clazz.canUseWeapon(weapon))
                .toArray(CharacterClass[]::new);
    }
    
    /**
     * Get class with highest base stat
     */
    public static CharacterClass getHighestHealth() {
        return Arrays.stream(values())
                .max((a, b) -> Integer.compare(a.getBaseHealth(), b.getBaseHealth()))
                .orElse(WARRIOR);
    }
    
    public static CharacterClass getHighestMana() {
        return Arrays.stream(values())
                .max((a, b) -> Integer.compare(a.getBaseMana(), b.getBaseMana()))
                .orElse(MAGE);
    }
    
    /**
     * Get balanced classes (health and mana both above average)
     */
    public static CharacterClass[] getBalancedClasses() {
        double avgHealth = Arrays.stream(values())
                .mapToInt(CharacterClass::getBaseHealth)
                .average()
                .orElse(0);
        
        double avgMana = Arrays.stream(values())
                .mapToInt(CharacterClass::getBaseMana)
                .average()
                .orElse(0);
        
        return Arrays.stream(values())
                .filter(clazz -> clazz.getBaseHealth() >= avgHealth && clazz.getBaseMana() >= avgMana)
                .toArray(CharacterClass[]::new);
    }
    
    /**
     * Create a character class recommendation based on player preferences
     */
    public static CharacterClass recommendClass(String playstyle, boolean isNewPlayer) {
        if (isNewPlayer) {
            CharacterClass[] beginnerClasses = getRecommendedForBeginner();
            if (beginnerClasses.length > 0) {
                return beginnerClasses[0]; // Return the most beginner-friendly
            }
        }
        
        return switch (playstyle.toLowerCase()) {
            case "tank", "melee", "fighter" -> WARRIOR;
            case "magic", "spell", "wizard" -> MAGE;
            case "stealth", "assassin", "sneaky" -> ROGUE;
            case "holy", "healer", "support" -> PALADIN;
            case "ranged", "bow", "hunter" -> ARCHER;
            default -> WARRIOR; // Default to warrior for unknown preferences
        };
    }
    
    /**
     * Get comprehensive class comparison data
     */
    public static String getClassComparison() {
        StringBuilder comparison = new StringBuilder();
        comparison.append("CHARACTER CLASS COMPARISON\n");
        comparison.append("═══════════════════════════════════════════════════════════════\n");
        comparison.append(String.format("%-10s %-6s %-6s %-12s %-6s %-4s%n", 
            "CLASS", "HEALTH", "MANA", "PRIMARY_STAT", "SPEED", "DIFF"));
        comparison.append("───────────────────────────────────────────────────────────────\n");
        
        for (CharacterClass clazz : values()) {
            comparison.append(String.format("%-10s %-6d %-6d %-12s %-6d %-4d%n",
                clazz.name(),
                clazz.getBaseHealth(),
                clazz.getBaseMana(),
                clazz.getPrimaryStat(),
                clazz.getBaseMovementSpeed(),
                clazz.getBeginnerFriendliness()));
        }
        
        comparison.append("═══════════════════════════════════════════════════════════════\n");
        comparison.append("DIFF = Beginner Friendliness (1-10, higher is easier)\n");
        
        return comparison.toString();
    }
}

/*
 * ════════════════════════════════════════════════════════════════════════════
 * CHARACTER CLASS DESIGN PATTERNS DEMONSTRATED:
 * ════════════════════════════════════════════════════════════════════════════
 * 
 * 1. RICH DATA MODELING:
 *    - Each class has unique base stats and characteristics
 *    - Meaningful descriptions and primary stats
 *    - Movement speed and combat preferences
 * 
 * 2. ABSTRACT METHOD IMPLEMENTATION:
 *    - Each class implements unique starting gold logic
 *    - Weapon compatibility rules vary by class
 *    - Different level-up progression patterns
 * 
 * 3. BUSINESS LOGIC ENCAPSULATION:
 *    - Combat effectiveness calculations
 *    - Beginner friendliness ratings
 *    - Preferred combat ranges and styles
 * 
 * 4. STATIC UTILITY METHODS:
 *    - Filtering and searching classes by criteria
 *    - Recommendation systems for new players
 *    - Comprehensive comparison tools
 * 
 * 5. COMPUTED PROPERTIES:
 *    - isSpellcaster() and isHeavilyArmored() derived from base stats
 *    - Combat bonuses calculated based on damage types
 *    - Dynamic recommendations based on preferences
 * 
 * This enum demonstrates how character classes can be more than simple
 * constants - they become intelligent entities that encapsulate the
 * rules and behaviors that define different gameplay styles!
 */
