/**
 * Student Admission Decision System
 * 
 * A practical example demonstrating all forms of conditional logic
 * in a realistic scenario - a comprehensive university admission system
 * that evaluates student applications using complex decision criteria.
 * 
 * This program shows how different conditional structures work together
 * to create intelligent, adaptive behavior in real-world applications.
 */
public class _PracticalExample {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(70));
        System.out.println("              UNIVERSITY ADMISSION DECISION SYSTEM");
        System.out.println("=".repeat(70));
        
        // ============================================================================
        // STUDENT APPLICATION DATA
        // ============================================================================
        
        // Student 1: Strong Academic Profile
        String student1Name = "Emily Rodriguez";
        double student1GPA = 3.85;
        int student1SAT = 1420;
        int student1ACT = 32;
        String student1State = "California";
        boolean student1IsInternational = false;
        int student1Extracurriculars = 4;  // Number of activities
        boolean student1HasSportsScholarship = false;
        boolean student1IsFirstGeneration = true;
        String student1IntendedMajor = "Computer Science";
        int student1VolunteerHours = 120;
        boolean student1HasWorkExperience = true;
        
        // Student 2: International Applicant
        String student2Name = "Hiroshi Tanaka";
        double student2GPA = 3.92;
        int student2SAT = 1380;
        int student2ACT = 30;
        String student2State = "International";
        boolean student2IsInternational = true;
        int student2Extracurriculars = 3;
        boolean student2HasSportsScholarship = false;
        boolean student2IsFirstGeneration = false;
        String student2IntendedMajor = "Engineering";
        int student2VolunteerHours = 80;
        boolean student2HasWorkExperience = false;
        
        // Student 3: Athletic Recruit
        String student3Name = "Marcus Johnson";
        double student3GPA = 3.45;
        int student3SAT = 1180;
        int student3ACT = 25;
        String student3State = "Texas";
        boolean student3IsInternational = false;
        int student3Extracurriculars = 2;
        boolean student3HasSportsScholarship = true;
        String student3IntendedMajor = "Business";
        int student3VolunteerHours = 40;
        boolean student3HasWorkExperience = false;
        
        // System Constants
        final double MIN_GPA_REGULAR = 3.0;
        final double MIN_GPA_COMPETITIVE = 3.5;
        final int MIN_SAT_REGULAR = 1200;
        final int MIN_SAT_COMPETITIVE = 1350;
        final int MIN_ACT_REGULAR = 26;
        final int MIN_ACT_COMPETITIVE = 30;
        final int MIN_VOLUNTEER_HOURS = 50;
        final int MIN_EXTRACURRICULARS = 2;
        
        // Process each student application
        processStudentApplication(student1Name, student1GPA, student1SAT, student1ACT, 
                                student1State, student1IsInternational, student1Extracurriculars,
                                student1HasSportsScholarship, student1IsFirstGeneration,
                                student1IntendedMajor, student1VolunteerHours, student1HasWorkExperience,
                                MIN_GPA_REGULAR, MIN_GPA_COMPETITIVE, MIN_SAT_REGULAR, MIN_SAT_COMPETITIVE,
                                MIN_ACT_REGULAR, MIN_ACT_COMPETITIVE, MIN_VOLUNTEER_HOURS, MIN_EXTRACURRICULARS);
        
        System.out.println();
        
        processStudentApplication(student2Name, student2GPA, student2SAT, student2ACT,
                                student2State, student2IsInternational, student2Extracurriculars,
                                student2HasSportsScholarship, student2IsFirstGeneration,
                                student2IntendedMajor, student2VolunteerHours, student2HasWorkExperience,
                                MIN_GPA_REGULAR, MIN_GPA_COMPETITIVE, MIN_SAT_REGULAR, MIN_SAT_COMPETITIVE,
                                MIN_ACT_REGULAR, MIN_ACT_COMPETITIVE, MIN_VOLUNTEER_HOURS, MIN_EXTRACURRICULARS);
        
        System.out.println();
        
        processStudentApplication(student3Name, student3GPA, student3SAT, student3ACT,
                                student3State, student3IsInternational, student3Extracurriculars,
                                student3HasSportsScholarship, student3IsFirstGeneration,
                                student3IntendedMajor, student3VolunteerHours, student3HasWorkExperience,
                                MIN_GPA_REGULAR, MIN_GPA_COMPETITIVE, MIN_SAT_REGULAR, MIN_SAT_COMPETITIVE,
                                MIN_ACT_REGULAR, MIN_ACT_COMPETITIVE, MIN_VOLUNTEER_HOURS, MIN_EXTRACURRICULARS);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("All applications processed successfully!");
        System.out.println("=".repeat(70));
    }
    
    /**
     * Processes a single student application using comprehensive conditional logic
     */
    private static void processStudentApplication(String name, double gpa, int sat, int act,
                                                String state, boolean isInternational, int extracurriculars,
                                                boolean hasSportsScholarship, boolean isFirstGeneration,
                                                String intendedMajor, int volunteerHours, boolean hasWorkExperience,
                                                double minGpaRegular, double minGpaCompetitive,
                                                int minSatRegular, int minSatCompetitive,
                                                int minActRegular, int minActCompetitive,
                                                int minVolunteerHours, int minExtracurriculars) {
        
        System.out.println("📋 PROCESSING APPLICATION: " + name);
        System.out.println("-".repeat(50));
        
        // ============================================================================
        // BASIC ELIGIBILITY CHECK - Simple IF Statements
        // ============================================================================
        
        System.out.println("📊 ACADEMIC PROFILE:");
        System.out.printf("   GPA: %.2f | SAT: %d | ACT: %d%n", gpa, sat, act);
        System.out.println("   State: " + state);
        System.out.println("   International: " + (isInternational ? "Yes" : "No"));
        System.out.println("   Intended Major: " + intendedMajor);
        System.out.println("   Extracurriculars: " + extracurriculars);
        System.out.println("   Volunteer Hours: " + volunteerHours);
        System.out.println("   Sports Scholarship: " + (hasSportsScholarship ? "Yes" : "No"));
        System.out.println("   First Generation: " + (isFirstGeneration ? "Yes" : "No"));
        System.out.println("   Work Experience: " + (hasWorkExperience ? "Yes" : "No"));
        
        // Basic eligibility checks using simple if statements
        boolean meetsMinGPA = false;
        if (gpa >= minGpaRegular) {
            meetsMinGPA = true;
            System.out.println("✓ Meets minimum GPA requirement (" + minGpaRegular + ")");
        } else {
            System.out.println("✗ Does not meet minimum GPA requirement (" + minGpaRegular + ")");
        }
        
        boolean meetsTestScores = false;
        if ((sat >= minSatRegular) || (act >= minActRegular)) {
            meetsTestScores = true;
            System.out.println("✓ Meets standardized test requirements");
        } else {
            System.out.println("✗ Does not meet standardized test requirements");
        }
        
        // Early elimination check
        if (!meetsMinGPA && !hasSportsScholarship) {
            System.out.println("❌ APPLICATION DENIED - Insufficient GPA and no athletic scholarship");
            return;  // Exit early - no need to continue evaluation
        }
        
        if (!meetsTestScores && !hasSportsScholarship) {
            System.out.println("❌ APPLICATION DENIED - Insufficient test scores and no athletic scholarship");
            return;  // Exit early
        }
        
        // ============================================================================
        // ACADEMIC STRENGTH EVALUATION - IF-ELSE IF-ELSE Chain
        // ============================================================================
        
        System.out.println("\n🎓 ACADEMIC STRENGTH ASSESSMENT:");
        
        String academicLevel;
        int academicPoints = 0;
        
        // GPA evaluation using if-else if chain
        if (gpa >= 3.9) {
            academicLevel = "Exceptional";
            academicPoints += 25;
            System.out.println("   GPA Level: Exceptional (3.9+) - 25 points");
        } else if (gpa >= 3.7) {
            academicLevel = "High";
            academicPoints += 20;
            System.out.println("   GPA Level: High (3.7-3.89) - 20 points");
        } else if (gpa >= 3.5) {
            academicLevel = "Above Average";
            academicPoints += 15;
            System.out.println("   GPA Level: Above Average (3.5-3.69) - 15 points");
        } else if (gpa >= 3.0) {
            academicLevel = "Average";
            academicPoints += 10;
            System.out.println("   GPA Level: Average (3.0-3.49) - 10 points");
        } else {
            academicLevel = "Below Average";
            academicPoints += 5;
            System.out.println("   GPA Level: Below Average (<3.0) - 5 points");
        }
        
        // SAT Score evaluation
        if (sat >= 1500) {
            academicPoints += 25;
            System.out.println("   SAT Level: Exceptional (1500+) - 25 points");
        } else if (sat >= 1400) {
            academicPoints += 20;
            System.out.println("   SAT Level: High (1400-1499) - 20 points");
        } else if (sat >= 1300) {
            academicPoints += 15;
            System.out.println("   SAT Level: Above Average (1300-1399) - 15 points");
        } else if (sat >= 1200) {
            academicPoints += 10;
            System.out.println("   SAT Level: Average (1200-1299) - 10 points");
        } else if (sat >= 1000) {
            academicPoints += 5;
            System.out.println("   SAT Level: Below Average (1000-1199) - 5 points");
        } else {
            System.out.println("   SAT Level: Low (<1000) - 0 points");
        }
        
        System.out.println("   Academic Points Total: " + academicPoints + "/50");
        
        // ============================================================================
        // HOLISTIC EVALUATION - Nested IF Statements
        // ============================================================================
        
        System.out.println("\n🌟 HOLISTIC EVALUATION:");
        
        int holisticPoints = 0;
        
        // Extracurricular evaluation with nested conditions
        if (extracurriculars >= minExtracurriculars) {
            System.out.println("   ✓ Meets extracurricular requirements");
            
            if (extracurriculars >= 5) {
                holisticPoints += 15;
                System.out.println("     Exceptional involvement (5+) - 15 points");
            } else if (extracurriculars >= 3) {
                holisticPoints += 10;
                System.out.println("     Strong involvement (3-4) - 10 points");
            } else {
                holisticPoints += 5;
                System.out.println("     Adequate involvement (2) - 5 points");
            }
            
        } else {
            System.out.println("   ✗ Does not meet extracurricular requirements");
        }
        
        // Volunteer work evaluation with nested conditions
        if (volunteerHours >= minVolunteerHours) {
            System.out.println("   ✓ Meets volunteer requirements");
            
            if (volunteerHours >= 200) {
                holisticPoints += 15;
                System.out.println("     Exceptional service (200+ hours) - 15 points");
            } else if (volunteerHours >= 100) {
                holisticPoints += 10;
                System.out.println("     Strong service (100-199 hours) - 10 points");
            } else {
                holisticPoints += 5;
                System.out.println("     Adequate service (50-99 hours) - 5 points");
            }
            
        } else {
            System.out.println("   ✗ Insufficient volunteer hours");
        }
        
        // Special circumstances evaluation
        if (isFirstGeneration) {
            holisticPoints += 10;
            System.out.println("   ✓ First-generation college student - 10 bonus points");
        }
        
        if (hasWorkExperience) {
            holisticPoints += 5;
            System.out.println("   ✓ Work experience demonstrated - 5 bonus points");
        }
        
        if (hasSportsScholarship) {
            holisticPoints += 20;
            System.out.println("   ✓ Athletic scholarship recipient - 20 bonus points");
        }
        
        System.out.println("   Holistic Points Total: " + holisticPoints + "/65");
        
        // ============================================================================
        // MAJOR-SPECIFIC EVALUATION - Switch Statement
        // ============================================================================
        
        System.out.println("\n🎯 MAJOR-SPECIFIC EVALUATION:");
        
        int majorPoints = 0;
        String majorRequirements = "";
        
        switch (intendedMajor.toLowerCase()) {
            case "computer science":
            case "engineering":
                majorRequirements = "STEM program - requires strong math/science background";
                if ((sat >= 1400) || (act >= 30)) {
                    majorPoints += 15;
                    System.out.println("   ✓ Meets STEM program requirements - 15 points");
                } else if ((sat >= 1300) || (act >= 28)) {
                    majorPoints += 10;
                    System.out.println("   ✓ Adequate STEM preparation - 10 points");
                } else {
                    majorPoints += 5;
                    System.out.println("   ⚠ Marginal STEM preparation - 5 points");
                }
                break;
                
            case "business":
            case "economics":
                majorRequirements = "Business program - values leadership and practical experience";
                if (hasWorkExperience && (extracurriculars >= 3)) {
                    majorPoints += 15;
                    System.out.println("   ✓ Strong business profile - 15 points");
                } else if (hasWorkExperience || (extracurriculars >= 3)) {
                    majorPoints += 10;
                    System.out.println("   ✓ Good business potential - 10 points");
                } else {
                    majorPoints += 5;
                    System.out.println("   ✓ Basic business readiness - 5 points");
                }
                break;
                
            case "liberal arts":
            case "english":
            case "history":
                majorRequirements = "Liberal Arts program - values well-rounded education";
                if ((volunteerHours >= 100) && (extracurriculars >= 3)) {
                    majorPoints += 15;
                    System.out.println("   ✓ Excellent liberal arts profile - 15 points");
                } else if ((volunteerHours >= 50) || (extracurriculars >= 2)) {
                    majorPoints += 10;
                    System.out.println("   ✓ Good liberal arts preparation - 10 points");
                } else {
                    majorPoints += 5;
                    System.out.println("   ✓ Adequate liberal arts readiness - 5 points");
                }
                break;
                
            case "pre-med":
            case "biology":
            case "chemistry":
                majorRequirements = "Pre-health program - requires exceptional academics";
                if ((gpa >= 3.8) && ((sat >= 1450) || (act >= 32))) {
                    majorPoints += 20;
                    System.out.println("   ✓ Exceptional pre-health profile - 20 points");
                } else if ((gpa >= 3.6) && ((sat >= 1350) || (act >= 30))) {
                    majorPoints += 15;
                    System.out.println("   ✓ Strong pre-health preparation - 15 points");
                } else {
                    majorPoints += 8;
                    System.out.println("   ⚠ Marginal pre-health readiness - 8 points");
                }
                break;
                
            default:
                majorRequirements = "General program requirements";
                majorPoints += 10;
                System.out.println("   ✓ Meets general program requirements - 10 points");
                break;
        }
        
        System.out.println("   Major: " + intendedMajor);
        System.out.println("   Requirements: " + majorRequirements);
        System.out.println("   Major-Specific Points: " + majorPoints + "/20");
        
        // ============================================================================
        // FINAL ADMISSION DECISION - Complex Nested Conditions
        // ============================================================================
        
        System.out.println("\n🏆 FINAL ADMISSION DECISION:");
        
        int totalPoints = academicPoints + holisticPoints + majorPoints;
        System.out.println("   Total Application Score: " + totalPoints + "/135");
        
        String admissionDecision;
        String scholarshipEligibility = "None";
        String specialPrograms = "";
        
        // Complex nested decision logic
        if (hasSportsScholarship) {
            // Athletic scholarship path - different criteria
            if ((gpa >= 3.0) && ((sat >= 1100) || (act >= 24))) {
                admissionDecision = "ADMITTED";
                scholarshipEligibility = "Athletic Scholarship";
                System.out.println("   🎯 Athletic Scholarship Admission Path");
            } else {
                admissionDecision = "CONDITIONAL ADMISSION";
                scholarshipEligibility = "Athletic Scholarship (Conditional)";
                System.out.println("   ⚠ Athletic Scholarship with Academic Conditions");
            }
            
        } else {
            // Regular admission path
            if (totalPoints >= 100) {
                admissionDecision = "ADMITTED";
                
                if (totalPoints >= 120) {
                    scholarshipEligibility = "Merit Scholarship - Full Tuition";
                    specialPrograms = "Honors Program, Research Opportunities";
                } else if (totalPoints >= 110) {
                    scholarshipEligibility = "Merit Scholarship - Partial Tuition";
                    specialPrograms = "Honors Program Eligible";
                } else {
                    scholarshipEligibility = "Academic Recognition Award";
                }
                
            } else if (totalPoints >= 80) {
                admissionDecision = "WAITLISTED";
                System.out.println("   📋 Competitive applicant - placed on waitlist");
                
            } else if (totalPoints >= 60) {
                // Special consideration for diversity and unique circumstances
                if (isFirstGeneration || isInternational) {
                    admissionDecision = "CONDITIONAL ADMISSION";
                    specialPrograms = "Academic Support Program";
                    System.out.println("   🌍 Special consideration for diversity/circumstances");
                } else {
                    admissionDecision = "DENIED";
                }
                
            } else {
                admissionDecision = "DENIED";
            }
        }
        
        // International student additional requirements
        if (isInternational && admissionDecision.equals("ADMITTED")) {
            if (sat < 1300 && act < 28) {
                specialPrograms += (specialPrograms.isEmpty() ? "" : ", ") + "English Language Support";
                System.out.println("   🌐 International student - English support recommended");
            }
        }
        
        // Display final decision
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📋 ADMISSION DECISION: " + admissionDecision);
        
        if (!scholarshipEligibility.equals("None")) {
            System.out.println("💰 Scholarship: " + scholarshipEligibility);
        }
        
        if (!specialPrograms.isEmpty()) {
            System.out.println("🌟 Special Programs: " + specialPrograms);
        }
        
        // Additional recommendations based on decision
        switch (admissionDecision) {
            case "ADMITTED":
                System.out.println("🎉 Congratulations! Welcome to our university community.");
                System.out.println("📅 Next steps: Submit enrollment deposit by May 1st");
                break;
            case "CONDITIONAL ADMISSION":
                System.out.println("📚 Admission granted with conditions.");
                System.out.println("📋 Requirements: Maintain current GPA, complete summer prep program");
                break;
            case "WAITLISTED":
                System.out.println("⏳ You have been placed on our waitlist.");
                System.out.println("📬 We will contact you by June 1st with an update");
                break;
            case "DENIED":
                System.out.println("😔 We regret that we cannot offer admission at this time.");
                System.out.println("🔄 You may reapply next year with improved credentials");
                break;
        }
        
        System.out.println("=".repeat(50));
    }
}

/*
 * PRACTICAL LEARNING POINTS:
 * 
 * 1. CONDITIONAL STRUCTURE SELECTION:
 *    - Simple if: Basic eligibility checks
 *    - If-else: Binary decisions (pass/fail, admit/deny)
 *    - If-else if chains: Multiple categories (grade levels, score ranges)
 *    - Switch: Discrete values (majors, commands, states)
 *    - Nested if: Complex hierarchical decisions
 * 
 * 2. REAL-WORLD DECISION LOGIC:
 *    - Early elimination to avoid unnecessary processing
 *    - Weighted scoring systems for complex evaluations
 *    - Special case handling (scholarships, international students)
 *    - Fallback options and conditional admissions
 * 
 * 3. CODE ORGANIZATION:
 *    - Logical grouping of related conditions
 *    - Clear variable naming for readability
 *    - Consistent formatting and indentation
 *    - Meaningful output messages for each decision point
 * 
 * 4. BEST PRACTICES DEMONSTRATED:
 *    - Early returns to avoid deep nesting
 *    - Constants for threshold values
 *    - Boolean variables for complex conditions
 *    - Comprehensive evaluation criteria
 *    - User-friendly decision explanations
 * 
 * 5. DECISION-MAKING PATTERNS:
 *    - Minimum requirements checking
 *    - Graduated scoring systems
 *    - Special circumstance handling
 *    - Multiple pathway evaluation
 *    - Final decision synthesis
 */
