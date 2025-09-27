/**
 * Personal Profile System - Complete Solution
 * 
 * This program demonstrates mastery of variables and data types
 * by creating a comprehensive personal profile system with
 * calculations, type conversions, and proper data type selection.
 * 
 * Author: The Wise Teacher's Example Solution
 */
public class PersonalProfile {
    
    public static void main(String[] args) {
        
        // ============================================================================
        // CONSTANTS - Values that never change
        // ============================================================================
        
        final double INCHES_TO_CM = 2.54;           // Conversion factor
        final double POUNDS_TO_KG = 0.453592;       // Conversion factor
        final int RETIREMENT_AGE = 65;              // Standard retirement age
        final int CURRENT_YEAR = 2024;              // Current year for calculations
        final double HOURS_PER_DAY_PROGRAMMING = 2.0; // Average programming hours
        final double BEGINNER_THRESHOLD = 1.0;      // Years to be considered intermediate
        final double ADVANCED_THRESHOLD = 5.0;      // Years to be considered advanced
        final int DAYS_PER_YEAR = 365;              // Approximate days per year
        final int MONTHS_PER_YEAR = 12;             // Months per year
        
        // ============================================================================
        // PERSONAL INFORMATION - Using appropriate data types
        // ============================================================================
        
        // String for textual information
        String firstName = "Alex";
        String middleName = "Jordan";
        String lastName = "Thompson";
        String emailAddress = "alex.thompson@email.com";
        String phoneNumber = "(555) 987-6543";
        String homeState = "CA";                    // 2-letter abbreviation
        String favoriteLanguage = "Java";
        String preferredIDE = "VSCode";
        
        // int for whole number values
        int ageInYears = 23;
        int heightInInches = 68;                    // 5'8"
        
        // double for precise decimal values
        double weightInPounds = 145.5;
        double yearsOfExperience = 1.5;
        
        // char for single character codes
        char eyeColorCode = 'G';                    // G=Green, B=Blue, H=Hazel, R=Brown
        
        // boolean for true/false flags
        boolean isCurrentlyStudent = true;
        boolean interestedInGameDev = true;
        boolean interestedInWebDev = true;
        boolean interestedInMobileDev = false;
        
        // long for potentially large numbers
        long favoriteLuckyNumber = 777777777L;      // Large lucky number
        
        // ============================================================================
        // CALCULATED INFORMATION - Demonstrating type usage and conversion
        // ============================================================================
        
        // Age calculations (automatic type conversion from int to double)
        double ageInMonths = ageInYears * MONTHS_PER_YEAR;
        double ageInDays = ageInYears * DAYS_PER_YEAR;
        int yearsUntilRetirement = RETIREMENT_AGE - ageInYears;
        
        // Physical measurement conversions (demonstrating double precision)
        double heightInCentimeters = heightInInches * INCHES_TO_CM;
        double weightInKilograms = weightInPounds * POUNDS_TO_KG;
        
        // BMI calculation (complex formula with multiple operations)
        double heightInMeters = heightInCentimeters / 100.0;
        double bmi = weightInKilograms / (heightInMeters * heightInMeters);
        
        // Programming experience calculations
        double totalProgrammingHours = yearsOfExperience * DAYS_PER_YEAR * HOURS_PER_DAY_PROGRAMMING;
        
        // Experience level determination (using boolean logic)
        String experienceLevel;
        if (yearsOfExperience < BEGINNER_THRESHOLD) {
            experienceLevel = "Beginner";
        } else if (yearsOfExperience < ADVANCED_THRESHOLD) {
            experienceLevel = "Intermediate";
        } else {
            experienceLevel = "Advanced";
        }
        
        // BMI category determination
        String bmiCategory;
        if (bmi < 18.5) {
            bmiCategory = "Underweight";
        } else if (bmi < 25.0) {
            bmiCategory = "Normal weight";
        } else if (bmi < 30.0) {
            bmiCategory = "Overweight";
        } else {
            bmiCategory = "Obese";
        }
        
        // Interest count (demonstrating boolean to int conversion)
        int totalInterests = 0;
        if (interestedInGameDev) totalInterests++;
        if (interestedInWebDev) totalInterests++;
        if (interestedInMobileDev) totalInterests++;
        
        // ============================================================================
        // DISPLAY PERSONAL PROFILE
        // ============================================================================
        
        System.out.println("=".repeat(60));
        System.out.println("              PERSONAL PROFILE SYSTEM");
        System.out.println("=".repeat(60));
        
        // Personal Information Section
        System.out.println("\n📋 PERSONAL INFORMATION:");
        System.out.println("   Full Name: " + firstName + " " + middleName + " " + lastName);
        System.out.println("   Age: " + ageInYears + " years old");
        System.out.println("   Physical: " + heightInInches + " inches tall, " + weightInPounds + " pounds");
        System.out.println("   Eye Color: " + getEyeColorName(eyeColorCode) + " (" + eyeColorCode + ")");
        System.out.println("   Student Status: " + (isCurrentlyStudent ? "Yes" : "No"));
        System.out.println("   Lucky Number: " + favoriteLuckyNumber);
        
        // Contact Information Section
        System.out.println("\n📞 CONTACT INFORMATION:");
        System.out.println("   Email: " + emailAddress);
        System.out.println("   Phone: " + phoneNumber);
        System.out.println("   Location: " + homeState);
        
        // Programming Background Section
        System.out.println("\n💻 PROGRAMMING BACKGROUND:");
        System.out.println("   Favorite Language: " + favoriteLanguage);
        System.out.println("   Experience: " + yearsOfExperience + " years");
        System.out.println("   IDE Preference: " + preferredIDE);
        System.out.println("   Development Interests:");
        System.out.println("     • Game Development: " + (interestedInGameDev ? "✓" : "✗"));
        System.out.println("     • Web Development: " + (interestedInWebDev ? "✓" : "✗"));
        System.out.println("     • Mobile Development: " + (interestedInMobileDev ? "✓" : "✗"));
        System.out.println("   Total Areas of Interest: " + totalInterests);
        
        // Calculated Information Section
        System.out.println("\n🧮 CALCULATED INFORMATION:");
        System.out.println("   Age Breakdown:");
        System.out.println("     • In months: ~" + (int)ageInMonths + " months");
        System.out.println("     • In days: ~" + String.format("%,d", (int)ageInDays) + " days");
        System.out.println("     • Years until retirement: " + yearsUntilRetirement + " years");
        
        System.out.println("   Physical Measurements (Metric):");
        System.out.println("     • Height: " + String.format("%.1f", heightInCentimeters) + " cm");
        System.out.println("     • Weight: " + String.format("%.1f", weightInKilograms) + " kg");
        System.out.println("     • BMI: " + String.format("%.1f", bmi) + " (" + bmiCategory + ")");
        
        System.out.println("   Programming Experience:");
        System.out.println("     • Total hours: ~" + String.format("%,.0f", totalProgrammingHours) + " hours");
        System.out.println("     • Experience level: " + experienceLevel);
        
        // ============================================================================
        // TYPE CONVERSION DEMONSTRATIONS
        // ============================================================================
        
        System.out.println("\n🔄 TYPE CONVERSION EXAMPLES:");
        
        // Automatic (widening) conversions
        long ageAsLong = ageInYears;                // int → long (automatic)
        double heightAsDouble = heightInInches;      // int → double (automatic)
        String bmiAsString = String.valueOf(bmi);    // double → String (method call)
        
        System.out.println("   Automatic Conversions:");
        System.out.println("     • Age as long: " + ageAsLong + "L");
        System.out.println("     • Height as double: " + heightAsDouble);
        System.out.println("     • BMI as String: \"" + String.format("%.2f", Double.parseDouble(bmiAsString)) + "\"");
        
        // Manual (narrowing) conversions - demonstrating potential data loss
        int bmiAsInt = (int) bmi;                   // double → int (truncated)
        int experienceAsInt = (int) yearsOfExperience; // double → int (truncated)
        char firstInitial = firstName.charAt(0);    // String → char (first character)
        
        System.out.println("   Manual Conversions (with potential data loss):");
        System.out.println("     • BMI as int: " + bmiAsInt + " (was " + String.format("%.1f", bmi) + ")");
        System.out.println("     • Experience as int: " + experienceAsInt + " (was " + yearsOfExperience + ")");
        System.out.println("     • First initial: '" + firstInitial + "'");
        
        // ============================================================================
        // VALIDATION AND INSIGHTS
        // ============================================================================
        
        System.out.println("\n✅ DATA VALIDATION:");
        
        // Age validation
        boolean ageIsReasonable = ageInYears > 0 && ageInYears < 120;
        System.out.println("   Age is reasonable (0-120): " + ageIsReasonable);
        
        // Physical measurements validation
        boolean heightIsReasonable = heightInInches > 24 && heightInInches < 96; // 2-8 feet
        boolean weightIsReasonable = weightInPounds > 50 && weightInPounds < 500;
        System.out.println("   Height is reasonable (24-96 inches): " + heightIsReasonable);
        System.out.println("   Weight is reasonable (50-500 pounds): " + weightIsReasonable);
        
        // Experience validation
        boolean experienceIsReasonable = yearsOfExperience >= 0 && yearsOfExperience <= ageInYears;
        System.out.println("   Programming experience is reasonable: " + experienceIsReasonable);
        
        // ============================================================================
        // SYSTEM INFORMATION AND CONSTANTS USAGE
        // ============================================================================
        
        System.out.println("\n⚙️  SYSTEM CONFIGURATION:");
        System.out.println("   Current year: " + CURRENT_YEAR);
        System.out.println("   Retirement age: " + RETIREMENT_AGE);
        System.out.println("   Conversion factors:");
        System.out.println("     • Inches to CM: " + INCHES_TO_CM);
        System.out.println("     • Pounds to KG: " + POUNDS_TO_KG);
        System.out.println("   Experience thresholds:");
        System.out.println("     • Beginner < " + BEGINNER_THRESHOLD + " years");
        System.out.println("     • Advanced > " + ADVANCED_THRESHOLD + " years");
        
        System.out.println("\n" + "=".repeat(60));
        
        // ============================================================================
        // EDUCATIONAL COMMENTARY
        // ============================================================================
        
        System.out.println("\n📚 DATA TYPE USAGE EXPLANATION:");
        System.out.println("   • String: Names, email, phone (textual data)");
        System.out.println("   • int: Age, height in inches (whole numbers)");
        System.out.println("   • double: Weight, BMI, experience (precise decimals)");
        System.out.println("   • boolean: Student status, interests (true/false)");
        System.out.println("   • char: Eye color code (single character)");
        System.out.println("   • long: Lucky number (potentially large integer)");
        System.out.println("   • final: Constants that never change");
        
        System.out.println("\n🎯 KEY LEARNING POINTS:");
        System.out.println("   ✓ Chose appropriate data types for each kind of information");
        System.out.println("   ✓ Used constants for values that don't change");
        System.out.println("   ✓ Demonstrated both automatic and manual type conversion");
        System.out.println("   ✓ Performed meaningful calculations with different data types");
        System.out.println("   ✓ Validated data for reasonableness");
        System.out.println("   ✓ Formatted output for readability");
    }
    
    /**
     * Helper method to convert eye color code to readable name
     * (This demonstrates method usage, which we'll learn more about later)
     * 
     * @param code The single character eye color code
     * @return The full name of the eye color
     */
    private static String getEyeColorName(char code) {
        switch (code) {
            case 'B': return "Blue";
            case 'G': return "Green";
            case 'H': return "Hazel";
            case 'R': return "Brown";
            default: return "Unknown";
        }
    }
}

/*
 * SOLUTION ANALYSIS:
 * 
 * This solution demonstrates mastery of variables and data types by:
 * 
 * 1. APPROPRIATE DATA TYPE SELECTION:
 *    - String for textual data (names, email, phone)
 *    - int for whole number counts (age, height in inches)
 *    - double for precise decimal calculations (weight, BMI, experience)
 *    - boolean for true/false flags (student status, interests)
 *    - char for single character codes (eye color)
 *    - long for potentially large numbers (lucky number)
 *    - final for constants that never change
 * 
 * 2. COMPREHENSIVE CALCULATIONS:
 *    - Age conversions (years to months/days)
 *    - Unit conversions (imperial to metric)
 *    - Complex formulas (BMI calculation)
 *    - Conditional logic (experience level, BMI category)
 * 
 * 3. TYPE CONVERSION MASTERY:
 *    - Automatic widening conversions (int → long, int → double)
 *    - Manual narrowing conversions with casting
 *    - String conversions for display
 *    - Demonstration of potential data loss
 * 
 * 4. BEST PRACTICES:
 *    - Descriptive variable names in camelCase
 *    - Constants in UPPER_CASE
 *    - Meaningful comments explaining choices
 *    - Data validation for reasonableness
 *    - Well-formatted, readable output
 * 
 * 5. REAL-WORLD APPLICATION:
 *    - Models a practical scenario (personal profile)
 *    - Uses realistic data and calculations
 *    - Demonstrates how different data types work together
 *    - Shows the importance of choosing the right type for each use case
 */
