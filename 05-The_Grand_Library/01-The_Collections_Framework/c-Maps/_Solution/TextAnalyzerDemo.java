/**
 * TextAnalyzerDemo.java — Complete Text Analysis Engine Solution
 * 
 * Compile & Run: javac TextAnalyzerDemo.java && java TextAnalyzerDemo
 */
import java.util.*;
import java.util.stream.Collectors;

public class TextAnalyzerDemo {

    static class TextAnalyzer {

        public Map<String, Integer> getWordFrequency(String text) {
            Map<String, Integer> freq = new LinkedHashMap<>();
            for (String word : extractWords(text)) {
                freq.merge(word, 1, Integer::sum);
            }
            return freq;
        }

        public Map<Character, Integer> getCharFrequency(String text) {
            Map<Character, Integer> freq = new TreeMap<>();
            for (char c : text.toLowerCase().toCharArray()) {
                if (c != ' ') freq.merge(c, 1, Integer::sum);
            }
            return freq;
        }

        public Map<Integer, List<String>> groupByWordLength(String text) {
            Map<Integer, List<String>> groups = new TreeMap<>();
            for (String word : new LinkedHashSet<>(extractWords(text))) {
                groups.computeIfAbsent(word.length(), k -> new ArrayList<>()).add(word);
            }
            return groups;
        }

        public Map<String, List<Integer>> getWordPositions(String text) {
            Map<String, List<Integer>> positions = new LinkedHashMap<>();
            List<String> words = extractWords(text);
            for (int i = 0; i < words.size(); i++) {
                positions.computeIfAbsent(words.get(i), k -> new ArrayList<>()).add(i);
            }
            return positions;
        }

        public List<Map.Entry<String, Integer>> getTopNWords(String text, int n) {
            return getWordFrequency(text).entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toList());
        }

        public Map<String, String> buildIndex(String text) {
            Map<String, String> index = new TreeMap<>();
            String[] sentences = text.split("[.!?]+");
            for (String sentence : sentences) {
                String trimmed = sentence.trim();
                for (String word : extractWords(trimmed)) {
                    index.putIfAbsent(word, trimmed);
                }
            }
            return index;
        }

        public double calculateSimilarity(String text1, String text2) {
            Set<String> words1 = new HashSet<>(extractWords(text1));
            Set<String> words2 = new HashSet<>(extractWords(text2));
            Set<String> intersection = new HashSet<>(words1);
            intersection.retainAll(words2);
            Set<String> union = new HashSet<>(words1);
            union.addAll(words2);
            return union.isEmpty() ? 0 : (double) intersection.size() / union.size();
        }

        private List<String> extractWords(String text) {
            return Arrays.stream(text.toLowerCase().split("\\W+"))
                .filter(w -> !w.isEmpty())
                .collect(Collectors.toList());
        }
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   📖 TEXT ANALYSIS ENGINE                ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        TextAnalyzer analyzer = new TextAnalyzer();

        String text = "Java is a powerful programming language. Java provides object oriented " +
            "programming features. Programming in Java requires understanding of classes and " +
            "objects. Object oriented programming is the foundation of modern software design. " +
            "Design patterns help developers write better code. Better code leads to better " +
            "software and better software serves more people.";

        // Word frequency
        System.out.println("── Top 8 Words ──");
        analyzer.getTopNWords(text, 8).forEach(e ->
            System.out.printf("  %-15s %d times%n", e.getKey(), e.getValue()));

        // Word length groups
        System.out.println("\n── Words by Length ──");
        analyzer.groupByWordLength(text).forEach((len, words) ->
            System.out.printf("  %2d letters: %s%n", len, words));

        // Word positions
        System.out.println("\n── Positions of 'java' ──");
        List<Integer> javaPos = analyzer.getWordPositions(text).getOrDefault("java", List.of());
        System.out.println("  Appears at positions: " + javaPos);

        // Character frequency (top 10)
        System.out.println("\n── Top 10 Characters ──");
        analyzer.getCharFrequency(text).entrySet().stream()
            .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
            .limit(10)
            .forEach(e -> System.out.printf("  '%c' → %d%n", e.getKey(), e.getValue()));

        // Word index
        System.out.println("\n── Word Index (sample) ──");
        Map<String, String> index = analyzer.buildIndex(text);
        List.of("java", "design", "foundation").forEach(word ->
            System.out.printf("  %-12s → \"%s\"%n", word,
                index.getOrDefault(word, "not found")));

        // Similarity
        String text2 = "Python is a versatile programming language. Python supports object " +
            "oriented programming and functional programming paradigms.";
        double sim = analyzer.calculateSimilarity(text, text2);
        System.out.printf("%n── Similarity ──%n  Java text vs Python text: %.1f%%%n", sim * 100);

        System.out.println("\n✅ Text Analysis Engine complete!");
    }
}
