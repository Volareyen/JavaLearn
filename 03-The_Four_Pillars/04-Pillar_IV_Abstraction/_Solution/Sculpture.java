/*
 * CONCRETE IMPLEMENTATION: SCULPTURE
 * 
 * "From stone and metal, form emerges - three-dimensional poetry that commands
 * space and invites contemplation from every angle. This is art given mass and presence."
 * 
 * COMPILATION NOTE: This file depends on ArtPiece.java and Sellable.java.
 * Compile all solution files together: javac *.java
 */

/**
 * Sculpture implementation extending ArtPiece and implementing Sellable.
 * 
 * Represents three-dimensional artworks - stone sculptures, metal works,
 * ceramic pieces, mixed media installations. These pieces exist in physical
 * space and often require specialized handling, installation, and maintenance.
 * 
 * This class demonstrates:
 * - Abstract class inheritance with sculpture-specific implementations
 * - Single interface implementation (Sellable) focused on physical art sales
 * - Weight, material, and space considerations unique to 3D art
 * - Professional sculpture handling and conservation practices
 */
public class Sculpture extends ArtPiece implements Sellable {
    
    // ===== SCULPTURE-SPECIFIC PROPERTIES =====
    
    private String material;          // Bronze, marble, steel, ceramic, mixed media, etc.
    private double weightPounds;      // Physical weight for handling/shipping
    private boolean isOutdoorSafe;    // Weather resistance for outdoor display
    private String constructionMethod; // Cast, carved, welded, assembled, etc.
    private boolean hasMovingParts;   // Kinetic sculptures with motion
    private String baseType;          // Pedestal, floor mount, wall mount, suspended
    private boolean requiresSpecialLighting; // Dramatic lighting needs
    
    // ===== SELLABLE INTERFACE PROPERTIES =====
    
    private boolean forSale;          // Sale availability
    private double askingPrice;       // Listed price
    private String provenanceHistory; // Ownership and exhibition history
    private boolean includesBase;     // Whether display base included
    private String shippingNotes;     // Special shipping considerations
    
    // Constructor - creating sculptural masterpiece
    public Sculpture(String title, String artist, int yearCreated, String dimensions,
                    double estimatedValue, String material, double weightPounds,
                    boolean isOutdoorSafe, String constructionMethod, String baseType) {
        
        // Call abstract parent constructor
        super(title, artist, yearCreated, dimensions, estimatedValue);
        
        // Initialize sculpture-specific properties
        this.material = material;
        this.weightPounds = weightPounds;
        this.isOutdoorSafe = isOutdoorSafe;
        this.constructionMethod = constructionMethod;
        this.baseType = baseType;
        this.hasMovingParts = constructionMethod.toLowerCase().contains("kinetic") ||
                             constructionMethod.toLowerCase().contains("mobile");
        this.requiresSpecialLighting = material.toLowerCase().contains("bronze") ||
                                      material.toLowerCase().contains("metal") ||
                                      weightPounds > 100; // Large pieces often need dramatic lighting
        
        // Initialize sellable properties
        this.forSale = false;
        this.askingPrice = 0.0;
        this.provenanceHistory = "Direct from artist studio";
        this.includesBase = !baseType.equals("floor mount"); // Floor mounts typically don't include base
        this.shippingNotes = generateShippingNotes();
        
        System.out.println("🗿 Sculpture cataloged: \"" + title + "\"");
        System.out.println("   🪨 Material: " + material + " (" + constructionMethod + ")");
        System.out.println("   ⚖️ Weight: " + weightPounds + " lbs");
        System.out.println("   🏛️ Base: " + baseType);
        System.out.println("   🌦️ " + (isOutdoorSafe ? "Weather-resistant" : "Indoor display only"));
    }
    
    // ===== IMPLEMENTING ABSTRACT METHODS FROM ARTPIECE =====
    
    @Override
    public String getArtMedium() {
        String medium = material + " sculpture";
        if (hasMovingParts) medium += " (kinetic)";
        return medium;
    }
    
    @Override
    public void performMaintenance() {
        System.out.println("🔧 SCULPTURE MAINTENANCE: \"" + title + "\"");
        System.out.println("═══════════════════════════════════════════");
        
        // Environmental assessment
        System.out.println("🌡️ Environmental condition check...");
        if (isOutdoorSafe) {
            System.out.println("   • Weather exposure assessment (outdoor piece)");
            System.out.println("   • UV damage inspection and protective measures");
            System.out.println("   • Moisture and corrosion check");
        } else {
            System.out.println("   • Indoor climate control verification");
            System.out.println("   • Humidity levels: 45-55% RH ✅ Optimal");
            System.out.println("   • Temperature stability: 68-72°F ✅ Stable");
        }
        
        // Material-specific maintenance
        System.out.println("🪨 Material-specific care for " + material + "...");
        performMaterialSpecificMaintenance();
        
        // Structural integrity check
        System.out.println("🏗️ Structural integrity assessment...");
        System.out.println("   • Foundation and base stability check");
        System.out.println("   • Joint and connection point inspection");
        System.out.println("   • Weight distribution verification");
        
        if (hasMovingParts) {
            System.out.println("⚙️ Kinetic mechanism maintenance...");
            System.out.println("   • Motor and drive system lubrication");
            System.out.println("   • Movement range and safety check");
            System.out.println("   • Electrical system inspection");
        }
        
        // Base and mounting system
        System.out.println("🏛️ Base and mounting system check...");
        System.out.println("   • " + baseType + " stability and security");
        System.out.println("   • Anti-tip mechanisms verification");
        System.out.println("   • Visitor safety barriers assessment");
        
        // Cleaning procedure
        System.out.println("🧽 Professional cleaning procedure...");
        performMaterialSpecificCleaning();
        
        if (requiresSpecialLighting) {
            System.out.println("💡 Lighting system maintenance...");
            System.out.println("   • Fixture positioning and intensity check");
            System.out.println("   • Color temperature optimization for " + material);
            System.out.println("   • Shadow and highlight balance adjustment");
        }
        
        System.out.println("✅ Sculpture maintenance completed successfully!");
        
        // Document maintenance
        String maintenanceDate = java.time.LocalDate.now().toString();
        System.out.println("📝 Maintenance logged: " + maintenanceDate);
    }
    
    @Override
    public double calculateSpaceRequired() {
        // Parse dimensions and add safety clearance
        String[] parts = dimensions.toLowerCase()
                                  .replace("inches", "")
                                  .replace("in", "")
                                  .replace("feet", "")
                                  .replace("ft", "")
                                  .replace("\"", "")
                                  .replace("'", "")
                                  .trim()
                                  .split("x");
        
        try {
            double width = Double.parseDouble(parts[0].trim());
            double depth = parts.length > 1 ? Double.parseDouble(parts[1].trim()) : width;
            
            // Assume dimensions are in inches, convert to feet
            if (width > 50 || depth > 50) { // Likely already in inches
                width = width / 12.0;
                depth = depth / 12.0;
            }
            
            // Calculate base space and add safety clearance
            double baseArea = width * depth;
            double clearanceMultiplier = 2.5; // 150% additional space for viewing
            
            // Adjust for special requirements
            if (hasMovingParts) clearanceMultiplier = 3.0; // More space for kinetic pieces
            if (weightPounds > 500) clearanceMultiplier = 2.8; // More space for very large pieces
            if (baseType.equals("suspended")) baseArea *= 1.5; // Overhead suspension needs more floor space
            
            double totalSpace = baseArea * clearanceMultiplier;
            
            System.out.println("📐 Space calculation for \"" + title + "\":");
            System.out.println("   Base footprint: " + String.format("%.1f", baseArea) + " sq ft");
            System.out.println("   Safety/viewing clearance: " + (int)((clearanceMultiplier - 1) * 100) + "%");
            System.out.println("   Total space needed: " + String.format("%.1f", totalSpace) + " sq ft");
            
            return totalSpace;
            
        } catch (Exception e) {
            System.out.println("⚠️ Unable to parse dimensions: " + dimensions);
            // Default based on weight
            if (weightPounds > 500) return 200.0; // Very large piece
            if (weightPounds > 100) return 100.0; // Large piece
            return 50.0; // Standard sculpture space
        }
    }
    
    @Override
    public String generateCatalogEntry() {
        StringBuilder catalog = new StringBuilder();
        
        catalog.append("🗿 SCULPTURE CATALOG ENTRY\n");
        catalog.append("═══════════════════════════════════════\n");
        catalog.append("Title: ").append(title).append("\n");
        catalog.append("Artist: ").append(artist).append(" (").append(yearCreated).append(")\n");
        catalog.append("Medium: ").append(getArtMedium()).append("\n");
        catalog.append("Dimensions: ").append(dimensions).append("\n");
        catalog.append("Weight: ").append(weightPounds).append(" lbs\n");
        catalog.append("Construction: ").append(constructionMethod).append("\n");
        catalog.append("Base Type: ").append(baseType).append("\n");
        catalog.append("Environment: ").append(isOutdoorSafe ? "Indoor/Outdoor" : "Indoor Only").append("\n");
        
        if (hasMovingParts) {
            catalog.append("Special Features: Kinetic/Moving Elements\n");
        }
        
        if (requiresSpecialLighting) {
            catalog.append("Display Requirements: Specialized lighting recommended\n");
        }
        
        catalog.append("Age: ").append(getAgeInYears()).append(" years\n");
        catalog.append("Estimated Value: $").append(String.format("%.2f", estimatedValue)).append("\n");
        catalog.append("Insurance Value: $").append(String.format("%.2f", calculateInsuranceValue())).append("\n");
        catalog.append("Space Required: ").append(String.format("%.0f", calculateSpaceRequired())).append(" sq ft\n");
        catalog.append("Display Status: ").append(isOnDisplay ? "On Exhibition" : "In Storage").append("\n");
        
        if (forSale) {
            catalog.append("AVAILABLE FOR PURCHASE:\n");
            catalog.append("  • Price: $").append(String.format("%.2f", askingPrice)).append("\n");
            catalog.append("  • Base Included: ").append(includesBase ? "Yes" : "Sold separately").append("\n");
            catalog.append("  • Shipping: ").append(shippingNotes).append("\n");
        }
        
        catalog.append("Provenance: ").append(provenanceHistory).append("\n");
        catalog.append("═══════════════════════════════════════");
        
        return catalog.toString();
    }
    
    @Override
    public boolean requiresSpecialHandling() {
        // Sculptures have various special handling requirements
        boolean veryHeavy = weightPounds > 200;
        boolean fragile = material.toLowerCase().contains("ceramic") || 
                         material.toLowerCase().contains("glass");
        boolean hasMovement = hasMovingParts;
        boolean awkwardSize = dimensions.contains("12") && dimensions.contains("feet"); // Very large
        boolean valuableAntique = getAgeInYears() > 75 && estimatedValue > 50000;
        
        if (veryHeavy || fragile || hasMovement || awkwardSize || valuableAntique) {
            System.out.println("⚠️ \"" + title + "\" requires special handling:");
            if (veryHeavy) System.out.println("   • Weight exceeds standard lifting capacity (" + weightPounds + " lbs)");
            if (fragile) System.out.println("   • Fragile " + material + " requires careful protection");
            if (hasMovement) System.out.println("   • Kinetic components need specialized transport");
            if (awkwardSize) System.out.println("   • Oversized dimensions require special equipment");
            if (valuableAntique) System.out.println("   • High-value antique requiring museum-grade handling");
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
        
        // Sculpture-specific pricing validation
        if (weightPounds > 500 && askingPrice < estimatedValue * 0.8) {
            System.out.println("⚠️ Warning: Large sculptures often command premium prices");
        }
        
        this.askingPrice = askingPrice;
        this.forSale = true;
        
        System.out.println("🏷️ \"" + title + "\" now available for purchase!");
        System.out.println("   💰 Asking Price: $" + String.format("%.2f", askingPrice));
        System.out.println("   💵 Final Price (with tax): $" + String.format("%.2f", getFinalPrice()));
        System.out.println("   🏛️ Base: " + (includesBase ? "Included" : "Sold separately"));
        System.out.println("   📦 Shipping: " + shippingNotes);
        System.out.println("   📋 Documentation: Certificate of authenticity included");
        
        // Update provenance
        String listingDate = java.time.LocalDate.now().toString();
        provenanceHistory += " | Listed for sale " + listingDate;
    }
    
    @Override
    public void removeFromSale() {
        if (!forSale) {
            System.out.println("ℹ️ \"" + title + "\" is not currently for sale");
            return;
        }
        
        System.out.println("📋 Removing \"" + title + "\" from sculpture market");
        this.forSale = false;
        this.askingPrice = 0.0;
        
        // Update provenance
        String removalDate = java.time.LocalDate.now().toString();
        provenanceHistory += " | Removed from sale " + removalDate;
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
            throw new IllegalStateException("Sculpture is not currently for sale");
        }
        
        System.out.println("🤝 PROCESSING SCULPTURE SALE: \"" + title + "\"");
        System.out.println("════════════════════════════════════════════");
        
        double salePrice = askingPrice;
        double tax = salePrice * TAX_RATE;
        double commission = salePrice * COMMISSION_RATE;
        double finalPrice = salePrice + tax;
        double shippingEstimate = calculateShippingCost();
        
        System.out.println("👤 Buyer: " + buyerName);
        System.out.println("🗿 Sculpture: " + getBasicInfo());
        System.out.println("💰 Sale Price: $" + String.format("%.2f", salePrice));
        System.out.println("🏛️ Gallery Commission: $" + String.format("%.2f", commission));
        System.out.println("💸 Tax: $" + String.format("%.2f", tax));
        System.out.println("📦 Estimated Shipping: $" + String.format("%.2f", shippingEstimate));
        System.out.println("💵 Total (excl. shipping): $" + String.format("%.2f", finalPrice));
        
        // Sculpture-specific transfer arrangements
        System.out.println("\n📦 SCULPTURE TRANSFER ARRANGEMENTS:");
        System.out.println("   ⚖️ Weight: " + weightPounds + " lbs - " + getWeightCategory());
        System.out.println("   📏 Dimensions: " + dimensions);
        System.out.println("   🏛️ Base: " + (includesBase ? "Included in sale" : "Not included"));
        System.out.println("   🚛 Shipping: " + shippingNotes);
        
        if (requiresSpecialHandling()) {
            System.out.println("   ⚠️ Special handling required - professional art movers recommended");
        }
        
        if (hasMovingParts) {
            System.out.println("   ⚙️ Kinetic components - assembly instructions and warranty included");
        }
        
        // Documentation package
        System.out.println("\n📄 DOCUMENTATION PACKAGE:");
        System.out.println("   • Certificate of authenticity");
        System.out.println("   • Condition report and maintenance history");
        System.out.println("   • Installation and care instructions");
        System.out.println("   • Artist biography and exhibition history");
        
        // Complete the sale
        this.forSale = false;
        this.askingPrice = 0.0;
        
        // Update provenance
        String saleDate = java.time.LocalDate.now().toString();
        provenanceHistory = "SOLD to " + buyerName + " on " + saleDate + 
                          " via gallery for $" + String.format("%.2f", salePrice);
        
        // Generate sales documentation
        String documentation = generateSalesDocumentation(buyerName, salePrice);
        System.out.println("\n" + documentation);
        
        System.out.println("✅ Sculpture sale completed successfully!");
        System.out.println("📋 Ownership transferred to: " + buyerName);
        System.out.println("🚛 Professional shipping will be arranged");
    }
    
    // ===== SCULPTURE-SPECIFIC METHODS =====
    
    /**
     * Perform material-specific maintenance procedures.
     */
    private void performMaterialSpecificMaintenance() {
        switch (material.toLowerCase()) {
            case "bronze":
                System.out.println("   • Bronze patina assessment and treatment");
                System.out.println("   • Corrosion prevention and protective coating");
                System.out.println("   • Metal surface polishing where appropriate");
                break;
            
            case "marble":
                System.out.println("   • Marble surface inspection for cracks or staining");
                System.out.println("   • Professional stone cleaning and sealing");
                System.out.println("   • Checking for structural weakness in carved areas");
                break;
            
            case "steel":
                System.out.println("   • Steel surface rust prevention treatment");
                System.out.println("   • Protective coating integrity check");
                System.out.println("   • Weld joint inspection for stress fractures");
                break;
                
            case "ceramic":
                System.out.println("   • Ceramic glaze condition assessment");
                System.out.println("   • Crack detection and monitoring");
                System.out.println("   • Gentle cleaning of ceramic surfaces");
                break;
                
            case "wood":
                System.out.println("   • Wood moisture content and pest inspection");
                System.out.println("   • Finish restoration and protective treatment");
                System.out.println("   • Joint stability and seasonal movement check");
                break;
                
            default:
                System.out.println("   • General material condition assessment");
                System.out.println("   • Surface cleaning appropriate to " + material);
                System.out.println("   • Protective measures for long-term preservation");
        }
    }
    
    /**
     * Perform material-specific cleaning procedures.
     */
    private void performMaterialSpecificCleaning() {
        switch (material.toLowerCase()) {
            case "bronze":
                System.out.println("   • Gentle bronze cleaning with specialized compounds");
                System.out.println("   • Removing oxidation while preserving patina");
                break;
                
            case "marble":
                System.out.println("   • pH-neutral stone cleaner application");
                System.out.println("   • Soft brush removal of surface deposits");
                break;
                
            case "steel":
                System.out.println("   • Degreasing and surface preparation");
                System.out.println("   • Protective coating reapplication if needed");
                break;
                
            default:
                System.out.println("   • Conservative cleaning appropriate to " + material);
                System.out.println("   • Surface debris and dust removal");
        }
    }
    
    /**
     * Generate shipping considerations based on sculpture properties.
     */
    private String generateShippingNotes() {
        StringBuilder notes = new StringBuilder();
        
        if (weightPounds < 50) {
            notes.append("Standard shipping available");
        } else if (weightPounds < 200) {
            notes.append("Heavy item - freight shipping required");
        } else {
            notes.append("Oversized freight - professional art movers recommended");
        }
        
        if (hasMovingParts) {
            notes.append(" | Kinetic components require careful packaging");
        }
        
        if (material.toLowerCase().contains("ceramic") || material.toLowerCase().contains("glass")) {
            notes.append(" | Fragile - custom crating essential");
        }
        
        return notes.toString();
    }
    
    /**
     * Calculate estimated shipping cost based on weight and special requirements.
     */
    private double calculateShippingCost() {
        double baseCost = 150.0; // Base shipping cost
        
        // Weight-based pricing
        if (weightPounds > 500) {
            baseCost += 500.0; // Very heavy
        } else if (weightPounds > 200) {
            baseCost += 250.0; // Heavy
        } else if (weightPounds > 50) {
            baseCost += 100.0; // Moderate
        }
        
        // Special handling surcharges
        if (hasMovingParts) baseCost += 200.0; // Kinetic complexity
        if (requiresSpecialHandling()) baseCost += 150.0; // General special handling
        if (material.toLowerCase().contains("ceramic")) baseCost += 100.0; // Fragility
        
        return baseCost;
    }
    
    /**
     * Get weight category description for shipping purposes.
     */
    private String getWeightCategory() {
        if (weightPounds < 25) return "Light";
        if (weightPounds < 75) return "Moderate";
        if (weightPounds < 200) return "Heavy";
        if (weightPounds < 500) return "Very Heavy";
        return "Extremely Heavy - Special Equipment Required";
    }
    
    /**
     * Professional condition assessment for sculpture.
     */
    public String generateConditionReport() {
        StringBuilder report = new StringBuilder();
        
        report.append("📋 SCULPTURE CONDITION REPORT: ").append(title).append("\n");
        report.append("═══════════════════════════════════════\n");
        report.append("Assessment Date: ").append(java.time.LocalDate.now()).append("\n");
        report.append("Medium: ").append(getArtMedium()).append("\n");
        report.append("Weight: ").append(weightPounds).append(" lbs\n");
        report.append("Age: ").append(getAgeInYears()).append(" years\n\n");
        
        // Overall condition based on age and material
        report.append("OVERALL CONDITION: ");
        if (getAgeInYears() < 10) {
            report.append("Excellent - Contemporary piece ✅\n");
        } else if (getAgeInYears() < 50) {
            report.append("Very Good - Well-preserved ✅\n");
        } else {
            report.append("Good with age-appropriate wear 👍\n");
        }
        
        // Material-specific conditions
        report.append("\nMATERIAL CONDITION (").append(material).append("):\n");
        report.append("• Surface integrity: Good ✅\n");
        report.append("• Structural stability: Excellent ✅\n");
        
        if (hasMovingParts) {
            report.append("• Kinetic mechanisms: Functional ⚙️\n");
        }
        
        // Environmental factors
        report.append("\nENVIRONMENTAL CONSIDERATIONS:\n");
        report.append("• Weather resistance: ").append(isOutdoorSafe ? "Suitable for outdoor display ☀️" : "Indoor only 🏠").append("\n");
        report.append("• Climate sensitivity: ").append(material.toLowerCase().contains("wood") ? "Moderate ⚠️" : "Low ✅").append("\n");
        
        // Recommendations
        report.append("\nMAINTENANCE RECOMMENDATIONS:\n");
        if (getAgeInYears() > 25) {
            report.append("• Annual professional condition assessment\n");
        }
        if (hasMovingParts) {
            report.append("• Quarterly kinetic system maintenance\n");
        }
        if (!isOutdoorSafe) {
            report.append("• Maintain stable indoor environment\n");
        }
        
        return report.toString();
    }
    
    // ===== GETTERS FOR SCULPTURE PROPERTIES =====
    
    public String getMaterial() { return material; }
    public double getWeightPounds() { return weightPounds; }
    public boolean isOutdoorSafe() { return isOutdoorSafe; }
    public String getConstructionMethod() { return constructionMethod; }
    public boolean hasMovingParts() { return hasMovingParts; }
    public String getBaseType() { return baseType; }
    public String getProvenanceHistory() { return provenanceHistory; }
    
    // ===== ENHANCED DISPLAY FOR SCULPTURES =====
    
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call parent's display method
        
        // Add sculpture-specific information
        System.out.println("   🪨 Material: " + material);
        System.out.println("   ⚖️ Weight: " + weightPounds + " lbs (" + getWeightCategory() + ")");
        System.out.println("   🏗️ Construction: " + constructionMethod);
        System.out.println("   🏛️ Base: " + baseType);
        System.out.println("   🌦️ Environment: " + (isOutdoorSafe ? "Indoor/Outdoor" : "Indoor Only"));
        
        if (hasMovingParts) {
            System.out.println("   ⚙️ Special Features: Kinetic/Moving Elements");
        }
        
        if (requiresSpecialLighting) {
            System.out.println("   💡 Lighting: Specialized display lighting recommended");
        }
        
        if (forSale) {
            System.out.println("   🏷️ FOR SALE: $" + String.format("%.2f", askingPrice));
            System.out.println("   📦 Shipping: " + shippingNotes);
        }
        
        System.out.println("🗿 ═══════════════════════════════════════");
    }
}

/*
 * SCULPTURE CLASS WISDOM DEMONSTRATED:
 * 
 * 1. PHYSICAL WORLD CONSIDERATIONS: Unlike digital art, sculptures have weight,
 *    require physical space, and have material-specific maintenance needs
 * 
 * 2. SPECIALIZED IMPLEMENTATIONS: Abstract methods are implemented with
 *    sculpture-specific logic - space calculations consider weight and
 *    clearance, maintenance addresses material degradation
 * 
 * 3. SINGLE INTERFACE FOCUS: Implements only Sellable (not all sculptures
 *    are displayable electronically or interactive), showing selective
 *    capability implementation
 * 
 * 4. REAL-WORLD COMPLEXITY: Addresses practical concerns like shipping,
 *    installation, environmental requirements, and conservation
 * 
 * 5. MATERIAL-DRIVEN BEHAVIOR: Different materials (bronze, marble, steel)
 *    require different maintenance and handling procedures
 * 
 * 6. PROFESSIONAL STANDARDS: Maintenance, condition reporting, and sales
 *    procedures reflect professional art handling practices
 * 
 * This class shows how the same abstract base (ArtPiece) can support
 * completely different types of art with their own specialized needs,
 * while still providing consistent polymorphic behavior.
 * 
 * "Sculpture commands space as painting commands vision - each medium
 *  brings its own requirements, its own possibilities, and its own
 *  relationship with those who encounter it."
 */
