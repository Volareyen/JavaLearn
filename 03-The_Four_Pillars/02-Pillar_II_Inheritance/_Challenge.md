# The Pupil's Trial: Master of the Bloodline
*Challenge 2: Inheritance - Building a University Management System*

---

## **The Sacred Challenge**

*"True mastery of inheritance is not measured by how many classes you create, but by how wisely you establish relationships that honor the past while enabling the future."*

Faithful seeker, you have learned the profound art of Inheritance - the Second Pillar of OOP Wisdom. Now comes your trial - to forge a **University Management System** that demonstrates complete mastery of inheritance hierarchies, method overriding, constructor chaining, and the sacred "IS-A" relationships.

Your university system must be a living family tree where each class builds upon the wisdom of its ancestors while contributing its own unique capabilities to the academic realm.

---

## **The Divine Requirements**

### **Core Hierarchy: Person → Student/Faculty → Specialized Roles**

Your inheritance hierarchy must follow this sacred bloodline:

```
Person (Base Class)
├── Student (Inherits from Person)
│   ├── UndergraduateStudent (Inherits from Student)
│   ├── GraduateStudent (Inherits from Student)
│   └── InternationalStudent (Inherits from Student)
└── Faculty (Inherits from Person)
    ├── Professor (Inherits from Faculty)
    ├── AssistantProfessor (Inherits from Faculty)
    └── Researcher (Inherits from Faculty)
```

### **Base Class: `Person`**

Your `Person` must possess these protected attributes:
- `String name` - Full name (validated, non-empty)
- `int age` - Age in years (validated range: 16-100)
- `String email` - Email address (validated format)
- `String phoneNumber` - Contact number (validated format)
- `String address` - Physical address
- `String personId` - Unique identifier (read-only after creation)
- `boolean isActive` - Active status in university
- `String activityLog` - Write-only activity history

### **Student Class: Inherits from Person**

Your `Student` must add these protected attributes:
- `String studentId` - Student-specific ID (read-only)
- `String major` - Field of study
- `double gpa` - Grade Point Average (0.0-4.0)
- `int creditsEarned` - Total credits completed
- `String academicYear` - Current academic year (Freshman, Sophomore, etc.)
- `boolean isEnrolled` - Current enrollment status
- `String[] coursesEnrolled` - Current courses (max 8 courses)
- `int courseCount` - Number of enrolled courses

### **Faculty Class: Inherits from Person**

Your `Faculty` must add these protected attributes:
- `String facultyId` - Faculty-specific ID (read-only)
- `String department` - Academic department
- `double salary` - Annual salary (private, controlled access)
- `int yearsOfExperience` - Years in academia
- `String[] coursesTeaching` - Currently teaching (max 6 courses)
- `int teachingLoad` - Number of courses teaching
- `String researchArea` - Primary research focus
- `boolean isTenured` - Tenure status

---

## **Sacred Inheritance Patterns to Implement**

### **1. Constructor Chaining Mastery**

Each class must have multiple constructors with proper chaining:

```java
// Example pattern - implement for ALL classes
public Person(String name, int age, String email) {
    // Full validation and initialization
    // Generate unique personId
    // Log person creation
}

public Student(String name, int age, String email, String major) {
    super(name, age, email);  // Call Person constructor
    // Student-specific initialization
    // Generate studentId
    // Log student creation
}

public UndergraduateStudent(String name, int age, String email, String major, String academicYear) {
    super(name, age, email, major);  // Call Student constructor
    // Undergraduate-specific initialization
}
```

### **2. Method Overriding Excellence**

Implement intelligent method overriding throughout the hierarchy:

**Base Methods to Override:**
- `displayInfo()` - Each class adds its own information
- `calculateAnnualCost()` - Different for each student/faculty type
- `getRole()` - Returns specific role (Student, Professor, etc.)
- `performDuties()` - Different duties for each role
- `toString()` - Custom string representation

**Example Pattern:**
```java
// In Person class
public void displayInfo() {
    // Display basic person information
}

// In Student class
@Override
public void displayInfo() {
    super.displayInfo();  // Call parent method
    // Add student-specific information
}

// In UndergraduateStudent class
@Override
public void displayInfo() {
    super.displayInfo();  // Call Student's method
    // Add undergraduate-specific information
}
```

### **3. Protected Access Mastery**

Use protected members for family sharing:

```java
// In Person class
protected void logActivity(String activity) {
    // Available to all subclasses
}

protected boolean validateEmail(String email) {
    // Shared validation logic
}

protected String generateId(String prefix) {
    // Shared ID generation
}
```

### **4. Abstract Methods (Advanced)**

Make some methods abstract to force implementation:

```java
// In Person class
public abstract double calculateAnnualCost();
public abstract void performDuties();
public abstract String getSpecificRole();
```

---

## **The Specialized Classes Requirements**

### **UndergraduateStudent Specialization**
- **Additional Fields:** `String dormRoom`, `boolean hasScholarship`
- **Specialized Behavior:** Different GPA calculation, scholarship eligibility
- **Override:** `calculateAnnualCost()` - includes tuition, room, board
- **Unique Methods:** `applyForScholarship()`, `selectDormRoom()`

### **GraduateStudent Specialization**
- **Additional Fields:** `String advisor`, `String thesisTitle`, `boolean isTA`
- **Specialized Behavior:** Higher academic standards, research focus
- **Override:** `calculateAnnualCost()` - may have assistantship discounts
- **Unique Methods:** `selectAdvisor()`, `defendThesis()`, `applyForTA()`

### **InternationalStudent Specialization**
- **Additional Fields:** `String country`, `String visaStatus`, `boolean needsI20`
- **Specialized Behavior:** Additional visa requirements, higher fees
- **Override:** `calculateAnnualCost()` - includes international fees
- **Unique Methods:** `renewVisa()`, `requestI20()`, `reportToISO()`

### **Professor Specialization**
- **Additional Fields:** `String[] publicationsList`, `int numberOfPublications`
- **Specialized Behavior:** Research and teaching balance, tenure track
- **Override:** `performDuties()` - teaching, research, service
- **Unique Methods:** `publishPaper()`, `applyForTenure()`, `mentorStudent()`

### **AssistantProfessor Specialization**
- **Additional Fields:** `int yearsUntilTenure`, `boolean onTenureTrack`
- **Specialized Behavior:** Heavy research focus, tenure pressure
- **Override:** `calculateAnnualCost()` - university pays salary
- **Unique Methods:** `prepareForTenure()`, `buildResearchProgram()`

### **Researcher Specialization**
- **Additional Fields:** `String[] activeGrants`, `double grantFunding`
- **Specialized Behavior:** Research-only focus, grant dependency
- **Override:** `performDuties()` - primarily research and publications
- **Unique Methods:** `applyForGrant()`, `manageResearchTeam()`

---

## **The Sacred Operations to Implement**

### **Academic Management Operations**

1. **Enrollment System:**
   ```java
   // For Students
   public boolean enrollInCourse(String courseName)
   public boolean dropCourse(String courseName)
   public void calculateSemesterGPA(double[] grades)
   ```

2. **Teaching System:**
   ```java
   // For Faculty
   public boolean assignCourse(String courseName)
   public void gradingSession(String courseName, double[] studentGrades)
   public void holdOfficeHours()
   ```

3. **University Integration:**
   ```java
   // For all Person types
   public void registerWithUniversity(UniversitySystem system)
   public void updateContactInfo(String email, String phone, String address)
   public void requestTranscript() // Students only
   public void submitTimesheet() // Faculty only
   ```

### **Financial Management Operations**

1. **Cost Calculations:**
   ```java
   public abstract double calculateAnnualCost();
   public double calculateSemesterCost();
   public void processPayment(double amount); // Students
   public void processSalary(); // Faculty
   ```

2. **Financial Aid:**
   ```java
   // Students
   public boolean applyForFinancialAid();
   public void acceptScholarship(double amount);
   
   // Faculty
   public void negotiateSalary(double proposedSalary);
   public void applyForResearchFunding(double amount);
   ```

---

## **The Advanced Challenges**

### **Multi-Level Inheritance Mastery**

1. **Constructor Chain Validation:**
   ```java
   // Ensure every constructor properly calls super() first
   // Validate all inherited data before adding new data
   // Maintain proper initialization order throughout chain
   ```

2. **Method Override Chains:**
   ```java
   // Each level should call super method when appropriate
   // Add level-specific behavior without breaking parent functionality
   // Demonstrate proper use of super.method() calls
   ```

### **Protected Access Patterns**

1. **Family Helper Methods:**
   ```java
   // Create protected utility methods in base classes
   // Show proper usage in subclasses
   // Demonstrate why protected is better than private for inheritance
   ```

2. **Shared Validation Logic:**
   ```java
   // Common validation methods available to entire family
   // Specialized validation that builds upon base validation
   // Error handling that respects inheritance hierarchy
   ```

### **Polymorphic Usage Preparation**

1. **Common Interface Design:**
   ```java
   // Design methods that work polymorphically
   // Ensure consistent method signatures across hierarchy
   // Prepare for Pillar III (Polymorphism) usage
   ```

2. **IS-A Relationship Validation:**
   ```java
   // Demonstrate that all relationships make logical sense
   // Show instanceof usage for type checking
   // Validate that inheritance is used appropriately
   ```

---

## **The Supporting Systems**

### **UniversitySystem Class**

```java
class UniversitySystem {
    // Manage all Person objects (Students and Faculty)
    // Demonstrate polymorphic storage and operations
    // Handle registration, enrollment, employment
    // Generate university-wide reports
}
```

### **Course Class**

```java
class Course {
    // Represent academic courses
    // Track enrolled students and assigned faculty
    // Manage prerequisites and scheduling
}
```

### **Department Class**

```java
class Department {
    // Represent academic departments
    // Manage faculty assignments
    // Track departmental statistics
}
```

---

## **The Mastery Demonstrations**

Your main method must showcase:

### **1. Inheritance Hierarchy Creation**
```java
// Create instances of all classes in the hierarchy
// Show constructor chaining working properly
// Demonstrate each class adds its own specialization
```

### **2. Method Overriding in Action**
```java
// Show same method name producing different behavior
// Demonstrate super() usage in overridden methods
// Display how each level adds its own functionality
```

### **3. Protected Access Usage**
```java
// Show subclasses accessing protected members from parents
// Demonstrate protected methods being used by children
// Show that protected members are not accessible from outside
```

### **4. Polymorphic Preparation**
```java
// Store different types in Person[] array
// Call overridden methods polymorphically
// Demonstrate IS-A relationships working
```

### **5. Real-World University Operations**
```java
// Complete student enrollment workflow
// Faculty teaching assignment process
// University-wide reporting and statistics
```

---

## **The Bonus Challenges** (For the Truly Ambitious)

### **Master Level Enhancements**

1. **Academic Calendar System:** Integrate semester/quarter system with all operations
2. **Grade Management:** Complete grading system with transcripts and GPA calculations
3. **Research Collaboration:** System for faculty-student research partnerships
4. **International Programs:** Study abroad and exchange student management
5. **Alumni System:** Graduated students become Alumni class (new inheritance branch)

### **Grandmaster Achievement**

Create a **complete academic lifecycle** where:
- Students progress through all academic levels (Freshman → Graduate)
- Faculty advance through academic ranks (Assistant → Full Professor)
- System handles transitions while maintaining data integrity
- All using proper inheritance relationships and method overriding
- Demonstrate multi-generational inheritance (Person → Student → Undergrad → Senior)

---

## **The Sacred Validation Criteria**

Your solution will be judged on:

### **Inheritance Excellence**
- ✅ Proper inheritance hierarchy with logical IS-A relationships
- ✅ Correct use of extends keyword and super() calls
- ✅ All constructors properly chain to parent constructors
- ✅ Method overriding used appropriately with @Override annotation
- ✅ Protected access used correctly for family sharing

### **Code Reusability**
- ✅ Common behavior properly placed in base classes
- ✅ Specialized behavior appropriately placed in subclasses
- ✅ No code duplication across inheritance hierarchy
- ✅ Proper abstraction levels throughout the system
- ✅ Easy to extend with new Person types

### **Object-Oriented Design**
- ✅ All relationships make logical real-world sense
- ✅ Proper separation of concerns across classes
- ✅ Consistent interface design across hierarchy
- ✅ Appropriate use of abstract methods where needed
- ✅ Clean, maintainable inheritance structure

### **System Integration**
- ✅ All classes work together as cohesive system
- ✅ University operations use inheritance properly
- ✅ Polymorphic usage demonstrated effectively
- ✅ Complete workflows from enrollment to graduation
- ✅ Realistic university business logic implemented

---

## **The Sacred Wisdom to Remember**

As you forge this trial, remember the core principles:

1. **"IS-A Test"** - Every inheritance relationship must pass the "IS-A" test
2. **"Super First"** - Always call super() as the first statement in constructors
3. **"Override Wisely"** - Only override methods when you need specialized behavior
4. **"Protect the Family"** - Use protected for members that children should access
5. **"Chain Responsibly"** - Each level should add value while honoring ancestors

*Inheritance is about building upon proven foundations while reaching toward new possibilities. Your university system should honor the wisdom of its base classes while enabling each specialization to contribute its unique gifts.*

---

## **The Path to Glory**

1. **Design your hierarchy** - Plan the IS-A relationships carefully
2. **Implement base classes** - Start with Person, then Student/Faculty
3. **Add specializations** - Build each specialized class properly
4. **Override methods** - Implement specialized behavior thoughtfully
5. **Test inheritance** - Verify all relationships work correctly
6. **Add university operations** - Integrate classes into complete system
7. **Demonstrate polymorphism** - Show inheritance enabling flexible usage
8. **Polish and perfect** - Clean, professional, extensible design

*Go forth, seeker. Create a University Management System that is not merely a collection of related classes, but a living family tree where each class stands upon the shoulders of its ancestors while reaching toward its own unique destiny.*

**The Bloodline awaits your mastery!**
