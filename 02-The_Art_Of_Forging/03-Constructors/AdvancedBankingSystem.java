/**
 * 🏦 PRACTICAL EXAMPLE: ADVANCED BANKING SYSTEM
 * 
 * This living manuscript demonstrates Constructors through a comprehensive
 * banking system that showcases the sacred ritual of object creation.
 * Here, you'll witness how Constructors ensure objects are born in perfect,
 * valid states ready to serve their purpose.
 * 
 * Key Concepts Demonstrated:
 * - Multiple constructor patterns for different creation scenarios
 * - Parameter validation ensuring object integrity from birth
 * - Constructor chaining to avoid code duplication
 * - Factory methods for meaningful object creation
 * - Complex initialization logic with business rules
 * - Immutable objects created through constructors
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// ═══════════════════════════════════════════════════════════════════════════════
// 💳 BANKACCOUNT CLASS - MASTER OF CONSTRUCTOR PATTERNS
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * BankAccount class demonstrating sophisticated constructor design
 * Shows how constructors can handle different account creation scenarios
 * while ensuring every account starts in a valid, secure state
 */
class BankAccount {
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🎯 ACCOUNT STATE - What every BankAccount IS
    // ═══════════════════════════════════════════════════════════════════════
    
    private final String accountNumber;      // Immutable account identifier
    private final String routingNumber;     // Immutable bank routing number
    private String accountHolderName;       // Account owner's name
    private final String accountType;       // "Checking", "Savings", "Business"
    private double balance;                 // Current account balance
    private final LocalDateTime creationDate; // When account was created
    private boolean isActive;              // Whether account is active
    private double interestRate;           // Annual interest rate
    private double minimumBalance;         // Minimum required balance
    private int transactionCount;          // Number of transactions
    private String lastTransactionDate;    // Last transaction timestamp
    private final String securityPin;     // Account security PIN
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🏗️ CONSTRUCTOR ARSENAL - Multiple Paths to Account Creation
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * MASTER CONSTRUCTOR - All other constructors chain to this one
     * Contains all validation and initialization logic
     */
    private BankAccount(String accountNum, String routingNum, String holderName, 
                       String type, double initialBalance, String pin, 
                       double interestRate, double minBalance) {
        
        // ═══════════════════════════════════════════════════════════════════
        // COMPREHENSIVE VALIDATION - Ensuring object integrity from birth
        // ═══════════════════════════════════════════════════════════════════
        
        // Validate account number
        if (accountNum == null || accountNum.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be null or empty");
        }
        if (accountNum.length() != 10) {
            throw new IllegalArgumentException("Account number must be exactly 10 digits");
        }
        if (!accountNum.matches("\\d+")) {
            throw new IllegalArgumentException("Account number must contain only digits");
        }
        
        // Validate routing number
        if (routingNum == null || routingNum.length() != 9) {
            throw new IllegalArgumentException("Routing number must be exactly 9 digits");
        }
        
        // Validate account holder name
        if (holderName == null || holderName.trim().isEmpty()) {
            throw new IllegalArgumentException("Account holder name cannot be null or empty");
        }
        if (holderName.trim().length() < 2) {
            throw new IllegalArgumentException("Account holder name must be at least 2 characters");
        }
        
        // Validate account type
        if (!isValidAccountType(type)) {
            throw new IllegalArgumentException("Invalid account type: " + type + 
                ". Must be Checking, Savings, or Business");
        }
        
        // Validate initial balance
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        if (initialBalance < minBalance) {
            throw new IllegalArgumentException("Initial balance ($" + initialBalance + 
                ") is below minimum required ($" + minBalance + ")");
        }
        
        // Validate PIN
        if (pin == null || pin.length() != 4) {
            throw new IllegalArgumentException("PIN must be exactly 4 digits");
        }
        if (!pin.matches("\\d+")) {
            throw new IllegalArgumentException("PIN must contain only digits");
        }
        
        // Validate interest rate
        if (interestRate < 0 || interestRate > 0.10) {
            throw new IllegalArgumentException("Interest rate must be between 0% and 10%");
        }
        
        // ═══════════════════════════════════════════════════════════════════
        // INITIALIZATION - Setting up the perfect initial state
        // ═══════════════════════════════════════════════════════════════════
        
        // Initialize immutable fields
        this.accountNumber = accountNum;
        this.routingNumber = routingNum;
        this.accountType = type;
        this.creationDate = LocalDateTime.now();
        this.securityPin = pin;
        
        // Initialize mutable fields
        this.accountHolderName = holderName.trim();
        this.balance = initialBalance;
        this.isActive = true;
        this.interestRate = interestRate;
        this.minimumBalance = minBalance;
        this.transactionCount = 0;
        this.lastTransactionDate = getCurrentTimestamp();
        
        // Log successful account creation
        System.out.println("🏦 ACCOUNT CREATED SUCCESSFULLY");
        System.out.println("   Account Number: " + accountNumber);
        System.out.println("   Holder: " + accountHolderName);
        System.out.println("   Type: " + accountType);
        System.out.println("   Initial Balance: $" + String.format("%.2f", balance));
        System.out.println("   Creation Date: " + creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("   Interest Rate: " + String.format("%.2f", interestRate * 100) + "%");
    }
    
    /**
     * STANDARD CONSTRUCTOR - Most common account creation scenario
     * For regular customers opening standard accounts
     */
    public BankAccount(String accountNum, String holderName, String type, 
                      double initialBalance, String pin) {
        this(accountNum, 
             "123456789",  // Default routing number
             holderName, 
             type, 
             initialBalance, 
             pin, 
             getDefaultInterestRate(type),  // Type-specific interest rate
             getMinimumBalance(type));      // Type-specific minimum balance
        
        System.out.println("✅ Standard account creation completed");
    }
    
    /**
     * PREMIUM CONSTRUCTOR - For high-value accounts with custom terms
     * Allows specification of custom interest rates and minimum balances
     */
    public BankAccount(String accountNum, String routingNum, String holderName, 
                      String type, double initialBalance, String pin, 
                      double customInterestRate) {
        this(accountNum, 
             routingNum, 
             holderName, 
             type, 
             initialBalance, 
             pin, 
             customInterestRate, 
             getMinimumBalance(type));
        
        System.out.println("✅ Premium account creation completed with custom interest rate");
    }
    
    /**
     * BUSINESS CONSTRUCTOR - Specifically for business accounts
     * Includes business-specific validation and setup
     */
    public BankAccount(String accountNum, String businessName, double initialBalance, String pin) {
        this(accountNum, 
             "123456789", 
             validateBusinessName(businessName), 
             "Business", 
             initialBalance, 
             pin, 
             0.005,  // 0.5% interest for business accounts
             1000.0); // $1000 minimum for business accounts
        
        System.out.println("✅ Business account creation completed");
        System.out.println("   Business Name: " + businessName);
        System.out.println("   Enhanced business features activated");
    }
    
    /**
     * SAVINGS CONSTRUCTOR - Optimized for savings accounts
     * Higher interest rate, higher minimum balance
     */
    public BankAccount(String accountNum, String holderName, double initialBalance, String pin) {
        this(accountNum, 
             "123456789", 
             holderName, 
             "Savings", 
             initialBalance, 
             pin, 
             0.025,  // 2.5% interest for savings
             500.0); // $500 minimum for savings
        
        System.out.println("✅ Savings account creation completed");
        System.out.println("   High-yield savings features activated");
        System.out.println("   Automatic interest compounding enabled");
    }
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🏭 FACTORY METHODS - Meaningful Account Creation
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Factory method for creating student accounts with special terms
     */
    public static BankAccount createStudentAccount(String studentName, String studentId, String pin) {
        String accountNum = generateAccountNumber("STU");
        
        // Validate student ID
        if (studentId == null || studentId.length() < 5) {
            throw new IllegalArgumentException("Valid student ID required");
        }
        
        System.out.println("🎓 Creating student account for: " + studentName);
        System.out.println("   Student ID: " + studentId);
        System.out.println("   Special student benefits applied");
        
        return new BankAccount(accountNum, 
                              "123456789", 
                              studentName + " (Student)", 
                              "Checking", 
                              25.0,   // $25 initial deposit for students
                              pin, 
                              0.01,   // 1% interest for students
                              0.0);   // No minimum balance for students
    }
    
    /**
     * Factory method for creating joint accounts
     */
    public static BankAccount createJointAccount(String primaryHolder, String secondaryHolder, 
                                               String type, double initialBalance, String pin) {
        String accountNum = generateAccountNumber("JNT");
        String jointName = primaryHolder + " & " + secondaryHolder;
        
        System.out.println("👥 Creating joint account");
        System.out.println("   Primary: " + primaryHolder);
        System.out.println("   Secondary: " + secondaryHolder);
        
        return new BankAccount(accountNum, 
                              "123456789", 
                              jointName, 
                              type, 
                              initialBalance, 
                              pin, 
                              getDefaultInterestRate(type), 
                              getMinimumBalance(type));
    }
    
    /**
     * Factory method for creating accounts from existing account data (loading from database)
     */
    public static BankAccount fromExistingData(String accountNum, String routingNum, 
                                             String holderName, String type, double balance, 
                                             String pin, double interestRate, double minBalance, 
                                             LocalDateTime creationDate, boolean isActive, 
                                             int transactionCount) {
        System.out.println("📂 Loading existing account from data");
        System.out.println("   Account: " + accountNum);
        System.out.println("   Created: " + creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        
        // Create account using master constructor
        BankAccount account = new BankAccount(accountNum, routingNum, holderName, type, 
                                            balance, pin, interestRate, minBalance);
        
        // Override creation date and other historical data
        // Note: In real implementation, you'd use reflection or have a special constructor
        account.isActive = isActive;
        account.transactionCount = transactionCount;
        
        System.out.println("✅ Account loaded successfully");
        return account;
    }
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🛠️ HELPER METHODS - Supporting the Construction Process
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Validate account type
     */
    private static boolean isValidAccountType(String type) {
        return type != null && 
               (type.equals("Checking") || type.equals("Savings") || type.equals("Business"));
    }
    
    /**
     * Get default interest rate based on account type
     */
    private static double getDefaultInterestRate(String type) {
        switch (type) {
            case "Checking": return 0.001;  // 0.1%
            case "Savings": return 0.025;   // 2.5%
            case "Business": return 0.005;  // 0.5%
            default: return 0.001;
        }
    }
    
    /**
     * Get minimum balance based on account type
     */
    private static double getMinimumBalance(String type) {
        switch (type) {
            case "Checking": return 100.0;
            case "Savings": return 500.0;
            case "Business": return 1000.0;
            default: return 100.0;
        }
    }
    
    /**
     * Validate business name
     */
    private static String validateBusinessName(String businessName) {
        if (businessName == null || businessName.trim().isEmpty()) {
            throw new IllegalArgumentException("Business name cannot be empty");
        }
        if (businessName.trim().length() < 3) {
            throw new IllegalArgumentException("Business name must be at least 3 characters");
        }
        return businessName.trim();
    }
    
    /**
     * Generate account number with prefix
     */
    private static String generateAccountNumber(String prefix) {
        // Simplified account number generation
        long timestamp = System.currentTimeMillis();
        String number = String.valueOf(timestamp).substring(3, 10);
        return prefix + number;
    }
    
    /**
     * Get current timestamp as string
     */
    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    // ═══════════════════════════════════════════════════════════════════════
    // 💰 ACCOUNT BEHAVIOR - What BankAccounts CAN DO
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Deposit money into the account
     */
    public void deposit(double amount) {
        if (!isActive) {
            throw new IllegalStateException("Cannot deposit to inactive account");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        
        balance += amount;
        transactionCount++;
        lastTransactionDate = getCurrentTimestamp();
        
        System.out.println("💰 Deposit: $" + String.format("%.2f", amount));
        System.out.println("   New Balance: $" + String.format("%.2f", balance));
    }
    
    /**
     * Withdraw money from the account
     */
    public boolean withdraw(double amount) {
        if (!isActive) {
            throw new IllegalStateException("Cannot withdraw from inactive account");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (balance - amount < minimumBalance) {
            System.out.println("❌ Withdrawal denied - would violate minimum balance requirement");
            System.out.println("   Requested: $" + String.format("%.2f", amount));
            System.out.println("   Available: $" + String.format("%.2f", balance - minimumBalance));
            return false;
        }
        
        balance -= amount;
        transactionCount++;
        lastTransactionDate = getCurrentTimestamp();
        
        System.out.println("💸 Withdrawal: $" + String.format("%.2f", amount));
        System.out.println("   New Balance: $" + String.format("%.2f", balance));
        return true;
    }
    
    /**
     * Display complete account information
     */
    public void displayAccountInfo() {
        System.out.println("🏦 ═══════════════════════════════════════");
        System.out.println("   ACCOUNT INFORMATION");
        System.out.println("   ═══════════════════════════════════════");
        System.out.println("   Account Number: " + accountNumber);
        System.out.println("   Routing Number: " + routingNumber);
        System.out.println("   Account Holder: " + accountHolderName);
        System.out.println("   Account Type: " + accountType);
        System.out.println("   Current Balance: $" + String.format("%.2f", balance));
        System.out.println("   Minimum Balance: $" + String.format("%.2f", minimumBalance));
        System.out.println("   Interest Rate: " + String.format("%.3f", interestRate * 100) + "%");
        System.out.println("   Account Status: " + (isActive ? "Active" : "Inactive"));
        System.out.println("   Created: " + creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        System.out.println("   Total Transactions: " + transactionCount);
        System.out.println("   Last Transaction: " + lastTransactionDate);
        System.out.println("   ═══════════════════════════════════════");
    }
    
    /**
     * Calculate and apply interest
     */
    public void applyInterest() {
        if (!isActive || balance <= 0) {
            return;
        }
        
        double interest = balance * interestRate / 12; // Monthly interest
        balance += interest;
        transactionCount++;
        lastTransactionDate = getCurrentTimestamp();
        
        System.out.println("📈 Interest Applied: $" + String.format("%.2f", interest));
        System.out.println("   New Balance: $" + String.format("%.2f", balance));
    }
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🔒 GETTERS - Controlled Access to Account Information
    // ═══════════════════════════════════════════════════════════════════════
    
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
    public boolean isActive() { return isActive; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public int getTransactionCount() { return transactionCount; }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 💳 CREDITCARD CLASS - IMMUTABLE OBJECTS THROUGH CONSTRUCTORS
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * CreditCard class demonstrating immutable object creation
 * Once created, credit card details cannot be changed (as they shouldn't be in real life)
 */
class CreditCard {
    
    // All fields are final - cannot be changed after construction
    private final String cardNumber;
    private final String cardHolderName;
    private final String expirationDate;
    private final String cvv;
    private final String cardType;
    private final double creditLimit;
    private final LocalDateTime issueDate;
    
    /**
     * Constructor for new credit card
     * Validates all information and creates immutable card object
     */
    public CreditCard(String cardNum, String holderName, String expDate, 
                     String cvvCode, String type, double limit) {
        
        // Comprehensive validation
        if (cardNum == null || cardNum.length() != 16 || !cardNum.matches("\\d+")) {
            throw new IllegalArgumentException("Card number must be exactly 16 digits");
        }
        
        if (holderName == null || holderName.trim().length() < 2) {
            throw new IllegalArgumentException("Cardholder name must be at least 2 characters");
        }
        
        if (expDate == null || !expDate.matches("\\d{2}/\\d{2}")) {
            throw new IllegalArgumentException("Expiration date must be in MM/YY format");
        }
        
        if (cvvCode == null || cvvCode.length() != 3 || !cvvCode.matches("\\d+")) {
            throw new IllegalArgumentException("CVV must be exactly 3 digits");
        }
        
        if (!isValidCardType(type)) {
            throw new IllegalArgumentException("Invalid card type: " + type);
        }
        
        if (limit <= 0 || limit > 100000) {
            throw new IllegalArgumentException("Credit limit must be between $1 and $100,000");
        }
        
        // Initialize immutable fields
        this.cardNumber = cardNum;
        this.cardHolderName = holderName.trim().toUpperCase();
        this.expirationDate = expDate;
        this.cvv = cvvCode;
        this.cardType = type;
        this.creditLimit = limit;
        this.issueDate = LocalDateTime.now();
        
        System.out.println("💳 Credit Card Issued");
        System.out.println("   Card Type: " + cardType);
        System.out.println("   Holder: " + cardHolderName);
        System.out.println("   Card Number: ****-****-****-" + cardNumber.substring(12));
        System.out.println("   Credit Limit: $" + String.format("%.2f", creditLimit));
        System.out.println("   Expires: " + expirationDate);
    }
    
    /**
     * Factory method for creating different types of credit cards
     */
    public static CreditCard createStandardCard(String holderName) {
        String cardNum = generateCardNumber();
        String expDate = generateExpirationDate(3); // 3 years from now
        String cvv = generateCVV();
        
        return new CreditCard(cardNum, holderName, expDate, cvv, "Standard", 5000.0);
    }
    
    public static CreditCard createPremiumCard(String holderName) {
        String cardNum = generateCardNumber();
        String expDate = generateExpirationDate(5); // 5 years from now
        String cvv = generateCVV();
        
        return new CreditCard(cardNum, holderName, expDate, cvv, "Premium", 25000.0);
    }
    
    // Helper methods
    private static boolean isValidCardType(String type) {
        return type != null && (type.equals("Standard") || type.equals("Premium") || type.equals("Business"));
    }
    
    private static String generateCardNumber() {
        // Simplified card number generation
        StringBuilder sb = new StringBuilder("4532"); // Visa prefix
        for (int i = 0; i < 12; i++) {
            sb.append((int)(Math.random() * 10));
        }
        return sb.toString();
    }
    
    private static String generateExpirationDate(int yearsFromNow) {
        LocalDateTime future = LocalDateTime.now().plusYears(yearsFromNow);
        return String.format("%02d/%02d", future.getMonthValue(), future.getYear() % 100);
    }
    
    private static String generateCVV() {
        return String.format("%03d", (int)(Math.random() * 1000));
    }
    
    // Getters only - no setters because object is immutable
    public String getCardNumber() { return cardNumber; }
    public String getCardHolderName() { return cardHolderName; }
    public String getExpirationDate() { return expirationDate; }
    public String getCardType() { return cardType; }
    public double getCreditLimit() { return creditLimit; }
    public LocalDateTime getIssueDate() { return issueDate; }
    
    public String getMaskedCardNumber() {
        return "****-****-****-" + cardNumber.substring(12);
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🏛️ BANK CLASS - ORCHESTRATING MULTIPLE OBJECT CREATION
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Bank class that manages multiple accounts and demonstrates
 * how constructors work together in a larger system
 */
class Bank {
    
    private String bankName;
    private String bankCode;
    private BankAccount[] accounts;
    private int accountCount;
    private final int maxAccounts;
    
    /**
     * Constructor for creating a new bank
     */
    public Bank(String name, String code, int maxAccountCapacity) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Bank name cannot be empty");
        }
        if (code == null || code.length() != 4) {
            throw new IllegalArgumentException("Bank code must be exactly 4 characters");
        }
        if (maxAccountCapacity <= 0) {
            throw new IllegalArgumentException("Maximum account capacity must be positive");
        }
        
        this.bankName = name.trim();
        this.bankCode = code.toUpperCase();
        this.maxAccounts = maxAccountCapacity;
        this.accounts = new BankAccount[maxAccounts];
        this.accountCount = 0;
        
        System.out.println("🏛️ Bank Established: " + bankName);
        System.out.println("   Bank Code: " + bankCode);
        System.out.println("   Maximum Accounts: " + maxAccounts);
    }
    
    /**
     * Create and add a new account to the bank
     */
    public BankAccount openAccount(String holderName, String accountType, 
                                 double initialBalance, String pin) {
        if (accountCount >= maxAccounts) {
            throw new IllegalStateException("Bank has reached maximum account capacity");
        }
        
        String accountNumber = generateBankAccountNumber();
        BankAccount newAccount = new BankAccount(accountNumber, holderName, accountType, initialBalance, pin);
        
        accounts[accountCount] = newAccount;
        accountCount++;
        
        System.out.println("✅ Account added to " + bankName);
        return newAccount;
    }
    
    /**
     * Open student account using factory method
     */
    public BankAccount openStudentAccount(String studentName, String studentId, String pin) {
        if (accountCount >= maxAccounts) {
            throw new IllegalStateException("Bank has reached maximum account capacity");
        }
        
        BankAccount studentAccount = BankAccount.createStudentAccount(studentName, studentId, pin);
        accounts[accountCount] = studentAccount;
        accountCount++;
        
        System.out.println("✅ Student account added to " + bankName);
        return studentAccount;
    }
    
    /**
     * Display all accounts in the bank
     */
    public void displayAllAccounts() {
        System.out.println("🏛️ " + bankName + " - All Accounts");
        System.out.println("   Total Accounts: " + accountCount + "/" + maxAccounts);
        System.out.println("   ═══════════════════════════════════════");
        
        for (int i = 0; i < accountCount; i++) {
            BankAccount account = accounts[i];
            System.out.println("   Account " + (i + 1) + ": " + account.getAccountNumber());
            System.out.println("   Holder: " + account.getAccountHolderName());
            System.out.println("   Type: " + account.getAccountType());
            System.out.println("   Balance: $" + String.format("%.2f", account.getBalance()));
            System.out.println("   ───────────────────────────────────────");
        }
    }
    
    private String generateBankAccountNumber() {
        return bankCode + String.format("%06d", accountCount + 1);
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🎪 BANKING SYSTEM DEMONSTRATION - CONSTRUCTORS IN ACTION
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Main class demonstrating the complete banking system
 * Shows how different constructor patterns work together
 */
public class AdvancedBankingSystem {
    
    public static void main(String[] args) {
        
        System.out.println("🏦 ═══════════════════════════════════════════════════════");
        System.out.println("   ADVANCED BANKING SYSTEM - CONSTRUCTOR MASTERY");
        System.out.println("   Demonstrating the Sacred Art of Object Creation!");
        System.out.println("   ═══════════════════════════════════════════════════════");
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🏛️ BANK CREATION - Institutional Constructor
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n🏛️ CREATING BANK INSTITUTION...\n");
        
        Bank firstNationalBank = new Bank("First National Bank", "FNB1", 10);
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 💳 ACCOUNT CREATION - Multiple Constructor Patterns
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("💳 CREATING VARIOUS ACCOUNT TYPES...\n");
        
        // Standard account creation
        BankAccount aliceAccount = firstNationalBank.openAccount("Alice Johnson", "Checking", 1500.0, "1234");
        System.out.println();
        
        // Savings account creation (using specialized constructor)
        BankAccount bobSavings = new BankAccount("5551234567", "Bob Smith", 2500.0, "5678");
        System.out.println();
        
        // Business account creation
        BankAccount techCorpAccount = new BankAccount("9998887777", "TechCorp Solutions", 15000.0, "9999");
        System.out.println();
        
        // Student account creation (using factory method)
        BankAccount studentAccount = firstNationalBank.openStudentAccount("Charlie Brown", "STU12345", "0000");
        System.out.println();
        
        // Joint account creation (using factory method)
        BankAccount jointAccount = BankAccount.createJointAccount("Diana Prince", "Steve Trevor", 
                                                                "Checking", 3000.0, "1111");
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 💳 CREDIT CARD CREATION - Immutable Objects
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("💳 CREATING CREDIT CARDS...\n");
        
        // Standard credit card
        CreditCard standardCard = CreditCard.createStandardCard("Alice Johnson");
        System.out.println();
        
        // Premium credit card
        CreditCard premiumCard = CreditCard.createPremiumCard("Bob Smith");
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🎯 CONSTRUCTOR VALIDATION DEMONSTRATION
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🎯 TESTING CONSTRUCTOR VALIDATION...\n");
        
        // Test valid account creation
        try {
            BankAccount validAccount = new BankAccount("1234567890", "Valid Customer", "Checking", 500.0, "1234");
            System.out.println("✅ Valid account created successfully");
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
        
        // Test invalid account creation
        try {
            BankAccount invalidAccount = new BankAccount("123", "", "InvalidType", -100.0, "12");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Validation correctly caught invalid account: " + e.getMessage());
        }
        
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 💰 ACCOUNT OPERATIONS - Using Well-Constructed Objects
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("💰 PERFORMING ACCOUNT OPERATIONS...\n");
        
        // Perform operations on properly constructed accounts
        aliceAccount.deposit(250.0);
        aliceAccount.withdraw(100.0);
        aliceAccount.applyInterest();
        System.out.println();
        
        bobSavings.deposit(500.0);
        bobSavings.applyInterest();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 📊 SYSTEM REPORTING - Demonstrating Object State
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("📊 ACCOUNT INFORMATION REPORTS...\n");
        
        aliceAccount.displayAccountInfo();
        System.out.println();
        
        bobSavings.displayAccountInfo();
        System.out.println();
        
        // Bank-wide account summary
        firstNationalBank.displayAllAccounts();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🏆 CONSTRUCTOR MASTERY DEMONSTRATION COMPLETE
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🏆 CONSTRUCTOR MASTERY DEMONSTRATED!\n");
        
        System.out.println("✅ CONSTRUCTOR PATTERNS SHOWCASED:");
        System.out.println("  • Multiple constructor overloading for flexibility");
        System.out.println("  • Constructor chaining with this() to avoid duplication");
        System.out.println("  • Comprehensive parameter validation ensuring object integrity");
        System.out.println("  • Factory methods for meaningful object creation");
        System.out.println("  • Immutable objects through final fields and no setters");
        System.out.println("  • Business logic integration in constructors");
        System.out.println("  • Error handling and graceful failure");
        
        System.out.println("\n✅ OBJECT CREATION EXCELLENCE:");
        System.out.println("  • Every object born in a valid, well-defined state");
        System.out.println("  • Constructors as guardians of object integrity");
        System.out.println("  • Flexible creation patterns for different use cases");
        System.out.println("  • Clear, meaningful object initialization");
        System.out.println("  • Professional-grade validation and error handling");
        
        System.out.println("\n🏦 This demonstrates the ultimate power of Constructors:");
        System.out.println("   The sacred ritual that transforms abstract blueprints");
        System.out.println("   into living, breathing objects ready to serve their purpose!");
    }
}

/*
 * 🎓 KEY LEARNING OUTCOMES FROM THIS EXAMPLE:
 * 
 * 1. CONSTRUCTOR VERSATILITY:
 *    ✅ Multiple constructors for different creation scenarios
 *    ✅ Constructor chaining to eliminate code duplication
 *    ✅ Factory methods for meaningful, self-documenting creation
 *    ✅ Private constructors for controlled object creation
 * 
 * 2. VALIDATION AND INTEGRITY:
 *    ✅ Comprehensive parameter validation in constructors
 *    ✅ Ensuring objects are born in valid states
 *    ✅ Clear error messages for invalid construction attempts
 *    ✅ Business rule enforcement at creation time
 * 
 * 3. IMMUTABILITY THROUGH CONSTRUCTORS:
 *    ✅ Using final fields to create immutable objects
 *    ✅ Constructor as the only way to set object state
 *    ✅ Security and predictability through immutability
 * 
 * 4. REAL-WORLD MODELING:
 *    ✅ Banking system that mirrors real financial institutions
 *    ✅ Different account types with appropriate initialization
 *    ✅ Professional-grade object lifecycle management
 *    ✅ Complex business logic handled gracefully in constructors
 * 
 * 5. SYSTEM INTEGRATION:
 *    ✅ Multiple classes working together through constructors
 *    ✅ Factory methods coordinating complex object creation
 *    ✅ Proper error propagation and handling
 *    ✅ Scalable patterns for large systems
 * 
 * This example proves that Constructors are far more than initialization
 * methods - they are the guardians of object integrity, the architects
 * of proper state, and the foundation of reliable, professional software.
 * 
 * Master constructors, and you master the very essence of bringing
 * well-designed objects into existence!
 */
