/**
 * _PracticalExample.java — DIP: Pluggable Analytics Pipeline
 *
 * A data analytics system where high-level processing logic depends
 * only on abstractions. Swap any component without touching existing code.
 *
 * Compile & Run: javac _PracticalExample.java && java _PracticalExample
 */
import java.util.*;
import java.util.stream.Collectors;

public class _PracticalExample {

    // ── Abstractions (owned by the high-level module) ──
    interface DataSource     { List<String> readData(); }
    interface DataFilter     { List<String> filter(List<String> data); }
    interface DataAggregator { Map<String, Long> aggregate(List<String> data); }
    interface DataSink       { void write(Map<String, Long> results); }

    // ── Low-level implementations ──
    static class InMemorySource implements DataSource {
        private List<String> data;
        InMemorySource(String... items) { this.data = Arrays.asList(items); }
        public List<String> readData() {
            System.out.println("  📥 InMemorySource reading " + data.size() + " records");
            return new ArrayList<>(data);
        }
    }

    static class FileSimulatedSource implements DataSource {
        public List<String> readData() {
            System.out.println("  📂 FileSource reading simulated CSV");
            return List.of("java","python","java","rust","python","java","go","rust","java");
        }
    }

    static class MinLengthFilter implements DataFilter {
        private int min;
        MinLengthFilter(int min) { this.min = min; }
        public List<String> filter(List<String> data) {
            return data.stream().filter(s -> s.length() >= min).collect(Collectors.toList());
        }
    }

    static class ExcludeFilter implements DataFilter {
        private Set<String> excluded;
        ExcludeFilter(String... words) { this.excluded = Set.of(words); }
        public List<String> filter(List<String> data) {
            return data.stream().filter(s -> !excluded.contains(s)).collect(Collectors.toList());
        }
    }

    static class FrequencyAggregator implements DataAggregator {
        public Map<String, Long> aggregate(List<String> data) {
            return data.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        }
    }

    static class ConsoleOutput implements DataSink {
        public void write(Map<String, Long> results) {
            System.out.println("  📊 Console Output:");
            results.entrySet().stream()
                .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                .forEach(e -> System.out.printf("     %-12s %d%n", e.getKey(), e.getValue()));
        }
    }

    static class AlertSink implements DataSink {
        private long threshold;
        AlertSink(long threshold) { this.threshold = threshold; }
        public void write(Map<String, Long> results) {
            System.out.println("  🚨 Alert Sink (threshold=" + threshold + "):");
            results.entrySet().stream()
                .filter(e -> e.getValue() >= threshold)
                .forEach(e -> System.out.println("     ALERT: " + e.getKey() + " = " + e.getValue()));
        }
    }

    // ── High-level: depends ONLY on abstractions, injected via constructor ──
    static class AnalyticsPipeline {
        private DataSource source;
        private DataFilter filter;
        private DataAggregator aggregator;
        private List<DataSink> sinks;

        AnalyticsPipeline(DataSource source, DataFilter filter,
                          DataAggregator aggregator, DataSink... sinks) {
            this.source = source; this.filter = filter;
            this.aggregator = aggregator; this.sinks = Arrays.asList(sinks);
        }

        public void run() {
            List<String> raw = source.readData();
            List<String> filtered = filter.filter(raw);
            Map<String, Long> results = aggregator.aggregate(filtered);
            sinks.forEach(sink -> sink.write(results));
        }
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   🔬 DIP ANALYTICS PIPELINE          ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        // Pipeline 1: In-memory source, no filter, console output
        System.out.println("── Pipeline 1: Language survey ──");
        new AnalyticsPipeline(
            new InMemorySource("java","python","java","rust","python","java","go","rust","java","kotlin","python"),
            new ExcludeFilter("go"),       // Swap in any filter
            new FrequencyAggregator(),
            new ConsoleOutput(),           // Multiple sinks
            new AlertSink(3)              // Also alert if count >= 3
        ).run();

        // Pipeline 2: Different source, different filter — SAME pipeline class
        System.out.println("\n── Pipeline 2: File source, min-length filter ──");
        new AnalyticsPipeline(
            new FileSimulatedSource(),
            new MinLengthFilter(4),
            new FrequencyAggregator(),
            new ConsoleOutput()
        ).run();

        System.out.println("\n✅ Swapped source, filter, and sinks — zero changes to AnalyticsPipeline!");
    }
}
