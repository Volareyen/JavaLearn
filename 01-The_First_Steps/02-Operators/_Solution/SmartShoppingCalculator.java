/**
 * Smart Shopping Calculator - Complete Solution
 * 
 * This program demonstrates mastery of all Java operator categories
 * through a comprehensive shopping cart system with complex discount
 * logic, tax calculations, and detailed financial analysis.
 * 
 * Author: The Wise Teacher's Example Solution
 */
public class SmartShoppingCalculator {
    
    // Bitwise flags for discount system (Bonus feature)
    private static final int SENIOR_FLAG = 1;      // 0001
    private static final int STUDENT_FLAG = 2;     // 0010  
    private static final int PREMIUM_FLAG = 4;     // 0100
    private static final int LOYALTY_FLAG = 8;     // 1000
    private static final int VOLUME_FLAG = 16;     // 10000
    
    public static void main(String[] args) {
        
        // ============================================================================
        // PRODUCT DATA AND CUSTOMER INFORMATION
        // ============================================================================
        
        // Product information
        String item1Name = "Laptop";
        double item1Price = 899.99;
        int item1Qty = 2;
        
        String item2Name = "Mouse";
        double item2Price = 24.95;
        int item2Qty = 3;
        
        String item3Name = "Keyboard";
        double item3Price = 79.50;
        int item3Qty = 1;
        
        String item4Name = "Monitor";
        double item4Price = 299.99;
        int item4Qty = 2;
        
        // Customer information
        String customerName = "Sarah Johnson";
        int customerAge = 67;
        boolean isPremiumMember = true;
        int yearsAsCustomer = 6;
        boolean hasStudentId = false;
        boolean usesCreditCard = true;  // vs debit card
        
        // System constants
        final double TAX_RATE = 8.25;  // 8.25% sales tax
        final double SENIOR_DISCOUNT_RATE = 10.0;
        final double STUDENT_DISCOUNT_RATE = 15.0;
        final double PREMIUM_DISCOUNT_RATE = 12.0;
        final double LOYALTY_DISCOUNT_RATE = 5.0;
        final double VOLUME_DISCOUNT_RATE = 8.0;
        final double CREDIT_CARD_FEE_RATE = 3.0;
        
        System.out.println("=".repeat(60));
        System.out.println("              SMART SHOPPING CALCULATOR");
        System.out.println("=".repeat(60));
        
        // ============================================================================
        // ARITHMETIC OPERATIONS - Basic Calculations
        // ============================================================================
        
        // Calculate subtotals using arithmetic operators
        double item1Subtotal = item1Price * item1Qty;
        double item2Subtotal = item2Price * item2Qty;
        double item3Subtotal = item3Price * item3Qty;
        double item4Subtotal = item4Price * item4Qty;
        
        // Calculate cart totals using addition
        double cartSubtotal = item1Subtotal + item2Subtotal + item3Subtotal + item4Subtotal;
        
        // Calculate total quantity using addition
        int totalQuantity = item1Qty + item2Qty + item3Qty + item4Qty;
        
        // Display customer information
        System.out.println("\n👤 CUSTOMER INFORMATION:");
        System.out.println("   Name: " + customerName);
        System.out.println("   Age: " + customerAge + " years");
        System.out.println("   Premium Member: " + (isPremiumMember ? "Yes" : "No"));
        System.out.println("   Years as Customer: " + yearsAsCustomer + " years");
        System.out.println("   Student ID: " + (hasStudentId ? "Yes" : "No"));
        System.out.println("   Payment Method: " + (usesCreditCard ? "Credit Card" : "Debit Card"));
        
        // Display shopping cart
        System.out.println("\n🛒 SHOPPING CART:");
        System.out.println("Item                 Price      Qty    Subtotal");
        System.out.println("-".repeat(52));
        System.out.printf("%-15s     $%7.2f  ×  %d  =  $%8.2f%n", item1Name, item1Price, item1Qty, item1Subtotal);
        System.out.printf("%-15s     $%7.2f  ×  %d  =  $%8.2f%n", item2Name, item2Price, item2Qty, item2Subtotal);
        System.out.printf("%-15s     $%7.2f  ×  %d  =  $%8.2f%n", item3Name, item3Price, item3Qty, item3Subtotal);
        System.out.printf("%-15s     $%7.2f  ×  %d  =  $%8.2f%n", item4Name, item4Price, item4Qty, item4Subtotal);
        System.out.println("-".repeat(52));
        System.out.printf("Cart Subtotal:                        $%8.2f%n", cartSubtotal);
        System.out.println("Total Items: " + totalQuantity);
        
        // ============================================================================
        // RELATIONAL AND LOGICAL OPERATIONS - Discount Eligibility
        // ============================================================================
        
        System.out.println("\n💰 DISCOUNT ELIGIBILITY ANALYSIS:");
        
        // Senior discount eligibility (relational and logical operators)
        boolean seniorAgeEligible = (customerAge >= 65);
        boolean seniorOrderEligible = (cartSubtotal > 200.0);
        boolean seniorDiscountEligible = seniorAgeEligible && seniorOrderEligible;
        
        // Student discount eligibility (logical operators with precedence)
        boolean studentAgeEligible = (customerAge <= 30);
        boolean studentIdValid = hasStudentId;
        boolean studentDiscountEligible = studentAgeEligible && studentIdValid && !seniorDiscountEligible;
        
        // Premium member discount eligibility (relational and logical)
        boolean premiumMemberEligible = isPremiumMember && (yearsAsCustomer >= 2);
        
        // Loyalty discount eligibility (can stack with others)
        boolean loyaltyDiscountEligible = (yearsAsCustomer >= 5);
        
        // Volume discount eligibility (multiple conditions)
        boolean volumeQuantityEligible = (totalQuantity >= 6);
        boolean volumeAmountEligible = (cartSubtotal >= 300.0);
        boolean volumeDiscountEligible = volumeQuantityEligible && volumeAmountEligible;
        
        // Display eligibility with detailed reasoning
        System.out.println("   Senior Discount (10%): " + 
                          (seniorDiscountEligible ? "✓ Eligible" : "✗ Not eligible") +
                          " - Age " + customerAge + (seniorAgeEligible ? " >= 65" : " < 65") +
                          ", Order $" + String.format("%.2f", cartSubtotal) + 
                          (seniorOrderEligible ? " > $200" : " <= $200"));
        
        System.out.println("   Student Discount (15%): " + 
                          (studentDiscountEligible ? "✓ Eligible" : "✗ Not eligible") +
                          " - Age " + customerAge + (studentAgeEligible ? " <= 30" : " > 30") +
                          ", Student ID: " + (hasStudentId ? "Yes" : "No") +
                          (seniorDiscountEligible ? ", Conflicts with senior" : ""));
        
        System.out.println("   Premium Member (12%): " + 
                          (premiumMemberEligible ? "✓ Eligible" : "✗ Not eligible") +
                          " - Premium: " + (isPremiumMember ? "Yes" : "No") +
                          ", " + yearsAsCustomer + (yearsAsCustomer >= 2 ? " >= 2" : " < 2") + " years");
        
        System.out.println("   Loyalty Discount (5%): " + 
                          (loyaltyDiscountEligible ? "✓ Eligible" : "✗ Not eligible") +
                          " - " + yearsAsCustomer + (yearsAsCustomer >= 5 ? " >= 5" : " < 5") + " years");
        
        System.out.println("   Volume Discount (8%): " + 
                          (volumeDiscountEligible ? "✓ Eligible" : "✗ Not eligible") +
                          " - " + totalQuantity + (volumeQuantityEligible ? " >= 6" : " < 6") + " items" +
                          ", $" + String.format("%.2f", cartSubtotal) + 
                          (volumeAmountEligible ? " >= $300" : " < $300"));
        
        // ============================================================================
        // BITWISE OPERATIONS - Discount Flags System (Bonus)
        // ============================================================================
        
        // Create discount flags using bitwise OR
        int discountFlags = 0;
        discountFlags |= seniorDiscountEligible ? SENIOR_FLAG : 0;
        discountFlags |= studentDiscountEligible ? STUDENT_FLAG : 0;
        discountFlags |= premiumMemberEligible ? PREMIUM_FLAG : 0;
        discountFlags |= loyaltyDiscountEligible ? LOYALTY_FLAG : 0;
        discountFlags |= volumeDiscountEligible ? VOLUME_FLAG : 0;
        
        System.out.println("\n🔢 BITWISE FLAGS ANALYSIS:");
        System.out.println("   Discount flags (binary): " + Integer.toBinaryString(discountFlags));
        System.out.println("   Senior flag set: " + ((discountFlags & SENIOR_FLAG) != 0));
        System.out.println("   Premium + Loyalty combo: " + 
                          ((discountFlags & (PREMIUM_FLAG | LOYALTY_FLAG)) == (PREMIUM_FLAG | LOYALTY_FLAG)));
        
        // Express shipping eligibility (premium + volume, or senior + loyalty)
        boolean expressShippingEligible = ((discountFlags & (PREMIUM_FLAG | VOLUME_FLAG)) != 0) ||
                                        ((discountFlags & (SENIOR_FLAG | LOYALTY_FLAG)) == (SENIOR_FLAG | LOYALTY_FLAG));
        
        // ============================================================================
        // ASSIGNMENT OPERATIONS - Applying Discounts
        // ============================================================================
        
        System.out.println("\n💸 APPLIED DISCOUNTS:");
        
        // Initialize discount tracking variables
        double totalSavings = 0.0;
        double runningTotal = cartSubtotal;
        String discountSummary = "";
        
        // Apply senior discount using compound assignment
        double seniorDiscountAmount = 0.0;
        if (seniorDiscountEligible) {
            seniorDiscountAmount = runningTotal * (SENIOR_DISCOUNT_RATE / 100.0);
            runningTotal -= seniorDiscountAmount;  // Compound assignment
            totalSavings += seniorDiscountAmount;  // Compound assignment
            discountSummary += "Senior(10%) ";
            System.out.printf("   Senior Discount (10%%):                -$%.2f%n", seniorDiscountAmount);
        }
        
        // Apply student discount (mutually exclusive with senior)
        double studentDiscountAmount = 0.0;
        if (studentDiscountEligible) {
            studentDiscountAmount = runningTotal * (STUDENT_DISCOUNT_RATE / 100.0);
            runningTotal -= studentDiscountAmount;
            totalSavings += studentDiscountAmount;
            discountSummary += "Student(15%) ";
            System.out.printf("   Student Discount (15%%):               -$%.2f%n", studentDiscountAmount);
        }
        
        // Apply premium member discount
        double premiumDiscountAmount = 0.0;
        if (premiumMemberEligible) {
            premiumDiscountAmount = runningTotal * (PREMIUM_DISCOUNT_RATE / 100.0);
            runningTotal -= premiumDiscountAmount;
            totalSavings += premiumDiscountAmount;
            discountSummary += "Premium(12%) ";
            System.out.printf("   Premium Member (12%%):                -$%.2f%n", premiumDiscountAmount);
        }
        
        // Apply loyalty discount (stackable)
        double loyaltyDiscountAmount = 0.0;
        if (loyaltyDiscountEligible) {
            loyaltyDiscountAmount = runningTotal * (LOYALTY_DISCOUNT_RATE / 100.0);
            runningTotal -= loyaltyDiscountAmount;
            totalSavings += loyaltyDiscountAmount;
            discountSummary += "Loyalty(5%) ";
            System.out.printf("   Loyalty Discount (5%%):                -$%.2f%n", loyaltyDiscountAmount);
        }
        
        // Apply volume discount
        double volumeDiscountAmount = 0.0;
        if (volumeDiscountEligible) {
            volumeDiscountAmount = runningTotal * (VOLUME_DISCOUNT_RATE / 100.0);
            runningTotal -= volumeDiscountAmount;
            totalSavings += volumeDiscountAmount;
            discountSummary += "Volume(8%) ";
            System.out.printf("   Volume Discount (8%%):                 -$%.2f%n", volumeDiscountAmount);
        }
        
        // Display total savings if any discounts applied
        if (totalSavings > 0) {
            System.out.println("-".repeat(52));
            System.out.printf("   Total Savings:                        -$%.2f%n", totalSavings);
        } else {
            System.out.println("   No discounts applied.");
        }
        
        // ============================================================================
        // TERNARY OPERATORS - Conditional Calculations
        // ============================================================================
        
        System.out.println("\n🚚 SHIPPING AND FEES CALCULATION:");
        
        // Shipping cost calculation using ternary operators
        double shippingCost = (runningTotal >= 500.0) ? 0.0 :
                             (runningTotal >= 200.0) ? 15.0 :
                             (runningTotal >= 100.0) ? 25.0 : 35.0;
        
        String shippingTier = (runningTotal >= 500.0) ? "FREE" :
                             (runningTotal >= 200.0) ? "STANDARD ($15)" :
                             (runningTotal >= 100.0) ? "ECONOMY ($25)" : "BASIC ($35)";
        
        // Delivery timeframe using ternary
        String deliveryTime = expressShippingEligible ? "1-2 business days" :
                             isPremiumMember ? "2-3 business days" : "3-5 business days";
        
        // Payment processing fee using ternary
        double processingFeeRate = usesCreditCard ? CREDIT_CARD_FEE_RATE : 0.0;
        String paymentMethod = usesCreditCard ? "Credit Card" : "Debit Card";
        
        System.out.println("   Shipping Tier: " + shippingTier);
        System.out.println("   Delivery Time: " + deliveryTime);
        System.out.println("   Express Shipping Eligible: " + (expressShippingEligible ? "Yes" : "No"));
        
        // ============================================================================
        // FINAL CALCULATIONS - Complete Order Total
        // ============================================================================
        
        // Calculate tax on discounted amount
        double taxAmount = runningTotal * (TAX_RATE / 100.0);
        runningTotal += taxAmount;  // Add tax
        
        // Add shipping cost
        runningTotal += shippingCost;
        
        // Calculate and add processing fee
        double processingFee = runningTotal * (processingFeeRate / 100.0);
        runningTotal += processingFee;
        
        // Round final total to 2 decimal places
        double finalTotal = Math.round(runningTotal * 100.0) / 100.0;
        
        // Calculate savings percentage
        double savingsPercentage = (totalSavings / cartSubtotal) * 100.0;
        
        System.out.println("\n📊 FINAL CALCULATION:");
        System.out.printf("   Cart Subtotal:                        $%8.2f%n", cartSubtotal);
        
        if (totalSavings > 0) {
            System.out.printf("   Total Discounts:                      -$%8.2f%n", totalSavings);
            System.out.printf("   Discounted Subtotal:                  $%8.2f%n", cartSubtotal - totalSavings);
        }
        
        System.out.printf("   Sales Tax (%.2f%%):                    +$%8.2f%n", TAX_RATE, taxAmount);
        
        if (shippingCost > 0) {
            System.out.printf("   Shipping Cost:                        +$%8.2f%n", shippingCost);
        } else {
            System.out.println("   Shipping Cost:                            FREE");
        }
        
        if (processingFee > 0) {
            System.out.printf("   Payment Processing (%.1f%%):           +$%8.2f%n", processingFeeRate, processingFee);
        }
        
        System.out.println("-".repeat(52));
        System.out.printf("   FINAL TOTAL:                          $%8.2f%n", finalTotal);
        
        // ============================================================================
        // SUMMARY AND ANALYSIS
        // ============================================================================
        
        System.out.println("\n📋 ORDER SUMMARY:");
        System.out.printf("   • Original Price: $%.2f%n", cartSubtotal);
        
        if (totalSavings > 0) {
            System.out.printf("   • You Saved: $%.2f (%.1f%%)%n", totalSavings, savingsPercentage);
            System.out.println("   • Discounts Applied: " + discountSummary.trim());
        } else {
            System.out.println("   • No discounts applied");
        }
        
        System.out.printf("   • Tax Included: $%.2f%n", taxAmount);
        System.out.println("   • Shipping: " + (shippingCost == 0 ? "FREE" : String.format("$%.2f", shippingCost)));
        System.out.println("   • Payment Method: " + paymentMethod);
        
        if (processingFee > 0) {
            System.out.printf("   • Processing Fee: $%.2f%n", processingFee);
        }
        
        System.out.println("   • Delivery: " + deliveryTime);
        System.out.println("   • Total Items: " + totalQuantity);
        
        // ============================================================================
        // OPERATOR PRECEDENCE DEMONSTRATION (Bonus)
        // ============================================================================
        
        System.out.println("\n🔍 OPERATOR PRECEDENCE DEMONSTRATION:");
        
        // Complex calculation showing precedence
        double complexCalc1 = cartSubtotal * 0.9 + taxAmount * 1.5 / 2.0;  // Without parentheses
        double complexCalc2 = (cartSubtotal * 0.9) + ((taxAmount * 1.5) / 2.0);  // With parentheses (same result)
        double complexCalc3 = cartSubtotal * (0.9 + taxAmount) * 1.5 / 2.0;  // Different grouping
        
        System.out.printf("   Without parentheses: $%.2f%n", complexCalc1);
        System.out.printf("   With parentheses (same): $%.2f%n", complexCalc2);
        System.out.printf("   Different grouping: $%.2f%n", complexCalc3);
        
        // Boolean precedence demonstration
        boolean complexCondition1 = customerAge >= 65 && isPremiumMember || yearsAsCustomer >= 5;
        boolean complexCondition2 = (customerAge >= 65 && isPremiumMember) || (yearsAsCustomer >= 5);
        boolean complexCondition3 = customerAge >= 65 && (isPremiumMember || yearsAsCustomer >= 5);
        
        System.out.println("\n   Boolean Precedence:");
        System.out.println("     Condition 1 (natural precedence): " + complexCondition1);
        System.out.println("     Condition 2 (explicit grouping): " + complexCondition2);
        System.out.println("     Condition 3 (different grouping): " + complexCondition3);
        
        // ============================================================================
        // PERFORMANCE INSIGHTS
        // ============================================================================
        
        System.out.println("\n⚡ PERFORMANCE INSIGHTS:");
        
        // Demonstrate bitwise vs arithmetic operations
        int fastMultiply = totalQuantity << 3;  // Multiply by 8 using bit shift
        int regularMultiply = totalQuantity * 8;  // Regular multiplication
        
        boolean fastEvenCheck = (totalQuantity & 1) == 0;  // Bitwise even check
        boolean regularEvenCheck = (totalQuantity % 2) == 0;  // Modulus even check
        
        System.out.println("   Bitwise multiply by 8: " + fastMultiply);
        System.out.println("   Regular multiply by 8: " + regularMultiply);
        System.out.println("   Bitwise even check: " + fastEvenCheck);
        System.out.println("   Regular even check: " + regularEvenCheck);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🎯 OPERATOR MASTERY DEMONSTRATED:");
        System.out.println("   ✓ Arithmetic: Price calculations and totals");
        System.out.println("   ✓ Assignment: Efficient discount accumulation");
        System.out.println("   ✓ Relational: Eligibility comparisons");
        System.out.println("   ✓ Logical: Complex discount rule combinations");
        System.out.println("   ✓ Ternary: Clean conditional assignments");
        System.out.println("   ✓ Bitwise: Flag system and performance optimizations");
        System.out.println("   ✓ Precedence: Demonstrated evaluation order importance");
        System.out.println("=".repeat(60));
        
        // Generate unique transaction ID using bitwise operations (bonus)
        int transactionId = (customerAge << 16) | (yearsAsCustomer << 8) | (totalQuantity & 0xFF);
        System.out.printf("\n🆔 Transaction ID (bitwise generated): %08X%n", transactionId);
    }
}

/*
 * SOLUTION ANALYSIS:
 * 
 * This comprehensive solution demonstrates mastery of all Java operator categories:
 * 
 * 1. ARITHMETIC OPERATORS:
 *    - Basic calculations (*, +, -, /)
 *    - Precise money handling with rounding
 *    - Percentage calculations for discounts and taxes
 *    - Compound calculations for final totals
 * 
 * 2. ASSIGNMENT OPERATORS:
 *    - Simple assignment for initial values
 *    - Compound assignment (+=, -=) for accumulating totals
 *    - String concatenation assignment for building summaries
 *    - Efficient variable updates throughout calculations
 * 
 * 3. RELATIONAL OPERATORS:
 *    - Age comparisons for discount eligibility
 *    - Price threshold comparisons for shipping tiers
 *    - Quantity comparisons for volume discounts
 *    - Multiple comparison combinations
 * 
 * 4. LOGICAL OPERATORS:
 *    - Complex AND conditions for discount eligibility
 *    - OR conditions for flexible discount combinations
 *    - NOT conditions for exclusions and conflicts
 *    - Short-circuit evaluation for efficiency
 * 
 * 5. TERNARY OPERATORS:
 *    - Shipping cost tier determination
 *    - Delivery time selection
 *    - Payment method fee calculation
 *    - Clean conditional value assignment
 * 
 * 6. BITWISE OPERATORS (BONUS):
 *    - Flag system for discount tracking
 *    - Efficient even/odd checking
 *    - Fast multiplication/division with bit shifts
 *    - Transaction ID generation
 * 
 * 7. REAL-WORLD APPLICATION:
 *    - Professional e-commerce checkout system
 *    - Complex business rule implementation
 *    - Accurate financial calculations
 *    - User-friendly output formatting
 * 
 * KEY LEARNING POINTS:
 * - Operators are the foundation of all program logic
 * - Complex business rules require combining multiple operator types
 * - Operator precedence affects calculation results
 * - Compound assignment operators improve code efficiency
 * - Bitwise operations can provide performance benefits
 * - Real-world applications require careful consideration of edge cases
 */
