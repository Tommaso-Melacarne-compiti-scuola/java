package online.polp.monster;

public class AirMonster extends Monster{
    private static final int ATTACK_PERCENTAGE = 20;

    public AirMonster(String name) {
        super(name);
    }

    @Override
    protected int getDoubleAttackPercentage() {
        return ATTACK_PERCENTAGE;
    }
}
