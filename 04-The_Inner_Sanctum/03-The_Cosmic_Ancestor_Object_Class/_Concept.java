/**
 * The Rune of Syntax: The Cosmic Ancestor Object Class
 * 
 * This sacred scroll demonstrates the pure syntax and behavior
 * of the Object class and its fundamental methods in their elemental form.
 * 
 * Study each implementation carefully - here lies the grammar of
 * object identity and the sacred contracts of the cosmic ancestor.
 */
import java.util.*;

public class _Concept {
    
    // ═══════════════════════════════════════════════════════════════
    // INSTANCE VARIABLES FOR DEMONSTRATION
    // ═══════════════════════════════════════════════════════════════
    
    private String name;
    private int value;
    private boolean active;
    
    // ═══════════════════════════════════════════════════════════════
    // CONSTRUCTORS
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Default constructor
     */
    public _Concept() {
        this.name = "DefaultConcept";
        this.value = 0;
        this.active = true;
    }
    
    /**
     * Parameterized constructor
     */
    public _Concept(String name, int value, boolean active) {
        this.name = name;
        this.value = value;
        this.active = active;
    }
    
    // ═══════════════════════════════════════════════════════════════
    // DEMONSTRATION: DEFAULT OBJECT METHODS (INHERITED FROM Object)
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * This class demonstrates the DEFAULT Object methods without overriding them
     * These methods are inherited from java.lang.Object
     */
    public void demonstrateDefaultObjectMethods() {
        System.out.println("🔍 DEFAULT OBJECT METHODS (inherited from Object):");
        
        _Concept obj1 = new _Concept("Test", 42, true);
        _Concept obj2 = new _Concept("Test", 42, true);
        
        // Default toString() - not very helpful
        System.out.println("obj1.toString(): " + obj1.toString());
        System.out.println("obj2.toString(): " + obj2.toString());
        System.out.println("Notice: toString() shows class@hashcode (not helpful for debugging)");
        
        // Default equals() - only reference equality
        System.out.println("\nobj1.equals(obj2): " + obj1.equals(obj2));
        System.out.println("obj1.equals(obj1): " + obj1.equals(obj1));
        System.out.println("Notice: equals() only returns true for same object reference");
        
        // Default hashCode() - based on memory address
        System.out.println("\nobj1.hashCode(): " + obj1.hashCode());
        System.out.println("obj2.hashCode(): " + obj2.hashCode());
        System.out.println("Notice: Different objects have different hash codes");
        
        // Default getClass() - runtime type information
        System.out.println("\nobj1.getClass(): " + obj1.getClass());
        System.out.println("obj1.getClass().getName(): " + obj1.getClass().getName());
        System.out.println("obj1.getClass().getSimpleName(): " + obj1.getClass().getSimpleName());
        
        System.out.println("\n" + "─".repeat(50));
    }
    
    // ═══════════════════════════════════════════════════════════════
    // OVERRIDING OBJECT METHODS - THE PROPER WAY
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * OVERRIDE toString() - Provide meaningful string representation
     * 
     * Guidelines:
     * - Include class name and key fields
     * - Be concise but informative
     * - Handle null values safely
     * - Format for human readability
     */
    @Override
    public String toString() {
        return String.format("_Concept{name='%s', value=%d, active=%s}", 
                           name, value, active);
    }
    
    /**
     * OVERRIDE equals() - Define logical equality
     * 
     * Must satisfy the contract:
     * - Reflexive: x.equals(x) == true
     * - Symmetric: x.equals(y) == y.equals(x)
     * - Transitive: if x.equals(y) && y.equals(z), then x.equals(z)
     * - Consistent: multiple calls return same result
     * - Null-safe: x.equals(null) == false
     */
    @Override
    public boolean equals(Object obj) {
        // Step 1: Check if same object reference (fast path)
        if (this == obj) return true;
        
        // Step 2: Check for null and class compatibility
        if (obj == null || getClass() != obj.getClass()) return false;
        
        // Step 3: Cast and compare fields
        _Concept concept = (_Concept) obj;
        
        // Compare all fields that contribute to object identity
        return Objects.equals(name, concept.name) &&    // null-safe String comparison
               value == concept.value &&                // primitive comparison
               active == concept.active;                // primitive comparison
    }
    
    /**
     * OVERRIDE hashCode() - Generate hash code for collections
     * 
     * CRITICAL CONTRACT: If objects are equal, they MUST have same hash code!
     * Use same fields that are used in equals()
     */
    @Override
    public int hashCode() {
        // Use Objects.hash() to combine multiple fields
        return Objects.hash(name, value, active);
    }
    
    // ═══════════════════════════════════════════════════════════════
    // DEMONSTRATION METHODS FOR OBJECT CONTRACT VERIFICATION
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Demonstrates the equals() contract properties
     */
    public static void demonstrateEqualsContract() {
        System.out.println("🔬 TESTING equals() CONTRACT:");
        
        _Concept x = new _Concept("Test", 100, true);
        _Concept y = new _Concept("Test", 100, true);  // Logically equal to x
        _Concept z = new _Concept("Test", 100, true);  // Logically equal to x and y
        _Concept w = new _Concept("Different", 200, false);  // Not equal to others
        
        // 1. REFLEXIVE: x.equals(x) must be true
        boolean reflexive = x.equals(x);
        System.out.println("1. Reflexive - x.equals(x): " + reflexive);
        
        // 2. SYMMETRIC: x.equals(y) == y.equals(x)
        boolean symmetric1 = x.equals(y);
        boolean symmetric2 = y.equals(x);
        System.out.println("2. Symmetric - x.equals(y): " + symmetric1 + ", y.equals(x): " + symmetric2);
        System.out.println("   Symmetric property: " + (symmetric1 == symmetric2));
        
        // 3. TRANSITIVE: if x.equals(y) && y.equals(z), then x.equals(z)
        boolean transitive1 = x.equals(y);
        boolean transitive2 = y.equals(z);
        boolean transitive3 = x.equals(z);
        System.out.println("3. Transitive - x.equals(y): " + transitive1 + 
                          ", y.equals(z): " + transitive2 + 
                          ", x.equals(z): " + transitive3);
        System.out.println("   Transitive property: " + 
                          (transitive1 && transitive2 ? transitive3 : "N/A"));
        
        // 4. CONSISTENT: multiple calls should return same result
        boolean consistent1 = x.equals(y);
        boolean consistent2 = x.equals(y);
        boolean consistent3 = x.equals(y);
        System.out.println("4. Consistent - Multiple calls: " + 
                          consistent1 + ", " + consistent2 + ", " + consistent3);
        
        // 5. NULL-SAFE: x.equals(null) must be false
        boolean nullSafe = x.equals(null);
        System.out.println("5. Null-safe - x.equals(null): " + nullSafe);
        
        System.out.println("\n" + "─".repeat(50));
    }
    
    /**
     * Demonstrates the hashCode() contract
     */
    public static void demonstrateHashCodeContract() {
        System.out.println("🔑 TESTING hashCode() CONTRACT:");
        
        _Concept obj1 = new _Concept("Equal", 42, true);
        _Concept obj2 = new _Concept("Equal", 42, true);  // Equal to obj1
        _Concept obj3 = new _Concept("Different", 99, false);  // Not equal
        
        System.out.println("obj1: " + obj1);
        System.out.println("obj2: " + obj2);
        System.out.println("obj3: " + obj3);
        
        // Check equals relationship
        boolean areEqual = obj1.equals(obj2);
        System.out.println("\nobj1.equals(obj2): " + areEqual);
        
        // Check hash codes
        int hash1 = obj1.hashCode();
        int hash2 = obj2.hashCode();
        int hash3 = obj3.hashCode();
        
        System.out.println("obj1.hashCode(): " + hash1);
        System.out.println("obj2.hashCode(): " + hash2);
        System.out.println("obj3.hashCode(): " + hash3);
        
        // Verify contract: equal objects must have equal hash codes
        boolean hashContract = areEqual ? (hash1 == hash2) : true;
        System.out.println("\nHashCode Contract Satisfied: " + hashContract);
        
        if (areEqual && hash1 != hash2) {
            System.out.println("❌ CONTRACT VIOLATION: Equal objects have different hash codes!");
        } else if (areEqual && hash1 == hash2) {
            System.out.println("✅ Contract upheld: Equal objects have equal hash codes");
        }
        
        System.out.println("\n" + "─".repeat(50));
    }
    
    /**
     * Demonstrates behavior with collections
     */
    public static void demonstrateCollectionBehavior() {
        System.out.println("📦 TESTING COLLECTION BEHAVIOR:");
        
        // Create objects
        _Concept concept1 = new _Concept("Alice", 100, true);
        _Concept concept2 = new _Concept("Alice", 100, true);  // Equal to concept1
        _Concept concept3 = new _Concept("Bob", 200, false);
        
        // Test with HashSet (requires proper equals() and hashCode())
        Set<_Concept> conceptSet = new HashSet<>();
        
        conceptSet.add(concept1);
        System.out.println("Added concept1, set size: " + conceptSet.size());
        
        conceptSet.add(concept2);  // Should not be added (equal to concept1)
        System.out.println("Added concept2 (equal to concept1), set size: " + conceptSet.size());
        
        conceptSet.add(concept3);  // Should be added (different from others)
        System.out.println("Added concept3 (different), set size: " + conceptSet.size());
        
        // Test contains()
        boolean containsConcept1 = conceptSet.contains(concept1);
        boolean containsConcept2 = conceptSet.contains(concept2);  // Should be true due to equals()
        System.out.println("\nSet contains concept1: " + containsConcept1);
        System.out.println("Set contains concept2: " + containsConcept2);
        
        // Test with HashMap (using objects as keys)
        Map<_Concept, String> conceptMap = new HashMap<>();
        conceptMap.put(concept1, "First concept");
        conceptMap.put(concept2, "Second concept");  // Should overwrite first due to equals()
        conceptMap.put(concept3, "Third concept");
        
        System.out.println("\nMap size: " + conceptMap.size());
        System.out.println("Value for concept1: " + conceptMap.get(concept1));
        System.out.println("Value for concept2: " + conceptMap.get(concept2));
        
        System.out.println("\n" + "─".repeat(50));
    }
    
    /**
     * Demonstrates inheritance and instanceof
     */
    public static void demonstrateInheritanceAndInstanceof() {
        System.out.println("🏗️ TESTING INHERITANCE AND instanceof:");
        
        _Concept concept = new _Concept("Test", 42, true);
        
        // Every object is an instance of Object
        System.out.println("concept instanceof Object: " + (concept instanceof Object));
        System.out.println("concept instanceof _Concept: " + (concept instanceof _Concept));
        
        // Using Object reference
        Object objectRef = concept;
        System.out.println("objectRef instanceof Object: " + (objectRef instanceof Object));
        System.out.println("objectRef instanceof _Concept: " + (objectRef instanceof _Concept));
        
        // Class information
        System.out.println("\nClass information:");
        System.out.println("concept.getClass(): " + concept.getClass());
        System.out.println("concept.getClass().getSuperclass(): " + concept.getClass().getSuperclass());
        
        // Polymorphic method calls
        System.out.println("\nPolymorphic toString() call:");
        Object[] objects = {
            concept,
            "Hello World",
            42,
            Arrays.asList(1, 2, 3)
        };
        
        for (Object obj : objects) {
            System.out.println(obj.getClass().getSimpleName() + ": " + obj.toString());
        }
        
        System.out.println("\n" + "─".repeat(50));
    }
    
    // ═══════════════════════════════════════════════════════════════
    // GETTERS AND SETTERS
    // ═══════════════════════════════════════════════════════════════
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    
    // ═══════════════════════════════════════════════════════════════
    // MAIN METHOD - COMPREHENSIVE DEMONSTRATION
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Main method demonstrating all aspects of the Object class
     */
    public static void main(String[] args) {
        System.out.println("🌌 THE RUNE OF SYNTAX: The Cosmic Ancestor Object Class 🌌\n");
        
        // ═══════════════════════════════════════════════════════════
        // DEMONSTRATE DEFAULT OBJECT METHODS
        // ═══════════════════════════════════════════════════════════
        
        _Concept demo = new _Concept();
        demo.demonstrateDefaultObjectMethods();
        
        // ═══════════════════════════════════════════════════════════
        // CREATE OBJECTS FOR TESTING
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("🏗️ CREATING TEST OBJECTS:");
        
        _Concept obj1 = new _Concept("Alpha", 100, true);
        _Concept obj2 = new _Concept("Alpha", 100, true);  // Logically equal to obj1
        _Concept obj3 = new _Concept("Beta", 200, false);  // Different from others
        
        System.out.println("obj1: " + obj1);
        System.out.println("obj2: " + obj2);
        System.out.println("obj3: " + obj3);
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════
        // DEMONSTRATE OVERRIDDEN METHODS
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("🎭 TESTING OVERRIDDEN METHODS:");
        
        // Custom toString()
        System.out.println("Custom toString() output:");
        System.out.println("  obj1: " + obj1.toString());
        System.out.println("  obj2: " + obj2.toString());
        System.out.println("  obj3: " + obj3.toString());
        
        // Custom equals()
        System.out.println("\nCustom equals() behavior:");
        System.out.println("  obj1.equals(obj2): " + obj1.equals(obj2));  // Should be true
        System.out.println("  obj1.equals(obj3): " + obj1.equals(obj3));  // Should be false
        System.out.println("  obj1.equals(null): " + obj1.equals(null));  // Should be false
        
        // Custom hashCode()
        System.out.println("\nCustom hashCode() values:");
        System.out.println("  obj1.hashCode(): " + obj1.hashCode());
        System.out.println("  obj2.hashCode(): " + obj2.hashCode());
        System.out.println("  obj3.hashCode(): " + obj3.hashCode());
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════
        // VERIFY CONTRACTS
        // ═══════════════════════════════════════════════════════════
        
        demonstrateEqualsContract();
        demonstrateHashCodeContract();
        demonstrateCollectionBehavior();
        demonstrateInheritanceAndInstanceof();
        
        // ═══════════════════════════════════════════════════════════
        // DEMONSTRATE COMMON PITFALLS
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("⚠️ DEMONSTRATING COMMON PITFALLS:");
        
        // Pitfall 1: Modifying objects in collections
        _Concept mutableObj = new _Concept("Mutable", 1, true);
        Set<_Concept> mutableSet = new HashSet<>();
        mutableSet.add(mutableObj);
        
        System.out.println("Before modification - set contains object: " + 
                          mutableSet.contains(mutableObj));
        
        mutableObj.setValue(999);  // This changes the hashCode!
        System.out.println("After modification - set contains object: " + 
                          mutableSet.contains(mutableObj));
        System.out.println("Object is 'lost' in the collection due to changed hashCode!");
        
        // ═══════════════════════════════════════════════════════════
        // FINAL SUMMARY
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("\n✨ THE COSMIC ANCESTOR REVEALED! ✨");
        System.out.println("🔹 toString() provides meaningful string representation");
        System.out.println("🔹 equals() defines logical equality with proper contract");
        System.out.println("🔹 hashCode() enables collection compatibility");
        System.out.println("🔹 All objects inherit from the cosmic Object class");
        System.out.println("🔹 Proper implementation enables seamless collection usage");
        System.out.println("🔹 Breaking contracts leads to mysterious bugs!");
        System.out.println("\n🌟 Master these methods, and your objects shall join the cosmic harmony!");
    }
}

// ═══════════════════════════════════════════════════════════════════════════
// DEMONSTRATION SUBCLASS - Inheritance and Method Overriding
// ═══════════════════════════════════════════════════════════════════════════

/**
 * Subclass demonstrating inheritance from _Concept (which inherits from Object)
 */
class ConceptSubclass extends _Concept {
    
    private String additionalProperty;
    
    public ConceptSubclass(String name, int value, boolean active, String additionalProperty) {
        super(name, value, active);
        this.additionalProperty = additionalProperty;
    }
    
    /**
     * Override toString() to include additional property
     */
    @Override
    public String toString() {
        return String.format("ConceptSubclass{name='%s', value=%d, active=%s, additional='%s'}", 
                           getName(), getValue(), isActive(), additionalProperty);
    }
    
    /**
     * Override equals() to include additional property
     */
    @Override
    public boolean equals(Object obj) {
        // Use getClass() for strict type checking (no subclass mixing)
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        // Call super.equals() to check parent fields, then check additional fields
        if (!super.equals(obj)) return false;
        
        ConceptSubclass that = (ConceptSubclass) obj;
        return Objects.equals(additionalProperty, that.additionalProperty);
    }
    
    /**
     * Override hashCode() to include additional property
     */
    @Override
    public int hashCode() {
        // Include parent hash code and additional property
        return Objects.hash(super.hashCode(), additionalProperty);
    }
    
    public String getAdditionalProperty() { return additionalProperty; }
    public void setAdditionalProperty(String additionalProperty) { 
        this.additionalProperty = additionalProperty; 
    }
}
