/**
 * SmartHomeSystem.java — ISP Solution
 *
 * Segregated interfaces — each device implements only what it actually does.
 * Run: javac SmartHomeSystem.java && java SmartHomeSystem
 */
import java.util.*;

public class SmartHomeSystem {

    interface Switchable    { void turnOn(); void turnOff(); }
    interface Dimmable      { void setDimLevel(int pct); }
    interface Thermostatic  { void setTemperature(double c); }
    interface AudioPlayable { void playAudio(String src); void stopAudio(); }
    interface Lockable      { void lock(); void unlock(); }

    static class SmartLight implements Switchable, Dimmable {
        private final String id;
        SmartLight(String id) { this.id = id; }
        public void turnOn()             { System.out.println("  💡 " + id + " ON"); }
        public void turnOff()            { System.out.println("  💡 " + id + " OFF"); }
        public void setDimLevel(int pct) { System.out.println("  💡 " + id + " dimmed to " + pct + "%"); }
    }

    static class SmartThermostat implements Switchable, Thermostatic {
        public void turnOn()                 { System.out.println("  🌡 Thermostat ON"); }
        public void turnOff()                { System.out.println("  🌡 Thermostat OFF"); }
        public void setTemperature(double c) { System.out.println("  🌡 Set to " + c + "°C"); }
    }

    static class SmartSpeaker implements Switchable, AudioPlayable {
        public void turnOn()              { System.out.println("  🔊 Speaker ON"); }
        public void turnOff()             { System.out.println("  🔊 Speaker OFF"); }
        public void playAudio(String src) { System.out.println("  🔊 Playing: " + src); }
        public void stopAudio()           { System.out.println("  🔊 Stopped"); }
    }

    static class SmartLock implements Lockable {
        private final String door;
        SmartLock(String door) { this.door = door; }
        public void lock()   { System.out.println("  🔒 " + door + " LOCKED"); }
        public void unlock() { System.out.println("  🔓 " + door + " UNLOCKED"); }
    }

    // Automation routines accept only the interface they need
    static void morningRoutine(Switchable... devices) {
        System.out.println("  [Morning: switching ON]");
        for (Switchable d : devices) d.turnOn();
    }

    static void movieMode(Dimmable... lights) {
        System.out.println("  [Movie: dimming lights]");
        for (Dimmable l : lights) l.setDimLevel(15);
    }

    static void goodnight(Lockable... locks) {
        System.out.println("  [Goodnight: locking up]");
        for (Lockable l : locks) l.lock();
    }

    public static void main(String[] args) {
        System.out.println("=== ISP SOLUTION: SMART HOME ===\n");

        SmartLight living  = new SmartLight("Living Room");
        SmartLight bedroom = new SmartLight("Bedroom");
        SmartThermostat t  = new SmartThermostat();
        SmartSpeaker spk   = new SmartSpeaker();
        SmartLock front    = new SmartLock("Front Door");
        SmartLock back     = new SmartLock("Back Door");

        morningRoutine(living, bedroom, t, spk);
        t.setTemperature(21.5);
        spk.playAudio("Morning news");

        System.out.println();
        movieMode(living, bedroom);
        spk.playAudio("Movie OST");

        System.out.println();
        goodnight(front, back);

        System.out.println("\nISP ✅ — no class has a single useless method stub");
    }
}
