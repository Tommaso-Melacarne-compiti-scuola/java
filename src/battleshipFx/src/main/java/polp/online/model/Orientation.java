package polp.online.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import polp.online.singletons.RandomSingleton;

@Getter
@RequiredArgsConstructor
public enum Orientation {
    VERTICAL("Verticale"),
    HORIZONTAL("Orizzontale");

    private final String name;

    public static Orientation randomOrientation() {
        return RandomSingleton.getInstance().nextBoolean() ? VERTICAL : HORIZONTAL;
    }
}
