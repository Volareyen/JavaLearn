/**
 * Operators - Pure Syntax Demonstration
 * 
 * This file demonstrates the raw syntax for all Java operators
 * organized by category for easy reference.
 * 
 * Purpose: Complete reference guide for operator syntax
 */
public class _Concept {
    
    public static void main(String[] args) {
        
        // ============================================================================
        // ARITHMETIC OPERATORS - Mathematical operations
        // ============================================================================
        
        int a = 10, b = 3;
        
        // Basic arithmetic operations
        int addition = a + b;           // 13 (sum)
        int subtraction = a - b;        // 7  (difference)
        int multiplication = a * b;     // 30 (product)
        int division = a / b;           // 3  (quotient - integer division!)
        int modulus = a % b;            // 1  (remainder)
        
        // Unary operators
        int positive = +a;              // +10 (unary plus, rarely used)
        int negative = -a;              // -10 (unary minus, negation)
        
        // Increment and decrement operators
        int x = 5;
        int preIncrement = ++x;         // Increment first, then use (x=6, result=6)
        int postIncrement = x++;        // Use first, then increment (result=6, x=7)
        int preDecrement = --x;         // Decrement first, then use (x=6, result=6)
        int postDecrement = x--;        // Use first, then decrement (result=6, x=5)
        
        // Floating-point arithmetic
        double d1 = 10.0, d2 = 3.0;
        double floatDivision = d1 / d2; // 3.3333... (true division)
        double mixedDivision = a / d2;  // 3.3333... (int promoted to double)
        
        // ============================================================================
        // ASSIGNMENT OPERATORS - Binding values to variables
        // ============================================================================
        
        int y = 10;                     // Simple assignment
        
        // Compound assignment operators
        y += 5;                         // y = y + 5;  (y becomes 15)
        y -= 3;                         // y = y - 3;  (y becomes 12)
        y *= 2;                         // y = y * 2;  (y becomes 24)
        y /= 4;                         // y = y / 4;  (y becomes 6)
        y %= 5;                         // y = y % 5;  (y becomes 1)
        
        // String concatenation assignment
        String message = "Hello";
        message += " World";            // message = message + " World";
        message += "!";                 // "Hello World!"
        
        // ============================================================================
        // RELATIONAL (COMPARISON) OPERATORS - Comparing values
        // ============================================================================
        
        int num1 = 10, num2 = 5, num3 = 10;
        
        boolean equal = (num1 == num3);         // true  (equality)
        boolean notEqual = (num1 != num2);      // true  (inequality)
        boolean greater = (num1 > num2);        // true  (greater than)
        boolean less = (num1 < num2);           // false (less than)
        boolean greaterEqual = (num1 >= num3);  // true  (greater than or equal)
        boolean lessEqual = (num2 <= num1);     // true  (less than or equal)
        
        // String comparison (IMPORTANT: Use equals() method for objects)
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");
        
        boolean stringRefEqual = (str1 == str2);        // true (string literals)
        boolean stringRefNotEqual = (str1 == str3);     // false (different objects)
        boolean stringContentEqual = str1.equals(str3); // true (content comparison)
        
        // ============================================================================
        // LOGICAL OPERATORS - Boolean logic operations
        // ============================================================================
        
        boolean sunny = true;
        boolean warm = false;
        boolean weekend = true;
        
        // Logical AND (&&) - both operands must be true
        boolean perfectDay = sunny && warm;         // false (warm is false)
        boolean goodWeekend = sunny && weekend;     // true (both are true)
        
        // Logical OR (||) - at least one operand must be true
        boolean niceDay = sunny || warm;            // true (sunny is true)
        boolean stayHome = !sunny || !warm;         // true (not warm is true)
        
        // Logical NOT (!) - reverses boolean value
        boolean cloudy = !sunny;                    // false (sunny is true)
        boolean cold = !warm;                       // true (warm is false)
        
        // Complex logical expressions
        boolean canGoOut = (sunny || warm) && weekend && !cold;
        
        // Short-circuit evaluation demonstration
        boolean shortCircuit1 = false && (5/0 > 1); // No exception! (5/0 not evaluated)
        boolean shortCircuit2 = true || (5/0 > 1);  // No exception! (5/0 not evaluated)
        
        // ============================================================================
        // BITWISE OPERATORS - Binary manipulation (advanced)
        // ============================================================================
        
        int bits1 = 5;      // Binary: 101
        int bits2 = 3;      // Binary: 011
        
        // Bitwise operations
        int bitwiseAnd = bits1 & bits2;     // 001 = 1 (AND each bit)
        int bitwiseOr = bits1 | bits2;      // 111 = 7 (OR each bit)
        int bitwiseXor = bits1 ^ bits2;     // 110 = 6 (XOR each bit)
        int bitwiseNot = ~bits1;            // Flip all bits (-6 in two's complement)
        
        // Shift operations
        int leftShift = bits1 << 1;         // 1010 = 10 (multiply by 2^1)
        int rightShift = bits1 >> 1;        // 10 = 2 (divide by 2^1)
        int unsignedRightShift = bits1 >>> 1; // 10 = 2 (unsigned right shift)
        
        // Compound bitwise assignment
        int bitwiseVar = 8;
        bitwiseVar &= 3;    // bitwiseVar = bitwiseVar & 3
        bitwiseVar |= 5;    // bitwiseVar = bitwiseVar | 5
        bitwiseVar ^= 2;    // bitwiseVar = bitwiseVar ^ 2
        bitwiseVar <<= 1;   // bitwiseVar = bitwiseVar << 1
        bitwiseVar >>= 2;   // bitwiseVar = bitwiseVar >> 2
        
        // ============================================================================
        // TERNARY OPERATOR - Conditional assignment
        // ============================================================================
        
        int age = 20;
        
        // Basic ternary: condition ? valueIfTrue : valueIfFalse
        String status = (age >= 18) ? "Adult" : "Minor";
        
        // Nested ternary (use sparingly for readability)
        String category = (age < 13) ? "Child" :
                         (age < 18) ? "Teen" :
                         (age < 65) ? "Adult" : "Senior";
        
        // Ternary with different data types (must be compatible)
        Object result = (age >= 18) ? "Adult" : 17;  // Both become Object
        
        // Ternary in expressions
        int max = (a > b) ? a : b;                   // Find maximum
        int absValue = (num1 >= 0) ? num1 : -num1;   // Absolute value
        
        // ============================================================================
        // OPERATOR PRECEDENCE EXAMPLES
        // ============================================================================
        
        // Arithmetic precedence
        int precedence1 = 2 + 3 * 4;        // 14 (not 20) - multiplication first
        int precedence2 = (2 + 3) * 4;      // 20 - parentheses override
        
        // Mixed operators precedence
        boolean precedence3 = 5 > 3 && 2 < 4;           // true - relational first, then logical
        boolean precedence4 = !false && true || false;   // true - NOT, then AND, then OR
        
        // Assignment precedence (lowest)
        int precedence5 = a = b = 5;        // Right-to-left: b=5, then a=b, result=5
        
        // ============================================================================
        // STRING OPERATIONS - Special operator behaviors with Strings
        // ============================================================================
        
        // String concatenation with +
        String concat1 = "Hello" + " " + "World";       // "Hello World"
        String concat2 = "Age: " + 25;                  // "Age: 25"
        String concat3 = "Sum: " + (2 + 3);             // "Sum: 5" (parentheses force arithmetic)
        String concat4 = "Result: " + 2 + 3;            // "Result: 23" (left-to-right concatenation)
        
        // String concatenation with compound assignment
        String builder = "Start";
        builder += " Middle";               // "Start Middle"
        builder += " End";                  // "Start Middle End"
        
        // ============================================================================
        // TYPE PROMOTION IN OPERATIONS
        // ============================================================================
        
        byte smallByte = 10;
        short smallShort = 20;
        int normalInt = 30;
        long bigLong = 40L;
        float floatNum = 50.5f;
        double doubleNum = 60.5;
        
        // Automatic promotion in arithmetic
        int promoted1 = smallByte + smallShort;  // byte + short → int
        long promoted2 = normalInt + bigLong;    // int + long → long
        float promoted3 = normalInt + floatNum;  // int + float → float
        double promoted4 = floatNum + doubleNum; // float + double → double
        
        // Mixed type operations
        double mixed = smallByte + normalInt + floatNum + doubleNum; // All promoted to double
        
        // ============================================================================
        // COMMON OPERATOR COMBINATIONS
        // ============================================================================
        
        // Range checking
        int value = 75;
        boolean inRange = (value >= 0) && (value <= 100);
        boolean outOfRange = (value < 0) || (value > 100);
        
        // Null-safe operations
        String nullableString = null;
        boolean safeCheck = (nullableString != null) && (nullableString.length() > 0);
        
        // Even/odd checking
        int number = 42;
        boolean isEven = (number % 2 == 0);
        boolean isOdd = (number % 2 != 0);
        
        // Sign checking
        int signTest = -5;
        boolean isPositive = (signTest > 0);
        boolean isNegative = (signTest < 0);
        boolean isZero = (signTest == 0);
        
        // Multiple condition checking
        int score = 85;
        boolean excellentScore = (score >= 90) && (score <= 100);
        boolean passingScore = (score >= 60) && (score < 90);
        boolean failingScore = (score < 60);
        
        // ============================================================================
        // DEMONSTRATION OUTPUT (normally you'd use these values)
        // ============================================================================
        
        // In a real program, you would use these computed values
        // This section just demonstrates that all operations completed successfully
        System.out.println("All operator demonstrations completed successfully!");
        System.out.println("Variables computed: addition=" + addition + 
                          ", status=" + status + 
                          ", inRange=" + inRange);
    }
}

/*
 * OPERATOR SYNTAX SUMMARY:
 * 
 * ARITHMETIC:     +, -, *, /, %, ++, --, +expr, -expr
 * ASSIGNMENT:     =, +=, -=, *=, /=, %=
 * RELATIONAL:     ==, !=, <, >, <=, >=
 * LOGICAL:        &&, ||, !
 * BITWISE:        &, |, ^, ~, <<, >>, >>>
 * TERNARY:        condition ? trueValue : falseValue
 * 
 * PRECEDENCE (High to Low):
 * 1. Postfix:     expr++, expr--
 * 2. Unary:       ++expr, --expr, +expr, -expr, !, ~
 * 3. Multiplicative: *, /, %
 * 4. Additive:    +, -
 * 5. Shift:       <<, >>, >>>
 * 6. Relational:  <, >, <=, >=
 * 7. Equality:    ==, !=
 * 8. Bitwise AND: &
 * 9. Bitwise XOR: ^
 * 10. Bitwise OR: |
 * 11. Logical AND: &&
 * 12. Logical OR: ||
 * 13. Ternary:    ? :
 * 14. Assignment: =, +=, -=, etc.
 */
