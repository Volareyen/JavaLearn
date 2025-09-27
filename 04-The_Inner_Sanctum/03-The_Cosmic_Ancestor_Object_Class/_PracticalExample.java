/**
 * The Living Manuscript: Digital Library Catalog System
 * 
 * A comprehensive real-world demonstration of the Object class methods
 * through a digital library catalog system managing books, authors, and users.
 * 
 * This system showcases:
 * - Proper toString() implementation for debugging and display
 * - Correct equals() and hashCode() contracts for different object types
 * - Value objects vs Entity objects equality strategies
 * - Collection usage with custom objects
 * - Inheritance and polymorphism with Object methods
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

// ═══════════════════════════════════════════════════════════════════════════════════
// VALUE OBJECT - ISBN (Immutable identifier)
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * ISBN represents an immutable book identifier
 * Value object - equality based on the ISBN value itself
 */
final class ISBN {
    private final String value;
    
    public ISBN(String value) {
        if (value == null || !isValidISBN(value)) {
            throw new IllegalArgumentException("Invalid ISBN: " + value);
        }
        this.value = value.replaceAll("-", ""); // Normalize format
    }
    
    private boolean isValidISBN(String isbn) {
        // Simplified ISBN validation (real implementation would be more complex)
        return isbn != null && isbn.replaceAll("-", "").matches("\\d{10}|\\d{13}");
    }
    
    public String getValue() { return value; }
    
    /**
     * toString() for value object - show the actual value clearly
     */
    @Override
    public String toString() {
        // Format as standard ISBN with dashes for readability
        if (value.length() == 10) {
            return String.format("ISBN-%s-%s-%s-%s", 
                               value.substring(0, 1), value.substring(1, 4), 
                               value.substring(4, 9), value.substring(9, 10));
        } else {
            return String.format("ISBN-%s-%s-%s-%s-%s", 
                               value.substring(0, 3), value.substring(3, 4), 
                               value.substring(4, 6), value.substring(6, 12), 
                               value.substring(12, 13));
        }
    }
    
    /**
     * equals() for value object - two ISBNs are equal if values match
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ISBN isbn = (ISBN) obj;
        return Objects.equals(value, isbn.value);
    }
    
    /**
     * hashCode() for value object - based on the value
     */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// ENTITY OBJECT - Author (Mutable with identity)
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Author represents a person who writes books
 * Entity object - equality based on unique ID, not mutable properties
 */
class Author {
    private final Long id;  // Unique identifier (never changes)
    private String name;    // Can change (pen names, name changes)
    private String email;   // Can change
    private LocalDate birthDate;  // Usually doesn't change, but could be corrected
    
    // Static counter for generating IDs
    private static Long nextId = 1L;
    
    public Author(String name, String email, LocalDate birthDate) {
        this.id = nextId++;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }
    
    // Constructor for testing with specific ID
    public Author(Long id, String name, String email, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }
    
    /**
     * toString() for entity - include ID and key identifying information
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("Author{id=%d, name='%s', email='%s', birthDate=%s}", 
                           id, name, email, 
                           birthDate != null ? birthDate.format(formatter) : "unknown");
    }
    
    /**
     * equals() for entity - based ONLY on the unique ID
     * Two Author objects are equal if they have the same ID,
     * regardless of whether other properties differ
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Author author = (Author) obj;
        return Objects.equals(id, author.id);  // Only compare ID for entities!
    }
    
    /**
     * hashCode() for entity - based ONLY on the unique ID
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);  // Only hash the ID
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// VALUE OBJECT - BookCopy (Immutable book instance)
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * BookCopy represents a specific physical/digital copy of a book
 * Value object - equality based on all properties
 */
class BookCopy {
    private final ISBN isbn;
    private final String title;
    private final Author author;
    private final int publicationYear;
    private final String publisher;
    private final String genre;
    private final int pageCount;
    
    public BookCopy(ISBN isbn, String title, Author author, int publicationYear, 
                   String publisher, String genre, int pageCount) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.genre = genre;
        this.pageCount = pageCount;
    }
    
    /**
     * toString() for book copy - comprehensive information for catalog display
     */
    @Override
    public String toString() {
        return String.format(
            "BookCopy{isbn=%s, title='%s', author='%s', year=%d, publisher='%s', genre='%s', pages=%d}", 
            isbn, title, author.getName(), publicationYear, publisher, genre, pageCount);
    }
    
    /**
     * equals() for book copy - two copies are equal if all properties match
     * This makes sense for a catalog system where identical books should be deduplicated
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        BookCopy bookCopy = (BookCopy) obj;
        return publicationYear == bookCopy.publicationYear &&
               pageCount == bookCopy.pageCount &&
               Objects.equals(isbn, bookCopy.isbn) &&
               Objects.equals(title, bookCopy.title) &&
               Objects.equals(author, bookCopy.author) &&
               Objects.equals(publisher, bookCopy.publisher) &&
               Objects.equals(genre, bookCopy.genre);
    }
    
    /**
     * hashCode() for book copy - include all fields used in equals()
     */
    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, author, publicationYear, publisher, genre, pageCount);
    }
    
    // Getters
    public ISBN getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public Author getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public String getPublisher() { return publisher; }
    public String getGenre() { return genre; }
    public int getPageCount() { return pageCount; }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// ENTITY OBJECT - LibraryUser (Mutable with identity)
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * LibraryUser represents a person who uses the library
 * Entity object - equality based on unique ID
 */
class LibraryUser {
    private final String userId;    // Unique identifier (library card number)
    private String name;           // Can change
    private String email;          // Can change
    private String phoneNumber;    // Can change
    private String address;        // Can change
    private LocalDate memberSince; // Usually doesn't change
    private boolean active;        // Can change
    
    public LibraryUser(String userId, String name, String email, String phoneNumber, String address) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.memberSince = LocalDate.now();
        this.active = true;
    }
    
    /**
     * toString() for library user - show ID and key contact info
     */
    @Override
    public String toString() {
        return String.format("LibraryUser{userId='%s', name='%s', email='%s', active=%s, memberSince=%s}", 
                           userId, name, email, active, memberSince);
    }
    
    /**
     * equals() for entity - based ONLY on userId
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LibraryUser user = (LibraryUser) obj;
        return Objects.equals(userId, user.userId);
    }
    
    /**
     * hashCode() for entity - based ONLY on userId
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
    
    // Getters and setters
    public String getUserId() { return userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public LocalDate getMemberSince() { return memberSince; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// CATALOG SYSTEM - MANAGING THE COLLECTIONS
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * LibraryCatalog manages books, authors, and users
 * Demonstrates practical usage of objects with proper equals/hashCode
 */
class LibraryCatalog {
    // Collections that rely on proper equals() and hashCode() implementation
    private final Set<BookCopy> books;           // No duplicate books
    private final Set<Author> authors;           // No duplicate authors
    private final Map<String, LibraryUser> users; // Users by ID
    private final Map<ISBN, Set<BookCopy>> booksByISBN; // Books grouped by ISBN
    
    public LibraryCatalog() {
        this.books = new HashSet<>();
        this.authors = new HashSet<>();
        this.users = new HashMap<>();
        this.booksByISBN = new HashMap<>();
    }
    
    /**
     * Add a book to the catalog
     */
    public boolean addBook(BookCopy book) {
        boolean added = books.add(book);  // HashSet uses equals() to prevent duplicates
        
        if (added) {
            authors.add(book.getAuthor());  // Add author (HashSet prevents duplicates)
            
            // Group books by ISBN
            booksByISBN.computeIfAbsent(book.getIsbn(), k -> new HashSet<>()).add(book);
            
            System.out.println("✅ Added book: " + book.getTitle() + " by " + book.getAuthor().getName());
        } else {
            System.out.println("📚 Book already exists in catalog: " + book.getTitle());
        }
        
        return added;
    }
    
    /**
     * Add a user to the catalog
     */
    public void addUser(LibraryUser user) {
        LibraryUser existing = users.put(user.getUserId(), user);
        if (existing == null) {
            System.out.println("👤 Added user: " + user.getName() + " (ID: " + user.getUserId() + ")");
        } else {
            System.out.println("👤 Updated user: " + user.getName() + " (ID: " + user.getUserId() + ")");
        }
    }
    
    /**
     * Find books by author
     */
    public List<BookCopy> findBooksByAuthor(Author author) {
        return books.stream()
            .filter(book -> book.getAuthor().equals(author))  // Uses Author.equals()
            .sorted((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()))
            .toList();
    }
    
    /**
     * Find books by ISBN (all editions/versions)
     */
    public Set<BookCopy> findBooksByISBN(ISBN isbn) {
        return booksByISBN.getOrDefault(isbn, Collections.emptySet());
    }
    
    /**
     * Get catalog statistics
     */
    public void displayStatistics() {
        System.out.println("\n" + "═".repeat(50));
        System.out.println("📊 LIBRARY CATALOG STATISTICS");
        System.out.println("═".repeat(50));
        System.out.println("Total Books: " + books.size());
        System.out.println("Total Authors: " + authors.size());
        System.out.println("Total Users: " + users.size());
        System.out.println("Unique ISBNs: " + booksByISBN.size());
        
        // Genre distribution
        Map<String, Long> genreCount = books.stream()
            .collect(java.util.stream.Collectors.groupingBy(
                BookCopy::getGenre, 
                java.util.stream.Collectors.counting()));
        
        System.out.println("\nGenre Distribution:");
        genreCount.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .forEach(entry -> System.out.println("  " + entry.getKey() + ": " + entry.getValue()));
        System.out.println("═".repeat(50));
    }
    
    public Set<BookCopy> getAllBooks() { return new HashSet<>(books); }
    public Set<Author> getAllAuthors() { return new HashSet<>(authors); }
    public Collection<LibraryUser> getAllUsers() { return users.values(); }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// MAIN DEMONSTRATION CLASS
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Main demonstration of Object methods in a practical library system
 */
public class _PracticalExample {
    
    public static void main(String[] args) {
        System.out.println("📚 DIGITAL LIBRARY CATALOG SYSTEM DEMONSTRATION 📚\n");
        
        // ═══════════════════════════════════════════════════════════════
        // CREATE LIBRARY CATALOG
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("🏗️ INITIALIZING LIBRARY CATALOG SYSTEM...\n");
        
        LibraryCatalog catalog = new LibraryCatalog();
        
        // ═══════════════════════════════════════════════════════════════
        // CREATE AUTHORS (ENTITY OBJECTS)
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("👨‍💼 CREATING AUTHORS (Entity Objects):");
        
        Author tolkien = new Author("J.R.R. Tolkien", "tolkien@middleearth.com", 
                                  LocalDate.of(1892, 1, 3));
        Author asimov = new Author("Isaac Asimov", "asimov@foundation.sci", 
                                 LocalDate.of(1920, 1, 2));
        Author clarke = new Author("Arthur C. Clarke", "clarke@space.odyssey", 
                                 LocalDate.of(1917, 12, 16));
        
        System.out.println("Created: " + tolkien);
        System.out.println("Created: " + asimov);
        System.out.println("Created: " + clarke);
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATE AUTHOR EQUALITY (ENTITY BEHAVIOR)
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🔍 DEMONSTRATING ENTITY EQUALITY (Authors):");
        
        // Create another reference to same author (same ID)
        Author tolkienCopy = new Author(tolkien.getId(), "John Ronald Reuel Tolkien", 
                                      "tolkien.updated@shire.com", tolkien.getBirthDate());
        
        System.out.println("Original Tolkien: " + tolkien);
        System.out.println("Modified Tolkien: " + tolkienCopy);
        System.out.println("Are they equal? " + tolkien.equals(tolkienCopy) + " (same ID, different name/email)");
        System.out.println("Same hash code? " + (tolkien.hashCode() == tolkienCopy.hashCode()));
        
        // ═══════════════════════════════════════════════════════════════
        // CREATE ISBNS (VALUE OBJECTS)
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n📖 CREATING ISBNs (Value Objects):");
        
        ISBN hobbitISBN = new ISBN("978-0-547-92822-7");
        ISBN foundationISBN = new ISBN("978-0-553-29337-4");
        ISBN odysseyISBN = new ISBN("978-0-451-45799-1");
        
        System.out.println("Created: " + hobbitISBN);
        System.out.println("Created: " + foundationISBN);
        System.out.println("Created: " + odysseyISBN);
        
        // Demonstrate ISBN equality (value object behavior)
        ISBN hobbitISBNCopy = new ISBN("9780547928227");  // Same ISBN, different format
        System.out.println("\nDifferent format: " + hobbitISBNCopy);
        System.out.println("Are ISBNs equal? " + hobbitISBN.equals(hobbitISBNCopy) + " (same value, different format)");
        
        // ═══════════════════════════════════════════════════════════════
        // CREATE BOOKS (VALUE OBJECTS)
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n📚 CREATING BOOKS (Value Objects):");
        
        BookCopy hobbit = new BookCopy(hobbitISBN, "The Hobbit", tolkien, 1937, 
                                     "Houghton Mifflin", "Fantasy", 304);
        BookCopy foundation = new BookCopy(foundationISBN, "Foundation", asimov, 1951, 
                                         "Gnome Press", "Science Fiction", 244);
        BookCopy odyssey = new BookCopy(odysseyISBN, "2001: A Space Odyssey", clarke, 1968, 
                                      "New American Library", "Science Fiction", 221);
        
        System.out.println("Created: " + hobbit.getTitle() + " by " + hobbit.getAuthor().getName());
        System.out.println("Created: " + foundation.getTitle() + " by " + foundation.getAuthor().getName());
        System.out.println("Created: " + odyssey.getTitle() + " by " + odyssey.getAuthor().getName());
        
        // ═══════════════════════════════════════════════════════════════
        // ADD BOOKS TO CATALOG (DEMONSTRATES COLLECTION BEHAVIOR)
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n📋 ADDING BOOKS TO CATALOG:");
        
        catalog.addBook(hobbit);
        catalog.addBook(foundation);
        catalog.addBook(odyssey);
        
        // Try to add duplicate book (should be rejected by HashSet)
        BookCopy hobbitDuplicate = new BookCopy(hobbitISBN, "The Hobbit", tolkien, 1937, 
                                              "Houghton Mifflin", "Fantasy", 304);
        catalog.addBook(hobbitDuplicate);  // Should not be added
        
        // ═══════════════════════════════════════════════════════════════
        // CREATE USERS (ENTITY OBJECTS)
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n👥 CREATING LIBRARY USERS:");
        
        LibraryUser alice = new LibraryUser("LIB001", "Alice Johnson", "alice@email.com", 
                                          "555-0001", "123 Main St");
        LibraryUser bob = new LibraryUser("LIB002", "Bob Smith", "bob@email.com", 
                                        "555-0002", "456 Oak Ave");
        
        catalog.addUser(alice);
        catalog.addUser(bob);
        
        // Demonstrate user entity behavior
        LibraryUser aliceUpdated = new LibraryUser("LIB001", "Alice Johnson-Wilson", 
                                                 "alice.wilson@newemail.com", "555-9999", "789 Pine St");
        catalog.addUser(aliceUpdated);  // Should update existing user
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATE COLLECTION OPERATIONS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🔍 DEMONSTRATING COLLECTION OPERATIONS:");
        
        // Find books by author (uses Author.equals())
        List<BookCopy> tolkienBooks = catalog.findBooksByAuthor(tolkien);
        System.out.println("\nBooks by " + tolkien.getName() + ":");
        tolkienBooks.forEach(book -> System.out.println("  - " + book.getTitle()));
        
        // Find books by ISBN
        Set<BookCopy> hobbitEditions = catalog.findBooksByISBN(hobbitISBN);
        System.out.println("\nEditions of " + hobbitISBN + ":");
        hobbitEditions.forEach(book -> System.out.println("  - " + book));
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATE OBJECT POLYMORPHISM
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🎭 DEMONSTRATING OBJECT POLYMORPHISM:");
        
        // Store different types in Object array
        Object[] libraryObjects = {hobbit, tolkien, alice, hobbitISBN};
        
        System.out.println("Polymorphic toString() calls:");
        for (Object obj : libraryObjects) {
            System.out.println("  " + obj.getClass().getSimpleName() + ": " + obj);
        }
        
        // Use instanceof for type checking
        System.out.println("\nType checking with instanceof:");
        for (Object obj : libraryObjects) {
            if (obj instanceof BookCopy book) {
                System.out.println("  Book: " + book.getTitle() + " (" + book.getPageCount() + " pages)");
            } else if (obj instanceof Author author) {
                System.out.println("  Author: " + author.getName() + " (born " + author.getBirthDate() + ")");
            } else if (obj instanceof LibraryUser user) {
                System.out.println("  User: " + user.getName() + " (member since " + user.getMemberSince() + ")");
            } else if (obj instanceof ISBN isbn) {
                System.out.println("  ISBN: " + isbn.getValue());
            }
        }
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATE HASH COLLECTION BEHAVIOR
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🗂️ DEMONSTRATING HASH COLLECTION BEHAVIOR:");
        
        // Create a map with different object types as keys
        Map<Object, String> objectMap = new HashMap<>();
        objectMap.put(hobbit, "Classic fantasy novel");
        objectMap.put(tolkien, "Famous fantasy author");
        objectMap.put(hobbitISBN, "Book identifier");
        
        System.out.println("HashMap with different object types as keys:");
        objectMap.forEach((key, value) -> 
            System.out.println("  " + key.getClass().getSimpleName() + " -> " + value));
        
        // Test retrieval (depends on proper equals() and hashCode())
        System.out.println("\nTesting key retrieval:");
        System.out.println("  Can find hobbit book? " + (objectMap.get(hobbit) != null));
        System.out.println("  Can find tolkien author? " + (objectMap.get(tolkien) != null));
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATE MUTABLE OBJECT COLLECTION ISSUES
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n⚠️ DEMONSTRATING MUTABLE OBJECT COLLECTION ISSUES:");
        
        Set<Author> authorSet = new HashSet<>();
        Author mutableAuthor = new Author("Test Author", "test@email.com", LocalDate.now());
        authorSet.add(mutableAuthor);
        
        System.out.println("Author in set: " + authorSet.contains(mutableAuthor));
        
        // Modify the author (this changes properties but not ID, so equals/hashCode remain same)
        mutableAuthor.setEmail("newemail@test.com");
        System.out.println("After email change, author in set: " + authorSet.contains(mutableAuthor));
        System.out.println("Entity objects are safe to modify because equality is based on ID only");
        
        // ═══════════════════════════════════════════════════════════════
        // DISPLAY FINAL STATISTICS
        // ═══════════════════════════════════════════════════════════════
        
        catalog.displayStatistics();
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATE OBJECT CLASS METHODS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🌌 DEMONSTRATING OBJECT CLASS METHODS:");
        
        System.out.println("getClass() information:");
        System.out.println("  hobbit.getClass(): " + hobbit.getClass());
        System.out.println("  hobbit.getClass().getSuperclass(): " + hobbit.getClass().getSuperclass());
        System.out.println("  Everything extends Object: " + (Object.class.isAssignableFrom(hobbit.getClass())));
        
        // ═══════════════════════════════════════════════════════════════
        // FINAL SUMMARY
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n✨ DIGITAL LIBRARY CATALOG DEMONSTRATION COMPLETE! ✨");
        System.out.println("\n🎯 OBJECT CLASS METHODS DEMONSTRATED:");
        System.out.println("🔹 toString() provided meaningful representations for all objects");
        System.out.println("🔹 equals() implemented differently for value objects vs entities");
        System.out.println("🔹 hashCode() enabled proper collection behavior for all types");
        System.out.println("🔹 Value objects (ISBN, BookCopy) use content-based equality");
        System.out.println("🔹 Entity objects (Author, User) use ID-based equality");
        System.out.println("🔹 Collections work seamlessly with proper Object method implementation");
        System.out.println("🔹 Polymorphism allows treating different objects uniformly");
        System.out.println("🔹 instanceof enables safe type checking and casting");
        
        System.out.println("\n💡 The cosmic ancestor Object class unifies all objects under one divine contract!");
        System.out.println("🏆 Proper implementation of Object methods enables collection harmony!");
    }
}
