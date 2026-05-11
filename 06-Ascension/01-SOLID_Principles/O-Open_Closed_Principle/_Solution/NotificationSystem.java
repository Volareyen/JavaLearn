/**
 * NotificationSystem.java — OCP Solution
 *
 * New channels added as new classes — the dispatcher never changes.
 * Run: javac NotificationSystem.java && java NotificationSystem
 */
import java.util.*;

public class NotificationSystem {

    interface Notifier {
        void send(String recipient, String message);
        String channel();
    }

    static class EmailNotifier implements Notifier {
        public void send(String to, String msg) {
            System.out.println("  📧 EMAIL → " + to + ": " + msg);
        }
        public String channel() { return "Email"; }
    }

    static class SMSNotifier implements Notifier {
        public void send(String to, String msg) {
            System.out.println("  📱 SMS → " + to + ": " + msg);
        }
        public String channel() { return "SMS"; }
    }

    // Added without touching anything above ↑
    static class SlackNotifier implements Notifier {
        public void send(String to, String msg) {
            System.out.println("  💬 SLACK → #" + to + ": " + msg);
        }
        public String channel() { return "Slack"; }
    }

    static class PushNotifier implements Notifier {
        public void send(String to, String msg) {
            System.out.println("  🔔 PUSH → " + to + ": " + msg);
        }
        public String channel() { return "Push"; }
    }

    // CLOSED for modification — never changes
    static class NotificationRouter {
        private List<Notifier> notifiers = new ArrayList<>();
        void register(Notifier n) { notifiers.add(n); }
        void broadcast(String recipient, String message) {
            notifiers.forEach(n -> n.send(recipient, message));
        }
        void sendVia(String channel, String recipient, String message) {
            notifiers.stream()
                .filter(n -> n.channel().equalsIgnoreCase(channel))
                .findFirst()
                .ifPresent(n -> n.send(recipient, message));
        }
    }

    public static void main(String[] args) {
        System.out.println("=== OCP SOLUTION: NOTIFICATION SYSTEM ===\n");
        NotificationRouter router = new NotificationRouter();
        router.register(new EmailNotifier());
        router.register(new SMSNotifier());
        router.register(new SlackNotifier());
        router.register(new PushNotifier());

        System.out.println("── Broadcast alert ──");
        router.broadcast("user@example.com", "Server is down!");

        System.out.println("\n── Single channel ──");
        router.sendVia("Slack", "engineering", "Deploy successful!");

        System.out.println("\nOCP ✅ — 4 channels added, router code never modified");
    }
}
