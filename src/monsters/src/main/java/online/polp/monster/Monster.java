package online.polp.monster;

import online.polp.player.AttackResult;
import online.polp.player.TurnPlayer;
import online.polp.utils.MonsterAttackUtils;

public abstract class Monster {
    private static final int MAX_HEALTH = 500;
    private final String name;
    private final int baseAttack = MonsterAttackUtils.generateRandomAttack();

    /**
     * The health of the monster.
     * If 0, then the monster is dead
     */
    private int health = MAX_HEALTH;

    protected Monster(String name) {
        this.name = name;
    }


    /**
     * Takes the given damage from the monster's health
     *
     * @param damage the damage to take
     */
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }


    /**
     * Attacks the other monster
     *
     * @param other      the other monster to attack
     * @param turnPlayer the player that is attacking
     * @return the result of the attack
     */
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

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public String toString() {
        boolean alive = isAlive();

        return "Monster " + name + " (of type " + getMonsterType() + ") is " + (alive ? "alive" : "dead") + " with " + health + " health and " + baseAttack + " attack";
    }

    /**
     * Returns the double attack percentage of the monster
     *
     * @return the double attack percentage
     */
    protected abstract int getDoubleAttackPercentage();

    /**
     * Returns the type of the monster
     *
     * @return the type of the monster
     */
    protected abstract String getMonsterType();
}
