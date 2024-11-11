package concurrentsystemsca2;

import creatures.Ghost;
import creatures.Vampire;
import creatures.Werewolf;
import java.util.ArrayList;
import java.util.List;
import match.Match;
import player.Player;

/**
 *
 * @author Charles Rocha
 */

/*
Description of Assessment Task
Congratulations! You’ve been hired to develop a new web-based board game called “Monster Mayhem”. This game is played on a 10x10 grid of squares, with each side of the grid belonging to one player. 
Game Mechanics:
Each round, the player with the fewest monsters on the grid gets a turn first. If there is a tie, then it is randomly decided from among those with the least monsters.
On a players turn, they may play either a vampire, a werewolf or a ghost anywhere on their edge of the grid. They may not move that monster this turn. They may also move any other monsters that they have. A monster can move any number of squares horizontally or vertically, or up to two squares diagonally. They can move over their own player’s monsters, but cannot move over other player’s monsters.
If two monsters finish on the same square, they are dealt with as follows:
●If there’s a vampire and a werewolf, the werewolf is removed
●If there’s a werewolf and a ghost, the ghost is removed
●If there’s a ghost and a vampire, the vampire is removed
●If there’s two of the same kind of monster, both are removed

A player’s turn ends when they decide so, or if they have no monsters left to move.
A round end once all players have had a turn. 
A player is eliminated once 10 of their monsters have been removed. A player wins if all other players have been eliminated.
*/
public class ConcurrentSystemsCA2 {


    public static void main(String[] args) {
        
                // Create players
        Player player1 = new Player("Player 1", 0); // Top edge
        Player player2 = new Player("Player 2", 1); // Right edge
        Player player3 = new Player("Player 3", 2); // Bottom edge
        Player player4 = new Player("Player 4", 3); // Left edge

        // Add players to the game
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        // Initialize the game
        Match game = new Match(players);

        // Add initial monsters for demonstration purposes
        game.getBoard().placeMonster(new Vampire(player1, 0, 0), 0, 0);
        game.getBoard().placeMonster(new Werewolf(player2, 9, 9), 9, 9);
        game.getBoard().placeMonster(new Ghost(player3, 0, 9), 0, 9);
        game.getBoard().placeMonster(new Vampire(player4, 9, 0), 9, 0);

        // Start the game rounds
        while (players.size() > 1) {
            game.startRound();
        }
    }
    
}
