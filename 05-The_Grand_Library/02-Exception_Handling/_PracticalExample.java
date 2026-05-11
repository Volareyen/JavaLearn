/**
 * _PracticalExample.java — The Living Manuscript: Secure Banking System
 * 
 * A banking system that uses custom exceptions for robust error handling.
 * 
 * Compile & Run:  javac _PracticalExample.java && java _PracticalExample
 */
import java.util.*;

public class _PracticalExample {

    // ── Custom Exceptions ──
    static class BankException extends Exception {
        public BankException(String msg) { super(msg); }
    }
    static class AccountNotFoundException extends BankException {
        public AccountNotFoundException(String id) { super("Account not found: " + id); }
    }
    static class InsufficientFundsException extends BankException {
        private double deficit;
        public InsufficientFundsException(double amt, double bal) {
            super(String.format("Cannot withdraw $%.2f — balance is $%.2f", amt, bal));
            this.deficit = amt - bal;
        }
        public double getDeficit() { return deficit; }
    }
    static class InvalidAmountException extends BankException {
        public InvalidAmountException(double amt) {
            super(String.format("Invalid amount: $%.2f (must be positive)", amt));
        }
    }
    static class AccountFrozenException extends BankException {
        public AccountFrozenException(String id) { super("Account " + id + " is frozen"); }
    }

    // ── Bank Account ──
    static class BankAccount {
        private String id, owner;
        private double balance;
        private boolean frozen;
        private List<String> history = new ArrayList<>();

        BankAccount(String id, String owner, double initialBalance) {
            this.id = id; this.owner = owner; this.balance = initialBalance;
            log("Account opened with $%.2f", initialBalance);
        }

        public void deposit(double amount) throws BankException {
            validateActive();
            if (amount <= 0) throw new InvalidAmountException(amount);
            balance += amount;
            log("Deposited $%.2f → balance $%.2f", amount, balance);
        }

        public void withdraw(double amount) throws BankException {
            validateActive();
            if (amount <= 0) throw new InvalidAmountException(amount);
            if (amount > balance) throw new InsufficientFundsException(amount, balance);
            balance -= amount;
            log("Withdrew $%.2f → balance $%.2f", amount, balance);
        }

        public void transfer(BankAccount target, double amount) throws BankException {
            try {
                this.withdraw(amount);
                try {
                    target.deposit(amount);
                } catch (BankException e) {
                    // Rollback: refund the withdrawal
                    this.balance += amount;
                    log("Transfer rolled back — target error: %s", e.getMessage());
                    throw new BankException("Transfer failed: " + e.getMessage());
                }
                log("Transferred $%.2f to %s", amount, target.id);
            } catch (InsufficientFundsException e) {
                log("Transfer failed — insufficient funds");
                throw e;
            }
        }

        public void freeze() { frozen = true; log("Account FROZEN"); }
        public void unfreeze() { frozen = false; log("Account UNFROZEN"); }

        private void validateActive() throws AccountFrozenException {
            if (frozen) throw new AccountFrozenException(id);
        }

        private void log(String fmt, Object... args) {
            history.add(String.format(fmt, args));
        }

        public String getId() { return id; }
        public double getBalance() { return balance; }
        public void printHistory() {
            System.out.printf("  📋 %s (%s) — $%.2f%n", id, owner, balance);
            history.forEach(h -> System.out.println("     → " + h));
        }
    }

    // ── Bank ──
    static class Bank {
        private Map<String, BankAccount> accounts = new LinkedHashMap<>();

        public BankAccount openAccount(String id, String owner, double initial) {
            BankAccount acc = new BankAccount(id, owner, initial);
            accounts.put(id, acc);
            return acc;
        }

        public BankAccount getAccount(String id) throws AccountNotFoundException {
            BankAccount acc = accounts.get(id);
            if (acc == null) throw new AccountNotFoundException(id);
            return acc;
        }

        public void processTransaction(String fromId, String toId, double amount) {
            System.out.printf("%n── Transfer $%.2f: %s → %s ──%n", amount, fromId, toId);
            try {
                BankAccount from = getAccount(fromId);
                BankAccount to = getAccount(toId);
                from.transfer(to, amount);
                System.out.println("  ✅ Transfer successful");
            } catch (AccountNotFoundException e) {
                System.out.println("  ❌ " + e.getMessage());
            } catch (InsufficientFundsException e) {
                System.out.printf("  ❌ %s (deficit: $%.2f)%n", e.getMessage(), e.getDeficit());
            } catch (AccountFrozenException e) {
                System.out.println("  ❌ " + e.getMessage());
            } catch (BankException e) {
                System.out.println("  ❌ Transaction failed: " + e.getMessage());
            }
        }
    }

    // ── Demo ──
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   🏦 SECURE BANKING SYSTEM              ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        Bank bank = new Bank();
        bank.openAccount("ACC001", "Alice", 1000.00);
        bank.openAccount("ACC002", "Bob", 500.00);
        bank.openAccount("ACC003", "Charlie", 250.00);

        // Successful transfer
        bank.processTransaction("ACC001", "ACC002", 200.00);

        // Insufficient funds
        bank.processTransaction("ACC003", "ACC001", 999.00);

        // Account not found
        bank.processTransaction("ACC001", "ACC999", 50.00);

        // Frozen account
        try {
            bank.getAccount("ACC003").freeze();
            bank.processTransaction("ACC003", "ACC001", 100.00);
        } catch (Exception e) { /* handled inside */ }

        // Invalid amount
        System.out.println("\n── Invalid deposit ──");
        try {
            bank.getAccount("ACC001").deposit(-50);
        } catch (BankException e) {
            System.out.println("  ❌ " + e.getMessage());
        }

        // Print all histories
        System.out.println("\n── Account Histories ──");
        try {
            bank.getAccount("ACC001").printHistory();
            bank.getAccount("ACC002").printHistory();
            bank.getAccount("ACC003").printHistory();
        } catch (BankException e) { /* won't happen */ }

        System.out.println("\n✅ Banking system demo complete!");
    }
}
