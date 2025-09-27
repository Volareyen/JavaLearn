# The Control Flow - Decisions Challenge
*Your Trial in the Art of Intelligent Decision-Making*

---

## The Sacred Task

You have been chosen to create an **Intelligent Personal Finance Advisor** that demonstrates your mastery of all conditional logic structures. This system will analyze a person's financial situation, make recommendations, and provide personalized advice using sophisticated decision-making algorithms.

---

## Challenge Requirements

Create a Java program called `PersonalFinanceAdvisor.java` that accomplishes the following:

### **Part 1: Financial Profile Analysis**
Gather and analyze the following financial information:

**Personal Information:**
- Name and age
- Employment status (employed, unemployed, student, retired)
- Annual income (before taxes)
- Current savings balance
- Monthly expenses
- Credit score (300-850 range)
- Number of dependents
- Homeownership status (rent, own, live with family)

**Financial Goals:**
- Primary goal (emergency fund, house down payment, retirement, debt payoff, vacation)
- Target amount for goal
- Desired timeframe (in months)
- Risk tolerance (conservative, moderate, aggressive)

### **Part 2: Basic Financial Health Assessment (Simple IF Statements)**
Evaluate basic financial health indicators:

1. **Emergency Fund Status**:
   - Adequate: 6+ months of expenses saved
   - Partial: 3-6 months of expenses saved  
   - Insufficient: Less than 3 months of expenses saved

2. **Debt-to-Income Ratio**:
   - Excellent: Less than 20%
   - Good: 20-35%
   - Concerning: 36-49%
   - Critical: 50% or higher

3. **Savings Rate**:
   - Excellent: Saving 20%+ of income
   - Good: Saving 10-19% of income
   - Needs Improvement: Saving 5-9% of income
   - Critical: Saving less than 5% of income

### **Part 3: Credit Score Analysis (IF-ELSE IF-ELSE Chain)**
Provide detailed credit score analysis and recommendations:

- **Excellent (750-850)**: Best rates available, consider premium credit cards
- **Very Good (700-749)**: Good rates, qualify for most loans
- **Good (650-699)**: Average rates, some loan restrictions
- **Fair (600-649)**: Higher rates, limited loan options
- **Poor (550-599)**: Very high rates, secured cards recommended
- **Bad (300-549)**: Credit repair needed, very limited options

### **Part 4: Investment Risk Assessment (Nested IF Statements)**
Determine appropriate investment strategy based on:

**Age-Based Risk Tolerance:**
- Young (18-30): Can take higher risks for growth
- Middle-aged (31-50): Balanced approach
- Pre-retirement (51-65): Conservative with some growth
- Retired (65+): Focus on income and capital preservation

**Income-Based Considerations:**
- High income (>$100k): More investment options
- Medium income ($50k-$100k): Standard investment approach  
- Lower income (<$50k): Focus on basics first

**Goal-Based Strategy:**
- Short-term goals (< 2 years): Conservative investments
- Medium-term goals (2-10 years): Moderate risk
- Long-term goals (10+ years): Can take more risk

### **Part 5: Financial Goal Feasibility (Switch Statement)**
Analyze the feasibility of their primary financial goal:

**Switch on goal type:**
- **Emergency Fund**: Calculate months needed to save target amount
- **House Down Payment**: Assess affordability and timeline
- **Retirement**: Determine if on track for retirement age
- **Debt Payoff**: Calculate payoff timeline and interest savings
- **Vacation**: Evaluate impact on other financial priorities
- **Education**: Assess funding options and timeline

### **Part 6: Personalized Recommendations**
Provide specific, actionable recommendations based on the complete analysis.

---

## Technical Requirements

### **Conditional Logic Usage:**
You must demonstrate proper use of:
- **Simple IF**: Basic threshold checking and eligibility
- **IF-ELSE**: Binary decisions and either/or scenarios
- **IF-ELSE IF-ELSE**: Multiple category analysis (credit scores, income levels)
- **Nested IF**: Complex hierarchical decisions (risk assessment)
- **Switch Statement**: Goal-specific analysis and recommendations

### **Complex Decision Logic:**
Create at least 3 complex conditional expressions combining multiple factors:
```java
// Example structure (create your own):
if ((age < 30) && (income > 50000) && (creditScore >= 700) && (savingsRate >= 0.15)) {
    // Aggressive growth strategy recommendation
}
```

### **Data Validation:**
Include validation for:
- Income and savings must be non-negative
- Credit scores must be 300-850
- Age must be reasonable (18-100)
- Percentages must be valid ranges
- Goal amounts must be positive

---

## Expected Output Format

Your program should produce a comprehensive financial analysis:

```
==================================================
          PERSONAL FINANCE ADVISOR
==================================================

CLIENT PROFILE:
Name: Sarah Johnson
Age: 28 years old
Employment: Employed
Annual Income: $75,000
Monthly Expenses: $4,200
Current Savings: $18,000
Credit Score: 720
Dependents: 0
Housing: Renting

FINANCIAL GOALS:
Primary Goal: House Down Payment
Target Amount: $60,000
Timeframe: 36 months
Risk Tolerance: Moderate

==================================================
FINANCIAL HEALTH ANALYSIS:
==================================================

💰 EMERGENCY FUND STATUS:
Current Emergency Fund: $18,000
Monthly Expenses: $4,200
Emergency Fund Coverage: 4.3 months
Status: ✓ PARTIAL - You have adequate short-term coverage
Recommendation: Consider building to 6 months ($25,200)

💳 CREDIT SCORE ANALYSIS:
Score: 720 (Very Good Range: 700-749)
✓ Qualifies for good interest rates
✓ Eligible for most loan products
✓ Consider rewards credit cards
Recommendation: Maintain current habits to reach excellent range

📊 DEBT-TO-INCOME ANALYSIS:
Monthly Income: $6,250
Monthly Expenses: $4,200
Debt-to-Income Ratio: 67.2%
Status: ⚠ CONCERNING - High expense ratio
Recommendation: Review and reduce monthly expenses

💵 SAVINGS RATE ANALYSIS:
Monthly Savings: $2,050
Savings Rate: 32.8% of income
Status: ✓ EXCELLENT - Well above recommended 20%
Recommendation: Continue excellent savings habits

==================================================
INVESTMENT RISK ASSESSMENT:
==================================================

👤 AGE-BASED PROFILE:
Age Group: Young Professional (28 years)
Risk Capacity: HIGH - Long investment timeline
Recommended Asset Allocation: 80% Stocks, 20% Bonds

💰 INCOME-BASED CONSIDERATIONS:
Income Level: Medium ($75,000)
Investment Options: Standard investment accounts
Recommendation: Max out employer 401(k) match first

🎯 GOAL-BASED STRATEGY:
Goal Timeline: Medium-term (36 months)
Recommended Strategy: MODERATE RISK
Suitable Investments: Balanced funds, CDs, high-yield savings

==================================================
GOAL FEASIBILITY ANALYSIS:
==================================================

🏠 HOUSE DOWN PAYMENT ANALYSIS:
Target Amount: $60,000
Current Savings: $18,000
Amount Needed: $42,000
Timeline: 36 months
Required Monthly Savings: $1,167

Current Monthly Savings: $2,050
Surplus Available: $883

FEASIBILITY: ✅ HIGHLY ACHIEVABLE
• You can reach your goal 18 months early!
• Consider increasing down payment target
• Explore house hunting in 24 months

==================================================
PERSONALIZED RECOMMENDATIONS:
==================================================

🎯 IMMEDIATE ACTIONS (Next 30 days):
1. Open high-yield savings account for house fund
2. Research first-time homebuyer programs
3. Get pre-qualified for mortgage to understand budget
4. Review and optimize monthly expenses

📈 SHORT-TERM GOALS (3-12 months):
1. Build emergency fund to $25,200 (6 months)
2. Save $14,000 toward house down payment
3. Improve credit score to 750+ range
4. Research target neighborhoods and prices

🚀 LONG-TERM STRATEGY (1-3 years):
1. Achieve house down payment goal by month 24
2. Maintain 15%+ savings rate after home purchase
3. Start retirement savings with employer match
4. Consider investment property after home purchase

⚠️  CAUTION AREAS:
• High monthly expenses relative to income
• No mention of retirement savings
• Consider disability and life insurance

==================================================
```

---

## Bonus Challenges (Optional)

### **Bonus 1: Advanced Decision Trees**
Create a sophisticated decision tree for investment recommendations:
```java
// Consider multiple factors simultaneously
if (age < 35) {
    if (riskTolerance.equals("aggressive")) {
        if (income > 75000 && emergencyFund >= 6) {
            // Aggressive growth portfolio
        } else if (income > 50000) {
            // Moderate growth with safety net building
        } else {
            // Focus on emergency fund first
        }
    }
    // ... more branches
}
```

### **Bonus 2: Monte Carlo Simulation**
Add probability-based projections:
- Calculate success probability for reaching goals
- Show best-case, worst-case, and expected scenarios
- Account for market volatility and inflation

### **Bonus 3: Multiple Goal Analysis**
Handle multiple competing financial goals:
- Prioritize goals by importance and timeline
- Show trade-offs between different goals
- Recommend optimal allocation of available savings

### **Bonus 4: Dynamic Recommendation Engine**
Create recommendations that adapt based on profile:
- Different advice for different life stages
- Personalized action timelines
- Risk-adjusted projections

---

## Success Criteria

Your solution will be considered successful when:

✅ **All conditional logic types are used appropriately**  
✅ **Complex financial decisions are handled correctly**  
✅ **Input validation prevents invalid data**  
✅ **Output is professional and comprehensive**  
✅ **Recommendations are specific and actionable**  
✅ **Edge cases are handled gracefully**  
✅ **Code is well-organized and readable**  
✅ **Comments explain decision logic clearly**  

---

## Learning Objectives

This challenge will help you master:

1. **Decision Architecture**: Designing complex decision-making systems
2. **Conditional Logic**: Using the right structure for each decision type  
3. **Data Validation**: Ensuring input integrity with conditionals
4. **Business Logic**: Implementing real-world rules and policies
5. **User Experience**: Providing clear, helpful feedback
6. **Code Organization**: Structuring complex conditional logic
7. **Problem Decomposition**: Breaking complex decisions into manageable parts

---

## Hints for Success

### **Financial Calculation Formulas:**
```java
// Emergency fund months
double emergencyMonths = currentSavings / monthlyExpenses;

// Debt-to-income ratio
double debtToIncomeRatio = (monthlyExpenses / monthlyIncome) * 100;

// Savings rate
double savingsRate = ((monthlyIncome - monthlyExpenses) / monthlyIncome) * 100;

// Months to reach goal
int monthsToGoal = (int) Math.ceil((goalAmount - currentSavings) / monthlySavings);
```

### **Decision Logic Patterns:**
```java
// Range checking with meaningful categories
if (creditScore >= 750) {
    category = "Excellent";
    benefits = "Best rates, premium cards";
} else if (creditScore >= 700) {
    category = "Very Good";  
    benefits = "Good rates, most loans";
}

// Multi-factor decision making
boolean qualifiesForAdvancedStrategy = (age < 40) && (income > 75000) && 
                                     (emergencyFundMonths >= 6) && 
                                     (riskTolerance.equals("moderate") || 
                                      riskTolerance.equals("aggressive"));
```

### **Common Pitfalls to Avoid:**
- **Division by zero**: Check for zero monthly expenses before calculating ratios
- **Invalid ranges**: Validate credit scores, ages, and percentages
- **Unrealistic goals**: Flag impossible timelines or amounts
- **Missing edge cases**: Handle unemployed, retired, or student scenarios
- **Inconsistent data**: Ensure savings don't exceed income capacity

### **Testing Scenarios:**
- High earner with poor savings habits
- Young person with aggressive goals
- Retiree with conservative needs
- Student with limited income
- Person with poor credit but improving finances

---

*"The master of conditional logic does not merely write if-statements - they architect intelligence. They create systems that can examine the complexity of human circumstances and respond with wisdom, guidance, and hope. Show me that you can build such a system."*
