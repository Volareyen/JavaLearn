/*
 * THE GALLERY MANAGEMENT SYSTEM: POLYMORPHISM IN ACTION
 * 
 * "Behold the power of abstraction made manifest - a single system that manages
 * all forms of art through their common essence, while respecting their unique natures."
 * 
 * COMPILATION NOTE: This file depends on:
 * - ArtPiece.java (abstract base)
 * - Displayable.java, Sellable.java, Interactive.java (interfaces)
 * - Painting.java, DigitalArt.java, Sculpture.java (concrete classes)
 * Compile all solution files together: javac *.java
 */

import java.util.*;
import java.util.stream.Collectors;

/**
 * Gallery Management System demonstrating the full power of abstraction.
 * 
 * This class shows how polymorphism allows us to work with objects through
 * their abstractions rather than their concrete types, creating flexible
 * and extensible systems.
 * 
 * Key demonstrations:
 * - Polymorphic collection management (List<ArtPiece>)
 * - Interface-based operations (working with capabilities)
 * - Abstract method calls that dispatch to concrete implementations
 * - Unified operations across diverse art types
 * - Runtime type checking and capability discovery
 */
public class GalleryManager {
    
    // ===== COLLECTION MANAGEMENT =====
    
    private List<ArtPiece> artCollection;     // Polymorphic collection
    private String galleryName;
    private Map<String, String> exhibitionSchedule;
    private double totalInsuranceValue;
    
    // Constructor
    public GalleryManager(String galleryName) {
        this.galleryName = galleryName;
        this.artCollection = new ArrayList<>();
        this.exhibitionSchedule = new HashMap<>();
        this.totalInsuranceValue = 0.0;
        
        System.out.println("🏛️ Gallery Management System Initialized");
        System.out.println("📍 Gallery: " + galleryName);
        System.out.println("📊 Ready to manage diverse art collections");
    }
    
    // ===== POLYMORPHIC COLLECTION OPERATIONS =====
    
    /**
     * Add any type of artwork to the collection.
     * Demonstrates polymorphism - method works with ANY ArtPiece subclass.
     */
    public void addArtPiece(ArtPiece art) {
        if (art == null) {
            throw new IllegalArgumentException("Cannot add null artwork");
        }
        
        artCollection.add(art);
        totalInsuranceValue += art.calculateInsuranceValue();
        
        System.out.println("✅ Added to collection: " + art.getBasicInfo());
        System.out.println("   🎨 Medium: " + art.getArtMedium());
        System.out.println("   💰 Insurance Value: $" + String.format("%.2f", art.calculateInsuranceValue()));
        
        // Demonstrate capability detection
        checkCapabilities(art);
        
        System.out.println("📊 Collection now contains " + artCollection.size() + " pieces");
    }
    
    /**
     * Remove artwork by title.
     * Shows polymorphic search and removal.
     */
    public boolean removeArtPiece(String title) {
        Iterator<ArtPiece> iterator = artCollection.iterator();
        
        while (iterator.hasNext()) {
            ArtPiece art = iterator.next();
            if (art.getTitle().equalsIgnoreCase(title)) {
                iterator.remove();
                totalInsuranceValue -= art.calculateInsuranceValue();
                
                System.out.println("🗑️ Removed from collection: " + art.getBasicInfo());
                System.out.println("📊 Collection now contains " + artCollection.size() + " pieces");
                
                return true;
            }
        }
        
        System.out.println("❌ Artwork not found: " + title);
        return false;
    }
    
    /**
     * Display entire collection information.
     * Each piece displays according to its concrete implementation.
     */
    public void listAllArtwork() {
        System.out.println("\n🏛️ " + galleryName.toUpperCase() + " - COMPLETE COLLECTION");
        System.out.println("═".repeat(80));
        
        if (artCollection.isEmpty()) {
            System.out.println("📭 Collection is currently empty");
            return;
        }
        
        System.out.println("📊 Total pieces: " + artCollection.size());
        System.out.println("💰 Total insurance value: $" + String.format("%.2f", totalInsuranceValue));
        System.out.println("\n📋 DETAILED INVENTORY:");
        
        for (int i = 0; i < artCollection.size(); i++) {
            System.out.println("\n" + (i + 1) + ". ");
            artCollection.get(i).displayInfo(); // Polymorphic method call!
        }
        
        System.out.println("\n" + "═".repeat(80));
    }
    
    /**
     * Search for artwork by artist name.
     * Demonstrates filtering with polymorphic operations.
     */
    public List<ArtPiece> findArtworkByArtist(String artist) {
        List<ArtPiece> results = artCollection.stream()
                .filter(art -> art.getArtist().toLowerCase().contains(artist.toLowerCase()))
                .collect(Collectors.toList());
        
        System.out.println("🔍 Search results for artist \"" + artist + "\": " + results.size() + " found");
        
        for (ArtPiece art : results) {
            System.out.println("   🎨 " + art.getBasicInfo());
        }
        
        return results;
    }
    
    // ===== POLYMORPHIC OPERATIONS ON ENTIRE COLLECTION =====
    
    /**
     * Perform maintenance on all artwork.
     * Each piece maintains itself according to its concrete type.
     */
    public void performMaintenanceOnAll() {
        System.out.println("\n🔧 GALLERY-WIDE MAINTENANCE SESSION");
        System.out.println("═".repeat(60));
        System.out.println("📅 Date: " + java.time.LocalDate.now());
        System.out.println("🏛️ Gallery: " + galleryName);
        System.out.println("📊 Pieces to maintain: " + artCollection.size());
        
        if (artCollection.isEmpty()) {
            System.out.println("📭 No artwork requires maintenance");
            return;
        }
        
        for (int i = 0; i < artCollection.size(); i++) {
            System.out.println("\n" + "-".repeat(50));
            System.out.println("🔧 MAINTENANCE SESSION " + (i + 1) + " of " + artCollection.size());
            
            ArtPiece art = artCollection.get(i);
            art.performMaintenance(); // Abstract method - different implementation per type!
            
            // Estimate next maintenance date
            System.out.println("📅 Next maintenance recommended: " + 
                             java.time.LocalDate.now().plusMonths(6));
        }
        
        System.out.println("\n✅ Gallery-wide maintenance completed successfully!");
        System.out.println("📝 All " + artCollection.size() + " pieces properly maintained");
    }
    
    /**
     * Calculate total insurance value for the collection.
     * Uses abstract class concrete method polymorphically.
     */
    public double calculateTotalInsuranceValue() {
        double total = artCollection.stream()
                .mapToDouble(ArtPiece::calculateInsuranceValue) // Method reference!
                .sum();
        
        System.out.println("💰 INSURANCE VALUATION SUMMARY");
        System.out.println("-".repeat(40));
        System.out.println("🏛️ Gallery: " + galleryName);
        System.out.println("📊 Total pieces: " + artCollection.size());
        System.out.println("💵 Combined estimated value: $" + String.format("%.2f", 
            artCollection.stream().mapToDouble(ArtPiece::getEstimatedValue).sum()));
        System.out.println("🛡️ Total insurance value (120%): $" + String.format("%.2f", total));
        System.out.println("📋 Average value per piece: $" + String.format("%.2f", 
            artCollection.isEmpty() ? 0 : total / artCollection.size()));
        
        return total;
    }
    
    /**
     * Generate complete catalog for all artwork.
     * Each piece generates its catalog entry according to its type.
     */
    public String generateFullCatalog() {
        StringBuilder catalog = new StringBuilder();
        
        catalog.append("📚 COMPLETE GALLERY CATALOG\n");
        catalog.append("═".repeat(80)).append("\n");
        catalog.append("Gallery: ").append(galleryName).append("\n");
        catalog.append("Catalog Date: ").append(java.time.LocalDate.now()).append("\n");
        catalog.append("Total Pieces: ").append(artCollection.size()).append("\n");
        catalog.append("Total Insurance Value: $").append(String.format("%.2f", totalInsuranceValue)).append("\n");
        catalog.append("═".repeat(80)).append("\n\n");
        
        if (artCollection.isEmpty()) {
            catalog.append("📭 No artwork currently in collection\n");
            return catalog.toString();
        }
        
        for (int i = 0; i < artCollection.size(); i++) {
            catalog.append("CATALOG ENTRY #").append(i + 1).append("\n");
            catalog.append(artCollection.get(i).generateCatalogEntry()); // Abstract method!
            catalog.append("\n\n");
        }
        
        catalog.append("═".repeat(80)).append("\n");
        catalog.append("END OF CATALOG - ").append(artCollection.size()).append(" ENTRIES");
        
        return catalog.toString();
    }
    
    // ===== INTERFACE-BASED OPERATIONS =====
    
    /**
     * Setup all displayable artwork.
     * Works only with pieces that implement Displayable interface.
     */
    public void setupAllDisplays() {
        System.out.println("\n📺 DISPLAY SYSTEM ACTIVATION");
        System.out.println("═".repeat(50));
        
        List<Displayable> displayableArt = getDisplayableArt();
        
        if (displayableArt.isEmpty()) {
            System.out.println("📭 No displayable artwork found in collection");
            return;
        }
        
        System.out.println("🔌 Found " + displayableArt.size() + " displayable pieces");
        
        for (int i = 0; i < displayableArt.size(); i++) {
            System.out.println("\n🖥️ DISPLAY SETUP " + (i + 1) + " of " + displayableArt.size());
            
            Displayable display = displayableArt.get(i);
            
            // Use interface methods polymorphically
            display.performDisplayCalibration(); // Default interface method
            display.turnOn();                     // Abstract interface method
            
            // Check battery if applicable
            int battery = display.getBatteryLevel();
            if (battery != -1) {
                System.out.println("🔋 Battery level: " + battery + "%");
                if (battery < 20) {
                    System.out.println("⚠️ Low battery - consider charging");
                }
            }
        }
        
        System.out.println("\n✅ All displayable artwork activated successfully!");
    }
    
    /**
     * Shutdown all displayable artwork safely.
     */
    public void shutdownAllDisplays() {
        System.out.println("\n🔽 DISPLAY SYSTEM SHUTDOWN");
        System.out.println("═".repeat(50));
        
        List<Displayable> displayableArt = getDisplayableArt();
        
        if (displayableArt.isEmpty()) {
            System.out.println("📭 No displayable artwork to shutdown");
            return;
        }
        
        System.out.println("🔌 Shutting down " + displayableArt.size() + " displays");
        
        for (Displayable display : displayableArt) {
            display.performSafeShutdown(); // Default interface method
        }
        
        System.out.println("✅ All displays safely powered down");
    }
    
    /**
     * List all artwork currently for sale.
     * Works with any piece implementing Sellable interface.
     */
    public void listForSaleItems() {
        System.out.println("\n🏷️ ARTWORK FOR SALE");
        System.out.println("═".repeat(50));
        
        List<Sellable> sellableArt = getSellableArt();
        List<Sellable> forSaleArt = sellableArt.stream()
                .filter(Sellable::isForSale)
                .collect(Collectors.toList());
        
        if (forSaleArt.isEmpty()) {
            System.out.println("📭 No artwork currently for sale");
            System.out.println("ℹ️ Total sellable pieces: " + sellableArt.size());
            return;
        }
        
        System.out.println("🛍️ Available for purchase: " + forSaleArt.size() + " pieces");
        System.out.println("📊 Total sellable inventory: " + sellableArt.size() + " pieces");
        
        double totalValue = 0;
        for (int i = 0; i < forSaleArt.size(); i++) {
            Sellable sellable = forSaleArt.get(i);
            
            // Cast to ArtPiece to get basic info (we know all our Sellables are ArtPieces)
            if (sellable instanceof ArtPiece art) {
                double finalPrice = sellable.getFinalPrice();
                totalValue += finalPrice;
                
                System.out.println("\n💰 SALE ITEM #" + (i + 1));
                System.out.println("   🎨 " + art.getBasicInfo());
                System.out.println("   💵 Price: $" + String.format("%.2f", finalPrice));
                System.out.println("   📊 Medium: " + art.getArtMedium());
            }
        }
        
        System.out.println("\n📊 SALES SUMMARY:");
        System.out.println("   💰 Total sales value: $" + String.format("%.2f", totalValue));
        System.out.println("   📈 Average price: $" + String.format("%.2f", totalValue / forSaleArt.size()));
    }
    
    /**
     * Process sales for all available artwork.
     * Demonstrates interface method calls with user interaction simulation.
     */
    public void processAllSales() {
        System.out.println("\n🤝 PROCESSING ALL AVAILABLE SALES");
        System.out.println("═".repeat(50));
        
        List<Sellable> forSaleArt = getSellableArt().stream()
                .filter(Sellable::isForSale)
                .collect(Collectors.toList());
        
        if (forSaleArt.isEmpty()) {
            System.out.println("📭 No artwork currently for sale");
            return;
        }
        
        System.out.println("💼 Processing " + forSaleArt.size() + " sales...");
        
        // Simulate buyers for demonstration
        String[] buyers = {"Museum of Modern Art", "Private Collector Johnson", 
                          "Corporate Art Collection", "International Gallery",
                          "Art Investment Fund"};
        
        for (int i = 0; i < forSaleArt.size() && i < buyers.length; i++) {
            System.out.println("\n" + "-".repeat(60));
            System.out.println("💰 PROCESSING SALE #" + (i + 1));
            
            Sellable sellable = forSaleArt.get(i);
            String buyer = buyers[i % buyers.length];
            
            try {
                sellable.processTransaction(buyer); // Interface method call!
            } catch (Exception e) {
                System.out.println("❌ Sale failed: " + e.getMessage());
            }
        }
        
        System.out.println("\n✅ Sales processing session completed!");
    }
    
    /**
     * Activate interaction mode for all interactive artwork.
     */
    public void activateInteractiveMode() {
        System.out.println("\n🎯 ACTIVATING INTERACTIVE MODE");
        System.out.println("═".repeat(50));
        
        List<Interactive> interactiveArt = getInteractiveArt();
        
        if (interactiveArt.isEmpty()) {
            System.out.println("📭 No interactive artwork found in collection");
            return;
        }
        
        System.out.println("🎮 Found " + interactiveArt.size() + " interactive pieces");
        
        for (Interactive interactive : interactiveArt) {
            interactive.enableInteractionMode(); // Interface method!
            
            // Simulate some interactions for demonstration
            if (interactive instanceof ArtPiece art) {
                System.out.println("🎨 Ready for interaction: " + art.getTitle());
            }
        }
        
        System.out.println("✅ All interactive systems activated!");
    }
    
    // ===== CAPABILITY DISCOVERY METHODS =====
    
    /**
     * Get all displayable artwork from collection.
     * Demonstrates instanceof checks and interface casting.
     */
    private List<Displayable> getDisplayableArt() {
        return artCollection.stream()
                .filter(art -> art instanceof Displayable)
                .map(art -> (Displayable) art)
                .collect(Collectors.toList());
    }
    
    /**
     * Get all sellable artwork from collection.
     */
    private List<Sellable> getSellableArt() {
        return artCollection.stream()
                .filter(art -> art instanceof Sellable)
                .map(art -> (Sellable) art)
                .collect(Collectors.toList());
    }
    
    /**
     * Get all interactive artwork from collection.
     */
    private List<Interactive> getInteractiveArt() {
        return artCollection.stream()
                .filter(art -> art instanceof Interactive)
                .map(art -> (Interactive) art)
                .collect(Collectors.toList());
    }
    
    /**
     * Check and report capabilities of a piece when added.
     */
    private void checkCapabilities(ArtPiece art) {
        List<String> capabilities = new ArrayList<>();
        
        if (art instanceof Displayable) capabilities.add("📺 Displayable");
        if (art instanceof Sellable) capabilities.add("💰 Sellable");
        if (art instanceof Interactive) capabilities.add("🎯 Interactive");
        
        if (!capabilities.isEmpty()) {
            System.out.println("   🔧 Capabilities: " + String.join(", ", capabilities));
        } else {
            System.out.println("   📋 Traditional artwork (no digital capabilities)");
        }
    }
    
    // ===== GALLERY ANALYTICS AND REPORTING =====
    
    /**
     * Generate comprehensive gallery statistics.
     */
    public void generateGalleryStats() {
        System.out.println("\n📊 COMPREHENSIVE GALLERY STATISTICS");
        System.out.println("═".repeat(80));
        System.out.println("🏛️ Gallery: " + galleryName);
        System.out.println("📅 Report Date: " + java.time.LocalDate.now());
        
        // Collection overview
        System.out.println("\n📚 COLLECTION OVERVIEW:");
        System.out.println("   🎨 Total pieces: " + artCollection.size());
        System.out.println("   💰 Total insurance value: $" + String.format("%.2f", totalInsuranceValue));
        
        if (!artCollection.isEmpty()) {
            double avgValue = totalInsuranceValue / artCollection.size();
            System.out.println("   📈 Average insurance value: $" + String.format("%.2f", avgValue));
            
            // Age analysis
            OptionalDouble avgAge = artCollection.stream()
                    .mapToInt(ArtPiece::getAgeInYears)
                    .average();
            System.out.println("   📊 Average age: " + String.format("%.1f", avgAge.orElse(0)) + " years");
        }
        
        // Capability breakdown
        System.out.println("\n🔧 CAPABILITY ANALYSIS:");
        System.out.println("   📺 Displayable pieces: " + getDisplayableArt().size());
        System.out.println("   💰 Sellable pieces: " + getSellableArt().size());
        System.out.println("   🎯 Interactive pieces: " + getInteractiveArt().size());
        
        // Medium analysis
        System.out.println("\n🎨 MEDIUM BREAKDOWN:");
        Map<String, Long> mediumCount = artCollection.stream()
                .collect(Collectors.groupingBy(ArtPiece::getArtMedium, Collectors.counting()));
        
        mediumCount.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> System.out.println("   • " + entry.getKey() + ": " + entry.getValue()));
        
        // Sales analysis
        List<Sellable> sellableArt = getSellableArt();
        List<Sellable> forSaleArt = sellableArt.stream()
                .filter(Sellable::isForSale)
                .collect(Collectors.toList());
        
        System.out.println("\n💼 SALES ANALYSIS:");
        System.out.println("   🏷️ Currently for sale: " + forSaleArt.size());
        System.out.println("   📊 Sales potential: " + sellableArt.size() + " total sellable");
        
        if (!forSaleArt.isEmpty()) {
            double totalSalesValue = forSaleArt.stream()
                    .mapToDouble(Sellable::getFinalPrice)
                    .sum();
            System.out.println("   💰 Total sales value: $" + String.format("%.2f", totalSalesValue));
        }
        
        System.out.println("\n" + "═".repeat(80));
    }
    
    // ===== GETTERS =====
    
    public String getGalleryName() { return galleryName; }
    public int getCollectionSize() { return artCollection.size(); }
    public double getTotalInsuranceValue() { return totalInsuranceValue; }
    
    /**
     * Get read-only view of the collection for external access.
     */
    public List<ArtPiece> getCollection() {
        return Collections.unmodifiableList(artCollection);
    }
}

/*
 * GALLERY MANAGER WISDOM DEMONSTRATED:
 * 
 * 1. POLYMORPHIC COLLECTIONS: List<ArtPiece> can hold any subclass -
 *    Painting, DigitalArt, Sculpture - and work with all uniformly
 * 
 * 2. ABSTRACT METHOD DISPATCH: Calls like art.performMaintenance() 
 *    automatically invoke the correct implementation based on runtime type
 * 
 * 3. INTERFACE-BASED PROGRAMMING: Methods work with Displayable, Sellable,
 *    Interactive interfaces regardless of the underlying concrete class
 * 
 * 4. RUNTIME TYPE DISCOVERY: instanceof checks allow capability detection
 *    and conditional interface usage
 * 
 * 5. STREAM API INTEGRATION: Modern Java streams work beautifully with
 *    polymorphic collections and method references
 * 
 * 6. UNIFIED OPERATIONS: Same management code works with vastly different
 *    art types - the power of programming to abstractions
 * 
 * 7. EXTENSIBILITY: Adding new art types requires no changes to this
 *    management code - they just need to extend ArtPiece and implement
 *    appropriate interfaces
 * 
 * This class embodies the ultimate goal of abstraction: writing code that
 * works with concepts and capabilities rather than specific implementations,
 * creating systems that are both powerful and flexible.
 * 
 * "The gallery manager sees not the specific brushstroke or pixel,
 *  but the universal essence of artistic expression - managing all
 *  through their common nature while honoring their unique spirits."
 */
