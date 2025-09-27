/**
 * THE SACRED RUNE OF SYNTAX: The `this` Keyword
 * 
 * This file demonstrates the pure syntax and mechanics of the `this` keyword
 * in its most essential forms. Study these patterns, for they are the 
 * building blocks of Object self-awareness.
 * 
 * The `this` keyword is a reference to the current object instance.
 * It allows an object to refer to itself explicitly.
 */

public class _Concept {
    
    // FIELDS: The object's state
    private String name;
    private int value;
    private boolean isActive;
    
    // DEMONSTRATION 1: Distinguishing Fields from Parameters
    // This is the most common and crucial use of `this`
    
    public void setName(String name) {
        // Without 'this', this would be: name = name (parameter = parameter)
        // The field would remain unchanged!
        this.name = name;  // CORRECT: field = parameter
    }
    
    public void setValue(int value) {
        this.value = value;  // Clear distinction: field = parameter
    }
    
    public void setActive(boolean isActive) {
        this.isActive = isActive;  // Field = parameter (different types of 'isActive')
    }
    
    // DEMONSTRATION 2: Optional but Clear Self-Reference
    // When there's no naming conflict, `this` is optional but improves clarity
    
    public String getName() {
        return this.name;  // Could be just 'return name;' but 'this' is explicit
    }
    
    public int getValue() {
        return this.value;  // Explicitly showing this is the object's field
    }
    
    public void displayInfo() {
        // All these 'this' references are optional but make intent crystal clear
        System.out.println("Name: " + this.name);
        System.out.println("Value: " + this.value);
        System.out.println("Active: " + this.isActive);
    }
    
    // DEMONSTRATION 3: Method Chaining
    // Returning 'this' allows methods to be chained together elegantly
    
    public _Concept setNameAndReturn(String name) {
        this.name = name;
        return this;  // Return the current object for chaining
    }
    
    public _Concept setValueAndReturn(int value) {
        this.value = value;
        return this;  // Return the current object for chaining
    }
    
    public _Concept activateAndReturn() {
        this.isActive = true;
        return this;  // Return the current object for chaining
    }
    
    // DEMONSTRATION 4: Passing Current Object to Other Methods
    // Sometimes an object needs to pass itself to another method
    
    public void registerWithManager(ObjectManager manager) {
        manager.addObject(this);  // Pass this object to the manager
    }
    
    public boolean isEqualTo(_Concept other) {
        // Compare this object with another object
        return this.name.equals(other.name) && 
               this.value == other.value;
    }
    
    // DEMONSTRATION 5: Constructor Chaining with this()
    // One constructor can call another constructor in the same class
    
    // Default constructor - calls parameterized constructor
    public _Concept() {
        this("Default", 0, false);  // Must be first statement
    }
    
    // Constructor with name only
    public _Concept(String name) {
        this(name, 0, false);  // Chain to full constructor
    }
    
    // Constructor with name and value
    public _Concept(String name, int value) {
        this(name, value, false);  // Chain to full constructor
    }
    
    // Full parameterized constructor
    public _Concept(String name, int value, boolean isActive) {
        this.name = name;        // Field = parameter (requires 'this')
        this.value = value;      // Field = parameter (requires 'this')
        this.isActive = isActive; // Field = parameter (requires 'this')
    }
    
    // DEMONSTRATION 6: Comparison - With and Without 'this'
    
    // Method showing when 'this' is REQUIRED
    public void demonstrateRequired(String name, int value) {
        // WRONG: Without 'this', these assign parameters to themselves!
        // name = name;     // Parameter = parameter (field unchanged!)
        // value = value;   // Parameter = parameter (field unchanged!)
        
        // CORRECT: With 'this', we assign parameters to fields
        this.name = name;   // Field = parameter
        this.value = value; // Field = parameter
    }
    
    // Method showing when 'this' is OPTIONAL but good practice
    public void demonstrateOptional() {
        // Both forms work when there's no naming conflict:
        String currentName1 = name;      // Direct access (works)
        String currentName2 = this.name; // Explicit access (clearer)
        
        // Demonstrate the difference for educational purposes
        System.out.println("Direct access: " + currentName1);
        System.out.println("Explicit access: " + currentName2);
        
        // But 'this' makes it explicit that we're accessing fields
        System.out.println("My name: " + this.name);
        System.out.println("My value: " + this.value);
    }
    
    // DEMONSTRATION 7: Method Chaining in Action
    public static void demonstrateChaining() {
        _Concept obj = new _Concept();
        
        // Traditional way (multiple statements)
        obj.setNameAndReturn("Chained");
        obj.setValueAndReturn(100);
        obj.activateAndReturn();
        
        // Elegant chaining way (single statement)
        obj.setNameAndReturn("Chained")
           .setValueAndReturn(100)
           .activateAndReturn();
        
        // The magic: each method returns 'this', allowing the next method call
    }
}

/**
 * HELPER CLASS: Demonstrates passing 'this' to other objects
 */
class ObjectManager {
    public void addObject(_Concept obj) {
        System.out.println("Registered object: " + obj.getName());
    }
}

/**
 * KEY SYNTAX PATTERNS TO REMEMBER:
 * 
 * 1. Field Assignment:     this.fieldName = parameter;
 * 2. Method Chaining:      return this;
 * 3. Self-Reference:       this.methodName() or this.fieldName
 * 4. Constructor Chaining: this(parameters);  // Must be first statement
 * 5. Passing Self:         otherObject.method(this);
 * 
 * WHEN 'this' IS REQUIRED:
 * - When parameter names match field names
 * - When calling other constructors (this())
 * - When returning the current object
 * - When passing the current object to other methods
 * 
 * WHEN 'this' IS OPTIONAL:
 * - When accessing fields/methods without naming conflicts
 * - But still recommended for clarity and explicit intent
 */
