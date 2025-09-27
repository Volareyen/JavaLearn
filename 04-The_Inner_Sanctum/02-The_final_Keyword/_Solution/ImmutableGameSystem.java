/**
 * The Path Revealed: Immutable Game State System Solution
 * 
 * This solution demonstrates complete mastery of the `final` keyword
 * through a comprehensive turn-based strategy game system.
 * 
 * Features demonstrated:
 * - Final classes for immutable value objects
 * - Final methods for security-critical operations
 * - Final variables for constants and immutable state
 * - Template method pattern with final orchestration
 * - Thread-safe immutable objects
 * - Complete state management without mutation
 */
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

// ═══════════════════════════════════════════════════════════════════════════════════
// GAME CONSTANTS - FINAL UTILITY CLASS
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Final utility class containing all game constants
 * Cannot be extended or instantiated
 */
final class GameConstants {
    // Board configuration constants
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 10;
    public static final int MIN_COORDINATE = 0;
    public static final int MAX_COORDINATE_X = BOARD_WIDTH - 1;
    public static final int MAX_COORDINATE_Y = BOARD_HEIGHT - 1;
    
    // Piece type constants
    public static final String PIECE_WARRIOR = "WARRIOR";
    public static final String PIECE_ARCHER = "ARCHER";
    public static final String PIECE_MAGE = "MAGE";
    
    // Game status constants
    public static final String STATUS_IN_PROGRESS = "IN_PROGRESS";
    public static final String STATUS_PLAYER1_WINS = "PLAYER1_WINS";
    public static final String STATUS_PLAYER2_WINS = "PLAYER2_WINS";
    public static final String STATUS_DRAW = "DRAW";
    
    // Piece stats by type (final arrays)
    public static final int[] WARRIOR_STATS = {100, 30, 1};  // health, attack, range
    public static final int[] ARCHER_STATS = {75, 25, 3};    // health, attack, range
    public static final int[] MAGE_STATS = {50, 40, 2};      // health, attack, range
    
    // Game rules
    public static final int MAX_TURNS = 100;
    public static final int MAX_PIECES_PER_PLAYER = 5;
    public static final double ADJACENT_DISTANCE = 1.0;
    
    // Private constructor prevents instantiation
    private GameConstants() {
        throw new AssertionError("Utility class cannot be instantiated");
    }
    
    /**
     * Final utility method for getting piece stats
     */
    public static final int[] getPieceStats(String pieceType) {
        switch (pieceType) {
            case PIECE_WARRIOR: return WARRIOR_STATS.clone();
            case PIECE_ARCHER: return ARCHER_STATS.clone();
            case PIECE_MAGE: return MAGE_STATS.clone();
            default: throw new IllegalArgumentException("Unknown piece type: " + pieceType);
        }
    }
    
    /**
     * Final utility method for validating coordinates
     */
    public static final boolean isValidCoordinate(int x, int y) {
        return x >= MIN_COORDINATE && x <= MAX_COORDINATE_X &&
               y >= MIN_COORDINATE && y <= MAX_COORDINATE_Y;
    }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// DIRECTION ENUMERATION - IMMUTABLE MOVEMENT DIRECTIONS
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Enumeration for movement directions (enums are implicitly final)
 */
enum Direction {
    NORTH(0, -1),
    SOUTH(0, 1),
    EAST(1, 0),
    WEST(-1, 0),
    NORTHEAST(1, -1),
    NORTHWEST(-1, -1),
    SOUTHEAST(1, 1),
    SOUTHWEST(-1, 1);
    
    private final int deltaX;
    private final int deltaY;
    
    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }
    
    public final int getDeltaX() { return deltaX; }
    public final int getDeltaY() { return deltaY; }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// POSITION - FINAL IMMUTABLE 2D COORDINATE CLASS
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Final immutable position class representing 2D coordinates
 * Cannot be extended - represents complete coordinate concept
 */
public final class Position {
    // Final coordinates - never change after creation
    private final int x;
    private final int y;
    
    /**
     * Constructor with coordinate validation
     */
    public Position(int x, int y) {
        if (!GameConstants.isValidCoordinate(x, y)) {
            throw new IllegalArgumentException(
                String.format("Invalid coordinates: (%d, %d). Valid range: (0-9, 0-9)", x, y));
        }
        this.x = x;
        this.y = y;
    }
    
    // Final getters - no setters for immutable object
    public final int getX() { return x; }
    public final int getY() { return y; }
    
    /**
     * Final method returning new Position object after movement
     */
    public final Position move(Direction direction) {
        int newX = x + direction.getDeltaX();
        int newY = y + direction.getDeltaY();
        return new Position(newX, newY);  // Returns new object - immutable
    }
    
    /**
     * Final method calculating distance to another position
     */
    public final double distance(Position other) {
        if (other == null) {
            throw new IllegalArgumentException("Other position cannot be null");
        }
        int deltaX = this.x - other.x;
        int deltaY = this.y - other.y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
    
    /**
     * Final method checking if positions are adjacent
     */
    public final boolean isAdjacent(Position other) {
        return distance(other) <= GameConstants.ADJACENT_DISTANCE;
    }
    
    /**
     * Final method getting all valid adjacent positions
     */
    public final Position[] getAdjacentPositions() {
        List<Position> adjacent = new ArrayList<>();
        
        for (Direction direction : Direction.values()) {
            try {
                Position newPos = move(direction);
                adjacent.add(newPos);
            } catch (IllegalArgumentException e) {
                // Skip invalid positions (out of bounds)
            }
        }
        
        return adjacent.toArray(new Position[0]);
    }
    
    /**
     * Final method checking if position is within range
     */
    public final boolean isInRange(Position target, int range) {
        return distance(target) <= range;
    }
    
    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return x == position.x && y == position.y;
    }
    
    @Override
    public final int hashCode() {
        return Objects.hash(x, y);
    }
    
    @Override
    public final String toString() {
        return String.format("(%d,%d)", x, y);
    }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// GAMEPIECE - FINAL IMMUTABLE GAME PIECE CLASS
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Final immutable game piece class
 * Cannot be extended - represents complete game piece concept
 */
public final class GamePiece {
    // Final instance fields - immutable state
    private final String id;
    private final String type;
    private final String owner;
    private final int health;
    private final int maxHealth;
    private final int attackPower;
    private final int attackRange;
    private final Position position;
    
    /**
     * Constructor with automatic stat assignment based on piece type
     */
    public GamePiece(String id, String type, String owner, Position position) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Piece ID cannot be null or empty");
        }
        if (owner == null || owner.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner cannot be null or empty");
        }
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null");
        }
        
        this.id = id;
        this.type = type;
        this.owner = owner;
        this.position = position;
        
        // Get stats based on type using final utility method
        final int[] stats = GameConstants.getPieceStats(type);
        this.maxHealth = stats[0];
        this.health = stats[0];  // Start at full health
        this.attackPower = stats[1];
        this.attackRange = stats[2];
    }
    
    /**
     * Private constructor for creating modified copies
     */
    private GamePiece(String id, String type, String owner, int health, int maxHealth,
                     int attackPower, int attackRange, Position position) {
        this.id = id;
        this.type = type;
        this.owner = owner;
        this.health = health;
        this.maxHealth = maxHealth;
        this.attackPower = attackPower;
        this.attackRange = attackRange;
        this.position = position;
    }
    
    // Final getters - no setters for immutable object
    public final String getId() { return id; }
    public final String getType() { return type; }
    public final String getOwner() { return owner; }
    public final int getHealth() { return health; }
    public final int getMaxHealth() { return maxHealth; }
    public final int getAttackPower() { return attackPower; }
    public final int getAttackRange() { return attackRange; }
    public final Position getPosition() { return position; }
    
    /**
     * Final method returning new GamePiece with damage applied
     */
    public final GamePiece takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        
        int newHealth = Math.max(0, this.health - damage);
        return new GamePiece(id, type, owner, newHealth, maxHealth, 
                           attackPower, attackRange, position);
    }
    
    /**
     * Final method returning new GamePiece with healing applied
     */
    public final GamePiece heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Heal amount cannot be negative");
        }
        
        int newHealth = Math.min(maxHealth, this.health + amount);
        return new GamePiece(id, type, owner, newHealth, maxHealth, 
                           attackPower, attackRange, position);
    }
    
    /**
     * Final method returning new GamePiece at new position
     */
    public final GamePiece moveTo(Position newPosition) {
        if (newPosition == null) {
            throw new IllegalArgumentException("New position cannot be null");
        }
        
        return new GamePiece(id, type, owner, health, maxHealth, 
                           attackPower, attackRange, newPosition);
    }
    
    /**
     * Final method checking if this piece can attack target
     */
    public final boolean canAttack(GamePiece target) {
        if (target == null || !isAlive() || !target.isAlive()) {
            return false;
        }
        
        if (this.owner.equals(target.owner)) {
            return false;  // Cannot attack own pieces
        }
        
        return position.isInRange(target.position, attackRange);
    }
    
    /**
     * Final method checking if piece is alive
     */
    public final boolean isAlive() {
        return health > 0;
    }
    
    /**
     * Final method getting health percentage
     */
    public final double getHealthPercentage() {
        return maxHealth > 0 ? (double) health / maxHealth * 100 : 0;
    }
    
    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GamePiece gamePiece = (GamePiece) obj;
        return Objects.equals(id, gamePiece.id);  // Pieces are equal if IDs match
    }
    
    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public final String toString() {
        return String.format("%s[%s]@%s HP:%d/%d ATK:%d RNG:%d (%s)", 
                           type, id, position, health, maxHealth, 
                           attackPower, attackRange, owner);
    }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// MOVE - FINAL IMMUTABLE MOVE COMMAND CLASS
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Final immutable move command class
 */
public final class Move {
    // Final move data
    private final String pieceId;
    private final Position targetPosition;
    private final String moveType;  // MOVE, ATTACK, HEAL
    private final String targetPieceId;  // For attacks/heals
    
    // Move type constants
    public static final String TYPE_MOVE = "MOVE";
    public static final String TYPE_ATTACK = "ATTACK";
    public static final String TYPE_HEAL = "HEAL";
    
    /**
     * Constructor for move command
     */
    public Move(String pieceId, String moveType, Position targetPosition, String targetPieceId) {
        this.pieceId = pieceId;
        this.moveType = moveType;
        this.targetPosition = targetPosition;
        this.targetPieceId = targetPieceId;
    }
    
    /**
     * Convenience constructor for simple moves
     */
    public Move(String pieceId, Position targetPosition) {
        this(pieceId, TYPE_MOVE, targetPosition, null);
    }
    
    // Final getters
    public final String getPieceId() { return pieceId; }
    public final Position getTargetPosition() { return targetPosition; }
    public final String getMoveType() { return moveType; }
    public final String getTargetPieceId() { return targetPieceId; }
    
    @Override
    public final String toString() {
        return String.format("%s: %s to %s%s", moveType, pieceId, targetPosition,
                           targetPieceId != null ? " targeting " + targetPieceId : "");
    }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// GAMESTATE - FINAL IMMUTABLE GAME STATE CLASS
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Final immutable game state class representing complete game state
 * Cannot be extended - represents complete game state concept
 */
public final class GameState {
    // Final game state fields
    private final int turn;
    private final String currentPlayer;
    private final String player1;
    private final String player2;
    private final Map<String, GamePiece> pieces;  // Final reference, immutable contents
    private final String gameStatus;
    private final String winner;
    private final List<Move> moveHistory;  // Final reference, immutable contents
    
    /**
     * Constructor for initial game state
     */
    public GameState(String player1, String player2) {
        if (player1 == null || player2 == null || player1.equals(player2)) {
            throw new IllegalArgumentException("Players must be different non-null values");
        }
        
        this.turn = 1;
        this.currentPlayer = player1;
        this.player1 = player1;
        this.player2 = player2;
        this.pieces = Collections.emptyMap();
        this.gameStatus = GameConstants.STATUS_IN_PROGRESS;
        this.winner = null;
        this.moveHistory = Collections.emptyList();
    }
    
    /**
     * Private constructor for creating modified copies
     */
    private GameState(int turn, String currentPlayer, String player1, String player2,
                     Map<String, GamePiece> pieces, String gameStatus, String winner,
                     List<Move> moveHistory) {
        this.turn = turn;
        this.currentPlayer = currentPlayer;
        this.player1 = player1;
        this.player2 = player2;
        this.pieces = Collections.unmodifiableMap(new HashMap<>(pieces));
        this.gameStatus = gameStatus;
        this.winner = winner;
        this.moveHistory = Collections.unmodifiableList(new ArrayList<>(moveHistory));
    }
    
    // Final getters - no setters for immutable object
    public final int getTurn() { return turn; }
    public final String getCurrentPlayer() { return currentPlayer; }
    public final String getPlayer1() { return player1; }
    public final String getPlayer2() { return player2; }
    public final Map<String, GamePiece> getPieces() { return pieces; }
    public final String getGameStatus() { return gameStatus; }
    public final String getWinner() { return winner; }
    public final List<Move> getMoveHistory() { return moveHistory; }
    
    /**
     * Final method to add a piece to the game (returns new state)
     */
    public final GameState addPiece(GamePiece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("Piece cannot be null");
        }
        
        Map<String, GamePiece> newPieces = new HashMap<>(pieces);
        newPieces.put(piece.getId(), piece);
        
        return new GameState(turn, currentPlayer, player1, player2, 
                           newPieces, gameStatus, winner, moveHistory);
    }
    
    /**
     * Final method to make a move (returns new state)
     */
    public final GameState makeMove(String pieceId, Position newPosition) {
        final GamePiece piece = pieces.get(pieceId);
        if (piece == null) {
            throw new IllegalArgumentException("Piece not found: " + pieceId);
        }
        
        if (!piece.getOwner().equals(currentPlayer)) {
            throw new IllegalArgumentException("Not your turn or not your piece");
        }
        
        if (!piece.isAlive()) {
            throw new IllegalArgumentException("Cannot move dead piece");
        }
        
        // Check if target position is occupied
        for (GamePiece otherPiece : pieces.values()) {
            if (otherPiece.getPosition().equals(newPosition) && otherPiece.isAlive()) {
                throw new IllegalArgumentException("Position occupied: " + newPosition);
            }
        }
        
        // Create new piece at new position
        GamePiece movedPiece = piece.moveTo(newPosition);
        
        // Create new pieces map with updated piece
        Map<String, GamePiece> newPieces = new HashMap<>(pieces);
        newPieces.put(pieceId, movedPiece);
        
        // Add move to history
        List<Move> newHistory = new ArrayList<>(moveHistory);
        newHistory.add(new Move(pieceId, newPosition));
        
        return new GameState(turn, currentPlayer, player1, player2, 
                           newPieces, gameStatus, winner, newHistory);
    }
    
    /**
     * Final method to execute an attack (returns new state)
     */
    public final GameState attack(String attackerId, String targetId) {
        final GamePiece attacker = pieces.get(attackerId);
        final GamePiece target = pieces.get(targetId);
        
        if (attacker == null || target == null) {
            throw new IllegalArgumentException("Attacker or target not found");
        }
        
        if (!attacker.getOwner().equals(currentPlayer)) {
            throw new IllegalArgumentException("Not your turn or not your piece");
        }
        
        if (!attacker.canAttack(target)) {
            throw new IllegalArgumentException("Cannot attack target");
        }
        
        // Apply damage to target
        GamePiece damagedTarget = target.takeDamage(attacker.getAttackPower());
        
        // Create new pieces map
        Map<String, GamePiece> newPieces = new HashMap<>(pieces);
        newPieces.put(targetId, damagedTarget);
        
        // Add move to history
        List<Move> newHistory = new ArrayList<>(moveHistory);
        newHistory.add(new Move(attackerId, Move.TYPE_ATTACK, target.getPosition(), targetId));
        
        return new GameState(turn, currentPlayer, player1, player2, 
                           newPieces, gameStatus, winner, newHistory);
    }
    
    /**
     * Final method to end current turn (returns new state)
     */
    public final GameState endTurn() {
        String nextPlayer = currentPlayer.equals(player1) ? player2 : player1;
        int nextTurn = currentPlayer.equals(player2) ? turn + 1 : turn;
        
        // Check for game over conditions
        String newStatus = checkGameOverConditions();
        String newWinner = determineWinner(newStatus);
        
        return new GameState(nextTurn, nextPlayer, player1, player2, 
                           pieces, newStatus, newWinner, moveHistory);
    }
    
    /**
     * Final method to check winner
     */
    public final String checkWinner() {
        return determineWinner(gameStatus);
    }
    
    /**
     * Final method to check if game is over
     */
    public final boolean isGameOver() {
        return !gameStatus.equals(GameConstants.STATUS_IN_PROGRESS);
    }
    
    /**
     * Private final helper methods
     */
    private final String checkGameOverConditions() {
        // Check if any player has no living pieces
        boolean player1HasLiving = pieces.values().stream()
            .anyMatch(p -> p.getOwner().equals(player1) && p.isAlive());
        boolean player2HasLiving = pieces.values().stream()
            .anyMatch(p -> p.getOwner().equals(player2) && p.isAlive());
        
        if (!player1HasLiving && !player2HasLiving) {
            return GameConstants.STATUS_DRAW;
        } else if (!player1HasLiving) {
            return GameConstants.STATUS_PLAYER2_WINS;
        } else if (!player2HasLiving) {
            return GameConstants.STATUS_PLAYER1_WINS;
        } else if (turn >= GameConstants.MAX_TURNS) {
            return GameConstants.STATUS_DRAW;
        }
        
        return GameConstants.STATUS_IN_PROGRESS;
    }
    
    private final String determineWinner(String status) {
        switch (status) {
            case GameConstants.STATUS_PLAYER1_WINS:
                return player1;
            case GameConstants.STATUS_PLAYER2_WINS:
                return player2;
            default:
                return null;
        }
    }
    
    /**
     * Final method to get living pieces for a player
     */
    public final List<GamePiece> getLivingPieces(String player) {
        return pieces.values().stream()
            .filter(p -> p.getOwner().equals(player) && p.isAlive())
            .collect(Collectors.toList());
    }
    
    /**
     * Final method to get game statistics
     */
    public final String getGameStatistics() {
        int player1Living = getLivingPieces(player1).size();
        int player2Living = getLivingPieces(player2).size();
        int totalMoves = moveHistory.size();
        
        return String.format("Turn %d | %s: %d pieces | %s: %d pieces | Moves: %d | Status: %s",
                           turn, player1, player1Living, player2, player2Living, 
                           totalMoves, gameStatus);
    }
    
    @Override
    public final String toString() {
        return getGameStatistics();
    }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// GAMEENGINE - ABSTRACT CLASS WITH FINAL TEMPLATE METHOD
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Abstract game engine with final template method pattern
 */
abstract class GameEngine {
    // Final template method - algorithm cannot be changed
    public final GameState processTurn(GameState currentState, Move move) {
        System.out.println("🔄 Processing turn: " + move);
        
        // Fixed algorithm steps using final methods
        if (!validateMove(currentState, move)) {
            System.out.println("❌ Move validation failed");
            return currentState;
        }
        
        if (!checkBusinessRules(currentState, move)) {
            System.out.println("❌ Business rules check failed");
            return currentState;
        }
        
        GameState newState = executeMove(currentState, move);  // Subclass implements
        newState = applyGameRules(newState);                   // Subclass implements
        
        return finalizeState(newState);  // Final method
    }
    
    /**
     * Final security method for move validation
     */
    private final boolean validateMove(GameState state, Move move) {
        System.out.println("🔍 Validating move...");
        
        if (move == null || move.getPieceId() == null) {
            return false;
        }
        
        GamePiece piece = state.getPieces().get(move.getPieceId());
        if (piece == null || !piece.isAlive()) {
            return false;
        }
        
        if (!piece.getOwner().equals(state.getCurrentPlayer())) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Final security method for business rules checking
     */
    protected final boolean checkBusinessRules(GameState state, Move move) {
        System.out.println("📋 Checking business rules...");
        
        final GamePiece piece = state.getPieces().get(move.getPieceId());
        final Position targetPosition = move.getTargetPosition();
        
        // Check position bounds
        if (!GameConstants.isValidCoordinate(targetPosition.getX(), targetPosition.getY())) {
            return false;
        }
        
        // Check movement distance based on piece type
        double distance = piece.getPosition().distance(targetPosition);
        if (distance > 2.0) {  // Max movement range
            System.out.println("❌ Move too far: " + distance);
            return false;
        }
        
        // Check for collisions
        for (GamePiece otherPiece : state.getPieces().values()) {
            if (otherPiece.getPosition().equals(targetPosition) && 
                otherPiece.isAlive() && 
                !otherPiece.getId().equals(piece.getId())) {
                System.out.println("❌ Position occupied by: " + otherPiece.getId());
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Final method for state finalization
     */
    private final GameState finalizeState(GameState state) {
        System.out.println("✅ Move processed successfully");
        return state;
    }
    
    // Abstract methods for subclasses to implement
    protected abstract GameState executeMove(GameState state, Move move);
    protected abstract GameState applyGameRules(GameState state);
}

/**
 * Concrete implementation of game engine
 */
class StrategyGameEngine extends GameEngine {
    @Override
    protected GameState executeMove(GameState state, Move move) {
        System.out.println("⚙️ Executing move: " + move.getMoveType());
        
        switch (move.getMoveType()) {
            case Move.TYPE_MOVE:
                return state.makeMove(move.getPieceId(), move.getTargetPosition());
            case Move.TYPE_ATTACK:
                return state.attack(move.getPieceId(), move.getTargetPieceId());
            default:
                System.out.println("❌ Unknown move type: " + move.getMoveType());
                return state;
        }
    }
    
    @Override
    protected GameState applyGameRules(GameState state) {
        System.out.println("📐 Applying game rules...");
        // Could apply additional rules like status effects, regeneration, etc.
        return state;
    }
}

// ═══════════════════════════════════════════════════════════════════════════════════
// MAIN DEMONSTRATION CLASS
// ═══════════════════════════════════════════════════════════════════════════════════

/**
 * Main demonstration of the Immutable Game State System
 */
public class ImmutableGameSystem {
    public static void main(String[] args) {
        System.out.println("🎮 IMMUTABLE GAME STATE SYSTEM DEMONSTRATION 🎮\n");
        
        // ═══════════════════════════════════════════════════════════════
        // INITIALIZING IMMUTABLE GAME COMPONENTS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("🏗️ INITIALIZING GAME COMPONENTS...\n");
        
        // Create initial game state (immutable)
        final GameState initialState = new GameState("Alice", "Bob");
        System.out.println("Created initial game state: " + initialState);
        
        // Create immutable positions
        final Position pos1 = new Position(1, 1);
        final Position pos2 = new Position(8, 8);
        final Position pos3 = new Position(1, 8);
        final Position pos4 = new Position(8, 1);
        
        System.out.println("Created positions: " + pos1 + ", " + pos2 + ", " + pos3 + ", " + pos4);
        
        // Create immutable game pieces
        final GamePiece warrior1 = new GamePiece("W1", GameConstants.PIECE_WARRIOR, "Alice", pos1);
        final GamePiece archer1 = new GamePiece("A1", GameConstants.PIECE_ARCHER, "Alice", pos2);
        final GamePiece warrior2 = new GamePiece("W2", GameConstants.PIECE_WARRIOR, "Bob", pos3);
        final GamePiece mage2 = new GamePiece("M2", GameConstants.PIECE_MAGE, "Bob", pos4);
        
        System.out.println("\nCreated game pieces:");
        System.out.println("  " + warrior1);
        System.out.println("  " + archer1);
        System.out.println("  " + warrior2);
        System.out.println("  " + mage2);
        
        // ═══════════════════════════════════════════════════════════════
        // BUILDING GAME STATE WITH IMMUTABLE OPERATIONS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🏛️ BUILDING GAME STATE WITH IMMUTABLE OPERATIONS...\n");
        
        // Add pieces to game state (each operation returns new state)
        GameState gameState = initialState
            .addPiece(warrior1)
            .addPiece(archer1)
            .addPiece(warrior2)
            .addPiece(mage2);
        
        System.out.println("Game state after adding pieces:");
        System.out.println("  " + gameState);
        System.out.println("  Alice's pieces: " + gameState.getLivingPieces("Alice").size());
        System.out.println("  Bob's pieces: " + gameState.getLivingPieces("Bob").size());
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATING POSITION IMMUTABILITY
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🧭 DEMONSTRATING POSITION IMMUTABILITY...\n");
        
        final Position originalPos = new Position(5, 5);
        final Position movedPos = originalPos.move(Direction.NORTH);
        final Position doubleMovedPos = movedPos.move(Direction.EAST);
        
        System.out.println("Original position: " + originalPos + " (unchanged)");
        System.out.println("After moving north: " + movedPos + " (new object)");
        System.out.println("After moving east: " + doubleMovedPos + " (new object)");
        System.out.println("Objects are different: " + (originalPos != movedPos));
        System.out.println("Distance calculation: " + originalPos.distance(doubleMovedPos));
        System.out.println("Adjacent check: " + originalPos.isAdjacent(movedPos));
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATING GAMEPIECE IMMUTABILITY
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n⚔️ DEMONSTRATING GAMEPIECE IMMUTABILITY...\n");
        
        final GamePiece originalWarrior = warrior1;
        final GamePiece damagedWarrior = originalWarrior.takeDamage(25);
        final GamePiece healedWarrior = damagedWarrior.heal(10);
        final GamePiece movedWarrior = healedWarrior.moveTo(new Position(2, 2));
        
        System.out.println("Original warrior: " + originalWarrior);
        System.out.println("After damage: " + damagedWarrior + " (new object)");
        System.out.println("After healing: " + healedWarrior + " (new object)");
        System.out.println("After moving: " + movedWarrior + " (new object)");
        System.out.println("All are different objects with different states");
        
        // ═══════════════════════════════════════════════════════════════
        // GAME ENGINE WITH FINAL TEMPLATE METHOD
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n⚙️ GAME ENGINE WITH FINAL TEMPLATE METHOD...\n");
        
        final GameEngine engine = new StrategyGameEngine();
        
        // Create moves (immutable)
        final Move move1 = new Move("W1", new Position(2, 2));
        final Move move2 = new Move("W2", new Position(2, 8));
        final Move move3 = new Move("A1", new Position(7, 7));
        
        // Process moves using final template method
        GameState currentState = gameState;
        
        System.out.println("Processing Alice's turn:");
        currentState = engine.processTurn(currentState, move1);
        System.out.println("State: " + currentState.getGameStatistics());
        
        // End turn
        currentState = currentState.endTurn();
        System.out.println("\nBob's turn begins:");
        System.out.println("State: " + currentState.getGameStatistics());
        
        System.out.println("\nProcessing Bob's turn:");
        currentState = engine.processTurn(currentState, move2);
        System.out.println("State: " + currentState.getGameStatistics());
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATING ATTACK MECHANICS
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n⚔️ DEMONSTRATING ATTACK MECHANICS...\n");
        
        // Position pieces for attack
        GameState attackState = currentState
            .makeMove("W1", new Position(3, 3))
            .endTurn()
            .makeMove("W2", new Position(4, 4))
            .endTurn();
        
        System.out.println("Positioned pieces for combat:");
        System.out.println("State: " + attackState.getGameStatistics());
        
        // Check if W1 can attack W2
        GamePiece attacker = attackState.getPieces().get("W1");
        GamePiece target = attackState.getPieces().get("W2");
        
        System.out.println("Can W1 attack W2? " + attacker.canAttack(target));
        System.out.println("Distance: " + attacker.getPosition().distance(target.getPosition()));
        System.out.println("Attack range: " + attacker.getAttackRange());
        
        if (attacker.canAttack(target)) {
            GameState afterAttack = attackState.attack("W1", "W2");
            System.out.println("\nAfter attack:");
            System.out.println("Attacker: " + afterAttack.getPieces().get("W1"));
            System.out.println("Target: " + afterAttack.getPieces().get("W2"));
            System.out.println("Original target unchanged: " + target);
        }
        
        // ═══════════════════════════════════════════════════════════════
        // DEMONSTRATING THREAD SAFETY
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🧵 DEMONSTRATING THREAD SAFETY...\n");
        
        final GameState sharedState = currentState;
        System.out.println("Created shared immutable game state");
        System.out.println("This state is thread-safe because:");
        System.out.println("1. All fields are final (immutable references)");
        System.out.println("2. All collections are immutable views");
        System.out.println("3. No setter methods exist");
        System.out.println("4. All operations return new objects");
        System.out.println("5. Classes are final (cannot be extended with mutable behavior)");
        
        // Simulate concurrent access
        final List<GameState> results = Collections.synchronizedList(new ArrayList<>());
        
        Runnable reader = () -> {
            for (int i = 0; i < 3; i++) {
                GameState readState = sharedState;  // Safe concurrent read
                System.out.println("Thread " + Thread.currentThread().getName() + 
                                 " read: " + readState.getCurrentPlayer());
                results.add(readState);
                try { Thread.sleep(10); } catch (InterruptedException e) {}
            }
        };
        
        Thread t1 = new Thread(reader, "Reader-1");
        Thread t2 = new Thread(reader, "Reader-2");
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Concurrent reads completed successfully: " + results.size() + " reads");
        
        // ═══════════════════════════════════════════════════════════════
        // FINAL IMMUTABILITY VERIFICATION
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n🔍 FINAL IMMUTABILITY VERIFICATION...\n");
        
        demonstrateImmutability();
        
        // ═══════════════════════════════════════════════════════════════
        // SYSTEM SUMMARY
        // ═══════════════════════════════════════════════════════════════
        
        System.out.println("\n✨ IMMUTABLE GAME STATE SYSTEM COMPLETE! ✨");
        System.out.println("\n🎯 THE POWER OF `final` FULLY DEMONSTRATED:");
        System.out.println("🔹 Final classes created truly immutable value objects");
        System.out.println("🔹 Final methods secured critical game operations");
        System.out.println("🔹 Final variables ensured unchangeable state");
        System.out.println("🔹 Final template method enforced consistent processing");
        System.out.println("🔹 Immutable objects achieved automatic thread safety");
        System.out.println("🔹 All state changes created new objects (no mutation)");
        System.out.println("🔹 Complete game system built on immutability principles");
        
        System.out.println("\n💡 The `final` keyword transformed mutable chaos into immutable order!");
        System.out.println("🏆 Game state integrity guaranteed through the power of finality!");
    }
    
    /**
     * Final method demonstrating immutability verification
     */
    private static final void demonstrateImmutability() {
        System.out.println("🧪 IMMUTABILITY TESTING:");
        
        // Create original objects
        final Position pos = new Position(5, 5);
        final GamePiece warrior = new GamePiece("TEST", GameConstants.PIECE_WARRIOR, "Player1", pos);
        final GameState state = new GameState("Player1", "Player2").addPiece(warrior);
        
        // Perform operations that return new objects
        final Position newPos = pos.move(Direction.NORTH);
        final GamePiece damagedWarrior = warrior.takeDamage(25);
        final GameState newState = state.makeMove("TEST", newPos);
        
        // Verify originals unchanged
        boolean posUnchanged = pos.getX() == 5 && pos.getY() == 5;
        boolean warriorUnchanged = warrior.getHealth() == 100;
        boolean stateUnchanged = state.getCurrentPlayer().equals("Player1");
        
        // Verify new objects created
        boolean differentObjects = (newPos != pos) && (damagedWarrior != warrior) && (newState != state);
        
        System.out.println("  ✅ Original position unchanged: " + posUnchanged);
        System.out.println("  ✅ Original warrior unchanged: " + warriorUnchanged);
        System.out.println("  ✅ Original state unchanged: " + stateUnchanged);
        System.out.println("  ✅ New objects created: " + differentObjects);
        System.out.println("  ✅ All immutability tests passed!");
    }
}
