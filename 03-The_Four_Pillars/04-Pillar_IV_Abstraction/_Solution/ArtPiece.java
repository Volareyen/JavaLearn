/*
 * THE FOUNDATION: ABSTRACT ART PIECE
 * 
 * "Every masterpiece begins with an idea - an essence that defines what it means to be art.
 * This abstract class captures that essence, the common truth that all artwork shares."
 * 
 * COMPILATION NOTE: This is the abstract base class for the gallery system.
 * Compile with all other solution files: javac *.java
 */

import java.time.LocalDate;

/**
 * Abstract base class representing the essential nature of all artwork.
 * 
 * This class demonstrates the power of abstraction by providing:
 * - Common state shared by all art pieces (concrete fields)
 * - Common behaviors implemented once for all types (concrete methods)
 * - Required contracts that each art type must fulfill (abstract methods)
 * 
 * The wisdom: Define what ALL art pieces ARE and MUST DO, while leaving
 * the HOW to be determined by specific art forms.
 */
public abstract class ArtPiece {
    
    // ===== SHARED STATE: What all artwork HAS =====
    protected String title;           // The artwork's name
    protected String artist;          // Creator of the piece
    protected int yearCreated;        // Year the artwork was made
    protected String dimensions;      // Size/dimensions of the piece
    protected double estimatedValue;  // Monetary value in dollars
    protected boolean isOnDisplay;    // Whether currently exhibited
    
    // Constructor - the sacred ritual of art piece creation
    public ArtPiece(String title, String artist, int yearCreated, 
                   String dimensions, double estimatedValue) {
        this.title = title;
        this.artist = artist;
        this.yearCreated = yearCreated;
        this.dimensions = dimensions;
        this.estimatedValue = estimatedValue;
        this.isOnDisplay = false;  // New acquisitions start in storage
        
        // Validation - ensuring artistic integrity
        if (estimatedValue < 0) {
            throw new IllegalArgumentException("Estimated value cannot be negative");
        }
        if (yearCreated > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Creation year cannot be in the future");
        }
    }
    
    // ===== CONCRETE METHODS: Complete wisdom shared by all art =====
    
    /**
     * Displays basic information common to all artwork.
     * This is the same for all art pieces - no need for subclass variation.
     */
    public void displayInfo() {
        System.out.println("🎨 ═══════════════════════════════════════");
        System.out.println("   Title: " + title);
        System.out.println("   Artist: " + artist);
        System.out.println("   Year Created: " + yearCreated);
        System.out.println("   Dimensions: " + dimensions);
        System.out.println("   Estimated Value: $" + String.format("%.2f", estimatedValue));
        System.out.println("   Currently: " + (isOnDisplay ? "On Display 🖼️" : "In Storage 📦"));
        System.out.println("   Medium: " + getArtMedium());  // Calls abstract method
        System.out.println("   Age: " + getAgeInYears() + " years old");
        System.out.println("   Special Handling: " + (requiresSpecialHandling() ? "Required ⚠️" : "Standard ✅"));
    }
    
    /**
     * Changes the exhibition status of the artwork.
     * Same logic applies to all art pieces.
     */
    public void setDisplayStatus(boolean status) {
        String statusChange = status ? "moved to exhibition hall" : "returned to storage";
        System.out.println("📋 \"" + title + "\" " + statusChange);
        this.isOnDisplay = status;
    }
    
    /**
     * Calculates insurance value at 120% of estimated value.
     * Standard calculation applies to all artwork.
     */
    public double calculateInsuranceValue() {
        return estimatedValue * 1.20;  // 20% markup for insurance coverage
    }
    
    /**
     * Calculates how old the artwork is.
     * Same calculation logic for all art pieces.
     */
    public int getAgeInYears() {
        return LocalDate.now().getYear() - yearCreated;
    }
    
    /**
     * Final method - cannot be overridden.
     * Some behaviors must remain consistent across all art types.
     */
    public final String getBasicInfo() {
        return "\"" + title + "\" by " + artist + " (" + yearCreated + ")";
    }
    
    // ===== ABSTRACT METHODS: Contracts each art type must fulfill =====
    
    /**
     * Returns the artistic medium (oil paint, bronze, digital, etc.)
     * Each art form has its own medium - this MUST be implemented.
     */
    public abstract String getArtMedium();
    
    /**
     * Performs type-specific maintenance and preservation.
     * Different art forms require different care - paintings need climate control,
     * sculptures need dusting, digital art needs file backup, etc.
     */
    public abstract void performMaintenance();
    
    /**
     * Calculates space needed for display in square feet.
     * Varies greatly: paintings need wall space, sculptures need floor space,
     * installations need entire rooms.
     */
    public abstract double calculateSpaceRequired();
    
    /**
     * Generates detailed catalog entry for this artwork.
     * Each art form has different catalog requirements and formatting.
     */
    public abstract String generateCatalogEntry();
    
    /**
     * Determines if artwork needs special handling during moves.
     * Some pieces are fragile, others are heavy, some are tech-dependent.
     */
    public abstract boolean requiresSpecialHandling();
    
    // ===== GETTERS: Protected field access =====
    
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public int getYearCreated() { return yearCreated; }
    public String getDimensions() { return dimensions; }
    public double getEstimatedValue() { return estimatedValue; }
    public boolean isOnDisplay() { return isOnDisplay; }
    
    // ===== SETTERS: Controlled modification =====
    
    public void updateEstimatedValue(double newValue) {
        if (newValue < 0) {
            throw new IllegalArgumentException("Estimated value cannot be negative");
        }
        System.out.println("💰 Updating value of \"" + title + "\" from $" + 
                          String.format("%.2f", estimatedValue) + " to $" + 
                          String.format("%.2f", newValue));
        this.estimatedValue = newValue;
    }
    
    // ===== OBJECT CLASS OVERRIDES: Professional behavior =====
    
    @Override
    public String toString() {
        return getBasicInfo() + " - " + getArtMedium() + 
               " ($" + String.format("%.2f", estimatedValue) + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        ArtPiece artPiece = (ArtPiece) obj;
        return title.equals(artPiece.title) && 
               artist.equals(artPiece.artist) && 
               yearCreated == artPiece.yearCreated;
    }
    
    @Override
    public int hashCode() {
        return title.hashCode() + artist.hashCode() + yearCreated;
    }
}

/*
 * SACRED TRUTHS DEMONSTRATED:
 * 
 * 1. SHARED IDENTITY: All art pieces have title, artist, value - defined once here
 * 
 * 2. SHARED BEHAVIOR: Methods like displayInfo(), calculateInsuranceValue() 
 *    work the same for all art - implemented once, used by all subclasses
 * 
 * 3. ENFORCED CONTRACTS: Abstract methods MUST be implemented by concrete 
 *    subclasses - each art form defines HOW these are done
 * 
 * 4. CONTROLLED EXTENSION: final method getBasicInfo() cannot be overridden -
 *    some behaviors must remain consistent
 * 
 * 5. TEMPLATE PATTERN: displayInfo() calls abstract method getArtMedium() - 
 *    mixing concrete and abstract behavior in one workflow
 * 
 * This abstract class captures the ESSENCE of what it means to be artwork
 * while allowing infinite variation in HOW that essence is expressed.
 * 
 * "The abstract reveals the universal truths hidden within the specific."
 */
