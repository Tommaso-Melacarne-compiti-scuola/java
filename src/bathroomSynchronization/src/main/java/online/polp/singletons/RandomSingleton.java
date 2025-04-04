package online.polp.singletons;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomSingleton {
    private static class InstanceHolder {
        private static final Random instance = new Random();
    }

    public static Random getInstance() {
        return InstanceHolder.instance;
    }
}