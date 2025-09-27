# The Methods Challenge
*Your Trial in the Art of Modular Programming and Code Architecture*

---

## The Sacred Task

You have been chosen to create a **Comprehensive Restaurant Management System** that demonstrates your mastery of methods in all their forms. This system will handle menu management, order processing, inventory tracking, and business analytics using sophisticated method design and organization.

---

## Challenge Requirements

Create a Java program called `RestaurantManagementSystem.java` that accomplishes the following:

### **Part 1: Core Data Management Methods**
Design methods to handle the restaurant's core data structures:

**Menu Management:**
- Store menu items with: name, category, price, ingredients, preparation time
- Categories: Appetizers, Main Courses, Desserts, Beverages
- Track item availability and popularity scores

**Inventory System:**
- Track ingredients with: name, quantity, unit, cost per unit, supplier
- Monitor stock levels and reorder points
- Calculate total inventory value

**Staff Management:**
- Store staff information: name, role, hourly wage, hours worked
- Roles: Chef, Server, Manager, Cashier
- Track performance ratings and schedule

### **Part 2: Validation and Utility Methods**
Create comprehensive validation and utility methods:

**Data Validation:**
```java
// Method signatures you must implement:
public static boolean isValidMenuItem(String name, String category, double price)
public static boolean isValidIngredient(String name, int quantity, String unit)
public static boolean isValidStaffMember(String name, String role, double wage)
public static boolean isValidOrder(int tableNumber, String[] items)
```

**Utility Methods:**
```java
public static String formatCurrency(double amount)
public static String formatTime(int minutes)
public static double calculateTax(double amount, double taxRate)
public static String generateOrderId()
public static String capitalizeWords(String text)
```

### **Part 3: Search and Retrieval Methods**
Implement various search capabilities:

**Menu Search Methods:**
- Find items by name (exact and partial matches)
- Find items by category
- Find items by price range
- Find items by preparation time
- Get available items only

**Advanced Search:**
- Search by multiple criteria simultaneously
- Sort results by different attributes (price, popularity, prep time)
- Filter by dietary restrictions (vegetarian, gluten-free, etc.)

### **Part 4: Business Logic Methods**
Create methods that implement restaurant business rules:

**Order Processing:**
```java
public static boolean canFulfillOrder(String[] menuItems)
public static double calculateOrderTotal(String[] items, boolean includeTax)
public static int calculatePreparationTime(String[] items)
public static boolean applyDiscount(String discountType, double orderTotal)
```

**Inventory Management:**
```java
public static boolean isIngredientAvailable(String ingredient, int requiredAmount)
public static void updateInventoryAfterOrder(String[] menuItems)
public static String[] getItemsNeedingRestock()
public static double calculateIngredientCost(String menuItem)
```

**Staff Scheduling:**
```java
public static boolean isStaffAvailable(String staffName, int hour)
public static double calculateDailyWages(String[] workingStaff, int[] hoursWorked)
public static String[] getOptimalStaffSchedule(int expectedCustomers)
```

### **Part 5: Method Overloading Demonstrations**
Show mastery of method overloading with multiple versions of key methods:

**Calculate Total Methods:**
```java
public static double calculateTotal(double baseAmount)  // Just add default tax
public static double calculateTotal(double baseAmount, double taxRate)  // Custom tax
public static double calculateTotal(double baseAmount, double taxRate, double tipPercent)  // Tax + tip
public static double calculateTotal(String[] items)  // Calculate from menu items
public static double calculateTotal(String[] items, String discountCode)  // With discount
```

**Display Methods:**
```java
public static void displayMenu()  // Show all items
public static void displayMenu(String category)  // Show specific category
public static void displayMenu(double minPrice, double maxPrice)  // Price range
public static void displayMenu(boolean availableOnly)  // Filter by availability
```

### **Part 6: Recursive Methods**
Implement sophisticated recursive algorithms:

**Menu Hierarchy Navigation:**
```java
// Navigate nested menu categories (Appetizers -> Hot -> Soups -> Vegetarian)
public static void displayMenuHierarchy(String[] categories, int depth)
```

**Order Combination Generation:**
```java
// Generate all possible meal combinations within a budget
public static int findMealCombinations(String[] items, double[] prices, double budget, int index)
```

**Staff Scheduling Optimization:**
```java
// Recursively find optimal staff assignments
public static boolean assignStaffOptimally(String[] staff, String[] shifts, int staffIndex)
```

### **Part 7: Interactive System Methods**
Create a complete menu-driven system:

**Main Menu System:**
- Restaurant overview and daily summary
- Menu management operations
- Order processing system
- Inventory management
- Staff management
- Reports and analytics
- System maintenance

**Each subsystem should have:**
- Input validation with retry logic
- Clear error messages and user guidance
- Confirmation for destructive operations
- Professional formatting and presentation

---

## Technical Requirements

### **Method Design Principles:**
- **Single Responsibility**: Each method should do one thing well
- **Meaningful Names**: Method names should clearly indicate their purpose
- **Parameter Validation**: All methods should validate their inputs
- **Consistent Return Types**: Use appropriate return types for each method's purpose
- **Error Handling**: Graceful handling of edge cases and invalid data

### **Method Categories Required:**
- **Void Methods**: At least 10 methods that perform actions without returning values
- **Return Methods**: At least 15 methods that calculate and return results
- **Boolean Methods**: At least 8 validation and condition-checking methods
- **Search Methods**: At least 6 methods that find and retrieve data
- **Overloaded Methods**: At least 3 sets of overloaded methods (3+ versions each)
- **Recursive Methods**: At least 2 meaningful recursive algorithms
- **Utility Methods**: At least 8 helper methods for common operations

### **Data Structures:**
Use arrays to simulate database tables:
```java
// Example structure (you can modify as needed)
private static String[] menuItemNames = new String[100];
private static String[] menuItemCategories = new String[100];
private static double[] menuItemPrices = new double[100];
private static int[] menuItemPrepTimes = new int[100];
private static boolean[] menuItemAvailability = new boolean[100];
private static int menuItemCount = 0;

// Similar structures for ingredients, staff, orders, etc.
```

---

## Expected Output Format

Your system should produce professional output like this:

```
==================================================
        GRAND PALACE RESTAURANT
         MANAGEMENT SYSTEM v3.0
==================================================

🍽️  DAILY OVERVIEW - March 15, 2024
   • Menu Items: 45 (38 available, 7 out of stock)
   • Staff On Duty: 12 (3 chefs, 6 servers, 2 managers, 1 cashier)
   • Inventory Value: $12,847.50
   • Today's Orders: 23 ($1,456.75 revenue)

==================================================
                 MAIN MENU
==================================================
1. 📋 Menu Management
2. 🛒 Order Processing  
3. 📦 Inventory Management
4. 👥 Staff Management
5. 📊 Reports & Analytics
6. 🔧 System Maintenance
7. 🚪 Exit System

Enter your choice (1-7): 1

==================================================
              MENU MANAGEMENT
==================================================
1. View Complete Menu
2. Add New Menu Item
3. Update Existing Item
4. Remove Menu Item
5. Search Menu Items
6. Category Management
7. Price Analysis
8. Return to Main Menu

Enter your choice (1-8): 1

==================================================
               COMPLETE MENU
==================================================

🥗 APPETIZERS (8 items)
   Caesar Salad........................$8.95  [15 min] ✓
   Chicken Wings (12pc)................$12.50 [20 min] ✓
   Mozzarella Sticks...................$7.95  [12 min] ✗
   Spinach Artichoke Dip...............$9.95  [10 min] ✓

🍖 MAIN COURSES (18 items)
   Grilled Salmon......................$24.95 [25 min] ✓
   Ribeye Steak (12oz).................$32.95 [30 min] ✓
   Chicken Parmesan....................$18.95 [22 min] ✓
   Vegetarian Pasta....................$16.95 [18 min] ✗

🍰 DESSERTS (6 items)
   Chocolate Cake......................$6.95  [5 min]  ✓
   Tiramisu...........................$7.95  [5 min]  ✓
   Ice Cream Sundae...................$5.95  [8 min]  ✓

🥤 BEVERAGES (13 items)
   Fresh Orange Juice..................$3.95  [2 min]  ✓
   Coffee.............................$2.95  [3 min]  ✓
   House Wine (glass)..................$8.95  [1 min]  ✓

==================================================
Menu Statistics:
• Total Items: 45
• Average Price: $12.47
• Average Prep Time: 14 minutes
• Most Expensive: Ribeye Steak ($32.95)
• Quickest Item: House Wine (1 minute)
• Most Popular: Grilled Salmon (4.8/5 stars)
==================================================

Continue to order processing? (y/n): y

==================================================
              ORDER PROCESSING
==================================================

📝 NEW ORDER #ORD-2024-0315-0024
   Table Number: 7
   Server: Maria Rodriguez
   Time: 7:42 PM

🛒 ORDER ITEMS:
   1. Caesar Salad........................$8.95
   2. Grilled Salmon......................$24.95
   3. House Wine (glass)...................$8.95
   4. Chocolate Cake......................$6.95

💰 ORDER SUMMARY:
   Subtotal:..............................$49.80
   Tax (8.25%):...........................$4.11
   Suggested Tip (18%):...................$9.70
   ----------------------------------------
   Total:................................$63.61

⏱️  PREPARATION TIME:
   Caesar Salad: 15 min (ready at 7:57 PM)
   Grilled Salmon: 25 min (ready at 8:07 PM)
   House Wine: 1 min (ready at 7:43 PM)
   Chocolate Cake: 5 min (ready at 7:47 PM)
   
   Estimated completion: 8:07 PM (25 minutes)

✅ Order confirmed! Kitchen notified.
📧 Receipt sent to table 7.
```

---

## Bonus Challenges (Optional)

### **Bonus 1: Advanced Analytics Methods**
Implement sophisticated business analytics:
```java
public static double calculateProfitMargin(String menuItem)
public static String[] predictPopularItems(int dayOfWeek, int hour)
public static double optimizeMenuPricing(String category)
public static String generateBusinessInsights()
```

### **Bonus 2: Recipe Management System**
Create methods to handle complex recipes:
```java
public static boolean addRecipe(String itemName, String[] ingredients, int[] quantities)
public static double calculateRecipeCost(String itemName)
public static boolean canMakeItem(String itemName, int quantity)
public static String[] getRecipeInstructions(String itemName)
```

### **Bonus 3: Customer Management**
Implement customer tracking and loyalty:
```java
public static void addCustomer(String name, String phone, String preferences)
public static double applyLoyaltyDiscount(String customerId, double orderTotal)
public static String[] getCustomerRecommendations(String customerId)
public static void updateCustomerPreferences(String customerId, String[] newPreferences)
```

### **Bonus 4: Advanced Scheduling Algorithm**
Create an intelligent staff scheduling system:
```java
public static String[][] generateWeeklySchedule(String[] staff, String[] shifts, int[] requirements)
public static double optimizeStaffCosts(String[][] schedule, double[] wages)
public static boolean handleStaffCallOut(String staffName, String shift, String replacement)
```

---

## Success Criteria

Your solution will be considered successful when:

✅ **All required method categories are implemented correctly**  
✅ **Method overloading demonstrates different parameter patterns**  
✅ **Recursive methods solve meaningful problems**  
✅ **Input validation prevents system crashes**  
✅ **Business logic methods implement realistic restaurant rules**  
✅ **Search methods provide flexible data retrieval**  
✅ **Interactive system provides professional user experience**  
✅ **Code is well-organized with clear method responsibilities**  
✅ **Error handling provides helpful feedback**  
✅ **Output formatting is professional and readable**  

---

## Learning Objectives

This challenge will help you master:

1. **Method Architecture**: Designing systems with well-organized method hierarchies
2. **Parameter Design**: Creating methods with appropriate parameter lists
3. **Return Type Selection**: Choosing optimal return types for different purposes
4. **Method Overloading**: Implementing multiple versions of methods effectively
5. **Recursive Thinking**: Solving complex problems with recursive approaches
6. **Validation Patterns**: Building robust input validation systems
7. **Business Logic**: Implementing real-world business rules in code
8. **User Experience**: Creating intuitive interactive systems
9. **Code Organization**: Structuring large programs with clear method separation
10. **Professional Development**: Building production-quality software systems

---

## Hints for Success

### **Method Organization Strategy:**
```java
// Group related methods together with clear comments
// ============================================================================
// MENU MANAGEMENT METHODS
// ============================================================================

// ============================================================================  
// ORDER PROCESSING METHODS
// ============================================================================

// ============================================================================
// VALIDATION AND UTILITY METHODS
// ============================================================================
```

### **Parameter Validation Pattern:**
```java
public static boolean addMenuItem(String name, String category, double price) {
    // Always validate parameters first
    if (name == null || name.trim().isEmpty()) {
        System.out.println("❌ Menu item name cannot be empty");
        return false;
    }
    
    if (price < 0) {
        System.out.println("❌ Price cannot be negative");
        return false;
    }
    
    // Then perform the operation
    // ... implementation
    
    return true;
}
```

### **Search Method Pattern:**
```java
public static int findMenuItemByName(String name) {
    if (name == null) return -1;
    
    String searchName = name.toLowerCase().trim();
    
    for (int i = 0; i < menuItemCount; i++) {
        if (menuItemNames[i].toLowerCase().contains(searchName)) {
            return i;
        }
    }
    
    return -1; // Not found
}
```

### **Method Overloading Guidelines:**
- Ensure each version serves a distinct purpose
- Use consistent naming and behavior across versions
- Document the differences between overloaded versions
- Avoid overloading solely based on return type

### **Recursive Method Tips:**
- Always define clear base cases
- Ensure each recursive call progresses toward the base case
- Consider iterative alternatives for performance-critical code
- Test with small examples first

### **Common Pitfalls to Avoid:**
- **Methods doing too much**: Keep methods focused on single responsibilities
- **Poor parameter validation**: Always validate inputs before processing
- **Inconsistent return patterns**: Use consistent conventions (e.g., -1 for not found)
- **Unclear method names**: Names should clearly indicate what the method does
- **Missing error handling**: Handle edge cases gracefully

### **Testing Strategy:**
- Test each method individually with various inputs
- Test edge cases (empty arrays, null values, boundary conditions)
- Test method interactions in realistic scenarios
- Verify that overloaded methods behave correctly
- Test recursive methods with different depth levels

---

*"The master of methods does not merely write functions - they architect solutions. They create systems where each method serves a clear purpose, where complex problems are broken into manageable pieces, and where code becomes a living, breathing representation of business logic. Show me that you can build such a system - elegant, maintainable, and powerful."*
