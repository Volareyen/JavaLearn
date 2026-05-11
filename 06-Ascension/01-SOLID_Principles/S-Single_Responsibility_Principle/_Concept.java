/**
 * _Concept.java — SRP: Single Responsibility Principle
 * 
 * Compile & Run: javac _Concept.java && java _Concept
 */
public class _Concept {

    // ══════════════════════════════════════
    // ❌ VIOLATION — One class doing too much
    // ══════════════════════════════════════
    static class BadUserManager {
        private String name, email;

        // Responsibility 1: Data validation
        public boolean validateEmail(String email) {
            return email != null && email.contains("@");
        }

        // Responsibility 2: Persistence
        public void saveToDatabase() {
            System.out.println("  [BAD] Saving to DB from UserManager");
        }

        // Responsibility 3: Notification
        public void sendWelcomeEmail() {
            System.out.println("  [BAD] Sending email from UserManager");
        }

        // Responsibility 4: Formatting
        public String toJson() {
            return "  [BAD] {\"name\":\"" + name + "\"}";
        }
    }

    // ══════════════════════════════════════
    // ✅ CORRECT — Each class, one responsibility
    // ══════════════════════════════════════
    static class User {
        private String name, email;
        public User(String name, String email) { this.name = name; this.email = email; }
        public String getName() { return name; }
        public String getEmail() { return email; }
    }

    static class UserValidator {
        public boolean isValidEmail(String email) {
            return email != null && email.contains("@");
        }
        public boolean isValidName(String name) {
            return name != null && !name.trim().isEmpty();
        }
    }

    static class UserRepository {
        public void save(User user) {
            System.out.println("  [GOOD] Saved " + user.getName() + " to database");
        }
    }

    static class EmailService {
        public void sendWelcome(User user) {
            System.out.println("  [GOOD] Sent welcome email to " + user.getEmail());
        }
    }

    static class UserSerializer {
        public String toJson(User user) {
            return String.format("{\"name\":\"%s\",\"email\":\"%s\"}", user.getName(), user.getEmail());
        }
    }

    // ── Coordinator (orchestrates the single-responsibility classes) ──
    static class UserRegistrationService {
        private UserValidator validator = new UserValidator();
        private UserRepository repo = new UserRepository();
        private EmailService emailService = new EmailService();

        public boolean register(String name, String email) {
            if (!validator.isValidName(name) || !validator.isValidEmail(email)) {
                System.out.println("  Validation failed for " + name);
                return false;
            }
            User user = new User(name, email);
            repo.save(user);
            emailService.sendWelcome(user);
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== SINGLE RESPONSIBILITY PRINCIPLE ===\n");

        System.out.println("── Bad (violation) ──");
        BadUserManager bad = new BadUserManager();
        bad.saveToDatabase();
        bad.sendWelcomeEmail();
        System.out.println(bad.toJson());

        System.out.println("\n── Good (SRP applied) ──");
        UserRegistrationService service = new UserRegistrationService();
        service.register("Alice", "alice@example.com");
        service.register("", "invalid");

        UserSerializer serializer = new UserSerializer();
        System.out.println("  JSON: " + serializer.toJson(new User("Bob", "bob@example.com")));

        System.out.println("\n=== Each class has ONE reason to change ===");
    }
}
