/**
 * _PracticalExample.java — Singleton: Application Configuration & Logging
 *
 * A real-world Singleton pair: AppConfig (read-once settings) and
 * AppLogger (central log collector) — two resources that must be shared
 * and exist exactly once across the whole application.
 *
 * Compile & Run: javac _PracticalExample.java && java _PracticalExample
 */
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class _PracticalExample {

    // ── 1. Enum Singleton: AppConfig — immutable, thread-safe ──
    enum AppConfig {
        INSTANCE;

        private final Map<String, String> settings = new LinkedHashMap<>();

        AppConfig() {
            // Simulated config file load — happens exactly once
            settings.put("db.host", "localhost");
            settings.put("db.port", "5432");
            settings.put("db.name", "appdb");
            settings.put("app.version", "2.1.0");
            settings.put("app.env", "production");
            settings.put("cache.ttl", "3600");
        }

        public String get(String key) { return settings.getOrDefault(key, ""); }
        public Map<String, String> all() { return Collections.unmodifiableMap(settings); }
    }

    // ── 2. Lazy Double-Check Singleton: AppLogger ──
    static class AppLogger {
        private static volatile AppLogger instance;
        private final List<String> logs = new ArrayList<>();
        private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");

        private AppLogger() { System.out.println("  [AppLogger initialized — one instance only]"); }

        public static AppLogger getInstance() {
            if (instance == null) {
                synchronized (AppLogger.class) {
                    if (instance == null) instance = new AppLogger();
                }
            }
            return instance;
        }

        public void info(String msg)  { log("INFO ", msg); }
        public void warn(String msg)  { log("WARN ", msg); }
        public void error(String msg) { log("ERROR", msg); }

        private void log(String level, String msg) {
            String entry = String.format("[%s] %s — %s", LocalTime.now().format(fmt), level, msg);
            logs.add(entry);
            System.out.println("  " + entry);
        }

        public void printSummary() {
            System.out.println("\n  ── Log Summary (" + logs.size() + " entries) ──");
            long errors = logs.stream().filter(l -> l.contains("ERROR")).count();
            long warns  = logs.stream().filter(l -> l.contains("WARN")).count();
            System.out.println("  ERRORs: " + errors + " | WARNs: " + warns +
                               " | INFOs: " + (logs.size() - errors - warns));
        }
    }

    // ── Simulated application components — all share the SAME instances ──
    static class DatabaseService {
        void connect() {
            AppConfig cfg = AppConfig.INSTANCE;
            AppLogger log = AppLogger.getInstance();
            log.info("Connecting to " + cfg.get("db.host") + ":" + cfg.get("db.port") + "/" + cfg.get("db.name"));
        }
        void query(String sql) { AppLogger.getInstance().info("Query: " + sql); }
    }

    static class CacheService {
        void init() {
            int ttl = Integer.parseInt(AppConfig.INSTANCE.get("cache.ttl"));
            AppLogger.getInstance().info("Cache initialized with TTL=" + ttl + "s");
        }
        void get(String key) { AppLogger.getInstance().warn("Cache MISS for key: " + key); }
    }

    static class AuthService {
        boolean login(String user, String pass) {
            if (pass.length() < 8) {
                AppLogger.getInstance().error("Login failed for " + user + ": password too short");
                return false;
            }
            AppLogger.getInstance().info("Login successful for " + user);
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   ⚙ SINGLETON CONFIG & LOGGING       ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        System.out.println("── App startup ──");
        System.out.println("  App version: " + AppConfig.INSTANCE.get("app.version"));
        System.out.println("  Environment: " + AppConfig.INSTANCE.get("app.env"));

        System.out.println("\n── Services initializing ──");
        DatabaseService db = new DatabaseService();
        CacheService cache = new CacheService();
        AuthService auth = new AuthService();

        db.connect();
        cache.init();

        System.out.println("\n── Runtime operations ──");
        auth.login("admin", "short");    // fails
        auth.login("alice", "securepass123");
        cache.get("user:42");
        db.query("SELECT * FROM users WHERE active=true");

        // Verify same logger instance
        System.out.println("\n── Singleton identity check ──");
        AppLogger l1 = AppLogger.getInstance();
        AppLogger l2 = AppLogger.getInstance();
        System.out.println("  Same logger instance: " + (l1 == l2));

        AppLogger.getInstance().printSummary();
        System.out.println("\n✅ One config, one logger — shared across entire application!");
    }
}
