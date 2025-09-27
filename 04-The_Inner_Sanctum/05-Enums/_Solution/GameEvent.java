/**
 * GameEvent.java - The Chronicle of Adventures
 * 
 * Represents significant game events with complex business logic,
 * event chaining, and dynamic requirements processing.
 * 
 * "Every great adventure is composed of moments that define the hero.
 * These events shape not just the story, but the soul of the character."
 */

import java.util.*;

/**
 * Game events with complex business logic and event chaining
 * Features nested enums for event rarity classification
 */
public enum GameEvent {
    CHARACTER_CREATED(1, "Welcome to the world, brave adventurer!", "character_create.wav", 0, "A new hero begins their journey") {
        @Override
        public void triggerEvent(GameCharacter character) {
            System.out.println("🌟 " + character.getName() + " has entered the realm!");
            System.out.println("May your adventures be legendary and your quests successful!");
            
            // Give starting blessing
            character.applyStatus(CharacterStatus.BLESSED, 3);
        }
        
        @Override
        public boolean meetsRequirements(GameCharacter character) {
            return true; // Always available for character creation
        }
        
        @Override
        public GameEvent[] getFollowupEvents() {
            return new GameEvent[0]; // No automatic follow-ups
        }
        
        @Override
        public Rarity getRarity() {
            return Rarity.COMMON;
        }
        
        @Override
        public Map<String, Object> getEventData() {
            Map<String, Object> data = new HashMap<>();
            data.put("welcome_bonus", 100);
            data.put("starting_items", Arrays.asList("Health Potion", "Training Sword"));
            return data;
        }
    },
    
    LEVEL_UP(5, "Power courses through your veins!", "level_up.wav", 50, "Character gains new power") {
        @Override
        public void triggerEvent(GameCharacter character) {
            System.out.println("⭐ " + character.getName() + " has grown stronger!");
            
            // Level up effects
            if (character.getLevel() % 5 == 0) {
                System.out.println("🎉 Milestone reached! Special abilities unlocked!");
                character.applyStatus(CharacterStatus.BLESSED, 5);
            }
            
            // Restore health and mana on level up
            System.out.println("✨ Health and mana fully restored!");
        }
        
        @Override
        public boolean meetsRequirements(GameCharacter character) {
            return character.getLevel() > 1; // Must be above level 1
        }
        
        @Override
        public GameEvent[] getFollowupEvents() {
            return new GameEvent[]{POWER_SURGE}; // Level up triggers power surge
        }
        
        @Override
        public Rarity getRarity() {
            return Rarity.COMMON;
        }
        
        @Override
        public Map<String, Object> getEventData() {
            Map<String, Object> data = new HashMap<>();
            data.put("stat_bonus", 5);
            data.put("skill_points", 1);
            return data;
        }
    },
    
    QUEST_COMPLETE(3, "Another quest accomplished!", "quest_complete.wav", 25, "Successfully finished a quest") {
        @Override
        public void triggerEvent(GameCharacter character) {
            System.out.println("🏆 " + character.getName() + " has completed a quest!");
            
            // Random bonus effects
            if (Math.random() < 0.3) { // 30% chance
                System.out.println("🎁 Bonus reward discovered!");
                // Would give bonus items/gold here
            }
            
            if (Math.random() < 0.1) { // 10% chance
                triggerFollowUpEvent(character, REPUTATION_GAIN);
            }
        }
        
        @Override
        public boolean meetsRequirements(GameCharacter character) {
            return !character.getActiveStatuses().contains(CharacterStatus.DEAD);
        }
        
        @Override
        public GameEvent[] getFollowupEvents() {
            return new GameEvent[]{EXPERIENCE_GAIN}; // Always gain experience
        }
        
        @Override
        public Rarity getRarity() {
            return Rarity.COMMON;
        }
        
        @Override
        public Map<String, Object> getEventData() {
            Map<String, Object> data = new HashMap<>();
            data.put("completion_bonus", 1.2);
            data.put("reputation_points", 10);
            return data;
        }
    },
    
    BOSS_DEFEATED(8, "The mighty foe has fallen!", "boss_defeat.wav", 200, "Defeated a powerful enemy") {
        @Override
        public void triggerEvent(GameCharacter character) {
            System.out.println("👹 " + character.getName() + " has slain a mighty boss!");
            System.out.println("🎖️ Your legend grows throughout the land!");
            
            // Boss victories are significant
            character.applyStatus(CharacterStatus.BLESSED, 10);
            
            // High chance of rare loot
            if (Math.random() < 0.8) { // 80% chance
                System.out.println("💎 Rare treasure obtained!");
            }
        }
        
        @Override
        public boolean meetsRequirements(GameCharacter character) {
            return character.getLevel() >= 5 && // Must be experienced enough
                   !character.getActiveStatuses().contains(CharacterStatus.DEAD);
        }
        
        @Override
        public GameEvent[] getFollowupEvents() {
            return new GameEvent[]{TREASURE_FOUND, REPUTATION_GAIN, POWER_SURGE};
        }
        
        @Override
        public Rarity getRarity() {
            return Rarity.RARE;
        }
        
        @Override
        public Map<String, Object> getEventData() {
            Map<String, Object> data = new HashMap<>();
            data.put("prestige_bonus", 50);
            data.put("legendary_item_chance", 0.15);
            return data;
        }
    },
    
    TREASURE_FOUND(4, "Riches beyond imagination!", "treasure.wav", 75, "Discovered valuable treasure") {
        @Override
        public void triggerEvent(GameCharacter character) {
            System.out.println("💰 " + character.getName() + " has discovered treasure!");
            
            // Different types of treasure based on character level
            if (character.getLevel() < 5) {
                System.out.println("🪙 Gold coins sparkle in the light!");
            } else if (character.getLevel() < 15) {
                System.out.println("💎 Precious gems glint with magical energy!");
            } else {
                System.out.println("⚡ Ancient artifacts pulse with power!");
            }
            
            // Higher level = better treasure
            int goldBonus = character.getLevel() * 10;
            System.out.println("💵 Gained " + goldBonus + " gold!");
        }
        
        @Override
        public boolean meetsRequirements(GameCharacter character) {
            return true; // Anyone can find treasure
        }
        
        @Override
        public GameEvent[] getFollowupEvents() {
            return new GameEvent[]{}; // Treasure is usually self-contained
        }
        
        @Override
        public Rarity getRarity() {
            return Rarity.UNCOMMON;
        }
        
        @Override
        public Map<String, Object> getEventData() {
            Map<String, Object> data = new HashMap<>();
            data.put("gold_multiplier", 2.0);
            data.put("magic_item_chance", 0.25);
            return data;
        }
    },
    
    PLAYER_DEATH(10, "Darkness claims another soul...", "death.wav", -500, "Character has perished") {
        @Override
        public void triggerEvent(GameCharacter character) {
            System.out.println("💀 " + character.getName() + " has fallen in battle...");
            System.out.println("⚰️ The adventure ends, but legends never die.");
            
            // Death penalties
            System.out.println("👻 Some experience has been lost in death...");
            
            // Clear all status effects except death
            for (CharacterStatus status : character.getActiveStatuses()) {
                if (status != CharacterStatus.DEAD) {
                    character.removeStatus(status);
                }
            }
        }
        
        @Override
        public boolean meetsRequirements(GameCharacter character) {
            return character.getHealth() <= 0;
        }
        
        @Override
        public GameEvent[] getFollowupEvents() {
            return new GameEvent[]{AFTERLIFE_JUDGMENT}; // Death leads to judgment
        }
        
        @Override
        public Rarity getRarity() {
            return Rarity.UNCOMMON; // Hopefully uncommon!
        }
        
        @Override
        public Map<String, Object> getEventData() {
            Map<String, Object> data = new HashMap<>();
            data.put("experience_penalty", 0.1); // Lose 10% experience
            data.put("resurrection_cost", 100);
            return data;
        }
    },
    
    POWER_SURGE(6, "Energy crackles through the air!", "power_surge.wav", 30, "Temporary power increase") {
        @Override
        public void triggerEvent(GameCharacter character) {
            System.out.println("⚡ " + character.getName() + " feels a surge of power!");
            
            // Temporary power boost
            character.applyStatus(CharacterStatus.BLESSED, 5);
            System.out.println("✨ All abilities enhanced for a short time!");
        }
        
        @Override
        public boolean meetsRequirements(GameCharacter character) {
            return !character.getActiveStatuses().contains(CharacterStatus.CURSED);
        }
        
        @Override
        public GameEvent[] getFollowupEvents() {
            return new GameEvent[]{}; // Power surge is self-contained
        }
        
        @Override
        public Rarity getRarity() {
            return Rarity.UNCOMMON;
        }
        
        @Override
        public Map<String, Object> getEventData() {
            Map<String, Object> data = new HashMap<>();
            data.put("power_multiplier", 1.5);
            data.put("duration", 5);
            return data;
        }
    },
    
    EXPERIENCE_GAIN(2, "Knowledge flows like water!", "experience.wav", 15, "Gained valuable experience") {
        @Override
        public void triggerEvent(GameCharacter character) {
            System.out.println("📚 " + character.getName() + " gains valuable experience!");
            
            int expBonus = 10 + (character.getLevel() * 5);
            System.out.println("🎓 +" + expBonus + " experience points!");
        }
        
        @Override
        public boolean meetsRequirements(GameCharacter character) {
            return true; // Always available
        }
        
        @Override
        public GameEvent[] getFollowupEvents() {
            return new GameEvent[]{}; // Experience is usually final
        }
        
        @Override
        public Rarity getRarity() {
            return Rarity.COMMON;
        }
        
        @Override
        public Map<String, Object> getEventData() {
            Map<String, Object> data = new HashMap<>();
            data.put("base_experience", 10);
            data.put("level_multiplier", 5);
            return data;
        }
    },
    
    REPUTATION_GAIN(7, "Your deeds echo through the realm!", "reputation.wav", 40, "Fame and recognition increase") {
        @Override
        public void triggerEvent(GameCharacter character) {
            System.out.println("🌟 " + character.getName() + "'s reputation grows!");
            System.out.println("📰 Tales of your deeds spread far and wide!");
            
            // Reputation has practical benefits
            System.out.println("💰 Better prices at shops!");
            System.out.println("🗣️ New quests become available!");
        }
        
        @Override
        public boolean meetsRequirements(GameCharacter character) {
            return character.getLevel() >= 3; // Must have some experience
        }
        
        @Override
        public GameEvent[] getFollowupEvents() {
            return new GameEvent[]{}; // Reputation is self-contained
        }
        
        @Override
        public Rarity getRarity() {
            return Rarity.UNCOMMON;
        }
        
        @Override
        public Map<String, Object> getEventData() {
            Map<String, Object> data = new HashMap<>();
            data.put("reputation_points", 25);
            data.put("shop_discount", 0.1); // 10% discount
            return data;
        }
    },
    
    AFTERLIFE_JUDGMENT(9, "The cosmic scales weigh your soul...", "judgment.wav", 0, "Post-death evaluation") {
        @Override
        public void triggerEvent(GameCharacter character) {
            System.out.println("⚖️ " + character.getName() + " stands before the cosmic scales...");
            
            // Judge based on character's deeds
            int goodDeeds = character.getEventHistory().size(); // Simple metric
            
            if (goodDeeds >= 10) {
                System.out.println("😇 Your heroic deeds shine brightly! Resurrection is possible!");
            } else if (goodDeeds >= 5) {
                System.out.println("😐 Your deeds are balanced. A second chance may be earned.");
            } else {
                System.out.println("😈 Your deeds are few. The afterlife claims you...");
            }
        }
        
        @Override
        public boolean meetsRequirements(GameCharacter character) {
            return character.getActiveStatuses().contains(CharacterStatus.DEAD);
        }
        
        @Override
        public GameEvent[] getFollowupEvents() {
            return new GameEvent[]{}; // Judgment is final
        }
        
        @Override
        public Rarity getRarity() {
            return Rarity.LEGENDARY;
        }
        
        @Override
        public Map<String, Object> getEventData() {
            Map<String, Object> data = new HashMap<>();
            data.put("judgment_threshold", 10);
            data.put("resurrection_chance", 0.5);
            return data;
        }
    };
    
    /**
     * Nested enum for event rarity classification
     */
    public enum Rarity {
        COMMON(1.0, "Happens regularly", "#CCCCCC"),
        UNCOMMON(0.6, "Happens occasionally", "#00FF00"),
        RARE(0.3, "Happens rarely", "#0080FF"),
        EPIC(0.1, "Happens very rarely", "#8000FF"),
        LEGENDARY(0.05, "Once in a lifetime", "#FF8000"),
        MYTHIC(0.01, "Stuff of legends", "#FF0040");
        
        private final double spawnRate;
        private final String description;
        private final String colorCode;
        
        Rarity(double spawnRate, String description, String colorCode) {
            this.spawnRate = spawnRate;
            this.description = description;
            this.colorCode = colorCode;
        }
        
        public double getSpawnRate() { return spawnRate; }
        public String getDescription() { return description; }
        public String getColorCode() { return colorCode; }
        
        public boolean shouldTrigger() {
            return Math.random() < spawnRate;
        }
    }
    
    // Instance fields
    private final int priority;
    private final String notificationMessage;
    private final String soundEffect;
    private final int experienceGained;
    private final String description;
    
    // Constructor
    GameEvent(int priority, String notificationMessage, String soundEffect, 
              int experienceGained, String description) {
        this.priority = priority;
        this.notificationMessage = notificationMessage;
        this.soundEffect = soundEffect;
        this.experienceGained = experienceGained;
        this.description = description;
    }
    
    // Abstract methods that each event must implement
    public abstract void triggerEvent(GameCharacter character);
    public abstract boolean meetsRequirements(GameCharacter character);
    public abstract GameEvent[] getFollowupEvents();
    public abstract Rarity getRarity();
    public abstract Map<String, Object> getEventData();
    
    // Concrete methods available to all events
    public int getPriority() { return priority; }
    public String getNotificationMessage() { return notificationMessage; }
    public String getSoundEffect() { return soundEffect; }
    public int getExperienceGained() { return experienceGained; }
    public String getDescription() { return description; }
    
    /**
     * Helper method for events to trigger follow-up events
     */
    protected void triggerFollowUpEvent(GameCharacter character, GameEvent event) {
        if (event.meetsRequirements(character)) {
            character.triggerEvent(event);
        }
    }
    
    /**
     * Get event category
     */
    public String getCategory() {
        return switch (this) {
            case CHARACTER_CREATED, LEVEL_UP, POWER_SURGE -> "Character Development";
            case QUEST_COMPLETE, BOSS_DEFEATED -> "Combat Achievement";
            case TREASURE_FOUND, EXPERIENCE_GAIN, REPUTATION_GAIN -> "Rewards";
            case PLAYER_DEATH, AFTERLIFE_JUDGMENT -> "Consequences";
        };
    }
    
    /**
     * Check if event is beneficial
     */
    public boolean isBeneficial() {
        return experienceGained > 0;
    }
    
    /**
     * Check if event is negative
     */
    public boolean isNegative() {
        return experienceGained < 0;
    }
    
    /**
     * Get event impact level (1-10)
     */
    public int getImpactLevel() {
        return Math.min(10, Math.abs(experienceGained) / 50 + priority);
    }
    
    /**
     * Check if event can chain to another event
     */
    public boolean canChainTo(GameEvent other) {
        return Arrays.asList(getFollowupEvents()).contains(other);
    }
    
    /**
     * Get visual representation
     */
    public String getEmoji() {
        return switch (this) {
            case CHARACTER_CREATED -> "🌟";
            case LEVEL_UP -> "⭐";
            case QUEST_COMPLETE -> "🏆";
            case BOSS_DEFEATED -> "👹";
            case TREASURE_FOUND -> "💰";
            case PLAYER_DEATH -> "💀";
            case POWER_SURGE -> "⚡";
            case EXPERIENCE_GAIN -> "📚";
            case REPUTATION_GAIN -> "🌟";
            case AFTERLIFE_JUDGMENT -> "⚖️";
        };
    }
    
    // ============= STATIC UTILITY METHODS =============
    
    /**
     * Get events sorted by priority
     */
    public static GameEvent[] getEventsByPriority() {
        return Arrays.stream(values())
                .sorted(Comparator.comparingInt(GameEvent::getPriority).reversed())
                .toArray(GameEvent[]::new);
    }
    
    /**
     * Get events by category
     */
    public static GameEvent[] getEventsByCategory(String category) {
        return Arrays.stream(values())
                .filter(event -> event.getCategory().equals(category))
                .toArray(GameEvent[]::new);
    }
    
    /**
     * Get beneficial events
     */
    public static GameEvent[] getBeneficialEvents() {
        return Arrays.stream(values())
                .filter(GameEvent::isBeneficial)
                .toArray(GameEvent[]::new);
    }
    
    /**
     * Get negative events
     */
    public static GameEvent[] getNegativeEvents() {
        return Arrays.stream(values())
                .filter(GameEvent::isNegative)
                .toArray(GameEvent[]::new);
    }
    
    /**
     * Get events by rarity
     */
    public static GameEvent[] getEventsByRarity(Rarity rarity) {
        return Arrays.stream(values())
                .filter(event -> event.getRarity() == rarity)
                .toArray(GameEvent[]::new);
    }
    
    /**
     * Get events available to character
     */
    public static GameEvent[] getAvailableEvents(GameCharacter character) {
        return Arrays.stream(values())
                .filter(event -> event.meetsRequirements(character))
                .toArray(GameEvent[]::new);
    }
    
    /**
     * Get events by impact level
     */
    public static GameEvent[] getHighImpactEvents() {
        return Arrays.stream(values())
                .filter(event -> event.getImpactLevel() >= 7)
                .toArray(GameEvent[]::new);
    }
    
    /**
     * Calculate total possible experience from all events
     */
    public static int getTotalPossibleExperience() {
        return Arrays.stream(values())
                .mapToInt(GameEvent::getExperienceGained)
                .filter(exp -> exp > 0)
                .sum();
    }
    
    /**
     * Generate event chain analysis
     */
    public static String getEventChainAnalysis() {
        StringBuilder analysis = new StringBuilder();
        analysis.append("EVENT CHAIN ANALYSIS\n");
        analysis.append("═══════════════════════════════════════════════════════════════\n");
        
        for (GameEvent event : values()) {
            analysis.append(String.format("\n%s %s → ",
                event.getEmoji(), event.name()));
            
            GameEvent[] followUps = event.getFollowupEvents();
            if (followUps.length > 0) {
                analysis.append(Arrays.stream(followUps)
                        .map(e -> e.getEmoji() + " " + e.name())
                        .reduce((a, b) -> a + ", " + b)
                        .orElse(""));
            } else {
                analysis.append("No follow-ups");
            }
        }
        
        analysis.append("\n\n═══════════════════════════════════════════════════════════════\n");
        return analysis.toString();
    }
    
    /**
     * Generate comprehensive event guide
     */
    public static String getEventGuide() {
        StringBuilder guide = new StringBuilder();
        guide.append("COMPREHENSIVE GAME EVENT GUIDE\n");
        guide.append("═══════════════════════════════════════════════════════════════════════════════\n");
        
        // Group by category
        Map<String, List<GameEvent>> eventsByCategory = new HashMap<>();
        for (GameEvent event : values()) {
            eventsByCategory.computeIfAbsent(event.getCategory(), k -> new ArrayList<>()).add(event);
        }
        
        for (Map.Entry<String, List<GameEvent>> entry : eventsByCategory.entrySet()) {
            guide.append(String.format("\n📂 %s\n", entry.getKey()));
            guide.append("─".repeat(entry.getKey().length() + 5)).append("\n");
            
            for (GameEvent event : entry.getValue()) {
                guide.append(String.format("\n%s %s (%s)\n",
                    event.getEmoji(), event.name(), event.getRarity().name()));
                guide.append("Description: ").append(event.getDescription()).append("\n");
                guide.append("Experience: ").append(event.getExperienceGained()).append(" XP\n");
                guide.append("Priority: ").append(event.getPriority()).append("/10\n");
                guide.append("Impact: ").append(event.getImpactLevel()).append("/10\n");
                guide.append("Rarity: ").append(event.getRarity().getDescription()).append("\n");
                guide.append("Message: \"").append(event.getNotificationMessage()).append("\"\n");
                
                GameEvent[] followUps = event.getFollowupEvents();
                if (followUps.length > 0) {
                    guide.append("Triggers: ");
                    guide.append(Arrays.stream(followUps)
                            .map(Enum::name)
                            .reduce((a, b) -> a + ", " + b)
                            .orElse(""));
                    guide.append("\n");
                }
            }
        }
        
        guide.append("\n═══════════════════════════════════════════════════════════════════════════════\n");
        
        return guide.toString();
    }
    
    /**
     * Simulate random event sequence
     */
    public static List<GameEvent> simulateEventSequence(GameCharacter character, int maxEvents) {
        List<GameEvent> sequence = new ArrayList<>();
        Set<GameEvent> triggered = new HashSet<>();
        
        for (int i = 0; i < maxEvents; i++) {
            GameEvent[] available = getAvailableEvents(character);
            
            if (available.length == 0) break;
            
            // Weighted random selection based on rarity
            GameEvent selected = null;
            for (GameEvent event : available) {
                if (!triggered.contains(event) && event.getRarity().shouldTrigger()) {
                    selected = event;
                    break;
                }
            }
            
            if (selected != null) {
                sequence.add(selected);
                triggered.add(selected);
                
                // Add follow-up events
                for (GameEvent followUp : selected.getFollowupEvents()) {
                    if (followUp.meetsRequirements(character)) {
                        sequence.add(followUp);
                        triggered.add(followUp);
                    }
                }
            }
        }
        
        return sequence;
    }
}

/*
 * ════════════════════════════════════════════════════════════════════════════
 * GAME EVENT DESIGN PATTERNS DEMONSTRATED:
 * ════════════════════════════════════════════════════════════════════════════
 * 
 * 1. COMPLEX BUSINESS LOGIC:
 *    - Event-specific processing with character interaction
 *    - Dynamic requirements checking based on character state
 *    - Conditional effects and probability-based outcomes
 * 
 * 2. EVENT CHAINING SYSTEM:
 *    - Follow-up events that trigger automatically
 *    - Chain validation and requirement checking
 *    - Recursive event processing capabilities
 * 
 * 3. NESTED ENUM INTEGRATION:
 *    - Rarity classification with spawn rates and descriptions
 *    - Color coding for UI integration
 *    - Probability-based event triggering
 * 
 * 4. DYNAMIC DATA STRUCTURES:
 *    - Event-specific data maps for flexible configuration
 *    - Priority-based processing orders
 *    - Impact level calculations for UI feedback
 * 
 * 5. COMPREHENSIVE CATEGORIZATION:
 *    - Event grouping by type and impact
 *    - Beneficial vs negative event classification
 *    - Multi-criteria filtering and analysis
 * 
 * 6. SIMULATION AND ANALYSIS:
 *    - Event sequence simulation for testing
 *    - Chain analysis for understanding relationships
 *    - Statistical analysis of event distributions
 * 
 * This enum demonstrates how game events can be sophisticated
 * systems that understand their context, requirements, and
 * relationships while providing rich gameplay experiences!
 */
