/**
 * _Concept.java - The Rune of Syntax for Generics
 * 
 * This sacred scroll demonstrates the pure syntax of generic programming
 * in its most fundamental forms. Study these patterns well,
 * for they are the building blocks of type-safe abstraction.
 * 
 * "Observe the progression from simple to sophisticated - 
 * each example reveals another facet of parametric power."
 */

import java.util.*;
import java.util.function.*;

// ========================================
// BASIC GENERIC CLASS DECLARATION
// ========================================
/**
 * The simplest form - a container parameterized by type T
 * T is a type parameter that will be replaced with an actual type
 */
class BasicContainer<T> {
    private T content;
    
    // Constructor uses the type parameter
    public BasicContainer(T content) {
        this.content = content;
    }
    
    // Methods can use the type parameter
    public T getContent() {
        return content;
    }
    
    public void setContent(T content) {
        this.content = content;
    }
    
    // Type parameter can be used in complex ways
    public boolean isSameType(T other) {
        return content != null && other != null && 
               content.getClass() == other.getClass();
    }
}

// ========================================
// MULTIPLE TYPE PARAMETERS
// ========================================
/**
 * A class can have multiple type parameters
 * Convention: T, U, V for general types; K, V for key-value pairs
 */
class Pair<T, U> {
    private final T first;
    private final U second;
    
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
    
    public T getFirst() { return first; }
    public U getSecond() { return second; }
    
    // Methods can use both type parameters
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
    
    // Static factory method with type inference
    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }
}

// ========================================
// BOUNDED TYPE PARAMETERS
// ========================================
/**
 * Type parameters can be bounded to specific types or interfaces
 * This allows using methods from the bounding type
 */
class NumberContainer<T extends Number> {
    private T number;
    
    public NumberContainer(T number) {
        this.number = number;
    }
    
    // Can call Number methods because T extends Number
    public double getDoubleValue() {
        return number.doubleValue();
    }
    
    public int getIntValue() {
        return number.intValue();
    }
    
    // Can compare if T also implements Comparable
    public static <T extends Number & Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }
}

// ========================================
// GENERIC INTERFACES
// ========================================
/**
 * Interfaces can be generic and define contracts for parameterized behavior
 */
interface Repository<T, ID> {
    void save(T entity);
    T findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    boolean existsById(ID id);
}

/**
 * Generic interface for transformation operations
 */
interface Transformer<F, T> {
    T transform(F from);
    
    // Default method can use both type parameters
    default List<T> transformAll(List<F> fromList) {
        List<T> result = new ArrayList<>();
        for (F item : fromList) {
            result.add(transform(item));
        }
        return result;
    }
}

// ========================================
// IMPLEMENTING GENERIC INTERFACES
// ========================================
/**
 * Example implementation of generic Repository
 */
class UserRepository implements Repository<String, Long> {
    private Map<Long, String> storage = new HashMap<>();
    private Long nextId = 1L;
    
    @Override
    public void save(String entity) {
        storage.put(nextId++, entity);
    }
    
    @Override
    public String findById(Long id) {
        return storage.get(id);
    }
    
    @Override
    public List<String> findAll() {
        return new ArrayList<>(storage.values());
    }
    
    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return storage.containsKey(id);
    }
}

// ========================================
// WILDCARD TYPES
// ========================================
/**
 * Utility class demonstrating wildcard usage
 */
class WildcardExamples {
    
    // Upper bounded wildcard - can read as Number or its subtypes
    public static double sum(List<? extends Number> numbers) {
        double total = 0.0;
        for (Number num : numbers) {
            total += num.doubleValue(); // Safe - all are Numbers
        }
        return total;
        // Cannot add to list: numbers.add(5); // Compile error
    }
    
    // Lower bounded wildcard - can add Integer or its subtypes
    public static void addIntegers(List<? super Integer> numbers) {
        numbers.add(1);
        numbers.add(42);
        numbers.add(100);
        // Cannot read as Integer: Integer i = numbers.get(0); // Compile error
    }
    
    // Unbounded wildcard - can work with any parameterized type
    public static int size(List<?> list) {
        return list.size(); // Can call methods that don't depend on type
        // Cannot read: Object obj = list.get(0); // Only Object is safe
        // Cannot add: list.add("hello"); // Compile error
    }
    
    // Copy method using both wildcards (PECS in action)
    public static <T> void copy(List<? extends T> source, List<? super T> destination) {
        for (T item : source) {
            destination.add(item);
        }
    }
}

// ========================================
// GENERIC METHODS
// ========================================
/**
 * Methods can be generic independently of their class
 */
class GenericMethods {
    
    // Simple generic method
    public static <T> T identity(T input) {
        return input;
    }
    
    // Generic method with bounds
    public static <T extends Comparable<T>> T min(T a, T b) {
        return a.compareTo(b) <= 0 ? a : b;
    }
    
    // Multiple type parameters
    public static <T, U> Pair<U, T> swap(Pair<T, U> pair) {
        return new Pair<>(pair.getSecond(), pair.getFirst());
    }
    
    // Array-based generic method
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    // Varargs generic method
    @SafeVarargs
    public static <T> List<T> listOf(T... items) {
        List<T> result = new ArrayList<>();
        Collections.addAll(result, items);
        return result;
    }
    
    // Generic method with wildcard parameter
    public static void printList(List<?> list) {
        for (Object item : list) {
            System.out.println(item);
        }
    }
}

// ========================================
// RECURSIVE TYPE BOUNDS
// ========================================
/**
 * Advanced pattern where type parameter refers to itself
 */
abstract class ComparableEntity<T extends ComparableEntity<T>> implements Comparable<T> {
    protected String name;
    
    public ComparableEntity(String name) {
        this.name = name;
    }
    
    // Abstract method that subclasses must implement
    protected abstract int compareFields(T other);
    
    @Override
    public final int compareTo(T other) {
        if (other == null) return 1;
        if (this == other) return 0;
        
        // First compare by class name for type safety
        int classComparison = this.getClass().getName().compareTo(other.getClass().getName());
        if (classComparison != 0) return classComparison;
        
        // Then by name
        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) return nameComparison;
        
        // Finally by specific fields
        return compareFields(other);
    }
}

/**
 * Concrete implementation of recursive type bound
 */
class Employee extends ComparableEntity<Employee> {
    private int salary;
    
    public Employee(String name, int salary) {
        super(name);
        this.salary = salary;
    }
    
    @Override
    protected int compareFields(Employee other) {
        return Integer.compare(this.salary, other.salary);
    }
    
    public int getSalary() { return salary; }
}

// ========================================
// GENERIC BUILDER PATTERN
// ========================================
/**
 * Generic builder demonstrating fluent interface with type safety
 */
class Query<T> {
    private Class<T> entityClass;
    private String whereClause;
    private List<Object> parameters;
    private String orderBy;
    private int limit;
    
    private Query(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.parameters = new ArrayList<>();
        this.limit = -1;
    }
    
    // Static factory method
    public static <T> Query<T> from(Class<T> entityClass) {
        return new Query<>(entityClass);
    }
    
    // Fluent interface methods
    public Query<T> where(String clause, Object... params) {
        this.whereClause = clause;
        this.parameters.addAll(Arrays.asList(params));
        return this;
    }
    
    public Query<T> orderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }
    
    public Query<T> limit(int limit) {
        this.limit = limit;
        return this;
    }
    
    // Terminal operation
    public List<T> execute() {
        // Simulate query execution
        System.out.println("Executing query for " + entityClass.getSimpleName());
        System.out.println("WHERE: " + whereClause);
        System.out.println("Parameters: " + parameters);
        System.out.println("ORDER BY: " + orderBy);
        System.out.println("LIMIT: " + limit);
        
        return new ArrayList<>(); // Return empty list for demo
    }
}

// ========================================
// TYPE ERASURE DEMONSTRATION
// ========================================
/**
 * Class to demonstrate type erasure concepts and limitations
 */
class TypeErasureDemo {
    
    // These methods have the same signature after erasure
    // public void process(List<String> list) { } // Would cause compile error
    // public void process(List<Integer> list) { } // if both existed
    
    public void processStrings(List<String> list) {
        System.out.println("Processing strings: " + list);
    }
    
    public void processIntegers(List<Integer> list) {
        System.out.println("Processing integers: " + list);
    }
    
    // Cannot create instances of type parameter
    public static <T> void cannotCreateArray() {
        // T[] array = new T[10]; // Compile error - cannot create array
        // T instance = new T();  // Compile error - cannot instantiate
    }
    
    // Cannot use instanceof with parameterized types
    public static boolean checkType(Object obj) {
        // if (obj instanceof List<String>) // Compile error
        if (obj instanceof List<?>) { // This is valid
            List<?> list = (List<?>) obj;
            return true;
        }
        return false;
    }
    
    // Demonstrate runtime type equality
    public static void demonstrateErasure() {
        List<String> stringList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        
        // At runtime, both are just ArrayList
        System.out.println("Same class: " + 
            (stringList.getClass() == intList.getClass())); // true
    }
}

// ========================================
// DEMONSTRATION CLASS
// ========================================
/**
 * Main class demonstrating all generic concepts
 */
public class _Concept {
    
    public static void main(String[] args) {
        System.out.println("=== GENERIC CONCEPT DEMONSTRATIONS ===\n");
        
        // Basic generic container
        demonstrateBasicGenerics();
        
        // Multiple type parameters
        demonstratePairs();
        
        // Bounded type parameters
        demonstrateBoundedTypes();
        
        // Wildcards
        demonstrateWildcards();
        
        // Generic methods
        demonstrateGenericMethods();
        
        // Generic interfaces and implementation
        demonstrateGenericInterfaces();
        
        // Recursive bounds
        demonstrateRecursiveBounds();
        
        // Builder pattern
        demonstrateGenericBuilder();
        
        // Type erasure
        demonstrateTypeErasure();
    }
    
    private static void demonstrateBasicGenerics() {
        System.out.println("--- Basic Generic Container ---");
        
        BasicContainer<String> stringContainer = new BasicContainer<>("Hello Generics!");
        BasicContainer<Integer> intContainer = new BasicContainer<>(42);
        BasicContainer<Date> dateContainer = new BasicContainer<>(new Date());
        
        System.out.println("String container: " + stringContainer.getContent());
        System.out.println("Integer container: " + intContainer.getContent());
        System.out.println("Date container: " + dateContainer.getContent());
        
        // Type safety - these won't compile:
        // stringContainer.setContent(42); // Error!
        // intContainer.setContent("hello"); // Error!
        
        System.out.println();
    }
    
    private static void demonstratePairs() {
        System.out.println("--- Multiple Type Parameters ---");
        
        Pair<String, Integer> nameAge = new Pair<>("Alice", 25);
        Pair<Integer, String> idName = new Pair<>(1001, "Bob");
        Pair<Date, Boolean> dateFlag = Pair.of(new Date(), true);
        
        System.out.println("Name-Age pair: " + nameAge);
        System.out.println("ID-Name pair: " + idName);
        System.out.println("Date-Flag pair: " + dateFlag);
        System.out.println();
    }
    
    private static void demonstrateBoundedTypes() {
        System.out.println("--- Bounded Type Parameters ---");
        
        NumberContainer<Integer> intContainer = new NumberContainer<>(100);
        NumberContainer<Double> doubleContainer = new NumberContainer<>(3.14159);
        
        System.out.println("Integer as double: " + intContainer.getDoubleValue());
        System.out.println("Double as int: " + doubleContainer.getIntValue());
        
        // Using bounded comparison
        Integer maxInt = NumberContainer.max(10, 20);
        Double maxDouble = NumberContainer.max(3.14, 2.71);
        System.out.println("Max integer: " + maxInt);
        System.out.println("Max double: " + maxDouble);
        
        // This won't compile - String doesn't extend Number:
        // NumberContainer<String> stringContainer = new NumberContainer<>("hello");
        
        System.out.println();
    }
    
    private static void demonstrateWildcards() {
        System.out.println("--- Wildcard Types ---");
        
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);
        List<Number> numbers = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        
        // Upper bounded wildcard
        double intSum = WildcardExamples.sum(integers);
        double doubleSum = WildcardExamples.sum(doubles);
        System.out.println("Sum of integers: " + intSum);
        System.out.println("Sum of doubles: " + doubleSum);
        
        // Lower bounded wildcard
        WildcardExamples.addIntegers(numbers);  // Can add to Number list
        WildcardExamples.addIntegers(objects);  // Can add to Object list
        System.out.println("Numbers after adding integers: " + numbers);
        System.out.println("Objects after adding integers: " + objects);
        
        // Unbounded wildcard
        System.out.println("Integer list size: " + WildcardExamples.size(integers));
        System.out.println("Double list size: " + WildcardExamples.size(doubles));
        
        // Copy using wildcards (PECS)
        List<Number> destination = new ArrayList<>();
        WildcardExamples.copy(integers, destination);
        System.out.println("Copied integers to numbers: " + destination);
        
        System.out.println();
    }
    
    private static void demonstrateGenericMethods() {
        System.out.println("--- Generic Methods ---");
        
        // Identity method
        String identity = GenericMethods.identity("Hello");
        Integer intIdentity = GenericMethods.identity(42);
        System.out.println("String identity: " + identity);
        System.out.println("Integer identity: " + intIdentity);
        
        // Min method with bounds
        String minString = GenericMethods.min("apple", "banana");
        Integer minInt = GenericMethods.min(10, 5);
        System.out.println("Min string: " + minString);
        System.out.println("Min integer: " + minInt);
        
        // Swap pairs
        Pair<String, Integer> original = new Pair<>("hello", 42);
        Pair<Integer, String> swapped = GenericMethods.swap(original);
        System.out.println("Original pair: " + original);
        System.out.println("Swapped pair: " + swapped);
        
        // Swap array elements
        String[] array = {"first", "second", "third"};
        System.out.println("Before swap: " + Arrays.toString(array));
        GenericMethods.swap(array, 0, 2);
        System.out.println("After swap: " + Arrays.toString(array));
        
        // Varargs method
        List<String> fruits = GenericMethods.listOf("apple", "banana", "cherry");
        List<Integer> numbers = GenericMethods.listOf(1, 2, 3, 4, 5);
        System.out.println("Fruit list: " + fruits);
        System.out.println("Number list: " + numbers);
        
        System.out.println();
    }
    
    private static void demonstrateGenericInterfaces() {
        System.out.println("--- Generic Interfaces ---");
        
        Repository<String, Long> userRepo = new UserRepository();
        
        // Use the repository
        userRepo.save("Alice");
        userRepo.save("Bob");
        userRepo.save("Charlie");
        
        System.out.println("All users: " + userRepo.findAll());
        System.out.println("User with ID 2: " + userRepo.findById(2L));
        System.out.println("User 1 exists: " + userRepo.existsById(1L));
        
        userRepo.deleteById(2L);
        System.out.println("After deletion: " + userRepo.findAll());
        
        System.out.println();
    }
    
    private static void demonstrateRecursiveBounds() {
        System.out.println("--- Recursive Type Bounds ---");
        
        Employee emp1 = new Employee("Alice", 50000);
        Employee emp2 = new Employee("Bob", 60000);
        Employee emp3 = new Employee("Alice", 55000);
        
        List<Employee> employees = Arrays.asList(emp1, emp2, emp3);
        Collections.sort(employees); // Works because Employee is Comparable
        
        System.out.println("Sorted employees:");
        for (Employee emp : employees) {
            System.out.println(emp.name + ": $" + emp.getSalary());
        }
        
        System.out.println();
    }
    
    private static void demonstrateGenericBuilder() {
        System.out.println("--- Generic Builder Pattern ---");
        
        // Fluent query building with type safety
        Query<Employee> query = Query.from(Employee.class)
            .where("salary > ?", 40000)
            .orderBy("name")
            .limit(10);
        
        List<Employee> results = query.execute();
        System.out.println("Query returned " + results.size() + " results");
        
        System.out.println();
    }
    
    private static void demonstrateTypeErasure() {
        System.out.println("--- Type Erasure ---");
        
        TypeErasureDemo demo = new TypeErasureDemo();
        
        List<String> strings = Arrays.asList("hello", "world");
        List<Integer> integers = Arrays.asList(1, 2, 3);
        
        demo.processStrings(strings);
        demo.processIntegers(integers);
        
        // Demonstrate runtime type equality
        TypeErasureDemo.demonstrateErasure();
        
        // Type checking with wildcards
        System.out.println("Is List: " + TypeErasureDemo.checkType(strings));
        System.out.println("Is List: " + TypeErasureDemo.checkType("not a list"));
        
        System.out.println();
    }
}

/* 
 * ========================================
 * KEY SYNTAX PATTERNS TO REMEMBER:
 * ========================================
 * 
 * 1. Generic Class Declaration:
 *    class ClassName<T> { ... }
 *    class ClassName<T, U> { ... }
 * 
 * 2. Bounded Type Parameters:
 *    class ClassName<T extends SomeClass> { ... }
 *    class ClassName<T extends Class1 & Interface1 & Interface2> { ... }
 * 
 * 3. Generic Method Declaration:
 *    public <T> ReturnType methodName(T parameter) { ... }
 *    public static <T> T methodName(T param) { ... }
 * 
 * 4. Wildcards:
 *    List<? extends Number> - upper bounded
 *    List<? super Integer> - lower bounded  
 *    List<?> - unbounded
 * 
 * 5. Generic Interface:
 *    interface InterfaceName<T> { ... }
 *    class Implementation implements InterfaceName<ConcreteType> { ... }
 * 
 * 6. Type Inference:
 *    List<String> list = new ArrayList<>(); // Diamond operator
 *    Method.<String>staticMethod(); // Explicit
 *    Method.staticMethod(); // Inferred
 * 
 * Remember: Generics provide compile-time type safety and eliminate
 * the need for casting. They exist only at compile time - at runtime,
 * all generic type information is erased for backward compatibility.
 * 
 * The key principle: Write once, use with any type, maintain type safety!
 */
