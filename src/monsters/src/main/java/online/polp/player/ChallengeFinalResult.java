package online.polp.player;

public enum ChallengeFinalResult {
    challengedMonsterAlreadyDefeated("Challenged monster already defeated"),
    challengerMonsterAlreadyDefeated("Challenger monster already defeated"),
    challengerWins("Challenger wins"),
    challengedWins("Challenged wins");

    ChallengeFinalResult(String s) {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
