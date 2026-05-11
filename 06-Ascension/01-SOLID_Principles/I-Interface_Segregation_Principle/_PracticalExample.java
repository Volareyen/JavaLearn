/**
 * _PracticalExample.java — ISP: Smart Home Device System
 *
 * Demonstrates ISP by splitting a fat SmartDevice interface into
 * focused capabilities. Each device implements only what it actually does.
 *
 * Compile & Run: javac _PracticalExample.java && java _PracticalExample
 */
public class _PracticalExample {

    // ── Focused interfaces — each represents one capability ──
    interface Switchable    { void turnOn(); void turnOff(); boolean isOn(); }
    interface Dimmable      { void setDimLevel(int percent); int getDimLevel(); }
    interface Thermostatic  { void setTemperature(double celsius); double getTemperature(); }
    interface AudioPlayable { void playAudio(String source); void stopAudio(); }
    interface Lockable      { void lock(); void unlock(); boolean isLocked(); }

    // ── SmartLight: Switchable + Dimmable only ──
    static class SmartLight implements Switchable, Dimmable {
        private boolean on = false;
        private int dimLevel = 100;
        private String name;
        SmartLight(String name) { this.name = name; }

        public void turnOn()  { on = true;  System.out.println("  💡 " + name + " ON at " + dimLevel + "%"); }
        public void turnOff() { on = false; System.out.println("  💡 " + name + " OFF"); }
        public boolean isOn() { return on; }
        public void setDimLevel(int p) { dimLevel = Math.max(0, Math.min(100, p)); System.out.println("  💡 " + name + " dimmed to " + dimLevel + "%"); }
        public int getDimLevel() { return dimLevel; }
    }

    // ── SmartThermostat: Switchable + Thermostatic only ──
    static class SmartThermostat implements Switchable, Thermostatic {
        private boolean on = false;
        private double temp = 20.0;
        public void turnOn()  { on = true;  System.out.println("  🌡 Thermostat ON, target: " + temp + "°C"); }
        public void turnOff() { on = false; System.out.println("  🌡 Thermostat OFF"); }
        public boolean isOn() { return on; }
        public void setTemperature(double c) { temp = c; System.out.println("  🌡 Temperature set to " + c + "°C"); }
        public double getTemperature() { return temp; }
    }

    // ── SmartSpeaker: Switchable + AudioPlayable only ──
    static class SmartSpeaker implements Switchable, AudioPlayable {
        private boolean on = false;
        private String playing = null;
        public void turnOn()  { on = true;  System.out.println("  🔊 Speaker ON"); }
        public void turnOff() { on = false; System.out.println("  🔊 Speaker OFF"); }
        public boolean isOn() { return on; }
        public void playAudio(String src) { playing = src; System.out.println("  🔊 Playing: " + src); }
        public void stopAudio() { System.out.println("  🔊 Stopped: " + playing); playing = null; }
    }

    // ── SmartLock: Lockable only ──
    static class SmartLock implements Lockable {
        private boolean locked = true;
        private String name;
        SmartLock(String name) { this.name = name; }
        public void lock()   { locked = true;  System.out.println("  🔒 " + name + " LOCKED"); }
        public void unlock() { locked = false; System.out.println("  🔓 " + name + " UNLOCKED"); }
        public boolean isLocked() { return locked; }
    }

    // ── SmartHub: Has everything ──
    static class SmartHub implements Switchable, Dimmable, Thermostatic, AudioPlayable, Lockable {
        private boolean on = false; private int dim = 80; private double temp = 21;
        private boolean locked = false; private String audio = null;
        public void turnOn()  { on = true;  System.out.println("  🏠 Hub active"); }
        public void turnOff() { on = false; System.out.println("  🏠 Hub offline"); }
        public boolean isOn() { return on; }
        public void setDimLevel(int p) { dim = p; }
        public int getDimLevel() { return dim; }
        public void setTemperature(double c) { temp = c; System.out.println("  🏠 Hub temp → " + c); }
        public double getTemperature() { return temp; }
        public void playAudio(String s) { audio = s; System.out.println("  🏠 Hub audio: " + s); }
        public void stopAudio() { audio = null; }
        public void lock()   { locked = true;  System.out.println("  🏠 Hub locked"); }
        public void unlock() { locked = false; System.out.println("  🏠 Hub unlocked"); }
        public boolean isLocked() { return locked; }
    }

    // ── Methods accept ONLY the capability they need ──
    static void switchAll(Switchable... devices) {
        System.out.println("  [Switching ON all switchable devices]");
        for (Switchable d : devices) d.turnOn();
    }

    static void setMoodLighting(Dimmable light, int level) {
        light.setDimLevel(level);
    }

    static void secureHome(Lockable... locks) {
        System.out.println("  [Securing home]");
        for (Lockable l : locks) l.lock();
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   🏠 ISP SMART HOME SYSTEM           ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        SmartLight living = new SmartLight("Living Room");
        SmartLight bedroom = new SmartLight("Bedroom");
        SmartThermostat thermo = new SmartThermostat();
        SmartSpeaker speaker = new SmartSpeaker();
        SmartLock frontDoor = new SmartLock("Front Door");
        SmartLock backDoor = new SmartLock("Back Door");

        System.out.println("── Morning routine ──");
        switchAll(living, bedroom, thermo, speaker);
        thermo.setTemperature(22.0);
        speaker.playAudio("Morning playlist");

        System.out.println("\n── Movie mode ──");
        setMoodLighting(living, 20);
        setMoodLighting(bedroom, 0);
        speaker.playAudio("Interstellar OST");

        System.out.println("\n── Leaving home ──");
        secureHome(frontDoor, backDoor);

        System.out.println("\n✅ Each class implements ONLY its relevant capabilities — no forced stubs!");
    }
}
