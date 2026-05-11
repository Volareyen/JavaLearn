/**
 * _Concept.java — LSP: Liskov Substitution Principle
 * 
 * Compile & Run: javac _Concept.java && java _Concept
 */
import java.util.*;

public class _Concept {

    // ✅ CORRECT — All birds implement Flyable only if they actually fly
    interface Bird { String name(); String sound(); }
    interface Flyable { void fly(); }

    static class Sparrow implements Bird, Flyable {
        public String name() { return "Sparrow"; }
        public String sound() { return "Chirp"; }
        public void fly() { System.out.println("  Sparrow soars through the sky"); }
    }

    static class Eagle implements Bird, Flyable {
        public String name() { return "Eagle"; }
        public String sound() { return "Screech"; }
        public void fly() { System.out.println("  Eagle glides majestically"); }
    }

    // Penguin is a Bird but NOT Flyable — no violation!
    static class Penguin implements Bird {
        public String name() { return "Penguin"; }
        public String sound() { return "Honk"; }
        public void swim() { System.out.println("  Penguin dives gracefully"); }
    }

    // This method works with ANY Bird — substitution works perfectly
    static void describeBird(Bird bird) {
        System.out.printf("  %s says '%s'%n", bird.name(), bird.sound());
    }

    // This method works with ANY Flyable — only birds that CAN fly
    static void makeFly(Flyable flyer) {
        flyer.fly();
    }

    public static void main(String[] args) {
        System.out.println("=== LISKOV SUBSTITUTION PRINCIPLE ===\n");

        List<Bird> allBirds = List.of(new Sparrow(), new Eagle(), new Penguin());
        System.out.println("All birds (substitution works):");
        allBirds.forEach(b -> describeBird(b));

        System.out.println("\nOnly flyable birds:");
        List<Flyable> flyers = List.of(new Sparrow(), new Eagle());
        flyers.forEach(f -> makeFly(f));

        System.out.println("\nPenguin swims instead:");
        new Penguin().swim();

        System.out.println("\nNo surprises — every type honors its contract!");
    }
}
