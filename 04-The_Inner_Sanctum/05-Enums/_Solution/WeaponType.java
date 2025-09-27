/**
 * WeaponType.java - Arsenal of Combat Implements
 * 
 * Represents different weapon types with combat statistics,
 * durability mechanics, and usage requirements.
 * 
 * "Every weapon tells a story of those who wielded it before.
 * Choose wisely, for your blade is an extension of your will."
 */

import java.util.Arrays;
import java.util.Comparator;

/**
 * Weapon types with combat statistics and usage mechanics
 * Implements Comparable for damage-based sorting
 */
public enum WeaponType implements Comparable<WeaponType> {
    DAGGER(25, 80, 1, false, "Swift and silent, the assassin's friend") {
        @Override
        public double getAttackSpeed() {
            return 2.5; // Very fast attacks
        }
        
        @Override
        public double getCriticalChance() {
            return 0.25; // 25% crit chance - precision weapon
        }
        
        @Override
        public int getDurabilityLoss() {
            return 1; // Low durability loss
        }
        
        @Override
        public String getSpecialAbility() {
            return "Backstab: 200% damage from behind";
        }
    },
    
    SWORD(45, 120, 3, false, "Balanced weapon of knights and heroes") {
        @Override
        public double getAttackSpeed() {
            return 1.5; // Moderate attack speed
        }
        
        @Override
        public double getCriticalChance() {
            return 0.15; // 15% crit chance
        }
        
        @Override
        public int getDurabilityLoss() {
            return 2; // Moderate durability loss
        }
        
        @Override
        public String getSpecialAbility() {
            return "Parry: Can block incoming attacks";
        }
    },
    
    HAMMER(65, 100, 5, true, "Crushing weapon that ignores armor") {
        @Override
        public double getAttackSpeed() {
            return 0.8; // Slow but powerful
        }
        
        @Override
        public double getCriticalChance() {
            return 0.10; // 10% crit chance
        }
        
        @Override
        public int getDurabilityLoss() {
            return 3; // High durability loss from crushing
        }
        
        @Override
        public String getSpecialAbility() {
            return "Armor Break: Ignores 50% of target's armor";
        }
    },
    
    BOW(35, 90, 2, true, "Ranged weapon for precise strikes") {
        @Override
        public double getAttackSpeed() {
            return 1.2; // Moderate-fast speed
        }
        
        @Override
        public double getCriticalChance() {
            return 0.20; // 20% crit chance - aimed shots
        }
        
        @Override
        public int getDurabilityLoss() {
            return 1; // Low durability loss
        }
        
        @Override
        public String getSpecialAbility() {
            return "Piercing Shot: Can hit multiple enemies in line";
        }
    },
    
    STAFF(40, 70, 4, true, "Magical implement that amplifies spells") {
        @Override
        public double getAttackSpeed() {
            return 1.0; // Base attack speed
        }
        
        @Override
        public double getCriticalChance() {
            return 0.12; // 12% crit chance
        }
        
        @Override
        public int getDurabilityLoss() {
            return 1; // Low durability loss
        }
        
        @Override
        public String getSpecialAbility() {
            return "Spell Power: +50% magical damage";
        }
    },
    
    WAND(20, 60, 1, false, "Quick-cast magical implement for rapid spells") {
        @Override
        public double getAttackSpeed() {
            return 2.0; // Fast magical attacks
        }
        
        @Override
        public double getCriticalChance() {
            return 0.18; // 18% crit chance
        }
        
        @Override
        public int getDurabilityLoss() {
            return 1; // Low durability loss
        }
        
        @Override
        public String getSpecialAbility() {
            return "Rapid Cast: Can cast spells 50% faster";
        }
    };
    
    // Instance fields
    private final int damage;
    private final int durability;
    private final int requiredLevel;
    private final boolean twoHanded;
    private final String description;
    
    // Constructor
    WeaponType(int damage, int durability, int requiredLevel, boolean twoHanded, String description) {
        this.damage = damage;
        this.durability = durability;
        this.requiredLevel = requiredLevel;
        this.twoHanded = twoHanded;
        this.description = description;
    }
    
    // Abstract methods that each weapon must implement
    public abstract double getAttackSpeed();
    public abstract double getCriticalChance();
    public abstract int getDurabilityLoss();
    public abstract String getSpecialAbility();
    
    // Concrete methods available to all weapons
    public int getDamage() { return damage; }
    public int getDurability() { return durability; }
    public int getRequiredLevel() { return requiredLevel; }
    public boolean isTwoHanded() { return twoHanded; }
    public String getDescription() { return description; }
    
    /**
     * Calculate damage per second (DPS) rating
     */
    public double getDPS() {
        return damage * getAttackSpeed();
    }
    
    /**
     * Calculate expected damage including critical hits
     */
    public double getExpectedDamage() {
        double baseDamage = damage;
        double critMultiplier = 2.0; // Critical hits do 2x damage
        return baseDamage * (1.0 + getCriticalChance() * critMultiplier);
    }
    
    /**
     * Get weapon category for inventory organization
     */
    public String getCategory() {
        return switch (this) {
            case DAGGER, SWORD -> "Melee Weapon";
            case HAMMER -> "Two-Handed Melee";
            case BOW -> "Ranged Weapon";
            case STAFF, WAND -> "Magical Implement";
        };
    }
    
    /**
     * Check if weapon is suitable for stealth attacks
     */
    public boolean isStealthyWeapon() {
        return this == DAGGER || this == BOW;
    }
    
    /**
     * Check if weapon is magical in nature
     */
    public boolean isMagicalWeapon() {
        return this == STAFF || this == WAND;
    }
    
    /**
     * Get weapon range classification
     */
    public String getRange() {
        return switch (this) {
            case DAGGER -> "Close";
            case SWORD, HAMMER -> "Melee";
            case BOW -> "Ranged";
            case STAFF, WAND -> "Magical";
        };
    }
    
    /**
     * Calculate weapon effectiveness against armor type
     */
    public double getArmorPenetration(String armorType) {
        return switch (this) {
            case DAGGER -> switch (armorType.toLowerCase()) {
                case "cloth", "leather" -> 1.5; // Excellent vs light armor
                case "chain", "plate" -> 0.6;   // Poor vs heavy armor
                default -> 1.0;
            };
            case SWORD -> switch (armorType.toLowerCase()) {
                case "cloth" -> 1.3;
                case "leather" -> 1.2;
                case "chain" -> 1.0;
                case "plate" -> 0.8;
                default -> 1.0;
            };
            case HAMMER -> switch (armorType.toLowerCase()) {
                case "plate", "chain" -> 1.4; // Excellent vs heavy armor
                case "cloth", "leather" -> 1.1; // Still good vs light armor
                default -> 1.0;
            };
            case BOW -> switch (armorType.toLowerCase()) {
                case "cloth" -> 1.4;
                case "leather" -> 1.2;
                case "chain" -> 0.9;
                case "plate" -> 0.7;
                default -> 1.0;
            };
            case STAFF, WAND -> 1.0; // Magic bypasses armor
        };
    }
    
    /**
     * Get weapon rarity tier
     */
    public int getRarityTier() {
        return switch (this) {
            case DAGGER, SWORD -> 1; // Common
            case BOW, WAND -> 2;     // Uncommon
            case STAFF -> 3;         // Rare
            case HAMMER -> 4;        // Epic
        };
    }
    
    /**
     * Note: Enums already implement Comparable naturally by ordinal()
     * This would override a final method, so we use static comparison methods instead
     */
    // @Override - Cannot override final method
    // public int compareTo(WeaponType other) {
    //     return Integer.compare(this.damage, other.damage);
    // }
    
    // ============= STATIC UTILITY METHODS =============
    
    /**
     * Get weapons sorted by damage (ascending)
     */
    public static WeaponType[] getWeaponsByDamage() {
        return Arrays.stream(values())
                .sorted()
                .toArray(WeaponType[]::new);
    }
    
    /**
     * Get weapons sorted by DPS (descending)
     */
    public static WeaponType[] getWeaponsByDPS() {
        return Arrays.stream(values())
                .sorted(Comparator.comparingDouble(WeaponType::getDPS).reversed())
                .toArray(WeaponType[]::new);
    }
    
    /**
     * Get weapons suitable for a character level
     */
    public static WeaponType[] getWeaponsForLevel(int characterLevel) {
        return Arrays.stream(values())
                .filter(weapon -> weapon.getRequiredLevel() <= characterLevel)
                .sorted(Comparator.comparingInt(WeaponType::getRequiredLevel).reversed())
                .toArray(WeaponType[]::new);
    }
    
    /**
     * Get weapons by hand requirement
     */
    public static WeaponType[] getOneHandedWeapons() {
        return Arrays.stream(values())
                .filter(weapon -> !weapon.isTwoHanded())
                .toArray(WeaponType[]::new);
    }
    
    public static WeaponType[] getTwoHandedWeapons() {
        return Arrays.stream(values())
                .filter(WeaponType::isTwoHanded)
                .toArray(WeaponType[]::new);
    }
    
    /**
     * Get weapons by category
     */
    public static WeaponType[] getMeleeWeapons() {
        return Arrays.stream(values())
                .filter(weapon -> weapon.getCategory().contains("Melee"))
                .toArray(WeaponType[]::new);
    }
    
    public static WeaponType[] getMagicalWeapons() {
        return Arrays.stream(values())
                .filter(WeaponType::isMagicalWeapon)
                .toArray(WeaponType[]::new);
    }
    
    public static WeaponType[] getRangedWeapons() {
        return Arrays.stream(values())
                .filter(weapon -> weapon.getRange().equals("Ranged"))
                .toArray(WeaponType[]::new);
    }
    
    /**
     * Find best weapon for specific criteria
     */
    public static WeaponType getBestDamage() {
        return Arrays.stream(values())
                .max(Comparator.comparingInt(WeaponType::getDamage))
                .orElse(SWORD);
    }
    
    public static WeaponType getBestDPS() {
        return Arrays.stream(values())
                .max(Comparator.comparingDouble(WeaponType::getDPS))
                .orElse(SWORD);
    }
    
    public static WeaponType getBestCritical() {
        return Arrays.stream(values())
                .max(Comparator.comparingDouble(WeaponType::getCriticalChance))
                .orElse(DAGGER);
    }
    
    /**
     * Get weapon recommendations for different playstyles
     */
    public static WeaponType recommendForPlaystyle(String playstyle) {
        return switch (playstyle.toLowerCase()) {
            case "berserker", "damage", "dps" -> getBestDPS();
            case "tank", "defensive" -> SWORD; // Balanced with parry
            case "stealth", "assassin", "rogue" -> DAGGER;
            case "ranged", "archer", "hunter" -> BOW;
            case "mage", "wizard", "magical" -> STAFF;
            case "battle-mage", "quick-cast" -> WAND;
            case "heavy", "armor-breaker" -> HAMMER;
            default -> SWORD; // Default balanced choice
        };
    }
    
    /**
     * Calculate weapon synergy score with character class
     */
    public double getSynergyScore(CharacterClass characterClass) {
        if (!characterClass.canUseWeapon(this)) {
            return 0.0; // No synergy if class can't use weapon
        }
        
        return switch (characterClass) {
            case WARRIOR -> switch (this) {
                case SWORD -> 1.0;   // Perfect synergy
                case HAMMER -> 0.9;  // Excellent synergy
                case DAGGER -> 0.6;  // Moderate synergy
                default -> 0.3;
            };
            case MAGE -> switch (this) {
                case STAFF -> 1.0;   // Perfect synergy
                case WAND -> 0.8;    // Good synergy
                case DAGGER -> 0.4;  // Poor synergy for backup
                default -> 0.0;
            };
            case ROGUE -> switch (this) {
                case DAGGER -> 1.0;  // Perfect synergy
                case BOW -> 0.8;     // Good synergy
                case SWORD -> 0.6;   // Moderate synergy
                default -> 0.2;
            };
            case PALADIN -> switch (this) {
                case SWORD -> 0.9;   // Excellent synergy
                case HAMMER -> 1.0;  // Perfect synergy (holy warhammer)
                case BOW -> 0.7;     // Good synergy
                case STAFF -> 0.5;   // Moderate magical synergy
                default -> 0.4;
            };
            case ARCHER -> switch (this) {
                case BOW -> 1.0;     // Perfect synergy
                case DAGGER -> 0.7;  // Good backup weapon
                case SWORD -> 0.5;   // Moderate backup
                default -> 0.2;
            };
        };
    }
    
    /**
     * Get comprehensive weapon statistics table
     */
    public static String getWeaponComparison() {
        StringBuilder comparison = new StringBuilder();
        comparison.append("WEAPON COMPARISON TABLE\n");
        comparison.append("═══════════════════════════════════════════════════════════════════════════════\n");
        comparison.append(String.format("%-8s %-4s %-4s %-4s %-4s %-6s %-6s %-12s %-8s%n", 
            "WEAPON", "DMG", "DUR", "LVL", "2H?", "SPEED", "CRIT%", "CATEGORY", "RANGE"));
        comparison.append("───────────────────────────────────────────────────────────────────────────────\n");
        
        for (WeaponType weapon : values()) {
            comparison.append(String.format("%-8s %-4d %-4d %-4d %-4s %-6.1f %-6.0f %-12s %-8s%n",
                weapon.name(),
                weapon.getDamage(),
                weapon.getDurability(),
                weapon.getRequiredLevel(),
                weapon.isTwoHanded() ? "Yes" : "No",
                weapon.getAttackSpeed(),
                weapon.getCriticalChance() * 100,
                weapon.getCategory(),
                weapon.getRange()));
        }
        
        comparison.append("═══════════════════════════════════════════════════════════════════════════════\n");
        comparison.append("DMG=Damage, DUR=Durability, LVL=Required Level, 2H=Two-Handed\n");
        comparison.append("SPEED=Attacks per second, CRIT%=Critical hit chance percentage\n");
        
        return comparison.toString();
    }
}

/*
 * ════════════════════════════════════════════════════════════════════════════
 * WEAPON TYPE DESIGN PATTERNS DEMONSTRATED:
 * ════════════════════════════════════════════════════════════════════════════
 * 
 * 1. COMPARABLE IMPLEMENTATION:
 *    - Natural ordering by damage for sorting
 *    - Additional comparators for DPS, level, etc.
 * 
 * 2. ABSTRACT METHOD SPECIALIZATION:
 *    - Each weapon has unique attack speed, crit chance, durability loss
 *    - Special abilities that define weapon personality
 * 
 * 3. COMPUTED PROPERTIES:
 *    - DPS calculations combining damage and speed
 *    - Expected damage including critical hit probability
 *    - Armor penetration effectiveness by armor type
 * 
 * 4. CATEGORIZATION LOGIC:
 *    - Weapon categories for inventory organization
 *    - Range classifications (Close, Melee, Ranged, Magical)
 *    - Rarity tiers for loot systems
 * 
 * 5. STATIC UTILITY ARSENAL:
 *    - Multiple sorting and filtering methods
 *    - Recommendation systems for different playstyles
 *    - Synergy calculations with character classes
 * 
 * 6. BUSINESS LOGIC INTEGRATION:
 *    - Level requirements for weapon access
 *    - Durability mechanics for maintenance systems
 *    - Two-handed weapon restrictions
 * 
 * This enum showcases how weapons can be intelligent entities that
 * know their own capabilities, limitations, and optimal usage scenarios!
 */
