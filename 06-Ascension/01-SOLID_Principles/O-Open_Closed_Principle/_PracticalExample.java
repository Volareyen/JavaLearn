/**
 * _PracticalExample.java — OCP: Extensible Report Generator
 *
 * A report system where new report formats are added as new classes,
 * never by modifying the existing engine. Demonstrates OCP in practice.
 *
 * Compile & Run: javac _PracticalExample.java && java _PracticalExample
 */
import java.util.*;

public class _PracticalExample {

    // ── Data ──
    static class SalesData {
        String region; double revenue; int units;
        SalesData(String region, double revenue, int units) {
            this.region = region; this.revenue = revenue; this.units = units;
        }
    }

    // ── CLOSED for modification: this interface never changes ──
    interface ReportFormatter {
        String format(String title, List<SalesData> data);
        String name();
    }

    // ── OPEN for extension: new classes, never touching old ones ──
    static class PlainTextFormatter implements ReportFormatter {
        public String name() { return "Plain Text"; }
        public String format(String title, List<SalesData> data) {
            StringBuilder sb = new StringBuilder("=== " + title + " ===\n");
            data.forEach(d -> sb.append(String.format("%-15s $%10.2f  %4d units%n",
                d.region, d.revenue, d.units)));
            return sb.toString();
        }
    }

    static class CsvFormatter implements ReportFormatter {
        public String name() { return "CSV"; }
        public String format(String title, List<SalesData> data) {
            StringBuilder sb = new StringBuilder("Region,Revenue,Units\n");
            data.forEach(d -> sb.append(d.region + "," + d.revenue + "," + d.units + "\n"));
            return sb.toString();
        }
    }

    static class MarkdownFormatter implements ReportFormatter {
        public String name() { return "Markdown"; }
        public String format(String title, List<SalesData> data) {
            StringBuilder sb = new StringBuilder("# " + title + "\n\n");
            sb.append("| Region | Revenue | Units |\n|--------|---------|-------|\n");
            data.forEach(d -> sb.append(String.format("| %s | $%.2f | %d |%n",
                d.region, d.revenue, d.units)));
            return sb.toString();
        }
    }

    // ── Adding JSON? Zero existing code changes ──
    static class JsonFormatter implements ReportFormatter {
        public String name() { return "JSON"; }
        public String format(String title, List<SalesData> data) {
            StringBuilder sb = new StringBuilder("{\"title\":\"" + title + "\",\"rows\":[\n");
            for (int i = 0; i < data.size(); i++) {
                SalesData d = data.get(i);
                sb.append(String.format("  {\"region\":\"%s\",\"revenue\":%.2f,\"units\":%d}%s%n",
                    d.region, d.revenue, d.units, i < data.size()-1 ? "," : ""));
            }
            return sb.append("]}").toString();
        }
    }

    // ── Report Engine — NEVER changes when new formats are added ──
    static class ReportEngine {
        public void generate(String title, List<SalesData> data, ReportFormatter formatter) {
            System.out.println("── " + formatter.name() + " Report ──");
            System.out.println(formatter.format(title, data));
        }
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   📊 OCP REPORT GENERATOR            ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        List<SalesData> q1 = List.of(
            new SalesData("North", 142500.00, 285),
            new SalesData("South", 98750.50,  197),
            new SalesData("East",  211300.75, 422),
            new SalesData("West",  176200.25, 352)
        );

        ReportEngine engine = new ReportEngine();

        // Same engine, any formatter — OCP in action
        for (ReportFormatter fmt : List.of(
                new PlainTextFormatter(),
                new CsvFormatter(),
                new MarkdownFormatter(),
                new JsonFormatter())) {
            engine.generate("Q1 Sales Report", q1, fmt);
        }

        System.out.println("✅ Added JSON format — zero changes to ReportEngine!");
    }
}
