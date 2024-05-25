package creatures;

import player.Player;

/**
 *
 * @author Charles Rocha
 */
public class Ghost extends Monster{
    
    public Ghost(Player owner, int x, int y) {
        super(owner, x, y);
    }

    @Override
    public String getType() {
        return "Ghost";
    }
    
}
