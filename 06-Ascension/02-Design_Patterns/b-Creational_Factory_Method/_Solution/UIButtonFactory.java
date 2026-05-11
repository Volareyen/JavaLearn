/**
 * UIButtonFactory.java — Factory Method Solution
 *
 * Cross-platform UI factory. Application code never knows which platform it's on.
 * Run: javac UIButtonFactory.java && java UIButtonFactory
 */
public class UIButtonFactory {

    interface Button {
        void render();
        void onClick();
    }

    static class WindowsButton implements Button {
        public void render()  { System.out.println("  [Windows] Rendering rectangular button with Win32 border"); }
        public void onClick() { System.out.println("  [Windows] Click! Win32 event dispatched"); }
    }

    static class MacButton implements Button {
        public void render()  { System.out.println("  [Mac] Rendering rounded Aqua-style button"); }
        public void onClick() { System.out.println("  [Mac] Click! NSButton action fired"); }
    }

    static class LinuxButton implements Button {
        public void render()  { System.out.println("  [Linux] Rendering GTK flat button"); }
        public void onClick() { System.out.println("  [Linux] Click! GTK signal emitted"); }
    }

    // Abstract creator
    static abstract class UIFactory {
        protected abstract Button createButton(); // Factory Method

        public void renderUI() {
            System.out.println("  Building UI...");
            Button btn = createButton();
            btn.render();
            btn.onClick();
        }
    }

    static class WindowsUIFactory extends UIFactory {
        protected Button createButton() { return new WindowsButton(); }
    }
    static class MacUIFactory extends UIFactory {
        protected Button createButton() { return new MacButton(); }
    }
    static class LinuxUIFactory extends UIFactory {
        protected Button createButton() { return new LinuxButton(); }
    }

    static UIFactory detectPlatform(String os) {
        return switch (os.toLowerCase()) {
            case "windows" -> new WindowsUIFactory();
            case "mac"     -> new MacUIFactory();
            case "linux"   -> new LinuxUIFactory();
            default -> throw new IllegalArgumentException("Unknown OS: " + os);
        };
    }

    public static void main(String[] args) {
        System.out.println("=== FACTORY METHOD SOLUTION: CROSS-PLATFORM UI ===\n");
        for (String os : new String[]{"windows", "mac", "linux"}) {
            System.out.println("── Platform: " + os.toUpperCase() + " ──");
            detectPlatform(os).renderUI();
            System.out.println();
        }
        System.out.println("Factory Method ✅ — same renderUI() code, 3 completely different outputs");
    }
}
