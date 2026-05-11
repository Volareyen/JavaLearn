/**
 * Playlist.java — Ordered song collection with full List operations
 */
import java.util.*;
import java.util.stream.Collectors;

public class Playlist {
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getName() { return name; }
    public List<Song> getSongs() { return new ArrayList<>(songs); }
    public int size() { return songs.size(); }

    // ── Core operations ──
    public void addSong(Song song) { songs.add(song); }

    public void addSongAt(int position, Song song) {
        if (position < 0 || position > songs.size()) {
            System.out.println("  ⚠ Invalid position: " + position);
            return;
        }
        songs.add(position, song);
    }

    public boolean removeSong(String title) {
        return songs.removeIf(s -> s.getTitle().equalsIgnoreCase(title));
    }

    public void moveSong(int from, int to) {
        if (from < 0 || from >= songs.size() || to < 0 || to >= songs.size()) {
            System.out.println("  ⚠ Invalid move indices");
            return;
        }
        Song song = songs.remove(from);
        songs.add(to, song);
    }

    // ── Ordering ──
    public void shuffle() { Collections.shuffle(songs); }
    public void sortByTitle() { songs.sort(Comparator.comparing(Song::getTitle, String.CASE_INSENSITIVE_ORDER)); }
    public void sortByDuration() { songs.sort(Comparator.comparingInt(Song::getDurationSeconds)); }

    // ── Queries ──
    public String getTotalDuration() {
        int total = songs.stream().mapToInt(Song::getDurationSeconds).sum();
        int h = total / 3600, m = (total % 3600) / 60, s = total % 60;
        return String.format("%d:%02d:%02d", h, m, s);
    }

    public List<Song> searchByArtist(String artist) {
        return songs.stream()
            .filter(s -> s.getArtist().equalsIgnoreCase(artist))
            .collect(Collectors.toList());
    }

    public Playlist getSubPlaylist(int from, int to) {
        Playlist sub = new Playlist(name + " (subset)");
        List<Song> subList = songs.subList(
            Math.max(0, from), Math.min(songs.size(), to));
        subList.forEach(sub::addSong);
        return sub;
    }

    public void removeDuplicates() {
        List<Song> unique = new ArrayList<>();
        for (Song s : songs) {
            if (!unique.contains(s)) unique.add(s);
        }
        songs = unique;
    }

    public Song playNext() {
        if (songs.isEmpty()) return null;
        return songs.remove(0);
    }

    public void print() {
        System.out.println("🎵 " + name + " [" + songs.size() + " songs, " + getTotalDuration() + "]");
        for (int i = 0; i < songs.size(); i++) {
            System.out.printf("  %2d. %s%n", i + 1, songs.get(i));
        }
    }
}
