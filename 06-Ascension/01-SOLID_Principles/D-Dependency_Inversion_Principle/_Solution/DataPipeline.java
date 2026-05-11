/**
 * DataPipeline.java — DIP Solution
 *
 * High-level Pipeline depends only on abstractions.
 * Swap any component — source, transform, or sink — without touching Pipeline.
 * Run: javac DataPipeline.java && java DataPipeline
 */
import java.util.*;
import java.util.stream.Collectors;

public class DataPipeline {

    // ── Abstractions ──
    interface DataReader      { List<String> read(); }
    interface DataTransformer { List<String> transform(List<String> data); }
    interface DataWriter      { void write(List<String> data); }

    // ── Readers ──
    static class CSVReader implements DataReader {
        public List<String> read() {
            System.out.println("  📂 CSVReader reading...");
            return List.of("alice,30", "bob,25", "carol,35", "dave,28");
        }
    }

    static class JSONReader implements DataReader {
        public List<String> read() {
            System.out.println("  📂 JSONReader reading...");
            return List.of("{name:alice}", "{name:bob}", "{name:carol}");
        }
    }

    // ── Transformers ──
    static class UpperCaseTransformer implements DataTransformer {
        public List<String> transform(List<String> data) {
            return data.stream().map(String::toUpperCase).collect(Collectors.toList());
        }
    }

    static class FilterTransformer implements DataTransformer {
        private String keyword;
        FilterTransformer(String keyword) { this.keyword = keyword; }
        public List<String> transform(List<String> data) {
            return data.stream().filter(s -> !s.contains(keyword)).collect(Collectors.toList());
        }
    }

    // ── Writers ──
    static class ConsoleWriter implements DataWriter {
        public void write(List<String> data) {
            System.out.println("  📊 ConsoleWriter output:");
            data.forEach(line -> System.out.println("    → " + line));
        }
    }

    static class FileWriter implements DataWriter {
        private String filename;
        FileWriter(String filename) { this.filename = filename; }
        public void write(List<String> data) {
            System.out.println("  💾 FileWriter → " + filename + " (" + data.size() + " records)");
        }
    }

    // ── High-level: depends ONLY on abstractions ──
    static class Pipeline {
        private DataReader reader;
        private DataTransformer transformer;
        private DataWriter writer;

        Pipeline(DataReader reader, DataTransformer transformer, DataWriter writer) {
            this.reader = reader;
            this.transformer = transformer;
            this.writer = writer;
        }

        public void execute() {
            List<String> raw = reader.read();
            List<String> processed = transformer.transform(raw);
            writer.write(processed);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== DIP SOLUTION: DATA PIPELINE ===\n");

        System.out.println("── Pipeline 1: CSV → UpperCase → Console ──");
        new Pipeline(new CSVReader(), new UpperCaseTransformer(), new ConsoleWriter()).execute();

        System.out.println("\n── Pipeline 2: JSON → Filter(carol) → File ──");
        new Pipeline(new JSONReader(), new FilterTransformer("carol"), new FileWriter("output.json")).execute();

        System.out.println("\n── Pipeline 3: CSV → Filter(bob) → Console ──");
        new Pipeline(new CSVReader(), new FilterTransformer("bob"), new ConsoleWriter()).execute();

        System.out.println("\nDIP ✅ — Pipeline class never changed across 3 completely different configurations");
    }
}
