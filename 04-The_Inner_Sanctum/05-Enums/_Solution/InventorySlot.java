/**
 * InventorySlot.java - Organized Storage System
 * 
 * Represents inventory slots with type-safe storage rules,
 * implementing the Storable interface for consistent behavior.
 * 
 * "A well-organized inventory is the mark of a wise adventurer.
 * Know your equipment, and your equipment will serve you well."
 */

import java.util.*;
import java.util.stream.Collectors;

/**
 * Storable interface defining storage contract
 */
interface Storable {
    int getCapacity();
    boolean canStore(String item);
    String getStorageRules();
}

/**
 * Inventory slot types implementing intelligent storage management
 */
public enum InventorySlot implements Storable {
    WEAPON("Primary combat equipment", 2, true, true, "One main, one backup") {
        @Override
        public int getCapacity() {
            return 2; // Main weapon + backup
        }
        
        @Override
        public boolean canStore(String item) {
            String itemLower = item.toLowerCase();
            return itemLower.contains("sword") || itemLower.contains("bow") ||
                   itemLower.contains("staff") || itemLower.contains("dagger") ||
                   itemLower.contains("hammer") || itemLower.contains("wand") ||
                   itemLower.contains("weapon");
        }
        
        @Override
        public String getStorageRules() {
            return "Weapons only - One active weapon + one backup weapon allowed";
        }
        
        @Override
        public List<String> getItemCategories() {
            return Arrays.asList("Swords", "Bows", "Staves", "Daggers", "Hammers", "Wands");
        }
        
        @Override
        public double getWeightMultiplier() {
            return 1.2; // Weapons are heavier when not equipped
        }
    },
    
    ARMOR("Protective equipment and clothing", 5, true, true, "Head, chest, legs, feet, accessories") {
        @Override
        public int getCapacity() {
            return 5; // Multiple armor pieces
        }
        
        @Override
        public boolean canStore(String item) {
            String itemLower = item.toLowerCase();
            return itemLower.contains("armor") || itemLower.contains("helmet") ||
                   itemLower.contains("boots") || itemLower.contains("gloves") ||
                   itemLower.contains("cloak") || itemLower.contains("shield") ||
                   itemLower.contains("ring") || itemLower.contains("amulet");
        }
        
        @Override
        public String getStorageRules() {
            return "Armor and accessories - Equipment that provides protection or stat bonuses";
        }
        
        @Override
        public List<String> getItemCategories() {
            return Arrays.asList("Head Armor", "Body Armor", "Leg Armor", "Foot Armor", "Accessories");
        }
        
        @Override
        public double getWeightMultiplier() {
            return 1.0; // Normal weight for armor
        }
    },
    
    CONSUMABLE("Items used for immediate effects", 20, true, false, "Potions, food, scrolls") {
        @Override
        public int getCapacity() {
            return 20; // Many consumables can stack
        }
        
        @Override
        public boolean canStore(String item) {
            String itemLower = item.toLowerCase();
            return itemLower.contains("potion") || itemLower.contains("food") ||
                   itemLower.contains("drink") || itemLower.contains("scroll") ||
                   itemLower.contains("elixir") || itemLower.contains("pill") ||
                   itemLower.contains("bread") || itemLower.contains("meat");
        }
        
        @Override
        public String getStorageRules() {
            return "Consumable items - Potions, food, scrolls that are used up when consumed";
        }
        
        @Override
        public List<String> getItemCategories() {
            return Arrays.asList("Health Potions", "Mana Potions", "Food", "Buff Scrolls", "Utility Items");
        }
        
        @Override
        public double getWeightMultiplier() {
            return 0.5; // Consumables are lighter
        }
    },
    
    QUEST_ITEM("Important items for quests and progression", 10, false, false, "Story items, keys, documents") {
        @Override
        public int getCapacity() {
            return 10; // Limited quest item storage
        }
        
        @Override
        public boolean canStore(String item) {
            String itemLower = item.toLowerCase();
            return itemLower.contains("key") || itemLower.contains("letter") ||
                   itemLower.contains("document") || itemLower.contains("crystal") ||
                   itemLower.contains("artifact") || itemLower.contains("token") ||
                   itemLower.contains("quest") || itemLower.contains("important");
        }
        
        @Override
        public String getStorageRules() {
            return "Quest items - Important items required for quest progression, cannot be sold";
        }
        
        @Override
        public List<String> getItemCategories() {
            return Arrays.asList("Keys", "Letters", "Artifacts", "Tokens", "Plot Items");
        }
        
        @Override
        public double getWeightMultiplier() {
            return 0.8; // Quest items vary in weight
        }
    },
    
    TREASURE("Valuable items and crafting materials", 15, true, true, "Gems, coins, materials") {
        @Override
        public int getCapacity() {
            return 15; // Good storage for valuable items
        }
        
        @Override
        public boolean canStore(String item) {
            String itemLower = item.toLowerCase();
            return itemLower.contains("gem") || itemLower.contains("coin") ||
                   itemLower.contains("ore") || itemLower.contains("crystal") ||
                   itemLower.contains("material") || itemLower.contains("ingredient") ||
                   itemLower.contains("gold") || itemLower.contains("silver");
        }
        
        @Override
        public String getStorageRules() {
            return "Treasure and materials - Valuable items, gems, crafting materials, currency";
        }
        
        @Override
        public List<String> getItemCategories() {
            return Arrays.asList("Precious Gems", "Coins", "Ores", "Crafting Materials", "Rare Components");
        }
        
        @Override
        public double getWeightMultiplier() {
            return 1.5; // Treasure is heavy (gold, gems, etc.)
        }
    };
    
    // Instance fields
    private final String description;
    private final int maxStackSize;
    private final boolean sellable;
    private final boolean droppable;
    private final String storageDescription;
    
    // Constructor
    InventorySlot(String description, int maxStackSize, boolean sellable, 
                 boolean droppable, String storageDescription) {
        this.description = description;
        this.maxStackSize = maxStackSize;
        this.sellable = sellable;
        this.droppable = droppable;
        this.storageDescription = storageDescription;
    }
    
    // Abstract methods that each slot must implement (in addition to Storable interface)
    public abstract List<String> getItemCategories();
    public abstract double getWeightMultiplier();
    
    // Concrete methods available to all slots
    public String getDescription() { return description; }
    public int getMaxStackSize() { return maxStackSize; }
    public boolean isSellable() { return sellable; }
    public boolean isDroppable() { return droppable; }
    public String getStorageDescription() { return storageDescription; }
    
    /**
     * Check if slot accepts a specific item category
     */
    public boolean acceptsCategory(String category) {
        return getItemCategories().stream()
                .anyMatch(cat -> cat.toLowerCase().contains(category.toLowerCase()));
    }
    
    /**
     * Get slot priority (1 = highest priority for auto-sorting)
     */
    public int getSortPriority() {
        return switch (this) {
            case WEAPON -> 1;     // Most important
            case ARMOR -> 2;      // Very important
            case QUEST_ITEM -> 3; // Important for progression
            case CONSUMABLE -> 4; // Useful
            case TREASURE -> 5;   // Can wait
        };
    }
    
    /**
     * Calculate storage efficiency (items per weight unit)
     */
    public double getStorageEfficiency() {
        return getCapacity() / getWeightMultiplier();
    }
    
    /**
     * Check if items in this slot can be auto-sorted
     */
    public boolean allowsAutoSort() {
        return this != QUEST_ITEM; // Quest items shouldn't be auto-sorted
    }
    
    /**
     * Get visual icon representation
     */
    public String getIcon() {
        return switch (this) {
            case WEAPON -> "⚔️";
            case ARMOR -> "🛡️";
            case CONSUMABLE -> "🧪";
            case QUEST_ITEM -> "📜";
            case TREASURE -> "💎";
        };
    }
    
    /**
     * Get slot color for UI
     */
    public String getSlotColor() {
        return switch (this) {
            case WEAPON -> "#FF6B6B";    // Red
            case ARMOR -> "#4ECDC4";     // Teal
            case CONSUMABLE -> "#45B7D1"; // Blue
            case QUEST_ITEM -> "#96CEB4"; // Green
            case TREASURE -> "#FFEAA7";  // Gold
        };
    }
    
    /**
     * Check if slot has special restrictions
     */
    public boolean hasRestrictions() {
        return !sellable || !droppable;
    }
    
    /**
     * Get restriction details
     */
    public String getRestrictions() {
        List<String> restrictions = new ArrayList<>();
        if (!sellable) restrictions.add("Cannot be sold");
        if (!droppable) restrictions.add("Cannot be dropped");
        
        return restrictions.isEmpty() ? "No restrictions" : String.join(", ", restrictions);
    }
    
    // ============= STATIC UTILITY METHODS =============
    
    /**
     * Organize items into appropriate slots
     */
    public static Map<InventorySlot, List<String>> organize(List<String> items) {
        Map<InventorySlot, List<String>> organized = new EnumMap<>(InventorySlot.class);
        
        // Initialize all slots with empty lists
        for (InventorySlot slot : values()) {
            organized.put(slot, new ArrayList<>());
        }
        
        // Sort items into appropriate slots
        for (String item : items) {
            InventorySlot bestSlot = findBestSlot(item);
            if (bestSlot != null) {
                List<String> slotItems = organized.get(bestSlot);
                if (slotItems.size() < bestSlot.getCapacity()) {
                    slotItems.add(item);
                } else {
                    // Try to find alternative slot or indicate overflow
                    System.out.println("Warning: " + bestSlot.name() + " slot is full! Item '" + item + "' needs storage.");
                }
            } else {
                System.out.println("Warning: No appropriate slot found for item: " + item);
            }
        }
        
        return organized;
    }
    
    /**
     * Find the best slot for a specific item
     */
    public static InventorySlot findBestSlot(String item) {
        return Arrays.stream(values())
                .filter(slot -> slot.canStore(item))
                .min(Comparator.comparingInt(InventorySlot::getSortPriority))
                .orElse(null);
    }
    
    /**
     * Get slots that can store a specific item
     */
    public static InventorySlot[] getCompatibleSlots(String item) {
        return Arrays.stream(values())
                .filter(slot -> slot.canStore(item))
                .toArray(InventorySlot[]::new);
    }
    
    /**
     * Get slots by sellability
     */
    public static InventorySlot[] getSellableSlots() {
        return Arrays.stream(values())
                .filter(InventorySlot::isSellable)
                .toArray(InventorySlot[]::new);
    }
    
    public static InventorySlot[] getUnsellableSlots() {
        return Arrays.stream(values())
                .filter(slot -> !slot.isSellable())
                .toArray(InventorySlot[]::new);
    }
    
    /**
     * Get slots by drop permission
     */
    public static InventorySlot[] getDroppableSlots() {
        return Arrays.stream(values())
                .filter(InventorySlot::isDroppable)
                .toArray(InventorySlot[]::new);
    }
    
    /**
     * Get slots sorted by capacity
     */
    public static InventorySlot[] getSlotsByCapacity() {
        return Arrays.stream(values())
                .sorted(Comparator.comparingInt(InventorySlot::getCapacity).reversed())
                .toArray(InventorySlot[]::new);
    }
    
    /**
     * Get slots sorted by storage efficiency
     */
    public static InventorySlot[] getSlotsByEfficiency() {
        return Arrays.stream(values())
                .sorted(Comparator.comparingDouble(InventorySlot::getStorageEfficiency).reversed())
                .toArray(InventorySlot[]::new);
    }
    
    /**
     * Calculate total inventory capacity
     */
    public static int getTotalCapacity() {
        return Arrays.stream(values())
                .mapToInt(InventorySlot::getCapacity)
                .sum();
    }
    
    /**
     * Get inventory utilization report
     */
    public static String getUtilizationReport(Map<InventorySlot, List<String>> inventory) {
        StringBuilder report = new StringBuilder();
        report.append("INVENTORY UTILIZATION REPORT\n");
        report.append("═══════════════════════════════════════════════════════════════\n");
        
        int totalUsed = 0;
        int totalCapacity = 0;
        
        for (InventorySlot slot : values()) {
            List<String> items = inventory.getOrDefault(slot, new ArrayList<>());
            int used = items.size();
            int capacity = slot.getCapacity();
            double utilization = (double) used / capacity * 100;
            
            totalUsed += used;
            totalCapacity += capacity;
            
            report.append(String.format("%-12s %s %-3d/%-3d (%5.1f%%) %s%n",
                slot.name(),
                slot.getIcon(),
                used,
                capacity,
                utilization,
                utilization > 90 ? "⚠️ NEARLY FULL" :
                utilization > 75 ? "📊 BUSY" :
                utilization > 50 ? "📈 ACTIVE" :
                utilization > 25 ? "📉 LIGHT" : "📭 EMPTY"));
        }
        
        report.append("───────────────────────────────────────────────────────────────\n");
        report.append(String.format("TOTAL: %d/%d slots used (%.1f%% utilization)%n",
            totalUsed, totalCapacity, (double) totalUsed / totalCapacity * 100));
        
        report.append("═══════════════════════════════════════════════════════════════\n");
        
        return report.toString();
    }
    
    /**
     * Generate inventory management guide
     */
    public static String getManagementGuide() {
        StringBuilder guide = new StringBuilder();
        guide.append("INVENTORY MANAGEMENT GUIDE\n");
        guide.append("═══════════════════════════════════════════════════════════════════════════════\n");
        
        for (InventorySlot slot : values()) {
            guide.append(String.format("\n%s %s (Priority: %d)\n",
                slot.getIcon(), slot.name(), slot.getSortPriority()));
            guide.append("─".repeat(slot.name().length() + 15)).append("\n");
            guide.append("Description: ").append(slot.getDescription()).append("\n");
            guide.append("Capacity: ").append(slot.getCapacity()).append(" items\n");
            guide.append("Storage Rules: ").append(slot.getStorageRules()).append("\n");
            guide.append("Weight Multiplier: ").append(String.format("%.1fx", slot.getWeightMultiplier())).append("\n");
            guide.append("Restrictions: ").append(slot.getRestrictions()).append("\n");
            guide.append("Categories: ").append(String.join(", ", slot.getItemCategories())).append("\n");
            guide.append("Auto-Sort: ").append(slot.allowsAutoSort() ? "Yes" : "No").append("\n");
            guide.append("Storage Efficiency: ").append(String.format("%.1f items/weight", slot.getStorageEfficiency())).append("\n");
        }
        
        guide.append("\n═══════════════════════════════════════════════════════════════════════════════\n");
        guide.append("💡 TIPS:\n");
        guide.append("• Keep weapons and armor slots for combat-ready equipment\n");
        guide.append("• Stock consumables for long adventures\n");
        guide.append("• Never sell quest items - they're usually needed later!\n");
        guide.append("• Treasure items are heavy but valuable - manage weight carefully\n");
        guide.append("• Use auto-sort for everything except quest items\n");
        
        return guide.toString();
    }
    
    /**
     * Create example inventory for testing
     */
    public static Map<InventorySlot, List<String>> createSampleInventory() {
        List<String> sampleItems = Arrays.asList(
            "Iron Sword", "Health Potion", "Magic Crystal", "Gold Coins",
            "Leather Armor", "Mana Potion", "Quest Letter", "Silver Ring",
            "Fire Staff", "Healing Scroll", "Ancient Key", "Ruby Gem",
            "Steel Helmet", "Invisibility Potion", "Royal Document", "Dragon Scale",
            "Elven Bow", "Bread", "Mysterious Orb", "Diamond"
        );
        
        return organize(sampleItems);
    }
}

/*
 * ════════════════════════════════════════════════════════════════════════════
 * INVENTORY SLOT DESIGN PATTERNS DEMONSTRATED:
 * ════════════════════════════════════════════════════════════════════════════
 * 
 * 1. INTERFACE IMPLEMENTATION:
 *    - Clean contract definition with Storable interface
 *    - Consistent storage behavior across all slot types
 *    - Type-safe storage validation methods
 * 
 * 2. BUSINESS RULE ENCAPSULATION:
 *    - Slot-specific storage rules and item validation
 *    - Capacity limitations and weight considerations
 *    - Sellability and drop restrictions per slot type
 * 
 * 3. INTELLIGENT ORGANIZATION:
 *    - Automatic item categorization and slot assignment
 *    - Priority-based sorting for optimal arrangement
 *    - Overflow handling and alternative slot suggestions
 * 
 * 4. UTILITY INTEGRATION:
 *    - Visual representation with icons and colors
 *    - Storage efficiency calculations for optimization
 *    - Auto-sort permissions and management features
 * 
 * 5. COMPREHENSIVE ANALYSIS:
 *    - Inventory utilization reporting and monitoring
 *    - Management guides and optimization tips
 *    - Sample inventory generation for testing
 * 
 * 6. FLEXIBLE QUERYING:
 *    - Multi-criteria slot filtering and searching
 *    - Compatibility checking for item placement
 *    - Statistical analysis of storage patterns
 * 
 * This enum demonstrates how inventory slots can be sophisticated
 * storage management systems that understand item types, enforce
 * business rules, and provide intelligent organization features!
 */
