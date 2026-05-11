/**
 * _PracticalExample.java — Factory Method: Document Exporter System
 *
 * A report exporter that produces PDF, CSV, or HTML from the same data,
 * using Factory Method to add new formats without modifying existing code.
 *
 * Compile & Run: javac _PracticalExample.java && java _PracticalExample
 */
import java.util.*;

public class _PracticalExample {

    // ── Data ──
    static class ReportData {
        String title;
        List<String[]> rows; // [product, qty, revenue]

        ReportData(String title) {
            this.title = title;
            this.rows = new ArrayList<>();
        }
        void addRow(String product, String qty, String revenue) {
            rows.add(new String[]{product, qty, revenue});
        }
    }

    // ── Product: Exporter interface ──
    interface Exporter {
        String export(ReportData data);
        String fileExtension();
    }

    // ── Concrete Products ──
    static class CsvExporter implements Exporter {
        public String fileExtension() { return ".csv"; }
        public String export(ReportData data) {
            StringBuilder sb = new StringBuilder();
            sb.append("Product,Quantity,Revenue\n");
            for (String[] row : data.rows) {
                sb.append(String.join(",", row)).append("\n");
            }
            return sb.toString();
        }
    }

    static class HtmlExporter implements Exporter {
        public String fileExtension() { return ".html"; }
        public String export(ReportData data) {
            StringBuilder sb = new StringBuilder();
            sb.append("<h1>").append(data.title).append("</h1>\n<table>\n");
            sb.append("  <tr><th>Product</th><th>Qty</th><th>Revenue</th></tr>\n");
            for (String[] row : data.rows) {
                sb.append("  <tr><td>").append(row[0]).append("</td><td>")
                  .append(row[1]).append("</td><td>").append(row[2])
                  .append("</td></tr>\n");
            }
            sb.append("</table>");
            return sb.toString();
        }
    }

    static class MarkdownExporter implements Exporter {
        public String fileExtension() { return ".md"; }
        public String export(ReportData data) {
            StringBuilder sb = new StringBuilder();
            sb.append("# ").append(data.title).append("\n\n");
            sb.append("| Product | Qty | Revenue |\n");
            sb.append("|---------|-----|----------|\n");
            for (String[] row : data.rows) {
                sb.append("| ").append(row[0]).append(" | ").append(row[1])
                  .append(" | ").append(row[2]).append(" |\n");
            }
            return sb.toString();
        }
    }

    // ── Abstract Creator ──
    static abstract class ReportGenerator {
        protected abstract Exporter createExporter(); // FACTORY METHOD

        public void generateReport(ReportData data) {
            Exporter exporter = createExporter();
            System.out.println("Generating " + exporter.fileExtension() + " report:");
            System.out.println("─────────────────────────────");
            System.out.println(exporter.export(data));
        }
    }

    // ── Concrete Creators ──
    static class CsvReportGenerator extends ReportGenerator {
        protected Exporter createExporter() { return new CsvExporter(); }
    }
    static class HtmlReportGenerator extends ReportGenerator {
        protected Exporter createExporter() { return new HtmlExporter(); }
    }
    static class MarkdownReportGenerator extends ReportGenerator {
        protected Exporter createExporter() { return new MarkdownExporter(); }
    }

    // ── Factory (runtime selection) ──
    static ReportGenerator getGenerator(String format) {
        return switch (format.toLowerCase()) {
            case "csv"  -> new CsvReportGenerator();
            case "html" -> new HtmlReportGenerator();
            case "md"   -> new MarkdownReportGenerator();
            default -> throw new IllegalArgumentException("Unknown format: " + format);
        };
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   📄 DOCUMENT EXPORTER SYSTEM       ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        // Build sample data once
        ReportData data = new ReportData("Q1 Sales Report");
        data.addRow("Laptop", "42", "$54,599.58");
        data.addRow("Mouse", "310", "$9,296.90");
        data.addRow("Keyboard", "180", "$14,398.20");

        // Export in multiple formats — same data, different factories
        for (String format : new String[]{"csv", "html", "md"}) {
            getGenerator(format).generateReport(data);
        }

        System.out.println("✅ Exporter demo complete — new formats need zero existing code changes!");
    }
}
