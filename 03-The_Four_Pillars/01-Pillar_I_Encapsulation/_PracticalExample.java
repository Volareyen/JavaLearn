/**
 * THE LIVING MANUSCRIPT: Secure Banking System
 * 
 * A comprehensive demonstration of Encapsulation principles through a
 * realistic banking system. This example showcases data protection,
 * controlled access, validation, security, and proper information hiding
 * in a real-world context.
 * 
 * This system demonstrates:
 * - Private data protection with controlled access
 * - Input validation and business rule enforcement
 * - Security through access control and authentication
 * - Implementation hiding with clean public interfaces
 * - Defensive programming against data corruption
 */

public class _PracticalExample {
    public static void main(String[] args) {
        System.out.println("=== SECURE BANKING SYSTEM ===");
        System.out.println("Demonstrating Encapsulation - The First Pillar\n");
        
        // DEMONSTRATION 1: Account Creation with Validation
        System.out.println("1. ACCOUNT CREATION WITH ENCAPSULATION:");
        BankAccount account1 = new BankAccount("John Doe", "john@email.com", 1000.0);
        BankAccount account2 = new BankAccount("Jane Smith", "jane@email.com", 500.0);
        
        account1.displayAccountInfo();
        account2.displayAccountInfo();
        
        // DEMONSTRATION 2: Controlled Access and Validation
        System.out.println("2. CONTROLLED ACCESS AND VALIDATION:");
        
        // Valid operations
        account1.deposit(250.0);
        account1.withdraw(100.0);
        
        // Invalid operations - encapsulation prevents corruption
        account1.deposit(-50.0);    // Negative deposit blocked
        account1.withdraw(2000.0);  // Insufficient funds blocked
        account1.withdraw(-100.0);  // Negative withdrawal blocked
        
        account1.displayAccountInfo();
        
        // DEMONSTRATION 3: Security Through Access Control
        System.out.println("3. SECURITY THROUGH ACCESS CONTROL:");
        
        // Account starts unlocked for normal operations
        account2.deposit(100.0);    // Should work
        
        // Lock account for security
        account2.lockAccount();
        
        // Operations blocked when locked
        account2.deposit(50.0);     // Blocked - account locked
        account2.withdraw(25.0);    // Blocked - account locked
        
        // Unlock and resume operations
        account2.unlockAccount();
        account2.deposit(75.0);     // Now works again
        
        account2.displayAccountInfo();
        
        // DEMONSTRATION 4: Data Integrity Protection
        System.out.println("4. DATA INTEGRITY PROTECTION:");
        
        // Attempt to set invalid data - encapsulation protects
        account1.updateEmail("invalid-email");      // Invalid format blocked
        account1.updateEmail("valid@email.com");    // Valid format accepted
        
        // Cannot directly access or modify private fields
        // account1.balance = 999999.0;             // Compilation error!
        // account1.accountNumber = "HACKED";       // Compilation error!
        
        System.out.println("Email updated successfully through controlled access");
        
        // DEMONSTRATION 5: Transaction History (Write-Only Logging)
        System.out.println("5. TRANSACTION HISTORY DEMONSTRATION:");
        
        account1.deposit(300.0);
        account1.withdraw(150.0);
        account1.transfer(200.0, account2);
        
        // Transaction history is write-only for security
        account1.displayTransactionSummary();
        account2.displayTransactionSummary();
        
        // DEMONSTRATION 6: Interest Calculation (Computed Properties)
        System.out.println("6. COMPUTED PROPERTIES DEMONSTRATION:");
        
        SavingsAccount savings = new SavingsAccount("Bob Wilson", "bob@email.com", 5000.0, 2.5);
        savings.displayAccountInfo();
        
        System.out.println("Monthly interest: $" + savings.calculateMonthlyInterest());
        System.out.println("Annual interest: $" + savings.calculateAnnualInterest());
        
        // DEMONSTRATION 7: Account Management System
        System.out.println("7. ACCOUNT MANAGEMENT SYSTEM:");
        
        AccountManager manager = new AccountManager();
        
        // Register accounts with the management system
        manager.registerAccount(account1);
        manager.registerAccount(account2);
        manager.registerAccount(savings);
        
        manager.displayAllAccounts();
        
        // DEMONSTRATION 8: Security Audit Trail
        System.out.println("8. SECURITY AUDIT DEMONSTRATION:");
        
        SecurityAuditor auditor = new SecurityAuditor();
        
        // Accounts report their activities to auditor
        account1.registerAuditor(auditor);
        account2.registerAuditor(auditor);
        
        // Perform operations that get audited
        account1.deposit(100.0);
        account1.withdraw(50.0);
        account2.transfer(75.0, account1);
        
        auditor.displayAuditLog();
        
        System.out.println("=== ENCAPSULATION DEMONSTRATION COMPLETE ===");
        System.out.println("All data protected, access controlled, validation enforced!");
    }
}

/**
 * BANK ACCOUNT: Core class demonstrating comprehensive encapsulation
 */
class BankAccount {
    // PRIVATE FIELDS: The protected inner sanctum
    private String accountNumber;        // Read-only after creation
    private String accountHolderName;    // Protected personal data
    private String email;               // Validated email address
    private double balance;             // Critical financial data
    private boolean isLocked;           // Security state
    private int transactionCount;       // Internal counter
    private String transactionHistory;  // Write-only audit log
    
    // CONSTRUCTOR: Controlled object creation with validation
    public BankAccount(String accountHolderName, String email, double initialBalance) {
        // Generate unique account number (read-only after creation)
        this.accountNumber = generateAccountNumber();
        
        // Validate and set account holder name
        setAccountHolderName(accountHolderName);
        
        // Validate and set email
        updateEmail(email);
        
        // Validate and set initial balance
        if (initialBalance >= 0) {
            this.balance = initialBalance;
            logTransaction("Account created with initial balance: $" + initialBalance);
        } else {
            this.balance = 0.0;
            logTransaction("Account created with zero balance (invalid initial amount rejected)");
        }
        
        // Initialize security and tracking
        this.isLocked = false;
        this.transactionCount = 0;
        this.transactionHistory = "";
    }
    
    // GETTER METHODS: Controlled read access to private data
    
    public String getAccountNumber() {
        return this.accountNumber;  // Read-only property
    }
    
    public String getAccountHolderName() {
        return this.accountHolderName;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public double getBalance() {
        if (isLocked) {
            System.out.println("Account locked - balance inquiry denied");
            return 0.0;  // Security: don't reveal balance when locked
        }
        return this.balance;
    }
    
    public boolean isLocked() {
        return this.isLocked;
    }
    
    public int getTransactionCount() {
        return this.transactionCount;
    }
    
    // SETTER METHODS: Controlled write access with validation
    
    public void setAccountHolderName(String accountHolderName) {
        if (isValidName(accountHolderName)) {
            this.accountHolderName = accountHolderName;
            logTransaction("Account holder name updated");
        } else {
            System.out.println("Invalid account holder name");
        }
    }
    
    public void updateEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
            logTransaction("Email address updated");
        } else {
            System.out.println("Invalid email format: " + email);
        }
    }
    
    // FINANCIAL OPERATIONS: Controlled business logic with validation
    
    public boolean deposit(double amount) {
        if (isLocked) {
            System.out.println("Account locked - deposit denied");
            return false;
        }
        
        if (amount > 0) {
            this.balance += amount;
            this.transactionCount++;
            logTransaction("Deposit: +$" + amount + " | New balance: $" + balance);
            System.out.println("Deposited $" + amount + " to " + accountHolderName + "'s account");
            return true;
        } else {
            System.out.println("Invalid deposit amount: $" + amount);
            logTransaction("Invalid deposit attempt: $" + amount);
            return false;
        }
    }
    
    public boolean withdraw(double amount) {
        if (isLocked) {
            System.out.println("Account locked - withdrawal denied");
            return false;
        }
        
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            this.transactionCount++;
            logTransaction("Withdrawal: -$" + amount + " | New balance: $" + balance);
            System.out.println("Withdrew $" + amount + " from " + accountHolderName + "'s account");
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient funds for withdrawal of $" + amount);
            logTransaction("Insufficient funds withdrawal attempt: $" + amount);
            return false;
        } else {
            System.out.println("Invalid withdrawal amount: $" + amount);
            logTransaction("Invalid withdrawal attempt: $" + amount);
            return false;
        }
    }
    
    public boolean transfer(double amount, BankAccount targetAccount) {
        if (isLocked) {
            System.out.println("Account locked - transfer denied");
            return false;
        }
        
        if (targetAccount == null) {
            System.out.println("Invalid target account for transfer");
            return false;
        }
        
        if (amount > 0 && amount <= balance) {
            if (this.withdraw(amount)) {  // Use existing validation
                if (targetAccount.deposit(amount)) {  // Use target's validation
                    logTransaction("Transfer: -$" + amount + " to " + targetAccount.getAccountNumber());
                    System.out.println("Transferred $" + amount + " to " + targetAccount.getAccountHolderName());
                    return true;
                } else {
                    // Rollback if target deposit fails
                    this.deposit(amount);
                    System.out.println("Transfer failed - target account rejected deposit");
                    return false;
                }
            }
        } else {
            System.out.println("Invalid transfer amount or insufficient funds: $" + amount);
        }
        return false;
    }
    
    // SECURITY OPERATIONS: Access control management
    
    public void lockAccount() {
        this.isLocked = true;
        logTransaction("Account locked for security");
        System.out.println("Account " + accountNumber + " locked for security");
    }
    
    public void unlockAccount() {
        this.isLocked = false;
        logTransaction("Account unlocked");
        System.out.println("Account " + accountNumber + " unlocked");
    }
    
    // AUDIT AND REPORTING: Information access with security
    
    public void displayAccountInfo() {
        System.out.println("=== Account Information ===");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Email: " + email);
        System.out.println("Balance: $" + (isLocked ? "***LOCKED***" : String.format("%.2f", balance)));
        System.out.println("Status: " + (isLocked ? "LOCKED" : "ACTIVE"));
        System.out.println("Transactions: " + transactionCount);
        System.out.println();
    }
    
    public void displayTransactionSummary() {
        System.out.println("Transaction Summary for " + accountHolderName + ":");
        System.out.println("Total Transactions: " + transactionCount);
        System.out.println("Current Balance: $" + (isLocked ? "***LOCKED***" : String.format("%.2f", balance)));
        // Note: Full transaction history is private for security
        if (transactionHistory.length() > 100) {  // Use transaction history for summary
            System.out.println("Transaction history contains " + transactionHistory.split("\n").length + " entries");
        }
        System.out.println();
    }
    
    // AUDITOR INTEGRATION: External monitoring support
    
    private SecurityAuditor auditor;
    
    public void registerAuditor(SecurityAuditor auditor) {
        this.auditor = auditor;
    }
    
    // PRIVATE METHODS: Implementation hiding
    
    private String generateAccountNumber() {
        return "ACC-" + System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
    }
    
    private boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() >= 2;
    }
    
    private boolean isValidEmail(String email) {
        return email != null && 
               email.contains("@") && 
               email.contains(".") &&
               email.indexOf("@") < email.lastIndexOf(".") &&
               email.length() > 5;
    }
    
    private void logTransaction(String transaction) {
        String timestamp = java.time.LocalDateTime.now().toString();
        String logEntry = timestamp + " - " + transaction + "\n";
        this.transactionHistory += logEntry;
        
        // Also notify auditor if registered
        if (auditor != null) {
            auditor.logAccountActivity(this.accountNumber, transaction);
        }
    }
}

/**
 * SAVINGS ACCOUNT: Inheritance with additional encapsulation
 */
class SavingsAccount extends BankAccount {
    private double interestRate;  // Private interest rate
    
    public SavingsAccount(String accountHolderName, String email, double initialBalance, double interestRate) {
        super(accountHolderName, email, initialBalance);
        setInterestRate(interestRate);
    }
    
    // Controlled access to interest rate
    public double getInterestRate() {
        return this.interestRate;
    }
    
    public void setInterestRate(double interestRate) {
        if (interestRate >= 0 && interestRate <= 10) {  // Reasonable range
            this.interestRate = interestRate;
        } else {
            System.out.println("Invalid interest rate: " + interestRate + "%");
        }
    }
    
    // Computed properties based on encapsulated data
    public double calculateMonthlyInterest() {
        return (getBalance() * interestRate / 100) / 12;
    }
    
    public double calculateAnnualInterest() {
        return getBalance() * interestRate / 100;
    }
    
    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Monthly Interest: $" + String.format("%.2f", calculateMonthlyInterest()));
    }
}

/**
 * ACCOUNT MANAGER: Demonstrates managing encapsulated objects
 */
class AccountManager {
    private BankAccount[] accounts;
    private int accountCount;
    
    public AccountManager() {
        this.accounts = new BankAccount[10];
        this.accountCount = 0;
    }
    
    public void registerAccount(BankAccount account) {
        if (accountCount < accounts.length && account != null) {
            accounts[accountCount] = account;
            accountCount++;
            System.out.println("Account registered: " + account.getAccountNumber());
        }
    }
    
    public void displayAllAccounts() {
        System.out.println("=== REGISTERED ACCOUNTS ===");
        for (int i = 0; i < accountCount; i++) {
            BankAccount account = accounts[i];
            System.out.println((i + 1) + ". " + account.getAccountHolderName() + 
                             " (" + account.getAccountNumber() + ") - Balance: $" + 
                             String.format("%.2f", account.getBalance()));
        }
        System.out.println();
    }
}

/**
 * SECURITY AUDITOR: Demonstrates write-only logging and monitoring
 */
class SecurityAuditor {
    private String auditLog;  // Write-only audit trail
    
    public SecurityAuditor() {
        this.auditLog = "";
    }
    
    // Write-only method - no getter for security
    public void logAccountActivity(String accountNumber, String activity) {
        String timestamp = java.time.LocalDateTime.now().toString();
        String logEntry = timestamp + " - Account: " + accountNumber + " - " + activity + "\n";
        this.auditLog += logEntry;
    }
    
    // Controlled access to audit information
    public void displayAuditLog() {
        System.out.println("=== SECURITY AUDIT LOG ===");
        if (auditLog.isEmpty()) {
            System.out.println("No audit entries recorded");
        } else {
            // Display only recent entries for security
            String[] entries = auditLog.split("\n");
            int startIndex = Math.max(0, entries.length - 10);  // Last 10 entries
            
            System.out.println("Recent audit entries:");
            for (int i = startIndex; i < entries.length; i++) {
                if (!entries[i].isEmpty()) {
                    System.out.println("  " + entries[i]);
                }
            }
        }
        System.out.println();
    }
}

/**
 * ENCAPSULATION MASTERY DEMONSTRATED:
 * 
 * This banking system showcases complete mastery of encapsulation through:
 * 
 * 1. DATA PROTECTION:
 *    - All critical fields (balance, account number, etc.) are private
 *    - No direct access to internal state from outside
 *    - Defensive programming prevents data corruption
 * 
 * 2. CONTROLLED ACCESS:
 *    - Public methods provide controlled access to private data
 *    - Input validation in all setter methods
 *    - Business rule enforcement in all operations
 * 
 * 3. SECURITY IMPLEMENTATION:
 *    - Account locking prevents unauthorized operations
 *    - Audit logging tracks all activities (write-only for security)
 *    - Sensitive information hidden when account is locked
 * 
 * 4. IMPLEMENTATION HIDING:
 *    - Private helper methods hide complex internal logic
 *    - Account number generation algorithm hidden
 *    - Validation logic encapsulated in private methods
 * 
 * 5. COMPUTED PROPERTIES:
 *    - Interest calculations performed on demand
 *    - No storage of derived data, calculated when needed
 *    - Clean separation between stored and computed values
 * 
 * 6. DEFENSIVE PROGRAMMING:
 *    - All inputs validated before processing
 *    - Invalid operations rejected with clear messages
 *    - State consistency maintained through controlled access
 * 
 * The result is a robust, secure, maintainable banking system where:
 * - Data integrity is guaranteed through encapsulation
 * - Security is enforced through access control
 * - Implementation can evolve without breaking external code
 * - Objects are self-protecting and intelligent
 * 
 * This demonstrates that encapsulation is not just about hiding data -
 * it's about creating intelligent, self-protecting objects that maintain
 * their own integrity while providing safe, controlled access to their
 * capabilities.
 */
