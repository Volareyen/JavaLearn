/**
 * Advanced Calculator System
 * 
 * A practical example demonstrating all categories of operators
 * in a realistic scenario - a comprehensive calculator that performs
 * various mathematical, logical, and comparison operations.
 * 
 * This program shows how operators work together to create
 * meaningful functionality in real-world applications.
 */
public class _PracticalExample {
    
    public static void main(String[] args) {
        
        // ============================================================================
        // CALCULATOR INPUT DATA
        // ============================================================================
        
        System.out.println("=".repeat(70));
        System.out.println("                    ADVANCED CALCULATOR SYSTEM");
        System.out.println("=".repeat(70));
        
        // Input values for our calculations
        double num1 = 45.5;
        double num2 = 12.3;
        int intNum1 = 17;
        int intNum2 = 5;
        int year = 2024;
        int userAge = 25;
        String userInput = "Calculator";
        boolean isAdvancedMode = true;
        boolean hasPermission = true;
        
        System.out.println("\n📊 INPUT VALUES:");
        System.out.println("   Decimal Number 1: " + num1);
        System.out.println("   Decimal Number 2: " + num2);
        System.out.println("   Integer Number 1: " + intNum1);
        System.out.println("   Integer Number 2: " + intNum2);
        System.out.println("   Current Year: " + year);
        System.out.println("   User Age: " + userAge);
        System.out.println("   User Input: \"" + userInput + "\"");
        System.out.println("   Advanced Mode: " + isAdvancedMode);
        System.out.println("   Has Permission: " + hasPermission);
        
        // ============================================================================
        // ARITHMETIC OPERATIONS - The Mathematical Foundation
        // ============================================================================
        
        System.out.println("\n🧮 ARITHMETIC OPERATIONS:");
        
        // Basic arithmetic with floating-point numbers
        double sum = num1 + num2;
        double difference = num1 - num2;
        double product = num1 * num2;
        double quotient = num1 / num2;
        double remainder = num1 % num2;
        
        System.out.println("   Floating-Point Arithmetic:");
        System.out.println("     " + num1 + " + " + num2 + " = " + String.format("%.2f", sum));
        System.out.println("     " + num1 + " - " + num2 + " = " + String.format("%.2f", difference));
        System.out.println("     " + num1 + " × " + num2 + " = " + String.format("%.2f", product));
        System.out.println("     " + num1 + " ÷ " + num2 + " = " + String.format("%.4f", quotient));
        System.out.println("     " + num1 + " % " + num2 + " = " + String.format("%.2f", remainder));
        
        // Integer arithmetic (showing integer division behavior)
        int intSum = intNum1 + intNum2;
        int intDifference = intNum1 - intNum2;
        int intProduct = intNum1 * intNum2;
        int intQuotient = intNum1 / intNum2;      // Integer division!
        int intRemainder = intNum1 % intNum2;
        
        System.out.println("\n   Integer Arithmetic:");
        System.out.println("     " + intNum1 + " + " + intNum2 + " = " + intSum);
        System.out.println("     " + intNum1 + " - " + intNum2 + " = " + intDifference);
        System.out.println("     " + intNum1 + " × " + intNum2 + " = " + intProduct);
        System.out.println("     " + intNum1 + " ÷ " + intNum2 + " = " + intQuotient + " (integer division)");
        System.out.println("     " + intNum1 + " % " + intNum2 + " = " + intRemainder + " (remainder)");
        
        // Demonstrating the difference between integer and floating-point division
        double preciseQuotient = (double) intNum1 / intNum2;
        System.out.println("     " + intNum1 + " ÷ " + intNum2 + " = " + String.format("%.4f", preciseQuotient) + " (precise division)");
        
        // Unary operators
        int positiveValue = +intNum1;  // Unary plus (rarely used)
        int negativeValue = -intNum1;  // Unary minus (negation)
        
        System.out.println("\n   Unary Operations:");
        System.out.println("     +" + intNum1 + " = " + positiveValue);
        System.out.println("     -" + intNum1 + " = " + negativeValue);
        
        // Increment and decrement operations
        int counter = 10;
        System.out.println("\n   Increment/Decrement Operations:");
        System.out.println("     Initial counter: " + counter);
        System.out.println("     Pre-increment (++counter): " + (++counter) + ", counter is now: " + counter);
        System.out.println("     Post-increment (counter++): " + (counter++) + ", counter is now: " + counter);
        System.out.println("     Pre-decrement (--counter): " + (--counter) + ", counter is now: " + counter);
        System.out.println("     Post-decrement (counter--): " + (counter--) + ", counter is now: " + counter);
        
        // ============================================================================
        // ASSIGNMENT OPERATIONS - Efficient Value Binding
        // ============================================================================
        
        System.out.println("\n📝 ASSIGNMENT OPERATIONS:");
        
        double accumulator = 100.0;
        System.out.println("   Initial accumulator: " + accumulator);
        
        // Compound assignment operators
        accumulator += 25.5;    // accumulator = accumulator + 25.5
        System.out.println("   After += 25.5: " + accumulator);
        
        accumulator -= 15.3;    // accumulator = accumulator - 15.3
        System.out.println("   After -= 15.3: " + String.format("%.2f", accumulator));
        
        accumulator *= 2.0;     // accumulator = accumulator * 2.0
        System.out.println("   After *= 2.0: " + String.format("%.2f", accumulator));
        
        accumulator /= 3.0;     // accumulator = accumulator / 3.0
        System.out.println("   After /= 3.0: " + String.format("%.2f", accumulator));
        
        accumulator %= 50.0;    // accumulator = accumulator % 50.0
        System.out.println("   After %= 50.0: " + String.format("%.2f", accumulator));
        
        // String concatenation assignment
        String message = "Hello";
        message += " ";         // message = message + " "
        message += "World";     // message = message + "World"
        message += "!";         // message = message + "!"
        System.out.println("   String concatenation result: \"" + message + "\"");
        
        // ============================================================================
        // RELATIONAL (COMPARISON) OPERATIONS - Decision Making Foundation
        // ============================================================================
        
        System.out.println("\n🔍 RELATIONAL (COMPARISON) OPERATIONS:");
        
        // Numeric comparisons
        boolean isEqual = (num1 == num2);
        boolean isNotEqual = (num1 != num2);
        boolean isGreater = (num1 > num2);
        boolean isLess = (num1 < num2);
        boolean isGreaterOrEqual = (num1 >= num2);
        boolean isLessOrEqual = (num1 <= num2);
        
        System.out.println("   Comparing " + num1 + " and " + num2 + ":");
        System.out.println("     " + num1 + " == " + num2 + " : " + isEqual);
        System.out.println("     " + num1 + " != " + num2 + " : " + isNotEqual);
        System.out.println("     " + num1 + " > " + num2 + "  : " + isGreater);
        System.out.println("     " + num1 + " < " + num2 + "  : " + isLess);
        System.out.println("     " + num1 + " >= " + num2 + " : " + isGreaterOrEqual);
        System.out.println("     " + num1 + " <= " + num2 + " : " + isLessOrEqual);
        
        // String comparisons (demonstrating proper and improper methods)
        String str1 = "Calculator";
        String str2 = "Calculator";
        String str3 = new String("Calculator");
        String str4 = "CALCULATOR";
        
        System.out.println("\n   String Comparisons:");
        System.out.println("     str1 == str2 (reference): " + (str1 == str2));           // true (string interning)
        System.out.println("     str1 == str3 (reference): " + (str1 == str3));           // false (different objects)
        System.out.println("     str1.equals(str3) (content): " + str1.equals(str3));     // true (content comparison)
        System.out.println("     str1.equals(str4) (case-sensitive): " + str1.equals(str4)); // false
        System.out.println("     str1.equalsIgnoreCase(str4): " + str1.equalsIgnoreCase(str4)); // true
        
        // ============================================================================
        // LOGICAL OPERATIONS - Boolean Logic in Action
        // ============================================================================
        
        System.out.println("\n🧠 LOGICAL OPERATIONS:");
        
        // Simulating user access control
        boolean isAdult = (userAge >= 18);
        boolean isRegistered = true;
        boolean isPremium = false;
        boolean hasValidLicense = true;
        
        System.out.println("   User Status Checks:");
        System.out.println("     Is adult (age >= 18): " + isAdult);
        System.out.println("     Is registered: " + isRegistered);
        System.out.println("     Is premium: " + isPremium);
        System.out.println("     Has valid license: " + hasValidLicense);
        
        // Logical AND operations
        boolean canUseBasicFeatures = isAdult && isRegistered;
        boolean canUsePremiumFeatures = isAdult && isRegistered && isPremium;
        boolean canDriveRentalCar = isAdult && hasValidLicense && isRegistered;
        
        System.out.println("\n   Access Control Results (AND operations):");
        System.out.println("     Can use basic features: " + canUseBasicFeatures);
        System.out.println("     Can use premium features: " + canUsePremiumFeatures);
        System.out.println("     Can drive rental car: " + canDriveRentalCar);
        
        // Logical OR operations
        boolean hasAnyAccess = isRegistered || isPremium;
        boolean canGetDiscount = isAdult || isPremium || hasValidLicense;
        
        System.out.println("\n   Flexible Access (OR operations):");
        System.out.println("     Has any access: " + hasAnyAccess);
        System.out.println("     Can get discount: " + canGetDiscount);
        
        // Logical NOT operations
        boolean isMinor = !isAdult;
        boolean needsUpgrade = !isPremium;
        boolean isUnregistered = !isRegistered;
        
        System.out.println("\n   Negation Operations (NOT):");
        System.out.println("     Is minor: " + isMinor);
        System.out.println("     Needs upgrade: " + needsUpgrade);
        System.out.println("     Is unregistered: " + isUnregistered);
        
        // Complex logical expressions
        boolean complexCondition1 = (isAdult && isRegistered) || (isPremium && hasValidLicense);
        boolean complexCondition2 = !(isMinor || isUnregistered) && (hasValidLicense || isPremium);
        
        System.out.println("\n   Complex Logical Expressions:");
        System.out.println("     Complex condition 1: " + complexCondition1);
        System.out.println("     Complex condition 2: " + complexCondition2);
        
        // Short-circuit evaluation demonstration
        System.out.println("\n   Short-Circuit Evaluation:");
        boolean shortCircuit1 = false && (10/0 > 1);  // No exception - second part not evaluated
        boolean shortCircuit2 = true || (10/0 > 1);   // No exception - second part not evaluated
        System.out.println("     false && (10/0 > 1): " + shortCircuit1 + " (no exception!)");
        System.out.println("     true || (10/0 > 1): " + shortCircuit2 + " (no exception!)");
        
        // ============================================================================
        // TERNARY OPERATOR - Conditional Assignment
        // ============================================================================
        
        System.out.println("\n❓ TERNARY OPERATOR EXAMPLES:");
        
        // Simple ternary operations
        String ageCategory = (userAge >= 18) ? "Adult" : "Minor";
        String accessLevel = (isPremium) ? "Premium" : "Basic";
        int maxValue = (intNum1 > intNum2) ? intNum1 : intNum2;
        double absoluteValue = (difference >= 0) ? difference : -difference;
        
        System.out.println("   Simple Ternary Results:");
        System.out.println("     Age category: " + ageCategory);
        System.out.println("     Access level: " + accessLevel);
        System.out.println("     Max of " + intNum1 + " and " + intNum2 + ": " + maxValue);
        System.out.println("     Absolute value of difference: " + String.format("%.2f", absoluteValue));
        
        // Nested ternary operations
        String detailedAgeCategory = (userAge < 13) ? "Child" :
                                    (userAge < 18) ? "Teen" :
                                    (userAge < 65) ? "Adult" : "Senior";
        
        String gradeFromScore = (intSum >= 90) ? "A" :
                               (intSum >= 80) ? "B" :
                               (intSum >= 70) ? "C" :
                               (intSum >= 60) ? "D" : "F";
        
        System.out.println("\n   Nested Ternary Results:");
        System.out.println("     Detailed age category: " + detailedAgeCategory);
        System.out.println("     Grade from sum (" + intSum + "): " + gradeFromScore);
        
        // ============================================================================
        // BITWISE OPERATIONS - Binary Level Manipulation
        // ============================================================================
        
        System.out.println("\n🔢 BITWISE OPERATIONS:");
        
        int bits1 = 12;  // Binary: 1100
        int bits2 = 10;  // Binary: 1010
        
        System.out.println("   Working with " + bits1 + " (binary: " + Integer.toBinaryString(bits1) + 
                          ") and " + bits2 + " (binary: " + Integer.toBinaryString(bits2) + "):");
        
        int bitwiseAnd = bits1 & bits2;     // 1000 = 8
        int bitwiseOr = bits1 | bits2;      // 1110 = 14
        int bitwiseXor = bits1 ^ bits2;     // 0110 = 6
        int bitwiseNot = ~bits1;            // Flip all bits
        
        System.out.println("     " + bits1 + " & " + bits2 + " = " + bitwiseAnd + " (binary: " + Integer.toBinaryString(bitwiseAnd) + ")");
        System.out.println("     " + bits1 + " | " + bits2 + " = " + bitwiseOr + " (binary: " + Integer.toBinaryString(bitwiseOr) + ")");
        System.out.println("     " + bits1 + " ^ " + bits2 + " = " + bitwiseXor + " (binary: " + Integer.toBinaryString(bitwiseXor) + ")");
        System.out.println("     ~" + bits1 + " = " + bitwiseNot + " (bitwise NOT)");
        
        // Shift operations
        int leftShift = bits1 << 2;         // Multiply by 2^2 = 4
        int rightShift = bits1 >> 2;        // Divide by 2^2 = 4
        
        System.out.println("\n   Shift Operations:");
        System.out.println("     " + bits1 + " << 2 = " + leftShift + " (multiply by 4)");
        System.out.println("     " + bits1 + " >> 2 = " + rightShift + " (divide by 4)");
        
        // Practical bitwise applications
        boolean isEvenBitwise = (intNum1 & 1) == 0;  // Check if even using bitwise AND
        int fastMultiplyBy8 = intNum1 << 3;          // Multiply by 8 using left shift
        int fastDivideBy4 = intNum1 >> 2;            // Divide by 4 using right shift
        
        System.out.println("\n   Practical Bitwise Applications:");
        System.out.println("     " + intNum1 + " is even (bitwise check): " + isEvenBitwise);
        System.out.println("     " + intNum1 + " × 8 (shift): " + fastMultiplyBy8);
        System.out.println("     " + intNum1 + " ÷ 4 (shift): " + fastDivideBy4);
        
        // ============================================================================
        // REAL-WORLD APPLICATION - Grade Calculator
        // ============================================================================
        
        System.out.println("\n🎓 REAL-WORLD APPLICATION: Grade Calculator");
        
        // Student scores
        int exam1 = 85;
        int exam2 = 92;
        int exam3 = 78;
        int homework = 88;
        int participation = 95;
        
        // Weights
        double examWeight = 0.6;        // 60% for exams
        double homeworkWeight = 0.3;    // 30% for homework
        double participationWeight = 0.1; // 10% for participation
        
        // Calculate weighted average
        double examAverage = (exam1 + exam2 + exam3) / 3.0;
        double finalGrade = (examAverage * examWeight) + 
                           (homework * homeworkWeight) + 
                           (participation * participationWeight);
        
        // Determine letter grade using ternary operators
        char letterGrade = (finalGrade >= 90) ? 'A' :
                          (finalGrade >= 80) ? 'B' :
                          (finalGrade >= 70) ? 'C' :
                          (finalGrade >= 60) ? 'D' : 'F';
        
        // Pass/fail determination
        boolean isPassing = finalGrade >= 60;
        boolean isHonors = finalGrade >= 90;
        boolean needsImprovement = finalGrade < 70;
        
        System.out.println("   Individual Scores:");
        System.out.println("     Exam 1: " + exam1);
        System.out.println("     Exam 2: " + exam2);
        System.out.println("     Exam 3: " + exam3);
        System.out.println("     Homework: " + homework);
        System.out.println("     Participation: " + participation);
        
        System.out.println("\n   Calculations:");
        System.out.println("     Exam Average: " + String.format("%.2f", examAverage));
        System.out.println("     Final Grade: " + String.format("%.2f", finalGrade));
        System.out.println("     Letter Grade: " + letterGrade);
        
        System.out.println("\n   Status:");
        System.out.println("     Passing: " + isPassing);
        System.out.println("     Honors: " + isHonors);
        System.out.println("     Needs Improvement: " + needsImprovement);
        
        // ============================================================================
        // SUMMARY AND INSIGHTS
        // ============================================================================
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("📋 OPERATOR USAGE SUMMARY:");
        System.out.println("   ✓ Arithmetic: Performed mathematical calculations with precision");
        System.out.println("   ✓ Assignment: Efficiently updated variables using compound operators");
        System.out.println("   ✓ Relational: Compared values for decision-making logic");
        System.out.println("   ✓ Logical: Combined conditions for complex business rules");
        System.out.println("   ✓ Ternary: Created concise conditional assignments");
        System.out.println("   ✓ Bitwise: Demonstrated low-level binary operations");
        System.out.println("\n🎯 KEY INSIGHTS:");
        System.out.println("   • Integer division truncates decimal portions");
        System.out.println("   • Use .equals() for object content comparison");
        System.out.println("   • Short-circuit evaluation prevents unnecessary computations");
        System.out.println("   • Compound assignment operators are more efficient");
        System.out.println("   • Ternary operator provides elegant conditional assignment");
        System.out.println("   • Bitwise operations enable efficient mathematical tricks");
        System.out.println("=".repeat(70));
    }
}

/*
 * PRACTICAL LEARNING POINTS:
 * 
 * 1. OPERATOR SELECTION:
 *    - Choose the right operator for each task
 *    - Understand the difference between similar operators (== vs .equals())
 *    - Consider performance implications (bitwise vs arithmetic)
 * 
 * 2. REAL-WORLD APPLICATIONS:
 *    - Operators form the foundation of all program logic
 *    - Complex business rules combine multiple operator types
 *    - Proper operator usage prevents bugs and improves performance
 * 
 * 3. COMMON PATTERNS:
 *    - Range checking: (value >= min) && (value <= max)
 *    - Null safety: (object != null) && (object.method())
 *    - Even/odd checking: (number % 2 == 0)
 *    - Min/max finding: (a > b) ? a : b
 * 
 * 4. BEST PRACTICES:
 *    - Use parentheses to clarify complex expressions
 *    - Prefer compound assignment operators for efficiency
 *    - Be aware of operator precedence rules
 *    - Use appropriate data types to avoid precision loss
 */
