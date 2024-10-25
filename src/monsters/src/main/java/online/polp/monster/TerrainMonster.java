package online.polp.monster;

public class TerrainMonster extends Monster {
    private static final int ATTACK_PERCENTAGE = 30;

    public TerrainMonster(String name) {
        super(name);
    }

    @Override
    protected int getDoubleAttackPercentage() {
        return ATTACK_PERCENTAGE;
    }

    @Override
    protected String getMonsterType() {
        return "Terrain Monster";
    }
}
