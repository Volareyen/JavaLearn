/**
 * GameCharacter.java - Core Integration Class
 * 
 * This class demonstrates how all enum systems work together
 * to create a cohesive RPG character management system.
 * 
 * "Witness how enums transform from simple constants into 
 * intelligent systems that power complex game mechanics!"
 */

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a game character integrating all enum systems
 */
public class GameCharacter {
    // Character Identity
    private String name;
    private CharacterClass characterClass;
    private Element favoredElement;
    private int level;
    private int experience;
    
    // Combat Stats
    private int health;
    private int maxHealth;
    private int mana;
    private int maxMana;
    private WeaponType equippedWeapon;
    
    // Status System using EnumSet for efficient bit operations
    private EnumSet<CharacterStatus> activeStatuses;
    private EnumMap<CharacterStatus, Integer> statusDurations;
    
    // Elemental System using EnumMap for O(1) lookups
    private EnumMap<Element, Integer> elementalResistances;
    private EnumMap<Element, Integer> elementalAffinities;
    
    // Inventory System
    private EnumMap<InventorySlot, List<String>> inventory;
    private int gold;
    
    // Game Progress
    private List<GameEvent> eventHistory;
    private EnumSet<QuestDifficulty> completedDifficulties;
    
    /**
     * Constructor using enum-powered character creation
     */
    public GameCharacter(String name, CharacterClass characterClass, Element favoredElement) {
        this.name = name;
        this.characterClass = characterClass;
        this.favoredElement = favoredElement;
        this.level = 1;
        this.experience = 0;
        
        // Initialize stats using enum methods
        initializeStats();
        initializeCollections();
        initializeStartingEquipment();
        
        // Log character creation event
        triggerEvent(GameEvent.CHARACTER_CREATED);
    }
    
    /**
     * Initialize character stats based on class and level
     */
    private void initializeStats() {
        this.maxHealth = characterClass.getBaseHealth() + (level * 10);
        this.health = maxHealth;
        this.maxMana = characterClass.getBaseMana() + (level * 5);
        this.mana = maxMana;
        this.gold = characterClass.getStartingGold();
    }
    
    /**
     * Initialize all enum-based collections
     */
    private void initializeCollections() {
        this.activeStatuses = EnumSet.noneOf(CharacterStatus.class);
        this.statusDurations = new EnumMap<>(CharacterStatus.class);
        
        this.elementalResistances = new EnumMap<>(Element.class);
        this.elementalAffinities = new EnumMap<>(Element.class);
        
        // Initialize elemental resistances - favored element gets bonus
        for (Element element : Element.values()) {
            if (element == favoredElement) {
                elementalResistances.put(element, 25); // 25% resistance
                elementalAffinities.put(element, 150); // 50% damage bonus
            } else {
                elementalResistances.put(element, 0);
                elementalAffinities.put(element, 100); // Normal damage
            }
        }
        
        this.inventory = new EnumMap<>(InventorySlot.class);
        for (InventorySlot slot : InventorySlot.values()) {
            inventory.put(slot, new ArrayList<>());
        }
        
        this.eventHistory = new ArrayList<>();
        this.completedDifficulties = EnumSet.noneOf(QuestDifficulty.class);
    }
    
    /**
     * Give starting equipment based on character class
     */
    private void initializeStartingEquipment() {
        // Use enum logic to determine starting weapon
        switch (characterClass) {
            case WARRIOR -> equipWeapon(WeaponType.SWORD);
            case MAGE -> equipWeapon(WeaponType.STAFF);
            case ROGUE -> equipWeapon(WeaponType.DAGGER);
            case PALADIN -> equipWeapon(WeaponType.HAMMER);
            case ARCHER -> equipWeapon(WeaponType.BOW);
        }
        
        // Add starting items to inventory
        addToInventory(InventorySlot.CONSUMABLE, "Health Potion");
        addToInventory(InventorySlot.CONSUMABLE, "Mana Potion");
    }
    
    /**
     * Attempt to equip a weapon using enum business logic
     */
    public boolean equipWeapon(WeaponType weapon) {
        if (!characterClass.canUseWeapon(weapon)) {
            System.out.println(name + " cannot use " + weapon + " as a " + characterClass);
            return false;
        }
        
        if (level < weapon.getRequiredLevel()) {
            System.out.println("Level " + weapon.getRequiredLevel() + " required to use " + weapon);
            return false;
        }
        
        this.equippedWeapon = weapon;
        System.out.println(name + " equipped " + weapon);
        return true;
    }
    
    /**
     * Add item to inventory using enum slot management
     */
    public boolean addToInventory(InventorySlot slot, String item) {
        List<String> slotItems = inventory.get(slot);
        
        if (!slot.canStore(item)) {
            System.out.println("Cannot store " + item + " in " + slot + " slot");
            return false;
        }
        
        if (slotItems.size() >= slot.getCapacity()) {
            System.out.println(slot + " slot is full!");
            return false;
        }
        
        slotItems.add(item);
        return true;
    }
    
    /**
     * Apply status effect using enum state management
     */
    public void applyStatus(CharacterStatus status, int duration) {
        // Check if status can be applied
        if (!canApplyStatus(status)) {
            System.out.println("Cannot apply " + status + " to " + name);
            return;
        }
        
        activeStatuses.add(status);
        statusDurations.put(status, duration);
        
        System.out.println(name + " is now " + status.name().toLowerCase());
        
        // Apply immediate effect
        status.applyEffect(this);
        
        // Check for status conflicts or combinations
        resolveStatusConflicts();
    }
    
    /**
     * Check if status can be applied using enum transition logic
     */
    private boolean canApplyStatus(CharacterStatus newStatus) {
        for (CharacterStatus activeStatus : activeStatuses) {
            if (!activeStatus.canCoexistWith(newStatus)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Resolve status conflicts using enum rules
     */
    private void resolveStatusConflicts() {
        // Some statuses cancel each other out
        if (activeStatuses.contains(CharacterStatus.BURNING) && 
            activeStatuses.contains(CharacterStatus.FROZEN)) {
            
            removeStatus(CharacterStatus.BURNING);
            removeStatus(CharacterStatus.FROZEN);
            System.out.println(name + "'s fire and ice effects cancelled each other out!");
        }
        
        // Blessed removes all debuffs
        if (activeStatuses.contains(CharacterStatus.BLESSED)) {
            EnumSet<CharacterStatus> debuffsToRemove = EnumSet.noneOf(CharacterStatus.class);
            for (CharacterStatus status : activeStatuses) {
                if (status.isDebuff()) {
                    debuffsToRemove.add(status);
                }
            }
            
            for (CharacterStatus debuff : debuffsToRemove) {
                removeStatus(debuff);
                System.out.println("Blessing removed " + debuff + " from " + name);
            }
        }
    }
    
    /**
     * Remove status effect
     */
    public void removeStatus(CharacterStatus status) {
        activeStatuses.remove(status);
        statusDurations.remove(status);
    }
    
    /**
     * Process all active status effects each turn
     */
    public void processStatusEffects() {
        EnumSet<CharacterStatus> statusesToRemove = EnumSet.noneOf(CharacterStatus.class);
        
        for (CharacterStatus status : activeStatuses) {
            // Apply ongoing effect
            status.applyEffect(this);
            
            // Reduce duration
            int duration = statusDurations.get(status) - 1;
            if (duration <= 0) {
                statusesToRemove.add(status);
            } else {
                statusDurations.put(status, duration);
            }
        }
        
        // Remove expired statuses
        for (CharacterStatus status : statusesToRemove) {
            removeStatus(status);
            System.out.println(name + " recovered from " + status.name().toLowerCase());
        }
    }
    
    /**
     * Take damage with elemental calculations
     */
    public void takeDamage(int baseDamage, Element damageElement) {
        if (health <= 0) return; // Already dead
        
        // Calculate elemental resistance
        int resistance = elementalResistances.getOrDefault(damageElement, 0);
        int finalDamage = baseDamage * (100 - resistance) / 100;
        
        // Apply status effect modifiers
        if (activeStatuses.contains(CharacterStatus.BLESSED)) {
            finalDamage = (int)(finalDamage * 0.75); // 25% damage reduction
        }
        
        if (activeStatuses.contains(CharacterStatus.CURSED)) {
            finalDamage = (int)(finalDamage * 1.25); // 25% more damage
        }
        
        health = Math.max(0, health - finalDamage);
        
        System.out.printf("%s took %d %s damage (%d base, %d%% resistance)%n",
            name, finalDamage, damageElement.name().toLowerCase(), baseDamage, resistance);
        
        if (health <= 0) {
            applyStatus(CharacterStatus.DEAD, Integer.MAX_VALUE);
            triggerEvent(GameEvent.PLAYER_DEATH);
        }
    }
    
    /**
     * Attempt a quest of given difficulty
     */
    public BattleResult attemptQuest(QuestDifficulty difficulty) {
        if (activeStatuses.contains(CharacterStatus.DEAD)) {
            return new BattleResult(false, "Cannot quest while dead!", 0, 0);
        }
        
        double successChance = difficulty.getSuccessChance(level);
        
        // Status effects modify success chance
        if (activeStatuses.contains(CharacterStatus.BLESSED)) {
            successChance += 0.15; // 15% bonus
        }
        if (activeStatuses.contains(CharacterStatus.CURSED)) {
            successChance -= 0.10; // 10% penalty
        }
        
        boolean success = Math.random() < successChance;
        
        if (success) {
            int expGained = difficulty.getExperienceReward(level);
            int goldGained = difficulty.getGoldReward(level);
            
            gainExperience(expGained);
            gold += goldGained;
            completedDifficulties.add(difficulty);
            
            triggerEvent(GameEvent.QUEST_COMPLETE);
            
            return new BattleResult(true, 
                String.format("Quest completed! Gained %d EXP and %d gold", expGained, goldGained),
                expGained, goldGained);
        } else {
            int penalty = difficulty.getFailurePenalty();
            takeDamage(penalty, Element.DARK); // Generic damage
            
            return new BattleResult(false,
                String.format("Quest failed! Lost %d health", penalty),
                0, 0);
        }
    }
    
    /**
     * Gain experience and handle level ups
     */
    public void gainExperience(int exp) {
        experience += exp;
        
        int expRequired = level * 100; // Simple level formula
        if (experience >= expRequired) {
            levelUp();
        }
    }
    
    /**
     * Level up the character
     */
    private void levelUp() {
        level++;
        experience = 0;
        
        // Apply class-specific level bonuses
        int healthBonus = characterClass.getLevelUpBonus(level);
        maxHealth += healthBonus;
        health = maxHealth; // Full heal on level up
        
        int manaBonus = characterClass.getBaseMana() / 10;
        maxMana += manaBonus;
        mana = maxMana; // Full mana restore
        
        System.out.printf("%s reached level %d! (+%d HP, +%d MP)%n", 
            name, level, healthBonus, manaBonus);
        
        triggerEvent(GameEvent.LEVEL_UP);
    }
    
    /**
     * Trigger a game event
     */
    public void triggerEvent(GameEvent event) {
        if (!event.meetsRequirements(this)) {
            return;
        }
        
        eventHistory.add(event);
        event.triggerEvent(this);
        
        // Process follow-up events
        for (GameEvent followUp : event.getFollowupEvents()) {
            if (followUp.meetsRequirements(this)) {
                triggerEvent(followUp);
            }
        }
    }
    
    /**
     * Get character's current power level
     */
    public int getPowerLevel() {
        int basePower = level * 10;
        
        if (equippedWeapon != null) {
            basePower += equippedWeapon.getDamage();
        }
        
        // Status modifiers
        if (activeStatuses.contains(CharacterStatus.BLESSED)) {
            basePower = (int)(basePower * 1.2);
        }
        if (activeStatuses.contains(CharacterStatus.CURSED)) {
            basePower = (int)(basePower * 0.8);
        }
        
        return basePower;
    }
    
    /**
     * Get detailed character status report
     */
    public String getStatusReport() {
        StringBuilder report = new StringBuilder();
        
        report.append("═══════════════════════════════════\n");
        report.append(String.format("      %s THE %s\n", name.toUpperCase(), characterClass));
        report.append("═══════════════════════════════════\n");
        report.append(String.format("Level: %d | Experience: %d\n", level, experience));
        report.append(String.format("Health: %d/%d | Mana: %d/%d\n", health, maxHealth, mana, maxMana));
        report.append(String.format("Gold: %d | Power Level: %d\n", gold, getPowerLevel()));
        report.append(String.format("Favored Element: %s\n", favoredElement));
        
        if (equippedWeapon != null) {
            report.append(String.format("Equipped Weapon: %s (Damage: %d)\n", 
                equippedWeapon, equippedWeapon.getDamage()));
        }
        
        if (!activeStatuses.isEmpty()) {
            report.append("\nActive Status Effects:\n");
            for (CharacterStatus status : activeStatuses) {
                int duration = statusDurations.getOrDefault(status, 0);
                report.append(String.format("- %s (%d turns remaining)\n", 
                    status.name(), duration));
            }
        }
        
        if (!completedDifficulties.isEmpty()) {
            report.append("\nCompleted Quest Difficulties: ");
            report.append(completedDifficulties.stream()
                .map(Enum::name)
                .collect(Collectors.joining(", ")));
            report.append("\n");
        }
        
        report.append("═══════════════════════════════════\n");
        
        return report.toString();
    }
    
    // Getters
    public String getName() { return name; }
    public CharacterClass getCharacterClass() { return characterClass; }
    public Element getFavoredElement() { return favoredElement; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getMana() { return mana; }
    public int getMaxMana() { return maxMana; }
    public WeaponType getEquippedWeapon() { return equippedWeapon; }
    public EnumSet<CharacterStatus> getActiveStatuses() { return EnumSet.copyOf(activeStatuses); }
    public EnumMap<Element, Integer> getElementalResistances() { return new EnumMap<>(elementalResistances); }
    public int getGold() { return gold; }
    public List<GameEvent> getEventHistory() { return new ArrayList<>(eventHistory); }
    
    /**
     * Battle result data class
     */
    public static class BattleResult {
        private final boolean success;
        private final String message;
        private final int experienceGained;
        private final int goldGained;
        
        public BattleResult(boolean success, String message, int experienceGained, int goldGained) {
            this.success = success;
            this.message = message;
            this.experienceGained = experienceGained;
            this.goldGained = goldGained;
        }
        
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public int getExperienceGained() { return experienceGained; }
        public int getGoldGained() { return goldGained; }
    }
}

/*
 * ════════════════════════════════════════════════════════════════════════════
 * KEY INTEGRATION PATTERNS DEMONSTRATED:
 * ════════════════════════════════════════════════════════════════════════════
 * 
 * 1. ENUMSET USAGE:
 *    - activeStatuses for efficient bit operations
 *    - completedDifficulties for quest tracking
 * 
 * 2. ENUMMAP USAGE:
 *    - elementalResistances for O(1) lookups
 *    - statusDurations for turn-based effects
 *    - inventory management by slot type
 * 
 * 3. ENUM BUSINESS LOGIC INTEGRATION:
 *    - CharacterClass.canUseWeapon() for equipment validation
 *    - CharacterStatus.applyEffect() for status processing
 *    - QuestDifficulty.getSuccessChance() for quest mechanics
 *    - GameEvent.triggerEvent() for event system
 * 
 * 4. STATE MANAGEMENT:
 *    - Status effect conflicts and combinations
 *    - Valid state transitions (e.g., can't quest while dead)
 *    - Elemental interaction calculations
 * 
 * 5. PERFORMANCE OPTIMIZATIONS:
 *    - EnumSet for fast status checks
 *    - EnumMap for constant-time element lookups
 *    - Cached calculations where appropriate
 * 
 * This class shows how enums aren't just data containers, but active
 * participants in complex business logic, providing type safety,
 * performance, and maintainable code structure!
 */
