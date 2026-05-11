# The Pupil's Trial: Sets — The Social Network Analyzer

*"Prove your mastery of uniqueness and set mathematics by building a system that analyzes social connections."*

---

## **Your Mission: Build a Social Network Analyzer**

### **Requirements**

#### **User Class**
- Fields: `username` (String), `displayName` (String)
- Proper `equals()` and `hashCode()` based on `username`

#### **SocialNetwork Class**
- Stores users in a `Set<User>`
- Stores friendships as a `Map<String, Set<String>>` (username → set of friend usernames)
- Methods:
  1. `addUser(User user)` — add user (reject duplicates)
  2. `addFriendship(String user1, String user2)` — mutual friendship
  3. `removeFriendship(String user1, String user2)` — remove connection
  4. `getFriends(String username)` — return Set of friends
  5. `getMutualFriends(String user1, String user2)` — intersection of friend sets
  6. `getFriendSuggestions(String username)` — friends-of-friends who aren't already friends
  7. `getIsolatedUsers()` — users with zero friends
  8. `getMostConnected()` — user with the most friends
  9. `getConnectionDegree(String user1, String user2)` — are they friends (1), friends-of-friends (2), or unconnected (0)?
  10. `printNetworkStats()` — total users, total connections, avg friends per user

### **Demo Scenario**
1. Create 8+ users
2. Establish 10+ friendships
3. Find mutual friends between two users
4. Get friend suggestions for a user
5. Find isolated users
6. Print full network statistics

---

*"In the realm of social connections, Sets reveal their true power — mutual friends are intersections, suggestions are differences, and isolated users are the complement. Show me this mathematical beauty."*
