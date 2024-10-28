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

        String challengerFullName = challenger.getFullName();
        String challengedFullName = challenged.getFullName();

        switch (challengeFinalResult) {
            case challengerMonsterAlreadyDefeated:
                winnerName = challengedFullName;
                sb.append(challengedFullName).append(" wins because ").append(challengerFullName).append(" monster is already defeated\n");
                break;
            case challengedMonsterAlreadyDefeated:
                winnerName = challengerFullName;
                sb.append(challengerFullName).append(" wins because ").append(challengedFullName).append(" monster is already defeated\n");
                break;
            case challengerWins:
                winnerName = challengerFullName;
                break;
            case challengedWins:
                winnerName = challengedFullName;
                break;
        }

        sb.append(Color.YELLOW.colorize("Challenge Result: ")).append(winnerName).append(" wins");

        return sb.toString();
    }

    private void appendTurnToString(AttackResult attackResult, int turn, StringBuilder sb) {
        sb.append("Turn ").append(turn + 1).append(": ");

        String attackerName, defenderName;

        String challengerFullName = challenger.getFullName();
        String challengedFullName = challenged.getFullName();

        if (TurnPlayer.Challenger == attackResult.turnPlayer()) {
            attackerName = challengerFullName;
            defenderName = challengedFullName;
        } else {
            attackerName = challengedFullName;
            defenderName = challengerFullName;
        }

        sb.append(attackerName).append(" attacks ").append(defenderName).append(" for ").append(attackResult.damage()).append(" damage");

        if (attackResult.isDoubleAttack()) {
            sb.append(" and gets a ").append(Color.RED.colorize("double attack"));
        }

        sb.append(" (").append(attackResult.initialHealth()).append(" -> ").append(attackResult.finalHealth()).append(")");

        sb.append("\n");
    }
}
