package player;

import creatures.Monster;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Charles Rocha
 */
public class Player {
        private String name;
    private int edge; // Side of the board (0: top, 1: right, 2: bottom, 3: left)
    private List<Monster> monsters = new ArrayList<>();

    public Player(String name, int edge) {
        this.name = name;
        this.edge = edge;
    }

    public String getName() {
        return name;
    }

    public int getEdge() {
        return edge;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void removeMonster(Monster monster) {
        monsters.remove(monster);
    }
}
