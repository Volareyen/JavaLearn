/**
 * PlaylistManager.java — Manages multiple playlists with cross-playlist operations
 */
import java.util.*;
import java.util.stream.Collectors;

public class PlaylistManager {
    private List<Playlist> playlists;

    public PlaylistManager() {
        this.playlists = new ArrayList<>();
    }

    public Playlist createPlaylist(String name) {
        Playlist p = new Playlist(name);
        playlists.add(p);
        return p;
    }

    public boolean deletePlaylist(String name) {
        return playlists.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }

    public Optional<Playlist> findPlaylist(String name) {
        return playlists.stream()
            .filter(p -> p.getName().equalsIgnoreCase(name))
            .findFirst();
    }

    public Playlist mergePlaylists(String name1, String name2, String newName) {
        Playlist p1 = findPlaylist(name1).orElse(null);
        Playlist p2 = findPlaylist(name2).orElse(null);
        Playlist merged = createPlaylist(newName);

        if (p1 != null) p1.getSongs().forEach(merged::addSong);
        if (p2 != null) {
            for (Song s : p2.getSongs()) {
                if (!merged.getSongs().contains(s)) {
                    merged.addSong(s);
                }
            }
        }
        return merged;
    }

    public Map<String, List<Song>> findSongAcrossAll(String title) {
        Map<String, List<Song>> results = new LinkedHashMap<>();
        for (Playlist p : playlists) {
            List<Song> matches = p.getSongs().stream()
                .filter(s -> s.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
            if (!matches.isEmpty()) {
                results.put(p.getName(), matches);
            }
        }
        return results;
    }

    public List<Song> getRecommendations(Playlist source, Playlist pool) {
        List<Song> sourceSongs = source.getSongs();
        return pool.getSongs().stream()
            .filter(s -> !sourceSongs.contains(s))
            .collect(Collectors.toList());
    }

    public void printAllPlaylists() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║      🎶 ALL PLAYLISTS               ║");
        System.out.println("╚══════════════════════════════════════╝");
        playlists.forEach(p -> { p.print(); System.out.println(); });
    }

    // ── Demo ──
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   🎵 PLAYLIST MANAGER SOLUTION      ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        PlaylistManager mgr = new PlaylistManager();

        // Create playlists
        Playlist rock = mgr.createPlaylist("Classic Rock");
        rock.addSong(new Song("Bohemian Rhapsody", "Queen", 354));
        rock.addSong(new Song("Stairway to Heaven", "Led Zeppelin", 482));
        rock.addSong(new Song("Hotel California", "Eagles", 391));
        rock.addSong(new Song("Comfortably Numb", "Pink Floyd", 382));
        rock.addSong(new Song("Sweet Child O Mine", "Guns N Roses", 356));

        Playlist indie = mgr.createPlaylist("Indie Vibes");
        indie.addSong(new Song("Somebody Told Me", "The Killers", 197));
        indie.addSong(new Song("Do I Wanna Know", "Arctic Monkeys", 272));
        indie.addSong(new Song("Bohemian Rhapsody", "Queen", 354));
        indie.addSong(new Song("Electric Feel", "MGMT", 229));
        indie.addSong(new Song("Take Me Out", "Franz Ferdinand", 237));

        Playlist chill = mgr.createPlaylist("Chill Evening");
        chill.addSong(new Song("Comfortably Numb", "Pink Floyd", 382));
        chill.addSong(new Song("Wish You Were Here", "Pink Floyd", 334));
        chill.addSong(new Song("Hallelujah", "Jeff Buckley", 412));
        chill.addSong(new Song("The Sound of Silence", "Simon & Garfunkel", 187));
        chill.addSong(new Song("Landslide", "Fleetwood Mac", 199));

        mgr.printAllPlaylists();

        // Sort demonstrations
        System.out.println("── Sorting Rock by duration ──");
        rock.sortByDuration();
        rock.print();

        System.out.println("\n── Sorting Indie by title ──");
        indie.sortByTitle();
        indie.print();

        // Move a song
        System.out.println("\n── Moving song in Chill (pos 3 → 0) ──");
        chill.moveSong(3, 0);
        chill.print();

        // Search by artist
        System.out.println("\n── Songs by Pink Floyd across all ──");
        for (Playlist p : List.of(rock, indie, chill)) {
            List<Song> pf = p.searchByArtist("Pink Floyd");
            if (!pf.isEmpty()) {
                System.out.println("  In '" + p.getName() + "': " + pf);
            }
        }

        // Merge playlists
        System.out.println("\n── Merging Rock + Chill (no duplicates) ──");
        Playlist merged = mgr.mergePlaylists("Classic Rock", "Chill Evening", "Rock & Chill");
        merged.print();

        // Find song across all
        System.out.println("\n── Finding 'Bohemian' across all playlists ──");
        Map<String, List<Song>> found = mgr.findSongAcrossAll("Bohemian");
        found.forEach((playlist, songs) -> {
            System.out.println("  📁 " + playlist + ":");
            songs.forEach(s -> System.out.println("     → " + s));
        });

        // Recommendations
        System.out.println("\n── Recommendations from Chill for Rock ──");
        List<Song> recs = mgr.getRecommendations(rock, chill);
        recs.forEach(s -> System.out.println("  💡 " + s));

        // Play next (queue behavior)
        System.out.println("\n── Playing next from Indie ──");
        Song next = indie.playNext();
        System.out.println("  ▶ Now playing: " + next);
        System.out.println("  Remaining: " + indie.size() + " songs");

        System.out.println("\n✅ Playlist Manager demonstration complete!");
    }
}
