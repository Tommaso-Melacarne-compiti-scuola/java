package online.polp.player;

import online.polp.monster.Monster;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final String lastName;
    private final Monster monster;

    public Player(String name, String lastName, Monster monster) {
        this.name = name;
        this.lastName = lastName;
        this.monster = monster;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return name + " " + lastName;
    }

    public Monster getMonster() {
        return monster;
    }

    public ChallengeResult challenge(Player otherPlayer) {
        List<AttackResult> attackResults = new ArrayList<>();

        if (!this.monster.isAlive()) {
            return new ChallengeResult(this, otherPlayer, ChallengeFinalResult.challengerMonsterAlreadyDefeated, attackResults);
        }

        if (!otherPlayer.monster.isAlive()) {
            return new ChallengeResult(this, otherPlayer, ChallengeFinalResult.challengedMonsterAlreadyDefeated, attackResults);
        }

        int turn = 0;

        ChallengeFinalResult challengeFinalResult;

        while (true) {
            boolean isFirstPlayerTurn = turn % 2 == 0;
            TurnPlayer turnPlayer = isFirstPlayerTurn ? TurnPlayer.Challenger : TurnPlayer.Challenged;


            if (isFirstPlayerTurn) {
                AttackResult attackResult = this.monster.attack(otherPlayer.monster, turnPlayer);

                attackResults.add(attackResult);

                if (!otherPlayer.monster.isAlive()) {
                    challengeFinalResult = ChallengeFinalResult.challengerWins;
                    break;
                }
            } else {
                AttackResult attackResult = otherPlayer.monster.attack(this.monster, turnPlayer);

                attackResults.add(attackResult);

                if (!this.monster.isAlive()) {
                    challengeFinalResult = ChallengeFinalResult.challengedWins;
                    break;
                }
            }

            turn++;
        }

        return new ChallengeResult(this, otherPlayer, challengeFinalResult, attackResults);
    }

    @Override
    public String toString() {
        return "Player " + getFullName() + " " + lastName + " with monster\n" + monster;
    }
}
