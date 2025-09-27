/**
 * THE LIVING MANUSCRIPT: Media Processing System
 * 
 * A comprehensive demonstration of Polymorphism principles through a
 * realistic media processing system. This example showcases runtime
 * polymorphism, interface implementation, method overloading, dynamic
 * method dispatch, and the power of writing code once that works with
 * infinite variations.
 * 
 * This system demonstrates:
 * - Runtime polymorphism with media file hierarchies
 * - Interface-based polymorphism for processing operations
 * - Method overloading for different parameter combinations
 * - Dynamic method dispatch showing runtime behavior selection
 * - Plugin architecture where new media types work automatically
 * - Casting and instanceof for safe type checking and conversion
 */

public class _PracticalExample {
    public static void main(String[] args) {
        System.out.println("=== MEDIA PROCESSING SYSTEM ===");
        System.out.println("Demonstrating Polymorphism - The Third Pillar\n");
        
        // DEMONSTRATION 1: Media File Hierarchy and Runtime Polymorphism
        System.out.println("1. MEDIA FILE HIERARCHY AND RUNTIME POLYMORPHISM:");
        
        // Create different media files - same reference type, different object types
        MediaFile[] mediaLibrary = {
            new VideoFile("vacation.mp4", 1024, "1920x1080", 30),
            new AudioFile("song.mp3", 256, 320, 180),
            new ImageFile("photo.jpg", 512, 4096, 3072),
            new VideoFile("tutorial.avi", 2048, "1280x720", 25),
            new AudioFile("podcast.wav", 128, 128, 3600),
            new ImageFile("artwork.png", 1024, 2048, 2048)
        };
        
        // Polymorphic processing - same method calls, different behaviors
        for (MediaFile media : mediaLibrary) {
            media.displayInfo();        // Polymorphic - each type displays differently
            media.play();              // Polymorphic - each type plays differently
            System.out.println("File size: " + media.getFileSizeKB() + " KB");
            System.out.println();
        }
        
        // DEMONSTRATION 2: Interface-Based Polymorphism
        System.out.println("2. INTERFACE-BASED POLYMORPHISM:");
        
        MediaProcessor processor = new MediaProcessor();
        
        // Create objects that implement processing interfaces
        Compressible[] compressibleFiles = {
            new VideoFile("large_video.mkv", 5120, "4K", 60),
            new AudioFile("uncompressed.wav", 1024, 1411, 240),
            new ImageFile("raw_photo.tiff", 2048, 8192, 6144)
        };
        
        // Polymorphic compression - same interface, different implementations
        for (Compressible file : compressibleFiles) {
            processor.compressMedia(file);  // Polymorphic compression
        }
        System.out.println();
        
        // DEMONSTRATION 3: Multiple Interface Implementation
        System.out.println("3. MULTIPLE INTERFACE IMPLEMENTATION:");
        
        VideoFile video = new VideoFile("movie.mp4", 3072, "1920x1080", 24);
        
        // Same object can be treated as different interface types
        Playable playableVideo = video;      // Video as Playable
        Compressible compressibleVideo = video;  // Video as Compressible
        Convertible convertibleVideo = video;    // Video as Convertible
        
        // Polymorphic usage through different interfaces
        processor.playMedia(playableVideo);
        processor.compressMedia(compressibleVideo);
        processor.convertMedia(convertibleVideo, "avi");
        System.out.println();
        
        // DEMONSTRATION 4: Method Overloading (Compile-time Polymorphism)
        System.out.println("4. METHOD OVERLOADING DEMONSTRATION:");
        
        MediaConverter converter = new MediaConverter();
        
        // Same method name, different parameters - resolved at compile time
        converter.convert(new VideoFile("test.mp4", 1024, "720p", 30));  // VideoFile version
        converter.convert(new AudioFile("test.mp3", 128, 320, 120));     // AudioFile version
        converter.convert("input.mov", "output.mp4");                    // String version
        converter.convert("batch.txt", new String[]{"mp4", "avi", "mkv"}); // Batch version
        System.out.println();
        
        // DEMONSTRATION 5: Plugin Architecture with Polymorphism
        System.out.println("5. PLUGIN ARCHITECTURE DEMONSTRATION:");
        
        MediaPlayer player = new MediaPlayer();
        
        // Register different codec plugins
        player.registerCodec(new MP4Codec());
        player.registerCodec(new MP3Codec());
        player.registerCodec(new JPEGCodec());
        player.registerCodec(new AVICodec());
        
        // Polymorphic playback - player automatically selects correct codec
        String[] testFiles = {"movie.mp4", "song.mp3", "image.jpg", "video.avi", "unknown.xyz"};
        
        for (String filename : testFiles) {
            player.playFile(filename);  // Polymorphic codec selection
        }
        System.out.println();
        
        // DEMONSTRATION 6: Casting and Type Safety
        System.out.println("6. CASTING AND TYPE SAFETY DEMONSTRATION:");
        
        // Process media files with type-specific operations
        for (MediaFile media : mediaLibrary) {
            processMediaWithCasting(media);
        }
        
        // DEMONSTRATION 7: Advanced Polymorphic Patterns
        System.out.println("7. ADVANCED POLYMORPHIC PATTERNS:");
        
        // Factory pattern with polymorphism
        MediaFactory factory = new MediaFactory();
        
        MediaFile createdVideo = factory.createMedia("video", "created.mp4", 2048);
        MediaFile createdAudio = factory.createMedia("audio", "created.mp3", 256);
        MediaFile createdImage = factory.createMedia("image", "created.jpg", 1024);
        
        // All created through same factory method, but different types
        MediaFile[] createdFiles = {createdVideo, createdAudio, createdImage};
        
        for (MediaFile file : createdFiles) {
            file.play();  // Polymorphic playback
        }
        
        // DEMONSTRATION 8: Polymorphic Collections and Filtering
        System.out.println("8. POLYMORPHIC COLLECTIONS AND FILTERING:");
        
        MediaLibrary library = new MediaLibrary();
        
        // Add all media to library
        for (MediaFile media : mediaLibrary) {
            library.addMedia(media);
        }
        
        // Polymorphic operations on entire collection
        library.playAll();           // Play all media polymorphically
        library.compressAll();       // Compress all compressible media
        library.generateReport();    // Analyze all media types
        
        System.out.println("=== POLYMORPHISM DEMONSTRATION COMPLETE ===");
        System.out.println("The Art of Many Forms has been mastered!");
    }
    
    /**
     * Demonstrates safe casting and type-specific operations
     */
    public static void processMediaWithCasting(MediaFile media) {
        System.out.println("Processing: " + media.getFilename());
        
        // Polymorphic methods work on any MediaFile
        media.play();  // Calls correct overridden method
        
        // Type-specific operations using safe casting
        if (media instanceof VideoFile) {
            VideoFile video = (VideoFile) media;
            System.out.println("  Video resolution: " + video.getResolution());
            System.out.println("  Frame rate: " + video.getFrameRate() + " fps");
            video.adjustBrightness(10);  // Video-specific method
            
        } else if (media instanceof AudioFile) {
            AudioFile audio = (AudioFile) media;
            System.out.println("  Bitrate: " + audio.getBitrate() + " kbps");
            System.out.println("  Duration: " + audio.getDurationSeconds() + " seconds");
            audio.adjustVolume(0.8);  // Audio-specific method
            
        } else if (media instanceof ImageFile) {
            ImageFile image = (ImageFile) media;
            System.out.println("  Dimensions: " + image.getWidth() + "x" + image.getHeight());
            image.rotate(90);  // Image-specific method
        }
        
        System.out.println();
    }
}

/**
 * BASE CLASS: MediaFile - Foundation for all media types
 */
abstract class MediaFile implements Playable {
    protected String filename;
    protected long fileSizeKB;
    protected String format;
    
    public MediaFile(String filename, long fileSizeKB) {
        this.filename = filename;
        this.fileSizeKB = fileSizeKB;
        this.format = extractFormat(filename);
    }
    
    // CONCRETE METHODS: Shared by all media files
    public String getFilename() { return filename; }
    public long getFileSizeKB() { return fileSizeKB; }
    public String getFormat() { return format; }
    
    // TEMPLATE METHOD: Uses polymorphic methods
    public void displayInfo() {
        System.out.println("=== " + getMediaType() + " FILE ===");
        System.out.println("Filename: " + filename);
        System.out.println("Format: " + format);
        System.out.println("Size: " + fileSizeKB + " KB");
        displaySpecificInfo();  // Polymorphic call
    }
    
    // ABSTRACT METHODS: Must be implemented by subclasses
    public abstract String getMediaType();
    protected abstract void displaySpecificInfo();
    
    // HELPER METHOD
    private String extractFormat(String filename) {
        int lastDot = filename.lastIndexOf('.');
        return lastDot > 0 ? filename.substring(lastDot + 1).toUpperCase() : "UNKNOWN";
    }
}

/**
 * VIDEO FILE CLASS: Specialized media file for videos
 */
class VideoFile extends MediaFile implements Compressible, Convertible {
    private String resolution;
    private int frameRate;
    
    public VideoFile(String filename, long fileSizeKB, String resolution, int frameRate) {
        super(filename, fileSizeKB);
        this.resolution = resolution;
        this.frameRate = frameRate;
    }
    
    // IMPLEMENT ABSTRACT METHODS
    @Override
    public String getMediaType() {
        return "VIDEO";
    }
    
    @Override
    protected void displaySpecificInfo() {
        System.out.println("Resolution: " + resolution);
        System.out.println("Frame Rate: " + frameRate + " fps");
    }
    
    // IMPLEMENT PLAYABLE INTERFACE
    @Override
    public void play() {
        System.out.println("Playing video: " + filename + " at " + resolution + " resolution");
    }
    
    @Override
    public void pause() {
        System.out.println("Video paused: " + filename);
    }
    
    @Override
    public void stop() {
        System.out.println("Video stopped: " + filename);
    }
    
    // IMPLEMENT COMPRESSIBLE INTERFACE
    @Override
    public void compress(int compressionLevel) {
        long newSize = fileSizeKB * (100 - compressionLevel) / 100;
        System.out.println("Compressing video " + filename + " from " + fileSizeKB + 
                          " KB to " + newSize + " KB");
        fileSizeKB = newSize;
    }
    
    @Override
    public double getCompressionRatio() {
        return 0.3;  // Videos can be compressed to 30% of original size
    }
    
    // IMPLEMENT CONVERTIBLE INTERFACE
    @Override
    public void convertTo(String newFormat) {
        System.out.println("Converting video " + filename + " from " + format + 
                          " to " + newFormat.toUpperCase());
        this.format = newFormat.toUpperCase();
        
        // Update filename
        int lastDot = filename.lastIndexOf('.');
        if (lastDot > 0) {
            filename = filename.substring(0, lastDot) + "." + newFormat.toLowerCase();
        }
    }
    
    @Override
    public String[] getSupportedFormats() {
        return new String[]{"MP4", "AVI", "MKV", "MOV", "WMV"};
    }
    
    // VIDEO-SPECIFIC METHODS
    public String getResolution() { return resolution; }
    public int getFrameRate() { return frameRate; }
    
    public void adjustBrightness(int brightness) {
        System.out.println("Adjusting video brightness by " + brightness + "%");
    }
    
    public void addSubtitles(String subtitleFile) {
        System.out.println("Adding subtitles from " + subtitleFile + " to " + filename);
    }
}

/**
 * AUDIO FILE CLASS: Specialized media file for audio
 */
class AudioFile extends MediaFile implements Compressible, Convertible {
    private int bitrate;  // kbps
    private int durationSeconds;
    
    public AudioFile(String filename, long fileSizeKB, int bitrate, int durationSeconds) {
        super(filename, fileSizeKB);
        this.bitrate = bitrate;
        this.durationSeconds = durationSeconds;
    }
    
    // IMPLEMENT ABSTRACT METHODS
    @Override
    public String getMediaType() {
        return "AUDIO";
    }
    
    @Override
    protected void displaySpecificInfo() {
        System.out.println("Bitrate: " + bitrate + " kbps");
        System.out.println("Duration: " + durationSeconds + " seconds");
    }
    
    // IMPLEMENT PLAYABLE INTERFACE
    @Override
    public void play() {
        System.out.println("Playing audio: " + filename + " at " + bitrate + " kbps");
    }
    
    @Override
    public void pause() {
        System.out.println("Audio paused: " + filename);
    }
    
    @Override
    public void stop() {
        System.out.println("Audio stopped: " + filename);
    }
    
    // IMPLEMENT COMPRESSIBLE INTERFACE
    @Override
    public void compress(int compressionLevel) {
        int newBitrate = bitrate * (100 - compressionLevel) / 100;
        long newSize = fileSizeKB * (100 - compressionLevel) / 100;
        System.out.println("Compressing audio " + filename + " from " + bitrate + 
                          " kbps to " + newBitrate + " kbps");
        bitrate = newBitrate;
        fileSizeKB = newSize;
    }
    
    @Override
    public double getCompressionRatio() {
        return 0.1;  // Audio can be compressed to 10% of original size
    }
    
    // IMPLEMENT CONVERTIBLE INTERFACE
    @Override
    public void convertTo(String newFormat) {
        System.out.println("Converting audio " + filename + " from " + format + 
                          " to " + newFormat.toUpperCase());
        this.format = newFormat.toUpperCase();
        
        // Update filename
        int lastDot = filename.lastIndexOf('.');
        if (lastDot > 0) {
            filename = filename.substring(0, lastDot) + "." + newFormat.toLowerCase();
        }
    }
    
    @Override
    public String[] getSupportedFormats() {
        return new String[]{"MP3", "WAV", "FLAC", "AAC", "OGG"};
    }
    
    // AUDIO-SPECIFIC METHODS
    public int getBitrate() { return bitrate; }
    public int getDurationSeconds() { return durationSeconds; }
    
    public void adjustVolume(double volumeLevel) {
        System.out.println("Adjusting audio volume to " + (volumeLevel * 100) + "%");
    }
    
    public void addEcho(int delayMs) {
        System.out.println("Adding echo effect with " + delayMs + "ms delay");
    }
}

/**
 * IMAGE FILE CLASS: Specialized media file for images
 */
class ImageFile extends MediaFile implements Compressible, Convertible {
    private int width;
    private int height;
    
    public ImageFile(String filename, long fileSizeKB, int width, int height) {
        super(filename, fileSizeKB);
        this.width = width;
        this.height = height;
    }
    
    // IMPLEMENT ABSTRACT METHODS
    @Override
    public String getMediaType() {
        return "IMAGE";
    }
    
    @Override
    protected void displaySpecificInfo() {
        System.out.println("Dimensions: " + width + "x" + height + " pixels");
        System.out.println("Aspect Ratio: " + String.format("%.2f", (double)width/height));
    }
    
    // IMPLEMENT PLAYABLE INTERFACE
    @Override
    public void play() {
        System.out.println("Displaying image: " + filename + " (" + width + "x" + height + ")");
    }
    
    @Override
    public void pause() {
        System.out.println("Image display paused: " + filename);
    }
    
    @Override
    public void stop() {
        System.out.println("Image display stopped: " + filename);
    }
    
    // IMPLEMENT COMPRESSIBLE INTERFACE
    @Override
    public void compress(int compressionLevel) {
        long newSize = fileSizeKB * (100 - compressionLevel) / 100;
        System.out.println("Compressing image " + filename + " with " + compressionLevel + 
                          "% compression (quality reduction)");
        fileSizeKB = newSize;
    }
    
    @Override
    public double getCompressionRatio() {
        return 0.05;  // Images can be heavily compressed to 5% of original size
    }
    
    // IMPLEMENT CONVERTIBLE INTERFACE
    @Override
    public void convertTo(String newFormat) {
        System.out.println("Converting image " + filename + " from " + format + 
                          " to " + newFormat.toUpperCase());
        this.format = newFormat.toUpperCase();
        
        // Update filename
        int lastDot = filename.lastIndexOf('.');
        if (lastDot > 0) {
            filename = filename.substring(0, lastDot) + "." + newFormat.toLowerCase();
        }
    }
    
    @Override
    public String[] getSupportedFormats() {
        return new String[]{"JPG", "PNG", "GIF", "BMP", "TIFF", "WEBP"};
    }
    
    // IMAGE-SPECIFIC METHODS
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
    public void rotate(int degrees) {
        System.out.println("Rotating image " + filename + " by " + degrees + " degrees");
        if (degrees == 90 || degrees == 270) {
            // Swap width and height for 90/270 degree rotations
            int temp = width;
            width = height;
            height = temp;
        }
    }
    
    public void resize(int newWidth, int newHeight) {
        System.out.println("Resizing image from " + width + "x" + height + 
                          " to " + newWidth + "x" + newHeight);
        this.width = newWidth;
        this.height = newHeight;
    }
}

/**
 * PLAYABLE INTERFACE: Contract for media that can be played
 */
interface Playable {
    void play();
    void pause();
    void stop();
}

/**
 * COMPRESSIBLE INTERFACE: Contract for media that can be compressed
 */
interface Compressible {
    void compress(int compressionLevel);  // 0-100 percentage
    double getCompressionRatio();         // Maximum compression ratio
}

/**
 * CONVERTIBLE INTERFACE: Contract for media that can be converted
 */
interface Convertible {
    void convertTo(String newFormat);
    String[] getSupportedFormats();
}

/**
 * MEDIA PROCESSOR: Demonstrates polymorphic processing operations
 */
class MediaProcessor {
    
    // Polymorphic method - works with any Playable
    public void playMedia(Playable media) {
        System.out.println("Starting playback...");
        media.play();  // Polymorphic call
    }
    
    // Polymorphic method - works with any Compressible
    public void compressMedia(Compressible media) {
        System.out.println("Compressing media...");
        media.compress(50);  // Polymorphic call - 50% compression
        System.out.println("Max compression ratio: " + (media.getCompressionRatio() * 100) + "%");
    }
    
    // Polymorphic method - works with any Convertible
    public void convertMedia(Convertible media, String targetFormat) {
        System.out.println("Converting media to " + targetFormat + "...");
        String[] supported = media.getSupportedFormats();
        System.out.print("Supported formats: ");
        for (String format : supported) {
            System.out.print(format + " ");
        }
        System.out.println();
        media.convertTo(targetFormat);  // Polymorphic call
    }
    
    // Method that accepts multiple interface types
    public void processMedia(MediaFile media) {
        // Always can play (MediaFile implements Playable)
        playMedia(media);
        
        // Check for additional capabilities
        if (media instanceof Compressible) {
            compressMedia((Compressible) media);
        }
        
        if (media instanceof Convertible) {
            convertMedia((Convertible) media, "mp4");
        }
    }
}

/**
 * MEDIA CONVERTER: Demonstrates method overloading (compile-time polymorphism)
 */
class MediaConverter {
    
    // OVERLOADED METHODS: Same name, different parameters
    
    // Convert VideoFile
    public void convert(VideoFile video) {
        System.out.println("Converting VideoFile: " + video.getFilename());
        System.out.println("  Original resolution: " + video.getResolution());
        video.convertTo("mp4");
    }
    
    // Convert AudioFile
    public void convert(AudioFile audio) {
        System.out.println("Converting AudioFile: " + audio.getFilename());
        System.out.println("  Original bitrate: " + audio.getBitrate() + " kbps");
        audio.convertTo("mp3");
    }
    
    // Convert by filename (String parameters)
    public void convert(String inputFile, String outputFile) {
        System.out.println("Converting file: " + inputFile + " -> " + outputFile);
        String inputFormat = inputFile.substring(inputFile.lastIndexOf('.') + 1);
        String outputFormat = outputFile.substring(outputFile.lastIndexOf('.') + 1);
        System.out.println("  Format conversion: " + inputFormat + " -> " + outputFormat);
    }
    
    // Batch conversion
    public void convert(String inputFile, String[] outputFormats) {
        System.out.println("Batch converting " + inputFile + " to multiple formats:");
        for (String format : outputFormats) {
            System.out.println("  Creating " + inputFile.replace(".mov", "." + format));
        }
    }
    
    // Convert with quality settings
    public void convert(MediaFile media, String format, int quality) {
        System.out.println("Converting " + media.getFilename() + " to " + format + 
                          " with quality " + quality + "%");
        if (media instanceof Convertible) {
            ((Convertible) media).convertTo(format);
        }
    }
}

/**
 * CODEC INTERFACE: Plugin contract for media codecs
 */
interface Codec {
    boolean canHandle(String filename);
    void decode(String filename);
    String getCodecName();
    String[] getSupportedExtensions();
}

/**
 * MP4 CODEC: Specific codec implementation
 */
class MP4Codec implements Codec {
    @Override
    public boolean canHandle(String filename) {
        return filename.toLowerCase().endsWith(".mp4");
    }
    
    @Override
    public void decode(String filename) {
        System.out.println("MP4 Codec: Decoding " + filename + " with H.264/AAC");
    }
    
    @Override
    public String getCodecName() {
        return "MP4 Codec";
    }
    
    @Override
    public String[] getSupportedExtensions() {
        return new String[]{".mp4", ".m4v"};
    }
}

/**
 * MP3 CODEC: Another codec implementation
 */
class MP3Codec implements Codec {
    @Override
    public boolean canHandle(String filename) {
        return filename.toLowerCase().endsWith(".mp3");
    }
    
    @Override
    public void decode(String filename) {
        System.out.println("MP3 Codec: Decoding " + filename + " with MPEG Audio Layer 3");
    }
    
    @Override
    public String getCodecName() {
        return "MP3 Codec";
    }
    
    @Override
    public String[] getSupportedExtensions() {
        return new String[]{".mp3"};
    }
}

/**
 * JPEG CODEC: Image codec implementation
 */
class JPEGCodec implements Codec {
    @Override
    public boolean canHandle(String filename) {
        String lower = filename.toLowerCase();
        return lower.endsWith(".jpg") || lower.endsWith(".jpeg");
    }
    
    @Override
    public void decode(String filename) {
        System.out.println("JPEG Codec: Decoding " + filename + " with JPEG compression");
    }
    
    @Override
    public String getCodecName() {
        return "JPEG Codec";
    }
    
    @Override
    public String[] getSupportedExtensions() {
        return new String[]{".jpg", ".jpeg"};
    }
}

/**
 * AVI CODEC: Legacy video codec
 */
class AVICodec implements Codec {
    @Override
    public boolean canHandle(String filename) {
        return filename.toLowerCase().endsWith(".avi");
    }
    
    @Override
    public void decode(String filename) {
        System.out.println("AVI Codec: Decoding " + filename + " with legacy AVI format");
    }
    
    @Override
    public String getCodecName() {
        return "AVI Codec";
    }
    
    @Override
    public String[] getSupportedExtensions() {
        return new String[]{".avi"};
    }
}

/**
 * MEDIA PLAYER: Plugin architecture using polymorphism
 */
class MediaPlayer {
    private Codec[] registeredCodecs;
    private int codecCount;
    
    public MediaPlayer() {
        registeredCodecs = new Codec[10];
        codecCount = 0;
    }
    
    // Register codec plugins
    public void registerCodec(Codec codec) {
        if (codecCount < registeredCodecs.length) {
            registeredCodecs[codecCount] = codec;
            codecCount++;
            System.out.println("Registered codec: " + codec.getCodecName());
        }
    }
    
    // Polymorphic file playback
    public void playFile(String filename) {
        System.out.println("Attempting to play: " + filename);
        
        // Find appropriate codec polymorphically
        for (int i = 0; i < codecCount; i++) {
            if (registeredCodecs[i].canHandle(filename)) {  // Polymorphic call
                registeredCodecs[i].decode(filename);       // Polymorphic call
                return;
            }
        }
        
        System.out.println("No codec found for: " + filename);
    }
    
    public void listCodecs() {
        System.out.println("Registered Codecs:");
        for (int i = 0; i < codecCount; i++) {
            Codec codec = registeredCodecs[i];
            System.out.println("  " + codec.getCodecName() + " - Supports: " + 
                             String.join(", ", codec.getSupportedExtensions()));
        }
    }
}

/**
 * MEDIA FACTORY: Factory pattern with polymorphism
 */
class MediaFactory {
    
    // Polymorphic factory method
    public MediaFile createMedia(String type, String filename, long sizeKB) {
        System.out.println("Creating " + type + " media: " + filename);
        
        switch (type.toLowerCase()) {
            case "video":
                return new VideoFile(filename, sizeKB, "1920x1080", 30);
            case "audio":
                return new AudioFile(filename, sizeKB, 320, 180);
            case "image":
                return new ImageFile(filename, sizeKB, 1920, 1080);
            default:
                throw new IllegalArgumentException("Unknown media type: " + type);
        }
    }
    
    // Overloaded factory methods
    public VideoFile createVideo(String filename, long sizeKB, String resolution, int fps) {
        return new VideoFile(filename, sizeKB, resolution, fps);
    }
    
    public AudioFile createAudio(String filename, long sizeKB, int bitrate, int duration) {
        return new AudioFile(filename, sizeKB, bitrate, duration);
    }
    
    public ImageFile createImage(String filename, long sizeKB, int width, int height) {
        return new ImageFile(filename, sizeKB, width, height);
    }
}

/**
 * MEDIA LIBRARY: Collection management with polymorphism
 */
class MediaLibrary {
    private MediaFile[] mediaFiles;
    private int fileCount;
    
    public MediaLibrary() {
        mediaFiles = new MediaFile[100];
        fileCount = 0;
    }
    
    public void addMedia(MediaFile media) {
        if (fileCount < mediaFiles.length) {
            mediaFiles[fileCount] = media;
            fileCount++;
        }
    }
    
    // Polymorphic operations on entire collection
    public void playAll() {
        System.out.println("Playing all media in library:");
        for (int i = 0; i < fileCount; i++) {
            mediaFiles[i].play();  // Polymorphic call
        }
        System.out.println();
    }
    
    public void compressAll() {
        System.out.println("Compressing all compressible media:");
        for (int i = 0; i < fileCount; i++) {
            if (mediaFiles[i] instanceof Compressible) {
                Compressible compressible = (Compressible) mediaFiles[i];
                compressible.compress(30);  // 30% compression
            }
        }
        System.out.println();
    }
    
    public void generateReport() {
        System.out.println("=== MEDIA LIBRARY REPORT ===");
        
        int videoCount = 0, audioCount = 0, imageCount = 0;
        long totalSize = 0;
        
        for (int i = 0; i < fileCount; i++) {
            MediaFile media = mediaFiles[i];
            totalSize += media.getFileSizeKB();
            
            // Polymorphic type checking
            if (media instanceof VideoFile) videoCount++;
            else if (media instanceof AudioFile) audioCount++;
            else if (media instanceof ImageFile) imageCount++;
        }
        
        System.out.println("Total files: " + fileCount);
        System.out.println("Videos: " + videoCount);
        System.out.println("Audio: " + audioCount);
        System.out.println("Images: " + imageCount);
        System.out.println("Total size: " + totalSize + " KB");
        System.out.println();
    }
}

/**
 * POLYMORPHISM MASTERY DEMONSTRATED:
 * 
 * This media processing system showcases complete mastery of polymorphism through:
 * 
 * 1. RUNTIME POLYMORPHISM:
 *    - MediaFile hierarchy with VideoFile, AudioFile, ImageFile
 *    - Same method calls produce different behaviors based on actual object type
 *    - Dynamic method dispatch selects correct implementation at runtime
 * 
 * 2. INTERFACE POLYMORPHISM:
 *    - Multiple interfaces (Playable, Compressible, Convertible)
 *    - Same object can be treated as different interface types
 *    - Plugin architecture with Codec interface and multiple implementations
 * 
 * 3. METHOD OVERLOADING:
 *    - MediaConverter class with multiple convert() methods
 *    - Same method name, different parameters resolved at compile time
 *    - Factory methods with overloaded creation patterns
 * 
 * 4. MULTIPLE INTERFACE IMPLEMENTATION:
 *    - Media classes implement multiple interfaces simultaneously
 *    - Objects can be cast to different interface types safely
 *    - Rich polymorphic behavior through interface combinations
 * 
 * 5. SAFE CASTING AND TYPE CHECKING:
 *    - instanceof usage for safe type identification
 *    - Explicit casting to access type-specific methods
 *    - Graceful handling of unknown types
 * 
 * 6. PLUGIN ARCHITECTURE:
 *    - MediaPlayer with registered Codec plugins
 *    - New codecs work automatically without changing player code
 *    - Polymorphic codec selection based on file type
 * 
 * 7. FACTORY PATTERNS:
 *    - MediaFactory creates different types through same interface
 *    - Polymorphic object creation based on parameters
 *    - Overloaded factory methods for different creation patterns
 * 
 * 8. COLLECTION POLYMORPHISM:
 *    - MediaLibrary operates on MediaFile array polymorphically
 *    - Same operations work on all media types
 *    - Type-specific behavior when needed through casting
 * 
 * The result is a flexible, extensible media processing system where:
 * - New media types can be added without changing existing code
 * - Processing operations work with any compatible media type
 * - Plugin architecture allows runtime extension
 * - Type-specific behavior is available when needed
 * - Code is written once but works with infinite variations
 * 
 * This demonstrates that polymorphism is the ultimate expression of
 * "write once, work with many" - creating systems that are both
 * specific enough to be useful and general enough to be infinitely
 * extensible through the power of dynamic method dispatch and
 * interface contracts.
 */
