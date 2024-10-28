package online.polp;

import java.util.Random;

public class RandomSingleton {
    private static Random INSTANCE;

    private RandomSingleton() {
    }

    public static Random getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Random();
        }

        return INSTANCE;
    }
}
