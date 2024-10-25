package online.polp.monster;

public class MarineMonster extends Monster {
    private static final int ATTACK_PERCENTAGE = 40;

    public MarineMonster(String name) {
        super(name);
    }

    @Override
    protected int getDoubleAttackPercentage() {
        return ATTACK_PERCENTAGE;
    }
}
