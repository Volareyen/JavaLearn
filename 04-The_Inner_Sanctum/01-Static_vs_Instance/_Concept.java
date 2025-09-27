/**
 * The Rune of Syntax: Static vs Instance
 * 
 * This sacred scroll demonstrates the pure syntax and behavior
 * of static and instance members in their elemental form.
 * 
 * Study each line carefully - here lies the grammar of 
 * the eternal versus the temporal.
 */
public class _Concept {
    
    // ═══════════════════════════════════════════════════════════════
    // STATIC MEMBERS (Belong to the CLASS itself)
    // ═══════════════════════════════════════════════════════════════
    
    // Static variable - shared by ALL instances of this class
    private static int classInstanceCount = 0;
    
    // Static constant - immutable, shared value
    public static final String CLASS_PURPOSE = "Demonstrating Static vs Instance";
    
    // Static variable for demonstration
    private static String sharedMessage = "I belong to the class!";
    
    // ═══════════════════════════════════════════════════════════════
    // INSTANCE MEMBERS (Belong to each OBJECT individually)
    // ═══════════════════════════════════════════════════════════════
    
    // Instance variables - each object has its own copy
    private String objectName;
    private int objectId;
    private boolean isActive;
    
    // ═══════════════════════════════════════════════════════════════
    // CONSTRUCTORS (Always instance-related)
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Constructor - called when creating each new instance
     * Automatically increments the static counter
     */
    public _Concept(String objectName) {
        this.objectName = objectName;           // Instance assignment
        this.objectId = ++classInstanceCount;   // Using static variable
        this.isActive = true;
        
        System.out.println("Created instance: " + objectName + 
                          " (Object #" + objectId + ")");
    }
    
    // ═══════════════════════════════════════════════════════════════
    // STATIC METHODS (Belong to the CLASS)
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Static method - can be called WITHOUT creating any object
     * Can ONLY access static members directly
     */
    public static void displayClassInfo() {
        System.out.println("=== CLASS INFORMATION ===");
        System.out.println("Purpose: " + CLASS_PURPOSE);
        System.out.println("Instances created: " + classInstanceCount);
        System.out.println("Shared message: " + sharedMessage);
        
        // ❌ ILLEGAL: Cannot access instance members directly
        // System.out.println("Object name: " + objectName);  // COMPILER ERROR!
        // System.out.println("Object ID: " + objectId);      // COMPILER ERROR!
    }
    
    /**
     * Static method to get the total number of instances created
     */
    public static int getInstanceCount() {
        return classInstanceCount;  // Accessing static variable
    }
    
    /**
     * Static method to update the shared message
     */
    public static void setSharedMessage(String message) {
        sharedMessage = message;  // Modifying static variable
    }
    
    /**
     * Static method to get the shared message
     */
    public static String getSharedMessage() {
        return sharedMessage;
    }
    
    // ═══════════════════════════════════════════════════════════════
    // INSTANCE METHODS (Belong to each OBJECT)
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Instance method - must be called on a specific object
     * Can access BOTH instance and static members
     */
    public void displayObjectInfo() {
        System.out.println("=== OBJECT INFORMATION ===");
        System.out.println("Object name: " + objectName);        // Instance variable
        System.out.println("Object ID: " + objectId);            // Instance variable
        System.out.println("Is active: " + isActive);            // Instance variable
        
        // ✅ LEGAL: Instance methods can access static members too
        System.out.println("Total instances: " + classInstanceCount);  // Static variable
        System.out.println("Class purpose: " + CLASS_PURPOSE);         // Static constant
        System.out.println("Shared message: " + sharedMessage);        // Static variable
    }
    
    /**
     * Instance method to demonstrate object-specific behavior
     */
    public void performAction() {
        System.out.println(objectName + " (Object #" + objectId + ") is performing an action!");
        
        // Instance methods can modify both instance and static members
        isActive = !isActive;  // Modify instance variable (affects only this object)
        
        // Could also modify static variables (affects ALL objects)
        // sharedMessage = objectName + " was the last to act";
    }
    
    /**
     * Instance method using 'this' keyword for clarity
     */
    public void demonstrateThis() {
        System.out.println("=== DEMONSTRATING 'this' KEYWORD ===");
        System.out.println("this.objectName = " + this.objectName);
        System.out.println("this.objectId = " + this.objectId);
        System.out.println("this.isActive = " + this.isActive);
        
        // 'this' refers to the current object instance
        System.out.println("Hash code of 'this' object: " + this.hashCode());
    }
    
    // ═══════════════════════════════════════════════════════════════
    // GETTERS AND SETTERS (Instance methods)
    // ═══════════════════════════════════════════════════════════════
    
    public String getObjectName() {
        return objectName;
    }
    
    public void setObjectName(String objectName) {
        this.objectName = objectName;  // Using 'this' to distinguish parameters
    }
    
    public int getObjectId() {
        return objectId;  // Read-only property (no setter)
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        this.isActive = active;
    }
    
    // ═══════════════════════════════════════════════════════════════
    // DEMONSTRATION MAIN METHOD (Static)
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Main method - the entry point, always static
     */
    public static void main(String[] args) {
        System.out.println("🔮 THE RUNE OF SYNTAX: Static vs Instance 🔮\n");
        
        // ═══════════════════════════════════════════════════════════
        // CALLING STATIC METHODS (No objects needed)
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("📚 CALLING STATIC METHODS (No objects exist yet):");
        _Concept.displayClassInfo();        // Class name dot method name
        System.out.println("Instance count: " + _Concept.getInstanceCount());
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════
        // CREATING INSTANCES (Objects)
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("🏗️ CREATING INSTANCES:");
        _Concept obj1 = new _Concept("Alpha");
        _Concept obj2 = new _Concept("Beta");
        _Concept obj3 = new _Concept("Gamma");
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════
        // CALLING INSTANCE METHODS (Objects required)
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("🎭 CALLING INSTANCE METHODS:");
        obj1.displayObjectInfo();
        System.out.println();
        
        obj2.performAction();
        obj3.performAction();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════
        // STATIC AFTER OBJECTS CREATED
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("📊 STATIC INFORMATION AFTER CREATING OBJECTS:");
        _Concept.displayClassInfo();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════
        // MODIFYING STATIC VS INSTANCE STATE
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("🔄 MODIFYING SHARED (STATIC) STATE:");
        _Concept.setSharedMessage("The wise teacher speaks to all!");
        
        System.out.println("All objects now see the updated shared message:");
        obj1.displayObjectInfo();
        System.out.println();
        obj2.displayObjectInfo();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════
        // DEMONSTRATING 'this' KEYWORD
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("🪞 DEMONSTRATING 'this' KEYWORD:");
        obj1.demonstrateThis();
        System.out.println();
        obj2.demonstrateThis();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════
        // FINAL SUMMARY
        // ═══════════════════════════════════════════════════════════
        
        System.out.println("📋 FINAL SUMMARY:");
        System.out.println("Total instances created: " + _Concept.getInstanceCount());
        System.out.println("Current shared message: " + _Concept.getSharedMessage());
        System.out.println("\n✨ The syntax of Static vs Instance has been revealed! ✨");
    }
}
