/**
 * Variables and Data Types - Pure Syntax Demonstration
 * 
 * This file demonstrates the raw syntax for declaring and initializing
 * variables of all primitive types and the String class.
 * 
 * Purpose: Reference guide for variable declaration syntax
 */
public class _Concept {
    
    public static void main(String[] args) {
        
        // ============================================================================
        // INTEGER TYPES - For whole numbers
        // ============================================================================
        
        // byte: 8 bits, range -128 to 127
        byte byteVariable;                    // Declaration only
        byte initializedByte = 100;           // Declaration with initialization
        byte negativeByte = -50;              // Negative values allowed
        
        // short: 16 bits, range -32,768 to 32,767
        short shortVariable;                  // Declaration only
        short initializedShort = 1000;       // Declaration with initialization
        short negativeShort = -2000;         // Negative values allowed
        
        // int: 32 bits, range -2,147,483,648 to 2,147,483,647
        int intVariable;                      // Declaration only
        int initializedInt = 50000;          // Declaration with initialization
        int negativeInt = -1000000;          // Negative values allowed
        
        // long: 64 bits, very large range
        long longVariable;                    // Declaration only
        long initializedLong = 100000L;      // Note the 'L' suffix for long literals
        long largeLong = 9223372036854775807L; // Maximum long value
        
        // ============================================================================
        // FLOATING-POINT TYPES - For decimal numbers
        // ============================================================================
        
        // float: 32 bits, ~7 decimal digits precision
        float floatVariable;                  // Declaration only
        float initializedFloat = 3.14f;      // Note the 'f' suffix for float literals
        float negativeFloat = -99.99f;       // Negative values allowed
        
        // double: 64 bits, ~15-17 decimal digits precision (preferred for decimals)
        double doubleVariable;                // Declaration only
        double initializedDouble = 3.14159;  // No suffix needed (default for decimals)
        double scientificNotation = 1.23e-4; // Scientific notation: 0.000123
        
        // ============================================================================
        // CHARACTER TYPE - For single characters
        // ============================================================================
        
        char charVariable;                    // Declaration only
        char letterChar = 'A';               // Single quotes for character literals
        char digitChar = '5';                // Digits as characters
        char symbolChar = '$';               // Symbol characters
        char escapeChar = '\n';              // Escape sequences (newline)
        char unicodeChar = '\u0041';         // Unicode notation (represents 'A')
        
        // ============================================================================
        // BOOLEAN TYPE - For true/false values
        // ============================================================================
        
        boolean booleanVariable;              // Declaration only
        boolean trueBoolean = true;          // Boolean literal: true
        boolean falseBoolean = false;        // Boolean literal: false
        
        // ============================================================================
        // STRING TYPE - For text (not a primitive, but commonly used)
        // ============================================================================
        
        String stringVariable;                // Declaration only
        String initializedString = "Hello";  // Double quotes for string literals
        String emptyString = "";              // Empty string
        String multiWordString = "Hello, World!";
        String stringWithNumbers = "Age: 25";
        String stringWithEscapes = "Line 1\nLine 2\tTabbed";
        
        // ============================================================================
        // MULTIPLE VARIABLE DECLARATIONS
        // ============================================================================
        
        // Multiple variables of same type (declaration only)
        int x, y, z;
        
        // Multiple variables with initialization
        int a = 1, b = 2, c = 3;
        
        // Mixed declaration and initialization
        double width = 10.5, height, depth = 5.0;
        
        // ============================================================================
        // CONSTANTS - Variables that cannot be changed
        // ============================================================================
        
        final int CONSTANT_INT = 100;        // Convention: UPPER_CASE names
        final double PI = 3.14159;
        final String COMPANY_NAME = "TechCorp";
        final boolean DEBUG_MODE = true;
        
        // Constants cannot be reassigned after initialization
        // CONSTANT_INT = 200;  // This would cause a compilation error
        
        // ============================================================================
        // TYPE CASTING AND CONVERSION
        // ============================================================================
        
        // Automatic conversion (widening) - smaller to larger types
        int smallInt = 100;
        long autoLong = smallInt;            // int automatically becomes long
        double autoDouble = smallInt;        // int automatically becomes double
        
        // Manual casting (narrowing) - larger to smaller types
        double largeDouble = 99.99;
        int castInt = (int) largeDouble;     // Explicit cast: becomes 99 (truncated)
        
        long largeLongValue = 1000L;
        int castFromLong = (int) largeLongValue; // Safe if value fits
        
        // ============================================================================
        // VARIABLE INITIALIZATION BEFORE USE
        // ============================================================================
        
        // This demonstrates proper initialization
        byteVariable = 50;                   // Initialize before use
        shortVariable = 500;
        intVariable = 50000;
        longVariable = 500000L;
        floatVariable = 5.5f;
        doubleVariable = 5.555;
        charVariable = 'X';
        booleanVariable = true;
        stringVariable = "Initialized";
        
        // Now all variables are ready to use
        // (In a real program, you'd do something with these variables)
    }
}

/*
 * SYNTAX SUMMARY:
 * 
 * Declaration:     dataType variableName;
 * Initialization:  dataType variableName = value;
 * Constants:       final dataType VARIABLE_NAME = value;
 * Multiple vars:   dataType var1, var2, var3;
 * Mixed:           dataType var1 = value1, var2, var3 = value3;
 * Casting:         targetType variable = (targetType) sourceVariable;
 * 
 * LITERAL SUFFIXES:
 * long:    123L or 123l
 * float:   3.14f or 3.14F
 * double:  3.14 (default for decimal numbers)
 * char:    'A' (single quotes)
 * String:  "Hello" (double quotes)
 */
