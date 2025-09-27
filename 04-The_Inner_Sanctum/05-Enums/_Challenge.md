# The Fifth Arcane Trial: Master the Gaming Realm Enum System

*"Young seeker, you have witnessed the power of enumerations in the coffee house scrolls. Now you must forge your own enum mastery by creating a sophisticated RPG (Role-Playing Game) character management system. This trial will test every aspect of enum knowledge - from basic constants to complex state machines!"*

---

## **🎮 THE SACRED CHALLENGE**

You must create a **Fantasy RPG Character Management System** that uses enums to model every aspect of character creation, progression, and gameplay. Your system must demonstrate mastery of all enum capabilities.

---

## **📋 REQUIREMENTS BREAKDOWN**

### **Part I: Character Foundation (25 points)**

Create these foundational enums with rich data and behavior:

#### **1. CharacterClass Enum**
- **Constants**: WARRIOR, MAGE, ROGUE, PALADIN, ARCHER
- **Fields**: baseHealth, baseMana, primaryStat, description
- **Methods**: 
  - `getStartingGold()` - different classes start with different amounts
  - `canUseWeapon(WeaponType weapon)` - class restrictions
  - `getLevelUpBonus(int level)` - scaling bonuses per level
- **Static Methods**: 
  - `getRecommendedForBeginner()` - return beginner-friendly classes
  - `getByPrimaryStat(String stat)` - find classes by primary stat

#### **2. WeaponType Enum**
- **Constants**: SWORD, BOW, STAFF, DAGGER, HAMMER, WAND
- **Fields**: damage, durability, requiredLevel, twoHanded
- **Methods**: 
  - `getAttackSpeed()` - calculated property based on type
  - `getCriticalChance()` - different weapons have different crit rates
  - `getDurabilityLoss()` - how much durability lost per use
- **Interface Implementation**: Implement `Comparable<WeaponType>` for damage comparison

#### **3. Element Enum (with Abstract Methods)**
- **Constants**: FIRE, WATER, EARTH, AIR, LIGHT, DARK
- **Fields**: color (hex code), description
- **Abstract Methods**: Each element must implement:
  - `calculateDamage(int baseDamage, Element targetWeakness)`
  - `getSpellEffect()` - unique effect description
  - `getOppositeElement()` - return the opposing element
- **Concrete Methods**: 
  - `isEffectiveAgainst(Element other)` - elemental advantages

---

### **Part II: Dynamic Systems (35 points)**

#### **4. CharacterStatus Enum (State Machine)**
Create a complex state machine for character conditions:
- **Constants**: HEALTHY, POISONED, BURNING, FROZEN, BLESSED, CURSED, DEAD
- **Fields**: isDebuff, duration, tickDamage, description
- **Abstract Methods**: 
  - `applyEffect(Character character)` - what happens each turn
  - `canTransitionTo(CharacterStatus newStatus)` - valid state transitions
  - `getRecoveryChance()` - chance to recover naturally
- **Static Methods**: 
  - `getActiveDebuffs(Character character)` - filter current debuffs
  - `isValidCombination(CharacterStatus... statuses)` - some statuses conflict

#### **5. QuestDifficulty Enum**
- **Constants**: TRIVIAL, EASY, MEDIUM, HARD, LEGENDARY, MYTHIC
- **Fields**: recommendedLevel, experienceMultiplier, goldReward, failurePenalty
- **Methods**: 
  - `getSuccessChance(int characterLevel)` - calculated success probability
  - `adjustRewards(int characterLevel)` - scale rewards based on level difference
  - `requiresParty()` - some difficulties require multiple players
- **Static Methods**: 
  - `getAppropriate(int characterLevel)` - recommend difficulty for level

---

### **Part III: Advanced Features (40 points)**

#### **6. InventorySlot Enum (Interface Implementation)**
- **Interface**: `Storable` with methods `getCapacity()`, `canStore(ItemType item)`
- **Constants**: WEAPON, ARMOR, CONSUMABLE, QUEST_ITEM, TREASURE
- **Fields**: maxStackSize, sellable, droppable
- **Methods**: Implement interface methods with slot-specific logic
- **Static Methods**: `organize(List<Item> items)` - sort items by slot type

#### **7. GameEvent Enum (Complex Business Logic)**
- **Constants**: LEVEL_UP, QUEST_COMPLETE, BOSS_DEFEATED, TREASURE_FOUND, PLAYER_DEATH
- **Fields**: priority, notificationMessage, soundEffect, experienceGained
- **Methods**: 
  - `triggerEvent(Character character)` - execute event-specific logic
  - `getRequirements()` - what conditions must be met
  - `getFollowupEvents()` - events that might trigger after this one
- **Enum with Nested Enums**: Create `Rarity` nested enum for treasure events

---

## **🏗️ INTEGRATION REQUIREMENTS**

### **Character Class Integration**
Create a `GameCharacter` class that uses ALL your enums:
```java
public class GameCharacter {
    private String name;
    private CharacterClass characterClass;
    private int level;
    private EnumSet<CharacterStatus> activeStatuses;
    private EnumMap<Element, Integer> resistances;
    private WeaponType equippedWeapon;
    // ... other fields using your enums
    
    // Methods that demonstrate enum integration:
    public boolean attemptQuest(QuestDifficulty difficulty) { /* use enum logic */ }
    public void takeDamage(int damage, Element element) { /* use elemental system */ }
    public void levelUp() { /* trigger GameEvent.LEVEL_UP */ }
    // ... more methods
}
```

### **Advanced Collections Usage**
Your solution must demonstrate:
- **EnumSet** for storing multiple active statuses
- **EnumMap** for element resistances and equipment slots
- **Enum Streams** for filtering and processing
- **Enum comparisons** for sorting and ranking

---

## **🎯 SPECIFIC FUNCTIONALITY TO IMPLEMENT**

### **Battle System Method**
```java
public BattleResult simulateBattle(GameCharacter attacker, GameCharacter defender) {
    // Must use: WeaponType damage, Element effectiveness, 
    // CharacterStatus effects, QuestDifficulty for encounter scaling
}
```

### **Character Creation Wizard**
```java
public static GameCharacter createCharacter(String name, CharacterClass charClass, 
                                          Element favoredElement) {
    // Must validate combinations and set appropriate starting values
}
```

### **Event Processing Engine**
```java
public void processGameEvents(GameCharacter character, GameEvent... events) {
    // Handle multiple events in priority order, check requirements,
    // trigger follow-up events
}
```

---

## **🏆 BONUS CHALLENGES (Extra Credit)**

### **Advanced Enum Patterns (10 bonus points each)**

1. **Command Pattern**: Create a `SpellCommand` enum that implements actions
2. **Factory Pattern**: Use enum to create different monster types
3. **Strategy Pattern**: Enum for different AI behaviors
4. **Observer Pattern**: Enum for different notification types

### **Performance Optimization (15 bonus points)**
- Implement enum-based lookup tables for O(1) combat calculations
- Use EnumSet bit operations for status effect combinations
- Create enum-powered caching system for repeated calculations

---

## **📝 SUBMISSION REQUIREMENTS**

Your solution must include:

1. **All Required Enums** (7 enums) with complete implementation
2. **GameCharacter Integration Class** showing real usage
3. **Test/Demo Class** demonstrating all functionality
4. **Documentation Comments** explaining your design decisions
5. **Error Handling** for invalid enum operations

### **Code Quality Standards**
- ✅ Comprehensive JavaDoc comments
- ✅ Meaningful variable and method names
- ✅ Proper exception handling
- ✅ Defensive programming practices
- ✅ Performance considerations

---

## **🧪 TESTING YOUR MASTERY**

Create test scenarios that verify:
- Character creation with different class/element combinations
- Status effect state transitions and conflicts
- Weapon compatibility with character classes
- Quest difficulty scaling and success calculations
- Event processing with complex requirements
- Battle simulation using all enum systems

---

## **💡 HINTS FROM THE ANCIENT WISDOM**

1. **Think Behavior, Not Just Data**: Each enum should be a mini-class with intelligent methods
2. **State Validation**: Use enum methods to enforce business rules
3. **Composition Over Complexity**: Simple enums working together beat one complex enum
4. **Performance Matters**: Use EnumSet/EnumMap for frequently accessed collections
5. **Extensibility**: Design enums so new constants can be added easily
6. **Type Safety**: Let the compiler prevent invalid combinations

---

## **🎓 EVALUATION CRITERIA**

| Aspect | Weight | What We're Looking For |
|--------|--------|------------------------|
| **Enum Design** | 30% | Rich enums with constructors, fields, methods |
| **State Management** | 25% | Proper state machines and transitions |
| **Interface Implementation** | 20% | Clean interface contracts and polymorphism |
| **Integration** | 15% | Seamless usage in business objects |
| **Code Quality** | 10% | Clean, documented, maintainable code |

---

*"Remember, young seeker - this is not about creating simple lists of constants. You are forging intelligent, type-safe entities that embody the very essence of your game world. Each enum should be a miniature universe of possibilities, constrained by wisdom and empowered by behavior."*

*"When you have completed this trial, you will understand why the ancients chose enums as one of the Sacred Arts. They bring order to chaos, meaning to numbers, and intelligence to constants!"*

**May your code compile cleanly and your enums be ever meaningful!** ⚔️🧙‍♂️✨
