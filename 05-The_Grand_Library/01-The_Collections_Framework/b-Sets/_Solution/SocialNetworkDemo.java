/**
 * SocialNetworkDemo.java — Complete Social Network Analyzer Solution
 * 
 * Compile & Run: javac SocialNetworkDemo.java && java SocialNetworkDemo
 */
import java.util.*;
import java.util.stream.Collectors;

public class SocialNetworkDemo {

    // ── User ──
    static class User {
        private String username;
        private String displayName;

        public User(String username, String displayName) {
            this.username = username;
            this.displayName = displayName;
        }
        public String getUsername() { return username; }
        public String getDisplayName() { return displayName; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;
            return username.equalsIgnoreCase(((User) o).username);
        }
        @Override
        public int hashCode() { return Objects.hash(username.toLowerCase()); }
        @Override
        public String toString() { return displayName + " (@" + username + ")"; }
    }

    // ── Social Network ──
    static class SocialNetwork {
        private Set<User> users = new LinkedHashSet<>();
        private Map<String, Set<String>> friendships = new HashMap<>();

        public boolean addUser(User user) {
            if (users.add(user)) {
                friendships.putIfAbsent(user.getUsername(), new LinkedHashSet<>());
                return true;
            }
            return false;
        }

        public void addFriendship(String u1, String u2) {
            friendships.computeIfAbsent(u1, k -> new LinkedHashSet<>()).add(u2);
            friendships.computeIfAbsent(u2, k -> new LinkedHashSet<>()).add(u1);
        }

        public void removeFriendship(String u1, String u2) {
            friendships.getOrDefault(u1, Collections.emptySet()).remove(u2);
            friendships.getOrDefault(u2, Collections.emptySet()).remove(u1);
        }

        public Set<String> getFriends(String username) {
            return new HashSet<>(friendships.getOrDefault(username, Collections.emptySet()));
        }

        public Set<String> getMutualFriends(String u1, String u2) {
            Set<String> mutual = getFriends(u1);
            mutual.retainAll(getFriends(u2));
            return mutual;
        }

        public Set<String> getFriendSuggestions(String username) {
            Set<String> friends = getFriends(username);
            Set<String> suggestions = new LinkedHashSet<>();
            for (String friend : friends) {
                for (String fof : getFriends(friend)) {
                    if (!fof.equals(username) && !friends.contains(fof)) {
                        suggestions.add(fof);
                    }
                }
            }
            return suggestions;
        }

        public Set<String> getIsolatedUsers() {
            return users.stream()
                .map(User::getUsername)
                .filter(u -> friendships.getOrDefault(u, Collections.emptySet()).isEmpty())
                .collect(Collectors.toCollection(LinkedHashSet::new));
        }

        public Optional<User> getMostConnected() {
            return users.stream()
                .max(Comparator.comparingInt(u -> getFriends(u.getUsername()).size()));
        }

        public int getConnectionDegree(String u1, String u2) {
            if (getFriends(u1).contains(u2)) return 1;
            if (!getMutualFriends(u1, u2).isEmpty() || !getFriendSuggestions(u1).isEmpty()
                && getFriendSuggestions(u1).contains(u2)) return 2;
            return 0;
        }

        public void printNetworkStats() {
            int totalConnections = friendships.values().stream().mapToInt(Set::size).sum() / 2;
            double avgFriends = users.isEmpty() ? 0 :
                (double) friendships.values().stream().mapToInt(Set::size).sum() / users.size();

            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║      📊 NETWORK STATISTICS          ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.printf("║ Total Users:       %d%n", users.size());
            System.out.printf("║ Total Connections: %d%n", totalConnections);
            System.out.printf("║ Avg Friends/User:  %.1f%n", avgFriends);
            System.out.printf("║ Isolated Users:    %d%n", getIsolatedUsers().size());
            getMostConnected().ifPresent(u ->
                System.out.printf("║ Most Connected:    %s (%d friends)%n",
                    u.getDisplayName(), getFriends(u.getUsername()).size()));
            System.out.println("╚══════════════════════════════════════╝");
        }
    }

    // ── Demo ──
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   🌐 SOCIAL NETWORK ANALYZER        ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        SocialNetwork net = new SocialNetwork();

        // Add users
        net.addUser(new User("alice", "Alice W."));
        net.addUser(new User("bob", "Bob S."));
        net.addUser(new User("charlie", "Charlie B."));
        net.addUser(new User("diana", "Diana P."));
        net.addUser(new User("eve", "Eve R."));
        net.addUser(new User("frank", "Frank M."));
        net.addUser(new User("grace", "Grace H."));
        net.addUser(new User("hank", "Hank T.")); // will be isolated

        // Add friendships
        net.addFriendship("alice", "bob");
        net.addFriendship("alice", "charlie");
        net.addFriendship("alice", "diana");
        net.addFriendship("bob", "charlie");
        net.addFriendship("bob", "eve");
        net.addFriendship("charlie", "diana");
        net.addFriendship("charlie", "frank");
        net.addFriendship("diana", "eve");
        net.addFriendship("eve", "grace");
        net.addFriendship("frank", "grace");

        // Duplicate rejection
        System.out.println("Duplicate user test: " + net.addUser(new User("alice", "Alice Dup")));

        // Mutual friends
        System.out.println("\n── Mutual Friends (Alice & Bob) ──");
        System.out.println("  " + net.getMutualFriends("alice", "bob"));

        System.out.println("\n── Mutual Friends (Alice & Eve) ──");
        System.out.println("  " + net.getMutualFriends("alice", "eve"));

        // Friend suggestions
        System.out.println("\n── Friend Suggestions for Alice ──");
        System.out.println("  " + net.getFriendSuggestions("alice"));

        // Isolated users
        System.out.println("\n── Isolated Users ──");
        System.out.println("  " + net.getIsolatedUsers());

        // Connection degrees
        System.out.println("\n── Connection Degrees ──");
        System.out.println("  Alice↔Bob: " + net.getConnectionDegree("alice", "bob") + " (direct)");
        System.out.println("  Alice↔Eve: " + net.getConnectionDegree("alice", "eve") + " (friend-of-friend)");
        System.out.println("  Alice↔Hank: " + net.getConnectionDegree("alice", "hank") + " (unconnected)");

        // Full stats
        net.printNetworkStats();

        System.out.println("\n✅ Social Network Analyzer complete!");
    }
}
