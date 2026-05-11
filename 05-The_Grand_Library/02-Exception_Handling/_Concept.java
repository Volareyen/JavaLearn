/**
 * _Concept.java — The Rune of Syntax: Exception Handling
 * 
 * Compile & Run:  javac _Concept.java && java _Concept
 */
import java.util.*;

public class _Concept {

    // ── Custom Checked Exception ──
    static class InsufficientFundsException extends Exception {
        private double deficit;
        public InsufficientFundsException(String msg, double deficit) {
            super(msg);
            this.deficit = deficit;
        }
        public double getDeficit() { return deficit; }
    }

    // ── Custom Unchecked Exception ──
    static class InvalidInputException extends RuntimeException {
        public InvalidInputException(String msg) { super(msg); }
    }

    public static void main(String[] args) {
        System.out.println("=== THE RUNE OF SYNTAX: EXCEPTIONS ===\n");

        // ── 1. BASIC TRY-CATCH ──
        System.out.println("--- 1. Basic try-catch ---");
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // ── 2. MULTIPLE CATCH BLOCKS ──
        System.out.println("\n--- 2. Multiple catch ---");
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General error: " + e.getMessage());
        }

        // ── 3. MULTI-CATCH (Java 7+) ──
        System.out.println("\n--- 3. Multi-catch ---");
        try {
            String s = null;
            s.length();
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Caught multi: " + e.getClass().getSimpleName());
        }

        // ── 4. FINALLY ──
        System.out.println("\n--- 4. Finally ---");
        try {
            System.out.println("  Try block");
            int x = 1 / 1; // no error
        } catch (Exception e) {
            System.out.println("  Catch block");
        } finally {
            System.out.println("  Finally block (always runs)");
        }

        // ── 5. THROW ──
        System.out.println("\n--- 5. Throw ---");
        try {
            validateAge(-5);
        } catch (InvalidInputException e) {
            System.out.println("Caught thrown: " + e.getMessage());
        }

        // ── 6. THROWS (checked) ──
        System.out.println("\n--- 6. Throws (checked) ---");
        try {
            withdraw(100, 50);
        } catch (InsufficientFundsException e) {
            System.out.printf("Caught checked: %s (deficit: $%.2f)%n",
                e.getMessage(), e.getDeficit());
        }

        // ── 7. CHAINED EXCEPTIONS ──
        System.out.println("\n--- 7. Chained Exceptions ---");
        try {
            try {
                throw new ArithmeticException("Original error");
            } catch (ArithmeticException e) {
                throw new RuntimeException("Wrapped error", e);
            }
        } catch (RuntimeException e) {
            System.out.println("Outer: " + e.getMessage());
            System.out.println("Cause: " + e.getCause().getMessage());
        }

        // ── 8. STACK TRACE ──
        System.out.println("\n--- 8. Stack trace info ---");
        try {
            methodA();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Stack depth: " + e.getStackTrace().length);
        }

        // ── 9. EXCEPTION IN CONDITIONALS ──
        System.out.println("\n--- 9. Safe parsing ---");
        String[] inputs = {"42", "hello", "99"};
        for (String input : inputs) {
            try {
                int val = Integer.parseInt(input);
                System.out.println("  Parsed: " + val);
            } catch (NumberFormatException e) {
                System.out.println("  Not a number: '" + input + "'");
            }
        }

        System.out.println("\n=== RUNE COMPLETE ===");
    }

    static void validateAge(int age) {
        if (age < 0) throw new InvalidInputException("Age cannot be negative: " + age);
    }

    static void withdraw(double amount, double balance) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(
                "Insufficient funds", amount - balance);
        }
    }

    static void methodA() { methodB(); }
    static void methodB() { methodC(); }
    static void methodC() { throw new RuntimeException("Deep error"); }
}
