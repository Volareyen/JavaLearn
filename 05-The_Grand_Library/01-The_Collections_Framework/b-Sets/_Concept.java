/**
 * _Concept.java — The Rune of Syntax: Sets
 * 
 * Compile & Run:  javac _Concept.java && java _Concept
 */
import java.util.*;
import java.util.stream.Collectors;

public class _Concept {
    public static void main(String[] args) {
        System.out.println("=== THE RUNE OF SYNTAX: SETS ===\n");

        // ── 1. CREATING SETS ──
        System.out.println("--- 1. Creating Sets ---");
        Set<String> hashSet = new HashSet<>();
        Set<String> linkedSet = new LinkedHashSet<>();
        Set<String> treeSet = new TreeSet<>();
        Set<String> immutable = Set.of("Alpha", "Beta", "Gamma");
        System.out.println("Immutable: " + immutable);
        Set<String> copy = new HashSet<>(immutable);
        System.out.println("Copy: " + copy);

        // ── 2. ADDING ELEMENTS ──
        System.out.println("\n--- 2. Adding Elements ---");
        Set<String> fruits = new HashSet<>();
        System.out.println("add Apple: " + fruits.add("Apple"));
        System.out.println("add Banana: " + fruits.add("Banana"));
        System.out.println("add Apple again: " + fruits.add("Apple")); // false!
        System.out.println("Set: " + fruits);
        fruits.addAll(Set.of("Cherry", "Date", "Elderberry"));
        System.out.println("After addAll: " + fruits);

        // ── 3. QUERYING ──
        System.out.println("\n--- 3. Querying ---");
        System.out.println("contains Apple: " + fruits.contains("Apple"));
        System.out.println("contains Mango: " + fruits.contains("Mango"));
        System.out.println("size: " + fruits.size());
        System.out.println("isEmpty: " + fruits.isEmpty());

        // ── 4. REMOVING ──
        System.out.println("\n--- 4. Removing ---");
        fruits.remove("Date");
        System.out.println("After remove Date: " + fruits);
        fruits.removeIf(f -> f.startsWith("E"));
        System.out.println("After removeIf(starts with E): " + fruits);

        // ── 5. ITERATION ──
        System.out.println("\n--- 5. Iteration ---");
        Set<String> ordered = new LinkedHashSet<>(List.of("Red", "Green", "Blue"));
        System.out.print("for-each: ");
        for (String c : ordered) System.out.print(c + " ");
        System.out.println();
        System.out.print("forEach: ");
        ordered.forEach(c -> System.out.print(c + " "));
        System.out.println();

        // ── 6. ORDERING DEMONSTRATIONS ──
        System.out.println("\n--- 6. Ordering ---");
        Set<String> hash = new HashSet<>(List.of("Banana", "Apple", "Cherry"));
        Set<String> linked = new LinkedHashSet<>(List.of("Banana", "Apple", "Cherry"));
        Set<String> tree = new TreeSet<>(List.of("Banana", "Apple", "Cherry"));
        System.out.println("HashSet order:       " + hash);
        System.out.println("LinkedHashSet order: " + linked);
        System.out.println("TreeSet order:       " + tree);

        // ── 7. SET OPERATIONS (MATH) ──
        System.out.println("\n--- 7. Set Operations ---");
        Set<Integer> a = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        Set<Integer> b = new HashSet<>(Set.of(3, 4, 5, 6, 7));

        Set<Integer> union = new HashSet<>(a);
        union.addAll(b);
        System.out.println("A ∪ B (union): " + union);

        Set<Integer> intersection = new HashSet<>(a);
        intersection.retainAll(b);
        System.out.println("A ∩ B (intersection): " + intersection);

        Set<Integer> difference = new HashSet<>(a);
        difference.removeAll(b);
        System.out.println("A - B (difference): " + difference);

        System.out.println("A is subset of union: " + union.containsAll(a));

        // ── 8. TREESET SPECIAL OPS ──
        System.out.println("\n--- 8. TreeSet Extras ---");
        TreeSet<Integer> nums = new TreeSet<>(List.of(10, 20, 30, 40, 50));
        System.out.println("first: " + nums.first());
        System.out.println("last: " + nums.last());
        System.out.println("headSet(<30): " + nums.headSet(30));
        System.out.println("tailSet(>=30): " + nums.tailSet(30));
        System.out.println("subSet(20,40): " + nums.subSet(20, 40));
        System.out.println("floor(25): " + nums.floor(25));
        System.out.println("ceiling(25): " + nums.ceiling(25));

        // ── 9. CONVERSIONS ──
        System.out.println("\n--- 9. Conversions ---");
        List<String> withDupes = List.of("A", "B", "A", "C", "B");
        Set<String> deduped = new LinkedHashSet<>(withDupes);
        System.out.println("List with dupes: " + withDupes);
        System.out.println("Set (deduped): " + deduped);
        List<String> backToList = new ArrayList<>(deduped);
        System.out.println("Back to List: " + backToList);

        System.out.println("\n=== RUNE COMPLETE ===");
    }
}
