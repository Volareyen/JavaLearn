/**
 * Methods - Pure Syntax Demonstration
 * 
 * This file demonstrates the raw syntax for all Java method concepts:
 * method declarations, parameters, return types, overloading, recursion,
 * and various method patterns.
 * 
 * Purpose: Complete reference guide for method syntax
 */
public class _Concept {
    
    public static void main(String[] args) {
        
        System.out.println("=== METHOD SYNTAX DEMONSTRATIONS ===");
        
        // ============================================================================
        // BASIC METHOD CALLS - Demonstrating different method types
        // ============================================================================
        
        System.out.println("\n--- Basic Method Calls ---");
        
        // Calling void methods (methods that don't return values)
        displayWelcomeMessage();
        printSeparator(30);
        greetUser("Alice");
        
        // Calling methods that return values
        int sum = addTwoNumbers(15, 25);
        System.out.println("Sum: " + sum);
        
        double area = calculateRectangleArea(5.0, 3.0);
        System.out.println("Rectangle area: " + area);
        
        boolean isEven = checkIfEven(42);
        System.out.println("42 is even: " + isEven);
        
        String message = createGreeting("World");
        System.out.println(message);
        
        // ============================================================================
        // METHOD OVERLOADING - Same name, different parameters
        // ============================================================================
        
        System.out.println("\n--- Method Overloading Examples ---");
        
        // Different versions of calculateArea method
        double circleArea = calculateArea(5.0);                    // Circle (1 parameter)
        double rectangleArea = calculateArea(4.0, 6.0);            // Rectangle (2 parameters)
        double triangleArea = calculateArea(3.0, 4.0, true);       // Triangle (3 parameters)
        
        System.out.println("Circle area (r=5): " + circleArea);
        System.out.println("Rectangle area (4x6): " + rectangleArea);
        System.out.println("Triangle area (b=3, h=4): " + triangleArea);
        
        // Different versions of print method for different data types
        printValue(42);                    // int version
        printValue(3.14159);              // double version
        printValue("Hello Methods!");      // String version
        printValue(true);                 // boolean version
        
        // ============================================================================
        // PARAMETER VARIATIONS - Different ways to pass data to methods
        // ============================================================================
        
        System.out.println("\n--- Parameter Variations ---");
        
        // Single parameter methods
        displaySquare(7);
        String reversed = reverseString("Hello");
        System.out.println("Reversed: " + reversed);
        
        // Multiple parameter methods
        int max = findMaximum(10, 25, 7);
        System.out.println("Maximum of 10, 25, 7: " + max);
        
        double distance = calculateDistance(0, 0, 3, 4);
        System.out.println("Distance from (0,0) to (3,4): " + distance);
        
        // Array parameter methods
        int[] numbers = {5, 2, 8, 1, 9, 3};
        double average = calculateAverage(numbers);
        System.out.println("Average of array: " + average);
        
        int arrayMax = findMaxInArray(numbers);
        System.out.println("Max in array: " + arrayMax);
        
        // Variable arguments (varargs) methods
        int varargsSum = sumNumbers(1, 2, 3, 4, 5);
        System.out.println("Sum using varargs: " + varargsSum);
        
        String combined = combineStrings("Hello", "World", "from", "Methods");
        System.out.println("Combined strings: " + combined);
        
        // ============================================================================
        // RETURN TYPE VARIATIONS - Different types of values methods can return
        // ============================================================================
        
        System.out.println("\n--- Return Type Variations ---");
        
        // Primitive return types
        int intResult = multiplyByTwo(21);
        double doubleResult = convertCelsiusToFahrenheit(25.0);
        boolean boolResult = isPositive(-5);
        char charResult = getFirstCharacter("Methods");
        
        System.out.println("21 * 2 = " + intResult);
        System.out.println("25°C = " + doubleResult + "°F");
        System.out.println("-5 is positive: " + boolResult);
        System.out.println("First char of 'Methods': " + charResult);
        
        // String return type
        String formatted = formatCurrency(1234.56);
        System.out.println("Formatted currency: " + formatted);
        
        // Array return type
        int[] evenNumbers = generateEvenNumbers(10);
        System.out.print("First 10 even numbers: ");
        printArray(evenNumbers);
        
        // ============================================================================
        // CONDITIONAL RETURNS - Methods with multiple return points
        // ============================================================================
        
        System.out.println("\n--- Conditional Returns ---");
        
        System.out.println("Grade for 95: " + getLetterGrade(95));
        System.out.println("Grade for 82: " + getLetterGrade(82));
        System.out.println("Grade for 67: " + getLetterGrade(67));
        System.out.println("Grade for 45: " + getLetterGrade(45));
        
        System.out.println("Age category for 8: " + categorizeAge(8));
        System.out.println("Age category for 16: " + categorizeAge(16));
        System.out.println("Age category for 35: " + categorizeAge(35));
        System.out.println("Age category for 70: " + categorizeAge(70));
        
        // ============================================================================
        // RECURSIVE METHODS - Methods that call themselves
        // ============================================================================
        
        System.out.println("\n--- Recursive Methods ---");
        
        int factorialResult = factorial(5);
        System.out.println("5! = " + factorialResult);
        
        int fibonacciResult = fibonacci(8);
        System.out.println("8th Fibonacci number: " + fibonacciResult);
        
        int powerResult = power(2, 6);
        System.out.println("2^6 = " + powerResult);
        
        String binaryString = decimalToBinary(25);
        System.out.println("25 in binary: " + binaryString);
        
        // ============================================================================
        // VALIDATION METHODS - Methods that check data validity
        // ============================================================================
        
        System.out.println("\n--- Validation Methods ---");
        
        System.out.println("Is 'test@email.com' valid email: " + isValidEmail("test@email.com"));
        System.out.println("Is 'invalid-email' valid email: " + isValidEmail("invalid-email"));
        
        System.out.println("Is 25 valid age: " + isValidAge(25));
        System.out.println("Is -5 valid age: " + isValidAge(-5));
        System.out.println("Is 150 valid age: " + isValidAge(150));
        
        System.out.println("Is 'Password123' strong: " + isStrongPassword("Password123"));
        System.out.println("Is 'weak' strong: " + isStrongPassword("weak"));
        
        // ============================================================================
        // UTILITY METHODS - Helper methods for common tasks
        // ============================================================================
        
        System.out.println("\n--- Utility Methods ---");
        
        String randomPassword = generateRandomPassword(8);
        System.out.println("Random password: " + randomPassword);
        
        System.out.println("Capitalize 'hello world': " + capitalizeWords("hello world"));
        System.out.println("Count vowels in 'education': " + countVowels("education"));
        
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15};
        int searchResult = binarySearch(sortedArray, 7);
        System.out.println("Binary search for 7 in sorted array: index " + searchResult);
        
        // ============================================================================
        // METHOD CHAINING DEMONSTRATION
        // ============================================================================
        
        System.out.println("\n--- Method Chaining Example ---");
        
        // Using multiple methods together
        String originalText = "  Hello World  ";
        String processedText = capitalizeWords(trimString(originalText).toLowerCase());
        System.out.println("Original: '" + originalText + "'");
        System.out.println("Processed: '" + processedText + "'");
        
        // Complex calculation using multiple methods
        double radius = 5.0;
        double circumference = 2 * Math.PI * radius;
        double area2 = calculateArea(radius);
        double ratio = circumference / area2;
        System.out.printf("Circle (r=%.1f): circumference/area ratio = %.3f%n", radius, ratio);
        
        System.out.println("\n=== All method syntax demonstrations completed! ===");
    }
    
    // ============================================================================
    // VOID METHODS - Methods that perform actions but don't return values
    // ============================================================================
    
    /**
     * Simple void method with no parameters
     */
    public static void displayWelcomeMessage() {
        System.out.println("Welcome to the Methods Demonstration!");
    }
    
    /**
     * Void method with one parameter
     */
    public static void printSeparator(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    
    /**
     * Void method with parameter - displays personalized greeting
     */
    public static void greetUser(String name) {
        System.out.println("Hello, " + name + "! Nice to meet you.");
    }
    
    /**
     * Void method that displays a number squared
     */
    public static void displaySquare(int number) {
        int square = number * number;
        System.out.println(number + " squared is " + square);
    }
    
    // ============================================================================
    // METHODS WITH RETURN VALUES - Methods that calculate and return results
    // ============================================================================
    
    /**
     * Method returning int - adds two numbers
     */
    public static int addTwoNumbers(int a, int b) {
        int sum = a + b;
        return sum;
    }
    
    /**
     * Method returning double - calculates rectangle area
     */
    public static double calculateRectangleArea(double length, double width) {
        return length * width;
    }
    
    /**
     * Method returning boolean - checks if number is even
     */
    public static boolean checkIfEven(int number) {
        return number % 2 == 0;
    }
    
    /**
     * Method returning String - creates greeting message
     */
    public static String createGreeting(String name) {
        return "Greetings, " + name + "!";
    }
    
    /**
     * Method returning char - gets first character of string
     */
    public static char getFirstCharacter(String text) {
        if (text == null || text.length() == 0) {
            return '\0'; // null character
        }
        return text.charAt(0);
    }
    
    // ============================================================================
    // METHOD OVERLOADING - Multiple methods with same name, different parameters
    // ============================================================================
    
    /**
     * Calculate area - Circle version (1 parameter)
     */
    public static double calculateArea(double radius) {
        return Math.PI * radius * radius;
    }
    
    /**
     * Calculate area - Rectangle version (2 parameters)
     */
    public static double calculateArea(double length, double width) {
        return length * width;
    }
    
    /**
     * Calculate area - Triangle version (3 parameters)
     */
    public static double calculateArea(double base, double height, boolean isTriangle) {
        if (isTriangle) {
            return 0.5 * base * height;
        } else {
            return base * height; // Rectangle if not triangle
        }
    }
    
    /**
     * Print value - int version
     */
    public static void printValue(int value) {
        System.out.println("Integer value: " + value);
    }
    
    /**
     * Print value - double version
     */
    public static void printValue(double value) {
        System.out.println("Double value: " + value);
    }
    
    /**
     * Print value - String version
     */
    public static void printValue(String value) {
        System.out.println("String value: " + value);
    }
    
    /**
     * Print value - boolean version
     */
    public static void printValue(boolean value) {
        System.out.println("Boolean value: " + value);
    }
    
    // ============================================================================
    // METHODS WITH DIFFERENT PARAMETER PATTERNS
    // ============================================================================
    
    /**
     * Method with multiple primitive parameters
     */
    public static int findMaximum(int a, int b, int c) {
        int max = a;
        if (b > max) {
            max = b;
        }
        if (c > max) {
            max = c;
        }
        return max;
    }
    
    /**
     * Method calculating distance between two points
     */
    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
    
    /**
     * Method with array parameter
     */
    public static double calculateAverage(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0.0;
        }
        
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        
        return (double) sum / numbers.length;
    }
    
    /**
     * Method with array parameter - finds maximum value
     */
    public static int findMaxInArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        
        return max;
    }
    
    /**
     * Method with variable arguments (varargs) - sums any number of integers
     */
    public static int sumNumbers(int... numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
    
    /**
     * Method with varargs - combines any number of strings
     */
    public static String combineStrings(String... strings) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            result.append(strings[i]);
            if (i < strings.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }
    
    // ============================================================================
    // METHODS WITH DIFFERENT RETURN PATTERNS
    // ============================================================================
    
    /**
     * Simple calculation method
     */
    public static int multiplyByTwo(int number) {
        return number * 2;
    }
    
    /**
     * Temperature conversion method
     */
    public static double convertCelsiusToFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32.0;
    }
    
    /**
     * Boolean test method
     */
    public static boolean isPositive(int number) {
        return number > 0;
    }
    
    /**
     * String formatting method
     */
    public static String formatCurrency(double amount) {
        return String.format("$%.2f", amount);
    }
    
    /**
     * Method returning array
     */
    public static int[] generateEvenNumbers(int count) {
        int[] evenNumbers = new int[count];
        for (int i = 0; i < count; i++) {
            evenNumbers[i] = (i + 1) * 2;
        }
        return evenNumbers;
    }
    
    /**
     * Helper method to print array
     */
    public static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    // ============================================================================
    // METHODS WITH CONDITIONAL RETURNS - Multiple return points
    // ============================================================================
    
    /**
     * Method with multiple return statements based on conditions
     */
    public static String getLetterGrade(int score) {
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
    
    /**
     * Method with early returns for different age categories
     */
    public static String categorizeAge(int age) {
        if (age < 0) {
            return "Invalid age";
        }
        if (age < 13) {
            return "Child";
        }
        if (age < 20) {
            return "Teenager";
        }
        if (age < 65) {
            return "Adult";
        }
        return "Senior";
    }
    
    // ============================================================================
    // RECURSIVE METHODS - Methods that call themselves
    // ============================================================================
    
    /**
     * Recursive method - calculates factorial
     */
    public static int factorial(int n) {
        // Base case: stop recursion
        if (n <= 1) {
            return 1;
        }
        
        // Recursive case: n! = n * (n-1)!
        return n * factorial(n - 1);
    }
    
    /**
     * Recursive method - calculates Fibonacci number
     */
    public static int fibonacci(int n) {
        // Base cases
        if (n <= 1) {
            return n;
        }
        
        // Recursive case: F(n) = F(n-1) + F(n-2)
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    /**
     * Recursive method - calculates power (base^exponent)
     */
    public static int power(int base, int exponent) {
        // Base case
        if (exponent == 0) {
            return 1;
        }
        
        // Handle negative exponents
        if (exponent < 0) {
            return 0; // Simplified for integer math
        }
        
        // Recursive case
        return base * power(base, exponent - 1);
    }
    
    /**
     * Recursive method - converts decimal to binary
     */
    public static String decimalToBinary(int decimal) {
        // Base case
        if (decimal == 0) {
            return "0";
        }
        if (decimal == 1) {
            return "1";
        }
        
        // Recursive case
        return decimalToBinary(decimal / 2) + (decimal % 2);
    }
    
    // ============================================================================
    // VALIDATION METHODS - Methods that check data validity
    // ============================================================================
    
    /**
     * Validate email address (simplified)
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        return email.contains("@") && email.contains(".") && email.length() > 5;
    }
    
    /**
     * Validate age range
     */
    public static boolean isValidAge(int age) {
        return age >= 0 && age <= 120;
    }
    
    /**
     * Check password strength (simplified)
     */
    public static boolean isStrongPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isDigit(c)) hasDigit = true;
        }
        
        return hasUpper && hasLower && hasDigit;
    }
    
    // ============================================================================
    // UTILITY METHODS - Helper methods for common tasks
    // ============================================================================
    
    /**
     * Generate random password of specified length
     */
    public static String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            password.append(chars.charAt(index));
        }
        
        return password.toString();
    }
    
    /**
     * Capitalize first letter of each word
     */
    public static String capitalizeWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return text;
        }
        
        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                String capitalized = words[i].substring(0, 1).toUpperCase() + 
                                   words[i].substring(1).toLowerCase();
                result.append(capitalized);
            }
            
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        
        return result.toString();
    }
    
    /**
     * Count vowels in a string
     */
    public static int countVowels(String text) {
        if (text == null) {
            return 0;
        }
        
        int count = 0;
        String vowels = "aeiouAEIOU";
        
        for (char c : text.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * Reverse a string
     */
    public static String reverseString(String text) {
        if (text == null) {
            return null;
        }
        
        StringBuilder reversed = new StringBuilder();
        for (int i = text.length() - 1; i >= 0; i--) {
            reversed.append(text.charAt(i));
        }
        
        return reversed.toString();
    }
    
    /**
     * Trim whitespace from string
     */
    public static String trimString(String text) {
        if (text == null) {
            return null;
        }
        return text.trim();
    }
    
    /**
     * Binary search in sorted array
     */
    public static int binarySearch(int[] sortedArray, int target) {
        if (sortedArray == null) {
            return -1;
        }
        
        int left = 0;
        int right = sortedArray.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (sortedArray[mid] == target) {
                return mid;
            }
            
            if (sortedArray[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1; // Not found
    }
}

/*
 * METHOD SYNTAX SUMMARY:
 * 
 * METHOD DECLARATION:
 *   [access] [static] returnType methodName(parameterType parameterName) {
 *       // method body
 *       return value; // if not void
 *   }
 * 
 * ACCESS MODIFIERS:
 *   public    - accessible from anywhere
 *   private   - accessible only within same class
 *   protected - accessible within package and subclasses
 *   (default) - accessible within same package
 * 
 * RETURN TYPES:
 *   void      - returns nothing
 *   int       - returns integer value
 *   double    - returns decimal value
 *   boolean   - returns true/false
 *   String    - returns text value
 *   int[]     - returns array of integers
 *   etc.
 * 
 * PARAMETERS:
 *   ()                    - no parameters
 *   (int x)              - single parameter
 *   (int x, double y)    - multiple parameters
 *   (int[] array)        - array parameter
 *   (int... numbers)     - variable arguments (varargs)
 * 
 * METHOD OVERLOADING:
 *   - Same method name
 *   - Different parameter lists (number, type, or order)
 *   - Return type alone cannot differentiate overloaded methods
 * 
 * RECURSION:
 *   - Method calls itself
 *   - Must have base case to stop recursion
 *   - Each recursive call should progress toward base case
 * 
 * BEST PRACTICES:
 *   - Use descriptive method names
 *   - Keep methods focused on single responsibility
 *   - Validate parameters
 *   - Use appropriate return types
 *   - Document complex methods with comments
 */
