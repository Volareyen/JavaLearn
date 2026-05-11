# The Pupil's Trial: Exception Handling — The Online Store Validator

*"Build an order processing system where every failure is handled gracefully with custom exceptions and proper recovery."*

---

## **Your Mission: Build an Order Processing System**

### **Custom Exception Hierarchy**
Create these custom exceptions extending from a base `OrderException`:
1. `ProductNotFoundException` — product SKU doesn't exist
2. `OutOfStockException` — not enough stock (include requested vs available)
3. `InvalidQuantityException` — negative or zero quantity
4. `PaymentDeclinedException` — payment fails (include reason)
5. `OrderLimitExceededException` — order exceeds max items per order

### **OrderProcessor Class**
- A product catalog (`Map<String, Product>` with stock tracking)
- Methods:
  1. `validateOrder(Order order)` — throws appropriate exceptions
  2. `processOrder(Order order)` — validate → charge → update stock → confirm
  3. `processOrderSafe(Order order)` — wraps processOrder, never throws, returns `OrderResult` (success/failure with message)
  4. `processBatch(List<Order> orders)` — process multiple, collect results, continue on failure

### **Demo**
1. Process a valid order successfully
2. Try to order a nonexistent product
3. Try to order more than available stock
4. Try with an invalid quantity
5. Process a batch of 5 orders (mix of valid and invalid)
6. Print a summary: X succeeded, Y failed, with failure reasons

### **Bonus** ⭐
- Add a retry mechanism: if `PaymentDeclinedException`, retry up to 3 times
- Add a `finally` block that always logs the attempt

---

*"Every transaction that fails silently is a customer lost. Every crash is trust destroyed. Show me that your system handles failure with the grace of a master."*
