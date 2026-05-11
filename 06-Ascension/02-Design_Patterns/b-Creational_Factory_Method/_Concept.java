/**
 * _Concept.java — Factory Method Pattern
 * 
 * Compile & Run: javac _Concept.java && java _Concept
 */
public class _Concept {

    // ── Product interface ──
    interface Transport {
        void deliver(String cargo);
        String type();
    }

    // ── Concrete Products ──
    static class Truck implements Transport {
        public void deliver(String cargo) {
            System.out.println("  🚛 Truck delivering '" + cargo + "' by road");
        }
        public String type() { return "Road Truck"; }
    }

    static class Ship implements Transport {
        public void deliver(String cargo) {
            System.out.println("  🚢 Ship delivering '" + cargo + "' by sea");
        }
        public String type() { return "Cargo Ship"; }
    }

    static class Drone implements Transport {
        public void deliver(String cargo) {
            System.out.println("  🚁 Drone delivering '" + cargo + "' by air");
        }
        public String type() { return "Delivery Drone"; }
    }

    // ── Abstract Creator ──
    static abstract class Logistics {
        // THE FACTORY METHOD — subclasses override this
        protected abstract Transport createTransport();

        // Template method uses the factory method
        public void planDelivery(String cargo) {
            Transport transport = createTransport();
            System.out.println("  Using: " + transport.type());
            transport.deliver(cargo);
        }
    }

    // ── Concrete Creators — each decides what to build ──
    static class RoadLogistics extends Logistics {
        protected Transport createTransport() { return new Truck(); }
    }

    static class SeaLogistics extends Logistics {
        protected Transport createTransport() { return new Ship(); }
    }

    // Adding a new type? Just add a new Creator — nothing else changes!
    static class AirLogistics extends Logistics {
        protected Transport createTransport() { return new Drone(); }
    }

    // ── Simple Factory (alternative, simpler but less flexible) ──
    static class TransportFactory {
        public static Transport create(String mode) {
            return switch (mode.toLowerCase()) {
                case "road" -> new Truck();
                case "sea"  -> new Ship();
                case "air"  -> new Drone();
                default -> throw new IllegalArgumentException("Unknown mode: " + mode);
            };
        }
    }

    public static void main(String[] args) {
        System.out.println("=== FACTORY METHOD PATTERN ===\n");

        System.out.println("── Factory Method (Polymorphic Creators) ──");
        Logistics[] logistics = { new RoadLogistics(), new SeaLogistics(), new AirLogistics() };
        for (Logistics l : logistics) {
            l.planDelivery("Electronics");
        }

        System.out.println("\n── Simple Factory (Centralized switch) ──");
        String[] modes = {"road", "sea", "air"};
        for (String mode : modes) {
            Transport t = TransportFactory.create(mode);
            t.deliver("Books");
        }

        System.out.println("\nNew transport type? Add a class — no existing code changes!");
    }
}
