# The Pupil's Trial: Mastering Abstraction

*"Now, young architect of code, you have witnessed the power of Abstraction through the sacred scrolls. The time has come to forge your own abstractions - to create templates of truth that reveal what must be done while concealing how it is accomplished."*

---

## **Your Sacred Quest**

You are tasked with designing and implementing a **Digital Art Gallery Management System** that showcases the profound power of abstraction. This system must handle different types of artwork, each with unique properties and behaviors, while providing common management capabilities.

Your mission is to demonstrate mastery of both **Abstract Classes** and **Interfaces**, showing when to use each form of abstraction and how they work together to create flexible, extensible code.

---

## **The Challenge Specifications**

### **Phase I: The Foundation - Abstract Art Piece**

Create an **abstract class** called `ArtPiece` that represents the common essence of all artwork in your gallery:

#### **Required Abstract Class Features:**

**Common Properties (Protected Fields):**
- `title` (String) - The artwork's name
- `artist` (String) - Creator of the piece
- `yearCreated` (int) - Year the artwork was made
- `dimensions` (String) - Size/dimensions of the piece
- `estimatedValue` (double) - Monetary value in dollars
- `isOnDisplay` (boolean) - Whether currently exhibited

**Concrete Methods (Implemented for all artwork):**
- `displayInfo()` - Shows basic artwork information
- `setDisplayStatus(boolean status)` - Changes exhibition status
- `calculateInsuranceValue()` - Returns 120% of estimated value
- `getAgeInYears()` - Calculates how old the artwork is
- **Constructor** - Initializes all common properties

**Abstract Methods (Must be implemented by subclasses):**
- `String getArtMedium()` - Returns the artistic medium (oil, bronze, digital, etc.)
- `void performMaintenance()` - Type-specific care and preservation
- `double calculateSpaceRequired()` - Space needed for display (square feet)
- `String generateCatalogEntry()` - Detailed catalog description
- `boolean requiresSpecialHandling()` - Whether needs special care during moves

### **Phase II: The Contracts - Capability Interfaces**

Create **interfaces** that define capabilities some artworks possess:

#### **1. `Displayable` Interface:**
**Purpose:** For artworks that can be actively displayed/projected

**Constants:**
- `int STANDARD_BRIGHTNESS = 75` - Default display brightness
- `int MAX_DISPLAY_HOURS = 12` - Maximum daily display time

**Methods:**
- `void turnOn()` - Activate the display
- `void turnOff()` - Deactivate the display
- `void setBrightness(int level)` - Adjust display brightness
- `int getBatteryLevel()` - Current power level (0-100)

**Default Method:**
- `void performDisplayCalibration()` - Standard calibration routine

**Static Method:**
- `boolean isValidBrightness(int level)` - Validates brightness level (0-100)

#### **2. `Sellable` Interface:**
**Purpose:** For artworks available for purchase

**Constants:**
- `double COMMISSION_RATE = 0.15` - 15% gallery commission
- `double TAX_RATE = 0.08` - 8% sales tax

**Methods:**
- `void putOnSale(double askingPrice)` - List artwork for sale
- `void removeFromSale()` - Take off the market
- `boolean isForSale()` - Check availability status
- `double getFinalPrice()` - Price including commission and tax
- `void processTransaction(String buyerName)` - Complete the sale

**Default Method:**
- `void applyStandardPricing()` - Set asking price to 110% of estimated value

#### **3. `Interactive` Interface:**
**Purpose:** For artworks that respond to user interaction

**Methods:**
- `void respondToTouch()` - Handle touch interaction
- `void respondToMotion()` - Handle movement detection  
- `void respondToVoice(String command)` - Process voice commands
- `void resetInteractionState()` - Return to default state

**Default Method:**
- `void enableInteractionMode()` - Prepare for user interaction

### **Phase III: The Implementations - Concrete Art Types**

Create **at least three concrete classes** that extend `ArtPiece` and implement appropriate interfaces:

#### **1. `Painting` Class:**
- **Extends:** `ArtPiece`
- **Implements:** `Sellable`
- **Additional Properties:** `paintType` (String), `canvasSize` (String), `isFramed` (boolean)
- **Special Behavior:** Requires climate control, vulnerable to humidity

#### **2. `DigitalArt` Class:**
- **Extends:** `ArtPiece`  
- **Implements:** `Displayable`, `Interactive`, `Sellable`
- **Additional Properties:** `resolution` (String), `fileFormat` (String), `requiresProjector` (boolean)
- **Special Behavior:** Needs power source, can have interactive elements

#### **3. `Sculpture` Class:**
- **Extends:** `ArtPiece`
- **Implements:** `Sellable`
- **Additional Properties:** `material` (String), `weight` (double), `isOutdoorSafe` (boolean)
- **Special Behavior:** Requires significant floor space, may need special handling

#### **4. `InteractiveInstallation` Class (Bonus):**
- **Extends:** `ArtPiece`
- **Implements:** `Displayable`, `Interactive`
- **Additional Properties:** `sensorCount` (int), `soundSystemIncluded` (boolean)
- **Special Behavior:** Complex setup, multiple interaction modes

### **Phase IV: The Gallery System - Bringing It All Together**

Create a **`GalleryManager`** class that demonstrates polymorphism and interface-based programming:

#### **Required Methods:**

**Collection Management:**
- `addArtPiece(ArtPiece art)` - Add artwork to collection
- `removeArtPiece(String title)` - Remove by title
- `listAllArtwork()` - Display entire collection
- `findArtworkByArtist(String artist)` - Search by artist

**Polymorphic Operations:**
- `performMaintenanceOnAll()` - Call maintenance on all pieces
- `calculateTotalInsuranceValue()` - Sum insurance values
- `generateFullCatalog()` - Create complete catalog

**Interface-Based Operations:**
- `setupAllDisplays()` - Turn on all Displayable artwork
- `shutdownAllDisplays()` - Turn off all displays
- `listForSaleItems()` - Show all Sellable pieces
- `processAllSales()` - Handle sales for all available pieces
- `activateInteractiveMode()` - Enable all Interactive pieces

---

## **Success Criteria**

Your implementation will be judged on these aspects:

### **🎯 Abstraction Mastery (40%)**
- ✅ Proper use of abstract class with both concrete and abstract methods
- ✅ Meaningful interface contracts that represent true capabilities
- ✅ Appropriate choice between abstract classes vs interfaces
- ✅ Effective use of default and static methods in interfaces

### **🏗️ Implementation Quality (30%)**
- ✅ All concrete classes properly implement required abstract methods
- ✅ Interface implementations are meaningful and complete
- ✅ Multiple interface implementation handled correctly
- ✅ Proper inheritance hierarchy and method overriding

### **🔄 Polymorphism Demonstration (20%)**
- ✅ GalleryManager works with ArtPiece references polymorphically
- ✅ Interface-based programming with instanceof checks
- ✅ Consistent behavior across different implementations
- ✅ Clean separation between abstract and concrete functionality

### **💎 Code Quality (10%)**
- ✅ Clear, descriptive naming conventions
- ✅ Proper access modifiers and encapsulation
- ✅ Comprehensive comments explaining design decisions
- ✅ Professional code structure and organization

---

## **Bonus Challenges** 

*For those who seek to transcend mere competency:*

### **🌟 Advanced Abstraction (Extra Credit):**
1. **Interface Inheritance:** Create a `SmartDisplayable` interface that extends `Displayable` with additional smart features
2. **Abstract Method Chaining:** Implement fluent interface pattern in your artwork classes
3. **Template Method Pattern:** Add a `performCompleteSetup()` method in `ArtPiece` that follows a specific sequence
4. **Multiple Interface Hierarchies:** Create additional interface families (e.g., `Transportable`, `Fragile`, `Valuable`)

### **🎨 Real-World Extensions:**
1. **Event System:** Add visitor interaction logging
2. **Collection Analytics:** Statistical analysis of the collection
3. **Dynamic Pricing:** Implement price changes based on demand
4. **Maintenance Scheduling:** Track and schedule maintenance activities

---

## **Forbidden Techniques**

*These approaches will result in failure:*

❌ **Concrete classes without proper abstraction implementation**
❌ **Empty or trivial implementations of abstract methods**
❌ **Interfaces used as mere marker interfaces without real contracts**
❌ **Violation of interface contracts or abstract class requirements**
❌ **Hardcoded values instead of using interface constants**
❌ **Missing polymorphic operations in GalleryManager**

---

## **The Wise Teacher's Guidance**

*"Remember, young seeker, abstraction is not about hiding complexity for its own sake, but about revealing the essential nature of things while allowing for infinite variation in implementation. Your abstract class should capture what ALL artworks ARE, while your interfaces should define what some artworks CAN DO.*

*When you find yourself repeating code, consider a concrete method in your abstract class. When you find unrelated classes need similar capabilities, consider an interface. When you need to work with objects but don't care about their specific type, you have achieved the wisdom of polymorphism."*

**Ask yourself these profound questions:**
- Does my abstract class represent a true "is-a" relationship?
- Do my interfaces represent genuine capabilities?
- Can I add new artwork types without modifying existing code?
- Does my GalleryManager work with concepts rather than concrete classes?

---

## **Submission Requirements**

Create your solution in the following files:
- `ArtPiece.java` - The abstract base class
- `Displayable.java`, `Sellable.java`, `Interactive.java` - The interface contracts
- `Painting.java`, `DigitalArt.java`, `Sculpture.java` - Concrete implementations
- `GalleryManager.java` - The polymorphic management system
- `GalleryDemo.java` - Demonstration of your complete system

*"Go forth and abstract! Show me that you understand not just the syntax of these forms, but their profound purpose in creating code that transcends the specific to embrace the universal. Create abstractions worthy of the title 'architect of code.'"*

---

**The Fourth Pillar awaits your mastery. Complete this trial, and you shall have conquered the core wisdom of Object-Oriented Programming!**
