/**
 * _PracticalExample.java - The Living Manuscript of Generics
 * 
 * MYSTIC DATA PROCESSING PIPELINE - Advanced Analytics System
 * 
 * This real-world example demonstrates generics solving complex business problems
 * in a data processing and analytics system. Witness how generics enable type safety,
 * code reuse, and elegant abstraction in demanding production scenarios.
 * 
 * "Behold how generics transform chaos into order, complexity into simplicity,
 * and rigid code into flexible, reusable masterpieces!"
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

// ========================================
// CORE GENERIC DATA STRUCTURES
// ========================================

/**
 * Generic result container with success/failure states and error handling
 * Demonstrates generic container pattern with comprehensive error management
 */
class Result<T> {
    private final boolean success;
    private final T data;
    private final String errorMessage;
    private final Exception exception;
    private final LocalDateTime timestamp;
    
    private Result(boolean success, T data, String errorMessage, Exception exception) {
        this.success = success;
        this.data = data;
        this.errorMessage = errorMessage;
        this.exception = exception;
        this.timestamp = LocalDateTime.now();
    }
    
    // Static factory methods for different result types
    public static <T> Result<T> success(T data) {
        return new Result<>(true, data, null, null);
    }
    
    public static <T> Result<T> failure(String message) {
        return new Result<>(false, null, message, null);
    }
    
    public static <T> Result<T> failure(String message, Exception exception) {
        return new Result<>(false, null, message, exception);
    }
    
    public static <T> Result<T> failure(Exception exception) {
        return new Result<>(false, null, exception.getMessage(), exception);
    }
    
    // Query methods
    public boolean isSuccess() { return success; }
    public boolean isFailure() { return !success; }
    public T getData() { return data; }
    public String getErrorMessage() { return errorMessage; }
    public Exception getException() { return exception; }
    public LocalDateTime getTimestamp() { return timestamp; }
    
    // Functional operations on results
    public <U> Result<U> map(Function<T, U> mapper) {
        if (isSuccess()) {
            try {
                return Result.success(mapper.apply(data));
            } catch (Exception e) {
                return Result.failure("Mapping failed: " + e.getMessage(), e);
            }
        }
        return Result.failure(errorMessage, exception);
    }
    
    public <U> Result<U> flatMap(Function<T, Result<U>> mapper) {
        if (isSuccess()) {
            try {
                return mapper.apply(data);
            } catch (Exception e) {
                return Result.failure("FlatMap failed: " + e.getMessage(), e);
            }
        }
        return Result.failure(errorMessage, exception);
    }
    
    public T orElse(T defaultValue) {
        return isSuccess() ? data : defaultValue;
    }
    
    public T orElseThrow() throws RuntimeException {
        if (isSuccess()) {
            return data;
        }
        throw new RuntimeException(errorMessage, exception);
    }
    
    @Override
    public String toString() {
        if (isSuccess()) {
            return "Success[" + data + "] at " + timestamp.format(DateTimeFormatter.ISO_LOCAL_TIME);
        } else {
            return "Failure[" + errorMessage + "] at " + timestamp.format(DateTimeFormatter.ISO_LOCAL_TIME);
        }
    }
}

/**
 * Generic data record with metadata and type-safe operations
 * Demonstrates bounded generics and flexible data modeling
 */
class DataRecord<T> {
    private final String id;
    private final T data;
    private final Map<String, Object> metadata;
    private final LocalDateTime createdAt;
    
    public DataRecord(String id, T data) {
        this.id = id;
        this.data = data;
        this.metadata = new HashMap<>();
        this.createdAt = LocalDateTime.now();
    }
    
    public DataRecord(String id, T data, Map<String, Object> metadata) {
        this.id = id;
        this.data = data;
        this.metadata = new HashMap<>(metadata);
        this.createdAt = LocalDateTime.now();
    }
    
    // Accessors
    public String getId() { return id; }
    public T getData() { return data; }
    public Map<String, Object> getMetadata() { return new HashMap<>(metadata); }
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    // Metadata operations
    public DataRecord<T> withMetadata(String key, Object value) {
        Map<String, Object> newMetadata = new HashMap<>(metadata);
        newMetadata.put(key, value);
        return new DataRecord<>(id, data, newMetadata);
    }
    
    @SuppressWarnings("unchecked")
    public <U> Optional<U> getMetadata(String key, Class<U> type) {
        Object value = metadata.get(key);
        if (value != null && type.isInstance(value)) {
            return Optional.of((U) value);
        }
        return Optional.empty();
    }
    
    // Transform data while preserving metadata
    public <U> DataRecord<U> transform(Function<T, U> transformer) {
        U newData = transformer.apply(data);
        return new DataRecord<>(id, newData, metadata);
    }
    
    @Override
    public String toString() {
        return String.format("DataRecord[%s: %s] (%d metadata)", id, data, metadata.size());
    }
}

// ========================================
// PROCESSING PIPELINE INTERFACES
// ========================================

/**
 * Generic data processor interface with error handling
 * Demonstrates generic interface design for flexible processing
 */
@FunctionalInterface
interface DataProcessor<T, U> {
    Result<U> process(T input);
    
    // Default method for chaining processors
    default <V> DataProcessor<T, V> andThen(DataProcessor<U, V> next) {
        return input -> {
            Result<U> firstResult = this.process(input);
            if (firstResult.isFailure()) {
                return Result.failure(firstResult.getErrorMessage(), firstResult.getException());
            }
            return next.process(firstResult.getData());
        };
    }
    
    // Static method to create processor from function
    static <T, U> DataProcessor<T, U> of(Function<T, U> function) {
        return input -> {
            try {
                return Result.success(function.apply(input));
            } catch (Exception e) {
                return Result.failure("Processing failed: " + e.getMessage(), e);
            }
        };
    }
}

/**
 * Generic validator interface for data validation
 * Demonstrates generic contract definition
 */
interface Validator<T> {
    ValidationResult validate(T data);
    
    // Combine validators
    default Validator<T> and(Validator<T> other) {
        return data -> {
            ValidationResult first = this.validate(data);
            if (!first.isValid()) {
                return first;
            }
            return other.validate(data);
        };
    }
    
    default Validator<T> or(Validator<T> other) {
        return data -> {
            ValidationResult first = this.validate(data);
            if (first.isValid()) {
                return first;
            }
            return other.validate(data);
        };
    }
}

/**
 * Validation result container
 */
class ValidationResult {
    private final boolean valid;
    private final List<String> errors;
    
    private ValidationResult(boolean valid, List<String> errors) {
        this.valid = valid;
        this.errors = new ArrayList<>(errors);
    }
    
    public static ValidationResult valid() {
        return new ValidationResult(true, Collections.emptyList());
    }
    
    public static ValidationResult invalid(String error) {
        return new ValidationResult(false, Arrays.asList(error));
    }
    
    public static ValidationResult invalid(List<String> errors) {
        return new ValidationResult(false, errors);
    }
    
    public boolean isValid() { return valid; }
    public List<String> getErrors() { return new ArrayList<>(errors); }
    
    @Override
    public String toString() {
        return valid ? "Valid" : "Invalid: " + String.join(", ", errors);
    }
}

// ========================================
// BUSINESS DOMAIN CLASSES
// ========================================

/**
 * Customer data model for demonstration
 */
class Customer {
    private String id;
    private String name;
    private String email;
    private int age;
    private double totalPurchases;
    
    public Customer(String id, String name, String email, int age, double totalPurchases) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.totalPurchases = totalPurchases;
    }
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public double getTotalPurchases() { return totalPurchases; }
    
    // Setters
    public void setTotalPurchases(double totalPurchases) { this.totalPurchases = totalPurchases; }
    
    @Override
    public String toString() {
        return String.format("Customer[%s: %s, age=%d, purchases=$%.2f]", 
            id, name, age, totalPurchases);
    }
}

/**
 * Sales data model
 */
class Sale {
    private String id;
    private String customerId;
    private double amount;
    private String product;
    private LocalDateTime timestamp;
    
    public Sale(String id, String customerId, double amount, String product) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.product = product;
        this.timestamp = LocalDateTime.now();
    }
    
    // Getters
    public String getId() { return id; }
    public String getCustomerId() { return customerId; }
    public double getAmount() { return amount; }
    public String getProduct() { return product; }
    public LocalDateTime getTimestamp() { return timestamp; }
    
    @Override
    public String toString() {
        return String.format("Sale[%s: $%.2f for %s by %s]", 
            id, amount, product, customerId);
    }
}

/**
 * Analytics result model
 */
class AnalyticsResult {
    private Map<String, Object> metrics;
    private LocalDateTime generatedAt;
    
    public AnalyticsResult() {
        this.metrics = new HashMap<>();
        this.generatedAt = LocalDateTime.now();
    }
    
    public AnalyticsResult addMetric(String name, Object value) {
        metrics.put(name, value);
        return this;
    }
    
    @SuppressWarnings("unchecked")
    public <T> Optional<T> getMetric(String name, Class<T> type) {
        Object value = metrics.get(name);
        if (value != null && type.isInstance(value)) {
            return Optional.of((T) value);
        }
        return Optional.empty();
    }
    
    public Map<String, Object> getAllMetrics() {
        return new HashMap<>(metrics);
    }
    
    @Override
    public String toString() {
        return String.format("AnalyticsResult[%d metrics generated at %s]", 
            metrics.size(), generatedAt.format(DateTimeFormatter.ISO_LOCAL_TIME));
    }
}

// ========================================
// GENERIC PROCESSING PIPELINE
// ========================================

/**
 * Generic data processing pipeline with comprehensive error handling and monitoring
 * Demonstrates advanced generic patterns in real-world scenarios
 */
class DataPipeline<T, U> {
    private final List<DataProcessor<?, ?>> processors;
    private final List<Validator<?>> validators;
    private final Map<String, Object> configuration;
    private final ExecutorService executorService;
    
    private DataPipeline(Builder<T, U> builder) {
        this.processors = new ArrayList<>(builder.processors);
        this.validators = new ArrayList<>(builder.validators);
        this.configuration = new HashMap<>(builder.configuration);
        this.executorService = builder.executorService != null ? 
            builder.executorService : ForkJoinPool.commonPool();
    }
    
    /**
     * Process a single data record through the pipeline
     */
    @SuppressWarnings("unchecked")
    public Result<DataRecord<U>> process(DataRecord<T> input) {
        try {
            // Start with input data
            Object currentData = input.getData();
            DataRecord<?> currentRecord = input;
            
            // Validate input if validators are present
            for (Validator<?> validator : validators) {
                Validator<Object> objValidator = (Validator<Object>) validator;
                ValidationResult validation = objValidator.validate(currentData);
                if (!validation.isValid()) {
                    return Result.failure("Validation failed: " + String.join(", ", validation.getErrors()));
                }
            }
            
            // Process through each processor
            for (DataProcessor<?, ?> processor : processors) {
                DataProcessor<Object, Object> objProcessor = (DataProcessor<Object, Object>) processor;
                Result<Object> stepResult = objProcessor.process(currentData);
                
                if (stepResult.isFailure()) {
                    return Result.failure("Pipeline step failed: " + stepResult.getErrorMessage(), 
                        stepResult.getException());
                }
                
                currentData = stepResult.getData();
                // Update record with new data
                currentRecord = new DataRecord<>(currentRecord.getId(), currentData, currentRecord.getMetadata())
                    .withMetadata("lastProcessedAt", LocalDateTime.now())
                    .withMetadata("processorCount", processors.size());
            }
            
            // Return final result
            return Result.success((DataRecord<U>) currentRecord);
            
        } catch (Exception e) {
            return Result.failure("Pipeline execution failed: " + e.getMessage(), e);
        }
    }
    
    /**
     * Process multiple records in parallel
     */
    public CompletableFuture<List<Result<DataRecord<U>>>> processAll(List<DataRecord<T>> inputs) {
        List<CompletableFuture<Result<DataRecord<U>>>> futures = inputs.stream()
            .map(input -> CompletableFuture.supplyAsync(() -> process(input), executorService))
            .collect(Collectors.toList());
        
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
            .thenApply(v -> futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()));
    }
    
    /**
     * Generic builder pattern for pipeline construction
     */
    public static class Builder<T, U> {
        private List<DataProcessor<?, ?>> processors = new ArrayList<>();
        private List<Validator<?>> validators = new ArrayList<>();
        private Map<String, Object> configuration = new HashMap<>();
        private ExecutorService executorService;
        
        public <V> Builder<T, V> addProcessor(DataProcessor<U, V> processor) {
            processors.add(processor);
            return (Builder<T, V>) this;
        }
        
        public Builder<T, U> addValidator(Validator<T> validator) {
            validators.add(validator);
            return this;
        }
        
        public Builder<T, U> configure(String key, Object value) {
            configuration.put(key, value);
            return this;
        }
        
        public Builder<T, U> withExecutor(ExecutorService executorService) {
            this.executorService = executorService;
            return this;
        }
        
        public DataPipeline<T, U> build() {
            return new DataPipeline<>(this);
        }
    }
    
    public static <T> Builder<T, T> create() {
        return new Builder<>();
    }
}

// ========================================
// SPECIALIZED PROCESSORS AND VALIDATORS
// ========================================

/**
 * Collection of customer-specific processors demonstrating generic specialization
 */
class CustomerProcessors {
    
    public static DataProcessor<Customer, Customer> normalizeEmail() {
        return DataProcessor.of(customer -> new Customer(
            customer.getId(),
            customer.getName(),
            customer.getEmail().toLowerCase().trim(),
            customer.getAge(),
            customer.getTotalPurchases()
        ));
    }
    
    public static DataProcessor<Customer, Customer> categorizeByAge() {
        return input -> {
            try {
                Customer customer = input;
                String category = customer.getAge() < 25 ? "Young" :
                                customer.getAge() < 50 ? "Middle" : "Senior";
                
                // Create new customer with age category in metadata-like toString
                Customer categorized = new Customer(
                    customer.getId(),
                    customer.getName() + " (" + category + ")",
                    customer.getEmail(),
                    customer.getAge(),
                    customer.getTotalPurchases()
                );
                
                return Result.success(categorized);
            } catch (Exception e) {
                return Result.failure("Age categorization failed", e);
            }
        };
    }
    
    public static DataProcessor<List<Sale>, Customer> calculateTotalPurchases(String customerId) {
        return sales -> {
            try {
                double total = sales.stream()
                    .filter(sale -> sale.getCustomerId().equals(customerId))
                    .mapToDouble(Sale::getAmount)
                    .sum();
                
                // This is a simplified example - normally would fetch customer data
                Customer enriched = new Customer(customerId, "Enriched Customer", 
                    "customer@example.com", 35, total);
                
                return Result.success(enriched);
            } catch (Exception e) {
                return Result.failure("Purchase calculation failed", e);
            }
        };
    }
}

/**
 * Collection of validators demonstrating generic validation patterns
 */
class CustomerValidators {
    
    public static Validator<Customer> validateEmail() {
        return customer -> {
            String email = customer.getEmail();
            if (email == null || email.trim().isEmpty()) {
                return ValidationResult.invalid("Email is required");
            }
            if (!email.contains("@") || !email.contains(".")) {
                return ValidationResult.invalid("Email format is invalid");
            }
            return ValidationResult.valid();
        };
    }
    
    public static Validator<Customer> validateAge() {
        return customer -> {
            int age = customer.getAge();
            if (age < 0 || age > 150) {
                return ValidationResult.invalid("Age must be between 0 and 150");
            }
            return ValidationResult.valid();
        };
    }
    
    public static Validator<Customer> validatePurchases() {
        return customer -> {
            double purchases = customer.getTotalPurchases();
            if (purchases < 0) {
                return ValidationResult.invalid("Total purchases cannot be negative");
            }
            return ValidationResult.valid();
        };
    }
}

// ========================================
// ANALYTICS ENGINE
// ========================================

/**
 * Generic analytics engine demonstrating advanced generic patterns
 * with bounded types and functional programming integration
 */
class AnalyticsEngine {
    
    /**
     * Generic aggregation function interface
     */
    @FunctionalInterface
    interface Aggregator<T, R> {
        R aggregate(Stream<T> data);
    }
    
    /**
     * Generic grouping analytics
     */
    public static <T, K> AnalyticsResult groupBy(
            List<T> data,
            Function<T, K> keyExtractor,
            Map<String, Aggregator<T, ?>> aggregators) {
        
        AnalyticsResult result = new AnalyticsResult();
        
        try {
            Map<K, List<T>> groups = data.stream()
                .collect(Collectors.groupingBy(keyExtractor));
            
            // Apply each aggregator to each group
            for (Map.Entry<K, List<T>> group : groups.entrySet()) {
                K key = group.getKey();
                List<T> values = group.getValue();
                
                for (Map.Entry<String, Aggregator<T, ?>> aggEntry : aggregators.entrySet()) {
                    String metricName = key + "_" + aggEntry.getKey();
                    Object metricValue = aggEntry.getValue().aggregate(values.stream());
                    result.addMetric(metricName, metricValue);
                }
            }
            
            return result;
            
        } catch (Exception e) {
            return result.addMetric("error", "Analytics failed: " + e.getMessage());
        }
    }
    
    /**
     * Generic statistical analysis
     */
    public static <T extends Number> AnalyticsResult calculateStatistics(
            List<T> numbers, String metricPrefix) {
        
        AnalyticsResult result = new AnalyticsResult();
        
        if (numbers.isEmpty()) {
            return result.addMetric(metricPrefix + "_error", "No data provided");
        }
        
        try {
            List<Double> doubles = numbers.stream()
                .map(Number::doubleValue)
                .collect(Collectors.toList());
            
            double sum = doubles.stream().mapToDouble(Double::doubleValue).sum();
            double mean = sum / doubles.size();
            double min = doubles.stream().mapToDouble(Double::doubleValue).min().orElse(0);
            double max = doubles.stream().mapToDouble(Double::doubleValue).max().orElse(0);
            
            // Standard deviation calculation
            double variance = doubles.stream()
                .mapToDouble(d -> Math.pow(d - mean, 2))
                .average()
                .orElse(0);
            double stdDev = Math.sqrt(variance);
            
            return result
                .addMetric(metricPrefix + "_count", doubles.size())
                .addMetric(metricPrefix + "_sum", sum)
                .addMetric(metricPrefix + "_mean", mean)
                .addMetric(metricPrefix + "_min", min)
                .addMetric(metricPrefix + "_max", max)
                .addMetric(metricPrefix + "_stddev", stdDev);
                
        } catch (Exception e) {
            return result.addMetric(metricPrefix + "_error", "Statistics calculation failed: " + e.getMessage());
        }
    }
    
    /**
     * Generic trend analysis
     */
    public static <T> AnalyticsResult analyzeTrend(
            List<T> data,
            Function<T, LocalDateTime> timeExtractor,
            Function<T, Double> valueExtractor) {
        
        AnalyticsResult result = new AnalyticsResult();
        
        try {
            List<Map.Entry<LocalDateTime, Double>> timeSeries = data.stream()
                .map(item -> new AbstractMap.SimpleEntry<>(
                    timeExtractor.apply(item),
                    valueExtractor.apply(item)))
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toList());
            
            if (timeSeries.size() < 2) {
                return result.addMetric("trend_error", "Insufficient data for trend analysis");
            }
            
            // Simple linear trend calculation
            double firstValue = timeSeries.get(0).getValue();
            double lastValue = timeSeries.get(timeSeries.size() - 1).getValue();
            double trend = lastValue - firstValue;
            double trendPercent = (trend / firstValue) * 100;
            
            String trendDirection = trend > 0 ? "Increasing" :
                                  trend < 0 ? "Decreasing" : "Stable";
            
            return result
                .addMetric("trend_direction", trendDirection)
                .addMetric("trend_absolute", trend)
                .addMetric("trend_percent", trendPercent)
                .addMetric("data_points", timeSeries.size());
                
        } catch (Exception e) {
            return result.addMetric("trend_error", "Trend analysis failed: " + e.getMessage());
        }
    }
}

// ========================================
// DEMONSTRATION CLASS
// ========================================

/**
 * Main demonstration class showcasing all generic patterns in action
 */
public class _PracticalExample {
    
    public static void main(String[] args) {
        System.out.println("🏛️ WELCOME TO THE MYSTIC DATA PROCESSING PIPELINE 🏛️");
        System.out.println("Witness the power of Generics in action!\n");
        
        // Generate sample data
        List<Customer> customers = generateCustomers();
        List<Sale> sales = generateSales();
        
        // Demonstrate Result container
        demonstrateResultContainer();
        
        // Demonstrate DataRecord
        demonstrateDataRecord();
        
        // Demonstrate generic pipeline
        demonstrateDataPipeline(customers);
        
        // Demonstrate parallel processing
        demonstrateParallelProcessing(customers);
        
        // Demonstrate analytics engine
        demonstrateAnalyticsEngine(customers, sales);
        
        // Demonstrate generic algorithms
        demonstrateGenericAlgorithms();
        
        System.out.println("🎊 GENERIC MASTERY DEMONSTRATION COMPLETE! 🎊");
    }
    
    private static List<Customer> generateCustomers() {
        return Arrays.asList(
            new Customer("C001", "Alice Johnson", "ALICE@EXAMPLE.COM", 28, 1250.00),
            new Customer("C002", "Bob Smith", "bob@test.com", 35, 2100.50),
            new Customer("C003", "Carol Davis", "carol.davis@email.com", 42, 850.75),
            new Customer("C004", "David Wilson", "david@company.org", 29, 3200.00),
            new Customer("C005", "Emma Brown", "emma.brown@service.net", 55, 1800.25)
        );
    }
    
    private static List<Sale> generateSales() {
        return Arrays.asList(
            new Sale("S001", "C001", 250.00, "Laptop"),
            new Sale("S002", "C002", 150.50, "Mouse"),
            new Sale("S003", "C001", 1000.00, "Monitor"),
            new Sale("S004", "C003", 300.75, "Keyboard"),
            new Sale("S005", "C004", 1200.00, "Tablet"),
            new Sale("S006", "C002", 1950.00, "Desktop"),
            new Sale("S007", "C005", 500.25, "Printer")
        );
    }
    
    private static void demonstrateResultContainer() {
        System.out.println("📦 RESULT CONTAINER DEMONSTRATION");
        System.out.println("═════════════════════════════════");
        
        // Success case
        Result<String> success = Result.success("Operation completed successfully");
        System.out.println("Success result: " + success);
        System.out.println("Data: " + success.getData());
        System.out.println("Is successful: " + success.isSuccess());
        
        // Failure case
        Result<String> failure = Result.failure("Something went wrong");
        System.out.println("\nFailure result: " + failure);
        System.out.println("Error message: " + failure.getErrorMessage());
        System.out.println("Is failure: " + failure.isFailure());
        
        // Functional operations
        Result<Integer> numberResult = Result.success("123")
            .map(Integer::parseInt)
            .map(n -> n * 2);
        System.out.println("\nMapped result: " + numberResult);
        System.out.println("Final value: " + numberResult.orElse(0));
        
        // Error handling in chain
        Result<Integer> errorResult = Result.success("not-a-number")
            .map(Integer::parseInt)
            .map(n -> n * 2);
        System.out.println("\nError in chain: " + errorResult);
        System.out.println("Fallback value: " + errorResult.orElse(-1));
        
        System.out.println();
    }
    
    private static void demonstrateDataRecord() {
        System.out.println("📋 DATA RECORD DEMONSTRATION");
        System.out.println("══════════════════════════════");
        
        // Create data record with metadata
        DataRecord<String> record = new DataRecord<>("REC001", "Important Data")
            .withMetadata("source", "user_input")
            .withMetadata("priority", "high")
            .withMetadata("version", 1.0);
        
        System.out.println("Original record: " + record);
        System.out.println("Source: " + record.getMetadata("source", String.class).orElse("unknown"));
        System.out.println("Priority: " + record.getMetadata("priority", String.class).orElse("normal"));
        System.out.println("Version: " + record.getMetadata("version", Double.class).orElse(0.0));
        
        // Transform data
        DataRecord<Integer> transformed = record.transform(String::length);
        System.out.println("\nTransformed record: " + transformed);
        System.out.println("Preserved metadata: " + transformed.getMetadata().keySet());
        
        System.out.println();
    }
    
    private static void demonstrateDataPipeline(List<Customer> customers) {
        System.out.println("⚙️ DATA PIPELINE DEMONSTRATION");
        System.out.println("════════════════════════════════");
        
        // Build a customer processing pipeline
        DataPipeline<Customer, Customer> pipeline = DataPipeline.<Customer>create()
            .addValidator(CustomerValidators.validateEmail())
            .addValidator(CustomerValidators.validateAge())
            .addValidator(CustomerValidators.validatePurchases())
            .addProcessor(CustomerProcessors.normalizeEmail())
            .addProcessor(CustomerProcessors.categorizeByAge())
            .configure("strict_validation", true)
            .build();
        
        // Process each customer
        for (Customer customer : customers.subList(0, 3)) { // Process first 3
            DataRecord<Customer> input = new DataRecord<>(customer.getId(), customer);
            Result<DataRecord<Customer>> result = pipeline.process(input);
            
            System.out.println("Processing " + customer.getName() + ":");
            if (result.isSuccess()) {
                DataRecord<Customer> processed = result.getData();
                System.out.println("  ✅ Success: " + processed.getData());
                System.out.println("  📊 Metadata: " + processed.getMetadata().keySet());
            } else {
                System.out.println("  ❌ Failed: " + result.getErrorMessage());
            }
            System.out.println();
        }
    }
    
    private static void demonstrateParallelProcessing(List<Customer> customers) {
        System.out.println("🚀 PARALLEL PROCESSING DEMONSTRATION");
        System.out.println("═══════════════════════════════════");
        
        // Create pipeline for parallel processing
        DataPipeline<Customer, Customer> pipeline = DataPipeline.<Customer>create()
            .addProcessor(CustomerProcessors.normalizeEmail())
            .addProcessor(CustomerProcessors.categorizeByAge())
            .build();
        
        // Convert customers to data records
        List<DataRecord<Customer>> records = customers.stream()
            .map(c -> new DataRecord<>(c.getId(), c))
            .collect(Collectors.toList());
        
        System.out.println("Processing " + records.size() + " customers in parallel...");
        
        long startTime = System.currentTimeMillis();
        
        // Process all records in parallel
        CompletableFuture<List<Result<DataRecord<Customer>>>> future = pipeline.processAll(records);
        
        try {
            List<Result<DataRecord<Customer>>> results = future.get(5, TimeUnit.SECONDS);
            long endTime = System.currentTimeMillis();
            
            System.out.println("⏱️ Processing completed in " + (endTime - startTime) + "ms");
            
            long successCount = results.stream().mapToLong(r -> r.isSuccess() ? 1 : 0).sum();
            long failureCount = results.size() - successCount;
            
            System.out.println("✅ Successful: " + successCount);
            System.out.println("❌ Failed: " + failureCount);
            
            // Show successful results
            results.stream()
                .filter(Result::isSuccess)
                .map(Result::getData)
                .forEach(record -> System.out.println("  → " + record.getData()));
            
        } catch (Exception e) {
            System.out.println("❌ Parallel processing failed: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    private static void demonstrateAnalyticsEngine(List<Customer> customers, List<Sale> sales) {
        System.out.println("📈 ANALYTICS ENGINE DEMONSTRATION");
        System.out.println("═════════════════════════════════");
        
        // Customer analytics by age group
        Map<String, AnalyticsEngine.Aggregator<Customer, ?>> customerAggregators = new HashMap<>();
        customerAggregators.put("count", stream -> stream.count());
        customerAggregators.put("avgPurchases", stream -> 
            stream.mapToDouble(Customer::getTotalPurchases).average().orElse(0));
        customerAggregators.put("totalPurchases", stream -> 
            stream.mapToDouble(Customer::getTotalPurchases).sum());
        
        Function<Customer, String> ageGrouper = customer -> 
            customer.getAge() < 30 ? "Young" :
            customer.getAge() < 50 ? "Middle" : "Senior";
        
        AnalyticsResult customerAnalytics = AnalyticsEngine.groupBy(
            customers, ageGrouper, customerAggregators);
        
        System.out.println("👥 Customer Analytics by Age Group:");
        customerAnalytics.getAllMetrics().forEach((key, value) ->
            System.out.println("  " + key + ": " + value));
        
        // Statistical analysis of purchase amounts
        List<Double> purchaseAmounts = customers.stream()
            .map(Customer::getTotalPurchases)
            .collect(Collectors.toList());
        
        AnalyticsResult stats = AnalyticsEngine.calculateStatistics(purchaseAmounts, "purchases");
        System.out.println("\n💰 Purchase Amount Statistics:");
        stats.getAllMetrics().forEach((key, value) -> {
            if (value instanceof Double) {
                System.out.printf("  %s: %.2f%n", key, (Double) value);
            } else {
                System.out.println("  " + key + ": " + value);
            }
        });
        
        // Sales trend analysis
        AnalyticsResult trendAnalysis = AnalyticsEngine.analyzeTrend(
            sales,
            Sale::getTimestamp,
            Sale::getAmount
        );
        System.out.println("\n📊 Sales Trend Analysis:");
        trendAnalysis.getAllMetrics().forEach((key, value) -> {
            if (value instanceof Double) {
                System.out.printf("  %s: %.2f%n", key, (Double) value);
            } else {
                System.out.println("  " + key + ": " + value);
            }
        });
        
        System.out.println();
    }
    
    private static void demonstrateGenericAlgorithms() {
        System.out.println("🧮 GENERIC ALGORITHMS DEMONSTRATION");
        System.out.println("══════════════════════════════════");
        
        // Generic utility methods
        System.out.println("🔀 Generic Utility Methods:");
        
        // Swap algorithm
        String[] words = {"first", "second", "third"};
        System.out.println("Before swap: " + Arrays.toString(words));
        swap(words, 0, 2);
        System.out.println("After swap: " + Arrays.toString(words));
        
        // Find maximum
        Integer maxNumber = findMax(Arrays.asList(10, 25, 5, 30, 15));
        String maxString = findMax(Arrays.asList("apple", "zebra", "banana"));
        System.out.println("Max number: " + maxNumber);
        System.out.println("Max string: " + maxString);
        
        // Filter with predicate
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = filter(numbers, n -> n % 2 == 0);
        System.out.println("Even numbers: " + evenNumbers);
        
        // Transform with mapper
        List<String> lengths = transform(Arrays.asList("hello", "world", "generics"), String::length)
            .stream().map(Object::toString).collect(Collectors.toList());
        System.out.println("String lengths: " + lengths);
        
        System.out.println();
    }
    
    // Generic utility methods
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    public static <T extends Comparable<T>> T findMax(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be empty");
        }
        
        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }
    
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }
    
    public static <T, U> List<U> transform(List<T> list, Function<T, U> mapper) {
        return list.stream().map(mapper).collect(Collectors.toList());
    }
}

/*
 * ════════════════════════════════════════════════════════════════════════════
 * KEY GENERIC PATTERNS DEMONSTRATED:
 * ════════════════════════════════════════════════════════════════════════════
 * 
 * 1. CONTAINER PATTERNS:
 *    ✅ Result<T> - Type-safe success/failure containers
 *    ✅ DataRecord<T> - Generic data records with metadata
 *    ✅ Optional-style functional operations
 * 
 * 2. INTERFACE DESIGN:
 *    ✅ DataProcessor<T,U> - Generic processing contracts
 *    ✅ Validator<T> - Type-safe validation interfaces
 *    ✅ Functional composition and chaining
 * 
 * 3. PIPELINE ARCHITECTURE:
 *    ✅ Generic processing pipelines with error handling
 *    ✅ Builder pattern with type safety
 *    ✅ Parallel processing with CompletableFuture
 * 
 * 4. ADVANCED PATTERNS:
 *    ✅ Bounded type parameters for analytics
 *    ✅ Wildcard usage in practical scenarios
 *    ✅ Generic algorithms and utilities
 * 
 * 5. REAL-WORLD INTEGRATION:
 *    ✅ Business domain modeling
 *    ✅ Error handling and validation
 *    ✅ Performance monitoring and parallel processing
 * 
 * 6. TYPE SAFETY BENEFITS:
 *    ✅ Compile-time error detection
 *    ✅ Elimination of casting
 *    ✅ Self-documenting code through types
 * 
 * This example demonstrates how generics enable building robust,
 * type-safe, and reusable systems that can handle complex business
 * logic while maintaining flexibility and performance!
 */
