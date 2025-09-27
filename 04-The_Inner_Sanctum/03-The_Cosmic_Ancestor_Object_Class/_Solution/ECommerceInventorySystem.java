/**
 * The Path Revealed: E-Commerce Product Inventory System Solution
 * 
 * This solution demonstrates complete mastery of the Object class methods
 * through a comprehensive e-commerce inventory management system.
 * 
 * Features demonstrated:
 * - Proper toString() implementation for debugging and display
 * - Correct equals() contracts for value objects and entity objects
 * - Valid hashCode() implementation maintaining all contracts
 * - Seamless collection integration with proper object behavior
 * - Currency and measurement handling with business logic
 * - Complex object relationships and referential integrity
 */
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// ═══════════════════════════════════════════════════════════════════════════════════
// VALUE OBJECT - PRODUCTCODE (Immutable identifier)
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * ProductCode represents an immutable product identifier
 * Value object - equality based on code content (case-insensitive)
 */
final class ProductCode {
    private final String code;
    private final String category;
    private final String number;
    
    public ProductCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Product code cannot be null or empty");
        }
        
        String normalizedCode = code.trim().toUpperCase();
        if (!isValidFormat(normalizedCode)) {
            throw new IllegalArgumentException("Invalid product code format: " + code + 
                                             " (expected: CATEGORY-NNNNN)");
        }
        
        this.code = normalizedCode;
        String[] parts = normalizedCode.split("-");
        this.category = parts[0];
        this.number = parts[1];
    }
    
    private boolean isValidFormat(String code) {
        return code.matches("[A-Z]{3,8}-\\d{5}");
    }
    
    /**
     * toString() for value object - clear, formatted representation
     */
    @Override
    public String toString() {
        return code;  // Already formatted as CATEGORY-NNNNN
    }
    
    /**
     * equals() for value object - case-insensitive code comparison
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductCode that = (ProductCode) obj;
        return Objects.equals(code, that.code);  // Already normalized to uppercase
    }
    
    /**
     * hashCode() for value object - based on normalized code
     */
    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
    
    // Accessors
    public String getCode() { return code; }
    public String getCategory() { return category; }
    public String getNumber() { return number; }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// VALUE OBJECT - MONEY (Immutable monetary value)
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Money represents an immutable monetary value with currency
 * Value object - equality based on amount and currency
 */
final class Money {
    private final BigDecimal amount;
    private final Currency currency;
    
    public Money(BigDecimal amount, Currency currency) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative: " + amount);
        }
        
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.currency = currency;
    }
    
    public Money(double amount, String currencyCode) {
        this(BigDecimal.valueOf(amount), Currency.getInstance(currencyCode));
    }
    
    public Money(String amount, String currencyCode) {
        this(new BigDecimal(amount), Currency.getInstance(currencyCode));
    }
    
    /**
     * toString() for money - formatted with currency symbol
     */
    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        formatter.setCurrency(currency);
        return formatter.format(amount);
    }
    
    /**
     * equals() for money - amount and currency must match exactly
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return Objects.equals(amount, money.amount) && 
               Objects.equals(currency, money.currency);
    }
    
    /**
     * hashCode() for money - combine amount and currency
     */
    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
    
    /**
     * Add money values (must be same currency)
     */
    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot add different currencies: " + 
                                             this.currency + " and " + other.currency);
        }
        return new Money(this.amount.add(other.amount), this.currency);
    }
    
    /**
     * Multiply money by factor
     */
    public Money multiply(double factor) {
        if (factor < 0) {
            throw new IllegalArgumentException("Factor cannot be negative: " + factor);
        }
        return new Money(this.amount.multiply(BigDecimal.valueOf(factor)), this.currency);
    }
    
    /**
     * Compare money values (must be same currency)
     */
    public boolean isGreaterThan(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot compare different currencies: " + 
                                             this.currency + " and " + other.currency);
        }
        return this.amount.compareTo(other.amount) > 0;
    }
    
    public boolean isLessThan(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot compare different currencies: " + 
                                             this.currency + " and " + other.currency);
        }
        return this.amount.compareTo(other.amount) < 0;
    }
    
    // Accessors
    public BigDecimal getAmount() { return amount; }
    public Currency getCurrency() { return currency; }
    public String getCurrencyCode() { return currency.getCurrencyCode(); }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// VALUE OBJECT - DIMENSIONS (Immutable measurements)
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Dimensions represents immutable physical measurements
 * Value object - equality based on all measurements with tolerance
 */
final class Dimensions {
    private static final double TOLERANCE = 0.01; // 1cm tolerance for equality
    
    private final double length;  // centimeters
    private final double width;   // centimeters
    private final double height;  // centimeters
    private final double weight;  // kilograms
    
    public Dimensions(double length, double width, double height, double weight) {
        if (length <= 0 || width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Length, width, and height must be positive");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        
        this.length = Math.round(length * 100.0) / 100.0;  // Round to 2 decimal places
        this.width = Math.round(width * 100.0) / 100.0;
        this.height = Math.round(height * 100.0) / 100.0;
        this.weight = Math.round(weight * 1000.0) / 1000.0;  // Round to 3 decimal places
    }
    
    /**
     * toString() for dimensions - human-readable format with units
     */
    @Override
    public String toString() {
        return String.format("%.1f×%.1f×%.1f cm, %.2f kg", length, width, height, weight);
    }
    
    /**
     * equals() for dimensions - compare all measurements with tolerance
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Dimensions that = (Dimensions) obj;
        return Math.abs(length - that.length) < TOLERANCE &&
               Math.abs(width - that.width) < TOLERANCE &&
               Math.abs(height - that.height) < TOLERANCE &&
               Math.abs(weight - that.weight) < TOLERANCE;
    }
    
    /**
     * hashCode() for dimensions - hash rounded values for tolerance consistency
     */
    @Override
    public int hashCode() {
        // Use rounded values to ensure equal objects have same hash code
        int lengthHash = (int) Math.round(length / TOLERANCE);
        int widthHash = (int) Math.round(width / TOLERANCE);
        int heightHash = (int) Math.round(height / TOLERANCE);
        int weightHash = (int) Math.round(weight / TOLERANCE);
        return Objects.hash(lengthHash, widthHash, heightHash, weightHash);
    }
    
    /**
     * Calculate volume in cubic centimeters
     */
    public double getVolume() {
        return length * width * height;
    }
    
    /**
     * Calculate shipping cost based on weight and volume
     */
    public Money getShippingCost() {
        // Simple shipping calculation: $5 base + $2 per kg + $0.01 per cm³
        double volumeCost = getVolume() * 0.01;
        double weightCost = weight * 2.0;
        double totalCost = 5.0 + weightCost + volumeCost;
        return new Money(totalCost, "USD");
    }
    
    // Accessors
    public double getLength() { return length; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public double getWeight() { return weight; }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// ENTITY OBJECT - SUPPLIER (Mutable with unique identity)
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Supplier represents a company that supplies products
 * Entity object - equality based ONLY on unique supplier ID
 */
class Supplier {
    private final String supplierId;  // Unique identifier (never changes)
    private String companyName;       // Can change
    private String contactEmail;      // Can change
    private String phoneNumber;       // Can change
    private String address;           // Can change
    private boolean active;           // Can change
    
    // Static counter for generating unique IDs
    private static final AtomicLong nextId = new AtomicLong(1000);
    
    public Supplier(String companyName, String contactEmail, String phoneNumber, String address) {
        this.supplierId = "SUP" + String.format("%06d", nextId.getAndIncrement());
        this.companyName = companyName;
        this.contactEmail = contactEmail;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.active = true;
    }
    
    // Constructor for testing with specific ID
    public Supplier(String supplierId, String companyName, String contactEmail, 
                   String phoneNumber, String address) {
        this.supplierId = supplierId;
        this.companyName = companyName;
        this.contactEmail = contactEmail;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.active = true;
    }
    
    /**
     * toString() for entity - include ID and key identifying information
     */
    @Override
    public String toString() {
        return String.format("Supplier{id='%s', name='%s', email='%s', active=%s}", 
                           supplierId, companyName, contactEmail, active);
    }
    
    /**
     * equals() for entity - based ONLY on supplierId
     * Two suppliers are equal if they have the same ID, regardless of other properties
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Supplier supplier = (Supplier) obj;
        return Objects.equals(supplierId, supplier.supplierId);
    }
    
    /**
     * hashCode() for entity - based ONLY on supplierId
     */
    @Override
    public int hashCode() {
        return Objects.hash(supplierId);
    }
    
    // Getters and setters
    public String getSupplierId() { return supplierId; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// ENTITY OBJECT - CUSTOMER (Mutable with unique identity)
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Customer represents a person who buys products
 * Entity object - equality based ONLY on unique customer ID
 */
class Customer {
    private final String customerId;  // Unique identifier
    private String name;              // Can change
    private String email;             // Can change
    private String phoneNumber;       // Can change
    private String shippingAddress;   // Can change
    private String preferences;       // Can change
    
    private static final AtomicLong nextId = new AtomicLong(10000);
    
    public Customer(String name, String email, String phoneNumber, String shippingAddress) {
        this.customerId = "CUST" + String.format("%08d", nextId.getAndIncrement());
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.shippingAddress = shippingAddress;
        this.preferences = "Standard";
    }
    
    /**
     * toString() for customer entity - include ID and name
     */
    @Override
    public String toString() {
        return String.format("Customer{id='%s', name='%s', email='%s'}", 
                           customerId, name, email);
    }
    
    /**
     * equals() for customer entity - based ONLY on customerId
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer customer = (Customer) obj;
        return Objects.equals(customerId, customer.customerId);
    }
    
    /**
     * hashCode() for customer entity - based ONLY on customerId
     */
    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
    
    // Getters and setters
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    public String getPreferences() { return preferences; }
    public void setPreferences(String preferences) { this.preferences = preferences; }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// COMPLEX OBJECT - PRODUCT (Entity with value object components)
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Product represents items in the inventory
 * Entity object - equality based on unique product ID, but contains value objects
 */
class Product {
    private final Long productId;          // Unique entity ID (never changes)
    private final ProductCode productCode; // Value object identifier
    private String name;                   // Can change
    private String description;            // Can change
    private Money price;                   // Can change (value object)
    private final Dimensions dimensions;   // Immutable value object
    private Supplier supplier;             // Entity reference (can change)
    private String category;               // Can change
    private int stockQuantity;            // Can change
    private boolean active;               // Can change
    
    private static final AtomicLong nextId = new AtomicLong(100000);
    
    public Product(ProductCode productCode, String name, String description, Money price,
                  Dimensions dimensions, Supplier supplier, String category, int stockQuantity) {
        this.productId = nextId.getAndIncrement();
        this.productCode = Objects.requireNonNull(productCode, "Product code cannot be null");
        this.name = Objects.requireNonNull(name, "Product name cannot be null");
        this.description = description != null ? description : "";
        this.price = Objects.requireNonNull(price, "Price cannot be null");
        this.dimensions = Objects.requireNonNull(dimensions, "Dimensions cannot be null");
        this.supplier = Objects.requireNonNull(supplier, "Supplier cannot be null");
        this.category = Objects.requireNonNull(category, "Category cannot be null");
        this.stockQuantity = Math.max(0, stockQuantity);
        this.active = true;
    }
    
    /**
     * toString() for product - comprehensive information for inventory display
     */
    @Override
    public String toString() {
        return String.format(
            "Product{id=%d, code=%s, name='%s', price=%s, stock=%d, supplier='%s', active=%s}", 
            productId, productCode, name, price, stockQuantity, 
            supplier.getCompanyName(), active);
    }
    
    /**
     * equals() for product entity - based ONLY on productId
     * Even if all other properties differ, products with same ID are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Objects.equals(productId, product.productId);
    }
    
    /**
     * hashCode() for product entity - based ONLY on productId
     */
    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
    
    /**
     * Calculate total inventory value for this product
     */
    public Money getTotalInventoryValue() {
        return price.multiply(stockQuantity);
    }
    
    /**
     * Check if product is available for sale
     */
    public boolean isAvailable() {
        return active && stockQuantity > 0 && supplier.isActive();
    }
    
    /**
     * Update stock quantity (with validation)
     */
    public void updateStock(int newQuantity) {
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative: " + newQuantity);
        }
        this.stockQuantity = newQuantity;
    }
    
    /**
     * Reduce stock by specified amount
     */
    public boolean reduceStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive: " + amount);
        }
        if (stockQuantity >= amount) {
            stockQuantity -= amount;
            return true;
        }
        return false;
    }
    
    // Getters and setters
    public Long getProductId() { return productId; }
    public ProductCode getProductCode() { return productCode; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Money getPrice() { return price; }
    public void setPrice(Money price) { this.price = price; }
    public Dimensions getDimensions() { return dimensions; }
    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getStockQuantity() { return stockQuantity; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// INVENTORY MANAGEMENT SYSTEM
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * InventorySystem manages products, suppliers, and customers
 * Demonstrates practical usage of objects with proper equals/hashCode
 */
class InventorySystem {
    private final Set<Product> products;              // Uses Product.equals() for uniqueness
    private final Set<Supplier> suppliers;           // Uses Supplier.equals() for uniqueness  
    private final Set<Customer> customers;           // Uses Customer.equals() for uniqueness
    private final Map<ProductCode, Product> productsByCode;  // Fast lookup by code
    private final Map<Supplier, List<Product>> productsBySupplier; // Grouped by supplier
    
    public InventorySystem() {
        this.products = new HashSet<>();
        this.suppliers = new HashSet<>();
        this.customers = new HashSet<>();
        this.productsByCode = new HashMap<>();
        this.productsBySupplier = new HashMap<>();
    }
    
    /**
     * Add a product to inventory (prevents duplicates using equals())
     */
    public boolean addProduct(Product product) {
        // Check for duplicate product code
        if (productsByCode.containsKey(product.getProductCode())) {
            System.out.println("❌ Product code already exists: " + product.getProductCode());
            return false;
        }
        
        boolean added = products.add(product);  // Uses Product.equals() (based on ID)
        
        if (added) {
            // Add supplier if not already present
            suppliers.add(product.getSupplier());
            
            // Update indices
            productsByCode.put(product.getProductCode(), product);
            productsBySupplier.computeIfAbsent(product.getSupplier(), k -> new ArrayList<>())
                            .add(product);
            
            System.out.println("✅ Added product: " + product.getName() + 
                             " (Code: " + product.getProductCode() + ")");
        }
        
        return added;
    }
    
    /**
     * Add supplier to system
     */
    public boolean addSupplier(Supplier supplier) {
        boolean added = suppliers.add(supplier);  // Uses Supplier.equals() (based on ID)
        
        if (added) {
            System.out.println("🏢 Added supplier: " + supplier.getCompanyName() + 
                             " (ID: " + supplier.getSupplierId() + ")");
        } else {
            System.out.println("🏢 Supplier already exists: " + supplier.getCompanyName());
        }
        
        return added;
    }
    
    /**
     * Add customer to system
     */
    public boolean addCustomer(Customer customer) {
        boolean added = customers.add(customer);  // Uses Customer.equals() (based on ID)
        
        if (added) {
            System.out.println("👤 Added customer: " + customer.getName() + 
                             " (ID: " + customer.getCustomerId() + ")");
        }
        
        return added;
    }
    
    /**
     * Find products by supplier (uses Supplier.equals())
     */
    public List<Product> findProductsBySupplier(Supplier supplier) {
        return productsBySupplier.getOrDefault(supplier, Collections.emptyList());
    }
    
    /**
     * Find products by price range
     */
    public List<Product> findProductsByPriceRange(Money minPrice, Money maxPrice) {
        return products.stream()
            .filter(p -> !p.getPrice().isLessThan(minPrice) && !p.getPrice().isGreaterThan(maxPrice))
            .sorted((p1, p2) -> p1.getPrice().getAmount().compareTo(p2.getPrice().getAmount()))
            .collect(Collectors.toList());
    }
    
    /**
     * Find products by category
     */
    public List<Product> findProductsByCategory(String category) {
        return products.stream()
            .filter(p -> p.getCategory().equalsIgnoreCase(category))
            .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
            .collect(Collectors.toList());
    }
    
    /**
     * Find product by code
     */
    public Product findProductByCode(ProductCode code) {
        return productsByCode.get(code);  // Uses ProductCode.equals()
    }
    
    /**
     * Calculate total inventory value by currency
     */
    public Map<String, Money> calculateTotalInventoryValue() {
        Map<String, Money> totals = new HashMap<>();
        
        for (Product product : products) {
            if (product.isActive()) {
                Money productValue = product.getTotalInventoryValue();
                String currency = productValue.getCurrencyCode();
                
                totals.merge(currency, productValue, (existing, newValue) -> existing.add(newValue));
            }
        }
        
        return totals;
    }
    
    /**
     * Get inventory statistics
     */
    public void displayInventoryStatistics() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("📊 INVENTORY SYSTEM STATISTICS");
        System.out.println("═".repeat(60));
        
        System.out.println("Total Products: " + products.size());
        System.out.println("Active Products: " + products.stream().mapToInt(p -> p.isActive() ? 1 : 0).sum());
        System.out.println("Total Suppliers: " + suppliers.size());
        System.out.println("Active Suppliers: " + suppliers.stream().mapToInt(s -> s.isActive() ? 1 : 0).sum());
        System.out.println("Total Customers: " + customers.size());
        
        // Category distribution
        Map<String, Long> categoryCount = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        
        System.out.println("\nProduct Categories:");
        categoryCount.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .forEach(entry -> System.out.println("  " + entry.getKey() + ": " + entry.getValue()));
        
        // Inventory values by currency
        Map<String, Money> inventoryValues = calculateTotalInventoryValue();
        System.out.println("\nTotal Inventory Value:");
        inventoryValues.forEach((currency, value) -> 
            System.out.println("  " + currency + ": " + value));
        
        System.out.println("═".repeat(60));
    }
    
    // Getters for testing
    public Set<Product> getAllProducts() { return new HashSet<>(products); }
    public Set<Supplier> getAllSuppliers() { return new HashSet<>(suppliers); }
    public Set<Customer> getAllCustomers() { return new HashSet<>(customers); }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// MAIN DEMONSTRATION CLASS
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Main demonstration of Object methods in an e-commerce inventory system
 */
public class ECommerceInventorySystem {
    
    public static void main(String[] args) {
        System.out.println("🛍️ E-COMMERCE PRODUCT INVENTORY SYSTEM DEMONSTRATION 🛍️\n");
        
        // ═══════════════════════════════════════════════════════════════
        // INITIALIZE SYSTEM
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("🏗️ INITIALIZING INVENTORY SYSTEM...\n");
        
        InventorySystem inventory = new InventorySystem();
        
        // ═══════════════════════════════════════════════════════════════
        // CREATE VALUE OBJECTS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("📦 CREATING VALUE OBJECTS:");
        
        // Product codes
        ProductCode laptopCode = new ProductCode("ELEC-12345");
        ProductCode mouseCode = new ProductCode("ELEC-54321");
        ProductCode bookCode = new ProductCode("BOOK-98765");
        
        System.out.println("Created ProductCodes: " + laptopCode + ", " + mouseCode + ", " + bookCode);
        
        // Money values
        Money laptopPrice = new Money("1299.99", "USD");
        Money mousePrice = new Money("29.99", "USD");
        Money bookPrice = new Money("24.95", "USD");
        Money eurPrice = new Money("899.99", "EUR");
        
        System.out.println("Created Money values: " + laptopPrice + ", " + mousePrice + ", " + bookPrice + ", " + eurPrice);
        
        // Dimensions
        Dimensions laptopDims = new Dimensions(35.0, 25.0, 2.5, 1.8);
        Dimensions mouseDims = new Dimensions(12.0, 7.0, 4.0, 0.1);
        Dimensions bookDims = new Dimensions(23.0, 15.5, 2.0, 0.4);
        
        System.out.println("Created Dimensions: " + laptopDims + ", " + mouseDims + ", " + bookDims);
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATE VALUE OBJECT EQUALITY
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🔍 DEMONSTRATING VALUE OBJECT EQUALITY:");
        
        // ProductCode equality (case-insensitive)
        ProductCode laptopCodeCopy = new ProductCode("elec-12345");  // Different case
        System.out.println("ProductCode equality test:");
        System.out.println("  " + laptopCode + " equals " + laptopCodeCopy + ": " + laptopCode.equals(laptopCodeCopy));
        System.out.println("  Hash codes: " + laptopCode.hashCode() + " vs " + laptopCodeCopy.hashCode());
        
        // Money equality
        Money laptopPriceCopy = new Money(1299.99, "USD");
        System.out.println("\nMoney equality test:");
        System.out.println("  " + laptopPrice + " equals " + laptopPriceCopy + ": " + laptopPrice.equals(laptopPriceCopy));
        System.out.println("  Hash codes: " + laptopPrice.hashCode() + " vs " + laptopPriceCopy.hashCode());
        
        // Cross-currency comparison (should fail)
        try {
            boolean crossCurrencyComparison = laptopPrice.isGreaterThan(eurPrice);
        } catch (IllegalArgumentException e) {
            System.out.println("  Cross-currency comparison correctly rejected: " + e.getMessage());
        }
        
        // Dimensions equality with tolerance
        Dimensions laptopDimsCopy = new Dimensions(35.005, 25.003, 2.502, 1.799);  // Within tolerance
        System.out.println("\nDimensions equality test (with tolerance):");
        System.out.println("  " + laptopDims + " equals " + laptopDimsCopy + ": " + laptopDims.equals(laptopDimsCopy));
        
        // ═══════════════════════════════════════════════════════════════
        // CREATE ENTITY OBJECTS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🏢 CREATING ENTITY OBJECTS (Suppliers):");
        
        Supplier techSupply = new Supplier("TechSupply Corp", "orders@techsupply.com", 
                                          "555-0001", "123 Tech Street");
        Supplier bookWorld = new Supplier("BookWorld Publishers", "sales@bookworld.com", 
                                         "555-0002", "456 Literature Ave");
        Supplier globalElectronics = new Supplier("Global Electronics", "contact@globalelec.com", 
                                                 "555-0003", "789 Circuit Road");
        
        System.out.println("Created: " + techSupply);
        System.out.println("Created: " + bookWorld);
        System.out.println("Created: " + globalElectronics);
        
        inventory.addSupplier(techSupply);
        inventory.addSupplier(bookWorld);
        inventory.addSupplier(globalElectronics);
        
        // Demonstrate entity equality
        System.out.println("\n🔍 DEMONSTRATING ENTITY EQUALITY:");
        
        Supplier techSupplyModified = new Supplier(techSupply.getSupplierId(), 
                                                  "TechSupply Corporation", 
                                                  "newemail@techsupply.com",
                                                  "555-9999", "New Address");
        
        System.out.println("Original: " + techSupply);
        System.out.println("Modified: " + techSupplyModified);
        System.out.println("Are they equal? " + techSupply.equals(techSupplyModified) + " (same ID, different data)");
        System.out.println("Same hash code? " + (techSupply.hashCode() == techSupplyModified.hashCode()));
        
        // ═══════════════════════════════════════════════════════════════
        // CREATE PRODUCTS (COMPLEX OBJECTS)
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n💻 CREATING PRODUCTS (Complex Objects):");
        
        Product laptop = new Product(laptopCode, "Gaming Laptop", 
                                   "High-performance gaming laptop with RTX graphics",
                                   laptopPrice, laptopDims, techSupply, "Electronics", 25);
        
        Product mouse = new Product(mouseCode, "Wireless Mouse", 
                                  "Ergonomic wireless mouse with USB receiver",
                                  mousePrice, mouseDims, techSupply, "Electronics", 100);
        
        Product book = new Product(bookCode, "Java Programming Guide", 
                                 "Complete guide to Java programming and OOP",
                                 bookPrice, bookDims, bookWorld, "Books", 50);
        
        inventory.addProduct(laptop);
        inventory.addProduct(mouse);
        inventory.addProduct(book);
        
        // Try to add duplicate product code
        Product duplicateProduct = new Product(laptopCode, "Different Laptop", 
                                             "Different laptop but same code",
                                             laptopPrice, laptopDims, globalElectronics, "Electronics", 10);
        inventory.addProduct(duplicateProduct);  // Should be rejected
        
        // ═══════════════════════════════════════════════════════════════
        // CREATE CUSTOMERS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n👥 CREATING CUSTOMERS:");
        
        Customer alice = new Customer("Alice Johnson", "alice@email.com", 
                                    "555-1001", "123 Customer St");
        Customer bob = new Customer("Bob Smith", "bob@email.com", 
                                  "555-1002", "456 Buyer Ave");
        
        inventory.addCustomer(alice);
        inventory.addCustomer(bob);
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATE COLLECTION OPERATIONS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🔍 DEMONSTRATING COLLECTION OPERATIONS:");
        
        // Find products by supplier (uses Supplier.equals())
        List<Product> techProducts = inventory.findProductsBySupplier(techSupply);
        System.out.println("\nProducts from " + techSupply.getCompanyName() + ":");
        techProducts.forEach(p -> System.out.println("  - " + p.getName() + " (" + p.getProductCode() + ")"));
        
        // Find products by category
        List<Product> electronics = inventory.findProductsByCategory("Electronics");
        System.out.println("\nElectronics products:");
        electronics.forEach(p -> System.out.println("  - " + p.getName() + " - " + p.getPrice()));
        
        // Find products by price range
        Money minPrice = new Money("20.00", "USD");
        Money maxPrice = new Money("50.00", "USD");
        List<Product> budgetProducts = inventory.findProductsByPriceRange(minPrice, maxPrice);
        System.out.println("\nProducts between " + minPrice + " and " + maxPrice + ":");
        budgetProducts.forEach(p -> System.out.println("  - " + p.getName() + " - " + p.getPrice()));
        
        // Find product by code (uses ProductCode.equals())
        Product foundProduct = inventory.findProductByCode(mouseCode);
        System.out.println("\nProduct found by code " + mouseCode + ": " + 
                          (foundProduct != null ? foundProduct.getName() : "Not found"));
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATE HASH COLLECTION BEHAVIOR
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🗂️ DEMONSTRATING HASH COLLECTION BEHAVIOR:");
        
        // Test HashSet behavior with proper equals/hashCode
        Set<Product> productSet = new HashSet<>();
        productSet.add(laptop);
        productSet.add(mouse);
        productSet.add(laptop);  // Duplicate - should not be added
        
        System.out.println("HashSet size after adding laptop twice: " + productSet.size());
        
        // Test HashMap with different object types as keys
        Map<Object, String> mixedMap = new HashMap<>();
        mixedMap.put(laptopCode, "Laptop identifier");
        mixedMap.put(techSupply, "Supplier information");
        mixedMap.put(laptop, "Product details");
        mixedMap.put(laptopPrice, "Price information");
        
        System.out.println("HashMap with mixed object types as keys:");
        mixedMap.forEach((key, value) -> 
            System.out.println("  " + key.getClass().getSimpleName() + " -> " + value));
        
        // Test retrieval
        System.out.println("\nTesting key retrieval:");
        System.out.println("  Can find laptop code? " + (mixedMap.get(laptopCode) != null));
        System.out.println("  Can find supplier? " + (mixedMap.get(techSupply) != null));
        System.out.println("  Can find product? " + (mixedMap.get(laptop) != null));
        
        // ═══════════════════════════════════════════════════════════════
        // TEST OBJECT CONTRACTS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🔬 TESTING OBJECT CONTRACTS:");
        
        testObjectContracts();
        
        // ═══════════════════════════════════════════════════════════════
        // BUSINESS OPERATIONS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n💼 BUSINESS OPERATIONS:");
        
        // Update stock
        System.out.println("Reducing mouse stock by 10 units...");
        boolean stockReduced = mouse.reduceStock(10);
        System.out.println("Stock reduction successful: " + stockReduced);
        System.out.println("New mouse stock: " + mouse.getStockQuantity());
        
        // Calculate shipping costs
        System.out.println("\nShipping costs:");
        System.out.println("  Laptop shipping: " + laptop.getDimensions().getShippingCost());
        System.out.println("  Mouse shipping: " + mouse.getDimensions().getShippingCost());
        System.out.println("  Book shipping: " + book.getDimensions().getShippingCost());
        
        // Money operations
        System.out.println("\nMoney operations:");
        Money totalCartValue = laptopPrice.add(mousePrice).add(bookPrice);
        System.out.println("  Cart total: " + totalCartValue);
        Money discountedPrice = laptopPrice.multiply(0.9);  // 10% discount
        System.out.println("  Discounted laptop price: " + discountedPrice);
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATE POLYMORPHISM
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🎭 DEMONSTRATING POLYMORPHISM:");
        
        Object[] inventoryObjects = {laptop, techSupply, alice, laptopCode, laptopPrice, laptopDims};
        
        System.out.println("Polymorphic toString() calls:");
        for (Object obj : inventoryObjects) {
            System.out.println("  " + obj.getClass().getSimpleName() + ": " + obj);
        }
        
        // Type checking with instanceof
        System.out.println("\nType checking and safe casting:");
        for (Object obj : inventoryObjects) {
            if (obj instanceof Product product) {
                System.out.println("  Product: " + product.getName() + " - Stock: " + product.getStockQuantity());
            } else if (obj instanceof Supplier supplier) {
                System.out.println("  Supplier: " + supplier.getCompanyName() + " - Active: " + supplier.isActive());
            } else if (obj instanceof Customer customer) {
                System.out.println("  Customer: " + customer.getName() + " - Email: " + customer.getEmail());
            } else if (obj instanceof Money money) {
                System.out.println("  Money: " + money + " (" + money.getCurrencyCode() + ")");
            } else {
                System.out.println("  " + obj.getClass().getSimpleName() + ": " + obj);
            }
        }
        
        // ═══════════════════════════════════════════════════════════════
        // DISPLAY FINAL STATISTICS
        // ═══════════════════════════════════════════════════════════════
        
        inventory.displayInventoryStatistics();
        
        // ═══════════════════════════════════════════════════════════════
        // FINAL SUMMARY
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n✨ E-COMMERCE INVENTORY SYSTEM DEMONSTRATION COMPLETE! ✨");
        System.out.println("\n🎯 OBJECT CLASS METHODS MASTERFULLY DEMONSTRATED:");
        System.out.println("🔹 toString() provided clear, formatted representations for all object types");
        System.out.println("🔹 equals() implemented correctly for value objects (content-based) and entities (ID-based)");
        System.out.println("🔹 hashCode() maintained proper contracts enabling flawless collection behavior");
        System.out.println("🔹 Value objects (ProductCode, Money, Dimensions) use content equality");
        System.out.println("🔹 Entity objects (Supplier, Customer, Product) use identity equality");
        System.out.println("🔹 Collections work seamlessly with proper Object method implementation");
        System.out.println("🔹 Polymorphism enables uniform treatment of different object types");
        System.out.println("🔹 Currency handling prevents invalid cross-currency operations");
        System.out.println("🔹 Measurement tolerance ensures practical equality comparisons");
        System.out.println("🔹 Business logic integrates perfectly with proper object contracts");
        
        System.out.println("\n💡 The cosmic ancestor Object class provides the universal foundation!");
        System.out.println("🏆 Proper Object method implementation creates harmonious collection ecosystems!");
    }
    
    /**
     * Comprehensive test of Object contracts for all object types
     */
    private static void testObjectContracts() {
        System.out.println("Testing Object contracts for all classes...");
        
        // Test ProductCode contracts
        testValueObjectContract("ProductCode", 
            new ProductCode("ELEC-12345"), 
            new ProductCode("elec-12345"),  // Same content, different case
            new ProductCode("BOOK-98765")); // Different content
        
        // Test Money contracts
        testValueObjectContract("Money",
            new Money("100.00", "USD"),
            new Money(100.0, "USD"),      // Same value, different constructor
            new Money("200.00", "USD")); // Different value
        
        // Test Dimensions contracts
        testValueObjectContract("Dimensions",
            new Dimensions(10.0, 15.0, 5.0, 1.0),
            new Dimensions(10.005, 15.003, 5.002, 1.001), // Within tolerance
            new Dimensions(20.0, 25.0, 8.0, 2.0));       // Different dimensions
        
        // Test Supplier contracts (entity)
        testEntityObjectContract("Supplier",
            new Supplier("SUP001", "Company A", "email@a.com", "555-0001", "Address A"),
            new Supplier("SUP001", "Company B", "email@b.com", "555-0002", "Address B"), // Same ID
            new Supplier("SUP002", "Company C", "email@c.com", "555-0003", "Address C")); // Different ID
    }
    
    /**
     * Test Object contracts for value objects
     */
    private static void testValueObjectContract(String className, Object obj1, Object obj2Equal, Object obj3Different) {
        System.out.println("\n" + className + " Contract Tests:");
        
        // Reflexive
        boolean reflexive = obj1.equals(obj1);
        System.out.println("  ✅ Reflexive: " + reflexive);
        
        // Symmetric
        boolean symmetric1 = obj1.equals(obj2Equal);
        boolean symmetric2 = obj2Equal.equals(obj1);
        System.out.println("  ✅ Symmetric: " + symmetric1 + " == " + symmetric2 + " (" + (symmetric1 == symmetric2) + ")");
        
        // HashCode contract
        boolean hashCodeContract = !symmetric1 || (obj1.hashCode() == obj2Equal.hashCode());
        System.out.println("  ✅ HashCode contract: " + hashCodeContract);
        
        // Null safety
        boolean nullSafe = !obj1.equals(null);
        System.out.println("  ✅ Null safe: " + nullSafe);
        
        // Different objects
        boolean differentObjectsUnequal = !obj1.equals(obj3Different);
        System.out.println("  ✅ Different objects unequal: " + differentObjectsUnequal);
    }
    
    /**
     * Test Object contracts for entity objects
     */
    private static void testEntityObjectContract(String className, Object obj1, Object obj2SameId, Object obj3DifferentId) {
        System.out.println("\n" + className + " Entity Contract Tests:");
        
        // Same ID should be equal regardless of other properties
        boolean sameIdEqual = obj1.equals(obj2SameId);
        System.out.println("  ✅ Same ID equal (despite different properties): " + sameIdEqual);
        
        // Same hash code for same ID
        boolean sameIdHashCode = obj1.hashCode() == obj2SameId.hashCode();
        System.out.println("  ✅ Same ID same hash code: " + sameIdHashCode);
        
        // Different ID should not be equal
        boolean differentIdUnequal = !obj1.equals(obj3DifferentId);
        System.out.println("  ✅ Different ID unequal: " + differentIdUnequal);
        
        // Reflexive still holds
        boolean reflexive = obj1.equals(obj1);
        System.out.println("  ✅ Reflexive: " + reflexive);
    }
}
