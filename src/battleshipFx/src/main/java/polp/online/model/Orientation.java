package polp.online.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Orientation {
    VERTICAL("Verticale"),
    HORIZONTAL("Orizzontale");

    private final String name;
}
