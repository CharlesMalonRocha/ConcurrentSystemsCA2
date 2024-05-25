package creatures;

import player.Player;

/**
 *
 * @author Charles Rocha
 */
public abstract class Monster {

    private Player owner;
    private int x, y; // Position on the board

    public Monster(Player owner, int x, int y) {
        this.owner = owner;
        this.x = x;
        this.y = y;
    }

    public Player getOwner() {
        return owner;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract String getType();
}
