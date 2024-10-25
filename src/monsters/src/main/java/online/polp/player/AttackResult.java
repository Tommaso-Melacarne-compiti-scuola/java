package online.polp.player;

public class AttackResult {
    private final int initialHealth;
    private final int finalHealth;
    private final int damage;
    private final boolean isDoubleAttack;
    private final TurnPlayer turnPlayer;

    public AttackResult(int initialHealth, int finalHealth, int damage, boolean isDoubleAttack, TurnPlayer turnPlayer) {
        this.initialHealth = initialHealth;
        this.finalHealth = finalHealth;
        this.damage = damage;
        this.isDoubleAttack = isDoubleAttack;
        this.turnPlayer = turnPlayer;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public int getFinalHealth() {
        return finalHealth;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isDoubleAttack() {
        return isDoubleAttack;
    }

    public TurnPlayer getTurnPlayer() {
        return turnPlayer;
    }
}
