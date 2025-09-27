/**
 * The Living Manuscript: Advanced Media Processing System
 * 
 * A comprehensive real-world demonstration of composition vs inheritance
 * through a media processing and streaming system.
 * 
 * This system showcases:
 * - When to use inheritance for genuine "is-a" relationships
 * - When to use composition for "has-a" relationships and flexibility
 * - Strategy pattern for algorithm selection
 * - Decorator pattern for feature enhancement
 * - Template method pattern for shared algorithms
 * - Bridge pattern for separating abstraction from implementation
 * - Real-world trade-offs between the two approaches
 */
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// ═══════════════════════════════════════════════════════════════════════════════════
// INHERITANCE HIERARCHY - MEDIA FILES (TRUE "IS-A" RELATIONSHIPS)
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Abstract base class for all media files - represents genuine "is-a" relationship
 * Every media file has common properties and behaviors
 */
abstract class MediaFile {
    protected final String filename;
    protected final long fileSize;
    protected final String format;
    protected boolean processed;
    
    public MediaFile(String filename, long fileSize, String format) {
        this.filename = filename;
        this.fileSize = fileSize;
        this.format = format;
        this.processed = false;
    }
    
    // Template method - defines common processing algorithm
    public final ProcessingResult process() {
        System.out.println("🔄 Processing " + getMediaType() + ": " + filename);
        
        ProcessingResult result = new ProcessingResult();
        result.startTime = System.currentTimeMillis();
        
        try {
            validateFile();
            loadMetadata();
            ProcessingResult specific = processSpecificContent();  // Polymorphic call
            applyCommonPostProcessing();
            
            result.success = true;
            result.outputFormat = specific.outputFormat;
            result.qualityMetrics = specific.qualityMetrics;
            this.processed = true;
            
        } catch (Exception e) {
            result.success = false;
            result.errorMessage = e.getMessage();
        }
        
        result.endTime = System.currentTimeMillis();
        result.processingTimeMs = result.endTime - result.startTime;
        
        return result;
    }
    
    // Common methods for all media files
    protected void validateFile() {
        if (fileSize <= 0) {
            throw new RuntimeException("Invalid file size: " + fileSize);
        }
        System.out.println("  ✅ File validation passed");
    }
    
    protected void loadMetadata() {
        System.out.println("  📋 Loading " + getMediaType() + " metadata");
    }
    
    protected void applyCommonPostProcessing() {
        System.out.println("  🎯 Applying common post-processing filters");
    }
    
    // Abstract methods - must be implemented by subclasses
    protected abstract String getMediaType();
    protected abstract ProcessingResult processSpecificContent();
    protected abstract Map<String, Object> extractMetadata();
    
    // Getters
    public String getFilename() { return filename; }
    public long getFileSize() { return fileSize; }
    public String getFormat() { return format; }
    public boolean isProcessed() { return processed; }
    
    @Override
    public String toString() {
        return String.format("%s{filename='%s', size=%d, format='%s', processed=%s}",
                           getClass().getSimpleName(), filename, fileSize, format, processed);
    }
}

/**
 * Audio files - inherits from MediaFile
 * AudioFile IS-A MediaFile (genuine is-a relationship)
 */
class AudioFile extends MediaFile {
    private final int sampleRate;
    private final int bitRate;
    private final int channels;
    
    public AudioFile(String filename, long fileSize, String format, 
                    int sampleRate, int bitRate, int channels) {
        super(filename, fileSize, format);
        this.sampleRate = sampleRate;
        this.bitRate = bitRate;
        this.channels = channels;
    }
    
    @Override
    protected String getMediaType() {
        return "Audio";
    }
    
    @Override
    protected ProcessingResult processSpecificContent() {
        System.out.println("  🎵 Processing audio: analyzing waveforms, normalizing levels");
        
        ProcessingResult result = new ProcessingResult();
        result.outputFormat = "processed_" + format;
        
        // Audio-specific processing metrics
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("originalBitRate", bitRate);
        metrics.put("originalSampleRate", sampleRate);
        metrics.put("channels", channels);
        metrics.put("dynamicRange", calculateDynamicRange());
        metrics.put("peakAmplitude", analyzePeakAmplitude());
        
        result.qualityMetrics = metrics;
        
        // Simulate processing time based on file size
        try {
            Thread.sleep(Math.min(fileSize / 1000000, 100)); // Max 100ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        return result;
    }
    
    @Override
    protected Map<String, Object> extractMetadata() {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("duration", estimateDuration());
        metadata.put("sampleRate", sampleRate);
        metadata.put("bitRate", bitRate);
        metadata.put("channels", channels);
        metadata.put("codec", format);
        return metadata;
    }
    
    private double calculateDynamicRange() {
        return 60 + Math.random() * 20; // Simulated dynamic range in dB
    }
    
    private double analyzePeakAmplitude() {
        return 0.8 + Math.random() * 0.2; // Simulated peak amplitude
    }
    
    private double estimateDuration() {
        // Rough estimate based on file size and bit rate
        return (fileSize * 8.0) / (bitRate * 1000); // Duration in seconds
    }
    
    // Audio-specific methods
    public int getSampleRate() { return sampleRate; }
    public int getBitRate() { return bitRate; }
    public int getChannels() { return channels; }
}

/**
 * Video files - inherits from MediaFile  
 * VideoFile IS-A MediaFile (genuine is-a relationship)
 */
class VideoFile extends MediaFile {
    private final int width;
    private final int height;
    private final double frameRate;
    
    public VideoFile(String filename, long fileSize, String format,
                    int width, int height, double frameRate) {
        super(filename, fileSize, format);
        this.width = width;
        this.height = height;
        this.frameRate = frameRate;
    }
    
    @Override
    protected String getMediaType() {
        return "Video";
    }
    
    @Override
    protected ProcessingResult processSpecificContent() {
        System.out.println("  🎬 Processing video: analyzing frames, optimizing compression");
        
        ProcessingResult result = new ProcessingResult();
        result.outputFormat = "processed_" + format;
        
        // Video-specific processing metrics
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("originalResolution", width + "x" + height);
        metrics.put("originalFrameRate", frameRate);
        metrics.put("estimatedFrames", calculateTotalFrames());
        metrics.put("compressionRatio", calculateCompressionRatio());
        metrics.put("motionComplexity", analyzeMotionComplexity());
        
        result.qualityMetrics = metrics;
        
        // Simulate longer processing time for video
        try {
            Thread.sleep(Math.min(fileSize / 500000, 200)); // Max 200ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        return result;
    }
    
    @Override
    protected Map<String, Object> extractMetadata() {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("resolution", width + "x" + height);
        metadata.put("frameRate", frameRate);
        metadata.put("estimatedDuration", estimateDuration());
        metadata.put("codec", format);
        metadata.put("aspectRatio", (double) width / height);
        return metadata;
    }
    
    private long calculateTotalFrames() {
        double duration = estimateDuration();
        return Math.round(duration * frameRate);
    }
    
    private double calculateCompressionRatio() {
        // Estimate compression ratio based on file size and resolution
        long uncompressedSize = width * height * 3 * calculateTotalFrames(); // RGB
        return (double) uncompressedSize / fileSize;
    }
    
    private double analyzeMotionComplexity() {
        return Math.random(); // Simulated motion complexity (0-1)
    }
    
    private double estimateDuration() {
        // Rough estimate based on file size (simplified)
        return fileSize / (width * height * frameRate * 0.1); // Simplified formula
    }
    
    // Video-specific methods
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public double getFrameRate() { return frameRate; }
    public String getResolution() { return width + "x" + height; }
}

/**
 * Processing result value object
 */
class ProcessingResult {
    public boolean success;
    public String errorMessage;
    public long startTime;
    public long endTime;
    public long processingTimeMs;
    public String outputFormat;
    public Map<String, Object> qualityMetrics = new HashMap<>();
    
    @Override
    public String toString() {
        if (success) {
            return String.format("✅ Success in %dms -> %s", processingTimeMs, outputFormat);
        } else {
            return String.format("❌ Failed: %s", errorMessage);
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// COMPOSITION-BASED SYSTEM - MEDIA PROCESSORS (HAS-A RELATIONSHIPS)
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Compression algorithm interface for Strategy pattern
 */
interface CompressionAlgorithm {
    String getName();
    double getCompressionRatio();
    long compress(MediaFile file);
}

/**
 * Concrete compression algorithms
 */
class LosslessCompression implements CompressionAlgorithm {
    public String getName() { return "Lossless"; }
    public double getCompressionRatio() { return 2.0; }
    
    public long compress(MediaFile file) {
        System.out.println("    🗜️ Applying lossless compression");
        return Math.round(file.getFileSize() / getCompressionRatio());
    }
}

class LossyCompression implements CompressionAlgorithm {
    private final double quality;
    
    public LossyCompression(double quality) {
        this.quality = Math.max(0.1, Math.min(1.0, quality));
    }
    
    public String getName() { return "Lossy (Quality: " + (int)(quality * 100) + "%)"; }
    public double getCompressionRatio() { return 5.0 * (1.1 - quality); }
    
    public long compress(MediaFile file) {
        System.out.println("    🗜️ Applying lossy compression at " + (int)(quality * 100) + "% quality");
        return Math.round(file.getFileSize() / getCompressionRatio());
    }
}

class AdaptiveCompression implements CompressionAlgorithm {
    public String getName() { return "Adaptive"; }
    public double getCompressionRatio() { return 3.5; }
    
    public long compress(MediaFile file) {
        System.out.println("    🗜️ Applying adaptive compression (analyzing content)");
        // Adaptive compression chooses best method based on content
        return Math.round(file.getFileSize() / getCompressionRatio());
    }
}

/**
 * Output format interface for Strategy pattern
 */
interface OutputFormat {
    String getFormatName();
    String getFileExtension();
    boolean isStreamingCompatible();
    Map<String, Object> getFormatOptions();
}

class MP4Format implements OutputFormat {
    public String getFormatName() { return "MP4"; }
    public String getFileExtension() { return ".mp4"; }
    public boolean isStreamingCompatible() { return true; }
    
    public Map<String, Object> getFormatOptions() {
        Map<String, Object> options = new HashMap<>();
        options.put("container", "MPEG-4");
        options.put("streamingOptimized", true);
        options.put("metadataSupport", true);
        return options;
    }
}

class WebMFormat implements OutputFormat {
    public String getFormatName() { return "WebM"; }
    public String getFileExtension() { return ".webm"; }
    public boolean isStreamingCompatible() { return true; }
    
    public Map<String, Object> getFormatOptions() {
        Map<String, Object> options = new HashMap<>();
        options.put("container", "WebM");
        options.put("openSource", true);
        options.put("webOptimized", true);
        return options;
    }
}

class HighQualityFormat implements OutputFormat {
    public String getFormatName() { return "Uncompressed"; }
    public String getFileExtension() { return ".raw"; }
    public boolean isStreamingCompatible() { return false; }
    
    public Map<String, Object> getFormatOptions() {
        Map<String, Object> options = new HashMap<>();
        options.put("compression", "none");
        options.put("quality", "maximum");
        options.put("fileSize", "large");
        return options;
    }
}

/**
 * Media processor using composition for flexibility
 * MediaProcessor HAS-A CompressionAlgorithm and HAS-A OutputFormat
 */
class MediaProcessor {
    private final String processorName;
    
    // Composed strategies - can change at runtime!
    private CompressionAlgorithm compressionAlgorithm;
    private OutputFormat outputFormat;
    
    // Optional features through composition
    private QualityAnalyzer qualityAnalyzer;
    private MetadataExtractor metadataExtractor;
    private ProgressTracker progressTracker;
    
    public MediaProcessor(String processorName, 
                         CompressionAlgorithm compression, 
                         OutputFormat format) {
        this.processorName = processorName;
        this.compressionAlgorithm = compression;
        this.outputFormat = format;
        
        // Default composed components
        this.qualityAnalyzer = new StandardQualityAnalyzer();
        this.metadataExtractor = new StandardMetadataExtractor();
        this.progressTracker = new ConsoleProgressTracker();
    }
    
    /**
     * Process media file using composed strategies
     */
    public ProcessingResult processMedia(MediaFile mediaFile) {
        System.out.println("\n🏭 " + processorName + " processing: " + mediaFile.getFilename());
        
        progressTracker.startProcessing(mediaFile.getFilename());
        
        try {
            // Step 1: Extract metadata using composed extractor
            progressTracker.updateProgress(20, "Extracting metadata");
            Map<String, Object> metadata = metadataExtractor.extract(mediaFile);
            
            // Step 2: Process the media file (inheritance-based processing)
            progressTracker.updateProgress(40, "Processing content");
            ProcessingResult processingResult = mediaFile.process();
            
            if (!processingResult.success) {
                return processingResult;
            }
            
            // Step 3: Apply compression using composed algorithm
            progressTracker.updateProgress(60, "Applying compression");
            long compressedSize = compressionAlgorithm.compress(mediaFile);
            
            // Step 4: Convert to output format using composed format
            progressTracker.updateProgress(80, "Converting format");
            String outputFilename = convertToFormat(mediaFile.getFilename());
            
            // Step 5: Analyze quality using composed analyzer
            progressTracker.updateProgress(90, "Analyzing quality");
            QualityMetrics quality = qualityAnalyzer.analyzeQuality(mediaFile, compressedSize);
            
            // Step 6: Finalize result
            progressTracker.updateProgress(100, "Complete");
            
            ProcessingResult result = new ProcessingResult();
            result.success = true;
            result.outputFormat = outputFilename;
            result.processingTimeMs = System.currentTimeMillis() - processingResult.startTime;
            
            // Combine metrics from processing and quality analysis
            result.qualityMetrics = new HashMap<>(processingResult.qualityMetrics);
            result.qualityMetrics.put("compressionRatio", compressionAlgorithm.getCompressionRatio());
            result.qualityMetrics.put("compressedSize", compressedSize);
            result.qualityMetrics.put("qualityScore", quality.overallScore);
            result.qualityMetrics.put("metadata", metadata);
            
            return result;
            
        } catch (Exception e) {
            ProcessingResult result = new ProcessingResult();
            result.success = false;
            result.errorMessage = e.getMessage();
            return result;
        }
    }
    
    private String convertToFormat(String originalFilename) {
        String baseName = originalFilename.substring(0, originalFilename.lastIndexOf('.'));
        System.out.println("    🔄 Converting to " + outputFormat.getFormatName() + " format");
        return baseName + outputFormat.getFileExtension();
    }
    
    // Runtime strategy changes - impossible with inheritance!
    public void setCompressionAlgorithm(CompressionAlgorithm algorithm) {
        this.compressionAlgorithm = algorithm;
        System.out.println("📊 " + processorName + " compression changed to: " + algorithm.getName());
    }
    
    public void setOutputFormat(OutputFormat format) {
        this.outputFormat = format;
        System.out.println("📋 " + processorName + " output format changed to: " + format.getFormatName());
    }
    
    // Dependency injection for composed components
    public void setQualityAnalyzer(QualityAnalyzer analyzer) {
        this.qualityAnalyzer = analyzer;
    }
    
    public void setMetadataExtractor(MetadataExtractor extractor) {
        this.metadataExtractor = extractor;
    }
    
    public void setProgressTracker(ProgressTracker tracker) {
        this.progressTracker = tracker;
    }
    
    // Getters for current strategies
    public String getProcessorName() { return processorName; }
    public CompressionAlgorithm getCompressionAlgorithm() { return compressionAlgorithm; }
    public OutputFormat getOutputFormat() { return outputFormat; }
}

// Supporting composed components
interface QualityAnalyzer {
    QualityMetrics analyzeQuality(MediaFile file, long compressedSize);
}

interface MetadataExtractor {
    Map<String, Object> extract(MediaFile file);
}

interface ProgressTracker {
    void startProcessing(String filename);
    void updateProgress(int percentage, String status);
}

class QualityMetrics {
    public double overallScore;
    public double fidelityScore;
    public double compressionEfficiency;
    
    public QualityMetrics(double overall, double fidelity, double compression) {
        this.overallScore = overall;
        this.fidelityScore = fidelity;
        this.compressionEfficiency = compression;
    }
}

class StandardQualityAnalyzer implements QualityAnalyzer {
    public QualityMetrics analyzeQuality(MediaFile file, long compressedSize) {
        double compressionRatio = (double) file.getFileSize() / compressedSize;
        double fidelityScore = Math.max(0.5, 1.0 - (compressionRatio / 10.0)); // Simplified
        double compressionEfficiency = Math.min(1.0, compressionRatio / 5.0);
        double overallScore = (fidelityScore + compressionEfficiency) / 2.0;
        
        return new QualityMetrics(overallScore, fidelityScore, compressionEfficiency);
    }
}

class StandardMetadataExtractor implements MetadataExtractor {
    public Map<String, Object> extract(MediaFile file) {
        Map<String, Object> metadata = file.extractMetadata();
        metadata.put("extractedAt", new Date());
        metadata.put("extractor", "StandardMetadataExtractor");
        return metadata;
    }
}

class ConsoleProgressTracker implements ProgressTracker {
    private String currentFile;
    
    public void startProcessing(String filename) {
        this.currentFile = filename;
        System.out.println("    📊 Starting processing: " + filename);
    }
    
    public void updateProgress(int percentage, String status) {
        System.out.println(String.format("    📊 Progress: %d%% - %s", percentage, status));
    }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// DECORATOR PATTERN - ENHANCED PROCESSORS
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Base processor decorator for adding features through composition
 */
abstract class ProcessorDecorator {
    protected MediaProcessor processor;  // Composition - HAS-A MediaProcessor
    
    public ProcessorDecorator(MediaProcessor processor) {
        this.processor = processor;
    }
    
    public ProcessingResult processMedia(MediaFile mediaFile) {
        return processor.processMedia(mediaFile);  // Delegation
    }
}

/**
 * Adds caching capability through composition
 */
class CachingProcessorDecorator extends ProcessorDecorator {
    private final Map<String, ProcessingResult> cache;
    private final int maxCacheSize;
    
    public CachingProcessorDecorator(MediaProcessor processor, int maxCacheSize) {
        super(processor);
        this.cache = new LinkedHashMap<>();
        this.maxCacheSize = maxCacheSize;
    }
    
    @Override
    public ProcessingResult processMedia(MediaFile mediaFile) {
        String cacheKey = generateCacheKey(mediaFile);
        
        // Check cache first
        if (cache.containsKey(cacheKey)) {
            System.out.println("💾 Cache hit for: " + mediaFile.getFilename());
            return cache.get(cacheKey);
        }
        
        // Process and cache result
        ProcessingResult result = super.processMedia(mediaFile);
        
        // Maintain cache size limit
        if (cache.size() >= maxCacheSize) {
            String oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        
        cache.put(cacheKey, result);
        System.out.println("💾 Cached result for: " + mediaFile.getFilename());
        
        return result;
    }
    
    private String generateCacheKey(MediaFile file) {
        return file.getFilename() + "_" + file.getFileSize() + "_" + 
               processor.getCompressionAlgorithm().getName() + "_" +
               processor.getOutputFormat().getFormatName();
    }
}

/**
 * Adds batch processing capability through composition
 */
class BatchProcessorDecorator extends ProcessorDecorator {
    private final ExecutorService executor;
    private final int maxConcurrentJobs;
    
    public BatchProcessorDecorator(MediaProcessor processor, int maxConcurrentJobs) {
        super(processor);
        this.maxConcurrentJobs = maxConcurrentJobs;
        this.executor = Executors.newFixedThreadPool(maxConcurrentJobs);
    }
    
    public CompletableFuture<List<ProcessingResult>> processBatch(List<MediaFile> files) {
        System.out.println("🚀 Starting batch processing of " + files.size() + " files");
        
        List<CompletableFuture<ProcessingResult>> futures = new ArrayList<>();
        
        for (MediaFile file : files) {
            CompletableFuture<ProcessingResult> future = CompletableFuture
                .supplyAsync(() -> super.processMedia(file), executor);
            futures.add(future);
        }
        
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                    .map(CompletableFuture::join)
                    .toList());
    }
    
    public void shutdown() {
        executor.shutdown();
    }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// MAIN DEMONSTRATION CLASS
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Advanced Media Processing System demonstrating composition vs inheritance
 */
public class _PracticalExample {
    
    public static void main(String[] args) {
        System.out.println("🎬 ADVANCED MEDIA PROCESSING SYSTEM DEMONSTRATION 🎬\n");
        
        // ═══════════════════════════════════════════════════════════════
        // CREATE MEDIA FILES USING INHERITANCE HIERARCHY
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("🎞️ CREATING MEDIA FILES (Inheritance Hierarchy):");
        
        List<MediaFile> mediaFiles = Arrays.asList(
            new AudioFile("podcast_episode.mp3", 5_200_000, "MP3", 44100, 320, 2),
            new AudioFile("classical_symphony.flac", 45_000_000, "FLAC", 96000, 1411, 2),
            new VideoFile("documentary.mkv", 1_200_000_000, "MKV", 1920, 1080, 24.0),
            new VideoFile("mobile_video.mp4", 85_000_000, "MP4", 1280, 720, 30.0),
            new AudioFile("nature_sounds.wav", 25_000_000, "WAV", 48000, 1536, 2)
        );
        
        System.out.println("Created media files:");
        mediaFiles.forEach(file -> System.out.println("  📁 " + file));
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATE INHERITANCE POLYMORPHISM
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🎭 INHERITANCE POLYMORPHISM DEMONSTRATION:");
        System.out.println("Processing files using polymorphic method calls...\n");
        
        for (MediaFile file : mediaFiles) {
            ProcessingResult result = file.process();  // Polymorphic call
            System.out.println("  " + result);
            
            // Extract metadata using polymorphic call
            Map<String, Object> metadata = file.extractMetadata();
            System.out.println("  📋 Metadata: " + metadata);
            System.out.println();
        }
        
        // ═══════════════════════════════════════════════════════════════
        // CREATE MEDIA PROCESSORS USING COMPOSITION
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🏭 CREATING MEDIA PROCESSORS (Composition):");
        
        // Create processors with different composed strategies
        MediaProcessor webProcessor = new MediaProcessor("WebOptimizedProcessor",
            new LossyCompression(0.8), new WebMFormat());
        
        MediaProcessor mobileProcessor = new MediaProcessor("MobileProcessor", 
            new LossyCompression(0.6), new MP4Format());
        
        MediaProcessor archivalProcessor = new MediaProcessor("ArchivalProcessor",
            new LosslessCompression(), new HighQualityFormat());
        
        System.out.println("Created processors:");
        System.out.println("  🌐 " + webProcessor.getProcessorName() + 
                          " - " + webProcessor.getCompressionAlgorithm().getName() + 
                          " + " + webProcessor.getOutputFormat().getFormatName());
        System.out.println("  📱 " + mobileProcessor.getProcessorName() + 
                          " - " + mobileProcessor.getCompressionAlgorithm().getName() + 
                          " + " + mobileProcessor.getOutputFormat().getFormatName());
        System.out.println("  🏛️ " + archivalProcessor.getProcessorName() + 
                          " - " + archivalProcessor.getCompressionAlgorithm().getName() + 
                          " + " + archivalProcessor.getOutputFormat().getFormatName());
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATE COMPOSITION FLEXIBILITY
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🔄 COMPOSITION FLEXIBILITY DEMONSTRATION:");
        
        // Process same file with different strategies
        MediaFile testFile = mediaFiles.get(0); // Audio file
        
        System.out.println("Processing same file with different composed strategies:");
        
        ProcessingResult webResult = webProcessor.processMedia(testFile);
        ProcessingResult mobileResult = mobileProcessor.processMedia(testFile);
        ProcessingResult archivalResult = archivalProcessor.processMedia(testFile);
        
        System.out.println("\n📊 PROCESSING RESULTS COMPARISON:");
        System.out.println("Web-optimized:    " + webResult);
        System.out.println("Mobile-optimized: " + mobileResult);
        System.out.println("Archival:         " + archivalResult);
        
        // ═══════════════════════════════════════════════════════════════
        // RUNTIME STRATEGY CHANGES (IMPOSSIBLE WITH INHERITANCE)
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n⚡ RUNTIME STRATEGY CHANGES:");
        System.out.println("Changing processor strategies at runtime...\n");
        
        // Change compression algorithm at runtime
        webProcessor.setCompressionAlgorithm(new AdaptiveCompression());
        
        // Change output format at runtime  
        mobileProcessor.setOutputFormat(new WebMFormat());
        
        // Process again with new strategies
        System.out.println("\nReprocessing with new strategies:");
        ProcessingResult newWebResult = webProcessor.processMedia(testFile);
        ProcessingResult newMobileResult = mobileProcessor.processMedia(testFile);
        
        System.out.println("Updated web result:    " + newWebResult);
        System.out.println("Updated mobile result: " + newMobileResult);
        
        // ═══════════════════════════════════════════════════════════════
        // DECORATOR PATTERN DEMONSTRATION
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🎨 DECORATOR PATTERN DEMONSTRATION:");
        System.out.println("Adding features through composition...\n");
        
        // Wrap processor with caching decorator
        CachingProcessorDecorator cachingProcessor = 
            new CachingProcessorDecorator(archivalProcessor, 10);
        
        // Process file twice - second time should hit cache
        System.out.println("First processing (cache miss):");
        ProcessingResult firstResult = cachingProcessor.processMedia(testFile);
        
        System.out.println("\nSecond processing (cache hit):");
        ProcessingResult cachedResult = cachingProcessor.processMedia(testFile);
        
        // ═══════════════════════════════════════════════════════════════
        // BATCH PROCESSING WITH DECORATOR
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🚀 BATCH PROCESSING DEMONSTRATION:");
        
        // Create batch processor decorator
        BatchProcessorDecorator batchProcessor = 
            new BatchProcessorDecorator(mobileProcessor, 3);
        
        try {
            // Process batch of files asynchronously
            CompletableFuture<List<ProcessingResult>> batchFuture = 
                batchProcessor.processBatch(mediaFiles.subList(0, 3));
            
            List<ProcessingResult> batchResults = batchFuture.get();
            
            System.out.println("\n📊 BATCH PROCESSING RESULTS:");
            batchResults.forEach(result -> System.out.println("  " + result));
            
        } catch (Exception e) {
            System.out.println("Batch processing failed: " + e.getMessage());
        } finally {
            batchProcessor.shutdown();
        }
        
        // ═══════════════════════════════════════════════════════════════
        // COMPOSITION VS INHERITANCE ANALYSIS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n📋 COMPOSITION VS INHERITANCE ANALYSIS:");
        
        analyzeApproachesUsed();
        
        // ═══════════════════════════════════════════════════════════════
        // PERFORMANCE AND FLEXIBILITY COMPARISON  
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n⚖️ PERFORMANCE AND FLEXIBILITY COMPARISON:");
        
        comparePerformanceAndFlexibility();
        
        // ═══════════════════════════════════════════════════════════════
        // FINAL SUMMARY
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n✨ ADVANCED MEDIA PROCESSING SYSTEM DEMONSTRATION COMPLETE! ✨");
        System.out.println("\n🎯 ARCHITECTURAL WISDOM DEMONSTRATED:");
        System.out.println("🔹 Inheritance used for genuine 'is-a' relationships (MediaFile hierarchy)");
        System.out.println("🔹 Composition used for flexible 'has-a' relationships (processing strategies)");
        System.out.println("🔹 Template method pattern leveraged inheritance for shared algorithms");
        System.out.println("🔹 Strategy pattern leveraged composition for runtime algorithm selection");
        System.out.println("🔹 Decorator pattern added features without inheritance explosion");
        System.out.println("🔹 Runtime behavior changes enabled through composition");
        System.out.println("🔹 Easy testing through dependency injection with composed objects");
        System.out.println("🔹 Flexible system evolution through strategy replacement");
        
        System.out.println("\n💡 The wise architect uses both inheritance and composition appropriately!");
        System.out.println("🏗️ Inheritance for stable hierarchies, composition for flexible collaboration!");
    }
    
    /**
     * Analyzes when each approach was used and why
     */
    private static void analyzeApproachesUsed() {
        System.out.println("\n🔍 When we used INHERITANCE:");
        System.out.println("  ✅ MediaFile hierarchy - genuine 'is-a' relationships");
        System.out.println("     - AudioFile IS-A MediaFile");
        System.out.println("     - VideoFile IS-A MediaFile");
        System.out.println("  ✅ Template method pattern for shared processing algorithm");
        System.out.println("  ✅ Polymorphic behavior for different media types");
        System.out.println("  ✅ Stable domain model with clear hierarchical structure");
        
        System.out.println("\n🔧 When we used COMPOSITION:");
        System.out.println("  ✅ MediaProcessor HAS-A CompressionAlgorithm");
        System.out.println("  ✅ MediaProcessor HAS-A OutputFormat");
        System.out.println("  ✅ MediaProcessor HAS-A QualityAnalyzer");
        System.out.println("  ✅ Runtime strategy changes needed");
        System.out.println("  ✅ Multiple orthogonal capabilities to combine");
        System.out.println("  ✅ Easy testing through dependency injection");
        System.out.println("  ✅ Decorator pattern for feature enhancement");
        
        System.out.println("\n🎯 Why this hybrid approach works:");
        System.out.println("  • Stable domain concepts use inheritance");
        System.out.println("  • Flexible algorithms and behaviors use composition");
        System.out.println("  • Each approach used for its strengths");
        System.out.println("  • System is both predictable and adaptable");
    }
    
    /**
     * Compares performance and flexibility characteristics
     */
    private static void comparePerformanceAndFlexibility() {
        System.out.println("\n📊 INHERITANCE CHARACTERISTICS:");
        System.out.println("  Performance:");
        System.out.println("    ✅ Direct method calls (no indirection)");
        System.out.println("    ✅ Single object in memory");
        System.out.println("    ✅ JVM optimizations for virtual calls");
        System.out.println("  Flexibility:");
        System.out.println("    ❌ Compile-time behavior binding");
        System.out.println("    ❌ Cannot change behavior at runtime");
        System.out.println("    ❌ Tight coupling to parent class");
        System.out.println("  Testing:");
        System.out.println("    ❌ Hard to isolate for unit testing");
        System.out.println("    ❌ Must test entire inheritance chain");
        
        System.out.println("\n🔧 COMPOSITION CHARACTERISTICS:");
        System.out.println("  Performance:");
        System.out.println("    ❌ Method delegation overhead");
        System.out.println("    ❌ Multiple objects in memory");
        System.out.println("    ❌ Extra indirection for method calls");
        System.out.println("  Flexibility:");
        System.out.println("    ✅ Runtime behavior changes");
        System.out.println("    ✅ Mix and match capabilities");
        System.out.println("    ✅ Loose coupling between objects");
        System.out.println("  Testing:");
        System.out.println("    ✅ Easy to mock dependencies");
        System.out.println("    ✅ Test components in isolation");
        System.out.println("    ✅ Dependency injection friendly");
        
        System.out.println("\n🏆 OPTIMAL STRATEGY:");
        System.out.println("  • Use inheritance for stable, performance-critical hierarchies");
        System.out.println("  • Use composition for flexible, testable, evolving systems");
        System.out.println("  • Combine both approaches as demonstrated in this system");
        System.out.println("  • Favor composition for new features and algorithms");
        System.out.println("  • Profile actual performance - don't assume overhead is significant");
    }
}
