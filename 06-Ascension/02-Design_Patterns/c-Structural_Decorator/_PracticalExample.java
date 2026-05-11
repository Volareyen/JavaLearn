/**
 * _PracticalExample.java — Decorator Pattern: Text Processing Pipeline
 *
 * Demonstrates stacked text transformers — each wraps the previous,
 * adding a new transformation layer without changing existing code.
 *
 * Compile & Run: javac _PracticalExample.java && java _PracticalExample
 */
public class _PracticalExample {

    // ── Component ──
    interface TextProcessor {
        String process(String text);
        String name();
    }

    // ── Concrete Component ──
    static class PlainText implements TextProcessor {
        public String process(String text) { return text; }
        public String name() { return "Plain"; }
    }

    // ── Abstract Decorator ──
    static abstract class TextDecorator implements TextProcessor {
        protected TextProcessor inner;
        TextDecorator(TextProcessor inner) { this.inner = inner; }
        public String name() { return inner.name(); }
    }

    // ── Concrete Decorators ──
    static class TrimDecorator extends TextDecorator {
        TrimDecorator(TextProcessor p) { super(p); }
        public String process(String text) { return inner.process(text).trim(); }
        public String name() { return inner.name() + " → Trim"; }
    }

    static class UpperCaseDecorator extends TextDecorator {
        UpperCaseDecorator(TextProcessor p) { super(p); }
        public String process(String text) { return inner.process(text).toUpperCase(); }
        public String name() { return inner.name() + " → UpperCase"; }
    }

    static class CensorDecorator extends TextDecorator {
        private String[] banned;
        CensorDecorator(TextProcessor p, String... banned) {
            super(p); this.banned = banned;
        }
        public String process(String text) {
            String result = inner.process(text);
            for (String word : banned) {
                result = result.replaceAll("(?i)" + word, "*".repeat(word.length()));
            }
            return result;
        }
        public String name() { return inner.name() + " → Censor"; }
    }

    static class SlugifyDecorator extends TextDecorator {
        SlugifyDecorator(TextProcessor p) { super(p); }
        public String process(String text) {
            return inner.process(text).toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("\\s+", "-");
        }
        public String name() { return inner.name() + " → Slugify"; }
    }

    static class TruncateDecorator extends TextDecorator {
        private int maxLen;
        TruncateDecorator(TextProcessor p, int maxLen) { super(p); this.maxLen = maxLen; }
        public String process(String text) {
            String result = inner.process(text);
            return result.length() > maxLen ? result.substring(0, maxLen) + "..." : result;
        }
        public String name() { return inner.name() + " → Truncate(" + maxLen + ")"; }
    }

    static void demo(String label, TextProcessor processor, String input) {
        System.out.println("  [" + label + "]");
        System.out.println("  Pipeline: " + processor.name());
        System.out.println("  Input:    \"" + input + "\"");
        System.out.println("  Output:   \"" + processor.process(input) + "\"");
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   📝 TEXT PROCESSING PIPELINE        ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        String input = "   Hello World! This is a SPAM test.   ";

        // Just trim
        demo("Trim only",
            new TrimDecorator(new PlainText()), input);

        // Trim + UpperCase
        demo("Trim + Upper",
            new UpperCaseDecorator(new TrimDecorator(new PlainText())), input);

        // Trim + Censor + Truncate
        demo("Trim + Censor + Truncate",
            new TruncateDecorator(
                new CensorDecorator(
                    new TrimDecorator(new PlainText()), "spam"),
                30), input);

        // Blog URL slug pipeline
        demo("Title to URL slug",
            new SlugifyDecorator(new TrimDecorator(new PlainText())),
            "  My Amazing Blog Post Title! (2024)  ");

        System.out.println("✅ Each pipeline built by composing simple decorators!");
    }
}
