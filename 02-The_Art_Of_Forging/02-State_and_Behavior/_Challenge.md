# 🎯 **The Pupil's Trial: Smart Home Automation System**
*Mastering the Sacred Duality of State and Behavior*

---

## **🏠 THE SACRED CHALLENGE**

*Young seeker, you have witnessed the profound truth of State and Behavior through the realm of game characters. Now, you must demonstrate your mastery by creating a complete **Smart Home Automation System** that showcases the elegant dance between what objects ARE (state) and what objects CAN DO (behavior).*

*This trial will test your understanding of:*
- **Rich State Design** - Creating objects with meaningful, comprehensive state
- **Intelligent Behavior** - Implementing behaviors that read and modify state
- **State-Behavior Integration** - Making state and behavior work together harmoniously
- **Real-World Modeling** - Designing objects that mirror actual smart home devices
- **Complex Interactions** - Creating systems where objects collaborate through behavior

---

## **🏡 THE DOMAIN: INTELLIGENT HOME ECOSYSTEM**

*Your mission is to model a smart home system with interconnected devices that maintain state and exhibit intelligent behavior. Each device should have rich state and meaningful behaviors that reflect real smart home functionality.*

### **🌟 REQUIRED SMART DEVICES**

#### **📺 SmartTV (Primary Device)**
*Every SmartTV should have:*

**STATE (What the TV IS):**
- **Power Status** - On/off state
- **Current Channel** - What channel is selected (1-999)
- **Volume Level** - Current volume (0-100)
- **Input Source** - "Cable", "Netflix", "Gaming", "YouTube", etc.
- **Screen Brightness** - Brightness level (0-100)
- **Is Muted** - Whether sound is muted
- **Current App** - What streaming app is open
- **Viewing Time Today** - Minutes watched today
- **Parental Controls** - Whether restrictions are active
- **Internet Connected** - Whether connected to WiFi

**BEHAVIOR (What the TV CAN DO):**
- **Power Management** - Turn on/off, check power state
- **Channel Control** - Change channel, channel up/down
- **Volume Control** - Adjust volume, mute/unmute
- **Input Management** - Switch input sources
- **App Navigation** - Open streaming apps, navigate menus
- **Settings Control** - Adjust brightness, enable parental controls
- **Usage Tracking** - Track viewing time, generate reports
- **Smart Features** - Auto-adjust settings based on time of day

#### **🌡️ SmartThermostat (Secondary Device)**
*Every SmartThermostat should have:*

**STATE:**
- **Current Temperature** - Actual room temperature
- **Target Temperature** - Desired temperature setting
- **Mode** - "Heat", "Cool", "Auto", "Off"
- **Fan Speed** - "Low", "Medium", "High", "Auto"
- **Schedule Active** - Whether following programmed schedule
- **Energy Save Mode** - Whether in energy-saving mode
- **Filter Status** - "Clean", "Dirty", "Replace"
- **Humidity Level** - Current humidity percentage
- **System Status** - "Idle", "Heating", "Cooling"

**BEHAVIOR:**
- **Temperature Control** - Set target temperature, adjust by degrees
- **Mode Management** - Change heating/cooling modes
- **Schedule Management** - Set/modify temperature schedules
- **Energy Management** - Enable energy-saving features
- **Maintenance** - Check filter status, reset maintenance alerts
- **Climate Analysis** - Report temperature trends, efficiency

#### **💡 SmartLightBulb (Tertiary Device)**
*Every SmartLightBulb should have:*

**STATE:**
- **Power Status** - On/off state
- **Brightness Level** - Light intensity (0-100)
- **Color** - Current color (for RGB bulbs)
- **Color Temperature** - Warm/cool white setting
- **Schedule Active** - Whether following automated schedule
- **Energy Usage** - Watts currently being used
- **Lifetime Hours** - Total hours the bulb has been on
- **Room Location** - Which room the bulb is in

**BEHAVIOR:**
- **Power Control** - Turn on/off, toggle
- **Brightness Control** - Adjust brightness, dim/brighten
- **Color Management** - Change colors, set color temperature
- **Schedule Management** - Set automated on/off times
- **Energy Monitoring** - Track usage, calculate costs
- **Scene Control** - Set predefined lighting scenes

---

## **⚔️ THE TRIALS YOU MUST COMPLETE**

### **🥇 Trial 1: Device Foundation (Core Requirement)**
*Create all three smart device classes with complete state and behavior.*

**Requirements:**
1. **Rich State Design**: Each device must have at least 8-10 meaningful state fields
2. **Comprehensive Behavior**: Each device must have at least 10-12 methods
3. **State Validation**: All behaviors must validate state before making changes
4. **State-Dependent Logic**: Behaviors must change based on current state
5. **Initialization Methods**: Each device needs proper setup methods
6. **Status Display**: Each device must have detailed status display methods

### **🥈 Trial 2: Smart Interactions (Advanced Requirement)**
*Implement intelligent behaviors that demonstrate state-behavior mastery.*

**Requirements:**
1. **Smart Automation**: Devices should have "smart" modes that automatically adjust settings
2. **Energy Management**: Implement power-saving behaviors that modify multiple state values
3. **Usage Analytics**: Create methods that analyze state history and provide insights
4. **Error Handling**: Robust validation and error messages for invalid operations
5. **Complex State Transitions**: Behaviors that change multiple related state values
6. **Conditional Behavior**: Actions that behave differently based on current state

### **🥉 Trial 3: Home Integration (Master Level)**
*Create a SmartHome controller that manages all devices together.*

**Requirements:**
1. **Device Management**: Create arrays of devices and manage them collectively
2. **Scene Control**: Implement "scenes" that adjust multiple devices at once
3. **Home Automation**: Time-based automation that adjusts devices automatically
4. **Energy Monitoring**: Track and report energy usage across all devices
5. **Status Dashboard**: Comprehensive home status display
6. **Device Interaction**: Show devices working together intelligently

---

## **📋 DETAILED SPECIFICATIONS**

### **SmartTV Class Specifications**

```java
class SmartTV {
    // STATE - Minimum required fields
    boolean isPoweredOn;
    int currentChannel;
    int volumeLevel;
    String inputSource;
    int screenBrightness;
    boolean isMuted;
    String currentApp;
    int viewingTimeToday;
    boolean parentalControlsActive;
    boolean internetConnected;
    
    // BEHAVIOR - Minimum required methods
    void powerOn()
    void powerOff()
    void changeChannel(int channel)
    void adjustVolume(int change)
    void setInputSource(String source)
    void openApp(String appName)
    void setBrightness(int brightness)
    void enableParentalControls(boolean enable)
    void displayStatus()
    void generateViewingReport()
    boolean canAccessContent(String content) // Based on parental controls
    void smartAdjustSettings() // Auto-adjust based on time/usage
}
```

### **SmartThermostat Class Specifications**

```java
class SmartThermostat {
    // STATE - Minimum required fields
    double currentTemperature;
    double targetTemperature;
    String mode; // "Heat", "Cool", "Auto", "Off"
    String fanSpeed;
    boolean scheduleActive;
    boolean energySaveMode;
    String filterStatus;
    int humidityLevel;
    String systemStatus;
    
    // BEHAVIOR - Minimum required methods
    void setTargetTemperature(double temp)
    void changeMode(String newMode)
    void setFanSpeed(String speed)
    void enableEnergyMode(boolean enable)
    void checkFilter()
    void setSchedule(int hour, double temp)
    void displayStatus()
    double calculateEfficiency()
    void performMaintenance()
    void smartAdjust() // Auto-adjust based on conditions
}
```

### **SmartLightBulb Class Specifications**

```java
class SmartLightBulb {
    // STATE - Minimum required fields
    boolean isPoweredOn;
    int brightnessLevel;
    String currentColor;
    int colorTemperature;
    boolean scheduleActive;
    double energyUsage;
    int lifetimeHours;
    String roomLocation;
    
    // BEHAVIOR - Minimum required methods
    void turnOn()
    void turnOff()
    void setBrightness(int brightness)
    void setColor(String color)
    void setColorTemperature(int temp)
    void setSchedule(int onHour, int offHour)
    void displayStatus()
    double calculateEnergyCost()
    void setScene(String sceneName)
    void smartAdjust() // Auto-adjust based on time/ambient light
}
```

---

## **🎨 ADVANCED FEATURES (Optional Mastery)**

*If you wish to demonstrate expert-level understanding:*

### **🏠 SmartHome Controller**
```java
class SmartHome {
    SmartTV[] tvs;
    SmartThermostat[] thermostats;
    SmartLightBulb[] lights;
    
    // Master control methods
    void setScene(String sceneName)        // "Movie", "Sleep", "Party", "Away"
    void enableAwayMode()                  // All devices to energy-save mode
    void generateEnergyReport()            // Total usage across all devices
    void performDailyAutomation()          // Time-based device adjustments
    void displayHomeStatus()               // Status of all devices
}
```

### **🎯 Smart Scene Examples**
- **"Movie Night"**: TV on, lights dimmed, thermostat to comfortable temp
- **"Good Morning"**: Lights brighten gradually, thermostat to day temp
- **"Bedtime"**: All devices off/sleep mode, security settings active
- **"Away Mode"**: All devices to energy-save, simulate presence

### **📊 Advanced Analytics**
- Energy usage tracking and cost calculation
- Device usage patterns and recommendations
- Maintenance scheduling based on usage
- Smart optimization suggestions

---

## **🧪 TEST SCENARIOS YOU MUST DEMONSTRATE**

### **Scenario 1: Individual Device Operations**
```java
// Create each device type and demonstrate all core functionality
// Show state changes through behavior calls
// Demonstrate state-dependent behavior (can't change channel when TV is off)
// Show intelligent auto-adjustments
```

### **Scenario 2: State Validation and Error Handling**
```java
// Attempt invalid operations (negative volume, impossible temperature)
// Show proper error messages and state preservation
// Demonstrate graceful handling of edge cases
```

### **Scenario 3: Smart Automation**
```java
// Demonstrate time-based automation
// Show energy-saving mode activation
// Display intelligent behavior based on usage patterns
```

### **Scenario 4: Home Integration (If Attempted)**
```java
// Show multiple devices working together
// Demonstrate scene control affecting multiple devices
// Display comprehensive home status and energy reporting
```

---

## **📊 SUCCESS CRITERIA**

### **🎯 State Design Excellence (30%)**
- Rich, meaningful state that models real device attributes
- Proper data types for all state fields
- State that enables intelligent behavior
- Consistent state management throughout operations

### **🚀 Behavior Implementation (30%)**
- Comprehensive behaviors that read and modify state appropriately
- State-dependent logic that changes behavior based on current state
- Intelligent automation and smart features
- Proper validation and error handling

### **🎭 State-Behavior Integration (25%)**
- Seamless interaction between state and behavior
- Behaviors that maintain state consistency
- Complex state transitions handled correctly
- Natural, intuitive object interactions

### **💎 Code Quality and Design (15%)**
- Clear, descriptive naming for state and behavior
- Proper code organization and commenting
- Realistic modeling of smart home functionality
- Creative and thoughtful implementation choices

---

## **💡 DESIGN GUIDANCE**

### **🧠 State Design Principles**
1. **Comprehensive**: Include all relevant attributes a real device would have
2. **Realistic**: Use appropriate data types and value ranges
3. **Cohesive**: All state should relate to the same device concept
4. **Meaningful**: Each state field should enable specific behaviors

### **🎯 Behavior Design Principles**
1. **State-Aware**: Always check current state before acting
2. **State-Modifying**: Most behaviors should update relevant state
3. **Validated**: Prevent invalid state changes with proper checks
4. **Intelligent**: Include "smart" behaviors that make autonomous decisions

### **🔧 Implementation Strategy**
1. **Start Simple**: Implement basic state and behavior first
2. **Build Incrementally**: Add complexity gradually
3. **Test Frequently**: Verify each behavior works with different states
4. **Think Realistically**: Ask "How would a real smart device behave?"

### **🎨 Creative Enhancements**
- **Voice Control**: Methods that respond to voice commands
- **Learning Behavior**: Devices that adapt to user preferences
- **Security Features**: State and behavior for device security
- **Integration APIs**: Methods for connecting with other smart home systems

---

## **🌟 THE MASTER'S EXPECTATIONS**

*When you complete this trial, you should be able to confidently demonstrate:*

- ✅ **Rich State Modeling**: Objects with comprehensive, meaningful state
- ✅ **Intelligent Behavior**: Methods that make smart decisions based on state
- ✅ **State-Behavior Harmony**: Seamless integration where state enables behavior
- ✅ **Real-World Accuracy**: Code that mirrors actual smart home functionality
- ✅ **System Thinking**: Understanding how state and behavior create complex systems

---

## **🚀 READY TO BEGIN?**

*Remember, wise seeker:*

*"State without behavior is lifeless data. Behavior without state is meaningless action. But state and behavior working together... that creates the magic of objects that think, learn, and adapt."*

*"Think like a smart home user: What would you want your devices to know about themselves? What would you want them to be able to do? Your answers will guide you to excellent state and behavior design."*

*"Start with one device. Give it rich state, then create behaviors that use that state intelligently. Watch how natural interactions emerge when state and behavior are well-designed."*

**Begin with the SmartTV class. Create comprehensive state, implement intelligent behaviors, then watch your object come alive with realistic smart TV functionality!**

*May your State be rich and meaningful, your Behavior be intelligent and responsive, and your Objects be living reflections of the smart devices they represent.*

**Go forth and master the sacred duality of State and Behavior!**

---

*When you have completed your trial, compare your solution with the master's implementation in the `_Solution/` directory. Remember - there are many excellent ways to solve this challenge. Focus on creating objects that truly embody what they represent.*
