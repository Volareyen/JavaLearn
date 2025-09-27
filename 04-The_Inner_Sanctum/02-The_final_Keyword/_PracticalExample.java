/**
 * The Living Manuscript: Immutable Banking System
 * 
 * A comprehensive real-world demonstration of the `final` keyword
 * through an immutable banking and transaction system.
 * 
 * This system showcases:
 * - Final classes for immutable value objects
 * - Final methods for security-critical operations
 * - Final variables for constants and immutable state
 * - Template method pattern with final orchestration
 * - Thread-safe immutable objects
 */
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Final class representing an immutable Money value
 * Cannot be extended - represents a complete, secure concept
 */
public final class Money {
    // Final constants for the Money class
    private static final int SCALE = 2;  // Two decimal places
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    
    // Final instance variables - immutable state
    private final BigDecimal amount;
    private final String currency;
    private final LocalDateTime createdAt;
    
    /**
     * Constructor ensuring all final fields are initialized
     */
    public Money(BigDecimal amount, String currency) {
        // Validate and store final values
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be null or empty");
        }
        
        this.amount = amount.setScale(SCALE, ROUNDING_MODE);
        this.currency = currency.toUpperCase().trim();
        this.createdAt = LocalDateTime.now();
    }
    
    /**
     * Convenience constructor with double amount
     */
    public Money(double amount, String currency) {
        this(BigDecimal.valueOf(amount), currency);
    }
    
    // Final methods for core operations that must not be overridden
    public final BigDecimal getAmount() { return amount; }
    public final String getCurrency() { return currency; }
    public final LocalDateTime getCreatedAt() { return createdAt; }
    
    /**
     * Final method - addition operation that returns new Money object
     * This ensures immutability - original object is never modified
     */
    public final Money add(Money other) {
        validateSameCurrency(other);
        return new Money(this.amount.add(other.amount), this.currency);
    }
    
    /**
     * Final method - subtraction operation
     */
    public final Money subtract(Money other) {
        validateSameCurrency(other);
        return new Money(this.amount.subtract(other.amount), this.currency);
    }
    
    /**
     * Final method - multiplication by scalar
     */
    public final Money multiply(double factor) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(factor)), this.currency);
    }
    
    /**
     * Final method - division by scalar
     */
    public final Money divide(double divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return new Money(this.amount.divide(BigDecimal.valueOf(divisor), SCALE, ROUNDING_MODE), this.currency);
    }
    
    /**
     * Final method - comparison operations
     */
    public final boolean isGreaterThan(Money other) {
        validateSameCurrency(other);
        return this.amount.compareTo(other.amount) > 0;
    }
    
    public final boolean isLessThan(Money other) {
        validateSameCurrency(other);
        return this.amount.compareTo(other.amount) < 0;
    }
    
    public final boolean isEqualTo(Money other) {
        return this.currency.equals(other.currency) && 
               this.amount.compareTo(other.amount) == 0;
    }
    
    /**
     * Final helper method for currency validation
     */
    private final void validateSameCurrency(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot operate on different currencies: " + 
                                             this.currency + " vs " + other.currency);
        }
    }
    
    /**
     * Final method for string representation
     */
    public final String toFormattedString() {
        return String.format("%s %.2f", currency, amount);
    }
    
    @Override
    public final String toString() {
        return toFormattedString();
    }
    
    /**
     * Final equals method for proper immutable object comparison
     */
    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return Objects.equals(amount, money.amount) && 
               Objects.equals(currency, money.currency);
    }
    
    /**
     * Final hashCode method for proper immutable object hashing
     */
    @Override
    public final int hashCode() {
        return Objects.hash(amount, currency);
    }
}

/**
 * Final class representing an immutable transaction record
 */
final class Transaction {
    // Static final constants for transaction types
    public static final String TYPE_DEPOSIT = "DEPOSIT";
    public static final String TYPE_WITHDRAWAL = "WITHDRAWAL";
    public static final String TYPE_TRANSFER = "TRANSFER";
    public static final String TYPE_FEE = "FEE";
    
    // Final instance variables
    private final String transactionId;
    private final String type;
    private final Money amount;
    private final String description;
    private final LocalDateTime timestamp;
    private final String fromAccount;
    private final String toAccount;
    
    /**
     * Constructor for all final fields
     */
    public Transaction(String transactionId, String type, Money amount, 
                      String description, String fromAccount, String toAccount) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = LocalDateTime.now();
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }
    
    // Final getters - no setters for immutable object
    public final String getTransactionId() { return transactionId; }
    public final String getType() { return type; }
    public final Money getAmount() { return amount; }
    public final String getDescription() { return description; }
    public final LocalDateTime getTimestamp() { return timestamp; }
    public final String getFromAccount() { return fromAccount; }
    public final String getToAccount() { return toAccount; }
    
    /**
     * Final method for formatted display
     */
    public final String toFormattedString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] %s: %s %s -> %s (%s)", 
                           timestamp.format(formatter), type, amount,
                           fromAccount != null ? fromAccount : "N/A",
                           toAccount != null ? toAccount : "N/A", description);
    }
}

/**
 * Abstract class with template method pattern using final methods
 */
abstract class AccountProcessor {
    // Static final constants
    protected static final Money ZERO_USD = new Money(0.0, "USD");
    protected static final BigDecimal MIN_BALANCE = new BigDecimal("0.01");
    
    // Final template method - algorithm cannot be changed
    public final boolean processTransaction(Transaction transaction) {
        System.out.println("\n🔄 Processing Transaction: " + transaction.getTransactionId());
        
        // Fixed algorithm steps using final method
        if (!validateTransaction(transaction)) {
            System.out.println("❌ Transaction validation failed");
            return false;
        }
        
        if (!checkBusinessRules(transaction)) {
            System.out.println("❌ Business rules check failed");
            return false;
        }
        
        executeTransaction(transaction);  // Subclass implements
        recordTransaction(transaction);   // Subclass implements
        
        System.out.println("✅ Transaction processed successfully");
        return true;
    }
    
    /**
     * Final method - validation logic must not be overridden
     */
    private final boolean validateTransaction(Transaction transaction) {
        System.out.println("🔍 Validating transaction...");
        return transaction != null && 
               transaction.getTransactionId() != null && 
               transaction.getAmount() != null &&
               transaction.getAmount().getAmount().compareTo(BigDecimal.ZERO) > 0;
    }
    
    /**
     * Final method - security-critical business rules
     */
    protected final boolean checkBusinessRules(Transaction transaction) {
        System.out.println("📋 Checking business rules...");
        
        // Final local variables for business rule parameters
        final Money maxTransactionAmount = new Money(10000.0, "USD");
        final Set<String> allowedTypes = Set.of(
            Transaction.TYPE_DEPOSIT, 
            Transaction.TYPE_WITHDRAWAL, 
            Transaction.TYPE_TRANSFER,
            Transaction.TYPE_FEE
        );
        
        // Business rule checks using final variables
        if (!allowedTypes.contains(transaction.getType())) {
            System.out.println("❌ Invalid transaction type: " + transaction.getType());
            return false;
        }
        
        if (transaction.getAmount().isGreaterThan(maxTransactionAmount)) {
            System.out.println("❌ Transaction exceeds maximum allowed amount");
            return false;
        }
        
        return true;
    }
    
    // Abstract methods for subclasses to implement
    protected abstract void executeTransaction(Transaction transaction);
    protected abstract void recordTransaction(Transaction transaction);
}

/**
 * Concrete implementation of account processor
 */
class BankAccountProcessor extends AccountProcessor {
    // Final instance variables for processor configuration
    private final String processorId;
    private final String bankName;
    
    // Final collections for transaction history (reference is final, content is mutable)
    private final List<Transaction> transactionHistory;
    private final Map<String, Money> accountBalances;
    
    public BankAccountProcessor(String processorId, String bankName) {
        this.processorId = processorId;
        this.bankName = bankName;
        this.transactionHistory = new ArrayList<>();
        this.accountBalances = new HashMap<>();
    }
    
    /**
     * Final method for getting processor information
     */
    public final String getProcessorInfo() {
        return "Processor ID: " + processorId + ", Bank: " + bankName;
    }
    
    /**
     * Final method for account balance management
     */
    public final Money getAccountBalance(String accountId) {
        return accountBalances.getOrDefault(accountId, ZERO_USD);
    }
    
    /**
     * Final method for setting initial account balance
     */
    public final void initializeAccount(String accountId, Money initialBalance) {
        if (!accountBalances.containsKey(accountId)) {
            accountBalances.put(accountId, initialBalance);
            System.out.println("🏦 Account " + accountId + " initialized with " + initialBalance);
        }
    }
    
    @Override
    protected void executeTransaction(Transaction transaction) {
        final String type = transaction.getType();  // Final local variable
        
        switch (type) {
            case Transaction.TYPE_DEPOSIT:
                executeDeposit(transaction);
                break;
            case Transaction.TYPE_WITHDRAWAL:
                executeWithdrawal(transaction);
                break;
            case Transaction.TYPE_TRANSFER:
                executeTransfer(transaction);
                break;
            case Transaction.TYPE_FEE:
                executeFee(transaction);
                break;
        }
    }
    
    /**
     * Final methods for specific transaction types - core logic must not change
     */
    private final void executeDeposit(Transaction transaction) {
        final String account = transaction.getToAccount();
        final Money currentBalance = getAccountBalance(account);
        final Money newBalance = currentBalance.add(transaction.getAmount());
        
        accountBalances.put(account, newBalance);
        System.out.println("💰 Deposited " + transaction.getAmount() + " to " + account);
        System.out.println("   New balance: " + newBalance);
    }
    
    private final void executeWithdrawal(Transaction transaction) {
        final String account = transaction.getFromAccount();
        final Money currentBalance = getAccountBalance(account);
        final Money newBalance = currentBalance.subtract(transaction.getAmount());
        
        if (newBalance.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Insufficient funds for withdrawal");
        }
        
        accountBalances.put(account, newBalance);
        System.out.println("💸 Withdrew " + transaction.getAmount() + " from " + account);
        System.out.println("   New balance: " + newBalance);
    }
    
    private final void executeTransfer(Transaction transaction) {
        final String fromAccount = transaction.getFromAccount();
        final String toAccount = transaction.getToAccount();
        final Money amount = transaction.getAmount();
        
        // Create sub-transactions for transfer
        Transaction withdrawal = new Transaction(
            transaction.getTransactionId() + "_W",
            Transaction.TYPE_WITHDRAWAL, amount, "Transfer out",
            fromAccount, null
        );
        
        Transaction deposit = new Transaction(
            transaction.getTransactionId() + "_D",
            Transaction.TYPE_DEPOSIT, amount, "Transfer in",
            null, toAccount
        );
        
        executeWithdrawal(withdrawal);
        executeDeposit(deposit);
        System.out.println("🔄 Transferred " + amount + " from " + fromAccount + " to " + toAccount);
    }
    
    private final void executeFee(Transaction transaction) {
        final String account = transaction.getFromAccount();
        executeWithdrawal(transaction);  // Fees are like withdrawals
        System.out.println("💳 Applied fee of " + transaction.getAmount() + " to " + account);
    }
    
    @Override
    protected void recordTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
        System.out.println("📝 Transaction recorded: " + transaction.getTransactionId());
    }
    
    /**
     * Final method for displaying transaction history
     */
    public final void displayTransactionHistory() {
        System.out.println("\n📊 TRANSACTION HISTORY:");
        System.out.println("Processor: " + getProcessorInfo());
        System.out.println("Total Transactions: " + transactionHistory.size());
        System.out.println();
        
        for (final Transaction transaction : transactionHistory) {  // Final loop variable
            System.out.println(transaction.toFormattedString());
        }
    }
    
    /**
     * Final method for displaying all account balances
     */
    public final void displayAccountBalances() {
        System.out.println("\n💰 ACCOUNT BALANCES:");
        for (final Map.Entry<String, Money> entry : accountBalances.entrySet()) {
            System.out.println("  Account " + entry.getKey() + ": " + entry.getValue());
        }
    }
}

/**
 * Main demonstration class
 */
public class _PracticalExample {
    // Static final constants for demo configuration
    private static final String DEMO_BANK = "Sacred Scrolls Bank";
    private static final String PROCESSOR_ID = "PROC_001";
    
    public static void main(String[] args) {
        System.out.println("🏛️ IMMUTABLE BANKING SYSTEM DEMONSTRATION 🏛️\n");
        
        // ═══════════════════════════════════════════════════════════════
        // FINAL IMMUTABLE MONEY OBJECTS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("💰 CREATING IMMUTABLE MONEY OBJECTS...\n");
        
        final Money deposit1 = new Money(1000.0, "USD");
        final Money deposit2 = new Money(750.50, "USD");
        final Money withdrawalAmount = new Money(200.0, "USD");
        final Money transferAmount = new Money(300.0, "USD");
        final Money feeAmount = new Money(15.0, "USD");
        
        System.out.println("Created money objects (all final references):");
        System.out.println("Deposit 1: " + deposit1);
        System.out.println("Deposit 2: " + deposit2);
        System.out.println("Withdrawal: " + withdrawalAmount);
        System.out.println("Transfer: " + transferAmount);
        System.out.println("Fee: " + feeAmount);
        
        // ═══════════════════════════════════════════════════════════════
        // IMMUTABLE MONEY OPERATIONS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🧮 IMMUTABLE MONEY OPERATIONS...\n");
        
        final Money totalDeposits = deposit1.add(deposit2);
        final Money halfAmount = deposit1.divide(2);
        final Money doubledAmount = withdrawalAmount.multiply(2);
        
        System.out.println("Original deposit1: " + deposit1 + " (unchanged)");
        System.out.println("Original deposit2: " + deposit2 + " (unchanged)");
        System.out.println("Total deposits: " + totalDeposits + " (new object)");
        System.out.println("Half of deposit1: " + halfAmount + " (new object)");
        System.out.println("Doubled withdrawal: " + doubledAmount + " (new object)");
        
        // Demonstrate comparison operations
        System.out.println("\nComparison operations:");
        System.out.println("deposit1 > withdrawalAmount: " + deposit1.isGreaterThan(withdrawalAmount));
        System.out.println("withdrawalAmount < deposit1: " + withdrawalAmount.isLessThan(deposit1));
        System.out.println("deposit1 == deposit1: " + deposit1.isEqualTo(deposit1));
        
        // ═══════════════════════════════════════════════════════════════
        // BANK ACCOUNT PROCESSOR WITH FINAL TEMPLATE METHOD
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🏦 CREATING BANK ACCOUNT PROCESSOR...\n");
        
        final BankAccountProcessor processor = new BankAccountProcessor(PROCESSOR_ID, DEMO_BANK);
        
        // Initialize accounts with final method
        processor.initializeAccount("ACC_001", new Money(500.0, "USD"));
        processor.initializeAccount("ACC_002", new Money(1200.0, "USD"));
        processor.initializeAccount("ACC_003", new Money(0.0, "USD"));
        
        // ═══════════════════════════════════════════════════════════════
        // CREATING IMMUTABLE TRANSACTIONS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n📝 CREATING IMMUTABLE TRANSACTIONS...\n");
        
        // Each transaction is a final, immutable object
        final Transaction tx1 = new Transaction("TX_001", Transaction.TYPE_DEPOSIT, 
                                               deposit1, "Initial deposit", null, "ACC_001");
        
        final Transaction tx2 = new Transaction("TX_002", Transaction.TYPE_DEPOSIT, 
                                               deposit2, "Second deposit", null, "ACC_002");
        
        final Transaction tx3 = new Transaction("TX_003", Transaction.TYPE_WITHDRAWAL, 
                                               withdrawalAmount, "ATM withdrawal", "ACC_001", null);
        
        final Transaction tx4 = new Transaction("TX_004", Transaction.TYPE_TRANSFER, 
                                               transferAmount, "Transfer between accounts", 
                                               "ACC_002", "ACC_003");
        
        final Transaction tx5 = new Transaction("TX_005", Transaction.TYPE_FEE, 
                                               feeAmount, "Monthly maintenance fee", "ACC_001", null);
        
        // ═══════════════════════════════════════════════════════════════
        // PROCESSING TRANSACTIONS WITH FINAL TEMPLATE METHOD
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n⚙️ PROCESSING TRANSACTIONS...\n");
        
        // The final template method ensures consistent processing
        processor.processTransaction(tx1);
        processor.processTransaction(tx2);
        processor.processTransaction(tx3);
        processor.processTransaction(tx4);
        processor.processTransaction(tx5);
        
        // ═══════════════════════════════════════════════════════════════
        // DISPLAYING RESULTS WITH FINAL METHODS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n📊 FINAL RESULTS...\n");
        
        processor.displayAccountBalances();
        processor.displayTransactionHistory();
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATING FINAL VARIABLE BEHAVIOR IN LOOPS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🔄 FINAL VARIABLES IN OPERATIONS...\n");
        
        // Final collection with mutable contents
        final List<Money> amounts = Arrays.asList(
            new Money(100, "USD"),
            new Money(250, "USD"),
            new Money(75, "USD")
        );
        
        // Calculate total using final variables
        Money runningTotal = new Money(0, "USD");
        for (final Money amount : amounts) {  // Final loop variable
            runningTotal = runningTotal.add(amount);  // Immutable addition
            System.out.println("Added " + amount + ", running total: " + runningTotal);
        }
        
        final Money finalTotal = runningTotal;
        System.out.println("Final total: " + finalTotal);
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATING THREAD SAFETY OF FINAL IMMUTABLE OBJECTS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🧵 THREAD SAFETY DEMONSTRATION...\n");
        
        final Money sharedMoney = new Money(1000, "USD");
        System.out.println("Created shared immutable money object: " + sharedMoney);
        System.out.println("This object is thread-safe because:");
        System.out.println("1. All fields are final (immutable references)");
        System.out.println("2. No setter methods exist");
        System.out.println("3. All operations return new objects");
        System.out.println("4. Class is final (cannot be extended)");
        
        // ═══════════════════════════════════════════════════════════════
        // FINAL SUMMARY
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n✨ IMMUTABLE BANKING SYSTEM COMPLETE! ✨");
        System.out.println("\n🎯 THE POWER OF `final` DEMONSTRATED:");
        System.out.println("🔹 Final classes created truly immutable value objects");
        System.out.println("🔹 Final methods ensured security-critical operations couldn't be overridden");
        System.out.println("🔹 Final variables provided guaranteed immutable state");
        System.out.println("🔹 Final template methods enforced consistent business processes");
        System.out.println("🔹 Final collections provided controlled mutability");
        System.out.println("🔹 Immutable objects achieved automatic thread safety");
        System.out.println("\n💡 The `final` keyword transformed mutable chaos into immutable order!");
    }
}
