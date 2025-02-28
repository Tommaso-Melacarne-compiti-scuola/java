package polp.online.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class BattleshipModel {
    @Getter
    private final static List<Player> players = new ArrayList<>(2);

    public static void addPlayer(Player player) {
        players.add(player);
    }

    public static Player getPlayerById(int id) {
        return players.stream()
                      .filter(player -> player.getId() == id)
                      .findFirst()
                      .orElse(null);
    }
}
