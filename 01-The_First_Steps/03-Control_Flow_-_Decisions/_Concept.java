/**
 * Control Flow - Decisions - Pure Syntax Demonstration
 * 
 * This file demonstrates the raw syntax for all Java decision-making
 * constructs: if, if-else, if-else if-else, nested if, and switch.
 * 
 * Purpose: Complete reference guide for conditional statement syntax
 */
public class _Concept {
    
    public static void main(String[] args) {
        
        // ============================================================================
        // BASIC IF STATEMENT - Single condition
        // ============================================================================
        
        int number = 10;
        boolean condition = true;
        
        // Simple if statement
        if (number > 5) {
            System.out.println("Number is greater than 5");
        }
        
        // If with boolean variable
        if (condition) {
            System.out.println("Condition is true");
        }
        
        // If with complex condition
        if ((number > 0) && (number < 20)) {
            System.out.println("Number is between 0 and 20");
        }
        
        // If with method call (returns boolean)
        String text = "Hello";
        if (text.startsWith("H")) {
            System.out.println("Text starts with H");
        }
        
        // ============================================================================
        // IF-ELSE STATEMENT - Two-way branching
        // ============================================================================
        
        int age = 20;
        
        // Basic if-else
        if (age >= 18) {
            System.out.println("You are an adult");
        } else {
            System.out.println("You are a minor");
        }
        
        // If-else with complex conditions
        boolean hasLicense = true;
        if ((age >= 16) && hasLicense) {
            System.out.println("You can drive");
        } else {
            System.out.println("You cannot drive");
        }
        
        // If-else with multiple statements in each block
        double balance = 150.50;
        if (balance >= 100.0) {
            System.out.println("Sufficient funds");
            System.out.println("Transaction approved");
            balance -= 50.0;  // Deduct amount
        } else {
            System.out.println("Insufficient funds");
            System.out.println("Transaction denied");
        }
        
        // ============================================================================
        // IF-ELSE IF-ELSE CHAIN - Multiple conditions
        // ============================================================================
        
        int score = 85;
        
        // Grade assignment with if-else if chain
        if (score >= 90) {
            System.out.println("Grade A - Excellent!");
        } else if (score >= 80) {
            System.out.println("Grade B - Good work!");
        } else if (score >= 70) {
            System.out.println("Grade C - Satisfactory");
        } else if (score >= 60) {
            System.out.println("Grade D - Needs improvement");
        } else {
            System.out.println("Grade F - Failed");
        }
        
        // Weather response system
        String weather = "sunny";
        if (weather.equals("sunny")) {
            System.out.println("Great day for outdoor activities!");
        } else if (weather.equals("cloudy")) {
            System.out.println("Good day for a walk");
        } else if (weather.equals("rainy")) {
            System.out.println("Stay inside and read a book");
        } else if (weather.equals("snowy")) {
            System.out.println("Time for hot chocolate!");
        } else {
            System.out.println("Unknown weather condition");
        }
        
        // Multiple variable conditions
        int temperature = 75;
        boolean isWeekend = true;
        if ((temperature > 80) && isWeekend) {
            System.out.println("Perfect beach day!");
        } else if ((temperature > 70) && isWeekend) {
            System.out.println("Good day for outdoor activities");
        } else if (temperature > 80) {
            System.out.println("Hot workday - stay cool");
        } else if (isWeekend) {
            System.out.println("Relaxing weekend indoors");
        } else {
            System.out.println("Regular day");
        }
        
        // ============================================================================
        // NESTED IF STATEMENTS - Decisions within decisions
        // ============================================================================
        
        boolean hasAccount = true;
        boolean isActive = true;
        String accountType = "premium";
        double accountBalance = 1000.0;
        
        // Nested if structure
        if (hasAccount) {
            System.out.println("Account found");
            
            if (isActive) {
                System.out.println("Account is active");
                
                if (accountType.equals("premium")) {
                    System.out.println("Premium account benefits available");
                    
                    if (accountBalance > 500.0) {
                        System.out.println("High balance - VIP treatment");
                    } else {
                        System.out.println("Standard premium treatment");
                    }
                    
                } else if (accountType.equals("standard")) {
                    System.out.println("Standard account access");
                } else {
                    System.out.println("Unknown account type");
                }
                
            } else {
                System.out.println("Account is inactive - please reactivate");
            }
            
        } else {
            System.out.println("No account found - please register");
        }
        
        // Nested if with different logic paths
        int userAge = 25;
        boolean hasJob = true;
        int creditScore = 720;
        
        if (userAge >= 18) {
            if (hasJob) {
                if (creditScore >= 700) {
                    System.out.println("Loan approved - excellent terms");
                } else if (creditScore >= 600) {
                    System.out.println("Loan approved - standard terms");
                } else {
                    System.out.println("Loan denied - poor credit");
                }
            } else {
                System.out.println("Loan denied - no employment");
            }
        } else {
            System.out.println("Loan denied - must be 18 or older");
        }
        
        // ============================================================================
        // SWITCH STATEMENT - Multi-way branching on single value
        // ============================================================================
        
        // Switch with int
        int dayOfWeek = 3;
        switch (dayOfWeek) {
            case 1:
                System.out.println("Monday - Start of work week");
                break;
            case 2:
                System.out.println("Tuesday - Getting into rhythm");
                break;
            case 3:
                System.out.println("Wednesday - Hump day");
                break;
            case 4:
                System.out.println("Thursday - Almost weekend");
                break;
            case 5:
                System.out.println("Friday - TGIF!");
                break;
            case 6:
                System.out.println("Saturday - Weekend fun");
                break;
            case 7:
                System.out.println("Sunday - Rest and prepare");
                break;
            default:
                System.out.println("Invalid day number");
                break;
        }
        
        // Switch with char
        char grade = 'B';
        switch (grade) {
            case 'A':
                System.out.println("Excellent work!");
                break;
            case 'B':
                System.out.println("Good job!");
                break;
            case 'C':
                System.out.println("Satisfactory");
                break;
            case 'D':
                System.out.println("Needs improvement");
                break;
            case 'F':
                System.out.println("Failed");
                break;
            default:
                System.out.println("Invalid grade");
                break;
        }
        
        // Switch with String (Java 7+)
        String command = "save";
        switch (command.toLowerCase()) {
            case "save":
                System.out.println("Saving file...");
                break;
            case "load":
                System.out.println("Loading file...");
                break;
            case "delete":
                System.out.println("Deleting file...");
                break;
            case "exit":
                System.out.println("Exiting program...");
                break;
            default:
                System.out.println("Unknown command");
                break;
        }
        
        // Switch with fall-through (intentional)
        char letterGrade = 'B';
        switch (letterGrade) {
            case 'A':
            case 'B':
            case 'C':
                System.out.println("You passed!");
                break;
            case 'D':
            case 'F':
                System.out.println("You failed");
                break;
            default:
                System.out.println("Invalid grade");
                break;
        }
        
        // Switch with multiple statements per case
        String season = "spring";
        switch (season.toLowerCase()) {
            case "spring":
                System.out.println("Season: Spring");
                System.out.println("Weather: Mild and pleasant");
                System.out.println("Activities: Gardening, hiking");
                break;
            case "summer":
                System.out.println("Season: Summer");
                System.out.println("Weather: Hot and sunny");
                System.out.println("Activities: Swimming, camping");
                break;
            case "fall":
            case "autumn":
                System.out.println("Season: Fall/Autumn");
                System.out.println("Weather: Cool and crisp");
                System.out.println("Activities: Leaf watching, harvesting");
                break;
            case "winter":
                System.out.println("Season: Winter");
                System.out.println("Weather: Cold and snowy");
                System.out.println("Activities: Skiing, indoor activities");
                break;
            default:
                System.out.println("Unknown season");
                break;
        }
        
        // ============================================================================
        // CONDITIONAL EXPRESSIONS AND PATTERNS
        // ============================================================================
        
        // Using boolean variables for cleaner conditions
        boolean isAdult = (userAge >= 18);
        boolean hasGoodCredit = (creditScore >= 650);
        boolean isEmployed = hasJob;
        
        if (isAdult && hasGoodCredit && isEmployed) {
            System.out.println("Qualified for loan");
        }
        
        // Combining conditions with parentheses for clarity
        if ((userAge >= 21) && ((hasJob && (creditScore > 600)) || (accountBalance > 10000))) {
            System.out.println("Qualified for premium credit card");
        }
        
        // Using early returns to avoid deep nesting (in methods)
        // This pattern would be used in a method:
        /*
        if (!hasAccount) {
            System.out.println("No account");
            return;
        }
        if (!isActive) {
            System.out.println("Inactive account");
            return;
        }
        // Main logic here
        */
        
        // ============================================================================
        // COMMON CONDITIONAL PATTERNS
        // ============================================================================
        
        // Range checking
        int testScore = 87;
        if (testScore >= 0 && testScore <= 100) {
            System.out.println("Valid test score: " + testScore);
        } else {
            System.out.println("Invalid test score: " + testScore);
        }
        
        // Null checking (safety pattern)
        String userName = "Alice";
        if (userName != null && userName.length() > 0) {
            System.out.println("Welcome, " + userName);
        } else {
            System.out.println("Welcome, Guest");
        }
        
        // Multiple condition checking with flags
        boolean condition1 = true;
        boolean condition2 = false;
        boolean condition3 = true;
        
        if (condition1 && condition2 && condition3) {
            System.out.println("All conditions met");
        } else if (condition1 && condition3) {
            System.out.println("Conditions 1 and 3 met");
        } else if (condition1) {
            System.out.println("Only condition 1 met");
        } else {
            System.out.println("No conditions met");
        }
        
        // String comparison patterns
        String input = "Hello";
        
        // Case-sensitive comparison
        if (input.equals("Hello")) {
            System.out.println("Exact match");
        }
        
        // Case-insensitive comparison
        if (input.equalsIgnoreCase("hello")) {
            System.out.println("Case-insensitive match");
        }
        
        // Null-safe comparison (constant first)
        if ("Hello".equals(input)) {
            System.out.println("Null-safe comparison");
        }
        
        // Contains check
        if (input.contains("ell")) {
            System.out.println("Contains substring");
        }
        
        System.out.println("All conditional statement examples completed!");
    }
}

/*
 * CONDITIONAL STATEMENT SYNTAX SUMMARY:
 * 
 * IF STATEMENT:
 *   if (condition) { statements; }
 * 
 * IF-ELSE STATEMENT:
 *   if (condition) { statements; } else { statements; }
 * 
 * IF-ELSE IF-ELSE CHAIN:
 *   if (condition1) { statements; }
 *   else if (condition2) { statements; }
 *   else if (condition3) { statements; }
 *   else { statements; }
 * 
 * NESTED IF:
 *   if (condition1) {
 *       if (condition2) { statements; }
 *       else { statements; }
 *   }
 * 
 * SWITCH STATEMENT:
 *   switch (variable) {
 *       case value1: statements; break;
 *       case value2: statements; break;
 *       default: statements; break;
 *   }
 * 
 * KEY POINTS:
 * - Conditions must evaluate to boolean (true/false)
 * - Use braces {} even for single statements (best practice)
 * - Switch requires break statements to prevent fall-through
 * - Order matters in if-else if chains
 * - Use parentheses to clarify complex conditions
 * - Always consider null safety for objects
 */
