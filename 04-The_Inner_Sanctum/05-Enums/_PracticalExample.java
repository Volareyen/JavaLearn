/**
 * _PracticalExample.java - The Living Manuscript of Enums
 * 
 * MYSTIC COFFEE HOUSE - Order Management System
 * 
 * This real-world example demonstrates enums solving actual business problems
 * in a coffee house order management system. Witness how enums bring type safety,
 * meaningful constants, and rich behavior to complex business logic.
 * 
 * "Behold how enums transform chaos into order, numbers into meaning,
 * and simple constants into intelligent entities!"
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// ========================================
// BUSINESS DOMAIN ENUMS
// ========================================

/**
 * Menu items with pricing and calorie information
 * Demonstrates enums with constructor parameters and computed properties
 */
enum MenuItem {
    // Hot Beverages
    ESPRESSO(2.50, 5, "Rich, bold coffee shot", "Hot Beverages"),
    AMERICANO(3.00, 10, "Espresso with hot water", "Hot Beverages"),
    CAPPUCCINO(4.50, 120, "Espresso with steamed milk and foam", "Hot Beverages"),
    LATTE(5.00, 150, "Espresso with steamed milk", "Hot Beverages"),
    MOCHA(5.50, 290, "Chocolate espresso drink", "Hot Beverages"),
    
    // Cold Beverages
    ICED_COFFEE(3.50, 15, "Cold brewed coffee over ice", "Cold Beverages"),
    FRAPPUCCINO(6.00, 420, "Blended coffee drink", "Cold Beverages"),
    COLD_BREW(4.00, 5, "Smooth cold extracted coffee", "Cold Beverages"),
    
    // Food Items
    CROISSANT(3.50, 280, "Buttery French pastry", "Pastries"),
    MUFFIN(2.75, 320, "Fresh baked muffin", "Pastries"),
    SANDWICH(7.50, 450, "Artisan sandwich", "Food"),
    BAGEL(2.25, 250, "Fresh baked bagel", "Food");
    
    private final double price;
    private final int calories;
    private final String description;
    private final String category;
    
    MenuItem(double price, int calories, String description, String category) {
        this.price = price;
        this.calories = calories;
        this.description = description;
        this.category = category;
    }
    
    public double getPrice() { return price; }
    public int getCalories() { return calories; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    
    // Business logic methods
    public boolean isHealthy() {
        return calories < 200;
    }
    
    public boolean isBeverage() {
        return category.contains("Beverages");
    }
    
    public String getPriceDisplay() {
        return String.format("$%.2f", price);
    }
    
    // Static utility methods for business queries
    public static MenuItem[] getHealthyOptions() {
        return Arrays.stream(values())
                .filter(MenuItem::isHealthy)
                .toArray(MenuItem[]::new);
    }
    
    public static MenuItem[] getByCategory(String category) {
        return Arrays.stream(values())
                .filter(item -> item.getCategory().equals(category))
                .toArray(MenuItem[]::new);
    }
    
    public static double getAveragePrice() {
        return Arrays.stream(values())
                .mapToDouble(MenuItem::getPrice)
                .average()
                .orElse(0.0);
    }
}

/**
 * Order status with workflow logic and validation
 * Demonstrates enum with complex business rules and state transitions
 */
enum OrderStatus {
    PENDING("Order received, preparing...", true, false, false) {
        @Override
        public OrderStatus getNextStatus() { return PREPARING; }
        
        @Override
        public boolean canTransitionTo(OrderStatus newStatus) {
            return newStatus == PREPARING || newStatus == CANCELLED;
        }
    },
    
    PREPARING("Being prepared by our baristas", true, false, false) {
        @Override
        public OrderStatus getNextStatus() { return READY; }
        
        @Override
        public boolean canTransitionTo(OrderStatus newStatus) {
            return newStatus == READY || newStatus == CANCELLED;
        }
    },
    
    READY("Ready for pickup!", false, true, false) {
        @Override
        public OrderStatus getNextStatus() { return COMPLETED; }
        
        @Override
        public boolean canTransitionTo(OrderStatus newStatus) {
            return newStatus == COMPLETED;
        }
    },
    
    COMPLETED("Order completed successfully", false, false, true) {
        @Override
        public OrderStatus getNextStatus() { return this; }
        
        @Override
        public boolean canTransitionTo(OrderStatus newStatus) {
            return false; // Final status
        }
    },
    
    CANCELLED("Order cancelled", false, false, true) {
        @Override
        public OrderStatus getNextStatus() { return this; }
        
        @Override
        public boolean canTransitionTo(OrderStatus newStatus) {
            return false; // Final status
        }
    };
    
    private final String displayMessage;
    private final boolean inProgress;
    private final boolean readyForPickup;
    private final boolean isFinal;
    
    OrderStatus(String displayMessage, boolean inProgress, boolean readyForPickup, boolean isFinal) {
        this.displayMessage = displayMessage;
        this.inProgress = inProgress;
        this.readyForPickup = readyForPickup;
        this.isFinal = isFinal;
    }
    
    public String getDisplayMessage() { return displayMessage; }
    public boolean isInProgress() { return inProgress; }
    public boolean isReadyForPickup() { return readyForPickup; }
    public boolean isFinal() { return isFinal; }
    
    // Abstract methods that each status must implement
    public abstract OrderStatus getNextStatus();
    public abstract boolean canTransitionTo(OrderStatus newStatus);
    
    // Business validation method
    public static boolean isValidTransition(OrderStatus from, OrderStatus to) {
        return from.canTransitionTo(to);
    }
}

/**
 * Payment methods with processing logic
 * Demonstrates enum implementing interface with different behaviors
 */
interface PaymentProcessor {
    boolean processPayment(double amount, String customerInfo);
    String getReceiptInfo();
}

enum PaymentMethod implements PaymentProcessor {
    CASH("Cash Payment", 0.0, "💵") {
        @Override
        public boolean processPayment(double amount, String customerInfo) {
            System.out.println("Processing cash payment of " + formatCurrency(amount));
            return true; // Cash always works
        }
        
        @Override
        public String getReceiptInfo() {
            return "Paid with cash - Thank you!";
        }
    },
    
    CREDIT_CARD("Credit Card", 0.029, "💳") {
        @Override
        public boolean processPayment(double amount, String customerInfo) {
            System.out.println("Processing credit card payment of " + formatCurrency(amount));
            System.out.println("Processing fee: " + formatCurrency(calculateFee(amount)));
            // Simulate payment processing (could fail in real world)
            return Math.random() > 0.05; // 95% success rate
        }
        
        @Override
        public String getReceiptInfo() {
            return "Charged to credit card ending in ****";
        }
    },
    
    MOBILE_PAY("Mobile Payment", 0.019, "📱") {
        @Override
        public boolean processPayment(double amount, String customerInfo) {
            System.out.println("Processing mobile payment of " + formatCurrency(amount));
            System.out.println("Processing fee: " + formatCurrency(calculateFee(amount)));
            // Mobile payments are very reliable
            return Math.random() > 0.02; // 98% success rate
        }
        
        @Override
        public String getReceiptInfo() {
            return "Paid via mobile wallet - Thanks for choosing digital!";
        }
    },
    
    GIFT_CARD("Gift Card", 0.0, "🎁") {
        @Override
        public boolean processPayment(double amount, String customerInfo) {
            System.out.println("Processing gift card payment of " + formatCurrency(amount));
            // In real world, would check gift card balance
            return true; // Assume sufficient balance
        }
        
        @Override
        public String getReceiptInfo() {
            return "Paid with gift card - Remaining balance available";
        }
    };
    
    private final String displayName;
    private final double processingFeeRate;
    private final String icon;
    
    PaymentMethod(String displayName, double processingFeeRate, String icon) {
        this.displayName = displayName;
        this.processingFeeRate = processingFeeRate;
        this.icon = icon;
    }
    
    public String getDisplayName() { return displayName; }
    public double getProcessingFeeRate() { return processingFeeRate; }
    public String getIcon() { return icon; }
    
    protected double calculateFee(double amount) {
        return amount * processingFeeRate;
    }
    
    protected String formatCurrency(double amount) {
        return String.format("$%.2f", amount);
    }
    
    public String getDisplayWithIcon() {
        return icon + " " + displayName;
    }
}

/**
 * Priority levels for rush orders
 * Demonstrates enum with comparison logic and business rules
 */
enum Priority implements Comparable<Priority> {
    STANDARD(0, "Standard", "Take your time"),
    RUSH(10, "Rush Order", "Customer is in a hurry"),
    URGENT(20, "Urgent", "Customer has a meeting"),
    EMERGENCY(30, "Emergency", "Customer's flight is boarding!");
    
    private final int level;
    private final String displayName;
    private final String description;
    
    Priority(int level, String displayName, String description) {
        this.level = level;
        this.displayName = displayName;
        this.description = description;
    }
    
    public int getLevel() { return level; }
    public String getDisplayName() { return displayName; }
    public String getDescription() { return description; }
    
    // Business logic for priority handling
    public double getPriorityMultiplier() {
        return 1.0 + (level * 0.05); // 5% surcharge per priority level
    }
    
    public int getEstimatedMinutes() {
        return Math.max(2, 8 - (level / 5)); // Higher priority = faster service
    }
    
    public boolean requiresManagerApproval() {
        return level >= URGENT.level;
    }
    
    // Static utility methods
    public static Priority getHighestPriority(Priority... priorities) {
        return Arrays.stream(priorities)
                .max(Priority::compareTo)
                .orElse(STANDARD);
    }
}

// ========================================
// ORDER MANAGEMENT CLASSES
// ========================================

/**
 * Represents a single coffee house order
 * Demonstrates how enums work together in real business objects
 */
class Order {
    private static int nextOrderId = 1000;
    
    private final int orderId;
    private final String customerName;
    private final LocalDateTime orderTime;
    private final Map<MenuItem, Integer> items;
    private final Priority priority;
    private final PaymentMethod paymentMethod;
    private OrderStatus status;
    private String specialInstructions;
    
    public Order(String customerName, Priority priority, PaymentMethod paymentMethod) {
        this.orderId = nextOrderId++;
        this.customerName = customerName;
        this.orderTime = LocalDateTime.now();
        this.items = new HashMap<>();
        this.priority = priority;
        this.paymentMethod = paymentMethod;
        this.status = OrderStatus.PENDING;
        this.specialInstructions = "";
    }
    
    // Order building methods
    public void addItem(MenuItem item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }
    
    public void setSpecialInstructions(String instructions) {
        this.specialInstructions = instructions;
    }
    
    // Business calculations using enum methods
    public double getSubtotal() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
    
    public double getTax() {
        return getSubtotal() * 0.08; // 8% tax
    }
    
    public double getPriorityFee() {
        return getSubtotal() * (priority.getPriorityMultiplier() - 1.0);
    }
    
    public double getPaymentFee() {
        return (getSubtotal() + getTax()) * paymentMethod.getProcessingFeeRate();
    }
    
    public double getTotal() {
        return getSubtotal() + getTax() + getPriorityFee() + getPaymentFee();
    }
    
    public int getTotalCalories() {
        return items.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getCalories() * entry.getValue())
                .sum();
    }
    
    public int getEstimatedWaitTime() {
        int baseTime = items.size() * 2; // 2 minutes per item
        return Math.max(priority.getEstimatedMinutes(), baseTime);
    }
    
    // Order status management using enum business logic
    public boolean advanceStatus() {
        if (status.isFinal()) {
            return false;
        }
        
        OrderStatus nextStatus = status.getNextStatus();
        if (status.canTransitionTo(nextStatus)) {
            this.status = nextStatus;
            return true;
        }
        return false;
    }
    
    public boolean updateStatus(OrderStatus newStatus) {
        if (status.canTransitionTo(newStatus)) {
            this.status = newStatus;
            return true;
        }
        return false;
    }
    
    // Payment processing using enum interface
    public boolean processPayment() {
        return paymentMethod.processPayment(getTotal(), customerName);
    }
    
    // Getters
    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public OrderStatus getStatus() { return status; }
    public Priority getPriority() { return priority; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public Map<MenuItem, Integer> getItems() { return new HashMap<>(items); }
    
    // Display methods using enum formatting
    public void printReceipt() {
        System.out.println("═══════════════════════════════════");
        System.out.println("        MYSTIC COFFEE HOUSE        ");
        System.out.println("═══════════════════════════════════");
        System.out.println("Order #: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Time: " + orderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        System.out.println("Priority: " + priority.getDisplayName());
        System.out.println("Payment: " + paymentMethod.getDisplayWithIcon());
        System.out.println("Status: " + status.getDisplayMessage());
        System.out.println("───────────────────────────────────");
        
        // Items
        for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            MenuItem item = entry.getKey();
            int quantity = entry.getValue();
            double itemTotal = item.getPrice() * quantity;
            
            System.out.printf("%-15s x%-2d %s  $%.2f%n", 
                item.name(), quantity, item.getPriceDisplay(), itemTotal);
        }
        
        System.out.println("───────────────────────────────────");
        System.out.printf("Subtotal:        $%.2f%n", getSubtotal());
        System.out.printf("Tax (8%%):        $%.2f%n", getTax());
        
        if (getPriorityFee() > 0) {
            System.out.printf("Priority Fee:    $%.2f%n", getPriorityFee());
        }
        
        if (getPaymentFee() > 0) {
            System.out.printf("Payment Fee:     $%.2f%n", getPaymentFee());
        }
        
        System.out.printf("TOTAL:           $%.2f%n", getTotal());
        System.out.println("───────────────────────────────────");
        System.out.println("Total Calories: " + getTotalCalories());
        System.out.println("Est. Wait Time: " + getEstimatedWaitTime() + " minutes");
        
        if (!specialInstructions.isEmpty()) {
            System.out.println("Special Instructions: " + specialInstructions);
        }
        
        System.out.println();
        System.out.println(paymentMethod.getReceiptInfo());
        System.out.println("═══════════════════════════════════");
    }
}

// ========================================
// COFFEE HOUSE MANAGEMENT SYSTEM
// ========================================

/**
 * Main business logic demonstrating all enum features
 */
public class _PracticalExample {
    
    public static void main(String[] args) {
        System.out.println("🏛️ WELCOME TO THE MYSTIC COFFEE HOUSE 🏛️");
        System.out.println("Witness the power of Enums in action!\n");
        
        // Demonstrate menu analysis using enum static methods
        analyzeMenu();
        
        // Create and process sample orders
        processOrderExample();
        
        // Demonstrate enum collections and utilities
        demonstrateEnumCollections();
        
        // Show order workflow with status transitions
        demonstrateOrderWorkflow();
    }
    
    /**
     * Analyze menu using enum static methods and business logic
     */
    private static void analyzeMenu() {
        System.out.println("📋 MENU ANALYSIS (Using Enum Static Methods)");
        System.out.println("═══════════════════════════════════════════");
        
        System.out.println("Total Menu Items: " + MenuItem.values().length);
        System.out.printf("Average Price: $%.2f%n", MenuItem.getAveragePrice());
        
        System.out.println("\nHealthy Options (< 200 calories):");
        for (MenuItem item : MenuItem.getHealthyOptions()) {
            System.out.printf("- %-15s (%d cal) - %s%n", 
                item.name(), item.getCalories(), item.getDescription());
        }
        
        System.out.println("\nMenu by Category:");
        Set<String> categories = new HashSet<>();
        for (MenuItem item : MenuItem.values()) {
            categories.add(item.getCategory());
        }
        
        for (String category : categories) {
            System.out.println("\n" + category + ":");
            for (MenuItem item : MenuItem.getByCategory(category)) {
                System.out.printf("  %-15s %s - %s%n", 
                    item.name(), item.getPriceDisplay(), item.getDescription());
            }
        }
        System.out.println();
    }
    
    /**
     * Create and process orders demonstrating enum integration
     */
    private static void processOrderExample() {
        System.out.println("☕ ORDER PROCESSING DEMONSTRATION");
        System.out.println("═════════════════════════════════");
        
        // Create orders with different priorities and payment methods
        Order order1 = new Order("Alice the Wizard", Priority.RUSH, PaymentMethod.CREDIT_CARD);
        order1.addItem(MenuItem.LATTE, 2);
        order1.addItem(MenuItem.CROISSANT, 1);
        order1.setSpecialInstructions("Extra foam please, and warm the croissant");
        
        Order order2 = new Order("Bob the Sage", Priority.EMERGENCY, PaymentMethod.MOBILE_PAY);
        order2.addItem(MenuItem.AMERICANO, 1);
        order2.addItem(MenuItem.MUFFIN, 1);
        order2.setSpecialInstructions("Flight leaves in 20 minutes!");
        
        // Process payments using enum interface implementations
        System.out.println("Processing Order 1 Payment:");
        boolean payment1Success = order1.processPayment();
        System.out.println("Payment successful: " + payment1Success);
        System.out.println();
        
        System.out.println("Processing Order 2 Payment:");
        boolean payment2Success = order2.processPayment();
        System.out.println("Payment successful: " + payment2Success);
        System.out.println();
        
        // Print receipts showing enum-powered calculations
        if (payment1Success) {
            order1.printReceipt();
        }
        
        if (payment2Success) {
            order2.printReceipt();
        }
    }
    
    /**
     * Demonstrate EnumSet and EnumMap collections
     */
    private static void demonstrateEnumCollections() {
        System.out.println("📊 ENUM COLLECTIONS DEMONSTRATION");
        System.out.println("═════════════════════════════════");
        
        // EnumSet for fast operations on enum constants
        EnumSet<MenuItem> beverages = EnumSet.noneOf(MenuItem.class);
        for (MenuItem item : MenuItem.values()) {
            if (item.isBeverage()) {
                beverages.add(item);
            }
        }
        
        System.out.println("Beverage Items (using EnumSet):");
        beverages.forEach(item -> System.out.println("- " + item.name()));
        
        // EnumMap for efficient enum-keyed mappings
        EnumMap<Priority, Integer> priorityOrders = new EnumMap<>(Priority.class);
        priorityOrders.put(Priority.STANDARD, 45);
        priorityOrders.put(Priority.RUSH, 12);
        priorityOrders.put(Priority.URGENT, 3);
        priorityOrders.put(Priority.EMERGENCY, 1);
        
        System.out.println("\nDaily Priority Distribution (using EnumMap):");
        for (Map.Entry<Priority, Integer> entry : priorityOrders.entrySet()) {
            Priority priority = entry.getKey();
            int count = entry.getValue();
            System.out.printf("%-12s: %2d orders (%.1fx multiplier)%n", 
                priority.getDisplayName(), count, priority.getPriorityMultiplier());
        }
        System.out.println();
    }
    
    /**
     * Demonstrate order status workflow with enum state management
     */
    private static void demonstrateOrderWorkflow() {
        System.out.println("🔄 ORDER STATUS WORKFLOW");
        System.out.println("═══════════════════════");
        
        Order order = new Order("Charlie the Alchemist", Priority.STANDARD, PaymentMethod.CASH);
        order.addItem(MenuItem.CAPPUCCINO, 1);
        
        System.out.println("Order created: #" + order.getOrderId());
        System.out.println("Customer: " + order.getCustomerName());
        
        // Simulate order progression through status workflow
        while (!order.getStatus().isFinal()) {
            OrderStatus currentStatus = order.getStatus();
            System.out.println("\nCurrent Status: " + currentStatus.name());
            System.out.println("Message: " + currentStatus.getDisplayMessage());
            System.out.println("In Progress: " + currentStatus.isInProgress());
            System.out.println("Ready for Pickup: " + currentStatus.isReadyForPickup());
            
            // Simulate time passing and status advancement
            try {
                Thread.sleep(1000); // Wait 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            boolean advanced = order.advanceStatus();
            if (advanced) {
                System.out.println("→ Status advanced to: " + order.getStatus().name());
            } else {
                System.out.println("→ Cannot advance status further");
                break;
            }
        }
        
        System.out.println("\n✅ Order workflow completed!");
        System.out.println("Final Status: " + order.getStatus().getDisplayMessage());
        System.out.println();
    }
}

/*
 * ════════════════════════════════════════════════════════════════════════════
 * KEY ENUM FEATURES DEMONSTRATED:
 * ════════════════════════════════════════════════════════════════════════════
 * 
 * 1. RICH DATA MODELING:
 *    - MenuItem enum with price, calories, descriptions
 *    - Multiple constructors and computed properties
 * 
 * 2. STATE MACHINES:
 *    - OrderStatus with valid transitions and business rules
 *    - Abstract methods for status-specific behavior
 * 
 * 3. INTERFACE IMPLEMENTATION:
 *    - PaymentMethod implementing PaymentProcessor
 *    - Different behavior per payment type
 * 
 * 4. BUSINESS LOGIC ENCAPSULATION:
 *    - Priority calculations and rush order handling
 *    - Type-safe constants with meaningful operations
 * 
 * 5. STATIC UTILITY METHODS:
 *    - Menu analysis and filtering
 *    - Business queries and aggregations
 * 
 * 6. ENUM COLLECTIONS:
 *    - EnumSet for fast set operations
 *    - EnumMap for efficient enum-keyed mappings
 * 
 * 7. INTEGRATION WITH BUSINESS OBJECTS:
 *    - Order class using multiple enums together
 *    - Type-safe method parameters and return values
 * 
 * This example shows how enums are not just constants, but powerful
 * business objects that encapsulate both data and behavior while
 * maintaining type safety and preventing invalid states!
 */
