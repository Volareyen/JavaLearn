/**
 * PaymentSystem.java — LSP Solution
 *
 * Every PaymentProcessor subtype substitutes safely.
 * RefundablePayment only for processors that support refunds.
 * Run: javac PaymentSystem.java && java PaymentSystem
 */
import java.util.*;

public class PaymentSystem {

    interface PaymentProcessor {
        boolean process(double amount, String currency);
        String name();
    }

    interface RefundablePayment {
        boolean refund(double amount, String transactionId);
    }

    static class CreditCardPayment implements PaymentProcessor, RefundablePayment {
        public boolean process(double amount, String currency) {
            System.out.printf("  💳 CreditCard: charged $%.2f %s%n", amount, currency);
            return true;
        }
        public String name() { return "CreditCard"; }
        public boolean refund(double amount, String txId) {
            System.out.printf("  💳 CreditCard: refunded $%.2f (tx: %s)%n", amount, txId);
            return true;
        }
    }

    static class PayPalPayment implements PaymentProcessor, RefundablePayment {
        public boolean process(double amount, String currency) {
            System.out.printf("  🅿 PayPal: charged $%.2f %s%n", amount, currency);
            return true;
        }
        public String name() { return "PayPal"; }
        public boolean refund(double amount, String txId) {
            System.out.printf("  🅿 PayPal: refunded $%.2f (tx: %s)%n", amount, txId);
            return true;
        }
    }

    // Crypto does NOT support refunds — so it does NOT implement RefundablePayment
    static class CryptoPayment implements PaymentProcessor {
        public boolean process(double amount, String currency) {
            System.out.printf("  ₿ Crypto: sent %.4f BTC (≈$%.2f)%n", amount / 50000, amount);
            return true;
        }
        public String name() { return "Crypto"; }
    }

    // Checkout works with ANY processor — LSP guaranteed
    static void checkout(PaymentProcessor processor, double amount) {
        System.out.println("  [Checkout via " + processor.name() + "]");
        boolean ok = processor.process(amount, "USD");
        System.out.println("  Result: " + (ok ? "✅ SUCCESS" : "❌ FAILED"));
    }

    static void processRefund(RefundablePayment processor, double amount, String txId) {
        System.out.println("  [Refund via " + ((PaymentProcessor)processor).name() + "]");
        processor.refund(amount, txId);
    }

    public static void main(String[] args) {
        System.out.println("=== LSP SOLUTION: PAYMENT SYSTEM ===\n");

        List<PaymentProcessor> processors = List.of(
            new CreditCardPayment(), new PayPalPayment(), new CryptoPayment());

        System.out.println("── Checkout (all processors substitutable) ──");
        processors.forEach(p -> checkout(p, 99.99));

        System.out.println("\n── Refunds (only refundable processors) ──");
        processors.stream()
            .filter(p -> p instanceof RefundablePayment)
            .map(p -> (RefundablePayment) p)
            .forEach(p -> processRefund(p, 99.99, "TX-" + p.hashCode()));

        System.out.println("\nLSP ✅ — every subtype honored its contract, Crypto excluded from refunds cleanly");
    }
}
