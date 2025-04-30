package online.polp.battleship.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AttackResult {
    MISS("Miss"), HIT("Hit"), SUNK("Sunk");

    private final String message;
}
