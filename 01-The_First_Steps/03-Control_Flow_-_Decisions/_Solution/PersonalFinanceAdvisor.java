/**
 * Personal Finance Advisor - Complete Solution
 * 
 * This program demonstrates mastery of all conditional logic structures
 * through a comprehensive personal finance analysis system that evaluates
 * financial health, assesses goals, and provides personalized recommendations.
 * 
 * Author: The Wise Teacher's Example Solution
 */
public class PersonalFinanceAdvisor {
    
    public static void main(String[] args) {
        
        System.out.println("=".repeat(60));
        System.out.println("              PERSONAL FINANCE ADVISOR");
        System.out.println("=".repeat(60));
        
        // ============================================================================
        // CLIENT FINANCIAL PROFILES - Multiple Test Cases
        // ============================================================================
        
        // Client 1: Young Professional with Aggressive Goals
        analyzeFinancialProfile(
            "Sarah Johnson", 28, "Employed", 75000.0, 18000.0, 4200.0, 720, 0, "Renting",
            "House Down Payment", 60000.0, 36, "Moderate"
        );
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Client 2: Mid-Career with Family Responsibilities  
        analyzeFinancialProfile(
            "Michael Chen", 42, "Employed", 95000.0, 45000.0, 6800.0, 680, 2, "Own",
            "Retirement", 500000.0, 276, "Conservative"
        );
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // Client 3: Recent Graduate with Student Debt
        analyzeFinancialProfile(
            "Emma Rodriguez", 24, "Employed", 48000.0, 3500.0, 3200.0, 650, 0, "Live with Family",
            "Emergency Fund", 15000.0, 24, "Aggressive"
        );
    }
    
    /**
     * Comprehensive financial profile analysis using all conditional logic structures
     */
    private static void analyzeFinancialProfile(String name, int age, String employmentStatus,
                                              double annualIncome, double currentSavings, double monthlyExpenses,
                                              int creditScore, int dependents, String housingStatus,
                                              String primaryGoal, double goalAmount, int goalTimeframe,
                                              String riskTolerance) {
        
        // Calculate derived financial metrics
        double monthlyIncome = annualIncome / 12.0;
        double monthlySavings = monthlyIncome - monthlyExpenses;
        double savingsRate = (monthlySavings / monthlyIncome) * 100.0;
        double expenseRatio = (monthlyExpenses / monthlyIncome) * 100.0;
        double emergencyFundMonths = currentSavings / monthlyExpenses;
        
        // Display client profile
        System.out.println("👤 CLIENT PROFILE:");
        System.out.println("   Name: " + name);
        System.out.println("   Age: " + age + " years old");
        System.out.println("   Employment: " + employmentStatus);
        System.out.printf("   Annual Income: $%,.0f%n", annualIncome);
        System.out.printf("   Monthly Income: $%,.0f%n", monthlyIncome);
        System.out.printf("   Monthly Expenses: $%,.0f%n", monthlyExpenses);
        System.out.printf("   Current Savings: $%,.0f%n", currentSavings);
        System.out.println("   Credit Score: " + creditScore);
        System.out.println("   Dependents: " + dependents);
        System.out.println("   Housing: " + housingStatus);
        
        System.out.println("\n🎯 FINANCIAL GOALS:");
        System.out.println("   Primary Goal: " + primaryGoal);
        System.out.printf("   Target Amount: $%,.0f%n", goalAmount);
        System.out.println("   Timeframe: " + goalTimeframe + " months");
        System.out.println("   Risk Tolerance: " + riskTolerance);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("FINANCIAL HEALTH ANALYSIS:");
        System.out.println("=".repeat(60));
        
        // ============================================================================
        // BASIC FINANCIAL HEALTH ASSESSMENT - Simple IF Statements
        // ============================================================================
        
        System.out.println("\n💰 EMERGENCY FUND STATUS:");
        System.out.printf("   Current Emergency Fund: $%,.0f%n", currentSavings);
        System.out.printf("   Monthly Expenses: $%,.0f%n", monthlyExpenses);
        System.out.printf("   Emergency Fund Coverage: %.1f months%n", emergencyFundMonths);
        
        // Simple if statements for emergency fund assessment
        if (emergencyFundMonths >= 6.0) {
            System.out.println("   Status: ✅ ADEQUATE - Excellent emergency coverage");
            System.out.println("   Recommendation: Maintain current level, focus on other goals");
        } else if (emergencyFundMonths >= 3.0) {
            System.out.println("   Status: ⚠️ PARTIAL - Good short-term coverage");
            System.out.printf("   Recommendation: Build to 6 months ($%,.0f total)%n", monthlyExpenses * 6);
        } else {
            System.out.println("   Status: ❌ INSUFFICIENT - Critical priority");
            System.out.println("   Recommendation: Focus on emergency fund before other goals");
        }
        
        System.out.println("\n💵 SAVINGS RATE ANALYSIS:");
        System.out.printf("   Monthly Savings: $%,.0f%n", monthlySavings);
        System.out.printf("   Savings Rate: %.1f%% of income%n", savingsRate);
        
        // Simple if statements for savings rate assessment
        if (savingsRate >= 20.0) {
            System.out.println("   Status: ✅ EXCELLENT - Well above recommended 20%");
            System.out.println("   Recommendation: Continue excellent habits, optimize investments");
        } else if (savingsRate >= 10.0) {
            System.out.println("   Status: ✅ GOOD - Meeting basic recommendations");
            System.out.println("   Recommendation: Try to increase to 20% if possible");
        } else if (savingsRate >= 5.0) {
            System.out.println("   Status: ⚠️ NEEDS IMPROVEMENT - Below recommended levels");
            System.out.println("   Recommendation: Review expenses, increase savings gradually");
        } else if (savingsRate >= 0.0) {
            System.out.println("   Status: ❌ CRITICAL - Minimal or no savings");
            System.out.println("   Recommendation: Immediate budget review and expense reduction");
        } else {
            System.out.println("   Status: ❌ DEFICIT - Spending exceeds income");
            System.out.println("   Recommendation: Urgent financial restructuring needed");
        }
        
        System.out.println("\n📊 EXPENSE RATIO ANALYSIS:");
        System.out.printf("   Expense Ratio: %.1f%% of income%n", expenseRatio);
        
        // Simple if statements for expense ratio assessment
        if (expenseRatio <= 50.0) {
            System.out.println("   Status: ✅ EXCELLENT - Living well below means");
        } else if (expenseRatio <= 70.0) {
            System.out.println("   Status: ✅ GOOD - Reasonable expense level");
        } else if (expenseRatio <= 85.0) {
            System.out.println("   Status: ⚠️ CONCERNING - High expense ratio");
        } else {
            System.out.println("   Status: ❌ CRITICAL - Expenses too high relative to income");
        }
        
        // ============================================================================
        // CREDIT SCORE ANALYSIS - IF-ELSE IF-ELSE Chain
        // ============================================================================
        
        System.out.println("\n💳 CREDIT SCORE ANALYSIS:");
        System.out.println("   Score: " + creditScore);
        
        String creditCategory;
        String creditBenefits;
        String creditRecommendations;
        
        // IF-ELSE IF-ELSE chain for credit score categorization
        if (creditScore >= 750) {
            creditCategory = "Excellent (750-850)";
            creditBenefits = "✅ Best interest rates available\n✅ Qualify for premium credit cards\n✅ Excellent loan terms";
            creditRecommendations = "Maintain current habits, consider rewards optimization";
        } else if (creditScore >= 700) {
            creditCategory = "Very Good (700-749)";
            creditBenefits = "✅ Good interest rates\n✅ Qualify for most loan products\n✅ Access to rewards credit cards";
            creditRecommendations = "Continue good habits to reach excellent range (750+)";
        } else if (creditScore >= 650) {
            creditCategory = "Good (650-699)";
            creditBenefits = "✅ Average interest rates\n⚠️ Some loan restrictions may apply\n✅ Standard credit card access";
            creditRecommendations = "Focus on payment history and credit utilization improvement";
        } else if (creditScore >= 600) {
            creditCategory = "Fair (600-649)";
            creditBenefits = "⚠️ Higher interest rates\n⚠️ Limited loan options\n⚠️ Secured cards may be recommended";
            creditRecommendations = "Credit building priority - pay down balances, avoid new debt";
        } else if (creditScore >= 550) {
            creditCategory = "Poor (550-599)";
            creditBenefits = "❌ Very high interest rates\n❌ Secured cards recommended\n❌ Limited financial product access";
            creditRecommendations = "Immediate credit repair needed - consider credit counseling";
        } else {
            creditCategory = "Bad (300-549)";
            creditBenefits = "❌ Extremely limited options\n❌ Secured cards only\n❌ Very high fees and rates";
            creditRecommendations = "Professional credit repair assistance strongly recommended";
        }
        
        System.out.println("   Category: " + creditCategory);
        System.out.println("   Benefits/Limitations:");
        System.out.println("   " + creditBenefits.replace("\n", "\n   "));
        System.out.println("   Recommendation: " + creditRecommendations);
        
        // ============================================================================
        // INVESTMENT RISK ASSESSMENT - Nested IF Statements
        // ============================================================================
        
        System.out.println("\n📈 INVESTMENT RISK ASSESSMENT:");
        
        String ageGroup;
        String riskCapacity;
        String recommendedAllocation = "";
        
        // Nested if statements for age-based risk assessment
        if (age < 30) {
            ageGroup = "Young Professional (" + age + " years)";
            riskCapacity = "HIGH - Long investment timeline";
            
            if (riskTolerance.equalsIgnoreCase("aggressive")) {
                recommendedAllocation = "90% Stocks, 10% Bonds";
            } else if (riskTolerance.equalsIgnoreCase("moderate")) {
                recommendedAllocation = "80% Stocks, 20% Bonds";
            } else {
                recommendedAllocation = "70% Stocks, 30% Bonds";
            }
            
        } else if (age < 50) {
            ageGroup = "Mid-Career (" + age + " years)";
            riskCapacity = "MODERATE - Balanced approach needed";
            
            if (riskTolerance.equalsIgnoreCase("aggressive")) {
                recommendedAllocation = "75% Stocks, 25% Bonds";
            } else if (riskTolerance.equalsIgnoreCase("moderate")) {
                recommendedAllocation = "65% Stocks, 35% Bonds";
            } else {
                recommendedAllocation = "55% Stocks, 45% Bonds";
            }
            
        } else if (age < 65) {
            ageGroup = "Pre-Retirement (" + age + " years)";
            riskCapacity = "MODERATE-LOW - Capital preservation important";
            
            if (riskTolerance.equalsIgnoreCase("aggressive")) {
                recommendedAllocation = "60% Stocks, 40% Bonds";
            } else if (riskTolerance.equalsIgnoreCase("moderate")) {
                recommendedAllocation = "50% Stocks, 50% Bonds";
            } else {
                recommendedAllocation = "40% Stocks, 60% Bonds";
            }
            
        } else {
            ageGroup = "Retirement Age (" + age + " years)";
            riskCapacity = "LOW - Income and capital preservation focus";
            
            if (riskTolerance.equalsIgnoreCase("aggressive")) {
                recommendedAllocation = "40% Stocks, 60% Bonds";
            } else if (riskTolerance.equalsIgnoreCase("moderate")) {
                recommendedAllocation = "30% Stocks, 70% Bonds";
            } else {
                recommendedAllocation = "20% Stocks, 80% Bonds";
            }
        }
        
        System.out.println("   Age Group: " + ageGroup);
        System.out.println("   Risk Capacity: " + riskCapacity);
        System.out.println("   Risk Tolerance: " + riskTolerance.toUpperCase());
        System.out.println("   Recommended Allocation: " + recommendedAllocation);
        
        // Income-based investment considerations (nested within age assessment)
        System.out.println("\n   💰 Income-Based Considerations:");
        if (annualIncome >= 100000) {
            System.out.println("   Income Level: High ($100k+)");
            
            if (age < 50) {
                System.out.println("   ✅ Consider maxing out 401(k) and IRA contributions");
                System.out.println("   ✅ Explore taxable investment accounts");
                System.out.println("   ✅ Consider tax-loss harvesting strategies");
            } else {
                System.out.println("   ✅ Focus on catch-up contributions");
                System.out.println("   ✅ Consider Roth conversion strategies");
            }
            
        } else if (annualIncome >= 50000) {
            System.out.println("   Income Level: Medium ($50k-$100k)");
            System.out.println("   ✅ Prioritize employer 401(k) match");
            System.out.println("   ✅ Consider Roth IRA contributions");
            System.out.println("   ✅ Build emergency fund alongside investing");
            
        } else {
            System.out.println("   Income Level: Lower (<$50k)");
            System.out.println("   ⚠️ Focus on emergency fund and debt reduction first");
            System.out.println("   ✅ Start with employer match if available");
            System.out.println("   ✅ Consider low-cost index funds");
        }
        
        // Goal-based investment timeline assessment (additional nesting)
        System.out.println("\n   🎯 Goal-Based Investment Strategy:");
        if (goalTimeframe <= 24) {
            System.out.println("   Timeline: Short-term (≤2 years)");
            System.out.println("   Strategy: CONSERVATIVE - Capital preservation priority");
            System.out.println("   Suitable: High-yield savings, CDs, money market");
        } else if (goalTimeframe <= 120) {
            System.out.println("   Timeline: Medium-term (2-10 years)");
            System.out.println("   Strategy: MODERATE RISK - Balanced growth and stability");
            System.out.println("   Suitable: Balanced funds, target-date funds, bond/stock mix");
        } else {
            System.out.println("   Timeline: Long-term (10+ years)");
            System.out.println("   Strategy: GROWTH FOCUSED - Can weather volatility");
            System.out.println("   Suitable: Stock index funds, growth funds, individual stocks");
        }
        
        // ============================================================================
        // GOAL FEASIBILITY ANALYSIS - Switch Statement
        // ============================================================================
        
        System.out.println("\n🎯 GOAL FEASIBILITY ANALYSIS:");
        
        double amountNeeded = goalAmount - currentSavings;
        double requiredMonthlySavings = amountNeeded / goalTimeframe;
        boolean isGoalFeasible = (requiredMonthlySavings <= monthlySavings);
        
        // Switch statement for goal-specific analysis
        switch (primaryGoal.toLowerCase().replace(" ", "")) {
            case "emergencyfund":
                System.out.println("💰 EMERGENCY FUND ANALYSIS:");
                System.out.printf("   Target Amount: $%,.0f%n", goalAmount);
                System.out.printf("   Current Savings: $%,.0f%n", currentSavings);
                System.out.printf("   Amount Needed: $%,.0f%n", amountNeeded);
                System.out.printf("   Timeline: %d months%n", goalTimeframe);
                System.out.printf("   Required Monthly Savings: $%,.0f%n", requiredMonthlySavings);
                System.out.printf("   Current Monthly Savings: $%,.0f%n", monthlySavings);
                
                if (isGoalFeasible) {
                    int monthsEarly = (int) ((monthlySavings - requiredMonthlySavings) / requiredMonthlySavings * goalTimeframe);
                    System.out.println("   FEASIBILITY: ✅ ACHIEVABLE");
                    if (monthsEarly > 0) {
                        System.out.println("   • You can reach your goal " + monthsEarly + " months early!");
                    }
                    System.out.println("   • Use high-yield savings account");
                    System.out.println("   • Keep funds easily accessible");
                } else {
                    double shortfall = requiredMonthlySavings - monthlySavings;
                    System.out.println("   FEASIBILITY: ⚠️ CHALLENGING");
                    System.out.printf("   • Need to save additional $%,.0f per month%n", shortfall);
                    System.out.println("   • Consider extending timeline or reducing expenses");
                }
                break;
                
            case "housedownpayment":
                System.out.println("🏠 HOUSE DOWN PAYMENT ANALYSIS:");
                System.out.printf("   Target Amount: $%,.0f%n", goalAmount);
                System.out.printf("   Current Savings: $%,.0f%n", currentSavings);
                System.out.printf("   Amount Needed: $%,.0f%n", amountNeeded);
                System.out.printf("   Timeline: %d months%n", goalTimeframe);
                System.out.printf("   Required Monthly Savings: $%,.0f%n", requiredMonthlySavings);
                
                // Additional home-buying considerations
                double estimatedHomePrice = goalAmount / 0.2; // Assuming 20% down payment
                double maxAffordablePayment = monthlyIncome * 0.28; // 28% rule
                
                System.out.printf("   Estimated Home Price: $%,.0f (assuming 20%% down)%n", estimatedHomePrice);
                System.out.printf("   Max Affordable Payment: $%,.0f (28%% rule)%n", maxAffordablePayment);
                
                if (isGoalFeasible) {
                    System.out.println("   FEASIBILITY: ✅ ACHIEVABLE");
                    System.out.println("   • Consider first-time homebuyer programs");
                    System.out.println("   • Get pre-qualified for mortgage");
                    System.out.println("   • Research target neighborhoods");
                    
                    if (creditScore >= 700) {
                        System.out.println("   • Excellent credit will help with rates");
                    } else {
                        System.out.println("   • Work on credit improvement for better rates");
                    }
                } else {
                    System.out.println("   FEASIBILITY: ⚠️ NEEDS ADJUSTMENT");
                    System.out.println("   • Consider smaller down payment (10-15%)");
                    System.out.println("   • Extend timeline for savings");
                    System.out.println("   • Look at lower-priced areas");
                }
                break;
                
            case "retirement":
                System.out.println("🏖️ RETIREMENT ANALYSIS:");
                System.out.printf("   Target Amount: $%,.0f%n", goalAmount);
                System.out.printf("   Current Savings: $%,.0f%n", currentSavings);
                System.out.printf("   Timeline: %d months (%.1f years)%n", goalTimeframe, goalTimeframe / 12.0);
                
                // Retirement-specific calculations
                int retirementAge = age + (goalTimeframe / 12);
                double requiredAnnualSavings = requiredMonthlySavings * 12;
                double currentAnnualSavings = monthlySavings * 12;
                
                System.out.println("   Projected Retirement Age: " + retirementAge);
                System.out.printf("   Required Annual Savings: $%,.0f%n", requiredAnnualSavings);
                System.out.printf("   Current Annual Savings: $%,.0f%n", currentAnnualSavings);
                
                // Rule of thumb: 10-15% of income for retirement
                double retirementSavingsRate = (requiredAnnualSavings / annualIncome) * 100;
                System.out.printf("   Required Retirement Savings Rate: %.1f%% of income%n", retirementSavingsRate);
                
                if (retirementSavingsRate <= 15.0) {
                    System.out.println("   FEASIBILITY: ✅ ON TRACK");
                    System.out.println("   • Consider maximizing employer match");
                    System.out.println("   • Explore tax-advantaged accounts");
                } else if (retirementSavingsRate <= 25.0) {
                    System.out.println("   FEASIBILITY: ⚠️ AGGRESSIVE BUT POSSIBLE");
                    System.out.println("   • May need to work longer or save more");
                    System.out.println("   • Consider increasing income");
                } else {
                    System.out.println("   FEASIBILITY: ❌ UNREALISTIC");
                    System.out.println("   • Extend working years");
                    System.out.println("   • Reduce retirement income expectations");
                    System.out.println("   • Focus on increasing income");
                }
                break;
                
            case "debtpayoff":
                System.out.println("💳 DEBT PAYOFF ANALYSIS:");
                System.out.printf("   Target Payoff Amount: $%,.0f%n", goalAmount);
                System.out.printf("   Timeline: %d months%n", goalTimeframe);
                System.out.printf("   Required Monthly Payment: $%,.0f%n", requiredMonthlySavings);
                
                if (isGoalFeasible) {
                    // Estimate interest savings (assuming 18% APR average)
                    double estimatedInterestSaved = goalAmount * 0.18 * (goalTimeframe / 12.0);
                    System.out.println("   FEASIBILITY: ✅ ACHIEVABLE");
                    System.out.printf("   • Estimated interest savings: $%,.0f%n", estimatedInterestSaved);
                    System.out.println("   • Consider debt avalanche method (highest interest first)");
                    System.out.println("   • Avoid taking on new debt during payoff");
                } else {
                    System.out.println("   FEASIBILITY: ⚠️ CHALLENGING");
                    System.out.println("   • Consider debt consolidation");
                    System.out.println("   • Negotiate with creditors for lower rates");
                    System.out.println("   • Explore debt management programs");
                }
                break;
                
            case "vacation":
                System.out.println("✈️ VACATION SAVINGS ANALYSIS:");
                System.out.printf("   Target Amount: $%,.0f%n", goalAmount);
                System.out.printf("   Timeline: %d months%n", goalTimeframe);
                System.out.printf("   Required Monthly Savings: $%,.0f%n", requiredMonthlySavings);
                
                // Check if vacation conflicts with other priorities
                if (emergencyFundMonths < 3.0) {
                    System.out.println("   ⚠️ PRIORITY CONFLICT: Emergency fund should come first");
                    System.out.println("   • Consider a smaller vacation budget");
                    System.out.println("   • Build emergency fund to 3 months first");
                } else if (isGoalFeasible) {
                    System.out.println("   FEASIBILITY: ✅ ACHIEVABLE");
                    System.out.println("   • Use dedicated vacation savings account");
                    System.out.println("   • Consider travel rewards credit cards");
                    System.out.println("   • Look for deals and off-season travel");
                } else {
                    System.out.println("   FEASIBILITY: ⚠️ NEEDS ADJUSTMENT");
                    System.out.println("   • Consider a more budget-friendly destination");
                    System.out.println("   • Extend savings timeline");
                    System.out.println("   • Look for additional income sources");
                }
                break;
                
            default:
                System.out.println("📚 GENERAL GOAL ANALYSIS:");
                System.out.printf("   Target Amount: $%,.0f%n", goalAmount);
                System.out.printf("   Required Monthly Savings: $%,.0f%n", requiredMonthlySavings);
                
                if (isGoalFeasible) {
                    System.out.println("   FEASIBILITY: ✅ ACHIEVABLE");
                } else {
                    System.out.println("   FEASIBILITY: ⚠️ CHALLENGING");
                }
                System.out.println("   • Create specific action plan");
                System.out.println("   • Set up automatic savings");
                System.out.println("   • Track progress monthly");
                break;
        }
        
        // ============================================================================
        // PERSONALIZED RECOMMENDATIONS
        // ============================================================================
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PERSONALIZED RECOMMENDATIONS:");
        System.out.println("=".repeat(60));
        
        // Immediate actions (next 30 days)
        System.out.println("\n🎯 IMMEDIATE ACTIONS (Next 30 days):");
        
        if (emergencyFundMonths < 3.0) {
            System.out.println("1. 🚨 PRIORITY: Build emergency fund to 3 months expenses");
            System.out.println("2. Open high-yield savings account for emergency fund");
            System.out.println("3. Set up automatic transfer for emergency fund building");
        } else {
            System.out.println("1. Open dedicated savings account for " + primaryGoal.toLowerCase());
            System.out.println("2. Set up automatic transfer of $" + String.format("%.0f", requiredMonthlySavings) + " monthly");
            System.out.println("3. Review and optimize current investment allocations");
        }
        
        if (creditScore < 700) {
            System.out.println("4. Request free credit report and identify improvement areas");
            System.out.println("5. Pay down high-utilization credit cards");
        } else {
            System.out.println("4. Research investment options aligned with risk tolerance");
            System.out.println("5. Consider increasing retirement contributions");
        }
        
        // Short-term goals (3-12 months)
        System.out.println("\n📈 SHORT-TERM GOALS (3-12 months):");
        
        if (savingsRate < 15.0) {
            System.out.println("1. Increase savings rate to 15% through expense optimization");
            System.out.println("2. Track expenses for 3 months to identify reduction opportunities");
        } else {
            System.out.println("1. Maintain excellent " + String.format("%.1f", savingsRate) + "% savings rate");
            System.out.println("2. Optimize investment allocation for better returns");
        }
        
        if (primaryGoal.equalsIgnoreCase("House Down Payment")) {
            System.out.println("3. Research first-time homebuyer programs");
            System.out.println("4. Get pre-qualified for mortgage");
            System.out.println("5. Start researching target neighborhoods");
        } else if (primaryGoal.equalsIgnoreCase("Retirement")) {
            System.out.println("3. Maximize employer 401(k) match");
            System.out.println("4. Consider Roth IRA contributions");
            System.out.println("5. Review and rebalance investment portfolio");
        }
        
        // Long-term strategy (1-3 years)
        System.out.println("\n🚀 LONG-TERM STRATEGY (1-3 years):");
        
        if (age < 40) {
            System.out.println("1. Focus on aggressive wealth building in early career");
            System.out.println("2. Consider increasing income through skills development");
            System.out.println("3. Build multiple income streams if possible");
        } else if (age < 55) {
            System.out.println("1. Balance wealth building with risk management");
            System.out.println("2. Ensure adequate life and disability insurance");
            System.out.println("3. Consider tax optimization strategies");
        } else {
            System.out.println("1. Focus on capital preservation and income generation");
            System.out.println("2. Plan for healthcare costs in retirement");
            System.out.println("3. Consider estate planning needs");
        }
        
        System.out.println("4. Achieve " + primaryGoal.toLowerCase() + " within " + goalTimeframe + " months");
        System.out.println("5. Reassess and adjust financial plan annually");
        
        // Caution areas
        System.out.println("\n⚠️ CAUTION AREAS:");
        
        if (emergencyFundMonths < 6.0) {
            System.out.println("• Emergency fund below 6 months - financial vulnerability");
        }
        
        if (savingsRate < 10.0) {
            System.out.println("• Low savings rate - may impact long-term financial security");
        }
        
        if (creditScore < 650) {
            System.out.println("• Credit score needs improvement - affecting loan options and rates");
        }
        
        if (expenseRatio > 80.0) {
            System.out.println("• High expense ratio - limited financial flexibility");
        }
        
        if (age > 40 && primaryGoal.equalsIgnoreCase("Retirement") && (requiredAnnualSavings / annualIncome) > 0.2) {
            System.out.println("• May be behind on retirement savings - consider working longer");
        }
        
        if (dependents > 0 && !primaryGoal.equalsIgnoreCase("Emergency Fund")) {
            System.out.println("• With dependents, ensure adequate life and disability insurance");
        }
        
        System.out.println("\n" + "=".repeat(60));
    }
}

/*
 * COMPREHENSIVE SOLUTION ANALYSIS:
 * 
 * This solution demonstrates mastery of all conditional logic structures:
 * 
 * 1. SIMPLE IF STATEMENTS:
 *    - Emergency fund status checking
 *    - Savings rate evaluation
 *    - Basic eligibility assessments
 *    - Threshold-based recommendations
 * 
 * 2. IF-ELSE STATEMENTS:
 *    - Binary financial health decisions
 *    - Goal feasibility determinations
 *    - Risk capacity assessments
 * 
 * 3. IF-ELSE IF-ELSE CHAINS:
 *    - Credit score categorization with 6 distinct ranges
 *    - Savings rate classification with 4+ levels
 *    - Age-based risk tolerance with multiple brackets
 * 
 * 4. NESTED IF STATEMENTS:
 *    - Complex investment risk assessment combining age, income, and risk tolerance
 *    - Multi-factor goal timeline analysis
 *    - Hierarchical recommendation logic
 * 
 * 5. SWITCH STATEMENTS:
 *    - Goal-specific analysis for 6 different financial goals
 *    - Distinct logic paths for each goal type
 *    - Default case handling for unknown goals
 * 
 * 6. REAL-WORLD APPLICATION FEATURES:
 *    - Comprehensive financial health assessment
 *    - Personalized investment recommendations
 *    - Goal feasibility analysis with specific action items
 *    - Risk-adjusted portfolio allocation
 *    - Multi-timeline recommendation structure
 * 
 * 7. ADVANCED CONDITIONAL PATTERNS:
 *    - Early validation and error handling
 *    - Complex multi-factor decision trees
 *    - Prioritized recommendation hierarchies
 *    - Context-aware advice generation
 * 
 * KEY LEARNING POINTS:
 * - Conditional logic structures serve different purposes and should be chosen appropriately
 * - Complex real-world decisions often require combining multiple conditional structures
 * - Nested conditions enable sophisticated decision trees
 * - Switch statements excel for discrete value-based decisions
 * - Professional applications require comprehensive validation and user-friendly output
 * - Financial logic demonstrates practical application of programming concepts
 */
