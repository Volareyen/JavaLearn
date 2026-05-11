# The Pupil's Trial: Lists — The Playlist Manager

*"You have studied the scrolls of ordered collections. Now prove your mastery by building a system that manages music playlists — a perfect domain for List operations."*

---

## **Your Mission: Build a Playlist Management System**

Create a `PlaylistManager` system that manages multiple playlists of songs using `List` operations.

### **Requirements**

#### **Song Class**
- Fields: `title` (String), `artist` (String), `durationSeconds` (int)
- A `toString()` that shows: `"Title - Artist (M:SS)"`

#### **Playlist Class**
- Stores songs in an `ArrayList<Song>`
- Has a `name` field
- Methods:
  1. `addSong(Song song)` — add to end
  2. `addSongAt(int position, Song song)` — insert at position
  3. `removeSong(String title)` — remove first song matching title
  4. `moveSong(int from, int to)` — move a song from one position to another
  5. `shuffle()` — randomize the order
  6. `sortByTitle()` — sort alphabetically by title
  7. `sortByDuration()` — sort by duration (shortest first)
  8. `getTotalDuration()` — return total duration in "H:MM:SS" format
  9. `searchByArtist(String artist)` — return a List of all songs by that artist
  10. `getSubPlaylist(int from, int to)` — return a new Playlist with songs in range

#### **PlaylistManager Class**
- Stores multiple playlists in a `List<Playlist>`
- Methods:
  1. `createPlaylist(String name)` — create and add a new playlist
  2. `deletePlaylist(String name)` — remove a playlist by name
  3. `mergePlayists(String name1, String name2, String newName)` — combine two playlists into a new one (no duplicates)
  4. `findSongAcrossAll(String title)` — find which playlists contain a given song
  5. `printAllPlaylists()` — display all playlists with their songs

### **Demo Scenario**

In your `main` method:
1. Create at least 3 playlists with 5+ songs each
2. Demonstrate adding, removing, and moving songs
3. Show sorting by title and by duration
4. Merge two playlists
5. Search for an artist across all playlists
6. Print the total duration of each playlist

### **Bonus Challenges** ⭐
- Add a `getRecommendations(Playlist source, Playlist pool)` method that finds songs in `pool` not already in `source`
- Add a `removeDuplicates()` method that removes duplicate songs from a playlist
- Implement `Queue`-like behavior: `playNext()` removes and returns the first song

---

*"A true artisan of Lists can insert, remove, search, and sort with the confidence of a master librarian managing an infinite scroll rack. Show me that you are that artisan."*
