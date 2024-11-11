package match;

import creatures.Ghost;
import creatures.Monster;
import creatures.Vampire;
import creatures.Werewolf;
import match.board.Board;
import match.monster.*;
import match.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Charles Rocha
 */
public class Match {

    private List<Player> players;
    private Board board;
    private int currentPlayerIndex = 0;
    private Random random = new Random();

    public Match(List<Player> players) {
        this.players = players;
        this.board = new Board();
    }

    public void startRound() {
        determineTurnOrder();
        for (Player player : players) {
            playerTurn(player);
        }
        endRound();
    }

    private void determineTurnOrder() {
        players.sort((p1, p2) -> Integer.compare(p1.getMonsters().size(), p2.getMonsters().size()));
        if (players.get(0).getMonsters().size() == players.get(1).getMonsters().size()) {
            Collections.shuffle(players);
        }
    }

    private void playerTurn(Player player) {
        // Player places a new monster on their edge of the board
        placeNewMonster(player);

        // Player moves existing monsters
        for (Monster monster : new ArrayList<>(player.getMonsters())) {
            moveMonster(monster);
        }

        // Handle interactions on the board
        resolveInteractions();
    }

    private void placeNewMonster(Player player) {
        // Determine the position on the player's edge and type of monster to place
        int x = 0, y = 0;
        switch (player.getEdge()) {
            case 0: // Top edge
                x = random.nextInt(board.getSize());
                y = 0;
                break;
            case 1: // Right edge
                x = board.getSize() - 1;
                y = random.nextInt(board.getSize());
                break;
            case 2: // Bottom edge
                x = random.nextInt(board.getSize());
                y = board.getSize() - 1;
                break;
            case 3: // Left edge
                x = 0;
                y = random.nextInt(board.getSize());
                break;
        }

        Monster newMonster = createRandomMonster(player, x, y);
        board.placeMonster(newMonster, x, y);
        player.addMonster(newMonster);
    }

    private Monster createRandomMonster(Player player, int x, int y) {
        int monsterType = random.nextInt(3);
        switch (monsterType) {
            case 0:
                return new Vampire(player, x, y);
            case 1:
                return new Werewolf(player, x, y);
            case 2:
                return new Ghost(player, x, y);
            default:
                return new Vampire(player, x, y); // Fallback, should never happen
        }
    }

    private void moveMonster(Monster monster) {
        // Logic to move a monster on the board
        int newX = monster.getX();
        int newY = monster.getY();

        // For simplicity, we randomly move the monster
        int direction = random.nextInt(4);
        switch (direction) {
            case 0: // Move up
                newY = Math.max(0, newY - 1);
                break;
            case 1: // Move down
                newY = Math.min(board.getSize() - 1, newY + 1);
                break;
            case 2: // Move left
                newX = Math.max(0, newX - 1);
                break;
            case 3: // Move right
                newX = Math.min(board.getSize() - 1, newX + 1);
                break;
        }

        if (!board.isOccupied(newX, newY)) {
            board.moveMonster(monster, newX, newY);
        }
    }

    private void resolveInteractions() {
        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                Monster monster1 = board.getMonster(x, y);
                if (monster1 != null) {
                    for (int i = x; i < board.getSize(); i++) {
                        for (int j = (i == x ? y + 1 : 0); j < board.getSize(); j++) {
                            Monster monster2 = board.getMonster(i, j);
                            if (monster2 != null && monster1 != monster2) {
                                handleInteraction(monster1, monster2);
                            }
                        }
                    }
                }
            }
        }
    }

    private void handleInteraction(Monster monster1, Monster monster2) {
        String type1 = monster1.getType();
        String type2 = monster2.getType();
        if (type1.equals(type2)) {
            board.removeMonster(monster1.getX(), monster1.getY());
            board.removeMonster(monster2.getX(), monster2.getY());
            monster1.getOwner().removeMonster(monster1);
            monster2.getOwner().removeMonster(monster2);
        } else if ((type1.equals("Vampire") && type2.equals("Werewolf"))
                || (type1.equals("Werewolf") && type2.equals("Ghost"))
                || (type1.equals("Ghost") && type2.equals("Vampire"))) {
            board.removeMonster(monster2.getX(), monster2.getY());
            monster2.getOwner().removeMonster(monster2);
        } else {
            board.removeMonster(monster1.getX(), monster1.getY());
            monster1.getOwner().removeMonster(monster1);
        }
    }

    private void endRound() {
        List<Player> eliminatedPlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.getMonsters().size() >= 10) {
                eliminatedPlayers.add(player);
            }
        }
        players.removeAll(eliminatedPlayers);

        if (players.size() == 1) {
            declareWinner(players.get(0));
        }
    }

    private void declareWinner(Player winner) {
        System.out.println(winner.getName() + " wins!");
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
}
