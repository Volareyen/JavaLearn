# Realm III: Maps — The Great Codex

*"The Map is the most powerful lookup structure in the Grand Library. Where Lists store things in order and Sets guard uniqueness, Maps forge associations — binding a key to a value, a name to a destiny, a word to its meaning. Master the Map, and no data shall ever be beyond your immediate reach."*

---

## **What Is a Map?**

A `Map<K,V>` is a collection of **key-value pairs** where:
- **Every key is unique** — duplicate keys are not allowed
- **Each key maps to exactly one value** — but values can be duplicated
- **Not a Collection** — Map does NOT extend the `Collection` interface
- **O(1) lookup by key** — the defining superpower (for HashMap)

---

## **The Three Great Implementations**

### **HashMap — The Swift Codex**
- **O(1)** get/put/remove (average case)
- **No ordering guarantee**
- **Allows one null key** and multiple null values
- **Best for**: General-purpose key-value storage

### **LinkedHashMap — The Ordered Codex**
- **O(1)** operations like HashMap
- **Maintains insertion order** (or access order if configured)
- **Best for**: When you need predictable iteration order
- **Bonus**: Can be used as an LRU cache with `removeEldestEntry()`

### **TreeMap — The Sorted Codex**
- **O(log n)** operations (Red-Black tree)
- **Keys always sorted** in natural or custom order
- **Best for**: Range queries, sorted iteration, navigable operations
- **Requires**: Keys must be `Comparable` or use a `Comparator`

---

## **Essential Map Operations**

```java
Map<String, Integer> ages = new HashMap<>();

// Put (add or update)
ages.put("Alice", 25);        // Add new
ages.put("Bob", 30);
ages.put("Alice", 26);        // Update existing — returns old value (25)

// Get
int age = ages.get("Alice");                    // 26
Integer unknown = ages.get("Zara");             // null
int safe = ages.getOrDefault("Zara", 0);        // 0

// Check
boolean hasAlice = ages.containsKey("Alice");   // true
boolean has30 = ages.containsValue(30);         // true

// Remove
ages.remove("Bob");                             // Remove by key
ages.remove("Alice", 99);                       // Remove only if value matches

// Size
int size = ages.size();
boolean empty = ages.isEmpty();
```

---

## **Iterating Over Maps**

```java
Map<String, Integer> scores = Map.of("Alice", 95, "Bob", 87, "Charlie", 92);

// Entry set — most common and efficient
for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}

// Key set
for (String key : scores.keySet()) {
    System.out.println(key + ": " + scores.get(key));
}

// Values only
for (Integer value : scores.values()) {
    System.out.println(value);
}

// forEach (Java 8+)
scores.forEach((name, score) -> System.out.println(name + ": " + score));
```

---

## **Modern Map Methods (Java 8+)**

```java
Map<String, List<String>> groups = new HashMap<>();

// computeIfAbsent — create value if key missing
groups.computeIfAbsent("Team A", k -> new ArrayList<>()).add("Alice");

// putIfAbsent — put only if key not present
ages.putIfAbsent("Diana", 28);

// merge — combine old and new values
Map<String, Integer> wordCount = new HashMap<>();
wordCount.merge("hello", 1, Integer::sum); // Increment pattern

// replaceAll — transform all values
ages.replaceAll((name, a) -> a + 1); // Everyone ages one year
```

*"These modern methods are the wise teacher's gift — they eliminate the verbose check-then-act patterns that plagued the ancient coders."*

---

## **Your Trial Awaits**

1. `_Concept.java` — Pure syntax reference for Map operations
2. `_PracticalExample.java` — A practical inventory system
3. `_Challenge.md` — Your trial to prove Map mastery
4. `_Solution/` — The path revealed
