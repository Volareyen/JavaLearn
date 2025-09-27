/**
 * Element.java - The Primal Forces of Magic
 * 
 * Represents the elemental forces that govern magical combat,
 * each with unique properties, strengths, and weaknesses.
 * 
 * "The elements are the building blocks of all magic. Master their
 * interactions, and you master the very fabric of magical reality."
 */

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;

/**
 * Elemental forces with unique damage calculations and interactions
 * Each element implements abstract methods for specialized behavior
 */
public enum Element {
    FIRE("#FF4500", "The consuming flame that purifies and destroys") {
        @Override
        public int calculateDamage(int baseDamage, Element targetWeakness) {
            int finalDamage = baseDamage;
            
            // Fire is super effective against ice/water, weak against water
            if (targetWeakness == WATER) {
                finalDamage = (int)(baseDamage * 0.5); // 50% damage vs water weakness
            } else if (targetWeakness == EARTH) {
                finalDamage = (int)(baseDamage * 1.5); // 150% damage vs earth weakness
            } else if (targetWeakness == AIR) {
                finalDamage = (int)(baseDamage * 1.2); // 120% damage (fire spreads with air)
            }
            
            return Math.max(1, finalDamage); // Minimum 1 damage
        }
        
        @Override
        public String getSpellEffect() {
            return "Burning: Target takes fire damage over time for 3 turns";
        }
        
        @Override
        public Element getOppositeElement() {
            return WATER;
        }
        
        @Override
        public String[] getStatusEffects() {
            return new String[]{"Burning", "Ignited", "Seared"};
        }
        
        @Override
        public double getSpellPowerModifier() {
            return 1.2; // Fire spells are 20% more powerful by nature
        }
    },
    
    WATER("#0066FF", "The flowing force that heals and cleanses") {
        @Override
        public int calculateDamage(int baseDamage, Element targetWeakness) {
            int finalDamage = baseDamage;
            
            // Water extinguishes fire, conducts through earth, evaporates vs air
            if (targetWeakness == FIRE) {
                finalDamage = (int)(baseDamage * 1.8); // 180% damage vs fire
            } else if (targetWeakness == EARTH) {
                finalDamage = (int)(baseDamage * 1.3); // 130% damage (erosion)
            } else if (targetWeakness == AIR) {
                finalDamage = (int)(baseDamage * 0.8); // 80% damage (evaporation)
            }
            
            return Math.max(1, finalDamage);
        }
        
        @Override
        public String getSpellEffect() {
            return "Healing Rain: Restores health to caster and allies over time";
        }
        
        @Override
        public Element getOppositeElement() {
            return FIRE;
        }
        
        @Override
        public String[] getStatusEffects() {
            return new String[]{"Soaked", "Frozen", "Healed"};
        }
        
        @Override
        public double getSpellPowerModifier() {
            return 0.9; // Water spells trade power for utility
        }
    },
    
    EARTH("#8B4513", "The steadfast foundation that endures and protects") {
        @Override
        public int calculateDamage(int baseDamage, Element targetWeakness) {
            int finalDamage = baseDamage;
            
            // Earth resists fire, weak to water, strong vs air
            if (targetWeakness == FIRE) {
                finalDamage = (int)(baseDamage * 0.7); // 70% damage (earth resists fire)
            } else if (targetWeakness == WATER) {
                finalDamage = (int)(baseDamage * 0.6); // 60% damage (mud, erosion)
            } else if (targetWeakness == AIR) {
                finalDamage = (int)(baseDamage * 1.4); // 140% damage (rocks vs wind)
            }
            
            return Math.max(1, finalDamage);
        }
        
        @Override
        public String getSpellEffect() {
            return "Stone Skin: Increases defense and reduces incoming damage";
        }
        
        @Override
        public Element getOppositeElement() {
            return AIR;
        }
        
        @Override
        public String[] getStatusEffects() {
            return new String[]{"Petrified", "Armored", "Rooted"};
        }
        
        @Override
        public double getSpellPowerModifier() {
            return 1.0; // Earth spells are balanced
        }
    },
    
    AIR("#87CEEB", "The swift wind that moves freely and strikes without warning") {
        @Override
        public int calculateDamage(int baseDamage, Element targetWeakness) {
            int finalDamage = baseDamage;
            
            // Air feeds fire, disperses water, erodes earth
            if (targetWeakness == FIRE) {
                finalDamage = (int)(baseDamage * 0.8); // 80% damage (air feeds fire)
            } else if (targetWeakness == WATER) {
                finalDamage = (int)(baseDamage * 1.3); // 130% damage (evaporation)
            } else if (targetWeakness == EARTH) {
                finalDamage = (int)(baseDamage * 0.6); // 60% damage (wind vs rock)
            }
            
            return Math.max(1, finalDamage);
        }
        
        @Override
        public String getSpellEffect() {
            return "Lightning Strike: Instant high damage with chance to stun";
        }
        
        @Override
        public Element getOppositeElement() {
            return EARTH;
        }
        
        @Override
        public String[] getStatusEffects() {
            return new String[]{"Shocked", "Stunned", "Floating"};
        }
        
        @Override
        public double getSpellPowerModifier() {
            return 1.1; // Air spells are quick and potent
        }
    },
    
    LIGHT("#FFD700", "The divine radiance that banishes darkness and evil") {
        @Override
        public int calculateDamage(int baseDamage, Element targetWeakness) {
            int finalDamage = baseDamage;
            
            // Light is devastating against dark, neutral against others
            if (targetWeakness == DARK) {
                finalDamage = (int)(baseDamage * 2.5); // 250% damage vs darkness
            }
            // Light has no other weaknesses - pure energy
            
            return Math.max(1, finalDamage);
        }
        
        @Override
        public String getSpellEffect() {
            return "Divine Blessing: Heals allies and damages undead creatures";
        }
        
        @Override
        public Element getOppositeElement() {
            return DARK;
        }
        
        @Override
        public String[] getStatusEffects() {
            return new String[]{"Blessed", "Purified", "Illuminated"};
        }
        
        @Override
        public double getSpellPowerModifier() {
            return 1.3; // Light spells are potent but rare
        }
    },
    
    DARK("#800080", "The consuming void that corrupts and devours all") {
        @Override
        public int calculateDamage(int baseDamage, Element targetWeakness) {
            int finalDamage = baseDamage;
            
            // Dark is weak against light but strong against life
            if (targetWeakness == LIGHT) {
                finalDamage = (int)(baseDamage * 0.3); // 30% damage vs light
            } else {
                // Dark magic is inherently corrupting
                finalDamage = (int)(baseDamage * 1.15); // 115% base damage vs others
            }
            
            return Math.max(1, finalDamage);
        }
        
        @Override
        public String getSpellEffect() {
            return "Life Drain: Damages enemy and heals caster for half the damage";
        }
        
        @Override
        public Element getOppositeElement() {
            return LIGHT;
        }
        
        @Override
        public String[] getStatusEffects() {
            return new String[]{"Cursed", "Drained", "Corrupted"};
        }
        
        @Override
        public double getSpellPowerModifier() {
            return 1.25; // Dark magic is powerful but dangerous
        }
    };
    
    // Instance fields
    private final String colorHex;
    private final String description;
    
    // Constructor
    Element(String colorHex, String description) {
        this.colorHex = colorHex;
        this.description = description;
    }
    
    // Abstract methods that each element must implement
    public abstract int calculateDamage(int baseDamage, Element targetWeakness);
    public abstract String getSpellEffect();
    public abstract Element getOppositeElement();
    public abstract String[] getStatusEffects();
    public abstract double getSpellPowerModifier();
    
    // Concrete methods available to all elements
    public String getColorHex() { return colorHex; }
    public String getDescription() { return description; }
    
    /**
     * Check if this element is effective against another
     */
    public boolean isEffectiveAgainst(Element other) {
        // Test with medium damage to see effectiveness
        int testDamage = 100;
        int actualDamage = this.calculateDamage(testDamage, other);
        return actualDamage > testDamage; // More than base = effective
    }
    
    /**
     * Check if this element is weak against another
     */
    public boolean isWeakAgainst(Element other) {
        int testDamage = 100;
        int actualDamage = this.calculateDamage(testDamage, other);
        return actualDamage < testDamage; // Less than base = weak
    }
    
    /**
     * Get elemental affinity (how much this element "likes" another)
     */
    public double getAffinity(Element other) {
        if (other == this) {
            return 2.0; // Perfect affinity with self
        } else if (other == this.getOppositeElement()) {
            return 0.2; // Very low affinity with opposite
        } else if (this.isEffectiveAgainst(other)) {
            return 1.5; // Good affinity when effective
        } else if (this.isWeakAgainst(other)) {
            return 0.7; // Poor affinity when weak
        } else {
            return 1.0; // Neutral affinity
        }
    }
    
    /**
     * Get the elemental tier (power level)
     */
    public int getTier() {
        return switch (this) {
            case FIRE, WATER, EARTH, AIR -> 1; // Primary elements
            case LIGHT, DARK -> 2;              // Advanced elements
        };
    }
    
    /**
     * Check if element is considered a primary element
     */
    public boolean isPrimary() {
        return getTier() == 1;
    }
    
    /**
     * Check if element is considered divine/unholy
     */
    public boolean isDivine() {
        return this == LIGHT;
    }
    
    public boolean isUnholy() {
        return this == DARK;
    }
    
    /**
     * Get element's natural habitat/environment
     */
    public String getNaturalEnvironment() {
        return switch (this) {
            case FIRE -> "Volcanic regions, deserts, forges";
            case WATER -> "Oceans, rivers, rainy areas";
            case EARTH -> "Mountains, caves, forests";
            case AIR -> "Sky, windy plains, high altitudes";
            case LIGHT -> "Temples, holy places, dawn";
            case DARK -> "Dungeons, night, shadowy realms";
        };
    }
    
    /**
     * Calculate resistance percentage against another element
     */
    public double getResistance(Element attackingElement) {
        if (attackingElement == this.getOppositeElement()) {
            return 0.1; // 10% resistance vs opposite (mostly vulnerable)
        } else if (attackingElement == this) {
            return 0.5; // 50% resistance vs same element
        } else if (this.isEffectiveAgainst(attackingElement)) {
            return 0.3; // 30% resistance when we're effective vs them
        } else {
            return 0.2; // 20% base resistance
        }
    }
    
    // ============= STATIC UTILITY METHODS =============
    
    /**
     * Get primary elements (classical four)
     */
    public static Element[] getPrimaryElements() {
        return Arrays.stream(values())
                .filter(Element::isPrimary)
                .toArray(Element[]::new);
    }
    
    /**
     * Get advanced elements (divine/unholy)
     */
    public static Element[] getAdvancedElements() {
        return Arrays.stream(values())
                .filter(element -> !element.isPrimary())
                .toArray(Element[]::new);
    }
    
    /**
     * Find element by color
     */
    public static Element getByColor(String colorHex) {
        return Arrays.stream(values())
                .filter(element -> element.getColorHex().equalsIgnoreCase(colorHex))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Get elemental opposites map
     */
    public static EnumMap<Element, Element> getOppositeMap() {
        EnumMap<Element, Element> opposites = new EnumMap<>(Element.class);
        for (Element element : values()) {
            opposites.put(element, element.getOppositeElement());
        }
        return opposites;
    }
    
    /**
     * Get elements effective against a target
     */
    public static Element[] getEffectiveAgainst(Element target) {
        return Arrays.stream(values())
                .filter(element -> element.isEffectiveAgainst(target))
                .toArray(Element[]::new);
    }
    
    /**
     * Get elements weak against a target
     */
    public static Element[] getWeakAgainst(Element target) {
        return Arrays.stream(values())
                .filter(element -> element.isWeakAgainst(target))
                .toArray(Element[]::new);
    }
    
    /**
     * Get strongest element by spell power modifier
     */
    public static Element getStrongestElement() {
        return Arrays.stream(values())
                .max((a, b) -> Double.compare(a.getSpellPowerModifier(), b.getSpellPowerModifier()))
                .orElse(FIRE);
    }
    
    /**
     * Calculate elemental combination result
     */
    public static Element combineElements(Element first, Element second) {
        if (first == second) {
            return first; // Same element = pure element
        }
        
        // Special combinations
        if ((first == FIRE && second == EARTH) || (first == EARTH && second == FIRE)) {
            return FIRE; // Molten rock = fire
        }
        if ((first == WATER && second == AIR) || (first == AIR && second == WATER)) {
            return AIR; // Storm = air dominates
        }
        if ((first == LIGHT && second == FIRE) || (first == FIRE && second == LIGHT)) {
            return LIGHT; // Holy fire = light
        }
        if ((first == DARK && second == WATER) || (first == WATER && second == DARK)) {
            return DARK; // Corrupt water = dark
        }
        
        // Default to the stronger element
        return first.getSpellPowerModifier() >= second.getSpellPowerModifier() ? first : second;
    }
    
    /**
     * Create elemental damage chart
     */
    public static String getDamageChart() {
        StringBuilder chart = new StringBuilder();
        chart.append("ELEMENTAL DAMAGE EFFECTIVENESS CHART\n");
        chart.append("═══════════════════════════════════════════════════════════════\n");
        chart.append("Attacker vs Defender | ");
        
        // Header row
        for (Element defender : values()) {
            chart.append(String.format("%-6s", defender.name().substring(0, Math.min(6, defender.name().length()))));
        }
        chart.append("\n");
        chart.append("───────────────────────────────────────────────────────────────\n");
        
        // Data rows
        for (Element attacker : values()) {
            chart.append(String.format("%-18s | ", attacker.name()));
            for (Element defender : values()) {
                int damage = attacker.calculateDamage(100, defender);
                String effectiveness = damage >= 150 ? "SUPER" : 
                                     damage >= 120 ? "GOOD " :
                                     damage >= 80 ? "NORM " :
                                     damage >= 50 ? "WEAK " : "POOR ";
                chart.append(String.format("%-6s", effectiveness));
            }
            chart.append("\n");
        }
        
        chart.append("═══════════════════════════════════════════════════════════════\n");
        chart.append("SUPER=150%+, GOOD=120%+, NORM=80-119%, WEAK=50-79%, POOR=<50%\n");
        
        return chart.toString();
    }
    
    /**
     * Get comprehensive element guide
     */
    public static String getElementGuide() {
        StringBuilder guide = new StringBuilder();
        guide.append("COMPREHENSIVE ELEMENTAL GUIDE\n");
        guide.append("═══════════════════════════════════════════════════════════════════════════════\n");
        
        for (Element element : values()) {
            guide.append(String.format("\n%s (%s)\n", element.name(), element.getColorHex()));
            guide.append("─".repeat(element.name().length() + element.getColorHex().length() + 3)).append("\n");
            guide.append("Description: ").append(element.getDescription()).append("\n");
            guide.append("Spell Effect: ").append(element.getSpellEffect()).append("\n");
            guide.append("Opposite: ").append(element.getOppositeElement().name()).append("\n");
            guide.append("Power Modifier: ").append(String.format("%.0f%%", element.getSpellPowerModifier() * 100)).append("\n");
            guide.append("Status Effects: ").append(String.join(", ", element.getStatusEffects())).append("\n");
            guide.append("Environment: ").append(element.getNaturalEnvironment()).append("\n");
            
            // Show what it's effective/weak against
            Element[] effectiveAgainst = getEffectiveAgainst(element);
            Element[] weakAgainst = getWeakAgainst(element);
            
            if (effectiveAgainst.length > 0) {
                guide.append("Effective vs: ");
                guide.append(Arrays.stream(effectiveAgainst)
                        .map(Enum::name)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("None"));
                guide.append("\n");
            }
            
            if (weakAgainst.length > 0) {
                guide.append("Weak vs: ");
                guide.append(Arrays.stream(weakAgainst)
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
 * ELEMENT DESIGN PATTERNS DEMONSTRATED:
 * ════════════════════════════════════════════════════════════════════════════
 * 
 * 1. ABSTRACT METHOD MASTERY:
 *    - Each element implements unique damage calculations
 *    - Specialized spell effects per element
 *    - Individual status effects and power modifiers
 * 
 * 2. COMPLEX BUSINESS LOGIC:
 *    - Elemental effectiveness matrices
 *    - Resistance calculations based on relationships
 *    - Affinity systems for compatibility
 * 
 * 3. RELATIONSHIP MODELING:
 *    - Opposite element pairs (Fire↔Water, Earth↔Air, Light↔Dark)
 *    - Effectiveness triangles and power relationships
 *    - Combination logic for mixed elements
 * 
 * 4. COMPUTATIONAL INTELLIGENCE:
 *    - Dynamic damage calculations based on target
 *    - Power modifier systems for spell casting
 *    - Resistance percentages for defensive calculations
 * 
 * 5. RICH DATA VISUALIZATION:
 *    - Damage effectiveness charts
 *    - Comprehensive element guides
 *    - Color-coded representations
 * 
 * 6. STATIC ANALYSIS TOOLS:
 *    - Element filtering and categorization
 *    - Effectiveness analysis against targets
 *    - Combination and synergy calculators
 * 
 * This enum demonstrates how elements can be sophisticated entities
 * that understand their place in a complex magical system, with
 * each element being a unique specialist in the art of magic!
 */
