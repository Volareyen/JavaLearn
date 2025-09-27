/*
 * CONCRETE IMPLEMENTATION: DIGITAL ART
 * 
 * "Where pixels replace pigments and code becomes canvas - the art of the digital age
 * transcends physical bounds to create experiences limited only by imagination."
 * 
 * COMPILATION NOTE: This file depends on ArtPiece.java and the interface files.
 * Compile all solution files together: javac *.java
 */

/**
 * Digital art implementation demonstrating multiple interface inheritance.
 * 
 * Represents digital artworks - computer graphics, generative art, digital paintings,
 * video art, interactive media. These pieces exist primarily in the digital realm
 * and require technology for both creation and display.
 * 
 * This class demonstrates:
 * - Multiple interface implementation (Displayable, Interactive, Sellable)
 * - Technology-dependent art management
 * - Digital-specific behaviors and considerations
 * - Complex interaction between different capabilities
 */
public class DigitalArt extends ArtPiece implements Displayable, Interactive, Sellable {
    
    // ===== DIGITAL ART SPECIFIC PROPERTIES =====
    
    private String resolution;         // "1920x1080", "4K", "8K", etc.
    private String fileFormat;        // "MP4", "PNG", "GIF", "Interactive App"
    private boolean requiresProjector; // For large-scale displays
    private double fileSizeMB;        // Digital file size
    private String softwareUsed;      // Creation software/tools
    private boolean hasSound;         // Audio component present
    
    // ===== DISPLAYABLE INTERFACE PROPERTIES =====
    
    private boolean isDisplayOn;      // Current display state
    private int currentBrightness;    // Current brightness level
    private int batteryLevel;         // For portable displays (-1 if hardwired)
    private long displayStartTime;    // When display was turned on
    
    // ===== INTERACTIVE INTERFACE PROPERTIES =====
    
    private boolean interactionEnabled; // Interaction mode status
    private int touchCount;             // Touch interactions today
    private int motionCount;            // Motion interactions today
    private int voiceCount;             // Voice interactions today
    private long sessionStartTime;     // Current interaction session start
    
    // ===== SELLABLE INTERFACE PROPERTIES =====
    
    private boolean forSale;           // Sale availability
    private double askingPrice;        // Listed price
    private String digitalRightsInfo; // Licensing and rights information
    private boolean includesSourceFiles; // Whether source files included
    
    // Constructor - creating digital masterpiece
    public DigitalArt(String title, String artist, int yearCreated, String dimensions,
                     double estimatedValue, String resolution, String fileFormat,
                     boolean requiresProjector, double fileSizeMB, String softwareUsed) {
        
        // Call abstract parent constructor
        super(title, artist, yearCreated, dimensions, estimatedValue);
        
        // Initialize digital-specific properties
        this.resolution = resolution;
        this.fileFormat = fileFormat;
        this.requiresProjector = requiresProjector;
        this.fileSizeMB = fileSizeMB;
        this.softwareUsed = softwareUsed;
        this.hasSound = fileFormat.toLowerCase().contains("mp4") || 
                       fileFormat.toLowerCase().contains("wav") ||
                       fileFormat.toLowerCase().contains("interactive");
        
        // Initialize display properties
        this.isDisplayOn = false;
        this.currentBrightness = STANDARD_BRIGHTNESS;
        this.batteryLevel = requiresProjector ? -1 : 85; // Projectors are hardwired
        
        // Initialize interaction properties
        this.interactionEnabled = false;
        this.touchCount = 0;
        this.motionCount = 0;
        this.voiceCount = 0;
        
        // Initialize sellable properties
        this.forSale = false;
        this.askingPrice = 0.0;
        this.digitalRightsInfo = "All rights reserved - Single display license";
        this.includesSourceFiles = false;
        
        System.out.println("💻 Digital artwork cataloged: \"" + title + "\"");
        System.out.println("   📺 Resolution: " + resolution);
        System.out.println("   💾 Format: " + fileFormat + " (" + fileSizeMB + " MB)");
        System.out.println("   🔧 Created with: " + softwareUsed);
        System.out.println("   📽️ " + (requiresProjector ? "Requires projector" : "Standard display"));
    }
    
    // ===== IMPLEMENTING ABSTRACT METHODS FROM ARTPIECE =====
    
    @Override
    public String getArtMedium() {
        String medium = "Digital " + fileFormat;
        if (hasSound) medium += " with audio";
        return medium;
    }
    
    @Override
    public void performMaintenance() {
        System.out.println("🔧 DIGITAL ART MAINTENANCE: \"" + title + "\"");
        System.out.println("═══════════════════════════════════════════════");
        
        // File integrity checks
        System.out.println("💾 Digital file integrity check...");
        System.out.println("   • Verifying file checksums and integrity");
        System.out.println("   • Scanning for digital corruption or artifacts");
        System.out.println("   • Confirming " + fileFormat + " file structure");
        System.out.println("   • File size verified: " + fileSizeMB + " MB");
        
        // Backup verification
        System.out.println("💿 Backup system verification...");
        System.out.println("   • Checking primary backup (Local NAS)");
        System.out.println("   • Verifying cloud backup (Redundant storage)");
        System.out.println("   • Testing archive backup (Long-term preservation)");
        
        // Display equipment check
        System.out.println("📺 Display equipment maintenance...");
        if (requiresProjector) {
            System.out.println("   • Projector lamp hours: 1,247 (Good condition)");
            System.out.println("   • Cleaning projector filters and vents");
            System.out.println("   • Calibrating projector color accuracy");
        } else {
            System.out.println("   • Monitor calibration and color profile update");
            System.out.println("   • Screen cleaning and dead pixel check");
        }
        
        // Interactive system maintenance
        if (fileFormat.toLowerCase().contains("interactive")) {
            System.out.println("🎯 Interactive system maintenance...");
            System.out.println("   • Testing touch response and calibration");
            System.out.println("   • Verifying motion sensor accuracy");
            System.out.println("   • Audio system check and calibration");
        }
        
        // Software and compatibility
        System.out.println("⚙️ Software environment check...");
        System.out.println("   • Runtime environment: Up to date ✅");
        System.out.println("   • Media codecs: Current versions ✅");
        System.out.println("   • Security updates: Applied ✅");
        
        System.out.println("✅ Digital art maintenance completed successfully!");
    }
    
    @Override
    public double calculateSpaceRequired() {
        // Digital art space depends on display method
        if (requiresProjector) {
            // Large projection needs significant space
            System.out.println("📐 Projection space calculation:");
            System.out.println("   Projector installation space: 200 sq ft");
            System.out.println("   Viewing area: 300 sq ft");
            System.out.println("   Equipment clearance: 100 sq ft");
            return 600.0; // Large installation space
        } else {
            // Standard display space
            System.out.println("📐 Display space calculation:");
            System.out.println("   Display unit: 25 sq ft");
            System.out.println("   Viewing area: 75 sq ft");
            System.out.println("   Interactive zone: 50 sq ft");
            return 150.0; // Moderate display space
        }
    }
    
    @Override
    public String generateCatalogEntry() {
        StringBuilder catalog = new StringBuilder();
        
        catalog.append("💻 DIGITAL ART CATALOG ENTRY\n");
        catalog.append("═══════════════════════════════════════\n");
        catalog.append("Title: ").append(title).append("\n");
        catalog.append("Artist: ").append(artist).append(" (").append(yearCreated).append(")\n");
        catalog.append("Medium: ").append(getArtMedium()).append("\n");
        catalog.append("Technical Specs:\n");
        catalog.append("  • Resolution: ").append(resolution).append("\n");
        catalog.append("  • File Format: ").append(fileFormat).append("\n");
        catalog.append("  • File Size: ").append(fileSizeMB).append(" MB\n");
        catalog.append("  • Software: ").append(softwareUsed).append("\n");
        catalog.append("  • Display: ").append(requiresProjector ? "Projection system" : "Standard display").append("\n");
        catalog.append("  • Audio: ").append(hasSound ? "Yes" : "None").append("\n");
        catalog.append("Interactive Features: ").append(fileFormat.toLowerCase().contains("interactive") ? "Yes" : "Static").append("\n");
        catalog.append("Age: ").append(getAgeInYears()).append(" years\n");
        catalog.append("Estimated Value: $").append(String.format("%.2f", estimatedValue)).append("\n");
        catalog.append("Space Required: ").append(String.format("%.0f", calculateSpaceRequired())).append(" sq ft\n");
        catalog.append("Display Status: ").append(isOnDisplay ? "On Exhibition" : "In Storage").append("\n");
        
        if (forSale) {
            catalog.append("AVAILABLE FOR PURCHASE:\n");
            catalog.append("  • Price: $").append(String.format("%.2f", askingPrice)).append("\n");
            catalog.append("  • Rights: ").append(digitalRightsInfo).append("\n");
            catalog.append("  • Source Files: ").append(includesSourceFiles ? "Included" : "Display version only").append("\n");
        }
        
        catalog.append("═══════════════════════════════════════");
        return catalog.toString();
    }
    
    @Override
    public boolean requiresSpecialHandling() {
        // Digital art has different "special handling" considerations
        boolean largeFile = fileSizeMB > 1000; // 1GB+
        boolean complexInteraction = fileFormat.toLowerCase().contains("interactive");
        boolean projectiveDisplay = requiresProjector;
        boolean customSoftware = !fileFormat.toLowerCase().matches("jpg|png|mp4|gif");
        
        if (largeFile || complexInteraction || projectiveDisplay || customSoftware) {
            System.out.println("⚠️ \"" + title + "\" requires special technical handling:");
            if (largeFile) System.out.println("   • Large file size requires high-performance systems");
            if (complexInteraction) System.out.println("   • Interactive features need specialized setup");
            if (projectiveDisplay) System.out.println("   • Projection system installation required");
            if (customSoftware) System.out.println("   • Custom software/runtime environment needed");
            return true;
        }
        
        return false;
    }
    
    // ===== IMPLEMENTING DISPLAYABLE INTERFACE =====
    
    @Override
    public void turnOn() {
        if (isDisplayOn) {
            System.out.println("ℹ️ \"" + title + "\" display is already active");
            return;
        }
        
        System.out.println("🔌 Powering on digital display for \"" + title + "\"");
        
        if (requiresProjector) {
            System.out.println("   📽️ Starting projector systems...");
            System.out.println("   🔆 Projector warming up (estimated 30 seconds)");
        } else {
            System.out.println("   📺 Initializing display monitor...");
        }
        
        System.out.println("   💾 Loading " + fileFormat + " file (" + fileSizeMB + " MB)");
        
        if (hasSound) {
            System.out.println("   🔊 Initializing audio systems...");
        }
        
        if (fileFormat.toLowerCase().contains("interactive")) {
            System.out.println("   🎯 Starting interactive runtime environment...");
            enableInteractionMode(); // Auto-enable interaction for interactive pieces
        }
        
        this.isDisplayOn = true;
        this.displayStartTime = System.currentTimeMillis();
        
        System.out.println("✨ \"" + title + "\" display is now active and ready!");
    }
    
    @Override
    public void turnOff() {
        if (!isDisplayOn) {
            System.out.println("ℹ️ \"" + title + "\" display is already off");
            return;
        }
        
        System.out.println("🔽 Shutting down display for \"" + title + "\"");
        
        // Disable interactions first
        if (interactionEnabled) {
            disableInteractionMode();
        }
        
        if (hasSound) {
            System.out.println("   🔇 Stopping audio playback...");
        }
        
        System.out.println("   💾 Closing digital file safely...");
        
        if (requiresProjector) {
            System.out.println("   📽️ Projector cooling down...");
        }
        
        // Calculate display session time
        long sessionTime = (System.currentTimeMillis() - displayStartTime) / 1000;
        System.out.println("   ⏱️ Display session: " + sessionTime + " seconds");
        
        this.isDisplayOn = false;
        System.out.println("🌙 \"" + title + "\" display safely powered down");
    }
    
    @Override
    public void setBrightness(int level) {
        if (!isValidBrightness(level)) {
            System.out.println("❌ Invalid brightness level: " + level + " (must be 0-100)");
            return;
        }
        
        int oldBrightness = this.currentBrightness;
        this.currentBrightness = level;
        
        System.out.println("🔆 Adjusting \"" + title + "\" brightness: " + 
                          oldBrightness + "% → " + level + "%");
        System.out.println("   Setting: " + getBrightnessDescription(level));
        
        if (requiresProjector) {
            System.out.println("   📽️ Adjusting projector lamp intensity");
        } else {
            System.out.println("   📺 Updating display backlight level");
        }
    }
    
    @Override
    public int getBatteryLevel() {
        if (requiresProjector) {
            return -1; // Projectors are typically hardwired
        }
        
        // Simulate battery drain during display
        if (isDisplayOn) {
            long displayMinutes = (System.currentTimeMillis() - displayStartTime) / 60000;
            int drainPerHour = Math.max(5, currentBrightness / 10); // Higher brightness = more drain
            int totalDrain = (int)(displayMinutes * drainPerHour / 60);
            batteryLevel = Math.max(0, batteryLevel - totalDrain);
        }
        
        return batteryLevel;
    }
    
    // ===== IMPLEMENTING INTERACTIVE INTERFACE =====
    
    @Override
    public void respondToTouch() {
        if (!interactionEnabled) {
            System.out.println("ℹ️ Touch detected, but interaction mode not enabled");
            return;
        }
        
        touchCount++;
        System.out.println("👆 Touch interaction #" + touchCount + " on \"" + title + "\"");
        
        if (fileFormat.toLowerCase().contains("interactive")) {
            System.out.println("   🎨 Digital art responding to touch input...");
            System.out.println("   ✨ Visual elements shifting and responding");
            showVisualFeedback(75, "blue");
        } else {
            System.out.println("   📋 Touch registered - displaying artwork information");
            showVisualFeedback(50, "white");
        }
        
        playInteractionFeedback("touch");
        logInteraction("touch", 1);
    }
    
    @Override
    public void respondToMotion() {
        if (!interactionEnabled) {
            System.out.println("ℹ️ Motion detected, but interaction mode not enabled");
            return;
        }
        
        motionCount++;
        System.out.println("🏃 Motion interaction #" + motionCount + " on \"" + title + "\"");
        
        if (fileFormat.toLowerCase().contains("interactive")) {
            System.out.println("   🌊 Art responding to movement with fluid animations");
            System.out.println("   🎭 Visual elements following user motion");
            showVisualFeedback(80, "green");
        } else {
            System.out.println("   👀 Motion tracking - highlighting different areas");
            showVisualFeedback(60, "yellow");
        }
        
        playInteractionFeedback("motion");
        logInteraction("motion", 1);
    }
    
    @Override
    public void respondToVoice(String command) {
        if (!interactionEnabled) {
            System.out.println("ℹ️ Voice detected, but interaction mode not enabled");
            return;
        }
        
        voiceCount++;
        System.out.println("🎤 Voice interaction #" + voiceCount + ": \"" + command + "\"");
        
        // Process basic voice commands
        String lowerCommand = command.toLowerCase();
        
        if (lowerCommand.contains("brighter") || lowerCommand.contains("bright")) {
            setBrightness(Math.min(100, currentBrightness + 20));
        } else if (lowerCommand.contains("dimmer") || lowerCommand.contains("dim")) {
            setBrightness(Math.max(10, currentBrightness - 20));
        } else if (lowerCommand.contains("info") || lowerCommand.contains("about")) {
            System.out.println("   🗣️ Providing audio description of artwork...");
            System.out.println("   📢 \"" + getBasicInfo() + " - " + getArtMedium() + "\"");
        } else if (lowerCommand.contains("reset")) {
            resetInteractionState();
        } else {
            System.out.println("   🎨 Art interpreting voice as creative input");
            if (fileFormat.toLowerCase().contains("interactive")) {
                System.out.println("   🎵 Artwork generating visual response to vocal tones");
                showVisualFeedback(90, "purple");
            }
        }
        
        playInteractionFeedback("voice");
        logInteraction("voice", 1);
    }
    
    @Override
    public void resetInteractionState() {
        System.out.println("🔄 Resetting \"" + title + "\" to default interactive state");
        
        if (fileFormat.toLowerCase().contains("interactive")) {
            System.out.println("   🎨 Returning art to original composition");
            System.out.println("   🔄 Clearing all user-generated modifications");
        }
        
        setBrightness(STANDARD_BRIGHTNESS);
        System.out.println("   ✅ Interactive state reset complete");
    }
    
    // ===== IMPLEMENTING SELLABLE INTERFACE =====
    
    @Override
    public void putOnSale(double askingPrice) {
        if (askingPrice <= 0) {
            throw new IllegalArgumentException("Asking price must be positive");
        }
        
        this.askingPrice = askingPrice;
        this.forSale = true;
        
        System.out.println("🏷️ \"" + title + "\" now available for digital purchase!");
        System.out.println("   💰 Asking Price: $" + String.format("%.2f", askingPrice));
        System.out.println("   💵 Final Price (with tax): $" + String.format("%.2f", getFinalPrice()));
        System.out.println("   📄 Rights: " + digitalRightsInfo);
        System.out.println("   💾 Source Files: " + (includesSourceFiles ? "Included" : "Display version only"));
        
        // Digital art specific considerations
        System.out.println("   🔒 Digital licensing terms apply");
        System.out.println("   💿 Backup and archival copies included");
    }
    
    @Override
    public void removeFromSale() {
        if (!forSale) {
            System.out.println("ℹ️ \"" + title + "\" is not currently for sale");
            return;
        }
        
        System.out.println("📋 Removing \"" + title + "\" from digital marketplace");
        this.forSale = false;
        this.askingPrice = 0.0;
    }
    
    @Override
    public boolean isForSale() {
        return forSale;
    }
    
    @Override
    public double getFinalPrice() {
        if (!forSale) return 0.0;
        return askingPrice * (1.0 + TAX_RATE);
    }
    
    @Override
    public void processTransaction(String buyerName) {
        if (!forSale) {
            throw new IllegalStateException("Digital art is not currently for sale");
        }
        
        System.out.println("💻 PROCESSING DIGITAL ART SALE: \"" + title + "\"");
        System.out.println("══════════════════════════════════════════════");
        
        double salePrice = askingPrice;
        double tax = salePrice * TAX_RATE;
        double commission = salePrice * COMMISSION_RATE;
        double finalPrice = salePrice + tax;
        
        System.out.println("👤 Buyer: " + buyerName);
        System.out.println("🎨 Digital Artwork: " + getBasicInfo());
        System.out.println("💰 Sale Price: $" + String.format("%.2f", salePrice));
        System.out.println("🏛️ Gallery Commission: $" + String.format("%.2f", commission));
        System.out.println("💸 Tax: $" + String.format("%.2f", tax));
        System.out.println("💵 Total: $" + String.format("%.2f", finalPrice));
        
        // Digital-specific transfer process
        System.out.println("\n💾 DIGITAL TRANSFER PROCESS:");
        System.out.println("   📁 Preparing digital file package...");
        System.out.println("   🔐 Generating unique license key for buyer");
        System.out.println("   📤 Creating secure download link");
        
        if (includesSourceFiles) {
            System.out.println("   🎨 Packaging source files and project data");
            System.out.println("   📚 Including creation documentation and assets");
        }
        
        System.out.println("   📧 Sending digital delivery to: " + buyerName);
        System.out.println("   🔒 License restrictions: " + digitalRightsInfo);
        
        // Complete the sale
        this.forSale = false;
        this.askingPrice = 0.0;
        
        // Generate digital receipt
        String documentation = generateSalesDocumentation(buyerName, salePrice);
        System.out.println("\n" + documentation);
        
        System.out.println("✅ Digital art sale completed successfully!");
        System.out.println("📧 License and download information sent to buyer");
    }
    
    // ===== DIGITAL ART SPECIFIC METHODS =====
    
    /**
     * Update digital rights and licensing terms.
     */
    public void setDigitalRights(String rightsInfo, boolean includeSourceFiles) {
        this.digitalRightsInfo = rightsInfo;
        this.includesSourceFiles = includeSourceFiles;
        
        System.out.println("📄 Updated digital rights for \"" + title + "\":");
        System.out.println("   Rights: " + rightsInfo);
        System.out.println("   Source Files: " + (includeSourceFiles ? "Included" : "Not included"));
    }
    
    /**
     * Create backup of digital artwork files.
     */
    public void createDigitalBackup(String backupLocation) {
        System.out.println("💿 Creating backup of \"" + title + "\"");
        System.out.println("   📁 Source: " + fileFormat + " (" + fileSizeMB + " MB)");
        System.out.println("   📍 Destination: " + backupLocation);
        System.out.println("   🔐 Encryption: AES-256");
        System.out.println("   ✅ Backup completed with integrity verification");
    }
    
    /**
     * Get interaction statistics for the current day.
     */
    public String getInteractionStats() {
        return generateInteractionStats(touchCount, motionCount, voiceCount, 
                                      interactionEnabled ? 60 : 0); // Assume 60 min sessions
    }
    
    // ===== ENHANCED DISPLAY FOR DIGITAL ART =====
    
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call parent's display method
        
        // Add digital-specific information
        System.out.println("   📺 Resolution: " + resolution);
        System.out.println("   💾 Format: " + fileFormat + " (" + fileSizeMB + " MB)");
        System.out.println("   🔧 Software: " + softwareUsed);
        System.out.println("   📽️ Display: " + (requiresProjector ? "Projection" : "Standard"));
        System.out.println("   🔊 Audio: " + (hasSound ? "Yes" : "None"));
        System.out.println("   🎯 Interactive: " + (fileFormat.toLowerCase().contains("interactive") ? "Yes" : "Static"));
        System.out.println("   🔋 Power: " + (getBatteryLevel() == -1 ? "Hardwired" : getBatteryLevel() + "%"));
        System.out.println("   📱 Status: " + (isDisplayOn ? "Display Active" : "Display Off"));
        
        if (forSale) {
            System.out.println("   🏷️ FOR SALE: $" + String.format("%.2f", askingPrice));
            System.out.println("   📄 Rights: " + digitalRightsInfo);
        }
        
        System.out.println("💻 ═══════════════════════════════════════");
    }
}

/*
 * DIGITAL ART CLASS WISDOM DEMONSTRATED:
 * 
 * 1. MULTIPLE INTERFACE IMPLEMENTATION: Shows how a single class can fulfill
 *    multiple contracts (Displayable, Interactive, Sellable) simultaneously
 * 
 * 2. INTERFACE INTERACTION: Demonstrates how different interfaces can work
 *    together - turning on display auto-enables interaction for interactive pieces
 * 
 * 3. TECHNOLOGY-DEPENDENT BEHAVIOR: All implementations consider the digital
 *    nature - file handling, display requirements, interactive capabilities
 * 
 * 4. DIFFERENT ABSTRACTION LEVELS: Same abstract methods (from ArtPiece) have
 *    completely different implementations than Painting - digital vs physical
 * 
 * 5. CONTRACT FULFILLMENT: Each interface is fully implemented with behavior
 *    appropriate to digital art - brightness affects battery, interactions
 *    modify digital content, sales include licensing considerations
 * 
 * 6. CAPABILITY COMPOSITION: The class gains rich functionality by implementing
 *    multiple interfaces - it CAN display, CAN interact, CAN sell
 * 
 * This demonstrates the power of interface-based design: the same code that
 * manages a Painting can also manage DigitalArt, but each provides specialized
 * implementations appropriate to their medium.
 * 
 * "Digital art transcends physical limitations - it can be interactive,
 *  infinitely reproducible, and globally accessible, yet still maintains
 *  the essential artistic essence that connects it to all creative expression."
 */
