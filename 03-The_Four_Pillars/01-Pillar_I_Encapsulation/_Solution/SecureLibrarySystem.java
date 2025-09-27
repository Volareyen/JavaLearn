/**
 * THE PATH REVEALED: Secure Library Management System
 * 
 * A masterful demonstration of Encapsulation - The First Pillar of OOP Wisdom.
 * This solution showcases complete mastery of data protection, controlled access,
 * validation, security, and intelligent boundaries through a comprehensive
 * library management system.
 * 
 * This system demonstrates:
 * - Private data protection with intelligent access control
 * - Comprehensive input validation and business rule enforcement
 * - Multi-level security with state-based access restrictions
 * - Defensive programming against data corruption
 * - Write-only audit logging and read-only computed properties
 * - Cross-object validation and referential integrity
 * 
 * Created by: The Seeker (Master of the Sacred Vault)
 */

public class SecureLibrarySystem {
    
    public static void main(String[] args) {
        System.out.println("=== SECURE LIBRARY MANAGEMENT SYSTEM ===");
        System.out.println("Demonstrating Encapsulation - The First Pillar of OOP Wisdom\n");
        
        // DEMONSTRATION 1: Object Creation and Validation
        System.out.println("1. OBJECT CREATION AND VALIDATION:");
        
        // Create library system
        LibrarySystem library = new LibrarySystem("Central City Library");
        
        // Create books with validation
        Book book1 = new Book("9780134685991", "Effective Java", "Joshua Bloch", "NON_FICTION", 2017, 45.99);
        Book book2 = new Book("9780596009205", "Head First Design Patterns", "Eric Freeman", "NON_FICTION", 2004, 39.99);
        Book book3 = new Book("9780132350884", "Clean Code", "Robert Martin", "NON_FICTION", 2008, 42.99);
        
        // Attempt invalid book creation (validation should catch)
        System.out.println("Attempting to create invalid book:");
        Book invalidBook = new Book("invalid-isbn", "", "Unknown", "INVALID_GENRE", 2030, -10.0);
        System.out.println("Invalid book creation handled gracefully (ISBN: " + invalidBook.getIsbn() + ")");
        
        // Create library members with validation
        LibraryMember student = new LibraryMember("Alice Johnson", "alice@university.edu", "555-0123", 20, "STUDENT");
        LibraryMember faculty = new LibraryMember("Dr. Bob Wilson", "bob.wilson@university.edu", "555-0456", 45, "FACULTY");
        LibraryMember publicMember = new LibraryMember("Charlie Brown", "charlie@email.com", "555-0789", 30, "PUBLIC");
        
        // Display created objects
        book1.displayBookInfo();
        student.displayMemberInfo();
        
        // DEMONSTRATION 2: Library System Integration
        System.out.println("2. LIBRARY SYSTEM INTEGRATION:");
        
        // Add books to library catalog
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        
        // Register members
        library.registerMember(student);
        library.registerMember(faculty);
        library.registerMember(publicMember);
        
        library.displaySystemStatus();
        
        // DEMONSTRATION 3: Controlled Access and Business Logic
        System.out.println("3. CONTROLLED ACCESS AND BUSINESS LOGIC:");
        
        // Valid borrowing operations
        library.processTransaction(student, book1, "BORROW");
        library.processTransaction(faculty, book2, "BORROW");
        
        // Test borrowing limits
        library.processTransaction(student, book3, "BORROW");  // Should work (2/3 limit)
        
        // Create additional books to test student limit
        Book book4 = new Book("9781234567890", "Test Book 1", "Test Author", "FICTION", 2020, 25.99);
        Book book5 = new Book("9781234567891", "Test Book 2", "Test Author", "FICTION", 2021, 28.99);
        library.addBook(book4);
        library.addBook(book5);
        
        library.processTransaction(student, book4, "BORROW");  // Should work (3/3 limit)
        library.processTransaction(student, book5, "BORROW");  // Should be blocked (over limit)
        
        // Display updated status
        student.displayMemberInfo();
        
        // DEMONSTRATION 4: Security and Access Control
        System.out.println("4. SECURITY AND ACCESS CONTROL:");
        
        // Suspend a member and try operations
        student.suspendMember("Testing suspension");
        library.processTransaction(student, book2, "BORROW");  // Should be blocked
        
        // Reactivate member
        student.reactivateMember();
        
        // Lock a book for maintenance
        book1.lockForMaintenance();
        library.processTransaction(faculty, book1, "BORROW");  // Should be blocked
        book1.unlockFromMaintenance();
        
        // DEMONSTRATION 5: Fee Management and Validation
        System.out.println("5. FEE MANAGEMENT AND VALIDATION:");
        
        // Add fees to member
        publicMember.addOutstandingFees(25.50, "Late return fee");
        publicMember.displayMemberInfo();
        
        // Try borrowing with fees (should work, under limit)
        library.processTransaction(publicMember, book3, "BORROW");
        
        // Add more fees to exceed limit
        publicMember.addOutstandingFees(35.00, "Damage fee");
        library.processTransaction(publicMember, book1, "BORROW");  // Should be blocked
        
        // Pay fees
        publicMember.payFees(40.00);
        library.processTransaction(publicMember, book1, "BORROW");  // Should work now
        
        // DEMONSTRATION 6: Return Operations and History
        System.out.println("6. RETURN OPERATIONS AND HISTORY:");
        
        // Return books
        library.processTransaction(student, book1, "RETURN");
        library.processTransaction(faculty, book2, "RETURN");
        
        // Display borrowing history
        book1.displayBorrowingHistory();
        book2.displayBorrowingHistory();
        
        // DEMONSTRATION 7: Search and Reporting
        System.out.println("7. SEARCH AND REPORTING:");
        
        // Search functionality
        Book foundBook = library.findBookByIsbn("9780134685991");
        if (foundBook != null) {
            System.out.println("Found book: " + foundBook.getTitle());
        }
        
        Book[] javaBooks = library.searchBooksByTitle("Java");
        System.out.println("Books with 'Java' in title: " + javaBooks.length);
        
        // Display available books
        Book[] availableBooks = library.getAvailableBooks();
        System.out.println("Available books count: " + availableBooks.length);
        
        // DEMONSTRATION 8: Advanced Features
        System.out.println("8. ADVANCED FEATURES:");
        
        // Renewal system
        library.processTransaction(publicMember, book3, "RENEW");
        
        // System maintenance mode
        library.enterMaintenanceMode();
        library.processTransaction(student, book1, "BORROW");  // Should be blocked
        library.exitMaintenanceMode();
        
        // Generate comprehensive report
        library.generateSystemReport();
        
        // DEMONSTRATION 9: Data Integrity and Defensive Programming
        System.out.println("9. DATA INTEGRITY AND DEFENSIVE PROGRAMMING:");
        
        // Test defensive copying
        Book[] catalogCopy = library.getAllBooks();
        System.out.println("Retrieved catalog copy with " + catalogCopy.length + " books");
        
        // Attempt to modify copy (should not affect original)
        if (catalogCopy.length > 0) {
            catalogCopy[0] = null;  // This should not affect library's internal catalog
        }
        
        // Verify original catalog is intact
        Book[] originalCatalog = library.getAllBooks();
        System.out.println("Original catalog still has " + originalCatalog.length + " books");
        
        // DEMONSTRATION 10: Audit Trail and Logging
        System.out.println("10. AUDIT TRAIL AND LOGGING:");
        
        // Display system audit log
        library.displayAuditLog();
        
        System.out.println("=== ENCAPSULATION MASTERY DEMONSTRATION COMPLETE ===");
        System.out.println("All data protected, access controlled, validation enforced!");
        System.out.println("The Sacred Vault has been successfully implemented!");
    }
}

/**
 * BOOK CLASS: Demonstrates comprehensive encapsulation of book data
 */
class Book {
    // PRIVATE FIELDS: The protected treasures
    private String isbn;                // Read-only after creation
    private String title;               // Validated, non-empty
    private String author;              // Validated, non-empty
    private String genre;               // Validated from allowed genres
    private int publicationYear;        // Validated range
    private double price;               // Validated, positive
    private boolean isAvailable;        // Availability status
    private boolean isLocked;           // Maintenance lock
    private int borrowCount;            // Read-only, auto-incremented
    private String borrowHistory;       // Write-only audit log
    private String currentBorrower;     // Current borrower ID (if any)
    
    // VALID GENRES: Controlled vocabulary
    private static final String[] VALID_GENRES = {
        "FICTION", "NON_FICTION", "SCIENCE", "HISTORY", "BIOGRAPHY", 
        "FANTASY", "MYSTERY", "ROMANCE", "TECHNICAL", "EDUCATIONAL"
    };
    
    // CONSTRUCTOR: Comprehensive validation and initialization
    public Book(String isbn, String title, String author, String genre, int publicationYear, double price) {
        // Validate and set ISBN (read-only after creation)
        if (isValidIsbn(isbn)) {
            this.isbn = isbn;
        } else {
            this.isbn = "INVALID-" + System.currentTimeMillis();
            System.out.println("Invalid ISBN provided: " + isbn + ". Generated temporary: " + this.isbn);
        }
        
        // Validate and set title
        setTitle(title);
        
        // Validate and set author
        setAuthor(author);
        
        // Validate and set genre
        setGenre(genre);
        
        // Validate and set publication year
        setPublicationYear(publicationYear);
        
        // Validate and set price
        setPrice(price);
        
        // Initialize other fields
        this.isAvailable = true;
        this.isLocked = false;
        this.borrowCount = 0;
        this.borrowHistory = "";
        this.currentBorrower = null;
        
        // Log creation
        addBorrowHistoryEntry("Book created: " + title + " by " + author);
    }
    
    // GETTER METHODS: Controlled read access
    
    public String getIsbn() {
        return this.isbn;  // Read-only property
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public String getGenre() {
        return this.genre;
    }
    
    public int getPublicationYear() {
        return this.publicationYear;
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public boolean isAvailable() {
        return this.isAvailable && !this.isLocked;  // Available and not locked
    }
    
    public boolean isLocked() {
        return this.isLocked;
    }
    
    public int getBorrowCount() {
        return this.borrowCount;  // Read-only property
    }
    
    // COMPUTED PROPERTIES: Calculated on demand
    
    public int getAgeInYears() {
        return java.time.Year.now().getValue() - this.publicationYear;
    }
    
    public String getAvailabilityStatus() {
        if (isLocked) {
            return "LOCKED FOR MAINTENANCE";
        } else if (!isAvailable) {
            return "BORROWED BY: " + (currentBorrower != null ? currentBorrower : "UNKNOWN");
        } else {
            return "AVAILABLE";
        }
    }
    
    public String getPriceCategory() {
        if (price < 20.0) return "BUDGET";
        else if (price < 50.0) return "STANDARD";
        else return "PREMIUM";
    }
    
    // SETTER METHODS: Controlled write access with validation
    
    public void setTitle(String title) {
        if (isValidTitle(title)) {
            this.title = title.trim();
        } else {
            this.title = "UNTITLED";
            System.out.println("Invalid title provided. Set to: UNTITLED");
        }
    }
    
    public void setAuthor(String author) {
        if (isValidAuthor(author)) {
            this.author = author.trim();
        } else {
            this.author = "UNKNOWN AUTHOR";
            System.out.println("Invalid author provided. Set to: UNKNOWN AUTHOR");
        }
    }
    
    public void setGenre(String genre) {
        if (isValidGenre(genre)) {
            this.genre = genre.toUpperCase().trim();
        } else {
            this.genre = "GENERAL";
            System.out.println("Invalid genre: " + genre + ". Set to: GENERAL");
        }
    }
    
    public void setPublicationYear(int publicationYear) {
        int currentYear = java.time.Year.now().getValue();
        if (publicationYear >= 1450 && publicationYear <= currentYear) {
            this.publicationYear = publicationYear;
        } else {
            this.publicationYear = currentYear;
            System.out.println("Invalid publication year: " + publicationYear + ". Set to: " + currentYear);
        }
    }
    
    public void setPrice(double price) {
        if (price >= 0.01 && price <= 999.99) {
            this.price = price;
        } else {
            this.price = 29.99;  // Default reasonable price
            System.out.println("Invalid price: $" + price + ". Set to: $29.99");
        }
    }
    
    // BORROWING OPERATIONS: Controlled state management
    
    public boolean borrowBook(String borrowerId) {
        if (isLocked) {
            System.out.println("Cannot borrow - book is locked for maintenance");
            return false;
        }
        
        if (!isAvailable) {
            System.out.println("Cannot borrow - book is already borrowed");
            return false;
        }
        
        if (borrowerId == null || borrowerId.trim().isEmpty()) {
            System.out.println("Cannot borrow - invalid borrower ID");
            return false;
        }
        
        // Execute borrowing
        this.isAvailable = false;
        this.currentBorrower = borrowerId;
        this.borrowCount++;
        addBorrowHistoryEntry("Borrowed by: " + borrowerId + " (Borrow #" + borrowCount + ")");
        
        System.out.println("Book '" + title + "' borrowed by " + borrowerId);
        return true;
    }
    
    public boolean returnBook(String borrowerId) {
        if (isLocked) {
            System.out.println("Cannot return - book is locked for maintenance");
            return false;
        }
        
        if (isAvailable) {
            System.out.println("Cannot return - book is not currently borrowed");
            return false;
        }
        
        if (!borrowerId.equals(currentBorrower)) {
            System.out.println("Cannot return - book was borrowed by different member");
            return false;
        }
        
        // Execute return
        this.isAvailable = true;
        addBorrowHistoryEntry("Returned by: " + borrowerId);
        this.currentBorrower = null;
        
        System.out.println("Book '" + title + "' returned by " + borrowerId);
        return true;
    }
    
    // MAINTENANCE OPERATIONS: Security and access control
    
    public void lockForMaintenance() {
        this.isLocked = true;
        addBorrowHistoryEntry("Book locked for maintenance");
        System.out.println("Book '" + title + "' locked for maintenance");
    }
    
    public void unlockFromMaintenance() {
        this.isLocked = false;
        addBorrowHistoryEntry("Book unlocked from maintenance");
        System.out.println("Book '" + title + "' unlocked from maintenance");
    }
    
    // AUDIT AND LOGGING: Write-only history tracking
    
    public void addBorrowHistoryEntry(String entry) {
        String timestamp = java.time.LocalDateTime.now().toString();
        this.borrowHistory += timestamp + " - " + entry + "\n";
    }
    
    public void displayBorrowingHistory() {
        System.out.println("=== Borrowing History for '" + title + "' ===");
        if (borrowHistory.isEmpty()) {
            System.out.println("No borrowing history available");
        } else {
            String[] entries = borrowHistory.split("\n");
            for (int i = Math.max(0, entries.length - 10); i < entries.length; i++) {
                if (!entries[i].isEmpty()) {
                    System.out.println("  " + entries[i]);
                }
            }
        }
        System.out.println();
    }
    
    // DISPLAY METHODS: Information presentation
    
    public void displayBookInfo() {
        System.out.println("=== Book Information ===");
        System.out.println("ISBN: " + isbn);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Published: " + publicationYear + " (" + getAgeInYears() + " years old)");
        System.out.println("Price: $" + String.format("%.2f", price) + " (" + getPriceCategory() + ")");
        System.out.println("Status: " + getAvailabilityStatus());
        System.out.println("Times Borrowed: " + borrowCount);
        System.out.println();
    }
    
    // PRIVATE VALIDATION METHODS: Implementation hiding
    
    private boolean isValidIsbn(String isbn) {
        if (isbn == null) return false;
        String cleanIsbn = isbn.replaceAll("[^0-9]", "");
        return cleanIsbn.length() == 10 || cleanIsbn.length() == 13;
    }
    
    private boolean isValidTitle(String title) {
        return title != null && !title.trim().isEmpty() && title.trim().length() >= 1;
    }
    
    private boolean isValidAuthor(String author) {
        return author != null && !author.trim().isEmpty() && author.trim().length() >= 2;
    }
    
    private boolean isValidGenre(String genre) {
        if (genre == null) return false;
        String upperGenre = genre.toUpperCase().trim();
        for (String validGenre : VALID_GENRES) {
            if (validGenre.equals(upperGenre)) {
                return true;
            }
        }
        return false;
    }
}

/**
 * LIBRARY MEMBER CLASS: Demonstrates member data encapsulation and access control
 */
class LibraryMember {
    // PRIVATE FIELDS: Protected member information
    private String memberId;            // Read-only after creation
    private String name;                // Validated name
    private String email;               // Validated email format
    private String phoneNumber;         // Validated phone format
    private int age;                    // Validated age range
    private String membershipType;      // STUDENT, FACULTY, PUBLIC
    private boolean isActive;           // Membership status
    private boolean isSuspended;        // Suspension status
    private int borrowedBooksCount;     // Current borrowed books
    private double outstandingFees;     // Fees owed
    private String activityLog;         // Write-only activity history
    
    // MEMBERSHIP CONSTANTS
    private static final String[] VALID_MEMBERSHIP_TYPES = {"STUDENT", "FACULTY", "PUBLIC"};
    private static final double MAX_OUTSTANDING_FEES = 50.0;
    
    // CONSTRUCTOR: Comprehensive member creation with validation
    public LibraryMember(String name, String email, String phoneNumber, int age, String membershipType) {
        // Generate unique member ID
        this.memberId = generateMemberId();
        
        // Validate and set all fields
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setAge(age);
        setMembershipType(membershipType);
        
        // Initialize status fields
        this.isActive = true;
        this.isSuspended = false;
        this.borrowedBooksCount = 0;
        this.outstandingFees = 0.0;
        this.activityLog = "";
        
        // Log creation
        addActivityEntry("Member account created: " + name + " (" + membershipType + ")");
    }
    
    // GETTER METHODS: Controlled read access
    
    public String getMemberId() {
        return this.memberId;  // Read-only property
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public int getAge() {
        return this.age;
    }
    
    public String getMembershipType() {
        return this.membershipType;
    }
    
    public boolean isActive() {
        return this.isActive && !this.isSuspended;
    }
    
    public boolean isSuspended() {
        return this.isSuspended;
    }
    
    public int getBorrowedBooksCount() {
        return this.borrowedBooksCount;
    }
    
    public double getOutstandingFees() {
        return this.outstandingFees;
    }
    
    // COMPUTED PROPERTIES: Calculated based on membership type and status
    
    public int getMaxBooksAllowed() {
        switch (membershipType) {
            case "STUDENT": return 3;
            case "FACULTY": return 10;
            case "PUBLIC": return 5;
            default: return 1;
        }
    }
    
    public int getRemainingBorrowLimit() {
        return getMaxBooksAllowed() - borrowedBooksCount;
    }
    
    public boolean canBorrowBooks() {
        return isActive() && 
               borrowedBooksCount < getMaxBooksAllowed() && 
               outstandingFees <= MAX_OUTSTANDING_FEES;
    }
    
    public String getMembershipStatus() {
        if (isSuspended) return "SUSPENDED";
        else if (!isActive) return "INACTIVE";
        else if (outstandingFees > MAX_OUTSTANDING_FEES) return "FEES_OVERDUE";
        else return "ACTIVE";
    }
    
    // SETTER METHODS: Controlled write access with validation
    
    public void setName(String name) {
        if (isValidName(name)) {
            this.name = name.trim();
        } else {
            this.name = "UNNAMED MEMBER";
            System.out.println("Invalid name provided. Set to: UNNAMED MEMBER");
        }
    }
    
    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email.toLowerCase().trim();
            addActivityEntry("Email updated to: " + this.email);
        } else {
            System.out.println("Invalid email format: " + email);
        }
    }
    
    public void setPhoneNumber(String phoneNumber) {
        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber.trim();
            addActivityEntry("Phone number updated");
        } else {
            System.out.println("Invalid phone number format: " + phoneNumber);
        }
    }
    
    public void setAge(int age) {
        if (age >= 5 && age <= 120) {
            this.age = age;
        } else {
            this.age = 18;  // Default adult age
            System.out.println("Invalid age: " + age + ". Set to: 18");
        }
    }
    
    public void setMembershipType(String membershipType) {
        if (isValidMembershipType(membershipType)) {
            this.membershipType = membershipType.toUpperCase().trim();
        } else {
            this.membershipType = "PUBLIC";
            System.out.println("Invalid membership type: " + membershipType + ". Set to: PUBLIC");
        }
    }
    
    // BORROWING OPERATIONS: Controlled book management
    
    public boolean borrowBook() {
        if (!canBorrowBooks()) {
            String reason = "";
            if (isSuspended) reason = "Member is suspended";
            else if (!isActive) reason = "Member is inactive";
            else if (borrowedBooksCount >= getMaxBooksAllowed()) reason = "Borrow limit exceeded";
            else if (outstandingFees > MAX_OUTSTANDING_FEES) reason = "Outstanding fees exceed limit";
            
            System.out.println("Cannot borrow book: " + reason);
            addActivityEntry("Borrow attempt denied: " + reason);
            return false;
        }
        
        this.borrowedBooksCount++;
        addActivityEntry("Book borrowed (Total: " + borrowedBooksCount + "/" + getMaxBooksAllowed() + ")");
        return true;
    }
    
    public boolean returnBook() {
        if (borrowedBooksCount <= 0) {
            System.out.println("No books to return");
            return false;
        }
        
        this.borrowedBooksCount--;
        addActivityEntry("Book returned (Remaining: " + borrowedBooksCount + "/" + getMaxBooksAllowed() + ")");
        return true;
    }
    
    // FEE MANAGEMENT: Controlled financial operations
    
    public void addOutstandingFees(double amount, String reason) {
        if (amount > 0) {
            this.outstandingFees += amount;
            addActivityEntry("Fee added: $" + amount + " (" + reason + ") - Total: $" + outstandingFees);
            System.out.println("Fee of $" + amount + " added to " + name + "'s account");
        }
    }
    
    public boolean payFees(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment amount: $" + amount);
            return false;
        }
        
        if (amount > outstandingFees) {
            System.out.println("Payment amount ($" + amount + ") exceeds outstanding fees ($" + outstandingFees + ")");
            return false;
        }
        
        this.outstandingFees -= amount;
        addActivityEntry("Payment received: $" + amount + " - Remaining balance: $" + outstandingFees);
        System.out.println("Payment of $" + amount + " received from " + name);
        return true;
    }
    
    // ACCOUNT MANAGEMENT: Security and access control
    
    public void suspendMember(String reason) {
        this.isSuspended = true;
        addActivityEntry("Member suspended: " + reason);
        System.out.println("Member " + name + " suspended: " + reason);
    }
    
    public void reactivateMember() {
        this.isSuspended = false;
        addActivityEntry("Member reactivated");
        System.out.println("Member " + name + " reactivated");
    }
    
    public void deactivateMember() {
        this.isActive = false;
        addActivityEntry("Member account deactivated");
        System.out.println("Member " + name + " deactivated");
    }
    
    // AUDIT AND LOGGING: Write-only activity tracking
    
    public void addActivityEntry(String activity) {
        String timestamp = java.time.LocalDateTime.now().toString();
        this.activityLog += timestamp + " - " + activity + "\n";
    }
    
    // Internal method to get activity count for reporting
    private int getActivityCount() {
        return activityLog.isEmpty() ? 0 : activityLog.split("\n").length;
    }
    
    // DISPLAY METHODS: Information presentation
    
    public void displayMemberInfo() {
        System.out.println("=== Member Information ===");
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Age: " + age);
        System.out.println("Membership: " + membershipType);
        System.out.println("Status: " + getMembershipStatus());
        System.out.println("Books Borrowed: " + borrowedBooksCount + "/" + getMaxBooksAllowed());
        System.out.println("Outstanding Fees: $" + String.format("%.2f", outstandingFees));
        System.out.println("Can Borrow: " + (canBorrowBooks() ? "YES" : "NO"));
        System.out.println("Activity Entries: " + getActivityCount());
        System.out.println();
    }
    
    // PRIVATE VALIDATION METHODS: Implementation hiding
    
    private String generateMemberId() {
        return "MBR-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
    }
    
    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.trim().length() >= 2;
    }
    
    private boolean isValidEmail(String email) {
        if (email == null) return false;
        email = email.trim();
        return email.contains("@") && 
               email.contains(".") &&
               email.indexOf("@") < email.lastIndexOf(".") &&
               email.length() > 5;
    }
    
    private boolean isValidPhoneNumber(String phone) {
        if (phone == null) return false;
        String cleanPhone = phone.replaceAll("[^0-9]", "");
        return cleanPhone.length() >= 10 && cleanPhone.length() <= 15;
    }
    
    private boolean isValidMembershipType(String type) {
        if (type == null) return false;
        String upperType = type.toUpperCase().trim();
        for (String validType : VALID_MEMBERSHIP_TYPES) {
            if (validType.equals(upperType)) {
                return true;
            }
        }
        return false;
    }
}

/**
 * LIBRARY SYSTEM CLASS: Demonstrates system-level encapsulation and management
 */
class LibrarySystem {
    // PRIVATE FIELDS: Protected system data
    private String libraryName;
    private Book[] bookCatalog;
    private LibraryMember[] members;
    private int bookCount;
    private int memberCount;
    private int totalTransactions;
    private boolean isSystemLocked;
    private String systemLog;
    
    // SYSTEM CONSTANTS
    private static final int MAX_BOOKS = 1000;
    private static final int MAX_MEMBERS = 500;
    
    // CONSTRUCTOR: System initialization
    public LibrarySystem(String libraryName) {
        this.libraryName = libraryName != null ? libraryName : "Unnamed Library";
        this.bookCatalog = new Book[MAX_BOOKS];
        this.members = new LibraryMember[MAX_MEMBERS];
        this.bookCount = 0;
        this.memberCount = 0;
        this.totalTransactions = 0;
        this.isSystemLocked = false;
        this.systemLog = "";
        
        addSystemLogEntry("Library system initialized: " + this.libraryName);
    }
    
    // GETTER METHODS: Controlled system information access
    
    public String getLibraryName() {
        return this.libraryName;
    }
    
    public int getTotalBooks() {
        return this.bookCount;
    }
    
    public int getTotalMembers() {
        return this.memberCount;
    }
    
    public int getTotalTransactions() {
        return this.totalTransactions;  // Read-only property
    }
    
    public boolean isSystemLocked() {
        return this.isSystemLocked;
    }
    
    // COMPUTED PROPERTIES: System statistics
    
    public int getAvailableBooksCount() {
        int available = 0;
        for (int i = 0; i < bookCount; i++) {
            if (bookCatalog[i].isAvailable()) {
                available++;
            }
        }
        return available;
    }
    
    public int getActiveMembersCount() {
        int active = 0;
        for (int i = 0; i < memberCount; i++) {
            if (members[i].isActive()) {
                active++;
            }
        }
        return active;
    }
    
    // BOOK MANAGEMENT: Controlled catalog operations
    
    public boolean addBook(Book book) {
        if (isSystemLocked) {
            System.out.println("System locked - cannot add books");
            return false;
        }
        
        if (book == null) {
            System.out.println("Cannot add null book");
            return false;
        }
        
        if (bookCount >= MAX_BOOKS) {
            System.out.println("Book catalog is full");
            return false;
        }
        
        // Check for duplicate ISBN
        if (findBookByIsbn(book.getIsbn()) != null) {
            System.out.println("Book with ISBN " + book.getIsbn() + " already exists");
            return false;
        }
        
        bookCatalog[bookCount] = book;
        bookCount++;
        addSystemLogEntry("Book added: " + book.getTitle() + " (ISBN: " + book.getIsbn() + ")");
        System.out.println("Book added to catalog: " + book.getTitle());
        return true;
    }
    
    public Book findBookByIsbn(String isbn) {
        if (isbn == null) return null;
        
        for (int i = 0; i < bookCount; i++) {
            if (bookCatalog[i].getIsbn().equals(isbn)) {
                return bookCatalog[i];
            }
        }
        return null;
    }
    
    public Book[] searchBooksByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return new Book[0];
        }
        
        // Count matches first
        int matchCount = 0;
        String searchTerm = title.toLowerCase().trim();
        
        for (int i = 0; i < bookCount; i++) {
            if (bookCatalog[i].getTitle().toLowerCase().contains(searchTerm)) {
                matchCount++;
            }
        }
        
        // Create result array and populate
        Book[] results = new Book[matchCount];
        int resultIndex = 0;
        
        for (int i = 0; i < bookCount && resultIndex < matchCount; i++) {
            if (bookCatalog[i].getTitle().toLowerCase().contains(searchTerm)) {
                results[resultIndex] = bookCatalog[i];
                resultIndex++;
            }
        }
        
        return results;
    }
    
    public Book[] getAvailableBooks() {
        // Count available books first
        int availableCount = getAvailableBooksCount();
        
        // Create defensive copy of available books
        Book[] availableBooks = new Book[availableCount];
        int index = 0;
        
        for (int i = 0; i < bookCount && index < availableCount; i++) {
            if (bookCatalog[i].isAvailable()) {
                availableBooks[index] = bookCatalog[i];
                index++;
            }
        }
        
        return availableBooks;
    }
    
    public Book[] getAllBooks() {
        // DEFENSIVE COPYING: Return copy, not original array
        Book[] copy = new Book[bookCount];
        System.arraycopy(bookCatalog, 0, copy, 0, bookCount);
        return copy;
    }
    
    // MEMBER MANAGEMENT: Controlled member operations
    
    public boolean registerMember(LibraryMember member) {
        if (isSystemLocked) {
            System.out.println("System locked - cannot register members");
            return false;
        }
        
        if (member == null) {
            System.out.println("Cannot register null member");
            return false;
        }
        
        if (memberCount >= MAX_MEMBERS) {
            System.out.println("Member database is full");
            return false;
        }
        
        // Check for duplicate member ID (shouldn't happen with good generation)
        if (findMemberById(member.getMemberId()) != null) {
            System.out.println("Member with ID " + member.getMemberId() + " already exists");
            return false;
        }
        
        members[memberCount] = member;
        memberCount++;
        addSystemLogEntry("Member registered: " + member.getName() + " (ID: " + member.getMemberId() + ")");
        System.out.println("Member registered: " + member.getName());
        return true;
    }
    
    public LibraryMember findMemberById(String memberId) {
        if (memberId == null) return null;
        
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getMemberId().equals(memberId)) {
                return members[i];
            }
        }
        return null;
    }
    
    // TRANSACTION PROCESSING: Core business logic with validation
    
    public boolean processTransaction(LibraryMember member, Book book, String operation) {
        if (isSystemLocked) {
            System.out.println("System is locked - transaction denied");
            return false;
        }
        
        if (member == null || book == null || operation == null) {
            System.out.println("Invalid transaction parameters");
            return false;
        }
        
        boolean success = false;
        String transactionLog = member.getName() + " - " + book.getTitle() + " - " + operation;
        
        switch (operation.toUpperCase()) {
            case "BORROW":
                if (member.canBorrowBooks() && book.isAvailable()) {
                    if (member.borrowBook() && book.borrowBook(member.getMemberId())) {
                        success = true;
                        totalTransactions++;
                    } else {
                        // Rollback if partial failure
                        member.returnBook();
                    }
                } else {
                    System.out.println("Borrow operation failed - member or book not eligible");
                }
                break;
                
            case "RETURN":
                if (book.returnBook(member.getMemberId()) && member.returnBook()) {
                    success = true;
                    totalTransactions++;
                } else {
                    System.out.println("Return operation failed");
                }
                break;
                
            case "RENEW":
                if (!book.isAvailable() && member.canBorrowBooks()) {
                    // Simple renewal - just log it
                    book.addBorrowHistoryEntry("Renewed by: " + member.getMemberId());
                    member.addActivityEntry("Book renewed: " + book.getTitle());
                    success = true;
                    totalTransactions++;
                    System.out.println("Book renewed: " + book.getTitle());
                } else {
                    System.out.println("Renewal failed - book not borrowed by member or member not eligible");
                }
                break;
                
            default:
                System.out.println("Unknown operation: " + operation);
                break;
        }
        
        // Log transaction
        addSystemLogEntry(transactionLog + " - " + (success ? "SUCCESS" : "FAILED"));
        
        return success;
    }
    
    // SYSTEM MANAGEMENT: Administrative operations
    
    public void enterMaintenanceMode() {
        this.isSystemLocked = true;
        addSystemLogEntry("System entered maintenance mode");
        System.out.println("Library system is now in maintenance mode");
    }
    
    public void exitMaintenanceMode() {
        this.isSystemLocked = false;
        addSystemLogEntry("System exited maintenance mode");
        System.out.println("Library system is now operational");
    }
    
    // REPORTING: System information and statistics
    
    public void displaySystemStatus() {
        System.out.println("=== " + libraryName + " System Status ===");
        System.out.println("Total Books: " + bookCount + "/" + MAX_BOOKS);
        System.out.println("Available Books: " + getAvailableBooksCount());
        System.out.println("Total Members: " + memberCount + "/" + MAX_MEMBERS);
        System.out.println("Active Members: " + getActiveMembersCount());
        System.out.println("Total Transactions: " + totalTransactions);
        System.out.println("System Status: " + (isSystemLocked ? "MAINTENANCE MODE" : "OPERATIONAL"));
        System.out.println();
    }
    
    public void displayAllMembers() {
        System.out.println("=== Registered Members ===");
        for (int i = 0; i < memberCount; i++) {
            LibraryMember member = members[i];
            System.out.println((i + 1) + ". " + member.getName() + 
                             " (" + member.getMemberId() + ") - " + 
                             member.getMembershipType() + " - " + 
                             member.getMembershipStatus());
        }
        System.out.println();
    }
    
    public void generateSystemReport() {
        System.out.println("=== COMPREHENSIVE SYSTEM REPORT ===");
        System.out.println("Library: " + libraryName);
        System.out.println("Report Generated: " + java.time.LocalDateTime.now());
        System.out.println();
        
        System.out.println("CATALOG STATISTICS:");
        System.out.println("  Total Books: " + bookCount);
        System.out.println("  Available Books: " + getAvailableBooksCount());
        System.out.println("  Borrowed Books: " + (bookCount - getAvailableBooksCount()));
        System.out.println();
        
        System.out.println("MEMBER STATISTICS:");
        System.out.println("  Total Members: " + memberCount);
        System.out.println("  Active Members: " + getActiveMembersCount());
        System.out.println("  Suspended Members: " + (memberCount - getActiveMembersCount()));
        System.out.println();
        
        System.out.println("TRANSACTION STATISTICS:");
        System.out.println("  Total Transactions: " + totalTransactions);
        System.out.println("  System Status: " + (isSystemLocked ? "MAINTENANCE" : "OPERATIONAL"));
        System.out.println();
        
        // Genre distribution
        System.out.println("GENRE DISTRIBUTION:");
        String[] genres = {"FICTION", "NON_FICTION", "SCIENCE", "HISTORY", "BIOGRAPHY", "FANTASY", "MYSTERY", "ROMANCE"};
        for (String genre : genres) {
            int count = 0;
            for (int i = 0; i < bookCount; i++) {
                if (bookCatalog[i].getGenre().equals(genre)) {
                    count++;
                }
            }
            if (count > 0) {
                System.out.println("  " + genre + ": " + count + " books");
            }
        }
        System.out.println();
    }
    
    // AUDIT AND LOGGING: System activity tracking
    
    private void addSystemLogEntry(String entry) {
        String timestamp = java.time.LocalDateTime.now().toString();
        this.systemLog += timestamp + " - " + entry + "\n";
    }
    
    public void displayAuditLog() {
        System.out.println("=== SYSTEM AUDIT LOG ===");
        if (systemLog.isEmpty()) {
            System.out.println("No system activities logged");
        } else {
            String[] entries = systemLog.split("\n");
            int startIndex = Math.max(0, entries.length - 15);  // Show last 15 entries
            
            System.out.println("Recent system activities:");
            for (int i = startIndex; i < entries.length; i++) {
                if (!entries[i].isEmpty()) {
                    System.out.println("  " + entries[i]);
                }
            }
        }
        System.out.println();
    }
}

/**
 * ENCAPSULATION MASTERY ACHIEVEMENT:
 * 
 * This comprehensive library system demonstrates complete mastery of
 * Encapsulation - The First Pillar of OOP Wisdom through:
 * 
 * 1. DATA PROTECTION:
 *    - All critical fields (ISBN, member ID, balance, etc.) are private
 *    - No direct external access to internal object state
 *    - Defensive copying prevents external modification of internal arrays
 * 
 * 2. CONTROLLED ACCESS:
 *    - Intelligent getter/setter patterns with comprehensive validation
 *    - Read-only properties (ISBN, member ID, transaction count)
 *    - Write-only properties (audit logs, activity histories)
 *    - Computed properties calculated on demand
 * 
 * 3. VALIDATION AND BUSINESS RULES:
 *    - All inputs validated at object boundaries
 *    - Complex business logic enforced (borrowing limits, fee thresholds)
 *    - Cross-object validation (member eligibility, book availability)
 *    - State consistency maintained through controlled operations
 * 
 * 4. SECURITY AND ACCESS CONTROL:
 *    - Multi-level security (book locking, member suspension, system maintenance)
 *    - State-based operation restrictions
 *    - Audit trails for all significant operations
 *    - Information hiding based on security context
 * 
 * 5. IMPLEMENTATION HIDING:
 *    - Private helper methods encapsulate complex logic
 *    - Internal algorithms hidden from external code
 *    - Object creation patterns abstracted through constructors
 *    - System internals protected through controlled interfaces
 * 
 * 6. DEFENSIVE PROGRAMMING:
 *    - All edge cases handled gracefully
 *    - Partial failure scenarios managed with rollback
 *    - Invalid inputs rejected with clear error messages
 *    - Object integrity maintained under all conditions
 * 
 * The result is a robust, secure, maintainable library management system
 * where every object is a self-protecting entity that:
 * - Guards its own data integrity
 * - Provides safe, controlled access to its capabilities
 * - Enforces business rules consistently
 * - Maintains audit trails for accountability
 * - Can evolve internally without breaking external dependencies
 * 
 * This demonstrates that Encapsulation is the foundation of reliable,
 * secure, and maintainable object-oriented systems - the Sacred Vault
 * that protects the treasures while serving those worthy of access.
 */
