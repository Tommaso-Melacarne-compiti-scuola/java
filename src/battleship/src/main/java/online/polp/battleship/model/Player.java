package online.polp.battleship.model;

import lombok.Getter;

@Getter
public class Player {
    private static int nextId = 0;

    private final int id;
    private final String name;
    private final Board board = new Board();

    public Player(String name) {
        this.id = nextId;
        nextId++;
        this.name = name;
    }

    /**
     * Attacks a point on this player's board
     *
     * @param point The point to attack
     * @return The result of the attack
     */
    public Hit attack(Point point) {
        return board.attack(point);
    }
}
