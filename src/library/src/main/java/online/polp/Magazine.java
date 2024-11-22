package online.polp;

import java.time.LocalDate;

public record Magazine(String title, LocalDate date) implements Readable {
    @Override
    public ReadableType getType() {
        return ReadableType.MAGAZINE;
    }
}
