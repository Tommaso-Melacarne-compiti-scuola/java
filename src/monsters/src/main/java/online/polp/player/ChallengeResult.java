package online.polp.player;

import online.polp.colorize.Color;

import java.util.List;

public record ChallengeResult(Player challenger, Player challenged, ChallengeFinalResult challengeFinalResult,
                              List<AttackResult> attackResults) {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < attackResults.size(); i++) {
            appendTurnToString(attackResults.get(i), i, sb);
        }

        String winnerName = null;

        switch (challengeFinalResult) {
            case challengerMonsterAlreadyDefeated:
                winnerName = challenged.getFullName();
                sb.append(challenged.getFullName()).append(" wins because ").append(challenger.getFullName()).append(" monster is already defeated\n");
                break;
            case challengedMonsterAlreadyDefeated:
                winnerName = challenger.getFullName();
                sb.append(challenger.getFullName()).append(" wins because ").append(challenged.getFullName()).append(" monster is already defeated\n");
                break;
            case challengerWins:
                winnerName = challenger.getFullName();
                break;
            case challengedWins:
                winnerName = challenged.getFullName();
                break;
        }

        sb.append(Color.YELLOW.colorize("Challenge Result: ")).append(winnerName).append(" wins");

        return sb.toString();
    }

    private void appendTurnToString(AttackResult attackResult, int turn, StringBuilder sb) {
        sb.append("Turn ").append(turn + 1).append(": ");
        String attackerName, defenderName;
        if (TurnPlayer.Challenger == attackResult.turnPlayer()) {
            attackerName = challenger.getFullName();
            defenderName = challenged.getFullName();
        } else {
            attackerName = challenged.getFullName();
            defenderName = challenger.getFullName();
        }
        sb.append(attackerName).append(" attacks ").append(defenderName).append(" for ").append(attackResult.damage()).append(" damage");
        if (attackResult.isDoubleAttack()) {
            sb.append(" and gets a ").append(Color.RED.colorize("double attack"));
        }
        sb.append(" (").append(attackResult.initialHealth()).append(" -> ").append(attackResult.finalHealth()).append(")");
        sb.append("\n");
    }
}
