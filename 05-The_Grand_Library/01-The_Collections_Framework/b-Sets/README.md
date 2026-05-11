# Realm II: Sets — The Unique Runes

*"In a world drowning in duplicates, the Set stands as the guardian of uniqueness. No two identical elements may coexist within its domain. Where the List remembers order but tolerates repetition, the Set demands that every element be distinct."*

---

## **What Is a Set?**

A `Set<E>` is a collection that:
- **Contains no duplicate elements** — the defining trait
- **Has no positional access** — no `get(index)` method
- **Models the mathematical set abstraction** — union, intersection, difference
- **Uses `equals()` and `hashCode()`** to determine uniqueness

---

## **The Three Great Implementations**

### **HashSet — The Swift Guardian**
- Backed by a **HashMap** internally
- **O(1)** average time for add, remove, contains
- **No ordering guarantee** — iteration order is unpredictable
- **Best for**: General-purpose uniqueness when order doesn't matter

### **LinkedHashSet — The Ordered Guardian**
- Backed by a **linked hash table**
- **O(1)** operations like HashSet
- **Maintains insertion order** — iterates in the order elements were added
- **Best for**: When you need uniqueness AND insertion order

### **TreeSet — The Sorted Guardian**
- Backed by a **TreeMap** (Red-Black tree)
- **O(log n)** for add, remove, contains
- **Maintains sorted order** — elements are always in natural or custom order
- **Best for**: When you need uniqueness AND sorted access
- **Requires**: Elements must be `Comparable` or a `Comparator` must be provided

---

## **Choosing the Right Set**

| Feature | `HashSet` | `LinkedHashSet` | `TreeSet` |
|---------|:---------:|:---------------:|:---------:|
| Duplicates blocked | ✅ | ✅ | ✅ |
| Ordering | None | Insertion order | Sorted order |
| add/remove/contains | O(1) | O(1) | O(log n) |
| null elements | 1 allowed | 1 allowed | ❌ (if using natural order) |
| Memory | Low | Medium | Higher |

---

## **The equals() and hashCode() Contract**

Sets rely critically on the `equals()` and `hashCode()` methods you mastered in Stage 4 (The Cosmic Ancestor):

```java
// Two objects that are equal MUST have the same hashCode
// If hashCode differs, the Set treats them as different elements
// If hashCode is the same, equals() is checked

Set<Student> roster = new HashSet<>();
Student s1 = new Student("Alice", 101);
Student s2 = new Student("Alice", 101);

// Without overriding equals/hashCode: s1 and s2 are DIFFERENT (object identity)
// With proper overrides: s1 and s2 are THE SAME (value equality)
```

*"This is where your mastery of the Object class pays its dividend. Without proper `equals()` and `hashCode()`, a Set cannot fulfill its sacred duty of uniqueness."*

---

## **Set Operations — Mathematical Beauty**

```java
Set<String> a = new HashSet<>(Set.of("Java", "Python", "Rust"));
Set<String> b = new HashSet<>(Set.of("Python", "Go", "Rust"));

// Union (all unique elements from both)
Set<String> union = new HashSet<>(a);
union.addAll(b);  // [Java, Python, Rust, Go]

// Intersection (common elements)
Set<String> intersection = new HashSet<>(a);
intersection.retainAll(b);  // [Python, Rust]

// Difference (in a but not in b)
Set<String> difference = new HashSet<>(a);
difference.removeAll(b);  // [Java]

// Symmetric difference (in one but not both)
Set<String> symDiff = new HashSet<>(union);
symDiff.removeAll(intersection);  // [Java, Go]
```

---

## **Your Trial Awaits**

Study the scrolls, then face the challenge:
1. `_Concept.java` — Pure syntax reference for Set operations
2. `_PracticalExample.java` — A practical system built with Sets
3. `_Challenge.md` — Your trial to prove Set mastery
4. `_Solution/` — The path revealed
