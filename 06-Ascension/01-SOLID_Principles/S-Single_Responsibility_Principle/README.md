# S — The Single Responsibility Principle

*"A class should have only one reason to change."*

---

## **The Essence**

Every class should do **one thing and do it well**. If a class has multiple responsibilities, changes to one responsibility risk breaking the other. It becomes fragile, hard to test, and impossible to reuse.

## **The Violation**

```java
// BAD: This class does THREE things
public class Employee {
    public double calculatePay() { /* payroll logic */ }
    public void saveToDatabase() { /* persistence logic */ }
    public String generateReport() { /* reporting logic */ }
}
```

If the database changes, you modify Employee. If the report format changes, you modify Employee. If payroll rules change, you modify Employee. Three reasons to change = three responsibilities = violation.

## **The Solution**

```java
public class Employee { /* only data and domain behavior */ }
public class PayrollCalculator { /* only pay calculation */ }
public class EmployeeRepository { /* only persistence */ }
public class EmployeeReportGenerator { /* only reporting */ }
```

Now each class has **one reason to change**. They can be tested, reused, and modified independently.

---

## **Your Trial Awaits**

Study `_Concept.java` for the syntax, `_PracticalExample.java` for a real refactoring, then face `_Challenge.md`.
