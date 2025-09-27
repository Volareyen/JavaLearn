/**
 * Student Grade Management System
 * 
 * A practical example demonstrating variables and data types
 * in a realistic scenario - managing student information and grades.
 * 
 * This program shows how different data types are used together
 * to model real-world entities and perform meaningful calculations.
 */
public class _PracticalExample {
    
    public static void main(String[] args) {
        
        // ============================================================================
        // STUDENT PERSONAL INFORMATION
        // ============================================================================
        
        // String for textual data
        String studentFirstName = "Emma";
        String studentLastName = "Johnson";
        String studentEmail = "emma.johnson@university.edu";
        
        // int for identification numbers and counts
        int studentId = 20241234;
        int currentYear = 2024;
        int enrollmentYear = 2022;
        
        // byte for small numeric values (grade levels, ages)
        byte gradeLevel = 12;           // Senior year
        byte studentAge = 18;
        
        // boolean for yes/no information
        boolean isHonorsStudent = true;
        boolean hasScholarship = false;
        boolean isInternationalStudent = false;
        
        // char for single character codes
        char genderCode = 'F';          // F for Female, M for Male
        char campusCode = 'M';          // M for Main campus, N for North, S for South
        
        // ============================================================================
        // ACADEMIC PERFORMANCE DATA
        // ============================================================================
        
        // double for precise grade calculations (preferred for decimal math)
        double mathGrade = 92.5;
        double scienceGrade = 88.75;
        double englishGrade = 95.0;
        double historyGrade = 91.25;
        double artGrade = 87.5;
        
        // int for credit hours (whole numbers)
        int mathCredits = 4;
        int scienceCredits = 3;
        int englishCredits = 3;
        int historyCredits = 3;
        int artCredits = 2;
        
        // Calculate total credits
        int totalCredits = mathCredits + scienceCredits + englishCredits + 
                          historyCredits + artCredits;
        
        // ============================================================================
        // GRADE POINT AVERAGE CALCULATION
        // ============================================================================
        
        // Calculate weighted grade points (grade × credits for each subject)
        double mathPoints = mathGrade * mathCredits;
        double sciencePoints = scienceGrade * scienceCredits;
        double englishPoints = englishGrade * englishCredits;
        double historyPoints = historyGrade * historyCredits;
        double artPoints = artGrade * artCredits;
        
        // Calculate total grade points
        double totalGradePoints = mathPoints + sciencePoints + englishPoints + 
                                 historyPoints + artPoints;
        
        // Calculate GPA (Grade Point Average)
        double gpa = totalGradePoints / totalCredits;
        
        // Determine letter grade based on GPA
        char letterGrade;
        if (gpa >= 90.0) {
            letterGrade = 'A';
        } else if (gpa >= 80.0) {
            letterGrade = 'B';
        } else if (gpa >= 70.0) {
            letterGrade = 'C';
        } else if (gpa >= 60.0) {
            letterGrade = 'D';
        } else {
            letterGrade = 'F';
        }
        
        // Determine if student qualifies for Dean's List (GPA >= 85.0)
        boolean isOnDeansList = gpa >= 85.0;
        
        // ============================================================================
        // FINANCIAL INFORMATION
        // ============================================================================
        
        // long for large monetary values (tuition costs)
        long annualTuition = 45000L;        // $45,000 per year
        long totalTuitionPaid = annualTuition * (currentYear - enrollmentYear + 1);
        
        // double for precise monetary calculations
        double scholarshipAmount = 7500.50; // Scholarship money received
        double bookExpenses = 1200.75;      // Cost of textbooks
        double labFees = 350.00;            // Laboratory fees
        
        // Calculate net cost
        double totalExpenses = annualTuition + bookExpenses + labFees;
        double netCost = totalExpenses - scholarshipAmount;
        
        // ============================================================================
        // CONSTANTS FOR SYSTEM CONFIGURATION
        // ============================================================================
        
        final double HONORS_GPA_THRESHOLD = 85.0;
        final int MAX_CREDIT_HOURS = 18;
        final String UNIVERSITY_NAME = "State University";
        final char PASSING_GRADE = 'D';
        
        // Check if student is taking too many credits
        boolean isOverloaded = totalCredits > MAX_CREDIT_HOURS;
        
        // ============================================================================
        // DISPLAY STUDENT REPORT
        // ============================================================================
        
        System.out.println("=".repeat(60));
        System.out.println("           STUDENT ACADEMIC REPORT");
        System.out.println("=".repeat(60));
        
        // Personal Information Section
        System.out.println("\nPERSONAL INFORMATION:");
        System.out.println("Name: " + studentFirstName + " " + studentLastName);
        System.out.println("Student ID: " + studentId);
        System.out.println("Email: " + studentEmail);
        System.out.println("Age: " + studentAge + " years old");
        System.out.println("Grade Level: " + gradeLevel);
        System.out.println("Gender: " + genderCode);
        System.out.println("Campus: " + campusCode);
        System.out.println("International Student: " + (isInternationalStudent ? "Yes" : "No"));
        
        // Academic Performance Section
        System.out.println("\nACADEMIC PERFORMANCE:");
        System.out.println("Mathematics (" + mathCredits + " credits): " + mathGrade + "%");
        System.out.println("Science (" + scienceCredits + " credits): " + scienceGrade + "%");
        System.out.println("English (" + englishCredits + " credits): " + englishGrade + "%");
        System.out.println("History (" + historyCredits + " credits): " + historyGrade + "%");
        System.out.println("Art (" + artCredits + " credits): " + artGrade + "%");
        
        // Summary Statistics
        System.out.println("\nSUMMARY:");
        System.out.println("Total Credits: " + totalCredits);
        System.out.println("Total Grade Points: " + String.format("%.2f", totalGradePoints));
        System.out.println("Grade Point Average: " + String.format("%.2f", gpa) + "%");
        System.out.println("Letter Grade: " + letterGrade);
        System.out.println("Honors Student: " + (isHonorsStudent ? "Yes" : "No"));
        System.out.println("Dean's List: " + (isOnDeansList ? "Yes" : "No"));
        System.out.println("Credit Overload: " + (isOverloaded ? "Yes" : "No"));
        
        // Financial Information
        System.out.println("\nFINANCIAL INFORMATION:");
        System.out.println("Annual Tuition: $" + String.format("%,d", annualTuition));
        System.out.println("Total Tuition Paid: $" + String.format("%,d", totalTuitionPaid));
        System.out.println("Scholarship Amount: $" + String.format("%.2f", scholarshipAmount));
        System.out.println("Book Expenses: $" + String.format("%.2f", bookExpenses));
        System.out.println("Lab Fees: $" + String.format("%.2f", labFees));
        System.out.println("Net Annual Cost: $" + String.format("%.2f", netCost));
        System.out.println("Has Scholarship: " + (hasScholarship ? "Yes" : "No"));
        
        // System Information
        System.out.println("\nSYSTEM INFORMATION:");
        System.out.println("University: " + UNIVERSITY_NAME);
        System.out.println("Report Year: " + currentYear);
        System.out.println("Enrollment Year: " + enrollmentYear);
        System.out.println("Years Enrolled: " + (currentYear - enrollmentYear + 1));
        
        System.out.println("\n" + "=".repeat(60));
        
        // ============================================================================
        // DEMONSTRATING TYPE CONVERSION
        // ============================================================================
        
        System.out.println("\nTYPE CONVERSION EXAMPLES:");
        
        // Automatic conversion (widening)
        int gradeAsInt = (int) mathGrade;    // 92.5 becomes 92 (truncated)
        long studentIdAsLong = studentId;    // int automatically becomes long
        double ageAsDouble = studentAge;     // byte automatically becomes double
        
        System.out.println("Math grade as integer (truncated): " + gradeAsInt);
        System.out.println("Student ID as long: " + studentIdAsLong);
        System.out.println("Age as double: " + ageAsDouble);
        
        // String conversion for display
        String gpaAsString = String.valueOf(gpa);
        String isHonorsAsString = String.valueOf(isHonorsStudent);
        
        System.out.println("GPA as string: \"" + gpaAsString + "\"");
        System.out.println("Honors status as string: \"" + isHonorsAsString + "\"");
        
        // ============================================================================
        // PRACTICAL INSIGHTS
        // ============================================================================
        
        System.out.println("\nDATA TYPE CHOICES EXPLAINED:");
        System.out.println("• String: Used for names, email (textual data)");
        System.out.println("• int: Used for IDs, years, credits (whole numbers)");
        System.out.println("• byte: Used for age, grade level (small whole numbers)");
        System.out.println("• double: Used for grades, GPA, money (precise decimals)");
        System.out.println("• boolean: Used for yes/no questions (true/false states)");
        System.out.println("• char: Used for single character codes (gender, campus)");
        System.out.println("• long: Used for large numbers (tuition, total costs)");
        System.out.println("• final: Used for constants that never change");
    }
}

/*
 * KEY LEARNING POINTS:
 * 
 * 1. DATA TYPE SELECTION:
 *    - Choose the most appropriate type for your data
 *    - Consider range, precision, and memory usage
 *    - Use meaningful variable names
 * 
 * 2. REAL-WORLD MODELING:
 *    - Different aspects of entities require different data types
 *    - Combine multiple variables to represent complex information
 *    - Use constants for values that don't change
 * 
 * 3. CALCULATIONS AND CONVERSIONS:
 *    - Perform calculations using appropriate data types
 *    - Be aware of automatic vs. manual type conversion
 *    - Understand precision loss in casting operations
 * 
 * 4. BOOLEAN LOGIC:
 *    - Use boolean variables for flags and conditions
 *    - Combine with conditional logic for decision making
 *    - Clear naming helps code readability
 */
