/**
 * _Concept.java — Observer Pattern: Two Approaches
 *
 * Compile & Run: javac _Concept.java && java _Concept
 */
import java.util.*;
import java.util.function.Consumer;

public class _Concept {

    // ══════════════════════════════
    // APPROACH 1: Classic OOP Observer
    // ══════════════════════════════

    // Observer interface
    interface StockObserver {
        void onPriceChange(String ticker, double oldPrice, double newPrice);
    }

    // Subject (Observable)
    static class Stock {
        private String ticker;
        private double price;
        private List<StockObserver> observers = new ArrayList<>();

        Stock(String ticker, double initialPrice) {
            this.ticker = ticker;
            this.price = initialPrice;
        }

        public void subscribe(StockObserver observer) { observers.add(observer); }
        public void unsubscribe(StockObserver observer) { observers.remove(observer); }

        public void setPrice(double newPrice) {
            double old = this.price;
            this.price = newPrice;
            // Notify ALL observers
            for (StockObserver obs : observers) {
                obs.onPriceChange(ticker, old, newPrice);
            }
        }
    }

    // Concrete Observers
    static class AlertSystem implements StockObserver {
        private double threshold;
        AlertSystem(double threshold) { this.threshold = threshold; }

        public void onPriceChange(String ticker, double old, double now) {
            double change = Math.abs(now - old) / old * 100;
            if (change >= threshold) {
                System.out.printf("  🚨 ALERT: %s moved %.1f%% ($%.2f → $%.2f)%n",
                    ticker, change, old, now);
            }
        }
    }

    static class PortfolioTracker implements StockObserver {
        private int shares;
        PortfolioTracker(int shares) { this.shares = shares; }

        public void onPriceChange(String ticker, double old, double now) {
            double gain = (now - old) * shares;
            System.out.printf("  📊 Portfolio: %s ×%d = %+$.2f today%n", ticker, shares, gain);
        }
    }

    // ══════════════════════════════
    // APPROACH 2: Modern Generic EventBus with lambdas
    // ══════════════════════════════
    static class EventBus<T> {
        private List<Consumer<T>> listeners = new ArrayList<>();

        public void subscribe(Consumer<T> listener) { listeners.add(listener); }
        public void publish(T event) { listeners.forEach(l -> l.accept(event)); }
    }

    public static void main(String[] args) {
        System.out.println("=== OBSERVER PATTERN ===\n");

        // ── Classic approach ──
        System.out.println("── Classic OOP Observer ──");
        Stock apple = new Stock("AAPL", 150.00);

        StockObserver alert = new AlertSystem(5.0);
        StockObserver portfolio = new PortfolioTracker(100);

        apple.subscribe(alert);
        apple.subscribe(portfolio);

        apple.setPrice(155.00); // 3.3% — no alert
        apple.setPrice(162.00); // 4.5% — no alert
        apple.setPrice(172.00); // 6.2% — triggers alert!

        // Unsubscribe alert
        System.out.println("\n  (Alert unsubscribed)");
        apple.unsubscribe(alert);
        apple.setPrice(165.00); // only portfolio notified

        // ── Modern lambda approach ──
        System.out.println("\n── Modern EventBus with Lambdas ──");
        EventBus<String> bus = new EventBus<>();

        bus.subscribe(msg -> System.out.println("  📧 Email: " + msg));
        bus.subscribe(msg -> System.out.println("  📱 SMS: " + msg));
        bus.subscribe(msg -> System.out.println("  🔔 Push: " + msg));

        bus.publish("Server is down!");
        System.out.println("  (All 3 handlers notified from one publish call)");
    }
}
