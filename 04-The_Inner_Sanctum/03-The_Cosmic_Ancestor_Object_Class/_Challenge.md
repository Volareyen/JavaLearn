# The Pupil's Trial: E-Commerce Product Inventory System
*The Cosmic Ancestor Object Class Mastery Challenge*

---

## **The Sacred Task**

*Seeker, you have witnessed the profound power of the cosmic ancestor and learned the sacred contracts of `toString()`, `equals()`, and `hashCode()`. Now you must forge your own system that demonstrates complete mastery over these fundamental methods.*

*Your challenge is to create a comprehensive **E-Commerce Product Inventory System** that showcases proper implementation of Object methods for different types of objects - value objects, entity objects, and composite objects working together in collections.*

---

## **The Requirements**

### **🎯 System Architecture Overview**

Create an e-commerce inventory system with the following components:

#### **1. Value Objects** (Equality based on content)
**`ProductCode`** - Immutable product identifier
- Unique alphanumeric code (e.g., "ELEC-12345", "BOOK-98765")
- Case-insensitive equality
- Formatted toString() for display

**`Money`** - Immutable monetary value
- Amount and currency
- Currency-aware equality (can't compare different currencies)
- Formatted toString() with currency symbol

**`Dimensions`** - Immutable product dimensions
- Length, width, height, weight
- Equality based on all measurements
- Human-readable toString() with units

#### **2. Entity Objects** (Equality based on unique ID)
**`Supplier`** - Company that supplies products
- Unique supplier ID (never changes)
- Mutable contact information (name, email, phone, address)
- ID-based equality and hashing

**`Customer`** - Person who buys products
- Unique customer ID (never changes)  
- Mutable personal information (name, email, preferences)
- ID-based equality and hashing

#### **3. Complex Objects** (Mixed equality strategies)
**`Product`** - Items in the inventory
- Unique product ID (entity behavior for ID)
- ProductCode, name, description, price, dimensions, supplier
- Category, stock quantity, active status
- Proper combination of entity and value object equality

---

## **The Detailed Specifications**

### **📋 ProductCode Requirements**
```java
public final class ProductCode {
    private final String code;
    
    // Constructor with validation
    public ProductCode(String code) { /* validate format: CATEGORY-NNNNN */ }
    
    @Override
    public String toString() { /* format as "CATEGORY-NNNNN" */ }
    
    @Override
    public boolean equals(Object obj) { /* case-insensitive comparison */ }
    
    @Override
    public int hashCode() { /* case-insensitive hash */ }
    
    // Additional methods
    public String getCategory() { /* extract category part */ }
    public String getNumber() { /* extract number part */ }
}
```

### **📋 Money Requirements**
```java
public final class Money {
    private final BigDecimal amount;
    private final Currency currency;
    
    // Constructors and validation
    public Money(BigDecimal amount, Currency currency) { /* validate non-null */ }
    public Money(double amount, String currencyCode) { /* convenience constructor */ }
    
    @Override
    public String toString() { /* format as "$123.45", "€67.89", "¥1000" */ }
    
    @Override  
    public boolean equals(Object obj) { /* amount and currency must match */ }
    
    @Override
    public int hashCode() { /* combine amount and currency */ }
    
    // Operations that return new Money objects
    public Money add(Money other) { /* validate same currency */ }
    public Money multiply(double factor) { /* return new Money */ }
    public boolean isGreaterThan(Money other) { /* compare amounts */ }
}
```

### **📋 Dimensions Requirements**
```java
public final class Dimensions {
    private final double length;  // in centimeters
    private final double width;   // in centimeters  
    private final double height;  // in centimeters
    private final double weight;  // in kilograms
    
    @Override
    public String toString() { /* "L×W×H cm, Weight kg" format */ }
    
    @Override
    public boolean equals(Object obj) { /* compare all measurements with tolerance */ }
    
    @Override
    public int hashCode() { /* hash all measurements */ }
    
    // Utility methods
    public double getVolume() { /* length × width × height */ }
    public double getShippingCost() { /* calculate based on weight and volume */ }
}
```

### **📋 Supplier (Entity) Requirements**
```java
public class Supplier {
    private final String supplierId;  // Unique ID (never changes)
    private String companyName;       // Can change
    private String contactEmail;      // Can change
    private String phoneNumber;       // Can change
    private String address;           // Can change
    private boolean active;           // Can change
    
    @Override
    public String toString() { /* include ID and company name */ }
    
    @Override
    public boolean equals(Object obj) { /* ONLY compare supplierId */ }
    
    @Override
    public int hashCode() { /* ONLY hash supplierId */ }
}
```

### **📋 Product (Complex Object) Requirements**
```java
public class Product {
    private final Long productId;          // Unique entity ID
    private final ProductCode productCode; // Value object
    private String name;                   // Mutable
    private String description;            // Mutable
    private Money price;                   // Mutable value object
    private Dimensions dimensions;         // Immutable value object
    private Supplier supplier;             // Entity reference
    private String category;               // Mutable
    private int stockQuantity;            // Mutable
    private boolean active;               // Mutable
    
    @Override
    public String toString() { /* comprehensive product information */ }
    
    @Override
    public boolean equals(Object obj) { /* ONLY compare productId (entity behavior) */ }
    
    @Override
    public int hashCode() { /* ONLY hash productId */ }
}
```

---

## **The Implementation Challenges**

### **🎮 Core Requirements**

1. **Proper Object Method Implementation**
   - All classes must override `toString()`, `equals()`, and `hashCode()`
   - Value objects use content-based equality
   - Entity objects use ID-based equality
   - All contracts must be properly maintained

2. **Collection Integration**
   - Objects must work correctly in `HashSet`, `HashMap`, `TreeSet`
   - No duplicate detection based on proper equality
   - Efficient lookups using proper hash codes

3. **Input Validation and Error Handling**
   - Validate all inputs in constructors
   - Handle null values appropriately
   - Provide meaningful error messages

4. **Currency and Measurement Handling**
   - Support multiple currencies (USD, EUR, GBP, JPY)
   - Handle measurement tolerances in Dimensions equality
   - Proper formatting for different locales

### **🏆 Advanced Challenges** ⭐

For those seeking deeper mastery:

1. **Inventory Management Operations**
   - Add/remove products with duplicate prevention
   - Update stock quantities with audit trail
   - Search and filter operations using collections

2. **Complex Queries**
   - Find products by supplier using entity equality
   - Group products by price range using Money operations
   - Sort products by multiple criteria

3. **Data Integrity**
   - Ensure referential integrity between products and suppliers
   - Handle supplier status changes affecting products
   - Validate business rules (e.g., active products must have active suppliers)

4. **Performance Optimization**
   - Efficient hash code distribution for large datasets
   - Optimize equals() methods for common comparison patterns
   - Memory-efficient string representations

5. **Internationalization**
   - Proper currency formatting for different locales
   - Measurement unit conversion and display
   - Localized product category names

---

## **The Testing Requirements**

Create comprehensive tests that demonstrate:

### **🧪 Object Contract Verification**
```java
public static void testObjectContracts() {
    // Test reflexive property: x.equals(x) must be true
    // Test symmetric property: x.equals(y) == y.equals(x)  
    // Test transitive property: if x.equals(y) && y.equals(z), then x.equals(z)
    // Test consistent property: multiple calls return same result
    // Test null handling: x.equals(null) must be false
    // Test hashCode contract: equal objects must have equal hash codes
}
```

### **🔧 Collection Behavior Testing**
```java
public static void testCollectionBehavior() {
    // Test HashSet duplicate prevention
    // Test HashMap key retrieval
    // Test object modification effects on collections
    // Test performance with large datasets
}
```

### **💰 Currency and Measurement Testing**
```java
public static void testBusinessLogic() {
    // Test cross-currency comparisons (should fail appropriately)
    // Test measurement tolerance handling
    // Test price calculations and formatting
    // Test dimension-based shipping cost calculations
}
```

---

## **Sample Expected Behavior**

```
🛍️ E-COMMERCE PRODUCT INVENTORY SYSTEM 🛍️

=== INITIALIZING SYSTEM ===
Created ProductCode: ELEC-12345
Created Money: $299.99 USD  
Created Dimensions: 15.0×10.0×3.0 cm, 0.5 kg
Created Supplier: TechSupply Inc. (ID: SUP001)

=== ADDING PRODUCTS ===  
✅ Added Product: Wireless Headphones (ID: 1001)
📦 Product Details:
   Code: ELEC-12345
   Price: $299.99 USD
   Dimensions: 15.0×10.0×3.0 cm, 0.5 kg
   Supplier: TechSupply Inc.
   Stock: 50 units

❌ Duplicate product code detected: ELEC-12345
   Using ProductCode.equals() for detection

=== TESTING OBJECT CONTRACTS ===
🔍 ProductCode Contract Test:
   ✅ Reflexive: code.equals(code) = true
   ✅ Symmetric: code1.equals(code2) = code2.equals(code1)  
   ✅ Transitive: Verified through chain
   ✅ HashCode: Equal objects have equal hash codes

💰 Money Contract Test:
   ✅ Same currency comparison works
   ❌ Cross-currency comparison properly rejected
   ✅ Hash codes match for equal amounts

=== COLLECTION OPERATIONS ===
📊 Inventory Summary:
   Total Products: 25
   Unique Product Codes: 25 (HashSet prevented duplicates)
   Active Suppliers: 8
   Total Inventory Value: $45,678.90 USD

🔍 Search Results:
   Products by TechSupply Inc.: [Headphones, Keyboards, Mice]
   Products under $100: [Cables, Adapters, Cases]
   Products over 1kg: [Monitors, Printers, Speakers]
```

---

## **The Evaluation Criteria**

Your solution will be judged on:

### **Correctness (40%)**
- Proper `toString()` implementation for all classes
- Correct `equals()` contracts for value and entity objects
- Valid `hashCode()` implementation maintaining contracts
- Accurate collection behavior with no contract violations

### **Design (30%)**
- Appropriate choice between value object and entity equality
- Clean separation of concerns between different object types
- Proper encapsulation and validation
- Logical business rules implementation

### **Functionality (20%)**
- Complete inventory management operations
- Working search and filter capabilities
- Currency and measurement handling
- Error handling and validation

### **Code Quality (10%)**
- Clear, well-documented implementations
- Consistent formatting and naming
- Efficient algorithms and data structures
- Comprehensive test coverage

---

## **The Sacred Hints**

*These whispers from the cosmic ancestor may guide your path:*

1. **Value vs Entity**: Value objects compare content, entities compare IDs. Choose based on business meaning.

2. **toString() Guidelines**: Include class name and key identifying fields. Make it useful for debugging.

3. **equals() Performance**: Check cheapest conditions first (reference equality, null, class), then expensive field comparisons.

4. **hashCode() Distribution**: Use `Objects.hash()` for combining fields. Include same fields as `equals()`.

5. **Collection Safety**: Never modify objects that are keys in HashMaps or elements in HashSets in ways that affect equals/hashCode.

6. **Currency Handling**: Use `BigDecimal` for precise monetary calculations. Don't compare different currencies.

7. **Null Safety**: Use `Objects.equals()` for null-safe field comparison in `equals()` methods.

8. **Testing Strategy**: Create objects with same and different values to verify equality contracts thoroughly.

---

## **The Moment of Truth**

*When you have completed your implementation, test it rigorously. Can you add products to collections without duplicates? Do equal objects have equal hash codes? Do your toString() methods provide meaningful information for debugging?*

*Remember: The goal is not just working code, but **demonstrating mastery** of the cosmic ancestor's sacred contracts. Each `toString()` should speak clearly, each `equals()` should judge fairly, and each `hashCode()` should distribute wisely.*

*The Object class is the foundation upon which all Java objects rest. Master its contracts, and your objects will participate harmoniously in the grand symphony of collections, algorithms, and frameworks.*

---

**Forge your inventory system, seeker. Let every object honor the cosmic contracts, let every collection work flawlessly, and let every operation demonstrate the profound wisdom of the universal ancestor. The Object class awaits your reverence...**
