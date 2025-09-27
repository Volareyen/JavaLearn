# 🎯 **The Pupil's Trial: University Management System**
*Mastering the Sacred Art of Object Creation*

---

## **🎓 THE SACRED CHALLENGE**

*Young seeker, you have witnessed the divine power of Constructors through the banking realm. Now, you must demonstrate your mastery by creating a comprehensive **University Management System** that showcases the sacred art of object creation in all its forms.*

*This trial will test your understanding of:*
- **Constructor Design** - Creating multiple pathways for object creation
- **Parameter Validation** - Ensuring objects are born in valid states
- **Constructor Chaining** - Avoiding code duplication through elegant design
- **Factory Methods** - Providing meaningful, self-documenting object creation
- **Immutable Objects** - Creating objects that cannot be changed after birth
- **Complex Initialization** - Handling sophisticated business logic in constructors

---

## **🏛️ THE DOMAIN: ACADEMIC INSTITUTION ECOSYSTEM**

*Your mission is to model a university system where different entities are created through various constructor patterns. Each class should demonstrate mastery of constructor principles while modeling realistic academic scenarios.*

### **🎓 REQUIRED ACADEMIC ENTITIES**

#### **👨‍🎓 Student (Primary Entity)**
*Every Student should have:*

**IMMUTABLE STATE (Set Once at Creation):**
- **Student ID** - Unique identifier (format: "STU" + 6 digits)
- **Full Name** - Student's complete name
- **Date of Birth** - Birth date for age calculation
- **Email Address** - University email
- **Enrollment Date** - When they started at the university

**MUTABLE STATE (Can Change Over Time):**
- **Major** - Current field of study
- **GPA** - Current grade point average (0.0-4.0)
- **Credit Hours Completed** - Total credits earned
- **Academic Standing** - "Good Standing", "Probation", "Suspended"
- **Graduation Status** - Whether they've graduated
- **Current Semester** - Which semester they're in

**CONSTRUCTOR REQUIREMENTS:**
1. **New Student Constructor** - For first-time enrollment
2. **Transfer Student Constructor** - For students transferring with credits
3. **Graduate Student Constructor** - For graduate program enrollment
4. **International Student Constructor** - With additional visa/country information
5. **Factory Method: `createHonorsStudent()`** - For honors program students
6. **Factory Method: `fromTranscriptData()`** - Loading from existing records

#### **👨‍🏫 Professor (Secondary Entity)**
*Every Professor should have:*

**IMMUTABLE STATE:**
- **Employee ID** - Unique identifier
- **Full Name** - Professor's name
- **Department** - Academic department
- **Hire Date** - When they started
- **Highest Degree** - PhD, Masters, etc.

**MUTABLE STATE:**
- **Title** - Assistant, Associate, Full Professor
- **Salary** - Current annual salary
- **Tenure Status** - Whether they have tenure
- **Courses Teaching** - Current course load
- **Research Areas** - Fields of expertise

**CONSTRUCTOR REQUIREMENTS:**
1. **New Faculty Constructor** - For newly hired professors
2. **Tenured Faculty Constructor** - For professors with tenure
3. **Visiting Faculty Constructor** - For temporary appointments
4. **Factory Method: `createDepartmentHead()`** - For department leadership
5. **Factory Method: `promoteToTenure()`** - Converting from non-tenured

#### **📚 Course (Tertiary Entity)**
*Every Course should have:*

**IMMUTABLE STATE:**
- **Course Code** - Unique identifier (e.g., "CS101")
- **Course Title** - Name of the course
- **Credit Hours** - How many credits the course is worth
- **Department** - Which department offers it

**MUTABLE STATE:**
- **Instructor** - Professor teaching the course
- **Enrolled Students** - List of students in the course
- **Room Assignment** - Where the course meets
- **Schedule** - Meeting times
- **Max Enrollment** - Maximum students allowed

**CONSTRUCTOR REQUIREMENTS:**
1. **Standard Course Constructor** - For regular courses
2. **Lab Course Constructor** - For courses with lab components
3. **Online Course Constructor** - For distance learning
4. **Factory Method: `createPrerequisiteCourse()`** - With prerequisite validation
5. **Copy Constructor** - For creating course sections

---

## **⚔️ THE TRIALS YOU MUST COMPLETE**

### **🥇 Trial 1: Constructor Foundation (Core Requirement)**
*Create all three entity classes with comprehensive constructor patterns.*

**Requirements:**
1. **Multiple Constructors**: Each class must have at least 4 different constructors
2. **Constructor Chaining**: Use `this()` to eliminate code duplication
3. **Parameter Validation**: Validate all parameters with meaningful error messages
4. **Immutable Fields**: Use `final` for fields that shouldn't change
5. **Default Values**: Provide sensible defaults for optional parameters
6. **Documentation**: Clear JavaDoc for each constructor explaining its purpose

### **🥈 Trial 2: Advanced Creation Patterns (Advanced Requirement)**
*Implement sophisticated object creation techniques.*

**Requirements:**
1. **Factory Methods**: At least 2 factory methods per class with descriptive names
2. **Private Constructors**: Use private constructors with factory methods where appropriate
3. **Copy Constructors**: Create objects based on existing objects
4. **Builder Pattern**: Implement a Builder for the most complex class
5. **Validation Logic**: Complex business rule validation in constructors
6. **Error Handling**: Graceful handling of invalid construction attempts

### **🥉 Trial 3: System Integration (Master Level)**
*Create a University class that orchestrates all entity creation.*

**Requirements:**
1. **University Management**: Create and manage students, professors, and courses
2. **Enrollment System**: Students can enroll in courses through proper object creation
3. **Academic Records**: Track student progress through object state management
4. **Reporting System**: Generate reports showing the power of well-constructed objects
5. **Data Persistence Simulation**: Load/save functionality using constructors
6. **Complex Scenarios**: Handle real-world academic scenarios

---

## **📋 DETAILED SPECIFICATIONS**

### **Student Class Specifications**

```java
class Student {
    // IMMUTABLE STATE
    private final String studentId;
    private final String fullName;
    private final LocalDate dateOfBirth;
    private final String email;
    private final LocalDate enrollmentDate;
    
    // MUTABLE STATE
    private String major;
    private double gpa;
    private int creditHoursCompleted;
    private String academicStanding;
    private boolean hasGraduated;
    private int currentSemester;
    
    // REQUIRED CONSTRUCTORS
    public Student(String name, LocalDate birthDate, String major, String email)
    public Student(String name, LocalDate birthDate, String major, String email, 
                   int transferCredits, double transferGPA)
    public Student(String name, LocalDate birthDate, String graduateProgram, 
                   String email, String bachelorsDegree)
    public Student(String name, LocalDate birthDate, String major, String email, 
                   String country, String visaStatus)
    
    // FACTORY METHODS
    public static Student createHonorsStudent(String name, LocalDate birthDate, 
                                            String major, String email)
    public static Student fromTranscriptData(String transcriptData)
    
    // ESSENTIAL METHODS
    public void enrollInCourse(Course course)
    public void updateGPA(double newGPA)
    public boolean canGraduate()
    public void displayStudentInfo()
}
```

### **Professor Class Specifications**

```java
class Professor {
    // IMMUTABLE STATE
    private final String employeeId;
    private final String fullName;
    private final String department;
    private final LocalDate hireDate;
    private final String highestDegree;
    
    // MUTABLE STATE
    private String title;
    private double salary;
    private boolean hasTenure;
    private String[] coursesTeaching;
    private String[] researchAreas;
    
    // REQUIRED CONSTRUCTORS
    public Professor(String name, String department, String degree, double salary)
    public Professor(String name, String department, String degree, double salary, 
                     boolean tenure, LocalDate hireDate)
    public Professor(String name, String department, String degree, 
                     int contractMonths, double monthlySalary)
    
    // FACTORY METHODS
    public static Professor createDepartmentHead(String name, String department, 
                                               String degree, double salary)
    public static Professor promoteToTenure(Professor professor)
    
    // ESSENTIAL METHODS
    public void assignCourse(Course course)
    public void updateSalary(double newSalary)
    public void displayProfessorInfo()
}
```

### **Course Class Specifications**

```java
class Course {
    // IMMUTABLE STATE
    private final String courseCode;
    private final String courseTitle;
    private final int creditHours;
    private final String department;
    
    // MUTABLE STATE
    private Professor instructor;
    private Student[] enrolledStudents;
    private String roomAssignment;
    private String schedule;
    private int maxEnrollment;
    private int currentEnrollment;
    
    // REQUIRED CONSTRUCTORS
    public Course(String code, String title, int credits, String department)
    public Course(String code, String title, int credits, String department, 
                  String labRoom, int labHours)
    public Course(String code, String title, int credits, String department, 
                  String platform, String meetingTimes)
    
    // FACTORY METHODS
    public static Course createPrerequisiteCourse(String code, String title, 
                                                int credits, String department, 
                                                String[] prerequisites)
    public Course createSection(String sectionNumber)
    
    // ESSENTIAL METHODS
    public boolean enrollStudent(Student student)
    public void assignInstructor(Professor professor)
    public void displayCourseInfo()
}
```

---

## **🎨 ADVANCED FEATURES (Optional Mastery)**

### **🏗️ Builder Pattern Implementation**
*For the most complex class (Student), implement a Builder pattern:*

```java
public static class StudentBuilder {
    private String name;
    private LocalDate birthDate;
    private String major;
    private String email;
    private String country;
    private int transferCredits;
    private double transferGPA;
    private boolean isHonorsStudent;
    
    public StudentBuilder name(String name) { /* ... */ }
    public StudentBuilder birthDate(LocalDate date) { /* ... */ }
    public StudentBuilder major(String major) { /* ... */ }
    public StudentBuilder international(String country) { /* ... */ }
    public StudentBuilder transferStudent(int credits, double gpa) { /* ... */ }
    public StudentBuilder honorsProgram() { /* ... */ }
    public Student build() { /* ... */ }
}

// Usage: Student student = new Student.StudentBuilder()
//                             .name("John Doe")
//                             .birthDate(LocalDate.of(2000, 5, 15))
//                             .major("Computer Science")
//                             .international("Canada")
//                             .honorsProgram()
//                             .build();
```

### **🏛️ University Management System**
```java
class University {
    private String universityName;
    private Student[] students;
    private Professor[] professors;
    private Course[] courses;
    
    // University constructor and management methods
    public University(String name, int maxStudents, int maxProfessors, int maxCourses)
    public Student admitStudent(String name, LocalDate birthDate, String major, String email)
    public Professor hireProfessor(String name, String department, String degree, double salary)
    public Course createCourse(String code, String title, int credits, String department)
    public void generateUniversityReport()
}
```

---

## **🧪 TEST SCENARIOS YOU MUST DEMONSTRATE**

### **Scenario 1: Student Lifecycle Management**
```java
// Create different types of students using various constructors
// Show validation working (reject invalid data)
// Demonstrate student enrollment and progress tracking
// Show immutable fields cannot be changed
```

### **Scenario 2: Faculty Management**
```java
// Hire new professors using different constructors
// Create department heads using factory methods
// Show tenure promotion process
// Demonstrate professor-course assignment
```

### **Scenario 3: Course Management**
```java
// Create various course types (standard, lab, online)
// Show prerequisite validation
// Demonstrate enrollment limits and waitlists
// Create course sections using copy constructors
```

### **Scenario 4: University System Integration**
```java
// Show complete university operations
// Demonstrate object interaction through constructors
// Generate comprehensive reports
// Show error handling for invalid operations
```

---

## **📊 SUCCESS CRITERIA**

### **🎯 Constructor Design Excellence (35%)**
- Multiple constructors per class with clear purposes
- Proper constructor chaining to eliminate duplication
- Comprehensive parameter validation with clear error messages
- Appropriate use of final fields for immutable state

### **🏗️ Advanced Creation Patterns (30%)**
- Meaningful factory methods with descriptive names
- Proper use of private constructors where appropriate
- Builder pattern implementation (if attempted)
- Copy constructors and object cloning

### **✅ Validation and Error Handling (20%)**
- Robust parameter validation in all constructors
- Clear, helpful error messages for invalid inputs
- Graceful handling of edge cases
- Prevention of invalid object creation

### **🎭 System Integration (15%)**
- Objects working together through proper construction
- University management system functionality
- Realistic academic workflow implementation
- Professional code organization and design

---

## **💡 DESIGN GUIDANCE**

### **🧠 Constructor Design Principles**
1. **Single Responsibility**: Each constructor should have a clear, specific purpose
2. **Fail Fast**: Validate parameters immediately and provide clear error messages
3. **Immutability**: Use final fields for data that shouldn't change after creation
4. **Chaining**: Use this() calls to eliminate code duplication between constructors
5. **Documentation**: Clearly document what each constructor does and when to use it

### **🎯 Validation Best Practices**
1. **Null Checks**: Always validate that required parameters are not null
2. **Range Validation**: Check that numeric values are within acceptable ranges
3. **Format Validation**: Ensure strings match expected formats (emails, IDs, etc.)
4. **Business Rules**: Enforce domain-specific rules (GPA between 0.0-4.0, etc.)
5. **Early Validation**: Check all parameters before starting initialization

### **🔧 Implementation Strategy**
1. **Start Simple**: Begin with basic constructors, then add complexity
2. **Test Early**: Create test objects after each constructor to verify it works
3. **Validate Everything**: Don't assume inputs will be valid
4. **Think Real-World**: Ask "How would a real university system work?"
5. **Document Thoroughly**: Future you will thank present you

---

## **🌟 THE MASTER'S EXPECTATIONS**

*When you complete this trial, you should be able to confidently demonstrate:*

- ✅ **Constructor Mastery**: Multiple creation patterns for different scenarios
- ✅ **Validation Excellence**: Robust parameter checking and error handling
- ✅ **Design Wisdom**: Appropriate use of immutability and encapsulation
- ✅ **System Thinking**: Objects that work together through proper construction
- ✅ **Professional Quality**: Code that would be suitable for a real university system

---

## **🚀 READY TO BEGIN?**

*Remember, wise seeker:*

*"A constructor is more than initialization code - it is a contract with the world. It promises: 'Give me valid inputs, and I will create a valid, useful object.' Honor this contract in every constructor you write."*

*"Think like a university administrator: What information do you need to create a student record? What validations would you perform? What different scenarios might arise? Your answers will guide you to excellent constructor design."*

*"Every object should be born perfect - fully initialized, validated, and ready to serve its purpose. The constructor is the guardian of this perfection."*

**Begin with the Student class. Create multiple constructors for different enrollment scenarios, validate all parameters thoroughly, and watch your objects come to life with perfect initial state!**

*May your Constructors be wise guardians of object integrity, your validations be thorough sentinels against invalid data, and your objects be born in perfect readiness to serve their noble purpose.*

**Go forth and master the sacred art of Object Creation!**

---

*When you have completed your trial, compare your solution with the master's implementation in the `_Solution/` directory. Remember - there are many excellent ways to design constructors. Focus on creating objects that are born valid, useful, and ready to fulfill their purpose.*
