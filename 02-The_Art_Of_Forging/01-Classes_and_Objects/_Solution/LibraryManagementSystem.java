/**
 * 📚 LIBRARY MANAGEMENT SYSTEM - THE MASTER'S SOLUTION
 * 
 * This is a complete implementation of the Library Management System challenge,
 * demonstrating advanced Object-Oriented Programming concepts with Classes and Objects.
 * 
 * Key Concepts Demonstrated:
 * - Class design that models real-world entities
 * - Object state management and behavior implementation
 * - Object interaction and collaboration
 * - Error handling and validation
 * - Array-based object management
 * - Search and statistical operations
 * 
 * Study this solution to understand how expert-level OOP code is structured,
 * but remember - there are many correct ways to solve the challenge!
 */

// ═══════════════════════════════════════════════════════════════════════════════
// 📖 BOOK CLASS - THE BLUEPRINT FOR ALL LIBRARY BOOKS
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Book class represents a single book in the library system.
 * Each Book object maintains its own state and provides methods
 * for library operations like checkout and return.
 */
class Book {
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🎯 OBJECT STATE - What a Book IS (Fields/Attributes)
    // ═══════════════════════════════════════════════════════════════════════
    
    String title;                // The name of the book
    String author;               // Who wrote the book
    String isbn;                 // Unique identifier (International Standard Book Number)
    String genre;                // Category (Fiction, Non-Fiction, Science, etc.)
    int publicationYear;         // When it was published
    int numberOfPages;           // How long the book is
    boolean isAvailable;         // Whether it's available for checkout
    String currentBorrower;      // Who has it (null if available)
    int timesCheckedOut;         // How many times this book has been borrowed
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🚀 OBJECT BEHAVIOR - What a Book CAN DO (Methods)
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Initialize a new book with all its information
     * This method sets up the book when it's first created
     */
    void initializeBook(String bookTitle, String bookAuthor, String bookIsbn, 
                       String bookGenre, int year, int pages) {
        title = bookTitle;
        author = bookAuthor;
        isbn = bookIsbn;
        genre = bookGenre;
        publicationYear = year;
        numberOfPages = pages;
        isAvailable = true;          // New books start as available
        currentBorrower = null;      // No one has it initially
        timesCheckedOut = 0;         // Start with zero checkouts
        
        System.out.println("📚 New book added to library: \"" + title + "\" by " + author);
    }
    
    /**
     * Display complete book information in a formatted way
     * Shows all the book's current state
     */
    void displayBookInfo() {
        System.out.println("📖 ═══════════════════════════════════════");
        System.out.println("   BOOK INFORMATION");
        System.out.println("   ═══════════════════════════════════════");
        System.out.println("   Title: " + title);
        System.out.println("   Author: " + author);
        System.out.println("   ISBN: " + isbn);
        System.out.println("   Genre: " + genre);
        System.out.println("   Publication Year: " + publicationYear);
        System.out.println("   Pages: " + numberOfPages);
        System.out.println("   Status: " + (isAvailable ? "Available" : "Checked Out"));
        
        if (!isAvailable) {
            System.out.println("   Current Borrower: " + currentBorrower);
        }
        
        System.out.println("   Times Checked Out: " + timesCheckedOut);
        System.out.println("   Est. Reading Time: " + calculateReadingTime() + " minutes");
        System.out.println("   ═══════════════════════════════════════");
    }
    
    /**
     * Check out this book to a borrower
     * Changes the book's status and records the borrower
     */
    boolean checkOut(String borrowerName) {
        // Validation: Check if book is available
        if (!isAvailable) {
            System.out.println("❌ Cannot check out \"" + title + "\" - already checked out to " + currentBorrower);
            return false;
        }
        
        // Validation: Check if borrower name is valid
        if (borrowerName == null || borrowerName.trim().isEmpty()) {
            System.out.println("❌ Invalid borrower name for book \"" + title + "\"");
            return false;
        }
        
        // Perform the checkout
        isAvailable = false;
        currentBorrower = borrowerName;
        timesCheckedOut++;
        
        System.out.println("✅ Book checked out successfully!");
        System.out.println("   Book: \"" + title + "\" by " + author);
        System.out.println("   Borrower: " + borrowerName);
        System.out.println("   This book has been checked out " + timesCheckedOut + " times");
        
        return true;
    }
    
    /**
     * Return this book to the library
     * Changes the book's status back to available
     */
    boolean returnBook() {
        // Validation: Check if book is actually checked out
        if (isAvailable) {
            System.out.println("❌ Cannot return \"" + title + "\" - it's already available");
            return false;
        }
        
        String returningBorrower = currentBorrower;
        
        // Perform the return
        isAvailable = true;
        currentBorrower = null;
        
        System.out.println("✅ Book returned successfully!");
        System.out.println("   Book: \"" + title + "\" by " + author);
        System.out.println("   Returned by: " + returningBorrower);
        System.out.println("   Status: Now available for checkout");
        
        return true;
    }
    
    /**
     * Check if this book is available for checkout
     * Simple getter method for availability status
     */
    boolean isBookAvailable() {
        return isAvailable;
    }
    
    /**
     * Calculate estimated reading time based on number of pages
     * Assumes average reading speed of 1 page per minute
     */
    int calculateReadingTime() {
        return numberOfPages; // 1 minute per page assumption
    }
    
    /**
     * Get a brief summary of the book for lists and searches
     */
    String getBookSummary() {
        return "\"" + title + "\" by " + author + " (" + genre + ", " + publicationYear + ")";
    }
    
    /**
     * Check if this book matches a search term
     * Searches in title, author, and genre (case-insensitive)
     */
    boolean matchesSearch(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return false;
        }
        
        String search = searchTerm.toLowerCase();
        
        return title.toLowerCase().contains(search) ||
               author.toLowerCase().contains(search) ||
               genre.toLowerCase().contains(search);
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 👤 LIBRARYMEMBER CLASS - THE BLUEPRINT FOR ALL LIBRARY MEMBERS
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * LibraryMember class represents a single member of the library.
 * Each member can borrow books, return books, and has different
 * privileges based on their membership type.
 */
class LibraryMember {
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🎯 OBJECT STATE - What a LibraryMember IS (Fields/Attributes)
    // ═══════════════════════════════════════════════════════════════════════
    
    String memberId;             // Unique identifier for this member
    String name;                 // Member's full name
    String email;                // Contact information
    String phoneNumber;          // Contact information
    String membershipType;       // "Student", "Faculty", "Public", "Premium"
    int booksBorrowed;           // Current number of books borrowed
    int totalBooksBorrowed;      // Lifetime count of books borrowed
    int memberSince;             // Year they joined the library
    String[] currentlyBorrowedBooks; // Titles of books currently borrowed
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🚀 OBJECT BEHAVIOR - What a LibraryMember CAN DO (Methods)
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Initialize a new library member with all their information
     */
    void initializeMember(String id, String memberName, String memberEmail, 
                         String phone, String type, int yearJoined) {
        memberId = id;
        name = memberName;
        email = memberEmail;
        phoneNumber = phone;
        membershipType = type;
        memberSince = yearJoined;
        booksBorrowed = 0;
        totalBooksBorrowed = 0;
        
        // Initialize array to hold borrowed book titles
        currentlyBorrowedBooks = new String[getBorrowingLimit()];
        
        System.out.println("👤 New " + membershipType + " member registered: " + name);
        System.out.println("   Member ID: " + memberId);
        System.out.println("   Borrowing Limit: " + getBorrowingLimit() + " books");
    }
    
    /**
     * Display complete member information including borrowed books
     */
    void displayMemberInfo() {
        System.out.println("👤 ═══════════════════════════════════════");
        System.out.println("   MEMBER INFORMATION");
        System.out.println("   ═══════════════════════════════════════");
        System.out.println("   Member ID: " + memberId);
        System.out.println("   Name: " + name);
        System.out.println("   Email: " + email);
        System.out.println("   Phone: " + phoneNumber);
        System.out.println("   Membership Type: " + membershipType);
        System.out.println("   Member Since: " + memberSince);
        System.out.println("   Membership Duration: " + calculateMembershipDuration() + " years");
        System.out.println("   Books Currently Borrowed: " + booksBorrowed + "/" + getBorrowingLimit());
        System.out.println("   Total Books Borrowed: " + totalBooksBorrowed);
        
        if (booksBorrowed > 0) {
            System.out.println("   Currently Borrowed Books:");
            for (int i = 0; i < currentlyBorrowedBooks.length; i++) {
                if (currentlyBorrowedBooks[i] != null) {
                    System.out.println("     • " + currentlyBorrowedBooks[i]);
                }
            }
        }
        
        System.out.println("   ═══════════════════════════════════════");
    }
    
    /**
     * Attempt to borrow a book (adds to member's borrowed list)
     * Returns true if successful, false if at limit or other error
     */
    boolean borrowBook(String bookTitle) {
        // Validation: Check if member has reached borrowing limit
        if (booksBorrowed >= getBorrowingLimit()) {
            System.out.println("❌ Cannot borrow \"" + bookTitle + "\" - " + name + 
                             " has reached borrowing limit (" + getBorrowingLimit() + " books)");
            return false;
        }
        
        // Validation: Check if book title is valid
        if (bookTitle == null || bookTitle.trim().isEmpty()) {
            System.out.println("❌ Invalid book title for member " + name);
            return false;
        }
        
        // Find empty slot in borrowed books array
        for (int i = 0; i < currentlyBorrowedBooks.length; i++) {
            if (currentlyBorrowedBooks[i] == null) {
                currentlyBorrowedBooks[i] = bookTitle;
                booksBorrowed++;
                totalBooksBorrowed++;
                
                System.out.println("✅ Book borrowed successfully!");
                System.out.println("   Member: " + name + " (" + memberId + ")");
                System.out.println("   Book: \"" + bookTitle + "\"");
                System.out.println("   Books borrowed: " + booksBorrowed + "/" + getBorrowingLimit());
                
                return true;
            }
        }
        
        // This shouldn't happen if our logic is correct, but just in case
        System.out.println("❌ Error: Could not add book to borrowed list for " + name);
        return false;
    }
    
    /**
     * Return a book (removes from member's borrowed list)
     * Returns true if successful, false if book wasn't borrowed
     */
    boolean returnBook(String bookTitle) {
        // Validation: Check if book title is valid
        if (bookTitle == null || bookTitle.trim().isEmpty()) {
            System.out.println("❌ Invalid book title for return by " + name);
            return false;
        }
        
        // Find the book in borrowed books array
        for (int i = 0; i < currentlyBorrowedBooks.length; i++) {
            if (currentlyBorrowedBooks[i] != null && currentlyBorrowedBooks[i].equals(bookTitle)) {
                currentlyBorrowedBooks[i] = null;
                booksBorrowed--;
                
                System.out.println("✅ Book returned successfully!");
                System.out.println("   Member: " + name + " (" + memberId + ")");
                System.out.println("   Book: \"" + bookTitle + "\"");
                System.out.println("   Books borrowed: " + booksBorrowed + "/" + getBorrowingLimit());
                
                return true;
            }
        }
        
        // Book not found in borrowed list
        System.out.println("❌ Cannot return \"" + bookTitle + "\" - " + name + " hasn't borrowed this book");
        return false;
    }
    
    /**
     * Get the borrowing limit based on membership type
     * Different membership types have different privileges
     */
    int getBorrowingLimit() {
        switch (membershipType) {
            case "Student":
                return 3;
            case "Faculty":
                return 10;
            case "Public":
                return 5;
            case "Premium":
                return 15;
            default:
                return 3; // Default to student limit for unknown types
        }
    }
    
    /**
     * Calculate how long this member has been with the library
     */
    int calculateMembershipDuration() {
        return 2024 - memberSince; // Assuming current year is 2024
    }
    
    /**
     * Check if this member can borrow more books
     */
    boolean canBorrowMore() {
        return booksBorrowed < getBorrowingLimit();
    }
    
    /**
     * Get a brief summary of the member for reports
     */
    String getMemberSummary() {
        return name + " (" + memberId + ") - " + membershipType + 
               " - " + booksBorrowed + "/" + getBorrowingLimit() + " books";
    }
    
    /**
     * Check if this member has borrowed a specific book
     */
    boolean hasBorrowedBook(String bookTitle) {
        for (String borrowedBook : currentlyBorrowedBooks) {
            if (borrowedBook != null && borrowedBook.equals(bookTitle)) {
                return true;
            }
        }
        return false;
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🏛️ LIBRARY MANAGEMENT SYSTEM - THE MAIN CLASS DEMONSTRATING EVERYTHING
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Main class that demonstrates the complete Library Management System
 * Shows how Book and LibraryMember objects interact to create a working system
 */
public class LibraryManagementSystem {
    
    public static void main(String[] args) {
        
        System.out.println("📚 ═══════════════════════════════════════════════════════");
        System.out.println("   WELCOME TO THE OBJECT-ORIENTED LIBRARY MANAGEMENT SYSTEM");
        System.out.println("   ═══════════════════════════════════════════════════════");
        System.out.println("   Demonstrating Classes and Objects in Action!");
        System.out.println("   ═══════════════════════════════════════════════════════");
        
        // ═══════════════════════════════════════════════════════════════════════
        // 📚 CREATING THE LIBRARY'S BOOK COLLECTION
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n📚 BUILDING THE LIBRARY'S BOOK COLLECTION...\n");
        
        // Create an array to hold our book objects
        Book[] libraryBooks = new Book[8];
        
        // Create individual book objects with diverse information
        libraryBooks[0] = new Book();
        libraryBooks[0].initializeBook("To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4", 
                                      "Fiction", 1960, 376);
        
        libraryBooks[1] = new Book();
        libraryBooks[1].initializeBook("The Art of Computer Programming", "Donald Knuth", "978-0-201-89683-1", 
                                      "Computer Science", 1968, 650);
        
        libraryBooks[2] = new Book();
        libraryBooks[2].initializeBook("A Brief History of Time", "Stephen Hawking", "978-0-553-38016-3", 
                                      "Science", 1988, 256);
        
        libraryBooks[3] = new Book();
        libraryBooks[3].initializeBook("1984", "George Orwell", "978-0-452-28423-4", 
                                      "Dystopian Fiction", 1949, 328);
        
        libraryBooks[4] = new Book();
        libraryBooks[4].initializeBook("Clean Code", "Robert C. Martin", "978-0-13-235088-4", 
                                      "Programming", 2008, 464);
        
        libraryBooks[5] = new Book();
        libraryBooks[5].initializeBook("The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5", 
                                      "Classic Literature", 1925, 180);
        
        libraryBooks[6] = new Book();
        libraryBooks[6].initializeBook("Introduction to Algorithms", "Thomas Cormen", "978-0-262-03384-8", 
                                      "Computer Science", 2009, 1312);
        
        libraryBooks[7] = new Book();
        libraryBooks[7].initializeBook("Pride and Prejudice", "Jane Austen", "978-0-14-143951-8", 
                                      "Romance", 1813, 432);
        
        // ═══════════════════════════════════════════════════════════════════════
        // 👥 CREATING THE LIBRARY'S MEMBER COMMUNITY
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n👥 REGISTERING LIBRARY MEMBERS...\n");
        
        // Create an array to hold our member objects
        LibraryMember[] libraryMembers = new LibraryMember[5];
        
        // Create individual member objects with different membership types
        libraryMembers[0] = new LibraryMember();
        libraryMembers[0].initializeMember("STU001", "Alice Johnson", "alice.johnson@university.edu", 
                                          "555-0101", "Student", 2022);
        System.out.println();
        
        libraryMembers[1] = new LibraryMember();
        libraryMembers[1].initializeMember("FAC001", "Dr. Robert Smith", "r.smith@university.edu", 
                                          "555-0102", "Faculty", 2018);
        System.out.println();
        
        libraryMembers[2] = new LibraryMember();
        libraryMembers[2].initializeMember("PUB001", "Maria Garcia", "maria.garcia@email.com", 
                                          "555-0103", "Public", 2020);
        System.out.println();
        
        libraryMembers[3] = new LibraryMember();
        libraryMembers[3].initializeMember("PRE001", "David Chen", "david.chen@email.com", 
                                          "555-0104", "Premium", 2019);
        System.out.println();
        
        libraryMembers[4] = new LibraryMember();
        libraryMembers[4].initializeMember("STU002", "Emma Wilson", "emma.wilson@university.edu", 
                                          "555-0105", "Student", 2023);
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🔄 DEMONSTRATING BASIC LIBRARY OPERATIONS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🔄 DEMONSTRATING LIBRARY OPERATIONS...\n");
        
        // Alice (Student) checks out a fiction book
        System.out.println("--- Alice checking out a book ---");
        if (libraryBooks[0].checkOut(libraryMembers[0].name)) {
            libraryMembers[0].borrowBook(libraryBooks[0].title);
        }
        System.out.println();
        
        // Dr. Smith (Faculty) checks out computer science books
        System.out.println("--- Dr. Smith checking out books ---");
        if (libraryBooks[1].checkOut(libraryMembers[1].name)) {
            libraryMembers[1].borrowBook(libraryBooks[1].title);
        }
        if (libraryBooks[4].checkOut(libraryMembers[1].name)) {
            libraryMembers[1].borrowBook(libraryBooks[4].title);
        }
        if (libraryBooks[6].checkOut(libraryMembers[1].name)) {
            libraryMembers[1].borrowBook(libraryBooks[6].title);
        }
        System.out.println();
        
        // Maria (Public) checks out science and classic books
        System.out.println("--- Maria checking out books ---");
        if (libraryBooks[2].checkOut(libraryMembers[2].name)) {
            libraryMembers[2].borrowBook(libraryBooks[2].title);
        }
        if (libraryBooks[5].checkOut(libraryMembers[2].name)) {
            libraryMembers[2].borrowBook(libraryBooks[5].title);
        }
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 📊 DISPLAYING CURRENT LIBRARY STATUS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("📊 CURRENT LIBRARY STATUS...\n");
        
        // Display all member information
        System.out.println("=== MEMBER INFORMATION ===");
        for (LibraryMember member : libraryMembers) {
            member.displayMemberInfo();
            System.out.println();
        }
        
        // Display some book information
        System.out.println("=== SAMPLE BOOK INFORMATION ===");
        libraryBooks[0].displayBookInfo(); // Checked out book
        System.out.println();
        libraryBooks[3].displayBookInfo(); // Available book
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // ❌ DEMONSTRATING ERROR HANDLING
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("❌ TESTING ERROR HANDLING...\n");
        
        // Try to check out an already checked out book
        System.out.println("--- Attempting to check out unavailable book ---");
        libraryBooks[0].checkOut(libraryMembers[3].name); // Should fail
        System.out.println();
        
        // Try to exceed borrowing limit
        System.out.println("--- Student attempting to exceed borrowing limit ---");
        libraryMembers[0].borrowBook("Extra Book 1"); // Alice already has 1, limit is 3
        libraryMembers[0].borrowBook("Extra Book 2"); // This should work (2/3)
        libraryMembers[0].borrowBook("Extra Book 3"); // This should work (3/3)
        libraryMembers[0].borrowBook("Extra Book 4"); // This should fail (over limit)
        System.out.println();
        
        // Try to return a book that wasn't borrowed
        System.out.println("--- Attempting to return unborrowed book ---");
        libraryMembers[4].returnBook("Random Book Title"); // Emma hasn't borrowed anything
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🔄 DEMONSTRATING BOOK RETURNS AND RE-CIRCULATION
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🔄 DEMONSTRATING RETURNS AND RE-CIRCULATION...\n");
        
        // Alice returns her book
        System.out.println("--- Alice returning a book ---");
        if (libraryMembers[0].returnBook(libraryBooks[0].title)) {
            libraryBooks[0].returnBook();
        }
        System.out.println();
        
        // Now Emma can check out the returned book
        System.out.println("--- Emma checking out the returned book ---");
        if (libraryBooks[0].checkOut(libraryMembers[4].name)) {
            libraryMembers[4].borrowBook(libraryBooks[0].title);
        }
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🔍 DEMONSTRATING SEARCH AND MANAGEMENT OPERATIONS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🔍 LIBRARY SEARCH AND MANAGEMENT...\n");
        
        // Search for books by author
        System.out.println("--- Searching for books by author 'Martin' ---");
        searchBooksByAuthor(libraryBooks, "Martin");
        System.out.println();
        
        // Search for available books
        System.out.println("--- Available books for checkout ---");
        displayAvailableBooks(libraryBooks);
        System.out.println();
        
        // Find most active member
        System.out.println("--- Most active member ---");
        LibraryMember mostActive = findMostActiveLibraryMember(libraryMembers);
        if (mostActive != null) {
            System.out.println("Most active member: " + mostActive.getMemberSummary());
        }
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 📈 LIBRARY STATISTICS AND REPORTS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("📈 LIBRARY STATISTICS REPORT...\n");
        generateLibraryStatistics(libraryBooks, libraryMembers);
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🎯 FINAL DEMONSTRATION - THE POWER OF OBJECTS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n🎯 THE POWER OF OBJECT-ORIENTED PROGRAMMING:\n");
        
        System.out.println("✅ Each Book object maintains its own state independently");
        System.out.println("✅ Each LibraryMember object has its own borrowing history");
        System.out.println("✅ Objects interact naturally (members borrow books)");
        System.out.println("✅ Same methods work with different data (polymorphic behavior)");
        System.out.println("✅ Easy to add new books and members without changing existing code");
        System.out.println("✅ Code mirrors real-world library operations");
        System.out.println("✅ Error handling is built into each object's methods");
        System.out.println("✅ Complex systems emerge from simple object interactions");
        
        System.out.println("\n📚 Library Management System Demo Complete!");
        System.out.println("   This demonstrates the elegant power of Classes and Objects!");
    }
    
    // ═══════════════════════════════════════════════════════════════════════════════
    // 🛠️ UTILITY METHODS FOR LIBRARY MANAGEMENT
    // ═══════════════════════════════════════════════════════════════════════════════
    
    /**
     * Search for books by author name (case-insensitive)
     */
    public static void searchBooksByAuthor(Book[] books, String authorName) {
        boolean found = false;
        
        for (Book book : books) {
            if (book.author.toLowerCase().contains(authorName.toLowerCase())) {
                System.out.println("📖 Found: " + book.getBookSummary() + 
                                 " [" + (book.isBookAvailable() ? "Available" : "Checked Out") + "]");
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("📖 No books found by author containing '" + authorName + "'");
        }
    }
    
    /**
     * Display all available books for checkout
     */
    public static void displayAvailableBooks(Book[] books) {
        int availableCount = 0;
        
        System.out.println("📚 Available Books for Checkout:");
        System.out.println("   ═══════════════════════════════════════");
        
        for (Book book : books) {
            if (book.isBookAvailable()) {
                System.out.println("   • " + book.getBookSummary());
                availableCount++;
            }
        }
        
        System.out.println("   ═══════════════════════════════════════");
        System.out.println("   Total Available: " + availableCount + "/" + books.length + " books");
    }
    
    /**
     * Find the library member who has borrowed the most books
     */
    public static LibraryMember findMostActiveLibraryMember(LibraryMember[] members) {
        LibraryMember mostActive = null;
        int highestTotal = 0;
        
        for (LibraryMember member : members) {
            if (member.totalBooksBorrowed > highestTotal) {
                highestTotal = member.totalBooksBorrowed;
                mostActive = member;
            }
        }
        
        return mostActive;
    }
    
    /**
     * Generate comprehensive library statistics
     */
    public static void generateLibraryStatistics(Book[] books, LibraryMember[] members) {
        // Book statistics
        int totalBooks = books.length;
        int availableBooks = 0;
        int checkedOutBooks = 0;
        int totalPages = 0;
        int totalCheckouts = 0;
        
        for (Book book : books) {
            if (book.isBookAvailable()) {
                availableBooks++;
            } else {
                checkedOutBooks++;
            }
            totalPages += book.numberOfPages;
            totalCheckouts += book.timesCheckedOut;
        }
        
        // Member statistics
        int totalMembers = members.length;
        int activeMembers = 0; // Members with borrowed books
        int totalBooksCurrentlyBorrowed = 0;
        int totalLifetimeBorrows = 0;
        
        for (LibraryMember member : members) {
            if (member.booksBorrowed > 0) {
                activeMembers++;
            }
            totalBooksCurrentlyBorrowed += member.booksBorrowed;
            totalLifetimeBorrows += member.totalBooksBorrowed;
        }
        
        // Display statistics
        System.out.println("📊 ═══════════════════════════════════════");
        System.out.println("   COMPREHENSIVE LIBRARY STATISTICS");
        System.out.println("   ═══════════════════════════════════════");
        System.out.println("   📚 BOOK COLLECTION:");
        System.out.println("     Total Books: " + totalBooks);
        System.out.println("     Available: " + availableBooks);
        System.out.println("     Checked Out: " + checkedOutBooks);
        System.out.println("     Total Pages: " + totalPages);
        System.out.println("     Average Pages per Book: " + (totalPages / totalBooks));
        System.out.println("     Total Checkouts (All Time): " + totalCheckouts);
        System.out.println();
        System.out.println("   👥 MEMBERSHIP:");
        System.out.println("     Total Members: " + totalMembers);
        System.out.println("     Active Members (with books): " + activeMembers);
        System.out.println("     Books Currently Borrowed: " + totalBooksCurrentlyBorrowed);
        System.out.println("     Lifetime Borrows: " + totalLifetimeBorrows);
        System.out.println("     Average Borrows per Member: " + 
                         (totalLifetimeBorrows / totalMembers));
        System.out.println();
        System.out.println("   📈 UTILIZATION:");
        System.out.println("     Collection Utilization: " + 
                         ((checkedOutBooks * 100) / totalBooks) + "%");
        System.out.println("     Member Activity Rate: " + 
                         ((activeMembers * 100) / totalMembers) + "%");
        System.out.println("   ═══════════════════════════════════════");
    }
}

/*
 * 🎓 MASTER'S ANALYSIS - KEY LEARNING OUTCOMES:
 * 
 * 1. CLASS DESIGN EXCELLENCE:
 *    ✅ Book class models real-world books with appropriate state and behavior
 *    ✅ LibraryMember class models real-world library members
 *    ✅ Clear separation of concerns - each class has focused responsibility
 *    ✅ Proper encapsulation - related data and methods stay together
 * 
 * 2. OBJECT INTERACTION MASTERY:
 *    ✅ Objects communicate through method calls (checkOut/borrowBook)
 *    ✅ State changes in one object can trigger actions in another
 *    ✅ Complex workflows emerge from simple object interactions
 *    ✅ Objects maintain their independence while collaborating
 * 
 * 3. REAL-WORLD MODELING:
 *    ✅ Code structure mirrors actual library operations
 *    ✅ Business rules implemented naturally (borrowing limits, availability)
 *    ✅ Error handling reflects real-world constraints
 *    ✅ Extensible design allows for future enhancements
 * 
 * 4. ADVANCED OOP CONCEPTS DEMONSTRATED:
 *    ✅ Arrays of objects for managing collections
 *    ✅ Object search and filtering algorithms
 *    ✅ Statistical analysis across multiple objects
 *    ✅ Method parameters and return values with objects
 *    ✅ Null handling and validation
 * 
 * 5. PROFESSIONAL CODE QUALITY:
 *    ✅ Comprehensive error handling and validation
 *    ✅ Clear, descriptive method and variable names
 *    ✅ Extensive comments explaining complex logic
 *    ✅ Modular design with reusable utility methods
 *    ✅ Consistent formatting and organization
 * 
 * This solution demonstrates that Object-Oriented Programming is not just
 * a programming technique - it's a way of modeling and understanding the
 * world through the lens of interacting entities with state and behavior.
 * 
 * The elegance of OOP lies in how naturally it maps to real-world concepts,
 * making code more intuitive, maintainable, and extensible.
 */
