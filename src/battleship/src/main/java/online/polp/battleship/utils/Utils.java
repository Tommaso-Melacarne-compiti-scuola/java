package online.polp.battleship.utils;

import online.polp.battleship.singletons.RandomSingleton;

import java.util.Random;

public class Utils {
    public static String getRandomHexColor() {
        Random random = RandomSingleton.getInstance();

        StringBuilder color = new StringBuilder("#");
        for (int i = 0; i < 6; i++) {
            int randomValue = random.nextInt(16);
            String hexValue = Integer.toHexString(randomValue);
            color.append(hexValue);
        }
        return color.toString();
    }
}
