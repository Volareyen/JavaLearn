/*
 * INTERFACE CONTRACT: INTERACTIVE
 * 
 * "The highest art invites participation, responding to human presence with grace
 * and purpose. This contract governs the sacred dialogue between art and observer."
 * 
 * COMPILATION NOTE: This interface is implemented by DigitalArt.java
 * Compile all solution files together: javac *.java
 */

/**
 * Interface defining the contract for artwork that responds to user interaction.
 * 
 * This represents the CAPABILITY of interaction - not all artwork can or should
 * respond to human input, but for those that can, they must provide meaningful
 * and consistent interaction experiences.
 * 
 * Examples: Touch-responsive sculptures, motion-activated installations,
 * voice-controlled digital art, gesture-driven projections
 * 
 * The wisdom: Interactivity is a behavior that can be added to any art form.
 * A painting can become interactive through digital augmentation, a sculpture
 * through embedded sensors, an installation through environmental response.
 */
public interface Interactive {
    
    // ===== INTERACTION CONSTANTS: Standards for user experience =====
    
    /**
     * Maximum response time for interactions (milliseconds).
     * Users expect immediate feedback - beyond this feels unresponsive.
     */
    int MAX_RESPONSE_TIME_MS = 500;
    
    /**
     * Minimum time between interactions to prevent system overload.
     */
    int MIN_INTERACTION_INTERVAL_MS = 100;
    
    /**
     * Default sensitivity level for interaction detection.
     */
    int DEFAULT_SENSITIVITY = 50;  // Range 0-100
    
    /**
     * Timeout for returning to idle state (seconds).
     */
    int IDLE_TIMEOUT_SECONDS = 30;
    
    // ===== INTERACTION MODES: Different types of engagement =====
    
    /**
     * Enumeration of supported interaction modes.
     * Helps standardize interaction types across different artwork.
     */
    enum InteractionMode {
        PASSIVE,      // Responds to presence only
        TOUCH,        // Responds to physical contact
        GESTURE,      // Responds to hand/body movements
        VOICE,        // Responds to vocal commands
        PROXIMITY,    // Responds to distance changes
        ENVIRONMENTAL // Responds to room conditions (light, sound, etc.)
    }
    
    // ===== ABSTRACT METHODS: Core interaction contracts =====
    
    /**
     * Handle touch-based interaction.
     * Each interactive artwork responds to touch differently.
     */
    void respondToTouch();
    
    /**
     * Handle motion/gesture-based interaction.
     * Movement detection and response varies by installation.
     */
    void respondToMotion();
    
    /**
     * Process voice commands or vocal interaction.
     * Each system may recognize different commands or sounds.
     * 
     * @param command Voice input received from user
     */
    void respondToVoice(String command);
    
    /**
     * Return to default/idle state.
     * Different artworks have different "resting" states.
     */
    void resetInteractionState();
    
    // ===== DEFAULT METHODS: Standard interaction behaviors =====
    
    /**
     * Prepare artwork for user interaction.
     * Standard setup routine that most interactive art can use.
     */
    default void enableInteractionMode() {
        System.out.println("🎯 Activating interactive mode...");
        System.out.println("   📡 Initializing sensors and input systems");
        System.out.println("   🎚️ Setting sensitivity to default level (" + DEFAULT_SENSITIVITY + "%)");
        System.out.println("   ⏱️ Configuring response timing (" + MAX_RESPONSE_TIME_MS + "ms max)");
        System.out.println("   🔄 Enabling automatic idle timeout (" + IDLE_TIMEOUT_SECONDS + "s)");
        System.out.println("✨ Interactive mode ready - artwork awaits your engagement!");
    }
    
    /**
     * Disable interaction systems for maintenance or closing.
     * Safe shutdown procedure for interactive components.
     */
    default void disableInteractionMode() {
        System.out.println("🔽 Disabling interactive mode...");
        System.out.println("   📡 Shutting down sensor systems");
        System.out.println("   💾 Saving interaction session data");
        
        resetInteractionState();  // Return to neutral state
        
        System.out.println("   🌙 Interaction systems safely disabled");
    }
    
    /**
     * Handle proximity-based interaction (user approaching/leaving).
     * Default behavior for presence detection.
     * 
     * @param distanceMeters Distance of nearest person in meters
     */
    default void respondToProximity(double distanceMeters) {
        if (distanceMeters <= 1.0) {
            System.out.println("👋 Welcome! You are now in the interaction zone");
            enableInteractionMode();
        } else if (distanceMeters <= 3.0) {
            System.out.println("👀 Sensing your presence... come closer to interact");
        } else if (distanceMeters > 5.0) {
            System.out.println("🚶 Visitor leaving interaction area - returning to idle");
            resetInteractionState();
        }
    }
    
    /**
     * Provide audio feedback for interactions.
     * Standard sound response system.
     * 
     * @param interactionType Type of interaction that occurred
     */
    default void playInteractionFeedback(String interactionType) {
        System.out.println("🔊 Audio feedback: " + getInteractionSound(interactionType));
    }
    
    /**
     * Log interaction for analytics and improvement.
     * Standard interaction tracking.
     * 
     * @param interactionType What type of interaction occurred
     * @param userCount Estimated number of users interacting
     */
    default void logInteraction(String interactionType, int userCount) {
        String timestamp = java.time.LocalDateTime.now().toString();
        System.out.println("📊 Interaction logged: " + interactionType + 
                          " | Users: " + userCount + " | Time: " + timestamp);
    }
    
    /**
     * Provide visual feedback for interactions.
     * Default visual response system.
     * 
     * @param intensity Feedback intensity (0-100)
     * @param color Color theme for the feedback
     */
    default void showVisualFeedback(int intensity, String color) {
        String[] patterns = {"✨", "🌟", "💫", "⭐", "🔆"};
        String pattern = patterns[Math.min(intensity / 20, patterns.length - 1)];
        
        System.out.println(pattern + " Visual feedback: " + color + 
                          " pulse at " + intensity + "% intensity " + pattern);
    }
    
    /**
     * Get appropriate sound for interaction type.
     * Helper for audio feedback.
     */
    private String getInteractionSound(String interactionType) {
        return switch (interactionType.toLowerCase()) {
            case "touch" -> "Gentle chime 🎵";
            case "motion" -> "Whoosh sound 🌬️";
            case "voice" -> "Acknowledgment tone 📢";
            case "proximity" -> "Welcome bell 🔔";
            default -> "Generic response beep 🔊";
        };
    }
    
    // ===== STATIC METHODS: Interaction utilities and standards =====
    
    /**
     * Validate interaction response time is within acceptable limits.
     * 
     * @param responseTimeMs Measured response time in milliseconds
     * @return true if response time is acceptable
     */
    static boolean isAcceptableResponseTime(long responseTimeMs) {
        return responseTimeMs <= MAX_RESPONSE_TIME_MS;
    }
    
    /**
     * Calculate optimal sensitivity based on environment.
     * 
     * @param ambientNoise Background noise level (0-100)
     * @param lighting Light level in the space (0-100)
     * @param crowdSize Number of people in the area
     * @return Recommended sensitivity setting
     */
    static int calculateOptimalSensitivity(int ambientNoise, int lighting, int crowdSize) {
        int baseSensitivity = DEFAULT_SENSITIVITY;
        
        // Adjust for noise - higher noise requires higher sensitivity
        if (ambientNoise > 70) baseSensitivity += 15;
        else if (ambientNoise < 30) baseSensitivity -= 10;
        
        // Adjust for lighting - low light might need higher sensitivity
        if (lighting < 30) baseSensitivity += 10;
        
        // Adjust for crowd - more people might need lower sensitivity to avoid chaos
        if (crowdSize > 5) baseSensitivity -= 20;
        else if (crowdSize > 10) baseSensitivity -= 30;
        
        // Keep within bounds
        baseSensitivity = Math.max(10, Math.min(90, baseSensitivity));
        
        System.out.println("🎯 Optimal sensitivity calculation:");
        System.out.println("   Base: " + DEFAULT_SENSITIVITY + "% | Noise: " + ambientNoise + 
                          "% | Light: " + lighting + "% | Crowd: " + crowdSize);
        System.out.println("   Recommended: " + baseSensitivity + "%");
        
        return baseSensitivity;
    }
    
    /**
     * Generate interaction statistics summary.
     * 
     * @param touchCount Number of touch interactions
     * @param motionCount Number of motion interactions  
     * @param voiceCount Number of voice interactions
     * @param sessionDurationMinutes Total session time
     * @return Statistics summary
     */
    static String generateInteractionStats(int touchCount, int motionCount, 
                                         int voiceCount, int sessionDurationMinutes) {
        int totalInteractions = touchCount + motionCount + voiceCount;
        double interactionsPerMinute = sessionDurationMinutes > 0 ? 
                                     (double) totalInteractions / sessionDurationMinutes : 0;
        
        StringBuilder stats = new StringBuilder();
        stats.append("📊 INTERACTION SESSION STATISTICS\n");
        stats.append("═══════════════════════════════════\n");
        stats.append("Duration: ").append(sessionDurationMinutes).append(" minutes\n");
        stats.append("Total Interactions: ").append(totalInteractions).append("\n");
        stats.append("  • Touch: ").append(touchCount).append("\n");
        stats.append("  • Motion: ").append(motionCount).append("\n");
        stats.append("  • Voice: ").append(voiceCount).append("\n");
        stats.append("Engagement Rate: ").append(String.format("%.1f", interactionsPerMinute))
              .append(" interactions/minute\n");
        
        // Add engagement quality assessment
        if (interactionsPerMinute > 2.0) {
            stats.append("Assessment: High engagement 🎉");
        } else if (interactionsPerMinute > 0.5) {
            stats.append("Assessment: Moderate engagement 👍");
        } else {
            stats.append("Assessment: Low engagement - consider adjustments 🤔");
        }
        
        return stats.toString();
    }
    
    /**
     * Determine most popular interaction mode from usage data.
     * 
     * @param touchCount Touch interactions
     * @param motionCount Motion interactions
     * @param voiceCount Voice interactions
     * @return Most used interaction mode
     */
    static InteractionMode getMostPopularMode(int touchCount, int motionCount, int voiceCount) {
        if (touchCount >= motionCount && touchCount >= voiceCount) {
            return InteractionMode.TOUCH;
        } else if (motionCount >= voiceCount) {
            return InteractionMode.GESTURE;
        } else {
            return InteractionMode.VOICE;
        }
    }
}

/*
 * INTERACTIVE INTERFACE WISDOM:
 * 
 * 1. USER EXPERIENCE FOCUS: Constants and methods prioritize responsive,
 *    intuitive interaction that respects user expectations
 * 
 * 2. MULTI-MODAL SUPPORT: Interface supports various interaction types
 *    (touch, voice, gesture, proximity) without forcing all implementations
 * 
 * 3. ADAPTIVE BEHAVIOR: Default methods can adjust to environmental conditions
 *    and provide appropriate feedback for different contexts
 * 
 * 4. ANALYTICS INTEGRATION: Built-in logging and statistics help improve
 *    interactive experiences over time
 * 
 * 5. GRACEFUL DEGRADATION: Systems can work with partial implementations
 *    and provide fallback behaviors
 * 
 * 6. STANDARD PROTOCOLS: Consistent interaction patterns across different
 *    artworks create familiar experiences for gallery visitors
 * 
 * This interface shows how abstraction can define complex behavioral contracts
 * while remaining flexible enough to accommodate diverse implementations.
 * The key insight: interactivity is about creating meaningful dialogue between
 * human and art, regardless of the underlying technology.
 * 
 * "True interactivity transforms the observer into a participant,
 *  the artwork into a conversation, and the gallery into a living space."
 */
