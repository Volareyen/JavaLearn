# The Variables and Data Types Challenge
*Your First Trial in Data Mastery*

---

## The Sacred Task

You have been chosen to create a **Personal Profile System** that demonstrates your mastery of variables and data types. This system will store and display comprehensive information about a person, using the most appropriate data type for each piece of information.

---

## Challenge Requirements

Create a Java program called `PersonalProfile.java` that accomplishes the following:

### **Part 1: Personal Information Storage**
Declare and initialize variables to store the following information about yourself (or a fictional character):

**Basic Information:**
- Full name (first, middle, last - separate variables)
- Age in years
- Height in inches (whole number)
- Weight in pounds (with decimal precision)
- Eye color (single character code: B=Blue, G=Green, H=Hazel, R=Brown)
- Is currently a student (true/false)
- Favorite lucky number (can be very large)

**Contact Information:**
- Email address
- Phone number (as text, including formatting)
- Home state/province (2-letter abbreviation)

**Preferences:**
- Favorite programming language
- Years of programming experience (with decimal precision)
- Preferred development environment/IDE
- Is interested in game development (true/false)
- Is interested in web development (true/false)
- Is interested in mobile development (true/false)

### **Part 2: Calculations and Derived Information**
Perform the following calculations using your stored data:

1. **Age Calculations:**
   - Calculate approximate age in months
   - Calculate approximate age in days
   - Calculate how many years until age 65 (retirement age)

2. **Physical Measurements:**
   - Convert height from inches to centimeters (1 inch = 2.54 cm)
   - Convert weight from pounds to kilograms (1 pound = 0.453592 kg)
   - Calculate BMI using the formula: BMI = weight(kg) / (height(m))²

3. **Programming Experience:**
   - Calculate total hours of programming experience (assume 2 hours per day on average)
   - Determine if you're a beginner (< 1 year), intermediate (1-5 years), or advanced (> 5 years)

### **Part 3: Profile Display**
Create a nicely formatted output that displays:
- All personal information in a readable format
- All calculated values with appropriate precision
- A summary section highlighting key facts
- Use appropriate formatting for different data types

### **Part 4: Constants and Configuration**
Define and use at least 5 constants for:
- Conversion factors (inches to cm, pounds to kg, etc.)
- Threshold values (beginner/intermediate programming experience boundaries)
- System configuration (current year, retirement age, etc.)

---

## Technical Requirements

### **Data Type Usage:**
You must demonstrate proper use of:
- `String` for textual data
- `int` for whole number counts and IDs
- `double` for precise decimal calculations
- `boolean` for true/false flags
- `char` for single character codes
- `long` for large numbers (if applicable)
- `final` for constants

### **Variable Naming:**
- Use descriptive, camelCase variable names
- Use UPPER_CASE for constants
- Follow Java naming conventions

### **Code Organization:**
- Include meaningful comments explaining your data type choices
- Group related variables together
- Use proper indentation and spacing

---

## Bonus Challenges (Optional)

### **Bonus 1: Type Conversion Mastery**
Demonstrate both automatic and manual type conversion:
- Show examples of widening conversion (automatic)
- Show examples of narrowing conversion (manual casting)
- Explain potential data loss in your comments

### **Bonus 2: Input Validation**
Add logic to validate that your data makes sense:
- Age should be reasonable (0-120)
- Height and weight should be positive
- Programming experience shouldn't exceed age
- Display warnings for unrealistic values

### **Bonus 3: Multiple Profiles**
Create variables for a second person and compare their information:
- Who is older?
- Who has more programming experience?
- Who has a higher BMI?
- Display comparison results

---

## Example Output Format

Your program should produce output similar to this:

```
==================================================
           PERSONAL PROFILE SYSTEM
==================================================

PERSONAL INFORMATION:
Name: John Michael Doe
Age: 25 years old
Physical: 70 inches tall, 150.5 pounds
Eye Color: Blue (B)
Student Status: Yes
Lucky Number: 777777

CONTACT INFORMATION:
Email: john.doe@email.com
Phone: (555) 123-4567
Location: CA

PROGRAMMING BACKGROUND:
Favorite Language: Java
Experience: 2.5 years
IDE Preference: VSCode
Interests: Game Development, Web Development

CALCULATED INFORMATION:
Age in months: ~300 months
Age in days: ~9,125 days
Years until retirement: 40 years

Height in centimeters: 177.8 cm
Weight in kilograms: 68.3 kg
Body Mass Index: 21.6 (Normal weight)

Programming hours: ~1,825 total hours
Experience level: Intermediate

==================================================
```

---

## Success Criteria

Your solution will be considered successful when:

✅ **All required variables are declared with appropriate data types**  
✅ **All calculations are performed correctly**  
✅ **Output is well-formatted and readable**  
✅ **Constants are used appropriately**  
✅ **Variable names follow Java conventions**  
✅ **Comments explain data type choices**  
✅ **Code compiles and runs without errors**  
✅ **Demonstrates understanding of type conversion**  

---

## Learning Objectives

This challenge will help you master:

1. **Data Type Selection**: Choosing the right type for different kinds of data
2. **Variable Declaration**: Proper syntax for declaring and initializing variables
3. **Type Conversion**: Understanding automatic and manual conversion
4. **Constants**: Using `final` for values that don't change
5. **Calculations**: Performing mathematical operations with different data types
6. **Formatting**: Displaying data in a user-friendly way
7. **Code Organization**: Writing clean, well-commented code

---

## Hints for Success

### **Data Type Selection Guide:**
- **Names, addresses, descriptions** → `String`
- **Ages, counts, IDs, years** → `int`
- **Measurements, money, percentages** → `double`
- **Yes/no questions, flags** → `boolean`
- **Single letters, codes** → `char`
- **Very large numbers** → `long`
- **Values that never change** → `final`

### **Common Pitfalls to Avoid:**
- Don't use `float` unless specifically needed (use `double` for decimals)
- Remember the 'L' suffix for long literals: `123456789L`
- Remember the 'f' suffix for float literals: `3.14f`
- Use single quotes for char: `'A'`, double quotes for String: `"Hello"`
- Initialize variables before using them

### **Calculation Tips:**
- Be careful with integer division (5/2 = 2, not 2.5)
- Use parentheses to ensure correct order of operations
- Consider precision when converting between types
- Use `String.format("%.2f", value)` for decimal formatting

---

*"The master of variables knows not just how to store data, but how to choose the perfect vessel for each piece of information. Show me your wisdom in this choice, and prove your readiness for greater challenges."*
