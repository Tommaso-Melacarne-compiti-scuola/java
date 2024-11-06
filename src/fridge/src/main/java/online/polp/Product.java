package online.polp;

import java.io.Serializable;
import java.time.LocalDate;

public record Product(int id, String description, LocalDate expiryDate, double calorie) implements Serializable {
    @Override
    public String toString() {
        return "Product: id=" + id + ", description=" + description + ", expiryDate=" + expiryDate + ", calorie=" + calorie;
    }
}
