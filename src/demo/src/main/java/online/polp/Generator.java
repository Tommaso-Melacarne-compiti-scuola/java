package online.polp;



public class Generator {
    private final int number;

    public enum GuessResult {
        TOO_LOW,
        TOO_HIGH,
        CORRECT
    }

    public Generator(int min_number, int max_number) {
        this.number = (int) (Math.random() * (max_number - min_number + 1) + min_number);
    }

    public GuessResult checkGuess(int guess) {
        if (guess < this.number) {
            return GuessResult.TOO_LOW;
        } else if (guess > this.number) {
            return GuessResult.TOO_HIGH;
        } else {
            return GuessResult.CORRECT;
        }
    }
}
