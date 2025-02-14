package polp.online.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Player {
    private final String name;
    private final Board board = new Board();
}
