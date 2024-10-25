package online.polp;

import online.polp.colorize.Color;
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
                new Player("Crancesco", "Fapezio", new MarineMonster("Marine Monster"), Color.BLUE),
                new Player("Pianluca", "Giccolo", new TerrainMonster("Terrain Monster"), Color.GREEN),
                new Player("Albanella", "Antonesi", new AirMonster("Air Monster"), Color.RED)
        );

        Color.YELLOW.printWithColor("Players: ");
        for (Player player : players) {
            System.out.println(player);
        }

        printSeparator();

        List<ChallengeResult> challengeResults = startChallenges(players);

        for (int i = 0; i < challengeResults.size(); i++) {
            ChallengeResult challengeResult = challengeResults.get(i);
            Color.YELLOW.printWithColor("Challenge " + (i + 1) + ":");
            System.out.println(challengeResult.challenger().getFullName() + " vs " + challengeResult.challenged().getFullName());
            System.out.println(challengeResult);

            printSeparator();
        }
    }


    /**
     * Start the challenges between all the players
     *
     * @param players the players to challenge
     * @return the results of the challenges
     */
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

    private static void printSeparator() {
        Color.CYAN.printWithColor("--------------------------------------------------");
    }
}