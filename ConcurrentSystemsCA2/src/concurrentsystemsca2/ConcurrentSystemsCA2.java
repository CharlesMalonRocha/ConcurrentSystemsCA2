package concurrentsystemsca2;

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

    }
    
}
