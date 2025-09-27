/**
 * The Path Revealed: Library Management System Solution
 * 
 * This solution demonstrates masterful use of static vs instance members
 * in a comprehensive Library Management System.
 * 
 * Static members handle library-wide information and utilities
 * Instance members handle individual book data and operations
 */
public class LibraryBook {
    
    // ═══════════════════════════════════════════════════════════════
    // STATIC MEMBERS - LIBRARY-WIDE INFORMATION AND CONSTANTS
    // ═══════════════════════════════════════════════════════════════
    
    // Library constants (never change)
    public static final String LIBRARY_NAME = "Sacred Scrolls Public Library";
    public static final String LIBRARY_ADDRESS = "123 Knowledge Ave, Wisdom City";
    public static final int ESTABLISHMENT_YEAR = 1995;
    public static final double DAILY_FINE_RATE = 0.50;  // $0.50 per day overdue
    public static final int LOAN_PERIOD_DAYS = 14;      // 2 weeks loan period
    
    // Library-wide tracking variables
    private static int totalBooksInLibrary = 0;
    private static int nextBookId = 1001;  // Starting book ID
    private static int booksCurrentlyCheckedOut = 0;
    private static double totalFinesCollected = 0.0;
    private static int totalBooksOverdue = 0;
    
    // Library operating information
    private static int openingHour = 8;   // 8 AM
    private static int closingHour = 21;  // 9 PM
    private static int currentDay = 1;    // For simulation purposes
    
    // Statistical tracking
    private static int totalCheckouts = 0;
    private static int totalReturns = 0;
    private static double totalFinesAssessed = 0.0;
    
    // ═══════════════════════════════════════════════════════════════
    // INSTANCE MEMBERS - INDIVIDUAL BOOK INFORMATION
    // ═══════════════════════════════════════════════════════════════
    
    // Book identification (permanent data)
    private final int bookId;
    private final String title;
    private final String author;
    private final String isbn;
    private final int publicationYear;
    
    // Checkout status and tracking
    private String checkoutStatus;     // "Available", "Checked Out", "Overdue"
    private String currentBorrower;
    private int checkoutDay;          // Day book was checked out
    private int dueDay;               // Day book is due back
    private double fineAmountOwed;
    private boolean finesPaid;
    
    // Book history tracking
    private int timesCheckedOut;
    private int daysSinceLastReturn;
    
    // ═══════════════════════════════════════════════════════════════
    // CONSTRUCTOR - CREATING A NEW LIBRARY BOOK
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Creates a new library book with automatic ID assignment
     * Updates library-wide statistics
     */
    public LibraryBook(String title, String author, String isbn, int publicationYear) {
        // Assign unique book ID and update static counter
        this.bookId = nextBookId++;
        
        // Set permanent book information
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        
        // Initialize checkout status
        this.checkoutStatus = "Available";
        this.currentBorrower = null;
        this.checkoutDay = 0;
        this.dueDay = 0;
        this.fineAmountOwed = 0.0;
        this.finesPaid = true;
        
        // Initialize history
        this.timesCheckedOut = 0;
        this.daysSinceLastReturn = 0;
        
        // Update library-wide statistics
        totalBooksInLibrary++;
        
        System.out.println("📚 Added to library: \"" + title + "\" by " + author + 
                          " (ID: " + bookId + ")");
    }
    
    // ═══════════════════════════════════════════════════════════════
    // STATIC METHODS - LIBRARY-WIDE OPERATIONS
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Displays comprehensive library information and statistics
     */
    public static void displayLibraryInformation() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("🏛️ " + LIBRARY_NAME.toUpperCase());
        System.out.println("═".repeat(60));
        System.out.println("📍 Address: " + LIBRARY_ADDRESS);
        System.out.println("📅 Established: " + ESTABLISHMENT_YEAR);
        System.out.println("🕒 Operating Hours: " + openingHour + ":00 AM - " + closingHour + ":00 PM");
        System.out.println("📆 Current Day: " + currentDay);
        System.out.println();
        
        System.out.println("📊 COLLECTION STATISTICS:");
        System.out.println("  Total Books in Library: " + totalBooksInLibrary);
        System.out.println("  Books Currently Checked Out: " + booksCurrentlyCheckedOut);
        System.out.println("  Books Available: " + (totalBooksInLibrary - booksCurrentlyCheckedOut));
        System.out.println("  Books Currently Overdue: " + totalBooksOverdue);
        System.out.println();
        
        System.out.println("💰 FINANCIAL STATISTICS:");
        System.out.println("  Daily Fine Rate: $" + String.format("%.2f", DAILY_FINE_RATE));
        System.out.println("  Total Fines Assessed: $" + String.format("%.2f", totalFinesAssessed));
        System.out.println("  Total Fines Collected: $" + String.format("%.2f", totalFinesCollected));
        System.out.println("  Outstanding Fines: $" + String.format("%.2f", (totalFinesAssessed - totalFinesCollected)));
        System.out.println();
        
        System.out.println("📈 CIRCULATION STATISTICS:");
        System.out.println("  Total Checkouts: " + totalCheckouts);
        System.out.println("  Total Returns: " + totalReturns);
        System.out.println("  Next Book ID: " + nextBookId);
        if (totalBooksInLibrary > 0) {
            double utilizationRate = (double) booksCurrentlyCheckedOut / totalBooksInLibrary * 100;
            System.out.println("  Current Utilization: " + String.format("%.1f", utilizationRate) + "%");
        }
        System.out.println("═".repeat(60));
    }
    
    /**
     * Calculates fine amount based on days overdue
     */
    public static double calculateFineAmount(int daysOverdue) {
        return daysOverdue > 0 ? daysOverdue * DAILY_FINE_RATE : 0.0;
    }
    
    /**
     * Checks if the library is currently open based on given hour (0-23)
     */
    public static boolean isLibraryOpen(int currentHour) {
        return currentHour >= openingHour && currentHour < closingHour;
    }
    
    /**
     * Updates library operating hours
     */
    public static void updateOperatingHours(int newOpeningHour, int newClosingHour) {
        if (newOpeningHour >= 0 && newOpeningHour < 24 && 
            newClosingHour >= 0 && newClosingHour < 24 && 
            newOpeningHour < newClosingHour) {
            
            openingHour = newOpeningHour;
            closingHour = newClosingHour;
            System.out.println("🕒 Library hours updated to: " + 
                              openingHour + ":00 AM - " + closingHour + ":00 PM");
        } else {
            System.out.println("❌ Invalid operating hours!");
        }
    }
    
    /**
     * Advances the current day (for simulation purposes)
     */
    public static void advanceDay() {
        currentDay++;
        System.out.println("📅 Day advanced to: " + currentDay);
        updateOverdueStatistics();
    }
    
    /**
     * Advances multiple days
     */
    public static void advanceDays(int days) {
        currentDay += days;
        System.out.println("📅 Advanced " + days + " days. Current day: " + currentDay);
        updateOverdueStatistics();
    }
    
    /**
     * Gets the current day
     */
    public static int getCurrentDay() {
        return currentDay;
    }
    
    /**
     * Updates statistics for overdue books (called internally)
     */
    private static void updateOverdueStatistics() {
        // This would normally iterate through all books to count overdue ones
        // For this demonstration, we'll rely on individual book reporting
        System.out.println("📊 Overdue statistics updated for day " + currentDay);
    }
    
    /**
     * Displays a summary report of library operations
     */
    public static void displayLibraryReport() {
        System.out.println("\n" + "█".repeat(50));
        System.out.println("📋 LIBRARY OPERATIONS REPORT");
        System.out.println("█".repeat(50));
        displayLibraryInformation();
        
        if (totalBooksInLibrary > 0) {
            System.out.println("\n📊 PERFORMANCE METRICS:");
            double avgCheckoutsPerBook = (double) totalCheckouts / totalBooksInLibrary;
            System.out.println("  Average checkouts per book: " + String.format("%.1f", avgCheckoutsPerBook));
            
            if (totalCheckouts > 0) {
                double returnRate = (double) totalReturns / totalCheckouts * 100;
                System.out.println("  Return rate: " + String.format("%.1f", returnRate) + "%");
            }
            
            if (totalFinesAssessed > 0) {
                double collectionRate = totalFinesCollected / totalFinesAssessed * 100;
                System.out.println("  Fine collection rate: " + String.format("%.1f", collectionRate) + "%");
                double avgFinePerOverdueBook = totalFinesAssessed / Math.max(1, totalBooksOverdue);
                System.out.println("  Average fine per overdue book: $" + String.format("%.2f", avgFinePerOverdueBook));
            }
        }
        System.out.println("█".repeat(50));
    }
    
    // ═══════════════════════════════════════════════════════════════
    // INSTANCE METHODS - INDIVIDUAL BOOK OPERATIONS
    // ═══════════════════════════════════════════════════════════════
    
    /**
     * Checks out the book to a borrower
     */
    public void checkOutTo(String borrowerName) {
        if (!checkoutStatus.equals("Available")) {
            System.out.println("❌ Cannot check out \"" + title + "\" - Status: " + checkoutStatus);
            return;
        }
        
        if (borrowerName == null || borrowerName.trim().isEmpty()) {
            System.out.println("❌ Borrower name cannot be empty!");
            return;
        }
        
        // Update instance state
        this.currentBorrower = borrowerName.trim();
        this.checkoutDay = currentDay;
        this.dueDay = currentDay + LOAN_PERIOD_DAYS;
        this.checkoutStatus = "Checked Out";
        this.timesCheckedOut++;
        
        // Update library-wide statistics
        booksCurrentlyCheckedOut++;
        totalCheckouts++;
        
        System.out.println("✅ \"" + title + "\" checked out to " + borrowerName);
        System.out.println("   Due date: Day " + dueDay + " (in " + LOAN_PERIOD_DAYS + " days)");
    }
    
    /**
     * Returns the book and processes any fines
     */
    public void returnBook() {
        if (checkoutStatus.equals("Available")) {
            System.out.println("❌ \"" + title + "\" is not currently checked out!");
            return;
        }
        
        int daysOverdue = currentDay - dueDay;
        String returnMessage = "📖 \"" + title + "\" returned by " + currentBorrower;
        
        if (daysOverdue > 0) {
            // Book is overdue
            fineAmountOwed = calculateFineAmount(daysOverdue);
            totalFinesAssessed += fineAmountOwed;
            finesPaid = false;
            
            if (checkoutStatus.equals("Checked Out")) {
                totalBooksOverdue++;  // Only increment if not already counted as overdue
            }
            
            returnMessage += " - " + daysOverdue + " days OVERDUE";
            returnMessage += "\n   Fine assessed: $" + String.format("%.2f", fineAmountOwed);
        } else {
            // Book returned on time
            returnMessage += " - ON TIME";
        }
        
        // Update instance state
        daysSinceLastReturn = 0;
        checkoutStatus = "Available";
        String previousBorrower = currentBorrower;
        currentBorrower = null;
        checkoutDay = 0;
        dueDay = 0;
        
        // Update library-wide statistics
        booksCurrentlyCheckedOut--;
        totalReturns++;
        
        System.out.println(returnMessage);
        
        if (fineAmountOwed > 0) {
            System.out.println("💰 Fine payment required before next checkout for " + previousBorrower);
        }
    }
    
    /**
     * Processes fine payment
     */
    public void payFine(double amount) {
        if (fineAmountOwed <= 0) {
            System.out.println("ℹ️ No fines owed for \"" + title + "\"");
            return;
        }
        
        if (amount <= 0) {
            System.out.println("❌ Payment amount must be positive!");
            return;
        }
        
        double actualPayment = Math.min(amount, fineAmountOwed);
        fineAmountOwed -= actualPayment;
        totalFinesCollected += actualPayment;
        
        System.out.println("💳 Fine payment received: $" + String.format("%.2f", actualPayment));
        
        if (fineAmountOwed <= 0) {
            finesPaid = true;
            System.out.println("✅ All fines paid for \"" + title + "\"");
        } else {
            System.out.println("   Remaining balance: $" + String.format("%.2f", fineAmountOwed));
        }
        
        if (amount > actualPayment) {
            System.out.println("   Overpayment returned: $" + String.format("%.2f", (amount - actualPayment)));
        }
    }
    
    /**
     * Checks if the book is currently overdue
     */
    public boolean isOverdue() {
        return checkoutStatus.equals("Checked Out") && currentDay > dueDay;
    }
    
    /**
     * Updates the overdue status and fine amount
     */
    public void updateOverdueStatus() {
        if (checkoutStatus.equals("Checked Out") && isOverdue()) {
            if (!checkoutStatus.equals("Overdue")) {
                checkoutStatus = "Overdue";
                totalBooksOverdue++;
            }
            
            int daysOverdue = currentDay - dueDay;
            double newFine = calculateFineAmount(daysOverdue);
            
            if (newFine > fineAmountOwed) {
                double additionalFine = newFine - fineAmountOwed;
                fineAmountOwed = newFine;
                totalFinesAssessed += additionalFine;
                finesPaid = false;
                
                System.out.println("⚠️ \"" + title + "\" is " + daysOverdue + " days overdue");
                System.out.println("   Updated fine: $" + String.format("%.2f", fineAmountOwed));
            }
        }
    }
    
    /**
     * Displays comprehensive book information
     */
    public void displayBookDetails() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("📚 BOOK DETAILS");
        System.out.println("-".repeat(50));
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Publication Year: " + publicationYear);
        System.out.println("Book ID: " + bookId);
        System.out.println();
        
        System.out.println("📋 STATUS INFORMATION:");
        System.out.println("  Current Status: " + checkoutStatus);
        if (currentBorrower != null) {
            System.out.println("  Current Borrower: " + currentBorrower);
            System.out.println("  Checked Out: Day " + checkoutDay);
            System.out.println("  Due Date: Day " + dueDay);
            
            if (isOverdue()) {
                int daysOverdue = currentDay - dueDay;
                System.out.println("  ⚠️ OVERDUE by " + daysOverdue + " days");
            } else if (checkoutStatus.equals("Checked Out")) {
                int daysRemaining = dueDay - currentDay;
                System.out.println("  📅 Due in " + daysRemaining + " days");
            }
        }
        
        if (fineAmountOwed > 0) {
            System.out.println("  💰 Fine Owed: $" + String.format("%.2f", fineAmountOwed));
            System.out.println("  💳 Fines Paid: " + (finesPaid ? "Yes" : "No"));
        }
        
        System.out.println();
        System.out.println("📊 HISTORY:");
        System.out.println("  Times Checked Out: " + timesCheckedOut);
        System.out.println("  Days Since Last Return: " + daysSinceLastReturn);
        
        System.out.println();
        System.out.println("🏛️ LIBRARY CONTEXT:");
        System.out.println("  Library: " + LIBRARY_NAME);
        System.out.println("  Current Day: " + currentDay);
        System.out.println("  Library Status: " + (isLibraryOpen(12) ? "Open" : "Closed") + 
                          " (" + openingHour + ":00 AM - " + closingHour + ":00 PM)");
        System.out.println("  Total Books in Library: " + totalBooksInLibrary);
        System.out.println("-".repeat(50));
    }
    
    // ═══════════════════════════════════════════════════════════════
    // GETTERS (Instance methods for accessing private data)
    // ═══════════════════════════════════════════════════════════════
    
    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getPublicationYear() { return publicationYear; }
    public String getCheckoutStatus() { return checkoutStatus; }
    public String getCurrentBorrower() { return currentBorrower; }
    public int getDueDay() { return dueDay; }
    public double getFineAmountOwed() { return fineAmountOwed; }
    public boolean areFinesPaid() { return finesPaid; }
    public int getTimesCheckedOut() { return timesCheckedOut; }
    
    // ═══════════════════════════════════════════════════════════════
    // MAIN METHOD - COMPREHENSIVE DEMONSTRATION
    // ═══════════════════════════════════════════════════════════════
    
    public static void main(String[] args) {
        System.out.println("🏛️ SACRED SCROLLS LIBRARY MANAGEMENT SYSTEM 🏛️\n");
        
        // Display initial library information
        displayLibraryInformation();
        
        System.out.println("\n📚 BUILDING THE LIBRARY COLLECTION...\n");
        
        // Create library books (demonstrates constructor and static counter)
        LibraryBook book1 = new LibraryBook("The Art of Programming", "Master Coder", 
                                           "978-0-123456-78-9", 2020);
        LibraryBook book2 = new LibraryBook("Data Structures Unveiled", "Algorithm Sage", 
                                           "978-0-987654-32-1", 2019);
        LibraryBook book3 = new LibraryBook("Object-Oriented Design", "Architecture Guru", 
                                           "978-0-456789-12-3", 2021);
        LibraryBook book4 = new LibraryBook("Database Mysteries", "Query Master", 
                                           "978-0-321654-98-7", 2018);
        LibraryBook book5 = new LibraryBook("Web Development Secrets", "Frontend Wizard", 
                                           "978-0-789123-45-6", 2022);
        
        // Display updated library statistics
        System.out.println("\n📊 LIBRARY AFTER BOOK ADDITIONS:");
        displayLibraryInformation();
        
        System.out.println("\n📋 CHECKOUT OPERATIONS...\n");
        
        // Checkout operations (demonstrates instance methods affecting static state)
        book1.checkOutTo("Alice Johnson");
        book2.checkOutTo("Bob Smith");
        book3.checkOutTo("Charlie Brown");
        book5.checkOutTo("Diana Prince");
        
        // Try to checkout already checked out book
        book1.checkOutTo("Eve Wilson");  // Should fail
        
        // Display individual book details
        System.out.println("\n👤 INDIVIDUAL BOOK STATUS:");
        book1.displayBookDetails();
        
        // Show updated library statistics after checkouts
        System.out.println("\n📊 LIBRARY AFTER CHECKOUTS:");
        displayLibraryInformation();
        
        System.out.println("\n⏰ TIME PROGRESSION SIMULATION...\n");
        
        // Advance time - some books become overdue
        advanceDays(10);  // 10 days pass
        
        // Update overdue status for checked out books
        book1.updateOverdueStatus();
        book2.updateOverdueStatus();
        book3.updateOverdueStatus();
        book5.updateOverdueStatus();
        
        // Advance more time
        advanceDays(8);   // 18 days total (4 days overdue)
        
        // Update overdue status again
        book1.updateOverdueStatus();
        book2.updateOverdueStatus();
        book3.updateOverdueStatus();
        book5.updateOverdueStatus();
        
        System.out.println("\n📖 BOOK RETURNS...\n");
        
        // Return books - some on time, some overdue
        book3.returnBook();  // Returned overdue (4 days late)
        book5.returnBook();  // Returned overdue (4 days late)
        
        // Advance a few more days
        advanceDays(3);  // Books 1 and 2 now 7 days overdue
        
        book1.updateOverdueStatus();
        book2.updateOverdueStatus();
        
        book1.returnBook();  // Very overdue (7 days)
        
        // Book 2 still checked out and overdue
        System.out.println("\n💰 FINE PAYMENT PROCESSING...\n");
        
        // Pay some fines
        book1.payFine(2.00);    // Partial payment
        book3.payFine(2.00);    // Full payment
        book5.payFine(5.00);    // Overpayment
        book1.payFine(1.50);    // Pay remaining balance
        
        System.out.println("\n🔧 LIBRARY MANAGEMENT OPERATIONS...\n");
        
        // Change library hours
        updateOperatingHours(9, 22);  // 9 AM to 10 PM
        
        // Test library hours checking
        System.out.println("Library open at 8 AM? " + isLibraryOpen(8));   // False now
        System.out.println("Library open at 10 AM? " + isLibraryOpen(10)); // True
        System.out.println("Library open at 11 PM? " + isLibraryOpen(23)); // False
        
        System.out.println("\n👥 FINAL BOOK STATUS REVIEW...\n");
        
        // Display all book details
        book1.displayBookDetails();
        book2.displayBookDetails();
        book3.displayBookDetails();
        book4.displayBookDetails();  // Never checked out
        book5.displayBookDetails();
        
        System.out.println("\n📋 FINAL LIBRARY OPERATIONS REPORT...\n");
        
        // Display comprehensive final report
        displayLibraryReport();
        
        System.out.println("\n🔍 STATIC UTILITY METHOD DEMONSTRATIONS...\n");
        
        // Demonstrate static utility methods
        System.out.println("Fine for 5 days overdue: $" + 
                          String.format("%.2f", calculateFineAmount(5)));
        System.out.println("Fine for 0 days overdue: $" + 
                          String.format("%.2f", calculateFineAmount(0)));
        System.out.println("Fine for -2 days (early return): $" + 
                          String.format("%.2f", calculateFineAmount(-2)));
        
        System.out.println("\n✨ LIBRARY MANAGEMENT SYSTEM DEMONSTRATION COMPLETE! ✨");
        System.out.println("\n📚 STATIC VS INSTANCE MASTERY ACHIEVED:");
        System.out.println("• Static members managed library-wide data and operations");
        System.out.println("• Instance members managed individual book states and actions");  
        System.out.println("• Both paradigms worked together seamlessly");
        System.out.println("• Proper encapsulation and data integrity maintained");
        System.out.println("• Statistics automatically updated through object interactions");
        
        System.out.println("\n🏛️ The Sacred Scrolls Library stands as a testament to your mastery!");
    }
}
