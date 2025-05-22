package online.polp.battleship.controllers.pojos;

import lombok.Data;
import online.polp.battleship.model.Hit;

@Data
public class AttackResponse {
    private final Hit playerHit;
    private final Hit computerHit;

    private final GameUpdate gameUpdate;
}
