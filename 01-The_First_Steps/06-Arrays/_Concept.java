/**
 * Arrays - Pure Syntax Demonstration
 * 
 * This file demonstrates the raw syntax for all Java array concepts:
 * array declarations, creation, initialization, access, iteration,
 * multi-dimensional arrays, and common array operations.
 * 
 * Purpose: Complete reference guide for array syntax
 */

import java.util.Arrays;

public class _Concept {
    
    public static void main(String[] args) {
        
        System.out.println("=== ARRAY SYNTAX DEMONSTRATIONS ===");
        
        // ============================================================================
        // ARRAY DECLARATION AND CREATION - Basic syntax patterns
        // ============================================================================
        
        System.out.println("\n--- Array Declaration and Creation ---");
        
        // Array declaration (preferred style)
        int[] numbers;
        String[] names;
        double[] prices;
        boolean[] flags;
        char[] letters;
        
        // Alternative declaration style (C-style, less preferred)
        int alternativeNumbers[];
        String alternativeNames[];
        
        // Array creation with specific size (elements get default values)
        numbers = new int[5];           // Creates array of 5 integers (all 0)
        names = new String[3];          // Creates array of 3 strings (all null)
        prices = new double[4];         // Creates array of 4 doubles (all 0.0)
        flags = new boolean[2];         // Creates array of 2 booleans (all false)
        letters = new char[6];          // Creates array of 6 characters (all '\0')
        
        System.out.println("Created arrays with default values:");
        System.out.println("int array: " + Arrays.toString(numbers));
        System.out.println("String array: " + Arrays.toString(names));
        System.out.println("double array: " + Arrays.toString(prices));
        System.out.println("boolean array: " + Arrays.toString(flags));
        System.out.println("char array: " + Arrays.toString(letters));
        
        // ============================================================================
        // ARRAY INITIALIZATION - Different ways to initialize arrays
        // ============================================================================
        
        System.out.println("\n--- Array Initialization Patterns ---");
        
        // Pattern 1: Declare and initialize with values (array literal)
        int[] scores = {85, 92, 78, 96, 88};
        String[] colors = {"red", "green", "blue", "yellow"};
        double[] temperatures = {98.6, 99.1, 97.8, 100.2};
        boolean[] results = {true, false, true, true, false};
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        
        System.out.println("Initialized arrays:");
        System.out.println("Scores: " + Arrays.toString(scores));
        System.out.println("Colors: " + Arrays.toString(colors));
        System.out.println("Temperatures: " + Arrays.toString(temperatures));
        System.out.println("Results: " + Arrays.toString(results));
        System.out.println("Vowels: " + Arrays.toString(vowels));
        
        // Pattern 2: Declare and create with new keyword and values
        int[] moreNumbers = new int[]{10, 20, 30, 40, 50};
        String[] cities = new String[]{"New York", "London", "Tokyo", "Paris"};
        
        System.out.println("Arrays with new keyword:");
        System.out.println("More numbers: " + Arrays.toString(moreNumbers));
        System.out.println("Cities: " + Arrays.toString(cities));
        
        // Pattern 3: Create first, then assign values
        int[] values = new int[3];
        values[0] = 100;
        values[1] = 200;
        values[2] = 300;
        
        System.out.println("Array assigned after creation: " + Arrays.toString(values));
        
        // ============================================================================
        // ARRAY ACCESS AND MODIFICATION - Reading and writing elements
        // ============================================================================
        
        System.out.println("\n--- Array Access and Modification ---");
        
        int[] testArray = {10, 20, 30, 40, 50};
        
        // Reading array elements (zero-based indexing)
        int firstElement = testArray[0];        // Gets 10
        int thirdElement = testArray[2];        // Gets 30
        int lastElement = testArray[testArray.length - 1];  // Gets 50
        
        System.out.println("Reading elements:");
        System.out.println("First element (index 0): " + firstElement);
        System.out.println("Third element (index 2): " + thirdElement);
        System.out.println("Last element: " + lastElement);
        
        // Modifying array elements
        testArray[0] = 999;     // Change first element
        testArray[2] = 888;     // Change third element
        testArray[4] = 777;     // Change last element
        
        System.out.println("After modifications: " + Arrays.toString(testArray));
        
        // Array length property
        System.out.println("Array length: " + testArray.length);
        
        // ============================================================================
        // ARRAY ITERATION - Different ways to loop through arrays
        // ============================================================================
        
        System.out.println("\n--- Array Iteration Methods ---");
        
        int[] iterationArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        // Method 1: Traditional for loop (when you need the index)
        System.out.println("Traditional for loop:");
        for (int i = 0; i < iterationArray.length; i++) {
            System.out.print("Index " + i + ": " + iterationArray[i] + " ");
        }
        System.out.println();
        
        // Method 2: Enhanced for loop (for-each) - when you just need values
        System.out.println("Enhanced for loop (for-each):");
        for (int value : iterationArray) {
            System.out.print(value + " ");
        }
        System.out.println();
        
        // Method 3: While loop iteration
        System.out.println("While loop iteration:");
        int index = 0;
        while (index < iterationArray.length) {
            System.out.print(iterationArray[index] + " ");
            index++;
        }
        System.out.println();
        
        // Method 4: Reverse iteration
        System.out.println("Reverse iteration:");
        for (int i = iterationArray.length - 1; i >= 0; i--) {
            System.out.print(iterationArray[i] + " ");
        }
        System.out.println();
        
        // ============================================================================
        // COMMON ARRAY OPERATIONS - Searching, finding, counting
        // ============================================================================
        
        System.out.println("\n--- Common Array Operations ---");
        
        int[] operationsArray = {15, 23, 8, 42, 16, 4, 23, 35, 23};
        
        // Find maximum element
        int max = operationsArray[0];
        for (int i = 1; i < operationsArray.length; i++) {
            if (operationsArray[i] > max) {
                max = operationsArray[i];
            }
        }
        System.out.println("Maximum element: " + max);
        
        // Find minimum element
        int min = operationsArray[0];
        for (int i = 1; i < operationsArray.length; i++) {
            if (operationsArray[i] < min) {
                min = operationsArray[i];
            }
        }
        System.out.println("Minimum element: " + min);
        
        // Calculate sum
        int sum = 0;
        for (int value : operationsArray) {
            sum += value;
        }
        System.out.println("Sum of all elements: " + sum);
        
        // Calculate average
        double average = (double) sum / operationsArray.length;
        System.out.println("Average: " + average);
        
        // Linear search for a specific value
        int searchValue = 23;
        int foundIndex = -1;
        for (int i = 0; i < operationsArray.length; i++) {
            if (operationsArray[i] == searchValue) {
                foundIndex = i;
                break;  // Find first occurrence
            }
        }
        
        if (foundIndex != -1) {
            System.out.println("Found " + searchValue + " at index " + foundIndex);
        } else {
            System.out.println(searchValue + " not found in array");
        }
        
        // Count occurrences of a value
        int countValue = 23;
        int count = 0;
        for (int value : operationsArray) {
            if (value == countValue) {
                count++;
            }
        }
        System.out.println("Value " + countValue + " appears " + count + " times");
        
        // Check if array contains a value
        boolean contains = false;
        int checkValue = 42;
        for (int value : operationsArray) {
            if (value == checkValue) {
                contains = true;
                break;
            }
        }
        System.out.println("Array contains " + checkValue + ": " + contains);
        
        // ============================================================================
        // ARRAY COPYING - Different methods to copy arrays
        // ============================================================================
        
        System.out.println("\n--- Array Copying Methods ---");
        
        int[] original = {1, 2, 3, 4, 5};
        System.out.println("Original array: " + Arrays.toString(original));
        
        // Method 1: Manual copying with loop
        int[] manualCopy = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            manualCopy[i] = original[i];
        }
        System.out.println("Manual copy: " + Arrays.toString(manualCopy));
        
        // Method 2: Using System.arraycopy()
        int[] systemCopy = new int[original.length];
        System.arraycopy(original, 0, systemCopy, 0, original.length);
        System.out.println("System.arraycopy: " + Arrays.toString(systemCopy));
        
        // Method 3: Using Arrays.copyOf()
        int[] arraysCopy = Arrays.copyOf(original, original.length);
        System.out.println("Arrays.copyOf: " + Arrays.toString(arraysCopy));
        
        // Method 4: Using Arrays.copyOfRange() for partial copying
        int[] rangeCopy = Arrays.copyOfRange(original, 1, 4);  // Copies indices 1, 2, 3
        System.out.println("Arrays.copyOfRange(1,4): " + Arrays.toString(rangeCopy));
        
        // Demonstrate that arrays are reference types
        int[] reference = original;  // This is NOT a copy!
        reference[0] = 999;
        System.out.println("After modifying reference: " + Arrays.toString(original));
        System.out.println("Original was affected because reference points to same array");
        
        // ============================================================================
        // ARRAY COMPARISON AND UTILITIES - Using Arrays class methods
        // ============================================================================
        
        System.out.println("\n--- Array Comparison and Utilities ---");
        
        int[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = {1, 2, 3, 4, 5};
        int[] array3 = {5, 4, 3, 2, 1};
        
        // Comparing arrays for equality (content comparison)
        boolean equal1 = Arrays.equals(array1, array2);
        boolean equal2 = Arrays.equals(array1, array3);
        
        System.out.println("array1 equals array2: " + equal1);  // true
        System.out.println("array1 equals array3: " + equal2);  // false
        
        // Wrong way to compare arrays (compares references, not content)
        boolean wrongComparison = (array1 == array2);
        System.out.println("array1 == array2 (wrong way): " + wrongComparison);  // false
        
        // Sorting arrays
        int[] unsortedArray = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Before sorting: " + Arrays.toString(unsortedArray));
        
        Arrays.sort(unsortedArray);
        System.out.println("After sorting: " + Arrays.toString(unsortedArray));
        
        // Binary search (only works on sorted arrays)
        int searchTarget = 25;
        int binarySearchResult = Arrays.binarySearch(unsortedArray, searchTarget);
        System.out.println("Binary search for " + searchTarget + " found at index: " + binarySearchResult);
        
        // Fill array with specific value
        int[] fillArray = new int[5];
        Arrays.fill(fillArray, 42);
        System.out.println("Array filled with 42: " + Arrays.toString(fillArray));
        
        // Fill part of array with specific value
        int[] partialFillArray = new int[8];
        Arrays.fill(partialFillArray, 2, 6, 99);  // Fill indices 2-5 with 99
        System.out.println("Partially filled array: " + Arrays.toString(partialFillArray));
        
        // ============================================================================
        // MULTI-DIMENSIONAL ARRAYS - 2D arrays and beyond
        // ============================================================================
        
        System.out.println("\n--- Multi-Dimensional Arrays ---");
        
        // 2D array declaration and creation
        int[][] matrix2D = new int[3][4];  // 3 rows, 4 columns
        
        // 2D array initialization with values
        int[][] grid = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        
        System.out.println("2D Array (matrix):");
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                System.out.printf("%3d ", grid[row][col]);
            }
            System.out.println();
        }
        
        // Enhanced for loop with 2D arrays
        System.out.println("2D Array using enhanced for loop:");
        for (int[] row : grid) {
            for (int element : row) {
                System.out.printf("%3d ", element);
            }
            System.out.println();
        }
        
        // Accessing individual elements in 2D array
        int element = grid[1][2];  // Row 1, Column 2 (value is 7)
        System.out.println("Element at [1][2]: " + element);
        
        // Modifying 2D array elements
        grid[0][0] = 99;
        System.out.println("After modifying [0][0] to 99:");
        System.out.println("First row: " + Arrays.toString(grid[0]));
        
        // Jagged arrays (arrays with rows of different lengths)
        int[][] jaggedArray = {
            {1, 2},
            {3, 4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Jagged Array:");
        for (int i = 0; i < jaggedArray.length; i++) {
            System.out.println("Row " + i + " (length " + jaggedArray[i].length + "): " + 
                             Arrays.toString(jaggedArray[i]));
        }
        
        // 3D array example
        int[][][] cube = new int[2][3][4];  // 2 layers, 3 rows, 4 columns
        cube[0][1][2] = 42;  // Set element at layer 0, row 1, column 2
        
        System.out.println("3D Array element [0][1][2]: " + cube[0][1][2]);
        System.out.println("3D Array dimensions: " + cube.length + "x" + 
                         cube[0].length + "x" + cube[0][0].length);
        
        // ============================================================================
        // STRING ARRAYS - Special considerations for String arrays
        // ============================================================================
        
        System.out.println("\n--- String Arrays ---");
        
        String[] stringArray = {"apple", "banana", "cherry", "date"};
        
        // String array iteration
        System.out.println("String array elements:");
        for (int i = 0; i < stringArray.length; i++) {
            System.out.println("Index " + i + ": " + stringArray[i]);
        }
        
        // String comparison in arrays (use .equals(), not ==)
        String searchString = "banana";
        boolean foundString = false;
        for (String str : stringArray) {
            if (str.equals(searchString)) {  // Correct way to compare strings
                foundString = true;
                break;
            }
        }
        System.out.println("Found '" + searchString + "': " + foundString);
        
        // String array with null values
        String[] stringArrayWithNulls = new String[3];
        stringArrayWithNulls[0] = "Hello";
        stringArrayWithNulls[2] = "World";
        // stringArrayWithNulls[1] remains null
        
        System.out.println("String array with nulls: " + Arrays.toString(stringArrayWithNulls));
        
        // Safe iteration with null checking
        System.out.println("Safe iteration with null checking:");
        for (String str : stringArrayWithNulls) {
            if (str != null) {
                System.out.println("Non-null string: " + str);
            } else {
                System.out.println("Found null value");
            }
        }
        
        // ============================================================================
        // ARRAY ALGORITHMS - Common sorting and searching algorithms
        // ============================================================================
        
        System.out.println("\n--- Array Algorithms ---");
        
        // Bubble Sort implementation
        int[] bubbleSortArray = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Before Bubble Sort: " + Arrays.toString(bubbleSortArray));
        
        // Bubble sort algorithm
        for (int i = 0; i < bubbleSortArray.length - 1; i++) {
            for (int j = 0; j < bubbleSortArray.length - 1 - i; j++) {
                if (bubbleSortArray[j] > bubbleSortArray[j + 1]) {
                    // Swap elements
                    int temp = bubbleSortArray[j];
                    bubbleSortArray[j] = bubbleSortArray[j + 1];
                    bubbleSortArray[j + 1] = temp;
                }
            }
        }
        System.out.println("After Bubble Sort: " + Arrays.toString(bubbleSortArray));
        
        // Selection Sort implementation
        int[] selectionSortArray = {64, 25, 12, 22, 11};
        System.out.println("Before Selection Sort: " + Arrays.toString(selectionSortArray));
        
        // Selection sort algorithm
        for (int i = 0; i < selectionSortArray.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < selectionSortArray.length; j++) {
                if (selectionSortArray[j] < selectionSortArray[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            int temp = selectionSortArray[minIndex];
            selectionSortArray[minIndex] = selectionSortArray[i];
            selectionSortArray[i] = temp;
        }
        System.out.println("After Selection Sort: " + Arrays.toString(selectionSortArray));
        
        // Linear Search implementation
        int[] searchArray = {10, 23, 45, 70, 11, 15};
        int linearSearchTarget = 70;
        int linearSearchIndex = -1;
        
        for (int i = 0; i < searchArray.length; i++) {
            if (searchArray[i] == linearSearchTarget) {
                linearSearchIndex = i;
                break;
            }
        }
        
        System.out.println("Linear search for " + linearSearchTarget + 
                         ": found at index " + linearSearchIndex);
        
        // ============================================================================
        // ARRAY EDGE CASES AND ERROR HANDLING
        // ============================================================================
        
        System.out.println("\n--- Array Edge Cases and Error Handling ---");
        
        // Empty array
        int[] emptyArray = new int[0];
        System.out.println("Empty array length: " + emptyArray.length);
        System.out.println("Empty array: " + Arrays.toString(emptyArray));
        
        // Array with one element
        int[] singleElementArray = {42};
        System.out.println("Single element array: " + Arrays.toString(singleElementArray));
        
        // Demonstrating ArrayIndexOutOfBoundsException (commented out to prevent crash)
        /*
        try {
            int[] testArray = {1, 2, 3};
            int invalidAccess = testArray[5];  // This would throw exception
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
        */
        
        // Safe array access with bounds checking
        int[] safeArray = {10, 20, 30};
        int indexToCheck = 5;
        
        if (indexToCheck >= 0 && indexToCheck < safeArray.length) {
            System.out.println("Safe access: " + safeArray[indexToCheck]);
        } else {
            System.out.println("Index " + indexToCheck + " is out of bounds for array of length " + 
                             safeArray.length);
        }
        
        // Null array handling
        int[] nullArray = null;
        if (nullArray != null) {
            System.out.println("Null array length: " + nullArray.length);
        } else {
            System.out.println("Array is null - cannot access length or elements");
        }
        
        // ============================================================================
        // VARIABLE ARGUMENTS (VARARGS) - Arrays in method parameters
        // ============================================================================
        
        System.out.println("\n--- Variable Arguments (Varargs) ---");
        
        // Demonstrate varargs methods (implemented below)
        int sum1 = sumNumbers(1, 2, 3);
        int sum2 = sumNumbers(10, 20, 30, 40, 50);
        int sum3 = sumNumbers();  // No arguments
        
        System.out.println("Sum of (1,2,3): " + sum1);
        System.out.println("Sum of (10,20,30,40,50): " + sum2);
        System.out.println("Sum of no arguments: " + sum3);
        
        // Varargs with array
        int[] numbersForVarargs = {5, 10, 15, 20};
        int sum4 = sumNumbers(numbersForVarargs);
        System.out.println("Sum of array via varargs: " + sum4);
        
        System.out.println("\n=== All array syntax demonstrations completed! ===");
    }
    
    // ============================================================================
    // VARARGS METHOD EXAMPLES - Methods that accept variable number of arguments
    // ============================================================================
    
    /**
     * Method with variable arguments (varargs)
     * Can accept any number of int arguments
     */
    public static int sumNumbers(int... numbers) {
        int total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total;
    }
    
    /**
     * Varargs method with additional parameters
     * The varargs parameter must be the last parameter
     */
    public static void printWithPrefix(String prefix, String... messages) {
        for (String message : messages) {
            System.out.println(prefix + message);
        }
    }
    
    /**
     * Method demonstrating array as parameter
     */
    public static void printArray(int[] array) {
        System.out.print("Array contents: ");
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    
    /**
     * Method returning an array
     */
    public static int[] createSequence(int start, int count) {
        int[] sequence = new int[count];
        for (int i = 0; i < count; i++) {
            sequence[i] = start + i;
        }
        return sequence;
    }
    
    /**
     * Method demonstrating 2D array parameter
     */
    public static void print2DArray(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.printf("%4d", element);
            }
            System.out.println();
        }
    }
}

/*
 * ARRAY SYNTAX SUMMARY:
 * 
 * ARRAY DECLARATION:
 *   dataType[] arrayName;           // Preferred style
 *   dataType arrayName[];           // Alternative style
 * 
 * ARRAY CREATION:
 *   arrayName = new dataType[size]; // Create with specific size
 *   arrayName = {val1, val2, ...};  // Create with initial values
 *   arrayName = new dataType[]{val1, val2, ...}; // Alternative initialization
 * 
 * ARRAY ACCESS:
 *   arrayName[index]                // Read element at index
 *   arrayName[index] = value;       // Write value to index
 *   arrayName.length                // Get array length
 * 
 * ARRAY ITERATION:
 *   for (int i = 0; i < array.length; i++) { ... }     // Traditional for loop
 *   for (dataType element : array) { ... }             // Enhanced for loop
 * 
 * MULTI-DIMENSIONAL ARRAYS:
 *   dataType[][] array2D = new dataType[rows][cols];   // 2D array creation
 *   dataType[][] array2D = {{...}, {...}, ...};       // 2D array initialization
 *   array2D[row][col]                                  // 2D array access
 * 
 * COMMON OPERATIONS:
 *   Arrays.toString(array)          // Convert to string representation
 *   Arrays.equals(array1, array2)   // Compare array contents
 *   Arrays.sort(array)              // Sort array in ascending order
 *   Arrays.binarySearch(array, key) // Binary search (requires sorted array)
 *   Arrays.fill(array, value)       // Fill array with specific value
 *   Arrays.copyOf(array, length)    // Create copy of array
 * 
 * VARARGS:
 *   public static void method(dataType... paramName) { ... }
 *   // Can be called with any number of arguments of specified type
 * 
 * KEY POINTS:
 * - Arrays are zero-indexed (first element is at index 0)
 * - Array size is fixed once created
 * - Arrays are reference types (assignment copies reference, not contents)
 * - Always check bounds to avoid ArrayIndexOutOfBoundsException
 * - Use Arrays.equals() to compare contents, not == operator
 * - Enhanced for loops are cleaner when you don't need the index
 * - Multi-dimensional arrays are arrays of arrays
 * - Varargs parameters must be the last parameter in method signature
 */
