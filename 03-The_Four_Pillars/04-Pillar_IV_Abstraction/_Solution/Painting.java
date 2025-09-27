/*
 * CONCRETE IMPLEMENTATION: PAINTING
 * 
 * "Behold the ancient art of pigment upon canvas - where color and light dance
 * to capture moments eternal. This is art in its most traditional form."
 */

/*
 * EDUCATIONAL NOTE: COMPILATION SETUP
 * 
 * To compile and run these files:
 * 1. Place all .java files in the same directory
 * 2. Compile together: javac *.java
 * 3. Run: java GalleryDemo
 * 
 * In a real project, these would be properly packaged with imports:
 * import com.gallery.models.ArtPiece;
 * import com.gallery.interfaces.Sellable;
 */

/**
 * Traditional painting implementation extending ArtPiece and implementing Sellable.
 * 
 * Represents physical paintings - oil, acrylic, watercolor, mixed media works
 * on canvas, wood, or other traditional surfaces. These are the classic artworks
 * that have graced gallery walls for centuries.
 * 
 * This class demonstrates:
 * - Abstract class inheritance with complete method implementation
 * - Single interface implementation (Sellable)
 * - Traditional art-specific properties and behaviors
 * - Professional art handling and maintenance procedures
 */
public class Painting extends ArtPiece implements Sellable {
    
    // ===== PAINTING-SPECIFIC PROPERTIES =====
    
    private String paintType;        // Oil, acrylic, watercolor, mixed media, etc.
    private String canvasSize;       // Standard sizes like "16x20", "24x36", etc.
    private boolean isFramed;        // Whether currently framed for display
    private String surfaceType;     // Canvas, wood, paper, metal, etc.
    private boolean hasVarnish;     // Protective coating applied
    
    // ===== SELLABLE INTERFACE PROPERTIES =====
    
    private boolean forSale;         // Current sale availability
    private double askingPrice;      // Listed price if for sale
    private String salesHistory;     // Previous sale records
    
    // Constructor - creating a painting masterpiece
    public Painting(String title, String artist, int yearCreated, String dimensions,
                   double estimatedValue, String paintType, String canvasSize, 
                   boolean isFramed, String surfaceType) {
        
        // Call abstract parent constructor
        super(title, artist, yearCreated, dimensions, estimatedValue);
        
        // Initialize painting-specific properties
        this.paintType = paintType;
        this.canvasSize = canvasSize;
        this.isFramed = isFramed;
        this.surfaceType = surfaceType;
        this.hasVarnish = false;  // Applied later if needed
        
        // Initialize sellable properties
        this.forSale = false;
        this.askingPrice = 0.0;
        this.salesHistory = "New acquisition - no prior sales";
        
        System.out.println("🎨 New painting cataloged: \"" + title + "\"");
        System.out.println("   🖌️ Medium: " + paintType + " on " + surfaceType);
        System.out.println("   📏 Canvas Size: " + canvasSize);
        System.out.println("   🖼️ " + (isFramed ? "Currently framed" : "Unframed"));
    }
    
    // ===== IMPLEMENTING ABSTRACT METHODS FROM ARTPIECE =====
    
    @Override
    public String getArtMedium() {
        return paintType + " on " + surfaceType;
    }
    
    @Override
    public void performMaintenance() {
        System.out.println("🧽 PAINTING MAINTENANCE: \"" + title + "\"");
        System.out.println("═══════════════════════════════════════════");
        
        // Climate and environmental checks
        System.out.println("🌡️ Checking environmental conditions...");
        System.out.println("   • Temperature: 68-72°F ✅ Optimal");
        System.out.println("   • Humidity: 45-55% RH ✅ Within range");
        System.out.println("   • Light exposure: < 150 lux ✅ Protected");
        
        // Physical condition assessment
        System.out.println("🔍 Inspecting painting surface...");
        System.out.println("   • Checking for cracks, flaking, or damage");
        System.out.println("   • Examining " + paintType + " layer integrity");
        System.out.println("   • Assessing " + surfaceType + " condition");
        
        // Cleaning procedure
        System.out.println("🧹 Gentle surface cleaning...");
        System.out.println("   • Using soft, natural bristle brush");
        System.out.println("   • Removing dust and particulates");
        System.out.println("   • Avoiding any moisture on paint surface");
        
        // Frame inspection if applicable
        if (isFramed) {
            System.out.println("🖼️ Frame maintenance...");
            System.out.println("   • Cleaning frame surface and glass");
            System.out.println("   • Checking frame security and stability");
            System.out.println("   • Inspecting matting for acid content");
        }
        
        // Varnish assessment
        if (hasVarnish) {
            System.out.println("✨ Varnish layer assessment...");
            System.out.println("   • Checking for yellowing or cloudiness");
            System.out.println("   • Assessing protective effectiveness");
        } else if (paintType.equals("oil")) {
            System.out.println("💡 Recommendation: Consider varnish application for oil painting protection");
        }
        
        System.out.println("✅ Painting maintenance completed successfully!");
        
        // Update maintenance log
        String maintenanceDate = java.time.LocalDate.now().toString();
        System.out.println("📝 Maintenance logged: " + maintenanceDate);
    }
    
    @Override
    public double calculateSpaceRequired() {
        // Parse dimensions to calculate wall space needed
        // Assuming format like "24x36" or "24 x 36 inches"
        String[] parts = dimensions.toLowerCase()
                                  .replace("inches", "")
                                  .replace("in", "")
                                  .replace("\"", "")
                                  .trim()
                                  .split("x");
        
        try {
            double width = Double.parseDouble(parts[0].trim());
            double height = Double.parseDouble(parts[1].trim());
            
            // Convert to square feet and add display margin
            double squareFeet = (width * height) / 144.0;  // 144 sq in = 1 sq ft
            double displaySpace = squareFeet * 1.5;  // Add 50% for proper spacing
            
            System.out.println("📐 Space calculation for \"" + title + "\":");
            System.out.println("   Canvas: " + width + "\" x " + height + "\" = " + 
                             String.format("%.1f", squareFeet) + " sq ft");
            System.out.println("   Display space needed: " + String.format("%.1f", displaySpace) + " sq ft");
            
            return displaySpace;
            
        } catch (Exception e) {
            System.out.println("⚠️ Unable to parse dimensions: " + dimensions);
            return 10.0;  // Default reasonable wall space
        }
    }
    
    @Override
    public String generateCatalogEntry() {
        StringBuilder catalog = new StringBuilder();
        
        catalog.append("🎨 PAINTING CATALOG ENTRY\n");
        catalog.append("═══════════════════════════════════════\n");
        catalog.append("Title: ").append(title).append("\n");
        catalog.append("Artist: ").append(artist).append(" (").append(yearCreated).append(")\n");
        catalog.append("Medium: ").append(getArtMedium()).append("\n");
        catalog.append("Dimensions: ").append(dimensions).append("\n");
        catalog.append("Canvas Size: ").append(canvasSize).append("\n");
        catalog.append("Presentation: ").append(isFramed ? "Framed" : "Unframed").append("\n");
        catalog.append("Surface Protection: ").append(hasVarnish ? "Varnished" : "Natural finish").append("\n");
        catalog.append("Age: ").append(getAgeInYears()).append(" years\n");
        catalog.append("Estimated Value: $").append(String.format("%.2f", estimatedValue)).append("\n");
        catalog.append("Insurance Value: $").append(String.format("%.2f", calculateInsuranceValue())).append("\n");
        catalog.append("Display Status: ").append(isOnDisplay ? "On Exhibition" : "In Storage").append("\n");
        
        if (forSale) {
            catalog.append("AVAILABLE FOR PURCHASE: $").append(String.format("%.2f", askingPrice)).append("\n");
            catalog.append("Final Price (with tax): $").append(String.format("%.2f", getFinalPrice())).append("\n");
        }
        
        catalog.append("Sales History: ").append(salesHistory).append("\n");
        catalog.append("Space Required: ").append(String.format("%.1f", calculateSpaceRequired())).append(" sq ft\n");
        catalog.append("═══════════════════════════════════════");
        
        return catalog.toString();
    }
    
    @Override
    public boolean requiresSpecialHandling() {
        // Paintings generally need careful but not extreme handling
        boolean oldAndFragile = getAgeInYears() > 100;
        boolean largeSize = dimensions.contains("48") || dimensions.contains("60") || dimensions.contains("72");
        boolean unvarnishedOil = paintType.equals("oil") && !hasVarnish;
        
        if (oldAndFragile || largeSize || unvarnishedOil) {
            System.out.println("⚠️ \"" + title + "\" requires special handling:");
            if (oldAndFragile) System.out.println("   • Age-related fragility concerns");
            if (largeSize) System.out.println("   • Large size requires multiple handlers");
            if (unvarnishedOil) System.out.println("   • Unprotected oil paint surface");
            return true;
        }
        
        return false;
    }
    
    // ===== IMPLEMENTING SELLABLE INTERFACE =====
    
    @Override
    public void putOnSale(double askingPrice) {
        if (askingPrice <= 0) {
            throw new IllegalArgumentException("Asking price must be positive");
        }
        
        if (!Sellable.isReasonablePrice(askingPrice, estimatedValue)) {
            System.out.println("⚠️ Warning: Asking price significantly differs from estimated value");
            System.out.println("   Estimated: $" + String.format("%.2f", estimatedValue));
            System.out.println("   Asking: $" + String.format("%.2f", askingPrice));
        }
        
        this.askingPrice = askingPrice;
        this.forSale = true;
        
        System.out.println("🏷️ \"" + title + "\" now available for purchase!");
        System.out.println("   💰 Asking Price: $" + String.format("%.2f", askingPrice));
        System.out.println("   💵 Final Price (with tax): $" + String.format("%.2f", getFinalPrice()));
        System.out.println("   💸 Seller receives: $" + String.format("%.2f", calculateNetProceeds(askingPrice)));
        
        // Update sales history
        String listingDate = java.time.LocalDate.now().toString();
        salesHistory = "Listed for sale on " + listingDate + " at $" + String.format("%.2f", askingPrice);
    }
    
    @Override
    public void removeFromSale() {
        if (!forSale) {
            System.out.println("ℹ️ \"" + title + "\" is not currently for sale");
            return;
        }
        
        System.out.println("📋 Removing \"" + title + "\" from sale");
        this.forSale = false;
        this.askingPrice = 0.0;
        
        // Update sales history
        String removalDate = java.time.LocalDate.now().toString();
        salesHistory += " | Removed from sale on " + removalDate;
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
            throw new IllegalStateException("Painting is not currently for sale");
        }
        
        if (buyerName == null || buyerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Buyer name cannot be empty");
        }
        
        System.out.println("🤝 PROCESSING SALE: \"" + title + "\"");
        System.out.println("════════════════════════════════════════");
        
        double salePrice = askingPrice;
        double tax = salePrice * TAX_RATE;
        double commission = salePrice * COMMISSION_RATE;
        double finalPrice = salePrice + tax;
        double sellerReceives = salePrice - commission;
        
        System.out.println("👤 Buyer: " + buyerName);
        System.out.println("🎨 Artwork: " + getBasicInfo());
        System.out.println("💰 Sale Price: $" + String.format("%.2f", salePrice));
        System.out.println("🏛️ Gallery Commission: $" + String.format("%.2f", commission));
        System.out.println("💸 Tax: $" + String.format("%.2f", tax));
        System.out.println("💵 Total Paid by Buyer: $" + String.format("%.2f", finalPrice));
        System.out.println("💴 Net to Seller: $" + String.format("%.2f", sellerReceives));
        
        // Generate documentation
        String documentation = generateSalesDocumentation(buyerName, salePrice);
        System.out.println("\n" + documentation);
        
        // Complete the sale
        this.forSale = false;
        this.askingPrice = 0.0;
        
        // Update sales history
        String saleDate = java.time.LocalDate.now().toString();
        salesHistory = "SOLD to " + buyerName + " on " + saleDate + 
                      " for $" + String.format("%.2f", salePrice);
        
        System.out.println("✅ Sale completed successfully!");
        System.out.println("📋 Ownership transferred to: " + buyerName);
        System.out.println("📝 Transaction recorded in gallery records");
    }
    
    // ===== PAINTING-SPECIFIC METHODS =====
    
    /**
     * Apply protective varnish to the painting surface.
     * Important for oil paintings and some acrylics.
     */
    public void applyVarnish() {
        if (hasVarnish) {
            System.out.println("ℹ️ \"" + title + "\" already has varnish protection");
            return;
        }
        
        System.out.println("✨ Applying protective varnish to \"" + title + "\"");
        
        if (paintType.equals("oil")) {
            System.out.println("   🎨 Using archival quality varnish suitable for oil paint");
            System.out.println("   ⏱️ Allowing proper drying time between coats");
        } else if (paintType.equals("acrylic")) {
            System.out.println("   🎨 Using acrylic-compatible varnish system");
        } else {
            System.out.println("   ⚠️ Special varnish considerations for " + paintType);
        }
        
        this.hasVarnish = true;
        System.out.println("✅ Varnish application completed - painting now protected");
    }
    
    /**
     * Frame or reframe the painting.
     */
    public void setFraming(boolean shouldFrame, String frameDescription) {
        if (shouldFrame && !isFramed) {
            System.out.println("🖼️ Framing \"" + title + "\" with " + frameDescription);
            this.isFramed = true;
        } else if (!shouldFrame && isFramed) {
            System.out.println("📤 Removing frame from \"" + title + "\"");
            this.isFramed = false;
        } else {
            System.out.println("ℹ️ No framing changes needed for \"" + title + "\"");
        }
    }
    
    /**
     * Professional condition assessment report.
     */
    public String generateConditionReport() {
        StringBuilder report = new StringBuilder();
        
        report.append("📋 CONDITION REPORT: ").append(title).append("\n");
        report.append("═══════════════════════════════════════\n");
        report.append("Assessment Date: ").append(java.time.LocalDate.now()).append("\n");
        report.append("Medium: ").append(getArtMedium()).append("\n");
        report.append("Age: ").append(getAgeInYears()).append(" years\n\n");
        
        report.append("OVERALL CONDITION: ");
        if (getAgeInYears() < 25) {
            report.append("Excellent ✅\n");
        } else if (getAgeInYears() < 75) {
            report.append("Good with normal aging 👍\n");
        } else {
            report.append("Requires monitoring due to age ⚠️\n");
        }
        
        report.append("\nPROTECTIVE MEASURES:\n");
        report.append("• Varnish Protection: ").append(hasVarnish ? "Applied ✅" : "Not applied ⚠️").append("\n");
        report.append("• Framing Status: ").append(isFramed ? "Properly framed ✅" : "Unframed 📝").append("\n");
        report.append("• Special Handling: ").append(requiresSpecialHandling() ? "Required ⚠️" : "Standard ✅").append("\n");
        
        report.append("\nRECOMMENDations:\n");
        if (!hasVarnish && paintType.equals("oil")) {
            report.append("• Consider varnish application for protection\n");
        }
        if (!isFramed) {
            report.append("• Professional framing recommended for display\n");
        }
        if (getAgeInYears() > 50) {
            report.append("• Annual professional conservation assessment advised\n");
        }
        
        return report.toString();
    }
    
    // ===== ADDITIONAL GETTERS FOR PAINTING PROPERTIES =====
    
    public String getPaintType() { return paintType; }
    public String getCanvasSize() { return canvasSize; }
    public boolean isFramed() { return isFramed; }
    public String getSurfaceType() { return surfaceType; }
    public boolean hasVarnish() { return hasVarnish; }
    public String getSalesHistory() { return salesHistory; }
    
    // ===== ENHANCED DISPLAY FOR PAINTINGS =====
    
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call parent's display method
        
        // Add painting-specific information
        System.out.println("   🎨 Paint Type: " + paintType);
        System.out.println("   📐 Canvas Size: " + canvasSize);
        System.out.println("   🖼️ Framing: " + (isFramed ? "Framed" : "Unframed"));
        System.out.println("   ✨ Varnish: " + (hasVarnish ? "Protected" : "Natural finish"));
        
        if (forSale) {
            System.out.println("   🏷️ FOR SALE: $" + String.format("%.2f", askingPrice));
        }
        
        System.out.println("🎨 ═══════════════════════════════════════");
    }
}

/*
 * PAINTING CLASS WISDOM DEMONSTRATED:
 * 
 * 1. CONCRETE IMPLEMENTATION: All abstract methods from ArtPiece are fully
 *    implemented with painting-specific logic and behavior
 * 
 * 2. INTERFACE IMPLEMENTATION: Sellable interface is completely implemented
 *    with commercial functionality appropriate for traditional art sales
 * 
 * 3. DOMAIN-SPECIFIC FUNCTIONALITY: Methods like applyVarnish() and setFraming()
 *    are specific to physical paintings and wouldn't apply to digital art
 * 
 * 4. PROFESSIONAL STANDARDS: Maintenance, condition reporting, and handling
 *    procedures reflect real-world art conservation practices
 * 
 * 5. BUSINESS INTEGRATION: Sales functionality integrates seamlessly with
 *    artistic properties - pricing considers age, condition, and provenance
 * 
 * 6. INHERITANCE BENEFITS: Inherits all common artwork behavior while adding
 *    specialized painting functionality
 * 
 * This class shows how concrete implementations can be both specialized
 * (painting-specific features) and conformant to abstractions (ArtPiece
 * and Sellable contracts). The result is code that works polymorphically
 * while maintaining the unique characteristics of traditional paintings.
 * 
 * "A painting is art made tangible - pigment and vision united on canvas,
 *  carrying both the universal essence of artistic expression and the
 *  specific traditions of the painter's craft."
 */
