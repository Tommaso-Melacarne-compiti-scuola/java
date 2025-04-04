package online.polp;

import online.polp.singletons.RandomSingleton;

import java.util.Random;

public enum Gender {
    MALE,
    FEMALE;

    public static Gender getRandom() {
        Random random = RandomSingleton.getInstance();
        boolean b = random.nextBoolean();
        if (b) {
            return MALE;
        } else {
            return FEMALE;
        }
    }
}
