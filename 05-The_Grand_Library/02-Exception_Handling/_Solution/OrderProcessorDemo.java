/**
 * OrderProcessorDemo.java — Complete Order Processing Solution
 * 
 * Compile & Run: javac OrderProcessorDemo.java && java OrderProcessorDemo
 */
import java.util.*;
import java.util.stream.Collectors;

public class OrderProcessorDemo {

    // ── Exception Hierarchy ──
    static class OrderException extends Exception {
        public OrderException(String msg) { super(msg); }
    }
    static class ProductNotFoundException extends OrderException {
        public ProductNotFoundException(String sku) { super("Product not found: " + sku); }
    }
    static class OutOfStockException extends OrderException {
        public OutOfStockException(String sku, int req, int avail) {
            super(String.format("%s: requested %d, available %d", sku, req, avail));
        }
    }
    static class InvalidQuantityException extends OrderException {
        public InvalidQuantityException(int qty) { super("Invalid quantity: " + qty); }
    }
    static class PaymentDeclinedException extends OrderException {
        public PaymentDeclinedException(String reason) { super("Payment declined: " + reason); }
    }
    static class OrderLimitExceededException extends OrderException {
        public OrderLimitExceededException(int count, int max) {
            super(String.format("Order has %d items, max is %d", count, max));
        }
    }

    // ── Data Classes ──
    static class Product {
        String sku, name; double price; int stock;
        Product(String sku, String name, double price, int stock) {
            this.sku = sku; this.name = name; this.price = price; this.stock = stock;
        }
    }

    static class OrderItem {
        String sku; int quantity;
        OrderItem(String sku, int quantity) { this.sku = sku; this.quantity = quantity; }
    }

    static class Order {
        String orderId; List<OrderItem> items; String paymentMethod;
        Order(String orderId, String paymentMethod, OrderItem... items) {
            this.orderId = orderId; this.paymentMethod = paymentMethod;
            this.items = Arrays.asList(items);
        }
    }

    static class OrderResult {
        String orderId; boolean success; String message; double total;
        OrderResult(String orderId, boolean success, String message, double total) {
            this.orderId = orderId; this.success = success;
            this.message = message; this.total = total;
        }
    }

    // ── Order Processor ──
    static class OrderProcessor {
        private Map<String, Product> catalog = new LinkedHashMap<>();
        private int maxItemsPerOrder = 10;
        private Random rng = new Random(42);

        public void addProduct(Product p) { catalog.put(p.sku, p); }

        public void validateOrder(Order order) throws OrderException {
            if (order.items.size() > maxItemsPerOrder) {
                throw new OrderLimitExceededException(order.items.size(), maxItemsPerOrder);
            }
            for (OrderItem item : order.items) {
                if (item.quantity <= 0) throw new InvalidQuantityException(item.quantity);
                Product p = catalog.get(item.sku);
                if (p == null) throw new ProductNotFoundException(item.sku);
                if (p.stock < item.quantity) throw new OutOfStockException(item.sku, item.quantity, p.stock);
            }
        }

        private void chargePayment(Order order, double total) throws PaymentDeclinedException {
            if (order.paymentMethod.equals("DECLINED")) {
                throw new PaymentDeclinedException("Card rejected by issuer");
            }
        }

        public double processOrder(Order order) throws OrderException {
            System.out.printf("  📦 Processing order %s...%n", order.orderId);
            validateOrder(order);

            double total = 0;
            for (OrderItem item : order.items) {
                Product p = catalog.get(item.sku);
                total += p.price * item.quantity;
            }

            // Payment with retry
            int retries = 3;
            for (int attempt = 1; attempt <= retries; attempt++) {
                try {
                    chargePayment(order, total);
                    break;
                } catch (PaymentDeclinedException e) {
                    if (attempt == retries) throw e;
                    System.out.printf("  ⚠ Payment attempt %d failed, retrying...%n", attempt);
                }
            }

            // Deduct stock
            for (OrderItem item : order.items) {
                catalog.get(item.sku).stock -= item.quantity;
            }

            return total;
        }

        public OrderResult processOrderSafe(Order order) {
            try {
                double total = processOrder(order);
                return new OrderResult(order.orderId, true,
                    String.format("Completed — $%.2f", total), total);
            } catch (OrderException e) {
                return new OrderResult(order.orderId, false, e.getMessage(), 0);
            } finally {
                System.out.printf("  📝 Logged attempt for order %s%n", order.orderId);
            }
        }

        public List<OrderResult> processBatch(List<Order> orders) {
            List<OrderResult> results = new ArrayList<>();
            for (Order order : orders) {
                results.add(processOrderSafe(order));
            }
            return results;
        }
    }

    // ── Demo ──
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   🛒 ORDER PROCESSING SYSTEM            ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        OrderProcessor proc = new OrderProcessor();
        proc.addProduct(new Product("LAP01", "Gaming Laptop", 1299.99, 5));
        proc.addProduct(new Product("PHN01", "Smartphone", 899.99, 10));
        proc.addProduct(new Product("HDP01", "Headphones", 149.99, 20));
        proc.addProduct(new Product("KBD01", "Keyboard", 79.99, 15));

        // Create batch of orders
        List<Order> batch = List.of(
            new Order("ORD-001", "VISA", new OrderItem("LAP01", 1), new OrderItem("HDP01", 2)),
            new Order("ORD-002", "VISA", new OrderItem("GHOST", 1)),         // product not found
            new Order("ORD-003", "VISA", new OrderItem("LAP01", 99)),        // out of stock
            new Order("ORD-004", "VISA", new OrderItem("PHN01", -1)),        // invalid qty
            new Order("ORD-005", "VISA", new OrderItem("KBD01", 3))          // valid
        );

        System.out.println("── Processing Batch ──");
        List<OrderResult> results = proc.processBatch(batch);

        // Summary
        long success = results.stream().filter(r -> r.success).count();
        long failed = results.size() - success;
        double revenue = results.stream().mapToDouble(r -> r.total).sum();

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║      📊 BATCH SUMMARY                   ║");
        System.out.println("╠══════════════════════════════════════════╣");
        for (OrderResult r : results) {
            System.out.printf("║ %s %s: %s%n",
                r.success ? "✅" : "❌", r.orderId, r.message);
        }
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.printf("║ Succeeded: %d  |  Failed: %d%n", success, failed);
        System.out.printf("║ Total Revenue: $%,.2f%n", revenue);
        System.out.println("╚══════════════════════════════════════════╝");

        System.out.println("\n✅ Order processing demo complete!");
    }
}
