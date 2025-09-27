/**
 * THE SACRED RUNE OF SYNTAX: Encapsulation - The First Pillar
 * 
 * This file demonstrates the pure syntax and mechanics of Encapsulation
 * in its most essential forms. Study these patterns, for they are the 
 * building blocks of data protection and information hiding.
 * 
 * Encapsulation = Data Hiding + Implementation Hiding + Controlled Access
 */

public class _Concept {
    
    // DEMONSTRATION 1: The Four Sacred Access Modifiers
    
    // PRIVATE: Only this class can access (Inner Sanctum)
    private String secretTreasure = "Hidden Gold";
    private int privateCounter = 0;
    
    // DEFAULT (Package-Private): Same package can access (Family Circle)
    String familySecret = "Package Visible";
    int packageCounter = 0;
    
    // PROTECTED: Same package + subclasses can access (Bloodline)
    protected String inheritedWisdom = "For My Children";
    protected int protectedCounter = 0;
    
    // PUBLIC: Everyone can access (Open Gates)
    public String publicMessage = "Welcome All";
    public int publicCounter = 0;
    
    // DEMONSTRATION 2: Basic Encapsulation Pattern (Getters and Setters)
    
    private String name;        // Private field - hidden from outside
    private int age;            // Private field - protected data
    private double balance;     // Private field - critical data
    
    // GETTER: Controlled read access to private field
    public String getName() {
        return this.name;       // Safe read access
    }
    
    // SETTER: Controlled write access with validation
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;   // Validated assignment
        } else {
            System.out.println("Invalid name provided");
        }
    }
    
    // GETTER with additional logic
    public int getAge() {
        return this.age;        // Could add logging, caching, etc.
    }
    
    // SETTER with validation and business rules
    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            System.out.println("Age must be between 0 and 150");
        }
    }
    
    // GETTER for sensitive data (might include security checks)
    public double getBalance() {
        // Could add authentication, logging, etc.
        return this.balance;
    }
    
    // SETTER for sensitive data with strict validation
    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("Balance cannot be negative");
        }
    }
    
    // DEMONSTRATION 3: Read-Only Properties (Getter Only)
    
    private final String accountNumber = "ACC-" + System.currentTimeMillis();
    
    public String getAccountNumber() {
        return this.accountNumber;  // Read-only: no setter provided
    }
    
    // DEMONSTRATION 4: Write-Only Properties (Setter Only)
    
    private String auditLog = "";
    
    public void addAuditEntry(String entry) {
        this.auditLog += entry + "\n";  // Write-only: no getter provided
    }
    
    // Internal method to demonstrate audit log usage
    private void processAuditLog() {
        if (auditLog.length() > 0) {
            System.out.println("Processing audit entries...");
        }
    }
    
    // DEMONSTRATION 5: Computed Properties (Calculated on Demand)
    
    private double width = 10.0;
    private double height = 5.0;
    
    public double getWidth() { return width; }
    public void setWidth(double width) {
        if (width > 0) this.width = width;
    }
    
    public double getHeight() { return height; }
    public void setHeight(double height) {
        if (height > 0) this.height = height;
    }
    
    // Computed property - not stored, calculated when requested
    public double getArea() {
        return this.width * this.height;  // Computed from other fields
    }
    
    public double getPerimeter() {
        return 2 * (this.width + this.height);  // Also computed
    }
    
    // DEMONSTRATION 6: Implementation Hiding (Private Methods)
    
    // Public interface - what the world sees
    public boolean authenticateUser(String username, String password) {
        return performAuthentication(username, password);
    }
    
    // Private implementation - how it actually works (hidden)
    private boolean performAuthentication(String username, String password) {
        // Complex authentication logic hidden from outside world
        return validateCredentials(username) && checkPassword(password);
    }
    
    // More private helper methods - internal implementation
    private boolean validateCredentials(String username) {
        return username != null && username.length() >= 3;
    }
    
    private boolean checkPassword(String password) {
        return password != null && password.length() >= 8;
    }
    
    // DEMONSTRATION 7: Defensive Copying (Protecting Mutable Objects)
    
    private int[] scores = {85, 92, 78, 96, 88};  // Private mutable array
    
    // BAD: Direct reference exposes internal array
    // public int[] getScores() { return scores; }  // NEVER DO THIS!
    
    // GOOD: Defensive copy protects internal state
    public int[] getScores() {
        int[] copy = new int[scores.length];
        System.arraycopy(scores, 0, copy, 0, scores.length);
        return copy;  // Return copy, not original
    }
    
    // GOOD: Controlled modification through methods
    public void setScore(int index, int score) {
        if (index >= 0 && index < scores.length && score >= 0 && score <= 100) {
            scores[index] = score;
        } else {
            System.out.println("Invalid score or index");
        }
    }
    
    // DEMONSTRATION 8: Lazy Initialization (Create When Needed)
    
    private ExpensiveObject expensiveResource;  // Not created initially
    
    public ExpensiveObject getExpensiveResource() {
        if (expensiveResource == null) {
            System.out.println("Creating expensive resource...");
            expensiveResource = new ExpensiveObject();  // Create only when needed
        }
        return expensiveResource;
    }
    
    // DEMONSTRATION 9: Encapsulation with Validation Chains
    
    private String email;
    
    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
    
    private boolean isValidEmail(String email) {
        return email != null && 
               email.contains("@") && 
               email.contains(".") &&
               email.length() > 5;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    // DEMONSTRATION 10: Access Control with State Management
    
    private boolean isLocked = false;
    private String data = "Important Data";
    
    public void lock() {
        this.isLocked = true;
        System.out.println("Object locked");
    }
    
    public void unlock() {
        this.isLocked = false;
        System.out.println("Object unlocked");
    }
    
    // Access controlled by state
    public String getData() {
        if (isLocked) {
            System.out.println("Access denied - object is locked");
            return null;
        }
        return this.data;
    }
    
    public void setData(String data) {
        if (isLocked) {
            System.out.println("Modification denied - object is locked");
            return;
        }
        this.data = data;
    }
    
    // DEMONSTRATION 11: Method Chaining with Encapsulation
    
    public _Concept setNameAndReturn(String name) {
        setName(name);  // Use existing validation
        return this;    // Enable chaining
    }
    
    public _Concept setAgeAndReturn(int age) {
        setAge(age);    // Use existing validation
        return this;    // Enable chaining
    }
    
    // DEMONSTRATION 12: Display Method Showing Encapsulated Data
    
    public void displayInfo() {
        System.out.println("=== Object Information ===");
        System.out.println("Name: " + (name != null ? name : "Not set"));
        System.out.println("Age: " + age);
        System.out.println("Balance: $" + balance);
        System.out.println("Account: " + accountNumber);
        System.out.println("Area: " + getArea());
        System.out.println("Locked: " + isLocked);
        System.out.println("Public Counter: " + publicCounter);
        // Note: Private fields accessed through this class only
        System.out.println("Private Counter: " + privateCounter);
        System.out.println("Secret Treasure: " + secretTreasure);  // Use private field
        
        // Process audit log if needed
        processAuditLog();
    }
    
    // DEMONSTRATION 13: Static Factory Method (Alternative to Constructor)
    
    public static _Concept createPerson(String name, int age) {
        _Concept person = new _Concept();
        person.setName(name);  // Uses validation
        person.setAge(age);    // Uses validation
        return person;
    }
    
    public static _Concept createAccount(double initialBalance) {
        _Concept account = new _Concept();
        account.setBalance(initialBalance);  // Uses validation
        return account;
    }
}

/**
 * HELPER CLASS: Demonstrates expensive resource for lazy initialization
 */
class ExpensiveObject {
    private String data;
    
    public ExpensiveObject() {
        // Simulate expensive initialization
        this.data = "Expensive data loaded";
    }
    
    public String getData() {
        return data;
    }
}

/**
 * DEMONSTRATION CLASS: Shows access modifier effects
 */
class AccessTester {
    public void testAccess() {
        _Concept obj = new _Concept();
        
        // PUBLIC: Can access from anywhere
        obj.publicMessage = "Modified";        // ✓ Allowed
        obj.publicCounter++;                   // ✓ Allowed
        
        // PACKAGE (Default): Can access from same package
        obj.familySecret = "Changed";          // ✓ Allowed (same package)
        obj.packageCounter++;                  // ✓ Allowed (same package)
        
        // PROTECTED: Can access from same package (inheritance allows too)
        obj.inheritedWisdom = "Modified";      // ✓ Allowed (same package)
        obj.protectedCounter++;                // ✓ Allowed (same package)
        
        // PRIVATE: Cannot access from outside the class
        // obj.secretTreasure = "Hack";        // ✗ Compilation error!
        // obj.privateCounter++;               // ✗ Compilation error!
        
        // Must use public methods to access private data
        obj.setName("John");                   // ✓ Controlled access
        String name = obj.getName();           // ✓ Controlled access
        System.out.println("Retrieved name: " + name);  // Use the retrieved name
    }
}

/**
 * KEY ENCAPSULATION PATTERNS TO REMEMBER:
 * 
 * 1. FIELD ACCESS CONTROL:
 *    private Type field;           // Hidden from outside
 *    public Type getField()        // Controlled read
 *    public void setField(Type)    // Controlled write with validation
 * 
 * 2. ACCESS MODIFIER HIERARCHY:
 *    private < default < protected < public
 *    (Most restrictive to most permissive)
 * 
 * 3. DEFENSIVE PROGRAMMING:
 *    - Validate all inputs in setters
 *    - Return copies of mutable objects
 *    - Use private methods for implementation details
 * 
 * 4. PROPERTY TYPES:
 *    - Read-Only: getter only
 *    - Write-Only: setter only  
 *    - Read-Write: both getter and setter
 *    - Computed: calculated in getter
 * 
 * 5. ENCAPSULATION BENEFITS:
 *    - Data integrity through validation
 *    - Implementation flexibility
 *    - Security through access control
 *    - Maintainability through clear interfaces
 * 
 * Remember: Encapsulation is about creating intelligent boundaries
 * that protect object integrity while providing controlled access
 * to object capabilities.
 */
