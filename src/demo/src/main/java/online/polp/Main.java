package online.polp;

import java.util.Scanner;

public class Main {
    private static final int MAX_ATTEMPTS = 10;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;

    public static void main(String[] args) {
        Generator generator = new Generator(MIN_NUMBER, MAX_NUMBER);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Indovina un numero tra 1 e 100");

        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            System.out.println("Inserisci il tuo tentativo:");
            int guess = scanner.nextInt();

            Generator.GuessResult result = generator.checkGuess(guess);

            if (result == Generator.GuessResult.CORRECT) {
                System.out.println("Hai indovinato!");
                break;
            } else if (result == Generator.GuessResult.TOO_LOW) {
                System.out.println("Troppo basso");
            } else {
                System.out.println("Troppo alto");
            }
        }
    }
}
