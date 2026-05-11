/**
 * _Concept.java — DIP: Dependency Inversion Principle
 * 
 * Compile & Run: javac _Concept.java && java _Concept
 */
import java.util.*;

public class _Concept {

    // ── Abstractions (owned by high-level module) ──
    interface MessageSender { void send(String to, String message); }
    interface Logger { void log(String message); }

    // ── Low-level implementations ──
    static class EmailSender implements MessageSender {
        public void send(String to, String msg) {
            System.out.println("  📧 Email to " + to + ": " + msg);
        }
    }
    static class SMSSender implements MessageSender {
        public void send(String to, String msg) {
            System.out.println("  📱 SMS to " + to + ": " + msg);
        }
    }
    static class ConsoleLogger implements Logger {
        public void log(String msg) { System.out.println("  📝 LOG: " + msg); }
    }

    // ── High-level module depends on ABSTRACTIONS ──
    static class NotificationService {
        private MessageSender sender;  // Interface, not concrete!
        private Logger logger;         // Interface, not concrete!

        // Dependencies INJECTED through constructor
        NotificationService(MessageSender sender, Logger logger) {
            this.sender = sender;
            this.logger = logger;
        }

        public void notifyUser(String user, String message) {
            sender.send(user, message);
            logger.log("Notified " + user);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== DEPENDENCY INVERSION PRINCIPLE ===\n");

        Logger logger = new ConsoleLogger();

        // Same high-level code, different low-level implementations
        System.out.println("With Email:");
        NotificationService emailService = new NotificationService(new EmailSender(), logger);
        emailService.notifyUser("alice@mail.com", "Your order shipped!");

        System.out.println("\nWith SMS:");
        NotificationService smsService = new NotificationService(new SMSSender(), logger);
        smsService.notifyUser("+1234567890", "Your order shipped!");

        System.out.println("\nSame business logic, swappable implementations!");
    }
}
