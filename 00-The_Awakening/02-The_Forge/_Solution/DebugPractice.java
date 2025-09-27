/**
 * Debug Practice - Learning the art of debugging
 * 
 * This program demonstrates:
 * - Using loops for repetitive tasks
 * - Performing calculations within loops
 * - Setting breakpoints and stepping through code
 * - Observing variable values during execution
 * 
 * DEBUGGING INSTRUCTIONS:
 * 1. Set a breakpoint on line 23 (the line that calculates the square)
 * 2. Run in debug mode (F5)
 * 3. When execution stops, observe the Variables panel
 * 4. Step through using F10 (Step Over)
 * 5. Watch how the variables change with each iteration
 */
public class DebugPractice {
    
    public static void main(String[] args) {
        System.out.println("=== Square Calculator ===");
        System.out.println("Number | Square");
        System.out.println("-------|-------");
        
        // Loop from 1 to 10
        for (int number = 1; number <= 10; number++) {
            // Calculate the square - SET BREAKPOINT HERE
            int square = number * number;
            
            // Display the result with formatting
            System.out.printf("  %2d   |  %3d%n", number, square);
            
            // Optional: Add a small delay to see the progression more clearly
            // (You can uncomment this if you want to see the output appear gradually)
            // try {
            //     Thread.sleep(500); // Pause for half a second
            // } catch (InterruptedException e) {
            //     // Handle interruption (not important for this exercise)
            // }
        }
        
        System.out.println("-------|-------");
        System.out.println("Debugging exercise complete!");
        
        // Additional debugging challenge: 
        // Try setting a breakpoint here and examining the final state
        int totalSquares = 0;
        for (int i = 1; i <= 10; i++) {
            totalSquares += i * i;  // Add each square to the total
        }
        
        System.out.println("Sum of all squares from 1 to 10: " + totalSquares);
        System.out.println("Expected result: 385 (verify this matches!)");
    }
}
