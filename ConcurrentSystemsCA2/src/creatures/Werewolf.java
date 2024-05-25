package creatures;

import player.Player;

/**
 *
 * @author Charles Rocha
 */
public class Werewolf extends Monster{
    
    public Werewolf(Player owner, int x, int y) {
        super(owner, x, y);
    }

    @Override
    public String getType() {
        return "Werewolf";
    }
    
}
