/**
 * _PracticalExample.java — SRP: Refactoring a Report Generator
 * 
 * Compile & Run: javac _PracticalExample.java && java _PracticalExample
 */
import java.util.*;

public class _PracticalExample {

    // ── Domain ──
    static class SalesRecord {
        String product; int quantity; double unitPrice;
        SalesRecord(String product, int qty, double price) {
            this.product = product; this.quantity = qty; this.unitPrice = price;
        }
        double getTotal() { return quantity * unitPrice; }
    }

    // ── SRP: Each class does ONE thing ──

    // 1. Data access
    static class SalesRepository {
        public List<SalesRecord> getAll() {
            return List.of(
                new SalesRecord("Laptop", 5, 1299.99),
                new SalesRecord("Mouse", 50, 29.99),
                new SalesRecord("Keyboard", 30, 79.99),
                new SalesRecord("Monitor", 10, 499.99)
            );
        }
    }

    // 2. Calculation logic
    static class SalesCalculator {
        public double totalRevenue(List<SalesRecord> records) {
            return records.stream().mapToDouble(SalesRecord::getTotal).sum();
        }
        public double averageOrderValue(List<SalesRecord> records) {
            return records.stream().mapToDouble(SalesRecord::getTotal).average().orElse(0);
        }
        public SalesRecord topProduct(List<SalesRecord> records) {
            return records.stream().max(Comparator.comparingDouble(SalesRecord::getTotal)).orElse(null);
        }
    }

    // 3. Formatting
    static class SalesReportFormatter {
        public String formatText(List<SalesRecord> records, SalesCalculator calc) {
            StringBuilder sb = new StringBuilder();
            sb.append("╔══════════════════════════════════════╗\n");
            sb.append("║       📊 SALES REPORT               ║\n");
            sb.append("╠══════════════════════════════════════╣\n");
            for (SalesRecord r : records) {
                sb.append(String.format("║ %-12s %3d × $%8.2f = $%10.2f%n",
                    r.product, r.quantity, r.unitPrice, r.getTotal()));
            }
            sb.append("╠══════════════════════════════════════╣\n");
            sb.append(String.format("║ Total Revenue: $%,.2f%n", calc.totalRevenue(records)));
            sb.append(String.format("║ Avg Order:     $%,.2f%n", calc.averageOrderValue(records)));
            SalesRecord top = calc.topProduct(records);
            if (top != null) sb.append(String.format("║ Top Product:   %s%n", top.product));
            sb.append("╚══════════════════════════════════════╝\n");
            return sb.toString();
        }
    }

    // 4. Orchestrator
    static class ReportService {
        private SalesRepository repo = new SalesRepository();
        private SalesCalculator calc = new SalesCalculator();
        private SalesReportFormatter formatter = new SalesReportFormatter();

        public String generateReport() {
            List<SalesRecord> data = repo.getAll();
            return formatter.formatText(data, calc);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== SRP PRACTICAL EXAMPLE ===\n");
        ReportService service = new ReportService();
        System.out.println(service.generateReport());
        System.out.println("Each component (data, calculation, formatting, orchestration)");
        System.out.println("can change independently without affecting the others.");
    }
}
