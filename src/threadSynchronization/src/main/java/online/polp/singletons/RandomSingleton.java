package online.polp.singletons;

import java.util.Random;

public class RandomSingleton {
    private static Random INSTANCE;

    private RandomSingleton() {
        // Private constructor to prevent instantiation
    }

    public synchronized static Random getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Random();
        }
        return INSTANCE;
    }
}
