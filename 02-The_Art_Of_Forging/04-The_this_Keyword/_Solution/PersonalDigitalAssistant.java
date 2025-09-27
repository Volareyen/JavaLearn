/**
 * THE PATH REVEALED: Personal Digital Assistant System
 * 
 * A masterful demonstration of the `this` keyword in all its sacred forms.
 * This solution showcases complete mastery of Object self-awareness through:
 * - Constructor chaining with this()
 * - Field/parameter disambiguation with this.field
 * - Method chaining with return this
 * - Object communication by passing this
 * - Explicit self-reference for clarity
 * 
 * Created by: The Seeker (Master of Object Self-Awareness)
 */

public class PersonalDigitalAssistant {
    
    public static void main(String[] args) {
        System.out.println("=== PERSONAL DIGITAL ASSISTANT SYSTEM ===");
        System.out.println("Demonstrating mastery of the 'this' keyword\n");
        
        // DEMONSTRATION 1: Constructor Chaining Magic
        System.out.println("1. CONSTRUCTOR CHAINING DEMONSTRATION:");
        PersonalAssistant basic = new PersonalAssistant();
        PersonalAssistant withOwner = new PersonalAssistant("Alice");
        PersonalAssistant full = new PersonalAssistant("Bob", "ARIA", 85);
        
        basic.displayStatus();
        withOwner.displayStatus();
        full.displayStatus();
        
        // DEMONSTRATION 2: Method Chaining Elegance
        System.out.println("2. METHOD CHAINING DEMONSTRATION:");
        PersonalAssistant charlie = new PersonalAssistant()
            .setOwnerName("Charlie")
            .setAssistantName("ZARA")
            .setBatteryLevel(90)
            .activate()
            .addTask("Team meeting at 3 PM")
            .addTask("Review quarterly reports")
            .addTask("Buy groceries after work")
            .addAppointment("Doctor appointment - 10 AM Monday")
            .addAppointment("Lunch with Sarah - 12:30 PM Tuesday")
            .addContact("Dr. Smith: 555-0123")
            .addContact("Sarah Johnson: 555-0456")
            .addContact("Office Main: 555-0789");
        
        charlie.displayStatus();
        
        // DEMONSTRATION 3: Object Communication and Data Transfer
        System.out.println("3. OBJECT COMMUNICATION DEMONSTRATION:");
        PersonalAssistant sourceAssistant = new PersonalAssistant("David", "NOVA", 95)
            .activate()
            .addTask("Prepare presentation")
            .addTask("Call client")
            .addAppointment("Board meeting - 2 PM Friday")
            .addContact("Client Corp: 555-1111");
        
        PersonalAssistant targetAssistant = new PersonalAssistant("Emma", "LUNA", 88);
        
        System.out.println("Before data transfer:");
        targetAssistant.displayStatus();
        
        sourceAssistant.transferDataTo(targetAssistant);
        
        System.out.println("After data transfer:");
        targetAssistant.displayStatus();
        
        // DEMONSTRATION 4: Cloud Registration and Management
        System.out.println("4. CLOUD SERVICE INTEGRATION:");
        CloudService cloud = new CloudService();
        AssistantManager manager = new AssistantManager();
        
        charlie.registerWithCloud(cloud);
        sourceAssistant.registerWithCloud(cloud);
        targetAssistant.registerWithCloud(cloud);
        
        charlie.registerWithManager(manager);
        sourceAssistant.registerWithManager(manager);
        
        cloud.displayRegisteredAssistants();
        manager.displayManagedAssistants();
        
        // DEMONSTRATION 5: Intelligent Self-Analysis
        System.out.println("5. INTELLIGENT SELF-ANALYSIS:");
        charlie.suggestTaskPriority();
        charlie.findFreeTime();
        
        // DEMONSTRATION 6: Advanced Object Interactions
        System.out.println("6. ADVANCED OBJECT INTERACTIONS:");
        PersonalAssistant backup = charlie.createBackup();
        System.out.println("Created backup assistant:");
        backup.displayStatus();
        
        // Sync demonstration
        PersonalAssistant syncTarget = new PersonalAssistant("Frank", "SYNC", 92);
        syncTarget.addTask("Existing task");
        
        System.out.println("Before sync:");
        syncTarget.displayStatus();
        
        syncTarget.syncWith(charlie);
        
        System.out.println("After sync with Charlie:");
        syncTarget.displayStatus();
        
        // DEMONSTRATION 7: Capacity Analysis and Optimization
        System.out.println("7. CAPACITY MANAGEMENT:");
        charlie.compareCapacityWith(sourceAssistant);
        charlie.optimizeStorage().displayStatus();
        
        System.out.println("=== DEMONSTRATION COMPLETE ===");
        System.out.println("All aspects of 'this' keyword mastery showcased!");
    }
}

/**
 * PERSONAL ASSISTANT: The main class demonstrating 'this' keyword mastery
 */
class PersonalAssistant {
    // FIELDS: The assistant's state (all private to demonstrate proper encapsulation)
    private String ownerName;
    private String assistantName;
    private String[] tasks;
    private String[] appointments;
    private String[] contacts;
    private int taskCount;
    private int appointmentCount;
    private int contactCount;
    private boolean isActive;
    private int batteryLevel;
    
    // CONSTRUCTOR CHAINING: Demonstrating this() in multiple patterns
    
    /**
     * Default constructor - demonstrates this() chaining
     * Creates assistant with default values
     */
    public PersonalAssistant() {
        this("User", "Assistant", 100);  // Chain to full constructor
    }
    
    /**
     * Owner-only constructor - demonstrates this() chaining
     * @param ownerName The name of the assistant's owner
     */
    public PersonalAssistant(String ownerName) {
        this(ownerName, "Assistant", 100);  // Chain to full constructor
    }
    
    /**
     * Full constructor - the foundation that all others chain to
     * @param ownerName The owner's name
     * @param assistantName The assistant's name
     * @param batteryLevel Initial battery level
     */
    public PersonalAssistant(String ownerName, String assistantName, int batteryLevel) {
        // FIELD/PARAMETER DISAMBIGUATION: 'this' required for all matching names
        this.ownerName = ownerName;           // this.field = parameter
        this.assistantName = assistantName;   // this.field = parameter
        this.batteryLevel = batteryLevel;     // this.field = parameter
        
        // Initialize arrays and counters (using 'this' for clarity)
        this.tasks = new String[20];
        this.appointments = new String[15];
        this.contacts = new String[30];
        this.taskCount = 0;
        this.appointmentCount = 0;
        this.contactCount = 0;
        this.isActive = false;  // Start inactive
    }
    
    // SETTER METHODS: Demonstrating field/parameter disambiguation and method chaining
    
    /**
     * Set the owner name - demonstrates 'this' for disambiguation and chaining
     * @param ownerName The new owner name
     * @return this assistant for method chaining
     */
    public PersonalAssistant setOwnerName(String ownerName) {
        this.ownerName = ownerName;  // REQUIRED: Without 'this', would be parameter = parameter
        return this;                 // METHOD CHAINING: Return current object
    }
    
    /**
     * Set the assistant name - demonstrates 'this' for disambiguation and chaining
     * @param assistantName The new assistant name
     * @return this assistant for method chaining
     */
    public PersonalAssistant setAssistantName(String assistantName) {
        this.assistantName = assistantName;  // REQUIRED: Distinguish field from parameter
        return this;                         // METHOD CHAINING: Enable fluent interface
    }
    
    /**
     * Set battery level with validation - demonstrates 'this' and chaining
     * @param batteryLevel New battery level (0-100)
     * @return this assistant for method chaining
     */
    public PersonalAssistant setBatteryLevel(int batteryLevel) {
        // Parameter validation using 'this' for clarity
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;  // REQUIRED: Field = parameter
        } else {
            System.out.println("Invalid battery level for " + this.assistantName);
        }
        return this;  // METHOD CHAINING: Always return this for chaining
    }
    
    /**
     * Activate the assistant - demonstrates optional 'this' and chaining
     * @return this assistant for method chaining
     */
    public PersonalAssistant activate() {
        this.isActive = true;  // Optional 'this' but shows explicit field access
        System.out.println(this.assistantName + " is now active!");
        return this;           // METHOD CHAINING: Return current object
    }
    
    /**
     * Deactivate the assistant - demonstrates 'this' usage
     * @return this assistant for method chaining
     */
    public PersonalAssistant deactivate() {
        this.isActive = false;
        System.out.println(this.assistantName + " is now inactive.");
        return this;
    }
    
    // DATA MANAGEMENT: Demonstrating 'this' in array operations
    
    /**
     * Add a task - demonstrates 'this' in array management and chaining
     * @param task The task to add
     * @return this assistant for method chaining
     */
    public PersonalAssistant addTask(String task) {
        if (this.taskCount < this.tasks.length) {
            this.tasks[this.taskCount] = task;
            this.taskCount++;
            System.out.println("Task added to " + this.assistantName + ": " + task);
        } else {
            System.out.println("Task storage full for " + this.assistantName + "!");
        }
        return this;  // METHOD CHAINING: Enable multiple task additions
    }
    
    /**
     * Add an appointment - demonstrates 'this' in complex operations
     * @param appointment The appointment to add
     * @return this assistant for method chaining
     */
    public PersonalAssistant addAppointment(String appointment) {
        if (this.appointmentCount < this.appointments.length) {
            this.appointments[this.appointmentCount] = appointment;
            this.appointmentCount++;
            System.out.println("Appointment added to " + this.assistantName + ": " + appointment);
        } else {
            System.out.println("Appointment storage full for " + this.assistantName + "!");
        }
        return this;  // METHOD CHAINING: Allow appointment chaining
    }
    
    /**
     * Add a contact - demonstrates 'this' usage and chaining
     * @param contact The contact to add
     * @return this assistant for method chaining
     */
    public PersonalAssistant addContact(String contact) {
        if (this.contactCount < this.contacts.length) {
            this.contacts[this.contactCount] = contact;
            this.contactCount++;
            System.out.println("Contact added to " + this.assistantName + ": " + contact);
        } else {
            System.out.println("Contact storage full for " + this.assistantName + "!");
        }
        return this;  // METHOD CHAINING: Enable contact chaining
    }
    
    // DISPLAY METHOD: Comprehensive use of 'this' for clarity
    
    /**
     * Display complete status - demonstrates extensive 'this' usage for clarity
     */
    public void displayStatus() {
        System.out.println("=== " + this.assistantName + " Status ===");
        System.out.println("Owner: " + this.ownerName);
        System.out.println("Assistant: " + this.assistantName);
        System.out.println("Battery: " + this.batteryLevel + "%");
        System.out.println("Status: " + (this.isActive ? "Active" : "Inactive"));
        
        // Display tasks using 'this' for explicit field access
        System.out.println("Tasks (" + this.taskCount + "/" + this.tasks.length + "):");
        for (int i = 0; i < this.taskCount; i++) {
            System.out.println("  " + (i + 1) + ". " + this.tasks[i]);
        }
        
        // Display appointments
        System.out.println("Appointments (" + this.appointmentCount + "/" + this.appointments.length + "):");
        for (int i = 0; i < this.appointmentCount; i++) {
            System.out.println("  " + (i + 1) + ". " + this.appointments[i]);
        }
        
        // Display contacts
        System.out.println("Contacts (" + this.contactCount + "/" + this.contacts.length + "):");
        for (int i = 0; i < this.contactCount; i++) {
            System.out.println("  " + (i + 1) + ". " + this.contacts[i]);
        }
        System.out.println();
    }
    
    // OBJECT COMMUNICATION: Demonstrating passing 'this' and receiving objects
    
    /**
     * Transfer all data to another assistant - demonstrates 'this' in object communication
     * @param otherAssistant The target assistant to receive data
     */
    public void transferDataTo(PersonalAssistant otherAssistant) {
        System.out.println(this.assistantName + " transferring data to " + otherAssistant.assistantName);
        
        // Transfer tasks from this assistant to other assistant
        for (int i = 0; i < this.taskCount; i++) {
            otherAssistant.receiveTask(this.tasks[i], this);  // Pass 'this' as source
        }
        
        // Transfer appointments
        for (int i = 0; i < this.appointmentCount; i++) {
            otherAssistant.receiveAppointment(this.appointments[i], this);
        }
        
        // Transfer contacts
        for (int i = 0; i < this.contactCount; i++) {
            otherAssistant.receiveContact(this.contacts[i], this);
        }
        
        System.out.println("Data transfer from " + this.assistantName + " completed!");
    }
    
    /**
     * Receive a task from another assistant - demonstrates receiving 'this' reference
     * @param task The task to receive
     * @param sourceAssistant The assistant sending the task (received as 'this')
     */
    public void receiveTask(String task, PersonalAssistant sourceAssistant) {
        this.addTask(task + " (from " + sourceAssistant.assistantName + ")");
    }
    
    /**
     * Receive an appointment from another assistant
     * @param appointment The appointment to receive
     * @param sourceAssistant The source assistant
     */
    public void receiveAppointment(String appointment, PersonalAssistant sourceAssistant) {
        this.addAppointment(appointment + " (from " + sourceAssistant.assistantName + ")");
    }
    
    /**
     * Receive a contact from another assistant
     * @param contact The contact to receive
     * @param sourceAssistant The source assistant
     */
    public void receiveContact(String contact, PersonalAssistant sourceAssistant) {
        this.addContact(contact + " (from " + sourceAssistant.assistantName + ")");
    }
    
    /**
     * Sync with another assistant - demonstrates bidirectional 'this' usage
     * @param otherAssistant The assistant to sync with
     */
    public void syncWith(PersonalAssistant otherAssistant) {
        System.out.println(this.assistantName + " syncing with " + otherAssistant.assistantName);
        
        // Receive data from other assistant
        otherAssistant.transferDataTo(this);  // Other assistant passes its 'this' to us
        
        System.out.println("Sync completed between " + this.assistantName + 
                          " and " + otherAssistant.assistantName);
    }
    
    /**
     * Register with cloud service - demonstrates passing 'this' to external objects
     * @param cloud The cloud service to register with
     */
    public void registerWithCloud(CloudService cloud) {
        cloud.registerAssistant(this);  // PASSING SELF: Give cloud service this assistant
    }
    
    /**
     * Register with assistant manager - demonstrates 'this' in management systems
     * @param manager The manager to register with
     */
    public void registerWithManager(AssistantManager manager) {
        manager.addAssistant(this);  // Pass this assistant to manager
    }
    
    // COMPARISON AND ANALYSIS: Demonstrating 'this' in object comparison
    
    /**
     * Compare capacity with another assistant - demonstrates 'this' in comparisons
     * @param otherAssistant The assistant to compare with
     */
    public void compareCapacityWith(PersonalAssistant otherAssistant) {
        System.out.println("Capacity comparison between " + this.assistantName + 
                          " and " + otherAssistant.assistantName + ":");
        
        int thisUsage = this.taskCount + this.appointmentCount + this.contactCount;
        int otherUsage = otherAssistant.taskCount + otherAssistant.appointmentCount + 
                        otherAssistant.contactCount;
        
        System.out.println("  " + this.assistantName + " usage: " + thisUsage + " items");
        System.out.println("  " + otherAssistant.assistantName + " usage: " + otherUsage + " items");
        
        if (thisUsage > otherUsage) {
            System.out.println("  " + this.assistantName + " has more data stored.");
        } else if (thisUsage < otherUsage) {
            System.out.println("  " + otherAssistant.assistantName + " has more data stored.");
        } else {
            System.out.println("  Both assistants have equal data storage.");
        }
    }
    
    /**
     * Create a backup copy - demonstrates returning new object based on 'this'
     * @return A new PersonalAssistant with identical data
     */
    public PersonalAssistant createBackup() {
        System.out.println("Creating backup of " + this.assistantName + "...");
        
        // Create new assistant with this assistant's data
        PersonalAssistant backup = new PersonalAssistant(
            this.ownerName + " (Backup)", 
            this.assistantName + "_Backup", 
            this.batteryLevel
        );
        
        // Copy all data from this assistant to backup
        for (int i = 0; i < this.taskCount; i++) {
            backup.addTask(this.tasks[i]);
        }
        
        for (int i = 0; i < this.appointmentCount; i++) {
            backup.addAppointment(this.appointments[i]);
        }
        
        for (int i = 0; i < this.contactCount; i++) {
            backup.addContact(this.contacts[i]);
        }
        
        if (this.isActive) {
            backup.activate();
        }
        
        return backup;  // Return the backup assistant
    }
    
    // INTELLIGENT ANALYSIS: Advanced 'this' usage in self-analysis
    
    /**
     * Suggest task priority - demonstrates 'this' in intelligent self-analysis
     */
    public void suggestTaskPriority() {
        System.out.println(this.assistantName + " analyzing task priorities:");
        
        if (this.taskCount == 0) {
            System.out.println("  No tasks to prioritize.");
            return;
        }
        
        System.out.println("  High Priority Tasks:");
        for (int i = 0; i < this.taskCount; i++) {
            if (this.tasks[i].toLowerCase().contains("meeting") || 
                this.tasks[i].toLowerCase().contains("urgent")) {
                System.out.println("    - " + this.tasks[i]);
            }
        }
        
        System.out.println("  Medium Priority Tasks:");
        for (int i = 0; i < this.taskCount; i++) {
            if (!this.tasks[i].toLowerCase().contains("meeting") && 
                !this.tasks[i].toLowerCase().contains("urgent")) {
                System.out.println("    - " + this.tasks[i]);
            }
        }
    }
    
    /**
     * Find free time - demonstrates 'this' in schedule analysis
     */
    public void findFreeTime() {
        System.out.println(this.assistantName + " analyzing schedule for free time:");
        
        if (this.appointmentCount < 3) {
            System.out.println("  Schedule looks light - plenty of free time available!");
        } else if (this.appointmentCount < 8) {
            System.out.println("  Moderate schedule - some free time available.");
        } else {
            System.out.println("  Busy schedule - limited free time available.");
        }
        
        System.out.println("  Current appointments: " + this.appointmentCount);
        System.out.println("  Available appointment slots: " + 
                          (this.appointments.length - this.appointmentCount));
    }
    
    /**
     * Optimize storage - demonstrates 'this' in self-optimization with chaining
     * @return this assistant for method chaining
     */
    public PersonalAssistant optimizeStorage() {
        System.out.println(this.assistantName + " optimizing storage...");
        
        // Simple optimization demonstration - in real implementation,
        // would have more sophisticated duplicate detection and removal
        System.out.println("  Analyzing current storage usage:");
        System.out.println("    Tasks: " + this.taskCount + "/" + this.tasks.length);
        System.out.println("    Appointments: " + this.appointmentCount + "/" + this.appointments.length);
        System.out.println("    Contacts: " + this.contactCount + "/" + this.contacts.length);
        
        System.out.println("  Storage optimization completed.");
        
        return this;  // Return this for method chaining
    }
    
    // GETTER METHODS: Demonstrating optional but clear 'this' usage
    
    public String getOwnerName() {
        return this.ownerName;  // Optional 'this' but shows explicit field access
    }
    
    public String getAssistantName() {
        return this.assistantName;  // Clear self-reference
    }
    
    public int getBatteryLevel() {
        return this.batteryLevel;  // Explicit field access
    }
    
    public boolean isActive() {
        return this.isActive;  // Boolean field access
    }
    
    public int getTaskCount() {
        return this.taskCount;  // Explicit count access
    }
    
    public int getAppointmentCount() {
        return this.appointmentCount;
    }
    
    public int getContactCount() {
        return this.contactCount;
    }
}

/**
 * CLOUD SERVICE: Demonstrates receiving 'this' from PersonalAssistant objects
 */
class CloudService {
    private PersonalAssistant[] registeredAssistants;
    private int assistantCount;
    
    public CloudService() {
        this.registeredAssistants = new PersonalAssistant[10];
        this.assistantCount = 0;
    }
    
    /**
     * Register an assistant - receives 'this' from PersonalAssistant objects
     * @param assistant The assistant to register (passed as 'this')
     */
    public void registerAssistant(PersonalAssistant assistant) {
        if (this.assistantCount < this.registeredAssistants.length) {
            this.registeredAssistants[this.assistantCount] = assistant;
            this.assistantCount++;
            System.out.println("Cloud Service: Registered " + assistant.getAssistantName() + 
                              " for " + assistant.getOwnerName());
        } else {
            System.out.println("Cloud Service: Registration full!");
        }
    }
    
    public void displayRegisteredAssistants() {
        System.out.println("Cloud Service Registered Assistants:");
        for (int i = 0; i < this.assistantCount; i++) {
            System.out.println("  " + (i + 1) + ". " + 
                this.registeredAssistants[i].getAssistantName() + 
                " (Owner: " + this.registeredAssistants[i].getOwnerName() + ")");
        }
        System.out.println();
    }
}

/**
 * ASSISTANT MANAGER: Demonstrates managing multiple 'this' references
 */
class AssistantManager {
    private PersonalAssistant[] managedAssistants;
    private int assistantCount;
    
    public AssistantManager() {
        this.managedAssistants = new PersonalAssistant[15];
        this.assistantCount = 0;
    }
    
    /**
     * Add an assistant to management - receives 'this' from PersonalAssistant
     * @param assistant The assistant to manage
     */
    public void addAssistant(PersonalAssistant assistant) {
        if (this.assistantCount < this.managedAssistants.length) {
            this.managedAssistants[this.assistantCount] = assistant;
            this.assistantCount++;
            System.out.println("Manager: Now managing " + assistant.getAssistantName());
        }
    }
    
    public void displayManagedAssistants() {
        System.out.println("Assistant Manager - Managed Assistants:");
        for (int i = 0; i < this.assistantCount; i++) {
            PersonalAssistant assistant = this.managedAssistants[i];
            System.out.println("  " + (i + 1) + ". " + assistant.getAssistantName() + 
                              " - Battery: " + assistant.getBatteryLevel() + "% - " +
                              "Tasks: " + assistant.getTaskCount());
        }
        System.out.println();
    }
}

/**
 * MASTERY DEMONSTRATED:
 * 
 * This solution showcases complete mastery of the 'this' keyword through:
 * 
 * 1. CONSTRUCTOR CHAINING:
 *    - Three constructor patterns all using this() properly
 *    - Eliminates code duplication and provides flexibility
 * 
 * 2. FIELD/PARAMETER DISAMBIGUATION:
 *    - All setter methods properly use this.field = parameter
 *    - Prevents the silent bug of parameter = parameter
 * 
 * 3. METHOD CHAINING:
 *    - All modifier methods return this for elegant chaining
 *    - Enables fluent interface design patterns
 * 
 * 4. OBJECT COMMUNICATION:
 *    - Objects pass themselves using 'this' to other objects
 *    - Complex data transfer and synchronization systems
 * 
 * 5. EXPLICIT SELF-REFERENCE:
 *    - Clear use of this.field even when optional
 *    - Improves code readability and intent
 * 
 * 6. INTELLIGENT SELF-ANALYSIS:
 *    - Objects analyze their own state using 'this'
 *    - Self-optimization and capacity management
 * 
 * The PersonalAssistant is not just a data container - it's a truly
 * self-aware digital entity that knows itself, can communicate its
 * identity, and can interact intelligently with other objects and
 * external systems.
 * 
 * This demonstrates the profound transformation from procedural
 * programming to Object-Oriented mastery through the power of 'this'.
 */
