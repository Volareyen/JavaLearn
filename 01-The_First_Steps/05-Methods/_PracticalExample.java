/**
 * Library Management System
 * 
 * A practical example demonstrating all aspects of methods in Java
 * through a comprehensive library management system that handles
 * books, members, transactions, and reporting.
 * 
 * This program shows how methods enable code organization, reusability,
 * and maintainability in real-world applications.
 */

import java.util.Scanner;

public class _PracticalExample {
    
    // Class-level data structures (simplified for demonstration)
    private static String[] bookTitles = new String[100];
    private static String[] bookAuthors = new String[100];
    private static String[] bookISBNs = new String[100];
    private static boolean[] bookAvailability = new boolean[100];
    private static double[] bookPrices = new double[100];
    private static int bookCount = 0;
    
    private static String[] memberNames = new String[50];
    private static String[] memberIds = new String[50];
    private static String[] memberEmails = new String[50];
    private static int[] memberBooksCheckedOut = new int[50];
    private static int memberCount = 0;
    
    private static final double LATE_FEE_PER_DAY = 0.50;
    private static final int MAX_BOOKS_PER_MEMBER = 5;
    private static final String LIBRARY_NAME = "Grand Central Library";
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(60));
        System.out.println("         " + LIBRARY_NAME.toUpperCase());
        System.out.println("              MANAGEMENT SYSTEM");
        System.out.println("=".repeat(60));
        
        // Initialize sample data using methods
        initializeLibraryData();
        
        // Run the interactive library system
        runLibrarySystem();
    }
    
    /**
     * Initialize the library with sample data
     * Demonstrates: void methods, method calls, data initialization
     */
    private static void initializeLibraryData() {
        System.out.println("\n🔄 Initializing Library Database...");
        
        // Add sample books using method calls
        addBook("To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4", 15.99, true);
        addBook("1984", "George Orwell", "978-0-452-28423-4", 13.99, true);
        addBook("Pride and Prejudice", "Jane Austen", "978-0-14-143951-8", 12.99, true);
        addBook("The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5", 14.99, false);
        addBook("The Catcher in the Rye", "J.D. Salinger", "978-0-316-76948-0", 16.99, true);
        addBook("Lord of the Flies", "William Golding", "978-0-571-05686-2", 13.50, true);
        addBook("Animal Farm", "George Orwell", "978-0-452-28424-1", 11.99, true);
        addBook("Brave New World", "Aldous Huxley", "978-0-06-085052-4", 15.50, false);
        
        // Add sample members using method calls
        addMember("Alice Johnson", "M001", "alice.johnson@email.com");
        addMember("Bob Smith", "M002", "bob.smith@email.com");
        addMember("Carol Williams", "M003", "carol.williams@email.com");
        addMember("David Brown", "M004", "david.brown@email.com");
        addMember("Eva Davis", "M005", "eva.davis@email.com");
        
        System.out.printf("✅ Initialized %d books and %d members%n", bookCount, memberCount);
        displaySystemStatistics();
    }
    
    /**
     * Main interactive system loop
     * Demonstrates: do-while loops, method calls, menu systems
     */
    private static void runLibrarySystem() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        
        do {
            displayMainMenu();
            choice = getValidMenuChoice(scanner, 1, 9);
            
            switch (choice) {
                case 1:
                    handleBookOperations(scanner);
                    break;
                case 2:
                    handleMemberOperations(scanner);
                    break;
                case 3:
                    handleCheckoutOperations(scanner);
                    break;
                case 4:
                    displayAllBooks();
                    break;
                case 5:
                    displayAllMembers();
                    break;
                case 6:
                    generateLibraryReports();
                    break;
                case 7:
                    searchLibraryDatabase(scanner);
                    break;
                case 8:
                    performSystemMaintenance();
                    break;
                case 9:
                    System.out.println("\n👋 Thank you for using " + LIBRARY_NAME + " Management System!");
                    break;
            }
            
            if (choice != 9) {
                pauseForUser(scanner);
            }
            
        } while (choice != 9);
        
        scanner.close();
    }
    
    /**
     * Display the main menu
     * Demonstrates: void method, formatted output
     */
    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("             MAIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. 📚 Book Operations");
        System.out.println("2. 👥 Member Operations");
        System.out.println("3. 🔄 Checkout/Return Operations");
        System.out.println("4. 📖 View All Books");
        System.out.println("5. 👤 View All Members");
        System.out.println("6. 📊 Generate Reports");
        System.out.println("7. 🔍 Search Database");
        System.out.println("8. 🛠️  System Maintenance");
        System.out.println("9. 🚪 Exit System");
        System.out.print("\nEnter your choice (1-9): ");
    }
    
    /**
     * Get valid menu choice with input validation
     * Demonstrates: method with parameters and return value, input validation
     */
    private static int getValidMenuChoice(Scanner scanner, int min, int max) {
        int choice = -1;
        
        while (choice < min || choice > max) {
            try {
                String input = scanner.nextLine().trim();
                choice = Integer.parseInt(input);
                
                if (choice < min || choice > max) {
                    System.out.printf("❌ Invalid choice. Please enter a number between %d and %d: ", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.print("❌ Please enter a valid number: ");
            }
        }
        
        return choice;
    }
    
    // ============================================================================
    // BOOK MANAGEMENT METHODS - Demonstrating different method patterns
    // ============================================================================
    
    /**
     * Add a new book to the library
     * Demonstrates: method with multiple parameters, data validation
     */
    private static boolean addBook(String title, String author, String isbn, double price, boolean available) {
        // Validate inputs
        if (!isValidBookData(title, author, isbn, price)) {
            return false;
        }
        
        // Check if library is full
        if (bookCount >= bookTitles.length) {
            System.out.println("❌ Library database is full. Cannot add more books.");
            return false;
        }
        
        // Check for duplicate ISBN
        if (findBookByISBN(isbn) != -1) {
            System.out.println("❌ Book with ISBN " + isbn + " already exists.");
            return false;
        }
        
        // Add the book
        bookTitles[bookCount] = title;
        bookAuthors[bookCount] = author;
        bookISBNs[bookCount] = isbn;
        bookPrices[bookCount] = price;
        bookAvailability[bookCount] = available;
        bookCount++;
        
        return true;
    }
    
    /**
     * Validate book data
     * Demonstrates: boolean return method, multiple validations
     */
    private static boolean isValidBookData(String title, String author, String isbn, double price) {
        if (isNullOrEmpty(title)) {
            System.out.println("❌ Book title cannot be empty.");
            return false;
        }
        
        if (isNullOrEmpty(author)) {
            System.out.println("❌ Author name cannot be empty.");
            return false;
        }
        
        if (!isValidISBN(isbn)) {
            System.out.println("❌ Invalid ISBN format.");
            return false;
        }
        
        if (price < 0) {
            System.out.println("❌ Book price cannot be negative.");
            return false;
        }
        
        return true;
    }
    
    /**
     * Validate ISBN format (simplified)
     * Demonstrates: String validation method
     */
    private static boolean isValidISBN(String isbn) {
        if (isNullOrEmpty(isbn)) {
            return false;
        }
        
        // Remove hyphens and check length
        String cleanISBN = isbn.replace("-", "");
        return cleanISBN.length() == 10 || cleanISBN.length() == 13;
    }
    
    /**
     * Find book by ISBN
     * Demonstrates: search method returning index, linear search algorithm
     */
    private static int findBookByISBN(String isbn) {
        for (int i = 0; i < bookCount; i++) {
            if (bookISBNs[i].equals(isbn)) {
                return i;
            }
        }
        return -1; // Not found
    }
    
    /**
     * Find book by title (case-insensitive partial match)
     * Demonstrates: flexible search method
     */
    private static int findBookByTitle(String title) {
        if (isNullOrEmpty(title)) {
            return -1;
        }
        
        String searchTitle = title.toLowerCase().trim();
        
        for (int i = 0; i < bookCount; i++) {
            if (bookTitles[i].toLowerCase().contains(searchTitle)) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * Get available books count
     * Demonstrates: counting method, enhanced for loop
     */
    private static int getAvailableBooksCount() {
        int count = 0;
        for (int i = 0; i < bookCount; i++) {
            if (bookAvailability[i]) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Calculate total library value
     * Demonstrates: calculation method, accumulation pattern
     */
    private static double calculateTotalLibraryValue() {
        double totalValue = 0.0;
        for (int i = 0; i < bookCount; i++) {
            totalValue += bookPrices[i];
        }
        return totalValue;
    }
    
    /**
     * Display book information
     * Demonstrates: formatted output method, parameter validation
     */
    private static void displayBookInfo(int bookIndex) {
        if (bookIndex < 0 || bookIndex >= bookCount) {
            System.out.println("❌ Invalid book index.");
            return;
        }
        
        String status = bookAvailability[bookIndex] ? "📗 Available" : "📕 Checked Out";
        
        System.out.println("\n📚 BOOK INFORMATION:");
        System.out.println("   Title: " + bookTitles[bookIndex]);
        System.out.println("   Author: " + bookAuthors[bookIndex]);
        System.out.println("   ISBN: " + bookISBNs[bookIndex]);
        System.out.println("   Price: " + formatCurrency(bookPrices[bookIndex]));
        System.out.println("   Status: " + status);
    }
    
    /**
     * Display all books in the library
     * Demonstrates: iteration method, formatted table output
     */
    private static void displayAllBooks() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                           ALL BOOKS IN LIBRARY");
        System.out.println("=".repeat(80));
        
        if (bookCount == 0) {
            System.out.println("📚 No books in the library database.");
            return;
        }
        
        System.out.printf("%-3s %-25s %-20s %-18s %-8s %s%n", 
                         "#", "Title", "Author", "ISBN", "Price", "Status");
        System.out.println("-".repeat(80));
        
        for (int i = 0; i < bookCount; i++) {
            String status = bookAvailability[i] ? "Available" : "Checked Out";
            String truncatedTitle = truncateString(bookTitles[i], 24);
            String truncatedAuthor = truncateString(bookAuthors[i], 19);
            
            System.out.printf("%-3d %-25s %-20s %-18s %-8s %s%n",
                            i + 1, truncatedTitle, truncatedAuthor, 
                            bookISBNs[i], formatCurrency(bookPrices[i]), status);
        }
        
        System.out.println("-".repeat(80));
        System.out.printf("Total Books: %d | Available: %d | Checked Out: %d%n",
                         bookCount, getAvailableBooksCount(), bookCount - getAvailableBooksCount());
    }
    
    // ============================================================================
    // MEMBER MANAGEMENT METHODS - Demonstrating object-like data handling
    // ============================================================================
    
    /**
     * Add a new member to the library
     * Demonstrates: member validation and addition
     */
    private static boolean addMember(String name, String memberId, String email) {
        if (!isValidMemberData(name, memberId, email)) {
            return false;
        }
        
        if (memberCount >= memberNames.length) {
            System.out.println("❌ Member database is full.");
            return false;
        }
        
        if (findMemberById(memberId) != -1) {
            System.out.println("❌ Member with ID " + memberId + " already exists.");
            return false;
        }
        
        memberNames[memberCount] = name;
        memberIds[memberCount] = memberId;
        memberEmails[memberCount] = email;
        memberBooksCheckedOut[memberCount] = 0;
        memberCount++;
        
        return true;
    }
    
    /**
     * Validate member data
     * Demonstrates: comprehensive validation method
     */
    private static boolean isValidMemberData(String name, String memberId, String email) {
        if (isNullOrEmpty(name)) {
            System.out.println("❌ Member name cannot be empty.");
            return false;
        }
        
        if (isNullOrEmpty(memberId)) {
            System.out.println("❌ Member ID cannot be empty.");
            return false;
        }
        
        if (!isValidEmail(email)) {
            System.out.println("❌ Invalid email format.");
            return false;
        }
        
        return true;
    }
    
    /**
     * Find member by ID
     * Demonstrates: search method for member data
     */
    private static int findMemberById(String memberId) {
        if (isNullOrEmpty(memberId)) {
            return -1;
        }
        
        for (int i = 0; i < memberCount; i++) {
            if (memberIds[i].equals(memberId)) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * Display member information
     * Demonstrates: member data formatting
     */
    private static void displayMemberInfo(int memberIndex) {
        if (memberIndex < 0 || memberIndex >= memberCount) {
            System.out.println("❌ Invalid member index.");
            return;
        }
        
        System.out.println("\n👤 MEMBER INFORMATION:");
        System.out.println("   Name: " + memberNames[memberIndex]);
        System.out.println("   Member ID: " + memberIds[memberIndex]);
        System.out.println("   Email: " + memberEmails[memberIndex]);
        System.out.println("   Books Checked Out: " + memberBooksCheckedOut[memberIndex]);
        System.out.println("   Can Check Out More: " + canMemberCheckoutMore(memberIndex));
    }
    
    /**
     * Check if member can checkout more books
     * Demonstrates: business logic method
     */
    private static boolean canMemberCheckoutMore(int memberIndex) {
        if (memberIndex < 0 || memberIndex >= memberCount) {
            return false;
        }
        
        return memberBooksCheckedOut[memberIndex] < MAX_BOOKS_PER_MEMBER;
    }
    
    /**
     * Display all members
     * Demonstrates: tabular data display
     */
    private static void displayAllMembers() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                      ALL LIBRARY MEMBERS");
        System.out.println("=".repeat(70));
        
        if (memberCount == 0) {
            System.out.println("👥 No members in the database.");
            return;
        }
        
        System.out.printf("%-3s %-20s %-10s %-25s %-10s%n", 
                         "#", "Name", "Member ID", "Email", "Books Out");
        System.out.println("-".repeat(70));
        
        for (int i = 0; i < memberCount; i++) {
            String truncatedName = truncateString(memberNames[i], 19);
            String truncatedEmail = truncateString(memberEmails[i], 24);
            
            System.out.printf("%-3d %-20s %-10s %-25s %-10d%n",
                            i + 1, truncatedName, memberIds[i], 
                            truncatedEmail, memberBooksCheckedOut[i]);
        }
        
        System.out.println("-".repeat(70));
        System.out.printf("Total Members: %d%n", memberCount);
    }
    
    // ============================================================================
    // CHECKOUT/RETURN OPERATIONS - Demonstrating transaction methods
    // ============================================================================
    
    /**
     * Process book checkout
     * Demonstrates: complex transaction method with multiple validations
     */
    private static boolean checkoutBook(String memberId, String isbn) {
        int memberIndex = findMemberById(memberId);
        int bookIndex = findBookByISBN(isbn);
        
        // Validate member exists
        if (memberIndex == -1) {
            System.out.println("❌ Member not found: " + memberId);
            return false;
        }
        
        // Validate book exists
        if (bookIndex == -1) {
            System.out.println("❌ Book not found: " + isbn);
            return false;
        }
        
        // Check if book is available
        if (!bookAvailability[bookIndex]) {
            System.out.println("❌ Book is already checked out: " + bookTitles[bookIndex]);
            return false;
        }
        
        // Check if member can checkout more books
        if (!canMemberCheckoutMore(memberIndex)) {
            System.out.printf("❌ Member %s has reached the maximum limit of %d books%n", 
                            memberNames[memberIndex], MAX_BOOKS_PER_MEMBER);
            return false;
        }
        
        // Process the checkout
        bookAvailability[bookIndex] = false;
        memberBooksCheckedOut[memberIndex]++;
        
        System.out.printf("✅ Successfully checked out '%s' to %s%n", 
                         bookTitles[bookIndex], memberNames[memberIndex]);
        
        return true;
    }
    
    /**
     * Process book return
     * Demonstrates: return transaction with late fee calculation
     */
    private static boolean returnBook(String memberId, String isbn, int daysLate) {
        int memberIndex = findMemberById(memberId);
        int bookIndex = findBookByISBN(isbn);
        
        // Validate member exists
        if (memberIndex == -1) {
            System.out.println("❌ Member not found: " + memberId);
            return false;
        }
        
        // Validate book exists
        if (bookIndex == -1) {
            System.out.println("❌ Book not found: " + isbn);
            return false;
        }
        
        // Check if book was actually checked out
        if (bookAvailability[bookIndex]) {
            System.out.println("❌ Book was not checked out: " + bookTitles[bookIndex]);
            return false;
        }
        
        // Process the return
        bookAvailability[bookIndex] = true;
        memberBooksCheckedOut[memberIndex]--;
        
        // Calculate late fee
        double lateFee = calculateLateFee(daysLate);
        
        System.out.printf("✅ Successfully returned '%s' from %s%n", 
                         bookTitles[bookIndex], memberNames[memberIndex]);
        
        if (lateFee > 0) {
            System.out.printf("💰 Late fee: %s (%d days late)%n", 
                            formatCurrency(lateFee), daysLate);
        }
        
        return true;
    }
    
    /**
     * Calculate late fee based on days overdue
     * Demonstrates: calculation method with business rules
     */
    private static double calculateLateFee(int daysLate) {
        if (daysLate <= 0) {
            return 0.0;
        }
        
        return daysLate * LATE_FEE_PER_DAY;
    }
    
    // ============================================================================
    // SEARCH AND REPORTING METHODS - Demonstrating data analysis
    // ============================================================================
    
    /**
     * Search books by various criteria
     * Demonstrates: flexible search method with multiple criteria
     */
    private static void searchBooks(String searchTerm, String searchType) {
        if (isNullOrEmpty(searchTerm)) {
            System.out.println("❌ Search term cannot be empty.");
            return;
        }
        
        System.out.printf("\n🔍 Searching for '%s' in %s...%n", searchTerm, searchType);
        System.out.println("-".repeat(50));
        
        boolean found = false;
        String lowerSearchTerm = searchTerm.toLowerCase();
        
        for (int i = 0; i < bookCount; i++) {
            boolean matches = false;
            
            switch (searchType.toLowerCase()) {
                case "title":
                    matches = bookTitles[i].toLowerCase().contains(lowerSearchTerm);
                    break;
                case "author":
                    matches = bookAuthors[i].toLowerCase().contains(lowerSearchTerm);
                    break;
                case "isbn":
                    matches = bookISBNs[i].toLowerCase().contains(lowerSearchTerm);
                    break;
                case "all":
                    matches = bookTitles[i].toLowerCase().contains(lowerSearchTerm) ||
                             bookAuthors[i].toLowerCase().contains(lowerSearchTerm) ||
                             bookISBNs[i].toLowerCase().contains(lowerSearchTerm);
                    break;
            }
            
            if (matches) {
                displayBookInfo(i);
                found = true;
                System.out.println();
            }
        }
        
        if (!found) {
            System.out.println("📚 No books found matching your search criteria.");
        }
    }
    
    /**
     * Generate comprehensive library statistics
     * Demonstrates: statistical analysis methods
     */
    private static void generateLibraryStatistics() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                LIBRARY STATISTICS REPORT");
        System.out.println("=".repeat(60));
        
        // Basic counts
        int availableBooks = getAvailableBooksCount();
        int checkedOutBooks = bookCount - availableBooks;
        double totalValue = calculateTotalLibraryValue();
        double averageBookPrice = (bookCount > 0) ? totalValue / bookCount : 0.0;
        
        System.out.println("\n📊 COLLECTION OVERVIEW:");
        System.out.printf("   Total Books: %d%n", bookCount);
        System.out.printf("   Available Books: %d (%.1f%%)%n", 
                         availableBooks, getPercentage(availableBooks, bookCount));
        System.out.printf("   Checked Out Books: %d (%.1f%%)%n", 
                         checkedOutBooks, getPercentage(checkedOutBooks, bookCount));
        System.out.printf("   Total Collection Value: %s%n", formatCurrency(totalValue));
        System.out.printf("   Average Book Price: %s%n", formatCurrency(averageBookPrice));
        
        // Member statistics
        int totalBooksCheckedOut = 0;
        int activeMembersCount = 0;
        
        for (int i = 0; i < memberCount; i++) {
            totalBooksCheckedOut += memberBooksCheckedOut[i];
            if (memberBooksCheckedOut[i] > 0) {
                activeMembersCount++;
            }
        }
        
        double averageBooksPerMember = (memberCount > 0) ? 
                                      (double) totalBooksCheckedOut / memberCount : 0.0;
        
        System.out.println("\n👥 MEMBERSHIP STATISTICS:");
        System.out.printf("   Total Members: %d%n", memberCount);
        System.out.printf("   Active Members: %d (%.1f%%)%n", 
                         activeMembersCount, getPercentage(activeMembersCount, memberCount));
        System.out.printf("   Average Books per Member: %.2f%n", averageBooksPerMember);
        
        // Popular authors analysis
        analyzePopularAuthors();
        
        // Price range analysis
        analyzePriceRanges();
    }
    
    /**
     * Analyze popular authors
     * Demonstrates: data aggregation and analysis
     */
    private static void analyzePopularAuthors() {
        System.out.println("\n📚 AUTHOR ANALYSIS:");
        
        // Simple author counting (would be more efficient with HashMap in real implementation)
        String[] uniqueAuthors = new String[bookCount];
        int[] authorCounts = new int[bookCount];
        int uniqueAuthorCount = 0;
        
        for (int i = 0; i < bookCount; i++) {
            String author = bookAuthors[i];
            int authorIndex = findStringInArray(uniqueAuthors, author, uniqueAuthorCount);
            
            if (authorIndex == -1) {
                // New author
                uniqueAuthors[uniqueAuthorCount] = author;
                authorCounts[uniqueAuthorCount] = 1;
                uniqueAuthorCount++;
            } else {
                // Existing author
                authorCounts[authorIndex]++;
            }
        }
        
        // Display top authors
        System.out.println("   Authors with multiple books:");
        for (int i = 0; i < uniqueAuthorCount; i++) {
            if (authorCounts[i] > 1) {
                System.out.printf("     %s: %d books%n", uniqueAuthors[i], authorCounts[i]);
            }
        }
        
        System.out.printf("   Total unique authors: %d%n", uniqueAuthorCount);
    }
    
    /**
     * Analyze book price ranges
     * Demonstrates: range analysis and categorization
     */
    private static void analyzePriceRanges() {
        System.out.println("\n💰 PRICE RANGE ANALYSIS:");
        
        int budget = 0;      // Under $10
        int moderate = 0;    // $10-$15
        int premium = 0;     // Over $15
        
        double minPrice = (bookCount > 0) ? bookPrices[0] : 0;
        double maxPrice = (bookCount > 0) ? bookPrices[0] : 0;
        
        for (int i = 0; i < bookCount; i++) {
            double price = bookPrices[i];
            
            if (price < 10.0) {
                budget++;
            } else if (price <= 15.0) {
                moderate++;
            } else {
                premium++;
            }
            
            if (price < minPrice) minPrice = price;
            if (price > maxPrice) maxPrice = price;
        }
        
        System.out.printf("   Budget Books (<$10): %d (%.1f%%)%n", 
                         budget, getPercentage(budget, bookCount));
        System.out.printf("   Moderate Books ($10-$15): %d (%.1f%%)%n", 
                         moderate, getPercentage(moderate, bookCount));
        System.out.printf("   Premium Books (>$15): %d (%.1f%%)%n", 
                         premium, getPercentage(premium, bookCount));
        System.out.printf("   Price Range: %s - %s%n", 
                         formatCurrency(minPrice), formatCurrency(maxPrice));
    }
    
    // ============================================================================
    // INTERACTIVE OPERATION HANDLERS - Demonstrating user interaction methods
    // ============================================================================
    
    /**
     * Handle book operations submenu
     * Demonstrates: submenu handling and delegation
     */
    private static void handleBookOperations(Scanner scanner) {
        System.out.println("\n📚 BOOK OPERATIONS");
        System.out.println("1. Add New Book");
        System.out.println("2. Search Books");
        System.out.println("3. View Book Details");
        System.out.print("Choose operation (1-3): ");
        
        int choice = getValidMenuChoice(scanner, 1, 3);
        
        switch (choice) {
            case 1:
                addBookInteractive(scanner);
                break;
            case 2:
                searchBooksInteractive(scanner);
                break;
            case 3:
                viewBookDetailsInteractive(scanner);
                break;
        }
    }
    
    /**
     * Interactive book addition
     * Demonstrates: user input handling and validation
     */
    private static void addBookInteractive(Scanner scanner) {
        System.out.println("\n➕ ADD NEW BOOK");
        
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        
        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim();
        
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine().trim();
        
        System.out.print("Enter price: $");
        double price = getValidDouble(scanner);
        
        System.out.print("Is book available? (y/n): ");
        boolean available = getYesNoResponse(scanner);
        
        if (addBook(title, author, isbn, price, available)) {
            System.out.println("✅ Book added successfully!");
        } else {
            System.out.println("❌ Failed to add book. Please check the information.");
        }
    }
    
    /**
     * Handle member operations submenu
     */
    private static void handleMemberOperations(Scanner scanner) {
        System.out.println("\n👥 MEMBER OPERATIONS");
        System.out.println("1. Add New Member");
        System.out.println("2. View Member Details");
        System.out.println("3. Member Statistics");
        System.out.print("Choose operation (1-3): ");
        
        int choice = getValidMenuChoice(scanner, 1, 3);
        
        switch (choice) {
            case 1:
                addMemberInteractive(scanner);
                break;
            case 2:
                viewMemberDetailsInteractive(scanner);
                break;
            case 3:
                displayMemberStatistics();
                break;
        }
    }
    
    /**
     * Handle checkout operations submenu
     */
    private static void handleCheckoutOperations(Scanner scanner) {
        System.out.println("\n🔄 CHECKOUT/RETURN OPERATIONS");
        System.out.println("1. Checkout Book");
        System.out.println("2. Return Book");
        System.out.print("Choose operation (1-2): ");
        
        int choice = getValidMenuChoice(scanner, 1, 2);
        
        switch (choice) {
            case 1:
                checkoutBookInteractive(scanner);
                break;
            case 2:
                returnBookInteractive(scanner);
                break;
        }
    }
    
    // ============================================================================
    // UTILITY METHODS - Demonstrating helper functions
    // ============================================================================
    
    /**
     * Check if string is null or empty
     * Demonstrates: utility validation method
     */
    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    /**
     * Validate email format (simplified)
     * Demonstrates: pattern validation method
     */
    private static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) {
            return false;
        }
        
        return email.contains("@") && email.contains(".") && email.length() > 5;
    }
    
    /**
     * Format currency value
     * Demonstrates: formatting utility method
     */
    private static String formatCurrency(double amount) {
        return String.format("$%.2f", amount);
    }
    
    /**
     * Calculate percentage
     * Demonstrates: calculation utility method
     */
    private static double getPercentage(int part, int total) {
        if (total == 0) {
            return 0.0;
        }
        return (double) part / total * 100.0;
    }
    
    /**
     * Truncate string to specified length
     * Demonstrates: string manipulation utility
     */
    private static String truncateString(String str, int maxLength) {
        if (str == null) {
            return "";
        }
        
        if (str.length() <= maxLength) {
            return str;
        }
        
        return str.substring(0, maxLength - 3) + "...";
    }
    
    /**
     * Find string in array (utility for simple counting)
     * Demonstrates: linear search utility method
     */
    private static int findStringInArray(String[] array, String target, int arraySize) {
        for (int i = 0; i < arraySize; i++) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Get valid double input from user
     * Demonstrates: input validation with type conversion
     */
    private static double getValidDouble(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                double value = Double.parseDouble(input);
                if (value >= 0) {
                    return value;
                } else {
                    System.out.print("Please enter a non-negative number: $");
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: $");
            }
        }
    }
    
    /**
     * Get yes/no response from user
     * Demonstrates: boolean input validation
     */
    private static boolean getYesNoResponse(Scanner scanner) {
        while (true) {
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("y") || response.equals("yes")) {
                return true;
            } else if (response.equals("n") || response.equals("no")) {
                return false;
            } else {
                System.out.print("Please enter 'y' for yes or 'n' for no: ");
            }
        }
    }
    
    /**
     * Pause execution and wait for user input
     * Demonstrates: user interaction control method
     */
    private static void pauseForUser(Scanner scanner) {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    // ============================================================================
    // PLACEHOLDER METHODS - Demonstrating method stubs for future implementation
    // ============================================================================
    
    private static void addMemberInteractive(Scanner scanner) {
        System.out.println("🚧 Member addition feature coming soon!");
    }
    
    private static void viewBookDetailsInteractive(Scanner scanner) {
        System.out.println("🚧 Book details view coming soon!");
    }
    
    private static void viewMemberDetailsInteractive(Scanner scanner) {
        System.out.println("🚧 Member details view coming soon!");
    }
    
    private static void checkoutBookInteractive(Scanner scanner) {
        System.out.println("🚧 Interactive checkout coming soon!");
    }
    
    private static void returnBookInteractive(Scanner scanner) {
        System.out.println("🚧 Interactive return coming soon!");
    }
    
    private static void searchBooksInteractive(Scanner scanner) {
        System.out.println("🚧 Advanced book search coming soon!");
    }
    
    private static void searchLibraryDatabase(Scanner scanner) {
        System.out.println("🚧 Database search feature coming soon!");
    }
    
    private static void performSystemMaintenance() {
        System.out.println("🚧 System maintenance tools coming soon!");
    }
    
    private static void displayMemberStatistics() {
        System.out.println("🚧 Member statistics coming soon!");
    }
    
    private static void generateLibraryReports() {
        generateLibraryStatistics();
    }
    
    private static void displaySystemStatistics() {
        System.out.printf("📊 System Status: %d books, %d members, %.1f%% utilization%n",
                         bookCount, memberCount, 
                         getPercentage(bookCount - getAvailableBooksCount(), bookCount));
    }
}

/*
 * PRACTICAL LEARNING POINTS:
 * 
 * 1. METHOD ORGANIZATION:
 *    - Logical grouping of related methods (book operations, member operations)
 *    - Clear separation of concerns (validation, business logic, display)
 *    - Consistent naming conventions and parameter patterns
 *    - Proper use of access modifiers (private static for internal methods)
 * 
 * 2. PARAMETER DESIGN:
 *    - Methods accept appropriate data types for their function
 *    - Validation methods return boolean for clear success/failure indication
 *    - Search methods return indices (-1 for not found) for efficiency
 *    - Display methods are void since they only output information
 * 
 * 3. RETURN VALUE PATTERNS:
 *    - Boolean returns for validation and success/failure operations
 *    - Index returns for search operations (-1 convention for not found)
 *    - Calculated values (double, int) for mathematical operations
 *    - Formatted strings for display purposes
 * 
 * 4. METHOD REUSABILITY:
 *    - Utility methods (formatCurrency, isNullOrEmpty) used throughout
 *    - Validation methods prevent code duplication
 *    - Search methods provide flexible data access
 *    - Display methods ensure consistent formatting
 * 
 * 5. ERROR HANDLING:
 *    - Parameter validation prevents invalid operations
 *    - Boundary checking prevents array index errors
 *    - Graceful handling of edge cases (empty arrays, null values)
 *    - Clear error messages for user feedback
 * 
 * 6. BUSINESS LOGIC ENCAPSULATION:
 *    - Complex operations broken into smaller, focused methods
 *    - Business rules (max books per member) centralized in methods
 *    - Transaction logic (checkout/return) properly encapsulated
 *    - Statistical calculations separated from display logic
 * 
 * 7. USER INTERACTION PATTERNS:
 *    - Input validation methods ensure data integrity
 *    - Menu handling methods provide consistent user experience
 *    - Interactive methods guide users through complex operations
 *    - Feedback methods provide clear operation results
 */
