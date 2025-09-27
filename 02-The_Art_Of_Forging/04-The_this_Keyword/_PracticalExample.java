/**
 * THE LIVING MANUSCRIPT: Smart Phone Management System
 * 
 * A comprehensive demonstration of the `this` keyword in a realistic,
 * practical scenario. This system manages smart phones with various
 * features, showcasing all the sacred uses of `this` in real-world context.
 * 
 * This example demonstrates:
 * - Field/parameter disambiguation
 * - Method chaining for fluent interfaces
 * - Object self-reference and communication
 * - Constructor chaining for flexible initialization
 */

public class _PracticalExample {
    public static void main(String[] args) {
        System.out.println("=== SMART PHONE MANAGEMENT SYSTEM ===");
        System.out.println("Demonstrating the power of 'this' keyword\n");
        
        // Demonstration 1: Constructor chaining and field initialization
        System.out.println("1. Creating phones with different constructors:");
        SmartPhone basicPhone = new SmartPhone();
        SmartPhone namedPhone = new SmartPhone("iPhone 15");
        SmartPhone fullPhone = new SmartPhone("Galaxy S24", 256, 85);
        
        basicPhone.displayInfo();
        namedPhone.displayInfo();
        fullPhone.displayInfo();
        
        // Demonstration 2: Method chaining for fluent configuration
        System.out.println("\n2. Configuring phone using method chaining:");
        SmartPhone chainedPhone = new SmartPhone()
            .setModel("Pixel 8 Pro")
            .setStorage(512)
            .setBattery(90)
            .installApp("Camera")
            .installApp("Maps")
            .activate();
        
        chainedPhone.displayInfo();
        
        // Demonstration 3: Phone interaction and communication
        System.out.println("\n3. Phone interaction and data transfer:");
        SmartPhone sourcePhone = new SmartPhone("iPhone 15", 128, 75)
            .installApp("Photos")
            .installApp("Messages");
            
        SmartPhone targetPhone = new SmartPhone("Galaxy S24", 256, 80);
        
        // Phone transfers its data to another phone
        sourcePhone.transferDataTo(targetPhone);
        
        System.out.println("After data transfer:");
        targetPhone.displayInfo();
        
        // Demonstration 4: Phone management system
        System.out.println("\n4. Phone registration with management system:");
        PhoneManager manager = new PhoneManager();
        
        fullPhone.registerWith(manager);
        chainedPhone.registerWith(manager);
        sourcePhone.registerWith(manager);
        
        manager.displayRegisteredPhones();
    }
}

/**
 * SMART PHONE CLASS: Demonstrates all aspects of 'this' keyword
 */
class SmartPhone {
    // Fields representing phone state
    private String model;
    private int storageGB;
    private int batteryPercent;
    private boolean isActive;
    private String[] installedApps;
    private int appCount;
    
    // CONSTRUCTOR CHAINING: Demonstrating this() calls
    
    // Default constructor - chains to parameterized constructor
    public SmartPhone() {
        this("Unknown Model", 64, 100);  // this() must be first statement
    }
    
    // Constructor with model only
    public SmartPhone(String model) {
        this(model, 64, 100);  // Chain to full constructor
    }
    
    // Full parameterized constructor
    public SmartPhone(String model, int storageGB, int batteryPercent) {
        // FIELD/PARAMETER DISAMBIGUATION: 'this' required here
        this.model = model;                    // this.field = parameter
        this.storageGB = storageGB;            // this.field = parameter
        this.batteryPercent = batteryPercent;  // this.field = parameter
        this.isActive = false;                 // No conflict, but 'this' for clarity
        this.installedApps = new String[10];   // Initialize app array
        this.appCount = 0;                     // Start with no apps
    }
    
    // SETTER METHODS: Demonstrating field/parameter disambiguation
    
    public SmartPhone setModel(String model) {
        this.model = model;  // REQUIRED: Without 'this', would be parameter = parameter
        return this;         // METHOD CHAINING: Return current object
    }
    
    public SmartPhone setStorage(int storageGB) {
        this.storageGB = storageGB;  // REQUIRED: Distinguish field from parameter
        return this;                 // METHOD CHAINING: Enable fluent interface
    }
    
    public SmartPhone setBattery(int batteryPercent) {
        this.batteryPercent = batteryPercent;  // REQUIRED: Field = parameter
        return this;                           // METHOD CHAINING: Return self
    }
    
    public SmartPhone activate() {
        this.isActive = true;  // Optional 'this' but shows explicit field access
        return this;           // METHOD CHAINING: Return current object
    }
    
    // APP MANAGEMENT: Demonstrating 'this' in complex operations
    
    public SmartPhone installApp(String appName) {
        if (this.appCount < this.installedApps.length) {
            this.installedApps[this.appCount] = appName;
            this.appCount++;
            System.out.println("Installed " + appName + " on " + this.model);
        } else {
            System.out.println("Storage full on " + this.model + "!");
        }
        return this;  // METHOD CHAINING: Allow multiple installations
    }
    
    // OBJECT COMMUNICATION: Passing 'this' to other objects
    
    public void transferDataTo(SmartPhone otherPhone) {
        System.out.println(this.model + " transferring data to " + otherPhone.model);
        
        // Transfer all installed apps to the other phone
        for (int i = 0; i < this.appCount; i++) {
            otherPhone.receiveApp(this.installedApps[i], this);  // Pass 'this' as source
        }
    }
    
    public void receiveApp(String appName, SmartPhone sourcePhone) {
        this.installApp(appName);  // Install the app on this phone
        System.out.println("  Received " + appName + " from " + sourcePhone.model);
    }
    
    public void registerWith(PhoneManager manager) {
        manager.registerPhone(this);  // PASSING SELF: Give manager this phone object
    }
    
    // COMPARISON: Demonstrating object comparison with 'this'
    
    public boolean isSameModelAs(SmartPhone otherPhone) {
        return this.model.equals(otherPhone.model);  // Compare this phone with other
    }
    
    public SmartPhone getBetterPhone(SmartPhone otherPhone) {
        // Compare this phone's specs with another phone
        if (this.storageGB > otherPhone.storageGB && 
            this.batteryPercent > otherPhone.batteryPercent) {
            return this;  // This phone is better
        }
        return otherPhone;  // Other phone is better
    }
    
    // GETTER METHODS: Demonstrating optional but clear 'this' usage
    
    public String getModel() {
        return this.model;  // Optional 'this' but shows explicit field access
    }
    
    public int getStorageGB() {
        return this.storageGB;  // Explicit field access
    }
    
    public int getBatteryPercent() {
        return this.batteryPercent;  // Clear self-reference
    }
    
    public boolean isActive() {
        return this.isActive;  // Explicit boolean field access
    }
    
    // DISPLAY METHOD: Comprehensive use of 'this' for clarity
    
    public void displayInfo() {
        System.out.println("Phone: " + this.model);
        System.out.println("  Storage: " + this.storageGB + "GB");
        System.out.println("  Battery: " + this.batteryPercent + "%");
        System.out.println("  Status: " + (this.isActive ? "Active" : "Inactive"));
        System.out.print("  Apps: ");
        
        if (this.appCount == 0) {
            System.out.println("None");
        } else {
            for (int i = 0; i < this.appCount; i++) {
                System.out.print(this.installedApps[i]);
                if (i < this.appCount - 1) System.out.print(", ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // ADVANCED: Method that uses 'this' in multiple ways
    
    public SmartPhone upgradeAndTransfer(SmartPhone newPhone) {
        System.out.println("Upgrading from " + this.model + " to " + newPhone.model);
        
        // Transfer all data from this phone to new phone
        this.transferDataTo(newPhone);  // 'this' calls method on current object
        
        // Activate the new phone and return it
        return newPhone.activate();  // Return the upgraded phone for chaining
    }
}

/**
 * PHONE MANAGER CLASS: Demonstrates receiving 'this' from other objects
 */
class PhoneManager {
    private SmartPhone[] registeredPhones;
    private int phoneCount;
    
    public PhoneManager() {
        this.registeredPhones = new SmartPhone[10];
        this.phoneCount = 0;
    }
    
    public void registerPhone(SmartPhone phone) {
        if (this.phoneCount < this.registeredPhones.length) {
            this.registeredPhones[this.phoneCount] = phone;
            this.phoneCount++;
            System.out.println("Registered phone: " + phone.getModel());
        }
    }
    
    public void displayRegisteredPhones() {
        System.out.println("\nRegistered Phones in System:");
        for (int i = 0; i < this.phoneCount; i++) {
            System.out.println("  " + (i + 1) + ". " + 
                this.registeredPhones[i].getModel());
        }
    }
}

/**
 * KEY PRACTICAL INSIGHTS:
 * 
 * 1. FIELD/PARAMETER DISAMBIGUATION:
 *    - Essential when parameter names match field names
 *    - Prevents the silent bug of parameter = parameter
 * 
 * 2. METHOD CHAINING:
 *    - 'return this;' enables fluent, readable interfaces
 *    - Allows multiple operations in a single statement
 * 
 * 3. OBJECT COMMUNICATION:
 *    - Objects pass themselves to other objects using 'this'
 *    - Enables complex interactions and data sharing
 * 
 * 4. CONSTRUCTOR CHAINING:
 *    - 'this()' eliminates code duplication in constructors
 *    - Creates flexible initialization patterns
 * 
 * 5. EXPLICIT CLARITY:
 *    - Even when optional, 'this' makes code more readable
 *    - Shows clear intent: "I'm accessing MY field/method"
 * 
 * The 'this' keyword transforms objects from passive data containers
 * into active, self-aware entities that can interact intelligently
 * with their environment and other objects.
 */
