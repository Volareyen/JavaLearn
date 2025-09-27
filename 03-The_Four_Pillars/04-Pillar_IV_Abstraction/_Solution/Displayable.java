/*
 * INTERFACE CONTRACT: DISPLAYABLE
 * 
 * "Some art lives in the realm of light and electricity, requiring power to reveal
 * its beauty. This contract defines the sacred obligations of all displayable artwork."
 * 
 * COMPILATION NOTE: This interface is implemented by DigitalArt.java
 * Compile all solution files together: javac *.java
 */

/**
 * Interface defining the contract for artwork that can be actively displayed/projected.
 * 
 * This represents a CAPABILITY - not all artwork can be "turned on" or "displayed"
 * electronically, but for those that can, they must fulfill these obligations.
 * 
 * Examples: Digital art, video installations, interactive displays, LED sculptures
 * 
 * The wisdom: Interfaces define what objects CAN DO, not what they ARE.
 * This is about capability, not identity.
 */
public interface Displayable {
    
    // ===== CONSTANTS: Eternal truths for all displayable art =====
    
    /**
     * Standard brightness level for most gallery environments.
     * Implicitly public, static, final
     */
    int STANDARD_BRIGHTNESS = 75;
    
    /**
     * Maximum safe display time per day to prevent screen burn-in and conserve energy.
     * Implicitly public, static, final
     */
    int MAX_DISPLAY_HOURS = 12;
    
    /**
     * Minimum brightness to ensure visibility.
     */
    int MIN_BRIGHTNESS = 10;
    
    /**
     * Maximum brightness to prevent eye strain.
     */
    int MAX_BRIGHTNESS = 100;
    
    // ===== ABSTRACT METHODS: Pure contracts, no implementation =====
    
    /**
     * Activate the display system.
     * Each displayable artwork turns on differently - projectors, LEDs, screens, etc.
     * Implicitly public abstract
     */
    void turnOn();
    
    /**
     * Deactivate the display system safely.
     * Proper shutdown procedures vary by display type.
     */
    void turnOff();
    
    /**
     * Adjust display brightness level.
     * @param level Brightness level (0-100)
     */
    void setBrightness(int level);
    
    /**
     * Get current power/battery level for wireless displays.
     * @return Power level (0-100), or -1 if hardwired
     */
    int getBatteryLevel();
    
    // ===== DEFAULT METHODS: Shared implementation provided =====
    
    /**
     * Standard calibration routine for display quality.
     * Most displayable artwork can use this standard procedure.
     */
    default void performDisplayCalibration() {
        System.out.println("🔧 Performing standard display calibration...");
        System.out.println("   📊 Checking color accuracy...");
        System.out.println("   💡 Adjusting brightness to " + STANDARD_BRIGHTNESS + "%");
        System.out.println("   🎯 Verifying resolution and aspect ratio");
        System.out.println("   ✅ Display calibration complete");
    }
    
    /**
     * Safe startup sequence for electronic art.
     * Implements best practices that most displays can use.
     */
    default void performSafeStartup() {
        System.out.println("🔄 Initializing display systems...");
        performDisplayCalibration();
        setBrightness(STANDARD_BRIGHTNESS);
        turnOn();
        System.out.println("✨ Display ready for exhibition");
    }
    
    /**
     * Safe shutdown sequence.
     * Standard procedure to prevent damage during power-off.
     */
    default void performSafeShutdown() {
        System.out.println("🔽 Beginning safe shutdown sequence...");
        setBrightness(MIN_BRIGHTNESS);
        
        // Simulate gradual shutdown
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        turnOff();
        System.out.println("🌙 Display safely powered down");
    }
    
    /**
     * Check if display is suitable for extended exhibition.
     * @param plannedHours How long the exhibition will run
     * @return true if display can handle the duration
     */
    default boolean canRunForDuration(int plannedHours) {
        if (plannedHours <= MAX_DISPLAY_HOURS) {
            System.out.println("✅ Display approved for " + plannedHours + " hour exhibition");
            return true;
        } else {
            System.out.println("⚠️ Exhibition duration (" + plannedHours + 
                             " hours) exceeds safe limit (" + MAX_DISPLAY_HOURS + " hours)");
            return false;
        }
    }
    
    // ===== STATIC METHODS: Utility functions for the contract =====
    
    /**
     * Validates brightness level is within acceptable range.
     * @param level Brightness level to validate
     * @return true if level is valid (0-100)
     */
    static boolean isValidBrightness(int level) {
        return level >= 0 && level <= MAX_BRIGHTNESS;
    }
    
    /**
     * Suggests optimal brightness based on ambient lighting.
     * @param ambientLightLevel Environmental light level (0-100)
     * @return Recommended brightness setting
     */
    static int suggestBrightness(int ambientLightLevel) {
        if (ambientLightLevel < 20) {
            return MIN_BRIGHTNESS + 10;  // Low light - reduce eye strain
        } else if (ambientLightLevel < 60) {
            return STANDARD_BRIGHTNESS;  // Normal gallery lighting
        } else {
            return MAX_BRIGHTNESS - 10;  // Bright environment - increase visibility
        }
    }
    
    /**
     * Calculates estimated power consumption for planning.
     * @param hours Duration of display
     * @param brightness Brightness level (0-100)
     * @return Estimated watts per hour
     */
    static double estimatePowerConsumption(int hours, int brightness) {
        // Base consumption varies with brightness
        double baseWatts = 50.0; // Typical display baseline
        double brightnessMultiplier = brightness / 100.0;
        double hourlyWatts = baseWatts * (0.3 + 0.7 * brightnessMultiplier);
        
        return hours * hourlyWatts;
    }
    
    /**
     * Gets a user-friendly name for brightness level.
     * @param level Brightness level (0-100)
     * @return Descriptive name
     */
    static String getBrightnessDescription(int level) {
        if (level <= 20) return "Dim";
        if (level <= 40) return "Low";
        if (level <= 60) return "Medium";
        if (level <= 80) return "High";
        return "Maximum";
    }
}

/*
 * INTERFACE WISDOM DEMONSTRATED:
 * 
 * 1. PURE CAPABILITY: This interface defines what it means to be "displayable"
 *    without caring about the object's identity or inheritance
 * 
 * 2. MULTIPLE INHERITANCE: Classes can implement this alongside other interfaces
 *    (Sellable, Interactive) to gain multiple capabilities
 * 
 * 3. CONSTANTS: Shared values that all displayable art should respect
 * 
 * 4. DEFAULT METHODS: Common implementations that most displays can use,
 *    but which can be overridden if needed
 * 
 * 5. STATIC UTILITIES: Helper functions related to the contract that don't
 *    require an instance of the implementing class
 * 
 * 6. LOOSE COUPLING: Any class can become displayable by implementing this
 *    contract - no inheritance hierarchy required
 * 
 * This interface embodies the principle: "Program to contracts, not implementations."
 * Code that works with Displayable objects can work with ANY implementation,
 * present or future, without modification.
 * 
 * "The interface is a promise - a sacred contract between the implementer
 *  and the user, defining obligations without constraining solutions."
 */
