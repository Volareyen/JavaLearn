/**
 * ConnectionPool.java — Singleton Solution
 *
 * A thread-safe Singleton managing a fixed pool of database connections.
 * Demonstrates getConnection(), releaseConnection(), getAvailableCount().
 * Run: javac ConnectionPool.java && java ConnectionPool
 */
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    // ── Simulated connection ──
    static class Connection {
        private final int id;
        private boolean inUse;
        Connection(int id) { this.id = id; this.inUse = false; }
        void open()  { inUse = true;  System.out.println("    [Connection #" + id + " opened]"); }
        void close() { inUse = false; System.out.println("    [Connection #" + id + " returned to pool]"); }
        @Override public String toString() { return "Connection#" + id; }
    }

    // ── The Singleton Pool ──
    private static volatile ConnectionPool instance;

    private final int maxSize;
    private final BlockingQueue<Connection> available;
    private final List<Connection> all;

    private ConnectionPool(int maxSize) {
        this.maxSize = maxSize;
        this.all = new ArrayList<>();
        this.available = new ArrayBlockingQueue<>(maxSize);
        for (int i = 1; i <= maxSize; i++) {
            Connection c = new Connection(i);
            all.add(c);
            available.offer(c);
        }
        System.out.println("  ⚡ ConnectionPool created with " + maxSize + " connections");
    }

    public static ConnectionPool getInstance(int maxSize) {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) instance = new ConnectionPool(maxSize);
            }
        }
        return instance;
    }

    public static ConnectionPool getInstance() {
        if (instance == null) throw new IllegalStateException("Pool not initialized");
        return instance;
    }

    public Connection getConnection() {
        Connection c = available.poll();
        if (c == null) throw new RuntimeException("No connections available! Pool exhausted.");
        c.open();
        return c;
    }

    public void releaseConnection(Connection c) {
        c.close();
        available.offer(c);
    }

    public int getAvailableCount() { return available.size(); }
    public int getTotalCount()     { return maxSize; }
    public int getInUseCount()     { return maxSize - available.size(); }

    public void printStatus() {
        System.out.printf("  Pool status: %d total | %d in use | %d available%n",
            getTotalCount(), getInUseCount(), getAvailableCount());
    }

    public static void main(String[] args) {
        System.out.println("=== SINGLETON SOLUTION: CONNECTION POOL ===\n");

        // Initialize pool
        ConnectionPool pool = ConnectionPool.getInstance(3);
        pool.printStatus();

        System.out.println("\n── Borrowing connections ──");
        Connection c1 = pool.getConnection();
        pool.printStatus();
        Connection c2 = pool.getConnection();
        pool.printStatus();
        Connection c3 = pool.getConnection();
        pool.printStatus();

        System.out.println("\n── Releasing one ──");
        pool.releaseConnection(c2);
        pool.printStatus();

        System.out.println("\n── Getting released connection again ──");
        Connection c4 = pool.getConnection();
        pool.printStatus();

        System.out.println("\n── Pool exhausted test ──");
        try {
            pool.getConnection(); // Should throw
        } catch (RuntimeException e) {
            System.out.println("  ✅ Caught expected: " + e.getMessage());
        }

        System.out.println("\n── Verify singleton identity ──");
        ConnectionPool ref1 = ConnectionPool.getInstance();
        ConnectionPool ref2 = ConnectionPool.getInstance();
        System.out.println("  Same instance: " + (ref1 == ref2));

        System.out.println("\n── Cleanup ──");
        pool.releaseConnection(c1);
        pool.releaseConnection(c3);
        pool.releaseConnection(c4);
        pool.printStatus();
    }
}
