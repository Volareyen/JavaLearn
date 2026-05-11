/**
 * _Concept.java — The Rune of Syntax: Maps
 * 
 * Compile & Run:  javac _Concept.java && java _Concept
 */
import java.util.*;

public class _Concept {
    public static void main(String[] args) {
        System.out.println("=== THE RUNE OF SYNTAX: MAPS ===\n");

        // ── 1. CREATING MAPS ──
        System.out.println("--- 1. Creating Maps ---");
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        Map<String, Integer> treeMap = new TreeMap<>();
        Map<String, Integer> immutable = Map.of("A", 1, "B", 2, "C", 3);
        System.out.println("Immutable: " + immutable);

        // ── 2. PUT & GET ──
        System.out.println("\n--- 2. Put & Get ---");
        Map<String, Integer> ages = new HashMap<>();
        ages.put("Alice", 25);
        ages.put("Bob", 30);
        ages.put("Charlie", 28);
        System.out.println("Map: " + ages);
        Integer old = ages.put("Alice", 26); // returns old value
        System.out.println("Updated Alice, old value: " + old);
        System.out.println("get(Bob): " + ages.get("Bob"));
        System.out.println("getOrDefault(Zara, 0): " + ages.getOrDefault("Zara", 0));

        // ── 3. CHECKING ──
        System.out.println("\n--- 3. Checking ---");
        System.out.println("containsKey(Alice): " + ages.containsKey("Alice"));
        System.out.println("containsValue(30): " + ages.containsValue(30));
        System.out.println("size: " + ages.size());
        System.out.println("isEmpty: " + ages.isEmpty());

        // ── 4. REMOVING ──
        System.out.println("\n--- 4. Removing ---");
        ages.remove("Charlie");
        System.out.println("After remove Charlie: " + ages);
        ages.put("Diana", 22);
        ages.remove("Diana", 99); // won't remove, value doesn't match
        System.out.println("Remove Diana if 99 (no match): " + ages);

        // ── 5. ITERATING ──
        System.out.println("\n--- 5. Iterating ---");
        Map<String, String> capitals = new LinkedHashMap<>();
        capitals.put("Japan", "Tokyo");
        capitals.put("France", "Paris");
        capitals.put("Brazil", "Brasilia");

        System.out.println("entrySet:");
        for (Map.Entry<String, String> e : capitals.entrySet()) {
            System.out.println("  " + e.getKey() + " → " + e.getValue());
        }
        System.out.println("forEach:");
        capitals.forEach((k, v) -> System.out.println("  " + k + " → " + v));

        // ── 6. MODERN METHODS (Java 8+) ──
        System.out.println("\n--- 6. Modern Methods ---");
        Map<String, List<String>> groups = new HashMap<>();
        groups.computeIfAbsent("Team A", k -> new ArrayList<>()).add("Alice");
        groups.computeIfAbsent("Team A", k -> new ArrayList<>()).add("Bob");
        groups.computeIfAbsent("Team B", k -> new ArrayList<>()).add("Charlie");
        System.out.println("computeIfAbsent: " + groups);

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : "the cat sat on the mat the cat".split(" ")) {
            wordCount.merge(word, 1, Integer::sum);
        }
        System.out.println("merge (word count): " + wordCount);

        ages.putIfAbsent("Eve", 35);
        ages.putIfAbsent("Alice", 99); // won't overwrite
        System.out.println("putIfAbsent: " + ages);

        // ── 7. ORDERING DEMOS ──
        System.out.println("\n--- 7. Ordering ---");
        Map<String, Integer> h = new HashMap<>(Map.of("Banana", 2, "Apple", 1, "Cherry", 3));
        Map<String, Integer> l = new LinkedHashMap<>();
        l.put("Banana", 2); l.put("Apple", 1); l.put("Cherry", 3);
        Map<String, Integer> t = new TreeMap<>(Map.of("Banana", 2, "Apple", 1, "Cherry", 3));
        System.out.println("HashMap:       " + h);
        System.out.println("LinkedHashMap: " + l);
        System.out.println("TreeMap:       " + t);

        // ── 8. TREEMAP EXTRAS ──
        System.out.println("\n--- 8. TreeMap Extras ---");
        TreeSet<String> keys = new TreeSet<>(t.keySet()); // already sorted
        TreeMap<String, Integer> tm = new TreeMap<>(t);
        System.out.println("firstKey: " + tm.firstKey());
        System.out.println("lastKey: " + tm.lastKey());
        System.out.println("headMap(<Cherry): " + tm.headMap("Cherry"));
        System.out.println("tailMap(>=Cherry): " + tm.tailMap("Cherry"));

        // ── 9. VIEWS ──
        System.out.println("\n--- 9. Views ---");
        System.out.println("keySet: " + capitals.keySet());
        System.out.println("values: " + capitals.values());
        System.out.println("entrySet: " + capitals.entrySet());

        System.out.println("\n=== RUNE COMPLETE ===");
    }
}
