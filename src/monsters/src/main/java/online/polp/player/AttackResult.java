package online.polp.player;

public record AttackResult(int initialHealth, int finalHealth, int damage, boolean isDoubleAttack,
                           TurnPlayer turnPlayer) {
}
