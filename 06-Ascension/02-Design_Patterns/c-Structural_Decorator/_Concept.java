/**
 * _Concept.java — Decorator Pattern
 *
 * Compile & Run: javac _Concept.java && java _Concept
 */
public class _Concept {

    // ── Component Interface ──
    interface Coffee {
        String description();
        double cost();
    }

    // ── Concrete Component ──
    static class SimpleCoffee implements Coffee {
        public String description() { return "Coffee"; }
        public double cost() { return 1.00; }
    }

    // ── Abstract Decorator — IS-A Coffee, HAS-A Coffee ──
    static abstract class CoffeeDecorator implements Coffee {
        protected Coffee wrapped;
        CoffeeDecorator(Coffee coffee) { this.wrapped = coffee; }
        public String description() { return wrapped.description(); }
        public double cost() { return wrapped.cost(); }
    }

    // ── Concrete Decorators ──
    static class Milk extends CoffeeDecorator {
        Milk(Coffee coffee) { super(coffee); }
        public String description() { return wrapped.description() + ", Milk"; }
        public double cost() { return wrapped.cost() + 0.25; }
    }

    static class Sugar extends CoffeeDecorator {
        Sugar(Coffee coffee) { super(coffee); }
        public String description() { return wrapped.description() + ", Sugar"; }
        public double cost() { return wrapped.cost() + 0.10; }
    }

    static class WhippedCream extends CoffeeDecorator {
        WhippedCream(Coffee coffee) { super(coffee); }
        public String description() { return wrapped.description() + ", Whipped Cream"; }
        public double cost() { return wrapped.cost() + 0.50; }
    }

    static class VanillaSyrup extends CoffeeDecorator {
        VanillaSyrup(Coffee coffee) { super(coffee); }
        public String description() { return wrapped.description() + ", Vanilla"; }
        public double cost() { return wrapped.cost() + 0.75; }
    }

    static void printOrder(Coffee coffee) {
        System.out.printf("  %-45s $%.2f%n", coffee.description(), coffee.cost());
    }

    public static void main(String[] args) {
        System.out.println("=== DECORATOR PATTERN ===\n");

        // Simple black coffee
        Coffee order1 = new SimpleCoffee();
        printOrder(order1);

        // Coffee + Milk
        Coffee order2 = new Milk(new SimpleCoffee());
        printOrder(order2);

        // Coffee + Milk + Sugar
        Coffee order3 = new Sugar(new Milk(new SimpleCoffee()));
        printOrder(order3);

        // Fancy stacked order
        Coffee order4 = new VanillaSyrup(
                            new WhippedCream(
                                new Sugar(
                                    new Milk(
                                        new SimpleCoffee()))));
        printOrder(order4);

        // Double milk (decorators can stack same type!)
        Coffee order5 = new Milk(new Milk(new SimpleCoffee()));
        printOrder(order5);

        System.out.println("\nAny combination, any order — no new subclasses needed!");
    }
}
