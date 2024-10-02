package online.polp.utils;

import java.time.LocalDate;

public class DateUtils {
    private DateUtils() {
        // This class should not be instantiated
    }

    public static LocalDate newRandomDateBounded(LocalDate min, LocalDate max) {
        long minDate = min.toEpochDay();
        long maxDate = max.toEpochDay();

        long randomDay = RandomUtils.random.nextLong(minDate, maxDate);

        return LocalDate.ofEpochDay(randomDay);
    }
}
