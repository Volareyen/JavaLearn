# ⚔️ The Grand Trial — Your Final Capstone

*"You have walked every path in the Grand Library. You have wielded the Four Pillars, navigated the Inner Sanctum, mastered the Collections, handled exceptions with grace, applied the SOLID principles, and wielded the patterns of the great architects. Now, young master, comes your final test.*

*You will build not a fragment, but a whole — a complete system that demonstrates every skill you have acquired. This is The Grand Trial. There are no hints. There is no guidance. There is only the requirement, and your mastery."*

---

## **The Task: Library Management System**

Build a console-based Library Management System that combines **all concepts** from the curriculum.

---

## **Functional Requirements**

### **Core Entities**

#### `Book`
- `isbn` (String), `title`, `author`, `genre` (enum), `year` (int), `totalCopies`, `availableCopies`
- Properly override `equals()`, `hashCode()`, `toString()`

#### `Member`
- `memberId`, `name`, `email`, `memberType` (enum: `STANDARD`, `PREMIUM`, `STUDENT`)
- A `List<Loan>` of their current loans
- Max active loans: STANDARD=3, PREMIUM=10, STUDENT=5

#### `Loan`
- `book`, `member`, `borrowedDate` (use `java.time.LocalDate`), `dueDate`, `returnedDate`
- `isOverdue()` — compare due date to today
- `getDaysOverdue()` — how many days past due

---

### **System Operations**

The `LibrarySystem` class must support:

1. **`addBook(Book book)`** — add to catalog
2. **`registerMember(Member member)`** — add member (no duplicates)
3. **`borrowBook(String memberId, String isbn)`** — throws custom exceptions:
   - `BookNotFoundException`
   - `MemberNotFoundException`
   - `NoAvailableCopiesException`
   - `LoanLimitExceededException`
4. **`returnBook(String memberId, String isbn)`** — returns the book, calculates fine if overdue
5. **`searchByTitle(String query)`** — returns `List<Book>` (partial match, case-insensitive)
6. **`searchByAuthor(String author)`** — returns `List<Book>`
7. **`searchByGenre(Genre genre)`** — returns `List<Book>`
8. **`getMemberLoans(String memberId)`** — returns member's active loans
9. **`getOverdueLoans()`** — returns all overdue loans across all members
10. **`generateReport()`** — prints full system statistics

---

## **Architecture Requirements**

Apply what you've learned:

| Concept | Where to Apply |
|---------|---------------|
| **SRP** | Separate `LoanCalculator`, `FineCalculator`, `SearchEngine` classes |
| **OCP** | `SearchStrategy` interface with multiple implementations |
| **Collections** | Use appropriate `List`, `Set`, `Map` for catalog, members, loans |
| **Custom Exceptions** | Full exception hierarchy rooted at `LibraryException` |
| **Generics** | `SearchResult<T>` class |
| **Enums** | `Genre`, `MemberType` |
| **Observer** | Notify members when a book they want becomes available |
| **Encapsulation** | All fields private, accessed via methods |

---

## **The Demo Script**

Your `main()` must demonstrate ALL of the following:

1. Register 5+ members of different types
2. Add 10+ books across 3+ genres
3. Borrow books successfully
4. Attempt to borrow when no copies available → handle exception
5. Attempt to exceed loan limit → handle exception
6. Return a book
7. Simulate an overdue book and calculate the fine ($0.50/day)
8. Search by title, author, and genre
9. Generate the full system report showing:
   - Total books, total copies, available copies
   - Total members by type
   - Active loans count
   - Overdue loans with fines owed

---

## **Expected Report Format**

```
╔══════════════════════════════════════════════════════╗
║          📚 LIBRARY SYSTEM REPORT                   ║
╠══════════════════════════════════════════════════════╣
║ CATALOG                                             ║
║   Total unique titles:   12                         ║
║   Total copies:          28                         ║
║   Available copies:      19                         ║
╠══════════════════════════════════════════════════════╣
║ MEMBERS                                             ║
║   Standard:  3  |  Premium: 1  |  Student: 2       ║
║   Total active loans: 9                             ║
╠══════════════════════════════════════════════════════╣
║ OVERDUE LOANS                                       ║
║   Alice — "Clean Code" — 5 days overdue — $2.50    ║
╚══════════════════════════════════════════════════════╝
```

---

## **Grading Your Own Work**

After completing your system, honestly answer:

- [ ] Does every class have a single, clear responsibility?
- [ ] Can I add a new `Genre` or `MemberType` without modifying existing logic?
- [ ] Do all exceptions carry meaningful messages with context?
- [ ] Are the correct collection types used for each use case?
- [ ] Does the Observer notify waiting members correctly?
- [ ] Does the code compile cleanly with `javac *.java`?
- [ ] Does the demo run and produce the expected output?

---

*"This is not a test of memory. It is a test of understanding. The architect builds cathedrals not by reciting their materials list, but by knowing why each stone is placed where it is.*

*If you have built this system — truly built it, understanding every design decision — then you have ascended. You are no longer a student of Java. You are a practitioner of the craft.*

*Go now. Build."*
