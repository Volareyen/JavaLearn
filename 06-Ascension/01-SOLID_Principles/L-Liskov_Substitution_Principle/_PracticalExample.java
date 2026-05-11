/**
 * _PracticalExample.java — LSP: Media Player Hierarchy
 *
 * A media system where every MediaPlayer subtype is safely substitutable.
 * No caller is ever surprised. Every subtype honors its supertype's contract.
 *
 * Compile & Run: javac _PracticalExample.java && java _PracticalExample
 */
import java.util.*;

public class _PracticalExample {

    // ── Base contract — all implementors MUST honor this fully ──
    interface MediaPlayer {
        void play(String filename);
        void stop();
        String getStatus();
        boolean supports(String fileExtension);
    }

    // ── All subtypes are complete, no surprises ──
    static class AudioPlayer implements MediaPlayer {
        private String current = null;
        public void play(String f) { current = f; System.out.println("  🎵 AudioPlayer playing: " + f); }
        public void stop() { System.out.println("  ⏹ AudioPlayer stopped: " + current); current = null; }
        public String getStatus() { return current != null ? "Playing: " + current : "Idle"; }
        public boolean supports(String ext) { return Set.of("mp3","wav","flac","aac").contains(ext.toLowerCase()); }
    }

    static class VideoPlayer implements MediaPlayer {
        private String current = null;
        public void play(String f) { current = f; System.out.println("  🎬 VideoPlayer playing: " + f); }
        public void stop() { System.out.println("  ⏹ VideoPlayer stopped: " + current); current = null; }
        public String getStatus() { return current != null ? "Playing: " + current : "Idle"; }
        public boolean supports(String ext) { return Set.of("mp4","avi","mkv","mov").contains(ext.toLowerCase()); }
    }

    static class StreamingPlayer implements MediaPlayer {
        private String current = null;
        public void play(String url) { current = url; System.out.println("  📡 StreamingPlayer streaming: " + url); }
        public void stop() { System.out.println("  ⏹ StreamingPlayer stopped: " + current); current = null; }
        public String getStatus() { return current != null ? "Streaming: " + current : "Disconnected"; }
        public boolean supports(String ext) { return Set.of("m3u8","hls","dash").contains(ext.toLowerCase()); }
    }

    // ── Client code — works with ANY MediaPlayer, no casting, no surprises ──
    static void playMedia(MediaPlayer player, String filename) {
        String ext = filename.contains(".") ? filename.substring(filename.lastIndexOf('.')+1) : "";
        if (player.supports(ext)) {
            player.play(filename);
            System.out.println("    Status: " + player.getStatus());
            player.stop();
        } else {
            System.out.println("  ⚠ " + player.getClass().getSimpleName() + " does not support ." + ext);
        }
    }

    static class MediaCenter {
        private List<MediaPlayer> players;
        MediaCenter(List<MediaPlayer> players) { this.players = players; }

        // Routes to the right player automatically — substitution works perfectly
        public void play(String filename) {
            String ext = filename.contains(".") ? filename.substring(filename.lastIndexOf('.')+1) : "";
            players.stream()
                .filter(p -> p.supports(ext))
                .findFirst()
                .ifPresentOrElse(
                    p -> { p.play(filename); p.stop(); },
                    () -> System.out.println("  ❌ No player supports ." + ext)
                );
        }
    }

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   🎬 LSP MEDIA PLAYER SYSTEM         ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        List<MediaPlayer> players = List.of(
            new AudioPlayer(), new VideoPlayer(), new StreamingPlayer()
        );

        // Any player can be substituted where MediaPlayer is expected
        System.out.println("── Direct substitution ──");
        players.forEach(p -> playMedia(p, "test.mp3"));

        System.out.println("\n── Smart media center routing ──");
        MediaCenter center = new MediaCenter(players);
        for (String file : List.of("concert.mp3", "movie.mp4", "live.m3u8", "unknown.xyz")) {
            System.out.print("  Routing " + file + ": ");
            center.play(file);
        }

        System.out.println("\n✅ Every subtype honored its contract — zero surprises!");
    }
}
