/*
 * THE LIVING MANUSCRIPT - ABSTRACTION IN THE REAL WORLD
 * 
 * "Behold, young seeker, how Abstraction breathes life into the realm of digital media!
 * Here we shall craft a Media Processing System that demonstrates the profound power
 * of abstract classes and interfaces working in harmony to create flexible, extensible code."
 * 
 * This system models a real-world scenario where different types of media files
 * (audio, video, documents) need to be processed, but each requires different
 * handling while sharing common operations.
 */

// ================================================================================================
// THE FOUNDATION: ABSTRACT MEDIA FILE
// ================================================================================================

/**
 * Abstract base class representing any media file in our system.
 * Defines common properties and behaviors while leaving specific processing to subclasses.
 * 
 * Real-world analogy: Every media file has a name, size, format, but each type
 * processes, compresses, and validates differently.
 */
abstract class MediaFile {
    
    // Common properties shared by all media files
    protected String fileName;
    protected long fileSizeBytes;
    protected String format;
    protected boolean isCorrupted;
    private java.time.LocalDateTime uploadTime;
    
    // Constructor - called when concrete subclasses are instantiated
    public MediaFile(String fileName, long fileSizeBytes, String format) {
        this.fileName = fileName;
        this.fileSizeBytes = fileSizeBytes;
        this.format = format.toLowerCase();
        this.isCorrupted = false;
        this.uploadTime = java.time.LocalDateTime.now();
    }
    
    // ===== CONCRETE METHODS: Complete implementation shared by all subclasses =====
    
    /**
     * Common file information display - same for all media types
     */
    public void displayInfo() {
        System.out.println("📁 File: " + fileName);
        System.out.println("   📊 Size: " + formatFileSize(fileSizeBytes));
        System.out.println("   🎨 Format: " + format.toUpperCase());
        System.out.println("   📅 Uploaded: " + uploadTime);
        System.out.println("   ✅ Status: " + (isCorrupted ? "CORRUPTED" : "OK"));
    }
    
    /**
     * Utility method to format file size in human-readable format
     */
    protected String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024) return String.format("%.1f MB", bytes / (1024.0 * 1024.0));
        return String.format("%.1f GB", bytes / (1024.0 * 1024.0 * 1024.0));
    }
    
    /**
     * Common backup functionality - creates backup copy
     */
    public final boolean createBackup() {
        System.out.println("🔄 Creating backup of " + fileName + "...");
        // Simulate backup process
        try {
            Thread.sleep(100);  // Simulate processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("✅ Backup created: " + fileName + ".backup");
        return true;
    }
    
    // ===== ABSTRACT METHODS: Must be implemented by each media type =====
    
    /**
     * Each media type has its own processing requirements
     */
    public abstract void process();
    
    /**
     * Each media type compresses differently
     */
    public abstract long compress();
    
    /**
     * Each media type has different validation rules
     */
    public abstract boolean validate();
    
    /**
     * Different media types have different metadata extraction
     */
    public abstract String extractMetadata();
    
    /**
     * Media type specific preview/thumbnail generation
     */
    public abstract String generatePreview();
    
    // Getters for protected fields
    public String getFileName() { return fileName; }
    public long getFileSizeBytes() { return fileSizeBytes; }
    public String getFormat() { return format; }
    public boolean isCorrupted() { return isCorrupted; }
}

// ================================================================================================
// INTERFACES: DEFINING CAPABILITIES
// ================================================================================================

/**
 * Interface for media that can be streamed online
 */
interface Streamable {
    
    // Constants for streaming quality
    int QUALITY_LOW = 480;
    int QUALITY_MEDIUM = 720;
    int QUALITY_HIGH = 1080;
    int QUALITY_ULTRA = 2160;
    
    // Streaming capabilities
    void startStreaming(int quality);
    void pauseStreaming();
    void stopStreaming();
    String getStreamingUrl();
    
    // Default implementation for common streaming setup
    default void prepareForStreaming() {
        System.out.println("🌐 Preparing streaming infrastructure...");
        System.out.println("🔗 Establishing CDN connections...");
        System.out.println("⚙️ Optimizing for network delivery...");
    }
    
    // Static utility method
    static String getQualityName(int quality) {
        return switch (quality) {
            case QUALITY_LOW -> "480p (Low)";
            case QUALITY_MEDIUM -> "720p (Medium)";
            case QUALITY_HIGH -> "1080p (High)";
            case QUALITY_ULTRA -> "4K (Ultra)";
            default -> "Unknown Quality";
        };
    }
}

/**
 * Interface for media that can be shared on social platforms
 */
interface Shareable {
    
    // Sharing platforms
    enum Platform { FACEBOOK, TWITTER, INSTAGRAM, LINKEDIN, EMAIL }
    
    // Sharing capabilities
    void shareOn(Platform platform);
    String generateShareableLink();
    void setPrivacyLevel(String level);
    
    // Default privacy settings
    default void applyDefaultPrivacy() {
        System.out.println("🔒 Applying default privacy settings (Public with restrictions)");
        setPrivacyLevel("public");
    }
}

/**
 * Interface for media that can be edited/modified
 */
interface Editable {
    
    // Editing operations
    void crop(int x, int y, int width, int height);
    void resize(int newWidth, int newHeight);
    void applyFilter(String filterName);
    void adjustQuality(int percentage);
    
    // Default implementation for common edit operations
    default void performBasicOptimization() {
        System.out.println("⚡ Performing basic optimization...");
        adjustQuality(85);  // Reduce to 85% quality for smaller size
        System.out.println("✨ Basic optimization complete");
    }
}

// ================================================================================================
// CONCRETE IMPLEMENTATIONS: SPECIFIC MEDIA TYPES
// ================================================================================================

/**
 * Audio File implementation - demonstrates abstract class inheritance
 * and multiple interface implementation
 */
class AudioFile extends MediaFile implements Streamable, Shareable {
    
    private int durationSeconds;
    private String artist;
    private String album;
    private int bitRate;
    private boolean isStreaming;
    
    public AudioFile(String fileName, long fileSizeBytes, String format, 
                    int durationSeconds, String artist, String album, int bitRate) {
        super(fileName, fileSizeBytes, format);
        this.durationSeconds = durationSeconds;
        this.artist = artist;
        this.album = album;
        this.bitRate = bitRate;
        this.isStreaming = false;
    }
    
    // ===== IMPLEMENTING ABSTRACT METHODS FROM MEDIAFILE =====
    
    @Override
    public void process() {
        System.out.println("🎵 Processing audio file: " + fileName);
        System.out.println("   🎨 Analyzing audio waveform...");
        System.out.println("   🔍 Detecting audio characteristics...");
        System.out.println("   ⚡ Normalizing audio levels...");
        System.out.println("   ✅ Audio processing complete!");
    }
    
    @Override
    public long compress() {
        System.out.println("🗜️ Compressing audio using advanced codec...");
        
        // Simulate different compression based on bitrate
        double compressionRatio;
        if (bitRate > 320) compressionRatio = 0.3;      // High quality -> 70% reduction
        else if (bitRate > 128) compressionRatio = 0.5; // Medium quality -> 50% reduction
        else compressionRatio = 0.7;                    // Low quality -> 30% reduction
        
        long newSize = (long)(fileSizeBytes * compressionRatio);
        System.out.println("   📉 Reduced from " + formatFileSize(fileSizeBytes) + 
                          " to " + formatFileSize(newSize));
        
        this.fileSizeBytes = newSize;
        return newSize;
    }
    
    @Override
    public boolean validate() {
        System.out.println("✅ Validating audio file...");
        
        // Check file format
        boolean validFormat = format.equals("mp3") || format.equals("wav") || 
                             format.equals("flac") || format.equals("aac");
        
        // Check duration
        boolean validDuration = durationSeconds > 0 && durationSeconds < 7200; // Max 2 hours
        
        // Check bitrate
        boolean validBitrate = bitRate >= 64 && bitRate <= 320;
        
        boolean isValid = validFormat && validDuration && validBitrate;
        
        if (!isValid) {
            this.isCorrupted = true;
            System.out.println("❌ Audio validation failed!");
        } else {
            System.out.println("✅ Audio validation successful!");
        }
        
        return isValid;
    }
    
    @Override
    public String extractMetadata() {
        StringBuilder metadata = new StringBuilder();
        metadata.append("🎵 AUDIO METADATA:\n");
        metadata.append("   🎤 Artist: ").append(artist).append("\n");
        metadata.append("   💿 Album: ").append(album).append("\n");
        metadata.append("   ⏱️ Duration: ").append(formatDuration(durationSeconds)).append("\n");
        metadata.append("   📡 Bitrate: ").append(bitRate).append(" kbps\n");
        metadata.append("   🎼 Sample Rate: 44.1 kHz\n");
        
        return metadata.toString();
    }
    
    @Override
    public String generatePreview() {
        System.out.println("🎧 Generating audio preview (30-second clip)...");
        String previewUrl = "https://preview.musicservice.com/" + 
                           fileName.replaceAll("\\s+", "_").toLowerCase() + "_preview.mp3";
        System.out.println("🔗 Preview URL: " + previewUrl);
        return previewUrl;
    }
    
    // ===== IMPLEMENTING STREAMABLE INTERFACE =====
    
    @Override
    public void startStreaming(int quality) {
        if (!isStreaming) {
            prepareForStreaming();  // Use default interface method
            System.out.println("▶️ Starting audio stream...");
            System.out.println("   🎵 " + artist + " - " + fileName);
            System.out.println("   📻 Quality: " + Streamable.getQualityName(quality));
            isStreaming = true;
        } else {
            System.out.println("⚠️ Already streaming!");
        }
    }
    
    @Override
    public void pauseStreaming() {
        if (isStreaming) {
            System.out.println("⏸️ Audio stream paused");
        }
    }
    
    @Override
    public void stopStreaming() {
        if (isStreaming) {
            System.out.println("⏹️ Audio stream stopped");
            isStreaming = false;
        }
    }
    
    @Override
    public String getStreamingUrl() {
        return "https://stream.musicservice.com/audio/" + 
               fileName.replaceAll("\\s+", "_").toLowerCase();
    }
    
    // ===== IMPLEMENTING SHAREABLE INTERFACE =====
    
    @Override
    public void shareOn(Platform platform) {
        applyDefaultPrivacy();  // Use default interface method
        System.out.println("📤 Sharing \"" + fileName + "\" by " + artist + " on " + platform);
        
        switch (platform) {
            case TWITTER -> System.out.println("🐦 Posted with #NowPlaying hashtag");
            case FACEBOOK -> System.out.println("📘 Shared on timeline with artist info");
            case INSTAGRAM -> System.out.println("📸 Posted with album artwork");
            case EMAIL -> System.out.println("📧 Sent as attachment with preview");
            case LINKEDIN -> System.out.println("💼 Shared as professional content");
        }
    }
    
    @Override
    public String generateShareableLink() {
        return "https://share.musicservice.com/track/" + 
               java.util.UUID.randomUUID().toString().substring(0, 8);
    }
    
    @Override
    public void setPrivacyLevel(String level) {
        System.out.println("🔐 Privacy set to: " + level);
    }
    
    // Helper method
    private String formatDuration(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%d:%02d", minutes, secs);
    }
}

/**
 * Video File implementation - shows different interface combinations
 */
class VideoFile extends MediaFile implements Streamable, Shareable, Editable {
    
    private int durationSeconds;
    private int width;
    private int height;
    private int frameRate;
    private String codec;
    private boolean hasAudio;
    
    public VideoFile(String fileName, long fileSizeBytes, String format,
                    int durationSeconds, int width, int height, int frameRate, 
                    String codec, boolean hasAudio) {
        super(fileName, fileSizeBytes, format);
        this.durationSeconds = durationSeconds;
        this.width = width;
        this.height = height;
        this.frameRate = frameRate;
        this.codec = codec;
        this.hasAudio = hasAudio;
    }
    
    // ===== IMPLEMENTING ABSTRACT METHODS FROM MEDIAFILE =====
    
    @Override
    public void process() {
        System.out.println("🎬 Processing video file: " + fileName);
        System.out.println("   🎞️ Analyzing video frames...");
        System.out.println("   🎨 Color correction and enhancement...");
        if (hasAudio) {
            System.out.println("   🔊 Processing audio track...");
            System.out.println("   🔄 Synchronizing audio/video...");
        }
        System.out.println("   🎯 Optimizing playback performance...");
        System.out.println("   ✅ Video processing complete!");
    }
    
    @Override
    public long compress() {
        System.out.println("🗜️ Compressing video using " + codec + " codec...");
        
        // Video compression is complex - depends on resolution, bitrate, codec
        double compressionRatio;
        if (width >= 1920) compressionRatio = 0.2;       // 4K/HD -> 80% reduction
        else if (width >= 1280) compressionRatio = 0.4;  // 720p -> 60% reduction
        else compressionRatio = 0.6;                     // SD -> 40% reduction
        
        long newSize = (long)(fileSizeBytes * compressionRatio);
        System.out.println("   📉 Video compressed from " + formatFileSize(fileSizeBytes) + 
                          " to " + formatFileSize(newSize));
        
        this.fileSizeBytes = newSize;
        return newSize;
    }
    
    @Override
    public boolean validate() {
        System.out.println("✅ Validating video file...");
        
        boolean validFormat = format.equals("mp4") || format.equals("avi") || 
                             format.equals("mov") || format.equals("mkv");
        boolean validResolution = width > 0 && height > 0 && width <= 7680 && height <= 4320;
        boolean validFrameRate = frameRate >= 15 && frameRate <= 120;
        boolean validDuration = durationSeconds > 0 && durationSeconds < 14400; // Max 4 hours
        
        boolean isValid = validFormat && validResolution && validFrameRate && validDuration;
        
        if (!isValid) {
            this.isCorrupted = true;
            System.out.println("❌ Video validation failed!");
        } else {
            System.out.println("✅ Video validation successful!");
        }
        
        return isValid;
    }
    
    @Override
    public String extractMetadata() {
        StringBuilder metadata = new StringBuilder();
        metadata.append("🎬 VIDEO METADATA:\n");
        metadata.append("   📐 Resolution: ").append(width).append("x").append(height).append("\n");
        metadata.append("   ⏱️ Duration: ").append(formatDuration(durationSeconds)).append("\n");
        metadata.append("   🎞️ Frame Rate: ").append(frameRate).append(" fps\n");
        metadata.append("   💾 Codec: ").append(codec).append("\n");
        metadata.append("   🔊 Audio: ").append(hasAudio ? "Yes" : "No").append("\n");
        
        return metadata.toString();
    }
    
    @Override
    public String generatePreview() {
        System.out.println("🎬 Generating video thumbnail and preview clip...");
        String thumbnailUrl = "https://thumbnails.videoservice.com/" + 
                             fileName.replaceAll("\\s+", "_").toLowerCase() + "_thumb.jpg";
        String previewUrl = "https://preview.videoservice.com/" + 
                           fileName.replaceAll("\\s+", "_").toLowerCase() + "_preview.mp4";
        
        System.out.println("🖼️ Thumbnail: " + thumbnailUrl);
        System.out.println("🎞️ Preview: " + previewUrl);
        return previewUrl;
    }
    
    // ===== IMPLEMENTING STREAMABLE INTERFACE =====
    
    @Override
    public void startStreaming(int quality) {
        prepareForStreaming();
        System.out.println("▶️ Starting video stream...");
        System.out.println("   🎬 " + fileName);
        System.out.println("   📺 Quality: " + Streamable.getQualityName(quality));
        System.out.println("   📐 Native Resolution: " + width + "x" + height);
    }
    
    @Override
    public void pauseStreaming() {
        System.out.println("⏸️ Video stream paused");
    }
    
    @Override
    public void stopStreaming() {
        System.out.println("⏹️ Video stream stopped");
    }
    
    @Override
    public String getStreamingUrl() {
        return "https://stream.videoservice.com/video/" + 
               fileName.replaceAll("\\s+", "_").toLowerCase();
    }
    
    // ===== IMPLEMENTING SHAREABLE INTERFACE =====
    
    @Override
    public void shareOn(Platform platform) {
        applyDefaultPrivacy();
        System.out.println("📤 Sharing video \"" + fileName + "\" on " + platform);
        
        switch (platform) {
            case TWITTER -> System.out.println("🐦 Posted with auto-generated preview");
            case FACEBOOK -> System.out.println("📘 Uploaded with description and tags");
            case INSTAGRAM -> System.out.println("📸 Posted as IGTV or Reel");
            case EMAIL -> System.out.println("📧 Sent as link (file too large for attachment)");
            case LINKEDIN -> System.out.println("💼 Shared as professional content");
        }
    }
    
    @Override
    public String generateShareableLink() {
        return "https://share.videoservice.com/video/" + 
               java.util.UUID.randomUUID().toString().substring(0, 8);
    }
    
    @Override
    public void setPrivacyLevel(String level) {
        System.out.println("🔐 Video privacy set to: " + level);
    }
    
    // ===== IMPLEMENTING EDITABLE INTERFACE =====
    
    @Override
    public void crop(int x, int y, int width, int height) {
        System.out.println("✂️ Cropping video to region (" + x + "," + y + ") " + 
                          width + "x" + height);
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void resize(int newWidth, int newHeight) {
        System.out.println("📏 Resizing video from " + width + "x" + height + 
                          " to " + newWidth + "x" + newHeight);
        this.width = newWidth;
        this.height = newHeight;
    }
    
    @Override
    public void applyFilter(String filterName) {
        System.out.println("🎨 Applying '" + filterName + "' filter to video");
        switch (filterName.toLowerCase()) {
            case "sepia" -> System.out.println("   📼 Adding vintage sepia tone");
            case "blur" -> System.out.println("   🌫️ Adding motion blur effect");
            case "sharpen" -> System.out.println("   ✨ Enhancing image sharpness");
            case "noir" -> System.out.println("   🎭 Converting to black and white");
            default -> System.out.println("   🎯 Applying custom " + filterName + " effect");
        }
    }
    
    @Override
    public void adjustQuality(int percentage) {
        System.out.println("⚙️ Adjusting video quality to " + percentage + "%");
        // Adjust file size based on quality
        double factor = percentage / 100.0;
        this.fileSizeBytes = (long)(this.fileSizeBytes * factor);
    }
    
    private String formatDuration(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        if (hours > 0) {
            return String.format("%d:%02d:%02d", hours, minutes, secs);
        } else {
            return String.format("%d:%02d", minutes, secs);
        }
    }
}

/**
 * Document File implementation - shows selective interface implementation
 */
class DocumentFile extends MediaFile implements Shareable {
    
    private int pageCount;
    private String author;
    private boolean hasImages;
    private String documentType;
    
    public DocumentFile(String fileName, long fileSizeBytes, String format,
                       int pageCount, String author, boolean hasImages, String documentType) {
        super(fileName, fileSizeBytes, format);
        this.pageCount = pageCount;
        this.author = author;
        this.hasImages = hasImages;
        this.documentType = documentType;
    }
    
    // ===== IMPLEMENTING ABSTRACT METHODS FROM MEDIAFILE =====
    
    @Override
    public void process() {
        System.out.println("📄 Processing document: " + fileName);
        System.out.println("   🔍 OCR text recognition...");
        System.out.println("   🏗️ Building document structure...");
        if (hasImages) {
            System.out.println("   🖼️ Processing embedded images...");
        }
        System.out.println("   🔤 Indexing content for search...");
        System.out.println("   ✅ Document processing complete!");
    }
    
    @Override
    public long compress() {
        System.out.println("🗜️ Compressing document...");
        
        double compressionRatio;
        if (hasImages) compressionRatio = 0.4;  // Images compress well
        else compressionRatio = 0.8;            // Text doesn't compress as much
        
        long newSize = (long)(fileSizeBytes * compressionRatio);
        System.out.println("   📉 Document compressed from " + formatFileSize(fileSizeBytes) + 
                          " to " + formatFileSize(newSize));
        
        this.fileSizeBytes = newSize;
        return newSize;
    }
    
    @Override
    public boolean validate() {
        System.out.println("✅ Validating document...");
        
        boolean validFormat = format.equals("pdf") || format.equals("docx") || 
                             format.equals("txt") || format.equals("rtf");
        boolean validPageCount = pageCount > 0 && pageCount <= 10000;
        boolean hasContent = fileSizeBytes > 0;
        
        boolean isValid = validFormat && validPageCount && hasContent;
        
        if (!isValid) {
            this.isCorrupted = true;
            System.out.println("❌ Document validation failed!");
        } else {
            System.out.println("✅ Document validation successful!");
        }
        
        return isValid;
    }
    
    @Override
    public String extractMetadata() {
        StringBuilder metadata = new StringBuilder();
        metadata.append("📄 DOCUMENT METADATA:\n");
        metadata.append("   ✍️ Author: ").append(author != null ? author : "Unknown").append("\n");
        metadata.append("   📑 Pages: ").append(pageCount).append("\n");
        metadata.append("   📝 Type: ").append(documentType).append("\n");
        metadata.append("   🖼️ Contains Images: ").append(hasImages ? "Yes" : "No").append("\n");
        metadata.append("   🗓️ Created: ").append(java.time.LocalDate.now()).append("\n");
        
        return metadata.toString();
    }
    
    @Override
    public String generatePreview() {
        System.out.println("📄 Generating document preview (first page)...");
        String previewUrl = "https://preview.docservice.com/" + 
                           fileName.replaceAll("\\s+", "_").toLowerCase() + "_page1.png";
        System.out.println("👁️ Preview URL: " + previewUrl);
        return previewUrl;
    }
    
    // ===== IMPLEMENTING SHAREABLE INTERFACE =====
    
    @Override
    public void shareOn(Platform platform) {
        applyDefaultPrivacy();
        System.out.println("📤 Sharing document \"" + fileName + "\" on " + platform);
        
        switch (platform) {
            case TWITTER -> System.out.println("🐦 Shared as link with preview image");
            case FACEBOOK -> System.out.println("📘 Posted with document description");
            case INSTAGRAM -> System.out.println("📸 Posted as image carousel (first few pages)");
            case EMAIL -> System.out.println("📧 Sent as PDF attachment");
            case LINKEDIN -> System.out.println("💼 Shared as professional document");
        }
    }
    
    @Override
    public String generateShareableLink() {
        return "https://share.docservice.com/doc/" + 
               java.util.UUID.randomUUID().toString().substring(0, 8);
    }
    
    @Override
    public void setPrivacyLevel(String level) {
        System.out.println("🔐 Document privacy set to: " + level);
    }
}

// ================================================================================================
// THE MEDIA PROCESSING ENGINE: PUTTING IT ALL TOGETHER
// ================================================================================================

/**
 * Demonstrates the power of abstraction through polymorphism
 * This class works with ANY MediaFile type without knowing the specifics
 */
class MediaProcessor {
    
    /**
     * Processes any type of media file using polymorphism
     * Notice: This method doesn't need to know what KIND of MediaFile it receives!
     */
    public void processMedia(MediaFile media) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🔄 STARTING MEDIA PROCESSING");
        System.out.println("=".repeat(60));
        
        // Display basic info (concrete method from abstract class)
        media.displayInfo();
        
        // Validate the file (abstract method - implementation depends on media type)
        if (!media.validate()) {
            System.out.println("❌ Media validation failed. Cannot process.");
            return;
        }
        
        // Create backup (final method from abstract class - same for all)
        media.createBackup();
        
        // Process the media (abstract method - different for each type)
        media.process();
        
        // Extract metadata (abstract method - type-specific)
        System.out.println(media.extractMetadata());
        
        // Compress the file (abstract method - optimized per type)
        long newSize = media.compress();
        
        // Generate preview (abstract method - type-specific)
        media.generatePreview();
        
        System.out.println("✅ Media processing completed successfully!");
        System.out.println("📊 Final size: " + media.formatFileSize(newSize));
    }
    
    /**
     * Demonstrates interface-based operations
     * Works with any object that implements the Streamable interface
     */
    public void setupStreaming(Streamable streamableMedia) {
        System.out.println("\n🌐 SETTING UP STREAMING SERVICE");
        System.out.println("-".repeat(40));
        
        streamableMedia.startStreaming(Streamable.QUALITY_HIGH);
        System.out.println("🔗 Stream URL: " + streamableMedia.getStreamingUrl());
        
        // Simulate streaming for a bit
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        streamableMedia.pauseStreaming();
        streamableMedia.stopStreaming();
    }
    
    /**
     * Demonstrates multiple interface usage
     * Works with any object that implements the Shareable interface
     */
    public void shareOnSocialMedia(Shareable shareableMedia) {
        System.out.println("\n📱 SOCIAL MEDIA SHARING");
        System.out.println("-".repeat(30));
        
        String shareLink = shareableMedia.generateShareableLink();
        System.out.println("🔗 Shareable link: " + shareLink);
        
        // Share on multiple platforms
        shareableMedia.shareOn(Shareable.Platform.TWITTER);
        shareableMedia.shareOn(Shareable.Platform.FACEBOOK);
        shareableMedia.shareOn(Shareable.Platform.INSTAGRAM);
    }
    
    /**
     * Demonstrates interface with default methods
     * Works with any Editable media
     */
    public void performQuickEdit(Editable editableMedia) {
        System.out.println("\n✏️ QUICK EDIT OPERATIONS");
        System.out.println("-".repeat(35));
        
        // Use default interface method
        editableMedia.performBasicOptimization();
        
        // Apply specific edits
        editableMedia.applyFilter("sharpen");
        editableMedia.resize(1920, 1080);
        
        System.out.println("🎨 Quick edit complete!");
    }
}

// ================================================================================================
// DEMONSTRATION: THE LIVING MANUSCRIPT IN ACTION
// ================================================================================================

/**
 * Main demonstration class showing real-world abstraction usage
 */
public class _PracticalExample {
    
    public static void main(String[] args) {
        
        System.out.println("🎬 WELCOME TO THE MEDIA PROCESSING SYSTEM");
        System.out.println("🌟 Demonstrating the Power of Abstraction!");
        System.out.println("=" .repeat(80));
        
        // ===== CREATE DIFFERENT TYPES OF MEDIA =====
        
        MediaFile[] mediaLibrary = {
            new AudioFile("Epic_Symphony.mp3", 8_500_000, "mp3", 
                         285, "Hans Zimmer", "Movie Soundtracks", 320),
            
            new VideoFile("Adventure_Movie.mp4", 1_200_000_000, "mp4",
                         7200, 1920, 1080, 24, "H.264", true),
            
            new DocumentFile("Project_Proposal.pdf", 2_500_000, "pdf",
                            45, "Sarah Johnson", true, "Business Proposal")
        };
        
        MediaProcessor processor = new MediaProcessor();
        
        // ===== POLYMORPHIC PROCESSING =====
        // Same method works with all media types!
        for (MediaFile media : mediaLibrary) {
            processor.processMedia(media);
        }
        
        // ===== INTERFACE-BASED OPERATIONS =====
        
        System.out.println("\n" + "🌟".repeat(40));
        System.out.println("INTERFACE-BASED OPERATIONS");
        System.out.println("🌟".repeat(40));
        
        // Work with Streamable media (Audio and Video implement this)
        for (MediaFile media : mediaLibrary) {
            if (media instanceof Streamable streamable) {
                processor.setupStreaming(streamable);
            }
        }
        
        // Work with Shareable media (All types implement this)
        for (MediaFile media : mediaLibrary) {
            if (media instanceof Shareable shareable) {
                processor.shareOnSocialMedia(shareable);
            }
        }
        
        // Work with Editable media (Only Video implements this)
        for (MediaFile media : mediaLibrary) {
            if (media instanceof Editable editable) {
                processor.performQuickEdit(editable);
            }
        }
        
        // ===== DEMONSTRATE INTERFACE STATIC METHODS =====
        
        System.out.println("\n📊 STREAMING QUALITY REFERENCE");
        System.out.println("-".repeat(35));
        System.out.println("Low: " + Streamable.getQualityName(Streamable.QUALITY_LOW));
        System.out.println("Medium: " + Streamable.getQualityName(Streamable.QUALITY_MEDIUM));
        System.out.println("High: " + Streamable.getQualityName(Streamable.QUALITY_HIGH));
        System.out.println("Ultra: " + Streamable.getQualityName(Streamable.QUALITY_ULTRA));
        
        // ===== DEMONSTRATE ABSTRACT CLASS POLYMORPHISM =====
        
        System.out.println("\n🔄 POLYMORPHIC METHOD CALLS");
        System.out.println("-".repeat(35));
        
        // All different implementations, same method call!
        for (MediaFile media : mediaLibrary) {
            System.out.println("\n📁 Processing: " + media.getFileName());
            String metadata = media.extractMetadata();  // Different for each type
            System.out.println(metadata);
        }
        
        System.out.println("\n✨ ABSTRACTION DEMONSTRATION COMPLETE!");
        System.out.println("🎯 Key Takeaways:");
        System.out.println("   • Abstract classes provide shared implementation + enforce contracts");
        System.out.println("   • Interfaces define pure capabilities without implementation");
        System.out.println("   • Polymorphism lets us work with objects through their abstractions");
        System.out.println("   • Multiple interfaces enable flexible, composable designs");
        System.out.println("   • Default methods provide shared functionality in interfaces");
        System.out.println("   • Static methods offer utility functions related to the contract");
        
        System.out.println("\n🌟 The power of Abstraction: Define the 'what' and let");
        System.out.println("   implementations handle the 'how' - creating flexible,");
        System.out.println("   extensible systems that work with future unknowns!");
    }
}

/*
 * ============================================================================
 * REAL-WORLD INSIGHTS FROM THIS EXAMPLE:
 * ============================================================================
 * 
 * 1. ABSTRACT CLASSES EXCEL AT:
 *    - Providing shared implementation (formatFileSize, displayInfo, createBackup)
 *    - Enforcing contracts (process, compress, validate must be implemented)
 *    - Maintaining common state (fileName, fileSizeBytes, format)
 *    - Constructor logic (setting upload time, validation)
 * 
 * 2. INTERFACES EXCEL AT:
 *    - Defining capabilities (Streamable, Shareable, Editable)
 *    - Multiple inheritance (VideoFile implements 3 interfaces)
 *    - Loose coupling (MediaProcessor works with any Streamable)
 *    - Constants and utility methods (quality levels, static methods)
 * 
 * 3. POLYMORPHISM BENEFITS:
 *    - Same code works with all media types (processMedia method)
 *    - Easy to add new media types without changing existing code
 *    - Interface-based programming (if instanceof checks)
 *    - Clean separation of concerns
 * 
 * 4. DESIGN PATTERNS EMERGING:
 *    - Template Method Pattern (MediaFile.processMedia defines the flow)
 *    - Strategy Pattern (Different compression strategies per media type)
 *    - Interface Segregation (Separate interfaces for different capabilities)
 * 
 * This is Abstraction in its full glory - creating systems that are both
 * flexible and powerful, working with concepts rather than concrete
 * implementations, enabling code that adapts to future requirements!
 */
