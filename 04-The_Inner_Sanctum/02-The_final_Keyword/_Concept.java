/**
 * The Rune of Syntax: The `final` Keyword
 * 
 * This sacred scroll demonstrates the pure syntax and behavior
 * of the `final` keyword in all its manifestations.
 * 
 * Study each application carefully - here lies the grammar of
 * unchangeability and permanence.
 */
public class _Concept {
    
    // ═══════════════════════════════════════════════════════════════
    // FINAL STATIC VARIABLES (Universal Constants)
    // ═══════════════════════════════════════════════════════════════
    
    // Final static constants - the eternal truths
    public static final String CLASS_NAME = "_Concept";
    public static final int MAX_ATTEMPTS = 3;
    public static final double PI_APPROXIMATION = 3.14159;
    private static final String SECRET_CODE = "FINAL_POWER";
    
    // ❌ These would cause compilation errors if uncommented:
    // static { MAX_ATTEMPTS = 5; }  // Cannot modify final static variable
    
    // ═══════════════════════════════════════════════════════════════
    // FINAL INSTANCE VARIABLES 
    // ═══════════════════════════════════════════════════════════════
    
    // Final instance variables - set once per object, never changed
    private final String objectId;
    private final long creationTime;
    private final int version;
    
    // Blank final - MUST be initialized in constructor
    private final String description;
    
    // Final object reference - reference cannot change, but object content can
    private final StringBuilder notes;
    private final int[] numbers;
    
    // ═══════════════════════════════════════════════════════════════
    // CONSTRUCTOR - Initializing Final Variables
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Constructor MUST initialize all final instance variables
     */
    public _Concept(String objectId, String description) {
        // Initialize final variables (this is the ONLY place they can be set)
        this.objectId = objectId;
        this.creationTime = System.currentTimeMillis();
        this.version = 1;  // Could also be a parameter
        this.description = description;  // Blank final initialized here
        
        // Initialize final object references
        this.notes = new StringBuilder("Initial notes");
        this.numbers = new int[]{1, 2, 3, 4, 5};
        
        System.out.println("Created object with ID: " + objectId);
        
        // ❌ These would cause compilation errors:
        // this.objectId = "different_id";     // Cannot reassign final variable
        // this.creationTime = 0L;             // Cannot reassign final variable
    }
    
    // ═══════════════════════════════════════════════════════════════
    // FINAL METHODS - Cannot be Overridden by Subclasses
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Final method - this behavior cannot be changed by inheritance
     */
    public final void displayObjectInfo() {
        System.out.println("=== OBJECT INFORMATION (FINAL METHOD) ===");
        System.out.println("Object ID: " + objectId);
        System.out.println("Creation Time: " + creationTime);
        System.out.println("Version: " + version);
        System.out.println("Description: " + description);
        System.out.println("Current Notes: " + notes.toString());
        System.out.println("Numbers Array: " + java.util.Arrays.toString(numbers));
    }
    
    /**
     * Final method demonstrating access to static final constants
     */
    public final String getClassInfo() {
        return "Class: " + CLASS_NAME + ", Max Attempts: " + MAX_ATTEMPTS + 
               ", PI: " + PI_APPROXIMATION;
    }
    
    /**
     * Final method with parameters - parameters can also be final
     */
    public final double calculateCircleArea(final double radius) {
        // radius = 10.0;  // ❌ Cannot modify final parameter
        
        if (radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }
        
        return PI_APPROXIMATION * radius * radius;
    }
    
    /**
     * Final method demonstrating operations on final object references
     */
    public final void demonstrateFinalObjectReference() {
        System.out.println("\n🔍 DEMONSTRATING FINAL OBJECT REFERENCES:");
        
        // ❌ Cannot reassign the reference itself:
        // notes = new StringBuilder("Different notes");  // COMPILATION ERROR!
        // numbers = new int[]{10, 20, 30};               // COMPILATION ERROR!
        
        // ✅ CAN modify the contents of the objects:
        notes.append(" - Additional note added!");
        numbers[0] = 999;  // Modify array contents
        
        System.out.println("Modified notes: " + notes.toString());
        System.out.println("Modified numbers: " + java.util.Arrays.toString(numbers));
        System.out.println("Reference itself remains final and unchanged!");
    }
    
    // ═══════════════════════════════════════════════════════════════
    // NON-FINAL METHODS - Can be Overridden
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Non-final method - can be overridden by subclasses
     */
    public void displayCustomMessage() {
        System.out.println("This is the default message (can be overridden)");
    }
    
    /**
     * Non-final method that can become final when overridden
     */
    public void flexibleBehavior() {
        System.out.println("This behavior can be customized by subclasses");
    }
    
    // ═══════════════════════════════════════════════════════════════
    // METHODS WITH FINAL LOCAL VARIABLES
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Demonstrates final local variables and parameters
     */
    public void demonstrateFinalLocals(final String input, final int count) {
        // Final parameters cannot be modified
        // input = "modified";  // ❌ COMPILATION ERROR
        // count = 10;          // ❌ COMPILATION ERROR
        
        // Final local variables
        final String localConstant = "This cannot change";
        final java.util.List<String> localList = new java.util.ArrayList<>();
        
        // ❌ Cannot reassign final local variables:
        // localConstant = "Different value";     // COMPILATION ERROR
        // localList = new java.util.ArrayList<>();  // COMPILATION ERROR
        
        // ✅ Can modify contents of final object references:
        localList.add("Item 1");
        localList.add("Item 2");
        
        System.out.println("Final parameter 'input': " + input);
        System.out.println("Final parameter 'count': " + count);
        System.out.println("Final local constant: " + localConstant);
        System.out.println("Final local list contents: " + localList);
        
        // Final variables in loops
        for (final String item : localList) {
            // item = "modified";  // ❌ Cannot modify final loop variable
            System.out.println("Processing final loop variable: " + item);
        }
    }
    
    /**
     * Demonstrates final variables in different scopes
     */
    public void demonstrateScopes() {
        System.out.println("\n🎯 FINAL VARIABLES IN DIFFERENT SCOPES:");
        
        final int blockConstant = 42;
        
        // Block scope with final variables
        {
            final String blockMessage = "Inside block";
            System.out.println("Block constant: " + blockConstant);
            System.out.println("Block message: " + blockMessage);
        }
        // blockMessage not accessible here
        
        // Final variable in if statement
        if (blockConstant > 0) {
            final String conditionalMessage = "Positive number";
            System.out.println("Conditional message: " + conditionalMessage);
        }
        
        // Final variables in try-catch
        try {
            final String tryMessage = "In try block";
            System.out.println("Try message: " + tryMessage);
        } catch (final Exception e) {
            // Final exception parameter
            System.out.println("Caught final exception: " + e.getMessage());
        }
    }
    
    // ═══════════════════════════════════════════════════════════════
    // GETTERS FOR FINAL INSTANCE VARIABLES
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Getters for final fields - no setters needed/possible
     */
    public String getObjectId() {
        return objectId;  // Safe to return - cannot be modified anyway
    }
    
    public long getCreationTime() {
        return creationTime;
    }
    
    public int getVersion() {
        return version;
    }
    
    public String getDescription() {
        return description;
    }
    
    /**
     * For final object references, we might want defensive copies
     */
    public String getNotes() {
        return notes.toString();  // Return copy of content, not the mutable reference
    }
    
    public int[] getNumbers() {
        return numbers.clone();  // Return copy to prevent external modification
    }
    
    // ═══════════════════════════════════════════════════════════════
    // MAIN METHOD - COMPREHENSIVE DEMONSTRATION
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Main method demonstrating all aspects of the final keyword
     */
    public static void main(String[] args) {
        System.out.println("🔒 THE RUNE OF SYNTAX: The `final` Keyword 🔒\n");
        
        // ═══════════════════════════════════════════════════════════
        // ACCESSING STATIC FINAL CONSTANTS
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("📜 STATIC FINAL CONSTANTS:");
        System.out.println("Class Name: " + CLASS_NAME);
        System.out.println("Max Attempts: " + MAX_ATTEMPTS);
        System.out.println("PI Approximation: " + PI_APPROXIMATION);
        // System.out.println("Secret Code: " + SECRET_CODE);  // Private - not accessible
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════
        // CREATING OBJECTS WITH FINAL INSTANCE VARIABLES
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("🏗️ CREATING OBJECTS WITH FINAL INSTANCE VARIABLES:");
        _Concept obj1 = new _Concept("OBJ_001", "First demonstration object");
        _Concept obj2 = new _Concept("OBJ_002", "Second demonstration object");
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════
        // CALLING FINAL METHODS
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("🔒 CALLING FINAL METHODS:");
        obj1.displayObjectInfo();
        System.out.println();
        
        System.out.println("Class Info: " + obj1.getClassInfo());
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════
        // FINAL METHOD WITH PARAMETERS
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("📊 FINAL METHOD WITH FINAL PARAMETERS:");
        double radius = 5.0;
        double area = obj1.calculateCircleArea(radius);
        System.out.println("Circle with radius " + radius + " has area: " + 
                          String.format("%.2f", area));
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════
        // DEMONSTRATING FINAL OBJECT REFERENCES
        // ═══════════════════════════════════════════════════════════
        
        obj1.demonstrateFinalObjectReference();
        obj1.displayObjectInfo();  // Show modified contents
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════
        // CALLING NON-FINAL METHODS
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("🔓 CALLING NON-FINAL METHODS (OVERRIDEABLE):");
        obj2.displayCustomMessage();
        obj2.flexibleBehavior();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════
        // FINAL LOCAL VARIABLES AND PARAMETERS
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("🎯 FINAL LOCAL VARIABLES AND PARAMETERS:");
        obj1.demonstrateFinalLocals("Immutable Input", 100);
        
        // ═══════════════════════════════════════════════════════════
        // FINAL VARIABLES IN DIFFERENT SCOPES
        // ═══════════════════════════════════════════════════════════
        
        obj2.demonstrateScopes();
        
        // ═══════════════════════════════════════════════════════════
        // DEMONSTRATING IMMUTABILITY OF FINAL FIELDS
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("\n🛡️ IMMUTABILITY OF FINAL FIELDS:");
        System.out.println("Object 1 ID: " + obj1.getObjectId() + " (cannot be changed)");
        System.out.println("Object 2 ID: " + obj2.getObjectId() + " (cannot be changed)");
        System.out.println("Creation times are permanent and unique per object");
        
        // ═══════════════════════════════════════════════════════════
        // FINAL LOCAL VARIABLE DEMONSTRATION
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("\n🎲 FINAL LOCAL VARIABLE OPERATIONS:");
        final String finalMessage = "This message cannot be reassigned";
        final java.util.Map<String, Integer> finalMap = new java.util.HashMap<>();
        
        // ❌ These would cause compilation errors:
        // finalMessage = "Different message";  // Cannot reassign
        // finalMap = new HashMap<>();          // Cannot reassign reference
        
        // ✅ But we can modify the contents:
        finalMap.put("key1", 10);
        finalMap.put("key2", 20);
        
        System.out.println("Final message: " + finalMessage);
        System.out.println("Final map contents: " + finalMap);
        System.out.println("Reference is final, content is mutable!");
        
        // ═══════════════════════════════════════════════════════════
        // SUMMARY
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("\n✨ THE POWER OF `final` DEMONSTRATED! ✨");
        System.out.println("🔹 Final variables create immutable references");
        System.out.println("🔹 Final methods prevent method overriding");
        System.out.println("🔹 Final parameters ensure parameter integrity");
        System.out.println("🔹 Final local variables provide local immutability");
        System.out.println("🔹 Static final variables create universal constants");
        System.out.println("🔹 Final object references ≠ immutable object contents");
        System.out.println("\n🎯 The keyword `final` brings certainty to an uncertain world!");
    }
}

// ═══════════════════════════════════════════════════════════════════════════
// SUBCLASS DEMONSTRATION - Inheritance with Final Methods
// ═══════════════════════════════════════════════════════════════════════════

/**
 * Subclass demonstrating what can and cannot be overridden
 */
class ConceptSubclass extends _Concept {
    
    public ConceptSubclass(String objectId, String description) {
        super(objectId, description);
    }
    
    // ❌ CANNOT override final methods - these would cause compilation errors:
    // public void displayObjectInfo() { }     // Final method cannot be overridden
    // public String getClassInfo() { }        // Final method cannot be overridden
    // public double calculateCircleArea(double radius) { } // Final method cannot be overridden
    
    // ✅ CAN override non-final methods:
    @Override
    public void displayCustomMessage() {
        System.out.println("This is a CUSTOMIZED message from the subclass!");
    }
    
    @Override
    public final void flexibleBehavior() {  // Can add final when overriding
        System.out.println("Subclass behavior - now made final!");
    }
    
    // Can add new final methods
    public final void newFinalMethod() {
        System.out.println("This is a new final method in the subclass");
    }
}
