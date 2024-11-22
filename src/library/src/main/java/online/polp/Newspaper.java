package online.polp;

public record Newspaper(String title, java.time.LocalDate date) implements Readable {
    @Override
    public ReadableType getType() {
        return ReadableType.NEWSPAPER;
    }
}
