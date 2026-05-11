# D — The Dependency Inversion Principle

*"Depend on abstractions, not on concretions."*

---

## **The Essence**

High-level modules (business logic) should NOT depend on low-level modules (database, email, files). Both should depend on **abstractions** (interfaces). This inverts the traditional dependency direction.

## **The Violation**

```java
// BAD: Business logic directly depends on MySQL
class OrderService {
    private MySQLDatabase db = new MySQLDatabase(); // Tight coupling!
    public void createOrder(Order order) {
        db.insert("orders", order); // Can't switch to PostgreSQL without changing this class
    }
}
```

## **The Solution**

```java
// Abstraction
interface OrderRepository { void save(Order order); }

// Low-level: specific implementation
class MySQLOrderRepository implements OrderRepository {
    public void save(Order order) { /* MySQL-specific */ }
}
class MongoOrderRepository implements OrderRepository {
    public void save(Order order) { /* MongoDB-specific */ }
}

// High-level: depends on abstraction, not MySQL
class OrderService {
    private OrderRepository repo; // Interface, not concrete class!
    OrderService(OrderRepository repo) { this.repo = repo; } // Injected!
    public void createOrder(Order order) { repo.save(order); }
}
```

*"The abstraction belongs to the high-level module. The low-level module implements it."*
