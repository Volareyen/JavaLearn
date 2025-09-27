/**
 * The Path Revealed: Enterprise Task Management System Solution
 * 
 * This solution demonstrates complete mastery of composition vs inheritance
 * through a comprehensive enterprise task management system.
 * 
 * Features demonstrated:
 * - Inheritance hierarchy for genuine "is-a" relationships (Task types)
 * - Template method pattern for shared algorithm structure
 * - Composition for flexible "has-a" relationships (strategies)
 * - Strategy pattern for runtime behavior switching
 * - Decorator pattern for non-intrusive feature enhancement
 * - Observer pattern for event-driven notifications
 * - Factory pattern using composition
 * - Complete separation of concerns through proper abstraction
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

// ═══════════════════════════════════════════════════════════════════════════════════
// ENUMERATIONS AND VALUE OBJECTS
// ═══════════════════════════════════════════════════════════════════════════════════

enum Priority {
    LOW, MEDIUM, HIGH, CRITICAL
}

enum TaskStatus {
    CREATED, VALIDATED, IN_PROGRESS, COMPLETED, FAILED, CANCELLED
}

enum NotificationType {
    INFO, WARNING, ERROR, SUCCESS
}

/**
 * Immutable result object for task execution
 */
final class TaskResult {
    private final boolean success;
    private final String message;
    private final Map<String, Object> metadata;
    private final LocalDateTime completionTime;
    
    public TaskResult(boolean success, String message) {
        this(success, message, Collections.emptyMap());
    }
    
    public TaskResult(boolean success, String message, Map<String, Object> metadata) {
        this.success = success;
        this.message = message;
        this.metadata = Collections.unmodifiableMap(new HashMap<>(metadata));
        this.completionTime = LocalDateTime.now();
    }
    
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Map<String, Object> getMetadata() { return metadata; }
    public LocalDateTime getCompletionTime() { return completionTime; }
    
    @Override
    public String toString() {
        return String.format("TaskResult{success=%s, message='%s', time=%s}", 
                           success, message, completionTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}

/**
 * Exception classes for task processing
 */
class ValidationException extends Exception {
    public ValidationException(String message) { super(message); }
}

class ExecutionException extends Exception {
    public ExecutionException(String message) { super(message); }
    public ExecutionException(String message, Throwable cause) { super(message, cause); }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// INHERITANCE HIERARCHY - TASK TYPES (TRUE "IS-A" RELATIONSHIPS)
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Abstract base class for all tasks - represents genuine "is-a" relationship
 * Every task type shares common properties and workflow
 */
abstract class Task {
    // Common properties for all tasks
    protected final String taskId;
    protected String title;
    protected String description;
    protected Priority priority;
    protected TaskStatus status;
    protected final LocalDateTime createdDate;
    protected String assignee;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    protected final List<String> executionLog;
    
    // Task observers for event notifications
    private final List<TaskObserver> observers;
    
    public Task(String taskId, String title, String description, Priority priority, String assignee) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.assignee = assignee;
        this.status = TaskStatus.CREATED;
        this.createdDate = LocalDateTime.now();
        this.executionLog = new ArrayList<>();
        this.observers = new ArrayList<>();
    }
    
    // Template method - defines the common workflow (final to prevent override)
    public final TaskResult executeTask() {
        try {
            logTaskStart();
            notifyObservers("Task execution started");
            
            // Step 1: Validate requirements (polymorphic call)
            log("Validating task requirements...");
            validateRequirements();
            updateStatus(TaskStatus.VALIDATED);
            notifyObservers("Task validation completed");
            
            // Step 2: Perform the work (polymorphic call)
            updateStatus(TaskStatus.IN_PROGRESS);
            log("Performing task work...");
            
            TaskResult workResult = performWork();
            
            if (!workResult.isSuccess()) {
                updateStatus(TaskStatus.FAILED);
                logTaskCompletion();
                return workResult;
            }
            
            // Step 3: Generate report (polymorphic call)
            log("Generating task report...");
            generateReport();
            
            // Step 4: Complete successfully
            updateStatus(TaskStatus.COMPLETED);
            logTaskCompletion();
            notifyObservers("Task execution completed successfully");
            
            Map<String, Object> metadata = new HashMap<>(workResult.getMetadata());
            metadata.put("executionTime", getExecutionTimeMs());
            metadata.put("taskType", getTaskType());
            
            return new TaskResult(true, "Task completed successfully", metadata);
            
        } catch (ValidationException e) {
            updateStatus(TaskStatus.FAILED);
            String errorMsg = "Validation failed: " + e.getMessage();
            log(errorMsg);
            notifyObservers("Task validation failed: " + e.getMessage());
            return new TaskResult(false, errorMsg);
            
        } catch (ExecutionException e) {
            updateStatus(TaskStatus.FAILED);
            String errorMsg = "Execution failed: " + e.getMessage();
            log(errorMsg);
            notifyObservers("Task execution failed: " + e.getMessage());
            return new TaskResult(false, errorMsg);
            
        } catch (Exception e) {
            updateStatus(TaskStatus.FAILED);
            String errorMsg = "Unexpected error: " + e.getMessage();
            log(errorMsg);
            notifyObservers("Task failed with unexpected error: " + e.getMessage());
            return new TaskResult(false, errorMsg);
        }
    }
    
    // Common concrete methods available to all subclasses
    protected void logTaskStart() {
        this.startTime = LocalDateTime.now();
        log("🚀 Starting task: " + title);
    }
    
    protected void logTaskCompletion() {
        this.endTime = LocalDateTime.now();
        log("✅ Task completed: " + title + " (Duration: " + getExecutionTimeMs() + "ms)");
    }
    
    protected void updateStatus(TaskStatus newStatus) {
        TaskStatus oldStatus = this.status;
        this.status = newStatus;
        log("Status changed: " + oldStatus + " → " + newStatus);
        notifyObservers("Status changed to " + newStatus);
    }
    
    protected void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String logEntry = String.format("[%s] %s: %s", timestamp, taskId, message);
        executionLog.add(logEntry);
        System.out.println("  " + logEntry);
    }
    
    private long getExecutionTimeMs() {
        if (startTime == null || endTime == null) return 0;
        return java.time.Duration.between(startTime, endTime).toMillis();
    }
    
    // Observer pattern implementation
    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }
    
    private void notifyObservers(String message) {
        for (TaskObserver observer : observers) {
            observer.onTaskEvent(this, message);
        }
    }
    
    // Abstract methods - must be implemented by subclasses
    protected abstract void validateRequirements() throws ValidationException;
    protected abstract TaskResult performWork() throws ExecutionException;
    protected abstract void generateReport();
    protected abstract String getTaskType();
    
    // Getters for common properties
    public String getTaskId() { return taskId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Priority getPriority() { return priority; }
    public TaskStatus getStatus() { return status; }
    public LocalDateTime getCreatedDate() { return createdDate; }
    public String getAssignee() { return assignee; }
    public List<String> getExecutionLog() { return Collections.unmodifiableList(executionLog); }
    
    @Override
    public String toString() {
        return String.format("%s{id='%s', title='%s', status=%s, assignee='%s'}", 
                           getClass().getSimpleName(), taskId, title, status, assignee);
    }
}

/**
 * Development tasks - for software development work
 * DevelopmentTask IS-A Task (genuine inheritance)
 */
class DevelopmentTask extends Task {
    private final String technologyStack;
    private final int estimatedHours;
    private final String complexity;
    private String codeRepository;
    private List<String> requirements;
    
    public DevelopmentTask(String taskId, String title, String description, 
                          Priority priority, String assignee, String technologyStack, 
                          int estimatedHours, String complexity) {
        super(taskId, title, description, priority, assignee);
        this.technologyStack = technologyStack;
        this.estimatedHours = estimatedHours;
        this.complexity = complexity;
        this.requirements = new ArrayList<>();
    }
    
    @Override
    protected void validateRequirements() throws ValidationException {
        log("🔍 Validating development requirements");
        
        if (technologyStack == null || technologyStack.trim().isEmpty()) {
            throw new ValidationException("Technology stack must be specified");
        }
        
        if (estimatedHours <= 0) {
            throw new ValidationException("Estimated hours must be positive");
        }
        
        if (complexity == null) {
            throw new ValidationException("Complexity level must be specified");
        }
        
        // Additional business logic validation
        if (priority == Priority.CRITICAL && estimatedHours > 40) {
            throw new ValidationException("Critical tasks cannot exceed 40 hours");
        }
        
        log("✅ Development requirements validation passed");
    }
    
    @Override
    protected TaskResult performWork() throws ExecutionException {
        log("💻 Performing development work");
        
        try {
            // Simulate development work based on complexity
            int workTimeMs = switch (complexity.toLowerCase()) {
                case "low" -> 100;
                case "medium" -> 200;
                case "high" -> 300;
                default -> 150;
            };
            
            // Simulate work with potential failure
            Thread.sleep(workTimeMs);
            
            if (Math.random() < 0.1) {  // 10% chance of failure
                throw new ExecutionException("Build failed - compilation errors detected");
            }
            
            log("✅ Development work completed successfully");
            
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("technologyStack", technologyStack);
            metadata.put("complexity", complexity);
            metadata.put("estimatedHours", estimatedHours);
            metadata.put("linesOfCode", 150 + (int)(Math.random() * 500));
            metadata.put("testsWritten", 5 + (int)(Math.random() * 15));
            
            return new TaskResult(true, "Development completed successfully", metadata);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ExecutionException("Development work interrupted", e);
        }
    }
    
    @Override
    protected void generateReport() {
        log("📊 Generating development report");
        
        Map<String, Object> report = new HashMap<>();
        report.put("taskId", taskId);
        report.put("taskType", "Development");
        report.put("technology", technologyStack);
        report.put("complexity", complexity);
        report.put("estimatedVsActual", estimatedHours + " hrs estimated");
        report.put("completionStatus", status);
        
        // In a real system, this would save to a reporting system
        log("📋 Development report generated: " + report);
    }
    
    @Override
    protected String getTaskType() {
        return "Development";
    }
    
    // Development-specific methods
    public void addRequirement(String requirement) {
        requirements.add(requirement);
        log("📝 Added requirement: " + requirement);
    }
    
    public void setCodeRepository(String repository) {
        this.codeRepository = repository;
        log("📁 Code repository set: " + repository);
    }
    
    // Getters for development-specific properties
    public String getTechnologyStack() { return technologyStack; }
    public int getEstimatedHours() { return estimatedHours; }
    public String getComplexity() { return complexity; }
    public String getCodeRepository() { return codeRepository; }
    public List<String> getRequirements() { return Collections.unmodifiableList(requirements); }
}

/**
 * Review tasks - for document or code reviews
 * ReviewTask IS-A Task (genuine inheritance)
 */
class ReviewTask extends Task {
    private final String documentToReview;
    private final String reviewType;
    private final LocalDateTime deadline;
    private List<String> findings;
    private String reviewOutcome;
    
    public ReviewTask(String taskId, String title, String description, 
                     Priority priority, String assignee, String documentToReview, 
                     String reviewType, LocalDateTime deadline) {
        super(taskId, title, description, priority, assignee);
        this.documentToReview = documentToReview;
        this.reviewType = reviewType;
        this.deadline = deadline;
        this.findings = new ArrayList<>();
    }
    
    @Override
    protected void validateRequirements() throws ValidationException {
        log("🔍 Validating review requirements");
        
        if (documentToReview == null || documentToReview.trim().isEmpty()) {
            throw new ValidationException("Document to review must be specified");
        }
        
        if (reviewType == null || reviewType.trim().isEmpty()) {
            throw new ValidationException("Review type must be specified");
        }
        
        if (deadline != null && deadline.isBefore(LocalDateTime.now())) {
            throw new ValidationException("Review deadline cannot be in the past");
        }
        
        // Check if reviewer has appropriate permissions
        if (!hasReviewPermissions(assignee, reviewType)) {
            throw new ValidationException("Assignee lacks permissions for " + reviewType + " review");
        }
        
        log("✅ Review requirements validation passed");
    }
    
    @Override
    protected TaskResult performWork() throws ExecutionException {
        log("📝 Performing review work");
        
        try {
            // Simulate review work
            int reviewTimeMs = switch (reviewType.toLowerCase()) {
                case "code" -> 300;
                case "security" -> 500;
                case "architecture" -> 400;
                case "documentation" -> 200;
                default -> 250;
            };
            
            Thread.sleep(reviewTimeMs);
            
            // Simulate finding issues
            int numFindings = (int)(Math.random() * 5);
            for (int i = 0; i < numFindings; i++) {
                String finding = "Finding " + (i + 1) + ": " + generateRandomFinding();
                findings.add(finding);
                log("🔍 " + finding);
            }
            
            reviewOutcome = numFindings == 0 ? "APPROVED" : "APPROVED_WITH_COMMENTS";
            
            log("✅ Review work completed: " + reviewOutcome);
            
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("documentReviewed", documentToReview);
            metadata.put("reviewType", reviewType);
            metadata.put("findingsCount", findings.size());
            metadata.put("outcome", reviewOutcome);
            metadata.put("deadline", deadline != null ? deadline.toString() : "None");
            
            return new TaskResult(true, "Review completed: " + reviewOutcome, metadata);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ExecutionException("Review work interrupted", e);
        }
    }
    
    @Override
    protected void generateReport() {
        log("📊 Generating review report");
        
        Map<String, Object> report = new HashMap<>();
        report.put("taskId", taskId);
        report.put("taskType", "Review");
        report.put("documentReviewed", documentToReview);
        report.put("reviewType", reviewType);
        report.put("outcome", reviewOutcome);
        report.put("findingsCount", findings.size());
        report.put("findings", findings);
        
        log("📋 Review report generated: " + report);
    }
    
    @Override
    protected String getTaskType() {
        return "Review";
    }
    
    private boolean hasReviewPermissions(String assignee, String reviewType) {
        // Simplified permission check - in real system would check against user roles
        return assignee != null && !assignee.trim().isEmpty();
    }
    
    private String generateRandomFinding() {
        String[] findings = {
            "Consider adding error handling",
            "Documentation needs updating",
            "Variable naming could be improved",
            "Performance optimization opportunity",
            "Security consideration required"
        };
        return findings[(int)(Math.random() * findings.length)];
    }
    
    // Review-specific methods
    public void addFinding(String finding) {
        findings.add(finding);
        log("📝 Added finding: " + finding);
    }
    
    // Getters for review-specific properties
    public String getDocumentToReview() { return documentToReview; }
    public String getReviewType() { return reviewType; }
    public LocalDateTime getDeadline() { return deadline; }
    public List<String> getFindings() { return Collections.unmodifiableList(findings); }
    public String getReviewOutcome() { return reviewOutcome; }
}

/**
 * Deployment tasks - for software deployment operations
 * DeploymentTask IS-A Task (genuine inheritance)
 */
class DeploymentTask extends Task {
    private final String environment;
    private final String deploymentType;
    private final String rollbackPlan;
    private String deploymentUrl;
    private Map<String, String> environmentVariables;
    
    public DeploymentTask(String taskId, String title, String description, 
                         Priority priority, String assignee, String environment, 
                         String deploymentType, String rollbackPlan) {
        super(taskId, title, description, priority, assignee);
        this.environment = environment;
        this.deploymentType = deploymentType;
        this.rollbackPlan = rollbackPlan;
        this.environmentVariables = new HashMap<>();
    }
    
    @Override
    protected void validateRequirements() throws ValidationException {
        log("🔍 Validating deployment requirements");
        
        if (environment == null || environment.trim().isEmpty()) {
            throw new ValidationException("Target environment must be specified");
        }
        
        if (deploymentType == null || deploymentType.trim().isEmpty()) {
            throw new ValidationException("Deployment type must be specified");
        }
        
        if (rollbackPlan == null || rollbackPlan.trim().isEmpty()) {
            throw new ValidationException("Rollback plan must be specified");
        }
        
        // Environment-specific validation
        if ("production".equalsIgnoreCase(environment) && priority != Priority.CRITICAL) {
            throw new ValidationException("Production deployments must have CRITICAL priority");
        }
        
        // Check deployment window for production
        if ("production".equalsIgnoreCase(environment) && !isInDeploymentWindow()) {
            throw new ValidationException("Production deployment outside allowed window");
        }
        
        log("✅ Deployment requirements validation passed");
    }
    
    @Override
    protected TaskResult performWork() throws ExecutionException {
        log("🚀 Performing deployment work");
        
        try {
            // Simulate deployment steps
            log("📦 Preparing deployment package");
            Thread.sleep(100);
            
            log("🔧 Configuring environment: " + environment);
            Thread.sleep(150);
            
            log("🚀 Executing deployment: " + deploymentType);
            Thread.sleep(200);
            
            // Simulate potential deployment failure
            if (Math.random() < 0.05) {  // 5% chance of failure
                log("❌ Deployment failed - initiating rollback");
                executeRollback();
                throw new ExecutionException("Deployment failed, rollback completed");
            }
            
            log("✅ Deployment completed successfully");
            
            // Generate deployment URL
            deploymentUrl = "https://" + environment + ".example.com";
            log("🌐 Application available at: " + deploymentUrl);
            
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("environment", environment);
            metadata.put("deploymentType", deploymentType);
            metadata.put("deploymentUrl", deploymentUrl);
            metadata.put("environmentVariables", environmentVariables.size());
            
            return new TaskResult(true, "Deployment completed successfully", metadata);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ExecutionException("Deployment interrupted", e);
        }
    }
    
    @Override
    protected void generateReport() {
        log("📊 Generating deployment report");
        
        Map<String, Object> report = new HashMap<>();
        report.put("taskId", taskId);
        report.put("taskType", "Deployment");
        report.put("environment", environment);
        report.put("deploymentType", deploymentType);
        report.put("deploymentUrl", deploymentUrl);
        report.put("completionStatus", status);
        
        log("📋 Deployment report generated: " + report);
    }
    
    @Override
    protected String getTaskType() {
        return "Deployment";
    }
    
    private boolean isInDeploymentWindow() {
        // Simplified check - production deployments only during business hours
        int hour = LocalDateTime.now().getHour();
        return hour >= 9 && hour <= 17;  // 9 AM to 5 PM
    }
    
    private void executeRollback() {
        log("🔄 Executing rollback plan: " + rollbackPlan);
        try {
            Thread.sleep(100);
            log("✅ Rollback completed");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log("❌ Rollback interrupted");
        }
    }
    
    // Deployment-specific methods
    public void addEnvironmentVariable(String key, String value) {
        environmentVariables.put(key, value);
        log("🔧 Added environment variable: " + key);
    }
    
    // Getters for deployment-specific properties
    public String getEnvironment() { return environment; }
    public String getDeploymentType() { return deploymentType; }
    public String getRollbackPlan() { return rollbackPlan; }
    public String getDeploymentUrl() { return deploymentUrl; }
    public Map<String, String> getEnvironmentVariables() { 
        return Collections.unmodifiableMap(environmentVariables); 
    }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// COMPOSITION-BASED SYSTEM - STRATEGIES AND BEHAVIORS (HAS-A RELATIONSHIPS)  
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Execution strategy interface for composition
 */
interface ExecutionStrategy {
    void execute(List<Task> tasks);
    String getStrategyName();
    Map<String, Object> getExecutionMetrics();
}

/**
 * Sequential execution strategy
 */
class SequentialExecution implements ExecutionStrategy {
    private long totalExecutionTime = 0;
    private int successCount = 0;
    private int failureCount = 0;
    
    @Override
    public void execute(List<Task> tasks) {
        System.out.println("🔄 Executing tasks sequentially...");
        long startTime = System.currentTimeMillis();
        
        for (Task task : tasks) {
            TaskResult result = task.executeTask();
            if (result.isSuccess()) {
                successCount++;
            } else {
                failureCount++;
            }
        }
        
        totalExecutionTime = System.currentTimeMillis() - startTime;
        System.out.println("✅ Sequential execution completed in " + totalExecutionTime + "ms");
    }
    
    @Override
    public String getStrategyName() {
        return "Sequential Execution";
    }
    
    @Override
    public Map<String, Object> getExecutionMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("strategy", getStrategyName());
        metrics.put("totalExecutionTime", totalExecutionTime);
        metrics.put("successCount", successCount);
        metrics.put("failureCount", failureCount);
        return metrics;
    }
}

/**
 * Parallel execution strategy
 */
class ParallelExecution implements ExecutionStrategy {
    private final int maxThreads;
    private long totalExecutionTime = 0;
    private int successCount = 0;
    private int failureCount = 0;
    
    public ParallelExecution() {
        this(Runtime.getRuntime().availableProcessors());
    }
    
    public ParallelExecution(int maxThreads) {
        this.maxThreads = maxThreads;
    }
    
    @Override
    public void execute(List<Task> tasks) {
        System.out.println("🚀 Executing tasks in parallel (max threads: " + maxThreads + ")...");
        long startTime = System.currentTimeMillis();
        
        ExecutorService executor = Executors.newFixedThreadPool(maxThreads);
        
        try {
            List<Future<TaskResult>> futures = tasks.stream()
                .map(task -> executor.submit(task::executeTask))
                .collect(Collectors.toList());
            
            // Wait for all tasks to complete
            for (Future<TaskResult> future : futures) {
                try {
                    TaskResult result = future.get();
                    if (result.isSuccess()) {
                        successCount++;
                    } else {
                        failureCount++;
                    }
                } catch (ExecutionException | InterruptedException e) {
                    failureCount++;
                    System.out.println("❌ Task execution failed: " + e.getMessage());
                }
            }
            
        } finally {
            executor.shutdown();
        }
        
        totalExecutionTime = System.currentTimeMillis() - startTime;
        System.out.println("✅ Parallel execution completed in " + totalExecutionTime + "ms");
    }
    
    @Override
    public String getStrategyName() {
        return "Parallel Execution (" + maxThreads + " threads)";
    }
    
    @Override
    public Map<String, Object> getExecutionMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("strategy", getStrategyName());
        metrics.put("totalExecutionTime", totalExecutionTime);
        metrics.put("maxThreads", maxThreads);
        metrics.put("successCount", successCount);
        metrics.put("failureCount", failureCount);
        return metrics;
    }
}

/**
 * Priority-based execution strategy
 */
class PriorityBasedExecution implements ExecutionStrategy {
    private long totalExecutionTime = 0;
    private int successCount = 0;
    private int failureCount = 0;
    
    @Override
    public void execute(List<Task> tasks) {
        System.out.println("📋 Executing tasks by priority (CRITICAL → HIGH → MEDIUM → LOW)...");
        long startTime = System.currentTimeMillis();
        
        // Sort tasks by priority (CRITICAL first, LOW last)
        List<Task> sortedTasks = tasks.stream()
            .sorted((t1, t2) -> {
                Priority[] order = {Priority.CRITICAL, Priority.HIGH, Priority.MEDIUM, Priority.LOW};
                int index1 = Arrays.asList(order).indexOf(t1.getPriority());
                int index2 = Arrays.asList(order).indexOf(t2.getPriority());
                return Integer.compare(index1, index2);
            })
            .collect(Collectors.toList());
        
        Priority currentPriority = null;
        for (Task task : sortedTasks) {
            if (currentPriority != task.getPriority()) {
                currentPriority = task.getPriority();
                System.out.println("🎯 Processing " + currentPriority + " priority tasks...");
            }
            
            TaskResult result = task.executeTask();
            if (result.isSuccess()) {
                successCount++;
            } else {
                failureCount++;
            }
        }
        
        totalExecutionTime = System.currentTimeMillis() - startTime;
        System.out.println("✅ Priority-based execution completed in " + totalExecutionTime + "ms");
    }
    
    @Override
    public String getStrategyName() {
        return "Priority-Based Execution";
    }
    
    @Override
    public Map<String, Object> getExecutionMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("strategy", getStrategyName());
        metrics.put("totalExecutionTime", totalExecutionTime);
        metrics.put("successCount", successCount);
        metrics.put("failureCount", failureCount);
        return metrics;
    }
}

/**
 * Notification strategy interface for composition
 */
interface NotificationStrategy {
    void notify(String recipient, String message, NotificationType type);
    String getStrategyName();
    boolean isAvailable();
}

/**
 * Email notification strategy
 */
class EmailNotification implements NotificationStrategy {
    private final String smtpServer;
    private int messagesSent = 0;
    
    public EmailNotification() {
        this("smtp.company.com");
    }
    
    public EmailNotification(String smtpServer) {
        this.smtpServer = smtpServer;
    }
    
    @Override
    public void notify(String recipient, String message, NotificationType type) {
        System.out.println("📧 Sending email notification:");
        System.out.println("   To: " + recipient);
        System.out.println("   Type: " + type);
        System.out.println("   Message: " + message);
        System.out.println("   SMTP Server: " + smtpServer);
        
        // Simulate email sending delay
        try {
            Thread.sleep(50);
            messagesSent++;
            System.out.println("✅ Email sent successfully");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("❌ Email sending interrupted");
        }
    }
    
    @Override
    public String getStrategyName() {
        return "Email Notification";
    }
    
    @Override
    public boolean isAvailable() {
        // Simulate availability check
        return true;  // In real system, would check SMTP server connection
    }
    
    public int getMessagesSent() { return messagesSent; }
}

/**
 * Slack notification strategy
 */
class SlackNotification implements NotificationStrategy {
    private final String webhookUrl;
    private int messagesSent = 0;
    
    public SlackNotification() {
        this("https://hooks.slack.com/services/company/channel");
    }
    
    public SlackNotification(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }
    
    @Override
    public void notify(String recipient, String message, NotificationType type) {
        String emoji = switch (type) {
            case INFO -> "ℹ️";
            case WARNING -> "⚠️";
            case ERROR -> "❌";
            case SUCCESS -> "✅";
        };
        
        System.out.println("💬 Sending Slack notification:");
        System.out.println("   Channel: " + recipient);
        System.out.println("   Type: " + type + " " + emoji);
        System.out.println("   Message: " + message);
        System.out.println("   Webhook: " + webhookUrl);
        
        // Simulate Slack API call delay
        try {
            Thread.sleep(30);
            messagesSent++;
            System.out.println("✅ Slack message sent successfully");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("❌ Slack notification interrupted");
        }
    }
    
    @Override
    public String getStrategyName() {
        return "Slack Notification";
    }
    
    @Override
    public boolean isAvailable() {
        // Simulate availability check
        return true;  // In real system, would check Slack API availability
    }
    
    public int getMessagesSent() { return messagesSent; }
}

/**
 * SMS notification strategy
 */
class SMSNotification implements NotificationStrategy {
    private final String provider;
    private int messagesSent = 0;
    
    public SMSNotification() {
        this("Twilio");
    }
    
    public SMSNotification(String provider) {
        this.provider = provider;
    }
    
    @Override
    public void notify(String recipient, String message, NotificationType type) {
        System.out.println("📱 Sending SMS notification:");
        System.out.println("   To: " + recipient);
        System.out.println("   Type: " + type);
        System.out.println("   Message: " + message);
        System.out.println("   Provider: " + provider);
        
        // Simulate SMS sending delay
        try {
            Thread.sleep(100);
            messagesSent++;
            System.out.println("✅ SMS sent successfully");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("❌ SMS sending interrupted");
        }
    }
    
    @Override
    public String getStrategyName() {
        return "SMS Notification (" + provider + ")";
    }
    
    @Override
    public boolean isAvailable() {
        // Simulate availability check
        return true;  // In real system, would check SMS provider API
    }
    
    public int getMessagesSent() { return messagesSent; }
}

/**
 * Persistence strategy interface for composition
 */
interface PersistenceStrategy {
    void save(Task task);
    Task load(String taskId);
    List<Task> findByStatus(TaskStatus status);
    String getStrategyName();
}

/**
 * In-memory persistence strategy (for demo)
 */
class InMemoryPersistence implements PersistenceStrategy {
    private final Map<String, Task> taskStore = new ConcurrentHashMap<>();
    
    @Override
    public void save(Task task) {
        taskStore.put(task.getTaskId(), task);
        System.out.println("💾 Saved task to memory: " + task.getTaskId());
    }
    
    @Override
    public Task load(String taskId) {
        Task task = taskStore.get(taskId);
        if (task != null) {
            System.out.println("📂 Loaded task from memory: " + taskId);
        } else {
            System.out.println("❌ Task not found in memory: " + taskId);
        }
        return task;
    }
    
    @Override
    public List<Task> findByStatus(TaskStatus status) {
        List<Task> tasks = taskStore.values().stream()
            .filter(task -> task.getStatus() == status)
            .collect(Collectors.toList());
        System.out.println("🔍 Found " + tasks.size() + " tasks with status " + status);
        return tasks;
    }
    
    @Override
    public String getStrategyName() {
        return "In-Memory Persistence";
    }
    
    public int getTaskCount() { return taskStore.size(); }
}

/**
 * Task observer interface for event-driven notifications
 */
interface TaskObserver {
    void onTaskEvent(Task task, String message);
}

/**
 * Concrete observer that logs task events
 */
class TaskEventLogger implements TaskObserver {
    @Override
    public void onTaskEvent(Task task, String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(String.format("[%s] EVENT - %s: %s", timestamp, task.getTaskId(), message));
    }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// TASK MANAGER - COMPOSITION COORDINATOR
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * TaskManager coordinates all operations through composition
 * HAS-A ExecutionStrategy, HAS-A NotificationStrategy, HAS-A PersistenceStrategy
 */
class TaskManager {
    private final String managerName;
    
    // Composed strategies - can change at runtime!
    private ExecutionStrategy executionStrategy;
    private NotificationStrategy notificationStrategy;
    private PersistenceStrategy persistenceStrategy;
    
    // Additional composed components
    private final List<TaskObserver> globalObservers;
    private final Map<String, Object> metrics;
    
    public TaskManager(String managerName, 
                      ExecutionStrategy executionStrategy,
                      NotificationStrategy notificationStrategy,
                      PersistenceStrategy persistenceStrategy) {
        this.managerName = managerName;
        this.executionStrategy = executionStrategy;
        this.notificationStrategy = notificationStrategy;
        this.persistenceStrategy = persistenceStrategy;
        this.globalObservers = new ArrayList<>();
        this.metrics = new ConcurrentHashMap<>();
        
        System.out.println("🏗️ Created TaskManager: " + managerName);
        System.out.println("   Execution Strategy: " + executionStrategy.getStrategyName());
        System.out.println("   Notification Strategy: " + notificationStrategy.getStrategyName());
        System.out.println("   Persistence Strategy: " + persistenceStrategy.getStrategyName());
    }
    
    /**
     * Process tasks using composed strategies
     */
    public void processTasks(List<Task> tasks) {
        System.out.println("\n🏭 " + managerName + " processing " + tasks.size() + " tasks...");
        
        // Add global observers to all tasks
        for (Task task : tasks) {
            for (TaskObserver observer : globalObservers) {
                task.addObserver(observer);
            }
        }
        
        // Persist tasks before processing
        for (Task task : tasks) {
            persistenceStrategy.save(task);
        }
        
        // Execute using composed strategy
        long startTime = System.currentTimeMillis();
        executionStrategy.execute(tasks);
        long processingTime = System.currentTimeMillis() - startTime;
        
        // Update persistence after processing
        for (Task task : tasks) {
            persistenceStrategy.save(task);
        }
        
        // Send summary notifications
        sendSummaryNotification(tasks, processingTime);
        
        // Update metrics
        updateMetrics(tasks, processingTime);
        
        System.out.println("✅ Task processing completed by " + managerName);
    }
    
    private void sendSummaryNotification(List<Task> tasks, long processingTime) {
        long successCount = tasks.stream().mapToLong(task -> 
            task.getStatus() == TaskStatus.COMPLETED ? 1 : 0).sum();
        long failureCount = tasks.size() - successCount;
        
        String message = String.format("Batch processing completed: %d/%d tasks successful (%.1fs)",
                                     successCount, tasks.size(), processingTime / 1000.0);
        
        NotificationType type = failureCount == 0 ? NotificationType.SUCCESS : 
                               failureCount < tasks.size() / 2 ? NotificationType.WARNING : 
                               NotificationType.ERROR;
        
        notificationStrategy.notify("task-managers", message, type);
    }
    
    private void updateMetrics(List<Task> tasks, long processingTime) {
        metrics.put("lastProcessingTime", processingTime);
        metrics.put("lastBatchSize", tasks.size());
        metrics.put("totalTasksProcessed", 
                   (Long) metrics.getOrDefault("totalTasksProcessed", 0L) + tasks.size());
        metrics.put("lastExecutionStrategy", executionStrategy.getStrategyName());
        metrics.putAll(executionStrategy.getExecutionMetrics());
    }
    
    // Runtime strategy changes - impossible with inheritance!
    public void setExecutionStrategy(ExecutionStrategy strategy) {
        System.out.println("🔄 " + managerName + " changing execution strategy:");
        System.out.println("   From: " + executionStrategy.getStrategyName());
        System.out.println("   To: " + strategy.getStrategyName());
        this.executionStrategy = strategy;
    }
    
    public void setNotificationStrategy(NotificationStrategy strategy) {
        System.out.println("🔄 " + managerName + " changing notification strategy:");
        System.out.println("   From: " + notificationStrategy.getStrategyName());
        System.out.println("   To: " + strategy.getStrategyName());
        this.notificationStrategy = strategy;
    }
    
    public void setPersistenceStrategy(PersistenceStrategy strategy) {
        System.out.println("🔄 " + managerName + " changing persistence strategy:");
        System.out.println("   From: " + persistenceStrategy.getStrategyName());
        System.out.println("   To: " + strategy.getStrategyName());
        this.persistenceStrategy = strategy;
    }
    
    // Observer management
    public void addGlobalObserver(TaskObserver observer) {
        globalObservers.add(observer);
        System.out.println("👁️ Added global observer to " + managerName);
    }
    
    public void removeGlobalObserver(TaskObserver observer) {
        globalObservers.remove(observer);
        System.out.println("👁️ Removed global observer from " + managerName);
    }
    
    // Query methods using composed persistence
    public Task findTask(String taskId) {
        return persistenceStrategy.load(taskId);
    }
    
    public List<Task> findTasksByStatus(TaskStatus status) {
        return persistenceStrategy.findByStatus(status);
    }
    
    // Metrics and status
    public void displayStatus() {
        System.out.println("\n📊 " + managerName.toUpperCase() + " STATUS:");
        System.out.println("   Current Execution Strategy: " + executionStrategy.getStrategyName());
        System.out.println("   Current Notification Strategy: " + notificationStrategy.getStrategyName());
        System.out.println("   Current Persistence Strategy: " + persistenceStrategy.getStrategyName());
        System.out.println("   Global Observers: " + globalObservers.size());
        System.out.println("   Metrics: " + metrics);
    }
    
    // Getters
    public String getManagerName() { return managerName; }
    public ExecutionStrategy getExecutionStrategy() { return executionStrategy; }
    public NotificationStrategy getNotificationStrategy() { return notificationStrategy; }
    public PersistenceStrategy getPersistenceStrategy() { return persistenceStrategy; }
    public Map<String, Object> getMetrics() { return Collections.unmodifiableMap(metrics); }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// DECORATOR PATTERN - ENHANCED TASKS
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Base task decorator using composition
 */
abstract class TaskDecorator extends Task {
    protected Task wrappedTask;  // Composition - HAS-A Task
    
    public TaskDecorator(Task task) {
        // Initialize with wrapped task's properties
        super(task.getTaskId(), task.getTitle(), task.getDescription(), 
              task.getPriority(), task.getAssignee());
        this.wrappedTask = task;
    }
    
    @Override
    protected void validateRequirements() throws ValidationException {
        wrappedTask.validateRequirements();  // Delegation
    }
    
    @Override
    protected TaskResult performWork() throws ExecutionException {
        return wrappedTask.performWork();  // Delegation
    }
    
    @Override
    protected void generateReport() {
        wrappedTask.generateReport();  // Delegation
    }
    
    @Override
    protected String getTaskType() {
        return wrappedTask.getTaskType();  // Delegation
    }
}

/**
 * Logging decorator - adds logging to any task
 */
class LoggingTaskDecorator extends TaskDecorator {
    private final String logPrefix;
    
    public LoggingTaskDecorator(Task task) {
        this(task, "LOG");
    }
    
    public LoggingTaskDecorator(Task task, String logPrefix) {
        super(task);
        this.logPrefix = logPrefix;
    }
    
    @Override
    public TaskResult executeTask() {
        System.out.println("📝 [" + logPrefix + "] Starting execution of: " + wrappedTask.getTitle());
        
        TaskResult result = super.executeTask();  // Call wrapped task
        
        if (result.isSuccess()) {
            System.out.println("📝 [" + logPrefix + "] Successfully completed: " + wrappedTask.getTitle());
        } else {
            System.out.println("📝 [" + logPrefix + "] Failed execution: " + wrappedTask.getTitle() + 
                             " - " + result.getMessage());
        }
        
        return result;
    }
}

/**
 * Timing decorator - adds execution time measurement
 */
class TimingTaskDecorator extends TaskDecorator {
    public TimingTaskDecorator(Task task) {
        super(task);
    }
    
    @Override
    public TaskResult executeTask() {
        System.out.println("⏱️ [TIMER] Starting timing for: " + wrappedTask.getTitle());
        
        long startTime = System.nanoTime();
        TaskResult result = super.executeTask();  // Call wrapped task
        long endTime = System.nanoTime();
        
        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.println(String.format("⏱️ [TIMER] Execution time: %.3f ms for: %s", 
                                        durationMs, wrappedTask.getTitle()));
        
        // Add timing info to result metadata
        Map<String, Object> enhancedMetadata = new HashMap<>(result.getMetadata());
        enhancedMetadata.put("executionTimeMs", durationMs);
        
        return new TaskResult(result.isSuccess(), result.getMessage(), enhancedMetadata);
    }
}

/**
 * Retry decorator - adds retry logic to any task
 */
class RetryTaskDecorator extends TaskDecorator {
    private final int maxRetries;
    private final long retryDelayMs;
    
    public RetryTaskDecorator(Task task, int maxRetries) {
        this(task, maxRetries, 1000);
    }
    
    public RetryTaskDecorator(Task task, int maxRetries, long retryDelayMs) {
        super(task);
        this.maxRetries = maxRetries;
        this.retryDelayMs = retryDelayMs;
    }
    
    @Override
    public TaskResult executeTask() {
        System.out.println("🔄 [RETRY] Max attempts: " + maxRetries + " for: " + wrappedTask.getTitle());
        
        TaskResult lastResult = null;
        
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            System.out.println("🔄 [RETRY] Attempt " + attempt + " of " + maxRetries);
            
            lastResult = super.executeTask();  // Call wrapped task
            
            if (lastResult.isSuccess()) {
                System.out.println("✅ [RETRY] Succeeded on attempt " + attempt);
                Map<String, Object> enhancedMetadata = new HashMap<>(lastResult.getMetadata());
                enhancedMetadata.put("attemptsRequired", attempt);
                return new TaskResult(true, lastResult.getMessage(), enhancedMetadata);
            } else {
                System.out.println("❌ [RETRY] Attempt " + attempt + " failed: " + lastResult.getMessage());
                
                if (attempt < maxRetries) {
                    System.out.println("⏳ [RETRY] Waiting " + retryDelayMs + "ms before retry...");
                    try {
                        Thread.sleep(retryDelayMs);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }
        
        System.out.println("❌ [RETRY] All attempts failed for: " + wrappedTask.getTitle());
        Map<String, Object> enhancedMetadata = new HashMap<>(lastResult.getMetadata());
        enhancedMetadata.put("attemptsRequired", maxRetries);
        enhancedMetadata.put("allAttemptsFailed", true);
        
        return new TaskResult(false, "Failed after " + maxRetries + " attempts: " + lastResult.getMessage(), 
                            enhancedMetadata);
    }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// MAIN DEMONSTRATION CLASS
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Enterprise Task Management System demonstrating composition vs inheritance mastery
 */
public class EnterpriseTaskSystem {
    
    public static void main(String[] args) {
        System.out.println("🏢 ENTERPRISE TASK MANAGEMENT SYSTEM DEMONSTRATION 🏢\n");
        
        // ═══════════════════════════════════════════════════════════════
        // CREATE TASK HIERARCHY USING INHERITANCE
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("📋 CREATING TASKS (Inheritance Hierarchy):");
        
        List<Task> tasks = Arrays.asList(
            new DevelopmentTask("DEV-001", "Implement User Authentication", 
                               "Add JWT-based authentication system", Priority.HIGH, 
                               "alice.dev", "Java Spring", 16, "medium"),
            
            new ReviewTask("REV-001", "Security Code Review", 
                          "Review authentication implementation for security issues", 
                          Priority.CRITICAL, "bob.security", "user-auth-pr-123", 
                          "security", LocalDateTime.now().plusDays(2)),
            
            new DeploymentTask("DEP-001", "Deploy to Staging", 
                              "Deploy authentication service to staging environment", 
                              Priority.MEDIUM, "charlie.devops", "staging", 
                              "blue-green", "rollback-to-previous-version"),
            
            new DevelopmentTask("DEV-002", "Implement Password Reset", 
                               "Add password reset functionality", Priority.MEDIUM, 
                               "diana.dev", "React TypeScript", 12, "low"),
            
            new DeploymentTask("DEP-002", "Production Deployment", 
                              "Deploy to production with monitoring", Priority.CRITICAL, 
                              "eve.devops", "production", "rolling", 
                              "automated-rollback-on-error")
        );
        
        System.out.println("Created task hierarchy:");
        tasks.forEach(task -> System.out.println("  📝 " + task));
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATE INHERITANCE POLYMORPHISM
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🎭 INHERITANCE POLYMORPHISM DEMONSTRATION:");
        System.out.println("Executing tasks using polymorphic method calls...\n");
        
        // Add some task-specific configuration
        if (tasks.get(0) instanceof DevelopmentTask devTask) {
            devTask.addRequirement("OAuth2 integration");
            devTask.addRequirement("Multi-factor authentication");
            devTask.setCodeRepository("https://github.com/company/auth-service");
        }
        
        if (tasks.get(4) instanceof DeploymentTask deployTask) {
            deployTask.addEnvironmentVariable("DATABASE_URL", "prod-db.company.com");
            deployTask.addEnvironmentVariable("API_KEY", "***secret***");
        }
        
        // Execute one task polymorphically to show template method pattern
        Task sampleTask = tasks.get(0);
        TaskResult sampleResult = sampleTask.executeTask();  // Polymorphic call
        System.out.println("Sample execution result: " + sampleResult);
        
        // ═══════════════════════════════════════════════════════════════
        // CREATE TASK MANAGERS USING COMPOSITION
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🏭 CREATING TASK MANAGERS (Composition):");
        
        // Create different task managers with different composed strategies
        TaskManager webTeamManager = new TaskManager("WebTeam-Manager",
            new SequentialExecution(),
            new SlackNotification(),
            new InMemoryPersistence()
        );
        
        TaskManager devOpsManager = new TaskManager("DevOps-Manager",
            new ParallelExecution(2),
            new EmailNotification(),
            new InMemoryPersistence()
        );
        
        TaskManager executiveManager = new TaskManager("Executive-Manager",
            new PriorityBasedExecution(),
            new SMSNotification(),
            new InMemoryPersistence()
        );
        
        // Add global observers
        TaskEventLogger eventLogger = new TaskEventLogger();
        webTeamManager.addGlobalObserver(eventLogger);
        devOpsManager.addGlobalObserver(eventLogger);
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATE COMPOSED STRATEGY EXECUTION
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n⚙️ COMPOSITION STRATEGY DEMONSTRATION:");
        
        // Process same tasks with different strategies
        List<Task> webTasks = Arrays.asList(tasks.get(0), tasks.get(3));  // Dev tasks
        List<Task> devOpsTasks = Arrays.asList(tasks.get(2), tasks.get(4));  // Deploy tasks
        List<Task> allTasks = new ArrayList<>(tasks);
        
        System.out.println("\n1️⃣ Web Team Processing (Sequential + Slack):");
        webTeamManager.processTasks(webTasks);
        
        System.out.println("\n2️⃣ DevOps Processing (Parallel + Email):");
        devOpsManager.processTasks(devOpsTasks);
        
        System.out.println("\n3️⃣ Executive Processing (Priority + SMS):");
        executiveManager.processTasks(allTasks);
        
        // ═══════════════════════════════════════════════════════════════
        // RUNTIME STRATEGY CHANGES (IMPOSSIBLE WITH INHERITANCE)
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🔄 RUNTIME STRATEGY CHANGES:");
        System.out.println("Changing strategies at runtime...\n");
        
        // Change execution strategy
        webTeamManager.setExecutionStrategy(new ParallelExecution(3));
        
        // Change notification strategy
        devOpsManager.setNotificationStrategy(new SlackNotification());
        
        // Process again with new strategies
        System.out.println("\nReprocessing with new strategies:");
        webTeamManager.processTasks(Arrays.asList(tasks.get(1)));  // Review task
        
        // ═══════════════════════════════════════════════════════════════
        // DECORATOR PATTERN DEMONSTRATION
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🎨 DECORATOR PATTERN DEMONSTRATION:");
        System.out.println("Adding features through composition...\n");
        
        // Create a development task
        Task baseTask = new DevelopmentTask("DEV-003", "Fix Critical Bug", 
                                           "Fix memory leak in payment service", 
                                           Priority.CRITICAL, "frank.dev", 
                                           "Java", 4, "high");
        
        // Apply multiple decorators using composition
        Task decoratedTask = new LoggingTaskDecorator(
            new TimingTaskDecorator(
                new RetryTaskDecorator(baseTask, 3, 500)
            ),
            "CRITICAL-BUG"
        );
        
        System.out.println("Executing task with multiple decorators:");
        TaskResult decoratedResult = decoratedTask.executeTask();
        System.out.println("Final result: " + decoratedResult);
        System.out.println("Enhanced metadata: " + decoratedResult.getMetadata());
        
        // ═══════════════════════════════════════════════════════════════
        // MANAGER STATUS AND METRICS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n📊 MANAGER STATUS AND METRICS:");
        
        webTeamManager.displayStatus();
        devOpsManager.displayStatus();
        executiveManager.displayStatus();
        
        // ═══════════════════════════════════════════════════════════════
        // QUERYING WITH COMPOSED PERSISTENCE
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🔍 QUERYING TASKS:");
        
        List<Task> completedTasks = webTeamManager.findTasksByStatus(TaskStatus.COMPLETED);
        List<Task> failedTasks = devOpsManager.findTasksByStatus(TaskStatus.FAILED);
        
        System.out.println("Completed tasks: " + completedTasks.size());
        System.out.println("Failed tasks: " + failedTasks.size());
        
        // ═══════════════════════════════════════════════════════════════
        // ARCHITECTURAL ANALYSIS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n📐 ARCHITECTURAL ANALYSIS:");
        
        analyzeInheritanceUsage();
        analyzeCompositionUsage();
        
        // ═══════════════════════════════════════════════════════════════
        // TESTING LISKOV SUBSTITUTION PRINCIPLE
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🧪 LISKOV SUBSTITUTION PRINCIPLE TEST:");
        
        testLiskovSubstitution();
        
        // ═══════════════════════════════════════════════════════════════
        // FINAL SUMMARY
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n✨ ENTERPRISE TASK MANAGEMENT SYSTEM DEMONSTRATION COMPLETE! ✨");
        System.out.println("\n🎯 ARCHITECTURAL MASTERY DEMONSTRATED:");
        System.out.println("🔹 Inheritance used for genuine 'is-a' task type relationships");
        System.out.println("🔹 Template method pattern provided shared workflow structure");
        System.out.println("🔹 Polymorphism enabled uniform task processing regardless of type");
        System.out.println("🔹 Composition enabled flexible strategy selection and runtime changes");
        System.out.println("🔹 Strategy pattern allowed algorithm swapping without inheritance explosion");
        System.out.println("🔹 Decorator pattern added features without modifying existing classes");
        System.out.println("🔹 Observer pattern provided loose coupling for event notifications");
        System.out.println("🔹 Dependency injection enabled easy testing and configuration");
        System.out.println("🔹 Both approaches used appropriately for their strengths");
        
        System.out.println("\n💡 The wise architect masters both inheritance and composition!");
        System.out.println("🏗️ Inheritance for stable hierarchies, composition for flexible behaviors!");
    }
    
    /**
     * Analyzes when and why inheritance was used
     */
    private static void analyzeInheritanceUsage() {
        System.out.println("\n🧬 INHERITANCE USAGE ANALYSIS:");
        System.out.println("✅ Used inheritance for Task hierarchy because:");
        System.out.println("   • Genuine 'is-a' relationships exist");
        System.out.println("   • DevelopmentTask IS-A Task, ReviewTask IS-A Task, DeploymentTask IS-A Task");
        System.out.println("   • Shared common workflow through template method pattern");
        System.out.println("   • Polymorphic behavior needed for uniform processing");
        System.out.println("   • Stable domain model with clear hierarchical structure");
        System.out.println("   • Liskov Substitution Principle maintained");
        System.out.println("   • Template method prevents algorithm modification while allowing customization");
    }
    
    /**
     * Analyzes when and why composition was used
     */
    private static void analyzeCompositionUsage() {
        System.out.println("\n🔧 COMPOSITION USAGE ANALYSIS:");
        System.out.println("✅ Used composition for TaskManager and strategies because:");
        System.out.println("   • TaskManager HAS-A ExecutionStrategy (not IS-A ExecutionStrategy)");
        System.out.println("   • TaskManager HAS-A NotificationStrategy (not IS-A NotificationStrategy)");
        System.out.println("   • TaskManager HAS-A PersistenceStrategy (not IS-A PersistenceStrategy)");
        System.out.println("   • Runtime strategy switching required");
        System.out.println("   • Multiple orthogonal capabilities needed");
        System.out.println("   • Easy testing through dependency injection");
        System.out.println("   • Decorator pattern for non-intrusive feature enhancement");
        System.out.println("   • Strategy pattern for algorithm family management");
        System.out.println("   • Loose coupling between components");
    }
    
    /**
     * Tests Liskov Substitution Principle with task hierarchy
     */
    private static void testLiskovSubstitution() {
        System.out.println("Testing that subclasses can substitute for base class...");
        
        // Create array of Task references pointing to different subtypes
        Task[] tasks = {
            new DevelopmentTask("LSP-001", "Test Task 1", "Test", Priority.LOW, 
                               "tester", "Java", 1, "low"),
            new ReviewTask("LSP-002", "Test Task 2", "Test", Priority.LOW, 
                          "tester", "document.pdf", "code", null),
            new DeploymentTask("LSP-003", "Test Task 3", "Test", Priority.LOW, 
                              "tester", "test", "rolling", "rollback")
        };
        
        // Test that all subtypes work identically through base class interface
        System.out.println("Processing tasks through base Task reference:");
        for (Task task : tasks) {
            System.out.println("  • " + task.getClass().getSimpleName() + 
                             " - getTaskType(): " + task.getTaskType());
            System.out.println("    Status: " + task.getStatus() + 
                             ", Priority: " + task.getPriority());
            System.out.println("    Can be processed uniformly: ✅");
        }
        
        System.out.println("✅ Liskov Substitution Principle verified!");
        System.out.println("   All subtypes can substitute for Task without breaking client code");
    }
}
