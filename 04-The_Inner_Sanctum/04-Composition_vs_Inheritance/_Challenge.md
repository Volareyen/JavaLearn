# The Pupil's Trial: Enterprise Task Management System
*Composition vs Inheritance Mastery Challenge*

---

## **The Sacred Task**

*Seeker, you have witnessed the profound architectural choice between inheritance ("is-a") and composition ("has-a"). Now you must forge your own system that demonstrates complete mastery over both approaches, knowing when to use each and why.*

*Your challenge is to create a comprehensive **Enterprise Task Management System** that showcases the wisdom of using inheritance for genuine hierarchical relationships and composition for flexible, collaborative behaviors.*

---

## **The Requirements**

### **🎯 System Architecture Overview**

Create a task management system for a large enterprise with the following components:

#### **1. Inheritance Hierarchy** (Stable "is-a" relationships)
**Task Hierarchy** - Different types of tasks with shared behavior:

**`Task`** (Abstract base class)
- Common properties: id, title, description, priority, status, createdDate, assignee
- Template method: `executeTask()` with common workflow
- Abstract methods: `validateRequirements()`, `performWork()`, `generateReport()`

**`DevelopmentTask`** extends Task
- Additional properties: technology stack, estimated hours, complexity
- Specific validation and work methods for development

**`ReviewTask`** extends Task  
- Additional properties: document to review, review type, deadline
- Specific validation and work methods for reviews

**`DeploymentTask`** extends Task
- Additional properties: environment, deployment type, rollback plan
- Specific validation and work methods for deployments

#### **2. Composition-Based System** (Flexible "has-a" relationships)
**Task Processor** - Uses composed strategies for flexible execution:

**Execution Strategies:**
- `ExecutionStrategy` interface with different implementations
- `SequentialExecution`, `ParallelExecution`, `PriorityBasedExecution`

**Notification Strategies:**
- `NotificationStrategy` interface with different implementations  
- `EmailNotification`, `SlackNotification`, `SMSNotification`

**Persistence Strategies:**
- `PersistenceStrategy` interface with different implementations
- `DatabasePersistence`, `FilePersistence`, `CloudPersistence`

**Task Manager** - Coordinates everything through composition:
- HAS-A ExecutionStrategy
- HAS-A NotificationStrategy  
- HAS-A PersistenceStrategy
- HAS-A TaskValidator
- HAS-A ProgressTracker

---

## **The Detailed Specifications**

### **📋 Task Hierarchy (Inheritance) Requirements**

```java
public abstract class Task {
    // Common properties
    protected final String taskId;
    protected String title;
    protected String description;
    protected Priority priority;
    protected TaskStatus status;
    protected LocalDateTime createdDate;
    protected String assignee;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    
    // Template method - final to prevent override
    public final TaskResult executeTask() {
        try {
            logTaskStart();
            validateRequirements();  // Abstract - subclass implements
            updateStatus(TaskStatus.IN_PROGRESS);
            
            TaskResult result = performWork();  // Abstract - subclass implements
            
            updateStatus(TaskStatus.COMPLETED);
            generateReport();  // Abstract - subclass implements
            logTaskCompletion();
            
            return result;
        } catch (Exception e) {
            updateStatus(TaskStatus.FAILED);
            return new TaskResult(false, e.getMessage());
        }
    }
    
    // Common concrete methods
    protected void logTaskStart() { /* logging */ }
    protected void updateStatus(TaskStatus status) { /* status update */ }
    protected void logTaskCompletion() { /* logging */ }
    
    // Abstract methods - subclasses must implement
    protected abstract void validateRequirements() throws ValidationException;
    protected abstract TaskResult performWork() throws ExecutionException;
    protected abstract void generateReport();
}
```

### **📋 Composition System Requirements**

```java
// Strategy interfaces for composition
interface ExecutionStrategy {
    void execute(List<Task> tasks);
    String getStrategyName();
}

interface NotificationStrategy {  
    void notify(String recipient, String message, NotificationType type);
    boolean isAvailable();
}

interface PersistenceStrategy {
    void save(Task task);
    Task load(String taskId);
    List<Task> findByStatus(TaskStatus status);
}

// Main coordinator using composition
class TaskManager {
    // Composed strategies - can change at runtime!
    private ExecutionStrategy executionStrategy;
    private NotificationStrategy notificationStrategy;
    private PersistenceStrategy persistenceStrategy;
    private TaskValidator validator;
    private ProgressTracker progressTracker;
    
    // Constructor injection
    public TaskManager(ExecutionStrategy execution, 
                      NotificationStrategy notification,
                      PersistenceStrategy persistence) { /* */ }
    
    // Runtime strategy changes
    public void setExecutionStrategy(ExecutionStrategy strategy) { /* */ }
    public void setNotificationStrategy(NotificationStrategy strategy) { /* */ }
    
    // Main operations using composed strategies
    public void processTasks(List<Task> tasks) { /* use strategies */ }
}
```

---

## **The Implementation Challenges**

### **🎮 Core Requirements**

1. **Proper Inheritance Usage**
   - Abstract Task class with template method pattern
   - Three concrete task types with specialized behavior
   - Polymorphic task processing
   - Proper Liskov Substitution Principle adherence

2. **Flexible Composition System**
   - Strategy pattern for execution, notification, and persistence
   - Runtime strategy switching capabilities
   - Dependency injection for easy testing
   - Multiple strategy combinations

3. **Decorator Pattern Integration**
   - Task decorators for adding features (logging, timing, retry logic)
   - Chain multiple decorators together
   - Non-intrusive feature enhancement

4. **Business Logic Implementation**
   - Task validation and execution workflows
   - Progress tracking and status management
   - Error handling and recovery
   - Comprehensive reporting

### **🏆 Advanced Challenges** ⭐

For those seeking deeper mastery:

1. **Observer Pattern Integration**
   - Task lifecycle observers for monitoring
   - Event-driven notifications
   - Loose coupling between components

2. **Factory Pattern for Task Creation**
   - TaskFactory using composition to create different types
   - Configuration-driven task creation
   - Plugin architecture for new task types

3. **Command Pattern for Task Operations**
   - Undoable task operations
   - Task operation queuing and batch processing
   - Audit trail for all task operations

4. **Bridge Pattern for Cross-Platform Support**
   - Separate task abstraction from platform implementation
   - Multiple platform backends (Windows, Linux, Cloud)
   - Runtime platform switching

5. **Performance Optimization**
   - Async task execution with CompletableFuture
   - Thread pool management
   - Memory-efficient task storage

---

## **The Testing Requirements**

Create comprehensive tests that demonstrate:

### **🧪 Inheritance Testing**
```java
@Test
public void testPolymorphicTaskExecution() {
    // Create different task types
    List<Task> tasks = Arrays.asList(
        new DevelopmentTask("DEV-001", "Implement feature", "Java"),
        new ReviewTask("REV-001", "Code review", "Pull Request #123"),
        new DeploymentTask("DEP-001", "Deploy to staging", "staging")
    );
    
    // Test polymorphic execution
    for (Task task : tasks) {
        TaskResult result = task.executeTask();  // Polymorphic call
        assertTrue(result.isSuccess());
    }
}

@Test
public void testTemplateMethodPattern() {
    // Verify template method calls all required steps
    // Test that abstract methods are called in correct order
    // Verify error handling in template method
}
```

### **🔧 Composition Testing**
```java
@Test
public void testStrategyPatternFlexibility() {
    TaskManager manager = new TaskManager(
        new SequentialExecution(),
        new EmailNotification(), 
        new DatabasePersistence()
    );
    
    // Test initial configuration
    manager.processTasks(tasks);
    
    // Test runtime strategy changes
    manager.setExecutionStrategy(new ParallelExecution());
    manager.setNotificationStrategy(new SlackNotification());
    
    // Verify behavior changed
    manager.processTasks(tasks);
}

@Test
public void testDecoratorPattern() {
    Task task = new LoggingTaskDecorator(
        new TimingTaskDecorator(
            new RetryTaskDecorator(
                new DevelopmentTask("DEV-001", "Test task", "Java"),
                3  // max retries
            )
        )
    );
    
    TaskResult result = task.executeTask();
    // Verify all decorators applied their behavior
}
```

### **📊 Integration Testing**
```java
@Test
public void testEndToEndWorkflow() {
    // Create complete system with all components
    // Test full task lifecycle
    // Verify all components work together
    // Test error scenarios and recovery
}
```

---

## **Sample Expected Behavior**

```
🏢 ENTERPRISE TASK MANAGEMENT SYSTEM 🏢

=== INITIALIZING SYSTEM ===
Created TaskManager with:
  📋 Execution: SequentialExecution
  📧 Notification: EmailNotification
  💾 Persistence: DatabasePersistence

=== CREATING TASKS (Inheritance Hierarchy) ===
✅ DevelopmentTask: Implement user authentication (Java, Spring)
✅ ReviewTask: Security audit review (Documentation)
✅ DeploymentTask: Production deployment (AWS, Blue-Green)

=== POLYMORPHIC TASK EXECUTION ===
🔄 Executing DevelopmentTask DEV-001:
   📋 Validating requirements...
   ⚙️ Performing development work...
   📊 Generating development report...
   ✅ Task completed successfully

🔄 Executing ReviewTask REV-001:
   📋 Validating requirements...
   📝 Performing review work...
   📊 Generating review report...
   ✅ Task completed successfully

=== RUNTIME STRATEGY CHANGES ===
🔄 Switching to ParallelExecution strategy
🔄 Switching to SlackNotification strategy

📦 Processing batch with new strategies:
   🚀 Tasks executing in parallel...
   💬 Notifications sent via Slack
   ✅ Batch completed in 2.3 seconds

=== DECORATOR PATTERN IN ACTION ===
🎨 Applying decorators to task:
   📝 LoggingTaskDecorator applied
   ⏱️ TimingTaskDecorator applied
   🔄 RetryTaskDecorator applied (max 3 attempts)

🔄 Executing decorated task:
   📝 [LOG] Task execution started
   ⏱️ [TIMER] Execution began at 10:30:15
   🔄 [RETRY] Attempt 1 of 3
   ✅ Task succeeded on first attempt
   ⏱️ [TIMER] Execution completed in 1.245s
   📝 [LOG] Task execution finished

=== SYSTEM STATISTICS ===
📊 Execution Summary:
   Total Tasks Processed: 15
   Success Rate: 93.3%
   Average Execution Time: 2.1s
   Strategy Switches: 3
   Decorator Applications: 8
```

---

## **The Evaluation Criteria**

Your solution will be judged on:

### **Correctness (35%)**
- Proper inheritance hierarchy with template method pattern
- Correct composition with strategy pattern implementation
- Working decorator pattern for feature enhancement
- Proper Liskov Substitution Principle adherence

### **Design (35%)**
- Appropriate choice between inheritance and composition
- Clean separation of concerns
- Flexible architecture supporting runtime changes
- Proper abstraction levels and interfaces

### **Functionality (20%)**
- Complete task lifecycle management
- Working strategy switching at runtime
- Error handling and recovery mechanisms
- Comprehensive logging and reporting

### **Code Quality (10%)**
- Clear, well-documented code
- Consistent naming and formatting
- Efficient algorithms and data structures
- Comprehensive test coverage

---

## **The Sacred Hints**

*These whispers from the architectural masters may guide your path:*

1. **Inheritance Guidelines**: Use inheritance when you have genuine "is-a" relationships that are stable and share common behavior through template methods.

2. **Composition Guidelines**: Use composition when you need runtime flexibility, multiple orthogonal capabilities, or easy testing through mocking.

3. **Template Method**: Put the common algorithm in the base class as a final method, with abstract steps for subclasses to implement.

4. **Strategy Pattern**: Define a family of algorithms, encapsulate each one, and make them interchangeable at runtime.

5. **Decorator Pattern**: Attach additional responsibilities to objects dynamically without subclassing.

6. **Dependency Injection**: Pass composed objects through constructors to enable easy testing and flexibility.

7. **Liskov Substitution**: Ensure subclasses can always substitute for their parent class without breaking client code.

8. **Single Responsibility**: Each class should have only one reason to change - composition helps achieve this.

---

## **The Moment of Truth**

*When you have completed your implementation, test it rigorously. Can you switch strategies at runtime? Do your task types work polymorphically? Can you add decorators without changing existing code? Does your system demonstrate both the power of inheritance hierarchies and the flexibility of composition?*

*Remember: The goal is not to choose one approach over the other, but to **demonstrate mastery** of both. Use inheritance where it provides natural hierarchical organization and shared behavior. Use composition where you need flexibility, testability, and runtime adaptability.*

*The wise architect knows that the most powerful systems combine both approaches judiciously.*

---

## **The Final Wisdom**

*This challenge tests your architectural judgment - the most crucial skill of a master programmer. Anyone can write code that works; only masters can design systems that evolve gracefully over time.*

*Show that you understand when to inherit (stable hierarchies with shared behavior) and when to compose (flexible capabilities that may change). Demonstrate that you can create systems that are both powerful and adaptable.*

**Forge your enterprise system, seeker. Let inheritance provide structure where appropriate, and let composition provide flexibility where needed. The architectural wisdom of the ages awaits your demonstration...**
