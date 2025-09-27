/**
 * THE PATH REVEALED: University Management System
 * 
 * A masterful demonstration of Inheritance - The Second Pillar of OOP Wisdom.
 * This solution showcases complete mastery of inheritance hierarchies, method
 * overriding, constructor chaining, protected access, and the sacred "IS-A"
 * relationships through a comprehensive university management system.
 * 
 * This system demonstrates:
 * - Multi-level inheritance hierarchies with logical IS-A relationships
 * - Proper constructor chaining with super() calls throughout the hierarchy
 * - Method overriding for specialized behavior while honoring parent functionality
 * - Protected access for family sharing of common functionality
 * - Abstract methods for enforced specialization contracts
 * - Polymorphic usage preparation through consistent interfaces
 * - Real-world university operations using inheritance effectively
 * 
 * Created by: The Seeker (Master of the Bloodline)
 */

public class UniversityManagementSystem {
    
    public static void main(String[] args) {
        System.out.println("=== UNIVERSITY MANAGEMENT SYSTEM ===");
        System.out.println("Demonstrating Inheritance - The Second Pillar of OOP Wisdom\n");
        
        // DEMONSTRATION 1: Inheritance Hierarchy Creation
        System.out.println("1. INHERITANCE HIERARCHY CREATION:");
        
        // Create university system
        UniversitySystem university = new UniversitySystem("Tech University");
        
        // Create students with inheritance hierarchy
        UndergraduateStudent undergrad = new UndergraduateStudent(
            "Alice Johnson", 19, "alice@tech.edu", "Computer Science", "Sophomore", "Dorm-205", true);
        
        GraduateStudent graduate = new GraduateStudent(
            "Bob Chen", 24, "bob.chen@tech.edu", "Artificial Intelligence", 
            "Dr. Smith", "Machine Learning in Healthcare", true);
        
        InternationalStudent international = new InternationalStudent(
            "Carlos Rodriguez", 21, "carlos@tech.edu", "Engineering", 
            "Spain", "F-1", true);
        
        // Create faculty with inheritance hierarchy
        Professor professor = new Professor(
            "Dr. Sarah Wilson", 45, "s.wilson@tech.edu", "Computer Science", 
            95000, 15, "Machine Learning", true);
        
        AssistantProfessor assistantProf = new AssistantProfessor(
            "Dr. Mike Davis", 32, "m.davis@tech.edu", "Mathematics", 
            65000, 3, "Statistics", 4, true);
        
        Researcher researcher = new Researcher(
            "Dr. Lisa Park", 38, "l.park@tech.edu", "Biology", 
            70000, 8, "Genetics");
        
        // Display hierarchy information
        System.out.println("Created complete inheritance hierarchy:");
        undergrad.displayInfo();
        graduate.displayInfo();
        international.displayInfo();
        professor.displayInfo();
        assistantProf.displayInfo();
        researcher.displayInfo();
        
        // DEMONSTRATION 2: Constructor Chaining Verification
        System.out.println("2. CONSTRUCTOR CHAINING VERIFICATION:");
        
        // Create objects to show constructor chaining in action
        System.out.println("Creating UndergraduateStudent (4-level chain):");
        UndergraduateStudent chainDemo = new UndergraduateStudent(
            "Demo Student", 18, "demo@tech.edu", "Physics", "Freshman");
        System.out.println("Constructor chain: Person → Student → UndergraduateStudent");
        System.out.println("Demo student created: " + chainDemo.getName() + "\n");
        
        System.out.println("Creating Professor (3-level chain):");
        Professor chainDemo2 = new Professor(
            "Demo Professor", 50, "demo.prof@tech.edu", "Chemistry", 85000, 20, "Organic Chemistry");
        System.out.println("Constructor chain: Person → Faculty → Professor");
        System.out.println("Demo professor created: " + chainDemo2.getName() + "\n");
        
        // DEMONSTRATION 3: Method Overriding in Action
        System.out.println("3. METHOD OVERRIDING IN ACTION:");
        
        // Same method name, different behavior based on actual type
        System.out.println("=== performDuties() - Different for each type ===");
        undergrad.performDuties();     // Student duties
        graduate.performDuties();      // Graduate student duties
        professor.performDuties();     // Professor duties
        assistantProf.performDuties(); // Assistant professor duties
        
        System.out.println("\n=== calculateAnnualCost() - Specialized calculations ===");
        System.out.printf("Undergraduate cost: $%.2f\n", undergrad.calculateAnnualCost());
        System.out.printf("Graduate cost: $%.2f\n", graduate.calculateAnnualCost());
        System.out.printf("International cost: $%.2f\n", international.calculateAnnualCost());
        System.out.printf("Professor cost (salary): $%.2f\n", professor.calculateAnnualCost());
        
        // DEMONSTRATION 4: Protected Access Usage
        System.out.println("\n4. PROTECTED ACCESS USAGE:");
        
        // Students can access protected members from Person and Student
        undergrad.demonstrateProtectedAccess();
        graduate.demonstrateProtectedAccess();
        
        // Faculty can access protected members from Person and Faculty
        professor.demonstrateProtectedAccess();
        assistantProf.demonstrateProtectedAccess();
        
        // DEMONSTRATION 5: University System Integration
        System.out.println("5. UNIVERSITY SYSTEM INTEGRATION:");
        
        // Register all people with university
        university.registerPerson(undergrad);
        university.registerPerson(graduate);
        university.registerPerson(international);
        university.registerPerson(professor);
        university.registerPerson(assistantProf);
        university.registerPerson(researcher);
        
        university.displaySystemStatus();
        
        // DEMONSTRATION 6: Academic Operations
        System.out.println("6. ACADEMIC OPERATIONS:");
        
        // Student enrollment operations
        undergrad.enrollInCourse("CS-101 Introduction to Programming");
        undergrad.enrollInCourse("MATH-201 Calculus II");
        graduate.enrollInCourse("CS-501 Advanced Machine Learning");
        
        // Faculty teaching assignments
        professor.assignCourse("CS-301 Data Structures");
        professor.assignCourse("CS-501 Advanced Machine Learning");
        assistantProf.assignCourse("MATH-101 College Algebra");
        
        // Show updated information
        undergrad.displayAcademicStatus();
        professor.displayTeachingLoad();
        
        // DEMONSTRATION 7: Specialized Behaviors
        System.out.println("7. SPECIALIZED BEHAVIORS:");
        
        // Undergraduate-specific operations
        undergrad.applyForScholarship();
        undergrad.selectDormRoom("Dorm-301");
        
        // Graduate-specific operations
        graduate.selectAdvisor("Dr. Johnson");
        graduate.defendThesis();
        
        // International-specific operations
        international.renewVisa();
        international.requestI20();
        
        // Professor-specific operations
        professor.publishPaper("AI in Education: A New Paradigm");
        professor.mentorStudent(graduate);
        
        // Assistant professor-specific operations
        assistantProf.prepareForTenure();
        assistantProf.buildResearchProgram();
        
        // Researcher-specific operations
        researcher.applyForGrant("NIH Research Grant", 250000);
        researcher.manageResearchTeam();
        
        // DEMONSTRATION 8: Polymorphic Usage Preparation
        System.out.println("8. POLYMORPHIC USAGE PREPARATION:");
        
        // Store all people in Person array (polymorphism preview)
        Person[] universityPeople = {undergrad, graduate, international, professor, assistantProf, researcher};
        
        System.out.println("=== University Directory (Polymorphic Display) ===");
        for (Person person : universityPeople) {
            System.out.println(person.getRole() + ": " + person.getName() + 
                             " - Annual Cost/Salary: $" + String.format("%.2f", person.calculateAnnualCost()));
        }
        
        // DEMONSTRATION 9: IS-A Relationship Testing
        System.out.println("\n9. IS-A RELATIONSHIP TESTING:");
        
        testIsARelationships(undergrad);
        testIsARelationships(graduate);
        testIsARelationships(professor);
        testIsARelationships(assistantProf);
        
        // DEMONSTRATION 10: Advanced Academic Operations
        System.out.println("10. ADVANCED ACADEMIC OPERATIONS:");
        
        // Semester operations
        university.processEndOfSemester();
        
        // Grade processing
        double[] undergradGrades = {3.7, 3.9, 3.5, 4.0};
        undergrad.calculateSemesterGPA(undergradGrades);
        
        // Financial operations
        undergrad.processPayment(15000);
        professor.processSalary();
        
        // Generate comprehensive report
        university.generateUniversityReport();
        
        System.out.println("=== INHERITANCE MASTERY DEMONSTRATION COMPLETE ===");
        System.out.println("All inheritance relationships working perfectly!");
        System.out.println("The Bloodline has been successfully established!");
    }
    
    /**
     * Test IS-A relationships using instanceof
     */
    public static void testIsARelationships(Person person) {
        System.out.println("Testing IS-A relationships for " + person.getName() + ":");
        System.out.println("  IS-A Person: " + (person instanceof Person));
        System.out.println("  IS-A Student: " + (person instanceof Student));
        System.out.println("  IS-A Faculty: " + (person instanceof Faculty));
        
        if (person instanceof UndergraduateStudent) {
            System.out.println("  IS-A UndergraduateStudent: true");
        } else if (person instanceof GraduateStudent) {
            System.out.println("  IS-A GraduateStudent: true");
        } else if (person instanceof Professor) {
            System.out.println("  IS-A Professor: true");
        } else if (person instanceof AssistantProfessor) {
            System.out.println("  IS-A AssistantProfessor: true");
        }
        System.out.println();
    }
}

/**
 * BASE CLASS: Person - The ancestral foundation of all university people
 */
abstract class Person {
    // PROTECTED FIELDS: Available to all subclasses
    protected String name;
    protected int age;
    protected String email;
    protected String phoneNumber;
    protected String address;
    protected String personId;
    protected boolean isActive;
    protected String activityLog;
    
    // CONSTRUCTOR: Base initialization for all people
    public Person(String name, int age, String email) {
        // Validate and set basic information
        setName(name);
        setAge(age);
        setEmail(email);
        
        // Initialize other fields
        this.phoneNumber = "";
        this.address = "";
        this.personId = generatePersonId();
        this.isActive = true;
        this.activityLog = "";
        
        logActivity("Person created: " + name);
        System.out.println("Person created: " + name + " (ID: " + personId + ")");
    }
    
    // CONSTRUCTOR: Extended initialization
    public Person(String name, int age, String email, String phoneNumber, String address) {
        this(name, age, email);  // Call basic constructor
        setPhoneNumber(phoneNumber);
        setAddress(address);
        logActivity("Extended person information added");
    }
    
    // CONCRETE METHODS: Inherited by all children
    public void updateContactInfo(String email, String phone, String address) {
        setEmail(email);
        setPhoneNumber(phone);
        setAddress(address);
        logActivity("Contact information updated");
        System.out.println(name + "'s contact information updated");
    }
    
    public void activate() {
        this.isActive = true;
        logActivity("Person activated");
        System.out.println(name + " has been activated in the university system");
    }
    
    public void deactivate() {
        this.isActive = false;
        logActivity("Person deactivated");
        System.out.println(name + " has been deactivated from the university system");
    }
    
    // PROTECTED METHODS: Available to subclasses for internal operations
    protected void logActivity(String activity) {
        String timestamp = java.time.LocalDateTime.now().toString();
        this.activityLog += timestamp + " - " + activity + "\n";
    }
    
    protected String generatePersonId() {
        return "PER-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
    }
    
    protected boolean validateEmail(String email) {
        return email != null && email.contains("@") && email.contains(".") && email.length() > 5;
    }
    
    protected boolean validatePhoneNumber(String phone) {
        if (phone == null) return false;
        String cleanPhone = phone.replaceAll("[^0-9]", "");
        return cleanPhone.length() >= 10;
    }
    
    // SETTER METHODS: Controlled access with validation
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        } else {
            this.name = "Unknown Person";
            System.out.println("Invalid name provided. Set to: Unknown Person");
        }
    }
    
    public void setAge(int age) {
        if (age >= 16 && age <= 100) {
            this.age = age;
        } else {
            this.age = 18;
            System.out.println("Invalid age: " + age + ". Set to: 18");
        }
    }
    
    public void setEmail(String email) {
        if (validateEmail(email)) {
            this.email = email.toLowerCase().trim();
        } else {
            System.out.println("Invalid email format: " + email);
        }
    }
    
    public void setPhoneNumber(String phoneNumber) {
        if (validatePhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber.trim();
        } else {
            System.out.println("Invalid phone number format: " + phoneNumber);
        }
    }
    
    public void setAddress(String address) {
        this.address = address != null ? address.trim() : "";
    }
    
    // GETTER METHODS: Public access to protected data
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public String getPersonId() { return personId; }
    public boolean isActive() { return isActive; }
    
    // ABSTRACT METHODS: Must be implemented by all children
    public abstract double calculateAnnualCost();
    public abstract void performDuties();
    public abstract String getRole();
    public abstract String getSpecificRole();
    
    // METHOD TO BE OVERRIDDEN: Base implementation that children can specialize
    public void displayInfo() {
        System.out.println("=== PERSON INFORMATION ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Address: " + address);
        System.out.println("Person ID: " + personId);
        System.out.println("Status: " + (isActive ? "Active" : "Inactive"));
    }
    
    @Override
    public String toString() {
        return getSpecificRole() + ": " + name + " (" + personId + ")";
    }
}

/**
 * STUDENT CLASS: Inherits from Person and adds student-specific functionality
 */
abstract class Student extends Person {
    // PROTECTED FIELDS: Available to student subclasses
    protected String studentId;
    protected String major;
    protected double gpa;
    protected int creditsEarned;
    protected String academicYear;
    protected boolean isEnrolled;
    protected String[] coursesEnrolled;
    protected int courseCount;
    
    // CONSTRUCTOR: Student-specific initialization
    public Student(String name, int age, String email, String major) {
        super(name, age, email);  // Call Person constructor
        this.studentId = generateStudentId();
        setMajor(major);
        this.gpa = 0.0;
        this.creditsEarned = 0;
        this.academicYear = "Freshman";
        this.isEnrolled = true;
        this.coursesEnrolled = new String[8];  // Max 8 courses
        this.courseCount = 0;
        
        logActivity("Student created with major: " + major);
        System.out.println("Student created: " + name + " (Student ID: " + studentId + ")");
    }
    
    // EXTENDED CONSTRUCTOR
    public Student(String name, int age, String email, String major, String academicYear) {
        this(name, age, email, major);  // Call basic student constructor
        setAcademicYear(academicYear);
        logActivity("Academic year set to: " + academicYear);
    }
    
    // STUDENT-SPECIFIC METHODS
    public boolean enrollInCourse(String courseName) {
        if (!isEnrolled) {
            System.out.println("Cannot enroll - student is not currently enrolled");
            return false;
        }
        
        if (courseCount >= coursesEnrolled.length) {
            System.out.println("Cannot enroll - maximum course load reached");
            return false;
        }
        
        coursesEnrolled[courseCount] = courseName;
        courseCount++;
        logActivity("Enrolled in course: " + courseName);
        System.out.println(name + " enrolled in " + courseName);
        return true;
    }
    
    public boolean dropCourse(String courseName) {
        for (int i = 0; i < courseCount; i++) {
            if (coursesEnrolled[i].equals(courseName)) {
                // Shift remaining courses
                for (int j = i; j < courseCount - 1; j++) {
                    coursesEnrolled[j] = coursesEnrolled[j + 1];
                }
                courseCount--;
                logActivity("Dropped course: " + courseName);
                System.out.println(name + " dropped " + courseName);
                return true;
            }
        }
        System.out.println("Course not found: " + courseName);
        return false;
    }
    
    public void calculateSemesterGPA(double[] grades) {
        if (grades.length == 0) return;
        
        double total = 0;
        for (double grade : grades) {
            total += grade;
        }
        double semesterGPA = total / grades.length;
        
        // Update cumulative GPA (simplified calculation)
        this.gpa = (this.gpa + semesterGPA) / 2;
        
        logActivity("Semester GPA calculated: " + semesterGPA);
        System.out.println(name + "'s semester GPA: " + String.format("%.2f", semesterGPA) + 
                          ", Cumulative GPA: " + String.format("%.2f", gpa));
    }
    
    public void processPayment(double amount) {
        double annualCost = calculateAnnualCost();
        if (amount > 0 && amount <= annualCost) {
            logActivity("Payment processed: $" + amount);
            System.out.println(name + " processed payment of $" + amount);
        } else {
            System.out.println("Invalid payment amount: $" + amount);
        }
    }
    
    public void requestTranscript() {
        System.out.println("Transcript requested for " + name + " (GPA: " + String.format("%.2f", gpa) + 
                          ", Credits: " + creditsEarned + ")");
        logActivity("Transcript requested");
    }
    
    // OVERRIDE PARENT METHOD: Add student-specific information
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call Person's displayInfo
        System.out.println("Student ID: " + studentId);
        System.out.println("Major: " + major);
        System.out.println("GPA: " + String.format("%.2f", gpa));
        System.out.println("Credits Earned: " + creditsEarned);
        System.out.println("Academic Year: " + academicYear);
        System.out.println("Enrollment Status: " + (isEnrolled ? "Enrolled" : "Not Enrolled"));
        System.out.println("Current Courses: " + courseCount + "/" + coursesEnrolled.length);
    }
    
    public void displayAcademicStatus() {
        System.out.println("=== Academic Status: " + name + " ===");
        System.out.println("Major: " + major + " | Year: " + academicYear);
        System.out.println("GPA: " + String.format("%.2f", gpa) + " | Credits: " + creditsEarned);
        System.out.println("Enrolled Courses (" + courseCount + "):");
        for (int i = 0; i < courseCount; i++) {
            System.out.println("  " + (i + 1) + ". " + coursesEnrolled[i]);
        }
        System.out.println();
    }
    
    // PROTECTED METHOD: Demonstrate protected access
    public void demonstrateProtectedAccess() {
        System.out.println("Student " + name + " accessing protected members:");
        System.out.println("  From Person - name: " + name);           // Protected from Person
        System.out.println("  From Person - age: " + age);             // Protected from Person
        System.out.println("  From Student - major: " + major);        // Protected from Student
        System.out.println("  From Student - gpa: " + gpa);            // Protected from Student
        logActivity("Demonstrated protected access from student");      // Protected method
        System.out.println();
    }
    
    // IMPLEMENT ABSTRACT METHOD: Student role
    @Override
    public String getRole() {
        return "Student";
    }
    
    // IMPLEMENT ABSTRACT METHOD: Basic student duties
    @Override
    public void performDuties() {
        System.out.println(name + " (Student) performs duties:");
        System.out.println("  - Attending classes");
        System.out.println("  - Completing assignments");
        System.out.println("  - Studying for exams");
        System.out.println("  - Participating in campus activities");
        logActivity("Performed student duties");
    }
    
    // PROTECTED HELPER METHODS
    protected String generateStudentId() {
        return "STU-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 10000);
    }
    
    protected void setMajor(String major) {
        this.major = (major != null && !major.trim().isEmpty()) ? major.trim() : "Undeclared";
    }
    
    protected void setAcademicYear(String academicYear) {
        String[] validYears = {"Freshman", "Sophomore", "Junior", "Senior"};
        for (String year : validYears) {
            if (year.equalsIgnoreCase(academicYear)) {
                this.academicYear = year;
                return;
            }
        }
        this.academicYear = "Freshman";  // Default
    }
    
    // GETTER METHODS
    public String getStudentId() { return studentId; }
    public String getMajor() { return major; }
    public double getGpa() { return gpa; }
    public int getCreditsEarned() { return creditsEarned; }
    public String getAcademicYear() { return academicYear; }
    public boolean isEnrolled() { return isEnrolled; }
    public int getCourseCount() { return courseCount; }
}

/**
 * UNDERGRADUATE STUDENT: Specializes Student for undergraduate-specific needs
 */
class UndergraduateStudent extends Student {
    // UNDERGRADUATE-SPECIFIC FIELDS
    private String dormRoom;
    private boolean hasScholarship;
    
    // CONSTRUCTOR: Undergraduate-specific initialization
    public UndergraduateStudent(String name, int age, String email, String major, String academicYear) {
        super(name, age, email, major, academicYear);  // Call Student constructor
        this.dormRoom = "";
        this.hasScholarship = false;
        System.out.println("Undergraduate student specialization added");
    }
    
    // EXTENDED CONSTRUCTOR
    public UndergraduateStudent(String name, int age, String email, String major, 
                               String academicYear, String dormRoom, boolean hasScholarship) {
        this(name, age, email, major, academicYear);  // Call basic undergraduate constructor
        this.dormRoom = dormRoom;
        this.hasScholarship = hasScholarship;
        logActivity("Dorm room assigned: " + dormRoom + ", Scholarship: " + hasScholarship);
    }
    
    // UNDERGRADUATE-SPECIFIC METHODS
    public boolean applyForScholarship() {
        if (gpa >= 3.5) {
            this.hasScholarship = true;
            logActivity("Scholarship application approved (GPA: " + gpa + ")");
            System.out.println(name + " awarded scholarship based on GPA: " + String.format("%.2f", gpa));
            return true;
        } else {
            System.out.println(name + " scholarship application denied (GPA too low: " + String.format("%.2f", gpa) + ")");
            return false;
        }
    }
    
    public void selectDormRoom(String roomNumber) {
        this.dormRoom = roomNumber;
        logActivity("Dorm room selected: " + roomNumber);
        System.out.println(name + " assigned to dorm room: " + roomNumber);
    }
    
    // OVERRIDE ABSTRACT METHOD: Undergraduate-specific cost calculation
    @Override
    public double calculateAnnualCost() {
        double tuition = 25000;  // Base undergraduate tuition
        double roomBoard = 12000;  // Room and board
        double fees = 2000;     // Various fees
        
        double total = tuition + roomBoard + fees;
        
        // Apply scholarship discount
        if (hasScholarship) {
            total *= 0.7;  // 30% discount
        }
        
        return total;
    }
    
    // OVERRIDE PARENT METHOD: Add undergraduate-specific duties
    @Override
    public void performDuties() {
        super.performDuties();  // Call Student's performDuties
        System.out.println("  - Living in dormitory");
        System.out.println("  - Participating in freshman orientation");
        System.out.println("  - Meeting with academic advisor");
    }
    
    // IMPLEMENT ABSTRACT METHOD: Specific role
    @Override
    public String getSpecificRole() {
        return "Undergraduate Student";
    }
    
    // OVERRIDE DISPLAY METHOD: Add undergraduate information
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call Student's displayInfo
        System.out.println("Dorm Room: " + (dormRoom.isEmpty() ? "Not assigned" : dormRoom));
        System.out.println("Scholarship: " + (hasScholarship ? "Yes" : "No"));
        System.out.println();
    }
    
    // GETTER METHODS
    public String getDormRoom() { return dormRoom; }
    public boolean hasScholarship() { return hasScholarship; }
}

/**
 * GRADUATE STUDENT: Specializes Student for graduate-level academics
 */
class GraduateStudent extends Student {
    // GRADUATE-SPECIFIC FIELDS
    private String advisor;
    private String thesisTitle;
    private boolean isTA;  // Teaching Assistant
    
    // CONSTRUCTOR: Graduate-specific initialization
    public GraduateStudent(String name, int age, String email, String major, 
                          String advisor, String thesisTitle, boolean isTA) {
        super(name, age, email, major, "Graduate");  // Call Student constructor
        this.advisor = advisor;
        this.thesisTitle = thesisTitle;
        this.isTA = isTA;
        System.out.println("Graduate student specialization added");
        logActivity("Graduate student created - Advisor: " + advisor + ", TA: " + isTA);
    }
    
    // GRADUATE-SPECIFIC METHODS
    public void selectAdvisor(String advisorName) {
        this.advisor = advisorName;
        logActivity("Advisor selected: " + advisorName);
        System.out.println(name + " selected advisor: " + advisorName);
    }
    
    public void defendThesis() {
        if (thesisTitle != null && !thesisTitle.isEmpty()) {
            System.out.println(name + " successfully defended thesis: \"" + thesisTitle + "\"");
            logActivity("Thesis defended: " + thesisTitle);
        } else {
            System.out.println(name + " cannot defend - no thesis title set");
        }
    }
    
    public boolean applyForTA() {
        if (gpa >= 3.0) {
            this.isTA = true;
            logActivity("TA application approved");
            System.out.println(name + " approved for Teaching Assistant position");
            return true;
        } else {
            System.out.println(name + " TA application denied - GPA too low");
            return false;
        }
    }
    
    // OVERRIDE ABSTRACT METHOD: Graduate-specific cost calculation
    @Override
    public double calculateAnnualCost() {
        double tuition = 35000;  // Higher graduate tuition
        double fees = 3000;     // Graduate fees
        
        double total = tuition + fees;
        
        // TA discount
        if (isTA) {
            total *= 0.5;  // 50% discount for TAs
        }
        
        return total;
    }
    
    // OVERRIDE PARENT METHOD: Add graduate-specific duties
    @Override
    public void performDuties() {
        super.performDuties();  // Call Student's performDuties
        System.out.println("  - Conducting research");
        System.out.println("  - Working on thesis");
        System.out.println("  - Meeting with advisor");
        if (isTA) {
            System.out.println("  - Teaching undergraduate courses");
            System.out.println("  - Grading assignments");
        }
    }
    
    // IMPLEMENT ABSTRACT METHOD: Specific role
    @Override
    public String getSpecificRole() {
        return "Graduate Student" + (isTA ? " (TA)" : "");
    }
    
    // OVERRIDE DISPLAY METHOD: Add graduate information
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call Student's displayInfo
        System.out.println("Advisor: " + (advisor != null ? advisor : "Not assigned"));
        System.out.println("Thesis: " + (thesisTitle != null ? thesisTitle : "Not defined"));
        System.out.println("Teaching Assistant: " + (isTA ? "Yes" : "No"));
        System.out.println();
    }
    
    // GETTER METHODS
    public String getAdvisor() { return advisor; }
    public String getThesisTitle() { return thesisTitle; }
    public boolean isTA() { return isTA; }
}

/**
 * INTERNATIONAL STUDENT: Specializes Student for international requirements
 */
class InternationalStudent extends Student {
    // INTERNATIONAL-SPECIFIC FIELDS
    private String country;
    private String visaStatus;
    private boolean needsI20;
    
    // CONSTRUCTOR: International-specific initialization
    public InternationalStudent(String name, int age, String email, String major, 
                               String country, String visaStatus, boolean needsI20) {
        super(name, age, email, major);  // Call Student constructor
        this.country = country;
        this.visaStatus = visaStatus;
        this.needsI20 = needsI20;
        System.out.println("International student specialization added");
        logActivity("International student from " + country + " with " + visaStatus + " visa");
    }
    
    // INTERNATIONAL-SPECIFIC METHODS
    public void renewVisa() {
        System.out.println(name + " renewing " + visaStatus + " visa for continued study");
        logActivity("Visa renewal initiated: " + visaStatus);
    }
    
    public void requestI20() {
        if (needsI20) {
            System.out.println("I-20 document requested for " + name + " from " + country);
            logActivity("I-20 document requested");
        } else {
            System.out.println(name + " does not require I-20 document");
        }
    }
    
    public void reportToISO() {  // International Student Office
        System.out.println(name + " reporting to International Student Office for check-in");
        logActivity("Reported to International Student Office");
    }
    
    // OVERRIDE ABSTRACT METHOD: International-specific cost calculation
    @Override
    public double calculateAnnualCost() {
        double tuition = 35000;        // Higher international tuition
        double internationalFee = 5000; // Additional international fee
        double fees = 2500;           // Various fees
        
        return tuition + internationalFee + fees;
    }
    
    // OVERRIDE PARENT METHOD: Add international-specific duties
    @Override
    public void performDuties() {
        super.performDuties();  // Call Student's performDuties
        System.out.println("  - Maintaining visa status");
        System.out.println("  - Reporting to International Student Office");
        System.out.println("  - Cultural adaptation activities");
    }
    
    // IMPLEMENT ABSTRACT METHOD: Specific role
    @Override
    public String getSpecificRole() {
        return "International Student (" + country + ")";
    }
    
    // OVERRIDE DISPLAY METHOD: Add international information
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call Student's displayInfo
        System.out.println("Country: " + country);
        System.out.println("Visa Status: " + visaStatus);
        System.out.println("Needs I-20: " + (needsI20 ? "Yes" : "No"));
        System.out.println();
    }
    
    // GETTER METHODS
    public String getCountry() { return country; }
    public String getVisaStatus() { return visaStatus; }
    public boolean needsI20() { return needsI20; }
}

/**
 * FACULTY CLASS: Inherits from Person and adds faculty-specific functionality
 */
abstract class Faculty extends Person {
    // PROTECTED FIELDS: Available to faculty subclasses
    protected String facultyId;
    protected String department;
    protected double salary;  // Private in implementation but protected in concept
    protected int yearsOfExperience;
    protected String[] coursesTeaching;
    protected int teachingLoad;
    protected String researchArea;
    protected boolean isTenured;
    
    // CONSTRUCTOR: Faculty-specific initialization
    public Faculty(String name, int age, String email, String department, 
                   double salary, int yearsOfExperience, String researchArea) {
        super(name, age, email);  // Call Person constructor
        this.facultyId = generateFacultyId();
        this.department = department;
        this.salary = salary;
        this.yearsOfExperience = yearsOfExperience;
        this.researchArea = researchArea;
        this.coursesTeaching = new String[6];  // Max 6 courses
        this.teachingLoad = 0;
        this.isTenured = false;
        
        logActivity("Faculty created in " + department + " department");
        System.out.println("Faculty created: " + name + " (Faculty ID: " + facultyId + ")");
    }
    
    // FACULTY-SPECIFIC METHODS
    public boolean assignCourse(String courseName) {
        if (teachingLoad >= coursesTeaching.length) {
            System.out.println("Cannot assign - maximum teaching load reached");
            return false;
        }
        
        coursesTeaching[teachingLoad] = courseName;
        teachingLoad++;
        logActivity("Course assigned: " + courseName);
        System.out.println(name + " assigned to teach " + courseName);
        return true;
    }
    
    public void holdOfficeHours() {
        System.out.println(name + " holding office hours for student consultations");
        logActivity("Office hours conducted");
    }
    
    public void gradingSession(String courseName, double[] studentGrades) {
        System.out.println(name + " grading " + studentGrades.length + " students for " + courseName);
        double average = 0;
        for (double grade : studentGrades) {
            average += grade;
        }
        average /= studentGrades.length;
        System.out.println("Class average: " + String.format("%.2f", average));
        logActivity("Grading completed for " + courseName);
    }
    
    public void submitTimesheet() {
        System.out.println(name + " submitting timesheet for " + teachingLoad + " courses");
        logActivity("Timesheet submitted");
    }
    
    public void processSalary() {
        System.out.println("Processing annual salary of $" + String.format("%.2f", salary) + " for " + name);
        logActivity("Salary processed: $" + salary);
    }
    
    public void negotiateSalary(double proposedSalary) {
        if (proposedSalary > salary) {
            this.salary = proposedSalary;
            System.out.println(name + "'s salary increased to $" + String.format("%.2f", salary));
            logActivity("Salary negotiated to: $" + salary);
        } else {
            System.out.println("Salary negotiation unsuccessful - proposed amount too low");
        }
    }
    
    // OVERRIDE PARENT METHOD: Add faculty-specific information
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call Person's displayInfo
        System.out.println("Faculty ID: " + facultyId);
        System.out.println("Department: " + department);
        System.out.println("Salary: $" + String.format("%.2f", salary));
        System.out.println("Experience: " + yearsOfExperience + " years");
        System.out.println("Research Area: " + researchArea);
        System.out.println("Tenured: " + (isTenured ? "Yes" : "No"));
        System.out.println("Teaching Load: " + teachingLoad + "/" + coursesTeaching.length);
    }
    
    public void displayTeachingLoad() {
        System.out.println("=== Teaching Load: " + name + " ===");
        System.out.println("Department: " + department + " | Courses: " + teachingLoad);
        System.out.println("Teaching assignments:");
        for (int i = 0; i < teachingLoad; i++) {
            System.out.println("  " + (i + 1) + ". " + coursesTeaching[i]);
        }
        System.out.println();
    }
    
    // PROTECTED METHOD: Demonstrate protected access
    public void demonstrateProtectedAccess() {
        System.out.println("Faculty " + name + " accessing protected members:");
        System.out.println("  From Person - personId: " + personId);        // Protected from Person
        System.out.println("  From Person - isActive: " + isActive);        // Protected from Person
        System.out.println("  From Faculty - department: " + department);   // Protected from Faculty
        System.out.println("  From Faculty - salary: " + salary);           // Protected from Faculty
        logActivity("Demonstrated protected access from faculty");           // Protected method
        System.out.println();
    }
    
    // IMPLEMENT ABSTRACT METHOD: Faculty role
    @Override
    public String getRole() {
        return "Faculty";
    }
    
    // IMPLEMENT ABSTRACT METHOD: Faculty cost (salary)
    @Override
    public double calculateAnnualCost() {
        return salary;  // For faculty, "cost" is their salary
    }
    
    // IMPLEMENT ABSTRACT METHOD: Basic faculty duties
    @Override
    public void performDuties() {
        System.out.println(name + " (Faculty) performs duties:");
        System.out.println("  - Teaching courses");
        System.out.println("  - Conducting research");
        System.out.println("  - Serving on committees");
        System.out.println("  - Mentoring students");
        logActivity("Performed faculty duties");
    }
    
    // PROTECTED HELPER METHOD
    protected String generateFacultyId() {
        return "FAC-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 10000);
    }
    
    // GETTER METHODS
    public String getFacultyId() { return facultyId; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getYearsOfExperience() { return yearsOfExperience; }
    public String getResearchArea() { return researchArea; }
    public boolean isTenured() { return isTenured; }
    public int getTeachingLoad() { return teachingLoad; }
}

/**
 * PROFESSOR CLASS: Specializes Faculty for full professor rank
 */
class Professor extends Faculty {
    // PROFESSOR-SPECIFIC FIELDS
    private String[] publicationsList;
    private int numberOfPublications;
    
    // CONSTRUCTOR: Professor-specific initialization
    public Professor(String name, int age, String email, String department, 
                    double salary, int yearsOfExperience, String researchArea) {
        super(name, age, email, department, salary, yearsOfExperience, researchArea);
        this.publicationsList = new String[50];  // Max 50 publications
        this.numberOfPublications = 0;
        this.isTenured = true;  // Professors are typically tenured
        System.out.println("Professor rank assigned - tenured position");
    }
    
    // CONSTRUCTOR: With tenure specification
    public Professor(String name, int age, String email, String department, 
                    double salary, int yearsOfExperience, String researchArea, boolean isTenured) {
        this(name, age, email, department, salary, yearsOfExperience, researchArea);
        this.isTenured = isTenured;
        logActivity("Tenure status set: " + isTenured);
    }
    
    // PROFESSOR-SPECIFIC METHODS
    public void publishPaper(String paperTitle) {
        if (numberOfPublications < publicationsList.length) {
            publicationsList[numberOfPublications] = paperTitle;
            numberOfPublications++;
            System.out.println(name + " published: \"" + paperTitle + "\"");
            logActivity("Paper published: " + paperTitle);
        } else {
            System.out.println("Publication list is full");
        }
    }
    
    public void mentorStudent(Student student) {
        System.out.println("Professor " + name + " mentoring " + student.getName() + 
                          " in " + researchArea);
        logActivity("Mentoring student: " + student.getName());
    }
    
    public boolean applyForTenure() {
        if (!isTenured && numberOfPublications >= 10 && yearsOfExperience >= 6) {
            this.isTenured = true;
            this.salary *= 1.2;  // 20% salary increase
            System.out.println(name + " granted tenure! Salary increased to $" + 
                             String.format("%.2f", salary));
            logActivity("Tenure granted");
            return true;
        } else {
            System.out.println(name + " tenure application denied - insufficient qualifications");
            return false;
        }
    }
    
    // OVERRIDE PARENT METHOD: Add professor-specific duties
    @Override
    public void performDuties() {
        super.performDuties();  // Call Faculty's performDuties
        System.out.println("  - Leading research projects");
        System.out.println("  - Publishing academic papers");
        System.out.println("  - Supervising graduate students");
        System.out.println("  - Serving on tenure committees");
    }
    
    // IMPLEMENT ABSTRACT METHOD: Specific role
    @Override
    public String getSpecificRole() {
        return "Professor" + (isTenured ? " (Tenured)" : "");
    }
    
    // OVERRIDE DISPLAY METHOD: Add professor information
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call Faculty's displayInfo
        System.out.println("Publications: " + numberOfPublications);
        System.out.println("Rank: Full Professor");
        System.out.println();
    }
    
    // GETTER METHODS
    public int getNumberOfPublications() { return numberOfPublications; }
    public String[] getPublicationsList() { 
        String[] copy = new String[numberOfPublications];
        System.arraycopy(publicationsList, 0, copy, 0, numberOfPublications);
        return copy;
    }
}

/**
 * ASSISTANT PROFESSOR CLASS: Specializes Faculty for junior faculty rank
 */
class AssistantProfessor extends Faculty {
    // ASSISTANT PROFESSOR-SPECIFIC FIELDS
    private int yearsUntilTenure;
    private boolean onTenureTrack;
    
    // CONSTRUCTOR: Assistant professor-specific initialization
    public AssistantProfessor(String name, int age, String email, String department, 
                             double salary, int yearsOfExperience, String researchArea, 
                             int yearsUntilTenure, boolean onTenureTrack) {
        super(name, age, email, department, salary, yearsOfExperience, researchArea);
        this.yearsUntilTenure = yearsUntilTenure;
        this.onTenureTrack = onTenureTrack;
        this.isTenured = false;  // Assistant professors are not tenured yet
        System.out.println("Assistant Professor rank assigned - " + 
                          (onTenureTrack ? "on tenure track" : "not on tenure track"));
    }
    
    // ASSISTANT PROFESSOR-SPECIFIC METHODS
    public void prepareForTenure() {
        if (onTenureTrack) {
            System.out.println(name + " preparing for tenure review in " + yearsUntilTenure + " years");
            System.out.println("Focus areas: Research productivity, teaching excellence, service");
            logActivity("Tenure preparation initiated");
        } else {
            System.out.println(name + " is not on tenure track");
        }
    }
    
    public void buildResearchProgram() {
        System.out.println(name + " building independent research program in " + researchArea);
        System.out.println("Seeking funding opportunities and establishing collaborations");
        logActivity("Research program development");
    }
    
    public void applyForResearchFunding(double amount) {
        System.out.println(name + " applying for research funding: $" + 
                          String.format("%.2f", amount));
        logActivity("Research funding application: $" + amount);
    }
    
    // OVERRIDE PARENT METHOD: Add assistant professor-specific duties
    @Override
    public void performDuties() {
        super.performDuties();  // Call Faculty's performDuties
        System.out.println("  - Establishing research independence");
        System.out.println("  - Building publication record");
        System.out.println("  - Developing teaching skills");
        if (onTenureTrack) {
            System.out.println("  - Preparing for tenure review");
        }
    }
    
    // IMPLEMENT ABSTRACT METHOD: Specific role
    @Override
    public String getSpecificRole() {
        return "Assistant Professor" + (onTenureTrack ? " (Tenure Track)" : "");
    }
    
    // OVERRIDE DISPLAY METHOD: Add assistant professor information
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call Faculty's displayInfo
        System.out.println("Years Until Tenure: " + yearsUntilTenure);
        System.out.println("Tenure Track: " + (onTenureTrack ? "Yes" : "No"));
        System.out.println("Rank: Assistant Professor");
        System.out.println();
    }
    
    // GETTER METHODS
    public int getYearsUntilTenure() { return yearsUntilTenure; }
    public boolean isOnTenureTrack() { return onTenureTrack; }
}

/**
 * RESEARCHER CLASS: Specializes Faculty for research-focused positions
 */
class Researcher extends Faculty {
    // RESEARCHER-SPECIFIC FIELDS
    private String[] activeGrants;
    private double grantFunding;
    private int grantCount;
    
    // CONSTRUCTOR: Researcher-specific initialization
    public Researcher(String name, int age, String email, String department, 
                     double salary, int yearsOfExperience, String researchArea) {
        super(name, age, email, department, salary, yearsOfExperience, researchArea);
        this.activeGrants = new String[10];  // Max 10 active grants
        this.grantFunding = 0.0;
        this.grantCount = 0;
        this.isTenured = false;  // Researchers typically not on tenure track
        System.out.println("Researcher position assigned - research-focused role");
    }
    
    // RESEARCHER-SPECIFIC METHODS
    public boolean applyForGrant(String grantName, double amount) {
        if (grantCount < activeGrants.length) {
            activeGrants[grantCount] = grantName + " ($" + String.format("%.2f", amount) + ")";
            grantCount++;
            grantFunding += amount;
            System.out.println(name + " awarded grant: " + grantName + " for $" + 
                             String.format("%.2f", amount));
            logActivity("Grant awarded: " + grantName + " ($" + amount + ")");
            return true;
        } else {
            System.out.println("Maximum number of active grants reached");
            return false;
        }
    }
    
    public void manageResearchTeam() {
        System.out.println(name + " managing research team in " + researchArea);
        System.out.println("Current funding: $" + String.format("%.2f", grantFunding));
        logActivity("Research team management session");
    }
    
    public void publishResearchFindings(String studyTitle) {
        System.out.println(name + " publishing research findings: \"" + studyTitle + "\"");
        logActivity("Research published: " + studyTitle);
    }
    
    // OVERRIDE PARENT METHOD: Research-focused duties
    @Override
    public void performDuties() {
        System.out.println(name + " (Researcher) performs duties:");
        System.out.println("  - Conducting research experiments");
        System.out.println("  - Managing research grants");
        System.out.println("  - Publishing research findings");
        System.out.println("  - Collaborating with other researchers");
        System.out.println("  - Supervising research assistants");
        logActivity("Performed researcher duties");
    }
    
    // IMPLEMENT ABSTRACT METHOD: Specific role
    @Override
    public String getSpecificRole() {
        return "Researcher";
    }
    
    // OVERRIDE DISPLAY METHOD: Add researcher information
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call Faculty's displayInfo
        System.out.println("Active Grants: " + grantCount);
        System.out.println("Total Funding: $" + String.format("%.2f", grantFunding));
        System.out.println("Position: Research Faculty");
        System.out.println();
    }
    
    // GETTER METHODS
    public double getGrantFunding() { return grantFunding; }
    public int getGrantCount() { return grantCount; }
    public String[] getActiveGrants() {
        String[] copy = new String[grantCount];
        System.arraycopy(activeGrants, 0, copy, 0, grantCount);
        return copy;
    }
}

/**
 * UNIVERSITY SYSTEM CLASS: Manages all university people and operations
 */
class UniversitySystem {
    // SYSTEM FIELDS
    private String universityName;
    private Person[] universityPeople;
    private int personCount;
    private String systemLog;
    
    // CONSTRUCTOR: Initialize university system
    public UniversitySystem(String universityName) {
        this.universityName = universityName;
        this.universityPeople = new Person[1000];  // Max 1000 people
        this.personCount = 0;
        this.systemLog = "";
        
        addSystemLogEntry("University system initialized: " + universityName);
        System.out.println("University system created: " + universityName);
    }
    
    // REGISTRATION METHODS
    public boolean registerPerson(Person person) {
        if (person == null) {
            System.out.println("Cannot register null person");
            return false;
        }
        
        if (personCount >= universityPeople.length) {
            System.out.println("University is at capacity");
            return false;
        }
        
        universityPeople[personCount] = person;
        personCount++;
        addSystemLogEntry("Person registered: " + person.getName() + " (" + person.getSpecificRole() + ")");
        System.out.println(person.getName() + " registered with " + universityName);
        return true;
    }
    
    // REPORTING METHODS
    public void displaySystemStatus() {
        System.out.println("=== " + universityName + " System Status ===");
        System.out.println("Total People: " + personCount);
        
        // Count by type using polymorphism
        int studentCount = 0, facultyCount = 0;
        for (int i = 0; i < personCount; i++) {
            if (universityPeople[i] instanceof Student) {
                studentCount++;
            } else if (universityPeople[i] instanceof Faculty) {
                facultyCount++;
            }
        }
        
        System.out.println("Students: " + studentCount);
        System.out.println("Faculty: " + facultyCount);
        System.out.println();
    }
    
    public void generateUniversityReport() {
        System.out.println("=== " + universityName + " COMPREHENSIVE REPORT ===");
        System.out.println("Report Generated: " + java.time.LocalDateTime.now());
        System.out.println();
        
        // Statistics by role
        int[] roleCounts = new int[10];  // Different role types
        String[] roleNames = {"Undergraduate", "Graduate", "International", 
                             "Professor", "Assistant Professor", "Researcher"};
        
        for (int i = 0; i < personCount; i++) {
            Person person = universityPeople[i];
            String role = person.getSpecificRole();
            
            if (role.contains("Undergraduate")) roleCounts[0]++;
            else if (role.contains("Graduate")) roleCounts[1]++;
            else if (role.contains("International")) roleCounts[2]++;
            else if (role.contains("Professor") && !role.contains("Assistant")) roleCounts[3]++;
            else if (role.contains("Assistant Professor")) roleCounts[4]++;
            else if (role.contains("Researcher")) roleCounts[5]++;
        }
        
        System.out.println("POPULATION BREAKDOWN:");
        for (int i = 0; i < roleNames.length; i++) {
            if (roleCounts[i] > 0) {
                System.out.println("  " + roleNames[i] + ": " + roleCounts[i]);
            }
        }
        
        // Financial summary
        double totalStudentCosts = 0;
        double totalFacultySalaries = 0;
        
        for (int i = 0; i < personCount; i++) {
            Person person = universityPeople[i];
            if (person instanceof Student) {
                totalStudentCosts += person.calculateAnnualCost();
            } else if (person instanceof Faculty) {
                totalFacultySalaries += person.calculateAnnualCost();
            }
        }
        
        System.out.println("\nFINANCIAL SUMMARY:");
        System.out.println("  Total Student Revenue: $" + String.format("%.2f", totalStudentCosts));
        System.out.println("  Total Faculty Salaries: $" + String.format("%.2f", totalFacultySalaries));
        System.out.println("  Net Financial Position: $" + 
                          String.format("%.2f", totalStudentCosts - totalFacultySalaries));
        System.out.println();
    }
    
    // SEMESTER OPERATIONS
    public void processEndOfSemester() {
        System.out.println("=== END OF SEMESTER PROCESSING ===");
        
        for (int i = 0; i < personCount; i++) {
            Person person = universityPeople[i];
            
            if (person instanceof Student) {
                Student student = (Student) person;
                System.out.println("Processing semester for " + student.getName() + 
                                 " (GPA: " + String.format("%.2f", student.getGpa()) + ")");
            } else if (person instanceof Faculty) {
                Faculty faculty = (Faculty) person;
                System.out.println("Processing semester for " + faculty.getName() + 
                                 " (Teaching load: " + faculty.getTeachingLoad() + ")");
            }
        }
        
        addSystemLogEntry("End of semester processing completed");
        System.out.println("End of semester processing complete\n");
    }
    
    // SYSTEM LOGGING
    private void addSystemLogEntry(String entry) {
        String timestamp = java.time.LocalDateTime.now().toString();
        this.systemLog += timestamp + " - " + entry + "\n";
    }
    
    // Display system log for administrative purposes
    public void displaySystemLog() {
        System.out.println("=== SYSTEM LOG ===");
        if (systemLog.isEmpty()) {
            System.out.println("No system activities logged");
        } else {
            String[] entries = systemLog.split("\n");
            int startIndex = Math.max(0, entries.length - 10);
            for (int i = startIndex; i < entries.length; i++) {
                if (!entries[i].isEmpty()) {
                    System.out.println(entries[i]);
                }
            }
        }
        System.out.println();
    }
    
    // GETTER METHODS
    public String getUniversityName() { return universityName; }
    public int getPersonCount() { return personCount; }
}

/**
 * INHERITANCE MASTERY ACHIEVEMENT:
 * 
 * This comprehensive university system demonstrates complete mastery of
 * Inheritance - The Second Pillar of OOP Wisdom through:
 * 
 * 1. INHERITANCE HIERARCHY:
 *    - Person (abstract base class)
 *    - Student → UndergraduateStudent, GraduateStudent, InternationalStudent
 *    - Faculty → Professor, AssistantProfessor, Researcher
 *    - Multi-level inheritance with logical IS-A relationships
 * 
 * 2. CONSTRUCTOR CHAINING:
 *    - Every constructor properly calls super() as first statement
 *    - Multiple constructor patterns (basic, extended, convenience)
 *    - Proper initialization chain from base to specialized classes
 *    - Parameter validation and default value handling
 * 
 * 3. METHOD OVERRIDING:
 *    - Abstract methods implemented differently by each class
 *    - Parent methods extended with super() calls
 *    - Specialized behavior while honoring parent functionality
 *    - Consistent method signatures across hierarchy
 * 
 * 4. PROTECTED ACCESS:
 *    - Protected fields shared appropriately within family
 *    - Protected methods for internal family operations
 *    - Demonstration methods showing protected access working
 *    - Proper encapsulation with family sharing
 * 
 * 5. ABSTRACT METHODS:
 *    - Base class defines contracts that children must fulfill
 *    - Forces consistent interface across all person types
 *    - Enables polymorphic usage while ensuring implementation
 *    - Clear separation between abstract and concrete methods
 * 
 * 6. IS-A RELATIONSHIPS:
 *    - All inheritance relationships pass the IS-A test
 *    - UndergraduateStudent IS-A Student IS-A Person ✓
 *    - Professor IS-A Faculty IS-A Person ✓
 *    - Logical real-world relationships throughout
 * 
 * 7. CODE REUSABILITY:
 *    - Common behavior in base classes (Person, Student, Faculty)
 *    - Specialized behavior in appropriate subclasses
 *    - No code duplication across inheritance hierarchy
 *    - Easy to extend with new person types
 * 
 * 8. POLYMORPHIC PREPARATION:
 *    - Consistent interfaces enable polymorphic usage
 *    - University system works with any Person type
 *    - Method overriding enables specialized behavior
 *    - instanceof testing demonstrates type relationships
 * 
 * The result is a flexible, extensible university management system where:
 * - New person types can be easily added to the hierarchy
 * - Common functionality is shared and consistent
 * - Specialized behavior is properly encapsulated
 * - System operations work polymorphically with all types
 * - Real-world university operations are accurately modeled
 * 
 * This demonstrates that inheritance is the foundation for building
 * organized, reusable, and extensible object-oriented systems where
 * each class builds upon the wisdom of its ancestors while contributing
 * its own unique capabilities to the family bloodline.
 * 
 * The Bloodline has been successfully established with complete mastery!
 */
