package polp.online.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import polp.online.singletons.RandomSingleton;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Orientation {
    VERTICAL("Verticale"),
    HORIZONTAL("Orizzontale");

    private final String name;

    public static Orientation randomOrientation() {
        return RandomSingleton.getInstance().nextBoolean() ? VERTICAL : HORIZONTAL;
    }

    public static List<String> getOrientations() {
        return Arrays.stream(Orientation.values())
                     .map(Orientation::getName)
                     .toList();
    }

    public static Orientation fromString(String name) {
        return Arrays.stream(Orientation.values())
                     .filter(orientation -> orientation.getName().equalsIgnoreCase(name))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Invalid orientation: " + name));
    }
}
