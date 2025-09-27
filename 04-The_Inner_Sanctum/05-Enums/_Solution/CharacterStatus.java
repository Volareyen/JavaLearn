/**
 * CharacterStatus.java - State Machine of Character Conditions
 * 
 * Represents different status effects that can afflict characters,
 * implementing a sophisticated state machine with transition rules.
 * 
 * "Status effects are the invisible threads that weave through combat,
 * determining victory and defeat through subtle influence over time."
 */

import java.util.Arrays;
import java.util.EnumSet;

/**
 * Character status effects with state transition logic
 * Each status implements its own effect application and transition rules
 */
public enum CharacterStatus {
    HEALTHY("Perfect health and vitality", false, 0, 0, "The ideal state of being") {
        @Override
        public void applyEffect(GameCharacter character) {
            // Healthy status slowly regenerates health
            if (character.getHealth() < character.getMaxHealth()) {
                int healAmount = Math.min(2, character.getMaxHealth() - character.getHealth());
                // Would heal character here: character.heal(healAmount);
                System.out.println(character.getName() + " naturally regenerates " + healAmount + " health");
            }
        }
        
        @Override
        public boolean canTransitionTo(CharacterStatus newStatus) {
            return newStatus != HEALTHY; // Can transition to any non-healthy status
        }
        
        @Override
        public double getRecoveryChance() {
            return 1.0; // Already healthy
        }
        
        @Override
        public boolean canCoexistWith(CharacterStatus other) {
            return other == HEALTHY || other == BLESSED; // Only with healthy or blessed
        }
    },
    
    POISONED("Toxins course through the bloodstream", true, 3, 8, "Green bubbling poison effect") {
        @Override
        public void applyEffect(GameCharacter character) {
            int poisonDamage = 5 + (character.getLevel() / 3); // Scales with level
            character.takeDamage(poisonDamage, Element.DARK);
            System.out.println(character.getName() + " takes " + poisonDamage + " poison damage!");
        }
        
        @Override
        public boolean canTransitionTo(CharacterStatus newStatus) {
            // Poison can be cured by blessing, death, or natural recovery
            return newStatus == BLESSED || newStatus == DEAD || newStatus == HEALTHY;
        }
        
        @Override
        public double getRecoveryChance() {
            return 0.15; // 15% chance to recover naturally each turn
        }
        
        @Override
        public boolean canCoexistWith(CharacterStatus other) {
            // Poison conflicts with blessing and burning
            return other != BLESSED && other != BURNING && other != HEALTHY;
        }
    },
    
    BURNING("Flames consume flesh and spirit", true, 2, 12, "Orange flickering flames") {
        @Override
        public void applyEffect(GameCharacter character) {
            int fireDamage = 8 + (character.getLevel() / 2); // Higher damage than poison
            character.takeDamage(fireDamage, Element.FIRE);
            System.out.println(character.getName() + " burns for " + fireDamage + " fire damage!");
            
            // Burning spreads to nearby enemies (in a real game)
            if (Math.random() < 0.1) { // 10% chance
                System.out.println("The flames spread menacingly!");
            }
        }
        
        @Override
        public boolean canTransitionTo(CharacterStatus newStatus) {
            // Fire can be extinguished by freezing, blessing, or death
            return newStatus == FROZEN || newStatus == BLESSED || newStatus == DEAD || newStatus == HEALTHY;
        }
        
        @Override
        public double getRecoveryChance() {
            return 0.08; // 8% chance - fire is hard to extinguish
        }
        
        @Override
        public boolean canCoexistWith(CharacterStatus other) {
            // Fire conflicts with frozen, blessed, poisoned
            return other != FROZEN && other != BLESSED && other != POISONED && other != HEALTHY;
        }
    },
    
    FROZEN("Encased in magical ice", true, 4, 0, "Blue crystalline ice coating") {
        @Override
        public void applyEffect(GameCharacter character) {
            // Frozen characters can't act but take no damage this turn
            System.out.println(character.getName() + " is frozen solid and cannot act!");
            
            // Chance to break free increases each turn
            if (Math.random() < 0.2) { // 20% base chance
                System.out.println(character.getName() + " struggles against the ice!");
            }
        }
        
        @Override
        public boolean canTransitionTo(CharacterStatus newStatus) {
            // Ice can melt from fire, be cured by blessing, or naturally thaw
            return newStatus == BURNING || newStatus == BLESSED || newStatus == HEALTHY || newStatus == DEAD;
        }
        
        @Override
        public double getRecoveryChance() {
            return 0.25; // 25% chance to break free
        }
        
        @Override
        public boolean canCoexistWith(CharacterStatus other) {
            // Frozen conflicts with burning, cursed
            return other != BURNING && other != CURSED && other != HEALTHY;
        }
    },
    
    BLESSED("Touched by divine grace", false, 5, 0, "Golden aura of divine protection") {
        @Override
        public void applyEffect(GameCharacter character) {
            // Blessed characters heal and gain combat bonuses
            int healAmount = 3 + (character.getLevel() / 4);
            // character.heal(healAmount);
            System.out.println(character.getName() + " is blessed and heals " + healAmount + " health!");
            
            // Remove one debuff each turn
            for (CharacterStatus status : character.getActiveStatuses()) {
                if (status.isDebuff() && status != CURSED) {
                    character.removeStatus(status);
                    System.out.println("Divine grace removes " + status.name() + " from " + character.getName());
                    break; // Only remove one per turn
                }
            }
        }
        
        @Override
        public boolean canTransitionTo(CharacterStatus newStatus) {
            // Blessing can be dispelled by curse or naturally fade
            return newStatus == CURSED || newStatus == HEALTHY || newStatus == DEAD;
        }
        
        @Override
        public double getRecoveryChance() {
            return 0.05; // 5% chance to naturally fade
        }
        
        @Override
        public boolean canCoexistWith(CharacterStatus other) {
            // Blessing conflicts with all debuffs except cursed (epic battle of light vs dark)
            return !other.isDebuff() || other == CURSED;
        }
    },
    
    CURSED("Afflicted by dark magic", true, 6, 5, "Purple shadowy tendrils") {
        @Override
        public void applyEffect(GameCharacter character) {
            // Cursed characters take extra damage and have reduced effectiveness
            int curseDamage = 3 + (character.getLevel() / 5);
            character.takeDamage(curseDamage, Element.DARK);
            System.out.println(character.getName() + " suffers " + curseDamage + " curse damage!");
            
            // Curse spreads corruption
            if (Math.random() < 0.15) { // 15% chance
                System.out.println("Dark energy swirls around " + character.getName() + "!");
            }
        }
        
        @Override
        public boolean canTransitionTo(CharacterStatus newStatus) {
            // Curses are hard to remove - only blessing or death
            return newStatus == BLESSED || newStatus == DEAD;
        }
        
        @Override
        public double getRecoveryChance() {
            return 0.02; // 2% chance - curses are persistent
        }
        
        @Override
        public boolean canCoexistWith(CharacterStatus other) {
            // Curse conflicts with blessed, but can coexist with other debuffs
            return other != BLESSED && other != HEALTHY;
        }
    },
    
    DEAD("The ultimate status - no recovery possible", true, Integer.MAX_VALUE, 0, "Grayish pallor of death") {
        @Override
        public void applyEffect(GameCharacter character) {
            // Dead characters cannot act and lose resources
            System.out.println(character.getName() + " lies motionless...");
            
            // In some games, dead characters might have ongoing effects
            if (Math.random() < 0.05) { // 5% chance
                System.out.println("A ghostly whisper echoes from " + character.getName() + "...");
            }
        }
        
        @Override
        public boolean canTransitionTo(CharacterStatus newStatus) {
            // Death is final in most cases - would need resurrection magic
            return false; // No normal transitions from death
        }
        
        @Override
        public double getRecoveryChance() {
            return 0.0; // No natural recovery from death
        }
        
        @Override
        public boolean canCoexistWith(CharacterStatus other) {
            // Death prevents all other statuses
            return other == DEAD;
        }
    };
    
    // Instance fields
    private final String description;
    private final boolean isDebuff;
    private final int baseDuration;
    private final int tickDamage;
    private final String visualEffect;
    
    // Constructor
    CharacterStatus(String description, boolean isDebuff, int baseDuration, 
                   int tickDamage, String visualEffect) {
        this.description = description;
        this.isDebuff = isDebuff;
        this.baseDuration = baseDuration;
        this.tickDamage = tickDamage;
        this.visualEffect = visualEffect;
    }
    
    // Abstract methods that each status must implement
    public abstract void applyEffect(GameCharacter character);
    public abstract boolean canTransitionTo(CharacterStatus newStatus);
    public abstract double getRecoveryChance();
    public abstract boolean canCoexistWith(CharacterStatus other);
    
    // Concrete methods available to all statuses
    public String getDescription() { return description; }
    public boolean isDebuff() { return isDebuff; }
    public int getBaseDuration() { return baseDuration; }
    public int getTickDamage() { return tickDamage; }
    public String getVisualEffect() { return visualEffect; }
    
    /**
     * Check if this is a beneficial status
     */
    public boolean isBuff() {
        return !isDebuff && this != HEALTHY;
    }
    
    /**
     * Get the severity level of this status (1-10)
     */
    public int getSeverity() {
        return switch (this) {
            case HEALTHY -> 0;
            case BLESSED -> 0; // Beneficial
            case POISONED -> 4;
            case BURNING -> 6;
            case FROZEN -> 5;
            case CURSED -> 8;
            case DEAD -> 10;
        };
    }
    
    /**
     * Get the priority for status resolution (higher = resolve first)
     */
    public int getResolutionPriority() {
        return switch (this) {
            case DEAD -> 1000;    // Death always processes first
            case BLESSED -> 900;  // Blessing removes debuffs
            case CURSED -> 800;   // Curse amplifies other effects
            case BURNING -> 600;  // Fire spreads
            case POISONED -> 500; // Poison damages
            case FROZEN -> 400;   // Frozen prevents action
            case HEALTHY -> 100;  // Healing processes last
        };
    }
    
    /**
     * Get elemental association of this status
     */
    public Element getElementalAssociation() {
        return switch (this) {
            case BURNING -> Element.FIRE;
            case FROZEN -> Element.WATER;
            case POISONED -> Element.EARTH;
            case BLESSED -> Element.LIGHT;
            case CURSED -> Element.DARK;
            case HEALTHY, DEAD -> null; // No elemental association
        };
    }
    
    /**
     * Calculate modified duration based on character resistance
     */
    public int getModifiedDuration(GameCharacter character) {
        int duration = baseDuration;
        
        // Character level affects duration
        if (isDebuff) {
            duration = Math.max(1, duration - (character.getLevel() / 10));
        } else {
            duration += (character.getLevel() / 15); // Buffs last longer for higher level
        }
        
        // Elemental resistance affects duration
        Element associated = getElementalAssociation();
        if (associated != null) {
            double resistance = character.getElementalResistances().getOrDefault(associated, 0) / 100.0;
            duration = (int)(duration * (1.0 - resistance * 0.5));
        }
        
        return Math.max(1, duration);
    }
    
    /**
     * Check if this status can stack with itself
     */
    public boolean canStack() {
        return this == POISONED || this == BURNING; // Some effects can stack
    }
    
    // ============= STATIC UTILITY METHODS =============
    
    /**
     * Get all debuff statuses
     */
    public static CharacterStatus[] getDebuffs() {
        return Arrays.stream(values())
                .filter(CharacterStatus::isDebuff)
                .toArray(CharacterStatus[]::new);
    }
    
    /**
     * Get all buff statuses
     */
    public static CharacterStatus[] getBuffs() {
        return Arrays.stream(values())
                .filter(CharacterStatus::isBuff)
                .toArray(CharacterStatus[]::new);
    }
    
    /**
     * Get statuses by severity level
     */
    public static CharacterStatus[] getStatusesBySeverity(int minSeverity, int maxSeverity) {
        return Arrays.stream(values())
                .filter(status -> status.getSeverity() >= minSeverity && status.getSeverity() <= maxSeverity)
                .toArray(CharacterStatus[]::new);
    }
    
    /**
     * Get statuses associated with an element
     */
    public static CharacterStatus[] getStatusesByElement(Element element) {
        return Arrays.stream(values())
                .filter(status -> status.getElementalAssociation() == element)
                .toArray(CharacterStatus[]::new);
    }
    
    /**
     * Get statuses sorted by resolution priority
     */
    public static CharacterStatus[] getStatusesByPriority() {
        return Arrays.stream(values())
                .sorted((a, b) -> Integer.compare(b.getResolutionPriority(), a.getResolutionPriority()))
                .toArray(CharacterStatus[]::new);
    }
    
    /**
     * Check if a combination of statuses is valid
     */
    public static boolean isValidCombination(CharacterStatus... statuses) {
        for (int i = 0; i < statuses.length; i++) {
            for (int j = i + 1; j < statuses.length; j++) {
                if (!statuses[i].canCoexistWith(statuses[j])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Get conflicting statuses for a given status
     */
    public static CharacterStatus[] getConflictingStatuses(CharacterStatus status) {
        return Arrays.stream(values())
                .filter(other -> !status.canCoexistWith(other) && other != status)
                .toArray(CharacterStatus[]::new);
    }
    
    /**
     * Get the most severe status from a collection
     */
    public static CharacterStatus getMostSevere(CharacterStatus... statuses) {
        return Arrays.stream(statuses)
                .max((a, b) -> Integer.compare(a.getSeverity(), b.getSeverity()))
                .orElse(HEALTHY);
    }
    
    /**
     * Get active debuffs from a character's status set
     */
    public static EnumSet<CharacterStatus> getActiveDebuffs(GameCharacter character) {
        EnumSet<CharacterStatus> debuffs = EnumSet.noneOf(CharacterStatus.class);
        for (CharacterStatus status : character.getActiveStatuses()) {
            if (status.isDebuff()) {
                debuffs.add(status);
            }
        }
        return debuffs;
    }
    
    /**
     * Calculate total damage per turn from all status effects
     */
    public static int calculateTotalTickDamage(EnumSet<CharacterStatus> activeStatuses) {
        return activeStatuses.stream()
                .mapToInt(CharacterStatus::getTickDamage)
                .sum();
    }
    
    /**
     * Generate status effect transition map
     */
    public static String getStatusTransitionMap() {
        StringBuilder map = new StringBuilder();
        map.append("STATUS EFFECT TRANSITION MAP\n");
        map.append("═══════════════════════════════════════════════════════════════\n");
        
        for (CharacterStatus from : values()) {
            map.append(String.format("\n%s → ", from.name()));
            
            boolean hasTransitions = false;
            for (CharacterStatus to : values()) {
                if (from != to && from.canTransitionTo(to)) {
                    if (hasTransitions) map.append(", ");
                    map.append(to.name());
                    hasTransitions = true;
                }
            }
            
            if (!hasTransitions) {
                map.append("No valid transitions");
            }
        }
        
        map.append("\n\n═══════════════════════════════════════════════════════════════\n");
        return map.toString();
    }
    
    /**
     * Get comprehensive status guide
     */
    public static String getStatusGuide() {
        StringBuilder guide = new StringBuilder();
        guide.append("COMPREHENSIVE STATUS EFFECT GUIDE\n");
        guide.append("═══════════════════════════════════════════════════════════════════════════════\n");
        
        for (CharacterStatus status : values()) {
            guide.append(String.format("\n%s (Severity: %d)\n", status.name(), status.getSeverity()));
            guide.append("─".repeat(status.name().length() + 15)).append("\n");
            guide.append("Description: ").append(status.getDescription()).append("\n");
            guide.append("Type: ").append(status.isDebuff() ? "Debuff" : (status.isBuff() ? "Buff" : "Neutral")).append("\n");
            guide.append("Base Duration: ").append(status.getBaseDuration() == Integer.MAX_VALUE ? "Permanent" : status.getBaseDuration() + " turns").append("\n");
            guide.append("Tick Damage: ").append(status.getTickDamage()).append("\n");
            guide.append("Visual Effect: ").append(status.getVisualEffect()).append("\n");
            guide.append("Recovery Chance: ").append(String.format("%.1f%%", status.getRecoveryChance() * 100)).append("\n");
            
            Element element = status.getElementalAssociation();
            if (element != null) {
                guide.append("Element: ").append(element.name()).append("\n");
            }
            
            // Show conflicts
            CharacterStatus[] conflicts = getConflictingStatuses(status);
            if (conflicts.length > 0) {
                guide.append("Conflicts with: ");
                guide.append(Arrays.stream(conflicts)
                        .map(Enum::name)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("None"));
                guide.append("\n");
            }
        }
        
        guide.append("\n═══════════════════════════════════════════════════════════════════════════════\n");
        
        return guide.toString();
    }
}

/*
 * ════════════════════════════════════════════════════════════════════════════
 * CHARACTER STATUS DESIGN PATTERNS DEMONSTRATED:
 * ════════════════════════════════════════════════════════════════════════════
 * 
 * 1. STATE MACHINE IMPLEMENTATION:
 *    - Each status defines valid transitions to other statuses
 *    - Complex rules for status coexistence and conflicts
 *    - Recovery mechanics with probability calculations
 * 
 * 2. ABSTRACT METHOD SPECIALIZATION:
 *    - applyEffect() - unique behavior for each status per turn
 *    - canTransitionTo() - state machine transition validation
 *    - canCoexistWith() - complex interaction rules
 * 
 * 3. TEMPORAL MECHANICS:
 *    - Duration calculations with character-specific modifiers
 *    - Recovery chances that change over time
 *    - Priority-based resolution order
 * 
 * 4. ELEMENTAL INTEGRATION:
 *    - Status effects tied to elemental damage types
 *    - Resistance calculations affecting duration
 *    - Elemental interactions (fire vs ice)
 * 
 * 5. CONFLICT RESOLUTION:
 *    - Status compatibility matrices
 *    - Automatic conflict detection and resolution
 *    - Severity-based priority systems
 * 
 * 6. COMPREHENSIVE ANALYSIS:
 *    - Status filtering by type, severity, element
 *    - Transition mapping and validation tools
 *    - Statistical analysis of status combinations
 * 
 * This enum demonstrates how status effects can be sophisticated
 * entities that understand their relationships, conflicts, and
 * temporal mechanics in a complex game system!
 */
