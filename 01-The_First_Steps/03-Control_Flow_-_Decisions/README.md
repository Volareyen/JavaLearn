# Control Flow - Decisions
*The Art of Teaching Your Programs to Think*

---

## The Philosophy of Decision Making

Until now, your programs have been like rivers flowing in a straight line - predictable, linear, following a single path from beginning to end. But true intelligence requires the ability to **choose** - to examine conditions and alter behavior accordingly.

**Control flow** is the art of directing your program's execution path. It transforms your code from a simple sequence of instructions into a thinking entity capable of adapting to different situations. This is where your programs begin to exhibit what resembles intelligence.

---

## The Nature of Conditional Logic

In life, we constantly make decisions:
- *"If it's raining, I'll take an umbrella."*
- *"If I'm hungry, I'll eat something."*
- *"If the traffic light is red, I'll stop."*

Programming mirrors this natural decision-making process. We examine **conditions** (boolean expressions) and execute different blocks of code based on whether those conditions are true or false.

---

## The `if` Statement - The Foundation of Choice

The `if` statement is the most fundamental decision-making structure in programming. It asks a question and executes code only if the answer is "yes" (true).

### **Basic `if` Statement**
```java
if (condition) {
    // Code executed only if condition is true
}
```

### **Real-World Example**
```java
int temperature = 85;

if (temperature > 80) {
    System.out.println("It's hot outside! Stay hydrated.");
}

// This line executes regardless of the condition
System.out.println("Have a great day!");
```

**Key Points:**
- The condition must be a boolean expression (evaluates to `true` or `false`)
- Code inside the braces `{}` only executes if the condition is `true`
- If the condition is `false`, the code block is skipped entirely
- Execution continues with the next statement after the `if` block

### **The Power of Boolean Expressions**
```java
int age = 17;
boolean hasLicense = true;
double accountBalance = 250.50;

// Simple conditions
if (age >= 18) {
    System.out.println("You are an adult.");
}

// Complex conditions with logical operators
if ((age >= 16) && hasLicense) {
    System.out.println("You can drive!");
}

// Multiple conditions
if ((accountBalance > 100.0) && (age >= 18) && hasLicense) {
    System.out.println("You can rent a car!");
}
```

---

## The `if-else` Statement - The Path of Two Choices

Life rarely offers just one option. Usually, we have at least two paths: "do this" or "do that instead." The `if-else` statement provides exactly this capability.

### **Basic `if-else` Structure**
```java
if (condition) {
    // Code executed if condition is true
} else {
    // Code executed if condition is false
}
```

### **Practical Example**
```java
int score = 75;

if (score >= 60) {
    System.out.println("Congratulations! You passed.");
    System.out.println("Your grade has been recorded.");
} else {
    System.out.println("Unfortunately, you did not pass.");
    System.out.println("Please consider retaking the exam.");
}

System.out.println("Thank you for your effort.");
```

**The Guarantee**: With `if-else`, exactly one of the two code blocks will always execute. There's no middle ground, no skipping both - your program must choose one path or the other.

---

## The `if-else if-else` Chain - Multiple Pathways

Real-world decisions often involve more than two options. Consider grading: A, B, C, D, or F. Or weather responses: sunny, cloudy, rainy, or stormy. The `if-else if-else` chain handles these multi-way decisions elegantly.

### **Structure for Multiple Decisions**
```java
if (condition1) {
    // Execute if condition1 is true
} else if (condition2) {
    // Execute if condition1 is false AND condition2 is true
} else if (condition3) {
    // Execute if condition1 and condition2 are false AND condition3 is true
} else {
    // Execute if all conditions are false (optional catch-all)
}
```

### **Grade Calculator Example**
```java
int score = 87;
char letterGrade;
String message;

if (score >= 90) {
    letterGrade = 'A';
    message = "Excellent work!";
} else if (score >= 80) {
    letterGrade = 'B';
    message = "Good job!";
} else if (score >= 70) {
    letterGrade = 'C';
    message = "Satisfactory performance.";
} else if (score >= 60) {
    letterGrade = 'D';
    message = "You passed, but consider improvement.";
} else {
    letterGrade = 'F';
    message = "Unfortunately, you did not pass.";
}

System.out.println("Score: " + score);
System.out.println("Grade: " + letterGrade);
System.out.println("Comment: " + message);
```

**Critical Understanding**: The conditions are evaluated **in order**, and only the **first true condition** executes. Once a condition is met, all subsequent conditions in the chain are skipped.

### **Order Matters - A Cautionary Tale**
```java
int score = 95;

// CORRECT order (specific to general)
if (score >= 90) {
    System.out.println("A grade");  // This executes for score 95
} else if (score >= 80) {
    System.out.println("B grade");  // This is skipped
} else if (score >= 70) {
    System.out.println("C grade");  // This is skipped
}

// INCORRECT order (general to specific)
if (score >= 70) {
    System.out.println("C grade");  // This would execute for score 95!
} else if (score >= 80) {
    System.out.println("B grade");  // This would never execute
} else if (score >= 90) {
    System.out.println("A grade");  // This would never execute
}
```

---

## Nested `if` Statements - Decisions Within Decisions

Sometimes, making one decision leads to the need for another decision. This is where **nested `if` statements** become powerful - placing `if` statements inside other `if` statements.

### **Basic Nested Structure**
```java
if (outerCondition) {
    // Code that executes if outerCondition is true
    
    if (innerCondition) {
        // Code that executes if BOTH outerCondition AND innerCondition are true
    } else {
        // Code that executes if outerCondition is true BUT innerCondition is false
    }
    
    // More code that executes if outerCondition is true
} else {
    // Code that executes if outerCondition is false
}
```

### **Real-World Example: Bank Account Access**
```java
boolean hasAccount = true;
String accountType = "premium";
double balance = 1500.00;
boolean hasValidId = true;

if (hasAccount) {
    System.out.println("Account found. Verifying access...");
    
    if (hasValidId) {
        System.out.println("Identity verified. Checking account type...");
        
        if (accountType.equals("premium")) {
            System.out.println("Premium account access granted.");
            
            if (balance > 1000.00) {
                System.out.println("You qualify for premium services.");
            } else {
                System.out.println("Premium account with standard services.");
            }
            
        } else if (accountType.equals("standard")) {
            System.out.println("Standard account access granted.");
        } else {
            System.out.println("Unknown account type. Please contact support.");
        }
        
    } else {
        System.out.println("Access denied. Invalid identification.");
    }
    
} else {
    System.out.println("No account found. Please register first.");
}
```

**When to Use Nesting**:
- When decisions depend on previous decisions
- When you need to check multiple related conditions in sequence
- When the logic naturally forms a hierarchy

**When to Avoid Deep Nesting**:
- More than 3-4 levels deep becomes hard to read
- Consider using logical operators (`&&`, `||`) to combine conditions
- Consider breaking complex logic into separate methods

---

## The `switch` Statement - The Multi-Path Gateway

When you have many possible values for a single variable and need different behavior for each, the `switch` statement provides an elegant alternative to long `if-else if` chains.

### **Basic `switch` Structure**
```java
switch (variable) {
    case value1:
        // Code for value1
        break;
    case value2:
        // Code for value2
        break;
    case value3:
        // Code for value3
        break;
    default:
        // Code for all other values (optional)
        break;
}
```

### **Day of Week Example**
```java
int dayNumber = 3;
String dayName;
String dayType;

switch (dayNumber) {
    case 1:
        dayName = "Monday";
        dayType = "Weekday - Start of work week";
        break;
    case 2:
        dayName = "Tuesday";
        dayType = "Weekday - Getting into rhythm";
        break;
    case 3:
        dayName = "Wednesday";
        dayType = "Weekday - Hump day!";
        break;
    case 4:
        dayName = "Thursday";
        dayType = "Weekday - Almost there";
        break;
    case 5:
        dayName = "Friday";
        dayType = "Weekday - TGIF!";
        break;
    case 6:
        dayName = "Saturday";
        dayType = "Weekend - Relax time";
        break;
    case 7:
        dayName = "Sunday";
        dayType = "Weekend - Prepare for next week";
        break;
    default:
        dayName = "Unknown";
        dayType = "Invalid day number";
        break;
}

System.out.println("Day " + dayNumber + ": " + dayName);
System.out.println("Type: " + dayType);
```

### **The Critical Importance of `break`**

Without `break` statements, execution "falls through" to subsequent cases:

```java
int grade = 2;

// WITHOUT break statements (dangerous!)
switch (grade) {
    case 1:
        System.out.println("Freshman");
    case 2:
        System.out.println("Sophomore");  // This executes
    case 3:
        System.out.println("Junior");     // This ALSO executes!
    case 4:
        System.out.println("Senior");     // This ALSO executes!
    default:
        System.out.println("Graduate");   // This ALSO executes!
}
// Output: Sophomore, Junior, Senior, Graduate (all of them!)

// WITH break statements (correct)
switch (grade) {
    case 1:
        System.out.println("Freshman");
        break;
    case 2:
        System.out.println("Sophomore");  // Only this executes
        break;
    case 3:
        System.out.println("Junior");
        break;
    case 4:
        System.out.println("Senior");
        break;
    default:
        System.out.println("Graduate");
        break;
}
// Output: Sophomore (only this one)
```

### **Intentional Fall-Through (Advanced Technique)**

Sometimes, fall-through behavior is desired:

```java
char grade = 'B';

switch (grade) {
    case 'A':
    case 'B':
    case 'C':
        System.out.println("You passed!");
        break;
    case 'D':
    case 'F':
        System.out.println("You need to retake the course.");
        break;
    default:
        System.out.println("Invalid grade.");
        break;
}
```

### **Switch with Strings (Java 7+)**

Modern Java allows switching on String values:

```java
String userCommand = "save";

switch (userCommand.toLowerCase()) {
    case "save":
        System.out.println("Saving your work...");
        break;
    case "load":
        System.out.println("Loading previous work...");
        break;
    case "exit":
        System.out.println("Goodbye!");
        break;
    case "help":
        System.out.println("Available commands: save, load, exit, help");
        break;
    default:
        System.out.println("Unknown command. Type 'help' for assistance.");
        break;
}
```

---

## When to Use Each Decision Structure

### **Use `if` when:**
- You have a simple yes/no decision
- The condition is complex (multiple logical operators)
- You're checking ranges of values
- The decision is based on multiple variables

### **Use `if-else` when:**
- You have exactly two mutually exclusive options
- You want to guarantee that one of two actions always occurs

### **Use `if-else if-else` when:**
- You have multiple conditions to check in order
- The conditions involve ranges or complex expressions
- You need the flexibility of different condition types

### **Use `switch` when:**
- You're comparing one variable against multiple specific values
- You have many discrete options (5 or more)
- The values are constants (literals or final variables)
- You want cleaner, more readable code for multiple options

---

## Common Patterns and Best Practices

### **Guard Clauses - Early Returns**
```java
// Instead of deep nesting:
if (user != null) {
    if (user.isActive()) {
        if (user.hasPermission()) {
            // Main logic here
        }
    }
}

// Use guard clauses:
if (user == null) {
    System.out.println("No user found");
    return;
}
if (!user.isActive()) {
    System.out.println("User account is inactive");
    return;
}
if (!user.hasPermission()) {
    System.out.println("Access denied");
    return;
}
// Main logic here
```

### **Boolean Flag Pattern**
```java
boolean isEligible = false;

if ((age >= 18) && (income > 30000) && (creditScore > 650)) {
    isEligible = true;
}

if (isEligible) {
    System.out.println("Loan approved!");
} else {
    System.out.println("Loan denied.");
}
```

### **Range Checking Pattern**
```java
int temperature = 75;

if (temperature < 32) {
    System.out.println("Freezing");
} else if (temperature < 60) {
    System.out.println("Cold");
} else if (temperature < 80) {
    System.out.println("Comfortable");
} else if (temperature < 90) {
    System.out.println("Warm");
} else {
    System.out.println("Hot");
}
```

---

## Common Pitfalls and How to Avoid Them

### **1. Assignment vs. Comparison**
```java
int x = 5;

// WRONG - assignment instead of comparison
if (x = 10) {  // Compilation error in Java (thankfully!)
    System.out.println("x is 10");
}

// CORRECT - comparison
if (x == 10) {
    System.out.println("x is 10");
}
```

### **2. Floating-Point Equality**
```java
double result = 0.1 + 0.2;

// DANGEROUS - floating-point precision issues
if (result == 0.3) {  // Might be false due to precision!
    System.out.println("Equal");
}

// SAFE - use tolerance
double tolerance = 0.0001;
if (Math.abs(result - 0.3) < tolerance) {
    System.out.println("Equal within tolerance");
}
```

### **3. Null Pointer Dangers**
```java
String text = null;

// DANGEROUS - NullPointerException risk
if (text.equals("hello")) {  // Crashes if text is null!
    System.out.println("Found hello");
}

// SAFE - null check first
if (text != null && text.equals("hello")) {
    System.out.println("Found hello");
}

// EVEN SAFER - constant first
if ("hello".equals(text)) {  // Never crashes
    System.out.println("Found hello");
}
```

### **4. Missing Braces**
```java
// DANGEROUS - only first statement is conditional
if (temperature > 100)
    System.out.println("It's hot!");
    System.out.println("Stay hydrated!");  // Always executes!

// SAFE - always use braces
if (temperature > 100) {
    System.out.println("It's hot!");
    System.out.println("Stay hydrated!");  // Both conditional
}
```

---

## The Philosophical Implications

Decision-making in programming mirrors decision-making in life:

### **Determinism vs. Choice**
Your programs become deterministic decision-makers - given the same input, they will always make the same choice. This predictability is both a strength (reliability) and a limitation (lack of creativity).

### **The Power of Abstraction**
Conditional logic allows you to abstract complex real-world decision processes into simple, logical rules. A credit approval system, a game AI, a medical diagnosis tool - all rely on the same fundamental `if-then` logic.

### **Complexity from Simplicity**
Complex, intelligent-seeming behavior emerges from combinations of simple decisions. A chess program doesn't "think" - it evaluates millions of `if-then` scenarios very quickly.

---

*"To give your program the power of choice is to breathe the first spark of intelligence into your creation. Master the art of conditional logic, and your programs will no longer be mere calculators - they will be decision-makers, capable of adapting to the infinite variety of the world around them."*
