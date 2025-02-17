package polp.online.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Player {
    private final String name;
    private final Board board = new Board();

    /**
     * Attacks a point on this player's board
     *
     * @param point The point to attack
     * @return The result of the attack
     */
    public AttackResult attack(Point point) {
        return board.attack(point);
    }
}
