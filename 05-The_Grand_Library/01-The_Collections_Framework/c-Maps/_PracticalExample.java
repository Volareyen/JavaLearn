/**
 * _PracticalExample.java — The Living Manuscript: Inventory Tracker
 * 
 * A warehouse inventory system using Maps for product lookup,
 * category grouping, and stock analytics.
 * 
 * Compile & Run:  javac _PracticalExample.java && java _PracticalExample
 */
import java.util.*;
import java.util.stream.Collectors;

public class _PracticalExample {

    static class Product {
        String sku, name, category;
        double price;
        int stock;

        Product(String sku, String name, String category, double price, int stock) {
            this.sku = sku; this.name = name; this.category = category;
            this.price = price; this.stock = stock;
        }
        @Override
        public String toString() {
            return String.format("%s (%s) $%.2f [%d in stock]", name, sku, price, stock);
        }
    }

    static class Inventory {
        private Map<String, Product> products = new LinkedHashMap<>();       // SKU → Product
        private Map<String, Set<String>> categories = new TreeMap<>();       // Category → SKUs
        private Map<String, Integer> salesHistory = new HashMap<>();         // SKU → units sold

        public void addProduct(Product p) {
            products.put(p.sku, p);
            categories.computeIfAbsent(p.category, k -> new LinkedHashSet<>()).add(p.sku);
        }

        public Optional<Product> findBySku(String sku) {
            return Optional.ofNullable(products.get(sku));
        }

        public void recordSale(String sku, int qty) {
            Product p = products.get(sku);
            if (p != null && p.stock >= qty) {
                p.stock -= qty;
                salesHistory.merge(sku, qty, Integer::sum);
                System.out.printf("  ✅ Sold %d × %s%n", qty, p.name);
            } else {
                System.out.printf("  ⚠ Cannot sell %d of %s%n", qty, sku);
            }
        }

        public void restock(String sku, int qty) {
            Product p = products.get(sku);
            if (p != null) { p.stock += qty; System.out.printf("  📦 Restocked %s +%d%n", p.name, qty); }
        }

        public List<Product> getLowStock(int threshold) {
            return products.values().stream()
                .filter(p -> p.stock <= threshold)
                .collect(Collectors.toList());
        }

        public Map<String, Double> getCategoryValues() {
            Map<String, Double> values = new TreeMap<>();
            categories.forEach((cat, skus) -> {
                double total = skus.stream()
                    .map(products::get).filter(Objects::nonNull)
                    .mapToDouble(p -> p.price * p.stock).sum();
                values.put(cat, total);
            });
            return values;
        }

        public List<Map.Entry<String, Integer>> getTopSellers(int count) {
            return salesHistory.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(count)
                .collect(Collectors.toList());
        }

        public void printReport() {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║      📦 INVENTORY REPORT                ║");
            System.out.println("╠══════════════════════════════════════════╣");
            categories.forEach((cat, skus) -> {
                System.out.println("║ 📁 " + cat);
                skus.stream().map(products::get).filter(Objects::nonNull)
                    .forEach(p -> System.out.println("║   → " + p));
            });
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.printf("║ Total Products: %d%n", products.size());
            double totalValue = products.values().stream().mapToDouble(p -> p.price * p.stock).sum();
            System.out.printf("║ Total Value:    $%,.2f%n", totalValue);
            System.out.println("╚══════════════════════════════════════════╝");
        }
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   🏛️ INVENTORY TRACKER SYSTEM            ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        Inventory inv = new Inventory();
        inv.addProduct(new Product("LAP001", "Gaming Laptop", "Electronics", 1299.99, 15));
        inv.addProduct(new Product("PHN001", "Smartphone Pro", "Electronics", 899.99, 30));
        inv.addProduct(new Product("HDP001", "Wireless Headphones", "Electronics", 149.99, 50));
        inv.addProduct(new Product("DSK001", "Standing Desk", "Furniture", 499.99, 8));
        inv.addProduct(new Product("CHR001", "Ergonomic Chair", "Furniture", 349.99, 12));
        inv.addProduct(new Product("PEN001", "Fountain Pen Set", "Stationery", 29.99, 100));
        inv.addProduct(new Product("NTB001", "Leather Notebook", "Stationery", 19.99, 75));

        inv.printReport();

        // Record sales
        System.out.println("\n── Recording Sales ──");
        inv.recordSale("LAP001", 3);
        inv.recordSale("PHN001", 10);
        inv.recordSale("HDP001", 20);
        inv.recordSale("PEN001", 40);
        inv.recordSale("DSK001", 5);

        // Low stock alert
        System.out.println("\n── Low Stock (≤10 units) ──");
        inv.getLowStock(10).forEach(p -> System.out.println("  ⚠ " + p));

        // Restock
        System.out.println("\n── Restocking ──");
        inv.restock("DSK001", 20);

        // Top sellers
        System.out.println("\n── Top 3 Sellers ──");
        inv.getTopSellers(3).forEach(e ->
            System.out.printf("  🏆 %s — %d units sold%n",
                inv.findBySku(e.getKey()).map(p -> p.name).orElse("?"), e.getValue()));

        // Category values
        System.out.println("\n── Category Values ──");
        inv.getCategoryValues().forEach((cat, val) ->
            System.out.printf("  📁 %s: $%,.2f%n", cat, val));

        inv.printReport();
        System.out.println("\n✅ Inventory Tracker demo complete!");
    }
}
