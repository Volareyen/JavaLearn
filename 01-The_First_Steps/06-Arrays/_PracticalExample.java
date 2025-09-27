/**
 * Student Grade Management System
 * 
 * A practical example demonstrating all aspects of arrays in Java
 * through a comprehensive system that manages student grades across
 * multiple subjects, calculates statistics, and generates reports.
 * 
 * This program shows how arrays enable efficient data organization,
 * systematic processing, and powerful analytical capabilities.
 */

import java.util.Arrays;
import java.util.Scanner;

public class _PracticalExample {
    
    // System constants
    private static final int MAX_STUDENTS = 50;
    private static final int NUM_SUBJECTS = 5;
    private static final String[] SUBJECT_NAMES = {
        "Mathematics", "Science", "English", "History", "Art"
    };
    private static final double[] SUBJECT_WEIGHTS = {
        0.25, 0.25, 0.20, 0.15, 0.15  // Weighted importance for GPA calculation
    };
    
    // Grade boundaries for letter grades
    private static final double[] GRADE_BOUNDARIES = {90.0, 80.0, 70.0, 60.0};
    private static final char[] LETTER_GRADES = {'A', 'B', 'C', 'D', 'F'};
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(70));
        System.out.println("              STUDENT GRADE MANAGEMENT SYSTEM");
        System.out.println("=".repeat(70));
        
        // ============================================================================
        // SYSTEM DATA STRUCTURES - Arrays for storing student information
        // ============================================================================
        
        // Student information arrays (parallel arrays)
        String[] studentNames = new String[MAX_STUDENTS];
        int[] studentIds = new int[MAX_STUDENTS];
        String[] studentEmails = new String[MAX_STUDENTS];
        
        // Grade storage: 2D array [student][subject]
        double[][] studentGrades = new double[MAX_STUDENTS][NUM_SUBJECTS];
        
        // Calculated data arrays
        double[] studentGPAs = new double[MAX_STUDENTS];
        char[] studentLetterGrades = new char[MAX_STUDENTS];
        int[] studentRankings = new int[MAX_STUDENTS];
        
        // System tracking
        int currentStudentCount = 0;
        
        // ============================================================================
        // DATA INITIALIZATION - Populating arrays with sample data
        // ============================================================================
        
        System.out.println("\n🔄 Initializing Student Database...");
        
        // Sample student data
        String[] sampleNames = {
            "Alice Johnson", "Bob Smith", "Charlie Brown", "Diana Wilson", "Eva Garcia",
            "Frank Miller", "Grace Lee", "Henry Davis", "Iris Chen", "Jack Taylor",
            "Kate Adams", "Liam Murphy", "Maya Patel", "Noah Kim", "Olivia Rodriguez"
        };
        
        int[] sampleIds = {
            1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010,
            1011, 1012, 1013, 1014, 1015
        };
        
        // Sample grades for each student across all subjects
        double[][] sampleGrades = {
            {95.5, 88.0, 92.5, 87.0, 94.0},  // Alice - Excellent student
            {78.5, 82.0, 80.0, 85.0, 88.5},  // Bob - Good student
            {92.0, 94.5, 89.0, 91.5, 90.0},  // Charlie - High achiever
            {85.0, 79.5, 88.5, 86.0, 82.0},  // Diana - Above average
            {88.5, 92.0, 85.5, 89.0, 91.5},  // Eva - Consistent performer
            {72.0, 75.5, 78.0, 74.5, 76.0},  // Frank - Needs improvement
            {96.0, 98.5, 94.0, 95.5, 97.0},  // Grace - Top performer
            {81.5, 84.0, 79.5, 83.0, 85.5},  // Henry - Steady student
            {89.0, 87.5, 91.0, 88.5, 86.0},  // Iris - Well-rounded
            {77.5, 80.0, 76.0, 78.5, 79.0},  // Jack - Average performer
            {93.5, 91.0, 95.0, 92.5, 94.5},  // Kate - High achiever
            {68.0, 72.5, 70.0, 71.0, 69.5},  // Liam - Struggling student
            {87.0, 89.5, 86.0, 88.0, 90.0},  // Maya - Good student
            {91.5, 89.0, 93.0, 90.5, 92.0},  // Noah - Excellent student
            {84.0, 86.5, 82.0, 85.5, 87.0}   // Olivia - Above average
        };
        
        // Initialize student data using array copying
        currentStudentCount = Math.min(sampleNames.length, MAX_STUDENTS);
        
        for (int i = 0; i < currentStudentCount; i++) {
            studentNames[i] = sampleNames[i];
            studentIds[i] = sampleIds[i];
            studentEmails[i] = generateEmail(sampleNames[i]);
            
            // Copy grades for this student
            System.arraycopy(sampleGrades[i], 0, studentGrades[i], 0, NUM_SUBJECTS);
        }
        
        System.out.printf("✅ Initialized %d students with complete grade records%n", currentStudentCount);
        
        // ============================================================================
        // GRADE CALCULATIONS - Using arrays for statistical analysis
        // ============================================================================
        
        System.out.println("\n📊 Calculating Student Statistics...");
        
        // Calculate GPA and letter grades for each student
        calculateStudentGPAs(studentGrades, studentGPAs, currentStudentCount);
        calculateLetterGrades(studentGPAs, studentLetterGrades, currentStudentCount);
        
        // Calculate class rankings
        calculateRankings(studentGPAs, studentRankings, currentStudentCount);
        
        System.out.println("✅ Completed statistical calculations for all students");
        
        // ============================================================================
        // SUBJECT-WISE ANALYSIS - Array processing for subject statistics
        // ============================================================================
        
        System.out.println("\n📈 Analyzing Subject Performance...");
        
        // Calculate subject statistics
        double[] subjectAverages = calculateSubjectAverages(studentGrades, currentStudentCount);
        double[] subjectMaxScores = findSubjectMaximums(studentGrades, currentStudentCount);
        double[] subjectMinScores = findSubjectMinimums(studentGrades, currentStudentCount);
        int[] subjectTopStudents = findTopStudentsPerSubject(studentGrades, currentStudentCount);
        
        displaySubjectAnalysis(subjectAverages, subjectMaxScores, subjectMinScores, 
                              subjectTopStudents, studentNames);
        
        // ============================================================================
        // GRADE DISTRIBUTION ANALYSIS - Statistical array processing
        // ============================================================================
        
        System.out.println("\n📊 Grade Distribution Analysis...");
        
        // Analyze grade distribution across all subjects
        int[][] gradeDistribution = calculateGradeDistribution(studentGrades, currentStudentCount);
        displayGradeDistribution(gradeDistribution);
        
        // Find students in each performance category
        String[][] performanceCategories = categorizeStudentsByPerformance(
            studentNames, studentGPAs, currentStudentCount);
        displayPerformanceCategories(performanceCategories);
        
        // ============================================================================
        // STUDENT REPORTS - Individual and comparative analysis
        // ============================================================================
        
        System.out.println("\n📋 Generating Individual Student Reports...");
        
        // Display top 5 students
        displayTopStudents(studentNames, studentIds, studentGPAs, studentLetterGrades, 
                          studentRankings, currentStudentCount, 5);
        
        // Display students needing attention
        displayStudentsNeedingAttention(studentNames, studentGrades, studentGPAs, 
                                       currentStudentCount);
        
        // ============================================================================
        // ADVANCED ARRAY OPERATIONS - Searching, sorting, and filtering
        // ============================================================================
        
        System.out.println("\n🔍 Advanced Data Analysis...");
        
        // Find students with perfect scores in any subject
        findPerfectScores(studentNames, studentGrades, currentStudentCount);
        
        // Analyze grade trends and patterns
        analyzeGradeTrends(studentGrades, currentStudentCount);
        
        // Find subject correlations
        analyzeSubjectCorrelations(studentGrades, currentStudentCount);
        
        // ============================================================================
        // INTERACTIVE FEATURES - Array-based search and reporting
        // ============================================================================
        
        System.out.println("\n💻 Interactive Features Available:");
        System.out.println("   • Student search by name or ID");
        System.out.println("   • Grade lookup and modification");
        System.out.println("   • Custom report generation");
        System.out.println("   • Statistical analysis tools");
        
        // Demonstrate search functionality
        demonstrateSearchFeatures(studentNames, studentIds, studentGrades, 
                                 studentGPAs, currentStudentCount);
        
        // ============================================================================
        // COMPREHENSIVE SYSTEM SUMMARY - Final statistics
        // ============================================================================
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                    SYSTEM SUMMARY");
        System.out.println("=".repeat(70));
        
        generateSystemSummary(studentNames, studentGrades, studentGPAs, 
                             subjectAverages, currentStudentCount);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("Grade Management System completed successfully!");
        System.out.println("All array operations demonstrated with real-world data.");
        System.out.println("=".repeat(70));
    }
    
    // ============================================================================
    // CALCULATION METHODS - Array processing for statistical analysis
    // ============================================================================
    
    /**
     * Calculate weighted GPA for each student
     * Demonstrates: 2D array processing, weighted calculations
     */
    private static void calculateStudentGPAs(double[][] grades, double[] gpas, int studentCount) {
        for (int student = 0; student < studentCount; student++) {
            double weightedSum = 0.0;
            double totalWeight = 0.0;
            
            for (int subject = 0; subject < NUM_SUBJECTS; subject++) {
                weightedSum += grades[student][subject] * SUBJECT_WEIGHTS[subject];
                totalWeight += SUBJECT_WEIGHTS[subject];
            }
            
            gpas[student] = weightedSum / totalWeight;
        }
    }
    
    /**
     * Convert numerical GPAs to letter grades
     * Demonstrates: array transformation, conditional logic
     */
    private static void calculateLetterGrades(double[] gpas, char[] letterGrades, int studentCount) {
        for (int i = 0; i < studentCount; i++) {
            letterGrades[i] = getLetterGrade(gpas[i]);
        }
    }
    
    /**
     * Calculate class rankings based on GPA
     * Demonstrates: array sorting, ranking algorithms
     */
    private static void calculateRankings(double[] gpas, int[] rankings, int studentCount) {
        // Create array of indices for sorting
        Integer[] indices = new Integer[studentCount];
        for (int i = 0; i < studentCount; i++) {
            indices[i] = i;
        }
        
        // Sort indices by GPA (descending order)
        Arrays.sort(indices, (a, b) -> Double.compare(gpas[b], gpas[a]));
        
        // Assign rankings
        for (int rank = 0; rank < studentCount; rank++) {
            rankings[indices[rank]] = rank + 1;
        }
    }
    
    /**
     * Calculate average grade for each subject
     * Demonstrates: column-wise array processing
     */
    private static double[] calculateSubjectAverages(double[][] grades, int studentCount) {
        double[] averages = new double[NUM_SUBJECTS];
        
        for (int subject = 0; subject < NUM_SUBJECTS; subject++) {
            double sum = 0.0;
            for (int student = 0; student < studentCount; student++) {
                sum += grades[student][subject];
            }
            averages[subject] = sum / studentCount;
        }
        
        return averages;
    }
    
    /**
     * Find maximum score in each subject
     * Demonstrates: finding extremes in 2D arrays
     */
    private static double[] findSubjectMaximums(double[][] grades, int studentCount) {
        double[] maxScores = new double[NUM_SUBJECTS];
        
        for (int subject = 0; subject < NUM_SUBJECTS; subject++) {
            maxScores[subject] = grades[0][subject];  // Initialize with first student's grade
            
            for (int student = 1; student < studentCount; student++) {
                if (grades[student][subject] > maxScores[subject]) {
                    maxScores[subject] = grades[student][subject];
                }
            }
        }
        
        return maxScores;
    }
    
    /**
     * Find minimum score in each subject
     * Demonstrates: finding extremes in 2D arrays
     */
    private static double[] findSubjectMinimums(double[][] grades, int studentCount) {
        double[] minScores = new double[NUM_SUBJECTS];
        
        for (int subject = 0; subject < NUM_SUBJECTS; subject++) {
            minScores[subject] = grades[0][subject];  // Initialize with first student's grade
            
            for (int student = 1; student < studentCount; student++) {
                if (grades[student][subject] < minScores[subject]) {
                    minScores[subject] = grades[student][subject];
                }
            }
        }
        
        return minScores;
    }
    
    /**
     * Find top student in each subject
     * Demonstrates: finding maximum with index tracking
     */
    private static int[] findTopStudentsPerSubject(double[][] grades, int studentCount) {
        int[] topStudents = new int[NUM_SUBJECTS];
        
        for (int subject = 0; subject < NUM_SUBJECTS; subject++) {
            int topStudentIndex = 0;
            double highestScore = grades[0][subject];
            
            for (int student = 1; student < studentCount; student++) {
                if (grades[student][subject] > highestScore) {
                    highestScore = grades[student][subject];
                    topStudentIndex = student;
                }
            }
            
            topStudents[subject] = topStudentIndex;
        }
        
        return topStudents;
    }
    
    // ============================================================================
    // ANALYSIS METHODS - Advanced array processing
    // ============================================================================
    
    /**
     * Calculate grade distribution across all subjects
     * Demonstrates: counting and categorization
     */
    private static int[][] calculateGradeDistribution(double[][] grades, int studentCount) {
        int[][] distribution = new int[NUM_SUBJECTS][LETTER_GRADES.length];
        
        for (int student = 0; student < studentCount; student++) {
            for (int subject = 0; subject < NUM_SUBJECTS; subject++) {
                char letterGrade = getLetterGrade(grades[student][subject]);
                int gradeIndex = getGradeIndex(letterGrade);
                distribution[subject][gradeIndex]++;
            }
        }
        
        return distribution;
    }
    
    /**
     * Categorize students by performance level
     * Demonstrates: filtering and categorization
     */
    private static String[][] categorizeStudentsByPerformance(String[] names, double[] gpas, int studentCount) {
        // Count students in each category first
        int excellentCount = 0, goodCount = 0, averageCount = 0, needsImprovementCount = 0;
        
        for (int i = 0; i < studentCount; i++) {
            if (gpas[i] >= 90.0) excellentCount++;
            else if (gpas[i] >= 80.0) goodCount++;
            else if (gpas[i] >= 70.0) averageCount++;
            else needsImprovementCount++;
        }
        
        // Create arrays for each category
        String[][] categories = new String[4][];
        categories[0] = new String[excellentCount];        // Excellent (90+)
        categories[1] = new String[goodCount];             // Good (80-89)
        categories[2] = new String[averageCount];          // Average (70-79)
        categories[3] = new String[needsImprovementCount]; // Needs Improvement (<70)
        
        // Fill category arrays
        int[] categoryIndices = new int[4];
        
        for (int i = 0; i < studentCount; i++) {
            int categoryIndex;
            if (gpas[i] >= 90.0) categoryIndex = 0;
            else if (gpas[i] >= 80.0) categoryIndex = 1;
            else if (gpas[i] >= 70.0) categoryIndex = 2;
            else categoryIndex = 3;
            
            categories[categoryIndex][categoryIndices[categoryIndex]] = names[i];
            categoryIndices[categoryIndex]++;
        }
        
        return categories;
    }
    
    /**
     * Find students with perfect scores
     * Demonstrates: conditional searching in 2D arrays
     */
    private static void findPerfectScores(String[] names, double[][] grades, int studentCount) {
        System.out.println("\n🌟 Students with Perfect Scores (100.0):");
        
        boolean foundPerfectScore = false;
        
        for (int student = 0; student < studentCount; student++) {
            for (int subject = 0; subject < NUM_SUBJECTS; subject++) {
                if (grades[student][subject] == 100.0) {
                    System.out.printf("   🏆 %s: Perfect score in %s%n", 
                                    names[student], SUBJECT_NAMES[subject]);
                    foundPerfectScore = true;
                }
            }
        }
        
        if (!foundPerfectScore) {
            System.out.println("   No perfect scores found in the current dataset.");
        }
    }
    
    /**
     * Analyze grade trends and consistency
     * Demonstrates: statistical analysis across arrays
     */
    private static void analyzeGradeTrends(double[][] grades, int studentCount) {
        System.out.println("\n📈 Grade Trend Analysis:");
        
        for (int student = 0; student < studentCount; student++) {
            double[] studentGrades = grades[student];
            
            // Calculate variance to measure consistency
            double mean = 0.0;
            for (double grade : studentGrades) {
                mean += grade;
            }
            mean /= NUM_SUBJECTS;
            
            double variance = 0.0;
            for (double grade : studentGrades) {
                variance += Math.pow(grade - mean, 2);
            }
            variance /= NUM_SUBJECTS;
            
            double standardDeviation = Math.sqrt(variance);
            
            // Classify consistency
            String consistency;
            if (standardDeviation < 3.0) {
                consistency = "Very Consistent";
            } else if (standardDeviation < 6.0) {
                consistency = "Consistent";
            } else if (standardDeviation < 10.0) {
                consistency = "Moderately Consistent";
            } else {
                consistency = "Inconsistent";
            }
            
            // Only show interesting cases (first few students as example)
            if (student < 3) {
                System.out.printf("   Student %d: Avg=%.1f, StdDev=%.1f (%s)%n", 
                                student + 1, mean, standardDeviation, consistency);
            }
        }
    }
    
    /**
     * Analyze correlations between subjects
     * Demonstrates: advanced statistical analysis
     */
    private static void analyzeSubjectCorrelations(double[][] grades, int studentCount) {
        System.out.println("\n🔗 Subject Correlation Analysis:");
        System.out.println("   (Simplified correlation indicators)");
        
        // Simple correlation analysis between Math and Science
        double mathScienceCorrelation = calculateSimpleCorrelation(grades, 0, 1, studentCount);
        System.out.printf("   Mathematics ↔ Science: %.3f %s%n", 
                         mathScienceCorrelation, getCorrelationDescription(mathScienceCorrelation));
        
        // English and History correlation
        double englishHistoryCorrelation = calculateSimpleCorrelation(grades, 2, 3, studentCount);
        System.out.printf("   English ↔ History: %.3f %s%n", 
                         englishHistoryCorrelation, getCorrelationDescription(englishHistoryCorrelation));
    }
    
    // ============================================================================
    // DISPLAY METHODS - Array-based reporting and visualization
    // ============================================================================
    
    /**
     * Display subject analysis results
     * Demonstrates: formatted array output
     */
    private static void displaySubjectAnalysis(double[] averages, double[] maxScores, 
                                              double[] minScores, int[] topStudents, String[] names) {
        System.out.printf("%n%-15s %8s %8s %8s %s%n", 
                         "Subject", "Average", "Highest", "Lowest", "Top Student");
        System.out.println("-".repeat(65));
        
        for (int i = 0; i < NUM_SUBJECTS; i++) {
            System.out.printf("%-15s %8.1f %8.1f %8.1f %s%n",
                            SUBJECT_NAMES[i], averages[i], maxScores[i], 
                            minScores[i], names[topStudents[i]]);
        }
    }
    
    /**
     * Display grade distribution
     * Demonstrates: 2D array visualization
     */
    private static void displayGradeDistribution(int[][] distribution) {
        System.out.println("\n📊 Grade Distribution by Subject:");
        System.out.printf("%n%-15s", "Subject");
        
        for (char grade : LETTER_GRADES) {
            System.out.printf("%6c", grade);
        }
        System.out.println();
        System.out.println("-".repeat(50));
        
        for (int subject = 0; subject < NUM_SUBJECTS; subject++) {
            System.out.printf("%-15s", SUBJECT_NAMES[subject]);
            for (int grade = 0; grade < LETTER_GRADES.length; grade++) {
                System.out.printf("%6d", distribution[subject][grade]);
            }
            System.out.println();
        }
    }
    
    /**
     * Display performance categories
     * Demonstrates: jagged array processing
     */
    private static void displayPerformanceCategories(String[][] categories) {
        String[] categoryNames = {"Excellent (90+)", "Good (80-89)", "Average (70-79)", "Needs Improvement (<70)"};
        String[] categoryEmojis = {"🌟", "👍", "📚", "💪"};
        
        System.out.println("\n🎯 Student Performance Categories:");
        
        for (int category = 0; category < categories.length; category++) {
            System.out.printf("%n%s %s (%d students):%n", 
                            categoryEmojis[category], categoryNames[category], categories[category].length);
            
            if (categories[category].length > 0) {
                for (String studentName : categories[category]) {
                    System.out.println("   • " + studentName);
                }
            } else {
                System.out.println("   No students in this category");
            }
        }
    }
    
    /**
     * Display top performing students
     * Demonstrates: sorted array display
     */
    private static void displayTopStudents(String[] names, int[] ids, double[] gpas, 
                                          char[] letterGrades, int[] rankings, 
                                          int studentCount, int topCount) {
        System.out.printf("%n🏆 Top %d Students:%n", topCount);
        System.out.printf("%n%-4s %-20s %-8s %-5s %-5s%n", "Rank", "Name", "ID", "GPA", "Grade");
        System.out.println("-".repeat(50));
        
        // Find students by ranking
        for (int rank = 1; rank <= Math.min(topCount, studentCount); rank++) {
            for (int student = 0; student < studentCount; student++) {
                if (rankings[student] == rank) {
                    System.out.printf("%-4d %-20s %-8d %-5.1f %-5c%n",
                                    rank, names[student], ids[student], 
                                    gpas[student], letterGrades[student]);
                    break;
                }
            }
        }
    }
    
    /**
     * Display students needing attention
     * Demonstrates: filtering and conditional display
     */
    private static void displayStudentsNeedingAttention(String[] names, double[][] grades, 
                                                       double[] gpas, int studentCount) {
        System.out.println("\n⚠️  Students Needing Additional Support:");
        
        boolean foundStudents = false;
        
        for (int student = 0; student < studentCount; student++) {
            // Check if student has GPA below 75 or any subject below 70
            boolean needsAttention = gpas[student] < 75.0;
            
            if (!needsAttention) {
                for (int subject = 0; subject < NUM_SUBJECTS; subject++) {
                    if (grades[student][subject] < 70.0) {
                        needsAttention = true;
                        break;
                    }
                }
            }
            
            if (needsAttention) {
                foundStudents = true;
                System.out.printf("   📢 %s (GPA: %.1f)%n", names[student], gpas[student]);
                
                // Show specific subjects needing improvement
                System.out.print("      Focus areas: ");
                boolean first = true;
                for (int subject = 0; subject < NUM_SUBJECTS; subject++) {
                    if (grades[student][subject] < 75.0) {
                        if (!first) System.out.print(", ");
                        System.out.printf("%s (%.1f)", SUBJECT_NAMES[subject], grades[student][subject]);
                        first = false;
                    }
                }
                System.out.println();
            }
        }
        
        if (!foundStudents) {
            System.out.println("   ✅ All students are performing well!");
        }
    }
    
    /**
     * Demonstrate search functionality
     * Demonstrates: array searching algorithms
     */
    private static void demonstrateSearchFeatures(String[] names, int[] ids, double[][] grades, 
                                                 double[] gpas, int studentCount) {
        System.out.println("\n🔍 Search Feature Demonstration:");
        
        // Search by name (partial match)
        String searchName = "Alice";
        System.out.printf("   Searching for students with name containing '%s':%n", searchName);
        
        boolean found = false;
        for (int i = 0; i < studentCount; i++) {
            if (names[i].toLowerCase().contains(searchName.toLowerCase())) {
                System.out.printf("     Found: %s (ID: %d, GPA: %.1f)%n", 
                                names[i], ids[i], gpas[i]);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("     No students found with that name.");
        }
        
        // Search by ID
        int searchId = 1005;
        System.out.printf("%n   Searching for student with ID %d:%n", searchId);
        
        found = false;
        for (int i = 0; i < studentCount; i++) {
            if (ids[i] == searchId) {
                System.out.printf("     Found: %s (GPA: %.1f)%n", names[i], gpas[i]);
                System.out.print("     Grades: ");
                for (int subject = 0; subject < NUM_SUBJECTS; subject++) {
                    System.out.printf("%s: %.1f ", SUBJECT_NAMES[subject], grades[i][subject]);
                }
                System.out.println();
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("     No student found with that ID.");
        }
    }
    
    /**
     * Generate comprehensive system summary
     * Demonstrates: comprehensive array analysis
     */
    private static void generateSystemSummary(String[] names, double[][] grades, double[] gpas, 
                                             double[] subjectAverages, int studentCount) {
        System.out.println("\n📈 COMPREHENSIVE STATISTICS:");
        
        // Overall system metrics
        double totalGPA = 0.0;
        double highestGPA = gpas[0];
        double lowestGPA = gpas[0];
        
        for (int i = 0; i < studentCount; i++) {
            totalGPA += gpas[i];
            if (gpas[i] > highestGPA) highestGPA = gpas[i];
            if (gpas[i] < lowestGPA) lowestGPA = gpas[i];
        }
        
        double averageGPA = totalGPA / studentCount;
        
        System.out.printf("   📊 Students Processed: %d%n", studentCount);
        System.out.printf("   📊 Subjects Tracked: %d%n", NUM_SUBJECTS);
        System.out.printf("   📊 Total Grade Records: %d%n", studentCount * NUM_SUBJECTS);
        System.out.printf("   📊 Average Class GPA: %.2f%n", averageGPA);
        System.out.printf("   📊 Highest Individual GPA: %.2f%n", highestGPA);
        System.out.printf("   📊 Lowest Individual GPA: %.2f%n", lowestGPA);
        
        // Subject performance summary
        System.out.println("\n📚 SUBJECT PERFORMANCE OVERVIEW:");
        double bestSubjectAverage = subjectAverages[0];
        double worstSubjectAverage = subjectAverages[0];
        int bestSubjectIndex = 0;
        int worstSubjectIndex = 0;
        
        for (int i = 1; i < NUM_SUBJECTS; i++) {
            if (subjectAverages[i] > bestSubjectAverage) {
                bestSubjectAverage = subjectAverages[i];
                bestSubjectIndex = i;
            }
            if (subjectAverages[i] < worstSubjectAverage) {
                worstSubjectAverage = subjectAverages[i];
                worstSubjectIndex = i;
            }
        }
        
        System.out.printf("   🏆 Strongest Subject: %s (%.1f average)%n", 
                         SUBJECT_NAMES[bestSubjectIndex], bestSubjectAverage);
        System.out.printf("   📈 Subject Needing Focus: %s (%.1f average)%n", 
                         SUBJECT_NAMES[worstSubjectIndex], worstSubjectAverage);
        
        // Grade distribution summary
        int[] gradeCounts = new int[LETTER_GRADES.length];
        for (int i = 0; i < studentCount; i++) {
            int gradeIndex = getGradeIndex(getLetterGrade(gpas[i]));
            gradeCounts[gradeIndex]++;
        }
        
        System.out.println("\n🎯 OVERALL GRADE DISTRIBUTION:");
        for (int i = 0; i < LETTER_GRADES.length; i++) {
            double percentage = (double) gradeCounts[i] / studentCount * 100;
            System.out.printf("   %c Grade: %2d students (%.1f%%)%n", 
                            LETTER_GRADES[i], gradeCounts[i], percentage);
        }
        
        System.out.println("\n✨ ARRAY OPERATIONS DEMONSTRATED:");
        System.out.println("   ✓ Multi-dimensional array processing");
        System.out.println("   ✓ Parallel array management");
        System.out.println("   ✓ Statistical calculations and analysis");
        System.out.println("   ✓ Searching and filtering algorithms");
        System.out.println("   ✓ Sorting and ranking operations");
        System.out.println("   ✓ Data transformation and categorization");
        System.out.println("   ✓ Advanced array manipulation techniques");
    }
    
    // ============================================================================
    // UTILITY METHODS - Helper functions for array operations
    // ============================================================================
    
    /**
     * Convert numerical grade to letter grade
     */
    private static char getLetterGrade(double grade) {
        for (int i = 0; i < GRADE_BOUNDARIES.length; i++) {
            if (grade >= GRADE_BOUNDARIES[i]) {
                return LETTER_GRADES[i];
            }
        }
        return LETTER_GRADES[LETTER_GRADES.length - 1]; // Return 'F' for lowest grades
    }
    
    /**
     * Get array index for letter grade
     */
    private static int getGradeIndex(char letterGrade) {
        for (int i = 0; i < LETTER_GRADES.length; i++) {
            if (LETTER_GRADES[i] == letterGrade) {
                return i;
            }
        }
        return LETTER_GRADES.length - 1; // Default to 'F'
    }
    
    /**
     * Generate email from student name
     */
    private static String generateEmail(String name) {
        String[] parts = name.toLowerCase().split(" ");
        return parts[0] + "." + parts[parts.length - 1] + "@university.edu";
    }
    
    /**
     * Calculate simple correlation between two subjects
     */
    private static double calculateSimpleCorrelation(double[][] grades, int subject1, int subject2, int studentCount) {
        // Simplified correlation calculation
        double sum1 = 0, sum2 = 0, sum1Sq = 0, sum2Sq = 0, pSum = 0;
        
        for (int i = 0; i < studentCount; i++) {
            double x = grades[i][subject1];
            double y = grades[i][subject2];
            
            sum1 += x;
            sum2 += y;
            sum1Sq += x * x;
            sum2Sq += y * y;
            pSum += x * y;
        }
        
        double num = pSum - (sum1 * sum2 / studentCount);
        double den = Math.sqrt((sum1Sq - sum1 * sum1 / studentCount) * 
                              (sum2Sq - sum2 * sum2 / studentCount));
        
        return (den == 0) ? 0 : num / den;
    }
    
    /**
     * Get correlation description
     */
    private static String getCorrelationDescription(double correlation) {
        double abs = Math.abs(correlation);
        if (abs >= 0.7) return "(Strong correlation)";
        else if (abs >= 0.4) return "(Moderate correlation)";
        else if (abs >= 0.2) return "(Weak correlation)";
        else return "(No significant correlation)";
    }
}

/*
 * PRACTICAL LEARNING POINTS:
 * 
 * 1. ARRAY ORGANIZATION:
 *    - Parallel arrays for related data (names, IDs, emails)
 *    - 2D arrays for structured data (student grades by subject)
 *    - Calculated arrays for derived information (GPAs, rankings)
 *    - Constant arrays for reference data (subject names, grade boundaries)
 * 
 * 2. ARRAY PROCESSING PATTERNS:
 *    - Row-wise processing: Calculate individual student statistics
 *    - Column-wise processing: Calculate subject-wide statistics
 *    - Element-wise processing: Transform individual grades
 *    - Aggregate processing: Calculate overall system metrics
 * 
 * 3. SEARCH AND FILTER OPERATIONS:
 *    - Linear search for specific values
 *    - Conditional filtering for categories
 *    - Multi-criteria searching
 *    - Index-based lookups
 * 
 * 4. STATISTICAL ANALYSIS:
 *    - Accumulation patterns (sums, averages)
 *    - Extreme value finding (max, min)
 *    - Distribution analysis (counting by categories)
 *    - Correlation calculations (relationship analysis)
 * 
 * 5. DATA TRANSFORMATION:
 *    - Grade-to-letter conversion
 *    - Ranking calculations
 *    - Performance categorization
 *    - Report generation
 * 
 * 6. ARRAY COPYING AND MANIPULATION:
 *    - System.arraycopy for efficient copying
 *    - Array creation and initialization
 *    - Dynamic array sizing based on data
 *    - Reference vs. value considerations
 * 
 * 7. MULTI-DIMENSIONAL ARRAY USAGE:
 *    - 2D arrays for tabular data
 *    - Jagged arrays for variable-length data
 *    - Nested iteration patterns
 *    - Index management and bounds checking
 * 
 * 8. REAL-WORLD APPLICATION:
 *    - Educational data management
 *    - Statistical reporting systems
 *    - Performance tracking and analysis
 *    - Interactive data exploration
 * 
 * 9. PERFORMANCE CONSIDERATIONS:
 *    - Efficient iteration patterns
 *    - Minimizing array traversals
 *    - Memory-efficient data structures
 *    - Algorithm complexity awareness
 * 
 * 10. ERROR PREVENTION:
 *     - Bounds checking for array access
 *     - Null checking for array references
 *     - Length validation for operations
 *     - Defensive programming practices
 */
