/**
 * _Concept.java — Singleton Pattern: Three Implementations
 * 
 * Compile & Run: javac _Concept.java && java _Concept
 */
public class _Concept {

    // 1. Lazy Singleton (thread-safe with double-check)
    static class LazyConfig {
        private static volatile LazyConfig instance;
        private String setting = "default";

        private LazyConfig() { System.out.println("  LazyConfig created"); }

        public static LazyConfig getInstance() {
            if (instance == null) {
                synchronized (LazyConfig.class) {
                    if (instance == null) instance = new LazyConfig();
                }
            }
            return instance;
        }
        public String getSetting() { return setting; }
        public void setSetting(String s) { this.setting = s; }
    }

    // 2. Eager Singleton (simplest, thread-safe by JVM class loading)
    static class EagerLogger {
        private static final EagerLogger INSTANCE = new EagerLogger();
        private EagerLogger() { System.out.println("  EagerLogger created"); }
        public static EagerLogger getInstance() { return INSTANCE; }
        public void log(String msg) { System.out.println("  LOG: " + msg); }
    }

    // 3. Enum Singleton (best practice — safe against serialization & reflection)
    enum AppRegistry {
        INSTANCE;
        private int counter = 0;
        public int nextId() { return ++counter; }
    }

    public static void main(String[] args) {
        System.out.println("=== SINGLETON PATTERN ===\n");

        System.out.println("1. Lazy Singleton:");
        LazyConfig c1 = LazyConfig.getInstance();
        LazyConfig c2 = LazyConfig.getInstance();
        System.out.println("  Same instance? " + (c1 == c2));
        c1.setSetting("custom");
        System.out.println("  c2 sees change: " + c2.getSetting());

        System.out.println("\n2. Eager Singleton:");
        EagerLogger.getInstance().log("First message");
        EagerLogger.getInstance().log("Second message");

        System.out.println("\n3. Enum Singleton:");
        System.out.println("  ID: " + AppRegistry.INSTANCE.nextId());
        System.out.println("  ID: " + AppRegistry.INSTANCE.nextId());
        System.out.println("  ID: " + AppRegistry.INSTANCE.nextId());
    }
}
