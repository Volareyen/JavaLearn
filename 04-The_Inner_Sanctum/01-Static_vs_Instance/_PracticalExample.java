/**
 * The Living Manuscript: University Registration System
 * 
 * A comprehensive real-world demonstration of static vs instance members
 * through a university student registration system.
 * 
 * This system showcases:
 * - Static members for university-wide information and utilities
 * - Instance members for individual student data
 * - Practical use cases for both paradigms
 * - Complex interactions between static and instance contexts
 */
public class _PracticalExample {
    
    // ═══════════════════════════════════════════════════════════════
    // STATIC MEMBERS - UNIVERSITY-WIDE INFORMATION
    // ═══════════════════════════════════════════════════════════════
    
    // University constants - shared by all students
    public static final String UNIVERSITY_NAME = "Sacred Scrolls University";
    public static final String UNIVERSITY_MOTTO = "Wisdom Through Code";
    public static final int CURRENT_YEAR = 2024;
    public static final double CREDIT_COST = 450.50;  // Cost per credit hour
    
    // University-wide tracking variables
    private static int totalStudentsRegistered = 0;
    private static int nextStudentId = 1000;  // Starting ID for students
    private static double totalTuitionCollected = 0.0;
    private static String currentSemester = "Fall 2024";
    
    // University-wide statistics
    private static int totalCreditsRegistered = 0;
    private static double averageGPA = 0.0;
    private static double totalGPAPoints = 0.0;
    
    // ═══════════════════════════════════════════════════════════════
    // INSTANCE MEMBERS - INDIVIDUAL STUDENT INFORMATION
    // ═══════════════════════════════════════════════════════════════
    
    // Personal student information
    private final int studentId;           // Unique ID (assigned once, never changes)
    private String firstName;
    private String lastName;
    private String email;
    private String major;
    
    // Academic information
    private int creditHours;
    private double gpa;
    private int graduationYear;
    private boolean isFullTime;
    private boolean isRegistered;
    
    // Financial information
    private double tuitionOwed;
    private double tuitionPaid;
    private boolean hasScholarship;
    private double scholarshipAmount;
    
    // ═══════════════════════════════════════════════════════════════
    // CONSTRUCTOR - CREATING A NEW STUDENT
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Creates a new student and automatically assigns them a unique ID
     * Updates university-wide statistics
     */
    public _PracticalExample(String firstName, String lastName, String email, String major) {
        // Assign unique student ID using static counter
        this.studentId = nextStudentId++;
        
        // Set personal information
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.major = major;
        
        // Initialize academic status
        this.creditHours = 0;
        this.gpa = 0.0;
        this.graduationYear = CURRENT_YEAR + 4;  // Default 4-year program
        this.isFullTime = false;
        this.isRegistered = false;
        
        // Initialize financial status
        this.tuitionOwed = 0.0;
        this.tuitionPaid = 0.0;
        this.hasScholarship = false;
        this.scholarshipAmount = 0.0;
        
        // Update university-wide statistics
        totalStudentsRegistered++;
        
        System.out.println("🎓 New student registered: " + getFullName() + 
                          " (ID: " + studentId + ")");
    }
    
    // ═══════════════════════════════════════════════════════════════
    // STATIC METHODS - UNIVERSITY-WIDE OPERATIONS
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Displays comprehensive university statistics
     */
    public static void displayUniversityStatistics() {
        System.out.println("\n" + "═".repeat(50));
        System.out.println("📊 " + UNIVERSITY_NAME.toUpperCase() + " STATISTICS");
        System.out.println("═".repeat(50));
        System.out.println("Motto: " + UNIVERSITY_MOTTO);
        System.out.println("Current Semester: " + currentSemester);
        System.out.println("Academic Year: " + CURRENT_YEAR);
        System.out.println();
        System.out.println("📈 Enrollment Statistics:");
        System.out.println("  Total Students Registered: " + totalStudentsRegistered);
        System.out.println("  Next Available Student ID: " + nextStudentId);
        System.out.println("  Total Credit Hours Registered: " + totalCreditsRegistered);
        System.out.println();
        System.out.println("💰 Financial Statistics:");
        System.out.println("  Credit Hour Cost: $" + CREDIT_COST);
        System.out.println("  Total Tuition Collected: $" + String.format("%.2f", totalTuitionCollected));
        System.out.println();
        System.out.println("🎯 Academic Statistics:");
        if (totalStudentsRegistered > 0) {
            averageGPA = totalGPAPoints / totalStudentsRegistered;
            System.out.println("  University Average GPA: " + String.format("%.2f", averageGPA));
        } else {
            System.out.println("  University Average GPA: No students registered yet");
        }
        System.out.println("═".repeat(50));
    }
    
    /**
     * Calculates tuition cost for a given number of credit hours
     */
    public static double calculateTuitionCost(int creditHours) {
        return creditHours * CREDIT_COST;
    }
    
    /**
     * Determines if a student is full-time based on credit hours
     */
    public static boolean isFullTimeStudent(int creditHours) {
        return creditHours >= 12;  // 12+ credits = full-time
    }
    
    /**
     * Updates the current semester for all students
     */
    public static void setCurrentSemester(String semester) {
        currentSemester = semester;
        System.out.println("🗓️ Semester updated to: " + semester);
    }
    
    /**
     * Gets the current semester
     */
    public static String getCurrentSemester() {
        return currentSemester;
    }
    
    /**
     * University utility method to validate email format
     */
    public static boolean isValidUniversityEmail(String email) {
        return email != null && email.contains("@") && 
               (email.endsWith(".edu") || email.endsWith("@ssu.edu"));
    }
    
    /**
     * Factory method to create honor student with scholarship
     */
    public static _PracticalExample createHonorStudent(String firstName, String lastName, 
                                                      String email, String major, 
                                                      double scholarshipAmount) {
        _PracticalExample student = new _PracticalExample(firstName, lastName, email, major);
        student.hasScholarship = true;
        student.scholarshipAmount = scholarshipAmount;
        System.out.println("🌟 Honor student created with $" + 
                          String.format("%.2f", scholarshipAmount) + " scholarship!");
        return student;
    }
    
    // ═══════════════════════════════════════════════════════════════
    // INSTANCE METHODS - INDIVIDUAL STUDENT OPERATIONS
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Registers the student for a specific number of credit hours
     */
    public void registerForCredits(int credits) {
        if (isRegistered) {
            System.out.println("⚠️ " + getFullName() + " is already registered!");
            return;
        }
        
        this.creditHours = credits;
        this.isFullTime = isFullTimeStudent(credits);  // Using static method
        this.tuitionOwed = calculateTuitionCost(credits);  // Using static method
        this.isRegistered = true;
        
        // Apply scholarship discount
        if (hasScholarship) {
            tuitionOwed = Math.max(0, tuitionOwed - scholarshipAmount);
        }
        
        // Update university-wide statistics (static variables)
        totalCreditsRegistered += credits;
        
        System.out.println("✅ " + getFullName() + " registered for " + credits + 
                          " credit hours (" + (isFullTime ? "Full-time" : "Part-time") + ")");
        System.out.println("   Tuition owed: $" + String.format("%.2f", tuitionOwed));
    }
    
    /**
     * Processes tuition payment
     */
    public void payTuition(double amount) {
        if (!isRegistered) {
            System.out.println("❌ " + getFullName() + " must register first before paying tuition!");
            return;
        }
        
        if (amount <= 0) {
            System.out.println("❌ Payment amount must be positive!");
            return;
        }
        
        tuitionPaid += amount;
        double remaining = tuitionOwed - tuitionPaid;
        
        // Update university-wide collection (static variable)
        totalTuitionCollected += amount;
        
        System.out.println("💳 " + getFullName() + " paid $" + String.format("%.2f", amount));
        
        if (remaining <= 0) {
            System.out.println("✅ Tuition fully paid! Overpayment: $" + 
                              String.format("%.2f", Math.abs(remaining)));
        } else {
            System.out.println("   Remaining balance: $" + String.format("%.2f", remaining));
        }
    }
    
    /**
     * Updates the student's GPA and university statistics
     */
    public void updateGPA(double newGPA) {
        if (newGPA < 0.0 || newGPA > 4.0) {
            System.out.println("❌ Invalid GPA! Must be between 0.0 and 4.0");
            return;
        }
        
        // Update university totals for average calculation
        totalGPAPoints = totalGPAPoints - this.gpa + newGPA;
        this.gpa = newGPA;
        
        System.out.println("📊 " + getFullName() + "'s GPA updated to: " + 
                          String.format("%.2f", gpa));
    }
    
    /**
     * Displays comprehensive student information
     */
    public void displayStudentProfile() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("👤 STUDENT PROFILE");
        System.out.println("-".repeat(40));
        System.out.println("Name: " + getFullName());
        System.out.println("Student ID: " + studentId);
        System.out.println("Email: " + email);
        System.out.println("Major: " + major);
        System.out.println();
        System.out.println("📚 Academic Information:");
        System.out.println("  Registration Status: " + (isRegistered ? "Registered" : "Not Registered"));
        System.out.println("  Credit Hours: " + creditHours);
        System.out.println("  Status: " + (isFullTime ? "Full-time" : "Part-time"));
        System.out.println("  GPA: " + String.format("%.2f", gpa));
        System.out.println("  Expected Graduation: " + graduationYear);
        System.out.println();
        System.out.println("💰 Financial Information:");
        System.out.println("  Tuition Owed: $" + String.format("%.2f", tuitionOwed));
        System.out.println("  Tuition Paid: $" + String.format("%.2f", tuitionPaid));
        System.out.println("  Balance: $" + String.format("%.2f", tuitionOwed - tuitionPaid));
        if (hasScholarship) {
            System.out.println("  🌟 Scholarship: $" + String.format("%.2f", scholarshipAmount));
        }
        System.out.println();
        System.out.println("🏫 University Context:");
        System.out.println("  University: " + UNIVERSITY_NAME);  // Static constant
        System.out.println("  Current Semester: " + currentSemester);  // Static variable
        System.out.println("  Total Students: " + totalStudentsRegistered);  // Static variable
        System.out.println("-".repeat(40));
    }
    
    /**
     * Helper method to get full name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Check if tuition is fully paid
     */
    public boolean isTuitionPaid() {
        return tuitionPaid >= tuitionOwed;
    }
    
    // ═══════════════════════════════════════════════════════════════
    // GETTERS AND SETTERS (Instance methods)
    // ═══════════════════════════════════════════════════════════════
    
    public int getStudentId() { return studentId; }  // No setter - ID is permanent
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    
    public int getCreditHours() { return creditHours; }
    public double getGPA() { return gpa; }
    public boolean isFullTime() { return isFullTime; }
    public boolean isRegistered() { return isRegistered; }
    
    // ═══════════════════════════════════════════════════════════════
    // DEMONSTRATION MAIN METHOD
    // ═══════════════════════════════════════════════════════════════
    
    public static void main(String[] args) {
        System.out.println("🎓 UNIVERSITY REGISTRATION SYSTEM DEMONSTRATION 🎓\n");
        
        // Display initial university statistics (all static)
        displayUniversityStatistics();
        
        System.out.println("\n🏗️ CREATING STUDENTS...\n");
        
        // Create regular students (using constructor)
        _PracticalExample alice = new _PracticalExample("Alice", "Johnson", 
                                                       "alice.j@ssu.edu", "Computer Science");
        _PracticalExample bob = new _PracticalExample("Bob", "Smith", 
                                                     "bob.s@ssu.edu", "Mathematics");
        
        // Create honor student with scholarship (using static factory method)
        _PracticalExample charlie = createHonorStudent("Charlie", "Brown", 
                                                      "charlie.b@ssu.edu", "Physics", 2000.0);
        
        System.out.println("\n📝 STUDENT REGISTRATIONS...\n");
        
        // Register students for different credit loads
        alice.registerForCredits(15);    // Full-time
        bob.registerForCredits(9);       // Part-time
        charlie.registerForCredits(18);  // Full-time with heavy load
        
        System.out.println("\n💳 TUITION PAYMENTS...\n");
        
        // Process payments
        alice.payTuition(3000.0);    // Partial payment
        bob.payTuition(4054.50);     // Full payment
        charlie.payTuition(10000.0); // Overpayment (with scholarship discount)
        
        System.out.println("\n📊 GPA UPDATES...\n");
        
        // Update GPAs
        alice.updateGPA(3.7);
        bob.updateGPA(3.2);
        charlie.updateGPA(3.95);  // Honor student with high GPA
        
        System.out.println("\n👥 INDIVIDUAL STUDENT PROFILES...\n");
        
        // Display individual profiles
        alice.displayStudentProfile();
        bob.displayStudentProfile();
        charlie.displayStudentProfile();
        
        System.out.println("\n🗓️ SEMESTER CHANGE...\n");
        
        // Change semester (affects all students through static variable)
        setCurrentSemester("Spring 2025");
        
        System.out.println("\n📈 FINAL UNIVERSITY STATISTICS...\n");
        
        // Display final university statistics
        displayUniversityStatistics();
        
        System.out.println("\n🔍 DEMONSTRATING STATIC UTILITY METHODS...\n");
        
        // Demonstrate static utility methods
        System.out.println("Cost for 12 credits: $" + 
                          String.format("%.2f", calculateTuitionCost(12)));
        System.out.println("Is 8 credits full-time? " + isFullTimeStudent(8));
        System.out.println("Is 15 credits full-time? " + isFullTimeStudent(15));
        System.out.println("Valid university email 'test@ssu.edu'? " + 
                          isValidUniversityEmail("test@ssu.edu"));
        System.out.println("Valid university email 'test@gmail.com'? " + 
                          isValidUniversityEmail("test@gmail.com"));
        
        System.out.println("\n✨ STATIC VS INSTANCE DEMONSTRATION COMPLETE! ✨");
        System.out.println("Static members managed university-wide data and utilities.");
        System.out.println("Instance members managed individual student information.");
        System.out.println("Both worked together to create a comprehensive system!");
    }
}
