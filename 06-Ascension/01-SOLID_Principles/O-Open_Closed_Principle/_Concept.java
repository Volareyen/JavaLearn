/**
 * _Concept.java — OCP: Open/Closed Principle
 * 
 * Compile & Run: javac _Concept.java && java _Concept
 */
import java.util.*;

public class _Concept {

    // ✅ OPEN for extension via new implementations
    // ✅ CLOSED for modification — existing code never changes
    interface Discount {
        double apply(double price);
        String description();
    }

    static class NoDiscount implements Discount {
        public double apply(double price) { return price; }
        public String description() { return "No discount"; }
    }

    static class PercentageDiscount implements Discount {
        private double percent;
        PercentageDiscount(double percent) { this.percent = percent; }
        public double apply(double price) { return price * (1 - percent / 100); }
        public String description() { return percent + "% off"; }
    }

    static class FlatDiscount implements Discount {
        private double amount;
        FlatDiscount(double amount) { this.amount = amount; }
        public double apply(double price) { return Math.max(0, price - amount); }
        public String description() { return "$" + amount + " off"; }
    }

    // NEW discount type — NO existing code modified!
    static class BuyOneGetOneFree implements Discount {
        public double apply(double price) { return price / 2; }
        public String description() { return "Buy 1 Get 1 Free"; }
    }

    // This method NEVER changes, regardless of new discount types
    static void checkout(double price, Discount discount) {
        double final_price = discount.apply(price);
        System.out.printf("  $%.2f → %s → $%.2f%n", price, discount.description(), final_price);
    }

    public static void main(String[] args) {
        System.out.println("=== OPEN/CLOSED PRINCIPLE ===\n");
        double price = 100.0;
        List<Discount> discounts = List.of(
            new NoDiscount(),
            new PercentageDiscount(20),
            new FlatDiscount(15),
            new BuyOneGetOneFree()
        );
        discounts.forEach(d -> checkout(price, d));
        System.out.println("\nNew discount types added without modifying checkout()!");
    }
}
