package online.polp;

import java.time.LocalDateTime;

public class Dumpster {
    private int id;
    private static int ID_COUNTER = 0;

    private DumpsterType type;

    private Location location;
    private LocalDateTime lastEmptied;
    private Volume volume;

    public Dumpster(DumpsterType type, Location location, LocalDateTime lastEmptied, Volume volume) {
        this.id = ID_COUNTER++;
        this.type = type;
        this.location = location;
        this.lastEmptied = lastEmptied;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getIdCounter() {
        return ID_COUNTER;
    }

    public static void setIdCounter(int idCounter) {
        ID_COUNTER = idCounter;
    }

    public DumpsterType getType() {
        return type;
    }

    public void setType(DumpsterType type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDateTime getLastEmptied() {
        return lastEmptied;
    }

    public void setLastEmptied(LocalDateTime lastEmptied) {
        this.lastEmptied = lastEmptied;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }
}
