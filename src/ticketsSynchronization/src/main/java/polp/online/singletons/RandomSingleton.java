package polp.online.singletons;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomSingleton {
    private static final class InstanceHolder {
        private static final Random INSTANCE = new Random();
    }

    public static Random getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
