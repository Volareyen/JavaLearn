/**
 * 🏠 SMART HOME AUTOMATION SYSTEM - THE MASTER'S SOLUTION
 * 
 * This is a complete implementation of the Smart Home System challenge,
 * demonstrating advanced State and Behavior design principles.
 * 
 * Key Concepts Demonstrated:
 * - Rich state modeling with comprehensive device attributes
 * - Intelligent behavior that reads and modifies state
 * - State-dependent logic and validation
 * - Complex state transitions and automation
 * - Device interaction and home-wide coordination
 * - Real-world smart home functionality
 */

// ═══════════════════════════════════════════════════════════════════════════════
// 📺 SMARTTV CLASS - COMPREHENSIVE STATE AND BEHAVIOR
// ═══════════════════════════════════════════════════════════════════════════════

class SmartTV {
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🎯 RICH STATE - What the SmartTV IS
    // ═══════════════════════════════════════════════════════════════════════
    
    boolean isPoweredOn;          // Power state
    int currentChannel;           // Current channel (1-999)
    int volumeLevel;             // Volume (0-100)
    String inputSource;          // "Cable", "Netflix", "Gaming", etc.
    int screenBrightness;        // Brightness (0-100)
    boolean isMuted;             // Mute state
    String currentApp;           // Current streaming app
    int viewingTimeToday;        // Minutes watched today
    boolean parentalControlsActive; // Parental restrictions
    boolean internetConnected;   // WiFi connection status
    String[] favoriteChannels;   // User's favorite channels
    int maxVolume;              // Maximum allowed volume
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🚀 INTELLIGENT BEHAVIOR - What the SmartTV CAN DO
    // ═══════════════════════════════════════════════════════════════════════
    
    void initializeTV(String location) {
        isPoweredOn = false;
        currentChannel = 1;
        volumeLevel = 50;
        inputSource = "Cable";
        screenBrightness = 75;
        isMuted = false;
        currentApp = "Home";
        viewingTimeToday = 0;
        parentalControlsActive = false;
        internetConnected = true;
        favoriteChannels = new String[]{"1", "5", "10", "25", "50"};
        maxVolume = 100;
        
        System.out.println("📺 SmartTV initialized in " + location);
    }
    
    void powerOn() {
        if (!isPoweredOn) {
            isPoweredOn = true;
            System.out.println("📺 TV powered on - Channel " + currentChannel);
            smartAdjustSettings();
        } else {
            System.out.println("📺 TV is already on");
        }
    }
    
    void powerOff() {
        if (isPoweredOn) {
            isPoweredOn = false;
            System.out.println("📺 TV powered off - Watched " + viewingTimeToday + " minutes today");
        } else {
            System.out.println("📺 TV is already off");
        }
    }
    
    void changeChannel(int channel) {
        if (!isPoweredOn) {
            System.out.println("❌ Cannot change channel - TV is off");
            return;
        }
        
        if (channel < 1 || channel > 999) {
            System.out.println("❌ Invalid channel: " + channel + " (Valid range: 1-999)");
            return;
        }
        
        if (parentalControlsActive && !canAccessContent("Channel " + channel)) {
            System.out.println("🔒 Channel " + channel + " blocked by parental controls");
            return;
        }
        
        currentChannel = channel;
        inputSource = "Cable";
        viewingTimeToday += 2; // Channel changes count as viewing time
        System.out.println("📺 Changed to channel " + channel);
    }
    
    void adjustVolume(int change) {
        if (!isPoweredOn) {
            System.out.println("❌ Cannot adjust volume - TV is off");
            return;
        }
        
        int newVolume = volumeLevel + change;
        if (newVolume < 0) newVolume = 0;
        if (newVolume > maxVolume) newVolume = maxVolume;
        
        volumeLevel = newVolume;
        if (isMuted && change > 0) {
            isMuted = false;
            System.out.println("🔊 Unmuted - Volume: " + volumeLevel);
        } else {
            System.out.println("🔊 Volume: " + volumeLevel);
        }
    }
    
    void setInputSource(String source) {
        if (!isPoweredOn) {
            System.out.println("❌ Cannot change input - TV is off");
            return;
        }
        
        String[] validSources = {"Cable", "Netflix", "Gaming", "YouTube", "HDMI1", "HDMI2"};
        boolean validSource = false;
        for (String valid : validSources) {
            if (valid.equals(source)) {
                validSource = true;
                break;
            }
        }
        
        if (!validSource) {
            System.out.println("❌ Invalid input source: " + source);
            return;
        }
        
        if (source.equals("Netflix") || source.equals("YouTube")) {
            if (!internetConnected) {
                System.out.println("❌ Cannot access " + source + " - No internet connection");
                return;
            }
        }
        
        inputSource = source;
        currentApp = source;
        System.out.println("📺 Input changed to " + source);
    }
    
    void openApp(String appName) {
        if (!isPoweredOn) {
            System.out.println("❌ Cannot open app - TV is off");
            return;
        }
        
        if (!internetConnected) {
            System.out.println("❌ Cannot open " + appName + " - No internet connection");
            return;
        }
        
        if (parentalControlsActive && !canAccessContent(appName)) {
            System.out.println("🔒 " + appName + " blocked by parental controls");
            return;
        }
        
        currentApp = appName;
        inputSource = "Smart Apps";
        viewingTimeToday += 1;
        System.out.println("📱 Opened " + appName);
    }
    
    void setBrightness(int brightness) {
        if (!isPoweredOn) {
            System.out.println("❌ Cannot adjust brightness - TV is off");
            return;
        }
        
        if (brightness < 0 || brightness > 100) {
            System.out.println("❌ Invalid brightness: " + brightness + " (Valid range: 0-100)");
            return;
        }
        
        screenBrightness = brightness;
        System.out.println("💡 Screen brightness set to " + brightness + "%");
    }
    
    void enableParentalControls(boolean enable) {
        parentalControlsActive = enable;
        if (enable) {
            maxVolume = 75; // Reduce max volume with parental controls
            System.out.println("🔒 Parental controls enabled - Max volume reduced to 75");
        } else {
            maxVolume = 100;
            System.out.println("🔓 Parental controls disabled");
        }
    }
    
    void displayStatus() {
        System.out.println("📺 ═══════════════════════════════════════");
        System.out.println("   SMART TV STATUS");
        System.out.println("   ═══════════════════════════════════════");
        System.out.println("   Power: " + (isPoweredOn ? "ON" : "OFF"));
        if (isPoweredOn) {
            System.out.println("   Channel: " + currentChannel);
            System.out.println("   Input: " + inputSource);
            System.out.println("   App: " + currentApp);
            System.out.println("   Volume: " + volumeLevel + (isMuted ? " (MUTED)" : ""));
            System.out.println("   Brightness: " + screenBrightness + "%");
        }
        System.out.println("   Internet: " + (internetConnected ? "Connected" : "Disconnected"));
        System.out.println("   Parental Controls: " + (parentalControlsActive ? "Active" : "Inactive"));
        System.out.println("   Viewing Time Today: " + viewingTimeToday + " minutes");
        System.out.println("   ═══════════════════════════════════════");
    }
    
    void generateViewingReport() {
        System.out.println("📊 VIEWING REPORT:");
        System.out.println("   Total viewing time today: " + viewingTimeToday + " minutes");
        System.out.println("   Average session: " + (viewingTimeToday > 0 ? viewingTimeToday / 3 : 0) + " minutes");
        System.out.println("   Most used input: " + inputSource);
        System.out.println("   Current app: " + currentApp);
    }
    
    boolean canAccessContent(String content) {
        if (!parentalControlsActive) return true;
        
        // Simple parental control logic
        String[] blockedContent = {"Channel 99", "Gaming", "Late Night Movies"};
        for (String blocked : blockedContent) {
            if (content.contains(blocked)) {
                return false;
            }
        }
        return true;
    }
    
    void smartAdjustSettings() {
        if (!isPoweredOn) return;
        
        // Auto-adjust brightness based on "time of day" (simulated)
        int hour = 14; // Simulate 2 PM
        if (hour >= 22 || hour <= 6) {
            setBrightness(30); // Night mode
            System.out.println("🌙 Night mode activated - Brightness reduced");
        } else if (hour >= 18) {
            setBrightness(60); // Evening mode
            System.out.println("🌅 Evening mode - Moderate brightness");
        } else {
            setBrightness(85); // Day mode
            System.out.println("☀️ Day mode - Full brightness");
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🌡️ SMARTTHERMOSTAT CLASS - CLIMATE CONTROL MASTERY
// ═══════════════════════════════════════════════════════════════════════════════

class SmartThermostat {
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🎯 COMPREHENSIVE STATE
    // ═══════════════════════════════════════════════════════════════════════
    
    double currentTemperature;    // Actual room temperature
    double targetTemperature;     // Desired temperature
    String mode;                  // "Heat", "Cool", "Auto", "Off"
    String fanSpeed;             // "Low", "Medium", "High", "Auto"
    boolean scheduleActive;       // Following programmed schedule
    boolean energySaveMode;       // Energy-saving mode active
    String filterStatus;          // "Clean", "Dirty", "Replace"
    int humidityLevel;           // Current humidity (0-100%)
    String systemStatus;         // "Idle", "Heating", "Cooling"
    double energyUsageToday;     // kWh used today
    int[] schedule;              // 24-hour temperature schedule
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🚀 INTELLIGENT CLIMATE BEHAVIOR
    // ═══════════════════════════════════════════════════════════════════════
    
    void initializeThermostat(double startTemp) {
        currentTemperature = startTemp;
        targetTemperature = 72.0;
        mode = "Auto";
        fanSpeed = "Auto";
        scheduleActive = false;
        energySaveMode = false;
        filterStatus = "Clean";
        humidityLevel = 45;
        systemStatus = "Idle";
        energyUsageToday = 0.0;
        schedule = new int[24]; // Initialize 24-hour schedule
        for (int i = 0; i < 24; i++) {
            schedule[i] = 72; // Default 72°F all day
        }
        
        System.out.println("🌡️ Smart Thermostat initialized at " + startTemp + "°F");
    }
    
    void setTargetTemperature(double temp) {
        if (temp < 45 || temp > 90) {
            System.out.println("❌ Invalid temperature: " + temp + "°F (Range: 45-90°F)");
            return;
        }
        
        targetTemperature = temp;
        updateSystemStatus();
        System.out.println("🌡️ Target temperature set to " + temp + "°F");
        
        if (energySaveMode && Math.abs(temp - currentTemperature) > 5) {
            System.out.println("⚡ Large temperature change - Energy save mode disabled");
            energySaveMode = false;
        }
    }
    
    void changeMode(String newMode) {
        String[] validModes = {"Heat", "Cool", "Auto", "Off"};
        boolean validMode = false;
        for (String valid : validModes) {
            if (valid.equals(newMode)) {
                validMode = true;
                break;
            }
        }
        
        if (!validMode) {
            System.out.println("❌ Invalid mode: " + newMode + " (Valid: Heat, Cool, Auto, Off)");
            return;
        }
        
        mode = newMode;
        updateSystemStatus();
        System.out.println("🌡️ Mode changed to " + newMode);
        
        if (newMode.equals("Off")) {
            systemStatus = "Idle";
            System.out.println("💤 System turned off");
        }
    }
    
    void setFanSpeed(String speed) {
        String[] validSpeeds = {"Low", "Medium", "High", "Auto"};
        boolean validSpeed = false;
        for (String valid : validSpeeds) {
            if (valid.equals(speed)) {
                validSpeed = true;
                break;
            }
        }
        
        if (!validSpeed) {
            System.out.println("❌ Invalid fan speed: " + speed);
            return;
        }
        
        fanSpeed = speed;
        System.out.println("💨 Fan speed set to " + speed);
    }
    
    void enableEnergyMode(boolean enable) {
        energySaveMode = enable;
        if (enable) {
            // Adjust target temperature for energy savings
            if (mode.equals("Cool") || mode.equals("Auto")) {
                targetTemperature += 2; // Raise cooling target
            } else if (mode.equals("Heat")) {
                targetTemperature -= 2; // Lower heating target
            }
            fanSpeed = "Low";
            System.out.println("⚡ Energy save mode enabled - Target adjusted to " + targetTemperature + "°F");
        } else {
            System.out.println("⚡ Energy save mode disabled");
        }
    }
    
    void checkFilter() {
        System.out.println("🔧 Checking filter status...");
        
        // Simulate filter degradation based on usage
        if (energyUsageToday > 10) {
            filterStatus = "Dirty";
        } else if (energyUsageToday > 20) {
            filterStatus = "Replace";
        }
        
        System.out.println("🔧 Filter status: " + filterStatus);
        
        if (filterStatus.equals("Dirty")) {
            System.out.println("⚠️ Filter needs cleaning soon");
        } else if (filterStatus.equals("Replace")) {
            System.out.println("🚨 Filter needs replacement!");
        }
    }
    
    void setSchedule(int hour, double temp) {
        if (hour < 0 || hour > 23) {
            System.out.println("❌ Invalid hour: " + hour + " (Range: 0-23)");
            return;
        }
        
        if (temp < 45 || temp > 90) {
            System.out.println("❌ Invalid temperature for schedule: " + temp + "°F");
            return;
        }
        
        schedule[hour] = (int)temp;
        scheduleActive = true;
        System.out.println("📅 Schedule set: " + hour + ":00 → " + temp + "°F");
    }
    
    void displayStatus() {
        System.out.println("🌡️ ═══════════════════════════════════════");
        System.out.println("   SMART THERMOSTAT STATUS");
        System.out.println("   ═══════════════════════════════════════");
        System.out.println("   Current Temperature: " + currentTemperature + "°F");
        System.out.println("   Target Temperature: " + targetTemperature + "°F");
        System.out.println("   Mode: " + mode);
        System.out.println("   System Status: " + systemStatus);
        System.out.println("   Fan Speed: " + fanSpeed);
        System.out.println("   Humidity: " + humidityLevel + "%");
        System.out.println("   Filter: " + filterStatus);
        System.out.println("   Schedule: " + (scheduleActive ? "Active" : "Inactive"));
        System.out.println("   Energy Save: " + (energySaveMode ? "On" : "Off"));
        System.out.println("   Energy Usage Today: " + energyUsageToday + " kWh");
        System.out.println("   ═══════════════════════════════════════");
    }
    
    double calculateEfficiency() {
        double tempDifference = Math.abs(currentTemperature - targetTemperature);
        double efficiency = Math.max(0, 100 - (tempDifference * 10));
        
        System.out.println("📊 Current efficiency: " + efficiency + "%");
        return efficiency;
    }
    
    void performMaintenance() {
        System.out.println("🔧 Performing maintenance...");
        filterStatus = "Clean";
        energyUsageToday = 0;
        System.out.println("✅ Maintenance complete - Filter cleaned, usage reset");
    }
    
    void smartAdjust() {
        if (mode.equals("Off")) return;
        
        // Simulate temperature changes and system response
        double tempDiff = targetTemperature - currentTemperature;
        
        if (Math.abs(tempDiff) > 0.5) {
            if (tempDiff > 0) {
                systemStatus = "Heating";
                currentTemperature += 0.5;
                energyUsageToday += 0.2;
            } else {
                systemStatus = "Cooling";
                currentTemperature -= 0.5;
                energyUsageToday += 0.3;
            }
            System.out.println("🌡️ " + systemStatus + " - Current temp: " + currentTemperature + "°F");
        } else {
            systemStatus = "Idle";
        }
    }
    
    private void updateSystemStatus() {
        if (mode.equals("Off")) {
            systemStatus = "Idle";
            return;
        }
        
        double tempDiff = targetTemperature - currentTemperature;
        if (Math.abs(tempDiff) > 1.0) {
            if (tempDiff > 0 && (mode.equals("Heat") || mode.equals("Auto"))) {
                systemStatus = "Heating";
            } else if (tempDiff < 0 && (mode.equals("Cool") || mode.equals("Auto"))) {
                systemStatus = "Cooling";
            }
        } else {
            systemStatus = "Idle";
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 💡 SMARTLIGHTBULB CLASS - INTELLIGENT LIGHTING
// ═══════════════════════════════════════════════════════════════════════════════

class SmartLightBulb {
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🎯 LIGHTING STATE
    // ═══════════════════════════════════════════════════════════════════════
    
    boolean isPoweredOn;         // Power state
    int brightnessLevel;         // Brightness (0-100)
    String currentColor;         // Current color for RGB bulbs
    int colorTemperature;        // Warm/cool white (2700-6500K)
    boolean scheduleActive;      // Following automated schedule
    double energyUsage;          // Watts currently being used
    int lifetimeHours;          // Total hours the bulb has been on
    String roomLocation;         // Which room the bulb is in
    String currentScene;         // Current lighting scene
    int onHour, offHour;        // Schedule times
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🚀 LIGHTING BEHAVIOR
    // ═══════════════════════════════════════════════════════════════════════
    
    void initializeBulb(String room) {
        isPoweredOn = false;
        brightnessLevel = 100;
        currentColor = "Warm White";
        colorTemperature = 3000; // Warm white
        scheduleActive = false;
        energyUsage = 0.0;
        lifetimeHours = 0;
        roomLocation = room;
        currentScene = "Normal";
        onHour = 18; // Default: on at 6 PM
        offHour = 23; // Default: off at 11 PM
        
        System.out.println("💡 Smart Light initialized in " + room);
    }
    
    void turnOn() {
        if (!isPoweredOn) {
            isPoweredOn = true;
            energyUsage = calculateEnergyUsage();
            System.out.println("💡 Light turned ON in " + roomLocation + 
                             " - Brightness: " + brightnessLevel + "%");
        } else {
            System.out.println("💡 Light is already on in " + roomLocation);
        }
    }
    
    void turnOff() {
        if (isPoweredOn) {
            isPoweredOn = false;
            energyUsage = 0.0;
            System.out.println("💡 Light turned OFF in " + roomLocation);
        } else {
            System.out.println("💡 Light is already off in " + roomLocation);
        }
    }
    
    void setBrightness(int brightness) {
        if (brightness < 0 || brightness > 100) {
            System.out.println("❌ Invalid brightness: " + brightness + "% (Range: 0-100%)");
            return;
        }
        
        brightnessLevel = brightness;
        if (isPoweredOn) {
            energyUsage = calculateEnergyUsage();
            System.out.println("💡 Brightness set to " + brightness + "% in " + roomLocation);
            
            if (brightness == 0) {
                turnOff();
            }
        } else if (brightness > 0) {
            turnOn();
        }
    }
    
    void setColor(String color) {
        if (!isPoweredOn) {
            System.out.println("❌ Cannot change color - Light is off");
            return;
        }
        
        String[] validColors = {"Red", "Green", "Blue", "Yellow", "Purple", "Orange", 
                               "Warm White", "Cool White", "Daylight"};
        boolean validColor = false;
        for (String valid : validColors) {
            if (valid.equals(color)) {
                validColor = true;
                break;
            }
        }
        
        if (!validColor) {
            System.out.println("❌ Invalid color: " + color);
            return;
        }
        
        currentColor = color;
        
        // Adjust color temperature based on color
        if (color.equals("Warm White")) {
            colorTemperature = 3000;
        } else if (color.equals("Cool White")) {
            colorTemperature = 4000;
        } else if (color.equals("Daylight")) {
            colorTemperature = 6500;
        }
        
        System.out.println("🎨 Color changed to " + color + " in " + roomLocation);
    }
    
    void setColorTemperature(int temp) {
        if (!isPoweredOn) {
            System.out.println("❌ Cannot change color temperature - Light is off");
            return;
        }
        
        if (temp < 2700 || temp > 6500) {
            System.out.println("❌ Invalid color temperature: " + temp + "K (Range: 2700-6500K)");
            return;
        }
        
        colorTemperature = temp;
        
        // Update color description based on temperature
        if (temp <= 3000) {
            currentColor = "Warm White";
        } else if (temp <= 4000) {
            currentColor = "Cool White";
        } else {
            currentColor = "Daylight";
        }
        
        System.out.println("🌡️ Color temperature set to " + temp + "K (" + currentColor + ")");
    }
    
    void setSchedule(int onTime, int offTime) {
        if (onTime < 0 || onTime > 23 || offTime < 0 || offTime > 23) {
            System.out.println("❌ Invalid schedule times (Range: 0-23 hours)");
            return;
        }
        
        onHour = onTime;
        offHour = offTime;
        scheduleActive = true;
        
        System.out.println("📅 Schedule set for " + roomLocation + 
                         ": ON at " + onTime + ":00, OFF at " + offTime + ":00");
    }
    
    void displayStatus() {
        System.out.println("💡 ═══════════════════════════════════════");
        System.out.println("   SMART LIGHT STATUS - " + roomLocation.toUpperCase());
        System.out.println("   ═══════════════════════════════════════");
        System.out.println("   Power: " + (isPoweredOn ? "ON" : "OFF"));
        if (isPoweredOn) {
            System.out.println("   Brightness: " + brightnessLevel + "%");
            System.out.println("   Color: " + currentColor);
            System.out.println("   Color Temperature: " + colorTemperature + "K");
            System.out.println("   Current Scene: " + currentScene);
            System.out.println("   Energy Usage: " + energyUsage + " watts");
        }
        System.out.println("   Schedule: " + (scheduleActive ? 
                         "Active (ON:" + onHour + ":00, OFF:" + offHour + ":00)" : "Inactive"));
        System.out.println("   Lifetime Hours: " + lifetimeHours);
        System.out.println("   ═══════════════════════════════════════");
    }
    
    double calculateEnergyCost() {
        double costPerKwh = 0.12; // $0.12 per kWh
        double dailyUsage = (energyUsage / 1000.0) * 8; // 8 hours average daily use
        double dailyCost = dailyUsage * costPerKwh;
        
        System.out.println("💰 Estimated daily cost: $" + String.format("%.2f", dailyCost));
        return dailyCost;
    }
    
    void setScene(String sceneName) {
        if (!isPoweredOn) {
            turnOn();
        }
        
        currentScene = sceneName;
        
        // Predefined scene settings
        switch (sceneName) {
            case "Reading":
                setBrightness(90);
                setColorTemperature(4000);
                break;
            case "Relaxing":
                setBrightness(30);
                setColor("Warm White");
                break;
            case "Party":
                setBrightness(100);
                setColor("Purple");
                break;
            case "Sleep":
                setBrightness(5);
                setColor("Warm White");
                break;
            case "Work":
                setBrightness(85);
                setColor("Daylight");
                break;
            default:
                setBrightness(75);
                setColor("Warm White");
                sceneName = "Normal";
        }
        
        System.out.println("🎬 Scene set to '" + sceneName + "' in " + roomLocation);
    }
    
    void smartAdjust() {
        if (!isPoweredOn) return;
        
        // Simulate time-based adjustment (simplified)
        int hour = 20; // Simulate 8 PM
        
        if (hour >= 22 || hour <= 6) {
            // Night mode
            setBrightness(20);
            setColor("Warm White");
            System.out.println("🌙 Night mode auto-activated in " + roomLocation);
        } else if (hour >= 18) {
            // Evening mode
            setBrightness(60);
            setColorTemperature(3000);
            System.out.println("🌅 Evening mode auto-activated in " + roomLocation);
        } else {
            // Day mode
            setBrightness(80);
            setColor("Daylight");
            System.out.println("☀️ Day mode auto-activated in " + roomLocation);
        }
        
        // Update lifetime hours (simplified)
        lifetimeHours++;
    }
    
    private double calculateEnergyUsage() {
        // Calculate watts based on brightness (LED bulb: max 10W)
        return (brightnessLevel / 100.0) * 10.0;
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🏠 SMARTHOME CONTROLLER - MASTER SYSTEM INTEGRATION
// ═══════════════════════════════════════════════════════════════════════════════

class SmartHome {
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🎯 HOME SYSTEM STATE
    // ═══════════════════════════════════════════════════════════════════════
    
    SmartTV[] tvs;
    SmartThermostat[] thermostats;
    SmartLightBulb[] lights;
    String currentScene;
    boolean awayMode;
    double totalEnergyUsage;
    
    // ═══════════════════════════════════════════════════════════════════════
    // 🚀 HOME AUTOMATION BEHAVIOR
    // ═══════════════════════════════════════════════════════════════════════
    
    void initializeHome() {
        // Initialize device arrays
        tvs = new SmartTV[2];
        thermostats = new SmartThermostat[1];
        lights = new SmartLightBulb[4];
        
        // Create and initialize devices
        tvs[0] = new SmartTV();
        tvs[0].initializeTV("Living Room");
        tvs[1] = new SmartTV();
        tvs[1].initializeTV("Bedroom");
        
        thermostats[0] = new SmartThermostat();
        thermostats[0].initializeThermostat(72.0);
        
        lights[0] = new SmartLightBulb();
        lights[0].initializeBulb("Living Room");
        lights[1] = new SmartLightBulb();
        lights[1].initializeBulb("Bedroom");
        lights[2] = new SmartLightBulb();
        lights[2].initializeBulb("Kitchen");
        lights[3] = new SmartLightBulb();
        lights[3].initializeBulb("Bathroom");
        
        currentScene = "Normal";
        awayMode = false;
        totalEnergyUsage = 0.0;
        
        System.out.println("🏠 Smart Home System initialized with " + 
                         tvs.length + " TVs, " + thermostats.length + 
                         " thermostat, " + lights.length + " lights");
    }
    
    void setScene(String sceneName) {
        currentScene = sceneName;
        System.out.println("🎬 Setting home scene to: " + sceneName);
        
        switch (sceneName) {
            case "Movie Night":
                // Dim lights, turn on living room TV
                lights[0].setScene("Relaxing"); // Living room
                for (int i = 1; i < lights.length; i++) {
                    lights[i].turnOff(); // Turn off other lights
                }
                tvs[0].powerOn();
                tvs[0].setBrightness(40);
                thermostats[0].setTargetTemperature(70.0);
                break;
                
            case "Good Morning":
                // Gradual light increase, comfortable temperature
                for (SmartLightBulb light : lights) {
                    light.setScene("Work");
                }
                thermostats[0].setTargetTemperature(72.0);
                break;
                
            case "Bedtime":
                // Dim lights, lower temperature, turn off TVs
                for (SmartLightBulb light : lights) {
                    light.setScene("Sleep");
                }
                for (SmartTV tv : tvs) {
                    tv.powerOff();
                }
                thermostats[0].setTargetTemperature(68.0);
                break;
                
            case "Party":
                // Bright colorful lights, comfortable temperature
                for (SmartLightBulb light : lights) {
                    light.setScene("Party");
                }
                thermostats[0].setTargetTemperature(70.0);
                break;
        }
        
        System.out.println("✅ Scene '" + sceneName + "' activated");
    }
    
    void enableAwayMode() {
        awayMode = true;
        System.out.println("🚪 Activating Away Mode...");
        
        // Turn off all TVs
        for (SmartTV tv : tvs) {
            tv.powerOff();
        }
        
        // Set lights to energy-save schedule
        for (SmartLightBulb light : lights) {
            light.setSchedule(18, 22); // Simulate presence
            light.turnOff();
        }
        
        // Set thermostat to energy-save mode
        for (SmartThermostat thermostat : thermostats) {
            thermostat.enableEnergyMode(true);
        }
        
        System.out.println("✅ Away Mode activated - Energy savings enabled");
    }
    
    void disableAwayMode() {
        awayMode = false;
        System.out.println("🏠 Welcome home! Disabling Away Mode...");
        
        // Restore normal thermostat operation
        for (SmartThermostat thermostat : thermostats) {
            thermostat.enableEnergyMode(false);
            thermostat.setTargetTemperature(72.0);
        }
        
        // Turn on main lights
        lights[0].turnOn(); // Living room
        lights[2].turnOn(); // Kitchen
        
        System.out.println("✅ Away Mode disabled - Welcome home!");
    }
    
    void generateEnergyReport() {
        System.out.println("⚡ ═══════════════════════════════════════");
        System.out.println("   SMART HOME ENERGY REPORT");
        System.out.println("   ═══════════════════════════════════════");
        
        double totalUsage = 0.0;
        double totalCost = 0.0;
        
        // Calculate lighting energy
        double lightingUsage = 0.0;
        for (SmartLightBulb light : lights) {
            lightingUsage += light.energyUsage;
        }
        
        // Calculate thermostat energy
        double climateUsage = 0.0;
        for (SmartThermostat thermostat : thermostats) {
            climateUsage += thermostat.energyUsageToday;
        }
        
        // Estimate TV energy (simplified)
        double tvUsage = 0.0;
        for (SmartTV tv : tvs) {
            if (tv.isPoweredOn) {
                tvUsage += 0.15; // 150W per TV
            }
        }
        
        totalUsage = lightingUsage + climateUsage + tvUsage;
        totalCost = totalUsage * 0.12; // $0.12 per kWh
        
        System.out.println("   Lighting: " + String.format("%.2f", lightingUsage) + " kWh");
        System.out.println("   Climate Control: " + String.format("%.2f", climateUsage) + " kWh");
        System.out.println("   Entertainment: " + String.format("%.2f", tvUsage) + " kWh");
        System.out.println("   ───────────────────────────────────────");
        System.out.println("   Total Usage: " + String.format("%.2f", totalUsage) + " kWh");
        System.out.println("   Estimated Cost: $" + String.format("%.2f", totalCost));
        System.out.println("   Away Mode Savings: " + (awayMode ? "Active" : "Inactive"));
        System.out.println("   ═══════════════════════════════════════");
    }
    
    void performDailyAutomation() {
        System.out.println("🤖 Performing daily automation...");
        
        // Smart adjust all devices
        for (SmartLightBulb light : lights) {
            light.smartAdjust();
        }
        
        for (SmartThermostat thermostat : thermostats) {
            thermostat.smartAdjust();
        }
        
        for (SmartTV tv : tvs) {
            tv.smartAdjustSettings();
        }
        
        System.out.println("✅ Daily automation complete");
    }
    
    void displayHomeStatus() {
        System.out.println("🏠 ═══════════════════════════════════════");
        System.out.println("   SMART HOME STATUS DASHBOARD");
        System.out.println("   ═══════════════════════════════════════");
        System.out.println("   Current Scene: " + currentScene);
        System.out.println("   Away Mode: " + (awayMode ? "Active" : "Inactive"));
        System.out.println();
        
        // Quick status summary
        int lightsOn = 0;
        for (SmartLightBulb light : lights) {
            if (light.isPoweredOn) lightsOn++;
        }
        
        int tvsOn = 0;
        for (SmartTV tv : tvs) {
            if (tv.isPoweredOn) tvsOn++;
        }
        
        System.out.println("   📊 QUICK SUMMARY:");
        System.out.println("     Lights On: " + lightsOn + "/" + lights.length);
        System.out.println("     TVs On: " + tvsOn + "/" + tvs.length);
        System.out.println("     Climate: " + thermostats[0].systemStatus + 
                         " (" + thermostats[0].currentTemperature + "°F)");
        System.out.println("   ═══════════════════════════════════════");
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// 🎪 SMART HOME DEMONSTRATION - STATE AND BEHAVIOR IN ACTION
// ═══════════════════════════════════════════════════════════════════════════════

public class SmartHomeSystem {
    
    public static void main(String[] args) {
        
        System.out.println("🏠 ═══════════════════════════════════════════════════════");
        System.out.println("   WELCOME TO THE SMART HOME AUTOMATION SYSTEM");
        System.out.println("   Demonstrating Advanced State and Behavior Design!");
        System.out.println("   ═══════════════════════════════════════════════════════");
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🏠 SMART HOME INITIALIZATION
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("\n🏗️ INITIALIZING SMART HOME SYSTEM...\n");
        
        SmartHome home = new SmartHome();
        home.initializeHome();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 📺 SMART TV DEMONSTRATIONS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("📺 SMART TV OPERATIONS...\n");
        
        SmartTV livingRoomTV = home.tvs[0];
        livingRoomTV.powerOn();
        livingRoomTV.changeChannel(25);
        livingRoomTV.adjustVolume(10);
        livingRoomTV.openApp("Netflix");
        livingRoomTV.enableParentalControls(true);
        livingRoomTV.displayStatus();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🌡️ SMART THERMOSTAT DEMONSTRATIONS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🌡️ SMART THERMOSTAT OPERATIONS...\n");
        
        SmartThermostat mainThermostat = home.thermostats[0];
        mainThermostat.setTargetTemperature(75.0);
        mainThermostat.changeMode("Cool");
        mainThermostat.setSchedule(22, 68); // 10 PM to 68°F
        mainThermostat.smartAdjust();
        mainThermostat.displayStatus();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 💡 SMART LIGHTING DEMONSTRATIONS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("💡 SMART LIGHTING OPERATIONS...\n");
        
        SmartLightBulb livingRoomLight = home.lights[0];
        livingRoomLight.turnOn();
        livingRoomLight.setBrightness(80);
        livingRoomLight.setColor("Daylight");
        livingRoomLight.setScene("Reading");
        livingRoomLight.displayStatus();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🎬 SCENE CONTROL DEMONSTRATIONS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🎬 SMART HOME SCENE CONTROL...\n");
        
        home.displayHomeStatus();
        System.out.println();
        
        home.setScene("Movie Night");
        System.out.println();
        home.displayHomeStatus();
        System.out.println();
        
        home.setScene("Bedtime");
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🚪 AWAY MODE DEMONSTRATIONS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🚪 AWAY MODE DEMONSTRATION...\n");
        
        home.enableAwayMode();
        System.out.println();
        home.displayHomeStatus();
        System.out.println();
        
        // Simulate returning home
        home.disableAwayMode();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // ⚡ ENERGY MONITORING DEMONSTRATIONS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("⚡ ENERGY MONITORING...\n");
        
        home.generateEnergyReport();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🤖 AUTOMATION DEMONSTRATIONS
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🤖 DAILY AUTOMATION...\n");
        
        home.performDailyAutomation();
        System.out.println();
        
        // ═══════════════════════════════════════════════════════════════════════
        // 🏆 FINAL DEMONSTRATION SUMMARY
        // ═══════════════════════════════════════════════════════════════════════
        
        System.out.println("🏆 STATE AND BEHAVIOR MASTERY DEMONSTRATED!\n");
        
        System.out.println("✅ RICH STATE MODELING:");
        System.out.println("  • Comprehensive device attributes (power, settings, usage, status)");
        System.out.println("  • Complex state relationships (temperature affects system status)");
        System.out.println("  • State persistence and tracking (viewing time, energy usage)");
        System.out.println("  • Multi-dimensional state (physical + logical properties)");
        
        System.out.println("\n✅ INTELLIGENT BEHAVIOR:");
        System.out.println("  • State-dependent actions (can't change channel when TV is off)");
        System.out.println("  • State-modifying behaviors (volume changes affect mute status)");
        System.out.println("  • Smart automation (auto-adjust based on time/conditions)");
        System.out.println("  • Complex validation and error handling");
        
        System.out.println("\n✅ STATE-BEHAVIOR INTEGRATION:");
        System.out.println("  • Behaviors read state to make intelligent decisions");
        System.out.println("  • State changes trigger cascading behavior updates");
        System.out.println("  • Complex state transitions maintain consistency");
        System.out.println("  • Real-world device modeling with high fidelity");
        
        System.out.println("\n✅ SYSTEM-LEVEL COORDINATION:");
        System.out.println("  • Multiple objects working together through behavior calls");
        System.out.println("  • Scene control affecting multiple devices simultaneously");
        System.out.println("  • Energy monitoring across all device states");
        System.out.println("  • Home-wide automation and intelligent coordination");
        
        System.out.println("\n🏠 This demonstrates the ultimate power of State and Behavior:");
        System.out.println("   Objects that truly embody what they represent, with rich internal");
        System.out.println("   state and intelligent behavior that creates realistic, useful,");
        System.out.println("   and sophisticated smart home automation!");
    }
}

/*
 * 🎓 MASTER'S ANALYSIS - ADVANCED STATE AND BEHAVIOR MASTERY:
 * 
 * This solution demonstrates expert-level understanding of:
 * 
 * 1. COMPREHENSIVE STATE DESIGN:
 *    ✅ Rich, multi-dimensional state modeling
 *    ✅ State that enables intelligent behavior
 *    ✅ Proper data types and validation ranges
 *    ✅ State relationships and dependencies
 * 
 * 2. SOPHISTICATED BEHAVIOR IMPLEMENTATION:
 *    ✅ State-dependent logic and decision making
 *    ✅ Complex state transitions and updates
 *    ✅ Intelligent automation and smart features
 *    ✅ Robust validation and error handling
 * 
 * 3. SEAMLESS STATE-BEHAVIOR INTEGRATION:
 *    ✅ Behaviors that read state to make decisions
 *    ✅ State changes that trigger related updates
 *    ✅ Consistent state maintenance throughout operations
 *    ✅ Natural, intuitive object interactions
 * 
 * 4. REAL-WORLD MODELING EXCELLENCE:
 *    ✅ Objects that accurately represent smart home devices
 *    ✅ Realistic functionality and limitations
 *    ✅ Professional-grade feature implementation
 *    ✅ Scalable and extensible design patterns
 * 
 * This example proves that when State and Behavior are masterfully
 * designed together, they create objects that are not just code
 * constructs, but living, intelligent representations of real-world
 * entities with all their complexity, capability, and sophistication.
 */
