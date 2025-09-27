/**
 * QuestDifficulty.java - Challenge Scaling and Reward Systems
 * 
 * Represents different quest difficulty levels with dynamic scaling,
 * success probability calculations, and balanced reward distribution.
 * 
 * "A true adventurer seeks not the easy path, but the one that
 * transforms them through struggle into something greater."
 */

import java.util.Arrays;

/**
 * Quest difficulty levels with intelligent scaling and reward systems
 */
public enum QuestDifficulty {
    TRIVIAL(1, 0.5, 10, 5, "A simple task for beginners") {
        @Override
        public double getSuccessChance(int characterLevel) {
            // Trivial quests are almost always successful
            return Math.min(0.98, 0.8 + (characterLevel * 0.02));
        }
        
        @Override
        public int getExperienceReward(int characterLevel) {
            // Low but guaranteed experience
            return Math.max(5, 15 - (characterLevel / 2));
        }
        
        @Override
        public int getGoldReward(int characterLevel) {
            // Minimal gold reward
            return Math.max(2, 8 - (characterLevel / 3));
        }
        
        @Override
        public boolean requiresParty() {
            return false; // Solo content
        }
        
        @Override
        public String getQuestTypeDescription() {
            return "Delivery missions, simple gathering, basic training";
        }
    },
    
    EASY(3, 1.0, 25, 15, "Suitable for novice adventurers") {
        @Override
        public double getSuccessChance(int characterLevel) {
            if (characterLevel <= 2) return 0.6;
            if (characterLevel <= 5) return 0.8;
            return Math.min(0.95, 0.85 + (characterLevel - 5) * 0.01);
        }
        
        @Override
        public int getExperienceReward(int characterLevel) {
            // Decent experience for low levels, scales down
            return Math.max(10, 35 - characterLevel);
        }
        
        @Override
        public int getGoldReward(int characterLevel) {
            return Math.max(5, 20 - (characterLevel / 2));
        }
        
        @Override
        public boolean requiresParty() {
            return false; // Still solo-friendly
        }
        
        @Override
        public String getQuestTypeDescription() {
            return "Combat training, escort missions, minor monster hunting";
        }
    },
    
    MEDIUM(6, 1.5, 50, 35, "Balanced challenge for experienced adventurers") {
        @Override
        public double getSuccessChance(int characterLevel) {
            if (characterLevel <= 3) return 0.3;
            if (characterLevel <= 6) return 0.6;
            if (characterLevel <= 10) return 0.8;
            return Math.min(0.9, 0.75 + (characterLevel - 10) * 0.01);
        }
        
        @Override
        public int getExperienceReward(int characterLevel) {
            // Good experience that stays relevant longer
            return Math.max(20, 80 - characterLevel);
        }
        
        @Override
        public int getGoldReward(int characterLevel) {
            return Math.max(15, 50 - characterLevel);
        }
        
        @Override
        public boolean requiresParty() {
            return false; // Optional party
        }
        
        @Override
        public String getQuestTypeDescription() {
            return "Dungeon exploration, monster contracts, mystery solving";
        }
    },
    
    HARD(10, 2.0, 100, 75, "Serious challenge requiring skill and preparation") {
        @Override
        public double getSuccessChance(int characterLevel) {
            if (characterLevel <= 5) return 0.15;
            if (characterLevel <= 8) return 0.4;
            if (characterLevel <= 12) return 0.65;
            if (characterLevel <= 15) return 0.8;
            return Math.min(0.85, 0.7 + (characterLevel - 15) * 0.01);
        }
        
        @Override
        public int getExperienceReward(int characterLevel) {
            // High experience that remains valuable
            return Math.max(40, 150 - characterLevel);
        }
        
        @Override
        public int getGoldReward(int characterLevel) {
            return Math.max(30, 100 - characterLevel);
        }
        
        @Override
        public boolean requiresParty(int characterLevel) {
            return characterLevel < 12; // Recommended party for lower levels
        }
        
        @Override
        public String getQuestTypeDescription() {
            return "Boss fights, complex dungeons, political intrigue";
        }
    },
    
    LEGENDARY(15, 3.0, 250, 150, "Epic challenges for heroes of renown") {
        @Override
        public double getSuccessChance(int characterLevel) {
            if (characterLevel <= 10) return 0.05;
            if (characterLevel <= 15) return 0.25;
            if (characterLevel <= 20) return 0.5;
            if (characterLevel <= 25) return 0.7;
            return Math.min(0.8, 0.6 + (characterLevel - 25) * 0.008);
        }
        
        @Override
        public int getExperienceReward(int characterLevel) {
            // Massive experience rewards
            return Math.max(100, 400 - (characterLevel * 2));
        }
        
        @Override
        public int getGoldReward(int characterLevel) {
            return Math.max(75, 250 - characterLevel);
        }
        
        @Override
        public boolean requiresParty(int characterLevel) {
            return characterLevel < 20; // Almost always requires party
        }
        
        @Override
        public String getQuestTypeDescription() {
            return "Dragon slaying, realm-saving missions, artifact recovery";
        }
    },
    
    MYTHIC(25, 5.0, 500, 300, "World-shaping quests that define eras") {
        @Override
        public double getSuccessChance(int characterLevel) {
            if (characterLevel <= 15) return 0.01; // Essentially impossible
            if (characterLevel <= 20) return 0.1;
            if (characterLevel <= 25) return 0.3;
            if (characterLevel <= 30) return 0.5;
            return Math.min(0.75, 0.4 + (characterLevel - 30) * 0.005);
        }
        
        @Override
        public int getExperienceReward(int characterLevel) {
            // Legendary experience gains
            return Math.max(200, 800 - (characterLevel * 3));
        }
        
        @Override
        public int getGoldReward(int characterLevel) {
            return Math.max(150, 500 - (characterLevel * 2));
        }
        
        @Override
        public boolean requiresParty() {
            return true; // Always requires a party
        }
        
        @Override
        public String getQuestTypeDescription() {
            return "God-slaying, reality alteration, cosmic balance restoration";
        }
    };
    
    // Instance fields
    private final int recommendedLevel;
    private final double experienceMultiplier;
    private final int baseGoldReward;
    private final int failurePenalty;
    private final String description;
    
    // Constructor
    QuestDifficulty(int recommendedLevel, double experienceMultiplier, 
                   int baseGoldReward, int failurePenalty, String description) {
        this.recommendedLevel = recommendedLevel;
        this.experienceMultiplier = experienceMultiplier;
        this.baseGoldReward = baseGoldReward;
        this.failurePenalty = failurePenalty;
        this.description = description;
    }
    
    // Abstract methods that each difficulty must implement
    public abstract double getSuccessChance(int characterLevel);
    public abstract int getExperienceReward(int characterLevel);
    public abstract int getGoldReward(int characterLevel);
    public abstract boolean requiresParty(int characterLevel);
    public abstract String getQuestTypeDescription();
    
    // Concrete methods available to all difficulties
    public int getRecommendedLevel() { return recommendedLevel; }
    public double getExperienceMultiplier() { return experienceMultiplier; }
    public int getBaseGoldReward() { return baseGoldReward; }
    public int getFailurePenalty() { return failurePenalty; }
    public String getDescription() { return description; }
    
    /**
     * Calculate the risk/reward ratio for a character level
     */
    public double getRiskRewardRatio(int characterLevel) {
        double successChance = getSuccessChance(characterLevel);
        double expectedReward = (getExperienceReward(characterLevel) + getGoldReward(characterLevel)) * successChance;
        double expectedPenalty = getFailurePenalty() * (1.0 - successChance);
        
        return expectedReward / (expectedPenalty + 1.0); // Avoid division by zero
    }
    
    /**
     * Get difficulty tier (1-6)
     */
    public int getDifficultyTier() {
        return ordinal() + 1;
    }
    
    /**
     * Check if quest is appropriate for character level
     */
    public boolean isAppropriateFor(int characterLevel) {
        return Math.abs(characterLevel - recommendedLevel) <= 3;
    }
    
    /**
     * Check if quest is too hard for character
     */
    public boolean isTooHardFor(int characterLevel) {
        return characterLevel < (recommendedLevel - 5);
    }
    
    /**
     * Check if quest is too easy for character
     */
    public boolean isTooEasyFor(int characterLevel) {
        return characterLevel > (recommendedLevel + 8);
    }
    
    /**
     * Get estimated completion time in hours
     */
    public double getEstimatedCompletionTime() {
        return switch (this) {
            case TRIVIAL -> 0.25; // 15 minutes
            case EASY -> 0.5;     // 30 minutes
            case MEDIUM -> 1.5;   // 1.5 hours
            case HARD -> 3.0;     // 3 hours
            case LEGENDARY -> 8.0; // 8 hours
            case MYTHIC -> 20.0;   // 20 hours
        };
    }
    
    /**
     * Get minimum party size recommendation
     */
    public int getRecommendedPartySize() {
        return switch (this) {
            case TRIVIAL, EASY -> 1;
            case MEDIUM -> 2;
            case HARD -> 3;
            case LEGENDARY -> 4;
            case MYTHIC -> 5;
        };
    }
    
    /**
     * Calculate prestige points earned
     */
    public int getPrestigePoints(int characterLevel) {
        if (isTooEasyFor(characterLevel)) return 1; // Minimal prestige for easy content
        
        int basePrestige = getDifficultyTier() * 10;
        double levelDifference = Math.max(0, recommendedLevel - characterLevel);
        double bonus = levelDifference * 0.1; // Bonus for attempting harder content
        
        return (int)(basePrestige * (1.0 + bonus));
    }
    
    /**
     * Get loot rarity modifier
     */
    public double getLootRarityModifier() {
        return switch (this) {
            case TRIVIAL -> 0.1;  // 10% chance for rare loot
            case EASY -> 0.2;     // 20% chance
            case MEDIUM -> 0.4;   // 40% chance
            case HARD -> 0.6;     // 60% chance
            case LEGENDARY -> 0.8; // 80% chance
            case MYTHIC -> 0.95;   // 95% chance
        };
    }
    
    // ============= STATIC UTILITY METHODS =============
    
    /**
     * Get appropriate difficulty for character level
     */
    public static QuestDifficulty getAppropriate(int characterLevel) {
        return Arrays.stream(values())
                .filter(difficulty -> difficulty.isAppropriateFor(characterLevel))
                .findFirst()
                .orElse(Arrays.stream(values())
                        .min((a, b) -> Integer.compare(
                            Math.abs(a.getRecommendedLevel() - characterLevel),
                            Math.abs(b.getRecommendedLevel() - characterLevel)))
                        .orElse(MEDIUM));
    }
    
    /**
     * Get all difficulties suitable for character level
     */
    public static QuestDifficulty[] getSuitableFor(int characterLevel) {
        return Arrays.stream(values())
                .filter(difficulty -> !difficulty.isTooHardFor(characterLevel))
                .toArray(QuestDifficulty[]::new);
    }
    
    /**
     * Get difficulties that require a party
     */
    public static QuestDifficulty[] getPartyQuests() {
        return Arrays.stream(values())
                .filter(QuestDifficulty::requiresParty)
                .toArray(QuestDifficulty[]::new);
    }
    
    /**
     * Get solo-friendly difficulties
     */
    public static QuestDifficulty[] getSoloQuests() {
        return Arrays.stream(values())
                .filter(difficulty -> !difficulty.requiresParty())
                .toArray(QuestDifficulty[]::new);
    }
    
    /**
     * Get difficulties by tier range
     */
    public static QuestDifficulty[] getByTierRange(int minTier, int maxTier) {
        return Arrays.stream(values())
                .filter(difficulty -> difficulty.getDifficultyTier() >= minTier && 
                                     difficulty.getDifficultyTier() <= maxTier)
                .toArray(QuestDifficulty[]::new);
    }
    
    /**
     * Find best risk/reward ratio for character
     */
    public static QuestDifficulty getBestRiskReward(int characterLevel) {
        return Arrays.stream(values())
                .filter(difficulty -> !difficulty.isTooHardFor(characterLevel))
                .max((a, b) -> Double.compare(
                    a.getRiskRewardRatio(characterLevel),
                    b.getRiskRewardRatio(characterLevel)))
                .orElse(TRIVIAL);
    }
    
    /**
     * Get highest difficulty accessible to character
     */
    public static QuestDifficulty getHighestAccessible(int characterLevel) {
        return Arrays.stream(values())
                .filter(difficulty -> difficulty.getSuccessChance(characterLevel) >= 0.1) // At least 10% chance
                .max((a, b) -> Integer.compare(a.getDifficultyTier(), b.getDifficultyTier()))
                .orElse(TRIVIAL);
    }
    
    /**
     * Calculate total possible rewards at character level
     */
    public static int getTotalPossibleExperience(int characterLevel) {
        return Arrays.stream(values())
                .filter(difficulty -> !difficulty.isTooHardFor(characterLevel))
                .mapToInt(difficulty -> difficulty.getExperienceReward(characterLevel))
                .sum();
    }
    
    public static int getTotalPossibleGold(int characterLevel) {
        return Arrays.stream(values())
                .filter(difficulty -> !difficulty.isTooHardFor(characterLevel))
                .mapToInt(difficulty -> difficulty.getGoldReward(characterLevel))
                .sum();
    }
    
    /**
     * Get difficulty progression recommendation
     */
    public static QuestDifficulty[] getProgressionPath(int characterLevel) {
        // Start with safe difficulties and work up
        return Arrays.stream(values())
                .filter(difficulty -> difficulty.getSuccessChance(characterLevel) >= 0.3)
                .sorted((a, b) -> Integer.compare(a.getDifficultyTier(), b.getDifficultyTier()))
                .toArray(QuestDifficulty[]::new);
    }
    
    /**
     * Generate difficulty comparison table
     */
    public static String getDifficultyComparison(int characterLevel) {
        StringBuilder comparison = new StringBuilder();
        comparison.append("QUEST DIFFICULTY ANALYSIS FOR LEVEL ").append(characterLevel).append("\n");
        comparison.append("═══════════════════════════════════════════════════════════════════════════════\n");
        comparison.append(String.format("%-10s %-6s %-8s %-6s %-6s %-6s %-6s %-8s%n", 
            "DIFFICULTY", "TIER", "SUCCESS%", "EXP", "GOLD", "RISK", "TIME", "PARTY?"));
        comparison.append("───────────────────────────────────────────────────────────────────────────────\n");
        
        for (QuestDifficulty difficulty : values()) {
            double successChance = difficulty.getSuccessChance(characterLevel);
            int exp = difficulty.getExperienceReward(characterLevel);
            int gold = difficulty.getGoldReward(characterLevel);
            double riskReward = difficulty.getRiskRewardRatio(characterLevel);
            double time = difficulty.getEstimatedCompletionTime();
            boolean party = difficulty.requiresParty();
            
            String status = difficulty.isTooHardFor(characterLevel) ? " (TOO HARD)" :
                           difficulty.isTooEasyFor(characterLevel) ? " (TOO EASY)" :
                           difficulty.isAppropriateFor(characterLevel) ? " (PERFECT)" : "";
            
            comparison.append(String.format("%-10s %-6d %-8.0f %-6d %-6d %-6.1f %-6.1fh %-8s%s%n",
                difficulty.name(),
                difficulty.getDifficultyTier(),
                successChance * 100,
                exp,
                gold,
                riskReward,
                time,
                party ? "Required" : "Optional",
                status));
        }
        
        comparison.append("═══════════════════════════════════════════════════════════════════════════════\n");
        comparison.append("RISK = Risk/Reward Ratio (higher is better)\n");
        comparison.append("Recommended: ").append(getAppropriate(characterLevel).name()).append("\n");
        comparison.append("Best Risk/Reward: ").append(getBestRiskReward(characterLevel).name()).append("\n");
        comparison.append("Highest Accessible: ").append(getHighestAccessible(characterLevel).name()).append("\n");
        
        return comparison.toString();
    }
    
    /**
     * Generate quest progression guide
     */
    public static String getProgressionGuide() {
        StringBuilder guide = new StringBuilder();
        guide.append("QUEST DIFFICULTY PROGRESSION GUIDE\n");
        guide.append("═══════════════════════════════════════════════════════════════════════════════\n");
        
        for (QuestDifficulty difficulty : values()) {
            guide.append(String.format("\n%s (Tier %d) - Recommended Level %d\n",
                difficulty.name(), difficulty.getDifficultyTier(), difficulty.getRecommendedLevel()));
            guide.append("─".repeat(difficulty.name().length() + 25)).append("\n");
            guide.append("Description: ").append(difficulty.getDescription()).append("\n");
            guide.append("Quest Types: ").append(difficulty.getQuestTypeDescription()).append("\n");
            guide.append("Estimated Time: ").append(difficulty.getEstimatedCompletionTime()).append(" hours\n");
            guide.append("Party Size: ").append(difficulty.getRecommendedPartySize()).append(" players\n");
            guide.append("Loot Quality: ").append(String.format("%.0f%% rare+", difficulty.getLootRarityModifier() * 100)).append("\n");
            
            // Show level-based success rates
            guide.append("Success Rates by Level:\n");
            for (int level = 1; level <= 30; level += 5) {
                double success = difficulty.getSuccessChance(level);
                guide.append(String.format("  Level %2d: %3.0f%%", level, success * 100));
                if (success < 0.1) guide.append(" (Nearly Impossible)");
                else if (success < 0.3) guide.append(" (Very Hard)");
                else if (success < 0.6) guide.append(" (Challenging)");
                else if (success < 0.8) guide.append(" (Manageable)");
                else guide.append(" (Easy)");
                guide.append("\n");
            }
        }
        
        guide.append("\n═══════════════════════════════════════════════════════════════════════════════\n");
        guide.append("Pro Tip: Always attempt the highest difficulty with at least 30% success chance\n");
        guide.append("for optimal experience and loot rewards!\n");
        
        return guide.toString();
    }
}

/*
 * ════════════════════════════════════════════════════════════════════════════
 * QUEST DIFFICULTY DESIGN PATTERNS DEMONSTRATED:
 * ════════════════════════════════════════════════════════════════════════════
 * 
 * 1. DYNAMIC SCALING SYSTEMS:
 *    - Success probability curves based on character level
 *    - Reward calculations that maintain relevance
 *    - Intelligent party requirements based on difficulty
 * 
 * 2. RISK/REWARD MATHEMATICS:
 *    - Expected value calculations for quest attempts
 *    - Risk assessment based on failure penalties
 *    - Prestige point systems for achievement tracking
 * 
 * 3. PROGRESSION MECHANICS:
 *    - Difficulty tier systems for organized advancement
 *    - Appropriateness checking for level-based content
 *    - Accessibility thresholds for challenging content
 * 
 * 4. GAMEPLAY BALANCE:
 *    - Time investment vs reward calculations
 *    - Loot rarity modifiers tied to difficulty
 *    - Party size recommendations for social gameplay
 * 
 * 5. PLAYER GUIDANCE SYSTEMS:
 *    - Automatic difficulty recommendations
 *    - Progression path calculations
 *    - Comprehensive comparison tools
 * 
 * 6. STATISTICAL ANALYSIS:
 *    - Total reward calculations across all difficulties
 *    - Best risk/reward ratio identification
 *    - Success rate visualization across level ranges
 * 
 * This enum showcases how quest difficulties can be intelligent
 * systems that adapt to player progression, provide balanced
 * challenges, and guide players toward optimal gameplay experiences!
 */
