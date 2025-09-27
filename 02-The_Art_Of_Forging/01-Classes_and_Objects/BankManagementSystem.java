/**
 * 🏦 PRACTICAL EXAMPLE: BANK ACCOUNT MANAGEMENT SYSTEM
 * 
 * This living manuscript demonstrates Classes and Objects through a realistic
 * Bank Account Management System. Here, you'll witness how Objects model
 * real-world entities with state and behavior.
 * 
 * Key Concepts Demonstrated:
 * - Class as blueprint for BankAccount objects
 * - Object state (account data) and behavior (banking operations)
 * - Multiple objects from same class with different data
 * - Object interaction and method calls
 * - Real-world problem solving with OOP
 */

// ═══════════════════════════════════════════════════════════════════════════════
// 🏛️ THE BANKACCOUNT CLASS - BLUEPRINT FOR ALL BANK ACCOUNTS
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * BankAccount Class - The blueprint that defines what every bank account IS
 * and what every bank account CAN DO.
 * 
 * This Class models a real bank account with:
 * - State: account number, holder name, balance, account type
 * - Behavior: deposit, withdraw, check balance, transfer money
 */
class BankAccount {
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🎯 OBJECT STATE - What a BankAccount IS (Fields/Attributes)
    // ═══════════════════════════════════════════════════════════════════════
    
    String accountNumber;      // Unique identifier for this account
    String accountHolderName;  // Name of the person who owns this account
    double balance;            // Current amount of money in this account
    String accountType;        // Type: "Checking", "Savings", "Business"
    boolean isActive;          // Whether this account is currently active
    int transactionCount;      // Number of transactions performed
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🚀 OBJECT BEHAVIOR - What a BankAccount CAN DO (Methods)
    // ═══════════════════════════════════════════════════════════════════════
    
    /**
     * Initialize a new bank account with starting values
     * This method sets up the account when it's first created
     */
    void initializeAccount(String accNumber, String holderName, String type, double initialDeposit) {
        accountNumber = accNumber;
        accountHolderName = holderName;
        accountType = type;
        balance = initialDeposit;
        isActive = true;
        transactionCount = 0;
        
        System.out.println("🎉 New " + accountType + " account created for " + accountHolderName);
        System.out.println("   Account Number: " + accountNumber);
        System.out.println("   Initial Balance: $" + balance);
        System.out.println("   Status: Active");
    }
    
    /**
     * Deposit money into this account
     * Increases the balance and records the transaction
     */
    void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid deposit amount. Must be greater than $0.");
            return;
        }
        
        if (!isActive) {
            System.out.println("❌ Cannot deposit to inactive account " + accountNumber);
            return;
        }
        
        balance += amount;
        transactionCount++;
        
        System.out.println("💰 Deposit successful!");
        System.out.println("   Account: " + accountNumber + " (" + accountHolderName + ")");
        System.out.println("   Deposited: $" + amount);
        System.out.println("   New Balance: $" + balance);
    }
    
    /**
     * Withdraw money from this account
     * Decreases the balance if sufficient funds exist
     */
    boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid withdrawal amount. Must be greater than $0.");
            return false;
        }
        
        if (!isActive) {
            System.out.println("❌ Cannot withdraw from inactive account " + accountNumber);
            return false;
        }
        
        if (balance < amount) {
            System.out.println("❌ Insufficient funds!");
            System.out.println("   Requested: $" + amount);
            System.out.println("   Available: $" + balance);
            return false;
        }
        
        balance -= amount;
        transactionCount++;
        
        System.out.println("💸 Withdrawal successful!");
        System.out.println("   Account: " + accountNumber + " (" + accountHolderName + ")");
        System.out.println("   Withdrawn: $" + amount);
        System.out.println("   New Balance: $" + balance);
        
        return true;
    }
    
    /**
     * Check the current balance of this account
     * Returns the current balance without modifying it
     */
    double checkBalance() {
        System.out.println("💳 Balance Inquiry for Account " + accountNumber);
        System.out.println("   Account Holder: " + accountHolderName);
        System.out.println("   Current Balance: $" + balance);
        System.out.println("   Account Status: " + (isActive ? "Active" : "Inactive"));
        
        return balance;
    }
    
    /**
     * Transfer money from this account to another account
     * This demonstrates object interaction - one object calling methods on another
     */
    boolean transferTo(BankAccount destinationAccount, double amount) {
        System.out.println("🔄 Initiating transfer...");
        System.out.println("   From: " + accountNumber + " (" + accountHolderName + ")");
        System.out.println("   To: " + destinationAccount.accountNumber + " (" + destinationAccount.accountHolderName + ")");
        System.out.println("   Amount: $" + amount);
        
        // First, try to withdraw from this account
        if (withdraw(amount)) {
            // If withdrawal successful, deposit to destination account
            destinationAccount.deposit(amount);
            System.out.println("✅ Transfer completed successfully!");
            return true;
        } else {
            System.out.println("❌ Transfer failed - insufficient funds in source account");
            return false;
        }
    }
    
    /**
     * Display complete account information
     * Shows all the state information for this account
     */
    void displayAccountInfo() {
        System.out.println("📋 ═══════════════════════════════════════");
        System.out.println("   ACCOUNT INFORMATION");
        System.out.println("   ═══════════════════════════════════════");
        System.out.println("   Account Number: " + accountNumber);
        System.out.println("   Account Holder: " + accountHolderName);
        System.out.println("   Account Type: " + accountType);
        System.out.println("   Current Balance: $" + balance);
        System.out.println("   Status: " + (isActive ? "Active" : "Inactive"));
        System.out.println("   Total Transactions: " + transactionCount);
        System.out.println("   ═══════════════════════════════════════");
    }
    
    /**
     * Close this account (deactivate it)
     * Changes the account status but preserves the data
     */
    void closeAccount() {
        if (!isActive) {
            System.out.println("❌ Account " + accountNumber + " is already closed");
            return;
        }
        
        if (balance > 0) {
            System.out.println("⚠️  Warning: Account has remaining balance of $" + balance);
            System.out.println("   Please withdraw all funds before closing");
            return;
        }
        
        isActive = false;
        System.out.println("🔒 Account " + accountNumber + " has been closed");
        System.out.println("   Final transaction count: " + transactionCount);
    }
    
    /**
     * Calculate interest for savings accounts
     * Demonstrates conditional behavior based on account type
     */
    void calculateInterest(double interestRate) {
        if (!accountType.equals("Savings")) {
            System.out.println("ℹ️  Interest calculation only applies to Savings accounts");
            return;
        }
        
        if (!isActive) {
            System.out.println("❌ Cannot calculate interest for inactive account");
            return;
        }
        
        double interest = balance * (interestRate / 100);
        balance += interest;
        transactionCount++;
        
        System.out.println("📈 Interest calculated and added!");
        System.out.println("   Interest Rate: " + interestRate + "%");
        System.out.println("   Interest Earned: $" + interest);
        System.out.println("   New Balance: $" + balance);
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🏪 BANK MANAGEMENT SYSTEM - DEMONSTRATING MULTIPLE OBJECTS
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * This class demonstrates how to use the BankAccount class to create
 * and manage multiple bank account objects, showing the power of OOP
 */
public class BankManagementSystem {
    
    public static void main(String[] args) {
        
        System.out.println("🏦 ═══════════════════════════════════════════════════════");
        System.out.println("   WELCOME TO THE OBJECT-ORIENTED BANK MANAGEMENT SYSTEM");
        System.out.println("   ═══════════════════════════════════════════════════════");
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🌟 CREATING MULTIPLE BANKACCOUNT OBJECTS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n📝 CREATING NEW BANK ACCOUNTS...\n");
        
        // Create first bank account object
        BankAccount aliceAccount = new BankAccount();
        aliceAccount.initializeAccount("ACC001", "Alice Johnson", "Checking", 1500.00);
        
        System.out.println(); // Blank line for readability
        
        // Create second bank account object
        BankAccount bobAccount = new BankAccount();
        bobAccount.initializeAccount("ACC002", "Bob Smith", "Savings", 2500.00);
        
        System.out.println();
        
        // Create third bank account object
        BankAccount carolAccount = new BankAccount();
        carolAccount.initializeAccount("ACC003", "Carol Davis", "Business", 10000.00);
        
        // ═══════════════════════════════════════════════════════════════════════
        // 💰 DEMONSTRATING OBJECT BEHAVIOR - BANKING OPERATIONS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n💼 PERFORMING BANKING OPERATIONS...\n");
        
        // Each object can perform the same operations with their own data
        aliceAccount.deposit(500.00);
        System.out.println();
        
        bobAccount.withdraw(300.00);
        System.out.println();
        
        carolAccount.checkBalance();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🔄 OBJECT INTERACTION - MONEY TRANSFER BETWEEN ACCOUNTS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🔄 DEMONSTRATING MONEY TRANSFERS...\n");
        
        // Alice transfers money to Bob (object interaction!)
        aliceAccount.transferTo(bobAccount, 250.00);
        System.out.println();
        
        // Bob transfers money to Carol
        bobAccount.transferTo(carolAccount, 500.00);
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 📊 ACCOUNT MANAGEMENT OPERATIONS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("📊 ACCOUNT MANAGEMENT OPERATIONS...\n");
        
        // Calculate interest for Bob's savings account
        bobAccount.calculateInterest(2.5);
        System.out.println();
        
        // Try to calculate interest for Alice's checking account (should fail)
        aliceAccount.calculateInterest(2.5);
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 📋 DISPLAYING ALL ACCOUNT INFORMATION
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n📋 FINAL ACCOUNT SUMMARIES...\n");
        
        aliceAccount.displayAccountInfo();
        System.out.println();
        
        bobAccount.displayAccountInfo();
        System.out.println();
        
        carolAccount.displayAccountInfo();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🎪 DEMONSTRATING ARRAYS OF OBJECTS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🎪 MANAGING MULTIPLE ACCOUNTS WITH ARRAYS...\n");
        
        // Create an array to hold multiple BankAccount objects
        BankAccount[] allAccounts = {aliceAccount, bobAccount, carolAccount};
        
        // Calculate total bank assets
        double totalBankAssets = 0;
        int activeAccounts = 0;
        
        System.out.println("📊 BANK SUMMARY REPORT:");
        System.out.println("   ═══════════════════════════════════════");
        
        for (int i = 0; i < allAccounts.length; i++) {
            BankAccount account = allAccounts[i];
            
            System.out.println("   Account " + (i + 1) + ": " + account.accountHolderName + 
                             " (" + account.accountNumber + ")");
            System.out.println("   Balance: $" + account.balance + 
                             " | Transactions: " + account.transactionCount);
            
            totalBankAssets += account.balance;
            if (account.isActive) {
                activeAccounts++;
            }
        }
        
        System.out.println("   ═══════════════════════════════════════");
        System.out.println("   Total Bank Assets: $" + totalBankAssets);
        System.out.println("   Active Accounts: " + activeAccounts + "/" + allAccounts.length);
        System.out.println("   ═══════════════════════════════════════");
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🧪 TESTING ERROR HANDLING AND EDGE CASES
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n🧪 TESTING ERROR HANDLING...\n");
        
        // Test invalid operations
        aliceAccount.withdraw(-100);    // Negative amount
        System.out.println();
        
        aliceAccount.withdraw(10000);   // Insufficient funds
        System.out.println();
        
        aliceAccount.deposit(0);        // Zero deposit
        System.out.println();
        
        // Test account closure
        System.out.println("🔒 TESTING ACCOUNT CLOSURE...\n");
        
        // Create a test account with zero balance
        BankAccount testAccount = new BankAccount();
        testAccount.initializeAccount("TEST001", "Test User", "Checking", 100.00);
        System.out.println();
        
        // Withdraw all money
        testAccount.withdraw(100.00);
        System.out.println();
        
        // Now close the account
        testAccount.closeAccount();
        System.out.println();
        
        // Try to use closed account
        testAccount.deposit(50.00);
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🎯 FINAL DEMONSTRATION - THE POWER OF OBJECTS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🎯 THE POWER OF OBJECT-ORIENTED PROGRAMMING:\n");
        
        System.out.println("✅ Each BankAccount object maintains its own state");
        System.out.println("✅ Objects can interact with each other (transfers)");
        System.out.println("✅ Same methods work differently based on object data");
        System.out.println("✅ Easy to create and manage multiple similar entities");
        System.out.println("✅ Code is organized, reusable, and mirrors real world");
        System.out.println("✅ Each object is independent yet can collaborate");
        
        System.out.println("\n🏦 Bank Management System Demo Complete!");
        System.out.println("   This is the power of Classes and Objects in action!");
    }
    
    // ═══════════════════════════════════════════════════════════════════════════════
    // 🎓 HELPER METHODS FOR ADDITIONAL DEMONSTRATIONS
    // ═══════════════════════════════════════════════════════════════════════════════
    
    /**
     * Demonstrates creating objects in a method and returning them
     */
    public static BankAccount createNewAccount(String number, String name, String type, double balance) {
        BankAccount newAccount = new BankAccount();
        newAccount.initializeAccount(number, name, type, balance);
        return newAccount;
    }
    
    /**
     * Demonstrates passing objects as method parameters
     */
    public static void performBulkTransfer(BankAccount source, BankAccount[] destinations, double amountEach) {
        System.out.println("🔄 Performing bulk transfer from " + source.accountHolderName);
        
        for (BankAccount destination : destinations) {
            if (destination != source && destination.isActive) {
                source.transferTo(destination, amountEach);
                System.out.println();
            }
        }
    }
    
    /**
     * Demonstrates object comparison and finding specific objects
     */
    public static BankAccount findAccountByNumber(BankAccount[] accounts, String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.accountNumber.equals(accountNumber)) {
                return account;
            }
        }
        return null; // Not found
    }
}

/*
 * 🎓 KEY LEARNING OUTCOMES FROM THIS EXAMPLE:
 * 
 * 1. CLASS AS BLUEPRINT:
 *    - BankAccount class defines what all bank accounts have in common
 *    - Fields define the STATE (what an account IS)
 *    - Methods define the BEHAVIOR (what an account CAN DO)
 * 
 * 2. OBJECTS AS INSTANCES:
 *    - Each BankAccount object is unique with its own data
 *    - aliceAccount, bobAccount, carolAccount are separate entities
 *    - Same methods, different data = different results
 * 
 * 3. OBJECT INTERACTION:
 *    - Objects can call methods on other objects (transfers)
 *    - Objects can be passed as parameters to methods
 *    - Objects can be stored in arrays and collections
 * 
 * 4. REAL-WORLD MODELING:
 *    - Code structure mirrors real banking concepts
 *    - Each object represents a real-world entity
 *    - Operations match real banking operations
 * 
 * 5. BENEFITS DEMONSTRATED:
 *    - Code reusability (one class, many objects)
 *    - Data organization (related data stays together)
 *    - Behavior encapsulation (methods with the data they operate on)
 *    - Scalability (easy to add more accounts)
 *    - Maintainability (changes to class affect all objects)
 * 
 * This example shows why Object-Oriented Programming is powerful:
 * It lets us model complex real-world systems in a natural, organized way!
 */
