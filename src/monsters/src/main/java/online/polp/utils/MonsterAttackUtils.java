package online.polp.utils;

import online.polp.RandomSingleton;

public class MonsterAttackUtils {
    // Minimum and maximum attack values, both inclusive
    public static final int MIN_ATTACK = 100;
    public static final int MAX_ATTACK = 200;


    /**
     * Generates a random attack value for a new monster
     *
     * @return the random attack value
     */
    public static int generateRandomAttack() {
        return RandomSingleton.getInstance().nextInt(MIN_ATTACK, MAX_ATTACK + 1);
    }


    /**
     * Returns whether the monster will perform a double attack, based on the double attack percentage
     *
     * @param doubleAttackPercentage the double attack percentage
     * @return whether the monster will perform a double attack
     */
    public static boolean isDoubleAttack(int doubleAttackPercentage) {
        return RandomSingleton.getInstance().nextInt(1, 101) <= doubleAttackPercentage;
    }
}
