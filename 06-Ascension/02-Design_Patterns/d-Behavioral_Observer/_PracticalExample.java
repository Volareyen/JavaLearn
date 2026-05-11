/**
 * _PracticalExample.java — Observer Pattern: Live Auction System
 *
 * A real-time auction where multiple bidders observe an item and are
 * instantly notified of new bids, overbids, and auction close events.
 *
 * Compile & Run: javac _PracticalExample.java && java _PracticalExample
 */
import java.util.*;
import java.util.function.Consumer;

public class _PracticalExample {

    // ── Event Types ──
    enum AuctionEvent { NEW_BID, OUTBID, AUCTION_CLOSED, RESERVE_MET }

    static class BidEvent {
        AuctionEvent type;
        String item;
        String bidder;
        double amount;

        BidEvent(AuctionEvent type, String item, String bidder, double amount) {
            this.type = type; this.item = item;
            this.bidder = bidder; this.amount = amount;
        }
    }

    // ── Subject: Auction ──
    static class Auction {
        private String itemName;
        private double currentBid;
        private double reservePrice;
        private String currentWinner;
        private boolean closed;

        private List<Consumer<BidEvent>> observers = new ArrayList<>();

        Auction(String itemName, double startingBid, double reservePrice) {
            this.itemName = itemName;
            this.currentBid = startingBid;
            this.reservePrice = reservePrice;
        }

        public void subscribe(Consumer<BidEvent> observer) { observers.add(observer); }

        private void publish(BidEvent event) {
            observers.forEach(obs -> obs.accept(event));
        }

        public boolean placeBid(String bidder, double amount) {
            if (closed) { System.out.println("  ⚠ Auction is closed!"); return false; }
            if (amount <= currentBid) {
                System.out.printf("  ⚠ %s bid $%.2f rejected (current: $%.2f)%n",
                    bidder, amount, currentBid);
                return false;
            }

            String previousWinner = currentWinner;
            currentBid = amount;
            currentWinner = bidder;

            publish(new BidEvent(AuctionEvent.NEW_BID, itemName, bidder, amount));

            if (previousWinner != null && !previousWinner.equals(bidder)) {
                publish(new BidEvent(AuctionEvent.OUTBID, itemName, previousWinner, amount));
            }

            if (amount >= reservePrice) {
                publish(new BidEvent(AuctionEvent.RESERVE_MET, itemName, bidder, amount));
            }
            return true;
        }

        public void close() {
            closed = true;
            publish(new BidEvent(AuctionEvent.AUCTION_CLOSED, itemName, currentWinner, currentBid));
        }
    }

    // ── Observer Implementations ──
    static class Bidder {
        String name;
        double budget;
        double maxBid;
        List<String> notifications = new ArrayList<>();

        Bidder(String name, double budget, double maxBid) {
            this.name = name; this.budget = budget; this.maxBid = maxBid;
        }

        Consumer<BidEvent> getHandler(Auction auction) {
            return event -> {
                if (event.type == AuctionEvent.OUTBID && event.item.equals(auction.itemName)
                        && event.bidder.equals(name)) {
                    System.out.printf("  📨 %s: OUTBID at $%.2f! Auto-bidding...%n", name, event.amount);
                    double nextBid = event.amount + 50;
                    if (nextBid <= maxBid) {
                        auction.placeBid(name, nextBid);
                    } else {
                        System.out.printf("  💸 %s: Reached budget limit, stepping out.%n", name);
                    }
                } else if (event.type == AuctionEvent.AUCTION_CLOSED) {
                    if (event.bidder != null && event.bidder.equals(name)) {
                        System.out.printf("  🏆 %s WON '%s' for $%.2f!%n", name, event.item, event.amount);
                    }
                }
            };
        }
    }

    static class AuctionHouse {
        Consumer<BidEvent> getLogger() {
            return event -> {
                switch (event.type) {
                    case NEW_BID -> System.out.printf("  📋 LOG: %s bid $%.2f on '%s'%n",
                        event.bidder, event.amount, event.item);
                    case RESERVE_MET -> System.out.printf("  ✅ RESERVE MET for '%s' at $%.2f%n",
                        event.item, event.amount);
                    case AUCTION_CLOSED -> System.out.printf("  🔨 SOLD '%s' to %s for $%.2f%n",
                        event.item, event.bidder, event.amount);
                    default -> {}
                }
            };
        }
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   🔨 LIVE AUCTION SYSTEM             ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        Auction auction = new Auction("Rare Java Manuscript", 100.00, 500.00);

        Bidder alice = new Bidder("Alice", 1000, 600);
        Bidder bob   = new Bidder("Bob",   800, 550);
        Bidder carol = new Bidder("Carol", 700, 480);

        AuctionHouse house = new AuctionHouse();

        // Subscribe observers
        auction.subscribe(house.getLogger());
        auction.subscribe(alice.getHandler(auction));
        auction.subscribe(bob.getHandler(auction));
        auction.subscribe(carol.getHandler(auction));

        System.out.println("── Bidding begins! ──");
        auction.placeBid("Alice", 150.00);
        System.out.println();
        auction.placeBid("Bob", 200.00);
        System.out.println();
        auction.placeBid("Carol", 300.00);
        System.out.println();
        auction.placeBid("Bob", 400.00);
        System.out.println();
        auction.placeBid("Alice", 520.00);
        System.out.println();

        System.out.println("── Auctioneer closes bidding ──");
        auction.close();

        System.out.println("\n✅ Observer pattern: all bidders notified of every event!");
    }
}
