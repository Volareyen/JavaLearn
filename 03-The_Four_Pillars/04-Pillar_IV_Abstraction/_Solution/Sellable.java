/*
 * INTERFACE CONTRACT: SELLABLE
 * 
 * "In the realm of commerce, some creations transcend their artistic essence
 * to become commodities. This contract governs the sacred exchange of art for treasure."
 * 
 * COMPILATION NOTE: This interface is implemented by Painting, DigitalArt, and Sculpture
 * Compile all solution files together: javac *.java
 */

/**
 * Interface defining the contract for artwork available for purchase.
 * 
 * This represents the CAPABILITY of being sold - not all artwork in a gallery
 * is for sale (some are permanent collections, loans, or artist retrospectives),
 * but for those that can be purchased, they must fulfill these commercial obligations.
 * 
 * Examples: Paintings for sale, limited edition prints, available sculptures
 * 
 * The wisdom: Commerce is a behavior, not an identity. An artwork doesn't
 * change what it IS when it becomes sellable - it gains what it CAN DO.
 */
public interface Sellable {
    
    // ===== COMMERCIAL CONSTANTS: The mathematics of art commerce =====
    
    /**
     * Gallery commission rate - 15% of sale price.
     * Standard rate for most gallery representations.
     */
    double COMMISSION_RATE = 0.15;
    
    /**
     * Sales tax rate - 8% of final price.
     * Varies by jurisdiction, but standardized here for simplicity.
     */
    double TAX_RATE = 0.08;
    
    /**
     * Minimum markup over estimated value for pricing.
     */
    double MIN_MARKUP_RATE = 1.05;  // 5% minimum above estimated value
    
    /**
     * Standard markup for gallery pricing.
     */
    double STANDARD_MARKUP_RATE = 1.10;  // 10% above estimated value
    
    // ===== ABSTRACT METHODS: Pure contracts for commercial operations =====
    
    /**
     * List artwork for sale at specified price.
     * Each sellable item may have different listing requirements.
     * 
     * @param askingPrice The price being asked for the artwork
     */
    void putOnSale(double askingPrice);
    
    /**
     * Remove artwork from the market.
     * Different items may have different removal procedures.
     */
    void removeFromSale();
    
    /**
     * Check if artwork is currently available for purchase.
     * 
     * @return true if available for sale
     */
    boolean isForSale();
    
    /**
     * Calculate final price including all fees and taxes.
     * Each type may have different calculation methods.
     * 
     * @return Total price the buyer will pay
     */
    double getFinalPrice();
    
    /**
     * Process the actual transaction and transfer ownership.
     * Different artwork types may have different transaction requirements.
     * 
     * @param buyerName Name of the purchasing party
     */
    void processTransaction(String buyerName);
    
    // ===== DEFAULT METHODS: Standard commercial implementations =====
    
    /**
     * Apply standard gallery pricing at 110% of estimated value.
     * Most artwork follows this standard markup strategy.
     */
    default void applyStandardPricing() {
        // Note: This requires the implementing class to have access to estimated value
        // In a real implementation, this might need to be more sophisticated
        System.out.println("💰 Applying standard gallery pricing (10% markup)...");
        System.out.println("📊 Use putOnSale() with calculated price");
    }
    
    /**
     * Generate sales documentation for the transaction.
     * Standard documentation most galleries require.
     * 
     * @param buyerName Name of the buyer
     * @param salePrice Final sale price
     * @return Documentation string
     */
    default String generateSalesDocumentation(String buyerName, double salePrice) {
        StringBuilder doc = new StringBuilder();
        doc.append("🧾 SALES RECEIPT\n");
        doc.append("═══════════════════\n");
        doc.append("Buyer: ").append(buyerName).append("\n");
        doc.append("Sale Price: $").append(String.format("%.2f", salePrice)).append("\n");
        doc.append("Commission (").append((int)(COMMISSION_RATE * 100))
           .append("%): $").append(String.format("%.2f", salePrice * COMMISSION_RATE)).append("\n");
        doc.append("Tax (").append((int)(TAX_RATE * 100))
           .append("%): $").append(String.format("%.2f", salePrice * TAX_RATE)).append("\n");
        doc.append("Date: ").append(java.time.LocalDate.now()).append("\n");
        doc.append("═══════════════════\n");
        
        return doc.toString();
    }
    
    /**
     * Handle the standard sale process workflow.
     * Common steps most sales follow.
     * 
     * @param buyerName Name of the buyer
     * @param agreedPrice Price buyer has agreed to pay
     */
    default void conductStandardSale(String buyerName, double agreedPrice) {
        System.out.println("🤝 Initiating standard sale process...");
        
        if (!isForSale()) {
            System.out.println("❌ Error: Artwork not currently for sale");
            return;
        }
        
        System.out.println("📋 Verifying buyer information: " + buyerName);
        System.out.println("💵 Confirmed sale price: $" + String.format("%.2f", agreedPrice));
        System.out.println("🧮 Calculating final costs...");
        
        double finalPrice = agreedPrice * (1.0 + TAX_RATE);
        System.out.println("💰 Total amount due: $" + String.format("%.2f", finalPrice));
        
        System.out.println("📄 Generating sales documentation...");
        processTransaction(buyerName);
        
        System.out.println("✅ Sale completed successfully!");
    }
    
    /**
     * Calculate net proceeds for the seller after fees.
     * What the seller actually receives after gallery commission.
     * 
     * @param salePrice Gross sale price
     * @return Net amount seller receives
     */
    default double calculateNetProceeds(double salePrice) {
        double commission = salePrice * COMMISSION_RATE;
        double netAmount = salePrice - commission;
        
        System.out.println("🧮 NET PROCEEDS CALCULATION:");
        System.out.println("   Gross Sale: $" + String.format("%.2f", salePrice));
        System.out.println("   Commission: $" + String.format("%.2f", commission));
        System.out.println("   Net to Seller: $" + String.format("%.2f", netAmount));
        
        return netAmount;
    }
    
    // ===== STATIC METHODS: Commercial utility functions =====
    
    /**
     * Validates that a sale price is reasonable.
     * 
     * @param salePrice Proposed sale price
     * @param estimatedValue Artwork's estimated value
     * @return true if price is within reasonable bounds
     */
    static boolean isReasonablePrice(double salePrice, double estimatedValue) {
        // Price should be at least 50% of estimated value and no more than 500%
        double minPrice = estimatedValue * 0.5;
        double maxPrice = estimatedValue * 5.0;
        
        return salePrice >= minPrice && salePrice <= maxPrice;
    }
    
    /**
     * Calculate suggested asking price based on various factors.
     * 
     * @param estimatedValue Base estimated value
     * @param artistReputation Factor (0.5 to 2.0) based on artist fame
     * @param marketDemand Factor (0.7 to 1.5) based on current demand
     * @return Suggested asking price
     */
    static double suggestAskingPrice(double estimatedValue, double artistReputation, double marketDemand) {
        double basePrice = estimatedValue * STANDARD_MARKUP_RATE;
        double adjustedPrice = basePrice * artistReputation * marketDemand;
        
        System.out.println("💡 PRICING SUGGESTION:");
        System.out.println("   Base Value: $" + String.format("%.2f", estimatedValue));
        System.out.println("   With Markup: $" + String.format("%.2f", basePrice));
        System.out.println("   Artist Factor: " + artistReputation + "x");
        System.out.println("   Market Factor: " + marketDemand + "x");
        System.out.println("   Suggested Price: $" + String.format("%.2f", adjustedPrice));
        
        return adjustedPrice;
    }
    
    /**
     * Calculate total buyer cost including all fees.
     * 
     * @param askingPrice The listed price of the artwork
     * @return Total amount buyer will pay
     */
    static double calculateBuyerTotal(double askingPrice) {
        return askingPrice * (1.0 + TAX_RATE);
    }
    
    /**
     * Generate market analysis for pricing decisions.
     * 
     * @param recentSales Array of recent comparable sale prices
     * @return Market analysis summary
     */
    static String generateMarketAnalysis(double[] recentSales) {
        if (recentSales == null || recentSales.length == 0) {
            return "📊 No comparable sales data available";
        }
        
        double total = 0;
        double min = recentSales[0];
        double max = recentSales[0];
        
        for (double sale : recentSales) {
            total += sale;
            if (sale < min) min = sale;
            if (sale > max) max = sale;
        }
        
        double average = total / recentSales.length;
        
        StringBuilder analysis = new StringBuilder();
        analysis.append("📊 MARKET ANALYSIS (").append(recentSales.length).append(" comparable sales)\n");
        analysis.append("   Average: $").append(String.format("%.2f", average)).append("\n");
        analysis.append("   Range: $").append(String.format("%.2f", min))
                .append(" - $").append(String.format("%.2f", max)).append("\n");
        analysis.append("   Suggested Range: $").append(String.format("%.2f", average * 0.85))
                .append(" - $").append(String.format("%.2f", average * 1.15));
        
        return analysis.toString();
    }
}

/*
 * COMMERCIAL INTERFACE WISDOM:
 * 
 * 1. SEPARATION OF CONCERNS: Being sellable is independent of being art -
 *    an artwork doesn't change its artistic nature when it gains commercial value
 * 
 * 2. BUSINESS LOGIC ENCAPSULATION: All commercial rules (commission rates,
 *    tax calculations, pricing strategies) are contained within this contract
 * 
 * 3. FLEXIBLE IMPLEMENTATION: Different art forms can implement sales differently
 *    (digital art might use blockchain, physical art needs physical transfer)
 * 
 * 4. DEFAULT WORKFLOWS: Common business processes are provided as defaults
 *    but can be overridden for specialized requirements
 * 
 * 5. UTILITY FUNCTIONS: Static methods provide tools for pricing and analysis
 *    that don't require an instance but are related to the concept
 * 
 * 6. EXTENSIBILITY: New sale models (auctions, installments, rentals) can be
 *    added without breaking existing code
 * 
 * This interface demonstrates how abstraction can cleanly separate business
 * concerns from core functionality, creating systems that are both flexible
 * and maintainable.
 * 
 * "Commerce is but one facet of art's existence - important when present,
 *  but not defining the art's essential nature."
 */
