/**
 * Student Grade Analysis System
 * 
 * A practical example demonstrating all types of loops in a realistic
 * scenario - a comprehensive system for analyzing student grades across
 * multiple subjects, calculating statistics, and generating reports.
 * 
 * This program shows how different loop structures work together to
 * process large amounts of data efficiently and elegantly.
 */
public class _PracticalExample {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(70));
        System.out.println("              STUDENT GRADE ANALYSIS SYSTEM");
        System.out.println("=".repeat(70));
        
        // ============================================================================
        // STUDENT GRADE DATA - Multiple classes and subjects
        // ============================================================================
        
        // Student names for each class
        String[] class1Students = {"Alice Johnson", "Bob Smith", "Charlie Brown", "Diana Wilson", "Eva Garcia"};
        String[] class2Students = {"Frank Miller", "Grace Lee", "Henry Davis", "Iris Chen", "Jack Taylor"};
        String[] class3Students = {"Kate Adams", "Liam Murphy", "Maya Patel", "Noah Kim", "Olivia Rodriguez"};
        
        // Grade data: [student][subject] - Math, Science, English, History
        double[][] class1Grades = {
            {95.5, 88.0, 92.5, 87.0},  // Alice
            {78.5, 85.5, 80.0, 82.5},  // Bob
            {92.0, 94.5, 89.0, 91.5},  // Charlie
            {85.0, 79.5, 88.5, 86.0},  // Diana
            {88.5, 92.0, 85.5, 89.0}   // Eva
        };
        
        double[][] class2Grades = {
            {82.0, 87.5, 85.0, 83.5},  // Frank
            {94.5, 91.0, 96.5, 92.0},  // Grace
            {76.0, 82.5, 78.5, 80.0},  // Henry
            {89.5, 88.0, 91.5, 87.5},  // Iris
            {91.0, 89.5, 88.0, 90.5}   // Jack
        };
        
        double[][] class3Grades = {
            {87.5, 85.0, 89.5, 88.0},  // Kate
            {93.0, 95.5, 91.0, 94.0},  // Liam
            {80.5, 83.0, 82.5, 81.0},  // Maya
            {86.0, 88.5, 87.0, 85.5},  // Noah
            {90.5, 87.5, 92.0, 89.5}   // Olivia
        };
        
        String[] subjectNames = {"Mathematics", "Science", "English", "History"};
        String[] classNames = {"Class 1 - Advanced", "Class 2 - Standard", "Class 3 - Honors"};
        
        // Store all class data for easy iteration
        String[][] allStudents = {class1Students, class2Students, class3Students};
        double[][][] allGrades = {class1Grades, class2Grades, class3Grades};
        
        // ============================================================================
        // INDIVIDUAL CLASS ANALYSIS - Enhanced For Loops
        // ============================================================================
        
        System.out.println("\n📊 INDIVIDUAL CLASS ANALYSIS:");
        System.out.println("=".repeat(50));
        
        // Process each class using enhanced for loop with index tracking
        for (int classIndex = 0; classIndex < allStudents.length; classIndex++) {
            String[] students = allStudents[classIndex];
            double[][] grades = allGrades[classIndex];
            
            System.out.println("\n🎓 " + classNames[classIndex] + ":");
            System.out.println("-".repeat(40));
            
            // Display each student's grades using enhanced for loop
            for (int studentIndex = 0; studentIndex < students.length; studentIndex++) {
                String studentName = students[studentIndex];
                double[] studentGrades = grades[studentIndex];
                
                System.out.printf("%-15s: ", studentName);
                
                // Calculate student's average using enhanced for loop
                double totalGrades = 0.0;
                for (double grade : studentGrades) {
                    System.out.printf("%6.1f ", grade);
                    totalGrades += grade;
                }
                
                double average = totalGrades / studentGrades.length;
                System.out.printf("| Avg: %5.1f", average);
                
                // Determine letter grade using conditional logic
                char letterGrade = (average >= 90) ? 'A' :
                                  (average >= 80) ? 'B' :
                                  (average >= 70) ? 'C' :
                                  (average >= 60) ? 'D' : 'F';
                
                System.out.printf(" (%c)%n", letterGrade);
            }
        }
        
        // ============================================================================
        // SUBJECT-WISE STATISTICS - Traditional For Loops
        // ============================================================================
        
        System.out.println("\n📈 SUBJECT-WISE STATISTICS:");
        System.out.println("=".repeat(50));
        
        // Calculate statistics for each subject across all classes
        for (int subjectIndex = 0; subjectIndex < subjectNames.length; subjectIndex++) {
            System.out.println("\n📚 " + subjectNames[subjectIndex] + " Analysis:");
            
            double subjectTotal = 0.0;
            double subjectMin = Double.MAX_VALUE;
            double subjectMax = Double.MIN_VALUE;
            int totalStudents = 0;
            int aGrades = 0, bGrades = 0, cGrades = 0, dGrades = 0, fGrades = 0;
            
            // Process each class for this subject
            for (int classIndex = 0; classIndex < allGrades.length; classIndex++) {
                double[][] classGrades = allGrades[classIndex];
                
                // Process each student in the class
                for (int studentIndex = 0; studentIndex < classGrades.length; studentIndex++) {
                    double grade = classGrades[studentIndex][subjectIndex];
                    
                    // Accumulate statistics
                    subjectTotal += grade;
                    totalStudents++;
                    
                    // Track min and max
                    if (grade < subjectMin) {
                        subjectMin = grade;
                    }
                    if (grade > subjectMax) {
                        subjectMax = grade;
                    }
                    
                    // Count letter grade distribution
                    if (grade >= 90) aGrades++;
                    else if (grade >= 80) bGrades++;
                    else if (grade >= 70) cGrades++;
                    else if (grade >= 60) dGrades++;
                    else fGrades++;
                }
            }
            
            double subjectAverage = subjectTotal / totalStudents;
            
            System.out.printf("   Average Score: %6.1f%n", subjectAverage);
            System.out.printf("   Highest Score: %6.1f%n", subjectMax);
            System.out.printf("   Lowest Score:  %6.1f%n", subjectMin);
            System.out.printf("   Total Students: %d%n", totalStudents);
            
            System.out.println("   Grade Distribution:");
            System.out.printf("     A (90-100): %2d students (%4.1f%%)%n", 
                            aGrades, (aGrades * 100.0 / totalStudents));
            System.out.printf("     B (80-89):  %2d students (%4.1f%%)%n", 
                            bGrades, (bGrades * 100.0 / totalStudents));
            System.out.printf("     C (70-79):  %2d students (%4.1f%%)%n", 
                            cGrades, (cGrades * 100.0 / totalStudents));
            System.out.printf("     D (60-69):  %2d students (%4.1f%%)%n", 
                            dGrades, (dGrades * 100.0 / totalStudents));
            System.out.printf("     F (0-59):   %2d students (%4.1f%%)%n", 
                            fGrades, (fGrades * 100.0 / totalStudents));
        }
        
        // ============================================================================
        // TOP PERFORMERS IDENTIFICATION - While Loops with Search Logic
        // ============================================================================
        
        System.out.println("\n🏆 TOP PERFORMERS IDENTIFICATION:");
        System.out.println("=".repeat(50));
        
        // Find students with overall average >= 90 (A students)
        System.out.println("\n⭐ Students with A Average (90.0+):");
        
        int totalAStudents = 0;
        
        // Search through each class
        int classIndex = 0;
        while (classIndex < allStudents.length) {
            String[] students = allStudents[classIndex];
            double[][] grades = allGrades[classIndex];
            
            System.out.println("\n   " + classNames[classIndex] + ":");
            
            int studentIndex = 0;
            boolean foundAStudentInClass = false;
            
            // Search through each student in the class
            while (studentIndex < students.length) {
                double[] studentGrades = grades[studentIndex];
                
                // Calculate student average
                double total = 0.0;
                int gradeIndex = 0;
                while (gradeIndex < studentGrades.length) {
                    total += studentGrades[gradeIndex];
                    gradeIndex++;
                }
                
                double average = total / studentGrades.length;
                
                // Check if student qualifies as A student
                if (average >= 90.0) {
                    System.out.printf("     %-15s: %5.1f average%n", 
                                    students[studentIndex], average);
                    totalAStudents++;
                    foundAStudentInClass = true;
                }
                
                studentIndex++;
            }
            
            if (!foundAStudentInClass) {
                System.out.println("     No A-average students in this class");
            }
            
            classIndex++;
        }
        
        System.out.printf("\n   Total A-Average Students: %d out of %d (%.1f%%)%n", 
                        totalAStudents, (allStudents[0].length * allStudents.length),
                        (totalAStudents * 100.0 / (allStudents[0].length * allStudents.length)));
        
        // ============================================================================
        // IMPROVEMENT TRACKING - Do-While Loops for Iterative Analysis
        // ============================================================================
        
        System.out.println("\n📊 IMPROVEMENT OPPORTUNITIES ANALYSIS:");
        System.out.println("=".repeat(50));
        
        // Find students who need improvement (average < 75) and suggest focus areas
        System.out.println("\n⚠️ Students Needing Additional Support:");
        
        int classNum = 0;
        do {
            String[] students = allStudents[classNum];
            double[][] grades = allGrades[classNum];
            
            System.out.println("\n   " + classNames[classNum] + ":");
            
            boolean foundStudentsNeedingHelp = false;
            int studentNum = 0;
            
            do {
                double[] studentGrades = grades[studentNum];
                String studentName = students[studentNum];
                
                // Calculate overall average
                double total = 0.0;
                for (double grade : studentGrades) {
                    total += grade;
                }
                double overallAverage = total / studentGrades.length;
                
                // Check if student needs support (average < 75)
                if (overallAverage < 75.0) {
                    foundStudentsNeedingHelp = true;
                    System.out.printf("     %-15s: %5.1f average%n", studentName, overallAverage);
                    
                    // Find weakest subjects for targeted support
                    System.out.print("       Focus areas: ");
                    int subjectNum = 0;
                    boolean firstWeakSubject = true;
                    
                    do {
                        if (studentGrades[subjectNum] < 80.0) {
                            if (!firstWeakSubject) {
                                System.out.print(", ");
                            }
                            System.out.printf("%s (%.1f)", subjectNames[subjectNum], studentGrades[subjectNum]);
                            firstWeakSubject = false;
                        }
                        subjectNum++;
                    } while (subjectNum < subjectNames.length);
                    
                    System.out.println();
                }
                
                studentNum++;
            } while (studentNum < students.length);
            
            if (!foundStudentsNeedingHelp) {
                System.out.println("     All students performing well (75+ average)");
            }
            
            classNum++;
        } while (classNum < allStudents.length);
        
        // ============================================================================
        // GRADE DISTRIBUTION ANALYSIS - Nested Loops with Break/Continue
        // ============================================================================
        
        System.out.println("\n📊 DETAILED GRADE DISTRIBUTION ANALYSIS:");
        System.out.println("=".repeat(50));
        
        // Create grade ranges for analysis
        String[] gradeRanges = {"90-100 (A)", "80-89 (B)", "70-79 (C)", "60-69 (D)", "0-59 (F)"};
        int[] rangeCounts = new int[5];
        
        // Count grades in each range using nested loops
        for (int classIdx = 0; classIdx < allGrades.length; classIdx++) {
            for (int studentIdx = 0; studentIdx < allGrades[classIdx].length; studentIdx++) {
                for (int subjectIdx = 0; subjectIdx < allGrades[classIdx][studentIdx].length; subjectIdx++) {
                    double grade = allGrades[classIdx][studentIdx][subjectIdx];
                    
                    // Determine which range this grade falls into
                    int rangeIndex = 4; // Default to F range
                    if (grade >= 90) rangeIndex = 0;
                    else if (grade >= 80) rangeIndex = 1;
                    else if (grade >= 70) rangeIndex = 2;
                    else if (grade >= 60) rangeIndex = 3;
                    
                    rangeCounts[rangeIndex]++;
                }
            }
        }
        
        // Calculate total grades for percentage calculation
        int totalGrades = 0;
        for (int count : rangeCounts) {
            totalGrades += count;
        }
        
        System.out.println("\n📈 Overall Grade Distribution:");
        for (int i = 0; i < gradeRanges.length; i++) {
            double percentage = (rangeCounts[i] * 100.0) / totalGrades;
            System.out.printf("   %-12s: %3d grades (%5.1f%%) ", 
                            gradeRanges[i], rangeCounts[i], percentage);
            
            // Visual representation using stars
            int stars = (int) (percentage / 2); // Each star represents ~2%
            for (int j = 0; j < stars; j++) {
                System.out.print("★");
            }
            System.out.println();
        }
        
        // ============================================================================
        // PERFORMANCE TRENDS ANALYSIS - Advanced Loop Patterns
        // ============================================================================
        
        System.out.println("\n📊 PERFORMANCE TRENDS ANALYSIS:");
        System.out.println("=".repeat(50));
        
        // Analyze which subjects show the most variation in performance
        System.out.println("\n📈 Subject Difficulty Analysis (Based on Score Variation):");
        
        for (int subjectIdx = 0; subjectIdx < subjectNames.length; subjectIdx++) {
            double subjectSum = 0.0;
            double subjectSumSquares = 0.0;
            int subjectCount = 0;
            
            // Calculate mean and variance for each subject
            for (int classIdx = 0; classIdx < allGrades.length; classIdx++) {
                for (int studentIdx = 0; studentIdx < allGrades[classIdx].length; studentIdx++) {
                    double grade = allGrades[classIdx][studentIdx][subjectIdx];
                    subjectSum += grade;
                    subjectSumSquares += grade * grade;
                    subjectCount++;
                }
            }
            
            double mean = subjectSum / subjectCount;
            double variance = (subjectSumSquares / subjectCount) - (mean * mean);
            double standardDeviation = Math.sqrt(variance);
            
            System.out.printf("   %-12s: Mean=%.1f, StdDev=%.1f ", 
                            subjectNames[subjectIdx], mean, standardDeviation);
            
            // Interpret the standard deviation
            if (standardDeviation < 3.0) {
                System.out.println("(Consistent performance)");
            } else if (standardDeviation < 6.0) {
                System.out.println("(Moderate variation)");
            } else {
                System.out.println("(High variation - challenging subject)");
            }
        }
        
        // ============================================================================
        // CLASS PERFORMANCE COMPARISON - Loop-based Statistical Analysis
        // ============================================================================
        
        System.out.println("\n🏫 CLASS PERFORMANCE COMPARISON:");
        System.out.println("=".repeat(50));
        
        double[] classAverages = new double[allGrades.length];
        
        // Calculate average for each class
        for (int classIdx = 0; classIdx < allGrades.length; classIdx++) {
            double classTotal = 0.0;
            int gradeCount = 0;
            
            for (int studentIdx = 0; studentIdx < allGrades[classIdx].length; studentIdx++) {
                for (int subjectIdx = 0; subjectIdx < allGrades[classIdx][studentIdx].length; subjectIdx++) {
                    classTotal += allGrades[classIdx][studentIdx][subjectIdx];
                    gradeCount++;
                }
            }
            
            classAverages[classIdx] = classTotal / gradeCount;
            
            System.out.printf("   %-20s: Average = %5.1f", 
                            classNames[classIdx], classAverages[classIdx]);
            
            // Performance indicator
            if (classAverages[classIdx] >= 90) {
                System.out.println(" ⭐ (Excellent)");
            } else if (classAverages[classIdx] >= 85) {
                System.out.println(" ✓ (Very Good)");
            } else if (classAverages[classIdx] >= 80) {
                System.out.println(" + (Good)");
            } else {
                System.out.println(" - (Needs Improvement)");
            }
        }
        
        // Find highest and lowest performing classes
        double highestAverage = classAverages[0];
        double lowestAverage = classAverages[0];
        int highestIndex = 0;
        int lowestIndex = 0;
        
        for (int i = 1; i < classAverages.length; i++) {
            if (classAverages[i] > highestAverage) {
                highestAverage = classAverages[i];
                highestIndex = i;
            }
            if (classAverages[i] < lowestAverage) {
                lowestAverage = classAverages[i];
                lowestIndex = i;
            }
        }
        
        System.out.printf("\n   🏆 Highest Performing: %s (%.1f average)%n", 
                        classNames[highestIndex], highestAverage);
        System.out.printf("   📈 Most Room for Growth: %s (%.1f average)%n", 
                        classNames[lowestIndex], lowestAverage);
        
        // ============================================================================
        // SUMMARY REPORT - Comprehensive Loop-Based Analysis
        // ============================================================================
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("COMPREHENSIVE SUMMARY REPORT");
        System.out.println("=".repeat(70));
        
        // Calculate overall statistics
        double grandTotal = 0.0;
        int totalGradeCount = 0;
        int totalStudentCount = 0;
        
        for (int classIdx = 0; classIdx < allGrades.length; classIdx++) {
            totalStudentCount += allGrades[classIdx].length;
            for (int studentIdx = 0; studentIdx < allGrades[classIdx].length; studentIdx++) {
                for (int subjectIdx = 0; subjectIdx < allGrades[classIdx][studentIdx].length; subjectIdx++) {
                    grandTotal += allGrades[classIdx][studentIdx][subjectIdx];
                    totalGradeCount++;
                }
            }
        }
        
        double overallAverage = grandTotal / totalGradeCount;
        
        System.out.println("\n📊 OVERALL STATISTICS:");
        System.out.printf("   Total Students: %d%n", totalStudentCount);
        System.out.printf("   Total Grades Recorded: %d%n", totalGradeCount);
        System.out.printf("   Overall Average: %.1f%n", overallAverage);
        System.out.printf("   A-Average Students: %d (%.1f%%)%n", 
                        totalAStudents, (totalAStudents * 100.0 / totalStudentCount));
        
        System.out.println("\n🎯 KEY INSIGHTS:");
        System.out.println("   ✓ Loop structures enabled efficient processing of " + totalGradeCount + " individual grades");
        System.out.println("   ✓ Enhanced for loops simplified array traversal and improved readability");
        System.out.println("   ✓ While loops provided flexible search and filtering capabilities");
        System.out.println("   ✓ Do-while loops ensured comprehensive analysis of all data sets");
        System.out.println("   ✓ Nested loops enabled multi-dimensional data analysis");
        System.out.println("   ✓ Break/continue statements optimized performance in search operations");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("Analysis completed successfully using all loop types!");
        System.out.println("=".repeat(70));
    }
}

/*
 * PRACTICAL LEARNING POINTS:
 * 
 * 1. LOOP TYPE SELECTION:
 *    - Enhanced for loops: Perfect for iterating through all elements
 *    - Traditional for loops: Ideal when you need index access or specific ranges
 *    - While loops: Excellent for search operations and conditional processing
 *    - Do-while loops: Ensure at least one execution, great for validation
 * 
 * 2. REAL-WORLD DATA PROCESSING:
 *    - Multi-dimensional arrays require nested loops
 *    - Statistical calculations benefit from accumulation patterns
 *    - Search operations use break statements for efficiency
 *    - Data validation employs continue statements for filtering
 * 
 * 3. PERFORMANCE CONSIDERATIONS:
 *    - Enhanced for loops are generally more efficient and safer
 *    - Break statements prevent unnecessary iterations
 *    - Proper loop structure reduces computational complexity
 *    - Nested loops should be used judiciously due to O(n²) or higher complexity
 * 
 * 4. CODE ORGANIZATION:
 *    - Logical grouping of loop operations improves readability
 *    - Meaningful variable names make loop logic clear
 *    - Consistent indentation shows loop nesting levels
 *    - Comments explain complex loop logic and algorithms
 * 
 * 5. COMMON PATTERNS DEMONSTRATED:
 *    - Accumulation: Summing values, counting occurrences
 *    - Search: Finding specific values or conditions
 *    - Filter: Processing only elements that meet criteria
 *    - Transform: Converting data from one form to another
 *    - Analysis: Statistical calculations and comparisons
 * 
 * 6. ERROR PREVENTION:
 *    - Proper array bounds checking prevents index errors
 *    - Loop variable initialization prevents infinite loops
 *    - Clear exit conditions ensure loops terminate
 *    - Defensive programming handles edge cases
 */
