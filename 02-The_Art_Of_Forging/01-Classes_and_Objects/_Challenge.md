# 🎯 **The Pupil's Trial: Library Management System**
*Your First Journey into Object-Oriented Mastery*

---

## **📚 THE SACRED CHALLENGE**

*Young seeker, the time has come to forge your first Objects with your own hands. You have witnessed the power of Classes and Objects through the Bank Account system. Now, you must demonstrate your understanding by creating a complete **Library Management System** from the ground up.*

*This trial will test your mastery of:*
- **Class Design** - Creating blueprints that model real-world entities
- **Object Creation** - Bringing your classes to life as working instances
- **State Management** - Defining what your objects know about themselves
- **Behavior Implementation** - Defining what your objects can do
- **Object Interaction** - Making your objects work together harmoniously

---

## **🏛️ THE DOMAIN: DIGITAL LIBRARY**

*Your mission is to model a library system with the following real-world entities:*

### **📖 Book (Primary Entity)**
*Every book in your library should have:*
- **Title** - The name of the book
- **Author** - Who wrote the book  
- **ISBN** - Unique identifier (International Standard Book Number)
- **Genre** - Category (Fiction, Non-Fiction, Science, History, etc.)
- **Publication Year** - When it was published
- **Number of Pages** - How long the book is
- **Availability Status** - Whether it's available or checked out
- **Current Borrower** - Who has it (if checked out)

*Every book should be able to:*
- **Display its information** - Show all book details
- **Be checked out** - Change status to unavailable, record borrower
- **Be returned** - Change status back to available, clear borrower
- **Check if available** - Return whether it can be borrowed
- **Calculate reading time** - Estimate based on pages (assume 1 minute per page)

### **👤 LibraryMember (Secondary Entity)**
*Every library member should have:*
- **Member ID** - Unique identifier
- **Name** - Member's full name
- **Email** - Contact information
- **Phone Number** - Contact information
- **Membership Type** - Student, Faculty, Public, Premium
- **Books Currently Borrowed** - List of books they have
- **Total Books Borrowed** - Lifetime count
- **Member Since** - Year they joined

*Every member should be able to:*
- **Display their information** - Show member details and borrowed books
- **Check out a book** - Add book to their borrowed list
- **Return a book** - Remove book from their borrowed list
- **Check borrowing limit** - Different types have different limits
- **Calculate membership duration** - How long they've been a member

---

## **⚔️ THE TRIALS YOU MUST COMPLETE**

### **🥇 Trial 1: The Book Class (Foundation)**
*Create a complete Book class with all the state and behavior described above.*

**Requirements:**
1. All fields properly declared with appropriate data types
2. Method to initialize a book with all its information
3. Method to display complete book information in a formatted way
4. Methods to check out and return the book (with proper status updates)
5. Method to check if the book is available
6. Method to calculate estimated reading time
7. Proper handling of edge cases (can't check out unavailable book, etc.)

### **🥈 Trial 2: The LibraryMember Class (Expansion)**
*Create a complete LibraryMember class with all the state and behavior described above.*

**Requirements:**
1. All fields properly declared
2. Method to initialize a member with their information
3. Method to display complete member information
4. Methods to borrow and return books (updating their borrowed list)
5. Method to check their borrowing limit based on membership type
6. Method to calculate how long they've been a member
7. Proper validation (can't borrow more than limit, etc.)

### **🥉 Trial 3: The Library System (Integration)**
*Create a main class that demonstrates your objects working together.*

**Requirements:**
1. Create multiple Book objects with diverse information
2. Create multiple LibraryMember objects of different types
3. Demonstrate the complete library workflow:
   - Members checking out books
   - Members returning books
   - Displaying library inventory
   - Displaying member information
   - Handling error cases (unavailable books, over limit, etc.)
4. Show the power of objects by creating arrays of books and members
5. Implement a method to find books by title or author
6. Implement a method to find the most active member

---

## **📋 DETAILED SPECIFICATIONS**

### **Book Class Specifications**

```java
// Fields you must include (you may add more):
String title;
String author;
String isbn;
String genre;
int publicationYear;
int numberOfPages;
boolean isAvailable;
String currentBorrower; // Member name or null if available

// Methods you must implement:
void initializeBook(String title, String author, String isbn, 
                   String genre, int year, int pages)
void displayBookInfo()
boolean checkOut(String borrowerName)
boolean returnBook()
boolean isAvailable()
int calculateReadingTime() // in minutes
```

### **LibraryMember Class Specifications**

```java
// Fields you must include (you may add more):
String memberId;
String name;
String email;
String phoneNumber;
String membershipType; // "Student", "Faculty", "Public", "Premium"
int booksBorrowed; // Current count
int totalBooksBorrowed; // Lifetime count
int memberSince; // Year joined

// Methods you must implement:
void initializeMember(String id, String name, String email, 
                     String phone, String type, int yearJoined)
void displayMemberInfo()
boolean borrowBook(String bookTitle)
boolean returnBook(String bookTitle)
int getBorrowingLimit() // Different limits for different types
int calculateMembershipDuration() // Current year - memberSince
```

### **Borrowing Limits by Membership Type**
- **Student**: 3 books maximum
- **Faculty**: 10 books maximum  
- **Public**: 5 books maximum
- **Premium**: 15 books maximum

---

## **🎨 CREATIVE ENHANCEMENTS (Optional Mastery)**

*If you wish to demonstrate advanced understanding, consider adding:*

1. **Book Categories**: Create different types of books (Textbook, Novel, Reference)
2. **Overdue Tracking**: Add due dates and overdue calculations
3. **Book Reservations**: Allow members to reserve checked-out books
4. **Library Statistics**: Methods to calculate library usage statistics
5. **Search Functionality**: Advanced search by multiple criteria
6. **Member History**: Track complete borrowing history for each member

---

## **🧪 TEST SCENARIOS YOU MUST DEMONSTRATE**

*Your main method should demonstrate:*

### **Scenario 1: Basic Operations**
```java
// Create 5 different books with diverse information
// Create 3 different members with different membership types
// Show each member checking out different books
// Display all book and member information
```

### **Scenario 2: Error Handling**
```java
// Attempt to check out an unavailable book
// Attempt to have a member exceed their borrowing limit
// Attempt to return a book that wasn't borrowed
// Show proper error messages for each case
```

### **Scenario 3: Advanced Operations**
```java
// Demonstrate book returns and status changes
// Show the same book being checked out by different members over time
// Calculate and display reading times for different books
// Show membership duration calculations
```

### **Scenario 4: Library Management**
```java
// Create arrays of books and members
// Implement and demonstrate search functionality
// Show library statistics (total books, available books, active members)
// Demonstrate finding the most active member
```

---

## **📊 SUCCESS CRITERIA**

*Your solution will be judged on:*

### **🎯 Correctness (40%)**
- All required fields and methods implemented
- Proper data types used
- Logic works correctly for all scenarios
- Error cases handled appropriately

### **🏗️ Object-Oriented Design (30%)**
- Proper class structure with clear separation of concerns
- Fields represent object state appropriately
- Methods represent object behavior logically
- Good use of object interaction

### **💎 Code Quality (20%)**
- Clear, descriptive variable and method names
- Proper code formatting and indentation
- Helpful comments explaining complex logic
- No redundant or unnecessary code

### **🎪 Demonstration (10%)**
- Comprehensive test scenarios in main method
- Clear output showing all functionality
- Creative enhancements (if attempted)
- Evidence of understanding OOP principles

---

## **💡 HINTS FOR SUCCESS**

### **🧠 Design Thinking**
1. **Start with the real world** - Think about how libraries actually work
2. **Identify the nouns** - These become your classes (Book, Member)
3. **Identify the verbs** - These become your methods (checkOut, return, display)
4. **Consider relationships** - How do books and members interact?

### **🔧 Implementation Strategy**
1. **Start simple** - Implement basic Book class first
2. **Test frequently** - Create objects and test each method as you write it
3. **Build incrementally** - Add one feature at a time
4. **Think about edge cases** - What could go wrong? Handle it!

### **🎯 Common Pitfalls to Avoid**
1. **Don't make everything static** - Objects should have instance methods
2. **Don't forget initialization** - Objects need their starting values set
3. **Don't ignore validation** - Check for invalid inputs and states
4. **Don't forget object interaction** - Books and members should work together

---

## **🎭 THE MASTER'S EXPECTATIONS**

*When you complete this trial, you should be able to confidently say:*

- ✅ "I understand the difference between a Class and an Object"
- ✅ "I can design classes that model real-world entities"
- ✅ "I can create objects and make them interact with each other"
- ✅ "I can manage object state and implement object behavior"
- ✅ "I see how Object-Oriented Programming makes code more organized and reusable"

---

## **🚀 READY TO BEGIN?**

*Remember, young seeker:*

*"The master has failed more times than the beginner has even tried. Do not fear mistakes - they are the stepping stones to mastery. Start with the simplest version that works, then enhance it step by step."*

*"Your objects should mirror the real world. If you can explain your Book class to a librarian and they understand it, you've designed it well."*

*"Code is written once but read many times. Make it clear, make it logical, make it beautiful."*

**Begin with the Book class. Create one book, test it thoroughly, then move to the LibraryMember class. Finally, bring them together in your main method to create a living, breathing library system!**

*May your Objects be well-designed, your methods be purposeful, and your code be a reflection of the elegant systems that surround us in the real world.*

**Go forth and forge your first Objects!**

---

*When you have completed your trial, compare your solution with the master's implementation in the `_Solution/` directory. But remember - there are many correct ways to solve this challenge. The goal is understanding, not perfect replication.*
