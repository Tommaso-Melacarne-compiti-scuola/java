package online.polp;

import online.polp.monster.AirMonster;
import online.polp.monster.MarineMonster;
import online.polp.monster.TerrainMonster;
import online.polp.player.ChallengeResult;
import online.polp.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Player> players = List.of(
                new Player("Crancesco", "Fapezio", new MarineMonster("Marine Monster")),
                new Player("Pianluca", "Giccolo", new TerrainMonster("Terrain Monster")),
                new Player("Albanella", "Antonesi", new AirMonster("Air Monster"))
        );

        List<ChallengeResult> challengeResults = startChallenges(players);

        for (ChallengeResult challengeResult : challengeResults) {
            System.out.println(challengeResult.getChallenger().getFullName() + " vs " + challengeResult.getChallenged().getFullName());
            System.out.println(challengeResult);

            System.out.println("--------------------------------------------------");
        }
    }

    private static List<ChallengeResult> startChallenges(List<Player> players) {
        int startingPlayerIndex = RandomSingleton.getInstance().nextInt(players.size());

        List<ChallengeResult> challengeResults = new ArrayList<>();


        for (int i = 0; i < players.size(); i++) {
            int currentPlayerIndex = (startingPlayerIndex + i) % players.size();
            int nextPlayerIndex = (currentPlayerIndex + 1) % players.size();

            Player currentPlayer = players.get(currentPlayerIndex);
            Player nextPlayer = players.get(nextPlayerIndex);

            ChallengeResult challengeResult = currentPlayer.challenge(nextPlayer);

            challengeResults.add(challengeResult);
        }

        return challengeResults;
    }
}