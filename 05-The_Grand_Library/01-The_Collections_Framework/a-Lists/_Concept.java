/**
 * _Concept.java — The Rune of Syntax: Lists
 * 
 * Compile & Run:
 *   javac _Concept.java
 *   java _Concept
 */

import java.util.*;
import java.util.stream.Collectors;

public class _Concept {
    public static void main(String[] args) {
        System.out.println("=== THE RUNE OF SYNTAX: LISTS ===\n");

        // ── 1. CREATING LISTS ──
        System.out.println("--- 1. Creating Lists ---");
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();
        List<String> sized = new ArrayList<>(100);
        List<String> immutable = List.of("Alpha", "Beta", "Gamma");
        System.out.println("Immutable: " + immutable);
        List<String> copy = new ArrayList<>(immutable);
        System.out.println("Copy: " + copy);

        // ── 2. ADDING ELEMENTS ──
        System.out.println("\n--- 2. Adding Elements ---");
        List<String> heroes = new ArrayList<>();
        heroes.add("Aragorn");
        heroes.add("Legolas");
        heroes.add("Gimli");
        System.out.println("After add: " + heroes);
        heroes.add(1, "Gandalf");
        System.out.println("Insert at 1: " + heroes);
        heroes.addAll(List.of("Frodo", "Sam"));
        System.out.println("After addAll: " + heroes);

        // ── 3. ACCESSING ELEMENTS ──
        System.out.println("\n--- 3. Accessing Elements ---");
        System.out.println("get(0): " + heroes.get(0));
        System.out.println("indexOf(Gandalf): " + heroes.indexOf("Gandalf"));
        System.out.println("contains(Legolas): " + heroes.contains("Legolas"));
        System.out.println("size: " + heroes.size());

        // ── 4. MODIFYING ELEMENTS ──
        System.out.println("\n--- 4. Modifying ---");
        String old = heroes.set(0, "Strider");
        System.out.println("Replaced '" + old + "' → " + heroes);

        // ── 5. REMOVING ELEMENTS ──
        System.out.println("\n--- 5. Removing ---");
        heroes.remove("Sam");
        heroes.remove(0);
        System.out.println("After removals: " + heroes);

        // ── 6. ITERATING ──
        System.out.println("\n--- 6. Iterating ---");
        List<String> colors = List.of("Red", "Green", "Blue");
        System.out.print("Enhanced for: ");
        for (String c : colors) System.out.print(c + " ");
        System.out.println();

        System.out.print("forEach: ");
        colors.forEach(c -> System.out.print(c + " "));
        System.out.println();

        System.out.print("Iterator: ");
        Iterator<String> it = colors.iterator();
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();

        // ── 7. SORTING ──
        System.out.println("\n--- 7. Sorting ---");
        List<String> names = new ArrayList<>(List.of("Charlie", "Alice", "Bob"));
        Collections.sort(names);
        System.out.println("Natural: " + names);
        names.sort(Comparator.reverseOrder());
        System.out.println("Reverse: " + names);
        names.sort(Comparator.comparingInt(String::length));
        System.out.println("By length: " + names);

        // ── 8. SEARCHING & FILTERING ──
        System.out.println("\n--- 8. Search & Filter ---");
        List<Integer> scores = List.of(85, 92, 78, 95, 88);
        List<Integer> high = scores.stream().filter(s -> s >= 90).collect(Collectors.toList());
        System.out.println("Scores >= 90: " + high);

        // ── 9. UTILITIES ──
        System.out.println("\n--- 9. Utilities ---");
        List<Integer> data = new ArrayList<>(List.of(3, 1, 4, 1, 5));
        System.out.println("Min: " + Collections.min(data));
        System.out.println("Max: " + Collections.max(data));
        System.out.println("Freq of 1: " + Collections.frequency(data, 1));
        Collections.shuffle(data);
        System.out.println("Shuffled: " + data);

        // ── 10. ARRAY ↔ LIST ──
        System.out.println("\n--- 10. Conversions ---");
        String[] arr = {"Java", "Python", "Rust"};
        List<String> fromArr = new ArrayList<>(Arrays.asList(arr));
        System.out.println("Array→List: " + fromArr);
        String[] back = fromArr.toArray(new String[0]);
        System.out.println("List→Array: " + Arrays.toString(back));

        System.out.println("\n=== RUNE COMPLETE ===");
    }
}
