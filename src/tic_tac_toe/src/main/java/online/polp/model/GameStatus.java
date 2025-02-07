package online.polp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameStatus {
    X_WINS("X wins!"),
    O_WINS("O wins!"),
    DRAW("It's a draw!"),
    RUNNING("");

    private final String message;
}
