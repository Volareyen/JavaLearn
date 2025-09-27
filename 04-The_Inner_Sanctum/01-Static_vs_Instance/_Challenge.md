# The Pupil's Trial: Library Management System
*Static vs Instance Mastery Challenge*

---

## **The Sacred Task**

*Seeker, you have witnessed the profound distinction between the eternal (static) and the temporal (instance). Now you must forge your own system that demonstrates this ancient wisdom.*

*Your challenge is to create a **Library Management System** that showcases both static and instance members working in perfect harmony. This system will manage both library-wide operations and individual book tracking.*

---

## **The Requirements**

### **📚 Book Class Specifications**

Create a `LibraryBook` class with the following specifications:

#### **Static Members (Library-wide)**
- **Library name and address** (constants that never change)
- **Total number of books** in the entire library system
- **Next available book ID** (auto-incrementing for each new book)
- **Library operating hours** (shared information)
- **Total fines collected** across all book transactions
- **Library establishment year** (constant)

#### **Static Methods (Library-wide operations)**
- **Display library information** - Show all static library data
- **Calculate fine amount** - Given days overdue, return fine amount ($0.50 per day)
- **Check if library is open** - Given current hour (0-23), return if library is open
- **Get library statistics** - Display comprehensive statistics
- **Update operating hours** - Change library hours for all books

#### **Instance Members (Individual book data)**
- **Unique book ID** (assigned automatically, never changes)
- **Title, Author, ISBN**
- **Publication year**
- **Checkout status** (available, checked out, overdue)
- **Current borrower name** (if checked out)
- **Due date** (if checked out)
- **Fine amount owed** (if overdue)

#### **Instance Methods (Individual book operations)**
- **Check out book** - Assign to borrower with due date (14 days from checkout)
- **Return book** - Process return, calculate fines if overdue
- **Display book details** - Show all book information including library context
- **Check if overdue** - Compare current date to due date
- **Update fine amount** - Calculate and update fine based on days overdue

---

## **The Implementation Guidelines**

### **🏗️ Constructor Requirements**
- Accept title, author, ISBN, and publication year
- Automatically assign unique book ID using static counter
- Initialize checkout status as "available"
- Update total book count

### **🔧 Core Functionality**
1. **Library Information Display**: Show library name, address, total books, operating hours
2. **Book Management**: Check out and return books with proper state tracking  
3. **Fine Calculation**: Automatic fine calculation for overdue books
4. **Statistics Tracking**: Track total fines collected, books in circulation
5. **Time-based Operations**: Library hours checking, due date management

### **📊 Required Statistics**
Your system should track and display:
- Total books in library system
- Books currently checked out
- Total fines collected
- Average fine per overdue book
- Library utilization metrics

---

## **The Testing Scenarios**

Create a main method that demonstrates:

1. **Library Setup**
   - Display initial library information
   - Show empty statistics

2. **Book Creation**
   - Add 5-8 books to the library
   - Display updated statistics

3. **Checkout Operations**
   - Check out several books to different borrowers
   - Show individual book status
   - Display library statistics

4. **Time Progression Simulation**
   - Simulate time passing (some books becoming overdue)
   - Update fine amounts
   - Show overdue status

5. **Return Operations**
   - Return books on time (no fines)
   - Return books late (with fines)
   - Process fine payments

6. **Library Management**
   - Change operating hours
   - Display comprehensive library report

---

## **The Advanced Challenges** ⭐

For those seeking deeper mastery:

1. **Book Categories**: Add static constants for book categories (Fiction, Non-Fiction, Reference)

2. **Borrower Management**: Track borrower information and borrowing history

3. **Reservation System**: Allow books to be reserved when checked out

4. **Fine Payment Processing**: Handle partial payments and payment tracking

5. **Library Network**: Support multiple library locations with shared statistics

6. **Search Functionality**: Implement book search by various criteria

---

## **The Evaluation Criteria**

Your solution will be judged on:

### **Correctness (40%)**
- Proper use of static vs instance members
- Accurate fine calculations
- Correct state management for book checkout/return
- Valid statistical tracking

### **Design (30%)**
- Appropriate choice between static and instance
- Clean separation of concerns
- Logical method organization
- Proper encapsulation

### **Functionality (20%)**
- All required features implemented
- Error handling for edge cases
- User-friendly output formatting
- Comprehensive testing scenarios

### **Code Quality (10%)**
- Clear variable names and comments
- Consistent formatting
- Efficient algorithms
- Following Java conventions

---

## **The Sacred Hints**

*These whispers from the ancient wisdom may guide your path:*

1. **Static for Universal**: Use static members for information that belongs to the library itself, not individual books

2. **Instance for Individual**: Use instance members for data that varies between different books

3. **ID Assignment**: Use a static counter to ensure each book gets a unique, sequential ID

4. **Date Handling**: For simplicity, you may use integers to represent days since checkout instead of complex date objects

5. **Fine Calculation**: Create a static utility method that any book can use to calculate fines

6. **State Validation**: Ensure books can't be checked out if already checked out, can't be returned if not checked out

---

## **Sample Expected Output**

```
🏛️ SACRED SCROLLS LIBRARY MANAGEMENT SYSTEM 🏛️

=== LIBRARY INFORMATION ===
Library: Sacred Scrolls Public Library  
Address: 123 Knowledge Ave, Wisdom City
Established: 1995
Operating Hours: 8:00 AM - 9:00 PM
Total Books: 0
Books Checked Out: 0
Total Fines Collected: $0.00

[Creating books...]
📚 Added: "The Art of Programming" by Master Coder (ID: 1001)
📚 Added: "Data Structures Unveiled" by Algorithm Sage (ID: 1002)
...

=== CHECKOUT OPERATIONS ===
✅ "The Art of Programming" checked out to Alice Johnson (Due: Day 14)
✅ "Data Structures Unveiled" checked out to Bob Smith (Due: Day 14)
...

=== RETURN OPERATIONS ===  
📖 "The Art of Programming" returned by Alice Johnson - ON TIME
💰 "Data Structures Unveiled" returned by Bob Smith - 3 days overdue, Fine: $1.50
...

=== FINAL LIBRARY STATISTICS ===
Total Books: 6
Books Available: 4  
Books Checked Out: 2
Total Fines Collected: $4.50
Average Fine: $2.25
```

---

## **The Moment of Truth**

*When you have completed your implementation, test it thoroughly. Does it demonstrate the sacred distinction between static and instance? Do the statistics update correctly? Can multiple books exist with individual states while sharing library-wide information?*

*Remember, young seeker: the goal is not just working code, but **understanding**. Each static member should belong to the library concept itself, while each instance member should belong to individual books.*

*The ancient wisdom of Static vs Instance flows through your design. Make it elegant, make it clear, make it worthy of the Inner Sanctum.*

---

**Proceed now to forge your solution. The Sacred Scrolls Library awaits your creation...**
