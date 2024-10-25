package online.polp;

import java.util.Random;

public class RandomSingleton {
    private static final Random instance = new Random();

    private RandomSingleton() {
    }

    public static Random getInstance() {
        return instance;
    }
}
