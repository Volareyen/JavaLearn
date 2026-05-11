/**
 * InvoiceSystem.java — SRP Solution: Invoice System
 *
 * Each class has ONE reason to change.
 * Run: javac InvoiceSystem.java && java InvoiceSystem
 */
import java.util.*;

public class InvoiceSystem {

    // ── Data ──
    static class LineItem {
        String name; double unitPrice; int qty;
        LineItem(String name, double unitPrice, int qty) {
            this.name = name; this.unitPrice = unitPrice; this.qty = qty;
        }
        double subtotal() { return unitPrice * qty; }
    }

    static class Invoice {
        String customer; String invoiceId; List<LineItem> items; double taxRate;
        Invoice(String customer, String id, double taxRate) {
            this.customer = customer; this.invoiceId = id;
            this.taxRate = taxRate; this.items = new ArrayList<>();
        }
        void addItem(LineItem item) { items.add(item); }
    }

    // SRP 1: Only knows how to calculate money
    static class InvoiceCalculator {
        double subtotal(Invoice inv) {
            return inv.items.stream().mapToDouble(LineItem::subtotal).sum();
        }
        double tax(Invoice inv) { return subtotal(inv) * inv.taxRate; }
        double total(Invoice inv) { return subtotal(inv) + tax(inv); }
    }

    // SRP 2: Only knows how to format/print
    static class InvoicePrinter {
        InvoiceCalculator calc;
        InvoicePrinter(InvoiceCalculator calc) { this.calc = calc; }

        void print(Invoice inv) {
            System.out.println("══════════════════════════════════");
            System.out.println("  INVOICE: " + inv.invoiceId);
            System.out.println("  Customer: " + inv.customer);
            System.out.println("──────────────────────────────────");
            inv.items.forEach(item ->
                System.out.printf("  %-15s %2d × $%6.2f = $%7.2f%n",
                    item.name, item.qty, item.unitPrice, item.subtotal()));
            System.out.println("──────────────────────────────────");
            System.out.printf("  Subtotal:  $%8.2f%n", calc.subtotal(inv));
            System.out.printf("  Tax:       $%8.2f%n", calc.tax(inv));
            System.out.printf("  TOTAL:     $%8.2f%n", calc.total(inv));
            System.out.println("══════════════════════════════════\n");
        }
    }

    // SRP 3: Only knows how to persist
    static class InvoiceRepository {
        private Map<String, Invoice> store = new LinkedHashMap<>();
        void save(Invoice inv) {
            store.put(inv.invoiceId, inv);
            System.out.println("  💾 Saved invoice: " + inv.invoiceId);
        }
        Invoice findById(String id) { return store.get(id); }
        int count() { return store.size(); }
    }

    // SRP 4: Only knows how to send notifications
    static class InvoiceNotifier {
        void sendConfirmation(Invoice inv) {
            System.out.println("  📧 Email sent to: " + inv.customer +
                " for invoice " + inv.invoiceId);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== SRP SOLUTION: INVOICE SYSTEM ===\n");

        Invoice inv = new Invoice("Alice Corp", "INV-2024-001", 0.08);
        inv.addItem(new LineItem("Java Book", 49.99, 3));
        inv.addItem(new LineItem("USB Hub", 29.99, 2));
        inv.addItem(new LineItem("Monitor Stand", 79.99, 1));

        InvoiceCalculator calc = new InvoiceCalculator();
        InvoicePrinter printer = new InvoicePrinter(calc);
        InvoiceRepository repo = new InvoiceRepository();
        InvoiceNotifier notifier = new InvoiceNotifier();

        printer.print(inv);
        repo.save(inv);
        notifier.sendConfirmation(inv);

        System.out.println("\n  Invoices in DB: " + repo.count());
        System.out.println("  SRP: 4 classes, each with one reason to change ✅");
    }
}
