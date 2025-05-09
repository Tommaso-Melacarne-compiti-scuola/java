package online.polp.battleship.controllers.pojos;

import lombok.Data;
import online.polp.battleship.model.Ship;

import java.util.List;

@Data
public class NewGameRequest {
    private List<Ship> ships;
}
