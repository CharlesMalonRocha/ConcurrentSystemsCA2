package creatures;

import player.Player;

/**
 *
 * @author Charles Rocha
 */
public class Vampire extends Monster {

    public Vampire(Player owner, int x, int y) {
        super(owner, x, y);
    }

    @Override
    public String getType() {
        return "Vampire";
    }
}
