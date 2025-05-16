package online.polp.battleship.controllers.pojos;

import lombok.Data;
import online.polp.battleship.model.AttackResult;

@Data
public class AttackResponse {
    private final AttackResult playerAttackResult;
    private final AttackResult computerAttackResult;

    private final GameUpdate gameUpdate;
}
