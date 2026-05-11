/**
 * LibrarySystem.java — The Grand Trial: Complete Solution
 *
 * A full Library Management System integrating every concept from the curriculum:
 *   - Encapsulation, Inheritance, Polymorphism, Abstraction
 *   - Collections (List, Set, Map), Exception Handling
 *   - SOLID Principles (SRP, OCP, DIP), Observer Pattern
 *   - Enums, Generics, LocalDate
 *
 * Compile & Run:
 *   javac LibrarySystem.java && java LibrarySystem
 */
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class LibrarySystem {

    // ══════════════════════════════════════════════
    // ENUMS
    // ══════════════════════════════════════════════

    enum Genre {
        FICTION, NON_FICTION, SCIENCE, HISTORY, TECHNOLOGY, BIOGRAPHY;

        public String display() {
            return name().charAt(0) + name().substring(1).toLowerCase().replace('_', ' ');
        }
    }

    enum MemberType {
        STANDARD(3), PREMIUM(10), STUDENT(5);

        private final int maxLoans;
        MemberType(int maxLoans) { this.maxLoans = maxLoans; }
        public int getMaxLoans() { return maxLoans; }
    }

    // ══════════════════════════════════════════════
    // EXCEPTION HIERARCHY
    // ══════════════════════════════════════════════

    static class LibraryException extends Exception {
        public LibraryException(String msg) { super(msg); }
    }

    static class BookNotFoundException extends LibraryException {
        public BookNotFoundException(String isbn) {
            super("Book not found — ISBN: " + isbn);
        }
    }

    static class MemberNotFoundException extends LibraryException {
        public MemberNotFoundException(String id) {
            super("Member not found — ID: " + id);
        }
    }

    static class NoAvailableCopiesException extends LibraryException {
        public NoAvailableCopiesException(String title) {
            super("No available copies of \"" + title + "\" — all copies are on loan.");
        }
    }

    static class LoanLimitExceededException extends LibraryException {
        public LoanLimitExceededException(String name, int limit) {
            super("Loan limit reached for " + name + " — max " + limit + " active loans allowed.");
        }
    }

    // ══════════════════════════════════════════════
    // GENERICS: SearchResult<T>
    // ══════════════════════════════════════════════

    static class SearchResult<T> {
        private final String query;
        private final List<T> results;

        SearchResult(String query, List<T> results) {
            this.query = query;
            this.results = Collections.unmodifiableList(results);
        }

        public List<T> getResults() { return results; }
        public int count() { return results.size(); }
        public boolean isEmpty() { return results.isEmpty(); }

        public void print(String label) {
            System.out.println("  Search (" + label + ": \"" + query + "\") → " + count() + " result(s):");
            results.forEach(r -> System.out.println("    • " + r));
        }
    }

    // ══════════════════════════════════════════════
    // CORE ENTITIES
    // ══════════════════════════════════════════════

    static class Book {
        private final String isbn;
        private final String title;
        private final String author;
        private final Genre genre;
        private final int year;
        private final int totalCopies;
        private int availableCopies;

        Book(String isbn, String title, String author, Genre genre, int year, int copies) {
            this.isbn = isbn; this.title = title; this.author = author;
            this.genre = genre; this.year = year;
            this.totalCopies = copies; this.availableCopies = copies;
        }

        // Getters
        public String getIsbn()          { return isbn; }
        public String getTitle()         { return title; }
        public String getAuthor()        { return author; }
        public Genre getGenre()          { return genre; }
        public int getYear()             { return year; }
        public int getTotalCopies()      { return totalCopies; }
        public int getAvailableCopies()  { return availableCopies; }

        void decrementAvailable() { availableCopies--; }
        void incrementAvailable() { availableCopies++; }

        // Object contract — based on ISBN (the natural unique key for a book)
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Book)) return false;
            return isbn.equals(((Book) o).isbn);
        }

        @Override
        public int hashCode() { return isbn.hashCode(); }

        @Override
        public String toString() {
            return String.format("\"%s\" by %s [%s, %d] — %d/%d copies available",
                title, author, genre.display(), year, availableCopies, totalCopies);
        }
    }

    static class Member {
        private final String memberId;
        private final String name;
        private final String email;
        private final MemberType memberType;
        private final List<Loan> activeLoans;

        Member(String memberId, String name, String email, MemberType memberType) {
            this.memberId = memberId; this.name = name;
            this.email = email; this.memberType = memberType;
            this.activeLoans = new ArrayList<>();
        }

        public String getMemberId()       { return memberId; }
        public String getName()           { return name; }
        public String getEmail()          { return email; }
        public MemberType getMemberType() { return memberType; }
        public List<Loan> getActiveLoans(){ return Collections.unmodifiableList(activeLoans); }
        public int getMaxLoans()          { return memberType.getMaxLoans(); }

        void addLoan(Loan loan)    { activeLoans.add(loan); }
        void removeLoan(Loan loan) { activeLoans.remove(loan); }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Member)) return false;
            return memberId.equals(((Member) o).memberId);
        }

        @Override
        public int hashCode() { return memberId.hashCode(); }

        @Override
        public String toString() {
            return String.format("%s (%s) [%s] — %d/%d loans active",
                name, memberId, memberType, activeLoans.size(), getMaxLoans());
        }
    }

    static class Loan {
        private final Book book;
        private final Member member;
        private final LocalDate borrowedDate;
        private final LocalDate dueDate;
        private LocalDate returnedDate;

        Loan(Book book, Member member) {
            this.book = book;
            this.member = member;
            this.borrowedDate = LocalDate.now();
            this.dueDate = LocalDate.now().plusDays(14);
        }

        // Constructor for simulating overdue loans in tests
        Loan(Book book, Member member, int daysAgo) {
            this.book = book;
            this.member = member;
            this.borrowedDate = LocalDate.now().minusDays(daysAgo);
            this.dueDate = LocalDate.now().minusDays(daysAgo - 14);
        }

        public Book getBook()            { return book; }
        public Member getMember()        { return member; }
        public LocalDate getBorrowedDate(){ return borrowedDate; }
        public LocalDate getDueDate()    { return dueDate; }
        public LocalDate getReturnedDate(){ return returnedDate; }

        void setReturnedDate(LocalDate date) { this.returnedDate = date; }

        public boolean isOverdue() {
            return returnedDate == null && LocalDate.now().isAfter(dueDate);
        }

        public long getDaysOverdue() {
            if (!isOverdue()) return 0;
            return ChronoUnit.DAYS.between(dueDate, LocalDate.now());
        }

        public double getFine() {
            return getDaysOverdue() * 0.50;
        }

        @Override
        public String toString() {
            return String.format("%s — \"%s\" (due: %s)%s",
                member.getName(), book.getTitle(), dueDate,
                isOverdue() ? " ⚠ OVERDUE " + getDaysOverdue() + " days ($" + String.format("%.2f", getFine()) + ")" : "");
        }
    }

    // ══════════════════════════════════════════════
    // SRP: SEARCH ENGINE (owns all search logic)
    // ══════════════════════════════════════════════

    static class SearchEngine {
        public SearchResult<Book> searchByTitle(Collection<Book> catalog, String query) {
            String q = query.toLowerCase();
            List<Book> found = catalog.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(q))
                .collect(Collectors.toList());
            return new SearchResult<>(query, found);
        }

        public SearchResult<Book> searchByAuthor(Collection<Book> catalog, String author) {
            String q = author.toLowerCase();
            List<Book> found = catalog.stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(q))
                .collect(Collectors.toList());
            return new SearchResult<>(author, found);
        }

        public SearchResult<Book> searchByGenre(Collection<Book> catalog, Genre genre) {
            List<Book> found = catalog.stream()
                .filter(b -> b.getGenre() == genre)
                .collect(Collectors.toList());
            return new SearchResult<>(genre.display(), found);
        }
    }

    // ══════════════════════════════════════════════
    // SRP: FINE CALCULATOR (owns all fine logic)
    // ══════════════════════════════════════════════

    static class FineCalculator {
        private static final double DAILY_FINE = 0.50;

        public double calculateFine(Loan loan) {
            return loan.getDaysOverdue() * DAILY_FINE;
        }

        public double totalFinesForMember(Member member) {
            return member.getActiveLoans().stream()
                .filter(Loan::isOverdue)
                .mapToDouble(this::calculateFine)
                .sum();
        }
    }

    // ══════════════════════════════════════════════
    // OBSERVER: Book Availability Notifier
    // ══════════════════════════════════════════════

    interface AvailabilityObserver {
        void onBookAvailable(Book book);
    }

    // ══════════════════════════════════════════════
    // MAIN LIBRARY SYSTEM
    // ══════════════════════════════════════════════

    static class Library {
        // Collections: Map for O(1) lookup by key
        private final Map<String, Book>   catalog  = new LinkedHashMap<>(); // isbn → Book
        private final Map<String, Member> members  = new LinkedHashMap<>(); // memberId → Member
        private final List<Loan>          allLoans = new ArrayList<>();

        // SRP: delegate search and fine calculation
        private final SearchEngine    searchEngine = new SearchEngine();
        private final FineCalculator  fineCalc     = new FineCalculator();

        // Observer: isbn → list of waiting observers
        private final Map<String, List<AvailabilityObserver>> waitlist = new HashMap<>();

        // ── Catalog management ──

        public void addBook(Book book) {
            if (catalog.containsKey(book.getIsbn())) {
                System.out.println("  ⚠ Book already in catalog: " + book.getTitle());
                return;
            }
            catalog.put(book.getIsbn(), book);
            System.out.println("  📗 Added: " + book);
        }

        public void registerMember(Member member) {
            if (members.containsKey(member.getMemberId())) {
                System.out.println("  ⚠ Member already registered: " + member.getName());
                return;
            }
            members.put(member.getMemberId(), member);
            System.out.println("  👤 Registered: " + member);
        }

        // ── Observer subscription ──

        public void addToWaitlist(String isbn, AvailabilityObserver observer) {
            waitlist.computeIfAbsent(isbn, k -> new ArrayList<>()).add(observer);
        }

        private void notifyWaitlist(Book book) {
            List<AvailabilityObserver> waiting = waitlist.getOrDefault(book.getIsbn(), List.of());
            if (!waiting.isEmpty()) {
                System.out.println("  🔔 Notifying " + waiting.size() + " waitlisted member(s) for \"" + book.getTitle() + "\":");
                waiting.forEach(obs -> obs.onBookAvailable(book));
                waitlist.remove(book.getIsbn());
            }
        }

        // ── Core operations ──

        public Loan borrowBook(String memberId, String isbn) throws LibraryException {
            Member member = members.get(memberId);
            if (member == null) throw new MemberNotFoundException(memberId);

            Book book = catalog.get(isbn);
            if (book == null) throw new BookNotFoundException(isbn);

            if (book.getAvailableCopies() == 0)
                throw new NoAvailableCopiesException(book.getTitle());

            if (member.getActiveLoans().size() >= member.getMaxLoans())
                throw new LoanLimitExceededException(member.getName(), member.getMaxLoans());

            Loan loan = new Loan(book, member);
            book.decrementAvailable();
            member.addLoan(loan);
            allLoans.add(loan);

            System.out.printf("  ✅ Borrowed: \"%s\" → %s (due: %s)%n",
                book.getTitle(), member.getName(), loan.getDueDate());
            return loan;
        }

        public void returnBook(String memberId, String isbn) throws LibraryException {
            Member member = members.get(memberId);
            if (member == null) throw new MemberNotFoundException(memberId);

            Loan loan = member.getActiveLoans().stream()
                .filter(l -> l.getBook().getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(isbn + " (not on loan by " + member.getName() + ")"));

            loan.setReturnedDate(LocalDate.now());
            loan.getBook().incrementAvailable();
            member.removeLoan(loan);

            System.out.printf("  📥 Returned: \"%s\" by %s%n", loan.getBook().getTitle(), member.getName());

            if (fineCalc.calculateFine(loan) > 0) {
                System.out.printf("  💰 Fine owed: $%.2f (%d days overdue)%n",
                    fineCalc.calculateFine(loan), loan.getDaysOverdue());
            }

            notifyWaitlist(loan.getBook());
        }

        // Overloaded: add a loan that simulates being borrowed N days ago (for overdue demo)
        public Loan borrowBookBackdated(String memberId, String isbn, int daysAgo) throws LibraryException {
            Member member = members.get(memberId);
            if (member == null) throw new MemberNotFoundException(memberId);
            Book book = catalog.get(isbn);
            if (book == null) throw new BookNotFoundException(isbn);
            if (book.getAvailableCopies() == 0) throw new NoAvailableCopiesException(book.getTitle());
            if (member.getActiveLoans().size() >= member.getMaxLoans())
                throw new LoanLimitExceededException(member.getName(), member.getMaxLoans());

            Loan loan = new Loan(book, member, daysAgo);
            book.decrementAvailable();
            member.addLoan(loan);
            allLoans.add(loan);
            System.out.printf("  ⏳ Backdated borrow: \"%s\" → %s (%d days ago, due %s) [OVERDUE SIMULATION]%n",
                book.getTitle(), member.getName(), daysAgo, loan.getDueDate());
            return loan;
        }

        // ── Search ──

        public SearchResult<Book> searchByTitle(String query) {
            return searchEngine.searchByTitle(catalog.values(), query);
        }

        public SearchResult<Book> searchByAuthor(String author) {
            return searchEngine.searchByAuthor(catalog.values(), author);
        }

        public SearchResult<Book> searchByGenre(Genre genre) {
            return searchEngine.searchByGenre(catalog.values(), genre);
        }

        public List<Loan> getMemberLoans(String memberId) throws LibraryException {
            Member m = members.get(memberId);
            if (m == null) throw new MemberNotFoundException(memberId);
            return m.getActiveLoans();
        }

        public List<Loan> getOverdueLoans() {
            return allLoans.stream()
                .filter(l -> l.getReturnedDate() == null && l.isOverdue())
                .collect(Collectors.toList());
        }

        // ── Report ──

        public void generateReport() {
            int totalCopies = catalog.values().stream().mapToInt(Book::getTotalCopies).sum();
            int availCopies = catalog.values().stream().mapToInt(Book::getAvailableCopies).sum();

            long standard = members.values().stream().filter(m -> m.getMemberType() == MemberType.STANDARD).count();
            long premium  = members.values().stream().filter(m -> m.getMemberType() == MemberType.PREMIUM).count();
            long student  = members.values().stream().filter(m -> m.getMemberType() == MemberType.STUDENT).count();

            long activeLoans = allLoans.stream().filter(l -> l.getReturnedDate() == null).count();
            List<Loan> overdue = getOverdueLoans();

            System.out.println("\n╔══════════════════════════════════════════════════════╗");
            System.out.println("║          📚 LIBRARY SYSTEM REPORT                   ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.printf( "║  CATALOG                                             ║%n");
            System.out.printf( "║    Total unique titles:  %-4d                        ║%n", catalog.size());
            System.out.printf( "║    Total copies:         %-4d                        ║%n", totalCopies);
            System.out.printf( "║    Available copies:     %-4d                        ║%n", availCopies);
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.printf( "║  MEMBERS                                             ║%n");
            System.out.printf( "║    Standard: %-2d | Premium: %-2d | Student: %-2d          ║%n",
                standard, premium, student);
            System.out.printf( "║    Total active loans: %-4d                          ║%n", activeLoans);
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.printf( "║  OVERDUE LOANS (%d)%n", overdue.size());
            if (overdue.isEmpty()) {
                System.out.println("║    No overdue loans                                  ║");
            } else {
                overdue.forEach(l -> System.out.printf(
                    "║    %-12s — \"%-20s\" — %2d days — $%.2f  ║%n",
                    l.getMember().getName(), l.getBook().getTitle(),
                    l.getDaysOverdue(), l.getFine()));
            }
            System.out.println("╚══════════════════════════════════════════════════════╝\n");
        }
    }

    // ══════════════════════════════════════════════
    // DEMO
    // ══════════════════════════════════════════════

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║     📚 GRAND TRIAL: LIBRARY SYSTEM              ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        Library lib = new Library();

        // ── 1. Register 5 members of different types ──
        System.out.println("── Registering Members ──");
        lib.registerMember(new Member("M001", "Alice",   "alice@mail.com",   MemberType.PREMIUM));
        lib.registerMember(new Member("M002", "Bob",     "bob@mail.com",     MemberType.STANDARD));
        lib.registerMember(new Member("M003", "Carol",   "carol@mail.com",   MemberType.STUDENT));
        lib.registerMember(new Member("M004", "Dave",    "dave@mail.com",    MemberType.STANDARD));
        lib.registerMember(new Member("M005", "Eve",     "eve@mail.com",     MemberType.STUDENT));
        lib.registerMember(new Member("M001", "Dup",     "dup@mail.com",     MemberType.STANDARD)); // duplicate demo

        // ── 2. Add 10+ books across 3+ genres ──
        System.out.println("\n── Adding Books ──");
        lib.addBook(new Book("978-0132350884", "Clean Code",               "Robert C. Martin", Genre.TECHNOLOGY,  2008, 2));
        lib.addBook(new Book("978-0201633610", "Design Patterns",          "GoF",              Genre.TECHNOLOGY,  1994, 2));
        lib.addBook(new Book("978-0316769174", "The Catcher in the Rye",   "J.D. Salinger",    Genre.FICTION,     1951, 3));
        lib.addBook(new Book("978-0061965579", "Outliers",                 "Malcolm Gladwell", Genre.NON_FICTION, 2008, 2));
        lib.addBook(new Book("978-0385333481", "A Brief History of Time",  "Stephen Hawking",  Genre.SCIENCE,     1988, 2));
        lib.addBook(new Book("978-1501156700", "Sapiens",                  "Yuval Noah Harari",Genre.HISTORY,     2011, 3));
        lib.addBook(new Book("978-0743273565", "The Great Gatsby",         "F. Scott Fitzgerald",Genre.FICTION,   1925, 2));
        lib.addBook(new Book("978-0679720201", "In Cold Blood",            "Truman Capote",    Genre.NON_FICTION, 1966, 1));
        lib.addBook(new Book("978-0743477108", "Steve Jobs",               "Walter Isaacson",  Genre.BIOGRAPHY,   2011, 2));
        lib.addBook(new Book("978-0307474278", "The Social Animal",        "David Brooks",     Genre.NON_FICTION, 2011, 2));
        lib.addBook(new Book("978-0451524935", "1984",                     "George Orwell",    Genre.FICTION,     1949, 3));

        // ── 3. Borrow books successfully ──
        System.out.println("\n── Borrowing Books ──");
        try {
            lib.borrowBook("M001", "978-0132350884"); // Alice borrows Clean Code
            lib.borrowBook("M001", "978-0201633610"); // Alice borrows Design Patterns
            lib.borrowBook("M002", "978-0316769174"); // Bob borrows Catcher in the Rye
            lib.borrowBook("M003", "978-0061965579"); // Carol borrows Outliers
            lib.borrowBook("M004", "978-0385333481"); // Dave borrows A Brief History of Time
            lib.borrowBook("M005", "978-1501156700"); // Eve borrows Sapiens
            lib.borrowBook("M002", "978-0743273565"); // Bob borrows The Great Gatsby
            lib.borrowBook("M002", "978-0451524935"); // Bob borrows 1984 (3rd loan — STANDARD limit)
        } catch (LibraryException e) {
            System.out.println("  ❌ " + e.getMessage());
        }

        // ── 4. Attempt to borrow when no copies available ──
        System.out.println("\n── Exception: No Available Copies ──");
        try {
            // Clean Code only has 2 copies, both borrowed by Alice
            lib.borrowBook("M001", "978-0132350884"); // Alice already has it
            lib.borrowBook("M004", "978-0132350884"); // Dave tries to borrow 2nd copy
            lib.borrowBook("M005", "978-0132350884"); // Eve tries — should fail, no copies left
        } catch (LibraryException e) {
            System.out.println("  ❌ Caught: " + e.getMessage());
        }

        // ── 5. Attempt to exceed loan limit ──
        System.out.println("\n── Exception: Loan Limit Exceeded ──");
        try {
            // Bob (STANDARD) already has 3 loans — max reached
            lib.borrowBook("M002", "978-0679720201");
        } catch (LibraryException e) {
            System.out.println("  ❌ Caught: " + e.getMessage());
        }

        // ── Exception: Member not found ──
        System.out.println("\n── Exception: Member Not Found ──");
        try {
            lib.borrowBook("M999", "978-0743477108");
        } catch (LibraryException e) {
            System.out.println("  ❌ Caught: " + e.getMessage());
        }

        // ── 6. Return a book ──
        System.out.println("\n── Returning a Book ──");
        try {
            lib.returnBook("M002", "978-0316769174"); // Bob returns Catcher in the Rye
        } catch (LibraryException e) {
            System.out.println("  ❌ " + e.getMessage());
        }

        // ── 7. Simulate overdue book ──
        System.out.println("\n── Simulating Overdue Loan ──");
        try {
            lib.borrowBookBackdated("M003", "978-0743273565", 20); // Carol borrowed 20 days ago (6 days overdue)
        } catch (LibraryException e) {
            System.out.println("  ❌ " + e.getMessage());
        }

        // ── Observer: waitlist demo ──
        System.out.println("\n── Observer: Waitlist ──");
        // In Cold Blood only has 1 copy — Dave borrows it, Eve joins waitlist
        try {
            lib.borrowBook("M004", "978-0679720201"); // Dave borrows In Cold Blood
        } catch (LibraryException e) {
            System.out.println("  ❌ " + e.getMessage());
        }
        // Eve joins waitlist
        lib.addToWaitlist("978-0679720201", book ->
            System.out.println("  📣 [NOTIFICATION] Eve: \"" + book.getTitle() + "\" is now available!"));
        System.out.println("  📋 Eve added to waitlist for \"In Cold Blood\"");
        // Dave returns it — Eve gets notified
        try {
            lib.returnBook("M004", "978-0679720201");
        } catch (LibraryException e) {
            System.out.println("  ❌ " + e.getMessage());
        }

        // ── 8. Search demonstrations ──
        System.out.println("\n── Search: by Title ──");
        lib.searchByTitle("code").print("title");

        System.out.println("\n── Search: by Author ──");
        lib.searchByAuthor("orwell").print("author");

        System.out.println("\n── Search: by Genre ──");
        lib.searchByGenre(Genre.FICTION).print("genre");

        System.out.println("\n── Overdue Loans ──");
        List<Loan> overdue = lib.getOverdueLoans();
        if (overdue.isEmpty()) {
            System.out.println("  No overdue loans.");
        } else {
            overdue.forEach(l -> System.out.println("  ⚠ " + l));
        }

        // ── 9. Full system report ──
        lib.generateReport();
    }
}
