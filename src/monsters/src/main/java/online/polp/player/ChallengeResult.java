package online.polp.player;

import java.util.List;

public class ChallengeResult {
    private final Player challenger;
    private final Player challenged;
    private final ChallengeFinalResult challengeFinalResult;
    private final List<AttackResult> attackResults;

    public ChallengeResult(Player challenger, Player challenged, ChallengeFinalResult challengeFinalResult, List<AttackResult> attackResults) {
        this.challenger = challenger;
        this.challenged = challenged;
        this.challengeFinalResult = challengeFinalResult;
        this.attackResults = attackResults;
    }

    public Player getChallenger() {
        return challenger;
    }

    public Player getChallenged() {
        return challenged;
    }

    public ChallengeFinalResult getChallengeFinalResult() {
        return challengeFinalResult;
    }

    public List<AttackResult> getAttackResults() {
        return attackResults;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Here's what happened:\n");

        for (int i = 0; i < attackResults.size(); i++) {
            AttackResult attackResult = attackResults.get(i);

            stringBuilder.append("Turn ").append(i + 1).append(": ");


            String attackerName, defenderName;

            if (TurnPlayer.Challenger == attackResult.getTurnPlayer()) {
                attackerName = challenger.getFullName();
                defenderName = challenged.getFullName();
            } else {
                attackerName = challenged.getFullName();
                defenderName = challenger.getFullName();
            }

            stringBuilder.append(attackerName).append(" attacks ").append(defenderName).append(" for ").append(attackResult.getDamage()).append(" damage");

            if (attackResult.isDoubleAttack()) {
                stringBuilder.append(" and gets a double attack");
            }

            stringBuilder.append(" (").append(attackResult.getInitialHealth()).append(" -> ").append(attackResult.getFinalHealth()).append(")");

            stringBuilder.append("\n");
        }


        String winnerName = null;

        switch (challengeFinalResult) {
            case challengerMonsterAlreadyDefeated:
                winnerName = challenged.getFullName();
                stringBuilder.append(challenged.getFullName()).append(" wins because ").append(challenger.getFullName()).append(" monster is already defeated\n");
                break;
            case challengedMonsterAlreadyDefeated:
                winnerName = challenger.getFullName();
                stringBuilder.append(challenger.getFullName()).append(" wins because ").append(challenged.getFullName()).append(" monster is already defeated\n");
                break;
            case challengerWins:
                winnerName = challenger.getFullName();
                break;
            case challengedWins:
                winnerName = challenged.getFullName();
                break;
        }

        stringBuilder.append("Challenge Result: ").append(winnerName).append(" wins");

        return stringBuilder.toString();
    }

}
