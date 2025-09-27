# The Operators Challenge
*Your Trial in the Art of Data Manipulation*

---

## The Sacred Task

You have been chosen to create a **Smart Shopping Calculator** that demonstrates your mastery of all operator categories. This system will calculate prices, apply discounts, determine eligibility for offers, and provide detailed shopping analysis using the full spectrum of Java operators.

---

## Challenge Requirements

Create a Java program called `SmartShoppingCalculator.java` that accomplishes the following:

### **Part 1: Product Information and Basic Calculations**
Set up the following shopping scenario:

**Product Data:**
- Item 1: "Laptop" - Price: $899.99, Quantity: 2
- Item 2: "Mouse" - Price: $24.95, Quantity: 3  
- Item 3: "Keyboard" - Price: $79.50, Quantity: 1
- Item 4: "Monitor" - Price: $299.99, Quantity: 2

**Customer Information:**
- Customer name: Your choice
- Age: Your choice (affects senior discount eligibility)
- Is premium member: Your choice
- Years as customer: Your choice
- Has student ID: Your choice

### **Part 2: Arithmetic Operations**
Perform these calculations using appropriate operators:

1. **Subtotals**: Calculate subtotal for each item (price × quantity)
2. **Cart Total**: Sum all subtotals
3. **Tax Calculation**: Apply 8.25% sales tax
4. **Shipping Cost**: 
   - Free if total > $500
   - $15 for orders $100-$500
   - $25 for orders < $100

### **Part 3: Discount Logic (Relational and Logical Operators)**
Implement discount eligibility using complex conditions:

1. **Senior Discount (10%)**:
   - Age >= 65
   - Valid on orders > $200

2. **Student Discount (15%)**:
   - Has student ID
   - Age <= 30
   - Cannot combine with senior discount

3. **Premium Member Discount (12%)**:
   - Is premium member
   - Customer for >= 2 years

4. **Loyalty Discount (5%)**:
   - Customer for >= 5 years
   - Can stack with other discounts

5. **Volume Discount (8%)**:
   - Total quantity >= 6 items
   - Order total >= $300

### **Part 4: Assignment Operations**
Use compound assignment operators to:
- Apply each eligible discount to running total
- Accumulate total savings
- Track total items in cart
- Build a summary message string

### **Part 5: Ternary Operations**
Use ternary operators for:
- Determining shipping cost tier
- Selecting appropriate discount message
- Choosing payment processing fee (3% for credit, 0% for debit)
- Setting delivery timeframe (1-2 days premium, 3-5 days standard)

### **Part 6: Bitwise Operations (Bonus)**
Use bitwise operations to:
- Create a flags system for discount eligibility (each discount = 1 bit)
- Check if customer qualifies for express shipping (specific flag combination)
- Generate a unique transaction ID using bit manipulation

---

## Technical Requirements

### **Operator Usage:**
You must demonstrate proper use of:
- **Arithmetic**: `+`, `-`, `*`, `/`, `%`, `++`, `--`
- **Assignment**: `=`, `+=`, `-=`, `*=`, `/=`
- **Relational**: `==`, `!=`, `<`, `>`, `<=`, `>=`
- **Logical**: `&&`, `||`, `!`
- **Ternary**: `condition ? true : false`
- **Bitwise** (bonus): `&`, `|`, `^`, `<<`, `>>`

### **Complex Expressions:**
Create at least 3 complex expressions combining multiple operator types:
```java
// Example structure (create your own):
boolean complexEligibility = (age >= 18) && (yearsCustomer >= 1) && 
                            (cartTotal > 100.0) || (isPremium && hasValidId);
```

### **Calculations Required:**
1. Individual item subtotals
2. Cart subtotal
3. Tax amount
4. Shipping cost
5. Each discount amount (if applicable)
6. Final total after all discounts
7. Total savings
8. Payment processing fee

---

## Output Requirements

Your program should produce a detailed receipt showing:

### **Shopping Cart Summary:**
```
==================================================
              SMART SHOPPING CALCULATOR
==================================================

CUSTOMER INFORMATION:
Name: [Customer Name]
Age: [Age] years
Premium Member: [Yes/No]
Years as Customer: [X] years
Student ID: [Yes/No]

SHOPPING CART:
Item                 Price      Qty    Subtotal
------------------------------------------------
Laptop              $899.99  ×  2  =  $1,799.98
Mouse               $24.95   ×  3  =  $74.85
Keyboard            $79.50   ×  1  =  $79.50
Monitor             $299.99  ×  2  =  $599.98
------------------------------------------------
Cart Subtotal:                      $2,554.31
```

### **Discount Analysis:**
```
DISCOUNT ELIGIBILITY:
✓ Senior Discount (10%): Eligible - Age 67 >= 65
✗ Student Discount (15%): Not eligible - No student ID
✓ Premium Member (12%): Eligible - Premium for 3 years
✓ Loyalty Discount (5%): Eligible - Customer for 6 years
✓ Volume Discount (8%): Eligible - 8 items >= 6

APPLIED DISCOUNTS:
Senior Discount (10%):              -$255.43
Premium Member (12%):               -$306.52
Loyalty Discount (5%):              -$127.72
Volume Discount (8%):               -$204.34
------------------------------------------------
Total Savings:                      $894.01
```

### **Final Calculation:**
```
FINAL CALCULATION:
Cart Subtotal:                      $2,554.31
Total Discounts:                    -$894.01
Discounted Subtotal:                $1,660.30
Sales Tax (8.25%):                  +$136.97
Shipping Cost:                      FREE
Payment Processing (3%):            +$49.81
------------------------------------------------
FINAL TOTAL:                        $1,847.08

SUMMARY:
• Original Price: $2,554.31
• You Saved: $894.01 (35.0%)
• Tax Included: $136.97
• Payment Method: Credit Card
• Delivery: 1-2 business days (Premium)
```

---

## Bonus Challenges (Optional)

### **Bonus 1: Operator Precedence Mastery**
Create a complex calculation that demonstrates operator precedence:
```java
// Example: Calculate compound discount with precedence considerations
double complexCalculation = basePrice * (1 - discount1) * (1 - discount2) + tax;
```
Show the step-by-step evaluation with and without parentheses.

### **Bonus 2: Bitwise Flag System**
Implement a discount flags system:
```java
final int SENIOR_FLAG = 1;      // 0001
final int STUDENT_FLAG = 2;     // 0010
final int PREMIUM_FLAG = 4;     // 0100
final int LOYALTY_FLAG = 8;     // 1000
final int VOLUME_FLAG = 16;     // 10000

int discountFlags = 0;
// Set flags based on eligibility
// Use bitwise operations to check combinations
```

### **Bonus 3: Advanced Ternary Chains**
Create sophisticated ternary operator chains for shipping calculation:
```java
String shippingTier = (total >= 500) ? "FREE" :
                     (total >= 200) ? "STANDARD" :
                     (total >= 100) ? "ECONOMY" : "BASIC";
```

### **Bonus 4: Error Handling**
Add validation using logical operators:
- Negative quantities should be converted to positive
- Zero prices should trigger warnings
- Invalid discount combinations should be detected

---

## Success Criteria

Your solution will be considered successful when:

✅ **All operator types are used correctly**  
✅ **Complex discount logic works properly**  
✅ **Calculations are accurate to 2 decimal places**  
✅ **Output is well-formatted and professional**  
✅ **Code demonstrates understanding of operator precedence**  
✅ **Compound assignment operators are used efficiently**  
✅ **Ternary operators create clean conditional logic**  
✅ **Comments explain complex expressions**  

---

## Learning Objectives

This challenge will help you master:

1. **Arithmetic Precision**: Handling money calculations accurately
2. **Logical Complexity**: Combining multiple conditions elegantly
3. **Assignment Efficiency**: Using compound operators effectively
4. **Conditional Logic**: Implementing business rules with operators
5. **Expression Building**: Creating readable complex expressions
6. **Operator Precedence**: Understanding evaluation order
7. **Real-World Application**: Solving practical problems with operators

---

## Hints for Success

### **Calculation Tips:**
```java
// For precise money calculations
double price = Math.round(calculation * 100.0) / 100.0;

// For percentage calculations
double discountAmount = originalPrice * (discountPercent / 100.0);

// For compound discounts
double finalPrice = originalPrice * (1 - discount1) * (1 - discount2);
```

### **Logical Expression Patterns:**
```java
// Range checking
boolean inRange = (value >= min) && (value <= max);

// Multiple conditions with priority
boolean eligible = highPriority || (mediumPriority && condition);

// Null-safe checking
boolean safe = (object != null) && (object.isValid());
```

### **Common Pitfalls to Avoid:**
- **Integer division**: Use `double` for money calculations
- **Floating-point precision**: Round final monetary values
- **Operator precedence**: Use parentheses for clarity
- **String comparison**: Use `.equals()` not `==`
- **Compound conditions**: Consider short-circuit evaluation

### **Testing Your Logic:**
Test with different scenarios:
- Young premium member with large order
- Senior citizen with small order
- Student with medium order
- Long-term customer with volume purchase
- Edge cases (exactly at threshold values)

---

*"The true master of operators does not merely perform calculations - they orchestrate a symphony of logic, arithmetic, and decision-making. Show me that you can conduct this symphony with precision and elegance."*
