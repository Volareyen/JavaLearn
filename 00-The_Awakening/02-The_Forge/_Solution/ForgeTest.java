/**
 * Forge Test - Demonstrating basic variable usage and string formatting
 * 
 * This program shows:
 * - Variable declaration and initialization
 * - Different data types (String and int)
 * - String concatenation and formatting
 * - VSCode's Java development features in action
 */
public class ForgeTest {
    
    public static void main(String[] args) {
        // Declare and initialize variables
        // Notice how VSCode provides syntax highlighting for these keywords
        String myName = "Seeker of Knowledge";  // Replace with your actual name
        int favoriteNumber = 42;                // The answer to life, universe, and everything
        
        // Display the information using string concatenation
        System.out.println("Greetings! My name is " + myName);
        System.out.println("My favorite number is " + favoriteNumber);
        
        // Alternative: Using formatted strings (more advanced, but useful to know)
        System.out.println(); // Empty line for spacing
        System.out.printf("Name: %s%n", myName);
        System.out.printf("Lucky Number: %d%n", favoriteNumber);
        
        // Demonstrate some basic calculations
        int doubled = favoriteNumber * 2;
        int squared = favoriteNumber * favoriteNumber;
        
        System.out.println(); // Another empty line
        System.out.println("Mathematical transformations of " + favoriteNumber + ":");
        System.out.println("  Doubled: " + doubled);
        System.out.println("  Squared: " + squared);
        
        // A message about the development environment
        System.out.println(); 
        System.out.println("This program was crafted in VSCode with Java extensions.");
        System.out.println("The forge is ready, and the journey begins!");
    }
}
