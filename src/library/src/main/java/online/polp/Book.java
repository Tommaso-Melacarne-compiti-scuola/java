package online.polp;

public record Book(String title, String author) implements Readable {
    @Override
    public ReadableType getType() {
        return ReadableType.BOOK;
    }
}
