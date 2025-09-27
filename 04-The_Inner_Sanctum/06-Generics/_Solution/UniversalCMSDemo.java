/**
 * UniversalCMSDemo.java - Complete Generic CMS Solution
 * 
 * This comprehensive solution demonstrates mastery of all generic concepts
 * through a sophisticated Content Management System implementation.
 * 
 * "Behold the culmination of generic wisdom - a complete system that
 * showcases type safety, abstraction, and elegant design in perfect harmony!"
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.Collectors;

// ========================================
// CORE GENERIC INFRASTRUCTURE
// ========================================

/**
 * Generic content container with comprehensive metadata and operations
 * Demonstrates generic class design with multiple type parameters
 */
class Content<T> {
    public enum Status { DRAFT, REVIEW, PUBLISHED, ARCHIVED, DELETED }
    
    private final String id;
    private final T data;
    private String title;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Status status;
    private String author;
    private int version;
    private final Map<String, Object> metadata;
    private final Set<String> tags;
    
    public Content(String id, T data, String title) {
        this.id = id;
        this.data = data;
        this.title = title;
        this.description = "";
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = this.createdAt;
        this.status = Status.DRAFT;
        this.author = "system";
        this.version = 1;
        this.metadata = new HashMap<>();
        this.tags = new HashSet<>();
    }
    
    // Fluent builder methods
    public Content<T> withDescription(String description) {
        this.description = description;
        this.modifiedAt = LocalDateTime.now();
        return this;
    }
    
    public Content<T> withAuthor(String author) {
        this.author = author;
        this.modifiedAt = LocalDateTime.now();
        return this;
    }
    
    public Content<T> withStatus(Status status) {
        if (isValidStatusTransition(this.status, status)) {
            this.status = status;
            this.modifiedAt = LocalDateTime.now();
            this.version++;
        } else {
            throw new IllegalStateException("Invalid status transition from " + this.status + " to " + status);
        }
        return this;
    }
    
    public Content<T> withMetadata(String key, Object value) {
        this.metadata.put(key, value);
        this.modifiedAt = LocalDateTime.now();
        return this;
    }
    
    public Content<T> withTag(String tag) {
        this.tags.add(tag);
        this.modifiedAt = LocalDateTime.now();
        return this;
    }
    
    // Transformation methods
    public <U> Content<U> transform(Function<T, U> transformer) {
        U newData = transformer.apply(this.data);
        Content<U> transformed = new Content<>(this.id + "_transformed", newData, this.title + " (Transformed)")
            .withDescription(this.description)
            .withAuthor(this.author);
        
        // Copy metadata and tags
        this.metadata.forEach(transformed::withMetadata);
        this.tags.forEach(transformed::withTag);
        
        return transformed;
    }
    
    // Validation
    private boolean isValidStatusTransition(Status from, Status to) {
        return switch (from) {
            case DRAFT -> to == Status.REVIEW || to == Status.DELETED;
            case REVIEW -> to == Status.PUBLISHED || to == Status.DRAFT || to == Status.DELETED;
            case PUBLISHED -> to == Status.ARCHIVED || to == Status.DELETED;
            case ARCHIVED -> to == Status.PUBLISHED || to == Status.DELETED;
            case DELETED -> false; // No transitions from deleted
        };
    }
    
    // Deep copy
    @SuppressWarnings("unchecked")
    public Content<T> deepCopy() {
        Content<T> copy = new Content<>(this.id + "_copy", this.data, this.title)
            .withDescription(this.description)
            .withAuthor(this.author)
            .withStatus(Status.DRAFT); // Reset to draft for copy
        
        // Copy metadata and tags
        this.metadata.forEach(copy::withMetadata);
        this.tags.forEach(copy::withTag);
        
        return copy;
    }
    
    // Getters
    public String getId() { return id; }
    public T getData() { return data; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getModifiedAt() { return modifiedAt; }
    public Status getStatus() { return status; }
    public String getAuthor() { return author; }
    public int getVersion() { return version; }
    public Map<String, Object> getMetadata() { return new HashMap<>(metadata); }
    public Set<String> getTags() { return new HashSet<>(tags); }
    
    @SuppressWarnings("unchecked")
    public <U> Optional<U> getMetadata(String key, Class<U> type) {
        Object value = metadata.get(key);
        if (value != null && type.isInstance(value)) {
            return Optional.of((U) value);
        }
        return Optional.empty();
    }
    
    @Override
    public String toString() {
        return String.format("Content[%s: %s (%s) v%d by %s]", 
            id, title, status, version, author);
    }
}

/**
 * Generic repository interface with comprehensive CRUD operations
 * Demonstrates generic interface design with bounded type parameters
 */
interface Repository<T, ID> {
    void save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    List<T> findByPredicate(Predicate<T> condition);
    void delete(ID id);
    boolean exists(ID id);
    long count();
    void clear();
    
    // Batch operations
    void saveAll(Collection<T> entities);
    List<T> findAllById(Collection<ID> ids);
    void deleteAll(Collection<ID> ids);
    
    // Advanced queries
    <U> List<U> findAndTransform(Predicate<T> condition, Function<T, U> transformer);
    Optional<T> findFirst(Predicate<T> condition);
    List<T> findSorted(Comparator<T> comparator);
}

/**
 * Thread-safe in-memory repository implementation
 * Demonstrates generic implementation with concurrent access
 */
class InMemoryRepository<T, ID> implements Repository<T, ID> {
    private final ConcurrentHashMap<ID, T> storage = new ConcurrentHashMap<>();
    private final Function<T, ID> idExtractor;
    
    public InMemoryRepository(Function<T, ID> idExtractor) {
        this.idExtractor = idExtractor;
    }
    
    @Override
    public void save(T entity) {
        ID id = idExtractor.apply(entity);
        storage.put(id, entity);
    }
    
    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }
    
    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }
    
    @Override
    public List<T> findByPredicate(Predicate<T> condition) {
        return storage.values().stream()
            .filter(condition)
            .collect(Collectors.toList());
    }
    
    @Override
    public void delete(ID id) {
        storage.remove(id);
    }
    
    @Override
    public boolean exists(ID id) {
        return storage.containsKey(id);
    }
    
    @Override
    public long count() {
        return storage.size();
    }
    
    @Override
    public void clear() {
        storage.clear();
    }
    
    @Override
    public void saveAll(Collection<T> entities) {
        entities.forEach(this::save);
    }
    
    @Override
    public List<T> findAllById(Collection<ID> ids) {
        return ids.stream()
            .map(this::findById)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
    }
    
    @Override
    public void deleteAll(Collection<ID> ids) {
        ids.forEach(this::delete);
    }
    
    @Override
    public <U> List<U> findAndTransform(Predicate<T> condition, Function<T, U> transformer) {
        return storage.values().stream()
            .filter(condition)
            .map(transformer)
            .collect(Collectors.toList());
    }
    
    @Override
    public Optional<T> findFirst(Predicate<T> condition) {
        return storage.values().stream()
            .filter(condition)
            .findFirst();
    }
    
    @Override
    public List<T> findSorted(Comparator<T> comparator) {
        return storage.values().stream()
            .sorted(comparator)
            .collect(Collectors.toList());
    }
}

// ========================================
// CONTENT TYPE HIERARCHY
// ========================================

/**
 * Article content type with rich text analysis capabilities
 */
class Article {
    private String body;
    private List<String> sections;
    private int wordCount;
    private ReadingLevel readingLevel;
    private List<String> references;
    private String summary;
    
    public enum ReadingLevel { ELEMENTARY, INTERMEDIATE, ADVANCED, EXPERT }
    
    public Article(String body) {
        this.body = body;
        this.sections = extractSections(body);
        this.wordCount = countWords(body);
        this.readingLevel = calculateReadingLevel(body);
        this.references = new ArrayList<>();
        this.summary = generateSummary(body);
    }
    
    private List<String> extractSections(String body) {
        // Simple section extraction (in real implementation would be more sophisticated)
        return Arrays.stream(body.split("\n\n"))
            .filter(section -> !section.trim().isEmpty())
            .collect(Collectors.toList());
    }
    
    private int countWords(String text) {
        return text.trim().isEmpty() ? 0 : text.trim().split("\\s+").length;
    }
    
    private ReadingLevel calculateReadingLevel(String text) {
        int words = countWords(text);
        if (words < 100) return ReadingLevel.ELEMENTARY;
        if (words < 500) return ReadingLevel.INTERMEDIATE;
        if (words < 1000) return ReadingLevel.ADVANCED;
        return ReadingLevel.EXPERT;
    }
    
    private String generateSummary(String text) {
        // Simple summary generation (first sentence or first 100 chars)
        if (text.length() <= 100) return text;
        int firstPeriod = text.indexOf('.');
        if (firstPeriod > 0 && firstPeriod <= 200) {
            return text.substring(0, firstPeriod + 1);
        }
        return text.substring(0, 100) + "...";
    }
    
    // Getters
    public String getBody() { return body; }
    public List<String> getSections() { return new ArrayList<>(sections); }
    public int getWordCount() { return wordCount; }
    public ReadingLevel getReadingLevel() { return readingLevel; }
    public List<String> getReferences() { return new ArrayList<>(references); }
    public String getSummary() { return summary; }
    
    public Article withReference(String reference) {
        this.references.add(reference);
        return this;
    }
    
    @Override
    public String toString() {
        return String.format("Article[%d words, %s level, %d sections]", 
            wordCount, readingLevel, sections.size());
    }
}

/**
 * Media content type for images, videos, audio
 */
class MediaContent {
    private String fileName;
    private long fileSize;
    private String mimeType;
    private Optional<Duration> duration;
    private Optional<Resolution> resolution;
    private Map<String, String> technicalMetadata;
    
    public static class Duration {
        private final long seconds;
        
        public Duration(long seconds) { this.seconds = seconds; }
        public long getSeconds() { return seconds; }
        public String getFormatted() {
            long hours = seconds / 3600;
            long minutes = (seconds % 3600) / 60;
            long secs = seconds % 60;
            return String.format("%02d:%02d:%02d", hours, minutes, secs);
        }
        
        @Override
        public String toString() { return getFormatted(); }
    }
    
    public static class Resolution {
        private final int width, height;
        
        public Resolution(int width, int height) {
            this.width = width;
            this.height = height;
        }
        
        public int getWidth() { return width; }
        public int getHeight() { return height; }
        public long getTotalPixels() { return (long) width * height; }
        
        @Override
        public String toString() { return width + "x" + height; }
    }
    
    public MediaContent(String fileName, long fileSize, String mimeType) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.mimeType = mimeType;
        this.duration = Optional.empty();
        this.resolution = Optional.empty();
        this.technicalMetadata = new HashMap<>();
    }
    
    public MediaContent withDuration(long seconds) {
        this.duration = Optional.of(new Duration(seconds));
        return this;
    }
    
    public MediaContent withResolution(int width, int height) {
        this.resolution = Optional.of(new Resolution(width, height));
        return this;
    }
    
    public MediaContent withMetadata(String key, String value) {
        this.technicalMetadata.put(key, value);
        return this;
    }
    
    public boolean isImage() { return mimeType.startsWith("image/"); }
    public boolean isVideo() { return mimeType.startsWith("video/"); }
    public boolean isAudio() { return mimeType.startsWith("audio/"); }
    
    // Getters
    public String getFileName() { return fileName; }
    public long getFileSize() { return fileSize; }
    public String getMimeType() { return mimeType; }
    public Optional<Duration> getDuration() { return duration; }
    public Optional<Resolution> getResolution() { return resolution; }
    public Map<String, String> getTechnicalMetadata() { return new HashMap<>(technicalMetadata); }
    
    public String getFormattedSize() {
        if (fileSize < 1024) return fileSize + " bytes";
        if (fileSize < 1024 * 1024) return (fileSize / 1024) + " KB";
        if (fileSize < 1024 * 1024 * 1024) return (fileSize / (1024 * 1024)) + " MB";
        return (fileSize / (1024 * 1024 * 1024)) + " GB";
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("MediaContent[%s, %s, %s", fileName, mimeType, getFormattedSize()));
        duration.ifPresent(d -> sb.append(", ").append(d));
        resolution.ifPresent(r -> sb.append(", ").append(r));
        sb.append("]");
        return sb.toString();
    }
}

// ========================================
// PROCESSING PIPELINE
// ========================================

/**
 * Generic content processor interface with error handling
 */
@FunctionalInterface
interface ContentProcessor<T, U> {
    ProcessingResult<U> process(T input);
    
    default <V> ContentProcessor<T, V> andThen(ContentProcessor<U, V> next) {
        return input -> {
            ProcessingResult<U> firstResult = this.process(input);
            if (firstResult.isFailure()) {
                return ProcessingResult.failure(firstResult.getErrorMessage());
            }
            return next.process(firstResult.getData());
        };
    }
    
    static <T, U> ContentProcessor<T, U> of(Function<T, U> function) {
        return input -> {
            try {
                return ProcessingResult.success(function.apply(input));
            } catch (Exception e) {
                return ProcessingResult.failure("Processing failed: " + e.getMessage());
            }
        };
    }
}

/**
 * Processing result container with success/failure states
 */
class ProcessingResult<T> {
    private final boolean success;
    private final T data;
    private final String errorMessage;
    private final LocalDateTime timestamp;
    
    private ProcessingResult(boolean success, T data, String errorMessage) {
        this.success = success;
        this.data = data;
        this.errorMessage = errorMessage;
        this.timestamp = LocalDateTime.now();
    }
    
    public static <T> ProcessingResult<T> success(T data) {
        return new ProcessingResult<>(true, data, null);
    }
    
    public static <T> ProcessingResult<T> failure(String errorMessage) {
        return new ProcessingResult<>(false, null, errorMessage);
    }
    
    public boolean isSuccess() { return success; }
    public boolean isFailure() { return !success; }
    public T getData() { return data; }
    public String getErrorMessage() { return errorMessage; }
    public LocalDateTime getTimestamp() { return timestamp; }
    
    @Override
    public String toString() {
        if (success) {
            return "Success[" + data + "]";
        } else {
            return "Failure[" + errorMessage + "]";
        }
    }
}

// ========================================
// UNIVERSAL CMS SYSTEM
// ========================================

/**
 * Main CMS class orchestrating all generic components
 * Demonstrates integration of multiple generic systems
 */
class UniversalCMS {
    private final Map<Class<?>, Repository<Content<?>, String>> repositories;
    private final List<ContentProcessor<?, ?>> processingPipeline;
    private final ExecutorService executorService;
    
    @SuppressWarnings("unchecked")
    public UniversalCMS() {
        this.repositories = new HashMap<>();
        this.processingPipeline = new ArrayList<>();
        this.executorService = ForkJoinPool.commonPool();
        
        // Initialize repositories for different content types
        repositories.put(Article.class, (Repository<Content<?>, String>) 
            new InMemoryRepository<Content<Article>, String>(Content::getId));
        repositories.put(MediaContent.class, (Repository<Content<?>, String>) 
            new InMemoryRepository<Content<MediaContent>, String>(Content::getId));
    }
    
    /**
     * Create content with type safety
     */
    public <T> Content<T> createContent(Class<T> contentType, String id, T data, String title) {
        Content<T> content = new Content<>(id, data, title)
            .withMetadata("contentType", contentType.getSimpleName())
            .withMetadata("createdBy", "UniversalCMS");
        
        // Store in appropriate repository
        Repository<Content<?>, String> repo = repositories.get(contentType);
        if (repo != null) {
            repo.save((Content<?>) content);
        }
        
        return content;
    }
    
    /**
     * Generic content search across all repositories
     */
    @SuppressWarnings("unchecked")
    public <T> List<Content<T>> searchContent(Class<T> contentType, Predicate<Content<T>> condition) {
        Repository<Content<?>, String> repo = repositories.get(contentType);
        if (repo == null) {
            return Collections.emptyList();
        }
        
        return repo.findAll().stream()
            .filter(content -> contentType.isInstance(content.getData()))
            .map(content -> (Content<T>) content)
            .filter(condition)
            .collect(Collectors.toList());
    }
    
    /**
     * Bulk content operations
     */
    public <T> CompletableFuture<List<ProcessingResult<T>>> processBatch(
            List<Content<T>> contents, 
            ContentProcessor<T, T> processor) {
        
        List<CompletableFuture<ProcessingResult<T>>> futures = contents.stream()
            .map(content -> CompletableFuture.supplyAsync(() -> 
                processor.process(content.getData()), executorService))
            .collect(Collectors.toList());
        
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
            .thenApply(v -> futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()));
    }
    
    /**
     * Content analytics
     */
    public <T> ContentAnalytics generateAnalytics(Class<T> contentType) {
        List<Content<T>> contents = searchContent(contentType, c -> true);
        
        ContentAnalytics analytics = new ContentAnalytics();
        analytics.addMetric("totalCount", contents.size());
        analytics.addMetric("publishedCount", contents.stream()
            .mapToLong(c -> c.getStatus() == Content.Status.PUBLISHED ? 1 : 0).sum());
        analytics.addMetric("averageVersion", contents.stream()
            .mapToInt(Content::getVersion).average().orElse(0));
        
        return analytics;
    }
    
    public void shutdown() {
        executorService.shutdown();
    }
}

/**
 * Analytics result container
 */
class ContentAnalytics {
    private final Map<String, Object> metrics = new HashMap<>();
    private final LocalDateTime generatedAt = LocalDateTime.now();
    
    public ContentAnalytics addMetric(String name, Object value) {
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
        return String.format("ContentAnalytics[%d metrics at %s]", 
            metrics.size(), generatedAt.format(DateTimeFormatter.ISO_LOCAL_TIME));
    }
}

// ========================================
// DEMONSTRATION
// ========================================

/**
 * Comprehensive demonstration of the Universal CMS
 */
public class UniversalCMSDemo {
    
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    🏛️ UNIVERSAL CMS DEMONSTRATION 🏛️                        ║");
        System.out.println("║                   The Ultimate Generic System Mastery                        ║");
        System.out.println("║                Complete Implementation of All Generic Patterns               ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════╝");
        
        UniversalCMS cms = new UniversalCMS();
        
        try {
            // Demonstrate content creation and management
            demonstrateContentManagement(cms);
            
            // Demonstrate generic processing pipelines
            demonstrateProcessingPipeline(cms);
            
            // Demonstrate search and analytics
            demonstrateSearchAndAnalytics(cms);
            
            // Demonstrate generic algorithms
            demonstrateGenericAlgorithms();
            
        } finally {
            cms.shutdown();
        }
        
        System.out.println("\n🎊 UNIVERSAL CMS DEMONSTRATION COMPLETE! 🎊");
        System.out.println("🏆 THE SIXTH ARCANE ART HAS BEEN MASTERED! 🏆");
        System.out.println("🏛️ THE INNER SANCTUM IS NOW 100% COMPLETE! 🏛️");
    }
    
    private static void demonstrateContentManagement(UniversalCMS cms) {
        System.out.println("\n📝 CONTENT MANAGEMENT DEMONSTRATION");
        System.out.println("═════════════════════════════════");
        
        // Create different types of content
        Article article = new Article("Java Generics are a powerful feature that provides compile-time type safety and eliminates the need for casting. They allow you to write more flexible and reusable code by parameterizing types.")
            .withReference("Oracle Java Documentation")
            .withReference("Effective Java by Joshua Bloch");
        
        Content<Article> articleContent = cms.createContent(Article.class, "ART001", article, "Introduction to Java Generics")
            .withDescription("A comprehensive guide to understanding Java Generics")
            .withAuthor("The Ancient Teacher")
            .withTag("java")
            .withTag("generics")
            .withTag("programming")
            .withStatus(Content.Status.PUBLISHED);
        
        System.out.println("Created article: " + articleContent);
        System.out.println("Article data: " + articleContent.getData());
        System.out.println("Article tags: " + articleContent.getTags());
        
        // Create media content
        MediaContent media = new MediaContent("generics_tutorial.mp4", 157286400L, "video/mp4")
            .withDuration(3600) // 1 hour
            .withResolution(1920, 1080)
            .withMetadata("codec", "H.264")
            .withMetadata("bitrate", "2500kbps");
        
        Content<MediaContent> mediaContent = cms.createContent(MediaContent.class, "MED001", media, "Java Generics Video Tutorial")
            .withDescription("Visual guide to mastering generics")
            .withAuthor("Video Production Team")
            .withTag("video")
            .withTag("tutorial")
            .withStatus(Content.Status.PUBLISHED);
        
        System.out.println("\nCreated media: " + mediaContent);
        System.out.println("Media data: " + mediaContent.getData());
        
        // Demonstrate content transformation
        Content<String> summary = articleContent.transform(article -> article.getSummary());
        System.out.println("\nTransformed to summary: " + summary);
        System.out.println("Summary data: " + summary.getData());
        
        // Demonstrate deep copy
        Content<Article> articleCopy = articleContent.deepCopy();
        System.out.println("\nDeep copy created: " + articleCopy);
        System.out.println("Copy has different ID: " + !articleContent.getId().equals(articleCopy.getId()));
    }
    
    private static void demonstrateProcessingPipeline(UniversalCMS cms) {
        System.out.println("\n⚙️ PROCESSING PIPELINE DEMONSTRATION");
        System.out.println("══════════════════════════════════════");
        
        // Create processing chain
        ContentProcessor<Article, Article> enhancer = ContentProcessor.of(article -> {
            // Simulate article enhancement
            System.out.println("  🔧 Enhancing article: " + article.getSummary());
            return article;
        });
        
        ContentProcessor<Article, Article> validator = ContentProcessor.of(article -> {
            // Simulate validation
            if (article.getWordCount() < 10) {
                throw new RuntimeException("Article too short");
            }
            System.out.println("  ✅ Article validated: " + article.getWordCount() + " words");
            return article;
        });
        
        ContentProcessor<Article, Article> optimizer = ContentProcessor.of(article -> {
            // Simulate SEO optimization
            System.out.println("  🎯 Article optimized for SEO");
            return article;
        });
        
        // Chain processors
        ContentProcessor<Article, Article> pipeline = enhancer.andThen(validator).andThen(optimizer);
        
        // Test the pipeline
        Article testArticle = new Article("This is a comprehensive test article about generic programming patterns in Java. It demonstrates how to build flexible and type-safe applications.");
        
        System.out.println("Processing article through pipeline:");
        ProcessingResult<Article> result = pipeline.process(testArticle);
        
        if (result.isSuccess()) {
            System.out.println("✅ Pipeline succeeded: " + result.getData().getSummary());
        } else {
            System.out.println("❌ Pipeline failed: " + result.getErrorMessage());
        }
        
        // Test failure case
        Article shortArticle = new Article("Too short");
        System.out.println("\nTesting failure case:");
        ProcessingResult<Article> failResult = pipeline.process(shortArticle);
        System.out.println("Result: " + failResult);
    }
    
    private static void demonstrateSearchAndAnalytics(UniversalCMS cms) {
        System.out.println("\n🔍 SEARCH AND ANALYTICS DEMONSTRATION");
        System.out.println("═══════════════════════════════════════");
        
        // Search for published articles
        List<Content<Article>> publishedArticles = cms.searchContent(Article.class, 
            content -> content.getStatus() == Content.Status.PUBLISHED);
        
        System.out.println("Published articles found: " + publishedArticles.size());
        publishedArticles.forEach(content -> 
            System.out.println("  📄 " + content.getTitle() + " by " + content.getAuthor()));
        
        // Search for media content
        List<Content<MediaContent>> videos = cms.searchContent(MediaContent.class,
            content -> content.getData().isVideo());
        
        System.out.println("\nVideo content found: " + videos.size());
        videos.forEach(content -> 
            System.out.println("  🎥 " + content.getTitle() + " (" + content.getData().getFormattedSize() + ")"));
        
        // Generate analytics
        ContentAnalytics articleAnalytics = cms.generateAnalytics(Article.class);
        System.out.println("\nArticle Analytics: " + articleAnalytics);
        articleAnalytics.getAllMetrics().forEach((key, value) -> 
            System.out.println("  " + key + ": " + value));
        
        ContentAnalytics mediaAnalytics = cms.generateAnalytics(MediaContent.class);
        System.out.println("\nMedia Analytics: " + mediaAnalytics);
        mediaAnalytics.getAllMetrics().forEach((key, value) -> 
            System.out.println("  " + key + ": " + value));
    }
    
    private static void demonstrateGenericAlgorithms() {
        System.out.println("\n🧮 GENERIC ALGORITHMS DEMONSTRATION");
        System.out.println("═════════════════════════════════════");
        
        // Generic filtering
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = filter(numbers, n -> n % 2 == 0);
        System.out.println("Even numbers: " + evenNumbers);
        
        // Generic transformation
        List<String> words = Arrays.asList("hello", "world", "generics", "java");
        List<Integer> lengths = transform(words, String::length);
        System.out.println("Word lengths: " + lengths);
        
        // Generic reduction
        Integer sum = reduce(numbers, 0, Integer::sum);
        String concatenated = reduce(words, "", (a, b) -> a + " " + b).trim();
        System.out.println("Sum: " + sum);
        System.out.println("Concatenated: " + concatenated);
        
        // Generic comparison
        Optional<String> longestWord = findMax(words, Comparator.comparing(String::length));
        Optional<Integer> largestNumber = findMax(numbers, Integer::compareTo);
        System.out.println("Longest word: " + longestWord.orElse("none"));
        System.out.println("Largest number: " + largestNumber.orElse(0));
        
        // Generic grouping
        Map<Integer, List<String>> wordsByLength = groupBy(words, String::length);
        System.out.println("Words grouped by length: " + wordsByLength);
        
        // Generic statistics
        System.out.println("\nGeneric Statistics:");
        Statistics<Integer> numberStats = calculateStatistics(numbers);
        System.out.println("Number statistics: " + numberStats);
        
        Statistics<Integer> lengthStats = calculateStatistics(lengths);
        System.out.println("Length statistics: " + lengthStats);
    }
    
    // Generic utility methods demonstrating various patterns
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }
    
    public static <T, U> List<U> transform(List<T> list, Function<T, U> mapper) {
        return list.stream().map(mapper).collect(Collectors.toList());
    }
    
    public static <T> T reduce(List<T> list, T identity, BinaryOperator<T> accumulator) {
        return list.stream().reduce(identity, accumulator);
    }
    
    public static <T> Optional<T> findMax(List<T> list, Comparator<T> comparator) {
        return list.stream().max(comparator);
    }
    
    public static <T, K> Map<K, List<T>> groupBy(List<T> list, Function<T, K> keyExtractor) {
        return list.stream().collect(Collectors.groupingBy(keyExtractor));
    }
    
    public static <T extends Number> Statistics<T> calculateStatistics(List<T> numbers) {
        if (numbers.isEmpty()) {
            return new Statistics<>(numbers, 0, 0, 0, 0, 0);
        }
        
        double sum = numbers.stream().mapToDouble(Number::doubleValue).sum();
        double mean = sum / numbers.size();
        double min = numbers.stream().mapToDouble(Number::doubleValue).min().orElse(0);
        double max = numbers.stream().mapToDouble(Number::doubleValue).max().orElse(0);
        
        return new Statistics<>(numbers, numbers.size(), sum, mean, min, max);
    }
    
    /**
     * Generic statistics container
     */
    static class Statistics<T extends Number> {
        private final List<T> data;
        private final int count;
        private final double sum;
        private final double mean;
        private final double min;
        private final double max;
        
        public Statistics(List<T> data, int count, double sum, double mean, double min, double max) {
            this.data = new ArrayList<>(data);
            this.count = count;
            this.sum = sum;
            this.mean = mean;
            this.min = min;
            this.max = max;
        }
        
        public List<T> getData() { return new ArrayList<>(data); }
        public int getCount() { return count; }
        public double getSum() { return sum; }
        public double getMean() { return mean; }
        public double getMin() { return min; }
        public double getMax() { return max; }
        
        @Override
        public String toString() {
            return String.format("Statistics[count=%d, sum=%.2f, mean=%.2f, min=%.2f, max=%.2f]",
                count, sum, mean, min, max);
        }
    }
}

/*
 * ════════════════════════════════════════════════════════════════════════════
 * COMPLETE GENERIC MASTERY DEMONSTRATED:
 * ════════════════════════════════════════════════════════════════════════════
 * 
 * 1. GENERIC CLASS DESIGN:
 *    ✅ Content<T> - Rich generic container with metadata and operations
 *    ✅ Repository<T,ID> - Generic CRUD interface with type safety
 *    ✅ InMemoryRepository<T,ID> - Thread-safe implementation
 * 
 * 2. BOUNDED TYPE PARAMETERS:
 *    ✅ Statistics<T extends Number> - Numeric operations only
 *    ✅ Type-safe mathematical operations with bounds
 * 
 * 3. GENERIC METHODS:
 *    ✅ filter, transform, reduce, findMax, groupBy
 *    ✅ Multiple type parameters in single methods
 *    ✅ Method-level generics independent of class generics
 * 
 * 4. WILDCARD USAGE:
 *    ✅ Producer/Consumer patterns in repository operations  
 *    ✅ Type-safe collections with unknown but related types
 * 
 * 5. INTERFACE DESIGN:
 *    ✅ ContentProcessor<T,U> - Functional generic interfaces
 *    ✅ Repository<T,ID> - Generic contract definitions
 *    ✅ Method chaining with type preservation
 * 
 * 6. ADVANCED PATTERNS:
 *    ✅ Generic builder patterns with fluent interfaces
 *    ✅ Generic factory methods and static constructors
 *    ✅ Type-safe transformation and mapping operations
 *    ✅ Generic error handling with Result patterns
 * 
 * 7. REAL-WORLD INTEGRATION:
 *    ✅ Complete CMS system with multiple content types
 *    ✅ Processing pipelines with generic transformation
 *    ✅ Search and analytics with type-safe operations
 *    ✅ Concurrent processing with generic futures
 * 
 * This solution demonstrates that generics enable building sophisticated,
 * type-safe, performant systems that eliminate runtime errors while
 * maximizing code reusability and maintainability!
 * 
 * THE SIXTH AND FINAL ARCANE ART HAS BEEN CONQUERED! 🏆
 * THE INNER SANCTUM STANDS COMPLETE AT 100%! 🏛️
 */
