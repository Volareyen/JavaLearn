/**
 * Control Flow - Loops - Pure Syntax Demonstration
 * 
 * This file demonstrates the raw syntax for all Java loop constructs:
 * for loops, enhanced for loops, while loops, do-while loops, and
 * loop control statements (break, continue).
 * 
 * Purpose: Complete reference guide for loop syntax
 */
public class _Concept {
    
    public static void main(String[] args) {
        
        // ============================================================================
        // BASIC FOR LOOP - Known number of iterations
        // ============================================================================
        
        System.out.println("=== BASIC FOR LOOPS ===");
        
        // Standard for loop: initialization; condition; increment
        for (int i = 0; i < 5; i++) {
            System.out.println("Basic for loop iteration: " + i);
        }
        
        // Counting upward
        for (int count = 1; count <= 3; count++) {
            System.out.println("Counting up: " + count);
        }
        
        // Counting downward
        for (int countdown = 3; countdown >= 1; countdown--) {
            System.out.println("Counting down: " + countdown);
        }
        
        // Stepping by different amounts
        for (int step = 0; step <= 10; step += 2) {
            System.out.println("Step by 2: " + step);
        }
        
        // Multiple variables in for loop
        for (int x = 0, y = 10; x <= 5; x++, y--) {
            System.out.println("x=" + x + ", y=" + y);
        }
        
        // Complex increment/decrement
        for (int i = 1; i <= 16; i *= 2) {
            System.out.println("Powers of 2: " + i);
        }
        
        // ============================================================================
        // ENHANCED FOR LOOP (for-each) - Iterating over collections/arrays
        // ============================================================================
        
        System.out.println("\n=== ENHANCED FOR LOOPS (for-each) ===");
        
        // Array iteration with enhanced for loop
        int[] numbers = {10, 20, 30, 40, 50};
        for (int number : numbers) {
            System.out.println("Array element: " + number);
        }
        
        // String array iteration
        String[] names = {"Alice", "Bob", "Charlie"};
        for (String name : names) {
            System.out.println("Name: " + name);
        }
        
        // Character array (from String)
        String text = "Hello";
        for (char ch : text.toCharArray()) {
            System.out.println("Character: " + ch);
        }
        
        // ============================================================================
        // WHILE LOOP - Condition-controlled repetition
        // ============================================================================
        
        System.out.println("\n=== WHILE LOOPS ===");
        
        // Basic while loop
        int counter = 0;
        while (counter < 3) {
            System.out.println("While loop iteration: " + counter);
            counter++; // Must modify counter to avoid infinite loop
        }
        
        // While loop with different condition
        int value = 1;
        while (value <= 8) {
            System.out.println("Value: " + value);
            value *= 2; // Double the value each iteration
        }
        
        // While loop with boolean flag
        boolean continueLoop = true;
        int flagCounter = 0;
        while (continueLoop) {
            System.out.println("Flag-controlled iteration: " + flagCounter);
            flagCounter++;
            if (flagCounter >= 3) {
                continueLoop = false; // Change flag to exit loop
            }
        }
        
        // While loop with complex condition
        int a = 1, b = 1;
        while (a + b <= 20) {
            System.out.println("Fibonacci-like: " + a);
            int temp = a + b;
            a = b;
            b = temp;
        }
        
        // Sentinel-controlled while loop
        int[] sentinelArray = {5, 3, 8, -1, 7, 2}; // -1 is sentinel
        int index = 0;
        while (index < sentinelArray.length && sentinelArray[index] != -1) {
            System.out.println("Processing: " + sentinelArray[index]);
            index++;
        }
        
        // ============================================================================
        // DO-WHILE LOOP - Execute at least once, then check condition
        // ============================================================================
        
        System.out.println("\n=== DO-WHILE LOOPS ===");
        
        // Basic do-while loop
        int doCounter = 0;
        do {
            System.out.println("Do-while iteration: " + doCounter);
            doCounter++;
        } while (doCounter < 3);
        
        // Do-while that executes only once (condition false from start)
        int falseCondition = 10;
        do {
            System.out.println("Executes once even though condition is false: " + falseCondition);
            falseCondition++;
        } while (falseCondition < 5); // This is false, but body executed once
        
        // Menu-like do-while pattern
        int choice = 0;
        int menuIteration = 0;
        do {
            menuIteration++;
            System.out.println("Menu iteration " + menuIteration);
            System.out.println("1. Option A");
            System.out.println("2. Option B");
            System.out.println("3. Exit");
            
            // Simulate user choice (in real program, would get input)
            choice = (menuIteration < 3) ? menuIteration : 3;
            
            switch (choice) {
                case 1:
                    System.out.println("Selected Option A");
                    break;
                case 2:
                    System.out.println("Selected Option B");
                    break;
                case 3:
                    System.out.println("Exiting menu");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            
        } while (choice != 3);
        
        // ============================================================================
        // NESTED LOOPS - Loops within loops
        // ============================================================================
        
        System.out.println("\n=== NESTED LOOPS ===");
        
        // Nested for loops - multiplication table
        System.out.println("3x3 Multiplication Table:");
        for (int row = 1; row <= 3; row++) {
            for (int col = 1; col <= 3; col++) {
                System.out.print((row * col) + "\t");
            }
            System.out.println(); // New line after each row
        }
        
        // Nested loops with different ranges
        System.out.println("Pattern:");
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        
        // Mixed loop types - for and while nested
        System.out.println("Mixed nested loops:");
        for (int outer = 1; outer <= 2; outer++) {
            System.out.println("Outer loop: " + outer);
            int inner = 1;
            while (inner <= 2) {
                System.out.println("  Inner loop: " + inner);
                inner++;
            }
        }
        
        // ============================================================================
        // LOOP CONTROL STATEMENTS - break and continue
        // ============================================================================
        
        System.out.println("\n=== LOOP CONTROL STATEMENTS ===");
        
        // BREAK statement - exit loop immediately
        System.out.println("Break example:");
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                System.out.println("Breaking at i = " + i);
                break; // Exit loop when i equals 5
            }
            System.out.println("i = " + i);
        }
        
        // CONTINUE statement - skip to next iteration
        System.out.println("Continue example (skip even numbers):");
        for (int i = 1; i <= 8; i++) {
            if (i % 2 == 0) {
                continue; // Skip even numbers
            }
            System.out.println("Odd number: " + i);
        }
        
        // Break in while loop
        System.out.println("Break in while loop:");
        int whileCounter = 0;
        while (true) { // Infinite loop
            System.out.println("While counter: " + whileCounter);
            whileCounter++;
            if (whileCounter >= 3) {
                break; // Exit the infinite loop
            }
        }
        
        // Continue in while loop
        System.out.println("Continue in while loop:");
        int continueCounter = 0;
        while (continueCounter < 6) {
            continueCounter++;
            if (continueCounter % 2 == 0) {
                continue; // Skip even iterations
            }
            System.out.println("Odd iteration: " + continueCounter);
        }
        
        // ============================================================================
        // LABELED BREAK AND CONTINUE (Advanced)
        // ============================================================================
        
        System.out.println("\n=== LABELED BREAK AND CONTINUE ===");
        
        // Labeled break - break out of outer loop
        System.out.println("Labeled break example:");
        outerLoop: for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i == 2 && j == 2) {
                    System.out.println("Breaking out of both loops at i=" + i + ", j=" + j);
                    break outerLoop; // Break out of outer loop
                }
                System.out.println("i=" + i + ", j=" + j);
            }
        }
        
        // Labeled continue - continue outer loop
        System.out.println("Labeled continue example:");
        outerContinue: for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (j == 2) {
                    System.out.println("Continuing outer loop at i=" + i + ", j=" + j);
                    continue outerContinue; // Continue outer loop
                }
                System.out.println("i=" + i + ", j=" + j);
            }
        }
        
        // ============================================================================
        // COMMON LOOP PATTERNS
        // ============================================================================
        
        System.out.println("\n=== COMMON LOOP PATTERNS ===");
        
        // Accumulation pattern
        int[] valuesToSum = {1, 2, 3, 4, 5};
        int sum = 0;
        for (int value : valuesToSum) {
            sum += value; // Accumulate sum
        }
        System.out.println("Sum of array: " + sum);
        
        // Finding maximum
        int[] scores = {85, 92, 78, 96, 88};
        int max = scores[0]; // Initialize with first element
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        System.out.println("Maximum score: " + max);
        
        // Counting pattern
        String sentence = "Hello World";
        int vowelCount = 0;
        for (char ch : sentence.toLowerCase().toCharArray()) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowelCount++;
            }
        }
        System.out.println("Vowels in '" + sentence + "': " + vowelCount);
        
        // Searching pattern
        int[] searchArray = {10, 25, 30, 45, 50};
        int target = 30;
        boolean found = false;
        int foundIndex = -1;
        
        for (int i = 0; i < searchArray.length; i++) {
            if (searchArray[i] == target) {
                found = true;
                foundIndex = i;
                break; // Exit once found
            }
        }
        
        if (found) {
            System.out.println("Found " + target + " at index " + foundIndex);
        } else {
            System.out.println(target + " not found");
        }
        
        // Filtering pattern
        int[] mixedNumbers = {1, -2, 3, -4, 5, -6, 7};
        System.out.print("Positive numbers: ");
        for (int num : mixedNumbers) {
            if (num > 0) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
        
        // Transformation pattern
        String[] lowercaseWords = {"hello", "world", "java"};
        System.out.print("Capitalized: ");
        for (String word : lowercaseWords) {
            String capitalized = word.substring(0, 1).toUpperCase() + word.substring(1);
            System.out.print(capitalized + " ");
        }
        System.out.println();
        
        // ============================================================================
        // INFINITE LOOP EXAMPLES (Commented out to prevent actual infinite loops)
        // ============================================================================
        
        System.out.println("\n=== INFINITE LOOP SYNTAX (Examples) ===");
        
        // Intentional infinite loop with break condition
        int infiniteCounter = 0;
        while (true) {
            System.out.println("Infinite loop iteration: " + infiniteCounter);
            infiniteCounter++;
            if (infiniteCounter >= 3) {
                System.out.println("Breaking out of infinite loop");
                break;
            }
        }
        
        // For-based infinite loop
        for (;;) { // Empty initialization, condition, and increment = infinite
            System.out.println("Infinite for loop (breaks immediately)");
            break; // Immediate break to prevent actual infinite execution
        }
        
        /*
        // Examples of ACCIDENTAL infinite loops (DO NOT RUN):
        
        // Forgot to increment counter
        int i = 0;
        while (i < 10) {
            System.out.println(i);
            // i++; // MISSING - causes infinite loop
        }
        
        // Wrong condition
        for (int j = 0; j >= 0; j++) { // j will never be < 0
            System.out.println(j);
        }
        
        // Modifying loop variable incorrectly
        for (int k = 0; k < 10; k++) {
            System.out.println(k);
            k--; // Cancels out the k++ increment
        }
        */
        
        System.out.println("\nAll loop syntax demonstrations completed!");
    }
}

/*
 * LOOP SYNTAX SUMMARY:
 * 
 * FOR LOOP:
 *   for (initialization; condition; increment/decrement) { statements; }
 *   for (int i = 0; i < 10; i++) { ... }
 * 
 * ENHANCED FOR LOOP:
 *   for (dataType variable : array/collection) { statements; }
 *   for (int num : numbers) { ... }
 * 
 * WHILE LOOP:
 *   while (condition) { statements; }
 *   while (i < 10) { ... i++; }
 * 
 * DO-WHILE LOOP:
 *   do { statements; } while (condition);
 *   do { ... i++; } while (i < 10);
 * 
 * LOOP CONTROL:
 *   break;           // Exit loop immediately
 *   continue;        // Skip to next iteration
 *   break label;     // Break out of labeled loop
 *   continue label;  // Continue labeled loop
 * 
 * NESTED LOOPS:
 *   for (...) {
 *       for (...) {
 *           // Inner loop body
 *       }
 *   }
 * 
 * KEY POINTS:
 * - For loops: Use when you know the number of iterations
 * - Enhanced for: Use when iterating over all elements
 * - While loops: Use when condition determines continuation
 * - Do-while: Use when you need at least one execution
 * - Always ensure loop variables are modified to avoid infinite loops
 * - Use break and continue judiciously for flow control
 * - Be careful with nested loops and their complexity
 */
