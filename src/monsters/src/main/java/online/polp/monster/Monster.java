package online.polp.monster;

import online.polp.player.AttackResult;
import online.polp.player.TurnPlayer;
import online.polp.utils.MonsterAttackUtils;

public abstract class Monster {
    private final String name;
    private static final int MAX_HEALTH = 500;
    // If 0, then the monster is dead
    private int health = MAX_HEALTH;
    private final int baseAttack = MonsterAttackUtils.generateRandomAttack();

    protected Monster(String name) {
        this.name = name;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public AttackResult attack(Monster other, TurnPlayer turnPlayer) {
        int doubleAttackPercentage = getDoubleAttackPercentage();

        int randomizedAttack = baseAttack;

        boolean isDoubleAttack = MonsterAttackUtils.isDoubleAttack(doubleAttackPercentage);

        if (isDoubleAttack) {
            randomizedAttack *= 2;
        }

        int initialHealth = other.getHealth();

        other.takeDamage(randomizedAttack);

        int finalHealth = other.getHealth();

        return new AttackResult(initialHealth, finalHealth, randomizedAttack, isDoubleAttack, turnPlayer);
    }


    /**
     * Returns the double attack percentage of the monster
     *
     * @return the double attack percentage
     */
    protected abstract int getDoubleAttackPercentage();

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public String toString() {
        boolean alive = isAlive();

        return "Monster " + name + " is " + (alive ? "alive" : "dead") + " with " + health + " health and " + baseAttack + " attack";

    }
}
