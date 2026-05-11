/**
 * LibrarySystem_Starter.java — Grand Trial Starter Skeleton
 *
 * This file gives you the skeleton structure.
 * Your job: implement every method marked TODO.
 * Do NOT change the method signatures or class names.
 *
 * Compile & Run: javac LibrarySystem_Starter.java && java LibrarySystem_Starter
 */
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class LibrarySystem_Starter {

    // ── TODO: Define Genre enum (FICTION, NON_FICTION, SCIENCE, HISTORY, TECHNOLOGY, BIOGRAPHY) ──
    enum Genre { /* TODO */ }

    // ── TODO: Define MemberType enum with maxLoans field ──
    enum MemberType { /* TODO */ }

    // ── Exception Hierarchy ──
    static class LibraryException extends Exception {
        public LibraryException(String msg) { super(msg); }
    }
    // TODO: Define BookNotFoundException, MemberNotFoundException,
    //       NoAvailableCopiesException, LoanLimitExceededException
    //       All extend LibraryException

    // ── Loan ──
    static class Loan {
        Book book;
        Member member;
        LocalDate borrowedDate;
        LocalDate dueDate;
        LocalDate returnedDate;

        Loan(Book book, Member member) {
            this.book = book;
            this.member = member;
            this.borrowedDate = LocalDate.now();
            this.dueDate = LocalDate.now().plusDays(14);
        }

        // TODO: isOverdue() — true if not returned AND past dueDate
        public boolean isOverdue() { return false; }

        // TODO: getDaysOverdue() — days past dueDate (0 if not overdue)
        public long getDaysOverdue() { return 0; }

        // TODO: getFine() — $0.50 per overdue day
        public double getFine() { return 0; }

        @Override
        public String toString() {
            return String.format("%s — \"%s\" (due: %s)%s",
                member.name, book.title, dueDate,
                isOverdue() ? " ⚠ OVERDUE " + getDaysOverdue() + " days" : "");
        }
    }

    // ── Book ──
    static class Book {
        String isbn, title, author;
        Genre genre;
        int year, totalCopies, availableCopies;

        Book(String isbn, String title, String author, Genre genre, int year, int copies) {
            this.isbn = isbn; this.title = title; this.author = author;
            this.genre = genre; this.year = year;
            this.totalCopies = copies; this.availableCopies = copies;
        }

        // TODO: Override equals() and hashCode() based on isbn
        // TODO: Override toString()
    }

    // ── Member ──
    static class Member {
        String memberId, name, email;
        MemberType memberType;
        List<Loan> activeLoans;

        Member(String memberId, String name, String email, MemberType memberType) {
            this.memberId = memberId; this.name = name;
            this.email = email; this.memberType = memberType;
            this.activeLoans = new ArrayList<>();
        }

        // TODO: getMaxLoans() — from MemberType
        public int getMaxLoans() { return 0; }

        // TODO: Override toString()
    }

    // ── SRP: Search Engine ──
    static class SearchEngine {
        // TODO: searchByTitle(List<Book> catalog, String query) — partial, case-insensitive
        public List<Book> searchByTitle(List<Book> catalog, String query) { return List.of(); }

        // TODO: searchByAuthor(List<Book> catalog, String author)
        public List<Book> searchByAuthor(List<Book> catalog, String author) { return List.of(); }

        // TODO: searchByGenre(List<Book> catalog, Genre genre)
        public List<Book> searchByGenre(List<Book> catalog, Genre genre) { return List.of(); }
    }

    // ── SRP: Fine Calculator ──
    static class FineCalculator {
        private static final double DAILY_FINE = 0.50;
        // TODO: calculateFine(Loan loan) — DAILY_FINE × days overdue
        public double calculateFine(Loan loan) { return 0; }
    }

    // ── Main Library System ──
    static class LibrarySystem {
        private Map<String, Book> catalog = new LinkedHashMap<>();   // isbn → Book
        private Map<String, Member> members = new LinkedHashMap<>(); // memberId → Member
        private List<Loan> allLoans = new ArrayList<>();

        private SearchEngine search = new SearchEngine();
        private FineCalculator fineCalc = new FineCalculator();

        // Observer support: isbn → list of members waiting
        private Map<String, List<Member>> waitlist = new HashMap<>();

        public void addBook(Book book) {
            // TODO: put in catalog, reject duplicates
        }

        public void registerMember(Member member) {
            // TODO: put in members, reject duplicates
        }

        public Loan borrowBook(String memberId, String isbn) throws LibraryException {
            // TODO:
            // 1. Find member (throw MemberNotFoundException if not found)
            // 2. Find book (throw BookNotFoundException if not found)
            // 3. Check availableCopies > 0 (throw NoAvailableCopiesException)
            // 4. Check member.activeLoans.size() < getMaxLoans() (throw LoanLimitExceededException)
            // 5. Create Loan, add to member.activeLoans and allLoans, decrement availableCopies
            return null;
        }

        public void returnBook(String memberId, String isbn) throws LibraryException {
            // TODO:
            // 1. Find the active loan
            // 2. Set returnedDate, increment availableCopies
            // 3. Remove from member.activeLoans
            // 4. If overdue, print fine amount
            // 5. Notify waitlist observers (if any)
        }

        public void generateReport() {
            // TODO: Print the full report in the format shown in the challenge
        }
    }

    // ── Demo ──
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║     📚 GRAND TRIAL: LIBRARY SYSTEM              ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        LibrarySystem lib = new LibrarySystem();

        // TODO: Register 5+ members
        // TODO: Add 10+ books across 3+ genres
        // TODO: Borrow books
        // TODO: Demonstrate exception handling
        // TODO: Return a book
        // TODO: Demonstrate search
        // TODO: Generate report

        System.out.println("Grand Trial complete!");
    }
}
