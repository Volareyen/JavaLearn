/**
 * _Concept.java — ISP: Interface Segregation Principle
 * 
 * Compile & Run: javac _Concept.java && java _Concept
 */
import java.util.*;

public class _Concept {

    // ✅ Segregated interfaces — each defines a focused capability
    interface Printable { void print(); }
    interface Scannable { void scan(); }
    interface Faxable { void fax(); }
    interface Stapler { void staple(); }

    // Full office machine implements everything it needs
    static class OfficePrinter implements Printable, Scannable, Faxable {
        public void print() { System.out.println("  🖨️ OfficePrinter printing..."); }
        public void scan() { System.out.println("  📷 OfficePrinter scanning..."); }
        public void fax() { System.out.println("  📠 OfficePrinter faxing..."); }
    }

    // Simple printer only implements Printable — no useless methods!
    static class SimplePrinter implements Printable {
        public void print() { System.out.println("  🖨️ SimplePrinter printing..."); }
    }

    // Scanner only implements Scannable
    static class PortableScanner implements Scannable {
        public void scan() { System.out.println("  📷 PortableScanner scanning..."); }
    }

    // Methods accept ONLY the interface they need
    static void printDocument(Printable printer) { printer.print(); }
    static void scanDocument(Scannable scanner) { scanner.scan(); }

    public static void main(String[] args) {
        System.out.println("=== INTERFACE SEGREGATION PRINCIPLE ===\n");

        OfficePrinter office = new OfficePrinter();
        SimplePrinter simple = new SimplePrinter();
        PortableScanner scanner = new PortableScanner();

        System.out.println("Printing (any Printable works):");
        printDocument(office);
        printDocument(simple);

        System.out.println("\nScanning (any Scannable works):");
        scanDocument(office);
        scanDocument(scanner);

        System.out.println("\nEach class implements ONLY what it needs!");
    }
}
